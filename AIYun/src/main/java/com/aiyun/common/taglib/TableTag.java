package com.aiyun.common.taglib;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.aiyun.common.taglib.model.TableModel;
import com.aiyun.common.util.Function;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

public class TableTag extends BodyTagSupport {

    private static final long serialVersionUID = -1967315274595528339L;
    private TableModel tb = null;
	private String width = "100%";
	private String height = "100%";
	private boolean num = true;
    private String parax="";
	public int doStartTag() throws JspException {
	    
	    if(tb==null) return 0;
	    
		String[] sName = tb.getSName();
		String[] chName = tb.getChName();
		String[] colWidth = tb.getColWidth();
		String tableClass = tb.getTableClass();
		Map rowClass = tb.getRowClass();
		Map colClass = tb.getColClass();
		
		if (colWidth == null) {
			colWidth = new String[chName.length];
			for (int i = 0; i < colWidth.length; i++) {
				colWidth[i] = "";
			}
		}
		CommonBean cbData = tb.getDataBean();
		JspWriter jspWriter = pageContext.getOut();
		
		try {
			jspWriter.println("<table width=\"" + width + "\"  class=\""+tableClass+"\" >");
			jspWriter.println("<thead>");
			jspWriter.println("<tr>");
			for (int i = 0; i < chName.length; i++) {
				jspWriter.println("<th>");
//				if (!"".equals(colWidth[i])) {
//					jspWriter.println(chName[i]);
//				}
				jspWriter.println(chName[i]);
				jspWriter.println("</th>");
			}
			jspWriter.println("</tr>");
			jspWriter.println("</thead>");
			
			jspWriter.println("<tbody>");
			for (int i = 0; i < cbData.getRowNum(); i++) {
				jspWriter.println("<tr ");
				if(i%2==0){
					jspWriter.println("bgcolor=\"#FFFFFF\" class=\""+rowClass.get(i)+"\">");
				}else{
					jspWriter.println("bgcolor=\"#F2F2F2\" class=\""+rowClass.get(i)+"\">");
				}
				for (int j = 0; j < sName.length; j++) {
					jspWriter.println("	<td class=\""+colClass.get(i)+"\" >" + Function.trimString(cbData.getCellStr(i, sName[j]))+ "</td>");
				}
				jspWriter.println("</tr>");
			}
			jspWriter.println("</tbody>");
			jspWriter.println("</table>");
			jspWriter.println("</div>");
		} catch (IOException e) {
			Log.error(this, e.getMessage());
		}

		return 0;
	}
	/**
	 * @return
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @return
	 */
	public boolean isNum() {
		return num;
	}

	/**
	 * @return
	 */
	public TableModel getTb() {
		return tb;
	}

	/**
	 * @return
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param string
	 */
	public void setHeight(String string) {
		height = string;
	}
	/**
		 * @return
		 */
		public String getParax() {
			return parax;
		}

		/**
		 * @param string
		 */
		public void setParax(String string) {
			parax = string;
		}

	/**
	 * @param b
	 */
	public void setNum(boolean b) {
		num = b;
	}

	/**
	 * @param bean
	 */
	public void setTb(TableModel bean) {
		tb = bean;
	}

	/**
	 * @param string
	 */
	public void setWidth(String string) {
		width = string;
	}

}
