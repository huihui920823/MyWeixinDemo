package com.huihui.po;

/**
 * ͼƬ��Ϣ
 * 
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage {

	private Image Image;

	private String PicUrl;// ͼƬ���ӣ���ϵͳ���ɣ�

	private String MsgId;// ��Ϣid��64λ����

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
