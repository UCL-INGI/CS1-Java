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

public class Correction {

    /*
     * @pre a!=null, b!=null, a.length==b.lenght
     * @post retourne un tableau dont chaque élément est la somme des éléments
     *       des tableaux a et b au même indice
     */
    
    public static double[] somme(double []a, double []b) {
        double[] r = new double[a.length];
        for(int i = 0; i < a.length; i++) {
            r[i] = a[i] + b[i];
        }
        return r;
    }
}
