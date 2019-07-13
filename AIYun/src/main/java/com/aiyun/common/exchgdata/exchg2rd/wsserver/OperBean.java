//package com.aiyun.common.exchgdata.exchg2rd.wsserver;
//
//import java.lang.reflect.Method;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import com.aiyun.common.bo.BusinessObject;
//import com.aiyun.common.bo.DataBaseObject;
//import com.aiyun.common.common.Function;
//import com.aiyun.common.util.Log;
//import com.aiyun.common.util.Oid;
//import com.aiyun.common.vo.CommonBean;
//import com.paink.module.ckyw.cpck.bean.CpckBean;
//import com.paink.sys.yw.bean.YWBean;
//
///**
// * ������3��֮������ݽ���ҵ��
// */
//public class OperBean extends BusinessObject {
//	private static String pubQyid = "2007021700";
//	//private static String pubQyid = "1234567890";
//	private static String authSN = "CF48257B-6F1A-4EB6-8837-C81C4654A5A2";
//	
//	HashMap hmYWEnum = null;
//	
//	public OperBean() throws Exception {
//		hmYWEnum = new HashMap();
//		hmYWEnum.put("7","JKLJ");	//�����ϼ�(gb/mx)
//		hmYWEnum.put("8","CPCK");	//��Ʒ����(mx/lj_mx/lj_gb/gb/cp_lj_gb)
//		hmYWEnum.put("10","SJGZR");	//��תת��(gb/mx)
//		hmYWEnum.put("11","SJGZC");	//��תת��(mx/lj_mx/lj_gb/gb/cp_lj_gb)
//		hmYWEnum.put("13","FQXH");	//��������(gb/mx)
//		hmYWEnum.put("14","LJTH");	//�ϼ��˻�(gb/mx)
//		hmYWEnum.put("15","LJHH");	//�ϼ�����(gb/mx)
//		hmYWEnum.put("16","CPTH");	//��Ʒ�˻�(mx/lj_mx/lj_gb/gb/cp_lj_gb)
//		hmYWEnum.put("17","CPHH");	//��Ʒ����(mx/lj_mx/lj_gb/gb/cp_lj_gb)
//		hmYWEnum.put("21","LJNX");	//�ϼ�����(gb/mx)
//		hmYWEnum.put("26","OTHERNX");//��������(gb/mx)
//		hmYWEnum.put("12","CPNX");	//��Ʒ����(�����ϼ�����)
//		hmYWEnum.put("18","BGDDEL");//ɾ�����ص�
//		hmYWEnum.put("19","HX");	//����
//	}
//	
//	public String getData(String ywid) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		StringBuffer sbdata = new StringBuffer();
//		
//		//�鿴ҵ������
//		CommonBean cbYW = dbo.getData("SELECT ID,TypeID,decode(status,5,'fwait','wait') as status FROM YWLIST WHERE ID = '"+ywid+"'");
//		String ywtype = cbYW.getValue("typeid");
//		
//		//����һЩ��������
//		String tpre = (String)hmYWEnum.get(ywtype);		//��ǰ׺,ͬʱ��Ϊxml�ĸ��ڵ�����(��Ϊ���������)
//		if (tpre==null) return "";
//		
//		//��ʼ��֯ҵ�����ݵ�xml
//		//sbdata.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>\n");
//		sbdata.append("<ROOT DATATYPE='"+tpre+"'>\n");
//		sbdata.append("<qyallid>"+getPubQyid()+"</qyallid>\n");
//		sbdata.append("<id>"+ywid+"</id>\n");
//		sbdata.append("<typeid>"+ywtype+"</typeid>\n");
//		sbdata.append("<status>"+cbYW.getValue("status")+"</status>\n");
//		if (ywtype.equals("18")) {			//ɾ�����ص�
//			CommonBean cbDelYW = dbo.getData("SELECT DELYWID FROM BGD_DEL WHERE YWID='"+ywid+"'");
//			sbdata.append("<delywid>"+cbDelYW.getValue("delywid")+"</delywid>\n");
//		}
//		else if (ywtype.equals("19")) {	//����
//			CommonBean cbZCH = dbo.getData("SELECT HXZCH FROM HX_YW WHERE YWID = '"+ywid+"'");
//			sbdata.append("<hxzch>"+cbZCH.getValue("hxzch")+"</hxzch>\n");
//			sbdata.append(getHXYW(ywtype,ywid));
//		}
//		else {
//			CommonBean cbzcmx = dbo.getData("SELECT BGD_ZC_MX.*,CL_TRANSAC.SNAME AS TRANS_MODE_NAME FROM BGD_ZC_MX LEFT JOIN CL_TRANSAC ON BGD_ZC_MX.JGTK = CL_TRANSAC.ID WHERE YWID='"+ywid+"'");
//			sbdata.append("<bgd_zc_mx>\n");
//			cbzcmx.setCellObj(0,"jgtk",cbzcmx.getValue("trans_mode_name"));
//			for (int i=0; i<cbzcmx.getColumnNum(); i++) {
//				String colname = cbzcmx.getColName(i);
//				if (colname.toUpperCase().lastIndexOf("_NAME")>-1 || colname.toUpperCase().lastIndexOf("_ENAME")>-1) continue;
//				sbdata.append("<"+colname+">"+Function.trimString(cbzcmx.getValue(colname))+"</"+colname+">\n");
//			}
//			sbdata.append("</bgd_zc_mx>\n");
//			//�ϼ����
//			if (ywtype.equals("7") || ywtype.equals("10") || ywtype.equals("14") || ywtype.equals("15")) {
//				sbdata.append(getLJYW(ywtype,ywid));
//			}
//			//��Ʒ���
//			else if  (ywtype.equals("8") || ywtype.equals("11") || ywtype.equals("16") || ywtype.equals("17")) {
//				sbdata.append(getCPYW(ywtype,ywid));
//			}
//			//��Ʒ����
//			else if (ywtype.equals("12") || ywtype.equals("13") || ywtype.equals("21") || ywtype.equals("26")) {
//				sbdata.append(getNXYW(ywtype,ywid));
//			}
//		}
//		sbdata.append("</ROOT>");
//		
//		return sbdata.toString();
//	}
//	
//	//�ϼ���ص�ҵ��xml
//	private String getLJYW(String ywtype, String ywid) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		String tpre = (String)hmYWEnum.get(ywtype);
//		StringBuffer sbdata = new StringBuffer();
//		
//		//��ϸ����
//		String sql = "SELECT MX.SPID,MX.SPNAME,MX.LJNO,MX.GUIGE,Sum(MX.SPINT) As spint,Sum(MX.ZJ) As ZJ,Sum(MX.ZJ)/Sum(mx.spint) As DJ,CL_UNIT.SNAME AS UNITS,CL_CURR.SENAME AS BZSNAME,CL_COUNTRY.SNAME AS SGOV \n"
//					+"FROM "+tpre+"_MX MX \n"
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=MX.UNITS \n"
//					+"LEFT JOIN CL_COUNTRY ON CL_COUNTRY.ID=MX.SGOV \n"
//					+"LEFT JOIN CL_CURR ON CL_CURR.ID=MX.BZSNAME \n"
//					+"WHERE MX.YWID='"+ywid+"' \n"
//					+"GROUP BY SPID,SPNAME,LJNO,GUIGE,CL_UNIT.SNAME,CL_CURR.SENAME,CL_COUNTRY.SNAME";
//		CommonBean cbMX = dbo.getData(sql);
//		sbdata.append("<mx>\n");
//		for (int r=0; r<cbMX.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			for (int c=0; c<cbMX.getColumnNum(); c++) {
//				String colname = cbMX.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbMX.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</mx>\n");
//			
//		//�鲢����
//		sql = "Select gb.spid,gb.spname,Sum(gb.spint) As spint,Sum(gb.zj) As zj,Sum(gb.zj)/Sum(gb.spint) As avgjg,3 as selzm,cl_unit.sname As units,cl_curr.sename As bzsname,cl_country.sname As sgov \n"
//			+ "From "+tpre+"_GB gb \n"
//			+ "Left Join cl_unit On cl_unit.id=gb.units \n"
//			+ "Left Join cl_country On cl_country.id=gb.sgov \n"
//			+ "Left Join cl_curr On cl_curr.id=gb.bzsname \n"
//			+ "Where gb.ywid='"+ywid+"' \n"
//			+ "Group By spid,spname,cl_unit.sname,cl_curr.sename,cl_country.sname";
//		CommonBean cbGB = dbo.getData(sql);
//		sbdata.append("<gb>\n");
//		for (int r=0; r<cbGB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			for (int c=0; c<cbGB.getColumnNum(); c++) {
//				String colname = cbGB.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbGB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</gb>\n");
//		
//		return sbdata.toString();
//	}
//	
//	//��Ʒ��ص�ҵ��xml
//	private String getCPYW(String ywtype, String ywid) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		String tpre = (String)hmYWEnum.get(ywtype);
//		StringBuffer sbdata = new StringBuffer();
//		
//		//��ϸ
//		String sql = "SELECT MX.SPID,MX.SPNAME,MX.guige As cpno,bomver.ver As dhver,MX.GUIGE,Sum(MX.SPINT) As spint,Sum(MX.ZJ) As zj,Sum(MX.ZJ)/Sum(MX.SPINT) As DJ,CL_UNIT.SNAME AS UNITS,CL_CURR.SENAME AS BZSNAME,CL_COUNTRY.SNAME AS SGOV \n"
//					+"FROM "+tpre+"_MX MX \n"
//					+"LEFT JOIN (select guige,cpno,max(ver) as ver from ba_dzzc_bom_ver group by guige,cpno) bomver ON bomver.guige=mx.guige And bomver.cpno=mx.cpno \n"
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=MX.UNITS \n"
//					+"LEFT JOIN CL_COUNTRY ON CL_COUNTRY.ID=MX.SGOV \n"
//					+"LEFT JOIN CL_CURR ON CL_CURR.ID=MX.BZSNAME \n"
//					+"WHERE MX.YWID='"+ywid+"' \n"
//					+"Group By SPID,SPNAME,mx.GUIGE,CL_UNIT.SNAME,CL_CURR.SENAME,CL_COUNTRY.SNAME,bomver.ver";
//		CommonBean cbMX = dbo.getData(sql);
//		sbdata.append("<mx>\n");
//		for (int r=0; r<cbMX.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			for (int c=0; c<cbMX.getColumnNum(); c++) {
//				String colname = cbMX.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbMX.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</mx>\n");
//		//�ϼ���ϸ
//		sql = "Select ljmx.spid,ljmx.cpname,ljmx.guige As cpno,bomver.ver as dhver,Sum(ljmx.cpint) As cpint,ljmx.ljspid,ljmx.ljname,ljmx.ljno,ljmx.guige,sum(ljmx.ljint) as ljint,cl_unit.sname As units,cl_country.sname as sgov,cl_curr.sename as bzsname \n"
//			+ "From "+tpre+"_lj_mx ljmx \n"
//			+ "LEFT JOIN (select guige,cpno,max(ver) as ver from ba_dzzc_bom_ver group by guige,cpno) bomver ON bomver.guige=ljmx.guige And bomver.cpno=ljmx.cpno \n"
//			+ "Left Join cl_unit On cl_unit.id=ljmx.units \n"
//			+ "Left Join cl_country On cl_country.id=ljmx.sgov \n"
//			+ "Left Join cl_curr On cl_curr.id=ljmx.bzsname \n"
//			+ "Where ljmx.ywid='"+ywid+"' And ljmx.ljint>0 \n"
//			+ "Group By spid,cpname,ljspid,ljname,ljno,ljmx.guige,cl_unit.sname,cl_country.sname,cl_curr.sename,bomver.ver";
//		CommonBean cbLJ_MX = dbo.getData(sql);
//		sbdata.append("<lj_mx>\n");
//		for (int r=0; r<cbLJ_MX.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			for (int c=0; c<cbLJ_MX.getColumnNum(); c++) {
//				String colname = cbLJ_MX.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbLJ_MX.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</lj_mx>\n");
//		//�ϼ��鲢
//		sql = "Select ljgb.spid,ljgb.spname,Sum(ljgb.spint) As spint,cl_unit.sname As units \n"
//			+ "From " + tpre+ "_lj_gb ljgb \n"
//			+ "Left Join cl_unit On cl_unit.id=ljgb.units \n"
//			+ "Where ljgb.ywid='"+ywid+"' And ljgb.spint>0 \n"
//			+ "Group By spid,spname,cl_unit.sname";
//		CommonBean cbLJ_GB = dbo.getData(sql);
//		sbdata.append("<lj_gb>\n");
//		for (int r=0; r<cbLJ_GB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//		
//			for (int c=0; c<cbLJ_GB.getColumnNum(); c++) {
//				String colname = cbLJ_GB.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbLJ_GB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</lj_gb>\n");
//		//�鲢(��Ʒ)
//		sql = "Select gb.spid,gb.spname,Sum(gb.spint) As spint,Sum(gb.zj) As zj,Sum(gb.zj)/Sum(gb.spint) As avgjg,3 As selzm,cl_unit.sname As units,cl_curr.sename As bzsname,cl_country.sname As sgov \n"
//			+ "From "+tpre+"_GB gb \n"
//			+ "Left Join cl_unit On cl_unit.id=gb.units \n"
//			+ "Left Join cl_country On cl_country.id=gb.sgov \n"
//			+ "Left Join cl_curr On cl_curr.id=gb.bzsname \n"
//			+ "Where gb.ywid='"+ywid+"' \n"
//			+ "Group By spid,spname,cl_unit.sname,cl_curr.sename,cl_country.sname";
//		CommonBean cbGB = dbo.getData(sql);
//		sbdata.append("<gb>\n");
//		for (int r=0; r<cbGB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//		
//			for (int c=0; c<cbGB.getColumnNum(); c++) {
//				String colname = cbGB.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbGB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</gb>\n");
//		//�鲢(��Ʒ�ϼ�)
//		sql = "Select cpljgb.cpspid,cpljgb.cpname,Sum(cpljgb.cpint) As cpint,cpljgb.ljspid,cpljgb.ljname,Sum(cpljgb.ljint) As ljint,Sum(cpljgb.ljint)/Sum(cpljgb.cpint) As dh,cl_curr.sename as bzsname,cl_country.sname as sgov \n"
//			+ "From "+tpre+"_cp_lj_gb cpljgb \n"
//			+ "Left Join cl_country On cl_country.id=cpljgb.sgov \n"
//			+ "Left Join cl_curr On cl_curr.id=cpljgb.bzsname \n"
//			+ "Where cpljgb.ywid='"+ywid+"' And cpljgb.ljint>0 \n"
//			+ "Group By cpspid,cpname,ljspid,ljname,cl_curr.sename,cl_country.sname";
//		CommonBean cbCP_LJ_GB = dbo.getData(sql);
//		sbdata.append("<cp_lj_gb>\n");
//		for (int r=0; r<cbCP_LJ_GB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			for (int c=0; c<cbCP_LJ_GB.getColumnNum(); c++) {
//				String colname = cbCP_LJ_GB.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbCP_LJ_GB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</cp_lj_gb>\n");
//		
//		return sbdata.toString();
//	}
//	
//	//����ҵ��xml(������Ʒ,�����ϼ�,��������,��������)
//	private String getNXYW(String ywtype, String ywid) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		String tpre = (String)hmYWEnum.get(ywtype);
//		StringBuffer sbdata = new StringBuffer();
//		
//		//��ϸ����
//		String sql = "SELECT MX.SPID,MX.SPNAME,MX.LJNO,MX.GUIGE,Sum(MX.SPINT) As spint,Sum(MX.ZJ) As ZJ,Sum(MX.ZJ)/Sum(mx.spint) As DJ,CL_UNIT.SNAME AS UNITS,CL_CURR.SENAME AS BZSNAME,CL_COUNTRY.SNAME AS SGOV \n"
//					+"FROM "+tpre+"_jk MX \n"
//					+"LEFT JOIN CL_UNIT ON CL_UNIT.ID=MX.UNITS \n"
//					+"LEFT JOIN CL_COUNTRY ON CL_COUNTRY.ID=MX.SGOV \n"
//					+"LEFT JOIN CL_CURR ON CL_CURR.ID=MX.BZSNAME \n"
//					+"WHERE MX.YWID='"+ywid+"' \n"
//					+"GROUP BY SPID,SPNAME,LJNO,GUIGE,CL_UNIT.SNAME,CL_CURR.SENAME,CL_COUNTRY.SNAME";
//		CommonBean cbMX = dbo.getData(sql);
//		sbdata.append("<mx>\n");
//		for (int r=0; r<cbMX.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//
//			for (int c=0; c<cbMX.getColumnNum(); c++) {
//				String colname = cbMX.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbMX.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</mx>\n");
//		
//		//�鲢����
//		sql = "Select gb.spid,gb.spname,Sum(gb.spint) As spint,Sum(gb.zj) As zj,Sum(gb.zj)/Sum(gb.spint) As avgjg,3 as selzm,cl_unit.sname As units,cl_curr.sename As bzsname,cl_country.sname As sgov \n"
//			+ "From "+tpre+"_gb gb \n"
//			+ "Left Join cl_unit On cl_unit.id=gb.units \n"
//			+ "Left Join cl_country On cl_country.id=gb.sgov \n"
//			+ "Left Join cl_curr On cl_curr.id=gb.bzsname \n"
//			+ "Where gb.ywid='"+ywid+"' \n"
//			+ "Group By spid,spname,cl_unit.sname,cl_curr.sename,cl_country.sname";
//		CommonBean cbGB = dbo.getData(sql); 
//		sbdata.append("<gb>\n");
//		for (int r=0; r<cbGB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//
//			for (int c=0; c<cbGB.getColumnNum(); c++) {
//				String colname = cbGB.getColName(c);
//				sbdata.append("<"+colname+">"+Function.trimString(cbGB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</gb>\n");
//		
//		return sbdata.toString();
//	}
//	
//	//����ҵ��xml
//	private String getHXYW(String ywtype, String ywid) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		String tpre = (String)hmYWEnum.get(ywtype);
//		StringBuffer sbdata = new StringBuffer();
//		
//		//��ϸ����
//		CommonBean cbMX = dbo.getData("SELECT HX_MX.*,CL_UNIT.SNAME AS UNITS_NAME FROM HX_MX LEFT JOIN CL_UNIT ON CL_UNIT.ID=HX_MX.UNITS WHERE HX_MX.YWID='"+ywid+"'");
//		cbMX.removeColumn("id");
//		cbMX.removeColumn("ywid");
//		sbdata.append("<mx>\n");
//		for (int r=0; r<cbMX.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			cbMX.setCellObj(r,"units",cbMX.getCellObj(r,"units_name"));
//
//			for (int c=0; c<cbMX.getColumnNum(); c++) {
//				String colname = cbMX.getColName(c);
//				if (colname.toUpperCase().lastIndexOf("_NAME")>-1 || colname.toUpperCase().lastIndexOf("_ENAME")>-1) continue;
//				sbdata.append("<"+colname+">"+Function.trimString(cbMX.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</mx>\n");
//			
//		//�鲢����
//		CommonBean cbGB = dbo.getData("SELECT HX_GB.*,CL_UNIT.SNAME AS UNITS_NAME FROM HX_GB LEFT JOIN CL_UNIT ON CL_UNIT.ID=HX_GB.UNITS WHERE HX_GB.YWID='"+ywid+"'");
//		cbGB.removeColumn("id");
//		cbGB.removeColumn("ywid");
//		sbdata.append("<gb>\n");
//		for (int r=0; r<cbGB.getRowNum(); r++) {
//			sbdata.append("<entry>\n");
//			cbGB.setCellObj(r,"units",cbGB.getCellObj(r,"units_name"));
//
//			for (int c=0; c<cbGB.getColumnNum(); c++) {
//				String colname = cbGB.getColName(c);
//				if (colname.toUpperCase().lastIndexOf("_NAME")>-1 || colname.toUpperCase().lastIndexOf("_ENAME")>-1) continue;
//				sbdata.append("<"+colname+">"+Function.trimString(cbGB.getCellStr(r,c))+"</"+colname+">\n");
//			}
//			sbdata.append("</entry>\n");
//		}
//		sbdata.append("</gb>\n");
//		
//		return sbdata.toString();
//	}
//	
//	//ȡ��������Ч����,�����ϼ��ͳ�Ʒ
//	public String getBaData(String operflag) throws Exception {
//		DataBaseObject dbo = this.getDataBaseObject();
//		StringBuffer sbdata = new StringBuffer();
//		
//			//operflag=BALJ�Ǵ��䱸���ϼ�����
//			//operflag=BACP�Ǵ��䱸����Ʒ����
//			
//			//��ʼ��֯ҵ�����ݵ�xml
//			//sbdata.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>\n");
//			sbdata.append("<ROOT>\n");
//			sbdata.append("<QYALLID>"+getPubQyid()+"</QYALLID>\n");
//			sbdata.append("<OPERFLAG>"+operflag+"</OPERFLAG>\n");			
//			//�ж��ϴ����ݵ�����
//			String strSql=null;
//			String strUpdate=null;
//			if (operflag.equals( "BALJ")){
//				//�ϴ������ϼ�
//				strSql="SELECT BA.SPID,BA.SPNAME,BA.LJNO,CL_UNIT.SNAME AS UNITS,BA.GUIGE  FROM BA_DZZC_LJ_QY BA,CL_UNIT "
//					 + "WHERE CL_UNIT.ID=BA.UNIT AND BA.EXCHGED=0";
//			}
//			else if(operflag.equals( "BACP")){
//				//�ϴ�������Ʒ
//				strSql="SELECT DISTINCT BA.SPID,BA.SPNAME,BA.GUIGE AS CPNO,CL_UNIT.SNAME AS UNITS,BA.GUIGE  FROM BA_DZZC_CP_QY BA,CL_UNIT "
//					 + "WHERE CL_UNIT.ID=BA.UNIT AND BA.EXCHGED=0";	
//			}
//			else if(operflag.equals( "BADH")){
//				//�ϴ�����BOM
//				strSql="SELECT CPNO,LJNO,VERSION,DH FROM BA_DZZC_BOM_GB WHERE EXCHGED=0";	
//			}
//
//			CommonBean cbData=dbo.getData(strSql);		
//			sbdata.append("<BADATA>\n");			
//			for (int i=0; i<cbData.getRowNum(); i++) {
//				sbdata.append("<entry>\n");			
//		
//				for (int j=0; j<cbData.getColumnNum(); j++) {
//					String colname = cbData.getColName(j);
//					
//					sbdata.append("<"+colname+">"+Function.trimString(cbData.getCellStr(i,j))+"</"+colname+">\n");
//				}
//				sbdata.append("</entry>\n");
//			}
//			sbdata.append("</BADATA>\n");	
//			sbdata.append("</ROOT>");
//			return sbdata.toString();
//		}
//		
//		
//	//ȡҪ����״̬�������б�
//	public CommonBean UpdataBaList(String operflag) {
//		try {
//			//����operflagȡ����
//			String strSql=null;
//			String strTable=null;
//			if (operflag.equals( "BALJ")){
//				strSql="SELECT ID,1 AS EXCHGED  FROM BA_DZZC_LJ_QY  WHERE  EXCHGED=0";
//				strTable="BA_DZZC_LJ_QY";
//			}
//			else if (operflag.equals("BACP")){
//				strSql="SELECT ID,1 AS EXCHGED  FROM BA_DZZC_CP_QY  WHERE  EXCHGED=0";
//				strTable="BA_DZZC_CP_QY";
//			}
//			else if (operflag.equals("BADH")){
//				strSql="SELECT ID,1 AS EXCHGED  FROM BA_DZZC_BOM_GB WHERE  EXCHGED=0";
//				strTable="BA_DZZC_BOM_GB";
//			}
//			  
//			DataBaseObject dbo = getDataBaseObject();
//			CommonBean cb = dbo.getData(strSql);
//			cb = Function.formatBean(cb);
//			cb.setBeanName(strTable);
//			cb.setAttribute("update");
//			
//			return cb;
//
//		} catch (Exception e) {
//			rollback();
//			Log.error(this, "ȡ�����ϴ������б�ʱ��������" + e.getMessage());			
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	//ȡҵ�������Ϣ����
//	public boolean getAuditData(String passdata) {
//		try {
//			//SAXReader saxReader = new SAXReader();
//			Document document = DocumentHelper.parseText(passdata);
//			Element root = document.getRootElement();
//			Iterator iter_root = root.elementIterator();
//			
//			CommonBean cbPassData = new CommonBean();
//			//����xml,�������bean
//			while (iter_root.hasNext()) {
//				Element element_entry = (Element)iter_root.next();
//					
//				if (element_entry.getName().equalsIgnoreCase("entry")) {
//					Iterator iter_entry = element_entry.elementIterator();
//					while (iter_entry.hasNext()) {
//						Element element_item = (Element)iter_entry.next();
//						if (element_item.getName().toLowerCase().equalsIgnoreCase("id")) {
//							cbPassData.addValue("id",element_item.getText());
//						}
//						else if (element_item.getName().toLowerCase().equalsIgnoreCase("status")) {
//							String value = element_item.getText();
//							if (value.toLowerCase().equals("pass"))
//								cbPassData.addValue("status","3");
//							else
//								cbPassData.addValue("status","4");
//						}
//						else if (element_item.getName().toLowerCase().equalsIgnoreCase("memo")) {
//							cbPassData.addValue("memo",element_item.getText());
//						}
//					}
//				}
//			}
//			cbPassData.setBeanName("yw_pass");
//			cbPassData.setAttribute("insert");
//			
//			DataBaseObject dbo = this.getDataBaseObject();
//			dbo.execute(cbPassData);
//			this.multicommit();
//			
//			cbPassData = dbo.getData("Select * From yw_pass Where oper_flag=0 And Exists (Select ywlist.id From ywlist Where ywlist.id=yw_pass.id)");
//			
//			//׼������ϵͳ
//			for (int i=0; i<cbPassData.getRowNum(); i++) {
//				String sid = cbPassData.getCellStr(i,"id");
//				String sta = cbPassData.getCellStr(i,"status");
//				
//				//�鿴ҵ������
//				if (sta.equals("3")) {	//���ͨ��
//					CommonBean cbType = dbo.getData("SELECT TYPEID FROM YWLIST WHERE ID = '"+sid+"'");
//					
//					if (cbType!=null && cbType.getRowNum()>0) {
//						String classname = getClassName(Integer.parseInt(cbType.getValue("typeid")));
//						Class cls = Class.forName(classname);
//						
//						Class partypes[] = new Class[2];
//						partypes[0] = CommonBean.class;
//						partypes[1] = String.class;
//						Method mtd = cls.getMethod("passYW",partypes);
//						
//						Object arglist[] = new Object[2];
//						arglist[0] = null;
//						arglist[1] = new String(sid);
//						
//						mtd.invoke(cls.newInstance(),arglist);
//					}
//				}
//				else {
//					YWBean ywbean = new YWBean();
//					CommonBean cbUnPassData = new CommonBean();
//					cbUnPassData.addValue("id", cbPassData.getCellStr(i,"id"));
//					cbUnPassData.addValue("memo", cbPassData.getCellStr(i,"memo"));
//					ywbean.refuseYW(null,cbUnPassData);
//				}
//				
//				dbo.execute("Update yw_pass Set oper_flag=1 Where id = '"+sid+"'");
//				this.multicommit();
//			}
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	//����ҵ����������ص����ļ�
//	private String getClassName(int typeid) {
//		String classname = "";
//
//		switch (typeid) {
//			case 7:	//�����ϼ�(gb/mx)
//				classname = "com.paink.module.jkyw.jklj.bean.JkljBean";
//				break;
//			case 8:	//��Ʒ����
//				classname = "com.paink.module.ckyw.cpck.bean.CpckBean";
//				break;
//			case 10:	//��תת��
//				classname = "com.paink.module.jkyw.sjgzr.bean.SjgzrBean";
//				break;
//			case 11:	//��תת��
//				classname = "com.paink.module.ckyw.sjgzc.bean.SjgzcBean";
//				break;
//			case 12:	//��Ʒ����
//				classname = "com.paink.module.ckyw.nx.bean.NxBean";
//				break;
//			case 13:	//��������
//				classname = "com.paink.module.ckyw.fqxh.bean.FqxhBean";
//				break;
//			case 14:	//�ϼ��˻�
//				classname = "com.paink.module.ckyw.ljth.bean.LjthBean";
//				break;
//			case 15:	//�ϼ�����
//				classname = "com.paink.module.jkyw.ljhh.bean.LjhhBean";
//				break;
//			case 16:	//��Ʒ�˻�
//				classname = "com.paink.module.jkyw.cpth.bean.CpthBean";
//				break;
//			case 17:	//��Ʒ����
//				classname = "com.paink.module.ckyw.cphh.bean.CphhBean";
//				break;
//			case 18:	//ɾ��
//				classname = "com.paink.module.bgddel.bean.BgddelBean";
//				break;
//			case 21:	//�ϼ�����
//				classname = "com.paink.module.ckyw.ljnx.bean.LjnxBean";
//				break;
//			case 26:	//��������
//				classname = "com.paink.module.ckyw.othernx.bean.OthernxBean";
//				break;
//		}
//		return classname;
//	}
//	
//	//���ع�����Ϣ
//	public boolean getCountryData(String cldata) throws Exception {
//		//SAXReader saxReader = new SAXReader();
//		Document document = DocumentHelper.parseText(cldata);
//		Element root = document.getRootElement();
//		Iterator iter_root = root.elementIterator();
//		
//		CommonBean cbCountry = new CommonBean();
//		//����xml,�������bean
//		while (iter_root.hasNext()) {
//			Element element_entry = (Element)iter_root.next();
//				
//			if (element_entry.getName().equalsIgnoreCase("entry")) {
//				Iterator iter_entry = element_entry.elementIterator();
//				while (iter_entry.hasNext()) {
//					Element element_item = (Element)iter_entry.next();
//					if (element_item.getName().toLowerCase().equalsIgnoreCase("code")) {
//						cbCountry.addValue("id",element_item.getText());
//					}
//					else if (element_item.getName().toLowerCase().equalsIgnoreCase("gname")) {
//						cbCountry.addValue("sname",element_item.getText());
//					}
//				}
//			}
//		}
//		
//		//��������
//		boolean bFlag = true;
//		if (cbCountry.getRowNum()>0) {
//			DataBaseObject dbo = this.getDataBaseObject();
//			bFlag = dbo.execute("delete from cl_country");
//			cbCountry.setBeanName("cl_country");
//			cbCountry.setAttribute("insert");
//			bFlag = bFlag && dbo.execute(cbCountry);
//		}
//		return bFlag;
//	}
//	
//	//���ر�����Ϣ
//	public boolean getCurrData(String cldata) throws Exception {
//		//SAXReader saxReader = new SAXReader();
//		Document document = DocumentHelper.parseText(cldata);
//		Element root = document.getRootElement();
//		Iterator iter_root = root.elementIterator();
//		
//		CommonBean cbCurr = new CommonBean();
//		//����xml,�������bean
//		while (iter_root.hasNext()) {
//			Element element_entry = (Element)iter_root.next();
//				
//			if (element_entry.getName().equalsIgnoreCase("entry")) {
//				Iterator iter_entry = element_entry.elementIterator();
//				while (iter_entry.hasNext()) {
//					Element element_item = (Element)iter_entry.next();
//					if (element_item.getName().toLowerCase().equalsIgnoreCase("code")) {
//						cbCurr.addValue("id",element_item.getText());
//					}
//					else if (element_item.getName().toLowerCase().equalsIgnoreCase("bzcname")) {
//						cbCurr.addValue("sname",element_item.getText());
//					}
//					else if (element_item.getName().toLowerCase().equalsIgnoreCase("bzename")) {
//						cbCurr.addValue("sename",element_item.getText());
//					}
//				}
//			}
//		}
//		
//		//��������
//		boolean bFlag = true;
//		if (cbCurr.getRowNum()>0) {
//			DataBaseObject dbo = this.getDataBaseObject();
//			bFlag = dbo.execute("delete from cl_curr");
//			cbCurr.setBeanName("cl_curr");
//			cbCurr.setAttribute("insert");
//			bFlag = bFlag && dbo.execute(cbCurr);
//		}
//		return bFlag;
//	}
//	
//	//���ص�λ��Ϣ
//	public boolean getUnitData(String cldata) throws Exception {
//		//SAXReader saxReader = new SAXReader();
//		Document document = DocumentHelper.parseText(cldata);
//		Element root = document.getRootElement();
//		Iterator iter_root = root.elementIterator();
//		
//		CommonBean cbUnit = new CommonBean();
//		//����xml,�������bean
//		while (iter_root.hasNext()) {
//			Element element_entry = (Element)iter_root.next();
//				
//			if (element_entry.getName().equalsIgnoreCase("entry")) {
//				Iterator iter_entry = element_entry.elementIterator();
//				while (iter_entry.hasNext()) {
//					Element element_item = (Element)iter_entry.next();
//					if (element_item.getName().toLowerCase().equalsIgnoreCase("code")) {
//						cbUnit.addValue("id",element_item.getText());
//					}
//					else if (element_item.getName().toLowerCase().equalsIgnoreCase("units")) {
//						cbUnit.addValue("sname",element_item.getText());
//					}
//				}
//			}
//		}
//		
//		//��������
//		boolean bFlag = true;
//		if (cbUnit.getRowNum()>0) {
//			DataBaseObject dbo = this.getDataBaseObject();
//			bFlag = dbo.execute("delete from cl_unit");
//			cbUnit.setBeanName("cl_unit");
//			cbUnit.setAttribute("insert");
//			bFlag = bFlag && dbo.execute(cbUnit);
//			//��Ӣ������д����
//			bFlag = bFlag && dbo.execute("update cl_unit set sename = (select sename from cl_unit_bak where id = cl_unit.id)");
//		}
//		return bFlag;
//	}
//	
//	//���ص�λ��Ϣ
//	public boolean getNews(String cldata) throws Exception {
//		//SAXReader saxReader = new SAXReader();
//		Document document = DocumentHelper.parseText(cldata);
//		Element root = document.getRootElement();
//		Iterator iter_root = root.elementIterator();
//		
//		CommonBean cbNews = new CommonBean();
//		//����xml,�������bean
//		while (iter_root.hasNext()) {
//			Element element_entry = (Element)iter_root.next();
//				
//			if (element_entry.getName().equalsIgnoreCase("entry")) {
//				Iterator iter_entry = element_entry.elementIterator();
//				cbNews.addValue("id",Oid.getOid());
//				while (iter_entry.hasNext()) {
//					Element element_item = (Element)iter_entry.next();
//					if (element_item.getName().toLowerCase().equalsIgnoreCase("adddate")) {
//						cbNews.addValue("adddate","_SYSDATE");
//						cbNews.addValue("issuedate",element_item.getText());
//						cbNews.addValue("startdate",element_item.getText());
//					}
//					else if (element_item.getName().toLowerCase().equalsIgnoreCase("content")) {
//						cbNews.addValue("content",element_item.getText());
//					}
//				}
//				cbNews.addValue("title","(���ع���)");
//				cbNews.addValue("operflag","1");
//				cbNews.addValue("hgflag","1");
//			}
//		}
//		
//		//��������
//		boolean bFlag = true;
//		if (cbNews.getRowNum()>0) {
//			DataBaseObject dbo = this.getDataBaseObject();
//			cbNews.setBeanName("notice");
//			cbNews.setAttribute("insert");
//			bFlag = bFlag && dbo.execute(cbNews);
//		}
//		return bFlag;
//	}
//	
//
//	public static String getPubQyid() {
//		return pubQyid;
//	}
//	
//	public static String getAuthSN() {
//		return authSN;
//	}
//}
