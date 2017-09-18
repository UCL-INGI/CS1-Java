//Inspired from: https://www.gnu.org/software/gettext/manual/html_node/Java.html

package student;
import java.util.ResourceBundle;
import java.util.Locale;

public class Translator {
    
    private static String bundleLocation = "translations_java/MessagesBundle";
    private static ResourceBundle myResources;
    private static boolean ok = true;
    
    public static String _(String s) {
        
        // Try to load once the bundle. If no bundle found, we use the String id instead of the translation.
        if(ok == true){
            try {
                if (myResources == null) {
                    // TOTO: test when inginious can change language at live.
                    myResources = ResourceBundle.getBundle(bundleLocation, Locale.getDefault()/* new Locale("fr")*/, ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));
                }
            } catch (Exception e){ // Change 'Exception'
                // No bundle found
            } finally {
                ok = false;
            }
        }
        
        if (myResources != null){
            try {
                return myResources.getString(s);
            } catch (Exception e) { // Can happen if {} translations are badly translated. (translation invalid).
                return s;
            }
        } else
            return s;
    }
}
