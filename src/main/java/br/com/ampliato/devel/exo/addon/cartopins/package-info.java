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
@Scripts({
//	@Script(id="jquery", value = "http://code.jquery.com/jquery-1.11.1.js", location = AssetLocation.URL),
//    @Script(id="bootstrap", value = "http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js", location = AssetLocation.URL, depends = {"jquery"}),
//    @Script(id="leaflet.js", value = "http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js", location = AssetLocation.URL, depends = {"jquery", "bootstrap"}),
//    @Script(id="jquery", value = "/portal/scripts/3.8.1/SHARED/jquery.js", location = AssetLocation.URL),
//    @Script(id="bootstrap", value = "/portal/scripts/3.8.1/SHARED/bootstrap.js", location = AssetLocation.SERVER),
//    @Script(id = "leaflet.js", value = "leaflet.js", depends = {"jquery"}),
//    @Script(id="bootstrap", value = "bootstrap.min.js", depends = {"jquery"}),

	@Script(id="jquery", value = "jquery-1.11.1.min.js"),
    @Script(id="leaflet-src.js", value = "leaflet-src.js", depends = {"jquery"}),
    @Script(id="Leaflet.MakiMarkers.js", value = "Leaflet.MakiMarkers.js", depends = {"leaflet-src.js"}),
    @Script(id="xml2json.min.js", value = "xml2json.min.js", depends = {"Leaflet.MakiMarkers.js"}),
    @Script(id="app.js", value = "app.js", depends = {"xml2json.min.js"})
})
@Stylesheets({
	@Stylesheet("bootstrap.min.css"),
	@Stylesheet("bootstrap-theme.min.css"),
	@Stylesheet("leaflet.css"),
	@Stylesheet("app.css")
})

@Assets("*")
@juzu.Application
//@juzu.plugin.servlet.Servlet(value = "/")
package br.com.ampliato.devel.exo.addon.cartopins;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Scripts;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.asset.Stylesheets;

