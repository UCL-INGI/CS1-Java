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

public class Correction {

    public static double[] t1 = {1.0, 2.0, -3.0};
    public static double[] t2 = {1.0, 3.0, 9.0 };
    
    /**
     * Modifie l'environnement de sorte que t1 == t2 renvoie true
     */
    public static void equalequalSucceeds(){
        t2 = t1;
    }
    
   	/**
     * @pre t1 et t2 != null
     * @return true si t1 et t2 contiennent les memes valeurs, false sinon.
     */
    public static boolean egal(double[] t1a, double[] t2a) {
        if(t1a.length != t2a.length)
            return false;
        for (int i = 0; i < t1a.length; i++)
            if(t1a[i] != t2a[i])
                return false;
        return true;
    }
}
