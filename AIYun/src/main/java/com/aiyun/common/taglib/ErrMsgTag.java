//package com.aiyun.common.taglib;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//
//import com.aiyun.common.bo.ErrorMessageBean;
//import com.aiyun.common.common.Function;
//import com.aiyun.common.util.Log;
//
///**
// * @author Liun
// *
// * ��������������ע�͵�ģ��Ϊ
// * ���� > ��ѡ�� > Java > �������� > �����ע��
// */
//public class ErrMsgTag extends BodyTagSupport {
//
//	private HttpServletRequest request = null;
//	private String msg = null;
//	
//	public ErrMsgTag() {
//		msg = null;
//	}
//	
//	public int doStartTag() throws JspException {
//
//		JspWriter jspWriter = pageContext.getOut();
//		try {
//
//			if (msg != null) {
//				jspWriter.println("<table width=\"100%\" height=\"20\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
//				jspWriter.println("<tr>");
//				jspWriter.println("<td align=\"left\" valign=\"middle\" class=\"mustitem\" style=\"padding-left:6px; \">");
//				jspWriter.println(Function.trimString(msg));
//				jspWriter.println("</td>");
//				jspWriter.println("</tr>");
//				jspWriter.println("</table>");
//
//			}
//		} catch (IOException e) {
//			Log.error(this, e.getMessage());
//		}
//
//		return 0;
//	}
//
//	/**
//	 * @return
//	 */
//	public HttpServletRequest getRequest() {
//		return request;
//	}
//
//	/**
//	 * @param request
//	 */
//	public void setRequest(HttpServletRequest request) {
//		this.request = request;
//		ErrorMessageBean emb = (ErrorMessageBean) request.getAttribute("_errmsgbean");
//		if (emb != null) {
//			msg = emb.getCommonMessage();
//		}
//	}
//
//}
