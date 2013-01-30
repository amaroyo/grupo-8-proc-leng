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



public int procesaCabecera(Vector<Token> VCabecera, int linea){
		
		int i = linea;
		boolean finCabecera = false;
		
	   while (!errorCompilacion && !finCabecera){	
		   
 		   if(VCabecera.get(i).getTipoToken() != tToken.program){
			   error(VCabecera.get(i).getLinea(),"No se reconoce la palabra reservada program!83");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   byteOut.add(new ByteCode(tByteCode.begin));
			   i++;
		   }
		   
		   if(VCabecera.get(i).getTipoToken() != tToken.dosPuntos){
			   error(VCabecera.get(i).getLinea(),"Faltan los dos puntos!93");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   i++;
		   }
		   
		   if(VCabecera.get(i).getTipoToken() != tToken.ident){
			   error(VCabecera.get(i).getLinea(),"Nombre de programa no valido!102");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   //meter el identificador en la posicion 0
			   i++;
		   }
		
		   if(VCabecera.get(i).getTipoToken() != tToken.LA){
			   error(VCabecera.get(i).getLinea(),"Falta la llave de apertura!112");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   i++;
			   finCabecera = true;
			   return i;
		   }
		   
		
	   }//while	
	   return -1;
	}





	
	
	public static void main(String[] args) {
		
		AnalizadorSintactico sintetiza = new AnalizadorSintactico();
		sintetiza.compilar();
		sintetiza.printParser();
		
	}
}


