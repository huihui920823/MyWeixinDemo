package com.huihui.po;

/**
 * ������Ϣ
 * @author Administrator
 *
 */
public class BaseMessage {

	private String ToUserName;// ������΢�ź�
	private String FromUserName;// ���ͷ��ʺţ�һ��OpenID��
	private long CreateTime;// ��Ϣ����ʱ�� �����ͣ�
	private String MsgType;// text
	


	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
