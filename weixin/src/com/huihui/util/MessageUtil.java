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
	public static final String MESSAGE_TEXT = "text";// �ı���Ϣ
	public static final String MESSAGE_IMAGE = "image";// ͼƬ��Ϣ
	public static final String MESSAGE_VOICE = "voice";// ������Ϣ
	public static final String MESSAGE_VIDEO = "video";// ��Ƶ��Ϣ
	public static final String MESSAGE_LINK = "link";// ������Ϣ
	public static final String MESSAGE_LOCATION = "location";// λ����Ϣ
	public static final String MESSAGE_EVENT = "event";// �¼���Ϣ
	public static final String MESSAGE_SUBSCRIBE = "subscribe";// ��ע�¼�
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";// ȡ����ע�¼�
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_NEWS = "news";// ͼ����Ϣ
	public static final String MESSAGE_SHORTVIDEO = "shortvideo";// С��Ƶ��Ϣ
	public static final String MESSAGE_MUSIC = "music";// ������Ϣ
	public static final String MESSAGE_SCANCODE_PUSH = "scancode_push";//ɨ���¼�

	/**
	 * XMLת��ΪMap����
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
	 * ���ı���ͼƬ��������Ϣ����ת��ΪXML
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
	 * ��ͼ����Ϣ�Ķ���ת��ΪXML
	 * 
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}

	// ��ע�����Ϣ��ʾ
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ��Ĺ�ע���밴�ղ˵���ʾ���в�����\n\n");
		sb.append("1.���˽���\n");
		sb.append("2.�����ɻ�\n");
		sb.append("3.ͼƬ\n");
		sb.append("4.����\n");
		sb.append("5.��Ƶ\n");
		sb.append("6.����\n");
		sb.append("�ظ����鿴���˵�");
		return sb.toString();
	}

	// �ظ�1����Ϣ��ʾ
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("��ҫ�ͣ�24�꣬������ɽ����Ӫ����ҵ�ں��Ϻ����ϻ���ѧ�����ھ�ְ�ڱ�����");
		return sb.toString();
	}

	// �ظ�2����Ϣ��ʾ
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"���ݻ����ڵ�����Ϣʱ������ҵ��Ϣϵͳ�������ߣ����������������Ⱥ����豸����ҵ���������Ϣ��ȫ��в����Ȼ��Դ���ڲ���Ա�����ڲ�������Դ�豸�Ĺ������Ͷ����ڲ����������ĵ�����ȡ�����ݻ��ۺ�����ά����Ͱ�ȫ�Ե��ںϣ��ж����ն˼����������ͷ�������Դ��ֱ�ӷ��ʣ����ǲ���Э�����ķ�ʽ���ӹ����ն˼����������ͷ������ķ��ʡ������˵���ն˼������Ŀ��ķ��ʣ�����Ҫ�������ݻ��ķ��롣����һ���ȷ������ݻ������ſ����ߵĹ��������ж������豸�ͷ�����������Ҫ�����ȴ��ž�������˱��ݻ��ܹ����طǷ����ʺͶ��⹥�����Բ��Ϸ��������������ϣ����˵����ж�Ŀ���豸�ķǷ�������Ϊ��Ŀǰ����һ�����������������ʵ�ֵĴ�������ݻ������ﱤ���ݻ����ñ��ݻ�������Ҫ��������С��ҵ��ϵͳ�����ߣ�����С�ĳɱ��������ķ�ʽʵ�����������������ܻ��͸߰�ȫ�Ĺ��������ʺŹ��������֤����Դ��Ȩ�����ʿ��ơ������¼��������ơ�");
		return sb.toString();
	}

	// �ظ�������Ч��Ϣ����ʾ
	public static String anyMessage(String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("�㷢�͵���Ϣ��" + content + ",����Ч���ݣ�");
		return sb.toString();
	}

	// ��������֮����ı���Ϣ
	public static String voiceText(String recognition, String format) {
		StringBuffer sb = new StringBuffer();
		sb.append("�㷢�͵������������ǣ�" + recognition + "\n");
		sb.append("�㷢�͵��������ļ���ʽ�ǣ�" + format);
		return sb.toString();
	}

	// �����ظ��ı���Ϣ
	public static String initText(String fromUserName, String toUserName, String content) {
		TextMessage text = new TextMessage();
		text.setToUserName(fromUserName);
		text.setFromUserName(toUserName);
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return messageToXml(text);
	}

	// �����ظ�ͼƬ��Ϣ
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

	// �����ظ�������Ϣ
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

	// �����ظ���Ƶ��Ϣ
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

	// �����ظ�������Ϣ
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

	// �����ظ�ͼ����Ϣ
	public static String initNewsMessage(String fromUserName, String toUserName, String picUrl) {
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();

		News news1 = new News();
		news1.setTitle("��ոշ��͵���Ƭ");
		news1.setDescription("�㷢�͵���һ�ź���������ͼƬ");
		news1.setPicUrl(picUrl);

		News news2 = new News();
		news2.setTitle("���߽���");
		news2.setDescription("������ҫ�ͣ���ӭ�����ҵĲ��͡�����");
		news2.setPicUrl("http://15587e69.ngrok.natapp.cn/weixin/Images/myphoto.jpg");
		news2.setUrl("http://blog.csdn.net/u013991521");// ����Ҫ��ת����·��

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
