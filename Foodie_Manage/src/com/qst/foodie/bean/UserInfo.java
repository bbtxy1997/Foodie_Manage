package com.qst.foodie.bean;
/**
 * �û���Ϣ��
 * @author ��׿��
 *
 */
public class UserInfo {
	private int id;				//������ϢID
	private int user_id;		//�û�ID�������û���
	private String address;		//�ͻ���ַ
	private String tel;			//�û��绰
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
