package br.com.ampliato.devel.exo.addon.cartopins.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ampliato.devel.exo.addon.cartopins.data.PortletPreferencesKeys;

/*
 * QueryBean.java
 *
 * QueryBean is used to execute and return the results of a query on behalf of
 * the QueryPortlet application.
 * This bean is far from robust.  Purely for demonstration of the portlet...
 *
 * Created on May 15, 2004, 59 PM
 */

public class DBFetcher {

	/** Designated Logger for this class. */
	private final Log log = LogFactory.getLog(getClass().getName());

	List queryData;
	String[] queryColumns;
	PortletPreferences prefs;

	public DBFetcher(PortletPreferences prefs) {
		this.prefs = prefs;
	}

	/** Returns a query's results. */
	public void executeQuery(String queryString) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;

		log.info("Executing executeQuery method. (New SQL to parse)");
		try {
			conn = DriverManager.getConnection(
				prefs.getValue(PortletPreferencesKeys.Driver, ""), 
				prefs.getValue(PortletPreferencesKeys.Username, ""), 
				prefs.getValue(PortletPreferencesKeys.Password, "")
			);
			
			psmt = conn.prepareStatement(queryString);

			log.info("QueryBean.executeQuery. queryString  : "+ queryString);

			rs = psmt.executeQuery();

			rsm = rs.getMetaData();
			int sqlType = 0;
			int colCount = rsm.getColumnCount();

			this.queryData = new ArrayList(); // All data are Strings.
			while (rs.next()) {
				String[] record = new String[colCount];
				this.queryColumns = new String[colCount];

				for (int i = 1; i <= colCount; i++) {
					queryColumns[i - 1] = rsm.getColumnLabel(i);
					sqlType = rsm.getColumnType(i);

					// For this simple example, I'm only checking 2 types...
					switch (sqlType) {
					case java.sql.Types.INTEGER:
						record[i - 1] = new Integer(rs.getInt(i)).toString();
						break;
					case java.sql.Types.VARCHAR:
						record[i - 1] = rs.getString(i);
						break;
					}
				}

				queryData.add(record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Getter for property queryData.
	 * 
	 * @return Value of property queryData.
	 */
	public java.util.List getQueryData() {
		return queryData;
	}

	/**
	 * Setter for property queryData.
	 * 
	 * @param queryData
	 *            New value of property queryData.
	 */
	public void setQueryData(java.util.List queryData) {
		this.queryData = queryData;
	}

	/**
	 * Getter for property queryColumns.
	 * 
	 * @return Value of property queryColumns.
	 */
	public java.lang.String[] getQueryColumns() {
		return this.queryColumns;
	}

	/**
	 * Setter for property queryColumns.
	 * 
	 * @param queryColumns
	 *            New value of property queryColumns.
	 */
	public void setQueryColumns(java.lang.String[] queryColumns) {
		this.queryColumns = queryColumns;
	}

}
