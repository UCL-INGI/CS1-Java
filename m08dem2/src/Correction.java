package StudentCode;

import java.lang.StringBuffer;

public class Correction{

	public static String repeat(String s, int n){
		if(s == null || n == 0)
			return null;
		StringBuffer strb = new StringBuffer("");
		for(int i = 0;i < n ; i++)
			strb.append(s);
		return strb.toString();
	}

}
