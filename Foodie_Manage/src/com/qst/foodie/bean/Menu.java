package com.qst.foodie.bean;

public class Menu {
	private int shop_id;            //�̵�ID
	private int menu_id;			//�˵�ID
	private String menu_name;		//�˵���
	private String menu_content;	//�˵����
	private double menu_price;		//�˵�����
	private String menu_image;		//��ƷͼƬ
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_content() {
		return menu_content;
	}
	public void setMenu_content(String menu_content) {
		this.menu_content = menu_content;
	}
	public double getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(double menu_price) {
		this.menu_price = menu_price;
	}
	public String getMenu_image() {
		return menu_image;
	}
	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}
}
