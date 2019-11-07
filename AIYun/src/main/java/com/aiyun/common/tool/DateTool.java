package com.aiyun.common.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		return date;
	}
	public static boolean isDate(String strDate) {
		try {
			String sdate = "";
			if (strDate == null || strDate.trim().length() == 0)
				return true;
			if (strDate.indexOf(" ") != -1 && strDate.indexOf(":") != -1) {
				int iB = strDate.indexOf(":");
				int iB1 = strDate.indexOf(":", iB + 1);
				String sH = strDate.substring(strDate.indexOf(" ") + 1, iB);
				String sM = strDate.substring(iB + 1, iB1);
				String sS = strDate.substring(iB1 + 1);
				int iH = Integer.parseInt(sH);
				int iM = Integer.parseInt(sM);
				int iS = Integer.parseInt(sS);
				if (iH > 23 || iM > 59 || iS > 59)
					return false;
				String sd = strDate.substring(0, strDate.indexOf(" "));
				java.sql.Date.valueOf(sd.trim());
				sdate = sd;
			} else {
				java.sql.Date.valueOf(strDate);
				sdate = strDate;
			}
			int index = sdate.indexOf("-");
			if (index == -1)
				return false;

			String sY = sdate.substring(0, index);

			if (sY.length() != 4) {
				return false;
			} else if (Integer.parseInt(sY) < 1800) {
				return false;
			} else if (Integer.parseInt(sY) > 2200) {
				return false;
			}

			int index1 = sdate.indexOf("-", index + 1);
			if (index1 == -1)
				return false;

			String sM = sdate.substring(index + 1, index1);

			String sD = "";
			sD = sdate.substring(index1 + 1);

			int iM = StrTool.str2int(sM);
			int iD = StrTool.str2int(sD);

			if (iM > 12 || iM <= 0 || iD <= 0)
				return false;
			if (iM == 1 || iM == 3 || iM == 5 || iM == 7 || iM == 8 || iM == 10 || iM == 12) {
				if (iD > 31)
					return false;
			} else if (iM == 4 || iM == 6 || iM == 9 || iM == 11) {
				if (iD > 30)
					return false;
			} else if (iM == 2) {
				if (isLeapYear(strDate)) {
					if (iD > 29)
						return false;
				} else if (iD > 28)
					return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * �ж��Ƿ�������
	 */
	public static boolean isLeapYear(String sDate) {
		try {
			String s = java.sql.Date.valueOf(sDate).toString();
			int iYear = StrTool.str2int(s.substring(0, 4));

			if (iYear % 4 == 0) {
				if (iYear % 100 == 0 && iYear % 400 != 0) {
					return false;
				} else
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}
}
