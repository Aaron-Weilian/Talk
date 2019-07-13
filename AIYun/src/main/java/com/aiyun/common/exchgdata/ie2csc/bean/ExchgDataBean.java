///*
// * ҵ����Ϣ����
// */
//package com.aiyun.common.exchgdata.ie2csc.bean;
//
//import com.aiyun.common.bo.BusinessObject;
//import com.aiyun.common.bo.DataBaseObject;
//import com.aiyun.common.util.Log;
//import com.aiyun.common.vo.CommonBean;
//import com.paink.module.pub.bean.CkywBean;
//
///**
// * ҵ����Ϣ�б�
// */
//public class ExchgDataBean extends BusinessObject {
//	//�����б�
//	public CommonBean listData(String ywid, String sobject, String modify_mark) {
//		try {
//			CommonBean cb = null;
//			if (sobject.equals("lj")) {
//				cb = listLJ(ywid,modify_mark);
//			}
//			else if (sobject.equals("cp")) {
//				cb = listCP(ywid,modify_mark);
//			}
//			else if (sobject.equals("bom")) {
//				cb = listBOM(ywid,modify_mark);
//			}
//			else if (sobject.equals("cpck")) {
//				cb = listCPCK(ywid,modify_mark);
//			}
//			else if (sobject.equals("trade")) {
//				cb = listTradeCPCK(ywid,modify_mark);
//			}
//			else if (sobject.equals("sjgzc")) {
//				cb = listSJGZC(ywid,modify_mark);
//			}
//			else if (sobject.equals("cphh")) {
//				cb = listCPHH(ywid,modify_mark);
//			}
//			else if (sobject.equals("ljth")) {
//				cb = listLJTH(ywid,modify_mark);
//			}
//			else if (sobject.equals("nx")) {
//				cb = listNX(ywid,modify_mark);
//			}
//			else if (sobject.equals("otherck")) {
//				cb = listOtherck(ywid,modify_mark);
//			}
//			else if (sobject.equals("ljnx")) {
//				cb = listLJNX(ywid,modify_mark);
//			}//�����ϼ�
//			else if (sobject.equals("jklj")) {
//				cb = listJKLJ(ywid,modify_mark,"JKLJ");
//			}//�����豸
//			else if (sobject.equals("jksb")) {
//				cb = listJKSB(ywid,modify_mark,"JKSB");
//			}//��תת��
//			else if (sobject.equals("sjgzr")) {
//				cb = listJKLJ(ywid,modify_mark,"SJGZR");
//			}//�ϼ�����
//			else if (sobject.equals("ljhh")) {
//				cb = listLJHH(ywid,modify_mark);
//			}//��������
//			else if (sobject.equals("otherjk")) {
//				cb = listOTHERJK(ywid,modify_mark,"OTHERJK");
//			}//��Ʒ�˻�
//			else if (sobject.equals("cpth")) {
//				cb = listCPTH(ywid,modify_mark);
//			}//��������
//			else if (sobject.equals("rejk")) {
//				cb = listOTHERJK(ywid,modify_mark,"REYW");
//			}//��������
//			else if (sobject.equals("reck")) {
//				cb = listReck(ywid,modify_mark);
//			}
//			
//			
//			release();
//			return cb;
//		} catch (Exception e) {
//			rollback();
//			Log.error(this, "ȡ����ʱ��������" + e.getMessage());
//			getErrMsgBean().addCommonMessage("ȡ����ʱ��������" + e.getMessage());
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	//�����б�
//	public boolean exchgData(String ywid, String sobject, String[] ObjectIDs) {
//		try {
//			if (ObjectIDs == null || ObjectIDs.length<=0)
//				return true;
//			
//			boolean bFlag = true;
//			if (sobject.equals("lj")) {
//				bFlag = exchgLJ(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("cp")) {
//				bFlag = exchgCP(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("bom")) {
//				bFlag = exchgBOM(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("cpck")) {
//				bFlag = exchgCPCK(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("sjgzc")) {
//				bFlag = exchgSJGZC(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("cphh")) {
//				bFlag = exchgCPHH(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("ljth")) {
//				bFlag = exchgLJTH(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("nx")) {
//				bFlag = exchgNX(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("otherck")) {
//				bFlag = exchgOtherck(ywid,ObjectIDs);
//			}
//			else if (sobject.equals("ljnx")) {
//				bFlag = exchgLJNX(ywid,ObjectIDs);
//			}//�����ϼ�
//			else if (sobject.equals("jklj")) {
//				bFlag = exchgJKLJ(ywid,ObjectIDs);
//			}//��תת��
//			else if (sobject.equals("sjgzr")) {
//				bFlag = exchgSJGZR(ywid,ObjectIDs);
//			}//�ϼ�����
//			else if (sobject.equals("ljhh")) {
//				bFlag = exchgLJHH(ywid,ObjectIDs);
//			}//��������
//			else if (sobject.equals("otherjk")) {
//				bFlag = exchgOTHERJK(ywid,ObjectIDs);
//			}//�����豸
//			else if (sobject.equals("jksb")) {
//				bFlag = exchgJKSB(ywid,ObjectIDs);
//			}//��Ʒ�˻�
//			else if (sobject.equals("cpth")) {
//				bFlag = exchgCPTH(ywid,ObjectIDs);
//			}//��������
//			else if (sobject.equals("rejk")) {
//				bFlag = exchgREJK(ywid,ObjectIDs);
//			}//��������
//			else if (sobject.equals("reck")) {
//				bFlag = exchgReck(ywid,ObjectIDs);
//			}
//			
//			
//			
//			if (bFlag) 
//				commit();
//			else
//				rollback();
//			
//			return bFlag;
//		} catch (Exception e) {
//			rollback();
//			Log.error(this, "��������ʱ��������" + e.getMessage());
//			getErrMsgBean().addCommonMessage("��������ʱ��������" + e.getMessage());
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	//�ϼ��б�
//	//2010-06-07 sunxiaofeng ���ϼ�����ѡ��ҳ������������ѡ�Ϲ���ȥ���ظ����ݡ�
//	public CommonBean listLJ(String ywid, String modify_mark) throws Exception {
//		/*
//		String sql = "Select T1.* From (SELECT ie_lj.*, cl_unit.sname as unitname "
//					+"FROM IE_LJ "
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=IE_LJ.UNIT "
//					+"WHERE IE_LJ.modify_mark='"+modify_mark+"' AND OPER_FLAG='0' "
//					+"ORDER BY IE_LJ.ADDDATE DESC,IE_LJ.LJNO ) T1 "
//					+"Inner Join (Select ie_lj.ljno,max(ie_lj.adddate) adddate "
//					+"from ie_lj where ie_lj.modify_mark = '"+modify_mark+"' And oper_flag = '0' Group By ljno) T2 "
//					+"On T1.ljno = T2.ljno And T1.adddate = T2.adddate "; 
//		*/
//		
//		String sql = "Select T1.*  From (SELECT ie_lj.*, cl_unit.sname as unitname "
//					+ "FROM IE_LJ "
//					+ "LEFT JOIN CL_UNIT ON CL_UNIT.ID = IE_LJ.UNIT "
//					+ "WHERE IE_LJ.modify_mark = '"+modify_mark+"' "
//					+ "AND OPER_FLAG = '0' "
//					+ "ORDER BY IE_LJ.ADDDATE DESC, IE_LJ.LJNO) T1 "
//					+ "Inner Join (Select min(id) As id "
//					+ "From (Select T3.id,T3.ljno "
//					+ "From ie_lj T3 "
//					+ "Inner Join (Select ie_lj.ljno, "
//					+ "min(ie_lj.adddate) adddate "
//					+ "from ie_lj "
//					+ "where ie_lj.modify_mark = '"+modify_mark+"' "
//					+ "And oper_flag = '0' "
//					+ "Group By ljno) T4 On T3.Ljno = T4.ljno "
//					+ "And T3.adddate = "
//					+ "T4.adddate "
//                   + "  where t3.modify_mark = '"+modify_mark+"' "
//                   + "and t3.oper_flag = '0') "
//                   + "Group By ljno) T2 On T1.id = T2.id"; 
//		
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//2010-05-25 sunxiaofeng ���ϼ�����ѡ��ҳ������������ѡ�Ϲ��ܡ�
//	public CommonBean listLJForWLFlagSearch(String ywid, String modify_mark,String wlflag){
//		try
//		{
//			CommonBean cb = null;
//			
//			String flag = wlflag.toUpperCase();
//			String sql = "";
//			
//			if(wlflag.trim().length()>0)
//			{
//				/* sql= "Select T1.* From (SELECT ie_lj.*, cl_unit.sname as unitname "
//					+"FROM IE_LJ "
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=IE_LJ.UNIT "
//					+"WHERE IE_LJ.modify_mark='"+modify_mark+"' AND OPER_FLAG='0' And upper(wlflag) ='" + flag + "'"
//					+"ORDER BY IE_LJ.ADDDATE DESC,IE_LJ.LJNO) T1 "
//				 
//					+"Inner Join (Select ie_lj.ljno,max(ie_lj.adddate) adddate "
//					+"from ie_lj where ie_lj.modify_mark = '"+modify_mark+"' And oper_flag = '0' And upper(wlflag) ='" + flag + "' Group By ljno) T2 "
//					+"On T1.ljno = T2.ljno And T1.adddate = T2.adddate "; 
//				 */
//				 
//				 sql = "Select T1.*  From (SELECT ie_lj.*, cl_unit.sname as unitname "
//						+ "FROM IE_LJ "
//						+ "LEFT JOIN CL_UNIT ON CL_UNIT.ID = IE_LJ.UNIT "
//						+ "WHERE IE_LJ.modify_mark = '"+modify_mark+"' "
//						+ "AND OPER_FLAG = '0' And upper(wlflag) ='" + flag + "' "
//						+ "ORDER BY IE_LJ.ADDDATE DESC, IE_LJ.LJNO) T1 "
//						+ "Inner Join (Select min(id) As id "
//						+ "From (Select T3.id,T3.ljno "
//						+ "From ie_lj T3 "
//						+ "Inner Join (Select ie_lj.ljno, "
//						+ "min(ie_lj.adddate) adddate "
//						+ "from ie_lj "
//						+ "where ie_lj.modify_mark = '"+modify_mark+"' "
//						+ "And oper_flag = '0' And upper(wlflag) ='" + flag + "' "
//						+ "Group By ljno) T4 On T3.Ljno = T4.ljno "
//						+ "And T3.adddate = "
//						+ "T4.adddate "
//	                   + "  where t3.modify_mark = '"+modify_mark+"' "
//	                   + "and t3.oper_flag = '0' And upper(wlflag) ='" + flag + "') "
//	                   + "Group By ljno) T2 On T1.id = T2.id";  
//				 
//				
//			}
//			else
//			{
//				sql = "Select T1.*  From (SELECT ie_lj.*, cl_unit.sname as unitname "
//					+ "FROM IE_LJ "
//					+ "LEFT JOIN CL_UNIT ON CL_UNIT.ID = IE_LJ.UNIT "
//					+ "WHERE IE_LJ.modify_mark = '"+modify_mark+"' "
//					+ "AND OPER_FLAG = '0' "
//					+ "ORDER BY IE_LJ.ADDDATE DESC, IE_LJ.LJNO) T1 "
//					+ "Inner Join (Select min(id) As id "
//					+ "From (Select T3.id,T3.ljno "
//					+ "From ie_lj T3 "
//					+ "Inner Join (Select ie_lj.ljno, "
//					+ "min(ie_lj.adddate) adddate "
//					+ "from ie_lj "
//					+ "where ie_lj.modify_mark = '"+modify_mark+"' "
//					+ "And oper_flag = '0' "
//					+ "Group By ljno) T4 On T3.Ljno = T4.ljno "
//					+ "And T3.adddate = "
//					+ "T4.adddate "
//                   + "  where t3.modify_mark = '"+modify_mark+"' "
//                   + "and t3.oper_flag = '0') "
//                   + "Group By ljno) T2 On T1.id = T2.id";  
//			}
//			
//			DataBaseObject dbo = getDataBaseObject();
//			cb = dbo.getData(sql);
//			
//			release();
//			
//			return cb;
//			
//		} catch (Exception e) {
//			rollback();
//			Log.error(this, "ȡ����ʱ��������" + e.getMessage());
//			getErrMsgBean().addCommonMessage("ȡ����ʱ��������" + e.getMessage());
//			e.printStackTrace();
//			return null;
//		}
//		
//		
//	}
//	
//	//��Ʒ�б� 
//	//2010-06-07 sunxiaofeng ȥ�����ظ����� ��
//	public CommonBean listCP(String ywid, String modify_mark) throws Exception {
//		/*
//		String sql = "SELECT ie_cp.*, cl_unit.sname as unitname "
//					+"FROM IE_CP "
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=IE_CP.UNIT "
//					+"WHERE OPER_FLAG='0' And Exists (Select cpno From ie_bom Where ie_bom.cpno=ie_cp.cpno And ie_bom.version=ie_cp.version) "
//					+"ORDER BY IE_CP.ADDDATE DESC, IE_CP.CPNO";
//		*/
//		/*
//		String sql = "Select T1.* From (SELECT ie_cp.*, cl_unit.sname as unitname "
//			+"FROM IE_CP "
//			+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=IE_CP.UNIT "
//			+"WHERE OPER_FLAG='0' And Exists (Select cpno From ie_bom Where ie_bom.cpno=ie_cp.cpno And ie_bom.version=ie_cp.version) "
//			+"ORDER BY IE_CP.ADDDATE DESC, IE_CP.CPNO ) T1 "
//			+"Inner Join (Select ie_cp.cpno,ie_cp.version,max(ie_cp.adddate) adddate "
//            +"from ie_cp where oper_flag = '0' And Exists (Select cpno From ie_bom Where ie_bom.cpno=ie_cp.cpno And ie_bom.version=ie_cp.version) Group By ie_cp.cpno,ie_cp.version) T2 "
//            +"On  T1.cpno = T2.cpno And T1.version = T2.version And T1.adddate = T2.adddate ";
//		*/
//		
//		String sql = "Select T1.* "
//			+"From (SELECT ie_cp.*, cl_unit.sname as unitname "
//			+"FROM IE_CP "
//			+"LEFT JOIN CL_UNIT ON CL_UNIT.ID = IE_CP.UNIT "
//			+"WHERE OPER_FLAG = '0' "
//			+"And Exists (Select cpno "
//			+"From ie_bom "
//			+"Where ie_bom.cpno = ie_cp.cpno "
//			+"And ie_bom.version = ie_cp.version) "
//			+"ORDER BY IE_CP.ADDDATE DESC, IE_CP.CPNO) T1 "
//			+"Inner Join (Select min(id) As id "
//			+"From (Select T3.id, T3.cpno, T3.version "
//			+"From ie_cp T3 "
//			+"Inner Join (Select ie_cp.cpno, "
//			+"ie_cp.version, "
//			+"min(ie_cp.adddate) adddate "
//			+"from ie_cp "
//			+"where oper_flag = '0' "
//			+"And Exists "
//			+"(Select cpno "
//			+"From ie_bom "
//			+"Where ie_bom.cpno = ie_cp.cpno "
//			+"And ie_bom.version = ie_cp.version) "
//			+"Group By ie_cp.cpno, ie_cp.version) T4 On T3.cpno = T4.cpno "
//			+"And T3.version = T4.version "
//			+"And T3.Adddate = T4.adddate "
//			+"Where T3.OPER_FLAG = '0' "
//			+"And Exists "
//			+"(Select cpno "
//			+"From ie_bom "
//			+"Where ie_bom.cpno = T3.cpno "
//			+"And ie_bom.version = T3.version)) "
//			+"Group By cpno, version) T2 On T1.id = T2.id";
//		
//		
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//	
//		return cb;
//	}
//	
//	//BOM�б�
//	public CommonBean listBOM(String ywid, String modify_mark) throws Exception {
//		String sql = "SELECT DISTINCT IE_BOM.CPNO,IE_BOM.VERSION \n"
//					+"FROM IE_BOM \n"
//					+"LEFT JOIN (SELECT DISTINCT CPNO,VERSION,1 AS HASFLAG FROM BA_DZZC_BOM LEFT JOIN YWLIST ON YWLIST.ID=BA_DZZC_BOM.YWID WHERE NOT (YWLIST.STATUS=3 AND BA_DZZC_BOM.MODIFY_MARK=2)) BOM ON BOM.CPNO=IE_BOM.CPNO AND BOM.VERSION=IE_BOM.VERSION \n"
//					+"WHERE IE_BOM.CPNO IN (SELECT DISTINCT CPNO FROM BA_DZZC_CP_QY) \n"
//					+"AND BOM.HASFLAG IS NULL \n"
//					+"AND EXISTS (SELECT LJNO FROM IE_BOM IEBOM WHERE LJNO IN (SELECT DISTINCT LJNO FROM BA_DZZC_LJ_QY) AND CPNO=IE_BOM.CPNO and rownum=1) \n"
//					+"AND OPER_FLAG="+(modify_mark.equals("3")?"0":modify_mark)+" \n"
//					+"ORDER BY CPNO";
//
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//��Ʒ����SO�б�
//	//And Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order))
//	public CommonBean listCPCK(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=8 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM CPCK_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'2' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE ie_ck_table.OPER_FLAG='0' AND ORDER_TYPE=8 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  ie_ck_table.SO_NO LIKE '%"+modify_mark+"%' AND ie_ck_table.MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE ie_ck_table.OPER_FLAG='0' AND ORDER_TYPE=8 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Not Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  ie_ck_table.SO_NO LIKE '%"+modify_mark+"%' AND ie_ck_table.MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=8 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//һ��ó��SO�б�
//	public CommonBean listTradeCPCK(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.invoice_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.fp_date,'yyyy-mm-dd') AS fp_date,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='3' AND FLAG = (SELECT TYPEID FROM YWLIST WHERE ID = '"+ywid+"') AND INVOICE_NO IN ( \n");
//		bfsql.append("SELECT INVOICE_NO FROM CPCK_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*���ӵ�SO*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.invoice_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.fp_date,'yyyy-mm-dd') AS fp_date,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='3' AND FLAG = (SELECT TYPEID FROM YWLIST WHERE ID = '"+ywid+"') \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,invoice_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//��תת��SO�б�
//	public CommonBean listSJGZC(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=11 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM SJGZC_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'2' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=11 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT cpno FROM BA_DZZC_CP_QY bacp Where bacp.cpno=ie_ck.cpno And ie_ck.version=bacp.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=11 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT cpno FROM BA_DZZC_CP_QY bacp Where bacp.cpno=ie_ck.cpno And ie_ck.version=bacp.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Not Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		
//		
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=11 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT cpno FROM BA_DZZC_CP_QY bacp Where bacp.cpno=ie_ck.cpno And ie_ck.version=bacp.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//��Ʒ����SO�б�
//	public CommonBean listCPHH(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=17 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM CPHH_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=17 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT cpno FROM BA_DZZC_CP_QY bacp Where bacp.cpno=ie_ck.cpno And ie_ck.version=bacp.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='3' AND ORDER_TYPE=17 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT cpno FROM BA_DZZC_CP_QY bacp Where bacp.cpno=ie_ck.cpno And ie_ck.version=bacp.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//��Ʒ����SO�б�
//	public CommonBean listNX(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=12 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM NX_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'2' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE ie_ck_table.OPER_FLAG='0' AND ORDER_TYPE=12 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO||VERSION FROM BA_DZZC_CP_QY ba Where ba.cpno=ie_ck.cpno And ba.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  ie_ck_table.SO_NO LIKE '%"+modify_mark+"%' AND ie_ck_table.MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE ie_ck_table.OPER_FLAG='0' AND ORDER_TYPE=12 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO||VERSION FROM BA_DZZC_CP_QY ba Where ba.cpno=ie_ck.cpno And ba.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append("And Not Exists (Select Id From ie_ck Where ie_ck.objectid=ie_ck_table.Id And Not Exists (Select Id From ie_gdhead Where ie_gdhead.job_order=ie_ck.job_order)) \n");
//		bfsql.append(" AND  ie_ck_table.SO_NO LIKE '%"+modify_mark+"%' AND ie_ck_table.MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip,ie_ck_table.bhasgd \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=12 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO||VERSION FROM BA_DZZC_CP_QY ba Where ba.cpno=ie_ck.cpno And ba.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//��������SO�б�
//	public CommonBean listOtherck(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE in (23,99) AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM NX_MX WHERE YWID='"+ywid+"')  And so_no Like '%"+modify_mark+"%' AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE ie_ck_table.OPER_FLAG='0' AND ORDER_TYPE in (23,99) AND MODIFY_MARK IN ('3') And ie_ck_table.so_no Like '%"+modify_mark+"%' \n");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//�ϼ�����SO�б�
//	public CommonBean listLJNX(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=21 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM LJNX_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�е��ϼ�����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=21 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�е��ϼ�����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=21 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//�ϼ��˻�SO�б�
//	public CommonBean listLJTH(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=14 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM LJTH_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=14 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=14 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	//�ϼ�����SO�б�
//	public CommonBean listLJHH(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=15 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM LJHH_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=15 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=15 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE CPNO NOT IN (SELECT LJNO FROM BA_DZZC_LJ_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	//�����ϼ�PO�����б�ֻ��ʾ��˰����B
//	  public CommonBean listJKLJ(String ywid, String modify_mark,String ywtype) throws Exception {
//		  StringBuffer bfsql = new StringBuffer();
//		  //�ҳ���Ӧ��PO�����ݣ�modify_mark��PO��:po_no,,'1' AS stip \n
//  
//			bfsql.append("/*����ָ��PO�ŵ���ϸ����*/ \n");		  		  
//			bfsql.append("SELECT IE_PO.*,'1' AS stip FROM IE_PO	WHERE REMAIN>0 AND WLFLAG='B' AND  PO_NO LIKE '%"+modify_mark+"%'\n");
//			bfsql.append(" AND NOT exists (SELECT id FROM "+ywtype+"_MX mx WHERE ie_po.po_no=mx.po_no and ie_po.ljno=mx.ljno and YWID='"+ywid+"')  \n");  
//			bfsql.append(" AND LJNO Is Not Null And LJNO In (Select LJNO From BA_DZZC_LJ_QY ) \n");
//			bfsql.append("UNION ALL \n");
//			bfsql.append("/*����ָ��PO�ŵ���ϸ����,û�б���*/ \n");		  		  
//			bfsql.append("SELECT IE_PO.* ,'0' AS stip FROM IE_PO	WHERE REMAIN>0 AND WLFLAG='B' AND  PO_NO LIKE '%"+modify_mark+"%'\n");
//			bfsql.append(" AND NOT exists (SELECT id FROM "+ywtype+"_MX mx WHERE ie_po.po_no=mx.po_no and ie_po.ljno=mx.ljno and YWID='"+ywid+"')  \n");  
//			bfsql.append(" AND LJNO Is Not Null And LJNO not In (Select LJNO From BA_DZZC_LJ_QY ) \n");
//
//		 // bfsql.append("ORDER BY PO_NO,LJNO");
//		
//		  String sql = bfsql.toString();
//		  DataBaseObject dbo = getDataBaseObject();
//		  CommonBean cb = dbo.getData(sql);
//		
//		  return cb;
//	  }
//	
//	//�豸PO�����б�
//	  public CommonBean listJKSB(String ywid, String modify_mark,String ywtype) throws Exception {
//		  StringBuffer bfsql = new StringBuffer();
//		  //�ҳ���Ӧ��PO�����ݣ�modify_mark��PO��:po_no
//		  
//		  bfsql.append("/*����ָ��PO�ŵ���ϸ����*/ \n");		  
//		  
//		  bfsql.append("SELECT * FROM IE_PO	WHERE REMAIN>0 AND  PO_NO LIKE '%"+modify_mark+"%'\n");
//		  bfsql.append(" AND PO_NO||LJNO NOT IN (SELECT PO_NO||LJNO FROM "+ywtype+"_MX WHERE YWID='"+ywid+"') ");  
//		
//		  bfsql.append("ORDER BY PO_NO,LJNO");
//		
//		  String sql = bfsql.toString();
//		  DataBaseObject dbo = getDataBaseObject();
//		  CommonBean cb = dbo.getData(sql);
//		
//		  return cb;
//	  }
//	
//	//�������ڣ�PO�����б�ֻ��ʾ�Ǳ�˰����N
//	  public CommonBean listOTHERJK(String ywid, String modify_mark,String ywtype) throws Exception {
//		  StringBuffer bfsql = new StringBuffer();
//		  //�ҳ���Ӧ��PO�����ݣ�modify_mark��PO��:po_no
//		  
//		  bfsql.append("/*����ָ��PO�ŵ���ϸ����*/ \n");		  
//		  
//		  bfsql.append("SELECT * FROM IE_PO	WHERE REMAIN>0 AND WLFLAG='N' AND  PO_NO LIKE '%"+modify_mark+"%'\n");
//		  bfsql.append(" AND LJNO Is Not Null  AND PO_NO||LJNO NOT IN (SELECT PO_NO||LJNO FROM "+ywtype+"_MX WHERE YWID='"+ywid+"') ");  
//		
//		  bfsql.append("ORDER BY PO_NO,LJNO");
//		
//		  String sql = bfsql.toString();
//		  DataBaseObject dbo = getDataBaseObject();
//		  CommonBean cb = dbo.getData(sql);
//		
//		  return cb;
//	  }
//	
//
//	//��Ʒ�˻�SO�б�
//	public CommonBean listCPTH(String ywid,String modify_mark) throws Exception {
//		StringBuffer bfsql = new StringBuffer();
//		bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=16 AND SO_NO IN ( \n");
//		bfsql.append("SELECT SO_NO FROM CPTH_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����,��po��*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=16 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  PO_NO LIKE '%"+modify_mark+"%' AND PO_NO IN (SELECT DISTINCT PO_NO FROM CPCK_MX Union All SELECT DISTINCT PO_NO FROM bmc.CPCK_MX) AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ���ţ���po��*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'2' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=16 AND NOT EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  PO_NO LIKE '%"+modify_mark+"%' AND PO_NO not IN (SELECT DISTINCT PO_NO FROM CPCK_MX Union All SELECT DISTINCT PO_NO FROM bmc.CPCK_MX) AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("UNION ALL \n");
//		bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip \n");
//		bfsql.append("FROM IE_CK_TABLE \n");
//		bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=16 AND EXISTS ( \n");
//		bfsql.append("SELECT ID FROM IE_CK WHERE Not Exists (SELECT CPNO FROM BA_DZZC_CP_QY b Where b.cpno=ie_ck.cpno And b.version=ie_ck.version) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		bfsql.append(") \n");
//		bfsql.append(" AND  PO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		bfsql.append("ORDER BY adddate desc,so_no");
//		
//		
//	
//		
//		String sql = bfsql.toString();
//		DataBaseObject dbo = getDataBaseObject();
//		CommonBean cb = dbo.getData(sql);
//		
//		return cb;
//	}
//	
//	
//	
//	
//	//�ϼ����ݽ���
//	public boolean exchgLJ(String ywid, String[] ObjectIDs) throws Exception {
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[2];
//		sql[0] = "INSERT INTO BA_DZZC_LJ (ID,YWID,LJNO,UNIT,MODIFY_MARK,ADDDATE,E_SPNAME,GUIGE,EXCHGID) "
//				+"SELECT SYS_GUID(),'"+ywid+"',ljno,unit,modify_mark,sysdate,spname,guige,id "
//				+"FROM IE_LJ "
//				+"WHERE ID IN ("+subWhereIDs+")";
//		
//		//2010-06-10 sunxiaofeng  ���ӡ��ϼ�������item�����ظ�ҵ��У�鹦��
//		//sql[1] = "UPDATE IE_LJ SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		sql[1] = "UPDATE IE_LJ SET OPER_FLAG='1' WHERE ljno in (Select ljno from ie_lj WHERE ID IN ("+subWhereIDs+"))";
//		
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ebook.dzzc.lj.bean.DzzcLJBean dzzclj = new com.paink.module.ebook.dzzc.lj.bean.DzzcLJBean();
//			dzzclj.setConnection(getConnection());
//			bFlag = bFlag && dzzclj.gbLJ(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//	//��Ʒ���ݽ���
//	public boolean exchgCP(String ywid, String[] ObjectIDs) throws Exception {
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[2];
//		sql[0] = "INSERT INTO BA_DZZC_CP (ID,YWID,CPNO,VERSION,UNIT,MODIFY_MARK,ADDDATE,SPNAME,E_SPNAME,GUIGE,GUIGE_OLD,EXCHGID,ROA_FLAG) "
//				+"SELECT SYS_GUID(),'"+ywid+"',cpno,VERSION,unit,modify_mark,sysdate,spname,spname,UPPER(guige),UPPER(guige),id,PURCHASEFLAG  "
//				+"FROM IE_CP "
//				+"WHERE ID IN ("+subWhereIDs+")";
//		
//		//2010-06-10 sunxiaofeng  ���ӡ���Ʒ������item�����ظ�ҵ��У�鹦��
//		//sql[1] = "UPDATE IE_CP SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		sql[1] = "UPDATE IE_CP SET OPER_FLAG='1' WHERE cpNo || version in (Select cpNo || version from ie_cp WHERE ID IN ("+subWhereIDs+"))";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ebook.dzzc.cp.bean.DzzcCPBean dzzccp = new com.paink.module.ebook.dzzc.cp.bean.DzzcCPBean();
//			dzzccp.setConnection(getConnection());
//			bFlag = bFlag && dzzccp.gbCP(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//	//BOM���ݽ���
//	public boolean exchgBOM(String ywid, String[] ObjectIDs) throws Exception {
//		String subWhere = "0>1";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhere = subWhere + " OR (CPNO='"+ObjectIDs[i].substring(0,ObjectIDs[i].lastIndexOf("-"))+"' AND VERSION='"+ObjectIDs[i].substring(ObjectIDs[i].lastIndexOf("-")+1)+"')";
//		}
//		String[] sql = new String[2];
//		sql[0] = "INSERT INTO BA_DZZC_BOM (ID,YWID,CPNO,VERSION,LJNO,DH,MODIFY_MARK,ADDDATE,EXCHGID) "
//				+"SELECT SYS_GUID(),'"+ywid+"',cpno,version,ljno,dh,'3',sysdate,id "
//				+"FROM IE_BOM "
//				+"WHERE LJNO IN (SELECT LJNO FROM BA_DZZC_LJ_QY UNION SELECT CPNO FROM BA_DZZC_CP_QY) AND (" + subWhere + ")";
//		sql[1] = "UPDATE IE_BOM SET OPER_FLAG='1' WHERE " + subWhere;
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = dbo.execute(sql);
//		
//		return bFlag;
//	}
//	
//	//��Ʒ����SO���ݽ���
//	public boolean exchgCPCK(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		//�Ȼ����������(���뱣��֮ǰ��)
//		bFlag = bFlag && ckywbean.revertData(ywid,"cpck");
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM CPCK_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO CPCK_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,CP_QY.UNIT,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO And CP_QY.VERSION=IE_CK.VERSION \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+") And ie_ck_table.modify_mark<>2";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		 
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ckyw.cpck.bean.CpckBean cpck = new com.paink.module.ckyw.cpck.bean.CpckBean();
//			cpck.setConnection(getConnection());
//			bFlag = bFlag && cpck.cpGb(ywid);
//			
//			//���¿���(�����ڼ�����������֮����)
//			bFlag = bFlag && ckywbean.udData(ywid,"cpck");
//		}
//		
//		return bFlag;
//	}
//	
//	
//	
//	
//	//��������SO���ݽ���
//   public boolean exchgReck(String ywid, String[] ObjectIDs) throws Exception {
//	   boolean bFlag = true;
//		
//	   String subWhereIDs = "";
//	   for (int i=0; i<ObjectIDs.length; i++) {
//		   subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//	   }
//	   String[] sql = new String[3];
//	   sql[0] = "DELETE FROM REYW_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//	   sql[1] = "INSERT INTO REYW_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE) \n"
//			   +"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,IE_CK.SLDW,IE_CK.CPNO,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE \n"
//			   +"FROM IE_CK_TABLE \n"
//			   +"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//			   +"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_CK.CPNO \n"
//			   +" LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//			   +" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//			   +"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//
//	  
//	   sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//	   DataBaseObject dbo = getDataBaseObject();
//	   bFlag = bFlag && dbo.execute(sql);
//		
//	   if (bFlag) {
//		   com.paink.module.ckyw.reck.bean.ReckBean ck = new com.paink.module.ckyw.reck.bean.ReckBean();
//		   ck.setConnection(getConnection());
//		   bFlag = bFlag && ck.LjGb(ywid);
//	
//	   }
//		
//	   return bFlag;
//   }
//	
//	//��תת��SO���ݽ���
//	public boolean exchgSJGZC(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		//�Ȼ����������(���뱣��֮ǰ��)
//		bFlag = bFlag && ckywbean.revertData(ywid,"sjgzc");
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM SJGZC_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO SJGZC_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,CP_QY.UNIT,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO and CP_QY.VERSION=IE_CK.VERSION \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ckyw.sjgzc.bean.SjgzcBean sjgzc = new com.paink.module.ckyw.sjgzc.bean.SjgzcBean();
//			sjgzc.setConnection(getConnection());
//			bFlag = bFlag && sjgzc.cpGb(ywid);
//			
//			//���¿���(�����ڼ�����������֮����)
//			bFlag = bFlag && ckywbean.udData(ywid,"sjgzc");
//		}
//		
//		return bFlag;
//	}
//	
//	//��Ʒ����SO���ݽ���
//	public boolean exchgCPHH(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		//�Ȼ����������(���뱣��֮ǰ��)
//		bFlag = bFlag && ckywbean.revertData(ywid,"cphh");
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM CPHH_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO CPHH_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,CP_QY.UNIT,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO and CP_QY.VERSION=IE_CK.VERSION \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ckyw.cphh.bean.CphhBean cphh = new com.paink.module.ckyw.cphh.bean.CphhBean();
//			cphh.setConnection(getConnection());
//			bFlag = bFlag && cphh.cpGb(ywid);
//			
//			//���¿���(�����ڼ�����������֮����)
//			bFlag = bFlag && ckywbean.udData(ywid,"cphh");
//		}
//		
//		return bFlag;
//	}
//	
//	//��Ʒ�˻�SO���ݽ���
//	  public boolean exchgCPTH(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		//�ͳ����෴���˻��ӣ�Ӧ���ȼ����
//		bFlag = bFlag && ckywbean.udData_cpth(ywid,"-");
////	   //�ȿ���
////	  bFlag = bFlag && ckywbean.udData(ywid,"cpth");
////	  bFlag = bFlag && ckywbean.udKRHTraceCpth(ywid);
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM CPTH_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO CPTH_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,CP_QY.UNIT,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO and CP_QY.VERSION=IE_CK.VERSION \n"
//				+" LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.jkyw.cpth.bean.CpthBean cpth = new com.paink.module.jkyw.cpth.bean.CpthBean();
//			cpth.setConnection(getConnection());
//			bFlag = bFlag && cpth.cpGb(ywid);
//			  
////		  //�Ȼ����������(���뱣��֮ǰ��)
//		  bFlag = bFlag && ckywbean.udData_cpth(ywid,"+");
////		  bFlag = bFlag && ckywbean.revertData(ywid,"cpth");
////		  bFlag = bFlag && ckywbean.revertKRHTrace(ywid);
//			
//		}
//		
//		return bFlag;
//	  }
//	
//	//����SO���ݽ���
//	public boolean exchgNX(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		//�Ȼ����������(���뱣��֮ǰ��)
//		bFlag = bFlag && ckywbean.revertData(ywid,"nx");
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM NX_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO NX_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,CP_QY.UNIT,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO and CP_QY.VERSION=IE_CK.VERSION \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			com.paink.module.ckyw.nx.bean.NxBean nx = new com.paink.module.ckyw.nx.bean.NxBean();
//			nx.setConnection(getConnection());
//			bFlag = bFlag && nx.cpGb(ywid);
//			
//			//���¿���(�����ڼ�����������֮����)
//			bFlag = bFlag && ckywbean.udData(ywid,"nx");
//		}
//		
//		return bFlag;
//	}
//	
//	//��������SO���ݽ���
//	public boolean exchgOtherck(String ywid, String[] ObjectIDs) throws Exception {
//		boolean bFlag = true;
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//
//		com.paink.module.ckyw.otherck.bean.OtherckBean otherck = new com.paink.module.ckyw.otherck.bean.OtherckBean();
//		otherck.setConnection(getConnection());
//
//		//��ɾ��δ��������ϸ����
//		otherck.delNoBA(ywid);
//		
//		//�Ȼ����������(���뱣��֮ǰ��)
//		bFlag = bFlag && ckywbean.revertData(ywid,"otherck");
//		
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[4];
//		sql[0] = "DELETE FROM OTHERCK_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO OTHERCK_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,CPID,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,CP_QY.CPID,CP_QY.SPID,CP_QY.SPNAME,CP_QY.GUIGE,IE_CK.SLDW,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"INNER JOIN BA_DZZC_CP_QY CP_QY ON CP_QY.CPNO=IE_CK.CPNO and CP_QY.VERSION=IE_CK.VERSION \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "INSERT INTO OTHERCK_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,SPID,SPNAME,GUIGE,UNITS,CPNO,DHVER,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,CPFLAG,ADDDATE,job_order) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,IE_CK.SLDW,IE_CK.CPNO,IE_CK.VERSION,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,0,SYSDATE,ie_ck.job_order \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"left JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_CK.CPNO \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+") And Not Exists (Select id From ba_dzzc_cp_qy bacp Where bacp.cpno = ie_ck.cpno And bacp.version=ie_ck.version)";
//		sql[3] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		bFlag = bFlag && dbo.execute(sql);
//		
//		if (bFlag) {
//			bFlag = bFlag && otherck.cpGb(ywid);
//			
//			//���¿���(�����ڼ�����������֮����)
//			bFlag = bFlag && ckywbean.udData(ywid,"otherck");
//			
//			//��ɾ��δ��������ϸ����
//			otherck.addNoBA(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//	//�ϼ�����SO���ݽ���
//	public boolean exchgLJNX(String ywid, String[] ObjectIDs) throws Exception {
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = true;
//		
//		//��������
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		bFlag = bFlag && ckywbean.revertData(ywid,"ljnx");
//		bFlag = bFlag && ckywbean.revertJKTrace(ywid,"ljnx");
//		
//		String[] sql = null;
//		for (int i=0; i<ObjectIDs.length; i++) {
//			sql = new String[3];
//			sql[0] = "DELETE FROM LJNX_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ('"+ObjectIDs[i]+"'))";
//			sql[1] = "INSERT INTO LJNX_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,SPINT,spint1,ADDDATE) \n"
//					+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,LJ_QY.UNIT,IE_CK.CPNO,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.SPINT,decode(dbtln.quantity,Null,(decode(roa_dbtln.quantity,null,0)),dbtln.quantity)-decode(sonx.spint1,Null,0,sonx.spint1),SYSDATE \n"
//					+"FROM IE_CK_TABLE \n"
//					+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//					+"INNER JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_CK.CPNO \n"
//					+"Left Join (Select ljno,Organization,Sum(spint1) As spint1 From ljnx_mx Where ywid='"+ywid+"' And srctype=0 Group By ljno,Organization) sonx On sonx.ljno=ie_ck.cpno And sonx.Organization=ie_ck_table.Organization \n"
//					+"Left Join database_tl dbtln On dbtln.ljno=ie_ck.cpno And dbtln.Organization=ie_ck_table.organization And dbtln.bsflag='N' And dbtln.bmc_flag='DPY' \n"
//					+"Left Join database_tl roa_dbtln On roa_dbtln.ljno=ie_ck.cpno And roa_dbtln.Organization=ie_ck_table.organization And roa_dbtln.bsflag='N' And roa_dbtln.bmc_flag='ROA' \n"
//					+"WHERE IE_CK_TABLE.ID IN ('"+ObjectIDs[i]+"')";
//			sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ('"+ObjectIDs[i]+"')";
//			bFlag = bFlag && dbo.execute(sql);
//		
//			//���ݷǱ�˰�ϵ���������һ�º������
//			sql = new String[3];
//			sql[0] = "update ljnx_mx set spint1=0 where ywid='"+ywid+"' and srctype=0 and spint1<0 and so_no=(select so_no from ie_ck_table where id ='"+ObjectIDs[i]+"')";
//			sql[1] = "update ljnx_mx set spint1=spint,spint=0 where ywid='"+ywid+"' and srctype=0 and spint1>=spint and so_no=(select so_no from ie_ck_table where id ='"+ObjectIDs[i]+"')";
//			sql[2] = "update ljnx_mx set spint=spint-spint1 where ywid='"+ywid+"' and srctype=0 and spint1<spint and so_no=(select so_no from ie_ck_table where id ='"+ObjectIDs[i]+"')";
//			bFlag = bFlag && dbo.execute(sql);
//		}
//			
//		//��������
//		bFlag = bFlag && ckywbean.udData(ywid,"ljnx");
//		bFlag = bFlag && ckywbean.udJKTrace(ywid,"ljnx");
//		
//		if (bFlag) {
//			com.paink.module.ckyw.ljnx.bean.LjnxBean ljnx = new com.paink.module.ckyw.ljnx.bean.LjnxBean();
//			ljnx.setConnection(getConnection());
//			bFlag = bFlag && ljnx.LjGb(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//	//�ϼ��˻�SO���ݽ���
//	public boolean exchgLJTH(String ywid, String[] ObjectIDs) throws Exception {
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM LJTH_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO LJTH_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,LJ_QY.UNIT,IE_CK.CPNO,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_CK.CPNO \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = true;
//		
//		//��������
//		CkywBean ckywbean = new CkywBean();
//		ckywbean.setConnection(getConnection());
//		bFlag = bFlag && ckywbean.revertData(ywid,"ljth");
//		
//		bFlag = dbo.execute(sql);
//			
//		//��������
//		bFlag = bFlag && ckywbean.udData(ywid,"ljth");
//		
//		if (bFlag) {
//			com.paink.module.ckyw.ljth.bean.LjthBean ljth = new com.paink.module.ckyw.ljth.bean.LjthBean();
//			ljth.setConnection(getConnection());
//			bFlag = bFlag && ljth.LjGb(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//	//�����ϼ�PO���ݽ���
//	  public boolean exchgJKLJ(String ywid, String[] ObjectIDs) throws Exception {
//		  String subWhereIDs = "";
//		  for (int i=0; i<ObjectIDs.length; i++) {
//			  subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		  }
//		  String[] sql = new String[2];
//		  sql[0] = "INSERT INTO JKLJ_MX (YWID,ID,EXCHGID,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,PO_NO,BUYER,SUPPLIER,ORGANIZATION,ADDDATE) \n"
//				  +"SELECT '"+ywid+"',SYS_GUID(),IE_PO.ID,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,LJ_QY.UNIT,IE_PO.LJNO,IE_PO.COUNTRY,IE_PO.CURR,IE_PO.PRICE/IE_PO.REMAIN,IE_PO.PRICE,IE_PO.PRICE*Y.RATE/U.RATE,IE_PO.REMAIN,IE_PO.PO_NO,IE_PO.BUYER,IE_PO.SUPPLIER,IE_PO.SHIPPINGTO,SYSDATE \n"
//				  +"FROM IE_PO \n"
//				  +" LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_PO.LJNO \n"
//				  +" LEFT JOIN CL_CURR Y ON Y.ID=IE_PO.CURR \n"
//				  +" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				  +"WHERE IE_PO.ID IN ("+subWhereIDs+") \n";
//		 
//		//��remain����������Ϊ0		
//		 sql[1]="UPDATE IE_PO SET REMAIN=0,price=0 WHERE ID IN ("+subWhereIDs+") ";
//		  DataBaseObject dbo = getDataBaseObject();
//		  boolean bFlag = dbo.execute(sql);
//		
//		  if (bFlag) {
//			  com.paink.module.jkyw.jklj.bean.JkljBean jklj = new com.paink.module.jkyw.jklj.bean.JkljBean();
//			  jklj.setConnection(getConnection());
//			  bFlag = bFlag && jklj.LjGb(ywid);
//		  }
//		
//		  return bFlag;
//	  }
//
//	  //��תת��PO���ݽ���
//		public boolean exchgSJGZR(String ywid, String[] ObjectIDs) throws Exception {
//			String subWhereIDs = "";
//			for (int i=0; i<ObjectIDs.length; i++) {
//				subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//			}
//			String[] sql = new String[2];
//			sql[0] = "INSERT INTO SJGZR_MX (YWID,ID,EXCHGID,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,PO_NO,BUYER,SUPPLIER,ORGANIZATION,ADDDATE) \n"
//					+"SELECT '"+ywid+"',SYS_GUID(),IE_PO.ID,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,LJ_QY.UNIT,IE_PO.LJNO,IE_PO.COUNTRY,IE_PO.CURR,IE_PO.PRICE/IE_PO.REMAIN,IE_PO.PRICE,IE_PO.PRICE*Y.RATE/U.RATE,IE_PO.REMAIN,IE_PO.PO_NO,IE_PO.BUYER,IE_PO.SUPPLIER,IE_PO.SHIPPINGTO,SYSDATE \n"
//					+"FROM IE_PO \n"
//					+"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_PO.LJNO \n"
//					+" LEFT JOIN CL_CURR Y ON Y.ID=IE_PO.CURR \n"
//					+" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//					+"WHERE IE_PO.ID IN ("+subWhereIDs+") \n";
//		 
//		  //��remain����������Ϊ0		
//		   sql[1]="UPDATE IE_PO SET REMAIN=0 WHERE ID IN ("+subWhereIDs+") ";
//			DataBaseObject dbo = getDataBaseObject();
//			boolean bFlag = dbo.execute(sql);
//		
//			if (bFlag) {
//				com.paink.module.jkyw.sjgzr.bean.SjgzrBean jk = new com.paink.module.jkyw.sjgzr.bean.SjgzrBean();
//				jk.setConnection(getConnection());
//				bFlag = bFlag && jk.LjGb(ywid);
//			}
//		
//			return bFlag;
//		}
//
//		
//	//�ϼ�����SO���ݽ���
//	public boolean exchgLJHH(String ywid, String[] ObjectIDs) throws Exception {
//		String subWhereIDs = "";
//		for (int i=0; i<ObjectIDs.length; i++) {
//			subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		}
//		String[] sql = new String[3];
//		sql[0] = "DELETE FROM LJHH_MX WHERE ywid='"+ywid+"' and SO_NO IN (SELECT SO_NO FROM IE_CK_TABLE WHERE ID IN ("+subWhereIDs+"))";
//		sql[1] = "INSERT INTO LJHH_MX (YWID,ID,EXCHGID,SO_NO,ORGANIZATION,PO_NO,LJID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,ADDDATE) \n"
//				+"SELECT '"+ywid+"',SYS_GUID(),IE_CK_TABLE.ID,IE_CK_TABLE.SO_NO,IE_CK_TABLE.ORGANIZATION,PO_NO,LJ_QY.LJID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,LJ_QY.UNIT,IE_CK.CPNO,IE_CK_TABLE.COUNTRY,IE_CK_TABLE.CURR,IE_CK.DJ,IE_CK.ZJ,IE_CK.ZJ*Y.RATE/U.RATE,IE_CK.SPINT,SYSDATE \n"
//				+"FROM IE_CK_TABLE \n"
//				+"INNER JOIN IE_CK ON IE_CK.OBJECTID=IE_CK_TABLE.ID \n"
//				+"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_CK.CPNO \n"
//				+"LEFT JOIN CL_CURR Y ON Y.ID=IE_CK_TABLE.CURR \n"
//				+"LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				+"WHERE IE_CK_TABLE.ID IN ("+subWhereIDs+")";
//		sql[2] = "UPDATE IE_CK_TABLE SET OPER_FLAG='1' WHERE ID IN ("+subWhereIDs+")";
//		
//		DataBaseObject dbo = getDataBaseObject();
//		boolean bFlag = dbo.execute(sql);
//		
//		if (bFlag) {
//			
//			com.paink.module.jkyw.ljhh.bean.LjhhBean ljhh = new com.paink.module.jkyw.ljhh.bean.LjhhBean();
//			ljhh.setConnection(getConnection());
//			bFlag = bFlag && ljhh.LjGb(ywid);
//		}
//		
//		return bFlag;
//	}
//	
//		  
//	 //��������PO���ݽ���
//	  public boolean exchgOTHERJK(String ywid, String[] ObjectIDs) throws Exception {
//		  String subWhereIDs = "";
//		  for (int i=0; i<ObjectIDs.length; i++) {
//			  subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		  }
//		  String[] sql = new String[2];
//		  sql[0] = "INSERT INTO OTHERJK_MX (YWID,ID,EXCHGID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,PO_NO,BUYER,SUPPLIER,ORGANIZATION,ADDDATE) \n"
//				  +"SELECT '"+ywid+"',SYS_GUID(),IE_PO.ID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,IE_PO.UNIT,IE_PO.LJNO,IE_PO.COUNTRY,IE_PO.CURR,IE_PO.PRICE/IE_PO.REMAIN,IE_PO.PRICE,IE_PO.PRICE*Y.RATE/U.RATE,IE_PO.REMAIN,IE_PO.PO_NO,IE_PO.BUYER,IE_PO.SUPPLIER,IE_PO.SHIPPINGTO,SYSDATE \n"
//				  +"FROM IE_PO \n"
//				  +"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_PO.LJNO \n"
//				+" LEFT JOIN CL_CURR Y ON Y.ID=IE_PO.CURR \n"
//				+" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				  +"WHERE IE_PO.ID IN ("+subWhereIDs+") \n";
//		 
//		//��remain����������Ϊ0		
//		 sql[1]="UPDATE IE_PO SET REMAIN=0 WHERE ID IN ("+subWhereIDs+") ";
//		  DataBaseObject dbo = getDataBaseObject();
//		  boolean bFlag = dbo.execute(sql);
//		
//		  if (bFlag) {
//			  com.paink.module.jkyw.otherjk.bean.OtherjkBean jk = new com.paink.module.jkyw.otherjk.bean.OtherjkBean();
//			  jk.setConnection(getConnection());
//			  bFlag = bFlag && jk.LjGb(ywid);
//		  }
//		
//		  return bFlag;
//	  }	
//	
//	//�����豸PO���ݽ���
//		 public boolean exchgJKSB(String ywid, String[] ObjectIDs) throws Exception {
//			 String subWhereIDs = "";
//			 for (int i=0; i<ObjectIDs.length; i++) {
//				 subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//			 }
//			 String[] sql = new String[2];
//			 sql[0] = "INSERT INTO JKSB_MX (YWID,ID,EXCHGID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,PO_NO,BUYER,SUPPLIER,ORGANIZATION,ADDDATE) \n"
//					 +"SELECT '"+ywid+"',SYS_GUID(),IE_PO.ID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,IE_PO.UNIT,IE_PO.LJNO,IE_PO.COUNTRY,IE_PO.CURR,IE_PO.PRICE/IE_PO.REMAIN,IE_PO.PRICE,IE_PO.PRICE*Y.RATE/U.RATE,IE_PO.REMAIN,IE_PO.PO_NO,IE_PO.BUYER,IE_PO.SUPPLIER,IE_PO.SHIPPINGTO,SYSDATE \n"
//					 +"FROM IE_PO \n"
//					 +"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_PO.LJNO \n"
//						+" LEFT JOIN CL_CURR Y ON Y.ID=IE_PO.CURR \n"
//						+" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//					 +"WHERE IE_PO.ID IN ("+subWhereIDs+") \n";
//		 
//		   //��remain����������Ϊ0		
//			sql[1]="UPDATE IE_PO SET REMAIN=0 WHERE ID IN ("+subWhereIDs+") ";
//			 DataBaseObject dbo = getDataBaseObject();
//			 boolean bFlag = dbo.execute(sql);
//		
//			 if (bFlag) {
//				 com.paink.module.jkyw.jksb.bean.JksbBean jk = new com.paink.module.jkyw.jksb.bean.JksbBean();
//				 jk.setConnection(getConnection());
//				 bFlag = bFlag && jk.LjGb(ywid);
//			 }
//		
//			 return bFlag;
//		 }	
//		  
//	//��������PO���ݽ���
//	 public boolean exchgREJK(String ywid, String[] ObjectIDs) throws Exception {
//		 String subWhereIDs = "";
//		 for (int i=0; i<ObjectIDs.length; i++) {
//			 subWhereIDs = subWhereIDs + (subWhereIDs.equals("")?"":",") + "'"+ObjectIDs[i]+"'";
//		 }
//		 String[] sql = new String[2];
//		 sql[0] = "INSERT INTO REYW_MX (YWID,ID,EXCHGID,SPID,SPNAME,GUIGE,UNITS,LJNO,SGOV,BZSNAME,DJ,ZJ,USDZJ,SPINT,PO_NO,BUYER,SUPPLIER,ORGANIZATION,ADDDATE) \n"
//				 +"SELECT '"+ywid+"',SYS_GUID(),IE_PO.ID,LJ_QY.SPID,LJ_QY.SPNAME,LJ_QY.GUIGE,IE_PO.UNIT,IE_PO.LJNO,IE_PO.COUNTRY,IE_PO.CURR,IE_PO.PRICE/IE_PO.REMAIN,IE_PO.PRICE,IE_PO.PRICE*Y.RATE/U.RATE,IE_PO.REMAIN,IE_PO.PO_NO,IE_PO.BUYER,IE_PO.SUPPLIER,IE_PO.SHIPPINGTO,SYSDATE \n"
//				 +"FROM IE_PO \n"
//				+"LEFT JOIN BA_DZZC_LJ_QY LJ_QY ON LJ_QY.LJNO=IE_PO.LJNO \n"
//				+" LEFT JOIN CL_CURR Y ON Y.ID=IE_PO.CURR \n"
//				+" LEFT JOIN CL_CURR U ON U.ID='502' \n"
//				 +"WHERE IE_PO.ID IN ("+subWhereIDs+") \n";
//		 
//	   //��remain����������Ϊ0		
//		sql[1]="UPDATE IE_PO SET REMAIN=0 WHERE ID IN ("+subWhereIDs+") ";
//		 DataBaseObject dbo = getDataBaseObject();
//		 boolean bFlag = dbo.execute(sql);
//		
//		 if (bFlag) {
//			 com.paink.module.jkyw.rejk.bean.RejkBean jk = new com.paink.module.jkyw.rejk.bean.RejkBean();
//			 jk.setConnection(getConnection());
//			 bFlag = bFlag && jk.LjGb(ywid);
//		 }
//		
//		 return bFlag;
//	 }
//	
//	  //��������SO�б�
//	  public CommonBean listReck(String ywid ,String modify_mark) throws Exception {
//		  StringBuffer bfsql = new StringBuffer();
//		  bfsql.append("/*ɾ�����޸ĵ�SO����Ҫ�ڵ�ǰҵ���´���*/ \n");
//		  bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		  bfsql.append("FROM IE_CK_TABLE \n");
//		  bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		  bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=23 AND SO_NO IN ( \n");
//		  bfsql.append("SELECT SO_NO FROM NX_MX WHERE YWID='"+ywid+"') AND MODIFY_MARK IN ('1','2') \n");
//		  bfsql.append("UNION \n");
//		  bfsql.append("/*��������ݲ����ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		  bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'1' AS stip \n");
//		  bfsql.append("FROM IE_CK_TABLE \n");
//		  bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		  bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=23 AND NOT EXISTS ( \n");
//		  bfsql.append("SELECT ID FROM IE_CK WHERE CPNO||VERSION NOT IN (SELECT CPNO||VERSION FROM BA_DZZC_BOM_QY) AND CPNO NOT IN (SELECT LJNO FROM BA_DZZC_BOM_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		  bfsql.append(") \n");
//		  bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		  bfsql.append("UNION \n");
//		  bfsql.append("/*��������ݴ��ڳ�Ʒ��ҵ����û�еĳ�Ʒ����*/ \n");
//		  bfsql.append("SELECT IE_CK_TABLE.id,IE_CK_TABLE.so_no,IE_CK_TABLE.po_no,IE_CK_TABLE.modify_mark,CL_MODIFY_MARK.sname as modifymarkname,to_char(IE_CK_TABLE.adddate,'yyyy-mm-dd') AS adddate,'0' AS stip \n");
//		  bfsql.append("FROM IE_CK_TABLE \n");
//		  bfsql.append("LEFT JOIN CL_MODIFY_MARK ON IE_CK_TABLE.MODIFY_MARK=CL_MODIFY_MARK.ID \n");
//		  bfsql.append("WHERE OPER_FLAG='0' AND ORDER_TYPE=23 AND EXISTS ( \n");
//		  bfsql.append("SELECT ID FROM IE_CK WHERE CPNO||VERSION NOT IN (SELECT CPNO||VERSION FROM BA_DZZC_BOM_QY) AND CPNO NOT IN (SELECT LJNO FROM BA_DZZC_BOM_QY) AND IE_CK.OBJECTID=IE_CK_TABLE.ID \n");
//		  bfsql.append(") \n");
//		  bfsql.append(" AND  SO_NO LIKE '%"+modify_mark+"%' AND MODIFY_MARK IN ('3') \n");
//		  bfsql.append("ORDER BY adddate desc,so_no");
//		
//		  String sql = bfsql.toString();
//		  DataBaseObject dbo = getDataBaseObject();
//		  CommonBean cb = dbo.getData(sql);
//		
//		  return cb;
//	  }
//	
//	//�鿴��Ʒ��ӦBOM����
//	  public CommonBean listBomData(String strCpno,String strVersion)  {
//	  	try{
//			///2010-05-24   Sun,Xiaofeng ��ʾ�ϼ�Ӣ��������
//	  		/*
//	  		String sql = "SELECT IE_BOM.LJNO LJNO,LJ.SPNAME SPNAME,lj.e_spname As e_spname,LJ.SPID SPID,DH  \n"
//								  +"FROM IE_BOM \n"
//								  +"LEFT JOIN BA_DZZC_LJ_QY LJ ON LJ.LJNO=IE_BOM.LJNO" 
//								  +	" WHERE CPNO='"+strCpno+"' AND VERSION= '"+strVersion+"'"
//								  +"ORDER BY LJNO";
//			*/
//	  		
//	  		String sql = "SELECT IE_BOM.LJNO LJNO,LJ.SPNAME SPNAME,ie_lj.spname As e_spname,LJ.SPID SPID,DH  \n"
//				  +"FROM IE_BOM \n"
//				  +"LEFT JOIN BA_DZZC_LJ_QY LJ ON LJ.LJNO=IE_BOM.LJNO" 
//				  + " Left Join bmc_ie_lj_desc ie_lj On ie_lj.LJNO = IE_BOM.LJNO"
//				  +	" WHERE CPNO='"+strCpno+"' AND VERSION= '"+strVersion+"'"
//				  +"ORDER BY LJNO";
//	  		
//				  DataBaseObject dbo = getDataBaseObject();
//				  CommonBean cb = dbo.getData(sql);
//			return cb;
//	  	}catch (Exception e) {
//		rollback();
//		Log.error(this, "ȡ������Ϣ��������" + e.getMessage());
//		getErrMsgBean().addCommonMessage("ȡ������Ϣ��������" + e.getMessage());
//		e.printStackTrace();
//		return null;
//	}
//		  
//		
//		  
//	  }
//	
//}
