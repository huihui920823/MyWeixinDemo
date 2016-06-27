package com.huihui.po;

/**
 * 图片消息
 * 
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage {

	private Image Image;

	private String PicUrl;// 图片链接（由系统生成）

	private String MsgId;// 消息id，64位整型

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
