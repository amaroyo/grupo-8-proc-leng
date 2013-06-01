package src.plg.proto;

import java.util.ArrayList;
import java.util.Iterator;


public class TS {
		
	public static ArrayList<Parametros> TablaSimbolos;
	
	public TS() {
		this.TablaSimbolos = new ArrayList<Parametros>();	
	}
	
	public void añadir(String id, String clase, String nivel, String dir, Tipo tipo) {
		Parametros param = new Parametros(id, clase, nivel, dir, tipo);
		TablaSimbolos.add(param);
	}

	public TS copia() {
		TS ts = new TS();
		for (int i = 0; i < TablaSimbolos.size(); i++)
			ts.TablaSimbolos.add(TablaSimbolos.get(i));
		return ts;
	}
	
	public Tipo dameTipo(String id) {
		
		Iterator<Parametros> it = TablaSimbolos.iterator();
	    while (it.hasNext()) {
	      Parametros element = it.next();
	      String ident=element.getId();
	      Tipo tipo = it.next().getTipo();
	     if(id.equals(ident)){
	    	 return tipo;	 
	     }
	    }
		return null;
	}
	
	public static boolean buscaId(String id) {
		
		Iterator<Parametros> it = TablaSimbolos.iterator();
	    while (it.hasNext()) {
	      Parametros element = it.next();
	      String ident=element.getId();
	      if(id.equals(ident)){
	    	  return true; 
	      }
	    }
		return false;
	}
	
}
