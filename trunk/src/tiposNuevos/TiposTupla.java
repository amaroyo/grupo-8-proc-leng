package tiposNuevos;

import java.util.ArrayList;
import java.util.Iterator;

import src.plg.proto.Tipo;

public class TiposTupla extends Tipo{

	private ArrayList<Tipo> campos;
	private int tamTupla;
	
	public TiposTupla(String tipo,ArrayList<Tipo> campos) {
		super(tipo);
		// TODO Auto-generated constructor stub
		this.campos = campos;
	}
	
	
	public String toString() {
		tamTupla=0;
		String tipoTotal = "< t : " + tip + " , campos : [";
		Iterator<Tipo> it = campos.iterator();
		while (it.hasNext()) {
			Tipo aux=it.next();
			tamTupla=tamTupla+aux.gettamano();
			tipoTotal += aux.toString();
			if (it.hasNext()) tipoTotal += " , ";
		}
		tipoTotal += "] , tam : " + tamTupla + " >";
		return tipoTotal;
	}

}
