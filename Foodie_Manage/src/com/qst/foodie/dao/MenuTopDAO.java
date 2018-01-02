package com.qst.foodie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.foodie.bean.Menu;
import com.qst.foodie.util.DBUtil;



public class MenuTopDAO {
	
	/**
	 * ���ݲ˵�IDɾ���Ƽ��˵�
	 * @throws SQLException 
	 */
	public void remove(int menu_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;		
		String sql = "delete from tb_menu_top where menu_id=?";
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
	 * ��ȡ�Ƽ��˵�ID
	 */
	public List<Integer> getTopMenuList() throws SQLException{
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<Integer>();
		String sql = "select menu_id from tb_menu_top";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
			   list.add(rs.getInt("menu_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;

	}
	
	/**
	 * ����Ƽ��˵�
	 * @throws SQLException 
	 */
	public void addTop(int menu_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;		
		String sql = "INSERT INTO tb_menu_top(menu_id) VALUES(?)";
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
}
