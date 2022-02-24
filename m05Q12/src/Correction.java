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

    /* @pre v != null
     * @post retourne l'opposé du tableau v, c'est-à-dire un tableau de même
     *       longueur que v et dont tous les éléments sont l'opposé de l'élément
     *       correspondant du tableau v
     */
    
    public static int[] oppose(int[] v) {
        int[] r = new int[v.length];
        for(int i = 0; i < v.length; i++) {
            r[i] = -v[i];
        }
        return r;
    }
    
    /* @pre v!=null
     * @post modifie le tableau v de façon à ce que chaque élement de v soit remplacé par
     *       son opposé
     */
    
    public static void oppose2(int[] v) {
        for(int i = 0; i < v.length; i++) {
            v[i] = -v[i];
        }
    }
}
