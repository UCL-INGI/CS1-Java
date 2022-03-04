package src;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Correction {
    
	public static int countDistinctInt (int a, int b, int c, int d, int e)
	{        
		Set<Integer> h = new HashSet<Integer>(Arrays.asList(a, b, c, d, e));

        return h.size();
	}
    
}