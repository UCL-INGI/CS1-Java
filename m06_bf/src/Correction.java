package src;

public class Correction {

	String n;
	float s;

	public Correction(String n, float s) {
	    this.n = n;
	    this.s = s;
	}

	public String getNom(){
	    return this.n;
	    
	}

	public float getSalaire(){
	    return this.s;
	}

	public String toString() {
	    return this.getNom() + " : " + this.getSalaire();
	}

	public void augmente(float pourcentage) {
	    this.s = this.s + (this.s*(pourcentage/100));
	}
}
