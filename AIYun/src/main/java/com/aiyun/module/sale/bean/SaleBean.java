package com.aiyun.module.sale.bean;

import com.aiyun.common.bo.BusinessObject;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

public class SaleBean extends BusinessObject {
	public CommonBean getHomeYWList(CommonBean cbUser, CommonBean cbCond) {
		try {
			String sql = "SELECT rownum,YWLIST.ID,to_char(YWLIST.SPDATE,'yyyy-mm-dd') AS spdate,to_char(YWLIST.SBDATE,'yyyy-mm-dd') AS sbdate, BGD_ZC_MX.MXDNO, CL_STATUS.F_START||CL_STATUS.sName||CL_STATUS.F_END AS STATUSNAME, CL_YWTYPE.sName AS TYPEIDNAME, CL_YWTYPE.YWLINK || YWLIST.ID AS YWLINK "
						+"FROM YWLIST "
						+"LEFT JOIN BGD_ZC_MX ON BGD_ZC_MX.YWID=YWLIST.ID "
						+"LEFT JOIN CL_STATUS ON CL_STATUS.ID=YWLIST.STATUS "
						+"INNER JOIN CL_YWTYPE ON CL_YWTYPE.ID=YWLIST.TYPEID AND CL_YWTYPE.BSHOW=1 "
						+"WHERE YWLIST.STATUS<>3 "
						+"AND YWLIST.TYPEID IN (SELECT YWTYPEID FROM SYS_BUSNODE WHERE ID IN (SELECT BUSNODEID FROM USER_AUTH WHERE USERID='"+cbUser.getValue("id")+"') AND YWTYPEID IS NOT NULL)";
			if (cbCond != null && cbCond.getColumnNum()>0) {
				String svalue=cbCond.getValue("ywtypeid");
				if (svalue!=null && !svalue.equals(""))	//ҵ������
					sql += "AND YWLIST.TypeID='" + svalue + "'";
				svalue=cbCond.getValue("mxdno");
				if (svalue!=null && !svalue.equals("")) 	//��ϸ����
					sql += "AND BGD_ZC_MX.MXDNO LIKE '%" + svalue.toUpperCase() + "%'";
				svalue=cbCond.getValue("ywstatus");
				if (svalue!=null && !svalue.equals("")) 	//ҵ��״̬
					sql += "AND YWLIST.STATUS = '" + svalue + "'";
			}
			sql = sql + " ORDER BY YWLIST.ADDDATE DESC ";
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(sql);

			release();
			return cb;
		} catch (Exception e) {
			Log.error(this, "ȡ��ҵ����Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ��ҵ����Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}
	
	public CommonBean getCond(CommonBean cbUser, CommonBean cbCond) {
		try {
			DataBaseObject dbo = getDataBaseObject();
			String sql = "SELECT ID,sName AS  FROM CL_YWTYPE WHERE BSHOW=1 AND ID IN (SELECT YWTYPEID FROM SYS_BUSNODE WHERE ID IN (SELECT BUSNODEID FROM USER_AUTH WHERE USERID='"+cbUser.getValue("id")+"') AND YWTYPEID IS NOT NULL) ORDER BY ID";
			CommonBean cbYWType = dbo.getData(sql);
			String[][] arrYWType = com.aiyun.common.tool.Function.bean2arr(cbYWType);
			cbCond.setColListValue("id",arrYWType);
			
			release();
			return cbCond;
		} catch (Exception e) {
			Log.error(this, "ȡ��ҵ����Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ��ҵ����Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}
}
