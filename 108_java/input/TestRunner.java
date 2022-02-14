import org.junit.runner.JUnitCore;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.Description;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(Mission9Test.class);
	if (result.wasSuccessful())
		System.out.println("OK");
	else{		
		for (Failure failure : result.getFailures()) {
			if(failure.getException().getClass()==java.lang.AssertionError.class){												
				String [] desc = failure.toString().split("\\n");
				String where = desc[0];
				String classe = where.split("\\(")[0];
				classe = Character.toUpperCase(classe.charAt(0)) + classe.substring(1);

			
      	  		System.out.println("Echec du test de la classe " + classe  + " : Champs ou méthodes demandés absents");
			}
			else if(failure.getException().getClass()==java.lang.reflect.InvocationTargetException.class){
				String [] desc = failure.toString().split("\\n");
				String where = desc[0];
				String classe = where.split("_")[0];
				classe = Character.toUpperCase(classe.charAt(0)) + classe.substring(1);
				String method = where.split("_")[1].split("\\(")[0];
				System.out.println("Exception lancée dans la méthode " +classe + "."+ method + "() : "+ failure.getException().getCause() );
			}
			else 
				System.out.println(failure);
		}
		
    }
	
  }
} 