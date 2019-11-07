package com.aiyun.common.po;

import java.sql.Connection;
import java.sql.SQLException;

import com.aiyun.common.tool.Log;
import com.aiyun.common.vo.CommonBean;

public abstract class DBUtil {

	private ErrorMessageBean errMsgBean = new ErrorMessageBean();
	 
	private DataBaseObject dbo = null;
	private Connection con = null;

    
	public void commit() {

		if (con != null) {
			try {
				con.commit();
			} catch (SQLException e) {
				Log.error(this, "submit fail," + e.getMessage());
				errMsgBean.addCommonMessage("submit fail," + e.getMessage());
			} finally {
				try {
					con.close();
				} catch (SQLException se) {
					Log.error(this, "connection close fail," + se.getMessage());
				}
			}
		}
	}
	
   public void multicommit() {

	   if (con != null) {
		   try {
			   con.commit();
		   } catch (SQLException e) {
			   Log.error(this, "multi submit fail," + e.getMessage());
			   errMsgBean.addCommonMessage("submit fail," + e.getMessage());
		   }
	   }
   }
	
	
	public void release() throws SQLException {
		rollback();
	}

	protected void rollback() {
		if (con != null) {
			try {
				con.rollback();

			} catch (SQLException e) {
				Log.error(this, "rollback fail," + e.getMessage());
				errMsgBean.addCommonMessage("rollback fail," + e.getMessage());
			} finally {
				try {
					con.close();
					con = null;
					dbo = null;
				} catch (SQLException se) {
					Log.error(this, "connection close fail," + se.getMessage());
				}
			}
		}
	}
	
   public void multirollback() {

	   if (con != null) {
		   try {
			   con.rollback();
		   } catch (SQLException e) {
			   Log.error(this, "rollback fail," + e.getMessage());
			   errMsgBean.addCommonMessage("rollback fail," + e.getMessage());
		   }
	   }
   }

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ErrorMessageBean getErrMsgBean() {
		return errMsgBean;
	}

	public void setErrMsgBean(ErrorMessageBean newErrMsgBean) {
		errMsgBean = newErrMsgBean;
	}

	private Connection getConnection() throws SQLException {
        DBPool pool = DBPool.getInstance();
        return pool.getConnection();
    }
	
	public DataBaseObject getDataBaseObject() throws SQLException {
		if (dbo == null) {
			dbo = new DataBaseObject(getConnection());
		}
		return dbo;
	}
	
	public boolean isDuplicate(String table, String field, String id, String value, String type) throws SQLException {

		String strSql = "SELECT Count(*) AS num FROM " + table + " WHERE " + field + "='" + value + "' ";
		if (type.equalsIgnoreCase("edit")) {
			strSql += "AND ID<>'" + id + "' ";
		}
		DataBaseObject dbo = getDataBaseObject();
		CommonBean cb = dbo.getData(strSql);
		if (cb.getValue("num").equals("0")) {
			return false;
		} else {
			return true;
		}

	}

}
