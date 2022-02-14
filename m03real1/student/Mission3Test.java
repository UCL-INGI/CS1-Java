package student;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

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
import org.junit.runner.notification.Failure;

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
		
		System.out.println("§" + name);
		System.out.println(end - start);
	}
	
	public void checkMethod(Class<?> returnType, String name, Class<?>... parameters) throws NoSuchMethodException, SecurityException
	{
		method = libMath.getDeclaredMethod(name, parameters);
		if (!method.getReturnType().equals(returnType))
			throw new NoSuchMethodException("Wrong return type");
		method.setAccessible(true);
	} 
	
	@Test
	public void average() throws Throwable
	{
		String str = "La méthode average() ne retourne pas les bons résultats : ";
		checkMethod(double.class, "average", double.class, double.class, double.class);
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, 7.0. ", (double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(4.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, 7.0. ", (double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs 3.0, -1.0, 7.0. ", (double) method.invoke(null, 3.0, -1.0, 7.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 1.0, -7.0. ", (double) method.invoke(null, 3.0, 1.0, -7.0), equalTo(-1.0));
			collector.checkThat(str + "avec les valeurs -3.0, -1.0, 7.0. ", (double) method.invoke(null, -3.0, -1.0, 7.0), equalTo(1.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, -7.0. ", (double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-2.0));
			collector.checkThat(str + "avec les valeurs -3.0, 1.0, -7.0. ", (double) method.invoke(null, -3.0, 1.0, -7.0), equalTo(-3.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, -7.0. ", (double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-4.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void average_equal() throws Throwable
	{
		checkMethod(double.class, "average", double.class, double.class, double.class);
		String str = "La méthode average() comporte des erreurs : ";
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 3.0. ", (double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 0.0. ", (double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 3.0. ", (double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 3.0. ", (double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 3.0. ", (double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(1.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 0.0. ", (double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(1.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 0.0. ", (double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(1.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 0.0. ", (double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void median() throws Throwable
	{
		checkMethod(double.class, "median", double.class, double.class, double.class);
		String str = "la méthode median() comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, 7.0. ", (double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, 7.0. ", (double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, 7.0. ", (double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, -7.0. ", (double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, 7.0. ", (double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(-2.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, -7.0. ", (double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-2.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, -7.0. ", (double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(-3.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, -7.0. ", (double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-3.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void median_equal() throws Throwable
	{
		checkMethod(double.class, "median", double.class, double.class, double.class);
		String str = "La méthode median comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 3.0. ", (double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 0.0. ", (double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 3.0. ", (double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 3.0. ", (double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 3.0. ", (double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 0.0. ", (double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 0.0. ", (double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 0.0. ", (double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void maximum() throws Throwable
	{
		checkMethod(double.class, "maximum", double.class, double.class, double.class);
		String str = "La méthode maximum() comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, 7.0. ", (double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(7.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, 7.0. ", (double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(7.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, 7.0. ", (double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(7.0));
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, -7.0. ", (double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, 7.0. ", (double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(7.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, -7.0. ", (double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, -7.0. ", (double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, -7.0. ", (double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-2.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void maximum_equal() throws Throwable
	{
		checkMethod(double.class, "maximum", double.class, double.class, double.class);
		String str = "La méthode maximum() comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 3.0. ", (double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 0.0. ", (double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 3.0. ", (double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 3.0. ", (double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 3.0. ", (double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 0.0. ", (double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 0.0. ", (double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 0.0. ", (double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void minimum() throws Throwable
	{
		checkMethod(double.class, "minimum", double.class, double.class, double.class);
		String str = "La méthode minimum() comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, 7.0. ", (double) method.invoke(null, 3.0, 2.0, 7.0), equalTo(2.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, 7.0. ", (double) method.invoke(null, -3.0, 2.0, 7.0), equalTo(-3.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, 7.0. ", (double) method.invoke(null, 3.0, -2.0, 7.0), equalTo(-2.0));
			collector.checkThat(str + "avec les valeurs 3.0, 2.0, -7.0. ", (double) method.invoke(null, 3.0, 2.0, -7.0), equalTo(-7.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, 7.0. ", (double) method.invoke(null, -3.0, -2.0, 7.0), equalTo(-3.0));
			collector.checkThat(str + "avec les valeurs 3.0, -2.0, -7.0. ", (double) method.invoke(null, 3.0, -2.0, -7.0), equalTo(-7.0));
			collector.checkThat(str + "avec les valeurs -3.0, 2.0, -7.0. ", (double) method.invoke(null, -3.0, 2.0, -7.0), equalTo(-7.0));
			collector.checkThat(str + "avec les valeurs -3.0, -2.0, -7.0. ", (double) method.invoke(null, -3.0, -2.0, -7.0), equalTo(-7.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
	
	@Test
	public void minimum_equal() throws Throwable
	{
		checkMethod(double.class, "minimum", double.class, double.class, double.class);
		String str = "La méthode minimum() comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 3.0. ", (double) method.invoke(null, 3.0, 3.0, 3.0), equalTo(3.0));
			collector.checkThat(str + "avec les valeurs 3.0, 3.0, 0.0. ", (double) method.invoke(null, 3.0, 3.0, 0.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 3.0. ", (double) method.invoke(null, 3.0, 0.0, 3.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 3.0. ", (double) method.invoke(null, 0.0, 3.0, 3.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 3.0. ", (double) method.invoke(null, 0.0, 0.0, 3.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 3.0, 0.0. ", (double) method.invoke(null, 0.0, 3.0, 0.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 3.0, 0.0, 0.0. ", (double) method.invoke(null, 3.0, 0.0, 0.0), equalTo(0.0));
			collector.checkThat(str + "avec les valeurs 0.0, 0.0, 0.0. ", (double) method.invoke(null, 0.0, 0.0, 0.0), equalTo(0.0));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}	
	
	@Test
	public void sublime() throws Throwable
	{
		checkMethod(boolean.class, "sublime", int.class);
		String str = "La méthode sublime comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le nombre 12 est sublime. ", (boolean) method.invoke(null, 12), equalTo(true));
		
			collector.checkThat(str + "le nombre 1 n'est pas sublime. ", (boolean) method.invoke(null, 1), equalTo(false));
			collector.checkThat(str + "le nombre 6 n'est pas sublime. ", (boolean) method.invoke(null, 6), equalTo(false));
			collector.checkThat(str + "le nombre 28 n'est pas sublime. ", (boolean) method.invoke(null, 28), equalTo(false));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
			if(t instanceof ArithmeticException){
				fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			}
			else if(t instanceof ClassCastException){
				fail(str+"Attention, certaines variables ont été mal castées	!");
			}
			else if(t instanceof StringIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			}
			else if(t instanceof ArrayIndexOutOfBoundsException){
				fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)" + t.getMessage());
			}
			else if(t instanceof NullPointerException){
				fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			}
			else{
				fail(str + "\n" + t.getMessage());
			}
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
		}
	}
}
