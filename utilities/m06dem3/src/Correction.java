package src;

/**
 * Une classe simple avec un objet contenant un booleen
 */

class DrapeauCorr {
	private boolean drapeau; // la valeur du drapeau

	// Constructeur
	/**
	 * @pre : -
	 * @post : un objet de type DrapeauCorr est cree et represente le booleen
	 *         passe en argument
	 */
	public DrapeauCorr(boolean b) {
		drapeau = b;
	}

	/**
	 * @pre -
	 * @post le drapeau vaut b
	 */
	public void set(boolean b) {
		drapeau = b;
	}

	/**
	 * @pre -
	 * @post retourne la valeur du drapeau
	 */
	public boolean get() {
		return drapeau;
	}
	
	public boolean same(DrapeauCorr d){
		return this.get() == d.get();
	}

}
