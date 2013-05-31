package src.plg.proto;

import java.util.ArrayList;


public class TS {
		
	public ArrayList<Parametros> TablaSimbolos;
	
	public TS() {
		this.TablaSimbolos = new ArrayList<Parametros>();	
	}
	
	public void añadir(String id, String clase, String nivel, String dir, String tipo) {
		Parametros param = new Parametros(id, clase, nivel, dir, tipo);
		TablaSimbolos.add(param);
	}

}
