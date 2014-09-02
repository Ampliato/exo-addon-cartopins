package br.com.ampliato.devel.exo.addon.cartopins.templates;
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s0);
;out.print(br.com.ampliato.devel.exo.addon.cartopins.Controller_.save(null,null,null,null,null,null,null,null,null,null,null));;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s1);
;out.print("${latitude}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s2);
;out.print("${longitude}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s3);
;out.print("${refreshtime}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s4);
;out.print("${zoom}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s5);
;out.print("${driverOptions}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s6);
;out.print("${host}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s7);
;out.print("${port}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s8);
;out.print("${database}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s9);
;out.print("${username}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s10);
;out.print("${password}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s11);
;out.print("${query}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cedit.s12);

public static class Cedit
{
public static final String s0 = '<form class="UIForm" action="';
public static final String s1 = '" id="cartopinconfigform" method="post">\n\n    <table class="UIFormGrid">\n    \n        <tr>\n            <td class="text-center" colspan="4" class="FieldLabel" scope="row">\n                <span><strong>Map Settings</strong></span>\n            </td>\n        </tr>\n    \n        <tr>\n            <td class="FieldLabel" scope="row">\n                <label for="latitude">Latitude:</label>\n            </td> \n    \n            <td class="FieldComponent">\n                <input name="latitude" value="';
public static final String s2 = '" type="text"></input>\n            </td>\n            \n            <td class="FieldLabel">\n                <label for="longitude">Longitude:</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="longitude" value="';
public static final String s3 = '" type="text"></input>\n            </td>\n\n        </tr>\n\n        <tr>\n            <td class="FieldLabel">\n                <label for="refreshtime">Refresh Time (secs):</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="refreshtime" value="';
public static final String s4 = '" type="number"></input>\n            </td>\n            \n            <td class="FieldLabel">\n                <label for="zoom">Map Zoom (1-13):</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="zoom" value="';
public static final String s5 = '" type="number"></input>\n            </td>\n        </tr>\n\n        <tr>\n            <td class="text-center" colspan="4" class="FieldLabel" scope="row">\n                 <span><strong>Database Settings</strong></span>\n            </td>\n        </tr>\n\n        <tr>\n            <td class="FieldLabel" scope="row">\n                <label for="driver">Driver:</label>\n            </td> \n    \n            <td class="FieldComponent">\n                <select name="driver">';
public static final String s6 = '</select>\n            </td>\n\n            <td class="FieldLabel">\n                <label for="host">Host:</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="host" value="';
public static final String s7 = '" type="text"></input>\n            </td>\n\n        </tr>\n        \n        <tr>\n\n            <td class="FieldLabel">\n                <label for="port">Port:</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="port" value="';
public static final String s8 = '" type="number"></input>\n            </td>\n\n\n            <td class="FieldLabel">\n                <label for="database">Database:</label>\n            </td>\n                \n            <td class="FieldComponent">\n                <input name="database" value="';
public static final String s9 = '" type="text"></input>\n            </td>\n\n        </tr>\n\n        <tr>\n\n            <td class="FieldLabel">\n                <label for="username">Username:</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="username" value="';
public static final String s10 = '" type="text"></input>\n            </td>\n\n            <td class="FieldLabel">\n                <label for="password">Password:</label>\n            </td>\n            \n            <td class="FieldComponent">\n                <input name="password" value="';
public static final String s11 = '" type="password"></input>\n            </td>\n\n        </tr>\n\n        <tr >\n            <td class="FieldLabel">\n                <label for="query">SQL Query:</label>\n            </td>\n            \n            <td colspan="3">\n                <textarea style="margin-left: 4px; min-width:558px" rows="10" cols="100" name="query">';
public static final String s12 = '</textarea>\n            </td>\n        </tr>\n        \n    </table>\n    \n</form>\n\n<div style="padding-top:10px;">\n    <p>Your query must provide 7 different columns, and a bunch of rows as your needs. \n        The columns provided must be strictly in this provided order bellow, along those names. Remeber that search occurs on server side,\n        as ajaxfied search based on your "meta" column. So, if you provide millions of tuples, this portlet may crash your\n        container\'s memory or cpu\'s resources. Beside this, you query must execute quick, because of "Refresh Time" parameter, giving\n        users a good usability and responsiveness sense. Don\'t forget to put the driver class (JDBC driver lib) into classpath. Is is usually done\n        by {tomcat-folder}/lib path.\n    </p>\n    <ol>\n        <li><strong>[VARCHAR] \'name\':</strong> The associated displayed name for pin.</li>\n        <li><strong>[VARCHAR] \'count\':</strong> The associated count for pin.</li>\n        <li><strong>[VARCHAR] \'lat\':</strong> The Latitude for pin.</li>\n        <li><strong>[VARCHAR] \'lng\':</strong> The Longitude for pin.</li>\n        <li><strong>[VARCHAR] \'color\':</strong> The color (e.g. #aaaaaa) associated for pin. It must be a 6-digit hex color.</li>\n        <li><strong>[VARCHAR] \'tokens\':</strong> A comma separated string, that will be used to search your pins. (e.g. industry,sao paolo,brazil)</li>\n        <li><strong>[VARCHAR] \'children\':</strong> A comma separated string, that will be used to split (line feed) details about your main entity. The search engine will employ this list in search mechanism too. (e.g. invoice items list, 1:N details about an occurence)</li>\n    </ol>\n</div>\n\n<div style="padding-top:10px;" class="text-center">\n    <button title="We will execute a \'SELECT 1\' query only." onclick="App.testDBConnection();">Test Connection</button>\n    <input type="submit" value="Save" form="cartopinconfigform"/>\n</div>';
public static final Map<Integer, juzu.impl.template.spi.juzu.dialect.gtmpl.Foo> TABLE = [
16:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(43,80),'port'),
18:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(47,89),'database'),
4:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(47,17),'latitude'),
20:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(47,101),'username'),
6:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(48,25),'longitude'),
22:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(47,109),'password'),
8:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(50,36),'refreshtime'),
24:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(103,120),'query'),
10:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(43,44),'zoom'),
12:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(39,60),'driverOptions'),
14:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(43,68),'host')];
}
