package parser;

public class Nodo {
	ByteCode info;
	Nodo izq, der;

	public Nodo() {
		info = null;
		izq = null;
		der = null;

	}
	
	public ByteCode getInfo(){
		return info;
	}
	
	public Nodo getDer(){
		return der;
	}
	
	public Nodo getIzq(){
		return izq;
	}
	
}