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

import java.util.ArrayList; // ne pas montrer aux étudiants, utilisé pour les tests
import java.text.MessageFormat;
import static student.Translations.Translator._;

public class OrderedList{
    
    //  ATTENTION Ici, certains modifiers sont public afin de pouvoir y accéder facilement dans le tests.
    //  Pour les étudiants, l'énoncé indiquera qu'ils sont private !
    
    public class Noeud {
        public double d;
        public Noeud suivant;
        public Noeud precedent;
        
        public Noeud(double d, Noeud n, Noeud p) {
            this.d = d;
            suivant = n;
            precedent = p;
        }
        
        // code non-fourni
        public boolean equals(Object o) {
            if(o instanceof Noeud) {
                Noeud n=(Noeud) o;
                return (this.d == n.d && this.suivant == n.suivant && this.precedent == n.precedent);
            }
            return false;
        }
    }

    public Noeud min; // le plus petit nombre de la liste
    public Noeud max; // le plus grand nombre de la liste
    
    public OrderedList() {
        min = null;
        max = null;
    }
    
    public void ajouteCorrect( double n) {
        Noeud ajout;
        // trouver la position
        if(min == null) {
            min = new Noeud(n, null, null);
            max = min;
            return;
        }
        if(n >= max.d) {
            ajout = new Noeud(n, null, max);
            max.suivant = ajout;
            max = ajout;
            return;
        }
        if(n <= min.d) {
            ajout = new Noeud(n, min, null);
            min.precedent = ajout;
            min = ajout;
            return;
        }
        Noeud no = min;
        while(no != null) {
            if(n > no.d) {
                no = no.suivant;
            } else {
                ajout = new Noeud(n, no, no.precedent);
                no.precedent = ajout;
                ajout.precedent.suivant = ajout;
                return;
            }
        }
    }
    
    /**
     * @pre -
     * @post retourne le plus grand élément de la queue et lance un exception IllegalStateException si la queue est vide
     */
    public double retireMaxCorrect () throws IllegalStateException {
        if (max == null)
            throw new IllegalStateException();
        double v=max.d;
        max=max.precedent;
        if(max==null)
            min=null;
        else
            max.suivant=null;
        return v;
    }
    /**
     * @pre
     * @post ajoute le nombre n à la liste ordonnée. Après ajout, la liste est
     *       ordonnée, le nombre le plus petit est à la référence min et le plus grand à la
     *       référence max
     */
    public void ajoute(double n) {
        @ @q1@@
    }
    
    /**
     * @pre -
     * @post retourne le plus grand élément de la queue et le retire de la queue
     *       lance un exception IllegalStateException si la queue est vide
     */
    public double retireMax() throws IllegalStateException {
        @ @q2@@
    }
    
    
    // code non fourni
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(min == null && min == min)
            return _("Liste vide\n");
        if(min == null && max != null)
            return MessageFormat.format(_("Attention ''{0}'' pointe vers null mais pas ''{1}'' !\n"), "min", "max");
        if(min != null && max == null)
            return MessageFormat.format(_("Attention ''{0}'' pointe vers null mais pas ''{1}'' !\n"), "max", "min");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = min;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return _("Attention : votre liste est mal formée. Parcourir votre liste provoque une boucle infinie…\n");
        }
        r.append(MessageFormat.format(_("Min : Noeud[{0}]\n"), l.indexOf(min)));
        r.append(MessageFormat.format(_("Max : Noeud[{0}]\n"), l.indexOf(max)));
        for(int i = 0; i < l.size(); i++) {
            Noeud nn = l.get(i);
            String suivS = (nn.suivant == null) ? "null" : MessageFormat.format(_("Noeud[{0}]"), +l.indexOf(nn.suivant));
            String percS = (nn.precedent == null) ? "null" : MessageFormat.format(_("Noeud[{0}]"), +l.indexOf(nn.precedent));
            r.append(MessageFormat.format(_("Noeud[{0}] : contenu : {1}, noeud précérent : {2}, noeud suivant : {3}\n"), i, nn.d, percS, suivS));
        }
        return r.toString();
    }
}
