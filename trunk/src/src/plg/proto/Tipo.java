package src.plg.proto;


public class Tipo {
	
	protected String tip;
	protected int tam;
	
	public Tipo(String tipo) {
		this.tip = tipo;
		this.tam = 1;
	}
	
	public Tipo(String tipo, int tam) {
		this.tip = tipo;
		this.tam = tam;
	}
	
	public int getTam() {
		return this.tam;
	}
	
	public String getT() {
		return this.tip;
	}
	
	public String toString() {
		return "< t : "+ tip + " , tam : " + tam + " >";
	}

}
