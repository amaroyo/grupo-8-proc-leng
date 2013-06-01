package src.plg.proto;

public class Parametros {

	
	private String id;
	private String clase;
	private String nivel;
	private String dir;
	private Tipo tipo;

	public Parametros(String id, String clase, String nivel, String dir, Tipo tipo2) {

		this.id = id;
		this.clase = clase;
		this.nivel = nivel;
		this.dir = dir;
		this.tipo = tipo2;
	}
	
	public Parametros(String clase, Tipo tipo) {
		this.clase = clase;
		this.tipo = tipo;
	}
	
	public int getTam(Tipo tipo2,String clase) {
		
		if ( clase.equals("PF")) return 1;
		else return 0;
	}
	
	
	public Tipo getTipo() {
    return tipo;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	
	
}
