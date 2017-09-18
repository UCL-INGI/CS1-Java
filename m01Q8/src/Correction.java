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
	/*
    * @pre : -
    * @post : Calcul la mediane de 3 nombres
    *
    */
    // Code a verifier
    public static int mediane(int a, int b, int c){
        
        int mediane=0;
        //startstud
        int un = a, deux = 0, trois = 0;
        int temp = 0;
        if(a > b){
            un = b;
            deux = a;
        }else{
            deux = b;
        }
        if (c < deux){
            temp = deux;
            deux = c;
            trois = temp;
            if(c < un){
                temp = un;
                un = c;
                deux = temp;
            }
        }else{
            trois = c;
        }
        
        mediane = deux;
        //endstud
        return mediane;
    }
}

//Other possible correction:
/*
 int un = a, deux = 0, trois = 0;
 un = Math.max(a, Math.max(b,c));
 trois = Math.min(a, Math.min(b,c));
 if( (a == un && c == trois) || (c == un && a == trois))
 mediane = b;
 if( (b == un && c == trois) || (c == un && b == trois))
 mediane = a;
 if( (a == un && b == trois) || (b == un && a == trois))
 mediane = c;
 */
