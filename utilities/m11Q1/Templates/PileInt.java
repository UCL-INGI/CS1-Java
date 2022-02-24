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
import student.Translations.Translator;

public class PileInt{
    
    //  ATTENTION Ici, certains modifiers sont public afin de pouvoir y accéder facilement dans le tests.
    //  Pour les étudiants, l'énoncé indiquera qu'ils sont private !
    
    /*
     * une classe interne représentant un noeud de la queue
     */
    public class Noeud {
        public int element;
        public Noeud suivant;
        
        public Noeud(int i, Noeud n) {
            element = i;
            suivant = n;
        }
    }
    
    public Noeud sommet; // le sommet de la pile
    public int profondeur; // le nombre d'entiers dans la pile
    
    /*
     * @pre -
     * @post initialise une pile vide
     */
    public PileInt() {
        sommet = null;
        profondeur = 0;
    }
    
    /**
     * @pre
     * @post ajoute l'entier i au sommet de la pile
     */
    public void push(int i) {
        @ @q1@@
    }
    public void pushCorrect(int i) {
        Noeud ajout;
        ajout = new Noeud(i, sommet);
        sommet = ajout;
        profondeur++;
    }
    
    /**
     * @pre -
     * @post retourne l'entier au sommet de la pile et le retire de la pile
     *       lance l'exception IllegalStateException si la pile est vide
     */
    public int pop() throws IllegalStateException {
        @ @q2@@
    }
    public int popCorrect() throws IllegalStateException {
        if (sommet == null) {
            throw new IllegalStateException();
        }
        int o = sommet.element;
        sommet = sommet.suivant;
        profondeur--;
        return o;
    }
    
    /**
     * @pre -
     * @post retourne la profondeur, c'est-à-dire le nombre d'éléments dans la pile. 0
     *       pour une pile vide.
     */
    public int depth(){
        return profondeur;
    }
    
    /*
     * @pre -
     * @post utilisé par les tests, présente la pile sous la forme d'un String
     */
    public String toString(){
        if(sommet == null)
            return Translator.translate("Pile vide\n");
        StringBuilder r = new StringBuilder(400);
        ArrayList<Noeud> l = new ArrayList<Noeud>();
        Noeud n = sommet;
        while(n != null) {
            l.add(n);
            n = n.suivant;
            if(l.size() > 50)
                return Translator.translate("Attention : votre pile est mal formée. Parcourir votre pile provoque une boucle infinie…");
        }
        r.append(MessageFormat.format(Translator.translate("Sommet : Noeud[{0}]\nProfondeur : {1}\n"), l.indexOf(sommet), profondeur));
        for(int i = 0; i < l.size(); i++) {
            Noeud nn =l.get(i);
            if(nn.suivant != null) {
                r.append(MessageFormat.format(Translator.translate("Noeud[{0}] : contenu : {1}, noeud suivant : Noeud[{2}]\n"), i, nn.element, l.indexOf(nn.suivant)));
            }
            else {
                r.append(MessageFormat.format(Translator.translate("Noeud[{0}] : contenu : {1}, noeud suivant : null\n"), i, nn.element));
            }
        }
        if(l.size() != profondeur) {
            r.append(MessageFormat.format(Translator.translate("Attention : la variable d''instance ''{0}'' ({1}) ne correspond pas au nombre d''entiers sur la pile.\n"), "profondeur", profondeur));
        }
        return r.toString();
    }
}
