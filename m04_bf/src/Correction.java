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
     * @pre -
     * @post Renvoie true si le nombre de chiffres n est pair et false sinon
     */
    public static int count (String p, String s){
        int count = 0;
        for (int i = 0; i < s.length() - p.length()+1; i++){
            if (match(p,s.substring(i,i + p.length()))){
                count++;
            }
        }
        return count;
    }

    public static boolean match (String p, String s){
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) != '?' && p.charAt(i) != s.charAt(i))
                return false;
        }
        return true;
    }
}

