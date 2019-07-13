//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import org.tempuri.*;
//
//import com.aiyun.common.bo.BusinessObject;
//import com.aiyun.common.bo.DataBaseObject;
//
///**
// * @author dejin
// *
// * ȡ��ҵ��(Ϊ���Ժ���ܶ����̷߳���,ʹ�ö������ʵ��)
// */
//public class CancelYW extends BusinessObject {
//	private String ywid = null;
//	
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public boolean cancelyw(String ywid) {
//		try {
//			//if (1>0) return true;
//			//����web service
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//			boolean bResult = true;
//			bResult = svr.cancleOperation(OperBean.getPubQyid(),ywid,OperBean.getAuthSN());
//			
//			return bResult;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//	}
//}
