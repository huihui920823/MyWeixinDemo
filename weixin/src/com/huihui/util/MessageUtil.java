package com.huihui.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huihui.po.Image;
import com.huihui.po.ImageMessage;
import com.huihui.po.Music;
import com.huihui.po.MusicMessage;
import com.huihui.po.News;
import com.huihui.po.NewsMessage;
import com.huihui.po.TextMessage;
import com.huihui.po.Video;
import com.huihui.po.VideoMessage;
import com.huihui.po.Voice;
import com.huihui.po.VoiceMessage;
import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";// 文本消息
	public static final String MESSAGE_IMAGE = "image";// 图片消息
	public static final String MESSAGE_VOICE = "voice";// 语音消息
	public static final String MESSAGE_VIDEO = "video";// 视频消息
	public static final String MESSAGE_LINK = "link";// 连接消息
	public static final String MESSAGE_LOCATION = "location";// 位置消息
	public static final String MESSAGE_EVENT = "event";// 事件消息
	public static final String MESSAGE_SUBSCRIBE = "subscribe";// 关注事件
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";// 取消关注事件
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_NEWS = "news";// 图文消息
	public static final String MESSAGE_SHORTVIDEO = "shortvideo";// 小视频消息
	public static final String MESSAGE_MUSIC = "music";// 音乐消息
	public static final String MESSAGE_SCANCODE_PUSH = "scancode_push";//扫码事件

	/**
	 * XML转换为Map集合
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream input = request.getInputStream();
		Document doc = reader.read(input);

		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}

		input.close();
		return map;
	}

	/**
	 * 将文本、图片、语音消息对象转换为XML
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(Object message) {
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 将图文消息的对象转换为XML
	 * 
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}

	// 关注后的信息提示
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎你的关注，请按照菜单提示进行操作：\n\n");
		sb.append("1.个人介绍\n");
		sb.append("2.技术干货\n");
		sb.append("3.图片\n");
		sb.append("4.语音\n");
		sb.append("5.视频\n");
		sb.append("6.音乐\n");
		sb.append("回复？查看主菜单");
		return sb.toString();
	}

	// 回复1的信息提示
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("张耀晖，24岁，来自于山东东营，毕业于湖南衡阳南华大学，现在就职于北京。");
		return sb.toString();
	}

	// 回复2的信息提示
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"堡垒机：在当今信息时代，企业信息系统最大的软肋，就在内网服务器等核心设备，企业面临最大信息安全威胁，依然是源自内部人员对于内部网络资源设备的攻击，和对于内部机密数据文档的窃取。堡垒机综合了运维管理和安全性的融合，切断了终端计算机对网络和服务器资源的直接访问，而是采用协议代理的方式，接管了终端计算机对网络和服务器的访问。形象地说，终端计算机对目标的访问，均需要经过堡垒机的翻译。打了一个比方，堡垒机扮演着看门者的工作，所有对网络设备和服务器的请求都要从这扇大门经过。因此堡垒机能够拦截非法访问和恶意攻击，对不合法命令进行命令阻断，过滤掉所有对目标设备的非法访问行为。目前，有一款基于虚拟主机技术实现的纯软件堡垒机——碉堡堡垒机，该堡垒机技术主要帮助了中小企业的系统管理者，以最小的成本、最灵活的方式实现了以下六大方面智能化和高安全的管理，包括帐号管理、身份认证、资源授权、访问控制、单点登录、操作审计。");
		return sb.toString();
	}

	// 回复其他无效信息的提示
	public static String anyMessage(String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("你发送的消息：" + content + ",是无效内容！");
		return sb.toString();
	}

	// 语音翻译之后的文本信息
	public static String voiceText(String recognition, String format) {
		StringBuffer sb = new StringBuffer();
		sb.append("你发送的语音的内容是：" + recognition + "\n");
		sb.append("你发送的语音的文件格式是：" + format);
		return sb.toString();
	}

	// 被动回复文本消息
	public static String initText(String fromUserName, String toUserName, String content) {
		TextMessage text = new TextMessage();
		text.setToUserName(fromUserName);
		text.setFromUserName(toUserName);
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return messageToXml(text);
	}

	// 被动回复图片消息
	public static String initImage(String fromUserName, String toUserName, String mediaId) {
		Image im = new Image();
		im.setMediaId(mediaId);

		ImageMessage image = new ImageMessage();
		image.setToUserName(fromUserName);
		image.setFromUserName(toUserName);
		image.setMsgType("image");
		image.setCreateTime(new Date().getTime());
		image.setImage(im);

		return messageToXml(image);
	}

	// 被动回复语音消息
	public static String initVoice(String fromUserName, String toUserName, String mediaId) {
		Voice vo = new Voice();
		vo.setMediaId(mediaId);

		VoiceMessage voice = new VoiceMessage();
		voice.setToUserName(fromUserName);
		voice.setFromUserName(toUserName);
		voice.setCreateTime(new Date().getTime());
		voice.setMsgType("voice");
		voice.setVoice(vo);

		return messageToXml(voice);
	}

	// 被动回复视频消息
	public static String initVideo(String fromUserName, String toUserName, String mediaId, String title,
			String description) {
		Video vi = new Video();
		vi.setMediaId(mediaId);
		vi.setTitle(title);
		vi.setDescription(description);

		VideoMessage video = new VideoMessage();
		video.setToUserName(fromUserName);
		video.setFromUserName(toUserName);
		video.setCreateTime(new Date().getTime());
		video.setMsgType(MESSAGE_VIDEO);
		video.setVideo(vi);

		return messageToXml(video);

	}

	// 被动回复音乐消息
	public static String initMusicMessage(String fromUserName, String toUserName, String title, String description,
			String musicURL, String hqMusicURL, String thumbMediaId) {
		Music mu = new Music();
		mu.setTitle(title);
		mu.setDescription(description);
		mu.setMusicUrl(musicURL);
		mu.setHQMusicUrl(hqMusicURL);
		mu.setThumbMediaId(thumbMediaId);

		MusicMessage music = new MusicMessage();
		music.setToUserName(fromUserName);
		music.setFromUserName(toUserName);
		music.setCreateTime(new Date().getTime());
		music.setMsgType(MESSAGE_MUSIC);
		music.setMusic(mu);

		return MessageUtil.messageToXml(music);

	}

	// 被动回复图文消息
	public static String initNewsMessage(String fromUserName, String toUserName, String picUrl) {
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();

		News news1 = new News();
		news1.setTitle("你刚刚发送的照片");
		news1.setDescription("你发送的是一张很有审美的图片");
		news1.setPicUrl(picUrl);

		News news2 = new News();
		news2.setTitle("作者介绍");
		news2.setDescription("我是张耀晖，欢迎访问我的博客。。。");
		news2.setPicUrl("http://15587e69.ngrok.natapp.cn/weixin/Images/myphoto.jpg");
		news2.setUrl("http://blog.csdn.net/u013991521");// 设置要跳转到的路径

		newsList.add(news1);
		newsList.add(news2);

		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setArticles(newsList);

		message = newsMessageToXml(newsMessage);
		return message;
	}

}
