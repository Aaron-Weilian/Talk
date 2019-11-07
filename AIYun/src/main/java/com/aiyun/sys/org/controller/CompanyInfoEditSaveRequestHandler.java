package com.aiyun.sys.org.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.org.bean.OrgBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;

public class CompanyInfoEditSaveRequestHandler extends RequestHandlerSupport {
    
	public void processRequest(HttpServletRequest request) throws CommonException {
	    CommonBean cbUser = (CommonBean)request.getSession(false).getAttribute(Constants.USERINFO);
	    
	    String companyName = parameter.getParameter("companyName");
	    String companyCode = parameter.getParameter("companyCode");
	    String companyShortName = parameter.getParameter("companyShortName");
	    String email = parameter.getParameter("email");
	    String duns = parameter.getParameter("duns");
	    String country = parameter.getParameter("country");
	    String busniess = parameter.getParameter("busniess");
	    
//		String userID = parameter.getParameter("id");
//		String sname = parameter.getParameter("sname");
//		String dep = parameter.getParameter("dep");
//		String duty = parameter.getParameter("duty");

		CommonBean company = new CommonBean();
		company.addValue("orgId", UUID.randomUUID().toString());
		company.addValue("orgName", companyName);
		company.addValue("orgCode", companyCode);
		company.addValue("orgDesc", companyShortName);
		company.addValue("type", "0");
		company.addValue("duns", duns);
		company.addValue("email", email);
		company.addValue("phone", companyCode);
		company.addValue("orgParentId", "0");
//		userBean.addValue("dep", dep);
//		userBean.addValue("duty", duty);

		OrgBean orgBean = new OrgBean();
		if (orgBean.saveUser(company)) {
//			request.setAttribute("forwardurl", "/grxx");
		}
	}

   

   
	
	
}