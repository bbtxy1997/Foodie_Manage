package com.qst.foodie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.foodie.bean.User;
import com.qst.foodie.dao.UserDAO;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {

	public UserUpdateServlet(){
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String info = request.getParameter("info");
		UserDAO dao = new UserDAO();
		if (info.equals("updateinfo")){			//�޸ĸ�����Ϣ
			String realname = request.getParameter("realname");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			User userud = (User) request.getSession().getAttribute("user");
			userud.setUser_realname(realname);
			userud.setUser_sex(sex);
			userud.setUser_mail(email);
			try {
				dao.update(userud);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("id: " + userud.getUser_id());
			System.out.println("realname: " + userud.getUser_realname());
			response.getWriter().println("<script LANGUAGE='javascript'> alert('�޸ĳɹ���');history.go(-1);</script>");
			
		} else if (info.equals("updatepsw")){	//�޸�����
			User userpw = (User) request.getSession().getAttribute("user");
			String oldPsw = null;
			try {
				oldPsw = dao.searchById(userpw.getUser_id()).getUser_password();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String psw = request.getParameter("psw");
			String newPsw = request.getParameter("psw1");
			try {
				if (oldPsw.equals(psw)){	//ԭ������ȷ
					userpw.setUser_password(newPsw);
					dao.update(userpw);
					response.getWriter().println("<script LANGUAGE='javascript'> alert('�����޸ĳɹ���');history.go(-1);</script>");
				} else {					//ԭ�������
					response.getWriter().println("<script LANGUAGE='javascript'> alert('ԭ�����������');history.go(-1);</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (info.equals("findpsw")){		//�һ�����
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String psw = request.getParameter("password1");
			User user = new User();
			try {
				user = dao.searchByUsername(username);
				if (email.equals(user.getUser_mail())){		//������ȷ
					user.setUser_password(psw);
					dao.update(user);
					response.getWriter().println("<script LANGUAGE='javascript'> alert('�����޸ĳɹ���');self.location='register.jsp'</script>");
				} else {									//�������
					response.getWriter().println("<script LANGUAGE='javascript'> alert('�����������');history.go(-1);</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
 
	}

}
