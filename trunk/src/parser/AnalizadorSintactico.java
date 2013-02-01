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

public int procesaSecVariables(Vector<Token> VVariables, int linea){
		
		int i = linea;
		boolean finVariables = false;
		
		while (!errorCompilacion && !finVariables){
			
			if(VVariables.get(i).getTipoToken() != tToken.varsConsts){
				   error(VVariables.get(i).getLinea(),"No se reconoce la palabra reservada vars-consts!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
			
			if(VVariables.get(i).getTipoToken() != tToken.LA){
				   error(VVariables.get(i).getLinea(),"Falta la llave de apertura!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
			
			int nuevapos = procesaInstVariables(VVariables, i);
			
			if (nuevapos != -1){
				finVariables = true;
				  //hemos procesado la seccion de las variables debuty
				   nuevapos++;
				return nuevapos;
			}
			
		}
		
		return -1;
	}
	
	public int procesaSecInstrucciones(Vector<Token> VInstrucciones, int linea){
		
		return -1;
	}





public void error(int i, String comentario) {
		//if (comentario == null)
			descripError = "Error en linea " + i+ " de tipo: "  + comentario +   "\n";
			errorCompilacion = true;
	
		
	}
	
	
	public void printParser(){
		
		
		System.out.println("***********************************************************************");
		System.out.println("*                           LENGUAJE PILA                             *");
		System.out.println("***********************************************************************");
		System.out.println();
		System.out.println();
		System.out.println("Resultado");
		System.out.println("---------");
		System.out.println();
		if (!errorCompilacion)
			System.out.println("La compilacion ha sido satisfactoria." + "\n" +
					"Fueron leidas " + scanner.getContPrograma() + " lineas.");
		else{
			if (descripError == null) System.out.println("Ocurrierron fallos durante el analisis");
			else
			System.out.println("Ocurrierron fallos durante el analisis:" + "\n" +
					descripError);
		}
		System.out.println();
		if (!errorCompilacion){
			System.out.println("-------------------------------");
			System.out.println("Detalle del analisis sintactico");
			System.out.println("-------------------------------");
			System.out.println();
			
			printTablaTS();
			printTablaMemoria();
			
			System.out.println();
			System.out.println("*Detalle del lenguaje pila");
			System.out.println("--------------------------");
			System.out.println();
			
		
			for (int i = 0; i < byteOut.size(); i++){
			
				if( byteOut.get(i).getDireccion() == -1){
					System.out.println( i + ": " + byteOut.get(i).getByteCode().toString());
				}
				else System.out.println( i + ": " + byteOut.get(i).getByteCode().toString() + 
							" " + byteOut.get(i).getDireccion());
			}	
		
		}
		System.out.println();
		System.out.println();
		System.out.println();
	
	}


	
	public void printTablaTS() {
		System.out.println("*Detalle de la tabla de simbolos");
		System.out.println("--------------------------------");
		System.out.println();
		
		Iterator it = TS.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.print("El identificador '" + e.getKey() + "' ");
			TablaInfo info = (TablaInfo) e.getValue();
			System.out.println(info.print());
			
		}
		
		System.out.println();
	}
	
	public void printTablaMemoria() {
		System.out.println("*Detalle de la tabla de memoria");
		System.out.println("-------------------------------");
		System.out.println();
		
		Iterator it = dirMemoria.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("Posicion " + e.getKey() + " : " + e.getValue());
		}
		
		System.out.println();
	}

	
	
	public static void main(String[] args) {
		
		AnalizadorSintactico sintetiza = new AnalizadorSintactico();
		sintetiza.compilar();
		sintetiza.printParser();
		
	}
}


