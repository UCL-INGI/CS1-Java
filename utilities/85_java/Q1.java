public class Q1
{
	private static int countPosIntBetween (int a, int b)
	{
@		@q1@@
	}
    
	public static void main (String[] args)
	{
        int a, b;
        int max = 10;
        int min = -10;
        int expectedAnswer;
        int studentAnswer;
        int borneMin, borneMax;
        for (int i = 0; i < 10; i++){
            a = (int)Math.floor(Math.random()*(max-min+1)+min);
            b = (int)Math.floor(Math.random()*(max-min+1)+min);
            
            if (a < b){
                borneMin = a;
                borneMax = b;
            } else {
                borneMin = b;
                borneMax = a;
            }
            
            for(int i = borneMin; i < borneMax; i++){
                if(i > 0) expectedAnswer++;
            }
            
            studentAnswer = Q1.countPosIntBetween(a,b);
            
            if(studentAnswer != expectedAnswer) throw new AssertionError("Le résultat attendu est : " + expectedResult + 
                                                                         " mais votre programme a donné : " + studentAnswer);
        }
	}
}