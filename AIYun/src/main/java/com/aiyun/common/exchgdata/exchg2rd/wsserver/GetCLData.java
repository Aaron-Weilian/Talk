//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import java.sql.SQLException;
//
//import org.tempuri.*;
//
//import com.aiyun.common.bo.BusinessObject;
//
///**
// * @author dejin
// *
// * ����ҵ������
// */
//public class GetCLData extends BusinessObject implements Runnable {
//	private String datatype = null;
//	
//	/* ���� Javadoc��
//	 * @see java.lang.Runnable#run()
//	 */
//	public void run() {
//		try {
//			//����web service
//			Service1Locator locator = new Service1Locator();
//			Service1Soap svr = locator.getService1Soap();
//			
//			OperBean ob = new OperBean();
//			ob.setConnection(getConnection());
//			
//			boolean bResult = true;
//			String cldata = null;
//			if (datatype.equals("country")) {
//				cldata = svr.countryDownload(OperBean.getAuthSN());
//				bResult = ob.getCountryData(cldata);
//			}
//			else if (datatype.equals("curr")) {
//				cldata = svr.hlDownload(OperBean.getAuthSN());
//				bResult = ob.getCurrData(cldata);
//			}
//			else if (datatype.equals("unit")) {
//				cldata = svr.unitsDownload(OperBean.getAuthSN());
//				bResult = ob.getUnitData(cldata);
//			}
//			
//			commit();
//		}
//		catch (SQLException e) {
//			rollback();
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			rollback();
//			e.printStackTrace();
//			return;
//		}
//		
//	}
//	
//	public GetCLData(String datatype) {
//		this.datatype = datatype;
//	}
//}
