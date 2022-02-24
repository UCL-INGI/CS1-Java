/**
 * 
 * @author Olivier Bonaventure
 * @version Oct. 2016
 * refactor Alexandre Dubray août 2017
 * Classe Date que les étudiants doivent compléter petit à petit dans la mission 6
 */
package src;

public class Correction 
{
	public int jour; // le jour du mois
	public int mois; // le mois
	public int annee; // l'année
	
	// Q1
	
	/*
	 * @pre 1<= jour <=31, 1<= mois <=12
	 * @post retourne une date, les mois commencent à 1 pour janvier
	 *       On ne gère pas les années bissextiles, février est supposé toujours
	 *       contenir 28 jours
	 */
	
	public Correction(int jour, int mois, int annee)
	{
		this.jour=jour;
		this.mois=mois;
		this.annee=annee;
	}
	
	
	// Q2
	/*
	 * @pre -
	 * @post retourne le jour 
	 */
	public int getJour()
	{
	   return this.jour; 
	
	}
	
	// Q2
	/*
	 * @pre -
	 * @post retourne le mois (1=janvier, 2=février, ...
	 */
	public int getMois()
	{
	return this.mois;
	}
	
	// Q2
	/*
	 * @pre -
	 * @post retourne l'année
	 */
	public int getAnnee()
	{
	return this.annee;
	}
	
	
	// Q3
	/*
	 * @pre d!=null
	 * @post retourne true si this et d correspondent à la même date, false sinon
	 */
	public boolean identique(Correction d) {
		if(d == null)
			return false;
		return d.getJour() == this.getJour() && d.getMois() == this.getMois() && d.getAnnee() == this.getAnnee();
	
	}
	
	public boolean equals(Object o) {
		if (o instanceof Correction) {
			return ((Correction) o).getJour() == this.jour && this.mois == ((Correction) o).getMois() && this.annee == ((Correction) o).getAnnee();
		}
		return false;
	}
	
	// Q4
	/* 
	 * @pre -
	 * @post retourne la date correspondant au jour qui suit this. Cette méthode
	 *       ne supporte pas les années bissextiles, février est supposé avoir toujours
	 *       28 jours.
	 */

	public Correction demain() {
			// dernier jour de l'annee
			int jour = this.getJour(), mois = this.getMois() , annee = this.getAnnee();
			if(mois == 12 && jour == 31)
				return new Correction(1,1,annee+1);
			// Dernier jour d'un mois à 31 jour
			if(jour == 31 && ( mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 ))
				return new Correction(1,mois+1,annee);
			// Dernier jour d'un mois à 30 jour
			if( jour == 30 && (mois == 4 || mois == 6 || mois == 9 || mois == 11))
				return new Correction(1,mois+1,annee);
			// Dernier jour de février
			if( jour == 28 && mois == 2)
				return new Correction(1,3,annee);
			// Pas un dernier jour, on reste dans le même mois
			return new Correction(jour+1,mois,annee);
	}
	
	// ne pas donner aux étudiants, mais inclure dans le code
	
	
	
	public String toString() {
	return this.jour+"/"+this.mois+"/"+this.annee;
	
	}
	
}
