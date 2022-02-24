package student;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
public class Mission7Test
{
	Class<?> article;
	Class<?> facture;
	Class<?> articleReparation;
	Class<?> piece;
	Class<?> articlePiece;
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
		article = Class.forName("Article");
		facture = Class.forName("Facture");
		articleReparation = Class.forName("ArticleReparation");
		piece = Class.forName("Piece");
		articlePiece = Class.forName("ArticlePiece");
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
	public void articleReparation() throws Throwable
	{
		checkConstructor(articleReparation, double.class);
		collector.checkThat("La classe ArticleReparation n'est pas une sous-classe de la classe Article. ",
							articleReparation.getSuperclass().equals(article), equalTo(true));
	}

	@Test
	public void articleReparation_getDescription() throws Throwable
	{
		checkMethod(articleReparation, String.class, "getDescription", new Class[]{});
	}

	@Test
	public void articleReparation_getPrix() throws Throwable
	{
		checkConstructor(articleReparation, double.class);
		checkMethod(articleReparation, double.class, "getPrix", new Class[]{});
		String str = "La méthode getPrix de la classe ArticleRéparation comporte des erreurs : ";

		try{
			object1 = constructor.newInstance(2.0);
			collector.checkThat(str + "pour un travail de 2 heures, la réparation devrait coûter 90€. ", 
					(double) method.invoke(object1, new Object[]{}), equalTo(90.0));
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
	public void piece() throws Throwable
	{
		checkConstructor(piece, String.class, double.class);
	}

	@Test
	public void piece_equals() throws Throwable
	{
		checkConstructor(piece, String.class, double.class);
		checkMethod(piece, boolean.class, "equals", Object.class);
		String str = "La méthode equals() de la classe Piece comporte des erreurs : ";

		try{
			object1 = constructor.newInstance("Description", 1.0);
			object2 = constructor.newInstance("Description", 1.0);

			collector.checkThat(str + "lorsque les deux objets Piece ont le même prix et la même description, la méthode ne retourne pas true. ", 
					(boolean) method.invoke(object1, object2), equalTo(true));
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
	public void articlePiece() throws Throwable
	{
		try
		{
			checkConstructor(articlePiece, int.class, piece);
		}
		catch(NoSuchMethodException e)
		{
			checkConstructor(articlePiece, piece, int.class);
		}

		collector.checkThat("La classe ArticlePiece n'est pas une sous-classe de la classe Article. ", 
				articlePiece.getSuperclass().equals(article), equalTo(true));
	}

	@Test
	public void articlePiece_getPrix() throws Throwable
	{
		String str = "La méthode getPrix de la classe ArticlePiece comporte des erreurs : ";
		try
		{
			checkConstructor(articlePiece, int.class, piece);
			object1 = constructor.newInstance(2, piece.getDeclaredConstructor(String.class, double.class).newInstance("Name", 5.0));
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			checkConstructor(articlePiece, piece, int.class);
			object1 = constructor.newInstance(piece.getDeclaredConstructor(String.class, double.class).newInstance("Name", 5.0), 2);
		}

		try{
			checkMethod(articlePiece, double.class, "getPrix", new Class[]{});
			collector.checkThat(str + "avec deux pièces à 5.0€, la méthode devrait retourner 10.0. ", 
					(double) method.invoke(object1, new Object[]{}), equalTo(10.0));
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
	public void facture_getNombre() throws Throwable
	{
		Object a1;
		Object a2;
		Object a3;
		Object a4;
		Object a5;
		String str = "La méthode getNombre() de la classe Facture comporte des erreurs : ";

		try{
			checkConstructor(articleReparation, double.class);
			a5 = constructor.newInstance(1.0);

			
			try
			{
				checkConstructor(articlePiece, int.class, piece);
			}catch(NoSuchMethodException e){
				checkConstructor(articlePiece, piece, int.class);
			}
			try
			{
				a1 = constructor.newInstance(2, piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 5.0));
				a2 = constructor.newInstance(7, piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 5.0));
				a3 = constructor.newInstance(2, piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece2", 5.0));
				a4 = constructor.newInstance(2, piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 10.0));
			}
			catch(NoSuchMethodException e)
			{
				a1 = constructor.newInstance(piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 5.0), 2);
				a2 = constructor.newInstance(piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 5.0), 7);
				a3 = constructor.newInstance(piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece2", 5.0), 2);
				a4 = constructor.newInstance(piece.getDeclaredConstructor(String.class, double.class).newInstance("Piece", 10.0), 2);			
			}

			object2 = Array.newInstance(article, 5);

			Array.set(object2, 0, a1);
			Array.set(object2, 1, a2);
			Array.set(object2, 2, a3);
			Array.set(object2, 3, a4);
			Array.set(object2, 4, a5);

			checkConstructor(facture, String.class, Class.forName("[LArticle;"));
			checkMethod(facture, int.class, "getNombre", piece);

			object1 = constructor.newInstance("Facture", object2);
			collector.checkThat(str + "Avec une facture comportant : " + Arrays.deepToString((Object[]) object2) + " la méthode devrait "
					+ "retourner 9. ", 
					(int) method.invoke(object1, piece.getDeclaredConstructor(String.class, double.class)
							.newInstance("Piece", 5.0)), equalTo(9));
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
