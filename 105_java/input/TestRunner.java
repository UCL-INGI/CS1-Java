import org.junit.runner.JUnitCore;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.Description;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class TestRunner {
  public static void main(String[] args) throws UnsupportedEncodingException {
	//PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
	PrintStream out = new PrintStream(System.out, true, "UTF-8"); 
    Result result = JUnitCore.runClasses(Mission7Test.class);
	if (result.wasSuccessful())
		out.println("OK");
	else{	
		out.println("KO");
		for (Failure failure : result.getFailures()) {
			if(failure.getException().getClass()==java.lang.AssertionError.class){	
				String msg = "";
				//Obtention des infos sur l'echec
				String meth = getMethodName(failure);
				String classe = getClassName(failure);				
				//System.out.println(failure.getMessage());				
				String [] desc = failure.getMessage().trim().split("\n");
				if(desc.length==3){
					msg = desc[0];
				}
				String [] values = getValues(failure);	
						
				String output = "F:"+classe+":"+meth + ":"+values[1]+":"+ values[0];
				if(msg!="") output+=(":"+msg); 
      	  		out.println(output);
			}
			else if(failure.getException().getClass()==java.lang.reflect.InvocationTargetException.class){
				String [] desc = failure.toString().split("\\n");
				String where = desc[0];
				//String classe = where.split("_")[0];
				//classe = Character.toUpperCase(classe.charAt(0)) + classe.substring(1);
				String classe = getClassName(failure);
				String method = getMethodName(failure);
				out.println("E:"+classe + ":" +method + ":"+ failure.getException().getCause().toString().replace("\n", " "));
			}
			else 
				out.println(failure + " "+ failure.getException().getCause().toString().replace("\n", " "));
		}
		
    }
	
  }
  public static String getMethodName(Failure f){
	  String header = f.getTestHeader();
	  //On retire les parenthèses et leur contenu
	  header = header.split("\\(")[0];
	  //Le test est de la forme <classe>_<methode>
	  if(header.contains("_")){
		  return header.split("_")[1];
	  }
	  return header;
  }
  public static String getClassName(Failure f){
	  String header = f.getTestHeader();
	  //On retire les parenthèses et leur contenu
	  header = header.split("\\(")[0];
	  //Le test est de la forme <classe>_<methode>
	  if(header.contains("_")){
		  header = header.split("_")[0];
	  }
	  return Character.toUpperCase(header.charAt(0)) + header.substring(1);
  }
  public static String[] getValues(Failure f){
	String [] val = new String[2];  
	//Obtention des valeurs attendues/obtenues
	
	Pattern p1=Pattern.compile("Expected: (.*)");
	Pattern p2=	Pattern.compile("but: was (.*)");
	Matcher m=p1.matcher(f.toString());
	if(m.find())
		val[0]=m.group(1).replace("<", "").replace(">", "").trim();
	m=p2.matcher(f.toString());
	if(m.find())
		val[1]=m.group(1).replace("<", "").replace(">", "").trim();
	return val;
  
  }
} 