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
// * ����ҵ������
// */
//public class SendYWData extends BusinessObject implements Runnable {
//	private String ywid = null;
//	
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public void run() {
//		try {
//			DataBaseObject dbo = getDataBaseObject();
//			OperBean ob = new OperBean();
//			ob.setConnection(getConnection());
//			
//			String ywdata = null;
//			try {
//				ywdata = ob.getData(ywid);
//			}
//			catch (Exception e) {
//				dbo.execute("UPDATE YWLIST SET STATUS=6 WHERE ID='"+ywid+"'");
//				commit();
//				e.printStackTrace();
//				return;
//			}
//			
//			String sResult = "";	//;Ϊ���ǳɹ�
//			//if (1>0) return;
//			if (!ywdata.equals("")) {
//				try {
//					//����web service
//					Service1Locator locator = new Service1Locator();
//					Service1Soap svr = locator.getService1Soap();
//					sResult = svr.upload(ywdata,OperBean.getAuthSN());
//				}
//				catch (java.rmi.RemoteException e) {
//					sResult="Զ������ʧ��";
//				}
//			}
//			else {
//				sResult = "��ҵ�����걨";
//			}
//			//�ϴ�û�гɹ�
//			if (!sResult.equals("")) {
//				dbo.execute("UPDATE YWLIST SET STATUS=6 WHERE ID='"+ywid+"'");
//				multicommit();
//			}
//			
//			//�����ϴ�֮ǰ�걨ʧ�ܵ�ҵ��
//			if (false) {
//				SendBatchYWData batchdata = new SendBatchYWData();
//				Thread t = new Thread(batchdata);
//				t.start();
//			}
//			
//			release();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//		}
//		
//	}
//	
//	public SendYWData(String ywid) {
//		this.ywid = ywid;
//	}
//}
