package com.aiyun.common.util;

import java.security.MessageDigest;
import java.util.StringTokenizer;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestParameter;
import com.aiyun.common.vo.CommonBean;

/**
 *
 */
public class Function {

	public static CommonBean[] trimBeanArray(CommonBean[] cbArray) {
		int ii = 0;
		for (int i = 0; i < cbArray.length; i++) {
			if (cbArray[i] != null && cbArray[i].getColumnNum() > 0) {
				ii++;
			}
		}
		CommonBean cb[] = new CommonBean[ii];
		int jj = 0;
		for (int i = 0; i < cbArray.length; i++) {
			if (cbArray[i] != null && cbArray[i].getColumnNum() > 0) {
				cb[jj] = cbArray[i];
				jj++;
			}
		}

		return cb;
	}
	/**
	 */
	public static String[] trimStringArray(String[] strArray) {

		if (strArray == null)
			return null;
		int ii = 0;
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i] != null && strArray[i].trim().length() > 1) {
				ii++;
			}
		}
		String str[] = new String[ii];
		int jj = 0;
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i] != null) {
				str[jj] = strArray[i];
				jj++;
			}
		}

		return str;
	}
	/**
	 */
	public static String[] splitStr2Array(String sValue) {
		java.util.StringTokenizer st = new java.util.StringTokenizer(sValue, ";");
		java.util.Vector vt = new java.util.Vector();
		while (st.hasMoreTokens()) {
			vt.addElement(st.nextToken());
		}

		String[] sRetValue = new String[vt.size()];
		vt.copyInto(sRetValue);
		return sRetValue;
	}

	public static String trimString(String strIn) {
		if (strIn == null)
			return "";
		else
			return strIn.trim();
	}

	public static CommonBean trimBean(CommonBean dataBean) {
		if (dataBean == null)
			dataBean = new CommonBean();

		return dataBean;
	}

	public static CommonBean trimBean(Object dataBean) {
		if (dataBean == null)
			return new CommonBean();

		return (CommonBean) dataBean;
	}

	public static CommonBean getBeanAttribute(javax.servlet.http.HttpServletRequest request, String name) {
		return trimBean((CommonBean) request.getAttribute(name));
	}

	public static String str2html(String strSource) {
		String strRes = trimString(strSource);

		strRes = StrTool.replaceStr(strRes, "@@quot@@", "\"");
		strRes = StrTool.replaceStr(strRes, "@@apos@@", "\'");
		strRes = StrTool.replaceStr(strRes, "&amp;", "&");

		return strRes;
	}

	public static String formatDouble(double dIn, int iDeci) {
		String strFormat = "#";
		if (iDeci > 0) {
			strFormat += ".";
			for (int i = 0; i < iDeci; i++)
				strFormat += "0";
		}
		java.text.DecimalFormat df1 = new java.text.DecimalFormat(strFormat);
		String strOut = df1.format(dIn);

		return strOut;
	}

	public static String getBitChecked(String strBitValue) {
		if (strBitValue == null)
			strBitValue = "";
		String strRes = "";
		if (strBitValue.equals("1"))
			strRes = "checked";

		return strRes;
	}

	public static String char2html(String strSource) {
		String strRes = trimString(strSource);

		strRes = StrTool.replaceStr(strRes, "\r\n", "<br>");
		strRes = StrTool.replaceStr(strRes, "\r", "<br>");
		strRes = StrTool.replaceStr(strRes, "\n", "<br>");

		return strRes;
	}

	public static String[][] trimStringArr2(String[][] strIn) {
		String[][] strOut;
		if (strIn == null)
			strOut = new String[0][];
		else {
			strOut = strIn;
			for (int i = 0; i < strOut.length; i++) {
				for (int j = 0; j < strOut[i].length; j++)
					strOut[i][j] = trimString(strIn[i][j]);
			}
		}
		return strOut;
	}

	public static String[] str2spc(String strIn, String strSpc) {
		if (strIn == null)
			strIn = "";
		else
			strIn = strIn.trim();

		String strTemp = "";
		int i = 0;
		StringTokenizer stIn1 = new StringTokenizer(strIn, strSpc);
		for (i = 0; stIn1.hasMoreTokens(); i++)
			strTemp = stIn1.nextToken();

		StringTokenizer stIn2 = new StringTokenizer(strIn, strSpc);
		String[] strArr = new String[i];
		for (i = 0; stIn2.hasMoreTokens(); i++) {
			strArr[i] = stIn2.nextToken();
		}

		return strArr;
	}
	
	public static String getLoadingHTML() {
		String strHTML = "";

		strHTML += "<table width='100%' border='0'>";
		strHTML += "<tr>";
		strHTML += "<td width='100%' height='100' align='center' valign='center'>";
		strHTML += "<img src='/common/images/loading.gif' border='0'>";
		strHTML += "</td>";
		strHTML += "</tr>";
		strHTML += "</table>";

		return strHTML;
	}

	public static String formatDoubleK(double dIn, int iDeci) {
		String strFormat = "#,###";
		if (iDeci > 0) {
			strFormat += ".";
			for (int i = 0; i < iDeci; i++)
				strFormat += "0";
		}
		java.text.DecimalFormat df1 = new java.text.DecimalFormat(strFormat);
		String strOut = df1.format(dIn);

		return strOut;
	}

	public static String getServer(javax.servlet.http.HttpServletRequest oRequest) {
		String strServerName = oRequest.getServerName();
		int intServerPort = oRequest.getServerPort();
		String strServer = strServerName;
		if (intServerPort != 80)
			strServer += ":" + intServerPort;
		return strServer;
	}

	public static String[][] bean2arr(CommonBean cb) {
		if (cb == null)
			return null;
		String arr[][] = new String[cb.getRowNum()][2];

		for (int i = 0; i < cb.getRowNum(); i++) {
			arr[i][0] = cb.getCellStr(i, "id");
			arr[i][1] = cb.getCellStr(i, "sname");
		}

		return arr;
	}
	public static CommonBean formatBean(CommonBean cb) {
		if (cb == null) {
			return null;
		}
		for (int i = 0; i < cb.getRowNum(); i++) {
			for (int j = 0; j < cb.getColumnNum(); j++) {
				if (cb.getCellStr(i, j) == null) {
					cb.setCellObj(i, j, "");
				}
			}
		}
		return cb;
	}

	public static CommonBean filterParam(RequestParameter parameter, String object) {

		object = object.toLowerCase();
		CommonBean cb = new CommonBean();
		Object[] keys = parameter.getParameters().keySet().toArray();
		String key = null;
		for (int i = 0; i < keys.length; i++) {
			key = (String) keys[i];
			if (key.startsWith(object + "_"))
				cb.addValue(key.substring(object.length() + 1), parameter.getParameter(key));
		}
		return cb;
	}

	public static boolean isNumber(String value) {

		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static int compare(String a, String b) {

		try {
			double da = Double.parseDouble(a);
			double db = Double.parseDouble(b);
			if (da > db) {
				return 1;
			} else if (da < db) {
				return -1;
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			throw new CommonException("无法转化成数字类型！");
		}
	}
	
	public final static String Encmd5(String s){
		 char hexDigits[] = {
			 '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			 'e', 'f'};
		 try {
		   byte[] strTemp = s.getBytes();
		   MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		   mdTemp.update(strTemp);
		   byte[] md = mdTemp.digest();
		   int j = md.length;
		   char str[] = new char[j * 2];
		   int k = 0;
		   for (int i = 0; i < j; i++) {
			 byte byte0 = md[i];
			 str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			 str[k++] = hexDigits[byte0 & 0xf];
			 }
			 return new String(str);
		   }
		   catch (Exception e){
			 return null;
		   }
	}
}
