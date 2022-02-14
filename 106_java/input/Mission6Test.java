import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

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
		
		System.out.println("€" + name);
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
		
		object1 = constructor.newInstance(0,0,0);
		collector.checkThat((int) method.invoke(object1, new Object[]{}), equalTo(0));
		
		object1 = constructor.newInstance(1,1,1);
		collector.checkThat((int) method.invoke(object1, new Object[]{}), equalTo(3661));
		
		object1 = constructor.newInstance(25,59,59);
		collector.checkThat((int) method.invoke(object1, new Object[]{}), equalTo(93599));
	}
	
	@Test
	public void temps_delta() throws Throwable
	{
		checkMethod(temps, int.class, "delta", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		
		object1 = constructor.newInstance(0,0,0);
		object2 = constructor.newInstance(0,0,0);
		collector.checkThat((int) method.invoke(object1, object2), equalTo(0));
		
		object1 = constructor.newInstance(1,1,1);
		collector.checkThat((int) method.invoke(object1, object2), equalTo(3661));
		
		object2 = constructor.newInstance(2,2,2);
		collector.checkThat((int) method.invoke(object1, object2), equalTo(-3661));
	}
	
	@Test
	public void temps_apres() throws Throwable
	{
		checkMethod(temps, boolean.class, "apres", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		
		object1 = constructor.newInstance(1,1,1);
		object2 = constructor.newInstance(0,0,0);
		collector.checkThat((boolean) method.invoke(object1, object2), equalTo(true));
		
		object2 = constructor.newInstance(2,2,2);
		collector.checkThat((boolean) method.invoke(object1, object2), equalTo(false));
	}
	
	@Test
	public void temps_ajouter() throws Throwable
	{
		checkMethod(temps, null, "ajouter", temps);
		checkConstructor(temps, int.class, int.class, int.class);
		
		object1 = constructor.newInstance(0,0,0);
		object2 = constructor.newInstance(2,2,2);
		method.invoke(object1, object2);
		
		collector.checkThat(temps_equal(object1, constructor.newInstance(2,2,2)), equalTo(true));
		
		object2 = constructor.newInstance(1,59,59);
		method.invoke(object1, object2);
		
		collector.checkThat(temps_equal(object1, constructor.newInstance(4,2,1)), equalTo(true));
	}
	
	@Test
	public void temps_toString() throws Throwable
	{
		checkMethod(temps, null, "toString", new Class<?>[]{});
		checkConstructor(temps, int.class, int.class, int.class);
		
		object1 = constructor.newInstance(1,1,1);
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo("01:01:01"));
		
		object1 = constructor.newInstance(10,10,10);
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo("10:10:10"));
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
		
		object1 = constructor.newInstance("", "", null);		
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo(""));
		
		object1 = constructor.newInstance("a", "", null);		
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo("a"));
		
		checkMethod(chanson, null, "getAuteur", new Class<?>[]{});
		
		object1 = constructor.newInstance("", "", null);		
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo(""));
		
		object1 = constructor.newInstance("", "a", null);		
		collector.checkThat((String) method.invoke(object1, new Object[]{}), equalTo("a"));
		
		checkMethod(chanson, null, "getDuree", new Class<?>[]{});
		
		object1 = constructor.newInstance("", "", null);		
		collector.checkThat(method.invoke(object1, new Object[]{}), equalTo(null));
	}
}