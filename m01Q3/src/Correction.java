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
     * @pre : taille > 0, poids > 0
     * @post : retourne 'mince' pour une personne ayant un indice strictement inférieur à 20,
     *   'normal' pour une personne dont l'indice est compris entre 20 et 25,
     *   'embonpoint' si l'indice est strictement supérieur à 25 et inférieur ou égal à 30,
     *   'obèse' lorsqu'il est strictement supérieur à 30.
     */
    public static String quetelet(double taille, int poids){
        String etat = "";
        double indiceDeQuételet = poids / (taille * taille);
        if(indiceDeQuételet < 20) etat = "mince";
        if((indiceDeQuételet >= 20) && (indiceDeQuételet <= 25)) etat = "normal";
        if((indiceDeQuételet > 25) && (indiceDeQuételet <= 30)) etat = "embonpoint";
        if(indiceDeQuételet > 30) etat = "obèse";
        return etat;
        
    }
}
