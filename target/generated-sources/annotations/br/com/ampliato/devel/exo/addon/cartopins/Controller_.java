package br.com.ampliato.devel.exo.addon.cartopins;
import juzu.impl.request.ControllerHandler;
import juzu.impl.request.ControlParameter;
import juzu.impl.request.PhaseParameter;
import juzu.impl.request.ContextualParameter;
import juzu.impl.request.BeanParameter;
import juzu.impl.common.Tools;
import java.util.Arrays;
import juzu.request.Phase;
import juzu.impl.plugin.controller.descriptor.ControllerDescriptor;
import javax.annotation.Generated;
import juzu.impl.common.Cardinality;
import juzu.impl.request.Request;
@Generated(value={})
public class Controller_ {
private static final Class<br.com.ampliato.devel.exo.addon.cartopins.Controller> TYPE = br.com.ampliato.devel.exo.addon.cartopins.Controller.class;
private static final ControllerHandler<Phase.View> method_0 = new ControllerHandler<Phase.View>("Controller.index",Phase.VIEW,TYPE,Tools.safeGetMethod(TYPE,"index",juzu.request.RequestContext.class), Arrays.<ControlParameter>asList(new ContextualParameter("reqContext",juzu.request.RequestContext.class)));
public static Phase.View.Dispatch index() { return Request.createViewDispatch(method_0); }
private static final ControllerHandler<Phase.Resource> method_1 = new ControllerHandler<Phase.Resource>("Controller.searchGeopin",Phase.RESOURCE,TYPE,Tools.safeGetMethod(TYPE,"searchGeopin",java.lang.String.class), Arrays.<ControlParameter>asList(new PhaseParameter("criteria",java.lang.String.class,java.lang.String.class,Cardinality.SINGLE,null)));
public static Phase.Resource.Dispatch searchGeopin(java.lang.String criteria) { return Request.createResourceDispatch(method_1,(Object)criteria); }
public static final ControllerDescriptor DESCRIPTOR = new ControllerDescriptor(TYPE,Arrays.<ControllerHandler<?>>asList(method_0,method_1));
}
