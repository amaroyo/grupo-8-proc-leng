package parser;

public class TablaInfo {

	private String tipo;
	private boolean constante;
	private int direccion;

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return this.tipo;
	}

	public boolean isConstante() {
		return constante;
	}

	public void setConstante(boolean constante) {
		this.constante = constante;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	TablaInfo(String t, boolean cte, int dir) {
		this.tipo = t;
		this.constante = cte;
		this.direccion = dir;
	}

	public String print() {
		String c;
		if (this.constante)
			c = " es ";
		else
			c = " no es ";

		String x = "de tipo: " + tipo + c
				+ "constante y tiene esta direccion de memoria: " + direccion;
		return x;
	}
}
