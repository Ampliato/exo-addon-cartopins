$( document ).ready(function() 
{
	App.init();
});

var App = 
{
	markers: null,
	map: null,
	
	init: function() 
	{
		var map = L.map('map').setView([latitude, longitude], zoom);

		L.tileLayer('https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png', {
			maxZoom: 18,
			id: 'geopin-map',
			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
			'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
			id: 'examples.map-i86knfo3'
		}).addTo(map);
		
		App.map = map;
		
		setTimeout(function() {App.searchCriteria("");},500);
	},
	
	searchCriteria: function(criteria) 
	{
		$(".geopin-container").jzAjax({
	        url: "Controller.searchGeopin()",
	        data: {"criteria": criteria}
	    }).done(function(data) {
	        var x2js = new X2JS();          
	        var pins = x2js.xml2json(data);
	        App.renderPins(pins.geoDataContainer.geoDatas);
    	}).error(function(jqxhr, text, error){
			alert("error: " + text + " - " + error);
		});
		
		return false;
	},
	
	
	renderPins: function(pins) 
	{
		// remove anterior
		if (App.markers)
			App.map.removeLayer(App.markers);
	    
		App.markers = new L.FeatureGroup();

		if (!pins.color && !pins.length)
			return;
		
		if (pins.color)
			pins = [pins];
		
		for (var i = 0; i < pins.length; i++) 
		{
			var pin = pins[i];
			
			var icon = L.MakiMarkers.icon({icon: "marker-stroked", color: pin.color, size: "m"});
			
			var marker = L.marker([pin.lat, pin.lng], {icon: icon});
			marker.bindPopup("<b>"+pin.name + " / " + pin.count+"!</b>");
			
			App.markers.addLayer(marker);
			App.map.addLayer(App.markers);
		}
		
	},
	
	testDBConnection: function()
	{
		var d = {};
		var form = document.forms.cartopinconfigform;

		for(var i = 0; i < form.elements.length; i++)
		   d[form.elements[i].name] = form.elements[i].value;
		
		$("#cartopinconfigform").jzAjax({
	        url: "Controller.testDBConnection()",
	        data: d,
	    }).done(function(data) {
	    	if (data.status =="ok"){
	    		alert("Connection successfully tested.");
	    	} else {
	    		alert("Error testing connection: " +data.status);
	    	}
    	}).error(function(jqxhr, text, error){
			alert("error: " + text + " - " + error);
		});
	}
};