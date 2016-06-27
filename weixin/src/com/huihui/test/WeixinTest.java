package com.huihui.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.huihui.po.AccessToken;
import com.huihui.util.WeixinUtil;

import net.sf.json.JSONObject;


/*
 * 注意事项
上传的临时多媒体文件有格式和大小限制，如下：
    图片（image）: 1M，支持JPG格式
    语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
    视频（video）：10MB，支持MP4格式
    缩略图（thumb）：64KB，支持JPG格式
媒体文件在后台保存时间为3天，即3天后media_id失效。
 */
public class WeixinTest {
	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE代码\\weixin\\WebContent\\res\\chengxuyuan.jpg";//图片
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "image");
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE代码\\weixin\\WebContent\\res\\粤语歌.mp3";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "voice");//语音
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE代码\\weixin\\WebContent\\res\\小生活.mp4";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "video");//视频
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE代码\\weixin\\WebContent\\res\\mysql.jpg";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "thumb");//缩略图
//		System.out.println(mediaId);
		
		//创建菜单
		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
		String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		int result = WeixinUtil.createMenu(accessToken.getToken(), menu);
		if(result==0){
			System.out.println("创建菜单成功！");
		}else{
			System.out.println("错误码："+result);
		}
		
//		//查询菜单
//		AccessToken accessToken = WeixinUtil.getAccessToken();
//		JSONObject jsonObj = WeixinUtil.GetMenu(accessToken.getToken());
//		System.out.println(jsonObj);
		
//		//删除菜单
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		int result = WeixinUtil.deleteMenu(accessToken.getToken());
//		if(result==0){
//			System.out.println("删除菜单成功！");
//		}else{
//			System.out.println("错误码："+result);
//		}
		
	}
}
