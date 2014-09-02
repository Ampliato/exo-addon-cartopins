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
		
		setTimeout(function() {App.searchCriteria("");},1000);
		
		setInterval(App.refreshSearch, refreshtime * 1000);
	},
	
	refreshSearch: function() {
		App.searchCriteria($("#criteria").val());
	},
	
	searchCriteria: function(criteria) 
	{
		$('#loading-indicator').css('display', '');
		
		$(".geopin-container").jzAjax({
	        url: "Controller.searchGeopin()",
	        data: {"criteria": criteria}
	    }).done(function(data) {
	    	$('#loading-indicator').css('display', 'none');
	        var x2js = new X2JS();          
	        var pins = x2js.xml2json(data);
	        App.renderPins(pins.geoDataContainer.geoDatas);
    	}).error(function(jqxhr, text, error){
    		$('#loading-indicator').css('display', 'none');
			alert("[AMPLIATO CARTOPINS] General Error: " + text + " - " + error);
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
			
			var popupContent = null;
			var tokens = pin.tokens ? pin.tokens.split(",") : "";
			var children = pin.children ? pin.children.join("<br/>") : "No related content.";
			
			popupContent = "" +
			"<h4><b>"+pin.name + " (" + pin.count+ ")</b></h4>" +
			"<h6>" + tokens + "</h6>" +
			"<br/>" + 
			"<h4>Related Data</h4>" +
			"<h6>"+children+"</h6>";
			
			marker.bindPopup(popupContent);

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
	    		alert("[AMPLIATO CARTOPINS] Connection successfully tested.");
	    	} else {
	    		alert("[AMPLIATO CARTOPINS] Error testing connection: " +data.status);
	    	}
    	}).error(function(jqxhr, text, error){
			alert("[AMPLIATO CARTOPINS] General Error: " + text + " - " + error);
		});
	}
};