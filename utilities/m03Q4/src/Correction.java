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

    // Code a verifier
    public static int mediane(int a, int b, int c){
        int x = min(a,b);
        x = min(x,c);
        int y = max(a,b);
        y=max(y,c);
        if(a !=x && a !=y){
            return a;
        }else if (b!=x && b!=y){
            return b;
        } else{
            return c;
        }
    }
    public static int min(int a,int b){
        return (a < b) ? a : b ;
    }
    public static int max(int a,int b){
        return (a < b) ? b : a ; 
    }
}
