package br.com.ampliato.devel.exo.addon.cartopins.data;

import java.io.StringWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class GeopinUtil {

	public List<GeoData> search(String criteria, List<GeoData> allData) {
	
		List<GeoData> result = new ArrayList<GeoData>();
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    
		for (GeoData g : allData) {
			
			String temp = Normalizer.normalize(g.getTokens().toLowerCase(), Normalizer.Form.NFD);
			temp = pattern.matcher(temp).replaceAll("");
		    
			criteria = Normalizer.normalize(criteria, Normalizer.Form.NFD);
			criteria = pattern.matcher(criteria).replaceAll("");
			
			if (temp.indexOf(criteria) > -1) {
				result.add(g);
			}
		}

		return result;
	}
	
	public String unmarshallData(GeoDataContainer container) 
	{
		try
		{
			JAXBContext ctx = JAXBContext.newInstance(GeoDataContainer.class);
			Marshaller marshaller = ctx.createMarshaller();
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(container, sw);
			return sw.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
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
