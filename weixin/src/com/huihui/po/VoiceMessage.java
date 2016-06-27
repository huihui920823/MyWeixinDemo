package com.huihui.po;

/**
 * 语音消息（开启了语音识别）
 * 
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseMessage {
	
	private Voice Voice;
	private String Format;// 语音格式，如amr，speex等
	private String MsgID;// 消息id，64位整型
	private String Recognition;// 语音识别结果，UTF8编码

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
