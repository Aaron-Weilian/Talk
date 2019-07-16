package com.aiyun.common.exchgdata.csc2ie.bean;

import com.aiyun.common.bo.DBUtil;

public class UploadAcc extends DBUtil {
	public boolean UpAcct() {
		try {
			java.text.SimpleDateFormat fd=new java.text.SimpleDateFormat("HH");
			java.util.Date date=new java.util.Date(System.currentTimeMillis());
			int id = Integer.parseInt(fd.format(date));
		
			if ((id<11 && id >=10) || (id<23 && id>=22)) {	
				UploadBean accbean = new UploadBean();
				//accbean.setConnection(this.getConnection());
				accbean.UploadAcc(null,null);
			}
			commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			return false;
		}
	}
}
