package com.aiyun.common.dict;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import com.aiyun.common.bo.DBUtil;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.util.StrTool;
import com.aiyun.common.vo.ColumnBean;
import com.aiyun.common.vo.CommonBean;

/**
 * 
 */
public class FieldBox extends DBUtil {
	static HashMap hsTableObject = null;
	String sTable = null;
	
	public FieldBox() {
		super();
		Init();
	}
	
	public FieldBox (Connection con) {
		setConnection(con);
		Init();
	}
	
	private void Init() {
		if (hsTableObject==null) {
			hsTableObject = new HashMap();
			
			hsTableObject.put("BGD_JK_7","BGD_JK");
			hsTableObject.put("BGD_JK_10","BGD_JK");
			hsTableObject.put("BGD_JK_15","BGD_JK");
			hsTableObject.put("BGD_JK_16","BGD_JK");
			hsTableObject.put("BGD_JK_22","BGD_JK");
			hsTableObject.put("BGD_JK_9","BGD_JK");
			hsTableObject.put("BGD_JK_24","BGD_JK");
			hsTableObject.put("BGD_JK_21","BGD_JK");
			hsTableObject.put("BGD_JK_26","BGD_JK");
			hsTableObject.put("BGD_JK_13","BGD_JK");
			hsTableObject.put("BGD_JK_12","BGD_JK");
			
			hsTableObject.put("BGD_CK_8","BGD_CK");
			hsTableObject.put("BGD_CK_11","BGD_CK");
			hsTableObject.put("BGD_CK_14","BGD_CK");
			hsTableObject.put("BGD_CK_17","BGD_CK");
			hsTableObject.put("BGD_CK_23","BGD_CK");
			hsTableObject.put("BGD_CK_25","BGD_CK");
			
			hsTableObject.put("TRADE_BGD_JK","BGD_JK");
			hsTableObject.put("TRADE_BGD_CK","BGD_CK");
		}
	}
	
	private String getTable(String sObject) {
		if (sTable!=null) return sTable;
		sTable = (String)hsTableObject.get(sObject.toUpperCase());
		if (sTable==null) sTable = sObject;
		return sTable;
	}
	
	/*
	 * ���ݶ������������ֵ�������Ϣ
	 * @sObject: ����
	 * @sID: ����ID(newʱΪnull)
	 * @sOperator: ����(new,edit,view)
	 */
	public CommonBean get_Field(String sObject, String sID, String sOperator) throws SQLException  {
		//������Ϣ�������Ϣ
		CommonBean cbFieldClass=get_FieldClass(sObject,sOperator);

		for(int iRow=0;iRow<cbFieldClass.getRowNum();iRow++){
			CommonBean cbFieldBoxBean=get_FieldBoxBean(sObject, cbFieldClass.getCellStr(iRow, "ID"), sID);

			cbFieldClass.setCellObj(iRow,"FieldBean",cbFieldBoxBean);
		}

		return cbFieldClass;
	}
	
	/* ������Ϣ����Ϣ
	 * @sObject: ����
	 * @sOperator: ����(new,edit,view)
	 */
	private CommonBean get_FieldClass(String sObject, String sOperator) throws SQLException {
		String sql = null;
		sql = "SELECT ID, sName, lExpand, lSys, '' AS FieldBean "
			+ "FROM Sys_FieldClass "
			+ "WHERE sObject = '"+sObject+"' AND sOperator = '"+sOperator+"' "
			+ "ORDER BY lPos";
		DataBaseObject dbo = getDataBaseObject();
		CommonBean cbFieldClass = dbo.getData(sql);
		return cbFieldClass;
	}
	
	/*
	 * ���������Ϣ��Ļ�����Ϣbean
	 * @sObject: ����
	 * @FieldClassID: ��Ϣ��ID
	 * @ObjectID: ����ID(�½�ʱΪnull; �༭�Ͳ鿴ʱ��ʵ��ֵ)
	 */
	private CommonBean get_FieldBoxBean(String sObject, String FieldClassID, String ObjectID) throws SQLException {
		String sql = null;
		DataBaseObject dbo = getDataBaseObject();
		
		//����Ϣ���е��ֶ���Ϣ(��Ҫ�Ǹ�ʽ��Ϣ)
		sql = "SELECT Sys_Field.*, Sys_FieldClassField.lReadOnly FROM Sys_FieldClassField LEFT JOIN Sys_FieldClass ON Sys_FieldClass.ID=Sys_FieldClassField.FieldClassID LEFT JOIN Sys_Field ON Sys_Field.ID=Sys_FieldClassField.FieldID WHERE Sys_FieldClassField.FieldClassID = '"+FieldClassID+"' ORDER BY Sys_FieldClassField.lPos";
		CommonBean cbField = dbo.getData(sql);
		if (cbField == null || cbField.getRowNum() == 0) {
			sql = "SELECT * FROM Sys_Field WHERE sFieldAs = 'ID' AND sObject = '"+sObject+"' ORDER BY lPos";
			cbField = dbo.getData(sql);
		}
		
		//��֯����Щ�ֶ���Ҫȡ����select�Ӿ�
		String subSelect = "";
		for (int i=0; i<cbField.getRowNum(); i++) {
			String sfield = cbField.getCellStr(i,"sfield");
			if (cbField.getCellStr(i,"sdatatype").equalsIgnoreCase("date"))
				sfield="to_char(" + sfield + ",'yyyy-mm-dd')";
			if (subSelect.equals("")) 
				subSelect = sfield + " AS " + cbField.getCellStr(i,"sfieldas");
			else
				subSelect = subSelect + "," + sfield + " AS " + cbField.getCellStr(i,"sfieldas");
		}
		
		//Ϊ��Ϣ��ȡ����
		String sSubFrom = getSubFrom(sObject);	//����Ķ�Ӧ��
		sql = "SELECT " + subSelect + " " + sSubFrom + " WHERE "+getTable(sObject)+".ID='"+ObjectID+"'";
		CommonBean cbFieldBox = dbo.getData(sql);
		if (cbFieldBox.getRowNum()==0) {
			//��һ������
			Vector erow = new Vector();
			for (int i=0; i<cbFieldBox.getColumnNum(); i++)
				erow.add("");
			cbFieldBox.addRow(erow);
		}
		
		//Ϊ���󸳸�ʽ��Ϣ��������Ϣ(��Ҫ�������б�����)
		setAppendixInfo(cbFieldBox, cbField);
		
		return cbFieldBox;
	}
	
	//����Ķ�Ӧ��(Ŀǰ���������������)
	private String getSubFrom(String sObject) throws SQLException {
		String subfrom = "FROM " + getTable(sObject);
		String sql = "SELECT sSQL FROM Sys_Field WHERE sObject='"+sObject+"' AND sFieldAS = 'ID'";
		
		DataBaseObject dbo = getDataBaseObject();
		
		CommonBean cb = dbo.getData(sql);
		if (cb!=null && cb.getRowNum()>0 && cb.getValue("ssql")!=null && !cb.getValue("ssql").trim().equals(""))
			subfrom = cb.getValue("ssql");
		
		return subfrom;
	}
	
	/*
	 * Ϊ���󸳸�ʽ��Ϣ��������Ϣ
	 * @cbFieldBox: ������Ϣ��bean
	 * @cbField: ������ʽ���ֵ���Ϣ
	 */
	private void setAppendixInfo(CommonBean cbFieldBox, CommonBean cbField) throws SQLException {
		//�Ը�ʽbean���н���ѭ��
		for (int irow=0; irow<cbField.getRowNum(); irow++) {
			ColumnBean cbColumnBean = new ColumnBean();
			cbColumnBean.setID(cbField.getCellStr(irow,"id"));
			cbColumnBean.setObject(cbField.getCellStr(irow,"sobject"));
			cbColumnBean.setTable(cbField.getCellStr(irow,"stable"));
			cbColumnBean.setFieldAs(cbField.getCellStr(irow,"sfieldas"));
			cbColumnBean.setField(cbField.getCellStr(irow,"sfield"));
			cbColumnBean.setTitle(cbField.getCellStr(irow,"stitle"));
			cbColumnBean.setDataType(cbField.getCellStr(irow,"sdatatype"));
			cbColumnBean.setLength(cbField.getCellStr(irow,"llength"));
			cbColumnBean.setDeciLength(cbField.getCellStr(irow,"ldecilength"));
			cbColumnBean.setSys(cbField.getCellStr(irow,"lsys"));
			cbColumnBean.setRefType(cbField.getCellStr(irow,"sreftype"));
			cbColumnBean.setRefKey(cbField.getCellStr(irow,"srefkey"));
			cbColumnBean.setAllowNull(cbField.getCellStr(irow,"lallownull"));
			cbColumnBean.setConfig(cbField.getCellStr(irow,"lconfig"));
			cbColumnBean.setVisible(cbField.getCellStr(irow,"lvisible"));
			cbColumnBean.setReadonly(cbField.getCellStr(irow,"lreadonly"));
			cbColumnBean.setJspFunction(cbField.getCellStr(irow,"jspfunction"));
			cbColumnBean.setFull(cbField.getCellStr(irow,"lfull"));
			
			String colname = cbField.getCellStr(irow,"sfieldas");
			cbFieldBox.setColumnBean(colname,cbColumnBean);
			
			//�����б�����
			String reftype = cbColumnBean.getRefType();
			String refkey = cbColumnBean.getRefKey();
			if (reftype!=null) {
				if (reftype.equalsIgnoreCase("enum")) setEnum(cbFieldBox, refkey, colname);
				if (reftype.equalsIgnoreCase("list")) setList(cbFieldBox, refkey, colname);
			}
		}
		return;
	}
	
	/*
	 * Ϊ������Ϣ���������ö���б�
	 * ΪcbData�е���fieldas���ùؼ���Ϊkey��ö���б���Ϣ
	 * @cbData: ����bean
	 * @key: ö���б�ֵ��key
	 * @fieldas:����bean�е�����
	 */
	private void setEnum(CommonBean cbData, String key, String fieldas) throws SQLException {
		DataBaseObject dbo = getDataBaseObject();
		String sql = "SELECT ID, sName FROM FieldEnum WHERE FieldKey='"+key+"' ORDER BY sName";
		CommonBean cbList = dbo.getData(sql);
		String[][] arrList = com.aiyun.common.util.Function.bean2arr(cbList);
		cbData.setColListValue(fieldas, arrList);
	}
	
	/*
	 * ΪcbData�е���fieldas�������ݿ��table�е��б���Ϣ
	 * @cbData: ����bean
	 * @table: �б���Ϣ��Դ�����ݿ��
	 * @fieldas:����bean�е�����
	 */
	private void setList(CommonBean cbData, String table, String fieldas) throws SQLException {
		DataBaseObject dbo = getDataBaseObject();
		if (table==null || table.equals("")) return;
		
		String sql = "SELECT ID, sName FROM "+table+" ORDER BY sName";
		if (table.toLowerCase().startsWith("select")) {
			sql = table;
		}
		CommonBean cbList = dbo.getData(sql);
		String[][] arrList = com.aiyun.common.util.Function.bean2arr(cbList);
		cbData.setColListValue(fieldas, arrList);
	}
	
	/*
	 * У������(�½�\�༭ʱ)
	 * sObject: ����
	 * @sOperator: ������־(�½�����ʱ:new; �༭����ʱ:edit)
	 * @cbData: ���������ݵ�bean
	 */
	public CommonBean validData(String sObject, String sOperator, CommonBean cbData) throws SQLException {
		CommonBean cbFieldClass = get_Field(sObject,cbData.getValue("id"),sOperator.toUpperCase());
		cbFieldClass.setAttribute("true");
		boolean bValid = true;
		for (int i=0; i<cbData.getColumnNum(); i++) {
			String colname = cbData.getColName(i);
			String colvalue = cbData.getCellStr(0,colname).trim();
			
			CommonBean cbFields = getBeanFromFieldClass(cbFieldClass, colname);
			if (cbFields!=null) {
				ColumnBean columnbean = cbFields.getColumnBean(colname);
				//��������(�����½�ʱ��id����,��ΪҪ��id�������жϵ�ʱ�Ĳ���!)
				if (sOperator.equalsIgnoreCase("new")){
					if (cbData.getColName(i).equalsIgnoreCase("id"))
						cbFields.setCellObj(0,cbData.getColName(i),"");
					else 
						cbFields.setCellObj(0,cbData.getColName(i),colvalue);
				}
				else
					cbFields.setCellObj(0,cbData.getColName(i),colvalue);
				
				//�ı���
				if ((columnbean.getDataType().equalsIgnoreCase("TEXT")) || (columnbean.getDataType().equalsIgnoreCase("TEXTAREA"))) {
					if (colvalue==null || colvalue.trim().equals("")) {
						if (columnbean.getAllowNull().equals("0")) {
							columnbean.setErrMsg(columnbean.getTitle() + "������Ϊ��");
							bValid = false;
						}	
					}
					else {
						//��鳤��
						int length=StrTool.str2int(columnbean.getLength());
						if (columnbean.getFull()!=null && columnbean.getFull().equals("1")) {
							if (colvalue.getBytes().length!=length) {
								columnbean.setErrMsg(columnbean.getTitle() + "���ȱ�����" + length);
								bValid = false;
							}
						}
						else {
							if (colvalue.getBytes().length>length) {
								columnbean.setErrMsg(columnbean.getTitle() + "���ȳ�����Χ");
								bValid = false;
							}
						}
					}
				}
				
			
				
				//��ֵ��(����\������)
				else if (columnbean.getDataType().equalsIgnoreCase("DECIMAL")) {
					if (!StrTool.isNumeric(colvalue)) {
						columnbean.setErrMsg(columnbean.getTitle() + "��ֵ���Ϸ�");
						bValid = false;
					}
					else {
						if (StrTool.str2int(columnbean.getDeciLength())==0) {	//����
							if (colvalue.indexOf(".")!=-1){
								columnbean.setErrMsg(columnbean.getTitle() + "Ϊ����,��ֵ���Ϸ�");
								bValid = false;
							}
							else {
								if (StrTool.str2int(colvalue)==0 && columnbean.getAllowNull().equals("0")) {
									columnbean.setErrMsg(columnbean.getTitle() + "������Ϊ�ջ���");
									bValid = false;
									continue;
								}
								//��鳤��
								int length=StrTool.str2int(columnbean.getLength());
								if (colvalue.length()>length) {
									columnbean.setErrMsg(columnbean.getTitle() + "���ȳ�����Χ");
									bValid = false;
								}
							}
						}
						else {	//������
							if (StrTool.str2float(colvalue)==0) {
								if (columnbean.getAllowNull().equals("0")) {
									columnbean.setErrMsg(columnbean.getTitle() + "������Ϊ�ջ���");
									bValid = false;
								}
							}
							else {
								//�����ֵ���ȼ���С��λ��
								int tl = colvalue.length();
								int td = 0;
								if (colvalue.indexOf(".")>-1) {
									tl = colvalue.length()-1;	//�ܳ���
									td = colvalue.substring(colvalue.indexOf(".")+1).length();	//С��λ��
								}
								if (tl>StrTool.str2int(columnbean.getLength()) || td>StrTool.str2int(columnbean.getDeciLength())) {
									columnbean.setErrMsg(columnbean.getTitle() + "��ֵ���Ȼ�С��λ��������Χ");
									bValid = false;
								}
							}
						}
					}
				}
				//������
				else if (columnbean.getDataType().equalsIgnoreCase("DATE")) {
					if (colvalue==null || colvalue.trim().equals("")) {
						if (columnbean.getAllowNull().equals("0")) {
							columnbean.setErrMsg(columnbean.getTitle() + "������Ϊ��");
							bValid = false;
						}	
					}
					else {
						//��鴫��������Ƿ�Ϊ�Ϸ�������ֵ
						if (!com.aiyun.common.util.DateTool.isDate(colvalue)) {
							columnbean.setErrMsg(columnbean.getTitle() + "��ֵ���Ϸ�");
							bValid = false;
						}
					}
				}
			}
		}
		
		if (!bValid) cbFieldClass.setAttribute("false");
		
		return cbFieldClass;
	}
	
	/*
	 * ������fieldclass bean��Ѱ��ĳ���ֶ����ڵ�bean
	 * @cbFieldClass: ����Ϣbean
	 * @sField: �ֶ�����
	 */
	 public CommonBean getBeanFromFieldClass(CommonBean cbFieldClass, String sField) {
	 	CommonBean cbTargetBean = null;
	 	//ѭ��ÿһ����Ϣ��bean
	 	for (int i=0; i<cbFieldClass.getRowNum()-1; i++) {
			cbTargetBean = getBeanFromFields((CommonBean)cbFieldClass.getCellObj(i,"fieldbean"),sField);
			if (cbTargetBean!=null) break;
	 	}
	 	return cbTargetBean;
	 }
	 
	/*
	 * ������bean��Ѱ��ĳ���ֶ����ڵ�bean
	 * @cbFields: ��Ϣbean
	 * @sField: �ֶ�����
	 */
	 public CommonBean getBeanFromFields(CommonBean cbFields, String sField) {
		CommonBean cbTargetBean = null;
		for (int icol=0; icol<cbFields.getColumnNum(); icol++) {
			if (cbFields.getColName(icol).equalsIgnoreCase(sField)) {
				cbTargetBean = cbFields;
				break;
			}
		}
	 	return cbTargetBean;
	 }
}
