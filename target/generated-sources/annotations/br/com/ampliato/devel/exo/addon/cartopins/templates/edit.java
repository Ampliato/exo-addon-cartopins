package br.com.ampliato.devel.exo.addon.cartopins.templates;
import juzu.impl.plugin.template.metadata.TemplateDescriptor;
import juzu.impl.plugin.template.TemplateService;
@javax.annotation.Generated({})
public class edit extends juzu.template.Template
{
@javax.inject.Inject
public edit(TemplateService templatePlugin)
{
super(templatePlugin, "/br/com/ampliato/devel/exo/addon/cartopins/templates/edit.gtmpl");
}
public static final juzu.impl.plugin.template.metadata.TemplateDescriptor DESCRIPTOR = new juzu.impl.plugin.template.metadata.TemplateDescriptor("/br/com/ampliato/devel/exo/addon/cartopins/templates/edit.gtmpl",0x15167cafa6e97e2aL,br.com.ampliato.devel.exo.addon.cartopins.templates.edit.class,juzu.impl.template.spi.juzu.dialect.gtmpl.GroovyTemplateStub.class);
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
