
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
import java.util.Arrays;

public class Mission5 {
	
	private static String str = "Le code semble comporter des erreurs : ";
	
	@Test
    	public void testMean() {
	       double[] v;
	       double val;
	       String msg;
	       
	       v=new double[] {1.0, 1.0, 1.0};
	       val=1.0;
	       msg="Pour le tableau "+Arrays.toString(v)+", mean("+Arrays.toString(v)+") retourne "+Mission5Stu.mean(v)+" alors que la réponse attendue est "+val+"\n";
	       assertEquals(msg,Mission5Stu.mean(v),val,0.001); 
		
	       v=new double[] {1.0, 0.0, 1.0};
	       val=2.0/3.0;
	       msg="Pour le tableau "+Arrays.toString(v)+", mean("+Arrays.toString(v)+") retourne "+Mission5Stu.mean(v)+" alors que la réponse attendue est "+val+"\n";
	       assertEquals(msg,Mission5Stu.mean(v),val,0.001); 
	       
	       v=new double[] {-1.0, -1.0, -2.0};
	       val=-4.0/3.0;
	       msg="Pour le tableau "+Arrays.toString(v)+", mean("+Arrays.toString(v)+") retourne "+Mission5Stu.mean(v)+" alors que la réponse attendue est "+val+"\n";
	       assertEquals(msg,Mission5Stu.mean(v),val,0.001); 
	       
	       v=new double[] {1.0, 2.0, 2.0};
	       val=5.0/3.0;
	       msg="Pour le tableau "+Arrays.toString(v)+", mean("+Arrays.toString(v)+") retourne "+Mission5Stu.mean(v)+" alors que la réponse attendue est "+val+"\n";
	       assertEquals(msg,Mission5Stu.mean(v),val,0.001); 
	       
	       v=new double[] {7.0};
	       val=7.0;
	       msg="Pour le tableau "+Arrays.toString(v)+", mean("+Arrays.toString(v)+") retourne "+Mission5Stu.mean(v)+" alors que la réponse attendue est "+val+"\n";
	       assertEquals(msg,Mission5Stu.mean(v),val,0.001); 
       
    	}

	

	
	// Code verificateur
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Mission5.class);
		for (Failure failure: result.getFailures()) {
			System.err.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Tous les tests se sont passés sans encombre");
			System.exit(127);
		}
	}
}
