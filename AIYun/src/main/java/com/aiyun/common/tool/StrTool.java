package com.aiyun.common.tool;

public class StrTool {
	public static int str2int(String str) {
		if (str == null || str.trim().equals(""))
			return 0;
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static boolean isNumeric(String str)
	{
		if(str==null || str.trim().equals(""))
			return true;
		int dom = 0;
		for(int i=0;i<str.length();i++)
		{
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
	
	public static float str2float(String str) {
		if (str == null)
			return 0;
		try {
			return Float.valueOf(str).floatValue();

		} catch (Exception e) {
			return 0;
		}
	}
	
	public static double str2double(String str) {
		if (str == null)
			return 0;
		try {
			return Double.valueOf(str).doubleValue();

		} catch (Exception e) {
			return 0;
		}
	}
	
	 public static String replaceStr(String str, String oldStr, String newStr) {
		   if (str == null || newStr == null || oldStr == null||oldStr.trim().length()==0)
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
	 
	public static String fmtNumber(String snumber,int decnum) {
		try {
			if (snumber==null || snumber.equals("")) return "";
			double f = Double.valueOf(snumber).doubleValue();
			String sdec = ".";
			for (int i=0; i<decnum; i++) {
				sdec = sdec + "0";
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat("#,###"+(sdec.equals(".")?"":sdec));
			String retvalue = df.format(f); 
			
			if (retvalue.indexOf(".")==0) retvalue="0"+retvalue;
			return retvalue;
		}
		catch (Exception e) {
			return "";
		}
	}
	 
	public static String fmtDate(String sdate) {
		try {
			if (sdate==null || sdate.equals("")) return "";
			java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM,java.util.Locale.US);
			java.util.Date dd = new java.util.Date();
			long ld = java.util.Date.parse(sdate);
			dd.setTime(ld);
			String retvalue = df.format(dd);
			
			return retvalue;
		}
		catch (Exception e) {
			return "";
		}
	}
}
