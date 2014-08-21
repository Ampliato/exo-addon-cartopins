package br.com.ampliato.devel.exo.addon.cartopins;
import java.util.HashSet;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ampliato.devel.exo.addon.cartopins.conn.DBFetcher;


public class CartoPinsPreferencesValidator implements PreferencesValidator{
	 /** Designated Logger for this class. */
    private final Log log = LogFactory.getLog(getClass().getName());
    
    public void validate(PortletPreferences preferences) throws ValidatorException 
    {
        Set errorsSet = new HashSet();
        errorsSet.clear();
        
        //Though we could validate title, we won't.  Just the SQL.
        String sql = preferences.getValue("sql","BAD SQL");
        
        //We've ensured the data is there.  Now, just call our QueryBean's execute method.
        //If the SQL parse fails, catch it and ask user to check their form data.
        try {
            DBFetcher qb = new DBFetcher(preferences);
            qb.executeQuery("SELECT 1;");
        }
        catch(Exception e) {
//            log.warn("Exception occured executing QueryBean executeQuery in  " +
//            "QueryPreferencesValidator. sql : " + sql);
            errorsSet.add(sql);
            throw new ValidatorException(e.toString(),errorsSet);
        }
    }
}
