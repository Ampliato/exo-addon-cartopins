package br.com.ampliato.devel.exo.addon.cartopins.templates;
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cindex.s0);
;out.print("${latitude}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cindex.s1);
;out.print("${longitude}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cindex.s2);
;out.print("${refreshtime}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cindex.s3);
;out.print("${zoom}");
;out.print(br.com.ampliato.devel.exo.addon.cartopins.templates.Cindex.s4);

public static class Cindex
{
public static final String s0 = '<script>\n    var latitude = ';
public static final String s1 = ';\n    var longitude = ';
public static final String s2 = ';\n    var refreshtime = ';
public static final String s3 = ';\n    var zoom = ';
public static final String s4 = ';\n    \n</script>\n\n<div class="geopin-container">\n    \n    <div class="row">\n        \n        <div class="col-sm-1">\n        </div>\n        \n        <div class="col-sm-10">\n        \n            <form class="form-inline" role="form" onsubmit="return App.searchCriteria(this.criteria.value)">\n        \n              <div class="form-group">\n        \n                <label class="sr-only" for="criteria">Search:</label>\n        \n                <input type="text" style="min-width: 500px" class="form-control" id="criteria" placeholder="Enter a criteria">\n        \n              </div>\n        \n              <button type="submit" class="btn btn-success">Search</button>\n        \n            </form>\n    \n        </div>\n        \n        <div class="col-sm-1">\n        </div>\n            \n        \n    </div>\n\n    <div id="map"></div>\n    \n</div>';
public static final Map<Integer, juzu.impl.template.spi.juzu.dialect.gtmpl.Foo> TABLE = [
3:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(20,2),'latitude'),
5:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(21,3),'longitude'),
7:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(23,4),'refreshtime'),
9:new juzu.impl.template.spi.juzu.dialect.gtmpl.Foo(new juzu.impl.common.Location(16,5),'zoom')];
}
