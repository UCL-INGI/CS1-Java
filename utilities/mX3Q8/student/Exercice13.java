
/**
 *  Copyright (c)  2016 Ludovic Taffin
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package student;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Exercice13 {
	
	private static String str = "Le code semble comporter des erreurs : ";
	
	@Test
	public void testeqsecdeg1(){
		try{
        	str = "Question 1 :\n Le code semble comporter des erreurs : ";
            int a=1;
			int b=2;
			int c=3;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			PrintStream old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Corr.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			String solv =baos.toString();
			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Stu.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			String stu =baos.toString();
            stu = stu.replace("\n", "").replace("\r", "");
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+stu+" Vous devriez avoir ceci:"+solv, solv.equals(stu));
			
            a=1;
			b=18;
			c=3;

			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Corr.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			solv =baos.toString();
			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Stu.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			stu =baos.toString();
            stu = stu.replace("\n", "").replace("\r", "");
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+stu+" Vous devriez avoir ceci:"+solv, solv.equals(stu));



            a=1;
			b=2;
			c=1;

			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Corr.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			solv =baos.toString();
			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			// IMPORTANT: Save the old System.out!
			old = System.out;
			// Tell Java to use your special stream
			System.setOut(ps);
			// Print some output: goes to your special stream
			Exercice13Stu.eqsecdeg1(a,b,c);
			System.out.flush();
			System.setOut(old);
			// Show what happened
			stu =baos.toString();
            stu = stu.replace("\n", "").replace("\r", "");
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+stu+" Vous devriez avoir ceci:"+solv, solv.equals(stu));

		}catch (ArithmeticException e){
			fail(str + "Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		}catch(ClassCastException e){
			fail(str + "Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(NullPointerException e){
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testeqsecdeg2(){
		try{
            str = "Question 2 :\n Le code semble comporter des erreurs : ";
            int a=1;
			int b=2;
			int c=3;

			String r =Exercice13Corr.eqsecdeg2(a,b,c);
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+Exercice13Stu.eqsecdeg2(a,b,c)+" Vous devriez avoir ceci:"+r, String.valueOf(r).equals(Exercice13Stu.eqsecdeg2(a,b,c)));
			
            a=1;
			b=18;
			c=3;

			r =Exercice13Corr.eqsecdeg2(a,b,c);
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+Exercice13Stu.eqsecdeg2(a,b,c)+" Vous devriez avoir ceci:"+r, String.valueOf(r).equals(Exercice13Stu.eqsecdeg2(a,b,c)));
			
            a=1;
			b=2;
			c=1;

			r =Exercice13Corr.eqsecdeg2(a,b,c);
            assertTrue(str+"Avec a="+a+" b="+b+" c="+c+" vous obtenez ceci:"+Exercice13Stu.eqsecdeg2(a,b,c)+" Vous devriez avoir ceci:"+r, String.valueOf(r).equals(Exercice13Stu.eqsecdeg2(a,b,c)));
        }catch (ArithmeticException e){
			fail(str + "Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		}catch(ClassCastException e){
			fail(str + "Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(NullPointerException e){
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}


	
	// Code verificateur
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Exercice13.class);
		for (Failure failure: result.getFailures()) {
			System.err.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Tous les tests se sont passés sans encombre");
			System.exit(127);
		}
	}
}
