package clases;

import java.util.ArrayList;
import java.util.TreeSet;

public class Polinomio {
	private TreeSet<Monomio> monomios;
	public Polinomio() {
		this.monomios = new TreeSet<>();
	}
	public TreeSet<Monomio> getMonomios() {
		return monomios;
	}
	public void setMonomios(TreeSet<Monomio> monomios) {
		this.monomios = monomios;
	}
	public Monomio find(Monomio monomio) {
		for(Monomio m : this.monomios)
			if(m.equals(monomio))
				return m;
		return null;
	}
	public Polinomio suma(Polinomio p) {
		//Guarda el primer polinomio completo en el resultado
		//Del segundo, guarda todos los monomios que no esten en el resultado. Si uno ya se encuentra, suma los coeficientes
		Polinomio resultado = new Polinomio();
		resultado.setMonomios(this.getMonomios());
		for(Monomio m : p.getMonomios()) {
			if(!resultado.getMonomios().contains(m)) resultado.getMonomios().add(m);
			else {
				Monomio original = resultado.find(m);
				original.setCoef(original.getCoef() + m.getCoef());
			}
		}
		return resultado;
	}
	public Polinomio producto(Polinomio p) {
		Polinomio resultado = new Polinomio();
		for(Monomio m1 : this.getMonomios()) {
			for(Monomio m2 : p.getMonomios()) {
				Monomio producto = new Monomio(m1.getCoef() * m2.getCoef(), m1.getExp() + m2.getExp());
				Monomio original = resultado.find(producto);
				if(original != null) resultado.find(producto).setCoef(original.getCoef() + producto.getCoef());
				else resultado.getMonomios().add(producto);
			}
		}
		return resultado;
	}
	public String division(Polinomio p) {
		int grado = this.getMonomios().first().getExp();
		int gradoAux = grado;
		int [] coeficientes = new int[grado + 1];
		int [] resultado = new int[grado + 1];
		int i = 0;
		//obtener array de coeficientes
		for(Monomio m : this.getMonomios()) {
			//bucle que pone un 0 en el array de coeficientes si no se contiene un grado en el dividendo
			while(gradoAux != m.getExp()) {
				coeficientes[i] = 0;
				gradoAux--;
				i++;
			}
			coeficientes[i] = m.getCoef();
			gradoAux--;
			i++;
		}
		//calculo resultado
		int a = p.getMonomios().last().getCoef() * -1;
		resultado[0] = coeficientes[0];
		for(int j = 0; j < resultado.length-1; j++) {
			int temp = resultado[j] * a; 
			resultado[j+1] = temp + coeficientes[j+1];	
		}
		//convierte el array resultado en polinomio
		Polinomio division = new Polinomio();
		for(int j = 0; j < resultado.length-1; j++) {
			division.getMonomios().add(new Monomio(resultado[j], --grado));
		}
		int resto = resultado[resultado.length-1];
		return division.toString() + "   Resto = " + resto;
	}
	public String toString() {
		String salida = "(";
		for(Monomio m : this.getMonomios())
			salida += m.toString() + "  ";
		return salida + ")";
	}
}
