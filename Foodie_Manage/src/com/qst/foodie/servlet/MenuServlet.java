package com.qst.foodie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.qst.foodie.bean.Menu;
import com.qst.foodie.dao.MenuDAO;
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
	private ServletConfig servletConfig;

	public MenuServlet() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		this.servletConfig = config;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MenuDAO dao = new MenuDAO();
		String type = request.getParameter("type");
		if("delete".equals(type)){
			int menu_id = Integer.parseInt(request.getParameter("menu_id"));
			try {
				dao.delete(menu_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().println("<script LANGUAGE='javascript'>location.replace(document.referrer);</script>");
		}else if("updateSelect".equals(type)){
			int menu_id = Integer.parseInt(request.getParameter("menu_id"));
			try {
				Menu menu = dao.getMenu(menu_id);
				request.setAttribute("menu", menu);
				request.getRequestDispatcher("update_food.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("update".equals(type)){
			try {
				//�½�һ��SmartUpload����
				SmartUpload su = new SmartUpload();
				//�ϴ���ʼ��
				su.initialize(servletConfig, request, response);
				//����ÿ���ϴ��ļ�����󳤶�
				su.setMaxFileSize(1000000);
				//�������ϴ����ݵĳ���
				su.setTotalMaxFileSize(200000);
				//�趨�����ϴ����ļ���ͨ����չ�����ƣ���������jpg��png�ļ�
				su.setAllowedFilesList("jpg,png");
				//�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�����ֹ�ϴ�����exe,bat,jsp,htm,html��չ�����ļ���û����չ�����ļ�
				su.setDeniedFilesList("exe,bat,jsp,htm,html");
				//�ϴ��ļ�
				su.upload();
				//��ȡ�ϴ����ļ�����
				Files files = su.getFiles();
				//��ȡ�����ļ�
				File singleFile = files.getFile(0);
				//��ȡ�ϴ��ļ�����չ��
				String fileType = singleFile.getFileExt();
				//�����ϴ��ļ�����չ��
				String[] type1 = {"JPG","jpg"};
				// �ж��ϴ��ļ��������Ƿ���ȷ
				int place = java.util.Arrays.binarySearch(type1, fileType);
				//�ж��ļ���չ���Ƿ���ȷ
				if (place != -1){
					//�жϸ��ļ��Ƿ�ѡ��
					if (!singleFile.isMissing()){
						String picSize = String.valueOf(singleFile.getSize());					
						//��ϵͳʱ����Ϊ�ϴ��ļ����ƣ������ϴ�����·��
						String fileName = String.valueOf(System.currentTimeMillis());
						String filedir = "images/" + fileName + "." + singleFile.getFileExt();
						String smalldir = "samllImages/" + fileName + "." + singleFile.getFileExt();				
						//ִ���ϴ�����
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);
						System.out.println("�ϴ����� " + filedir);
						Menu menu = new Menu();
						menu.setMenu_id(Integer.parseInt(request.getParameter("menu_id")));
						menu.setMenu_name(request.getParameter("menu_name"));
						menu.setMenu_content(request.getParameter("menu_content"));
						menu.setMenu_price(Float.parseFloat(request.getParameter("menu_price")));
						menu.setMenu_image(fileName);
						dao.update(menu);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().println("<script LANGUAGE='javascript'> alert('�޸ĳɹ���');location.replace(document.referrer);</script>");
		}
		}
	}

