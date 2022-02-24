/**
 * @author O. Bonaventure
 */
package src;

import src.librairies.MyString;

class StringTabCorr implements MyString {

	private char [] s;

	/**
	 * @pre		-
	 * @post	Constructeur
	 */
	public StringTabCorr(char c) {
		this.s = new char [] {c};
	}

	/**
	 * @pre		-
	 * @post	Constructeur
	 */
	public StringTabCorr(char [] c) {
		this.s = c;
	}

	/**
	 * Setters utilisez pour set le tableau s
	 * pour tester les méthodes des étudiants.
	 * 
	 */
	 public void setArray(char [] c) {
		this.s = c;
	}

	public MyString concat(char c) {
		char [] s2 = new char[this.length()+1];
		for(int i = 0; i < this.length(); i++)
				s2[i] = this.charAt(i);
		s2[s2.length-1] = c;
		return new StringTabCorr(s2);
	}

	public boolean contains(MyString other) {
		if(other.length() > this.length())
			return false;

		for(int i=0;i<= this.length()-other.length();i++) {
			boolean found = true;

			for(int j=i;j<other.length();j++) {
				if(other.charAt(j-i) != this.charAt(j)) {
					found = false;
					break;
				}
			}
			if(found)
				return true;
		}
		return false;
	}

	public int length() {
		return this.s.length;
	}

	public char charAt(int i) {
		return this.s[i];
	}
}

