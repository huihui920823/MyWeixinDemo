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
		String signature = req.getParameter("signature");// ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
		String timestamp = req.getParameter("timestamp");// ʱ���
		String nonce = req.getParameter("nonce");// �����
		String echostr = req.getParameter("echostr");// ����ַ���

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
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {// �ж���Ϣ�����Ƿ����ı���Ϣ
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
							"Za_QZthtnl2JjIOMEbZ162dejoMyb6F5IZlL_uA0VD5KazzwbEbOHSdgpGPaML8b", "С����", "�ҵĹ�������");
				} else if ("6".equals(content)) {
					message = MessageUtil.initMusicMessage(fromUserName, toUserName, "�ҵ�����", "������ϲ����һ�׸�",
							"E:\\javaEE����\\weixin\\WebContent\\res\\ȫ��������.mp3",
							"E:\\javaEE����\\weixin\\WebContent\\res\\�����q,������ - ѩ.mp3",
							"LE0JzuT8ksLY50e86pXhoM_KHc5S9AKhQmxnM8RfjkTtW5MO2UmUeU3SWXlHT6lG");
				} else if ("?".equals(content) || "��".equals(content)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				} else {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.anyMessage(content));
				}
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {// �ж���Ϣ�����Ƿ����¼�����
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){//����¼���������click����
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){//����¼�������view����
					String url = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE_PUSH.equals(eventType)){//����¼�������ɨ�����¼�
					String key = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, key);
				}
			} else if (MessageUtil.MESSAGE_IMAGE.equals(msgType)) {// ���յ�����Ϣ��ͼƬ��Ϣ
				String picUrl = map.get("PicUrl");
				message = MessageUtil.initNewsMessage(fromUserName, toUserName, picUrl);
			} else if (MessageUtil.MESSAGE_VOICE.equals(msgType)) {// ���ܵ�����Ϣ��������Ϣ
				String format = map.get("Format");
				String recognition = new String(map.get("Recognition").getBytes("UTF-8"), "UTF-8");

				message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.voiceText(recognition, format));

			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {// ���յ�����Ϣ�ǵ���λ����Ϣ
				String location_x = map.get("Location_X");//γ��
				String location_y = map.get("Location_Y");//����
				String scale = map.get("Scale");//��ͼ���Ŵ�С
				String label = map.get("Label");//����λ����Ϣ
				
				String content = "������λ�õ�:\nγ��:"+location_x+"\n���ȣ�"+location_y+"\n��ͼ���Ŵ�С��"+scale+"\n����λ����Ϣ��"+label;
				
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
