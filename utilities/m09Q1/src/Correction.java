package src;

/**
 * Une classe représentant une fraction
 *
 * @author O. Bonaventure
 * @version Oct. 2016
 */
 class FractionCorr implements Comparable
{
	private int num; // numerateur
	private int den; // denominateur

	/**
	 * @pre num>=0, den>0
	 * @post construit la fraction num/den
	 */
	public FractionCorr(int num, int den)
	{
		this.num=num;
		this.den=den;
	}

	/*
	 * @pre -
	 * @post retourne le dénominateur de la fraction
	 */
	public int getDen()
	{
		return this.den;
	}


	/*
	 * @pre -
	 * @post retourne le numérateur de la fraction
	 */
	public int getNum()
	{

		return this.num;
	}

	// la méthode qui manque
	public int compareTo(Object other) {
		FractionCorr o = (FractionCorr) other;
		double f1 = (double)this.getNum()/(double)this.getDen(), f2 = (double)o.getNum()/(double)o.getDen();
		if(f1 == f2)
			return 0;
		return (f1 > f2) ? 1 : -1;
	}

}
