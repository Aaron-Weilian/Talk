/*
 * �������� 2004-12-14
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.common.exchgdata.csc2ie.bean;

import java.io.FileOutputStream;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.aiyun.common.po.DBUtil;
import com.aiyun.common.po.DataBaseObject;
import com.aiyun.common.tool.Function;
import com.aiyun.common.tool.Log;
import com.aiyun.common.vo.CommonBean;

public class XmlBean extends DBUtil {
	//����·��
	//private static String pubDir = "e:/data/ie";
	private static String pubDir = "/data/ie";

	//�����ϼ�������IE���ڷ�Ʊ����	
	public CommonBean JkljCi(CommonBean cbUser, String ywid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "���ڷ�Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��jklj_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,po_item,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,ljno,spid,spname,spint,units,zj,bzsname from jklj_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", Function.trimString(cbXml.getCellStr(i, "po_item"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "ljno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", "7"));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "�����ϼ�����XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("�����ϼ�����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}
	//	��תת�룭����IE���ڷ�Ʊ����	
	public CommonBean SjgzrCi(CommonBean cbUser, String ywid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "��תת�뷢Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��jklj_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,po_item,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,ljno,spid,spname,spint,units,zj,bzsname from sjgzr_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", Function.trimString(cbXml.getCellStr(i, "po_item"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "ljno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", "8"));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "��תת������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("��תת������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//	�ϼ�����������IE���ڷ�Ʊ����	
	public CommonBean LjhhCi(CommonBean cbUser, String ywid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "�ϼ�������Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��jklj_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,po_item,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,ljno,spid,spname,spint,units,zj,bzsname from ljhh_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", Function.trimString(cbXml.getCellStr(i, "po_item"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "ljno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", "9"));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "�ϼ���������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("�ϼ���������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}
	//��Ʒ�˻�������IE���ڷ�Ʊ����	
	public CommonBean CpthCi(CommonBean cbUser, String ywid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "���ڷ�Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��cpth_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,cpno,spid,spname,spint,units,zj,bzsname from cpth_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", ""));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "cpno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", "10"));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "��Ʒ�˻�����XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("��Ʒ�˻�����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}
	
	
	

	//	�����豸������IE���ڷ�Ʊ����	
	public CommonBean JksbCi(CommonBean cbUser, String ywid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "�����豸��Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��jklj_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,po_item,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,ljno,spid,spname,spint,units,zj,bzsname from jksb_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", Function.trimString(cbXml.getCellStr(i, "po_item"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "ljno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", "11"));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "�����豸����XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("�����ϼ�����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//	һ��ó�ף�����IE���ڷ�Ʊ����	
	public CommonBean TradeCi(CommonBean cbUser, String ywid, String ywtype) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ci");
			XmlElement.addAttribute("description", "һ��ó�׷�Ʊ����");
			DataBaseObject dbo = getDataBaseObject();
			//�ӱ��ص�����ȡ�˵���
			String strB = "select bill_no,house_no,ywoperid,mxdno from bgd_jk where ywid='" + ywid + "'";
			CommonBean cbB = dbo.getData(strB);
			String strBill_no = Function.trimString(cbB.getCellStr(0, "house_no"));
			String strBsflag = Function.trimString(cbB.getCellStr(0, "ywoperid"));
			String strMxdno = Function.trimString(cbB.getCellStr(0, "mxdno"));

			//��trade_mx��ȡ��Ʊ���ݣ���˰����	

			String strSql =
				"select invoice_no,po_no,po_item,lc_no,bill_no,vendor,to_char(invoice_date,'yyyymmdd') as invoice_date,ljno,spid,spname,spint,units,zj,bzsname from trade_mx where ywid='"
					+ ywid
					+ "'";
			CommonBean cbXml = dbo.getData(strSql);

			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
				return cb;
			}

			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", strMxdno));
				eData.add(getElement("ci_no", cbXml.getCellStr(i, "invoice_no")));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("po_item", Function.trimString(cbXml.getCellStr(i, "po_item"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("bl_no", strBill_no));
				eData.add(getElement("vendor", Function.trimString(cbXml.getCellStr(i, "vendor"))));
				eData.add(getElement("cidate", Function.trimString(cbXml.getCellStr(i, "invoice_date"))));
				eData.add(getElement("ljno", Function.trimString(cbXml.getCellStr(i, "ljno"))));
				//eData.add(getElement("spid",  Function.trimString( cbXml.getCellStr(i, "spid"))));
				eData.add(getElement("sname", Function.trimString(cbXml.getCellStr(i, "spname"))));
				eData.add(getElement("quantity", Function.trimString(cbXml.getCellStr(i, "spint"))));
				eData.add(getElement("unit", Function.trimString(cbXml.getCellStr(i, "units"))));
				eData.add(getElement("amount", Function.trimString(cbXml.getCellStr(i, "zj"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "bzsname"))));
				//��˰��־
				eData.add(getElement("bsflag", strBsflag));
				//ҵ�����
				eData.add(getElement("flag", ywtype));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ci/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "һ��ó������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("һ��ó������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//	���ص����ݣ�����IE���ص�����	����˰��һ��ó�׹���һ����
	public CommonBean JkbgdIm(CommonBean cbUser, String ywid, String ywtype) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("im");
			XmlElement.addAttribute("description", "���ڱ��ص�");

			//��bgd_jk��ȡ��Ʊ����
			DataBaseObject dbo = getDataBaseObject();
			String strSql =
				"select a.bill_no,a.house_no,to_char(a.i_e_date,'yyyymmdd') as i_e_date,c.sname as trade_mode,a.mxdno,a.ywoperid from bgd_jk a,cl_trade c  where ywid='"
					+ ywid
					+ "' and a.trade_mode=c.id";
			CommonBean cbXml = dbo.getData(strSql);
			//ȡ���ص����ܽ������(2005.1.13ȥ��)
			//strSql = "select sum(zj) as zj from jklj_mx where ywid='" + ywid + "'";
			//CommonBean cbZj = dbo.getData(strSql);
			//String strZj = Function.trimString(cbZj.getCellStr(0, "zj"));
			//ȡ���ص���
			strSql = "select ywid,bgdh from bgd_zc_mx where ywid='" + ywid + "'";
			CommonBean cbbgd = dbo.getData(strSql);
			String strBgd = Function.trimString(cbbgd.getCellStr(0, "bgdh"));
			String invoice_no = null;
			//			if (cbXml == null || cbXml.getRowNum() <= 0) {
			//				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
			//				return cb;
			//			}

			Element eData = XmlElement.addElement("entry");
			//��ϸ����
			eData.add(getElement("mxdno", Function.trimString(cbXml.getCellStr(0, "mxdno"))));
			eData.add(getElement("ywoperid", Function.trimString(cbXml.getCellStr(0, "ywoperid"))));
			eData.add(getElement("dec_no", strBgd));
			eData.add(getElement("trade", Function.trimString(cbXml.getCellStr(0, "trade_mode"))));
			eData.add(getElement("bl_no", Function.trimString(cbXml.getCellStr(0, "bill_no"))));
			//eData.add(getElement("amount", strZj));
			eData.add(getElement("imdate", Function.trimString(cbXml.getCellStr(0, "i_e_date"))));
			//ҵ�����
			eData.add(getElement("flag", ywtype));
			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/im/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "�����ϼ����ɱ��ص�XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("�����ϼ����ص�����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//	���ڱ��ص����ݣ�����IE���ص�����	
	public CommonBean CpckEx(CommonBean cbUser, String ywid, String ywtype, String trade) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("ex");
			XmlElement.addAttribute("description", "���ڱ��ص�");

			//��bgd_jk��ȡ��Ʊ����
			String strSql = null;
			DataBaseObject dbo = getDataBaseObject();
			if (ywtype.equals("3") || ywtype.equals("6")) {
				strSql =
					"select a.bill_no,a.house_no,to_char(a.i_e_date,'yyyymmdd'),c.sname as trade_mode,a.appr_no,to_char(bgd_zc_mx.bgddate,'yyyymmdd') as sbdate,a.mxdno,a.ywoperid "
						+ "from bgd_jk a left join bgd_zc_mx on bgd_zc_mx.ywid=a.ywid,cl_trade c "
						+ "where a.ywid='"
						+ ywid
						+ "' and a.trade_mode=c.id";
			} else {
				strSql =
					"select a.bill_no,a.house_no,to_char(a.i_e_date,'yyyymmdd'),c.sname as trade_mode,a.appr_no,to_char(bgd_zc_mx.bgddate,'yyyymmdd') as sbdate,a.mxdno,a.ywoperid "
						+ "from bgd_ck a left join bgd_zc_mx on bgd_zc_mx.ywid=a.ywid,cl_trade c "
						+ "where a.ywid='"
						+ ywid
						+ "' and a.trade_mode=c.id";

			}

			CommonBean cbXml = dbo.getData(strSql);
			//ȡ���ص����ܽ������(2005.1.13ȥ��)
			//strSql = "select sum(zj) as zj from cpck_mx where ywid='" + ywid + "'";
			//CommonBean cbZj = dbo.getData(strSql);
			//String strZj = Function.trimString(cbZj.getCellStr(0, "zj"));

			//ȡ���ص���
			strSql = "select ywid,bgdh from bgd_zc_mx where ywid='" + ywid + "'";
			CommonBean cbbgd = dbo.getData(strSql);
			String strBgd = Function.trimString(cbbgd.getCellStr(0, "bgdh"));
			String invoice_no = null;
			//			if (cbXml == null || cbXml.getRowNum() <= 0) {
			//				cb.setCellObj(0, "info", "��ҵ�����޷�Ʊ���ݣ�");
			//				return cb;
			//			}

			//ȡ�������ţ�delivery_no��
			if (ywtype.equals("3")) {
				strSql = "select distinct bill_no as invoice_no from nx_mx where ywid='" + ywid + "'";

			} else {

				if (trade != null && trade.equals("1")) {
					strSql = "select distinct invoice_no from trade_mx where ywid='" + ywid + "'";
				} else {
					if (ywtype.equals("1")) {
					strSql = "select distinct invoice_no from cpck_mx where ywid='" + ywid + "'";
					}else if (ywtype.equals("2")){
						strSql = "select distinct invoice_no from sjgzc_mx where ywid='" + ywid + "'";
					}else if (ywtype.equals("4")){
						strSql = "select distinct invoice_no from ljth_mx where ywid='" + ywid + "'";
					}else if (ywtype.equals("5")){
						strSql = "select distinct invoice_no from cphh_mx where ywid='" + ywid + "'";
					}
				}
			}
			CommonBean cbBill = dbo.getData(strSql);
			String strInvoice_no = "";
			if (cbBill == null || cbBill.getRowNum() <= 0) {
				strInvoice_no = "";
			} else {
				for (int i = 0; i < cbBill.getRowNum(); i++) {
					strInvoice_no = strInvoice_no + Function.trimString(cbBill.getCellStr(i, "invoice_no")) + ",";
				}
				strInvoice_no = strInvoice_no.substring(0, strInvoice_no.length() - 1);
			}

			Element eData = XmlElement.addElement("entry");
			//��ϸ����
			eData.add(getElement("mxdno", Function.trimString(cbXml.getCellStr(0, "mxdno"))));
			eData.add(getElement("ywoperid", Function.trimString(cbXml.getCellStr(0, "ywoperid"))));
			eData.add(getElement("dec_no", strBgd));
			eData.add(getElement("trade", Function.trimString(cbXml.getCellStr(0, "trade_mode"))));
			eData.add(getElement("invoice_no", strInvoice_no));
			eData.add(getElement("bl_no", Function.trimString(cbXml.getCellStr(0, "bill_no"))));
			eData.add(getElement("whhx_no", Function.trimString(cbXml.getCellStr(0, "appr_no"))));
			//eData.add(getElement("amount", strZj));
			eData.add(getElement("exdate", Function.trimString(cbXml.getCellStr(0, "sbdate"))));
			//ҵ�����
			eData.add(getElement("flag", ywtype));

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/ex/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "��Ʒ�������ɱ��ص�XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("��Ʒ���ڱ��ص�����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}
	//���ã�����IE���ڷ�������	
	public CommonBean FyglBl(CommonBean cbUser, String bill_no) {
		String strPath = null;
		String strRoot = null;
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {
			DataBaseObject dbo = getDataBaseObject();
			//ȷ�������ڷ��ã�����ywidȷ��������
			String str = "select id from  bgd_jk where bill_no='" + bill_no + "'";
			CommonBean cbIE = dbo.getData(str);
			if (cbIE == null || cbIE.getRowNum() <= 0) {
				str = "select id from bgd_ck where bill_no='" + bill_no + "'";
				CommonBean cbCk = dbo.getData(str);
				if (cbCk == null || cbCk.getRowNum() <= 0) {
					cb.setCellObj(0, "info", "û�ж�Ӧ���˵���Ϣ�����飡");
					return cb;

				} else {
					strPath = pubDir + "/ebl/";
					strRoot = "ebl";
				}
			} else {
				strPath = pubDir + "/ibl/";
				strRoot = "ibl";
			}

			Document document = DocumentHelper.createDocument();

			Element XmlElement = document.addElement(strRoot);
			XmlElement.addAttribute("description", "��������");

			//��fygl��ȡ��Ʊ����

			String strSql = "select * from fygl where bill_no='" + bill_no + "'";
			CommonBean cbXml = dbo.getData(strSql);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "�޶�Ӧ���ݣ�");
				return cb;
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");

				eData.add(getElement("bl_no", Function.trimString(cbXml.getCellStr(i, "bill_no"))));
				eData.add(getElement("freight", Function.trimString(cbXml.getCellStr(i, "trans_rate"))));
				eData.add(getElement("insurance", Function.trimString(cbXml.getCellStr(i, "insur_rate"))));
				eData.add(getElement("dlf", Function.trimString(cbXml.getCellStr(i, "agent_rate"))));
				eData.add(getElement("zzf", Function.trimString(cbXml.getCellStr(i, "refer_rate"))));
				eData.add(getElement("xgf", Function.trimString(cbXml.getCellStr(i, "busi_rate"))));
				eData.add(getElement("ccf", Function.trimString(cbXml.getCellStr(i, "storage_rate"))));
				eData.add(getElement("ghczf", Function.trimString(cbXml.getCellStr(i, "thc_rate"))));
				eData.add(getElement("others", Function.trimString(cbXml.getCellStr(i, "other_rate"))));
				eData.add(getElement("customs", Function.trimString(cbXml.getCellStr(i, "custom_rate"))));
				eData.add(getElement("curr", Function.trimString(cbXml.getCellStr(i, "curr"))));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(strPath + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "��������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("��������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//	BL��Ϣ������IE BL����	
	public CommonBean Blinfo(CommonBean cbUser, String sID) {
		String strPath = null;
		String strRoot = null;
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();

			Element XmlElement = document.addElement("bl");
			XmlElement.addAttribute("description", "BL��Ϣ����");

			//��bl��ȡ����
			DataBaseObject dbo = getDataBaseObject();
			String strSql = "select * from bl_info where id='" + sID + "'";
			CommonBean cbXml = dbo.getData(strSql);
			strSql =
				"select to_char(a.etd_date,'yyyymmdd') as etd_date,to_char(a.eta_date,'yyyymmdd') as eta_date,to_char(a.bl_date,'yyyymmdd') as bl_date,to_char(a.req_date,'yyyymmdd') as req_date,to_char(a.apply_date,'yyyymmdd') as apply_date,to_char(a.ip_date,'yyyymmdd') as ip_date,to_char(a.tax_date,'yyyymmdd') as tax_date from bl_info a where id='"
					+ sID
					+ "'";
			CommonBean cbDate = dbo.getData(strSql);
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "�޶�Ӧ���ݣ�");
				return cb;
			}
			//��forwardת��������
			String strForward="";
			if (cbXml.getValue("forwarder")==null || cbXml.getValue("forwarder").equals("")){
				strForward="";
			}else{
				String strF="select sname from cl_forwarder where id='" +cbXml.getValue("forwarder")+"'";
				CommonBean cbF=dbo.getData(strF);
				strForward=cbF.getValue("sname");
			}
			for (int i = 0; i < cbXml.getRowNum(); i++) {
				Element eData = XmlElement.addElement("entry");
				eData.add(getElement("mxdno", Function.trimString(cbXml.getCellStr(i, "mxdno"))));
				eData.add(getElement("bl_no", Function.trimString(cbXml.getCellStr(i, "bl_no"))));
				eData.add(getElement("offer_no", Function.trimString(cbXml.getCellStr(i, "offer_no"))));
				eData.add(getElement("po_no", Function.trimString(cbXml.getCellStr(i, "po_no"))));
				eData.add(getElement("house_no", Function.trimString(cbXml.getCellStr(i, "house_no"))));
				eData.add(getElement("lc_no", Function.trimString(cbXml.getCellStr(i, "lc_no"))));
				eData.add(getElement("pre_material", Function.trimString(cbXml.getCellStr(i, "pre_material"))));
				eData.add(getElement("invoice_amt", Function.trimString(cbXml.getCellStr(i, "invoice_amt"))));
				eData.add(getElement("material_amt", Function.trimString(cbXml.getCellStr(i, "material_amt"))));
				eData.add(getElement("ship_from", Function.trimString(cbXml.getCellStr(i, "ship_from"))));
				eData.add(getElement("ship_point", Function.trimString(cbXml.getCellStr(i, "ship_point"))));
				eData.add(getElement("aof", Function.trimString(cbXml.getCellStr(i, "aof"))));
				eData.add(getElement("etd", Function.trimString(cbDate.getCellStr(i, "etd_date"))));
				eData.add(getElement("eta", Function.trimString(cbDate.getCellStr(i, "eta_date"))));
				eData.add(getElement("bl_date", Function.trimString(cbDate.getCellStr(i, "bl_date"))));
				eData.add(getElement("forwarder", strForward));
				eData.add(getElement("vessel_name", Function.trimString(cbXml.getCellStr(i, "vessel_name"))));
				eData.add(getElement("gross_weight", Function.trimString(cbXml.getCellStr(i, "gross_weight"))));
				eData.add(getElement("chag_weight", Function.trimString(cbXml.getCellStr(i, "chag_weight"))));
				eData.add(getElement("weight_unit", Function.trimString(cbXml.getCellStr(i, "weight_unit"))));
				eData.add(getElement("carton", Function.trimString(cbXml.getCellStr(i, "carton"))));
				eData.add(getElement("carton_unit", Function.trimString(cbXml.getCellStr(i, "carton_unit"))));
				eData.add(getElement("carrier", Function.trimString(cbXml.getCellStr(i, "carrier"))));
				eData.add(getElement("req_date", Function.trimString(cbDate.getCellStr(i, "req_date"))));
				eData.add(getElement("apply_date", Function.trimString(cbDate.getCellStr(i, "apply_date"))));
				eData.add(getElement("ip_no", Function.trimString(cbXml.getCellStr(i, "ip_no"))));
				eData.add(getElement("ip_date", Function.trimString(cbDate.getCellStr(i, "ip_date"))));
				eData.add(getElement("tax_date", Function.trimString(cbDate.getCellStr(i, "tax_date"))));
				eData.add(getElement("total_amt", Function.trimString(cbXml.getCellStr(i, "total_amt"))));
				eData.add(getElement("amt_unit", Function.trimString(cbXml.getCellStr(i, "amt_unit"))));
				eData.add(getElement("other1", Function.trimString(cbXml.getCellStr(i, "oter1"))));
				eData.add(getElement("other2", Function.trimString(cbXml.getCellStr(i, "oter2"))));
				eData.add(getElement("ship_to", Function.trimString(cbXml.getCellStr(i, "ship_to"))));
				eData.add(getElement("ship_to_point", Function.trimString(cbXml.getCellStr(i, "ship_to_point"))));

			}

			// document.addDocType("catalog",
			//					   null,"file://c:/Dtds/catalog.dtd");

			//�ļ���
			Date dTime = new Date();
			String strFileName = "" + dTime.getTime() + ".xml";
			//String strFileName=TimeTool.getTimeTool.getCurTime();
			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + "/bl/" + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݴ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "BL��Ϣ����XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("BL��Ϣ����XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݴ���ʧ�ܣ�");
		}
		return cb;

	}

	//��֯ElementԪ��
	public Element getElement(String Ename, String Etext) {
		Element e = DocumentHelper.createElement(Ename);
		e.setText(Etext);
		return e;
	}

}
