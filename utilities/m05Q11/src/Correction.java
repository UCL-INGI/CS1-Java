/**
 *  Copyright (c)  2016 Olivier Bonaventure, 2017 Olivier Martin
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
     * @pre v!=null, n<v.length`
     * @post retourne un tableau de longueur n dont les éléments sont dans l'ordre croissant et qui
     *       contient les n plus grands entiers du tableau v
     */
    public static int[] top(int n, int[] v) {
        // initialisation
        int [] tab=new int[n];
        for(int i=0;i<n;i++) {
            tab[i]=Integer.MIN_VALUE;
        }
        
        for(int i=0;i<v.length;i++) {
            if(v[i]>tab[0]) {
                // insert
                tab[0]=v[i];
                for(int j=0;j<n-1;j++) {
                    
                    if( tab[j]>tab[j+1] ) {
                        int x=tab[j];
                        tab[j]=tab[j+1];
                        tab[j+1]=x;
                    }
                    else {
                        j=n; // exit for
                    }
                    
                }
            }
            
        }
        return tab;
    }
}
