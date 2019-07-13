/*
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.exchgdata.csc2ie.bean;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aiyun.common.bo.BusinessObject;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.tool.Function;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class UploadBean extends BusinessObject {
	//����·��
	//private static String pubDir = "c:/data/bmc";
	private static String pubDir = "/app/paink/data/BMC";

	public boolean UploadAcc(CommonBean cbUser, String ywid) {
		
		try {
			boolean bFlag = true;
			String strSql=null;
			DataBaseObject dbo = getDataBaseObject();
			if (ywid==null){
//				 strSql =" Select bom.cpno,bom.version ,bom.ljno,bom.dh ,lj.spname,lj.e_spname,lj.guige,cp.cpid,cp.h2000ver \n"
//						+" From ie_bom bom \n"
//						+" inner Join ba_dzzc_lj_qy lj On bom.ljno=lj.ljno \n"
//						+" inner Join ba_dzzc_cp_qy cp On bom.cpno=cp.cpno  And BOM.VERSION=CP.VERSION\n"
//						+" Where bom.oper_flag=0 And lj.accflag='1'";

				strSql =" Select bom.cpno,bom.version ,bom.ljno,bom.dh ,lj.spname,lj.e_spname,lj.guige,cp.cpid,cp.h2000ver,cp.spname cpname  \n"
					   +" From ba_dzzc_bom_qy bom \n"
					   +" inner Join ba_dzzc_lj_qy lj On bom.ljno=lj.ljno \n"
					   +" inner Join ba_dzzc_cp_qy cp On bom.cpno=cp.cpno  And BOM.VERSION=CP.VERSION \n"
					   +" Where  lj.accflag='1'";
			}else{
				strSql =" Select bom.cpno,bom.version ,bom.ljno,bom.dh ,lj.spname,lj.e_spname,lj.guige,cp.cpid,cp.h2000ver,cp.spname cpname   \n"
					   +" From ba_dzzc_bom bom \n"
					   +" inner Join ba_dzzc_lj_qy lj On bom.ljno=lj.ljno \n"
					   +" inner Join ba_dzzc_cp_qy cp On bom.cpno=cp.cpno  And BOM.VERSION=CP.VERSION\n"
					   +" Where bom.ywid='"+ywid+"' And lj.accflag='1'";
			}
			CommonBean cbData = dbo.getData(strSql);
			
			if (cbData == null || cbData.getRowNum() <= 0) {
				
				return true;
			}
			StringBuffer strB=new StringBuffer();
			for (int i = 0; i < cbData.getRowNum(); i++) {
				strB.append(""+Function.trimString(cbData.getCellStr(i,"cpname"))+",");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"cpno"))+",");
				strB.append(cbData.getCellStr(i,"version")+",");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"cpid"))+",");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"h2000ver"))+",");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"ljno"))+",");
				if (Function.trimString(cbData.getCellStr(i,"spname")).equalsIgnoreCase("�����")){
					strB.append(""+Function.trimString(cbData.getCellStr(i,"spname"))+Function.trimString(cbData.getCellStr(i,"guige"))+" ,");
				}else{
					strB.append(""+Function.trimString(cbData.getCellStr(i,"spname"))+" ,");
				
				}
				strB.append(",");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"dh"))+" \n");		

			}		
			String strContent=strB.toString();
			

			Date dTime = new Date();			 
		    SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
		    String fname=sd.format( dTime).toString();
		    String strFileName =pubDir+ "/acc/" + fname+ ".csv";
		    
			OutputStreamWriter   out   =   null;   
		  try   {   
			out   =   new   OutputStreamWriter((new   FileOutputStream(strFileName)),"GB2312");   
				  out.write(strContent);   
				  out.flush();   
				  out.close();   

		    
//			FileWriter fw = new FileWriter(strFileName);		
//			fw.write(strContent);			
//			fw.flush();
//			fw.close();
			bFlag=true;
				
		  }   catch   (Exception   e)   { 
		  		bFlag=false;  
		  		e.printStackTrace();
				 System.err.println(e);  
				return false; 
		  }   finally   {   
					out.close();   
        
		  }   

		    
		    
			//FileWriter fw = new FileWriter(pubDir + strFileName);		
		
			//fw.write(strContent);			
			//fw.flush();
			//bFlag=true;

			return bFlag;


		} catch (Exception e) {
			
			rollback();
			Log.error(this, "bom�ĸ�����Ϣ�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("bom�ĸ�����Ϣ�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			return false;
			
		}
		

	}
	
		//�ϴ���Ʒ�����õİ��Ʒ������Ϣ
	  public boolean UploadGd(CommonBean cbUser, String ywid) {
		if (1>0) return true;
		  try {
			  boolean bFlag = true;
			  DataBaseObject dbo = getDataBaseObject();
			  //��YWID��BOM���ҳ�����
			  String strSql =" Select distinct mj_order,cj_order From ie_gdtrace Where mj_order is not null and ywid='"+ywid+"'";
			  CommonBean cbData = dbo.getData(strSql);
			
			  if (cbData == null || cbData.getRowNum() <= 0) {
				
				  return true;
			  }
			  StringBuffer strB=new StringBuffer();
			  for (int i = 0; i < cbData.getRowNum(); i++) {
				  //ѭ����д����
				  //���������ӹ���
				  strB.append(""+Function.trimString(cbData.getCellStr(i,"mj_order"))+","+Function.trimString(cbData.getCellStr(i,"cj_order")) +" \n");
				strB.append(""+Function.trimString(cbData.getCellStr(i,"cj_order"))+","+Function.trimString(cbData.getCellStr(i,"mj_order")) +" \n");
				 

			  }		
			  //��������
			  String strContent=strB.toString();
			

			  //�ļ���
			  Date dTime = new Date();			 
			  SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
			  String fname=sd.format( dTime).toString();
			  String strFileName =pubDir+ "/gd/" + fname+ ".csv";
		    
			OutputStreamWriter   out   =   null;   
		  try   {   
				  out   =   new   OutputStreamWriter((new   FileOutputStream(strFileName)),"GB2312");   
				  out.write(strContent);   
				  out.flush();   
				  out.close();   
				bFlag   =   true;   
		  }   catch   (Exception   e)   { 
				bFlag=false;  
				e.printStackTrace();
				 System.err.println(e);   
				 return false;
		  }   finally   {   
					out.close();   
        
		  }   

			  

//			  FileWriter fw = new FileWriter(pubDir + strFileName);		
//			  fw.write(strContent);			
//			  fw.flush();
//			  bFlag=true;

			  return bFlag;


		  } catch (Exception e) {
			
			  rollback();
			  Log.error(this, "��Ʒ���ڶ�Ӧ���Ʒ�����ļ�ʱ��������" + e.getMessage());
			  getErrMsgBean().addCommonMessage("��Ʒ���ڶ�Ӧ���Ʒ�����ļ�ʱ��������" + e.getMessage());
			  e.printStackTrace();
			  return false;
			
		  }
		

	  }
	
	

}
