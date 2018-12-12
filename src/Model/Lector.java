package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Lector {
	private String direccion;
	static private Vector<Double[]> puntos;
	static private String nombres[];
	static private Vector<String> descrip;
	private Scanner sc;
	
	public static Vector<String> getDescrip() {
		return descrip;
	}

	public static void setDescrip(Vector<String> descrip) {
		Lector.descrip = descrip;
	}

	public Vector<Double[]> getPuntos() {
		return puntos;
	}

	@SuppressWarnings("static-access")
	public void setPuntos(Vector<Double[]> puntos) {
		this.puntos = puntos;
	}

	public String[] getNombres() {
		return nombres;
	}

	@SuppressWarnings("static-access")
	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}

	public Lector(String d){
		direccion=d;
	}
	
	public void leer(){
		puntos=new Vector<Double[]>();
		descrip=new Vector<String>();
		nombres=new String[2];
		
		try {
			sc = new Scanner(new FileReader(direccion));
			int contador=0;
			Double[] a;
			String aux;
			while(sc.hasNext()){
				a=new Double[2];
				if(sc.hasNextDouble()==false){
					aux=sc.next();
					if(aux.charAt(0)=='#'){
						descrip.add(sc.nextLine());
					} else {
						if(contador<2){
						nombres[contador]=aux;
						contador++;
						}
					}
				} else {
					a[0]=sc.nextDouble();
					a[1]=sc.nextDouble();
					puntos.add(a);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
