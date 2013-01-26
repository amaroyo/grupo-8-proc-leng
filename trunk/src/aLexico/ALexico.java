package aLexico;

import java.util.Vector;

public class ALexico {
	
	private Vector<String> palReservadas;
	private Vector<Token> tokensSalida;
	
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
	
	public boolean esLetraMinus(char car) {
		if ((car >= 'a' && car <= 'z'))
			return true;
		else
			return false;
	}
	
	public boolean esLetra(char car) {
		if ((car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z'))
			return true;
		else
			return false;
	}

	public Vector<Token> getTokensSalida() {
		return tokensSalida;
	}

	public void setTokensSalida(Vector<Token> tokensSalida) {
		this.tokensSalida = tokensSalida;
	}
	
	
	public static void main(String[] args) {

	}


}
