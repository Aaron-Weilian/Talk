//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import org.tempuri.*;
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
//public class SendBatchYWData extends BusinessObject implements Runnable {
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public void run() {
//		try {
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//			
//			java.text.SimpleDateFormat fd=new java.text.SimpleDateFormat("HH");
//			java.util.Date date = null;
//			
//			DataBaseObject dbo = getDataBaseObject();
//			OperBean ob = new OperBean();
//			ob.setConnection(getConnection());
//			
//			CommonBean cbBatch = null;
//			
//			while (true) {
//				date=new java.util.Date(System.currentTimeMillis());
//				int id = Integer.parseInt(fd.format(date));
//
//				if (!(id<8 && id>=1) || (id<23 && id>=19))	//��1-8,19-23��֮��ִ��
//					break;
//					
//				cbBatch = dbo.getData("select ywlist.id from ywlist Where ywlist.status=6 And rownum=1");
//				if (cbBatch.getRowNum()==0) break;
//				
//				String ywid = cbBatch.getValue("id");
//				String bgdh = cbBatch.getValue("bgdh");
//
//				//�Ȱ�״̬����,��ֹͬ������
//				dbo.execute("UPDATE YWLIST SET STATUS=2,sbdate=sysdate WHERE status=6 And id = (Select ywid From bgd_zc_mx Where bgd_zc_mx.ywid='"+ywid+"' And bgd_zc_mx.bgdh Is Not null)");
//				dbo.execute("UPDATE YWLIST SET STATUS=5,sbdate=sysdate WHERE status=6 And id = (Select ywid From bgd_zc_mx Where bgd_zc_mx.ywid='"+ywid+"' And bgd_zc_mx.bgdh Is null)");
//				multicommit();
//				
//				String ywdata = "";
//				try {
//					ywdata = ob.getData(ywid);
//				}
//				catch (Exception e) {
//					dbo.execute("UPDATE YWLIST SET STATUS=99 WHERE ID='"+ywid+"'");
//					multicommit();
//					e.printStackTrace();
//				}
//				
//				String sResult = "";	//;Ϊ���ǳɹ�
//				//if (1>0) return;
//				if (!ywdata.equals("")) {
//					try {
//						//����web service
//						sResult = svr.upload(ywdata,OperBean.getAuthSN());
//					}
//					catch (java.rmi.RemoteException e) {
//						sResult="Զ������ʧ��";
//					}
//				}
//				else {
//					sResult = "��ҵ�����걨";
//				}
//				//�ϴ�û�гɹ�
//				if (!sResult.equals("")) {
//					dbo.execute("UPDATE YWLIST SET STATUS=99 WHERE ID='"+ywid+"'");
//					multicommit();
//				}
//			}
//			
//			dbo.execute("UPDATE YWLIST SET STATUS=6 WHERE status=99");
//			
//			commit();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//		}
//		
//	}
//}
