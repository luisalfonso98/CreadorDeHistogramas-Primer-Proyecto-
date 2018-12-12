package Model;
public class Escala {
	private int coorBase;
	private double valorBase;
	private double factor;
	
	public int getCoorBase() {
		return coorBase;
	}
	public void setCoorBase(int coorBase) {
		this.coorBase = coorBase;
	}
	public double getValorBase() {
		return valorBase;
	}
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	Escala(int coorBase, double valorBase, double factor) {
		this.coorBase = coorBase;
		this.valorBase = valorBase;
		if (factor != 0) {
			this.factor = factor;
		} else
			factor = 1;
	}
	public double toValor(int coor){
		return valorBase +(coor-coorBase)/factor;
	}
	public int toCoor(double valor){
		return (int) (coorBase +(valor-valorBase)*factor);
	}
}