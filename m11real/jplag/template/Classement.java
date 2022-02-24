/**
 * Une liste de r�sultats ordonn�s (selon {compareTo}), repr�sentant le
 * classement d'une course cycliste.  
 * 
 * @author Benoit Donnet et Charles Pecheur, UCLouvain
 * @version Septembre 2013
 */
public interface Classement {

	/**
	 * Ajoute un r�sultat dans le classement.
	 * 
	 * @pre r != null
	 * @post le r�sultat {r} a �t� ins�r� selon l'ordre du classement. En cas
	 *       d'ex-aequo, {r} est ins�r� apr�s les autres r�sultats de m�me
	 *       ordre.
	 */
	public void add(Resultat r);

	/**
	 * Retire un r�sultat du classement.
	 * 
	 * @pre c != null
	 * @post retire le premier (meilleur) r�sultat pour le coureur {c} du classement. {c}
	 *       est compar� au sens de {equals}. Retourne {true} si un r�sultat a �t� retir�,
	 *       {false} si {c} n'est pas trouv� dans la liste.
	 */
	public boolean remove(Coureur c);

	/**
	 * Taille du classement.
	 * 
	 * @pre -
	 * @post retourne le nombre de r�sultats dans le classement.
	 */
	public int size();

	/**
	 * Le r�sultat d'un coureur.
	 * 
	 * @pre c != null
	 * @post retourne le premier (meilleur) r�sultat du coureur {c} dans le
	 *       classement. Retourne {null} si le coureur ne figure pas dans le
	 *       classement.
	 */
	public Resultat get(Coureur c);
	
	/**
	 * La position d'un coureur dans le classement.
	 * 
	 * @pre c != null
	 * @post retourne la position du coureur {c} dans le classement, � partir de
	 *       1 pour la t�te de classement. Si le coureur figure plusieurs fois
	 *       dans le classement, la premi�re (meilleure) position est retourn�e.
	 *       Retourne -1 si le coureur ne figure pas dans le classement.
	 */
	public int getPosition(Coureur c);

	/**
	 * Le classement sous forme de texte.
	 * 
	 * @pre -
	 * @post retourne le classement sous forme de texte, avec une ligne par
	 *       r�sultat.
	 */
	public String toString();
}
