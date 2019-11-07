package com.aiyun.common.exchgdata.bgddr.bean;

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

/**
 * @author Administrator
 *
 */
public class DaoRuBean extends DBUtil {

	private static String pubDir = "e:/data/bgdfiles/";

	//
	public CommonBean selectforward(CommonBean cbUser, String ywid) {

		String i_e = cbUser.getValue("i_e");
		String typeid = cbUser.getValue("typeid");
		String bsflag = cbUser.getValue("bsflag");
		CommonBean cb = new CommonBean();
		if (i_e.equals("0") && bsflag.equals("0")) {
			cb = jk(ywid, typeid);
		} else if (i_e.equals("1") && bsflag.equals("0")) {
			cb = ck(ywid, typeid);
		} else if (i_e.equals("0") && bsflag.equals("1")) {
			cb = tradeJk(ywid, typeid);
		} else if (i_e.equals("1") && bsflag.equals("1")) {
			cb = tradeCk(ywid, typeid);
		}
		return cb;
	}

	public CommonBean jk(String ywid, String typeid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("DEC");
			XmlElement.addAttribute("xmlns", "Eport");
			DataBaseObject dbo = getDataBaseObject();
			String sqlHead = "select bgd_jk.*,to_char(i_e_date,'yyyymmdd')as iedate  from bgd_jk where ywid='" + ywid
					+ "'";

			CommonBean cbXml = dbo.getData(sqlHead);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����ޱ��ص���ͷ���ݿɵ��룡");
				return cb;
			}
			// dec_headԪ�أ����ص�ͷ��
			Element eData = XmlElement.addElement("DEC_HEAD");

			eData.add(getElement("I_E_FLAG", Function.trimString(cbXml.getCellStr(0, "I_E_FLAG"))));
			eData.add(getElement("CUSTOM_MASTER", Function.trimString(cbXml.getCellStr(0, "CUSTOM_MASTER"))));
			eData.add(getElement("I_E_PORT", Function.trimString(cbXml.getCellStr(0, "I_E_PORT"))));
			eData.add(getElement("MANUAL_NO", Function.trimString(cbXml.getCellStr(0, "ZCH"))));
			eData.add(getElement("CONTR_NO", Function.trimString(cbXml.getCellStr(0, "CONTR_NO"))));
			eData.add(getElement("I_E_DATE", Function.trimString(cbXml.getCellStr(0, "iedate"))));
			eData.add(getElement("TRADE_CO", Function.trimString(cbXml.getCellStr(0, "TRADE_CODE"))));
			eData.add(getElement("TRADE_NAME", Function.trimString(cbXml.getCellStr(0, "TRADE_NAME"))));
			eData.add(getElement("OWNER_CODE", Function.trimString(cbXml.getCellStr(0, "OWNER_CODE"))));
			eData.add(getElement("OWNER_NAME", Function.trimString(cbXml.getCellStr(0, "OWNER_NAME"))));
			eData.add(getElement("AGENT_CODE", Function.trimString(cbXml.getCellStr(0, "AGENT_CODE"))));
			eData.add(getElement("AGENT_NAME", Function.trimString(cbXml.getCellStr(0, "AGENT_NAME"))));
			eData.add(getElement("TRAF_MODE", Function.trimString(cbXml.getCellStr(0, "TRAF_MODE"))));
			eData.add(getElement("TRAF_NAME", Function.trimString(cbXml.getCellStr(0, "TRAF_NAME"))));
			eData.add(getElement("VOYNO", Function.trimString(cbXml.getCellStr(0, "VOYNO"))));
			eData.add(getElement("BILL_NO", Function.trimString(cbXml.getCellStr(0, "BILL_NO"))));
			eData.add(getElement("TRADE_MODE", Function.trimString(cbXml.getCellStr(0, "TRADE_MODE"))));
			eData.add(getElement("CUT_MODE", Function.trimString(cbXml.getCellStr(0, "CUT_MODE"))));
			eData.add(getElement("PAYMENT_MARK", Function.trimString(cbXml.getCellStr(0, "PAYMENT_MARK"))));
			eData.add(getElement("LICENSE_NO", Function.trimString(cbXml.getCellStr(0, "LICENSE_NO"))));
			eData.add(getElement("TRADE_COUNTRY", Function.trimString(cbXml.getCellStr(0, "TRADE_COUNTRY"))));
			eData.add(getElement("DISTINATE_PORT", Function.trimString(cbXml.getCellStr(0, "DISTINATE_PORT"))));
			eData.add(getElement("DISTRICT_CODE", Function.trimString(cbXml.getCellStr(0, "DISTRICT_CODE"))));
			eData.add(getElement("APPR_NO", Function.trimString(cbXml.getCellStr(0, "APPR_NO"))));
			eData.add(getElement("TRANS_MODE", Function.trimString(cbXml.getCellStr(0, "TRANS_MODE"))));
			eData.add(getElement("CO_OWNER", Function.trimString(cbXml.getCellStr(0, "CO_OWNER"))));
			eData.add(getElement("FEE_MARK", Function.trimString(cbXml.getCellStr(0, "FEE_MARK"))));
			eData.add(getElement("FEE_RATE", Function.trimString(cbXml.getCellStr(0, "FEE_RATE"))));
			eData.add(getElement("FEE_CURR", Function.trimString(cbXml.getCellStr(0, "FEE_CURR"))));
			eData.add(getElement("INSUR_MARK", Function.trimString(cbXml.getCellStr(0, "INSUR_MARK"))));
			eData.add(getElement("INSUR_RATE", Function.trimString(cbXml.getCellStr(0, "INSUR_RATE"))));
			eData.add(getElement("INSUR_CURR", Function.trimString(cbXml.getCellStr(0, "INSUR_CURR"))));
			eData.add(getElement("OTHER_MARK", Function.trimString(cbXml.getCellStr(0, "OTHER_MARK"))));
			eData.add(getElement("OTHER_RATE", Function.trimString(cbXml.getCellStr(0, "OTHER_RATE"))));
			eData.add(getElement("OTHER_CURR", Function.trimString(cbXml.getCellStr(0, "OTHER_CURR"))));
			eData.add(getElement("PACK_NO", Function.trimString(cbXml.getCellStr(0, "PACK_NO"))));
			eData.add(getElement("WRAP_TYPE", Function.trimString(cbXml.getCellStr(0, "WRAP_TYPE"))));
			eData.add(getElement("GROSS_WT", Function.trimString(cbXml.getCellStr(0, "GROSS_WT"))));
			eData.add(getElement("NET_WT", Function.trimString(cbXml.getCellStr(0, "NET_WT"))));
			eData.add(getElement("ENTRY_TYPE", Function.trimString(cbXml.getCellStr(0, "ENTRY_TYPE"))));
			eData.add(getElement("NOTE_S", Function.trimString(cbXml.getCellStr(0, "MEMO"))));

			// free_textsԪ�أ������ı���
			Element eTexts = XmlElement.addElement("FREE_TEXTS");

			eTexts.add(getElement("RELID", Function.trimString(cbXml.getCellStr(0, "RELID"))));
			eTexts.add(getElement("RELMANNO", Function.trimString(cbXml.getCellStr(0, "RELMANNO"))));
			eTexts.add(getElement("BONNO", Function.trimString(cbXml.getCellStr(0, "BONNO"))));
			eTexts.add(getElement("CUSFIE", Function.trimString(cbXml.getCellStr(0, "CUSFIE"))));
			eTexts.add(getElement("DECNO", Function.trimString(cbXml.getCellStr(0, "DECNO"))));
			eTexts.add(getElement("DECBPNO", Function.trimString(cbXml.getCellStr(0, "DECBPNO"))));

			// dec_listԪ�أ����ص��壩
			String table = null;
			int type = Integer.parseInt(typeid);
			switch (type) {
			case 7:
				table = "jklj_gb";
				break;
			case 10:
				table = "sjgzr_gb";
				break;
			case 15:
				table = "ljhh_gb";
				break;
			case 16:
				table = "cpth_gb";
				break;
			case 9:
				table = "jksb_gb";
				break;
			case 13:
				table = "fqxh_gb";
				break;
			case 12:
				table = "nx_gb";
				break;
			case 21:
				table = "ljnx_gb";
				break;

			}

			String sqlList = "select * from " + table + " where rownum<=20 and ywid=" + ywid;
			CommonBean cbXmlList = dbo.getData(sqlList);
			if (cbXmlList == null || cbXmlList.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����ޱ��ص������ݿɵ��룡");
				return cb;
			}
			for (int i = 0, t = 1; i < cbXmlList.getRowNum(); i++, t++) {
				String tt = Integer.toString(t);
				String code_t = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(0, 7);
				String code_s = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(8);

				Element eList = XmlElement.addElement("DEC_LIST");
				eList.add(getElement("G_NO", tt));
				eList.add(getElement("CONTR_ITEM", Function.trimString(cbXmlList.getCellStr(i, "LJID"))));
				eList.add(getElement("CODE_T", code_t));
				eList.add(getElement("CODE_S", code_s));
				eList.add(getElement("G_NAME", Function.trimString(cbXmlList.getCellStr(i, "spname"))));
				eList.add(getElement("G_MODEL", ""));
				eList.add(getElement("QTY_1", Function.trimString(cbXmlList.getCellStr(i, "spint"))));
				eList.add(getElement("UNIT_1", Function.trimString(cbXmlList.getCellStr(i, "units"))));
				eList.add(getElement("DECL_PRICE", Function.trimString(cbXmlList.getCellStr(i, "avgjg"))));
				eList.add(getElement("DECL_TOTAL", Function.trimString(cbXmlList.getCellStr(i, "zj"))));
				eList.add(getElement("TRADE_CURR", Function.trimString(cbXmlList.getCellStr(i, "bzsname"))));
				eList.add(getElement("G_QTY", Function.trimString(cbXmlList.getCellStr(i, "spint1"))));
				eList.add(getElement("G_UNIT", Function.trimString(cbXmlList.getCellStr(i, "unit_1"))));
				eList.add(getElement("EXG_VERSION", ""));
				eList.add(getElement("EXG_NO", ""));
				eList.add(getElement("USE_TO", ""));
				eList.add(getElement("QTY_2", Function.trimString(cbXmlList.getCellStr(i, "spint2"))));
				eList.add(getElement("UNIT_2", Function.trimString(cbXmlList.getCellStr(i, "unit_2"))));
				eList.add(getElement("ORIGIN_COUNTRY", Function.trimString(cbXmlList.getCellStr(i, "sgov"))));
				eList.add(getElement("DUTY_MODE", "3"));
				eList.add(getElement("WORK_USD", ""));
			}

			// DEC_CONTAINERԪ�أ���װ�䣩
			String sqlContainer = "select * from bgd_container where ywid=" + ywid;
			CommonBean cbXmlCon = dbo.getData(sqlContainer);

			if (cbXmlCon.getRowNum() > 0) {

				for (int i = 0; i < cbXmlCon.getRowNum(); i++) {
					Element eContainer = XmlElement.addElement("DEC_CONTAINER");
					eContainer.add(
							getElement("CONTAINER_ID", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_ID"))));
					eContainer.add(
							getElement("CONTAINER_MD", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_MD"))));
					eContainer.add(
							getElement("CONTAINER_WT", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_WT"))));
				}
			}

			// DEC_LICENSEDOCUԪ�أ��渽����
			String sqlLicensedocu = "select * from bgd_licensedocu where ywid=" + ywid;
			CommonBean cbXmlLicen = dbo.getData(sqlLicensedocu);
			if (cbXmlLicen.getRowNum() > 0) {

				for (int i = 0; i < cbXmlLicen.getRowNum(); i++) {
					Element eLicensedocu = XmlElement.addElement("DEC_LICENSEDOCU");
					eLicensedocu
							.add(getElement("DOCU_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "DOCU_CODE"))));
					eLicensedocu
							.add(getElement("CERT_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "CERT_CODE"))));

				}
			}
			// �ļ���
			Date dTime = new Date();
			String strFileName = ywid + ".xml";

			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݵ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݵ���ʧ�ܣ�");
		}
		return cb;
	}

	public CommonBean ck(String ywid, String typeid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("DEC");
			XmlElement.addAttribute("xmlns", "Eport");
			DataBaseObject dbo = getDataBaseObject();
			String sqlHead = "select bgd_ck.*,to_char(i_e_date,'yyyymmdd') as iedate  from bgd_ck where ywid='" + ywid
					+ "'";

			CommonBean cbXml = dbo.getData(sqlHead);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�����ޱ��ص���ͷ���ݿɵ��룡");
				return cb;
			}
			Element eData = XmlElement.addElement("DEC_HEAD");

			eData.add(getElement("I_E_FLAG", Function.trimString(cbXml.getCellStr(0, "I_E_FLAG"))));
			eData.add(getElement("CUSTOM_MASTER", Function.trimString(cbXml.getCellStr(0, "CUSTOM_MASTER"))));
			eData.add(getElement("I_E_PORT", Function.trimString(cbXml.getCellStr(0, "I_E_PORT"))));
			eData.add(getElement("MANUAL_NO", Function.trimString(cbXml.getCellStr(0, "ZCH"))));
			eData.add(getElement("CONTR_NO", Function.trimString(cbXml.getCellStr(0, "CONTR_NO"))));
			eData.add(getElement("I_E_DATE", Function.trimString(cbXml.getCellStr(0, "iedate"))));
			eData.add(getElement("TRADE_CO", Function.trimString(cbXml.getCellStr(0, "TRADE_CODE"))));
			eData.add(getElement("TRADE_NAME", Function.trimString(cbXml.getCellStr(0, "TRADE_NAME"))));
			eData.add(getElement("OWNER_CODE", Function.trimString(cbXml.getCellStr(0, "OWNER_CODE"))));
			eData.add(getElement("OWNER_NAME", Function.trimString(cbXml.getCellStr(0, "OWNER_NAME"))));
			eData.add(getElement("AGENT_CODE", Function.trimString(cbXml.getCellStr(0, "AGENT_CODE"))));
			eData.add(getElement("AGENT_NAME", Function.trimString(cbXml.getCellStr(0, "AGENT_NAME"))));
			eData.add(getElement("TRAF_MODE", Function.trimString(cbXml.getCellStr(0, "TRAF_MODE"))));
			eData.add(getElement("TRAF_NAME", Function.trimString(cbXml.getCellStr(0, "TRAF_NAME"))));
			eData.add(getElement("VOYNO", Function.trimString(cbXml.getCellStr(0, "VOYNO"))));
			eData.add(getElement("BILL_NO", Function.trimString(cbXml.getCellStr(0, "BILL_NO"))));
			eData.add(getElement("TRADE_MODE", Function.trimString(cbXml.getCellStr(0, "TRADE_MODE"))));
			eData.add(getElement("CUT_MODE", Function.trimString(cbXml.getCellStr(0, "CUT_MODE"))));
			eData.add(getElement("PAYMENT_MARK", Function.trimString(cbXml.getCellStr(0, "PAYMENT_MARK"))));
			eData.add(getElement("LICENSE_NO", Function.trimString(cbXml.getCellStr(0, "LICENSE_NO"))));
			eData.add(getElement("TRADE_COUNTRY", Function.trimString(cbXml.getCellStr(0, "TRADE_COUNTRY"))));
			eData.add(getElement("DISTINATE_PORT", Function.trimString(cbXml.getCellStr(0, "DISTINATE_PORT"))));
			eData.add(getElement("DISTRICT_CODE", Function.trimString(cbXml.getCellStr(0, "DISTRICT_CODE"))));
			eData.add(getElement("APPR_NO", Function.trimString(cbXml.getCellStr(0, "APPR_NO"))));
			eData.add(getElement("TRANS_MODE", Function.trimString(cbXml.getCellStr(0, "TRANS_MODE"))));
			eData.add(getElement("CO_OWNER", Function.trimString(cbXml.getCellStr(0, "CO_OWNER"))));
			eData.add(getElement("FEE_MARK", Function.trimString(cbXml.getCellStr(0, "FEE_MARK"))));
			eData.add(getElement("FEE_RATE", Function.trimString(cbXml.getCellStr(0, "FEE_RATE"))));
			eData.add(getElement("FEE_CURR", Function.trimString(cbXml.getCellStr(0, "FEE_CURR"))));
			eData.add(getElement("INSUR_MARK", Function.trimString(cbXml.getCellStr(0, "INSUR_MARK"))));
			eData.add(getElement("INSUR_RATE", Function.trimString(cbXml.getCellStr(0, "INSUR_RATE"))));
			eData.add(getElement("INSUR_CURR", Function.trimString(cbXml.getCellStr(0, "INSUR_CURR"))));
			eData.add(getElement("OTHER_MARK", Function.trimString(cbXml.getCellStr(0, "OTHER_MARK"))));
			eData.add(getElement("OTHER_RATE", Function.trimString(cbXml.getCellStr(0, "OTHER_RATE"))));
			eData.add(getElement("OTHER_CURR", Function.trimString(cbXml.getCellStr(0, "OTHER_CURR"))));
			eData.add(getElement("PACK_NO", Function.trimString(cbXml.getCellStr(0, "PACK_NO"))));
			eData.add(getElement("WRAP_TYPE", Function.trimString(cbXml.getCellStr(0, "WRAP_TYPE"))));
			eData.add(getElement("GROSS_WT", Function.trimString(cbXml.getCellStr(0, "GROSS_WT"))));
			eData.add(getElement("NET_WT", Function.trimString(cbXml.getCellStr(0, "NET_WT"))));
			eData.add(getElement("ENTRY_TYPE", Function.trimString(cbXml.getCellStr(0, "ENTRY_TYPE"))));
			eData.add(getElement("NOTE_S", Function.trimString(cbXml.getCellStr(0, "MEMO"))));

			Element eTexts = XmlElement.addElement("FREE_TEXTS");

			eTexts.add(getElement("RELID", Function.trimString(cbXml.getCellStr(0, "RELID"))));
			eTexts.add(getElement("RELMANNO", Function.trimString(cbXml.getCellStr(0, "RELMANNO"))));
			eTexts.add(getElement("BONNO", Function.trimString(cbXml.getCellStr(0, "BONNO"))));
			eTexts.add(getElement("CUSFIE", Function.trimString(cbXml.getCellStr(0, "CUSFIE"))));
			eTexts.add(getElement("DECNO", Function.trimString(cbXml.getCellStr(0, "DECNO"))));
			eTexts.add(getElement("DECBPNO", Function.trimString(cbXml.getCellStr(0, "DECBPNO"))));

			String table = null;
			int type = Integer.parseInt(typeid);
			switch (type) {
			case 8:
				table = "cpck_gb";
				break;
			case 11:
				table = "sjgzc_gb";
				break;
			case 14:
				table = "ljth_gb";
				break;
			case 17:
				table = "cphh_gb";
				break;

			}

			String sqlList = "select * from " + table + " where rownum<=20 and ywid=" + ywid;
			CommonBean cbXmlList = dbo.getData(sqlList);
			for (int i = 0, t = 1; i < cbXmlList.getRowNum(); i++, t++) {
				String tt = Integer.toString(t);
				String code_t = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(0, 7);
				String code_s = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(8);

				Element eList = XmlElement.addElement("DEC_LIST");
				eList.add(getElement("G_NO", tt));
				eList.add(getElement("CONTR_ITEM", Function.trimString(cbXmlList.getCellStr(i, "CPID"))));
				eList.add(getElement("CODE_T", code_t));
				eList.add(getElement("CODE_S", code_s));
				eList.add(getElement("G_NAME", Function.trimString(cbXmlList.getCellStr(i, "spname"))));
				eList.add(getElement("G_MODEL", ""));
				eList.add(getElement("QTY_1", Function.trimString(cbXmlList.getCellStr(i, "spint"))));
				eList.add(getElement("UNIT_1", Function.trimString(cbXmlList.getCellStr(i, "units"))));
				eList.add(getElement("DECL_PRICE", Function.trimString(cbXmlList.getCellStr(i, "avgjg"))));
				eList.add(getElement("DECL_TOTAL", Function.trimString(cbXmlList.getCellStr(i, "zj"))));
				eList.add(getElement("TRADE_CURR", Function.trimString(cbXmlList.getCellStr(i, "bzsname"))));
				eList.add(getElement("G_QTY", Function.trimString(cbXmlList.getCellStr(i, "spint1"))));
				eList.add(getElement("G_UNIT", Function.trimString(cbXmlList.getCellStr(i, "unit_1"))));
				eList.add(getElement("EXG_VERSION", ""));
				eList.add(getElement("EXG_NO", ""));
				eList.add(getElement("USE_TO", ""));
				eList.add(getElement("QTY_2", Function.trimString(cbXmlList.getCellStr(i, "spint2"))));
				eList.add(getElement("UNIT_2", Function.trimString(cbXmlList.getCellStr(i, "unit_2"))));
				eList.add(getElement("ORIGIN_COUNTRY", Function.trimString(cbXmlList.getCellStr(i, "sgov"))));
				eList.add(getElement("DUTY_MODE", "3"));
				eList.add(getElement("WORK_USD", ""));
			}

			String sqlContainer = "select * from bgd_container where ywid=" + ywid;
			CommonBean cbXmlCon = dbo.getData(sqlContainer);
			if (cbXmlCon.getRowNum() > 0) {
				for (int i = 0; i < cbXmlCon.getRowNum(); i++) {
					Element eContainer = XmlElement.addElement("DEC_CONTAINER");
					eContainer.add(
							getElement("CONTAINER_ID", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_ID"))));
					eContainer.add(
							getElement("CONTAINER_MD", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_MD"))));
					eContainer.add(
							getElement("CONTAINER_WT", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_WT"))));
				}
			}

			String sqlLicensedocu = "select * from bgd_licensedocu where ywid=" + ywid;
			CommonBean cbXmlLicen = dbo.getData(sqlLicensedocu);
			if (cbXmlLicen.getRowNum() > 0) {
				for (int i = 0; i < cbXmlLicen.getRowNum(); i++) {
					Element eLicensedocu = XmlElement.addElement("DEC_LICENSEDOCU");
					eLicensedocu
							.add(getElement("DOCU_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "DOCU_CODE"))));
					eLicensedocu
							.add(getElement("CERT_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "CERT_CODE"))));

				}
			}
			Date dTime = new Date();
			String strFileName = ywid + ".xml";

			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݵ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݵ���ʧ�ܣ�");
		}
		return cb;
	}

	// һ��ó�׽���ҵ�񱨹ص�����\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public CommonBean tradeJk(String ywid, String typeid) {
		CommonBean cb = new CommonBean();
		cb.addValue("info", "");
		try {

			Document document = DocumentHelper.createDocument();
			Element XmlElement = document.addElement("DEC");
			XmlElement.addAttribute("xmlns", "Eport");
			DataBaseObject dbo = getDataBaseObject();
			// �ӽ��ڱ��ص�����ȡ���ص���ͷ
			String sqlHead = "select bgd_jk.*,to_char(i_e_date,'yyyymmdd')as iedate  from bgd_jk where ywid='" + ywid
					+ "'";

			CommonBean cbXml = dbo.getData(sqlHead);
			String invoice_no = null;
			if (cbXml == null || cbXml.getRowNum() <= 0) {
				cb.setCellObj(0, "info", "��ҵ�������ݿɵ��룡");
				return cb;
			}
			// dec_headԪ�أ����ص�ͷ��
			Element eData = XmlElement.addElement("DEC_HEAD");

			eData.add(getElement("I_E_FLAG", Function.trimString(cbXml.getCellStr(0, "I_E_FLAG"))));
			eData.add(getElement("CUSTOM_MASTER", Function.trimString(cbXml.getCellStr(0, "CUSTOM_MASTER"))));
			eData.add(getElement("I_E_PORT", Function.trimString(cbXml.getCellStr(0, "I_E_PORT"))));
			eData.add(getElement("MANUAL_NO", Function.trimString(cbXml.getCellStr(0, "ZCH"))));
			eData.add(getElement("CONTR_NO", Function.trimString(cbXml.getCellStr(0, "CONTR_NO"))));
			eData.add(getElement("I_E_DATE", Function.trimString(cbXml.getCellStr(0, "iedate"))));
			eData.add(getElement("TRADE_CO", Function.trimString(cbXml.getCellStr(0, "TRADE_CODE"))));
			eData.add(getElement("TRADE_NAME", Function.trimString(cbXml.getCellStr(0, "TRADE_NAME"))));
			eData.add(getElement("OWNER_CODE", Function.trimString(cbXml.getCellStr(0, "OWNER_CODE"))));
			eData.add(getElement("OWNER_NAME", Function.trimString(cbXml.getCellStr(0, "OWNER_NAME"))));
			eData.add(getElement("AGENT_CODE", Function.trimString(cbXml.getCellStr(0, "AGENT_CODE"))));
			eData.add(getElement("AGENT_NAME", Function.trimString(cbXml.getCellStr(0, "AGENT_NAME"))));
			eData.add(getElement("TRAF_MODE", Function.trimString(cbXml.getCellStr(0, "TRAF_MODE"))));
			eData.add(getElement("TRAF_NAME", Function.trimString(cbXml.getCellStr(0, "TRAF_NAME"))));
			eData.add(getElement("VOYNO", Function.trimString(cbXml.getCellStr(0, "VOYNO"))));
			eData.add(getElement("BILL_NO", Function.trimString(cbXml.getCellStr(0, "BILL_NO"))));
			eData.add(getElement("TRADE_MODE", Function.trimString(cbXml.getCellStr(0, "TRADE_MODE"))));
			eData.add(getElement("CUT_MODE", Function.trimString(cbXml.getCellStr(0, "CUT_MODE"))));
			eData.add(getElement("PAYMENT_MARK", Function.trimString(cbXml.getCellStr(0, "PAYMENT_MARK"))));
			eData.add(getElement("LICENSE_NO", Function.trimString(cbXml.getCellStr(0, "LICENSE_NO"))));
			eData.add(getElement("TRADE_COUNTRY", Function.trimString(cbXml.getCellStr(0, "TRADE_COUNTRY"))));
			eData.add(getElement("DISTINATE_PORT", Function.trimString(cbXml.getCellStr(0, "DISTINATE_PORT"))));
			eData.add(getElement("DISTRICT_CODE", Function.trimString(cbXml.getCellStr(0, "DISTRICT_CODE"))));
			eData.add(getElement("APPR_NO", Function.trimString(cbXml.getCellStr(0, "APPR_NO"))));
			eData.add(getElement("TRANS_MODE", Function.trimString(cbXml.getCellStr(0, "TRANS_MODE"))));
			eData.add(getElement("CO_OWNER", Function.trimString(cbXml.getCellStr(0, "CO_OWNER"))));
			eData.add(getElement("FEE_MARK", Function.trimString(cbXml.getCellStr(0, "FEE_MARK"))));
			eData.add(getElement("FEE_RATE", Function.trimString(cbXml.getCellStr(0, "FEE_RATE"))));
			eData.add(getElement("FEE_CURR", Function.trimString(cbXml.getCellStr(0, "FEE_CURR"))));
			eData.add(getElement("INSUR_MARK", Function.trimString(cbXml.getCellStr(0, "INSUR_MARK"))));
			eData.add(getElement("INSUR_RATE", Function.trimString(cbXml.getCellStr(0, "INSUR_RATE"))));
			eData.add(getElement("INSUR_CURR", Function.trimString(cbXml.getCellStr(0, "INSUR_CURR"))));
			eData.add(getElement("OTHER_MARK", Function.trimString(cbXml.getCellStr(0, "OTHER_MARK"))));
			eData.add(getElement("OTHER_RATE", Function.trimString(cbXml.getCellStr(0, "OTHER_RATE"))));
			eData.add(getElement("OTHER_CURR", Function.trimString(cbXml.getCellStr(0, "OTHER_CURR"))));
			eData.add(getElement("PACK_NO", Function.trimString(cbXml.getCellStr(0, "PACK_NO"))));
			eData.add(getElement("WRAP_TYPE", Function.trimString(cbXml.getCellStr(0, "WRAP_TYPE"))));
			eData.add(getElement("GROSS_WT", Function.trimString(cbXml.getCellStr(0, "GROSS_WT"))));
			eData.add(getElement("NET_WT", Function.trimString(cbXml.getCellStr(0, "NET_WT"))));
			eData.add(getElement("ENTRY_TYPE", Function.trimString(cbXml.getCellStr(0, "ENTRY_TYPE"))));
			eData.add(getElement("NOTE_S", Function.trimString(cbXml.getCellStr(0, "MEMO"))));

			// free_textsԪ�أ������ı���
			Element eTexts = XmlElement.addElement("FREE_TEXTS");

			eTexts.add(getElement("RELID", Function.trimString(cbXml.getCellStr(0, "RELID"))));
			eTexts.add(getElement("RELMANNO", Function.trimString(cbXml.getCellStr(0, "RELMANNO"))));
			eTexts.add(getElement("BONNO", Function.trimString(cbXml.getCellStr(0, "BONNO"))));
			eTexts.add(getElement("CUSFIE", Function.trimString(cbXml.getCellStr(0, "CUSFIE"))));
			eTexts.add(getElement("DECNO", Function.trimString(cbXml.getCellStr(0, "DECNO"))));
			eTexts.add(getElement("DECBPNO", Function.trimString(cbXml.getCellStr(0, "DECBPNO"))));

			// dec_listԪ�أ����ص��壩
			String table = null;
			int type = Integer.parseInt(typeid);

			String sqlList = "select * from trade_gb where rownum<=20 and ywid=" + ywid;
			CommonBean cbXmlList = dbo.getData(sqlList);
			for (int i = 0, t = 1; i < cbXmlList.getRowNum(); i++, t++) {
				String tt = Integer.toString(t);
				String code_t = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(0, 7);
				String code_s = Function.trimString(cbXmlList.getCellStr(i, "SPID")).substring(8);

				Element eList = XmlElement.addElement("DEC_LIST");
				eList.add(getElement("G_NO", tt));
				// eList.add(getElement("CONTR_ITEM",
				// Function.trimString(cbXmlList.getCellStr(i, "LJID"))));
				eList.add(getElement("CODE_T", code_t));
				eList.add(getElement("CODE_S", code_s));
				eList.add(getElement("G_NAME", Function.trimString(cbXmlList.getCellStr(i, "spname"))));
				eList.add(getElement("G_MODEL", ""));
				eList.add(getElement("QTY_1", Function.trimString(cbXmlList.getCellStr(i, "spint"))));
				eList.add(getElement("UNIT_1", Function.trimString(cbXmlList.getCellStr(i, "units"))));
				eList.add(getElement("DECL_PRICE", Function.trimString(cbXmlList.getCellStr(i, "avgjg"))));
				eList.add(getElement("DECL_TOTAL", Function.trimString(cbXmlList.getCellStr(i, "zj"))));
				eList.add(getElement("TRADE_CURR", Function.trimString(cbXmlList.getCellStr(i, "bzsname"))));
				eList.add(getElement("G_QTY", Function.trimString(cbXmlList.getCellStr(i, "spint1"))));
				eList.add(getElement("G_UNIT", Function.trimString(cbXmlList.getCellStr(i, "unit_1"))));
				eList.add(getElement("EXG_VERSION", ""));
				eList.add(getElement("EXG_NO", ""));
				eList.add(getElement("USE_TO", ""));
				eList.add(getElement("QTY_2", Function.trimString(cbXmlList.getCellStr(i, "spint2"))));
				eList.add(getElement("UNIT_2", Function.trimString(cbXmlList.getCellStr(i, "unit_2"))));
				eList.add(getElement("ORIGIN_COUNTRY", Function.trimString(cbXmlList.getCellStr(i, "sgov"))));
				eList.add(getElement("DUTY_MODE", "3"));
				eList.add(getElement("WORK_USD", ""));
			}

			// DEC_CONTAINERԪ�أ���װ�䣩
			String sqlContainer = "select * from bgd_container where ywid=" + ywid;
			CommonBean cbXmlCon = dbo.getData(sqlContainer);
			if (cbXmlCon.getRowNum() > 0) {
				for (int i = 0; i < cbXmlCon.getRowNum(); i++) {
					Element eContainer = XmlElement.addElement("DEC_CONTAINER");
					eContainer.add(
							getElement("CONTAINER_ID", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_ID"))));
					eContainer.add(
							getElement("CONTAINER_MD", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_MD"))));
					eContainer.add(
							getElement("CONTAINER_WT", Function.trimString(cbXmlCon.getCellStr(i, "CONTAINER_WT"))));
				}
			}

			// DEC_LICENSEDOCUԪ�أ��渽����
			String sqlLicensedocu = "select * from bgd_licensedocu where ywid=" + ywid;
			CommonBean cbXmlLicen = dbo.getData(sqlLicensedocu);
			if (cbXmlLicen.getRowNum() > 0) {
				for (int i = 0; i < cbXmlLicen.getRowNum(); i++) {
					Element eLicensedocu = XmlElement.addElement("DEC_LICENSEDOCU");
					eLicensedocu
							.add(getElement("DOCU_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "DOCU_CODE"))));
					eLicensedocu
							.add(getElement("CERT_CODE", Function.trimString(cbXmlLicen.getCellStr(i, "CERT_CODE"))));

				}
			}
			// �ļ���
			Date dTime = new Date();
			String strFileName = ywid + ".xml";

			XMLWriter output = new XMLWriter(new FileOutputStream(pubDir + strFileName));
			output.write(document);
			output.close();
			cb.setCellObj(0, "info", "���ݵ���ɹ���");

		} catch (Exception e) {
			rollback();
			Log.error(this, "����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("����ҵ�񱨹ص���������XML�ļ�ʱ��������" + e.getMessage());
			e.printStackTrace();
			cb.setCellObj(0, "info", "���ݵ���ʧ�ܣ�");
		}
		return cb;
	}

	// һ��ó�׳���ҵ�񱨹ص�����\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public CommonBean tradeCk(String ywid, String typeid) {
		CommonBean cb = new CommonBean();
		cb = tradeJk(ywid, typeid);
		return cb;
	}

//	��֯ElementԪ��
	public Element getElement(String Ename, String Etext) {
		Element e = DocumentHelper.createElement(Ename);
		e.setText(Etext);
		return e;
	}

}
