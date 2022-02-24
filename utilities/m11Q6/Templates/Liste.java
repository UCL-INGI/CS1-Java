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
package StudentCode;

import java.util.ArrayList;
import java.text.MessageFormat;
import student.Translations.Translator;

public class Liste {
    
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
    
    public Noeud tete; // le début de la liste

    
    /*
     * @pre -
     * @post enitialise une queue vide
     */
    public Liste() {
        tete = null;
    }
    
    
    /**
     * @pre o!= null
     * @post ajoute l'objet à la liste
     */
    public void ajoute( Object o ) {
        Noeud ajout;
        ajout = new Noeud(o, tete);
        tete=ajout;
    }
    
    public boolean contientCorrect(Object o) {
        if(tete==null) {
            return false;
        }
        Noeud n=tete;
        while(n!=null) {
            if( n.element.equals(o)) {
                return true;
            }
            n=n.suivant;
        }
        return false;
    }
    
    /**
     * @pre o!=null
     * @post retire toutes les copies de l'objet o de la liste.
     *       Retourne le nombre d'objets qui ont été retiré de la liste
     *       (0 si l'objet o n'était pas présent dans la liste)
     */
    public int retireCorrect(Object o)  {
        if(tete==null) {
            return 0;
        }
        Noeud n=tete;
        Noeud precedent=null;
        
        int count=0;
        
        while(n!=null) {
            if( n.element.equals(o)) {
                count++;
                if(precedent!=null) {
                    // retrait au milieu de la liste
                    precedent.suivant=n.suivant;
                } else {
                    // retrait en tete de liste
                    tete=n.suivant;
                }
            } else {
                precedent=n;
            }
            n=n.suivant;
        }
        return count;
    }
    
    /*
     * @pre o!=null
     * @post retourne true si l'objet o se trouve dans la liste, false sinon
     */
    public boolean contient(Object o) {
        @  @q1@@
    }
    
    /**
     * @pre o!=null
     * @post retire toutes les copies de l'objet o de la liste.
     *       Retourne le nombre d'objets qui ont été retiré de la liste
     *       (0 si l'objet o n'était pas présent dans la liste)
     */
    public int retire(Object o)  {
        @ @q2@@
    }
    
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(tete == null)
            return Translator.translate("Liste vide\n");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = tete;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return Translator.translate("Attention : votre liste est mal formée. Parcourir votre liste provoque une boucle infinie…\n");
        }
        r.append(MessageFormat.format(Translator.translate("Tête : Noeud[{0}]\n"), l.indexOf(tete)));
        for(int i = 0; i < l.size(); i++) {
            Noeud nn = l.get(i);
            if(nn.suivant != null) {
                r.append(MessageFormat.format(Translator.translate("Noeud[{0}] : contenu : {1}, noeud suivant : Noeud[{2}]\n"), i, nn.element, l.indexOf(nn.suivant)));
            }
            else {
                r.append(MessageFormat.format(Translator.translate("Noeud[{0}] : contenu : {1}, noeud suivant : null\n"), i, nn.element));
            }
        }
        return r.toString();
    }
}
