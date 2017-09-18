package student;

public class M1EC13Stu {

	public static void equationSecondDegre_det(double a, double b, double c) throws Exception {
    try{
      
		@@q1@@
        
    }
    catch(Throwable e){
	  	Exception custom = new CeciEstGrave("Attention, vous effectuez une opération illégale dans votre code \n"+e.getMessage());
 	   	custom.initCause(e.getCause());
		StackTraceElement[] ste = {};
    	custom.setStackTrace(ste);
	  	throw custom;
    }

	}
	
}

class CeciEstGrave extends Exception {

	public CeciEstGrave(String m){
    		super(m);
    }

	public CeciEstGrave(){
    		super();
    }

}