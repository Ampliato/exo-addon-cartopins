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
			
			String temp = Normalizer.normalize(g.getName().toLowerCase(), Normalizer.Form.NFD);
			temp = pattern.matcher(temp).replaceAll("");
		    
			criteria = Normalizer.normalize(criteria, Normalizer.Form.NFD);
			criteria = pattern.matcher(criteria).replaceAll("");
			
			if (temp.indexOf(criteria) > -1) {
				result.add(g);
			}
		}

		return result;
	}
	
	public List<GeoData> getAllData() {
		
		List<GeoData> results = new ArrayList<GeoData>();
		
		results.add(new GeoData("pin1", "-23.5487433", "-46.612363", "1", "#990000"));
		results.add(new GeoData("pin2", "-23.5505233", "-46.6342982", "2", "#9900FF"));
		results.add(new GeoData("pin3", "-23.5549746", "-46.6056471", "3", "#CCFF00"));
		results.add(new GeoData("pin4", "-23.5703996", "-46.6438527", "4", "#FF9966"));
		results.add(new GeoData("pin5", "-23.5002381", "-46.649226", "5", "#99FF33"));
		
		return results;
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
