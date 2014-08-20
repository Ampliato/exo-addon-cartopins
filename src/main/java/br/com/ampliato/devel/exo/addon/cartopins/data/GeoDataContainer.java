package br.com.ampliato.devel.exo.addon.cartopins.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(GeoData.class)
public class GeoDataContainer {
	public List<GeoData> geoDatas = null;
}
