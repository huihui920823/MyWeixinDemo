package com.huihui.po;

public class Music {

	private String Title;// ���ֱ���
	private String Description;// ��������
	private String MusicUrl;// ��������
	private String HQMusicUrl;// �������������ӣ�WIFI��������ʹ�ø����Ӳ�������
	private String ThumbMediaId;// ����ͼ��ý��id��ͨ���زĹ����еĽӿ��ϴ���ý���ļ����õ���id

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
