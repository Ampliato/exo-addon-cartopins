package br.com.ampliato.devel.exo.addon.cartopins.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeoData {
	
	public GeoData() {}

	public GeoData(
			String aName, 
			String aCount, 
			String aLat, 
			String aLng, 
			String aColor, 
			String aTokens,
			String aChildren) {
		this.setName(aName == null ? "" : aName);
		this.setLat(aLat);
		this.setLng(aLng);
		this.setCount(aCount == null ? "" : aCount);
		this.setColor(aColor);
		this.setTokens(aTokens == null ? "" : aTokens);
		
		if (aChildren != null && aChildren.length() > 0) {
			this.children = aChildren.split(",");
			this.rawChildren = aChildren;
		}
		
	}
	
	private String name, count, lat, lng, color, tokens, rawChildren;
	
	private String[] children;
	
	public String  getLat() {
		return lat;
	}
	public void setLat(String  lat) {
		this.lat = lat;
	}
	public String  getLng() {
		return lng;
	}
	public void setLng(String  lng) {
		this.lng = lng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String  getCount() {
		return count;
	}
	public void setCount(String  count) {
		this.count = count;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public String[] getChildren() {
		return children;
	}

	public void setChildren(String[] children) {
		this.children = children;
	}
	
	public String getRawChildren() {
		return rawChildren;
	}
}