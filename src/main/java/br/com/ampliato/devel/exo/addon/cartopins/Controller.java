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
import juzu.plugin.ajax.Ajax;
import juzu.request.RequestContext;
import juzu.template.Template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ampliato.devel.exo.addon.cartopins.data.GeoData;
import br.com.ampliato.devel.exo.addon.cartopins.data.GeoDataContainer;
import br.com.ampliato.devel.exo.addon.cartopins.data.GeopinUtil;
import br.com.ampliato.devel.exo.addon.cartopins.data.PortletPreferencesKeys;

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
		preferences.setValue(PortletPreferencesKeys.Driver, driver);
		preferences.setValue(PortletPreferencesKeys.RefreshTime, refreshtime);
		preferences.setValue(PortletPreferencesKeys.Host, host);
		preferences.setValue(PortletPreferencesKeys.Port, port);
		preferences.setValue(PortletPreferencesKeys.Database, database);
		preferences.setValue(PortletPreferencesKeys.Username, username);
		preferences.setValue(PortletPreferencesKeys.Password, password);
		preferences.setValue(PortletPreferencesKeys.Query, query);
	
		log.info("Storing preferences. Validating.");
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
	

	// @Override
	// public void postActivity(String activityText) {
	//
	// // Gets the current container.
	// PortalContainer container = PortalContainer.getInstance();
	//
	// // Gets the current user id
	// ConversationState conversationState = ConversationState.getCurrent();
	// org.exoplatform.services.security.Identity identity =
	// conversationState.getIdentity();
	// String userId = identity.getUserId();
	//
	// // Gets identityManager to handle an identity operation.
	// IdentityManager identityManager = (IdentityManager)
	// container.getComponentInstanceOfType(IdentityManager.class);
	//
	// // Gets an existing social identity or creates a new one.
	// Identity userIdentity =
	// identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME,
	// userId, false);
	//
	// // Gets activityManager to handle an activity operation.
	// ActivityManager activityManager = (ActivityManager)
	// container.getComponentInstanceOfType(ActivityManager.class);
	//
	// // Saves an activity by using ActivityManager.
	// activityManager.saveActivity(userIdentity, null, activityText);
	// socialService.postActivity("I have just bought <b>" + productName +
	// "</b> !");

	// }

//	public class Context {
//		ResourceBundle rs;
//
//		public Context(ResourceBundle rs) {
//			this.rs = rs;
//		}
//
//		public String appRes(String key) {
//			try {
//				return rs.getString(key).replaceAll("'", "&#39;")
//						.replaceAll("\"", "&#34;");
//			} catch (java.util.MissingResourceException e) {
//				LOG.warn("Can't find resource for bundle key " + key);
//			} catch (Exception e) {
//				LOG.error("Error when get resource bundle key " + key, e);
//			}
//			return key;
//		}
//
//	}
}
