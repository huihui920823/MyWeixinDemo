package com.huihui.po;

public class Button {
	
	private String type;//�˵�����Ӧ��������
	private String name;//�˵����⣬������16���ֽڣ��Ӳ˵�������40���ֽ�
	private Button[] sub_button;//�����˵����飬����ӦΪ1~5��
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
	

}
