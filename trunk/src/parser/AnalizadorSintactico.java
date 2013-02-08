package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;


import aLexico.ALexico;
import aLexico.Token;
import aLexico.TToken;
import parser.ArbolBin;
import parser.Nodo;

public class AnalizadorSintactico {

	private HashMap<Integer,String> dirMemoria;
	private HashMap<String,TablaInfo> TS;
	private Vector<ByteCode> byteOut;
	private Vector<Token> entrada;
	private String descripError;
	private boolean errorCompilacion;
	private ALexico scanner;
	private int posMemoLibre;

// Arbol declarado como Local para poder imprimir
	private ArbolBin arbol;

	
	
	public AnalizadorSintactico(){
		
		arbol=new ArbolBin();
		dirMemoria = new HashMap(100);
		TS = new HashMap(100);
		errorCompilacion = false;
		byteOut = new Vector<ByteCode>();
		posMemoLibre = 0;
		
		String nombreFichero = "src/aLexico/ejemplos/ejemplo2.txt";
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
             /////Programa -> Program: ident { var- consts { Decs } instructions { Inst } }/////
             //////////////////////////////////////////////////////////////////////////////////
			
			linea = procesaCabecera(entrada, i);
			
			if(!errorCompilacion && linea != -1) {
				linea = procesaSecVariables(entrada,linea);
				
				if(!errorCompilacion && linea != -1) {
					procesaSecInstrucciones(entrada, linea);
					 byteOut.add(new ByteCode(tByteCode.stop));
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
				   error(VVariables.get(i).getLinea(),"No se reconoce la palabra reservada vars-consts!135");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
			
			if(VVariables.get(i).getTipoToken() != TToken.LA){
				   error(VVariables.get(i).getLinea(),"Falta la llave de apertura!144");
				   errorCompilacion = true;
				   break;
			 }
		     else {
				   i++;
			 }
            
            ////////////////////////////////////////////////////////////////////
            ////////////////Decs -> Decs Dec | Dec///////////////////////////////
            ////////////////Dec -> var tipo ident;///////////////////////////////
            ////////////////Dec -> const tipo ident := ValorDec;//////////////////
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
            ////////////Inst -> Inst Ins | Ins///////////////////////
            ////////////Ins -> InsAsig;//////////// ////////////////
            ////////////Ins -> InsR; ///////////////////////////////
            ////////////Ins -> InsW; ///////////////////////////////
            ////////////Ins -> SWAP1();/////////////////////////////
            ////////////Ins -> SWAP2();/////////////////////////////
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
    

	
	
	public int procesaInstVariables(Vector<Token> v, int linea){
		
		int i = linea;
		boolean insFinalizada = false;
		
		while(v.get(i).getTipoToken() != TToken.LC){
			insFinalizada = false;
			
			
			if(v.get(i).getTipoToken() == TToken.var || v.get(i).getTipoToken() == TToken.constante){
				
				boolean variable = (v.get(i).getTipoToken() == TToken.var);
				i++;
				if(procesaTipoVariable(v,i)){
					String tipo = v.get(i).getTipoToken().toString();
					i++;
					if(procesaIdentificador(v,i)) {
						String identificador = v.get(i).getLexema();
						i++;
						if(variable && procesaFinInstruccion(v,i)){
							//ahora tenemos q mirar si esta duplicada, y si no meterla en la tabla!!
							//tenemos el identificador, el tipo y si es cte o no!! DELUXE
							//esta en la tabla TS???
							
							if(TS.containsKey(identificador)){
								error(v.get(i).getLinea(),"VARIABLE DUPLICADA197");
								errorCompilacion = true;
								break;
							}
							else {
								//variable no duplicada, la metemos en la TS y la inicializamos en la MEMO
								//Y SUMAMOS UNO A LA i!!!!
								TablaInfo novata = new TablaInfo(tipo,false,posMemoLibre);
								TS.put(identificador, novata);
								dirMemoria.put(posMemoLibre, "null");
								actualizaPunteroMemoriaDatos();
								insFinalizada=true;
								i++;
								
							}
							
						}
						else if (variable){
							error(v.get(i).getLinea(),"Falta punto y coma!!215");
							errorCompilacion = true;
							break;
						}
						
						if(!insFinalizada){
							if(!variable && procesaAsignacion(v,i)){
								i++;
								boolean numeroNegativo = false;
								if(numNegativo(v,i)) {numeroNegativo = true;i++;}
								//lo de arriba sirve para ver si es un num negativo
								if(correspondenciaDeTipos(v,tipo, i)){
									String valor = "";
									if (numeroNegativo)  valor = "-" + v.get(i).getLexema();
									else  valor = v.get(i).getLexema();
									
									if(procesaFinInstruccion(v,i+1)){
										if(TS.containsKey(identificador)){
											error(v.get(i).getLinea(),"VARIABLE DUPLICADA233");
											errorCompilacion = true;
											break;
										}
										else {
											//variable no duplicada, la metemos en la TS y la inicializamos en la MEMO
											//Y SUMAMOS UNO A LA i!!!!
											TablaInfo novata = new TablaInfo(tipo,true,posMemoLibre);
											TS.put(identificador, novata);
											dirMemoria.put(posMemoLibre, valor);
											actualizaPunteroMemoriaDatos();
											insFinalizada=true;
											i = i+2;// ya q ya sabemos q hay punto y coma!!! :D
										}
									}
									else {
										error(v.get(i).getLinea(),"Falta punto y coma!!249");
										errorCompilacion = true;
										break;
									}
									
								}
								else{
									error(v.get(i).getLinea(),"Error de tipos!!!256");
									errorCompilacion = true;
									break;
									
								}
								
							}
							else{
								error(v.get(i).getLinea(),"Error en la asignacion de la cte264");
								errorCompilacion = true;
								break;
							}
						
						}
					}
					else{
						error(v.get(i).getLinea(),"Identificador erroneo272");
						errorCompilacion = true;
						break;
					}
				}
				else {
					error(v.get(i).getLinea(),"Tipo no reconocido278");
					errorCompilacion = true;
					break;
				}
				
			}//if
			
			
			else {
				error(v.get(i).getLinea(),"Error en linea287");
				errorCompilacion = true;
				break;
			}
				
			
		}//while
		
		if(!errorCompilacion) {
		if(v.get(i).getTipoToken()==TToken.LC){
		}
		else {
		error(v.get(i).getLinea(),"Falta la } de Declaraciones");
		errorCompilacion = true;
		}	
		}
		if(!errorCompilacion) return i;
		return -1;
	}
	
	
	public int procesaInstInstrucciones(Vector<Token> v, int linea){
		String identificador;
		int i = linea;
		while(v.get(i).getTipoToken() != TToken.LC){
			
///////////////Instrucción de Tipo IN//////////////////////
///////////////InsR->in(ident); ///////////////////////////////
///////////////////////////////////////////////////////////
			if(v.get(i).getTipoToken()==TToken.entradaTeclado){
				i++;

	//leo (			
			if(v.get(i).getTipoToken()==TToken.PA){
			i++;}
			else {
			error(v.get(i).getLinea(),"Falta el (");
			errorCompilacion = true;
			break;}	
	//leo iden y compruebo que esta en TS y no es constante			
			if(v.get(i).getTipoToken()==TToken.ident){		
			  identificador = v.get(i).getLexema();
			 	    // si es Identificador de TS
			 		if(TS.containsKey(identificador)){
			 			 // si no es Constante
			 			if(!TS.get(identificador).isConstante()){
			 				i++;			
			 			}
			 			else {
					 		error(v.get(i).getLinea(),"IN sobre identificador que es una Constante");
					 		errorCompilacion = true;
					 		break;}		
			 				 			
			 		}
			 		else {
			 		error(v.get(i).getLinea(),"Identificador que no está en TS");
			 		errorCompilacion = true;
			 		break;}			
			
			}
			else {
			error(v.get(i).getLinea(),"No es identificador válido");
			errorCompilacion = true;
			break;}	
	//leo )			
			if(v.get(i).getTipoToken()==TToken.PC){
			i++;}
			else {
			error(v.get(i).getLinea(),"Falta el )");
			errorCompilacion = true;
			break;}				
	//leo ;			
			if(v.get(i).getTipoToken()==TToken.puntoyComa){
			byteOut.add(new ByteCode(tByteCode.read));
			String aux=String.valueOf(TS.get(identificador).getDireccion());
			byteOut.add(new ByteCode(tByteCode.desapila_dir,aux));
			i++;}
			else {
			error(v.get(i).getLinea(),"Falta el ;");
			errorCompilacion = true;
			break;}	
			
			
			    } 

			
///////////////Instrucción de Tipo OUT//////////////////////
///////////////InsW->out(exp); /////////////////////////////
////////////////////////////////////////////////////////////
			else 
				if(v.get(i).getTipoToken()==TToken.salidaPantalla){
					i++;			

					//leo (		
					if(v.get(i).getTipoToken()==TToken.PA){
						i++;}
					else {
						error(v.get(i).getLinea(),"Faltan el(");
						errorCompilacion = true;
						break;}

					//leo Expresion	
					i=procesaExpresionOut(v,i);
					if(i!=-1){//////Procesa Exp.///////
						byteOut.add(new ByteCode(tByteCode.write));
						}
					else {
						errorCompilacion = true;
						break;}	
		
					//leo ;	
					if(v.get(i).getTipoToken()==TToken.puntoyComa){
						i++;}
					else {
						error(v.get(i).getLinea(),"Falta el ;");
						errorCompilacion = true;
						break;}	

					}
///////////////Instrucción de Tipo Asig//////////////////////	
///////////////InstAsig->iden=Exp;///////////////////////////
/////////////////////////////////////////////////////////////
				else 
					if(v.get(i).getTipoToken()==TToken.ident){		
						identificador = v.get(i).getLexema();
// si es Identificador de TS
						if(TS.containsKey(identificador)){
// si no es Constante
							if(!TS.get(identificador).isConstante()){
								i++;			
							}
							else {
								error(v.get(i).getLinea(),"ASIG sobre identificador que es una Constante");
								errorCompilacion = true;
								break;}		

						}
						else {
							error(v.get(i).getLinea(),"Identificador que no está en TS");
							errorCompilacion = true;
							break;}			

//leo =			
						if(v.get(i).getTipoToken()==TToken.equals){
							i++;}
						else {
							error(v.get(i).getLinea(),"Falta el = ");
							errorCompilacion = true;
							break;}	
//leo Exp			
						i=procesaExpresion(v,i);
						if(i!=-1){//////Procesa Exp.///////
							String aux2=String.valueOf(TS.get(identificador).getDireccion());
							arbol.posorden(arbol.raiz,byteOut);
							byteOut.add(new ByteCode(tByteCode.desapila_dir,aux2));
							i++;
							}
						else {
							errorCompilacion = true;
							break;}	
					}

			
///////////////Instrucción de Tipo SWAP1///////////////////////
///////////////Ins -> SWAP1();/////////////////////////////////
///////////////////////////////////////////////////////////////
			else 
				if(v.get(i).getTipoToken()==TToken.swap1){
					i++;			

		//leo (			
				if(v.get(i).getTipoToken()==TToken.PA){
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el (");
				errorCompilacion = true;
				break;}
		//leo )			
				if(v.get(i).getTipoToken()==TToken.PC){
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el )");
				errorCompilacion = true;
				break;}	
		//leo ;			
				if(v.get(i).getTipoToken()==TToken.puntoyComa){
				byteOut.add(new ByteCode(tByteCode.swap1));
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el ;");
				errorCompilacion = true;
				break;}	
				
				    }
///////////////Instrucción de Tipo SWAP2////////////////////
///////////////Ins -> SWAP2();//////////////////////////////
////////////////////////////////////////////////////////////
				else 
				if(v.get(i).getTipoToken()==TToken.swap2){
				i++;			
		
		//leo (			
				if(v.get(i).getTipoToken()==TToken.PA){
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el (");
				errorCompilacion = true;
				break;}
		//leo )			
				if(v.get(i).getTipoToken()==TToken.PC){
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el )");
				errorCompilacion = true;
				break;}	
		//leo ;			
				if(v.get(i).getTipoToken()==TToken.puntoyComa){
				byteOut.add(new ByteCode(tByteCode.swap2));
				i++;}
				else {
				error(v.get(i).getLinea(),"Falta el ;");
				errorCompilacion = true;
				break;}	
				}	
	
			
///////////////Instrucciones Inválidas//////////////////////	
////////////////////////////////////////////////////////////			
			
			else {
			error(v.get(i).getLinea(),"No hay instrucciones válidas");
			errorCompilacion = true;
			break;}	
			
			
		}//while
		
		
		if(!errorCompilacion) {
		if(v.get(i).getTipoToken()==TToken.LC){
			i++;
		}
		else {
		error(v.get(i).getLinea(),"Falta la } de Instrucciones");
		errorCompilacion = true;
		}	
		if(v.get(i).getTipoToken()==TToken.LC){
		}
		else {
		error(v.get(i).getLinea(),"Falta la } Program");
		errorCompilacion = true;
		}	
		}
		if(!errorCompilacion) return i;
		return -1;
	}


////////////////////////////////////////////////////////
///////////////Procesa Expresiones//////////////////////
////////////////////////////////////////////////////////

private int procesaExpresion(Vector<Token> v, int i) {

TToken operacion=null;
Nodo raiz=new Nodo();
int indice=0;
int indice2=0;
int referencia;
//Nos hacemos un array auxiliar para meter la expresion  General
Vector<Token> expresion = new Vector<Token>();
while(v.get(i).getTipoToken()!=TToken.puntoyComa){
	expresion.add(v.get(i));
	i++;
	}

//Si encontramos un ( ) nos situamos después de él.
if(expresion.get(indice).getTipoToken()==TToken.PA){
	indice=procesaExpParentesis(expresion,indice);
	}

//Si encontramos un op0 meter raiz arbol binario
if(procesaOperacionCero(expresion.get(indice).getTipoToken())){
//Seleccionamos el Op0 y almacenamos su indice en Refenrecia para luego dividir en dos subvectores	
		referencia=indice;
		operacion=expresion.get(indice).getTipoToken();
		if(operacion!=null){
		switch(operacion) { // Elige la opcion acorde al numero de mes	
		case igualIgual:raiz.info=new ByteCode(tByteCode.igual); break;
		case great:raiz.info=new ByteCode(tByteCode.mayor);break;
		case less:raiz.info=new ByteCode(tByteCode.menor);break;
		case greatEq:raiz.info=new ByteCode(tByteCode.mayorigual);break;
		case lessEq:raiz.info=new ByteCode(tByteCode.menorigual);break;
		case distinto:raiz.info=new ByteCode(tByteCode.distinto);break;		
		}
		}
		
//Nos hacemos dos subarrays de las expresiones de los lados del operador
		Vector<Token> expresionIzq = new Vector<Token>();
		Vector<Token> expresionDer = new Vector<Token>();

while (indice2!=referencia){
		expresionIzq.add(expresion.get(indice2));
		indice2++;
		}
		
while(indice2 != expresion.size()){
		expresionDer.add(expresion.get(indice2));
		indice2++;
		}

raiz.izq=procesaExpresion0(expresionIzq);	
raiz.der=procesaExpresion0(expresionDer);	
	   }
// si no e encontrado ningún Op0
else 
   {
		raiz=procesaExpresion0(expresion);
	}

arbol.insertarNodo(raiz);
return i;
}


private Nodo procesaExpresion0(Vector<Token> expresion) {


TToken operacion=null;
Nodo raizaux=new Nodo();
int indice=0;

while(indice != expresion.size()){
	//Si encontramos un op0 meter raiz arbol binario
	if(procesaOperacionCero(expresion.get(indice).getTipoToken())){
	//Seleccionamos el Op0		
			operacion=expresion.get(indice).getTipoToken();
			if(operacion!=null){
			switch(operacion) { // Elige la opcion acorde al numero de mes	
			case igualIgual:raizaux.info=new ByteCode(tByteCode.igual); break;
			case great:raizaux.info=new ByteCode(tByteCode.mayor);break;
			case less:raizaux.info=new ByteCode(tByteCode.menor);break;
			case greatEq:raizaux.info=new ByteCode(tByteCode.mayorigual);break;
			case lessEq:raizaux.info=new ByteCode(tByteCode.menorigual);break;
			case distinto:raizaux.info=new ByteCode(tByteCode.distinto);break;		
			}
			}
	indice++;	
	}

//Si encontramos un entero, real , natgural , caracter, true o false = apilo
	if((expresion.get(indice).getTipoToken()==TToken.natural||expresion.get(indice).getTipoToken()==TToken.entero||
		expresion.get(indice).getTipoToken()==TToken.real||expresion.get(indice).getTipoToken()==TToken.caracter||
		expresion.get(indice).getTipoToken()==TToken.booleanoCierto||expresion.get(indice).getTipoToken()==TToken.booleanoFalso))
	{
			if(raizaux.izq==null){
		    raizaux.izq=new Nodo();
		    raizaux.izq.info=new ByteCode(tByteCode.apila,expresion.get(indice).getLexema());
	        } 
			else{raizaux.der=new Nodo();
				raizaux.der.info=new ByteCode(tByteCode.apila,expresion.get(indice).getLexema());}
	indice++;
	}

//Si encontramos un ident apilo_dir	
	if((expresion.get(indice).getTipoToken()==TToken.ident)&&TS.containsKey(expresion.get(indice).getLexema()))
	{
	String aux=String.valueOf(TS.get(expresion.get(indice).getLexema()).getDireccion());
	if(raizaux.izq==null){
		raizaux.izq=new Nodo();
		raizaux.izq.info=new ByteCode(tByteCode.apila_dir,aux);
        } 
		else{raizaux.der=new Nodo();
			raizaux.der.info=new ByteCode(tByteCode.apila_dir,aux);}
	indice++; 
	}
	if((expresion.get(indice).getTipoToken()==TToken.PA)||(expresion.get(indice).getTipoToken()==TToken.PC))
	{	indice++; }

	
}			
return raizaux;

}

///////////////Procesa Paréntesis///////////////////////
////////////////////////////////////////////////////////

private int procesaExpParentesis(Vector<Token> v, int i) {
	while(v.get(i).getTipoToken()!=TToken.PC){
		i++;
	}
	i=i+1;
	return i;
}

///////////////Procesa Operaciones//////////////////////
///////////////Op0,Op1,Op2,Op3//////////////////////////


private boolean procesaOperacionCero(TToken oper0){

if((oper0==TToken.great||oper0==TToken.distinto||oper0==TToken.igualIgual
	||oper0==TToken.less||oper0==TToken.greatEq||oper0==TToken.lessEq)){
return true;
}
else return false;

}

private boolean procesaOperacionUno(TToken oper1){//sin or

if((oper1==TToken.sum||oper1==TToken.rest)){
return true;
}
else return false;

}
private boolean procesaOperacionDos(TToken oper2){

if((oper2==TToken.mult||oper2==TToken.div||oper2==TToken.mod)){
return true;
}
else return false;

}
private boolean procesaOperacionTres(TToken oper3){

if((oper3==TToken.despIzq||oper3==TToken.despDer)){
return true;
}
else return false;

}





/////////////////// ANTIGUO PERO APROVECHABLE
////////////////////////////////////////////////////////
	
	private int procesaExpresionOut(Vector<Token> v, int i) {
		int expresionCorrecta = 0;
		TToken operacion=null;
		TToken operacion2=null;
///////////////Procesa Expresiones//////////////////////
///////////////Exp->Exp0 Op0 Exp0 | Exp0////////////////
while(v.get(i).getTipoToken()!=TToken.PC && expresionCorrecta!=-1){

//If eres Identificador con una Op0 anterior y )) después...
	if((v.get(i).getTipoToken()==TToken.ident)&&TS.containsKey(v.get(i).getLexema())  && (procesaOperacionCero(v.get(i-1).getTipoToken()))&&(v.get(i+1).getTipoToken()==TToken.PC)&&(v.get(i+2).getTipoToken()==TToken.PC))
				{
		String aux=String.valueOf(TS.get(v.get(i).getLexema()).getDireccion());
		byteOut.add(new ByteCode(tByteCode.apila_dir,aux));
		
		if(operacion!=null){
			switch(operacion) { // Elige la opcion acorde al numero de mes
			case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
			case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
			case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
			case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
			case less:byteOut.add(new ByteCode(tByteCode.menor));break;
			case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
			case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
			case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;
			
			case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
			case div:byteOut.add(new ByteCode(tByteCode.divide));break;
			case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
			case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
			}
			}
		if(operacion2!=null){
			switch(operacion2) { // Elige la opcion acorde al numero de mes
			case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
			case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
			case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
			case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
			case less:byteOut.add(new ByteCode(tByteCode.menor));break;
			case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
			case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
			case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;

			case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
			case div:byteOut.add(new ByteCode(tByteCode.divide));break;
			case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
			case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
			}
			}
		i=i+2;
		expresionCorrecta=i;
		
		
		
		}//if del identificador
	else 
		
//If eres nat,real,.... con una Op0 anterior y )) después		
		if((v.get(i).getTipoToken()==TToken.natural||v.get(i).getTipoToken()==TToken.entero||
				v.get(i).getTipoToken()==TToken.real||v.get(i).getTipoToken()==TToken.caracter||
				v.get(i).getTipoToken()==TToken.booleanoCierto||v.get(i).getTipoToken()==TToken.booleanoFalso) && (procesaOperacionCero(v.get(i-1).getTipoToken()))&&(v.get(i+1).getTipoToken()==TToken.PC)&&(v.get(i+2).getTipoToken()==TToken.PC))
		{
		byteOut.add(new ByteCode(tByteCode.apila,v.get(i).getLexema()));

		if(operacion!=null){
		switch(operacion) { // Elige la opcion acorde al numero de mes
		case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
		case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
		case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
		case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
		case less:byteOut.add(new ByteCode(tByteCode.menor));break;
		case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
		case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
		case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;
	
		case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
		case div:byteOut.add(new ByteCode(tByteCode.divide));break;
		case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
		case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
		}
		}
		if(operacion2!=null){
			switch(operacion2) { // Elige la opcion acorde al numero de mes
			case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
			case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
			case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
			case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
			case less:byteOut.add(new ByteCode(tByteCode.menor));break;
			case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
			case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
			case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;

			case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
			case div:byteOut.add(new ByteCode(tByteCode.divide));break;
			case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
			case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
			}
			}
		
		
		
			i=i+2;
			expresionCorrecta=i;

		}
		else	
	
//If eres Identificador con una Op0 anterior...
			if((v.get(i).getTipoToken()==TToken.ident)&&TS.containsKey(v.get(i).getLexema()) && (procesaOperacionCero(v.get(i-1).getTipoToken())))
						{
				String aux=String.valueOf(TS.get(v.get(i).getLexema()).getDireccion());
				byteOut.add(new ByteCode(tByteCode.apila_dir,aux));
				
				if(operacion!=null){
					switch(operacion) { // Elige la opcion acorde al numero de mes
					case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
					case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
					case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
					case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
					case less:byteOut.add(new ByteCode(tByteCode.menor));break;
					case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
					case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
					case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;
					
					case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
					case div:byteOut.add(new ByteCode(tByteCode.divide));break;
					case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
					case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
					}
					}
				
				i++;
				expresionCorrecta=i;
				}//if del identificador
			else 
				
				
//If eres nat,real,.... con una Op0 anterior...			
				if((v.get(i).getTipoToken()==TToken.natural||v.get(i).getTipoToken()==TToken.entero||
						v.get(i).getTipoToken()==TToken.real||v.get(i).getTipoToken()==TToken.caracter||
						v.get(i).getTipoToken()==TToken.booleanoCierto||v.get(i).getTipoToken()==TToken.booleanoFalso) && (procesaOperacionCero(v.get(i-1).getTipoToken())))
				{
				byteOut.add(new ByteCode(tByteCode.apila,v.get(i).getLexema()));
		
				if(operacion!=null){
				switch(operacion) { // Elige la opcion acorde al numero de mes
				case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
				case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
				case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
				case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
				case less:byteOut.add(new ByteCode(tByteCode.menor));break;
				case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
				case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
				case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;
			
				case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
				case div:byteOut.add(new ByteCode(tByteCode.divide));break;
				case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
				case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
				}
				}
		
					i++;
					expresionCorrecta=i;
		
				}
				else 
	
					
//If identficador apilo						
				if((v.get(i).getTipoToken()==TToken.ident)&&TS.containsKey(v.get(i).getLexema())){
					String aux=String.valueOf(TS.get(v.get(i).getLexema()).getDireccion());
				byteOut.add(new ByteCode(tByteCode.apila_dir,aux));
				i++;
				expresionCorrecta=i;
					}//if del identificador
				else 
//If eres nat,real,.... apilo
				if(v.get(i).getTipoToken()==TToken.natural||v.get(i).getTipoToken()==TToken.entero||
					v.get(i).getTipoToken()==TToken.real||v.get(i).getTipoToken()==TToken.caracter||
					v.get(i).getTipoToken()==TToken.booleanoCierto||v.get(i).getTipoToken()==TToken.booleanoFalso){
				byteOut.add(new ByteCode(tByteCode.apila,v.get(i).getLexema()));
				i++;
				expresionCorrecta=i;
				}
				else
				
//operaciones intermedias y+x+(x+2)
					 if((procesaOperacionCero(v.get(i).getTipoToken()))&&
									(v.get(i-1).getTipoToken()==TToken.ident||v.get(i-1).getTipoToken()==TToken.natural||v.get(i-1).getTipoToken()==TToken.entero||
									v.get(i-1).getTipoToken()==TToken.real||v.get(i-1).getTipoToken()==TToken.caracter||
									v.get(i-1).getTipoToken()==TToken.booleanoCierto||v.get(i-1).getTipoToken()==TToken.booleanoFalso)&&
									(v.get(i+1).getTipoToken()==TToken.PA)){
						
						operacion2=v.get(i).getTipoToken();
						i=i+2;
						expresionCorrecta=i;
									
					}	
				else 
					
					
//operaciones x+y
					if((procesaOperacionCero(v.get(i).getTipoToken()))&&
						(v.get(i-1).getTipoToken()==TToken.ident||v.get(i-1).getTipoToken()==TToken.natural||v.get(i-1).getTipoToken()==TToken.entero||
						v.get(i-1).getTipoToken()==TToken.real||v.get(i-1).getTipoToken()==TToken.caracter||
						v.get(i-1).getTipoToken()==TToken.booleanoCierto||v.get(i-1).getTipoToken()==TToken.booleanoFalso)&&
						(v.get(i+1).getTipoToken()==TToken.ident||v.get(i+1).getTipoToken()==TToken.natural||v.get(i+1).getTipoToken()==TToken.entero||
						v.get(i+1).getTipoToken()==TToken.real||v.get(i+1).getTipoToken()==TToken.caracter||
						v.get(i+1).getTipoToken()==TToken.booleanoCierto||v.get(i+1).getTipoToken()==TToken.booleanoFalso))
						{
						operacion=v.get(i).getTipoToken();
						i++;
						expresionCorrecta=i;
						}	
					
		else {
			error(v.get(i).getLinea(),"Fallo en la Expresión");
			expresionCorrecta = -1;
			}
		}

if(expresionCorrecta!=-1){
	//leo )
if(v.get(i).getTipoToken()==TToken.PC){
	i++;
	expresionCorrecta=i;	
		}
else {	
error(v.get(i).getLinea(),"Falta el ) de final de expresión");
expresionCorrecta = -1;}
}
return expresionCorrecta;
}

/////////////////// ANTIGUO PERO APROVECHABLE
////////////////////////////////////////////////////////

	private int procesaExpresionAsig(Vector<Token> v, int i) {
		int expresionCorrecta = 0;
		TToken operacion=null;
		TToken operacion2=null;
///////////////Procesa Expresiones//////////////////////
///////////////Exp->Exp0 Op0 Exp0 | Exp0////////////////
while(v.get(i).getTipoToken()!=TToken.puntoyComa && expresionCorrecta!=-1){
	if((v.get(i).getTipoToken()==TToken.ident)&&TS.containsKey(v.get(i).getLexema())&&(procesaOperacionCero(v.get(i-1).getTipoToken())||procesaOperacionUno(v.get(i-1).getTipoToken())||
			procesaOperacionDos(v.get(i-1).getTipoToken())||procesaOperacionTres(v.get(i-1).getTipoToken())||v.get(i-1).getTipoToken()==TToken.negArit||v.get(i-1).getTipoToken()==TToken.negLogica))
				{
		String aux=String.valueOf(TS.get(v.get(i).getLexema()).getDireccion());
		byteOut.add(new ByteCode(tByteCode.apila_dir,aux));
		
		if(operacion!=null){
			switch(operacion) { // Elige la opcion acorde al numero de mes
			case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
			case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
			case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
			case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
			case less:byteOut.add(new ByteCode(tByteCode.menor));break;
			case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
			case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
			case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;
			
			case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
			case div:byteOut.add(new ByteCode(tByteCode.divide));break;
			case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
			case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
			}
			}
		
		i++;
		expresionCorrecta=i;
		
		
		
		}//if del identificador
	else 
		if((v.get(i).getTipoToken()==TToken.ident)&&TS.containsKey(v.get(i).getLexema())){
			String aux=String.valueOf(TS.get(v.get(i).getLexema()).getDireccion());
			byteOut.add(new ByteCode(tByteCode.apila_dir,aux));
			i++;
			expresionCorrecta=i;
			}//if del identificador
		else 
	if(v.get(i).getTipoToken()==TToken.natural||v.get(i).getTipoToken()==TToken.entero||
			v.get(i).getTipoToken()==TToken.real||v.get(i).getTipoToken()==TToken.caracter||
			v.get(i).getTipoToken()==TToken.booleanoCierto||v.get(i).getTipoToken()==TToken.booleanoFalso){
	byteOut.add(new ByteCode(tByteCode.apila,v.get(i).getLexema()));
	i++;
	expresionCorrecta=i;
		}

else 
	if(v.get(i).getTipoToken()==TToken.castChar||v.get(i).getTipoToken()==TToken.castInt||
			v.get(i).getTipoToken()==TToken.castNat||v.get(i).getTipoToken()==TToken.castFloat){
	byteOut.add(new ByteCode(tByteCode.apila,v.get(i).getLexema()));	
	i++;
	expresionCorrecta=i;
		}

			
else
	if(v.get(i).getTipoToken()==TToken.negArit){
	operacion=v.get(i).getTipoToken();
	i++;
	expresionCorrecta=i;
		}

else if(v.get(i).getTipoToken()==TToken.negLogica){
	operacion=v.get(i).getTipoToken();
	i++;
	expresionCorrecta=i;
		}

//operaciones intermedias x+y+x
else if((procesaOperacionCero(v.get(i).getTipoToken())||procesaOperacionUno(v.get(i).getTipoToken())||
		procesaOperacionDos(v.get(i).getTipoToken())||procesaOperacionTres(v.get(i).getTipoToken()))&&
				(v.get(i-1).getTipoToken()==TToken.ident||v.get(i-1).getTipoToken()==TToken.natural||v.get(i-1).getTipoToken()==TToken.entero||
				v.get(i-1).getTipoToken()==TToken.real||v.get(i-1).getTipoToken()==TToken.caracter||
				v.get(i-1).getTipoToken()==TToken.booleanoCierto||v.get(i-1).getTipoToken()==TToken.booleanoFalso)&&
					(v.get(i+1).getTipoToken()==TToken.ident||v.get(i+1).getTipoToken()==TToken.natural||v.get(i+1).getTipoToken()==TToken.entero||
					v.get(i+1).getTipoToken()==TToken.real||v.get(i+1).getTipoToken()==TToken.caracter||
					v.get(i+1).getTipoToken()==TToken.booleanoCierto||v.get(i+1).getTipoToken()==TToken.booleanoFalso)&&
					(procesaOperacionCero(v.get(i-2).getTipoToken())||procesaOperacionUno(v.get(i-2).getTipoToken())||
					procesaOperacionDos(v.get(i-2).getTipoToken())||procesaOperacionTres(v.get(i-2).getTipoToken())) )
				{
				operacion2=v.get(i).getTipoToken();
				i++;
				expresionCorrecta=i;
				}	
	
//operaciones intermedias y+x+(x+2)
			else if((procesaOperacionCero(v.get(i).getTipoToken())||procesaOperacionUno(v.get(i).getTipoToken())||
					procesaOperacionDos(v.get(i).getTipoToken())||procesaOperacionTres(v.get(i).getTipoToken()))&&
							(v.get(i-1).getTipoToken()==TToken.ident||v.get(i-1).getTipoToken()==TToken.natural||v.get(i-1).getTipoToken()==TToken.entero||
							v.get(i-1).getTipoToken()==TToken.real||v.get(i-1).getTipoToken()==TToken.caracter||
							v.get(i-1).getTipoToken()==TToken.booleanoCierto||v.get(i-1).getTipoToken()==TToken.booleanoFalso)&&
							(v.get(i+1).getTipoToken()==TToken.PA)){
				
				operacion2=v.get(i).getTipoToken();
				i++;
				expresionCorrecta=i;
							
			}	
//operaciones x+y
else if((procesaOperacionCero(v.get(i).getTipoToken())||procesaOperacionUno(v.get(i).getTipoToken())||
		procesaOperacionDos(v.get(i).getTipoToken())||procesaOperacionTres(v.get(i).getTipoToken()))&&
				(v.get(i-1).getTipoToken()==TToken.ident||v.get(i-1).getTipoToken()==TToken.natural||v.get(i-1).getTipoToken()==TToken.entero||
				v.get(i-1).getTipoToken()==TToken.real||v.get(i-1).getTipoToken()==TToken.caracter||
				v.get(i-1).getTipoToken()==TToken.booleanoCierto||v.get(i-1).getTipoToken()==TToken.booleanoFalso)&&
				(v.get(i+1).getTipoToken()==TToken.ident||v.get(i+1).getTipoToken()==TToken.natural||v.get(i+1).getTipoToken()==TToken.entero||
				v.get(i+1).getTipoToken()==TToken.real||v.get(i+1).getTipoToken()==TToken.caracter||
				v.get(i+1).getTipoToken()==TToken.booleanoCierto||v.get(i+1).getTipoToken()==TToken.booleanoFalso))
				{
				operacion=v.get(i).getTipoToken();
				i++;
				expresionCorrecta=i;
				}	
			
else {
	error(v.get(i).getLinea(),"Fallo en el primer miembro de la Expresión");
	expresionCorrecta = -1;
	}
		}

if(operacion2!=null){
	switch(operacion2) { // Elige la opcion acorde al numero de mes
	case negArit:byteOut.add(new ByteCode(tByteCode.restaunitaria)); break;
	case negLogica:byteOut.add(new ByteCode(tByteCode.negacionlogica)); break;
	case igualIgual:byteOut.add(new ByteCode(tByteCode.igual)); break;
	case great:byteOut.add(new ByteCode(tByteCode.mayor));break;
	case less:byteOut.add(new ByteCode(tByteCode.menor));break;
	case greatEq:byteOut.add(new ByteCode(tByteCode.mayorigual));break;
	case lessEq:byteOut.add(new ByteCode(tByteCode.menorigual));break;
	case distinto:byteOut.add(new ByteCode(tByteCode.distinto));break;

	case sum:byteOut.add(new ByteCode(tByteCode.suma)); break;
	case div:byteOut.add(new ByteCode(tByteCode.divide));break;
	case mult:byteOut.add(new ByteCode(tByteCode.multiplica));break;
	case mod:byteOut.add(new ByteCode(tByteCode.modulo));break;
	}
	}

if(expresionCorrecta!=-1){
	//leo ;
if(v.get(i).getTipoToken()==TToken.puntoyComa){
	i++;
	expresionCorrecta=i;	
		}
else {	
error(v.get(i).getLinea(),"Fallo falta el ; de final de expresión");
expresionCorrecta = -1;}
}
return expresionCorrecta;

}

	
	
	
/////////////////// ERRORES Y MAIN//////////////////////
////////////////////////////////////////////////////////

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
			
				if( byteOut.get(i).getDireccion().equals("")){
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
	
	public boolean procesaIdentificador(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.ident) return true;
		else return false;
	}
	
	public void actualizaPunteroMemoriaDatos(){
		if(posMemoLibre < 100) { //mismo numero que la creacion de las tablas hash
			posMemoLibre = posMemoLibre + 1;
		}
		else {
			System.out.println("YA NO HAY ESPACIO LIBRE EN LA MEMORIA!!!");
			errorCompilacion = true;
		}
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
	
//MAAAAAAIIIIIINNNNNN_____________________________
	
	
	public static void main(String[] args) {
		
		AnalizadorSintactico sintetiza = new AnalizadorSintactico();
		sintetiza.compilar();
		sintetiza.printParser();
		
	}
}




