import lib.pythia.TestSuite;

public final class Q1 extends TestSuite
{
	public Q1()
	{
		super ("q1", new Object[][] {{"AKAYAK"}, {"AVABCD"}, {"A"}, {"AA"}, {""}, {"ABCDEF"}});
	}
	
	private static int longueurPlusLongPalindrome (String s)
	{
@		@q1@@
	}

@		@subproblems@@
	
	public Object studentCode (Object[] data)
	{
		return longueurPlusLongPalindrome ((String) data[0]);
	}
	
	public Object[] parseTestdata (String[] data)
	{
		return new Object[] {data[0]};
	}
	
	public static void main (String[] args)
	{
		new Q1().run();
	}
}