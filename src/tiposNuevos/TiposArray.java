package tiposNuevos;

import src.plg.proto.Tipo;

public class TiposArray extends Tipo{

	private Tipo tBase;
	private int nElems;
	private int tamArray;
	
	
	public TiposArray(String tipo, int tamano,int nElems, Tipo tBase) {
		super(tipo);
		this.nElems = nElems;
		this.tBase = tBase;
		this.tamArray=nElems*tamano;
	
	}
	public String toString() {
		return "< t : " + "array" + " , nelems : " + nElems + " , tbase : " + tBase.toString() + 
				" tam : " + tamArray + " >";
	}
	

}
