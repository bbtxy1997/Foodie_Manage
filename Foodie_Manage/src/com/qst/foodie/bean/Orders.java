package com.qst.foodie.bean;

import java.util.Date;

public class Orders {
	private int order_id;			//����ID
	private int user_id;			//�û�ID�������û���
	private int menu_id;			//�˵�ID�����ղ˵���
	private int order_num;			//������Ʒ����
	private String order_notice;	//��ζҪ��
	private String states;			//��������״̬
	private float price;
	private Date order_time;
	private String eval;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getOrder_notice() {
		return order_notice;
	}
	public void setOrder_notice(String order_notice) {
		this.order_notice = order_notice;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getEval() {
		return eval;
	}
	public void setEval(String eval) {
		this.eval = eval;
	}
	
}
