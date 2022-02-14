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

public class FIFOQueue{
    
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
    
    public Noeud entree; // correspond à tout moment à l'entrée de la queue
    public Noeud sortie; // correspond à tout moment à la sortie de la queue
    
    /*
     * @pre -
     * @post enitialise une queue vide
     */
    public FIFOQueue() {
        entree=null;
        sortie=null;
    }
    
    
    /**
     * @pre o != null
     * @post ajoute l'objet à la queue via le noeud entree
     */
    public void ajoute( Object o ) {
        Noeud ajout;
        ajout = new Noeud(o, entree);
        entree=ajout;
        if(sortie==null) {
            sortie=ajout;
        }
    }
    
    /**
     * @pre -
     * @post retourne le plus ancien objet de la queue via le noeud sortie et
     *       le retire de la queue
     *       null si la queue est vide
     */
    public Object retire () {
        @@q1@@
    }
    public Object retireCorrect(){
        if(sortie == null)
            return null;
        Object i= sortie.element;
        if(entree == sortie) {
            entree = null;
            sortie = null;
            return i;
        }
        Noeud runner = entree;
        while(runner.suivant!=sortie) {
            runner=runner.suivant;
        }
        runner.suivant=null;
        sortie=runner;
        return i;
    }
    
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(entree == null && sortie == null)
            return Translator.translate("Queue vide\n");
        if(entree == null && sortie != null)
            return MessageFormat.format(Translator.translate("Attention ''{0}'' pointe vers null mais pas ''{1}'' !\n"), "entree", "sortie");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = entree;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return Translator.translate("Attention : votre queue est mal formée. Parcourir votre queue provoque une boucle infinie…\n");
        }
        r.append(MessageFormat.format(Translator.translate("Entrée : Noeud[{0}]\n"), l.indexOf(entree)));
        if(sortie != null)
            if(l.indexOf(sortie) == -1)
                r.append(MessageFormat.format(Translator.translate("Attention : ''{0}'' pointe vers un noeud non présent dans la queue !\n"), "sortie"));
            else
                r.append(MessageFormat.format(Translator.translate("Sortie : Noeud[{0}]\n"), l.indexOf(sortie)));
        else
            r.append(MessageFormat.format(Translator.translate("Attention ''{0}'' pointe vers null !\n"), "sortie"));
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
