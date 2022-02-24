package StudentCode;

import src.librairies.Byte;

/**
 * Implémentation d'un Byte
 *
 * @author O. Bonaventure
 * @version Novembre 2016
 */
class ByteTabCorr implements Byte {

	private boolean b[];

	/**
	 * @pre		-
	 * @post	A construit un Byte dont tout
	 *			les bits sont à 0 (false)
	 */
	public ByteTabCorr() {
		this.b = new boolean [] {false,false,false,false,false,false,false,false};
	}

	/**
	 * @pre		s.length == 8 et s ne contient que des 0 et des 1
	 * @post	a construit un byte dont la représentation naturelle
	 *			en String est s. La représentation naturelle d'un byte
	 *			est celle avec le bit de poids fort à gauche. Le byte
	 *			est stocké dans le tableau de manière à ce que le bit de 
	 *			poids faible soit à l'indice 0.
	 */
	public ByteTabCorr(String s) {
		this.b = new boolean[8];
		for(int i=0;i<8;i++)
			this.b[i] = (s.charAt(7-i) == '0') ? false : true;
	}

	/**
	 * @pre		-
	 * @post	retourne la représentation naturelle du byte
	 *			stocké dans le tableau.
	 */
	public String toString() {
		String r = "";
		for(int i= this.b.length-1;i>=0;i--)
			r += b[i] ? '1' : '0';
		return r;
	}

	public int getBit(int i) {
		return b[i] ? 1 : 0;
	}

	public void setBit(int i) {
		this.b[i] = true;
	}

	public void resetBit(int i) {
		this.b[i] = false;
	}

	public void shiftLeft() {
		boolean t = this.b[0];
		for(int i=0;i<8;i++) {
			boolean tmp = this.b[i];
			this.b[i] = t;
			t = tmp;
		}
		this.b[0]= false;
	}
	
	public void rotateRight() {
		boolean t = this.b[this.b.length-1];
		for(int i=this.b.length -2;i>=0;i--) {
			boolean tmp = this.b[i];
			this.b[i] = t;
			t = tmp;
		}
		this.b[this.b.length-1] =t;
	}

	public Byte and(Byte b1) {
		String str="";
		for(int i=0;i<8;i++)
			str += (this.getBit(i)==1 && b1.getBit(i) == 1) ? "1" : "false";
		return new ByteTab(str);
	}
}
