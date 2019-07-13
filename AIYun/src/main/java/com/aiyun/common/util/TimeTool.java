/*
 * �������� 2004-7-14
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Liun
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class TimeTool {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static String getCurLongDate(){
		return dateFormat.format(new Date());
	}
	
	public static String getCurDate(){
		return dateFormat.format(new Date()).substring(0,10);
	}
	
	public static boolean isDate(String date){
		try {
			dateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			Log.warn(TimeTool.class, "���롰"+date+"������ʱ�䡣");
			return false;
		}
	}
	
	public static int dateComp(String Date1, String Date2) {
		if((Date1==null||Date1.trim().length()==0)&&(Date2 == null||Date2.trim().length()==0))
			return 0;

		if(Date2 == null||Date2.trim().length()==0)
			return 1;

		if(Date1==null||Date1.trim().length()==0)
			return -1;

		if(!isDate(Date1)||!isDate(Date2))
			return 0;

		String sD1 = java.sql.Date.valueOf(Date1).toString();
		String sD2 = java.sql.Date.valueOf(Date2).toString();

		return sD1.compareTo(sD2);
	}
	/**
	 * �ƶ����ڣ��죩��
	 * �������ڣ�(2001-6-28 15:57:34)
	 * @return java.lang.String
	 * @param sCurDate java.lang.String
	 * @param iDays int
	 */
	public static String dateMove(String sCurDate, int iDays) {
		long lD = java.sql.Date.valueOf(sCurDate).getTime();
		long ll =1;
		ll=ll*iDays*24*3600*1000;
		lD = lD+ll;
		return new java.sql.Date(lD).toString();
	}
	/**
	 * �ƻ���������
	 * �������ڣ�(2001-6-28 15:57:34)
	 * @return java.lang.String
	 * @param sCurDate java.lang.String
	 * @param iDays int
	 */
	public static long daySub(String Date1, String Date2) {

		return daySub(java.sql.Date.valueOf(Date1),java.sql.Date.valueOf(Date2));
	}
	/**
	 * �ƻ���������
	 * �������ڣ�(2001-6-28 15:57:34)
	 * @return java.lang.String
	 * @param sCurDate java.lang.String
	 * @param iDays int
	 */
	public static long daySub(java.sql.Date Date1, java.sql.Date Date2) {
		long lD1 = Date1.getTime();
		long lD2 = Date2.getTime();
		long ll =lD1-lD2;
		ll=ll/(24*3600*1000);
		return ll;
	}
	/**
	 * ȡ�õ�ǰ���ڡ�
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */

	/**
	 * ȡ�õ�ǰ�졣
	 * �������ڣ�(2001-6-28 11:33:16)
	 * @return java.lang.String
	 */
	public static String getCurDay() {
		String s=getCurDate();
		return s.substring(s.lastIndexOf("-")+1);
	}

	/**
	 * ȡ�õ�ǰ�¡�
	 * �������ڣ�(2001-6-28 11:33:16)
	 * @return java.lang.String
	 */
	public static String getCurMonth() {
		String s=getCurDate();
		int iB = s.indexOf("-");
		return s.substring(iB+1,s.indexOf("-",iB+1));
	}
	/**
	 * ȡ�õ�ǰʱ�䡣
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getCurTime() {

		String st =  new java.util.Date().toString();
		int iB = st.indexOf(":");
		return st.substring(iB-2,iB+6);
	}
	/**
	 * ȡ�õ�ǰ�ܼ���
	 * �������ڣ�(2001-6-28 11:33:16)
	 * @return java.lang.String
	 */
	public static int getCurWeekDay() {
		String sCurDay=getCurDate();
		String sDay="1000-01-07";// ����
		return Integer.parseInt(daySub(sCurDay,sDay)%7+"");
	}
	/**
	 * ȡ�õ�ǰ�ꡣ
	 * �������ڣ�(2001-6-28 11:33:16)
	 * @return java.lang.String
	 */
	public static String getCurYear() {
		String s=getCurDate();
		return s.substring(0,s.indexOf("-"));
	}
	/**
	 * ȡ�õ�ǰ�����ڡ�
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getDay(String sdate) {
		try{
			java.sql.Date dt = java.sql.Date.valueOf(sdate);
			return dt.toString().substring(8,10);

		}catch(Exception e){
			return "0";
		}
	}
	/**
	 * �������ڡ�
	 * �������ڣ�(2001-7-10 18:01:30)
	 * @return boolean
	 * @param strDate java.lang.String
	 */
	public static String getEndDateOfMonth(String strDate) {

		int iD = 30;
		int iM = StrTool.str2int(strDate.substring(5,7));
		if (iM == 1
			|| iM == 3
			|| iM == 5
			|| iM == 7
			|| iM == 8
			|| iM == 10
			|| iM == 12)
			iD = 31;
		else
			if (iM == 4 || iM == 6 || iM == 9 || iM == 11)
				iD = 30;
			else
				if (iM == 2) {
					if (isRenYear(strDate))
						iD = 29;
					else
						iD = 28;
				}

		strDate=strDate.substring(0,7)+"-"+iD;

		return strDate;

	}
	/**
	 * ȡ�ñ��ص�ǰ���ڡ�
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getLocalCurDate() {
		String sData = getCurDate();
		return getYear(sData) + "��" + getMonth(sData) + "��" + getDay(sData) + "��";
	}
	/**
	 * �ܼ���
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getLocalCurWeekDay() {
		int iDay = getCurWeekDay();
		String sLocalDay = "";
		if (iDay==0)
			sLocalDay = "��";
		else if (iDay==1)
			sLocalDay = "һ";
		else if (iDay==2)
			sLocalDay = "��";
		else if (iDay==3)
			sLocalDay = "��";
		else if (iDay==4)
			sLocalDay = "��";
		else if (iDay==5)
			sLocalDay = "��";
		else if (iDay==6)
			sLocalDay = "��";

		return "����"+sLocalDay;
	}
	/**
	 * ȡ�ñ������ڡ�
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getLocalDate(String sDate) {
		return getYear(sDate) + "��" + getMonth(sDate) + "��" + getDay(sDate) + "��";
	}
	/**
	 * �����ܼ�s��
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getLocalWeekDay(String sDate) {
		int iDay = getWeekDay(sDate);
		String sLocalDay = "";
		if (iDay==0)
			sLocalDay = "��";
		else if (iDay==1)
			sLocalDay = "һ";
		else if (iDay==2)
			sLocalDay = "��";
		else if (iDay==3)
			sLocalDay = "��";
		else if (iDay==4)
			sLocalDay = "��";
		else if (iDay==5)
			sLocalDay = "��";
		else if (iDay==6)
			sLocalDay = "��";

		return "����"+sLocalDay;
	}
	/**
	 * ȡ�õ�ǰ�¡�
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getMonth(String sdate) {
		try{
			java.sql.Date dt = java.sql.Date.valueOf(sdate);
			return dt.toString().substring(5,7);

		}catch(Exception e){
			return "0";
		}

	}
	/**
	 * ȡ���µ�������
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static int getNumOfMonth(String sDate, String sMonth) {
		if (sMonth == null || sDate == null)
			return 0;
		int iYear = StrTool.str2int(sDate);

		if (sMonth.equals("1")
			|| sMonth.equals("3")
			|| sMonth.equals("5")
			|| sMonth.equals("7")
			|| sMonth.equals("8")
			|| sMonth.equals("10")
			|| sMonth.equals("12"))
			return 31;
		else
			if (sMonth.equals("4")
				|| sMonth.equals("6")
				|| sMonth.equals("9")
				|| sMonth.equals("11"))
				return 30;
			else
				if (iYear % 4 == 0) {
					if (iYear % 100 != 0 && iYear % 400 == 0) {
						return 29;
					}
				} else {
					return 28;
				}
		return 0;
	}
	/**
	 * ȡ�õ�ǰ�ܼ���
	 * �������ڣ�(2001-6-28 11:33:16)
	 * @return java.lang.String
	 */
	public static int getWeekDay(String sDate) {
		String sDay="1000-01-07";// ����
		return Integer.parseInt(daySub(sDate,sDay)%7+"");
	}
	/**
	 * ȡ���ꡣ
	 * �������ڣ�(2001-6-28 11:00:03)
	 * @return java.lang.String
	 */
	public static String getYear(String sdate) {
		try{
			java.sql.Date dt = java.sql.Date.valueOf(sdate);
			return dt.toString().substring(0,4);

		}catch(Exception e){
			return "0";
		}
	}

		  public static String formatDate(String strDate) {

					String sdate = strDate;
			  try {
					if(!isDate(strDate)){
						  return sdate;
					}
					String sH = "";
					String sM = "";
					String sS = "";
					if (strDate.indexOf(" ") != -1 && strDate.indexOf(":") != -1) {
						  int iB = strDate.indexOf(":");
						  int iB1 = strDate.indexOf(":", iB + 1);
						  sH = strDate.substring(strDate.indexOf(" ") + 1, iB);
						  sM = strDate.substring(iB + 1, iB1);
						  sS = strDate.substring(iB1 + 1);
						  int iH = Integer.parseInt(sH);
						  int iM = Integer.parseInt(sM);
						  int iS = Integer.parseInt(sS);

						  String sd = strDate.substring(0, strDate.indexOf(" "));
						  java.sql.Date.valueOf(sd.trim());
						  sdate = sd;
					}
				int index = sdate.indexOf("-");
					  int index1 = sdate.indexOf("-", index + 1);
					String sY = sdate.substring(0, index);
					sM = sdate.substring(index + 1, index1);
					String sD = sdate.substring(index1 + 1);

					int iM = StrTool.str2int(sM);
					int iD = StrTool.str2int(sD);
					sdate = sY+"-";
					sdate += (iM < 10) ? "0" + sM : sM;
					sdate += "-";
					sdate+=(iD<10)?"0"+sD:sD;

					if(strDate.indexOf(" ")!=-1){
						 sdate += strDate.substring(strDate.indexOf(" "), strDate.length()-1);
				}
				  return sdate;
			  } catch (Exception e) {
				  return sdate;
			  }
		  }
	/**
	 * �˴����뷽��˵����
	 * �������ڣ�(2001-9-13 14:28:31)
	 * @return boolean
	 * @param sDate java.lang.String
	 */
	public static boolean isRenYear(String sDate) {
		try{
			String s = 	java.sql.Date.valueOf(sDate).toString();
			int iYear = StrTool.str2int(s.substring(0,4));

			if (iYear % 4 == 0) {
					if (iYear % 100 == 0 && iYear % 400 != 0) {
						return false;
					}
					else
						return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}

	}

	/**
	 * �ƶ��¡�
	 * �������ڣ�(2001-6-28 15:57:34)
	 * @return java.lang.String
	 * @param sDate 2001-02
	 * @param iDays int
	 */
	public static String MonthMove(String sDate, int iMonth) {
		int iyear = Integer.parseInt(sDate.substring(0,sDate.indexOf("-")));
		int imonth = Integer.parseInt(sDate.substring(sDate.indexOf("-") + 1, sDate.lastIndexOf("-")))-1;
		int iday = Integer.parseInt(sDate.substring(sDate.lastIndexOf("-") + 1));
	
		java.util.GregorianCalendar thedate = new java.util.GregorianCalendar(iyear, imonth, iday);
		thedate.add(java.util.GregorianCalendar.MONTH, iMonth);
		java.util.Date d = thedate.getTime();
		java.text.DateFormat df = java.text.DateFormat.getDateInstance();
	
		String s = df.format(d);
	
		String syear=s.substring(0,s.indexOf("-"));
		String smonth=s.substring(s.indexOf("-") + 1, s.lastIndexOf("-"));
		if (smonth.length()==1) smonth="0"+smonth;
		String sday=s.substring(s.lastIndexOf("-") + 1);
		if (sday.length()==1) sday="0"+sday;
	
		return syear+"-"+smonth+"-"+sday;			  
	}
}
