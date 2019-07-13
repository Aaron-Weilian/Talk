/*
 * �������� 2004-9-22
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.vo;

/**
 * @author Liun
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class TableBean {

	private CommonBean dataBean = null;
	private String[] sName = null;
	private String[] chName = null;
	private String[] colWidth = null;
	public TableBean() {
	}

	public TableBean(CommonBean dataBean) {
		this.dataBean = dataBean;
	}

	public void setChName(String[] chName) {
		this.chName = chName;
	}
	
	public String[] getChName() {
		return chName;
	}
	
	public CommonBean getDataBean() {
		return dataBean;
	}

	public void setDataBean(CommonBean bean) {
		dataBean = bean;
	}
	/**
	 * @return
	 */
	public String[] getColWidth() {
		return colWidth;
	}

	/**
	 * @param strings
	 */
	public void setColWidth(String[] strings) {
		colWidth = strings;
	}

	/**
	 * @return
	 */
	public String[] getSName() {
		return sName;
	}

	/**
	 * @param strings
	 */
	public void setSName(String[] strings) {
		sName = strings;
	}

}
