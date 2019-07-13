//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import com.aiyun.common.exchgdata.csc2ie.bean.UploadAcc;
//import com.aiyun.common.exchgdata.csc2ie.bean.UploadBean;
//
///**
// * @author dejin
// *
// * �ṩweb service�Ľ�������
// */
//public class WSServer {
//	//�걨ҵ��
//	public boolean applyYW(String ywid) {
//		try {
//			SendYWData ywdata = new SendYWData(ywid);
//			Thread t = new Thread(ywdata);
//			t.start();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	public boolean applyBatchYW() {
//		try {
//			SendBatchYWData ywdata = new SendBatchYWData();
//			Thread t = new Thread(ywdata);
//			t.start();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	public void getPassYW() {
//		try {
//			
//			GetAuditData adata = new GetAuditData();
//			Thread t = new Thread(adata);
//			t.start();
//			
//			UploadAcc accbean = new UploadAcc();
//			accbean.UpAcct();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
//	}
//	
//	//��ȡ���ع���
//	public void getNews() {
//		try {
//			GetNews adata = new GetNews();
//			Thread t = new Thread(adata);
//			t.start();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
//	}
//	
//	//ȡ��ҵ��
//	public boolean cancelYW(String ywid) {
//		try {
//			boolean bFlag = true;
//			CancelYW cancelyw = new CancelYW();
//			bFlag = cancelyw.cancelyw(ywid);
//			return bFlag;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	//���ع�����Ϣ
//	public void downloadCountry() {
//		try {
//			GetCLData cldata = new GetCLData("country");
//			Thread t = new Thread(cldata);
//			t.start();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
//	}
//	
//	//���ر�����Ϣ
//	public void downloadCurr() {
//		try {
//			GetCLData cldata = new GetCLData("curr");
//			Thread t = new Thread(cldata);
//			t.start();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
//	}
//	
//	//���ؼ�����λ��Ϣ
//	public void downloadUnit() {
//		try {
//			GetCLData cldata = new GetCLData("unit");
//			Thread t = new Thread(cldata);
//			t.start();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
//	}
//}
