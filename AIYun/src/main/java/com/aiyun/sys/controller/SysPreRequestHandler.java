package com.aiyun.sys.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.PreRequestHandlerSupport;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.po.ErrorMessageBean;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;

/**
 * @author Aaron
 *
 */
public class SysPreRequestHandler extends PreRequestHandlerSupport {
	public void processRequest(HttpServletRequest request) throws CommonException {
	    //判断用户Session
		if (request.getSession() == null || request.getSession().getAttribute(Constants.USERINFO) == null) {
			ErrorMessageBean errBean = new ErrorMessageBean();
			errBean.addCommonMessage("Session disconnected, Please reconnect!");
			request.setAttribute("_errmsgbean", errBean);
			throw new CommonException("Session disconnected, Please reconnect!");
		}
		else {
		    //判断用户权限
			String obj = (String)request.getAttribute("currentobject");
			CommonBean cbUser = (CommonBean)request.getSession().getAttribute(Constants.USERINFO);
			UserBean userbean = new UserBean();
			if (!userbean.bHasPower(cbUser, obj)) {
				ErrorMessageBean errBean = new ErrorMessageBean();
				errBean.addCommonMessage("Sorry, you are not allowed to access this page, please contact the administrator");
				request.setAttribute("_errmsgbean", errBean);
				throw new CommonException("Sorry, you are not allowed to access this page, please contact the administrator");
			}
		}
	}
}
