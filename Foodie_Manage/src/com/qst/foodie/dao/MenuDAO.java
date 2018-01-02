package com.qst.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.foodie.bean.Menu;
import com.qst.foodie.bean.Shop;
import com.qst.foodie.util.DBUtil;

public class MenuDAO {
	
	/**
	 * ���ݲ˵�ID������Ϣ
	 * @throws SQLException 
	 */
	public void update(Menu menu) throws SQLException{
	Connection conn = DBUtil.getConnection();
	PreparedStatement pstmt = null;	
	String sql = "UPDATE tb_menu SET menu_name=?, menu_content=?, menu_price=?, menu_image=? WHERE menu_id=?";
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menu.getMenu_name());
		pstmt.setString(2, menu.getMenu_content());
		pstmt.setFloat(3, (float) menu.getMenu_price());
		pstmt.setString(4, menu.getMenu_image());
		pstmt.setInt(5, menu.getMenu_id());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeJDBC(null, pstmt, conn);
	}	
	}
	
	/**
	 * ���ݲ˵�IDɾ����Ϣ
	 * @throws SQLException 
	 */
	public void delete(int menu_id) throws SQLException{
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;		
		String sql = "delete from tb_menu where menu_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menu_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	/**
	 * ���ݲ˵�ID���Ҳ˵���Ϣ
	 * @throws SQLException 
	 */
	public Menu getMenu(int menu_id) throws SQLException{
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Menu menu = new Menu();
		String sql = "select * from tb_menu where menu_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menu_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				menu.setShop_id(rs.getInt("shop_id"));
				menu.setMenu_id(rs.getInt("menu_id"));
				menu.setMenu_name(rs.getString("menu_name"));
				menu.setMenu_price(rs.getFloat("menu_price"));
				menu.setMenu_content(rs.getString("menu_content"));
				menu.setMenu_image(rs.getString("menu_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return menu;
	}
	
	/**
	 * �����Ʒ
	 * @throws SQLException 
	 */
	public void save(Menu menu, int shop_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_menu(shop_id, menu_name, menu_content, menu_price, menu_image) VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shop_id);
			pstmt.setString(2, menu.getMenu_name());
			pstmt.setString(3, menu.getMenu_content());
			pstmt.setFloat(4, (float) menu.getMenu_price());
			pstmt.setString(5, menu.getMenu_image());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
}
