package com.aiyun.common.vo;

/**
 * �Զ����쳣�ࡣ
 * �������ڣ�(2001-3-16 10:47:45)
 * @author��Τ����
 */
public class ValidateException extends Exception {
	private java.lang.String type = "0";
	private java.lang.String title = null;
/**
 * ValidateException ������ע�⡣
 */
public ValidateException() {
	super();
}
/**
 * ValidateException ������ע�⡣
 * @param s java.lang.String
 */
public ValidateException(String s) {
	super(s);
}
/**
 * ValidateException ������ע�⡣
 * @param s java.lang.String
 */
public ValidateException(String s,String sTile) {
	super(s);
	setTitle(sTile);
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2001-9-30 11:44:56)
 * @return java.lang.String
 */
public java.lang.String getTitle() {
	return title;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2001-8-29 20:58:09)
 * @return java.lang.String
 */
public java.lang.String getType() {
	return type;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2001-9-30 11:44:56)
 * @param newTitle java.lang.String
 */
public void setTitle(java.lang.String newTitle) {
	title = newTitle;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2001-8-29 20:58:09)
 * @param newType java.lang.String
 */
public void setType(java.lang.String newType) {
	type = newType;
}
}
