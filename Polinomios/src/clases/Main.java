package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Polinomio> expresiones = new ArrayList<>();
	static File archivo = new File("src/clases/datos.txt");
	public void cargarArchivo(File archivo2) {
		Scanner scan = null;
		String linea = "";
		String [] items = null; 
		
		try {
			scan = new Scanner(archivo2);
		}catch (IOException e) {
			System.exit(-1);
		}
		while (scan.hasNextLine()){
			linea = scan.nextLine().trim();
			if(linea.isEmpty() || linea.startsWith("@")) continue;
			items = linea.split(",");
			if(items.length%2 != 0) {
				System.out.println("¡Faltan datos por introducir!");
				System.exit(0);
			}
			Polinomio p = new Polinomio();
			for(int i = 0; i < items.length; i+=2) {
				if(Integer.parseInt(items[i]) == 0 && Integer.parseInt(items[i+1]) == 0) break;
				if(Integer.parseInt(items[i+1]) < 0) {
					System.out.println("¡No se pueden introducir exponentes negativos!");
					System.exit(0);
				}
				Monomio m = new Monomio(Integer.parseInt(items[i]), Integer.parseInt(items[i+1]));
				if(!p.getMonomios().contains(m)) p.getMonomios().add(m);
				else {
					for(Monomio mon : p.getMonomios())
						if(mon.equals(m))
							mon.setCoef(mon.getCoef() + m.getCoef()); break;
				}
			}
			expresiones.add(p);
		}
		scan.close();
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.cargarArchivo(archivo);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String opcion = "";
		System.out.println("Selecciona la operación: ");
		System.out.println("1. Suma");
		System.out.println("2. Producto");
		System.out.println("3. División");
		try {
			opcion = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (opcion) {
		case "1":
			System.out.println(expresiones.get(0) + " + " + expresiones.get(1) + " = " +
					expresiones.get(0).suma(expresiones.get(1)));
			break;
		case "2":
			System.out.println(expresiones.get(0) + " x " + expresiones.get(1) + " = " +
					expresiones.get(0).producto(expresiones.get(1)));
			break;
		case "3":
			if(comprobarDisible(expresiones.get(1))){
				System.out.println(expresiones.get(0) + " / " + expresiones.get(1) + " = " +
						expresiones.get(0).division(expresiones.get(1)));
				break;
			} else {
				System.out.println("El polinomio divisor debe ser del tipo (x-a)");
				System.exit(0);
			}
		default:
			break;
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean comprobarDisible(Polinomio divisor) {
		// TODO Auto-generated method stub
		if(divisor.getMonomios().size() != 2) return false;
		if(divisor.getMonomios().first().getExp() != 1 || divisor.getMonomios().first().getCoef() != 1) return false;
		if(divisor.getMonomios().last().getExp() != 0) return false;
		return true;
	}
}
