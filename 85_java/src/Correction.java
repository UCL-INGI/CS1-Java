package src;

public class Correction {
    public static int countPosIntBetween (int a, int b)
	{
        int borneMin = a < b ? a : b;
        int borneMax = borneMin == b ? a : b;
        
        int counter = 0;
        
        for (int i = borneMin; i <= borneMax; i++){
            if(i > 0) counter++;
        }
        
        return counter;
	}
}