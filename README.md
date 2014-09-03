Ampliato CartoPins
===
formerly exo-addon-cartopins
___


  
**Ampliato CartoPìns** is an [eXo Platform] add-on, developed by [Ampliato], built using [Juzu Framework] as a bridge to [Portlet][1] technology. Since eXo is built upon [JBoss GateIn Portal], this software runs gracefully in GateIn, as an addon too. 

___
#### General Purpose 
The main goal of this project is allow you to render and search (using ajax) geographic SQL data inside [OpenStreet Maps] using [Leaflet] library. So:

* Ampliato CartoPins is a portlet developed using Juzu Framework.
* It's written in Java, using only two template views: one for EDIT mode and other one for VIEW mode.
* It runs inside eXo Platform (tested on community version - 4.0.{6,7,8}) and JBoss GateIn (tested on 3.{5,6,7,8}.0, )
* It's controlled by a single class, that provides both view mode above.
* It connects to database backend using JDBC driver. It works with different database providers.
* It's very configurable, enabling you to define your own SQL queries, just exposing your data using some pre-defined columns.
* It allows you perform search into your data domain, using Ajax requests, and multiple criteria searches simultaneously.
* It allows you to render colored pins in maps. It's up to you to define your pins colors.
* It allows you to render 1:N related data, for each of your pins. The data is displayed inside Leaflet ballons.
* It allows you to define a period for auto-refresh your map and your pins.



#### Example of Use
This example of use is about displaying some hipotetically customer accounts into map and the currently monthly invoices tax, against individual customers sell target. It allows you to search your data by vendor, account, region, city, state and other attibutes.

######Install
To install this addon just drop the war file (target folder) into your container "webapps" folder. Watch the log expecting the auto-deploy behavior.

######Placeholder
Open your container's site on browser, and go to Administrator preferences, specifically into "Applications" sections. Categorize the Ampliato CartoPins addon by your own. Back to your portal page, edit it, and place this addon wherever you need.

######Configurations
Click on preferences in page edit mode, and go to Portlet Preferences tab.

This addon support some preferences:

* Zoom:
* Refresh Time:
* Latitude:
* Longitude

* Driver: asdasda (DO NOT FORGET TO PUT JDBC DRIVER JAR INTO LIB's CONTAINER FOLDER)
* Database:
* Username:
* Password:
* SQLQuery:


*Query Structure*
asdasd



*IMPORTANT: eXo and GateIn portals force "settings" tab to hide "Save and Close" button. So, when changing addon settings, do not forget to click first on save, and then on close button. The only way I discover to put then together is placing some generic configurations defined in portlet.xml file. But this just deny some custom features, as "Test Connection" button, for example.*


######Search data
asd




#### Considerations

This project has some considerations and premises about using it.

######GEOCODE YOUR DATA
This project has as a premise you already heave you data [geocoded] [2]. Geocoding data is a pretty simple process, but in a 99.9% of cases involves costs and licences about rendering the same geocoded data into maps, and that's the reason of this premise. For lucky, there are some vendors that can help you to geocode you data:

* [Google Maps] [2]
* [CloudMade] [3]
* [GeoCode.US] [4]
* [GisGraphy] - An opensource tool that merges data of OSM and GeoNames
* [HERE.COM]
* [MapQuest]


######MAKE YOUR QUERY RUN QUICKLY
Because this addon is ajax powered, it is intented to search your data on a server side, and this search occurs without telling your database that your are actually search. In other words, it works just looking for data in the Java server container. Beside this, your server must refresh you data periodically (it's a settting), and the results must return fast, lightweight. So, to reach this, materialize your data into views, or replicate your complex data query into a phisical table, and then, configure this addon to search this view/table.

######DO NOT USE TONS OF RESULTS
Leaflet render pins as DOM elements, on demand, sincronously. Given that, try to assembly your query to fetch just some few result, when few means hundreds of results. That enable users to make quick searches and reduces the ammount of server processed data, as the data traffed between your browser and your server.


####Development
Learn here how to develop, build, deploy and test your portlet.
######Learning
If you reach this project, and just does not know what a Portlet is or even what Juzu is, just take a look at this nice posts:


* There is some interesting material [here] [5], [here][6] and [here][7].
* [Juzu Reference Guide] [8]


The development flow of this addon actually is hosted into our's Gitlab instance. This code is just a stable mirror of the project, so, do not expect recent activity in the code.

######BUILD PROCESS

######TEST PROCESS

######DEPLOY PROCESS


Version
--

1.0 This is the first one release.


License
----

GPLv2

[eXo Platform]:http://www.exoplatform.com/
[JBoss GateIn Portal]:http://gatein.jboss.org/
[Juzu Framework]:http://juzuweb.org/
[OpenStreet Maps]:http://www.openstreetmap.org/about
[Leaflet]:http://leafletjs.com/
[GisGraphy]:http://www.gisgraphy.com/
[HERE.COM]:http://developer.here.com/frontpage
[MapQuest]:http://developer.mapquest.com/web/products/dev-services/geocoding-ws
[Ampliato]:http://www.ampliato.com.br/

[1]:http://www.developer.com/java/web/article.php/3547186/Introduction-to-the-Java-Portlet-Specification.htm
[2]:https://developers.google.com/maps/documentation/geocoding/
[3]:http://cloudmade.com/
[4]:http://geocoder.us/


[5]:http://www.developer.com/java/web/article.php/3366111/Understanding-the-Java-Portlet-Specification.htm
[6]:http://www.developer.com/java/web/article.php/3372881/Developing-to-the-Java-Portlet-Specification.htm
[7]:http://www.developer.com/java/web/article.php/10935_3846491_2/An-Introduction-to-Java-Enterprise-Portals-and-Portlet-Development.htm
[8]:http://juzuweb.org/tutorial/index.html