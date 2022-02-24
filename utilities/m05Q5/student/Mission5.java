
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
    	public void testInitVector() {
        
	      double[] v;
	      String msg;
	      int n;
	      double val;
	      
	      v=new double[] {  7.0  };
	      n=1;
	      val=7.0;
	      msg="initVector("+n+","+val+") retourne le vecteur \n"+java.util.Arrays.toString(Mission5Stu.initVector(n,val))+"\n alors que le résultat attendu est le vecteur "+java.util.Arrays.toString(v)+"\n";
	      assertEquals(msg,java.util.Arrays.equals(v,Mission5Stu.initVector(n,val)),true);
	      
	      v=new double[] {  5.0, 5.0, 5.0  };
	      n=3;
	      val=5.0;
	      msg="initVector("+n+","+val+") retourne le vecteur \n"+java.util.Arrays.toString(Mission5Stu.initVector(n,val))+"\n alors que le résultat attendu est le vecteur "+java.util.Arrays.toString(v)+"\n";
	      assertEquals(msg,java.util.Arrays.equals(v,Mission5Stu.initVector(n,val)),true);
	      
	      v=new double[] {  5.0, 5.0, 5.0 , 5.0 };
	      n=4;
	      val=5.0;
	      msg="initVector("+n+","+val+") retourne le vecteur \n"+java.util.Arrays.toString(Mission5Stu.initVector(n,val))+"\n alors que le résultat attendu est le vecteur "+java.util.Arrays.toString(v)+"\n";
	      assertEquals(msg,java.util.Arrays.equals(v,Mission5Stu.initVector(n,val)),true);
      
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
