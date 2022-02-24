package src;


public class CorrectionCD extends CorrectionItem{

	private static int num=100001;
	private int duree;

	public CorrectionCD(String a, String t, Integer d){
	    super(a, t, num);
	    num++;
	    duree=d;
	}

	public String toString(){
	    return (super.toString()+" ("+duree+" s)");
	}
}
