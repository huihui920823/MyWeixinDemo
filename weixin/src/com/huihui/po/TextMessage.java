package com.huihui.po;

/**
 * �ı���Ϣ
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage {

	private String Content;// �ı���Ϣ����
	private String MsgId;// ��Ϣid��64λ����
	
	

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
