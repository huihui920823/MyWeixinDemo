package com.huihui.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.huihui.po.AccessToken;
import com.huihui.util.WeixinUtil;

import net.sf.json.JSONObject;


/*
 * ע������
�ϴ�����ʱ��ý���ļ��и�ʽ�ʹ�С���ƣ����£�
    ͼƬ��image��: 1M��֧��JPG��ʽ
    ������voice����2M�����ų��Ȳ�����60s��֧��AMR\MP3��ʽ
    ��Ƶ��video����10MB��֧��MP4��ʽ
    ����ͼ��thumb����64KB��֧��JPG��ʽ
ý���ļ��ں�̨����ʱ��Ϊ3�죬��3���media_idʧЧ��
 */
public class WeixinTest {
	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE����\\weixin\\WebContent\\res\\chengxuyuan.jpg";//ͼƬ
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "image");
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE����\\weixin\\WebContent\\res\\�����.mp3";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "voice");//����
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE����\\weixin\\WebContent\\res\\С����.mp4";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "video");//��Ƶ
//		System.out.println(mediaId);
		
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		String path = "E:\\javaEE����\\weixin\\WebContent\\res\\mysql.jpg";
//		String mediaId = WeixinUtil.upload(path, accessToken.getToken(), "thumb");//����ͼ
//		System.out.println(mediaId);
		
		//�����˵�
		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
		String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		int result = WeixinUtil.createMenu(accessToken.getToken(), menu);
		if(result==0){
			System.out.println("�����˵��ɹ���");
		}else{
			System.out.println("�����룺"+result);
		}
		
//		//��ѯ�˵�
//		AccessToken accessToken = WeixinUtil.getAccessToken();
//		JSONObject jsonObj = WeixinUtil.GetMenu(accessToken.getToken());
//		System.out.println(jsonObj);
		
//		//ɾ���˵�
//		AccessToken accessToken = WeixinUtil.getCheckAccessToken();
//		int result = WeixinUtil.deleteMenu(accessToken.getToken());
//		if(result==0){
//			System.out.println("ɾ���˵��ɹ���");
//		}else{
//			System.out.println("�����룺"+result);
//		}
		
	}
}
