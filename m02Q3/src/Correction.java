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
package src;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Correction {

    /*
    * @pre : -
    * @post : Méthode pour imprimer un triangle
    *
    */
    public static String triangle(int cote){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        for (int x=0;x<cote;x++){
            for (int y=0;y<cote;y++){
                    if (y <= x) {
                        System.out.print("0");
                    }
            }
            System.out.print("\n");
        }
        System.setOut(old);
        return baos.toString();

    }
}
