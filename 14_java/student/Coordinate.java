// Coordinate.java

/**
 * Class representing a coordinate in the cartesian plane
 * 
 * @author Sebastien Combefis
 * @version 22 nov. 2008
 */
package student;

public class Coordinate
{
	// Instance variables
	private final double x, y;
	
	/**
	 * Constructor
	 * 
	 * @pre -
	 * @post An instance of this is created
	 *       This represents the (x, y) coordinate
	 */
	public Coordinate (double x, double y)
	{
		this . x = x;
		this.y = y;
	}
	
	/**
	 * Get the x-position of this coordinate
	 * 
	 * @pre -
	 * @post The returned value contains the x-coordinate of this coordinate
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Get the y-position of this coordinate
	 * 
	 * @pre -
	 * @post The returned value contains the y-coordinate of this coordinate
	 */
	public double getY()
	{
		return y;
	}
}
