/**
 *  Copyright (c)  2016 Ody Lucas, Rousseaux Tom
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



public class M2Q3 {

	private static String str = "Le code semble comporter des erreurs : ";

	@Test
	public void testP1(){
		try{
            int diviseur = 0;
            int dividende = 0;
            int[] sol = new int[2];
            int[] stu = new int[2];
            for(int i = 0; i < 8; i++)
            {
                dividende=(int)(Math.random()*40);
                diviseur=(int)(Math.random()*10+1);
                stu=M2Q3Stu.M2Q3(diviseur,dividende);
                sol[0]=dividende/diviseur;
                sol[1]=dividende%diviseur;
                
                assertTrue("La calcul est erroné : "+dividende+" divisé par "+diviseur+" doivent donner "+sol[0]+" comme quotient, et "+sol[1]+" comme reste. Or votre code donne respectivement les valeurs "+stu[0]+" et "+stu[1]+".",(sol[0]==stu[0])&&(sol[1]==stu[1]));
                
            }
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
		Result result = JUnitCore.runClasses(M2Q3.class);
		for (Failure failure: result.getFailures()) {
			System.err.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Tous les tests se sont passés sans encombre");
			System.exit(127);
		}
	}
}