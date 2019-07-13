/*
 * �������� 2004-12-14
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.exchgdata.csc2ie.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.exchgdata.csc2ie.bean.UploadBean;
import com.aiyun.common.vo.CommonBean;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class CreateXmlRequestHandler extends RequestHandlerSupport {
	public void processRequest(HttpServletRequest request) throws CommonException {

		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		String yw = parameter.getParameter("yw");	//����ҵ�����
		String strYwoper = parameter.getParameter("ywoper");
		String strYwtype=parameter.getParameter("ywtype");	//ҵ�����
		String strTrade=parameter.getParameter("trade");	//�Ƿ���һ��ó��
		UploadBean upbean = new UploadBean();
		
		if (yw.equals("ci")&& strYwtype.equals("7")&& strTrade.equals("0")) {
			//ȡ����ywid
			String sYwid = parameter.getParameter("ywid");
			//���ø���
			//sYwid="11655470682650797583189446594810";
			//boolean bFlag = xmlbean.UploadAcc(cbUser, sYwid);

			//�����ϴ�����
			sYwid="11338387859542926940571680189497";
			boolean bFlag = upbean.UploadGd(cbUser, sYwid);
			//�жϲ���
			CommonBean xmlData =new CommonBean();
			xmlData.addValue("info", "�����ļ��ɹ�");
			request.setAttribute("databean", xmlData);

		}
	}

}
