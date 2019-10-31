package com.aiyun.common.util;

import java.io.*;
import java.util.HashMap;

// Referenced classes of package mycrm.pub.util:
//            ResourceManager

public class FileResourceManager implements ResourceManager {

	public FileResourceManager() {
		this("appl.props.path");
	}

	public FileResourceManager(String s) {
		map = new HashMap();
		path = "";
		setSystemProperty(s);
	}

	public void setSystemProperty(String s) {
		setPath(s);
	}

	public void setPath(String s) {
		if (!s.endsWith("\\") && !s.endsWith("/"))
			s = s + "/";
		path = s;
		path = path.replace('\\', '/');
	}

	public String getPath() {
		return path;
	}

	public void init() {
		map.clear();
	}

	public boolean shouldReload(String s) throws IOException, FileNotFoundException {
		Object obj = null;
		boolean flag = true;
		if (s == null)
			throw new NullPointerException(this +"# File Name is null.");
		File file = null;
		if (path != null && path.length() > 0)
			file = new File(path, s);
		else
			file = new File(s);
		String strLastModified = (String)lastModified.get(s); 
		if(strLastModified==null){
			strLastModified = "0";
		}
		if(file.lastModified()==Long.parseLong(strLastModified)){
			flag = false;
		}else{
			map = new HashMap();			
		}
		return flag;
	}

	public InputStream getResourceAsStream(String s) throws IOException, FileNotFoundException {
		if (s == null)
			throw new NullPointerException(this +"# File Name is null.");
		File file = null;
		if (path != null && path.length() > 0)
			file = new File(path, s);
		else
			file = new File(s);
		String s1 = file.toString();
		long longlastModified = file.lastModified();
		lastModified.put(s,String.valueOf(longlastModified));
		Object obj = null;
		InputStream inputstream = null;
		File obj1 = (File)map.get(s1);
		if (obj1 == null) {
			File resourceFile = new File(s1);
			inputstream = new FileInputStream(resourceFile);
			map.put(s, resourceFile);
		} else {
			File resourceFile = obj1;
			inputstream = new FileInputStream(resourceFile);
			map.put(s, resourceFile);
		}
		if (inputstream == null)
			throw new FileNotFoundException(this +"# " + s1 + " is not Found.");
		else
			return inputstream;
	}

	private static final String PROPERTY_FOLDER_KEY_NAME = "appl.props.path";
	private HashMap map;
	private String path;
	private HashMap lastModified = new HashMap();
}