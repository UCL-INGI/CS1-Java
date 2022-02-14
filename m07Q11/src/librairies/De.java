package src.librairies;

import java.util.Random;

public class De
{
    private String nom; // le nom de ce dé (par exemple sa couleur)
    protected Random r ; // nombre aléatoire

    /*
     * @pre nom!=null
     * @post a créé l'instance de la classe De ayant comme nom la chaine n, a initilisé
     *       le générateur de nombres aléatoires
     */
    public De(String n)
    {
        nom=n;
        r= new Random();
    }

    /*
     * @pre -
     * @post a lance le dé et retourne la valeur du dé
     */
    public int lance() 
    {
		return r.nextInt(6)+1;
    }
    
    /*
     * @pre -
     * @post retourne le générateur de nombres aléatoires utilisé par le dé
     */
    public Random getRandom() 
    {
        return r;
    }
    
    /*
     * @pre -
     * @post retourne le nom du dé
     */
    
    private String getNom()
    {
        return nom;
    }
    /*
     * @pre n!=null
     * @post a modifié le nom du dé qui est maintenant n
     */
    public void setNom(String n)
    {
        this.nom=n;
    }
    
    /*
     * @pre r!=null
     * @post a modifié le générateur de nombres aléatoires du dé qui est maintenant r
     */
    public void setRandom(Random r)
    {
        this.r=r;
    }
    
    /*
     * @pre -
     * @post retourne une chaîne de caractères représentant le dé
     */
    public String toString()
    {
		return this.toRealString();
    }

	/**
	 * Méthode utilisée uniquement pour le mocking
	 */

	public String toRealString(){
		return this.nom;
	}
        
    
    /*
     * @pre -
     * @post retourne true ssi other est un dé identique à celui-ci (même nom et même générateur aléatoire)
     */
	public boolean equals(Object other) {
		if (other instanceof De) {
			De otherde = (De) other;
			return (this.getNom().equals(otherde.getNom()) && (this.getRandom().equals(otherde.getRandom())) ); 
		} else {
			return false; 
		}
	}
}
