package com.huihui.po;

/**
 * ��Ƶ��Ϣ
 * 
 * @author Administrator
 *
 */
public class VideoMessage extends BaseMessage {

	private Video Video;
	private String ThumbMediaId;// ��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
	private String MsgId;// ��Ϣid��64λ����

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

}
