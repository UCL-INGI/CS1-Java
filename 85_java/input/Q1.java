import lib.pythia.TestSuite;

public final class Q1 extends TestSuite
{
	public Q1()
	{
		super ("q1", new Object[][] {{0,1}, {0,1}, {1,0}, {-1,0}, {0, -1}, {0, 2}, {-2, 0}, {-2,2}, {-2,-2}, {0,-2}, {2,-2}});
	}
	
	private static int countPosIntBetween (int a, int b)
	{
@		@q1@@
	}	
	public Object studentCode (Object[] data)
	{		
		return countPosIntBetween((Integer) (data[0]), (Integer) (data[1]));
	}
	
	public Object[] parseTestdata (String[] data)
	{
		return new Object[] {Integer.parseInt (data[0]), Integer.parseInt (data[1])};
	}
	
	public static void main (String[] args)
	{
		new Q1().run();
	}
}