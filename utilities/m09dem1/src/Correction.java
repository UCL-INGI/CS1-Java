package StudentCode;

/**
 *	@author: François MICHEL
 */
 
public class Correction{

	Integer i;

	public int compareTo(Object o){
		return this.compareTo((Correction)o);
	}
	
	public int compareTo(Integer in){
		return i.compareTo(in);
	}
	
	public Correction(int n){
		i = new Integer(n);
	}
}
