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
//public class GetAuditData extends BusinessObject implements Runnable {
//	
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public void run() {
//		try {
//			if (1>0) return;
//			OperBean ob = new OperBean();
//			String qyallid = ob.getPubQyid();
//			
//			//����web service
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//			boolean bResult = true;
//			String sResult = svr.auditing(qyallid,OperBean.getAuthSN());
//			
//			ob.setConnection(getConnection());
//			boolean bFlag = ob.getAuditData(sResult);
//			if (bFlag) 
//				commit();
//			else
//				rollback();
//			
//			//���������걨ʧ�ܵ�ҵ��
//			SendBatchYWData ywdata = new SendBatchYWData();
//			Thread t = new Thread(ywdata);
//			t.start();
//			
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//		}
//		
//	}
//}
