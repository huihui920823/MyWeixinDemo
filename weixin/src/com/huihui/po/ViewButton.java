package com.huihui.po;

//view类型的菜单
public class ViewButton extends Button{
	
	private String url;//网页链接，用户点击菜单可打开链接，不超过256字节

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
