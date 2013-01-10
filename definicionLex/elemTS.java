package definicionLex;

enum tipos {INT,BOOL,STRING,FLOAT}
public class elemTS {
	
	String id;
	String tipo;
	int constante;
	int direccion;
	
	public elemTS(String id, String tipo, int constante, int direccion) {
		this.id = id;
		this.tipo = tipo;
		this.constante = constante;
		this.direccion = 0+direccion;
	}

	public String toString() {
		return "elemTS [id=" + id + ", tipo=" + tipo + ", constante="
				+ constante + ", direccion=" + direccion + "]";
	}
	
}
