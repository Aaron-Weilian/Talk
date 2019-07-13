package com.aiyun.common.vo;

import java.util.Hashtable;
import java.util.Vector;

import com.aiyun.common.util.Log;

public class CommonBean implements java.io.Serializable {

	private String beanName; 
	private String attribute = null;

	private Hashtable hListData = null;

	private String[] colNames = null; 
	private Vector vecColumnName = null; 

	private Vector vecData = new Vector(); 

	private Vector vecColumnParam = null; 

	public CommonBean() {
		super();
	}
	public CommonBean[] toArray() {
		CommonBean[] retBean = new CommonBean[vecData.size()];
		for (int i = 0; i < retBean.length; i++) {
			retBean[i] = new CommonBean();
			retBean[i].beanName = beanName;
			retBean[i].attribute = attribute;
			retBean[i].colNames = colNames;
			retBean[i].vecColumnName = vecColumnName;
			retBean[i].vecColumnParam = vecColumnParam;
			retBean[i].vecData.add(this.getRowData(i));
		}
		return retBean;
	}

	public void addColumn(String colName, String defaultValue) {
		String[] newCol = new String[colNames.length + 1];
		System.arraycopy(colNames, 0, newCol, 0, colNames.length);
		newCol[newCol.length - 1] = colName;
		colNames = newCol;
		for (int i = 0; i < vecData.size(); i++) {
			((Vector) vecData.get(i)).add(defaultValue);
		}
	}
	
	public void addObject(String sColName, CommonBean cb) {
		if (sColName == null || sColName.trim().length() == 0)
			return;
		if (vecData == null)
			vecData = new Vector();
		String noputvalueflag = "noputvalue";
		boolean bNoPutValue = true;
		if (getColIndex(sColName) != -1) { //�Ѿ�����һ����¼
			for (int i = 0; i < vecData.size(); i++) {
				Object obj = ((Vector) vecData.elementAt(i)).elementAt(getColIndex(sColName));
				if (obj != null && obj.equals(noputvalueflag)) {
					((Vector) vecData.elementAt(i)).setElementAt(cb, getColIndex(sColName));
					bNoPutValue = false;
					break;
				}
			}
			if (bNoPutValue) {
				Vector vecNewRow = new Vector();
				for (int i = 0; i < getColumnNum(); i++) {
					if (i == getColIndex(sColName))
						vecNewRow.addElement(cb);
					else
						vecNewRow.addElement(noputvalueflag);
				}
				vecData.addElement(vecNewRow);
			}
			return;

		} else { 

			if (colNames == null)
				colNames = new String[0];
			String newCols[] = new String[colNames.length + 1];
			for (int i = 0; i < colNames.length; i++)
				newCols[i] = colNames[i];
			newCols[colNames.length] = sColName;
			colNames = newCols;
			if (vecData.size() == 0) {
				Vector newRow = new Vector();
				newRow.addElement(cb);
				vecData.addElement(newRow);
			} else {
				((Vector) vecData.elementAt(0)).addElement(cb);
				for (int i = 1; i < vecData.size(); i++)
					 ((Vector) vecData.elementAt(i)).addElement(noputvalueflag);
			}
			return;

		}
	}

	public void addRow(Vector vecRow) {
		vecData.add(vecRow);
	}
	public void addRow(Vector vecRow, int pos) {
		vecData.add(pos, vecRow);
	}

	public void moveColumnLast(int from) {
		Vector vecfrom = new Vector();
		String colName = getColName(from);
		for (int i = 0; i < getRowNum(); i++) {
			vecfrom.add(getCellObj(i, from));
		}
		removeColumn(colName);
		for (int i = 0; i < getRowNum(); i++) {
			addValue(colName, (String) vecfrom.get(i));
		}
	}

	public void insertRow(int pos) {
		Vector row = new Vector();
		for (int i = 0; i < getColumnNum(); i++) {
			row.add("");
		}
		vecData.add(pos, row);
	}
	
	public void addValue(String sColName, String sValue) {
		if (sColName == null || sColName.trim().length() == 0)
			return;
		if (vecData == null)
			vecData = new Vector();
		String noputvalueflag = "noputvalue";

		boolean bNoPutValue = true;
		if (getColIndex(sColName) != -1) { 
			for (int i = 0; i < vecData.size(); i++) {

				Object obj = ((Vector) vecData.elementAt(i)).elementAt(getColIndex(sColName));
				if (obj != null && obj.equals(noputvalueflag)) {
					((Vector) vecData.elementAt(i)).setElementAt(sValue, getColIndex(sColName));
					bNoPutValue = false;
					break;
				}
			}
			if (bNoPutValue) {
				Vector vecNewRow = new Vector();
				for (int i = 0; i < getColumnNum(); i++) {
					if (i == getColIndex(sColName))
						vecNewRow.addElement(sValue);
					else
						vecNewRow.addElement(noputvalueflag);
				}
				vecData.addElement(vecNewRow);
			}
			return;

		} else { //����

			if (colNames == null)
				colNames = new String[0];
			String newCols[] = new String[colNames.length + 1];
			for (int i = 0; i < colNames.length; i++)
				newCols[i] = colNames[i];
			newCols[colNames.length] = sColName;
			colNames = newCols;
			if (vecData.size() == 0) {
				Vector newRow = new Vector();
				for (int i = 0; i < colNames.length - 1; i++)
					newRow.addElement(noputvalueflag);
				newRow.addElement(sValue);
				vecData.addElement(newRow);
			} else {
				((Vector) vecData.elementAt(0)).addElement(sValue);
				for (int i = 1; i < vecData.size(); i++)
					 ((Vector) vecData.elementAt(i)).addElement(noputvalueflag);
			}
			return;

		}
	}

	public CommonBean cloneMe() {
		throw new RuntimeException("not support!");
	}

	public String getAttribute() {
		return attribute;
	}
	public String getBeanName() {
		return beanName;
	}
	public Object getCellObj(int iRow, int iCol) {
		if (vecData == null || iCol == -1 || iRow == -1 || iRow > getRowNum() - 1 || iCol > getColumnNum() - 1)
			return null;
		Vector vecRow = ((Vector) vecData.elementAt(iRow));

		if (vecRow != null && vecRow.size() > iCol)
			return vecRow.elementAt(iCol);
		else
			return null;
	}
	public Object getCellObj(int iRow, String strColName) {
		Object obj = getCellObj(iRow, getColIndex(strColName));

		return obj;
	}
	public String getCellStr(int iRow, int iCol) {
		if (getCellObj(iRow, iCol) == null)
			return null;
		return getCellObj(iRow, iCol).toString();
	}

	public String getCellStr(int iRow, String strColName) {
		Object obj = getCellObj(iRow, getColIndex(strColName));
		if (obj == null)
			return null;
		return obj.toString();
	}
	
	public int getCellInt(int iRow, String strColName) {
		return getCellInt(iRow, getColIndex(strColName));
	}
	public int getCellInt(int iRow, int iCol) {
		if (getCellObj(iRow, iCol) == null)
			return 0;

		Object obj = getCellObj(iRow, iCol);
		if (obj == null)
			return 0;
		if (obj.toString().trim().length() < 1) {
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}

	public double getCellDouble(int iRow, String strColName) {
		return getCellDouble(iRow, getColIndex(strColName));
	}

	public double getCellDouble(int iRow, int iCol) {
		if (getCellObj(iRow, iCol) == null)
			return 0;

		Object obj = getCellObj(iRow, iCol);
		if (obj == null)
			return 0;
		if (obj.toString().trim().length() < 1) {
			return 0;
		}
		return Double.parseDouble(obj.toString());
	}

	public int getColIndex(String strColName) {
		if (colNames == null)
			return -1;
		for (int i = 0; i < colNames.length; i++) {
			if (colNames[i] != null && colNames[i].equalsIgnoreCase(strColName))
				return i;
		}
		return -1;
	}
	public Hashtable getColListValue() {

		return hListData;
	}
	public String[][] getColListValue(String strColName) {
		if (colNames == null || hListData == null)
			return null;
		strColName = strColName.toLowerCase();
		String sValues[][] = (String[][]) hListData.get(strColName);
		return sValues;
	}
	public String getColName(int iCol) {
		if (colNames == null || iCol == -1 || iCol > getColumnNum() - 1)
			return null;
		return colNames[iCol];
	}
	public String[] getColNames() {
		return colNames;
	}
	public int getColumnNum() {
		if (colNames == null)
			return 0;
		return getColNames().length;
	}
	public Vector getColumnParam() {
		return vecColumnParam;
	}
	public CommonBean getCommonBean(String strBeanName) {
		if (strBeanName == null || hListData == null)
			return null;
		strBeanName = strBeanName.toLowerCase();
		//String sValues[][] = (String[][]) hListData.get(strColName);
		CommonBean cb = (CommonBean) hListData.get(strBeanName);
		return cb;
	}

	public int getRowNum() {
		if (vecData == null)
			return 0;
		return vecData.size();

	}

	public String getValue(String sColName) {
		if (sColName == null || sColName.trim().length() == 0)
			return null;
		String value = getCellStr(0, sColName);
		if ("null".equals(value)) {
			return "";
		} else {
			return value;
		}
	}
	public Vector getVecData() {
		return vecData;
	}
	public Vector getRowData(int i) {
		return (Vector) vecData.get(i);
	}

	public Vector removeRowData(String colName, String cellContent) {
		Vector vRet = null;
		for (int i = 0; i < vecData.size(); i++) {
			if (getCellStr(i, colName).equals(cellContent)) {
				vRet = (Vector) vecData.get(i);
				vecData.remove(i);
				break;
			}
		}
		return vRet;
	}

	public void printcolum() {

		if (colNames == null) {
			Log.info(this, "�ؼ����б����ڣ�");
			return;
		}

		Log.info(this, "========�ؼ����б����£�====================\n");
		for (int i = 0; i < colNames.length; i++) {
			Log.info(this, "�ؼ���" + i + " = " + colNames[i]);
		}

	}
	public String[] printData() {

		if (vecData == null || colNames == null) {
			Log.info(this, "����������ڣ�");
			return null;
		}
		String strData[] = null;
		Vector vecPintData = new Vector();
		Log.info(this, "\n");
		Log.info(this, "===============��������£�================\n");

		vecPintData.addElement("===============��������£�================\n");

		String str = "";
		for (int i = 0; i < getRowNum(); i++) {
			for (int j = 0; j < getColumnNum(); j++) {
				str = getColNames()[j];
				if (str.length() < 15)
					for (int k = str.length(); k <= 15; k++)
						str += " ";
				Log.info(this, str + "[" + i + "]" + " = " + getCellStr(i, j));
				vecPintData.addElement(str + "[" + i + "]" + " = " + getCellStr(i, j));
			}
			Log.info(this, "===========================================\n");
			vecPintData.addElement("===========================================\n");

		}
		Log.info(this, "\n");
		strData = new String[vecPintData.size()];
		vecPintData.copyInto(strData);
		return strData;
	}

	public void removeColumn(String sColName) {
		if (getColIndex(sColName) == -1)
			return;

		if (hListData != null)
			hListData.remove(sColName.toLowerCase());

		int index = getColIndex(sColName);

		if (vecColumnParam != null)
			vecColumnParam.remove(index);

		if (vecData != null) {
			for (int i = 0; i < getRowNum(); i++)
				 ((Vector) vecData.elementAt(i)).remove(index);
		}

		if (vecColumnName != null)
			vecColumnName.remove(index);

		Vector vecColNamesTemp = new Vector();
		for (int i = 0; i < getColumnNum(); i++) {
			if (index == i)
				continue;
			vecColNamesTemp.addElement(colNames[i]);
		}
		colNames = new String[colNames.length - 1];

		vecColNamesTemp.copyInto(colNames);

	}
	public void removeRowData(int iRow) {
		if (iRow >= 0 && vecData != null && vecData.size() > 0 && iRow < vecData.size()) {
			vecData.remove(iRow);
		}
	}
	public boolean reNameCol(String strOldName, String strNewName) {

		if (strOldName == null || strOldName.trim().length() == 0)
			return false;
		if (getColNames() == null || getColNames().length == 0)
			return false;

		int index = getColIndex(strOldName);
		if (index == -1)
			return false;
		getColNames()[index] = strNewName;

		return true;

	}

	public void setAttribute(String newAttribute) {
		attribute = newAttribute;
	}
	public void setBeanName(String newBeanName) {
		beanName = newBeanName;
	}
	public boolean setCellObj(int iRow, int iCol, Object obj) {
		if (vecData == null || iCol == -1 || iRow == -1 || iRow > getRowNum() - 1 || iCol > getColumnNum() - 1)
			return false;

		((Vector) vecData.elementAt(iRow)).setElementAt(obj, iCol);
		return true;
	}
	public boolean setCellObj(int iRow, String strColName, Object obj) {
		return setCellObj(iRow, getColIndex(strColName), obj);

	}
	public void setColListValue(String strColName, String[][] sValues) {
		if (strColName == null)
			return;
		if (sValues == null)
			sValues = new String[0][];
		if (hListData == null)
			hListData = new Hashtable();
		strColName = strColName.toLowerCase();
		hListData.put(strColName, sValues);
		return;
	}
	public void setColListValue(Hashtable hData) {
		hListData = hData;
	}
	public void setColNames(String[] newColNames) {
		colNames = newColNames;
	}
	public void setColumnParam(Vector newColumnParam) {
		vecColumnParam = newColumnParam;
	}
	public void setCommonBean(String strColName, CommonBean cb) {
		if (cb == null || strColName == null)
			return;
		if (hListData == null)
			hListData = new Hashtable();
		strColName = strColName.toLowerCase();
		hListData.put(strColName, cb);
		return;
	}

	public void setVecData(Vector newVecData) {
		vecData = newVecData;
	}
	
	public void setColumnBean(int col, ColumnBean columnbean) {
		if (getColumnParam()==null) setColumnParam(new Vector());
		if (col>getColumnParam().size()-1) {
			for (int i=getColumnParam().size(); i<=col; i++) {
				getColumnParam().add(new ColumnBean());
			}
		}
		getColumnParam().setElementAt(columnbean,col);
	}
	public void setColumnBean(String strColName, ColumnBean columnbean) {
		int col = this.getColIndex(strColName);
		setColumnBean(col,columnbean);
	}
	public ColumnBean getColumnBean(int col){
		if (getColumnParam()==null) return null;
		return (ColumnBean)getColumnParam().elementAt(col);
		
	}
	public ColumnBean getColumnBean(String strColName){
		int col = this.getColIndex(strColName);
		return getColumnBean(col);
	}
}
