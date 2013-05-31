package src.plg.proto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TS {
	
	
	public HashMap<String, ArrayList<String> > TablaSimbolos;
	public String Identificador;
	public ArrayList<String> Parametros;
	
	
	public TS() {
		super();
		this.Parametros = new ArrayList<String>();
		this.TablaSimbolos = new HashMap<String, ArrayList<String>>();	
	}
	
	public void añadirTS(HashMap<String, ArrayList<String>> TS,String Identificador,ArrayList<String> Parametros){
		
		TS.put(Identificador, Parametros);
		
	}
	
    public ArrayList<String> creaParametros(String Clase,String Nivel,String Dir,String Tipo){
	
    	Parametros=new ArrayList<String>();
	
    	Parametros.add(0, Clase);
    	Parametros.add(1, Nivel);
		Parametros.add(2, Dir);
		Parametros.add(3, Tipo);
	
		return Parametros;
		
	}
	
}
