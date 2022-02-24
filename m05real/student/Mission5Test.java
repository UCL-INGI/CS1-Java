package student;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
public class Mission5Test
{
	Class<?> imageGray;
	Method method;
	ThreadMXBean threadMXB;
	long start;
	long end;

	int[][] img1;
	int[][] img2;
	int[][] img3;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void before() throws ClassNotFoundException
	{
		imageGray = Class.forName("ImageGray");
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

	public void checkMethod(Class<?> returnType, String name, Class<?>... parameters) throws Throwable
	{
		method = imageGray.getDeclaredMethod(name, parameters);
		if (!method.getReturnType().equals(returnType))
			throw new NoSuchMethodException("Wrong return type");
		method.setAccessible(true);
	}

	@Test
	public void subtract() throws Throwable
	{
		checkMethod(int[][].class, "subtract", int[][].class, int[][].class, int.class);
		String str = "La méthode subtract comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,0,6},{7,8,9}};

			img3 = new int[][]{{255,255,255},{255,5,255},{255,255,255}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(img3));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{0,0,0},{7,8,9}};

			img3 = new int[][]{{255,255,255},{4,5,6},{255,255,255}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(img3));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{0,0,0},{0,0,0},{0,0,0}};

			img3 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(img3));
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
	public void subtract_threshold() throws Throwable
	{
		checkMethod(int[][].class, "subtract", int[][].class, int[][].class, int.class);
		String str = "La méthode subtract comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{2,3,4},{5,7,7},{8,9,10}};

			img3 = new int[][]{{255,255,255},{255,5,255},{255,255,255}};

			collector.checkThat(str + "avec threshold = 1, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 1), equalTo(img3));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{6,7,8},{7,8,9}};

			img3 = new int[][]{{255,255,255},{4,5,6},{255,255,255}};

			collector.checkThat(str + "avec threshold = 1, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 1), equalTo(img3));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{3,4,5},{6,7,8},{9,10,11}};

			img3 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			collector.checkThat(str + "avec threshold = 1, img1 = "+Arrays.deepToString(img1)+" et img2 = " + Arrays.deepToString(img2)
			+ ", subtract devrait retourner "+Arrays.deepToString(img3)+". ", 
			(int[][]) method.invoke(null, (Object) img1, (Object) img2, 1), equalTo(img3));
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
	public void subtract_modify() throws Throwable
	{
		checkMethod(int[][].class, "subtract", int[][].class, int[][].class, int.class);
		String str = "La méthode subtract comporte des erreurs : ";

		try{
			img3 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};		
			method.invoke(null, (Object) img1, (Object) img2, 0);
			collector.checkThat(str + "avec threshold = 0, la méthode modifie img1. ", img1, equalTo(img3));
			collector.checkThat(str + "avec threshold = 0, la méthode modifie img2. ", img2, equalTo(img3));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			method.invoke(null, (Object) img1, (Object) img2, 1);
			collector.checkThat(str + "avec threshold = 1, la méthode modifie img1. ", img1, equalTo(img3));
			collector.checkThat(str + "avec threshold = 1, la méthode modifie img2. ", img2, equalTo(img3));
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
	public void brighten() throws Throwable
	{
		checkMethod(int[][].class, "brighten", int[][].class);
		String str = "La méthode brighten comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};		
			img2 = new int[][]{{ 
				(int) Math.sqrt(255.0 * 1),
				(int) Math.sqrt(255.0 * 2),
				(int) Math.sqrt(255.0 * 3)
			},
				{
				(int) Math.sqrt(255.0 * 4),
				(int) Math.sqrt(255.0 * 5),
				(int) Math.sqrt(255.0 * 6)
				},
				{
					(int) Math.sqrt(255.0 * 7),
					(int) Math.sqrt(255.0 * 8),
					(int) Math.sqrt(255.0 * 9)
				}};
				img3 = new int[][]{{ 
					(int) Math.round(Math.sqrt(255.0 * 1)),
					(int) Math.round(Math.sqrt(255.0 * 2)),
					(int) Math.round(Math.sqrt(255.0 * 3))
				},
					{
					(int) Math.round(Math.sqrt(255.0 * 4)),
					(int) Math.round(Math.sqrt(255.0 * 5)),
					(int) Math.round(Math.sqrt(255.0 * 6))
					},
					{
						(int) Math.round(Math.sqrt(255.0 * 7)),
						(int) Math.round(Math.sqrt(255.0 * 8)),
						(int) Math.round(Math.sqrt(255.0 * 9))
					}};

					collector.checkThat(str + "avec img1 = "+Arrays.deepToString(img1)+", brighten devrait retourner "+Arrays.deepToString(img3)
					+ "ou " + Arrays.deepToString(img2)+". ", 
					(int[][]) method.invoke(null, (Object) img1), anyOf(equalTo(img2), equalTo(img3)));

					img1 = new int[][]{{1,2},{3,4}};
					img2 = new int[][]{{ 
						(int) Math.sqrt(255.0 * 1),
						(int) Math.sqrt(255.0 * 2)
					},
						{
						(int) Math.sqrt(255.0 * 3),
						(int) Math.sqrt(255.0 * 4)		
						}};
						img3 = new int[][]{{ 
							(int) Math.round(Math.sqrt(255.0 * 1)),
							(int) Math.round(Math.sqrt(255.0 * 2))
						},
							{
							(int) Math.round(Math.sqrt(255.0 * 3)),
							(int) Math.round(Math.sqrt(255.0 * 4))		
							}};

							collector.checkThat(str + "avec img1 = "+Arrays.deepToString(img1)+", brighten devrait retourner "+Arrays.deepToString(img3)
							+ "ou " + Arrays.deepToString(img2)+". ", 
							(int[][]) method.invoke(null, (Object) img1), anyOf(equalTo(img2), equalTo(img3)));

							img1 = new int[][]{{1}};
							img2 = new int[][]{{(int) Math.sqrt(255.0 * 1)}};
							img3 = new int[][]{{(int) Math.round(Math.sqrt(255.0 * 1))}};

							collector.checkThat(str + "avec img1 = "+Arrays.deepToString(img1)+", brighten devrait retourner "+Arrays.deepToString(img3)
							+ "ou " + Arrays.deepToString(img2)+". ", 
							(int[][]) method.invoke(null, (Object) img1), anyOf(equalTo(img2), equalTo(img3)));
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
	public void brighten_modify() throws Throwable
	{
		checkMethod(int[][].class, "brighten", int[][].class);
		String str = "La méthode brighten comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			method.invoke(null, (Object) img1);
			collector.checkThat(str + "brighten modifie img1. ", img1, equalTo(img2));
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
	public void contains() throws Throwable
	{
		checkMethod(boolean.class, "contains", int[][].class, int[][].class, int.class);
		String str = "La méthode contains comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{5,6},{8,9}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner true. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(true));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{9}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner true. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(true));
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
	public void contains_threshold() throws Throwable
	{
		checkMethod(boolean.class, "contains", int[][].class, int[][].class, int.class);
		String str = "La méthode contains comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{5,6},{9,10}};

			collector.checkThat(str + "avec threshold = 1, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner true. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 1), equalTo(true));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{5,6},{9,10}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner false. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(false));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{10}};

			collector.checkThat(str + "avec threshold = 1, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner true. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 1), equalTo(true));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{10}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ ", contains devrait retourner false. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(false));
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
	public void contains_equal() throws Throwable
	{
		checkMethod(boolean.class, "contains", int[][].class, int[][].class, int.class);
		String str = "La méthode contains comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			collector.checkThat(str + "avec threshold = 0, img1 = "+Arrays.deepToString(img1)+", img2 = "+Arrays.deepToString(img2)
			+ " (identique), contains devrait retourner true. ", 
			(boolean) method.invoke(null, (Object) img1, (Object) img2, 0), equalTo(true));
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
	public void contains_modify() throws Throwable
	{
		checkMethod(boolean.class, "contains", int[][].class, int[][].class, int.class);
		String str = "La méthode contains comporte des erreurs : ";

		try{
			img3 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			method.invoke(null, (Object) img1, (Object) img2, 0);
			collector.checkThat(str + "la méthode a modifié img1. ", img1, equalTo(img3));
			collector.checkThat(str + "la méthode a modifié img2. ", img2, equalTo(img3));
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
	public void rescale() throws Throwable
	{
		checkMethod(int[][].class, "rescale", int[][].class, int.class, int.class);
		String str = "La méthode rescale comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

			collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+img1.length+" et "
					+ "newWidth = "+img1[0].length+", la méthode devrait retourner "
					+ Arrays.deepToString(img2)+ ". ", 
					(int[][]) method.invoke(null, (Object) img1, img1.length, img1[0].length), equalTo(img2));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,1,2,2,3,3},{4,4,5,5,6,6},{7,7,8,8,9,9}};

			collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+img1.length+" et "
					+ "newWidth = "+(img1[0].length * 2)+", la méthode devrait retourner "
					+ Arrays.deepToString(img2)+ ". ", 
					(int[][]) method.invoke(null, (Object) img1, img1.length, img1[0].length * 2), equalTo(img2));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{1,2,3},{4,5,6},{4,5,6},{7,8,9},{7,8,9}};

			collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+(img1.length * 2)+" et "
					+ "newWidth = "+img1[0].length+", la méthode devrait retourner "
					+ Arrays.deepToString(img2)+ ". ", 
					(int[][]) method.invoke(null, (Object) img1, img1.length * 2, img1[0].length), equalTo(img2));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,1,2,2,3,3},{1,1,2,2,3,3},{4,4,5,5,6,6},{4,4,5,5,6,6},{7,7,8,8,9,9},{7,7,8,8,9,9}};

			collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+(img1.length * 1)+" et "
					+ "newWidth = "+(img1[0].length * 2)+", la méthode devrait retourner "
					+ Arrays.deepToString(img2)+ ". ", 
					(int[][]) method.invoke(null, (Object) img1, img1.length * 2, img1[0].length * 2), equalTo(img2));

			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1},{4},{7}};

			collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+img1.length+" et "
					+ "newWidth = "+(img1[0].length / 2)+", la méthode devrait retourner "
					+ Arrays.deepToString(img2)+ ". ", 
					(int[][]) method.invoke(null, (Object) img1, img1.length, img1[0].length / 2), equalTo(img2));;

					img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
					img2 = new int[][]{{1,2,3}};

					collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+(img1.length / 2)+" et "
							+ "newWidth = "+img1[0].length+", la méthode devrait retourner "
							+ Arrays.deepToString(img2)+ ". ", 
							(int[][]) method.invoke(null, (Object) img1, img1.length / 2, img1[0].length), equalTo(img2));

					img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
					img2 = new int[][]{{1}};

					collector.checkThat(str + "lorsque img1 = "+Arrays.deepToString(img1)+", newHeight = "+(img1.length / 2)+" et "
							+ "newWidth = "+(img1[0].length / 2)+", la méthode devrait retourner "
							+ Arrays.deepToString(img2)+ ". ", 
							(int[][]) method.invoke(null, (Object) img1, img1.length / 2, img1[0].length / 2), equalTo(img2));
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
	public void rescale_modify() throws Throwable
	{
		checkMethod(int[][].class, "rescale", int[][].class, int.class, int.class);
		String str = "La méthode rescale comporte des erreurs : ";

		try{
			img1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			img2 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
			method.invoke(null, (Object) img1, img1.length, img1[0].length);
			collector.checkThat(str + "la méthode modifie img1. ", img1, equalTo(img2));
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
