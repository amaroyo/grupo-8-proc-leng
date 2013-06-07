package src.plg.proto;

public class Tipo {
	
	protected String tip;
	protected int tamano;
	
	public Tipo(String tipo) {
		this.tip = tipo;
		this.tamano = 1;
	}
	
	public Tipo(String tipo, int tamano) {
		this.tip = tipo;
		this.tamano = tamano;
	}
	
	public int gettamano() {
		return tamano;
	}
	
	
	public String getTipo() {
		return tip;
	}

	public void setTipo(String tip) {
		this.tip = tip;
	}

	public void settamano(int tamano) {
		this.tamano = tamano;
	}

	public String toString() {
		return "< t : "+ tip + " , tam : " + tamano + " >";
	}

	public boolean compatibles(Tipo tipoExp) {
		
		//tipo = tipoBasico
		if (this.toString().equals(tipoExp.toString())) {
		
			return false;
		}
		else return true;
		
	}

}
