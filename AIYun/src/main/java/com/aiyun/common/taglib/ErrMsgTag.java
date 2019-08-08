package com.aiyun.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.aiyun.common.bo.ErrorMessageBean;
import com.aiyun.common.util.Function;
import com.aiyun.common.util.Log;

public class ErrMsgTag extends BodyTagSupport {

	private HttpServletRequest request = null;
	private String msg = null;
	
	public ErrMsgTag() {
		msg = null;
	}
	
	public int doStartTag() throws JspException {

		JspWriter jspWriter = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("<div id=\"aiyun-container\" class=\"clearfix\">");
			sb.append("<div class=\"container\">");
			sb.append("<div id=\"aiyun-error-container\">");
			if (msg != null) {
				sb.append("<p id=\"aiyun-error-message\">").append(Function.trimString(msg)).append("</p>");
			}
			else {
				sb.append("<h1 id=\"aiyun-error-code\">Error <span>404</span></h1>");
			}
			sb.append("</div>");
			sb.append("</div> ");
			sb.append("</div>");
			
			jspWriter.println(sb.toString());
			
		} catch (IOException e) {
			Log.error(this, e.getMessage());
		}

		return 0;
	}

	/**
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		ErrorMessageBean emb = (ErrorMessageBean) request.getAttribute("_errmsgbean");
		if (emb != null) {
			msg = emb.getCommonMessage();
		}
	}

}
