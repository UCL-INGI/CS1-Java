// TODO : try catch
package student;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Mission11Test
{
	Class<?> classement;
	Class<?> classementTemps;
	Class<?> resultat;
	Class<?> coureur;
	Class<?> temps;	
	Method method;
	Constructor<?> constructor;
	Object object1;
	Object object2;
	Object object3;
	Object object4;

	ThreadMXBean threadMXB;
	long start;
	long end;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void before() throws ClassNotFoundException
	{
		classement = Class.forName("Classement");
		classementTemps = Class.forName("ClassementTemps");
		resultat = Class.forName("Resultat");
		coureur = Class.forName("Coureur");
		temps = Class.forName("Temps");
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

	public Object newCoureur(String name, int age) throws Throwable
	{
		checkConstructor(coureur, String.class, int.class);

		return constructor.newInstance(name, age);
	}

	public Object newTemps(int h, int m, int s) throws Throwable
	{
		checkConstructor(temps, int.class, int.class, int.class);

		return constructor.newInstance(h, m ,s);
	}

	public Object newResultat(Object conc, Object time) throws Throwable
	{
		checkConstructor(resultat, coureur, temps);

		return constructor.newInstance(conc, time);
	}

	@Test
	public void classementTemps() throws Throwable
	{
		checkConstructor(classementTemps);
		collector.checkThat("La classe ClassementTemps n'implémente pas l'interface Classement. ", 
				Arrays.asList(classementTemps.getInterfaces()).contains(classement), equalTo(true));
	}

	@Test
	public void classementTemps_add() throws Throwable
	{
		String str = "La méthode add() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, void.class, "add", resultat);
			method.invoke(object1, newResultat(newCoureur("coureur1", 18), newTemps(1, 1, 1)));
			method.invoke(object1, newResultat(newCoureur("coureur3", 18), newTemps(3, 3, 3)));
			method.invoke(object1, newResultat(newCoureur("coureur2", 18), newTemps(2, 2, 2)));				
			method.invoke(object1, newResultat(newCoureur("coureur1", 18), newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(newCoureur("coureur2", 18), newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(newCoureur("coureur3", 18), newTemps(2, 2, 2)));
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
	public void classementTemps_remove() throws Throwable
	{
		String str = "La méthode remove() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, boolean.class, "remove", coureur);
			collector.checkThat(str + "la méthode devrait renvoyer false lorsque l'on désire enlever un coureur qui n'est pas présent dans le classement. ", 
					(boolean) method.invoke(object1, newCoureur("coureur1", 18)), equalTo(false));
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
	public void classementTemps_remove_add() throws Throwable
	{
		String str = "La méthode remove ou add de la classe ClassementTemps comporte(nt) des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, void.class, "add", resultat);
			method.invoke(object1, newResultat(newCoureur("coureur1", 18), newTemps(1, 1, 1)));
			method.invoke(object1, newResultat(newCoureur("coureur2", 18), newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(newCoureur("coureur3", 18), newTemps(3, 3, 3)));

			checkMethod(classementTemps, boolean.class, "remove", coureur);		
			collector.checkThat(str + "lorsque l'on remove un coureur qu'on a ajouté avec la méthode add, la méthode remove ne retourne pas true. ", 
					(boolean) method.invoke(object1, newCoureur("coureur1", 18)), equalTo(true));
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
	public void classementTemps_size() throws Throwable
	{
		String str = "La méthode size() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, int.class, "size");
			collector.checkThat(str + "lorsque le classement est vide, la méthode devrait retourner 0. ", 
					(int) method.invoke(object1), equalTo(0));
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
	public void classementTemps_size_add() throws Throwable
	{
		String str = "La méthode size() ou add() de la classe ClassementTemps comporte(nt) des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, void.class, "add", resultat);
			method.invoke(object1, newResultat(newCoureur("coureur1", 18), newTemps(1, 1, 1)));
			method.invoke(object1, newResultat(newCoureur("coureur2", 18), newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(newCoureur("coureur3", 18), newTemps(3, 3, 3)));

			checkMethod(classementTemps, int.class, "size");
			collector.checkThat(str + "lorsqu'on a rajouté 3 coureurs dans le classement avec la méthode add, la méthode size devrait retourner 3. ", 
					(int) method.invoke(object1), equalTo(3));
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
	public void classementTemps_get() throws Throwable
	{
		String str = "La méthode get() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();

		try{
			checkMethod(classementTemps, resultat, "get", coureur);
			collector.checkThat(str + "lorsque l'on get un coureur dans une liste vide, get() devrait retourner null. ", 
					method.invoke(object1, newCoureur("coureur1", 18)), equalTo(null));
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
	public void classementTemps_get_add() throws Throwable
	{
		String str = "La méthode get() ou add() de la classe ClassementTemps comporte(nt) des erreurs : ";
			checkConstructor(classementTemps);
		object1 = constructor.newInstance();
		object2 = newCoureur("coureur1", 18);
		object3 = newCoureur("coureur2", 18);
		object4 = newCoureur("coureur3", 18);
		Object result1 = newResultat(object2, newTemps(1, 1, 1));
		Object result2 = newResultat(object3, newTemps(2, 2, 2));
		Object result3 = newResultat(object4, newTemps(3, 3, 3));
		Object result4 = newResultat(object2, newTemps(2, 2, 2));
		Object result5 = newResultat(object3, newTemps(2, 2, 2));
		Object result6 = newResultat(object4, newTemps(2, 2, 2));

		try{
			checkMethod(classementTemps, void.class, "add", resultat);
			method.invoke(object1, result1);
			method.invoke(object1, result2);
			method.invoke(object1, result3);				
			method.invoke(object1, result4);
			method.invoke(object1, result5);
			method.invoke(object1, result6);

			checkMethod(classementTemps, resultat, "get", coureur);
			collector.checkThat(str + "un mauvais coureur est retourné lorsque l'on get un coureur qui est en tête de classement. (le temps le plus petit) ", 
					method.invoke(object1, object2), equalTo(result1));
			collector.checkThat(str + "un mauvais coureur est retourné lorsque l'on get un coureur présent deux fois dans le classement. ", 
					method.invoke(object1, object3), anyOf(equalTo(result2),equalTo(result5)));
			collector.checkThat(str + "un mauvais coureur est retourné lorsque l'on get un coureur au milieu du classement. ", 
					method.invoke(object1, object4), equalTo(result6));
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
	public void classementTemps_getPosition() throws Throwable
	{
		String str = "La méthode getPosition de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();
		try{
			checkMethod(classementTemps, int.class, "getPosition", coureur);
			collector.checkThat(str + "lorsque l'on cherche un coureur qui n'est pas dans le classement, la méthode devrait retourner -1. ", 
					(int) method.invoke(object1, newCoureur("coureur1", 18)), equalTo(-1));
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
	public void classementTemps_getPosition_add() throws Throwable
	{
		String str = "La méthode getPosition() ou add() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();
		object2 = newCoureur("coureur1", 18);
		object3 = newCoureur("coureur2", 18);
		object4 = newCoureur("coureur3", 18);

		try{
			checkMethod(classementTemps, void.class, "add", resultat);
			method.invoke(object1, newResultat(object2, newTemps(1, 1, 1)));
			method.invoke(object1, newResultat(object3, newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(object3, newTemps(2, 2, 2)));
			method.invoke(object1, newResultat(object4, newTemps(4, 4, 4)));
			method.invoke(object1, newResultat(object4, newTemps(5, 5, 5)));
			method.invoke(object1, newResultat(object2, newTemps(6, 6, 6)));

			checkMethod(classementTemps, int.class, "getPosition", coureur);
			collector.checkThat(str + "la mauvaise position est retournée quand on get la position du coureur qui devrait être en tête de classement. ",
					(int) method.invoke(object1, object2), equalTo(1));
			collector.checkThat(str + "la mauvaise position est retournée quand on get la position du coureur qui devrait être en deuxième position. ",
					(int) method.invoke(object1, object3), equalTo(2));
			collector.checkThat(str + "la mauvaise position est retournée quand on get la position du coureur qui devrait être en quatrième position. ",
					(int) method.invoke(object1, object4), equalTo(4));
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
	public void classementTemps_toString() throws Throwable
	{
		String str = "La méthode toString() de la classe ClassementTemps comporte des erreurs : ";
		checkConstructor(classementTemps);
		object1 = constructor.newInstance();
		try{
			checkMethod(classementTemps, String.class, "toString");
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
