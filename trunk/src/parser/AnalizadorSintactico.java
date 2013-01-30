package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import aLexico.ALexico;
import aLexico.Token;
import aLexico.TToken;

public class AnalizadorSintactico {

	private HashMap<Integer,String> dirMemoria;
	private HashMap<String,TablaInfo> TS;
	private Vector<ByteCode> byteOut;
	private Vector<Token> entrada;
	private String descripError;
	private boolean errorCompilacion;
	private AnalizadorLex scanner;
	private int posMemoLibre;
	

	
	
	public AnalizadorSintactico(){
		

		dirMemoria = new HashMap(100);
		TS = new HashMap(100);
		errorCompilacion = false;
		byteOut = new Vector<ByteCode>();
		posMemoLibre = 0;
		
		String nombreFichero = "src/aLexico/ejemplos/ejemplo.txt";
	    scanner = new ALexico();
		scanner.scanFichero(nombreFichero);
		entrada = scanner.dameTokens();
		
		if(scanner.getErrorLex()) errorCompilacion = true;
		
		
		
	}
	
	
	
	public void compilar(){
		
		int i = 0;
		int linea = 0;
		
		
		 if(!entrada.isEmpty()){
			
			linea = procesaCabecera(entrada, i);
			
			if(!errorCompilacion && linea != -1) {
				linea = procesaSecVariables(entrada,linea);
				
				if(!errorCompilacion && linea != -1) {
					procesaSecInstrucciones(entrada, linea);
				}
				
			}
			
			
			
			
			
		}
		else System.out.println("No hay nada que compilar!!!");
	 
		
	}

	
	
	public static void main(String[] args) {
		
		AnalizadorSintactico sintetiza = new AnalizadorSintactico();
		sintetiza.compilar();
		sintetiza.printParser();
		
	}
}


