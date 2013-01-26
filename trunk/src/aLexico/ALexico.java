package aLexico;

import java.util.Vector;

enum est {e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, 
	e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, 
	e33, e34, e35, e36, e37, e38};

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
	
	public boolean esDigito(char car) {
		if (car >= '0' && car <= '9')
			return true;
		else
			return false;
	}
	
	public boolean esDigitoNo0(char car) {
		if (car >= '1' && car <= '9')
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
