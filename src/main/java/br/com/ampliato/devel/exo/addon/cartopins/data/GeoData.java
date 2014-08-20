package br.com.ampliato.devel.exo.addon.cartopins.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeoData {
	
	public GeoData() {}

	public GeoData(String aName, String aLat, String  aLng, String aCount, String aColor) {
		this.setName(aName);
		this.setLat(aLat);
		this.setLng(aLng);
		this.setCount(aCount);
		this.setColor(aColor);
	}
	
	private String name, lat, lng, color, count;
	
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
}