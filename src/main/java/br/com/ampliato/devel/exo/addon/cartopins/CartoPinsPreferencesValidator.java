package br.com.ampliato.devel.exo.addon.cartopins;
import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

public class CartoPinsPreferencesValidator implements PreferencesValidator
{
    //private final Log log = LogFactory.getLog(getClass().getName());
    
    /**
     * We can use this class to validate preferences. It will be called after preferences.store()
     * during portlet settings save, and the hook is declared under portlet.xml as <preferences-validator>
     */
    public void validate(PortletPreferences preferences) throws ValidatorException 
    {
    }
}
