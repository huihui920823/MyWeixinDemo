package com.huihui.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.huihui.po.AccessToken;
import com.huihui.po.Button;
import com.huihui.po.ClickButton;
import com.huihui.po.Menu;
import com.huihui.po.ViewButton;

import net.sf.json.JSONObject;

public class WeixinUtil {

	// 这里的APPID和APPSECRET使用的是微信测试公众号中的，因为我的公众号没有素材管理接口的权限和其他的很多接口权限
	private static final String APPID = "wx47ba959cac78b0a9";
	private static final String APPSECRET = "95501762282cd222122c89bad3b20b00";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 文件上传
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken, String type)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head); 

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if ("thumb".equals(type)) {
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);// 接收请求后的返回的结果
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url, String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	/**
	 * 获取经过有效期检测的access_token
	 * @return
	 */
	public static AccessToken getCheckAccessToken() throws IOException{
		Properties pp = new Properties();
		String filePath = "E:\\javaEE代码\\weixin\\files\\token.properties";
		FileInputStream fis = new FileInputStream(filePath);
		pp.load(fis);
		String myToken = pp.getProperty("token");
		String myExpiresIn = pp.getProperty("expiresIn");
		String time = pp.getProperty("time");
		fis.close();
		
		if (!"".equals(myToken) && myToken != null && !"".equals(myExpiresIn) && myExpiresIn != null && !"".equals(time)
				&& time != null) {// 说明之前写入过凭证内容 
			System.out.println("-----------之前已经写入过-----------");
			long pastTime = Long.valueOf(time);//之前保存的时间
			long expiresIn = Integer.parseInt(myExpiresIn);//时间差
			long nowTime = System.currentTimeMillis();//当前时间
			
			if(nowTime-pastTime>=expiresIn*1000){//如果凭证超时就要重新获取新的凭证
				System.out.println("---------再次写入新的凭证-----------");
				AccessToken token = WeixinUtil.getAccessToken();
				pp.setProperty("token", token.getToken());
				pp.setProperty("expiresIn", String.valueOf(token.getExpiresIn()));
				pp.setProperty("time", System.currentTimeMillis()+"");

				FileOutputStream fos = new FileOutputStream(filePath);
				pp.store(fos, "update data");
				
				fos.close();
				return token;
			}else{//凭证没有过期，继续使用该凭证
				System.out.println("---------凭证没有过期----------");
				AccessToken token = new AccessToken();
				token.setToken(myToken);
				token.setExpiresIn(Integer.parseInt(myExpiresIn));
				
				return token;
			}
		} else {// 第一次写入凭证内容
			System.out.println("-------第一次写入凭证内容--------");
			AccessToken token = WeixinUtil.getAccessToken();
			pp.setProperty("token", token.getToken());
			pp.setProperty("expiresIn", String.valueOf(token.getExpiresIn()));
			pp.setProperty("time", String.valueOf(System.currentTimeMillis()));

			FileOutputStream fos = new FileOutputStream(filePath);
			pp.store(fos, "update data");
			fos.close();
			return token;
		}
	
	}
	
	//组装菜单
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://blog.csdn.net/u013991521");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button11,button21,button});
		
		return menu;
	}
	
	//自定义菜单的创建
	public static int createMenu(String token,String menu){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doPostStr(url, menu);
		if(jsonObj!=null){
			result = jsonObj.getInt("errcode");
		}
		return result;
	}
	
	//自定义菜单的查询
	public static JSONObject GetMenu(String token){
		
		String url = GET_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doGetStr(url);
		return jsonObj;
	}
	
	//自定义菜单的删除
	public static int deleteMenu(String token){
		int result = 0;
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doGetStr(url);
		if(jsonObj!=null){
			result = jsonObj.getInt("errcode");
		}
		return result;
	}

}
