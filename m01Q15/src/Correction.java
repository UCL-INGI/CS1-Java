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
     * @post : Donne la saison de l'année
     *
     */
    public static String sais(int jour, int mois){
        String saison = "";
        if(  ((mois==3) && (jour>=20)) || (mois==4) || (mois==5) || ((mois==6) && (jour<20)) )
            saison = "printemps";
        if(  ((mois==6) && (jour>=20)) || (mois==7) || (mois==8) || ((mois==9) && (jour<22)) )
            saison = "été";
        if(  ((mois==9) && (jour>=22)) || (mois==10) || (mois==11) || ((mois==12) && (jour<21)) )
            saison = "automne";
        if(  ((mois==12) && (jour>=21)) || (mois==1) || (mois==2) || ((mois==3) && (jour<20)) )
            saison = "hiver";
        /*
         if (((mois == 12)&&(jour>=21))||((mois<=3)&&(jour<20))){
         saison = "hiver";
         }
         else if (((mois >= 3)&&(jour>=20))||((mois<=6)&&(jour<20))){
         saison = "printemps";
         }
         else if (((mois >= 6)&&(jour>=20))||((mois<=9)&&(jour<22))){
         saison = "été";
         }
         else if (((mois >= 9)&&(jour>=22))||((mois<=12)&&(jour<21))){
         saison = "automne";
         }
         
         */
        return saison;
        
    }

}
