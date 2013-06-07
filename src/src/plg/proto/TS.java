package src.plg.proto;

import java.util.ArrayList;
import java.util.Iterator;


public class TS {
		
	public static ArrayList<Parametros> TablaSimbolos;
	
	public TS() {
		this.TablaSimbolos = new ArrayList<Parametros>();	
	}
	
	public void añadir(String id, String clase, String nivel, String dir, Tipo tipo,int valor) {
		Parametros param = new Parametros(id, clase, nivel, dir, tipo, valor);
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
	
	public String dameDir(String id) {
		
		Iterator<Parametros> it = TablaSimbolos.iterator();
	    while (it.hasNext()) {
	      Parametros element = it.next();
	      String ident=element.getId();
	      String tipo = it.next().getDir();
	     if(id.equals(ident)){
	    	 return tipo;	 
	     }
	    }
		return "";
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

	public int tamanoDe(String id, String clase) {
		
		Iterator<Parametros> it = TablaSimbolos.iterator();
	    while (it.hasNext()) {
	      Parametros element = it.next();
	      String ident=element.getId();
	      String clase2=element.getClase();
	      if(id.equals(ident)&&clase2.equals(clase)){
	    	  return element.getTipo().gettamano(); 
	      }
	    }
		return -1;
	}
	
}
