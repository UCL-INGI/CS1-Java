package src;

import src.Employe;

/**
 * Une classe représentant un directeur
 *
 * @author O. Bonaventure
 * @version Novembre 2016
 */

class DirecteurCorr extends Employe {

	private double prime; // en pourcents du salaire

	/**
	 * Constructeur
	 * @pre		nom != null, salaire > 0 , 0<= prime <= 1
	 * @post	construit une instance de la classe DirecteurCorr avec comme
	 */
	public DirecteurCorr (String nom, double salaire, double prime) {
		super(nom,salaire);
		this.prime = prime;
	}

	/**
	 * @pre		-
	 * @post	retourne le salaire du directeur, calculé comme (1+prime)*salaire
	 */
	public double getSalaire() {
		return super.getSalaire()*(1+this.prime);
	}

	/**
	 * @pre		-
	 * @post	retourne true si this et other sont identique (i.e. même nom,
	 *			même salaire et même prime).
	 */
	public boolean equals(Object other) {
		if( other instanceof DirecteurCorr ) {
			return super.equals(other) && (this.getPrime() == ((DirecteurCorr)other).getPrime());
		}
		return false;
	}

	/**
	 * @pre		-
	 * @post	retourne la prime de ce directeur
	 */
	public double getPrime() {
		return this.prime;
	}
}
