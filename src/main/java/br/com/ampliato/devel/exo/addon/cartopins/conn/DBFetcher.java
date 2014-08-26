package br.com.ampliato.devel.exo.addon.cartopins.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBFetcher {

	private final Log log = LogFactory.getLog(getClass().getName());

	List<String[]> queryData;
	String[] queryColumns;
	DBConfig conf;
	
	public DBFetcher(DBConfig aConf) {
		this.conf = aConf;
	}

	public void executeQuery(String query) 
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;

		try 
		{
			String url = null;
			String driver = this.conf.getDriver();

			try
			{
				Class.forName(driver);
			}
			catch(Exception e)
			{
				throw new RuntimeException(e);
			}

			if (driver.equals("org.postgresql.Driver"))
				url = "jdbc:postgresql://__host__:__port__/__database__";
			else if (driver.equals("net.sourceforge.jtds.jdbc.Driver"))
				url = "jdbc:jtds://__host__:__port__/__database__";
			else if (driver.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver"))
				url = "jdbc:sqlserver://__host__:__port__;database=__database__";
			else if (driver.equals("com.mysql.jdbc.Driver"))
				url = "jdbc:mysql://__host__:__port__/__database__";
			else if (driver.equals("oracle.jdbc.driver.OracleDriver"))
				url = "jdbc:oracle:thin:@__host__:__port__:__database__";
			else if (driver.equals("org.mariadb.jdbc.Driver"))
				url = "jdbc:mariadb://__host__:__port__/__database__";
					
			url = url.replace("__host__", conf.getHost())
					 .replace("__port__", conf.getPort())
					 .replace("__database__", conf.getDatabase());
			
			conn = DriverManager.getConnection(
				url,
				conf.getUsername(),
				conf.getPassword()
			);
			
			log.info("[AMPLIATO CARTOPIN - DBFetcher] Query: "+ query);

			psmt = conn.prepareStatement(query);

			rs = psmt.executeQuery();

			rsm = rs.getMetaData();
			// int sqlType = 0;
			int colCount = rsm.getColumnCount();

			this.queryData = new ArrayList<String[]>();
			
			while (rs.next()) 
			{
				String[] record = new String[colCount];
				this.queryColumns = new String[colCount];

				for (int i = 1; i <= colCount; i++) 
				{
					queryColumns[i - 1] = rsm.getColumnLabel(i);
					//sqlType = rsm.getColumnType(i);

					// ignore types
					// case java.sql.Types.INTEGER
					// case java.sql.Types.INTEGER:
					record[i - 1] = rs.getString(i);
				}

				queryData.add(record);
			}
		} catch (Exception e) 
		{
			log.info("[AMPLIATO CARTOPIN - DBFetcher] Error in query: " + query);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public java.util.List<String[]> getQueryData() {
		return queryData;
	}

	public java.lang.String[] getQueryColumns() {
		return this.queryColumns;
	}
}