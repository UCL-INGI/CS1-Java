/**
 *  Copyright (c) 2017 Brandon Naitali
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
import StudentCode.*;

public class Correction{
	/*
	* @pre o!=null
	* @post retourne true si l'objet o se trouve dans l'AList
	*/
	public static boolean contains(Etudiant al, Object o)
	{
		for(int i=0;i<al.length();i++) {
			if(o.equals(al.l[i])) {
				return true;
			}
		}
		return false;
	}
	public static Object get(Etudiant al, int index)
	{
		if(index<al.length())
			return al.l[index];
		else
			return null;

	}
	/*
	* @pre -
	* @post ajoute l'objet o à la liste en position index. Si index>length, augmente d'avoir la taille
	*       de la liste en créant un nouveau tableau et en y recopiant toutes les
	*        références de la liste this avant d'ajouter l'objet o
	*
	*/
	public static void add(Etudiant al, int index, Object o)
	{
		if(index<al.length()) {
			al.l[index]=o;
		}
		else
		{
			Object[] lnew=new Object[index+1];
			for(int i=0; i<al.length();i++) {
				lnew[i]=al.l[i];
			}
			lnew[index]=o;
			al.l=lnew;
		}
	}
	/*
	* @pre 0<=index< this.length()
	* @post remplace la référence se trouvant à l'index dans la liste par la référence à o
	*
	*/
	public static void set(Etudiant al, int index, Object o)
	{
		al.l[index]=o;
	}

}
