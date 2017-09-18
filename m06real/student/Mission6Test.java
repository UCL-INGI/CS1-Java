package student;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
public class Mission6Test
{
	Class<?> temps;
	Class<?> chanson;
	Method method;
	Constructor<?> constructor;
	Object object1;
	Object object2;

	ThreadMXBean threadMXB;
	long start;
	long end;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void before() throws ClassNotFoundException
	{
		temps = Class.forName("Temps");
		chanson = Class.forName("Chanson");
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

	public void checkMethod(Class<?> cl, Class<?> returnType, String name, Class<?>... parameters) throws Throwable
	{
		method = cl.getDeclaredMethod(name, parameters);
		if (returnType != null && !method.getReturnType().equals(returnType))
			throw new NoSuchMethodException("Wrong return type");
		method.setAccessible(true);
	}

	public void checkConstructor(Class<?> cl, Class<?>... parameters) throws Throwable
	{
		constructor = cl.getDeclaredConstructor(parameters);
		constructor.setAccessible(true);
	}

	@Test
	public void temps() throws Throwable
	{
		checkConstructor(temps, int.class, int.class, int.class);

		collector.checkThat(constructor.newInstance(0,0,0), instanceOf(temps));
		collector.checkThat(constructor.newInstance(1,1,1), instanceOf(temps));
		collector.checkThat(constructor.newInstance(25,59,59), instanceOf(temps));
	}

	@Test
	public void temps_toSecondes() throws Throwable
	{
		checkMethod(temps, int.class, "toSecondes", new Class<?>[]{});
		checkConstructor(temps, int.class, int.class, int.class);
		String str = "La méthode toSecondes comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(0,0,0);
			collector.checkThat(str + "avec un Temps instancié à 0h, 0m, 0s, toSecondes devrait retourner 0. ", 
					(int) method.invoke(object1, new Object[]{}), equalTo(0));

			object1 = constructor.newInstance(1,1,1);
			collector.checkThat(str + "avec un Temps instancié à 1h, 1m, 1s, toSecondes devrait retourner 3661. ", 
					(int) method.invoke(object1, new Object[]{}), equalTo(3661));

			object1 = constructor.newInstance(25,59,59);
			collector.checkThat(str + "avec un Temps instancié à 25h, 59m, 59s, toSecondes devrait retourner 93599. ", 
					(int) method.invoke(object1, new Object[]{}), equalTo(93599));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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
	public void temps_delta() throws Throwable
	{
		checkMethod(temps, long.class, "delta", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		String str = "La méthode delta comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(0,0,0);
			object2 = constructor.newInstance(0,0,0);
			collector.checkThat(str + "avec un Temps initialisé à 0h, 0m, 0s et t initialisé à 0h, 0m, 0s, delta devrait retourner 0. ", 
					((int) (long) method.invoke(object1, object2)), equalTo(0));

			object1 = constructor.newInstance(1,1,1);
			collector.checkThat(str + "avec un Temps initialisé à 1h, 1m, 1s et t initialisé à 0h, 0m, 0s, delta devrait retourner 3661. ", 
					((int) (long) method.invoke(object1, object2)), equalTo(3661));

			object2 = constructor.newInstance(2,2,2);
			collector.checkThat(str + "avec un Temps initialisé à 1h, 1m, 1s et t initialisé à 0h, 0m, 0s, delta devrait retourner -3661. ", 
					((int) (long) method.invoke(object1, object2)), equalTo(-3661));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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
	public void temps_apres() throws Throwable
	{
		checkMethod(temps, boolean.class, "apres", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		String str = "La méthode apres comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(1,1,1);
			object2 = constructor.newInstance(0,0,0);
			collector.checkThat(str + "avec un Temps initialisé à 1h, 1m, 1s et t initialisé à 0h, 0m, 0s, apres() devrait retourner true. ", 
					(boolean) method.invoke(object1, object2), equalTo(true));

			object2 = constructor.newInstance(2,2,2);
			collector.checkThat(str + "avec un Temps initialisé à 1h, 1m, 1s et t initialisé à 2h, 2m, 2s, apres() devrait retourner false. ", 
					(boolean) method.invoke(object1, object2), equalTo(false));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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
	public void temps_ajouter() throws Throwable
	{
		checkMethod(temps, null, "ajouter", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		String str = "La méthode ajouter comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(0,0,0);
			object2 = constructor.newInstance(2,2,2);
			method.invoke(object1, object2);

			collector.checkThat(str + "avec un Temps initialisé à 0h, 0m, 0s et t initialisé à 2h, 2m, 2s, l'objet devrait à présent avoir " 
					+ "2h, 2m, 2s. ", temps_equal(object1, constructor.newInstance(2,2,2)), equalTo(true));

			object1 = constructor.newInstance(2,2,2);
			object2 = constructor.newInstance(1,59,59);
			method.invoke(object1, object2);

			collector.checkThat(str + "avec un Temps initialisé à 2h, 2m, 2s et t initialisé à 1h, 59m, 59s, l'objet devrait à présent avoir " 
					+ "4h, 2m, 1s. ", temps_equal(object1, constructor.newInstance(4,2,1)), equalTo(true));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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
	public void temps_toString() throws Throwable
	{
		checkMethod(temps, null, "toString", new Class<?>[]{});
		checkConstructor(temps, int.class, int.class, int.class);
		String str = "La méthode toString comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(1,1,1);
			collector.checkThat(str + "avec un Temps initialisé à 1h, 1m, 1s, toString devrait renvoyer \"01:01:01\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo("01:01:01"));

			object1 = constructor.newInstance(10,10,10);
			collector.checkThat(str + "avec un Temps initialisé à 10h, 10m, 10s, toString devrait renvoyer \"10:10:10\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo("10:10:10"));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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

	public boolean temps_equal(Object a, Object b) throws IllegalArgumentException, IllegalAccessException
	{
		for(Field f : temps.getFields())
			if(!f.get(temps.cast(a)).equals(f.get(temps.cast(b))))
				return false;
		return true;
	}

	@Test
	public void chanson() throws Throwable
	{
		checkConstructor(chanson, String.class, String.class, temps);

		collector.checkThat(constructor.newInstance("", "", null), instanceOf(chanson));
		collector.checkThat(constructor.newInstance("a", "a", null), instanceOf(chanson));
	}

	@Test
	public void chanson_getters() throws Throwable
	{
		checkMethod(chanson, null, "getTitre", new Class<?>[]{});
		checkConstructor(chanson, String.class, String.class, temps);
		String str = "Certains getters comportent des erreurs : ";

		try{
			object1 = constructor.newInstance("", "", null);		
			collector.checkThat(str + "lorsque le titre est \"\", l'auteur est \"\" et la duree vaut null, getTitre devrait retourner \"\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo(""));

			object1 = constructor.newInstance("a", "", null);		
			collector.checkThat(str + "lorsque le titre est \"a\", l'auteur est \"\" et la duree vaut null, getTitre devrait retourner \"a\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo("a"));

			checkMethod(chanson, null, "getAuteur", new Class<?>[]{});

			object1 = constructor.newInstance("", "", null);		
			collector.checkThat(str + "lorsque le titre est \"\", l'auteur est \"\" et la duree vaut null, getAuteur devrait retourner \"\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo(""));

			object1 = constructor.newInstance("", "a", null);		
			collector.checkThat(str + "lorsque le titre est \"\", l'auteur est \"a\" et la duree vaut null, getAuteur devrait retourner \"a\". ", 
					(String) method.invoke(object1, new Object[]{}), equalTo("a"));

			checkMethod(chanson, null, "getDuree", new Class<?>[]{});

			object1 = constructor.newInstance("", "", null);		
			collector.checkThat(str + "lorsque le titre est \"\", l'auteur est \"\" et la duree vaut null, getDuree devrait retourner null. ", 
					method.invoke(object1, new Object[]{}), equalTo(null));
		}catch(InvocationTargetException e){
			Throwable t = e.getCause();
            t.printStackTrace();
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
