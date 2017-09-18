import lib.pythia.TestSuite;

public final class Q1 extends TestSuite
{
	public Q1()
	{
		super ("q1", new Object[][] {{0,0,0,0,0}, {1,2,3,4,5}, {0,2,1,0,10}, {-1,0, 9 , 9,9}});
	}
	
	private static int countDistinctInt (int a, int b, int c, int d, int e)
	{
@		@q1@@
	}

	
	public Object studentCode (Object[] data)
	{
		return countDistinctInt ((Integer) data[0],(Integer) data[1], (Integer) data[2], (Integer) data[3], (Integer) data[4]);
	}
	
	public Object[] parseTestdata (String[] data)
	{
		return new Object[] {Integer.parseInt (data[0]), Integer.parseInt (data[1]), Integer.parseInt (data[2]), Integer.parseInt (data[3]), Integer.parseInt (data[4])};
	}
	
	public static void main (String[] args)
	{
		new Q1().run();
	}
}