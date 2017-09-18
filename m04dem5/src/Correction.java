package src;
/**
 *  @author François MICHEL
 */


public class Correction{

	public static boolean containsChar(String s1, String s2){
		for(int i = 0; i < s1.length(); i++){
		    if(!s2.contains(String.valueOf(s1.charAt(i)))){
			return false;
		    }
		}
		return true;
	}
}
