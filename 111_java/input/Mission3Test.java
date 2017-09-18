import static org.hamcrest.CoreMatchers.equalTo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Mission3Test
{
	Class<?> libMath;
	Method method;
	ThreadMXBean threadMXB;
	long start;
	long end;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void before() throws ClassNotFoundException
	{
		libMath = Class.forName("LibMath");
		threadMXB = ManagementFactory.getThreadMXBean();
	}
	
	public void printTime(String name, Object... parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		start = threadMXB.getCurrentThreadCpuTime();
		method.invoke(null, parameters);
		end = threadMXB.getCurrentThreadCpuTime();
		
		System.out.println("€" + name);
		System.out.println(end - start);
	}
	
	public void checkMethod(Class<?> returnType, String name, Class<?>... parameters) throws NoSuchMethodException, SecurityException
	{
		method = libMath.getDeclaredMethod(name, parameters);
		if (!method.getReturnType().equals(returnType))
			throw new NoSuchMethodException(name + "() : La valeur de retour de la méthode n'est pas du type attendu");
		method.setAccessible(true);
	}
	
	@Test
	public void average() throws Throwable
	{
		checkMethod(double.class, "average", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 2.0, 7.0", (Double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(4.0));
		collector.checkThat("-3.0, 2.0, 7.0",(Double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(2.0));
		collector.checkThat("3.0, -1.0, 7.",(Double) method.invoke(null, 3.0, -1.0, 7.0), equalTo(3.0));
		collector.checkThat("3.0, 1.0, -7.0",(Double) method.invoke(null, 3.0, 1.0, -7.0), equalTo(-1.0));
		collector.checkThat("-3.0, -1.0, 7.0",(Double) method.invoke(null, -3.0, -1.0, 7.0), equalTo(1.0));
		collector.checkThat("3.0, -2.0, -7.0",(Double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-2.0));
		collector.checkThat("-3.0, 1.0, -7.0",(Double) method.invoke(null, -3.0, 1.0, -7.0), equalTo(-3.0));
		collector.checkThat("-3.0, -2.0, -7.0",(Double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-4.0));
	}
	
	@Test
	public void average_equal() throws Throwable
	{
		checkMethod(double.class, "average", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 3.0, 3.0",(Double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("3.0, 3.0, 0.0",(Double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(2.0));
		collector.checkThat("3.0, 0.0, 3.0",(Double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(2.0));
		collector.checkThat("0.0, 3.0, 3.0",(Double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(2.0));
		collector.checkThat("0.0, 0.0, 3.0",(Double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(1.0));
		collector.checkThat("0.0, 3.0, 0.0",(Double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(1.0));
		collector.checkThat("3.0, 0.0, 0.0",(Double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(1.0));
		collector.checkThat("0.0, 0.0, 0.0",(Double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
	}
	
	@Test
	public void median() throws Throwable
	{
		checkMethod(double.class, "median", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 2.0, 7.0",(Double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(3.0));
		collector.checkThat("-3.0, 2.0, 7.0",(Double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(2.0));
		collector.checkThat("3.0, -2.0, 7.0",(Double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(3.0));
		collector.checkThat("3.0, 2.0, -7.0",(Double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(2.0));
		collector.checkThat("-3.0, -2.0, 7.0",(Double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(-2.0));
		collector.checkThat("3.0, -2.0, -7.0",(Double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-2.0));
		collector.checkThat("-3.0, 2.0, -7.0",(Double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(-3.0));
		collector.checkThat("-3.0, -2.0, -7.0",(Double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-3.0));
	}
	
	@Test
	public void median_equal() throws Throwable
	{
		checkMethod(double.class, "median", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 3.0, 3.0",(Double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("3.0, 3.0, 0.0",(Double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(3.0));
		collector.checkThat("3.0, 0.0, 3.0",(Double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(3.0));
		collector.checkThat("0.0, 3.0, 3.0",(Double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("0.0, 0.0, 3.0",(Double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(0.0));
		collector.checkThat("0.0, 3.0, 0.0",(Double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(0.0));
		collector.checkThat("3.0, 0.0, 0.0",(Double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(0.0));
		collector.checkThat("0.0, 0.0, 0.0",(Double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
	}
	
	@Test
	public void maximum() throws Throwable
	{
		checkMethod(double.class, "maximum", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 2.0, 7.0",(Double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(7.0));
		collector.checkThat("-3.0, 2.0, 7.0",(Double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(7.0));
		collector.checkThat("3.0, -2.0, 7.0",(Double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(7.0));
		collector.checkThat("3.0, 2.0, -7.0",(Double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(3.0));
		collector.checkThat("-3.0, -2.0, 7.0",(Double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(7.0));
		collector.checkThat("3.0, -2.0, -7.0",(Double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(3.0));
		collector.checkThat("-3.0, 2.0, -7.0",(Double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(2.0));
		collector.checkThat("-3.0, -2.0, -7.0",(Double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-2.0));
	}
	
	@Test
	public void maximum_equal() throws Throwable
	{
		checkMethod(double.class, "maximum", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 3.0, 3.0",(Double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("3.0, 3.0, 0.0",(Double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(3.0));
		collector.checkThat("3.0, 0.0, 3.0",(Double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(3.0));
		collector.checkThat("0.0, 3.0, 3.0",(Double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("0.0, 0.0, 3.0",(Double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(3.0));
		collector.checkThat("0.0, 3.0, 0.0",(Double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(3.0));
		collector.checkThat("3.0, 0.0, 0.0",(Double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(3.0));
		collector.checkThat("0.0, 0.0, 0.0",(Double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
	}
	
	@Test
	public void minimum() throws Throwable
	{
		checkMethod(double.class, "minimum", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 2.0, 7.0",(Double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(2.0));
		collector.checkThat("-3.0, 2.0, 7.0",(Double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(-3.0));
		collector.checkThat("3.0, -2.0, 7.0",(Double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(-2.0));
		collector.checkThat("3.0, 2.0, -7.0",(Double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(-7.0));
		collector.checkThat("-3.0, -2.0, 7.0",(Double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(-3.0));
		collector.checkThat("3.0, -2.0, -7.0",(Double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-7.0));
		collector.checkThat("-3.0, 2.0, -7.0",(Double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(-7.0));
		collector.checkThat("-3.0, -2.0, -7.0",(Double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-7.0));
	}
	
	@Test
	public void minimum_equal() throws Throwable
	{
		checkMethod(double.class, "minimum", double.class, double.class, double.class);
		
		collector.checkThat("3.0, 3.0, 3.0",(Double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
		collector.checkThat("3.0, 3.0, 0.0",(Double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(0.0));
		collector.checkThat("3.0, 0.0, 3.0",(Double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(0.0));
		collector.checkThat("0.0, 3.0, 3.0",(Double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(0.0));
		collector.checkThat("0.0, 0.0, 3.0",(Double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(0.0));
		collector.checkThat("0.0, 3.0, 0.0",(Double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(0.0));
		collector.checkThat("3.0, 0.0, 0.0",(Double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(0.0));
		collector.checkThat("0.0, 0.0, 0.0",(Double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
	}
	
	@Test
	public void sublime() throws Throwable
	{
		checkMethod(boolean.class, "sublime", int.class);
		
		collector.checkThat("12",(Boolean) method.invoke(null, 12), equalTo(true));		
		collector.checkThat("1",(Boolean) method.invoke(null, 1), equalTo(false));
		collector.checkThat("6",(Boolean) method.invoke(null, 6), equalTo(false));
		collector.checkThat("28",(Boolean) method.invoke(null, 28), equalTo(false));
	}
}