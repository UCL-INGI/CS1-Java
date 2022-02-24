package student;

import static org.hamcrest.CoreMatchers.anyOf;
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

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Mission4Test
{
	Class<?> bioInfo;
	Method method;
	ThreadMXBean threadMXB;
	long start;
	long end;
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void before() throws ClassNotFoundException
	{
		bioInfo = Class.forName("BioInfo");
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
		method = bioInfo.getDeclaredMethod(name, parameters);
		if (!method.getReturnType().equals(returnType))
			throw new NoSuchMethodException("Wrong return type");
		method.setAccessible(true);
	}
	
	@Test
	public void isADN() throws Throwable
	{
		
		checkMethod(boolean.class, "isADN", String.class);
		String str = "La méthode isADN comporte des problèmes : ";
		
		try{	
			collector.checkThat(str + "la séquence agtc est une séquence d'ADN. ", (boolean) method.invoke(null, "agtc"), equalTo(true));
			collector.checkThat(str + "la séquence agxtc n'est pas une séquence d'ADN. ", (boolean) method.invoke(null, "agxtc"), equalTo(false));
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
	public void isADN_first() throws Throwable
	{
	
		checkMethod(boolean.class, "isADN", String.class);
		String str = "La méthode isADN comporte des erreurs : ";
	
		try{
			collector.checkThat(str + "La séquence \"xagtc\" n'est pas une séquence d'ADN. ", (boolean) method.invoke(null, "xagtc"), equalTo(false));
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
	public void isADN_last() throws Throwable
	{
		
		checkMethod(boolean.class, "isADN", String.class);
		String str = "La méthode isADN comporte des problèmes : ";
		
		try{
			collector.checkThat(str + "la séquence \"agtcx\" n'est pas une séquence d'ADN. ", (boolean) method.invoke(null, "agtcx"), equalTo(false));
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
	public void isADN_empty() throws Throwable
	{
		
		checkMethod(boolean.class, "isADN", String.class);
		String str = "La méthode isADN comporte des problèmes : ";
		
		try{
			collector.checkThat(str + "la séquence \"\" n'est pas une séquence d'ADN. ", (boolean) method.invoke(null, ""), equalTo(false));
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
	public void count() throws Throwable
	{
		
		checkMethod(int.class, "count", String.class, char.class);
		String str = "La méthode count comporte des problèmes : ";
		
		try{
			collector.checkThat(str + "le caractère 'a' apparaît 4 fois dans la séquence \"aaxaa\". ", (int) method.invoke(null, "aaxaa", 'a'), equalTo(4));
			collector.checkThat(str + "le caractère 'a' apparaît 1 fois dans la séquence \"xxaxx\". ", (int) method.invoke(null, "xxaxx", 'a'), equalTo(1));
			collector.checkThat(str + "le caractère 'x' apparaît 4 fois dans la séquence \"xxaxx\". ", (int) method.invoke(null, "xxaxx", 'x'), equalTo(4));
			collector.checkThat(str + "le caractère 'x' apparaît 1 fois dans la séquence \"aaxaa\". ", (int) method.invoke(null, "aaxaa", 'x'), equalTo(1));
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
	public void count_empty() throws Throwable
	{
	
		checkMethod(int.class, "count", String.class, char.class);
		String str = "La méthode count comporte des erreurs : ";
		try{
			collector.checkThat(str + "le caractère 'x' apparaît 0 fois dans la séquence \"\". ", (int) method.invoke(null, "", 'x'), equalTo(0));
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
	public void distanceH() throws Throwable
	{
		
		checkMethod(int.class, "distanceH", String.class, String.class);
		String str = "La méthode distanceH comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "la distance de Hamming entre les séquences \"a\" et \"a\" est de 0. ", (int) method.invoke(null, "a", "a"), equalTo(0));
			collector.checkThat(str + "la distance de Hamming entre les séquences \"abcd\" et \"abcd\" est de 0. ", (int) method.invoke(null, "abcd", "abcd"), equalTo(0));
		
			collector.checkThat(str + "la distance de Hamming entre les séquences \"aabaa\" et \"aaaaa\" est de 1. ", (int) method.invoke(null, "aabaa", "aaaaa"), equalTo(1));
			collector.checkThat(str + "la distance de Hamming entre les séquences \"aaaaa\" et \"aabaa\" est de 1. ", (int) method.invoke(null, "aaaaa", "aabaa"), equalTo(1));
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
	public void distanceH_first() throws Throwable
	{
		checkMethod(int.class, "distanceH", String.class, String.class);
		String str = "La méthode distanceH comporte des erreurs : ";
		try{
			collector.checkThat(str + "la distance de Hamming entre les séquences \"aaaaa\" et \"baaaa\" est de 1. ", (int) method.invoke(null, "aaaaa", "baaaa"), equalTo(1));
			collector.checkThat(str + "la distance de Hamming entre les séquences \"baaaa\" et \"aaaaa\" est de 1. ", (int) method.invoke(null, "baaaa", "aaaaa"), equalTo(1));		
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
	public void distanceH_last() throws Throwable
	{
		checkMethod(int.class, "distanceH", String.class, String.class);
		String str = "La méthode distanceH comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "la distance de Hamming entre les séquences \"aaaaa\" et \"aaaab\" est de 1. ", (int) method.invoke(null, "aaaaa", "aaaab"), equalTo(1));
			collector.checkThat(str + "la distance de Hamming entre les séquences \"aaaab\" et \"aaaaa\" est de 1. ", (int) method.invoke(null, "aaaab", "aaaaa"), equalTo(1));
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
	public void distanceH_empty() throws Throwable
	{
		checkMethod(int.class, "distanceH", String.class, String.class);
		String str = "La méthode distanceH comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "la distance de Hamming entre les séquences \"\" et \"\" est de 0. ", (int) method.invoke(null, "", ""), equalTo(0));
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
	public void plusLongPalindrome() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"abbbc\" est \"bbb\". ", (String) method.invoke(null, "abbbc"), equalTo("bbb"));
			collector.checkThat(str + "le plus long palindrome de la séquence \"abbcd\" est \"bb\". ", (String) method.invoke(null, "abbcd"), equalTo("bb"));
			collector.checkThat(str + "le plus long palindrome de la séquence \"abccd\" est \"cc\". ", (String) method.invoke(null, "abccd"), equalTo("cc"));
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
	public void plusLongPalindrome_beginning() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"aabcd\" est \"aa\". ", (String) method.invoke(null, "aabcd"), equalTo("aa"));
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
	public void plusLongPalindrome_end() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"abcdd\" est \"dd\". ", (String) method.invoke(null, "abcdd"), equalTo("dd"));
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
	public void plusLongPalindrome_empty() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"\" est \"\". ", (String) method.invoke(null, ""), equalTo(""));
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
	public void plusLongPalindrome_singleCharacter() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"abc\" est \"a\", \"b\" ou \"c\". ", (String) method.invoke(null, "abc"), anyOf(equalTo("a"), equalTo("b"), equalTo("c")));
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
	public void plusLongPalindrome_longest() throws Throwable
	{
		checkMethod(String.class, "plusLongPalindrome", String.class);
		String str = "La méthode plusLongPalindrome comporte des erreurs : ";
		
		try{
			collector.checkThat(str + "le plus long palindrome de la séquence \"abbcddde\" est \"ddd\". ", (String) method.invoke(null, "abbcddde"), equalTo("ddd"));
			collector.checkThat(str + "le plus long palindrome de la séquence \"abbcdde\" est \"bb\" ou \"dd\". ", (String) method.invoke(null, "abbcdde"), anyOf(equalTo("bb"), equalTo("dd")));
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
