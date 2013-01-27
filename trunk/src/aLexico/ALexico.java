package aLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;


enum est {e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, 
	e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, 
	e33, e34, e35, e36, e37, e38};

public class ALexico {
	
	private Vector<String> palReservadas;
	private Vector<Token> tokensSalida;	
	private char buff[];
	private String lex;
	private est estado;
	private BufferedReader bfr;
	private int contPrograma;
	private boolean errorLex;
	private String descripError;
	private boolean quedanCar;
	private char carAntConsumido[];
	private boolean finFichero;
	
	
	public ALexico() {
		buff = new char[1];
		lex = new String();
		tokensSalida = new Vector<Token>();
		contPrograma = 1;
		errorLex = false;
		descripError = new String();
		quedanCar = true;
		palReservadas = new Vector<String>();
		iniciaVecPalReservadas();
		carAntConsumido = new char[1];
		finFichero = false;
		estado=est.e0;
		
	}
	

	public void iniciaScanner(){
		//Se usa para volver al estado 0 sin consumir ningún caracter.
		lex = "";
		estado = est.e0;
	}
	
	
	public void cambiaEstado(est estSig) {
		try {
			//No añadimos las comillas simples en los tipo character
			if (buff[0] != '\'')
				lex = lex + buff[0];
			if (buff[0] == '\n')
				contPrograma++;
			if (bfr.read(buff) == -1) {
				finFichero = true;
				buff[0] = ' ';
			}
			estado = estSig;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
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
	
	public void inicio(String nomFichero) {
		try {
			bfr=new BufferedReader(new FileReader(nomFichero));
			bfr.read(buff);
			carAntConsumido[0] = ' ';
			contPrograma = 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void resetearFichero() {
		try {
			bfr.reset();
			bfr.read(buff);
			contPrograma = 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
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
	
	public boolean esBlanFLinTab(char car) {
		if (buff[0] == ' ' || buff[0] == '\r' || buff[0] == '\n' || buff[0] == '\t')
			return true;
		else
			return false;
	}
	
	public boolean esFLin(char car) {
		if (buff[0] == '\r' || buff[0] == '\n')
			return true;
		else
			return false;
	}
	
	public boolean esE(char car) {
		if (buff[0] == 'e' || buff[0] == 'E')
			return true;
		else
			return false;
	}
	
	public boolean esOpCast(String lexema) {
		if (lexema.equals("char") || lexema.equals("int") || lexema.equals("nat") || lexema.equals("float"))
			return true;
		else
			return false;
	}
	
	public void error(String comentario) {
		if (comentario == null)
			descripError = "Caracter inesperado en la linea " + contPrograma + " : '" + buff[0] + "'\n";
		else
			descripError = "Caracter en buffer: '" + buff[0] + "'. Linea: " + contPrograma + '\n' +
					"Error: " + comentario;
		errorLex = true;
	}
	
	public void errorCarAnt(String comentario) {
		if (comentario == null)
			descripError = "Caracter inesperado en la linea " + contPrograma + " : '" + carAntConsumido[0] + "'\n";
		else
			descripError = "Caracter en buffer: '" + carAntConsumido[0] + "'. Linea: " + contPrograma + '\n' +
					"Error: " + comentario;
		errorLex = true;
	}
	
	public Token dameToken(char car) {
		switch (car) {
		case ';':
			return new Token(TToken.puntoyComa, contPrograma);
		case '+':
			return new Token(TToken.sum, contPrograma);
		case '-':
			//Distinguimos el caso del - unario
			if (!tokensSalida.isEmpty()) 
				if (tokensSalida.lastElement().getTipoToken() == TToken.natural || 
						tokensSalida.lastElement().getTipoToken() == TToken.entero ||
						tokensSalida.lastElement().getTipoToken() == TToken.real ||
						tokensSalida.lastElement().getTipoToken() == TToken.PC ||
						tokensSalida.lastElement().getTipoToken() == TToken.ident)
					return new Token(TToken.rest, contPrograma);
				else
					return new Token(TToken.negArit, contPrograma);
			else
				errorCarAnt(null);
			break;
		case '*':
			return new Token(TToken.mult, contPrograma);
		case '/':
			return new Token(TToken.div, contPrograma);
		case '%':
			return new Token(TToken.mod, contPrograma);
		case '(':
			return new Token(TToken.PA, contPrograma);
		case ')':
			return new Token(TToken.PC, contPrograma);
		case '{':
			return new Token(TToken.LA, contPrograma);
		case '}':
			return new Token(TToken.LC, contPrograma);
		case '.':
			return new Token(TToken.punto, contPrograma);
		}
		return new Token();
	}
	
	public Token dameTokenPalReservada(String palReservada) {
		if (palReservada.equals("program")) {
			return new Token(TToken.program, contPrograma);
		}
		//Ninguna de las palabras reservadas debe llevar lexemas
		if (palReservada.equals("boolean")) {
			if (tokensSalida.lastElement().getTipoToken() == TToken.constante|| 
				tokensSalida.lastElement().getTipoToken() == TToken.var)
				return new Token(TToken.tipoVarBooleano, contPrograma);
			else {
				error("Declaración incorrecta de tipo 'boolean'.");
				return new Token();
			}
		}
		if (palReservada.equals("character")) {
			if (tokensSalida.lastElement().getTipoToken() == TToken.constante|| 
				tokensSalida.lastElement().getTipoToken() == TToken.var)
				return new Token(TToken.tipoVarCaracter, contPrograma);
			else {
				error("Declaración incorrecta de tipo 'character'.");
				return new Token();
			}
		}
		if (palReservada.equals("natural")) {
			if (tokensSalida.lastElement().getTipoToken() == TToken.constante|| 
				tokensSalida.lastElement().getTipoToken() == TToken.var)
				return new Token(TToken.tipoVarNatural, contPrograma);
			else {
				error("Declaración incorrecta de tipo 'natural'.");
				return new Token();
			}
		}
		if (palReservada.equals("integer")) {
			if (tokensSalida.lastElement().getTipoToken() == TToken.constante|| 
				tokensSalida.lastElement().getTipoToken() == TToken.var)
				return new Token(TToken.tipoVarEntero, contPrograma);
			else {
				error("Declaración incorrecta de tipo 'integer'.");
				return new Token();
			}
		}
		if (palReservada.equals("true")) {
			return new Token(TToken.booleanoCierto,"true",contPrograma);
		}
		if (palReservada.equals("false")) {
			return new Token(TToken.booleanoFalso, "false ",contPrograma);
		}
		if (palReservada.equals("or")) {
			return new Token(TToken.oLogica, contPrograma);
		}
		if (palReservada.equals("and")) {
			return new Token(TToken.yLogica, contPrograma);
		}
		if (palReservada.equals("not")) {
			return new Token(TToken.negLogica, contPrograma);
		}
		if (palReservada.equals("in")) {
			return new Token(TToken.entradaTeclado, contPrograma);
		}
		if (palReservada.equals("out")) {
			return new Token(TToken.salidaPantalla, contPrograma);
		}
		if (palReservada.equals("char")) {
			return new Token(TToken.castChar, contPrograma);
		}
		if (palReservada.equals("nat")) {
			return new Token(TToken.castNat, contPrograma);
		}
		if (palReservada.equals("int")) {
			return new Token(TToken.castInt, contPrograma);
		}
		if (palReservada.equals("float")) {
			return new Token(TToken.castFloat, contPrograma);
		}

		if (palReservada.equals("vars-consts")) {
			return new Token(TToken.varsConsts, contPrograma);
		}
		
		if (palReservada.equals("var")) {
			return new Token(TToken.var, contPrograma);
		}
		
		if (palReservada.equals("const")) {
			return new Token(TToken.constante, contPrograma);
		}
		
		if (palReservada.equals("instructions")) {
			return new Token(TToken.instrucciones, contPrograma);
		}
		
		if (palReservada.equals("swap1")) {
			return new Token(TToken.swap1, contPrograma);
		}
		
		if (palReservada.equals("swap2")) {
			return new Token(TToken.swap2, contPrograma);
		}

		if (palReservada.equals("tipo")) {
			return new Token(TToken.decTipo, contPrograma);
		}

		return new Token();
	}
	
	public Token dameTokenIdentificador(String lexema) {
		return new Token(TToken.ident, lexema, contPrograma);
	}
	
	public Token dameTokenComentario(String comentario) {
		return new Token(TToken.comentario,comentario, contPrograma);
	}
	
	public Token dameTokenCaracter(String car) {
		return new Token(TToken.caracter,car, contPrograma);
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
