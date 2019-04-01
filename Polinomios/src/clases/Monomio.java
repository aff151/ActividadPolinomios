package clases;

public class Monomio implements Comparable<Monomio>{
	private int coef;
	private int exp;
	public Monomio(int coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int compareTo(Monomio o) {
		//2 monomios son iguales si sus exponentes lo son
		if(Integer.compare(this.exp, o.exp) == 0) return 0;
		return -Integer.compare(this.exp, o.exp);
	}
	public boolean equals(Object o) {
		return this.compareTo((Monomio)o) == 0;
	}
	public String toString() {
		if(this.coef == 0) return "";
		if(this.exp == 1 && this.coef == 1) return "x";
		if(this.exp == 1) return this.coef + "x";
		if(this.exp == 0) return String.valueOf(this.coef);
		return this.coef + "x^" + this.exp;
	}
}
