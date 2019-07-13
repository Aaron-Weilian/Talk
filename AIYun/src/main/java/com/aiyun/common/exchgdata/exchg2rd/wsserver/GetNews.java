//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import org.tempuri.*;
//
//import com.aiyun.common.bo.BusinessObject;
//
///**
// * @author dejin
// *
// * ��ȡҵ�������Ϣ����
// */
//public class GetNews extends BusinessObject implements Runnable {
//	
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public void run() {
//		
//		try {
//			OperBean ob = new OperBean();
//			String qyallid = ob.getPubQyid();
//			
//			//����web service
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//			boolean bResult = true;
//			String sResult = svr.hgnews(qyallid,OperBean.getAuthSN());
//			
//			ob.setConnection(getConnection());
//			//boolean bFlag =true;
//			boolean bFlag = ob.getNews(sResult);
//			if (bFlag) 
//				commit();
//			else
//				rollback();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//		}
//		
//	}
//}
