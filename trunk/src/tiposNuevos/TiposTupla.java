package tiposNuevos;

import java.util.ArrayList;
import java.util.Iterator;

import src.plg.proto.Tipo;

public class TiposTupla extends Tipo{

	private ArrayList<Tipo> campos;
	
	public TiposTupla(String tipo, int tamano,ArrayList<Tipo> campos) {
		super(tipo, tamano);
		// TODO Auto-generated constructor stub
		this.campos = campos;
	}
	
	
	public String toString() {
		String tipoTotal = "< t : " + tip + " , campos : [";
		Iterator<Tipo> it = campos.iterator();
		while (it.hasNext()) {			
			tipoTotal += it.next().toString();
			if (it.hasNext()) tipoTotal += " , ";
		}
		tipoTotal += "] , tam : " + tamano + " >";
		return tipoTotal;
	}

}
