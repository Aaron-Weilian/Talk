//package com.aiyun.common.taglib;
//
//import java.io.IOException;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//
//import com.aiyun.common.util.Log;
//import com.aiyun.common.vo.CommonBean;
//import com.aiyun.common.vo.TableBean;
//
///**
// * @author Liun
// *
// * ��������������ע�͵�ģ��Ϊ
// * ���� > ��ѡ�� > Java > �������� > �����ע��
// */
//public class InputTableTag extends BodyTagSupport {
//
//	private TableBean tb = null;
//	private String width = "100%";
//	private String height = "100%";
//	private boolean num = true;
//
//	public int doStartTag() throws JspException {
//		String[] sName = tb.getSName();
//		String[] chName = tb.getChName();
//		String[] colWidth = tb.getColWidth();
//		if (colWidth == null) {
//			colWidth = new String[chName.length];
//			for (int i = 0; i < colWidth.length; i++) {
//				colWidth[i] = "";
//			}
//		}
//		CommonBean cbData = tb.getDataBean();
//		JspWriter jspWriter = pageContext.getOut();
//		try {
//			jspWriter.println(
//				"<table width=\""
//					+ width
//					+ "\"  border=\"0\" cellpadding=\"2\" cellspacing=\"1\" bgcolor=\"#aaaaaa\">");
//			jspWriter.println("<colgroup>");
//			if (num) {
//				jspWriter.println(
//					"	<col width=\"50\" align=\"center\" valign=\"middle\" bgcolor=\"#017FA8\" scope=\"col\"></col>");
//			}
//			for (int i = 0; i < chName.length; i++) {
//				jspWriter.println("	<col ");
//				if (!"".equals(colWidth[i])) {
//					jspWriter.println("width=\"" + colWidth[i] + "\" ");
//				}
//				jspWriter.println("align=\"center\" valign=\"middle\" bgcolor=\"#017FA8\" scope=\"col\"></col>");
//			}
//			jspWriter.println("</colgroup>");
//			jspWriter.println("<tr bgcolor=\"#017FA8\">");
//			if (num) {
//				jspWriter.println("	<td ><span class=\"style4\">���</span></td>");
//			}
//			for (int i = 0; i < chName.length; i++) {
//				jspWriter.println("	<td ><span class=\"style4\">" + chName[i] + "</span></td>");
//			}
//			jspWriter.println("</tr>");
//			jspWriter.println("</table>");
//			jspWriter.println(
//				"<div style=\"width:" + width + ";overflow-y:auto; height:" + height + ";\" class=\"scroll_gray\">");
//			jspWriter.println(
//				"<table width=\"100%\"  border=\"0\" cellpadding=\"2\" cellspacing=\"1\" bgcolor=\"#aaaaaa\">");
//			jspWriter.println("<colgroup>");
//			if (num) {
//				jspWriter.println(
//					"	<col width=\"50\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\" scope=\"col\"></col>");
//			}
//			for (int i = 0; i < chName.length; i++) {
//				jspWriter.println("	<col ");
//				if (!"".equals(colWidth[i])) {
//					jspWriter.println("width=\"" + colWidth[i] + "\" ");
//				}
//				jspWriter.println("align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\" scope=\"col\"></col>");
//			}
//			jspWriter.println("</colgroup>");
//
//			for (int i = 0; i < cbData.getRowNum(); i++) {
//				jspWriter.println("<tr>");
//				if (num) {
//					jspWriter.println("	<td >" + (i + 1));
//					jspWriter.println(
//						"<input type=\"hidden\" name=\"sDataType\" value=\""
//							+ cbData.getCellStr(i, "sDataType")
//							+ "\">");
//					jspWriter.println("</td>");
//				}
//				for (int j = 0; j < sName.length; j++) {
//					jspWriter.println(
//						"	<td><input type='text' name='"
//							+ sName[j]
//							+ "' value='"
//							+ cbData.getCellStr(i, sName[j])
//							+ "'></td>");
//				}
//				jspWriter.println("</tr>");
//			}
//			jspWriter.println("</table>");
//			jspWriter.println("</div>");
//		} catch (IOException e) {
//			Log.error(this, e.getMessage());
//		}
//
//		return 0;
//	}
//	/**
//	 * @return
//	 */
//	public String getHeight() {
//		return height;
//	}
//
//	/**
//	 * @return
//	 */
//	public boolean isNum() {
//		return num;
//	}
//
//	/**
//	 * @return
//	 */
//	public TableBean getTb() {
//		return tb;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getWidth() {
//		return width;
//	}
//
//	/**
//	 * @param string
//	 */
//	public void setHeight(String string) {
//		height = string;
//	}
//
//	/**
//	 * @param b
//	 */
//	public void setNum(boolean b) {
//		num = b;
//	}
//
//	/**
//	 * @param bean
//	 */
//	public void setTb(TableBean bean) {
//		tb = bean;
//	}
//
//	/**
//	 * @param string
//	 */
//	public void setWidth(String string) {
//		width = string;
//	}
//
//}
