package br.com.ampliato.devel.exo.addon.cartopins;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
@WebServlet(name="CartopinsServlet",urlPatterns="/")
public class CartopinsServlet extends juzu.bridge.servlet.JuzuServlet {
@Override
protected String getApplicationName(javax.servlet.ServletConfig config) {
return "br.com.ampliato.devel.exo.addon.cartopins";
}
}
