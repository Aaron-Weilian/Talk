package com.aiyun.common.po;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import com.aiyun.common.tool.Log;
import com.aiyun.common.tool.StrTool;
import com.aiyun.common.vo.CommonBean;

public class DataBaseObject {

	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Statement stmt;
	public Connection con;

	public DataBaseObject(Connection con) {
		this.con = con;
	}

	public DataBaseObject() {
	}

	protected void closeRS() throws java.sql.SQLException {
		if (rs != null) {
			rs.close();
			rs = null;
		}
		if (rsmd != null) {
			rsmd = null;
		}
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	protected void executeQuery(String strSql) throws SQLException {

		Log.info(this, "\n\n\n" + strSql + "\n");
		Connection con = getConnection();
		try {
			if (con.isClosed())
				throw new SQLException("con is close");
			stmt = con.createStatement();
			rs = stmt.executeQuery(strSql);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			Log.error(this, "sql exec failure, " + e.getMessage());
			throw e;
		}
	}
	public CommonBean getData(String strSql) throws SQLException {
		return getData(strSql, 0, 0);
	}

	public CommonBean getData(String strSql, int pageNum, int rowNum) throws SQLException {
		try {
			long ib = System.currentTimeMillis();
			executeQuery(strSql);
			Vector vRet = new Vector();
			int iColNum = rsmd.getColumnCount();
			String sHead[] = new String[iColNum];
			for (int i = 0; i < iColNum; i++)
				sHead[i] = rsmd.getColumnLabel(i + 1);
			int counter = 0;
			while (counter++ < pageNum * rowNum && rs.next());
			while (rs.next()) {
				Vector vRow = new Vector();
				for (int i = 1; i <= iColNum; i++) {
					vRow.addElement(getField(i));
				}
				vRet.addElement(vRow);
			}
			CommonBean cb = new CommonBean();
			cb.setColNames(sHead);
			cb.setVecData(vRet);
			long ie = System.currentTimeMillis();
			Log.info(this, "\n\n =================sql execution time: " + (ie - ib) + " ms================================================");
			return cb;
		} catch (SQLException e) {
			Log.error(this, "sql exec failure," + e.getMessage());
			throw e;
		} finally {
			closeRS();
		}
	}

	public boolean execute(String[] sql) throws SQLException {

		if (sql == null) {
			throw new SQLException("sql is null");
		}
		try {
			stmt = con.createStatement();

			for (int i = 0; i < sql.length; i++) {
				Log.info(this, sql[i]);
				if (sql[i] == null || sql[i].trim().length() == 0) {
					throw new SQLException("sql is null");
				}
				
				stmt.executeUpdate(sql[i]);
			}
			return true;

		} finally {
			closeRS();
		}

	}

	public boolean execute(String sql) throws SQLException {
		return execute(new String[] { sql });
	}

	public boolean execute(CommonBean cb) throws SQLException {
		if (cb == null) {
			throw new SQLException("cb object is null");
		}
		String[] sqls = new String[cb.getRowNum()];
		String table = cb.getBeanName();
		String oper = cb.getAttribute();
		if (table == null || table.equals("")) {
			throw new SQLException("table info is null");
		}
		if (oper == null || oper.equals("")) {
			throw new SQLException("Please set action, insert,update or delete");
		}

		for (int i = 0; i < cb.getRowNum(); i++) {
			if (oper.equalsIgnoreCase("insert")) {
				sqls[i] = "INSERT INTO " + table + "(";
				for (int j = 0; j < cb.getColumnNum(); j++) {
					sqls[i] += cb.getColName(j) + ",";
				}
				sqls[i] = sqls[i].substring(0, sqls[i].length() - 1) + ")values(";
				for (int j = 0; j < cb.getColumnNum(); j++) {
					String value = cb.getCellStr(i, j);
					if (value == null) {
						sqls[i] += "null,";
					} else {
						if (cb.getColName(j).toLowerCase().endsWith("date")) {
							if (value.equals("_SYSDATE")) {
								sqls[i] += "SysDate,";
							}
							else {
								sqls[i] += "to_date('" + value + "','yyyy-mm-dd hh24:mi:ss'),";
							}
						}
						else {
							sqls[i] += "'" + StrTool.replaceStr(value,"'","''") + "',";
						}
					}
				}
				sqls[i] = sqls[i].substring(0, sqls[i].length() - 1) + ") ";
			} else if (oper.equalsIgnoreCase("update")) {
				sqls[i] = "UPDATE " + table + " SET ";
				for (int j = 0; j < cb.getColumnNum(); j++) {
					if (!cb.getColName(j).equalsIgnoreCase("id")) {
						sqls[i] += cb.getColName(j) + "=";
						String value = cb.getCellStr(i, j);
						if (value == null) {
							sqls[i] += "null,";
						} else {
							if (cb.getColName(j).toLowerCase().endsWith("date")) {
								if (value.equals("_SYSDATE")) {
									sqls[i] += "SysDate,";
								}
								else {
									sqls[i] += "to_date('" + value + "','yyyy-mm-dd hh24:mi:ss'),";
								}
							}
							else {
								sqls[i] += "'" + StrTool.replaceStr(value,"'","''") + "',";
							}
						}
					}
				}
				sqls[i] = sqls[i].substring(0, sqls[i].length() - 1) + " WHERE ID='";
				sqls[i] += cb.getCellStr(i, "ID") + "' ";
			} else if (oper.equalsIgnoreCase("delete")) {
				sqls[i] = "DELETE FROM " + table + " WHERE ";
				for (int j = 0; j < cb.getColumnNum(); j++) {
					sqls[i] += cb.getColName(j) + "=";
					String value = cb.getCellStr(i, j);
					if (value == null) {
						sqls[i] += "null AND ";
					} else {
						sqls[i] += "'" + value + "' AND ";
					}
				}
				sqls[i] = sqls[i].substring(0, sqls[i].length() - 4);
			}
		}
		return execute(sqls);
	}

	protected Connection getConnection() throws java.sql.SQLException {
		return con;
	}

	protected Object getField(int column) throws SQLException {

		if (rs == null || rsmd == null)
			throw new SQLException("ResultSet is null.");
		Object obj = rs.getObject(column);
		if (rsmd.getColumnType(column) == Types.TIMESTAMP) {
			if (obj != null && obj.toString().length() > 9) {

				String sObject = obj.toString();
				if (sObject.lastIndexOf(".") != -1) {
					sObject = sObject.substring(0, sObject.lastIndexOf("."));
					String sTemp = sObject.substring(10, sObject.length());
					sTemp = StrTool.replaceStr(sTemp, ":", "");
					sTemp = StrTool.replaceStr(sTemp, "0", "");
					if (sTemp.trim().equals(""))
						sObject = sObject.substring(0, 10);
				}
				obj = sObject;

			}
		}
		return obj;

	}

	public void setConnection(Connection con) {
		this.con = con;
	}

}
