/*
 * Copyright 2013 eXo Platform SAS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.ampliato.devel.exo.addon.cartopins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.portlet.PortletMode;

import juzu.Action;
import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.Route;
import juzu.View;
import juzu.bridge.portlet.JuzuPortlet;
import juzu.impl.common.JSON;
import juzu.plugin.ajax.Ajax;
import juzu.request.RequestContext;
import juzu.template.Template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ampliato.devel.exo.addon.cartopins.conn.DBConfig;
import br.com.ampliato.devel.exo.addon.cartopins.conn.DBFetcher;
import br.com.ampliato.devel.exo.addon.cartopins.data.GeoData;
import br.com.ampliato.devel.exo.addon.cartopins.data.GeoDataContainer;
import br.com.ampliato.devel.exo.addon.cartopins.data.GeopinUtil;

public class Controller 
{
	private final Log log = LogFactory.getLog(getClass().getName());
	
	GeopinUtil util = new GeopinUtil();
	
	@Inject javax.portlet.PortletPreferences preferences;
	
	@Inject
	ResourceBundle bundle;
	private Locale locale = Locale.ENGLISH;
	
	@Inject
	@Path("index.gtmpl")
	Template index;

	@Inject
	@Path("edit.gtmpl")
	Template edit;
	
	@Inject
	juzu.plugin.asset.AssetController assetController;
	
	@View
	public Response.Content index(RequestContext reqContext) throws IOException 
	{
		if (reqContext != null) {
			locale = reqContext.getUserContext().getLocale();
		}
		if (bundle == null) {
			bundle = reqContext.getApplicationContext().resolveBundle(locale);
		}
		
//		String userId = reqContext.getCurrentInstance().getRemoteUser();
		
		String refresh = preferences.getValue("RefreshTime", "10");
		System.out.println(refresh);
		 
		PortletMode portletMode = reqContext.getProperty(JuzuPortlet.PORTLET_MODE);

		Map<String, Object> parameters = new HashMap<String, Object>();

	    parameters.put("latitude", preferences.getValue("latitude", "-23.54"));
	    parameters.put("longitude", preferences.getValue("longitude", "-46.61"));
	    parameters.put("zoom", preferences.getValue("zoom", "13"));
	    parameters.put("refreshtime", preferences.getValue("refreshtime", "60"));

		if (portletMode.equals(PortletMode.VIEW)) 
		{
			parameters.put("ajaxloader", assetController.byPath("images/ajax-loader.gif"));
			
			return index.with(parameters).ok();
		} 
		else if (portletMode.equals(PortletMode.EDIT)) 
		{
			String currentDriver = preferences.getValue("driver", "org.postgresql.Driver");
		    
		    parameters.put("host", preferences.getValue("host", "localhost"));
		    parameters.put("port", preferences.getValue("port", "5432"));
		    parameters.put("database", preferences.getValue("database", "postgres"));
		    parameters.put("username", preferences.getValue("username", "postgres"));
		    parameters.put("password", preferences.getValue("password", ""));
		    parameters.put("query", preferences.getValue("query", "select 1;"));
		    
		    String driverOptions = "";
		    
		    driverOptions += "<option " + ("org.postgresql.Driver".equals(currentDriver) ? "selected" :"") +" value='org.postgresql.Driver'>PostgreSQL</option>";
		    driverOptions += "<option " + ("net.sourceforge.jtds.jdbc.Driver".equals(currentDriver) ? "selected" :"") +" value='net.sourceforge.jtds.jdbc.Driver'>SQLServer (jTDS)</option>";
		    driverOptions += "<option " + ("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(currentDriver) ? "selected" :"") +" value='com.microsoft.sqlserver.jdbc.SQLServerDriver'>SQLServer (Microsoft)</option>";
		    driverOptions += "<option " + ("com.mysql.jdbc.Driver".equals(currentDriver) ? "selected" :"") +" value='com.mysql.jdbc.Driver'>MySQL</option>";
		    driverOptions += "<option " + ("oracle.jdbc.driver.OracleDriver".equals(currentDriver) ? "selected" :"") +" value='oracle.jdbc.driver.OracleDriver'>Oracle</option>";
		    driverOptions += "<option " + ("org.mariadb.jdbc.Driver".equals(currentDriver) ? "selected" :"") +" value='org.mariadb.jdbc.Driver'>MariaDB</option>";
		    
		    parameters.put("driverOptions", driverOptions);
    		
		    return edit.with(parameters).ok();
		}
		return null;

	}

	@Action
	public Response save(
			String latitude,
			String longitude,
			String zoom,
			String refreshtime, 
			String driver, 
			String host, 
			String port, 
			String database,
			String username,
			String password,
			String query) throws Exception 
	{
		preferences.setValue("latitude", latitude);
		preferences.setValue("longitude", longitude);
		preferences.setValue("zoom", zoom);
		preferences.setValue("refreshtime", refreshtime);
		
		preferences.setValue("driver", driver);
		preferences.setValue("host", host);
		preferences.setValue("port", port);
		preferences.setValue("database", database);
		preferences.setValue("username", username);
		preferences.setValue("password", password);
		preferences.setValue("query", query);

		preferences.store();

		Controller_.index().setProperty(JuzuPortlet.PORTLET_MODE, PortletMode.VIEW);
		return Controller_.index().ok();
	}

	@Ajax
	@Resource
	@Route("/search")
	public Response.Body searchGeopin(String criteria) throws Exception 
	{
		String driver = preferences.getValue("driver", "");
		String host = preferences.getValue("host", "");
	    String port = preferences.getValue("port", "");
	    String database = preferences.getValue("database", "");
	    String username = preferences.getValue("username", "");
	    String password = preferences.getValue("password", "");
	    String query = preferences.getValue("query", "");
	    GeoDataContainer c = new GeoDataContainer();
	    try
	    {
	    	if (host == null || host.length() == 0 ||
    		    port == null || port.length() == 0 ||
    		    database == null || database.length() == 0 ||
    		    username == null || username.length() == 0 ||
    		    query == null || query.length() == 0) {
    		    throw new Exception("You must configure your portlet before start searching.");
    		}
    		    
		    DBConfig conf = new DBConfig();
			conf.setDriver(driver);
			conf.setHost(host);
			conf.setPort(port);
			conf.setDatabase(database);
			conf.setUsername(username);
			conf.setPassword(password);
			
			DBFetcher fetcher = new DBFetcher(conf);
			
			Date startDate = new Date();
			
			log.info("[AMPLIATO CARTOPINS] " + startDate + " - start searching");
			fetcher.executeQuery(query);
			log.info("[AMPLIATO CARTOPINS] "+ new Date() + " - end searching");
			
			List<String[]> results = fetcher.getQueryData();
			String[] columns = fetcher.getQueryColumns();
			log.info("[AMPLIATO CARTOPINS] rows: " + results.size() + " cols: " + columns.length);  
			
			if (columns.length != 7)
				throw new Exception("Your query should have exactly 7 pre-defined columns.");
			
			if (!columns[0].toLowerCase().equals("name"))
				throw new Exception("Column 1 must be called by 'name'.");
			if (!columns[1].toLowerCase().equals("count"))
				throw new Exception("Column 2 must be called by 'count'.");
			if (!columns[2].toLowerCase().equals("lat"))
				throw new Exception("Column 3 must be called by 'lat'.");
			if (!columns[3].toLowerCase().equals("lng"))
				throw new Exception("Column 4 must be called by 'lng'.");
			if (!columns[4].toLowerCase().equals("color"))
				throw new Exception("Column 5 must be called by 'color'.");
			if (!columns[5].toLowerCase().equals("tokens"))
				throw new Exception("Column 6 must be called by 'tokens'.");
			if (!columns[6].toLowerCase().equals("children"))
				throw new Exception("Column 7 must be called by 'children'.");
			
			List<GeoData> allData = new ArrayList<GeoData>();
			
			for (String[] _d : results) 
			{
				String aName = _d[0], aCount = _d[1], aLat = _d[2], aLng = _d[3];
				
				// desde que haja latitude e longitude
				if (aLat != null && aLat.length() > 0 && aLng != null && aLng.length() > 0) 
				{
					GeoData d = new GeoData(
						aName, aCount, aLat, aLng, _d[4], _d[5], _d[6]	
					);
					allData.add(d);
				}
				else {
					log.info("[AMPLIATO CARTOPINS] discarting data. there is no lat/lng: " + aName);
				}
			}
			
			c.geoDatas = util.search(criteria, allData);
			
			return Response.Body.content(200, "").body(util.unmarshallData(c))
					.withMimeType("application/xml");

	    }
	    catch(Exception e) 
	    {
	    	log.error("Erro ao processar: "+e.getMessage());
	    	
	    	e.printStackTrace();
	    	c.geoDatas = null;
			return Response.Body.content(500, "").body(util.unmarshallData(c)).withMimeType("application/xml");
	    }
	}
	
	@Ajax
	@Resource
	@Route("/testdb")
	public Response.Body testDBConnection(
		String driver, String host, String port, String database, String username, String password
	)
	{
		DBConfig conf = new DBConfig();
		conf.setDriver(driver);
		conf.setHost(host);
		conf.setPort(port);
		conf.setDatabase(database);
		conf.setUsername(username);
		conf.setPassword(password);
		
		JSON response = new JSON();

		try
		{
			DBFetcher fetcher = new DBFetcher(conf);
			fetcher.executeQuery("SELECT 1");
			List<String[]> results = fetcher.getQueryData();
				
			if (results.size() == 1 && results.get(0)[0].equals("1"))
				response.set("status",  "ok");
			else
				response.set("status",  "Query does not return as expected.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.set("status",  "Error connecting: " + e.getMessage());
		}
		
		return Response.Body.content(200, "").body(response.toString()).withMimeType("application/json");
	}
}
