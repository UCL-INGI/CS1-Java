/**
 *  Copyright (c)  2017 Olivier Martin
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
package src;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Correction {
    
    public static String syracuse(int s0) {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        
        // Code etudiant
        while (s0 != 1){
            System.out.println(s0);
            if (s0 % 2 == 0)
                s0 = s0 / 2;
            else
                s0 = 3 * s0 + 1;
        }
        System.out.println(s0);
        //Fin code etudiant
        
        System.setOut(old);
        return baos.toString();
    }
}
