package com.huihui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.huihui.po.ImageMessage;
import com.huihui.po.ShortVideoMessage;
import com.huihui.po.TextMessage;
import com.huihui.po.VideoMessage;
import com.huihui.po.VoiceMessage;
import com.huihui.util.CheckUtil;
import com.huihui.util.MessageUtil;
import com.huihui.util.WeixinUtil;

public class WeixinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String timestamp = req.getParameter("timestamp");// 时间戳
		String nonce = req.getParameter("nonce");// 随机数
		String echostr = req.getParameter("echostr");// 随机字符串

		PrintWriter out = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");

			String message = null;
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {// 判断消息类型是否是文本消息
				String content = map.get("Content");
				if ("1".equals(content)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.firstMenu());
				} else if ("2".equals(content)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.secondMenu());
				} else if ("3".equals(content)) {
					message = MessageUtil.initImage(fromUserName, toUserName,
							"N9eyQ65O_vpmx1L5MsYNWMepq7vMs8c8n-dOmJCbdgbxHdz9NiNAGfZWGT-YZwJb");
				} else if ("4".equals(content)) {
					message = MessageUtil.initVoice(fromUserName, toUserName,
							"l5fsNHq9ciB_uwDutuwCqYu6G3lMit7gnyZ4Upkv5ta5gmKf3AfAJbPVd7C5XGsE");
				} else if ("5".equals(content)) {
					message = MessageUtil.initVideo(fromUserName, toUserName,
							"Za_QZthtnl2JjIOMEbZ162dejoMyb6F5IZlL_uA0VD5KazzwbEbOHSdgpGPaML8b", "小生活", "我的工作环境");
				} else if ("6".equals(content)) {
					message = MessageUtil.initMusicMessage(fromUserName, toUserName, "我的音乐", "这是我喜欢的一首歌",
							"E:\\javaEE代码\\weixin\\WebContent\\res\\全场都会侧耳.mp3",
							"E:\\javaEE代码\\weixin\\WebContent\\res\\杜雯媞,王艺翔 - 雪.mp3",
							"LE0JzuT8ksLY50e86pXhoM_KHc5S9AKhQmxnM8RfjkTtW5MO2UmUeU3SWXlHT6lG");
				} else if ("?".equals(content) || "？".equals(content)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				} else {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.anyMessage(content));
				}
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {// 判断消息类型是否是事件类型
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){//如果事件的类型是click类型
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){//如果事件类型是view类型
					String url = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE_PUSH.equals(eventType)){//如果事件类型是扫码推事件
					String key = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, key);
				}
			} else if (MessageUtil.MESSAGE_IMAGE.equals(msgType)) {// 接收到的消息是图片消息
				String picUrl = map.get("PicUrl");
				message = MessageUtil.initNewsMessage(fromUserName, toUserName, picUrl);
			} else if (MessageUtil.MESSAGE_VOICE.equals(msgType)) {// 接受到的消息是语音消息
				String format = map.get("Format");
				String recognition = new String(map.get("Recognition").getBytes("UTF-8"), "UTF-8");

				message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.voiceText(recognition, format));

			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {// 接收到的消息是地理位置信息
				String location_x = map.get("Location_X");//纬度
				String location_y = map.get("Location_Y");//经度
				String scale = map.get("Scale");//地图缩放大小
				String label = map.get("Label");//地理位置信息
				
				String content = "你所在位置的:\n纬度:"+location_x+"\n经度："+location_y+"\n地图缩放大小："+scale+"\n地理位置信息："+label;
				
				message = MessageUtil.initText(fromUserName, toUserName, content);
			} 

			System.out.println(message);
			pw.print(message);

		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

}
