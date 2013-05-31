package src.plg.proto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import src.es.ucm.fdi.plg.evlib.SAtributo;

public class TS {
	
	
	public HashMap<SAtributo, ArrayList<SAtributo> > TablaSimbolos;
	public SAtributo Identificador;
	public ArrayList<SAtributo> Parametros;
	
	
	public TS() {
		super();
		this.Parametros = new ArrayList<SAtributo>();
		this.TablaSimbolos = new HashMap<SAtributo, ArrayList<SAtributo>>();	
	}
	
	public void añadirTS(SAtributo Identificador,ArrayList<SAtributo> Parametros){
		
		TablaSimbolos.put(Identificador, Parametros);
		
	}
	
    public ArrayList<SAtributo> creaParametros(SAtributo Clase,SAtributo Nivel,SAtributo Dir,SAtributo Tipo){
	
    	Parametros=new ArrayList<SAtributo>();
	
    	Parametros.add(0, Clase);
    	Parametros.add(1, Nivel);
		Parametros.add(2, Dir);
		Parametros.add(3, Tipo);
	
		return Parametros;
		
	}
	
}
