package StudentCode;

import src.librairies.DrapeauIF;

public class Drapeau{

	private boolean drapeau; // la valeur du drapeau

	// Constructeurs
	/**
	 * @pre : -
	 * @post : un objet de type Drapeau est créé et représente le
	 *         drapeau 'false' 
	 */
	public Drapeau()
	{
		drapeau=false;
	}

	/**
	 * @pre : -
	 * @post : un objet de type Drapeau est créé et représente le booleen
	 *         passé en argument
	 */
	public Drapeau(boolean drapeau)
	{
		this.drapeau=drapeau;
	}

	/**
	 * @pre : -
	 * @post : un objet de type Drapeau est créé. Si l'argument est 'V' ou 'v',
	 *         il représente le drapeau true. Sinon il 
	 *         représente le drapeau false 
	 */
	public Drapeau(char c)
	{
		drapeau = (c == 'V' || c == 'v'); 
	}

	public void set(boolean drapeau)
	{
		@	@q1@@
	}

	public void set(char c)
	{
		@	@q2@@
	}

	public boolean toBoolean()
	{
		return drapeau;
	}

}
