package com.qst.foodie.servlet;
import java.io.IOException;
import java.io.PrintWriter;

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
import com.qst.foodie.dao.ShopDAO;
@WebServlet("/AddMenuServlet")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig servletConfig;
	
    public AddMenuServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		this.servletConfig = config;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
			String[] type = {"JPG","jpg"};
			// �ж��ϴ��ļ��������Ƿ���ȷ
			int place = java.util.Arrays.binarySearch(type, fileType);
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
					ShopDAO dao1 = new ShopDAO();
					MenuDAO dao = new MenuDAO();
					int shop_id = dao1.searchByShopname(su.getRequest().getParameter("shop_name"));
					String menu_name = su.getRequest().getParameter("menu_name");
					float menu_price = Float.parseFloat(su.getRequest().getParameter("menu_price"));
					String menu_content = su.getRequest().getParameter("menu_content");
					Menu menu = new Menu();
					menu.setMenu_name(menu_name);
					menu.setMenu_content(menu_content);
					menu.setMenu_price(menu_price);
					menu.setMenu_image(fileName);
					dao.save(menu, shop_id);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println("<script LANGUAGE='javascript'> alert('��ӳɹ���');location.replace(document.referrer);</script>");
	}
 
	}


