///*
// * �������� 2005-1-6
// *
// * �����������ļ�ģ��Ϊ
// * ���� > ��ѡ�� > Java > �������� > �����ע��
// */
//package com.aiyun.common.exchgdata.ie2csc.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.aiyun.common.control.exception.CommonException;
//import com.aiyun.common.control.web.RequestHandlerSupport;
//import com.aiyun.common.exchgdata.ie2csc.bean.ExchgDataBean;
//import com.aiyun.common.vo.CommonBean;
//
///**
// * @author Administrator
// *
// * ��������������ע�͵�ģ��Ϊ
// * ���� > ��ѡ�� > Java > �������� > �����ע��
// */
//public class SelectDataRequestHandler extends RequestHandlerSupport {
//	public void processRequest(HttpServletRequest request) throws CommonException {
//		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
//		
//		//ȡ����
//		String ywid = parameter.getParameter("ywid");
//		String sobject = parameter.getParameter("sobject");
//		String modify_mark = parameter.getParameter("modify_mark");
//		
//		ExchgDataBean exchgbean = new ExchgDataBean();
//		
//		//2010-05-25 sunxiaofeng ���ϼ�����ѡ��ҳ������������ѡ�Ϲ��ܡ�
//		String wlflag = (String)request.getParameter("wlflag");
//		
//		if (sobject.equals("lj") && wlflag != null)
//		{
//			
//			CommonBean listbean2 = exchgbean.listLJForWLFlagSearch(ywid,modify_mark,wlflag);
//			request.setAttribute("listbean", listbean2);
//			
//		}
//		else
//		{
//			CommonBean listbean = exchgbean.listData(ywid,sobject,modify_mark);
//			request.setAttribute("listbean", listbean);
//		}
//		
//	}
//
//}