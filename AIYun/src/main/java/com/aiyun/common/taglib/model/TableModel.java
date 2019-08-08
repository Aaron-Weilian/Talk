package com.aiyun.common.taglib.model;

import java.util.Map;

import com.aiyun.common.vo.CommonBean;

public class TableModel {

	private CommonBean dataBean = null;
	private String[] sName = null;
	private String[] chName = null;
	private String[] colWidth = null;
	
	private String tableClass;
	private Map<String,String> rowClass ;
	private Map<String,String> colClass ;
	
	public TableModel() {
	}

	public TableModel(CommonBean dataBean) {
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

	public String getTableClass() {
		return tableClass;
	}

	public void setTableClass(String tableClass) {
		this.tableClass = tableClass;
	}

	public Map<String, String> getRowClass() {
		return rowClass;
	}

	public void setRowClass(Map<String, String> rowClass) {
		this.rowClass = rowClass;
	}

	public Map<String, String> getColClass() {
		return colClass;
	}

	public void setColClass(Map<String, String> colClass) {
		this.colClass = colClass;
	}
	
	

}
