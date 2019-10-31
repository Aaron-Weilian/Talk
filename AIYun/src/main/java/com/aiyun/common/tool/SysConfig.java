package com.aiyun.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.aiyun.common.control.web.BaseServlet;

public class SysConfig {

	public static SysConfig getInstance() {
		if (instance == null) {
			instance = new SysConfig();
		}
		return instance;
	}

	private SysConfig() {
		map = new HashMap();
		try {
			InputStream ins = BaseServlet.getResourceManager().getResourceAsStream(FILE_NAME);
			SAXReader reader = new SAXReader();
			Document document = reader.read(ins);
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator("item"); i.hasNext();) {
				Element element = (Element) i.next();
				map.put(element.attributeValue("name"), element.attributeValue("value"));
			}
		} catch (FileNotFoundException e) {
			Log.error(this, "�����ļ�" + FILE_NAME + "�����ڣ�");
		} catch (IOException e) {
			Log.error(this, "�����ļ�" + FILE_NAME + "��ȡ����" + e.getMessage());
		} catch (DocumentException e) {
			Log.error(this, "�����ļ�" + FILE_NAME + "��ʽ����" + e.getMessage());
		}
	}

	public final String get(String s) {
		return (String) map.get(s);
	}

	public final String getTrim(String s) {
		String s1 = get(s);
		if (s1 != null)
			s1 = s1.trim();
		return s1;
	}

	private static SysConfig instance = null;
	public static final String FILE_NAME = "config.xml";
	private HashMap map = null;

}