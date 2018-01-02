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
				//新建一个SmartUpload对象
				SmartUpload su = new SmartUpload();
				//上传初始化
				su.initialize(servletConfig, request, response);
				//限制每个上传文件的最大长度
				su.setMaxFileSize(1000000);
				//限制总上传数据的长度
				su.setTotalMaxFileSize(200000);
				//设定允许上传的文件（通过扩展名限制），仅允许jpg，png文件
				su.setAllowedFilesList("jpg,png");
				//设定禁止上传的文件（通过扩展名限制），禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件
				su.setDeniedFilesList("exe,bat,jsp,htm,html");
				//上传文件
				su.upload();
				//获取上传的文件操作
				Files files = su.getFiles();
				//获取单个文件
				File singleFile = files.getFile(0);
				//获取上传文件的扩展名
				String fileType = singleFile.getFileExt();
				//设置上传文件的扩展名
				String[] type1 = {"JPG","jpg"};
				// 判断上传文件的类型是否正确
				int place = java.util.Arrays.binarySearch(type1, fileType);
				//判断文件扩展名是否正确
				if (place != -1){
					//判断该文件是否被选择
					if (!singleFile.isMissing()){
						String picSize = String.valueOf(singleFile.getSize());					
						//以系统时间作为上传文件名称，设置上传完整路径
						String fileName = String.valueOf(System.currentTimeMillis());
						String filedir = "images/" + fileName + "." + singleFile.getFileExt();
						String smalldir = "samllImages/" + fileName + "." + singleFile.getFileExt();				
						//执行上传操作
						singleFile.saveAs(filedir, File.SAVEAS_VIRTUAL);
						System.out.println("上传至： " + filedir);
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
			response.getWriter().println("<script LANGUAGE='javascript'> alert('修改成功！');location.replace(document.referrer);</script>");
		}
		}
	}

