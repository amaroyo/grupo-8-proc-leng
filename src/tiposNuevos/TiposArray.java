package tiposNuevos;

import src.plg.proto.Tipo;

public class TiposArray extends Tipo{

	private Tipo tBase;
	private int nElems;
	
	
	public TiposArray(String tipo, int tamano,int nElems, Tipo tBase) {
		super(tipo, tamano);
		this.nElems = nElems;
		this.tBase = tBase;
	
	}
	public String toString() {
		return "< t : " + tip + " , nelems : " + nElems + " , tbase : " + tBase.toString() + 
				" tam : " + tamano + " >";
	}
	

}
