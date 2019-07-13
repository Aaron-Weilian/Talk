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
//public class SelectBomRequestHandler extends RequestHandlerSupport {
//	public void processRequest(HttpServletRequest request) throws CommonException {
//		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
//		
//		//ȡ����
//		String strCpno = parameter.getParameter("cpno");
//		String strVersion = parameter.getParameter("version");
//		
//		ExchgDataBean exchgbean = new ExchgDataBean();
//		
//		CommonBean listbean = exchgbean.listBomData(strCpno,strVersion);
//		
//		request.setAttribute("listbean", listbean);
//	}
//
//}