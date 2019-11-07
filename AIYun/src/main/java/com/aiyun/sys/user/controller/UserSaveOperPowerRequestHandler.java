/*
 * �������� 2004-9-6
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
//import com.aiyun.sys.czrz.bean.CzrzBean;
import com.aiyun.sys.user.bean.UserBean;

public class UserSaveOperPowerRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {
		String userid = parameter.getParameter("userid");
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		
		CommonBean cbAuth = new CommonBean();
		
		String[] modules = parameter.getParameterValues("module");
		if (modules!=null) {
			for (int i = 0; i < modules.length; i++) {
				String[] busnodes = parameter.getParameterValues("m"+modules[i]);
				if (busnodes==null || busnodes.length==0) {
					cbAuth.addValue("userid",userid);
					cbAuth.addValue("moduleid",modules[i]);
					cbAuth.addValue("busnodeid","");
				}
				else {
					for (int inode=0; inode<busnodes.length; inode++) {
						String busnodeid = busnodes[inode];
						cbAuth.addValue("userid",userid);
						cbAuth.addValue("moduleid",modules[i]);
						cbAuth.addValue("busnodeid",busnodeid);
						//����б��������Ȩ�޾�Ӧ���б��������Ȩ��
						if (busnodeid.equals("01") || busnodeid.equals("02") || busnodeid.equals("03")) {
							cbAuth.addValue("userid",userid);
							cbAuth.addValue("moduleid",modules[i]);
							cbAuth.addValue("busnodeid",busnodeid+"1");
						}
					}
				}
			}
		}
		UserBean bean = new UserBean();
		bean.saveOperationPower(cbUser, userid, cbAuth);
		if (bean.getErrMsgBean() != null && bean.getErrMsgBean().getCommonMessage() != null) {
			request.setAttribute("_errmsgbean", bean.getErrMsgBean());
			request.setAttribute("forwardurl", "/applicationException");
		} else {
//			CzrzBean czrzBean = new CzrzBean();
//			czrzBean.saveLog(cbUser, "�û�", "�����û�Ȩ�ޱ���", null);
//			request.setAttribute("forwardurl", "/yhqx");
		}

	}

}
