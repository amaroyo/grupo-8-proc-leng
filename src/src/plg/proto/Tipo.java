package src.plg.proto;


public class Tipo {
	
	protected String t;
	protected int tam;
	
	public Tipo(String t) {
		this.t = t;
		this.tam = 1;
	}
	
	public Tipo(String t, int tam) {
		this.t = t;
		this.tam = tam;
	}
	
	public int getTam() {
		return this.tam;
	}
	
	public String getT() {
		return this.t;
	}
	
	public String toString() {
		return "< t : "+ t + " , tam : " + tam + " >";
	}

}
