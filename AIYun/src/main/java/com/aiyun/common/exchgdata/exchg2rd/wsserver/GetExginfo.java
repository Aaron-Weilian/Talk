//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import org.tempuri.Service1Locator;
//import org.tempuri.Service1Soap;
//
//import com.paink.module.bgddel.bean.BgddelBean;
//
///**
// * ��ȡҵ��Ľ������
// */
//public class GetExginfo {
//	public String GetExginfo(String ywid) {
//		try {
//			//����web service
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//		
//			boolean bResult = true;
//			String ywdata = null;
//			ywdata = svr.getQyinfo(OperBean.getAuthSN(),ywid);
//			return ywdata;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//}
