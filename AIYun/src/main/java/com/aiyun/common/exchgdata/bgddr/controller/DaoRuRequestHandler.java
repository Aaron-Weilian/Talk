/*
 * �������� 2005-4-19
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.exchgdata.bgddr.controller;


import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.exchgdata.bgddr.bean.DaoRuBean;
import com.aiyun.common.vo.CommonBean;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class DaoRuRequestHandler extends RequestHandlerSupport{
	public void processRequest(HttpServletRequest request) throws CommonException {
	        CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
			String i_e = parameter.getParameter("I_E");
	    	String typeid = parameter.getParameter("typeid");
	    	String bsflag = parameter.getParameter("bsflag");
		    String ywid = parameter.getParameter("ywid");	   
		    
	    	CommonBean cbData = new CommonBean();
    	
			cbData.addValue("i_e", i_e);
	    	cbData.addValue("typeid", typeid);
	    	cbData.addValue("bsflag", bsflag);
	    	
	    	
	    	DaoRuBean drbean=new DaoRuBean();
	    	CommonBean xmlData=drbean.selectforward(cbData,ywid);
	    	System.out.println(xmlData.getCellStr(0,"info"));
	     	request.setAttribute("databean", xmlData);
		     	
	}		
}
