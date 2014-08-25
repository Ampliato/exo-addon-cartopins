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
import java.util.List;
import java.util.Locale;
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
	List<GeoData> allData = null;
	
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

		if (portletMode.equals(PortletMode.VIEW)) {
			return index.ok();
		} else if (portletMode.equals(PortletMode.EDIT)) {
			return edit.ok();
		}
		return null;

	}

	@Action
	public Response save(
			String driver, 
			String refreshtime, 
			String host, 
			String port, 
			String database,
			String username,
			String password,
			String query) throws Exception 
	{
		preferences.setValue("driver", driver);
		preferences.setValue("refreshtime", refreshtime);
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
	public Response.Body searchGeopin(String criteria) 
	{
		if (this.allData == null)
			this.allData = util.getAllData();
		
		GeoDataContainer c = new GeoDataContainer();
		c.geoDatas = util.search(criteria, allData);

		return Response.Body.content(200, "").body(util.unmarshallData(c))
				.withMimeType("application/xml");
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

			if (results.size() == 1 && results.get(0)[0] == "1")
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
