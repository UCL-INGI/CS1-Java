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
import static student.Translations.Translator._;

public class Queue{
    
    //  ATTENTION Ici, certains modifiers sont public afin de pouvoir y accéder facilement dans le tests.
    //  Pour les étudiants, l'énoncé indiquera qu'ils sont private !
    
    public class Noeud {
        public Object element;
        public Noeud suivant;
        public Noeud precedent;
        
        public Noeud(Object o, Noeud n, Noeud p) {
            element = o;
            suivant = n;
            precedent = p;
        }
    }
    
    public Noeud entree; // correspond à tout moment à l'entrée de la queue
    public Noeud sortie; // correspond à tout moment à la sortie de la queue

    /*
     * @pre -
     * @post initialise une queue vide
     */
    public Queue() {
        entree = null;
        sortie = null;
    }
    
    
    /**
     * @pre o!= null
     * @post ajoute l'objet à la queue
     */
    public void ajouteCorrect(Object o) {
        Noeud ajout;
        ajout = new Noeud(o, entree, null);
        if(entree!=null) {
            entree.precedent=ajout;
        }
        entree = ajout;
        if(sortie == null) {
            sortie = ajout;
        }
    }
    
    /**
     * @pre -
     * @post retourne le plus ancien objet de la queue et null si la queue est vide
     */
    public Object retireCorrect () {
        if ( sortie == null ) {
            return null;
        }
        Object o=sortie.element;
        sortie=sortie.precedent;
        if(sortie!=null) {
            sortie.suivant=null;
        } else {
            entree=null;
        }
        return o;
    }
    
    /**
     * @pre o != null
     * @post ajoute l'objet à la queue
     */
    public void ajoute(Object o) {
        @@q1@@
    }
    
    /**
     * @pre -
     * @post retourne le plus ancien objet de la queue via le noeud sortie et
     *       le retire de la queue
     *       null si la queue est vide
     */
    public Object retire() {
        @@q2@@
    }
    
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(entree == null && sortie == null)
            return _("Queue vide\n");
        if(entree == null && sortie != null)
            return MessageFormat.format(_("Attention ''{0}'' pointe vers null mais pas ''{1}'' !\n"), "entree", "sortie");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = entree;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return _("Attention : votre queue est mal formée. Parcourir votre queue provoque une boucle infinie…\n");
        }
        r.append(MessageFormat.format(_("Entrée : Noeud[{0}]\n"), l.indexOf(entree)));
        if(sortie != null)
            if(l.indexOf(sortie) == -1)
                r.append(MessageFormat.format(_("Attention : ''{0}'' pointe vers un noeud non présent dans la queue !\n"), "sortie"));
            else
                r.append(MessageFormat.format(_("Sortie : Noeud[{0}]\n"), l.indexOf(sortie)));
        else
            r.append(MessageFormat.format(_("Attention ''{0}'' pointe vers null !\n"), "sortie"));
        for(int i = 0; i < l.size(); i++) {
            Noeud nn = l.get(i);
            String suivS = (nn.suivant == null) ? "null" : MessageFormat.format(_("Noeud[{0}]"), +l.indexOf(nn.suivant));
            String percS = (nn.precedent == null) ? "null" : MessageFormat.format(_("Noeud[{0}]"), +l.indexOf(nn.precedent));
            r.append(MessageFormat.format(_("Noeud[{0}] : contenu : {1}, noeud précérent : {2}, noeud suivant : {3}\n"), i, nn.element, percS, suivS));
        }
        return r.toString();
    }
}
