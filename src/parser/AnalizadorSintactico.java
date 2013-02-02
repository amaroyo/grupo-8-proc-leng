package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import definicionLex.tToken;

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
	private ALexico scanner;
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

//////////////////////////////////////////////////////////////////////////////////			 
/////Programa → Program: ident { var- consts { Decs } instructions { Inst } }/////
//////////////////////////////////////////////////////////////////////////////////
			 
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
		   
 		   if(VCabecera.get(i).getTipoToken() != TToken.program){
			   error(VCabecera.get(i).getLinea(),"No se reconoce la palabra reservada program!83");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   byteOut.add(new ByteCode(tByteCode.begin));
			   i++;
		   }
		   
		   if(VCabecera.get(i).getTipoToken() != TToken.dosPuntos){
			   error(VCabecera.get(i).getLinea(),"Faltan los dos puntos!93");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   i++;
		   }
		   
		   if(VCabecera.get(i).getTipoToken() != TToken.ident){
			   error(VCabecera.get(i).getLinea(),"Nombre de programa no valido!102");
			   errorCompilacion = true;
			   break;
		   }
		   else {
			   //meter el identificador en la posicion 0
			   i++;
		   }
		
		   if(VCabecera.get(i).getTipoToken() != TToken.LA){
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
			
			if(VVariables.get(i).getTipoToken() != TToken.varsConsts){
				   error(VVariables.get(i).getLinea(),"No se reconoce la palabra reservada vars-consts!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
			
			if(VVariables.get(i).getTipoToken() != TToken.LA){
				   error(VVariables.get(i).getLinea(),"Falta la llave de apertura!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }

////////////////////////////////////////////////////////////////////
////////////////Decs → Decs Dec | Dec///////////////////////////////
////////////////Dec → var tipo ident;///////////////////////////////
////////////////Dec→ const tipo ident := ValorDec;////////////////// 
////////////////////////////////////////////////////////////////////
			
			
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
		int i = linea;
		boolean finVariables = false;
		
		while (!errorCompilacion && !finVariables){
			
			if(VInstrucciones.get(i).getTipoToken() != TToken.instrucciones){
				   error(VInstrucciones.get(i).getLinea(),"No se reconoce la palabra reservada instructions!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
			
			if(VInstrucciones.get(i).getTipoToken() != TToken.LA){
				   error(VInstrucciones.get(i).getLinea(),"Falta la llave de apertura del comienzo de las Inst!");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
///////////////////////////////////////////////////////			
////////////Inst→ Inst Ins | Ins///////////////////////
////////////Ins → InsAsig;//////////// ////////////////
////////////Ins → InsR; ///////////////////////////////
////////////Ins → InsW; ///////////////////////////////
////////////Ins → SWAP1();/////////////////////////////
////////////Ins → SWAP2();/////////////////////////////
///////////////////////////////////////////////////////		
			
			int nuevapos = procesaInstInstrucciones(VInstrucciones, i);
			
			if (nuevapos != -1){
				finVariables = true;
				  //hemos procesado la seccion de las instrucciones debuty
				   nuevapos++;
				return nuevapos;
			}
			
		}
	
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

public boolean procesaTipoVariable(Vector<Token> v, int i){
		
		if (v.get(i).getTipoToken() == TToken.tipoVarBooleano
				|| v.get(i).getTipoToken() == TToken.tipoVarCaracter
				|| v.get(i).getTipoToken() == TToken.tipoVarNatural
				|| v.get(i).getTipoToken() == TToken.tipoVarEntero
				|| v.get(i).getTipoToken() == TToken.tipoVarReal) return true;
		else return false;
	}

	
	

	public boolean numNegativo(Vector<Token> v, int i){
		if (v.get(i).getTipoToken() == TToken.negArit) return true;
		else return false;
	}
	
	public boolean procesaFinInstruccion(Vector<Token> v, int i){
		if(v.get(i).getTipoToken() == TToken.puntoyComa) return true;
		else return false;
	}

public void actualizaPunteroMemoriaDatos(){
		if(posMemoLibre < 10) {
			posMemoLibre = posMemoLibre + 1;
		}
		else {
			System.out.println("YA NO HAY ESPACIO LIBRE EN LA MEMORIA!!!");
			errorCompilacion = true;
		}
	}
	
	public boolean procesaIdentificador(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.ident) return true;
		else return false;
	}
	
	
	
	public boolean procesaAsignacion(Vector<Token> v, int i){
		if (v.get(i).getTipoToken() == TToken.asigConst) return true;
		else return false;
	}

	
	
		
	public boolean correspondenciaDeTipos(Vector<Token> v, String t, int i){
		
		if (t == "tipoVarBooleano"){
				if (v.get(i).getTipoToken().toString() == "booleanoFalso"
						|| v.get(i).getTipoToken().toString() == "booleanoCierto") return true;
		}
		
		else if (t == "tipoVarCaracter"){
			if (v.get(i).getTipoToken().toString() == "caracter") return true;
		}
		
		else if (t == "tipoVarNatural"){
			//por si acaso metemos un numero negativo al natural!!
			if (v.get(i-1).getTipoToken().toString() != "negArit" &&
					v.get(i).getTipoToken().toString() == "natural") return true;
		}
		
		else if (t == "tipoVarEntero"){
			if (v.get(i).getTipoToken().toString() == "entero"
					||v.get(i).getTipoToken().toString() == "natural") return true;
		}
		
		else if (t == "tipoVarReal"){
			if (v.get(i).getTipoToken().toString() == "entero"
					|| v.get(i).getTipoToken().toString() == "natural"
					|| v.get(i).getTipoToken().toString() == "real") return true;
		}
		
	 return false;
	}

	
	
	public static void main(String[] args) {
		
		AnalizadorSintactico sintetiza = new AnalizadorSintactico();
		sintetiza.compilar();
		sintetiza.printParser();
		
	}
}


