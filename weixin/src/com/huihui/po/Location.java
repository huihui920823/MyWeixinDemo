package com.huihui.po;

public class Location extends BaseMessage{

	private String Location_X;// ����λ��ά��
	private String Location_Y;// ����λ�þ���
	private String Scale;// ��ͼ���Ŵ�С
	private String Label;// ����λ����Ϣ
	private String MsgId;// ��Ϣid��64λ����

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

}
