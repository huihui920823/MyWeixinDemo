package com.huihui.po;

import java.util.List;

/**
 * ͼ����Ϣ�б�
 * 
 * @author Administrator
 *
 */
public class NewsMessage extends BaseMessage {

	private int ArticleCount;// ͼ����Ϣ����������Ϊ10������
	private List<News> Articles;// ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ,ע�⣬���ͼ��������10���򽫻�����Ӧ

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}

}
