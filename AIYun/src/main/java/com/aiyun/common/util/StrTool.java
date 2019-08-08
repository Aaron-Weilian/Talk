package com.aiyun.common.util;

import java.math.BigDecimal;

import com.aiyun.common.vo.CommonBean;

public class StrTool {
	public StrTool() {
		super();
	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		int dom = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.') {
				if (i == 0 && str.substring(0, 1).equalsIgnoreCase("-"))
					continue;
				if (str.charAt(i) > '9' || str.charAt(i) < '0') {
					return false;
				}

			} else {
				dom++;
				if (dom > 1)
					return false;
			}
		}
		return true;
	}

	public static String[] parseStr(String str, String chars) {
		if (str == null || chars == null)
			return null;

		return null;

	}

	public static String replaceStr(String str, String oldStr, String newStr) {
		if (str == null
			|| newStr == null
			|| oldStr == null
			|| oldStr.trim().length() == 0)
			return str;
		int iE = str.indexOf(oldStr);
		int iB = 0;
		String temp = "";
		while (iE != -1) {
			temp += str.substring(iB, iE) + newStr;
			iB = iE + oldStr.length();
			iE = str.indexOf(oldStr, iB);
		}
		if (iB < str.length())
			temp += str.substring(iB);
		return temp;
	}
	
	public static String setScale(String str, int scale) {

		if (str == null || str.trim().length() == 0)
			return "";

		try {
			java.math.BigDecimal bd = new java.math.BigDecimal(str);

			java.math.BigDecimal bd1 = bd.setScale(scale, BigDecimal.ROUND_HALF_DOWN);

			return bd1.toString();
		} catch (Exception e) {
			Log.error(StrTool.class, "e =============" + str + " " + scale);
			e.printStackTrace();
			Log.error(StrTool.class, "e =============" + e.getMessage());
			return "0";
		}

	}
	
	public static float str2float(String str) {
		if (str == null)
			return 0;
		try {
			return Float.valueOf(str).floatValue();
			//		return Double.valueOf(str).doubleValue();

		} catch (Exception e) {
			return 0;
		}
	}

	public static int str2int(String str) {
		if (str == null)
			return 0;
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String[][] bean2array(CommonBean cbList) {

		if(cbList==null||cbList.getColumnNum()<1) return new String[0][0];
		String sUDFTemp[][]=new String[cbList.getRowNum()][cbList.getColumnNum()];
		for(int j=0;j<cbList.getRowNum();j++){
			for(int i=0;i<cbList.getColumnNum();i++){
				sUDFTemp[j][i]=cbList.getCellStr(j,i);
			}
		}
		return sUDFTemp;
	}
}
