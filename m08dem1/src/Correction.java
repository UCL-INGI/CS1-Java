package src;

class DrapeauCorr{
	private boolean drapeau; // la valeur du drapeau

	// Constructeurs
	/**
	 * @pre : -
	 * @post : un objet de type DrapeauCorr est créé et représente le
	 *         drapeau 'false' 
	 */
	public DrapeauCorr()
	{
		drapeau=false;
	}

	/**
	 * @pre : -
	 * @post : un objet de type DrapeauCorr est créé et représente le booleen
	 *         passé en argument
	 */
	public DrapeauCorr(boolean drapeau)
	{
		this.drapeau=drapeau;
	}

	/**
	 * @pre : -
	 * @post : un objet de type DrapeauCorr est créé. Si l'argument est 'V' ou 'v',
	 *         il représente le drapeau true. Sinon il 
	 *         représente le drapeau false 
	 */
	public DrapeauCorr(char c)
	{
		drapeau = (c == 'V' || c == 'v'); 
	}

	public void set(boolean drapeau)
	{
		this.drapeau = drapeau;
	}

	public void set(char c)
	{
		this.drapeau = (c == 'v' || c == 'V') ? true : false;
	}

	public boolean toBoolean()
	{
		return drapeau;
	}

}
