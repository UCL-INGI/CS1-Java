package src;

public class Correction {

	private int a;
	private int b;

	public Correction(int a,int b){
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return this.a;
	}

	public int getB() {
		return this.b;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Correction) {
			Correction p = (Correction) o;
			return (this.getA() == p.getA()) && (this.getB() == p.getB());
		}
		return false;
	}

	public String toString(){
		return "("+this.getA()+","+this.getB()+")";
	}
}
