/**
 *  Copyright (c)  2016 O. Bonaventure, 2017 Olivier Martin
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

/*
 * Une pile d'entiers est une structure de données qui permet d'empiler des entiers. Les
 * entiers sont ajoutés à la pile via son sommet. La pile
 * garantit un fonctionnement Last-In First-Out (LIFO, dernier entré, premier sorti)
 */

/**
 * Une pile d'entiers représentée sous la forme d'une structure chaînée
 */

package StudentCode;

import java.util.ArrayList;
import java.text.MessageFormat;
import static student.Translations.Translator._;

public class Pile{
    
    //  ATTENTION Ici, certains modifiers sont public afin de pouvoir y accéder facilement dans le tests.
    //  Pour les étudiants, l'énoncé indiquera qu'ils sont private !
    
    public class Noeud {
        public Object element;
        public Noeud suivant;
        
        public Noeud(Object o, Noeud n) {
            element = o;
            suivant = n;
        }
    }
    
    public Noeud sommet; // le sommet de la pile
    
    /*
     * @pre -
     * @post initialise une pile vide
     */
    public Pile() {
        sommet = null;
    }
    
    /**
     * @pre o != null
     * @post ajoute l'objet o au sommet de la pile
     */
    public void push( Object o ) {
        Noeud ajout;
        ajout = new Noeud(o, sommet);
        sommet = ajout;
    }
    
    /**
     * @pre -
     * @post retourne l'élément au sommet de la pile et le supprime
     *       null si la pile est vide
     */
    public Object pop() {
        @ @q1@@
    }
    public Object popCorrect() {
        if (sommet == null) {
            return null;
        }
        Object o = sommet.element;
        sommet = sommet.suivant;
        return o;
    }
    
    /**
     * @pre -
     * @post retourne la profondeur, c'est-à-dire le nombre d'éléments dans la pile. 0
     *       pour une pile vide.
     */
    public int depth(){
        @  @q2@@
    }
    public int depthCorrect(){
        if(sommet == null)
            return 0;
        int count = 0;
        Noeud n = sommet;
        while(n != null) {
            n = n.suivant;
            count++;
        }
        return count;
    }
    
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(sommet == null)
            return _("Pile vide\n");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = sommet;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return _("Attention : votre pile est mal formée. Parcourir votre pile provoque une boucle infinie…");
        }
        r.append(MessageFormat.format(_("Sommet : Noeud[{0}]\n"), l.indexOf(sommet)));
        for(int i = 0; i < l.size(); i++) {
            Noeud nn =l.get(i);
            if(nn.suivant != null) {
                r.append(MessageFormat.format(_("Noeud[{0}] : contenu : {1}, noeud suivant : Noeud[{2}]\n"), i, nn.element, l.indexOf(nn.suivant)));
            }
            else {
                r.append(MessageFormat.format(_("Noeud[{0}] : contenu : {1}, noeud suivant : null\n"), i, nn.element));
            }
        }
        return r.toString();
    }
}
