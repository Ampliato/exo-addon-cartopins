package br.com.ampliato.devel.exo.addon.cartopins.templates;
import juzu.impl.plugin.template.metadata.TemplateDescriptor;
import juzu.impl.plugin.template.TemplateService;
@javax.annotation.Generated({})
public class index extends juzu.template.Template
{
@javax.inject.Inject
public index(TemplateService templatePlugin)
{
super(templatePlugin, "/br/com/ampliato/devel/exo/addon/cartopins/templates/index.gtmpl");
}
public static final juzu.impl.plugin.template.metadata.TemplateDescriptor DESCRIPTOR = new juzu.impl.plugin.template.metadata.TemplateDescriptor("/br/com/ampliato/devel/exo/addon/cartopins/templates/index.gtmpl",0xbac2481a81d67ea9L,br.com.ampliato.devel.exo.addon.cartopins.templates.index.class,juzu.impl.template.spi.juzu.dialect.gtmpl.GroovyTemplateStub.class);
public Builder builder() {
return new Builder();
}
public Builder with() {
return (Builder)super.with();
}
public class Builder extends juzu.template.Template.Builder
{
}
}
