package com.huihui.po;

/**
 * ������Ϣ������������ʶ��
 * 
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseMessage {
	
	private Voice Voice;
	private String Format;// ������ʽ����amr��speex��
	private String MsgID;// ��Ϣid��64λ����
	private String Recognition;// ����ʶ������UTF8����

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

}
