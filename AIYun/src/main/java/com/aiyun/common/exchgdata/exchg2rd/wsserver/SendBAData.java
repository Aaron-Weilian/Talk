//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//
//import java.beans.Encoder;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.tempuri.Service1Locator;
//import org.tempuri.Service1Soap;
//
//import com.aiyun.common.bo.BusinessObject;
//import com.aiyun.common.bo.DataBaseObject;
//import com.aiyun.common.vo.CommonBean;
//
///**
// * @author dejin
// *
// * ����ҵ������
// */
//public class SendBAData extends BusinessObject {
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public boolean sendBA(String ywtype) {
//		try {
//			DataBaseObject dbo = getDataBaseObject();
//			
//			OperBean ob = new OperBean();
//			ob.setConnection(getConnection());
//
//			CommonBean cbBa = null;
//			String ywdata = null;
//			
//			boolean bResult = true;
//			//����web service
//			try {
//			
//				Service1Locator locator = new Service1Locator();
//
//				Service1Soap c = locator.getService1Soap();
//			
//				//�ϴ��ϼ�
//				if (ywtype.equals("1")) {
//					cbBa = ob.UpdataBaList("BALJ");
//					ywdata = ob.getBaData("BALJ");
//				
//					bResult = bResult && c.dzljUpload(ywdata,OperBean.getAuthSN());
//					bResult = bResult && dbo.execute(cbBa);
//				}
//				
//				//�ϴ���Ʒ
//				if (ywtype.equals("2")) {
//					cbBa = ob.UpdataBaList("BACP");
//					ywdata = ob.getBaData("BACP");
//					bResult = c.dzcpUpload(ywdata,OperBean.getAuthSN());
//					bResult = bResult && dbo.execute(cbBa);
//				}
//				
//				//�ϴ�����
//				if (ywtype.equals("3")) {
//					cbBa = ob.UpdataBaList("BADH");
//					ywdata = ob.getBaData("BADH");
//					bResult = c.dzdhUpload(ywdata,OperBean.getAuthSN());
//					bResult = bResult && dbo.execute(cbBa);
//				}
//			}
//			catch (java.rmi.RemoteException e) {
//				e.printStackTrace();
//				bResult = false;
//			}
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
