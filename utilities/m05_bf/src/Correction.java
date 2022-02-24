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
     * @pre tab != null
     * @post Le tableau tab a été modifié et tous ses éléments sont décalés
     *       d'une position vers la droite (le dernier élément revenant
     *       en première position)
     */
    public static void rotateRightOne(int[] tab) {
        if (tab.length == 0)
            return;
        int last = tab[tab.length-1];
        for(int i = tab.length-1; i > 0; i--)
            tab[i]=tab[i-1];
        tab[0] = last;
    }
    
    /*
     * @pre tab != null
     * @post Le tableau tab a été modifié et tous ses éléments sont décalés
     *       de n positions vers la droite, c'est-à-dire que les éléments
     *       qui se trouvaient en i se retrouvent en (i + n) % tab.length
     */
    public static void rotateRight(int[] tab, int n) {
        for(int i = 0; i < n; i++)
            rotateRightOne(tab);
    }
}
