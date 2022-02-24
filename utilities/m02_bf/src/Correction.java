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
    
    public static String main(int n) {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        
        // Code etudiant
        for (int i = 1; i <= n; i++){
            int count = 0;
            for (int j = 2; j <= i; j++){
                if (i%j==0)
                    count++;
            }
            System.out.println(i+":"+count);
        }
        //Fin code etudiant
        
        System.setOut(old);
        return baos.toString();
    }
}
