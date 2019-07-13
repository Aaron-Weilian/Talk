//package com.aiyun.common.taglib;
//
//import java.io.IOException;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//
//import com.aiyun.common.util.Log;
//
///**
// *
// */
//public class BodyTag extends BodyTagSupport {
//	private String title = "";
//	private String bgImage = "";
//	public int doStartTag() throws JspException {
//
////		JspWriter jspWriter = pageContext.getOut();
////		try {
////			jspWriter.println("<table width=\"100%\" height=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #a1a1a1; \">");
////			jspWriter.println("<tr>");
////			jspWriter.println("<td height=\"19\" align=\"left\" valign=\"middle\" background=\"/common/images/bg_gray.gif\" style=\"padding-left:6px; \">");
////			jspWriter.println("<table border=\"0\" cellspacing=\"0\" cellpadding=\"2\">");
////			jspWriter.println("<tr>");
////			jspWriter.println("<td align=\"center\" valign=\"middle\"><img src=\"/common/images/ball_y2.jpg\" width=\"13\" height=\"13\"></td>");
////			jspWriter.println("<td align=\"left\" valign=\"bottom\"><div class=\"Caption\">"+title+"</div></td>");
////			jspWriter.println("</tr>");
////			jspWriter.println("</table>");
////			jspWriter.println("<div style=\"font-weight:bold; color:#666666; \"></div>");
////			jspWriter.println("</td>");
////			jspWriter.println("<td align=\"right\" valign=\"middle\" background=\"/common/images/bg_gray.gif\" style=\"padding-right:6px; \">");
////			jspWriter.println("<table border=\"0\" cellspacing=\"0\" cellpadding=\"2\">");
////			jspWriter.println("<tr align=\"center\" valign=\"middle\">");
////			if(bgImage.equals("")){
////				jspWriter.println("<td>&nbsp;</td><td><a href=\"#\" >����</a></td>");
////			}
////			//jspWriter.println("<td><a href=\"#\"><img src=\"/common/images/xpprint.gif\" width=\"14\" height=\"14\" border=\"0\"></a></td>");
////			//jspWriter.println("<td><a href=\"#\"><img src=\"/common/images/xphelp.gif\" width=\"14\" height=\"14\" border=\"0\"></a></td>");
////			jspWriter.println("</tr>");
////			jspWriter.println("</table>");
////			jspWriter.println("</td>");
////			jspWriter.println("</tr>");
////			jspWriter.println("<tr>");
////			jspWriter.println("<td colspan=\"2\" ");
////			if(bgImage.equals("1")){
////				jspWriter.println("class=\"system_bg\" ");
////			}
////			jspWriter.println("align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\" style=\"padding:9px; \">");
////		} catch (IOException e) {
////			Log.error(this, e.getMessage());
////		}
////
////		return EVAL_BODY_INCLUDE;
//		return 1;
//	}
//	public int doEndTag() throws JspException {
////		JspWriter jspWriter = pageContext.getOut();
////		try {
////			jspWriter.println("</td>");
////			jspWriter.println("</tr>");
////			jspWriter.println("</table>");
////		} catch (IOException e) {
////			Log.error(this, e.getMessage());
////		}
////		return 0;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getTitle() {
//		return title;
//	}
//
//	/**
//	 * @param string
//	 */
//	public void setTitle(String string) {
//		title = string;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getBgImage() {
//		return bgImage;
//	}
//
//	/**
//	 * @param string
//	 */
//	public void setBgImage(String string) {
//		bgImage = string;
//	}
//
//}
