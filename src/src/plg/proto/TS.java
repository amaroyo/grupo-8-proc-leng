package src.plg.proto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TS {
	public String Identificador;
	public HashMap<String, ArrayList<String> > TablaSimbolos;
	public ArrayList<String> Parametros;
	public TS(String identificador, HashMap<String, ArrayList<String>> TablaSimbolos,
			ArrayList<String> parametros) {
		super();
		Identificador = identificador;
		Parametros = new ArrayList<String>();
		TablaSimbolos = new HashMap<String, ArrayList<String>>();	
	}
	
	public void añadirTS(HashMap<String, ArrayList<String>> TS,String Identificador,ArrayList<String> Parametros){
		
	}
	
}
