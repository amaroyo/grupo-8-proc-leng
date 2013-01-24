package aLexico;

import java.util.Vector;

public class ALexico {
	
	private Vector<String> palReservadas;
	
	public ALexico() {
		
	}
	
	public void iniciaVecPalReservadas() {
		palReservadas.clear();
		
		palReservadas.add("var");
		palReservadas.add("const");
		palReservadas.add("var-consts");
		palReservadas.add("instructions");
		palReservadas.add("program");
		palReservadas.add("swap1");
		palReservadas.add("swap2");
		palReservadas.add("e");
		palReservadas.add("E");
		
		//tipos
		palReservadas.add("boolean");
		palReservadas.add("character");
		palReservadas.add("natural");
		palReservadas.add("integer");
		palReservadas.add("float");
		
		//Valores booleanos
		palReservadas.add("true");
		palReservadas.add("false");
		
		//Operadores booleanos
		palReservadas.add("or");
		palReservadas.add("and");
		palReservadas.add("not");
		
		//Operadores de entrada / salida
		palReservadas.add("in");
		palReservadas.add("out");
		
		//Operadores de casting
		palReservadas.add("char");
		palReservadas.add("nat");
		palReservadas.add("int");

	}
	
	public static void main(String[] args) {

	}

}
