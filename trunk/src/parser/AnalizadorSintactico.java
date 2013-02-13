package parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import aLexico.ALexico;
import aLexico.Token;
import aLexico.TToken;
import parser.ArbolBin;
import parser.Nodo;

public class AnalizadorSintactico {

	private HashMap<Integer, String> dirMemoria;
	private HashMap<String, TablaInfo> TS;
	private Vector<ByteCode> byteOut;
	private Vector<ByteCode> byteOutInorden;
	private Vector<Token> entrada;
	private String descripError;
	private Vector<String> descripErrorContextual;
	private boolean errorCompilacion;
	private ALexico scanner;
	private int posMemoLibre;
	private ArbolBin arbol; // Arbol declarado como Local para poder imprimir
	private int numVueltas; //variable para controlar que el programa no se queda en bucle infinito
	private static String salida; 


	public AnalizadorSintactico(String nombreFichero) {

		dirMemoria = new HashMap<Integer, String>(100);
		TS = new HashMap<String, TablaInfo>(100);
		errorCompilacion = false;
		byteOut = new Vector<ByteCode>();
		byteOutInorden = new Vector<ByteCode>();
		posMemoLibre = 0;
		scanner = new ALexico();
		salida="";
		salida+=scanner.scan(nombreFichero);
		entrada = scanner.dameTokens();
		descripErrorContextual = new Vector<String>();
		numVueltas=0;
		if (scanner.getErrorLex())
			errorCompilacion = true;

	}
	
	public void compilar() {

		int i = 0;
		int linea = 0;
		if (!entrada.isEmpty()) {
			linea = procesaCabecera(entrada, i);
			if (!errorCompilacion && linea != -1) {
				linea = procesaSecVariables(entrada, linea);
				if (!errorCompilacion && linea != -1) {
					procesaSecInstrucciones(entrada, linea);
					byteOut.add(new ByteCode(tByteCode.stop));
				}
			}
		} else
			salida+="¡No hay nada que compilar!\n";

	}

	public int procesaCabecera(Vector<Token> VCabecera, int linea) {

		int i = linea;
		boolean finCabecera = false;
		while (!errorCompilacion && !finCabecera) {

			if (VCabecera.get(i).getTipoToken() != TToken.program) {
				error(VCabecera.get(i).getLinea(),"No se reconoce la palabra reservada program");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}

			if (VCabecera.get(i).getTipoToken() != TToken.dosPuntos) {
				error(VCabecera.get(i).getLinea(), "Faltan los dos puntos");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}

			if (VCabecera.get(i).getTipoToken() != TToken.ident) {
				error(VCabecera.get(i).getLinea(),"Nombre de programa no valido");
				errorCompilacion = true;
				break;
			} else {// meter el identificador en la posicion 0
				i++;
			}

			if (VCabecera.get(i).getTipoToken() != TToken.LA) {
				error(VCabecera.get(i).getLinea(),
						"Falta la llave de apertura!");
				errorCompilacion = true;
				break;
			} else {
				i++;
				finCabecera = true;
				return i;
			}
		}
		return -1;
	}

	public int procesaSecVariables(Vector<Token> VVariables, int linea) {

		int i = linea;
		boolean finVariables = false;

		while (!errorCompilacion && !finVariables) {

			if (VVariables.get(i).getTipoToken() != TToken.varsConsts) {
				error(VVariables.get(i).getLinea(), "No se reconoce la palabra reservada vars-consts");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}

			if (VVariables.get(i).getTipoToken() != TToken.LA) {
				error(VVariables.get(i).getLinea(),"Falta la llave de apertura");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}

			// //////////////////////////////////////////////////////////////////
			// //////////////Decs -> Decs Dec |
			// Dec///////////////////////////////
			// //////////////Dec -> var tipo
			// ident;///////////////////////////////
			// //////////////Dec -> const tipo ident :=
			// ValorDec;//////////////////
			// //////////////////////////////////////////////////////////////////

			int nuevapos = procesaInstVariables(VVariables, i);

			if (nuevapos != -1) {
				finVariables = true; // hemos procesado la seccion de las variables debuty
				nuevapos++;
				return nuevapos;
			}
		}
		return -1;
	}

	public int procesaSecInstrucciones(Vector<Token> VInstrucciones, int linea) {
		int i = linea;
		boolean finVariables = false;

		while (!errorCompilacion && !finVariables) {

			if (VInstrucciones.get(i).getTipoToken() != TToken.instrucciones) {
				error(VInstrucciones.get(i).getLinea(),"No se reconoce la palabra reservada instructions");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}

			if (VInstrucciones.get(i).getTipoToken() != TToken.LA) {
				error(VInstrucciones.get(i).getLinea(),"Falta la llave de apertura del comienzo de las Inst");
				errorCompilacion = true;
				break;
			} else {
				i++;
			}
			// /////////////////////////////////////////////////////
			// //////////Inst -> Inst Ins | Ins///////////////////////
			// //////////Ins -> InsAsig;//////////// ////////////////
			// //////////Ins -> InsR; ///////////////////////////////
			// //////////Ins -> InsW; ///////////////////////////////
			// //////////Ins -> SWAP1();/////////////////////////////
			// //////////Ins -> SWAP2();/////////////////////////////
			// /////////////////////////////////////////////////////

			int nuevapos = procesaInstInstrucciones(VInstrucciones, i);

			if (nuevapos != -1) {
				finVariables = true;// hemos procesado la seccion de las instrucciones debuty
				nuevapos++;
				return nuevapos;
			}
		}
		return -1;
	}

	public int procesaInstVariables(Vector<Token> v, int linea) {

		int i = linea;
		boolean insFinalizada = false;

		while (v.get(i).getTipoToken() != TToken.LC) {
			insFinalizada = false;

			if (v.get(i).getTipoToken() == TToken.var
					|| v.get(i).getTipoToken() == TToken.constante) {

				boolean variable = (v.get(i).getTipoToken() == TToken.var);
				i++;
				if (procesaTipoVariable(v, i)) {
					String tipo = v.get(i).getTipoToken().toString();
					i++;
					if (procesaIdentificador(v, i)) {
						String identificador = v.get(i).getLexema();
						i++;
						if (variable && procesaFinInstruccion(v, i)) {
							// ahora tenemos q mirar si esta duplicada, y si no meterla en la tabla!!
							// tenemos el identificador, el tipo y si es cte o no!!
							// esta en la tabla TS???

							if (TS.containsKey(identificador)) {
								error(v.get(i).getLinea(), "VARIABLE DUPLICADA");
								errorCompilacion = true;
								break;
							} else {
								// variable no duplicada, la metemos en la TS y la inicializamos en la MEMO
								// Y SUMAMOS UNO A LA i!!!!
								TablaInfo novata = new TablaInfo(tipo, false,
										posMemoLibre);
								TS.put(identificador, novata);
								dirMemoria.put(posMemoLibre, "null");
								actualizaPunteroMemoriaDatos();
								insFinalizada = true;
								i++;
							}

						} else if (variable) {
							error(v.get(i).getLinea(), "Falta punto y coma!");
							errorCompilacion = true;
							break;
						}

						if (!insFinalizada) {
							if (!variable && procesaAsignacion(v, i)) {
								i++;
								boolean numeroNegativo = false;
								if (numNegativo(v, i)) {
									numeroNegativo = true;
									i++;
								}
								if (correspondenciaDeTipos(v, tipo, i)) {
									String valor = "";
									if (numeroNegativo)
										valor = "-" + v.get(i).getLexema();
									else
										valor = v.get(i).getLexema();

									if (procesaFinInstruccion(v, i + 1)) {
										if (TS.containsKey(identificador)) {
											error(v.get(i).getLinea(),"VARIABLE DUPLICADA");
											errorCompilacion = true;
											break;
										} else {// variable no duplicada, la metemos en la TS
												// y la inicializamos en la MEMO Y SUMAMOS UNO A LA i!!!!
											TablaInfo novata = new TablaInfo(tipo, true, posMemoLibre);
											TS.put(identificador, novata);
											dirMemoria.put(posMemoLibre, valor);
											actualizaPunteroMemoriaDatos();
											insFinalizada = true;
											i = i + 2;// ya q ya sabemos q hay punto y coma!!! :D
										}
									} else {
										error(v.get(i).getLinea(),"Falta punto y coma");
										errorCompilacion = true;
										break;
									}

								} else {
									error(v.get(i).getLinea(),"Error de tipos!");
									errorCompilacion = true;
									break;

								}

							} else {
								error(v.get(i).getLinea(),"Error en la asignacion de la cte");
								errorCompilacion = true;
								break;
							}

						}
					} else {
						error(v.get(i).getLinea(), "Identificador erroneo");
						errorCompilacion = true;
						break;
					}
				} else {
					error(v.get(i).getLinea(), "Tipo no reconocido");
					errorCompilacion = true;
					break;
				}

			}// if

			else {
				error(v.get(i).getLinea(), "Error en linea");
				errorCompilacion = true;
				break;
			}

		}// while

		if (!errorCompilacion) {
			if (v.get(i).getTipoToken() == TToken.LC) {
			} else {
				error(v.get(i).getLinea(), "Falta la } de Declaraciones");
				errorCompilacion = true;
			}
		}
		if (!errorCompilacion)
			return i;
		return -1;
	}

	public int procesaInstInstrucciones(Vector<Token> v, int linea) {
		String identificador;
		int i = linea;
		while (v.get(i).getTipoToken() != TToken.LC) {

			// /////////////Instrucción de Tipo IN//////////////////////
			// /////////////InsR->in(ident); ///////////////////////////////
			// /////////////////////////////////////////////////////////

			if (v.get(i).getTipoToken() == TToken.entradaTeclado) {
				i++;
				if (v.get(i).getTipoToken() == TToken.PA) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el (");
					errorCompilacion = true;
					break;
				}
				// leo iden y compruebo que esta en TS y no es constante
				if (v.get(i).getTipoToken() == TToken.ident) {
					identificador = v.get(i).getLexema();
					// si es Identificador de TS
					if (TS.containsKey(identificador)) {
						// si no es Constante
						if (!TS.get(identificador).isConstante()) {
							i++;
						} else {
							error(v.get(i).getLinea(),"IN sobre identificador que es una Constante");
							errorCompilacion = true;
							break;
						}

					} else {
						error(v.get(i).getLinea(),"Identificador que no está en TS");
						errorCompilacion = true;
						break;
					}

				} else {
					error(v.get(i).getLinea(), "No es identificador válido");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.PC) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el )");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.puntoyComa) {
					byteOut.add(new ByteCode(tByteCode.read));
					String aux = String.valueOf(TS.get(identificador)
							.getDireccion());
					byteOut.add(new ByteCode(tByteCode.desapila_dir, aux));
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el ;");
					errorCompilacion = true;
					break;
				}

			}

			// /////////////Instrucción de Tipo OUT//////////////////////
			// /////////////InsW->out(exp); /////////////////////////////
			// //////////////////////////////////////////////////////////
			else if (v.get(i).getTipoToken() == TToken.salidaPantalla) {
				i++;
				if (v.get(i).getTipoToken() == TToken.PA) {
				} else {
					error(v.get(i).getLinea(), "Faltan el(");
					errorCompilacion = true;
					break;
				}

				// leo Exp
				// creo aquí el arbol para poder leer otra instruccion
				arbol = new ArbolBin();
				
				Vector<Token> expParaConTextualOut=new Vector<Token>();
				//aux=Contiene la expresión en sí mediante el metodo procesaExpParaContextuales
				expParaConTextualOut=procesaExpParaContextuales(v,i);
				i = procesaExpresion(v, i);

				if (i != -1) {// ////Procesa Exp.///////
					procesaRestriccionesContextualesExpresion(expParaConTextualOut);
					arbol.posorden(arbol.raiz, byteOut);
					byteOut.add(new ByteCode(tByteCode.write));
				} else {
					errorCompilacion = true;
					break;
				}

				// leo ;
				if (v.get(i).getTipoToken() == TToken.puntoyComa) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el ;");
					errorCompilacion = true;
					break;
				}

			}
			// /////////////Instrucción de Tipo Asig//////////////////////
			// /////////////InstAsig->iden=Exp;///////////////////////////
			// ///////////////////////////////////////////////////////////
			else if (v.get(i).getTipoToken() == TToken.ident) {
				identificador = v.get(i).getLexema();
				// si es Identificador de TS
				if (TS.containsKey(identificador)) {
					// si no es Constante
					if (!TS.get(identificador).isConstante()) {
						i++;
					} else {
						error(v.get(i).getLinea(),"ASIG sobre identificador que es una Constante");
						errorCompilacion = true;
						break;
					}

				} else {
					error(v.get(i).getLinea(),"Identificador que no está en TS");
					errorCompilacion = true;
					break;
				}

				if (v.get(i).getTipoToken() == TToken.equals) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el = ");
					errorCompilacion = true;
					break;
				}
				// leo Exp
				// creo aquí el arbol para poder leer otra instruccion
				arbol = new ArbolBin();
				
				Vector<Token> expParaConTextual=new Vector<Token>();
				//aux=Contiene la expresión en sí mediante el metodo procesaExpParaContextuales
				expParaConTextual=procesaExpParaContextuales(v,i);
				i = procesaExpresion(v, i);

				if (i != -1) {// ////Procesa Exp.///////
					procesaRestriccionesContextuales(TS.get(identificador).getTipo(),v.get(i).getLinea(),expParaConTextual);
					String aux2 = String.valueOf(TS.get(identificador).getDireccion());
					arbol.posorden(arbol.raiz, byteOut);
					byteOut.add(new ByteCode(tByteCode.desapila_dir, aux2));
					i++;
				} else {
					errorCompilacion = true;
					break;
				}
			}

			// /////////////Instrucción de Tipo SWAP1///////////////////////
			// /////////////Ins -> SWAP1();/////////////////////////////////
			// /////////////////////////////////////////////////////////////
			else if (v.get(i).getTipoToken() == TToken.swap1) {
				i++;
				if (v.get(i).getTipoToken() == TToken.PA) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el (");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.PC) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el )");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.puntoyComa) {
					byteOut.add(new ByteCode(tByteCode.swap1));
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el ;");
					errorCompilacion = true;
					break;
				}

			}
			// /////////////Instrucción de Tipo SWAP2////////////////////
			// /////////////Ins -> SWAP2();//////////////////////////////
			// //////////////////////////////////////////////////////////
			else if (v.get(i).getTipoToken() == TToken.swap2) {
				i++;
				if (v.get(i).getTipoToken() == TToken.PA) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el (");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.PC) {
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el )");
					errorCompilacion = true;
					break;
				}
				if (v.get(i).getTipoToken() == TToken.puntoyComa) {
					byteOut.add(new ByteCode(tByteCode.swap2));
					i++;
				} else {
					error(v.get(i).getLinea(), "Falta el ;");
					errorCompilacion = true;
					break;
				}
			}

			// /////////////Instrucciones Inválidas//////////////////////
			// //////////////////////////////////////////////////////////

			else {
				error(v.get(i).getLinea(), "No hay instrucciones válidas");
				errorCompilacion = true;
				break;
			}

		}// while

		if (!errorCompilacion) {
			if (v.get(i).getTipoToken() == TToken.LC) {
				i++;
			} else {
				error(v.get(i).getLinea(), "Falta la } de Instrucciones");
				errorCompilacion = true;
			}
			if (v.get(i).getTipoToken() == TToken.LC) {
			} else {
				error(v.get(i).getLinea(), "Falta la } Program");
				errorCompilacion = true;
			}
		}
		if (!errorCompilacion)
			return i;
		return -1;
	}



	private Vector<Token> procesaExpParaContextuales(Vector<Token> v, int i) {
		Vector<Token> expresion = new Vector<Token>();
		while (v.get(i).getTipoToken() != TToken.puntoyComa) {
			expresion.add(v.get(i));
			i++;}
		return expresion;
	}

	// //////////////////////////////////////////////////////
	// /////////////Procesa Expresiones//////////////////////
	// //////////////////////////////////////////////////////

	private int procesaExpresion(Vector<Token> v, int i) {

		TToken operacion = null;
		Nodo raiz = new Nodo();
		int indice = 0;
		int indice2 = 0;
		int referencia;
		int lineaActual = v.get(i).getLinea();
		// Nos hacemos un array auxiliar para meter la expresion General
		Vector<Token> expresion = new Vector<Token>();
		while (v.get(i).getTipoToken() != TToken.puntoyComa) {
			expresion.add(v.get(i));
			i++;
			if (i >= v.size()) {
				error(lineaActual, "Se esperaba leer un punto y coma");
				return -1;
			}
		}
		
		// Si encontramos un ( ) nos situamos después de él.
		if (expresion.size() > 0) {

			if (expresion.get(indice).getTipoToken() == TToken.PA) {

					indice = procesaExpParentesis(expresion, indice);
				if (indice == -1) {
					error(v.get(i).getLinea(), "error en los parentesis");
					return -1;
				}
			}
		} else {
			error(v.get(i).getLinea(),"Después de la asignacion se espera un identificador o número");
			return -1;
		}

		// Si encontramos un nat,real,... o ident nos situamos después de él.
		if ((indice < expresion.size()) && (procesaTipo(expresion, indice))) {
			if (!(TS.containsKey(expresion.get(indice).getLexema()))
					&& (expresion.get(indice).getTipoToken() == TToken.ident)) { 
				error(expresion.get(indice).getLinea(),
						"Se intenta asignar un identificador que no está en TS");
				return -1;
			} else {
				indice++;
			}
		}

		// Si encontramos un op0 meter raiz arbol binario
		if ((indice < expresion.size())
				&& (buscaOperacionCero(indice, expresion) != -1)) {
			// Seleccionamos el Op0 y almacenamos su indice en Refenrecia para
			// luego dividir en dos subvectores
			referencia = buscaOperacionCero(indice, expresion);

			operacion = expresion.get(referencia).getTipoToken();

			if (operacion != null) {
				raiz.info = new ByteCode(procesaOperacion(operacion));
			}

			// Nos hacemos dos subarrays de las expresiones de los lados del operador
			Vector<Token> expresionIzq = new Vector<Token>();
			Vector<Token> expresionDer = new Vector<Token>();

			while (indice2 != referencia) {
				expresionIzq.add(expresion.get(indice2));
				indice2++;
			}
			indice2++;
			while (indice2 != expresion.size()) {
				expresionDer.add(expresion.get(indice2));
				indice2++;
			}

			try {
				raiz.izq = procesaExpresion0(expresionIzq);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return -1;
				}
			}

			try {
				raiz.der = procesaExpresion0(expresionDer);
			}

			catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return -1;
				}
			}

		}
		// si no e encontrado ningun Op0
		else {
			try {
				raiz = procesaExpresion0(expresion);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return -1;
				}
			}
		}

		arbol.insertarNodo(raiz);
		return i;
	}


	// //////////////////////////////////////////////////////
	// /////////////Procesa Exp0/////////////////////////////
	// //////////////////////////////////////////////////////

	private Nodo procesaExpresion0(Vector<Token> expresion) throws Exception {

		TToken operacion = null;
		Nodo raizaux = new Nodo();
		int indice = 0;
		int indice2 = 0;
		int referencia;
		numVueltas++;
		if (expresion.size() == 0 || numVueltas>=50) {
			throw new Exception("La expresión de la asignación es incorrecta");
		}
		int lineaActual = expresion.get(indice).getLinea();
		

		if (esPalReservada(expresion.get(indice).getTipoToken())){
		
			throw new Exception("No se puede utilizar aquí esta palabra reservada");

		}
		
		if (expresion.size() == 1) {
			// si el tamaño es solamente 1, es que tenemos o un numero o un identificador
			if (procesaTipo(expresion, indice)) {
				if (TS.containsKey(expresion.get(indice).getLexema())
						|| (expresion.get(indice).getTipoToken() != TToken.ident)) {
					raizaux.info = new ByteCode(tByteCode.apila, expresion.get(
							indice).getLexema(),dameTipoParaBytecode(expresion,indice));
				} else {
					error(expresion.get(indice).getLinea(),
							"Se intenta asignar un identificador que no está en TS");
				}
			} else if ((expresion.get(indice).getTipoToken() == TToken.ident)
					&& TS.containsKey(expresion.get(indice).getLexema())) {
				String aux = String.valueOf(TS.get(expresion.get(indice).getLexema()).getDireccion());
				raizaux.info = new ByteCode(tByteCode.apila_dir, aux);
			} else
				throw new Exception("Error procesando el elemento");
			indice++;

		} else {

			if (expresion.size() == 2) { // No puede haber una expresion de dos elementos si el primero es un número
				if ((expresion.get(indice).getTipoToken() == TToken.entero)
						|| (expresion.get(indice).getTipoToken() == TToken.natural)
						|| (expresion.get(indice).getTipoToken() == TToken.real))
					throw new Exception("Error procesando el elemento");
			}

			// Si encontramos un ( ) nos situamos después de él.
			if (expresion.get(indice).getTipoToken() == TToken.PA) {
				indice = procesaExpParentesis(expresion, indice);
			}

			// Si encontramos un nat,real,... o ident nos situamos después de él.
			if ((indice < expresion.size()) && (procesaTipo(expresion, indice))) {
				if (!(TS.containsKey(expresion.get(indice).getLexema()))
						&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
					error(expresion.get(indice).getLinea(),
							"Se intenta asignar un identificador que no está en TS");
					return null;
				} else {
					indice++;
				}
			}
			// Si encontramos un op0 anterior lo bajamos a procesaExp1
			if ((indice < expresion.size())
					&& (buscaOperacionCero(indice, expresion) != -1)) {
				try {
					raizaux = procesaExpresion1(expresion);
					indice = expresion.size() - 1;
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

			}

			// Si encontramos un op1 meter raiz arbol binario
			if ((indice < expresion.size())
					&& (buscaOperacionUno(indice, expresion) != -1)) {
				// Seleccionamos el Op1 y almacenamos su indice en Refenrecia
				// para luego dividir en dos subvectores
				referencia = buscaOperacionUno(indice, expresion);
				operacion = expresion.get(referencia).getTipoToken();
				if (operacion != null) {
					raizaux.info = new ByteCode(procesaOperacion(operacion));
				}

				// Nos hacemos dos subarrays de las expresiones de los lados del operador
				Vector<Token> expresionIzq = new Vector<Token>();
				Vector<Token> expresionDer = new Vector<Token>();

				while (indice2 != referencia) {
					expresionIzq.add(expresion.get(indice2));
					indice2++;
				}
				indice2++;
				while (indice2 != expresion.size()) {
					expresionDer.add(expresion.get(indice2));
					indice2++;
				}

				try {
					raizaux.izq = procesaExpresion0(expresionIzq);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

				try {
					raizaux.der = procesaExpresion1(expresionDer);
				}

				catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}
			// si no e encontrado ningun Op1 se lo paso a procesaExp1
			else {
				try {
					raizaux = procesaExpresion1(expresion);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}

		}
		return raizaux;

	}

	// //////////////////////////////////////////////////////
	// /////////////Procesa Exp1/////////////////////////////
	// //////////////////////////////////////////////////////

	private Nodo procesaExpresion1(Vector<Token> expresion) throws Exception {

		TToken operacion = null;
		Nodo raizaux = new Nodo();
		int indice = 0;
		int indice2 = 0;
		int referencia;
		if (expresion.size() == 0) {
			throw new Exception("La expresión de la asignación es incorrecta");
		}
		int lineaActual = expresion.get(indice).getLinea();

		if (expresion.size() == 1) {
			// si el tamaño es solamente 1, es que tenemos o un numero o un identificador
			if (procesaTipo(expresion, indice)) {
				if (TS.containsKey(expresion.get(indice).getLexema())
						|| (expresion.get(indice).getTipoToken() != TToken.ident)) {
					raizaux.info = new ByteCode(tByteCode.apila, expresion.get(
							indice).getLexema(),dameTipoParaBytecode(expresion,indice));
				} else {
					error(expresion.get(indice).getLinea(),"Se intenta asignar un identificador que no está en TS");
				}
			} else if ((expresion.get(indice).getTipoToken() == TToken.ident)
					&& TS.containsKey(expresion.get(indice).getLexema())) {
				String aux = String.valueOf(TS.get(expresion.get(indice).getLexema()).getDireccion());
				raizaux.info = new ByteCode(tByteCode.apila_dir, aux);
			} else
				throw new Exception("Error procesando el elemento");

			indice++;

		} else {

			// Si encontramos un ( ) nos situamos después de él.
			if (expresion.get(indice).getTipoToken() == TToken.PA) {
				indice = procesaExpParentesis(expresion, indice);
				if (indice == -1) {
					throw new Exception("La expresión de la asignación es incorrecta debido a los parentesis");
				}
			}

			// Si encontramos un nat,real,... o ident nos situamos después de él.
			if ((indice < expresion.size()) && (procesaTipo(expresion, indice))) {
				if (!(TS.containsKey(expresion.get(indice).getLexema()))
						&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
					error(expresion.get(indice).getLinea(),"Se intenta asignar un identificador que no está en TS");
					return null;
				} else {
					indice++;
				}
			}
			// Si encontramos un op0 o Op1 anterior lo bajamos a procesaExp2
			if ((indice < expresion.size())
					&& ((buscaOperacionCero(indice, expresion) != -1) || (buscaOperacionUno(
							indice, expresion) != -1))) {
				try {
					raizaux = procesaExpresion2(expresion);
					indice = expresion.size() - 1;
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

			}

			// Si encontramos un op2 meter raiz arbol binario
			if ((indice < expresion.size())
					&& (buscaOperacionDos(indice, expresion) != -1)) {
				// Seleccionamos el Op2 y almacenamos su indice en Refenrecia
				// para luego dividir en dos subvectores
				referencia = buscaOperacionDos(indice, expresion);
				operacion = expresion.get(referencia).getTipoToken();
				if (operacion != null) {
					raizaux.info = new ByteCode(procesaOperacion(operacion));
				}

				// Nos hacemos dos subarrays de las expresiones de los lados del operador
				Vector<Token> expresionIzq = new Vector<Token>();
				Vector<Token> expresionDer = new Vector<Token>();

				while (indice2 != referencia) {
					expresionIzq.add(expresion.get(indice2));
					indice2++;
				}
				indice2++;
				while (indice2 != expresion.size()) {
					expresionDer.add(expresion.get(indice2));
					indice2++;
				}

				try {
					raizaux.izq = procesaExpresion1(expresionIzq);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

				try {
					raizaux.der = procesaExpresion2(expresionDer);
				}

				catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}
			// si no e encontrado ningun Op2 se lo paso a procesaExp2
			else {
				try {
					raizaux = procesaExpresion2(expresion);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}

		}
		return raizaux;

	}

	// //////////////////////////////////////////////////////
	// /////////////Procesa Exp2/////////////////////////////
	// //////////////////////////////////////////////////////

	private Nodo procesaExpresion2(Vector<Token> expresion) throws Exception {

		TToken operacion = null;
		Nodo raizaux = new Nodo();
		int indice = 0;
		int indice2 = 0;
		int referencia;
		if (expresion.size() == 0) {
			throw new Exception("La expresión de la asignación es incorrecta");
		}
		int lineaActual = expresion.get(indice).getLinea();

		if (expresion.size() == 1) {
			// si el tamaño es solamente 1, es que tenemos o un numero o un identificador

			if (procesaTipo(expresion, indice)) {
				if (!(TS.containsKey(expresion.get(indice).getLexema()))
						&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
					throw new Exception("Se intenta asignar un identificador que no está en TS");

				} else {
					raizaux.info = new ByteCode(tByteCode.apila, expresion.get(
							indice).getLexema(),dameTipoParaBytecode(expresion,indice));
				}

			} else if ((expresion.get(indice).getTipoToken() == TToken.ident)
					&& TS.containsKey(expresion.get(indice).getLexema())) {
				String aux = String.valueOf(TS.get(expresion.get(indice).getLexema()).getDireccion());
				raizaux.info = new ByteCode(tByteCode.apila_dir, aux);
			} else
				throw new Exception("Error procesando el elemento");

			indice++;

		} else {

			// Si encontramos un ( ) nos situamos después de él.
			if (expresion.get(indice).getTipoToken() == TToken.PA) {
				indice = procesaExpParentesis(expresion, indice);
			}

			if (indice == -1) {
				throw new Exception(
						"La expresión de la asignación es incorrecta debido a los parentesis");
			}

			// Si encontramos un nat,real,... o ident nos situamos después de él.
			if ((indice < expresion.size()) && (procesaTipo(expresion, indice))) {
				if (!(TS.containsKey(expresion.get(indice).getLexema()))
						&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
					error(expresion.get(indice).getLinea(),"Se intenta asignar un identificador que no está en TS");
					return null;
				} else {
					indice++;
				}
			}

			// Si encontramos un op0 ,Op1 o op2 anterior lo bajamos a procesaExp2
			if ((indice < expresion.size())
					&& ((buscaOperacionCero(indice, expresion) != -1)
							|| (buscaOperacionUno(indice, expresion) != -1) || (buscaOperacionDos(
							indice, expresion) != -1))) {
				try {
					raizaux = procesaExpresion3(expresion);
					indice = expresion.size() - 1;
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

			}

			// Si encontramos un op3 meter raiz arbol binario
			if ((indice < expresion.size())
					&& (buscaOperacionTres(indice, expresion) != -1)) {
				// Seleccionamos el Op3 y almacenamos su indice en Refenrecia
				// para luego dividir en dos subvectores
				referencia = buscaOperacionTres(indice, expresion);
				operacion = expresion.get(referencia).getTipoToken();
				if (operacion != null) {
					raizaux.info = new ByteCode(procesaOperacion(operacion));
				}

				// Nos hacemos dos subarrays de las expresiones de los lados del operador
				Vector<Token> expresionIzq = new Vector<Token>();
				Vector<Token> expresionDer = new Vector<Token>();

				while (indice2 != referencia) {
					expresionIzq.add(expresion.get(indice2));
					indice2++;
				}
				indice2++;
				while (indice2 != expresion.size()) {
					expresionDer.add(expresion.get(indice2));
					indice2++;
				}

				try {
					raizaux.izq = procesaExpresion3(expresionIzq);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}

				try {
					raizaux.der = procesaExpresion2(expresionDer);
				}

				catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}
			// si no e encontrado ningun Op3 se lo paso a procesaExp3
			else {
				try {
					raizaux = procesaExpresion3(expresion);
				} catch (Exception e) {
					if (e != null) {
						error(lineaActual, e.getMessage());
						// e.printStackTrace();
						return null;
					}
				}
			}

		}
		return raizaux;

	}

	// //////////////////////////////////////////////////////
	// /////////////Procesa Exp3/////////////////////////////
	// //////////////////////////////////////////////////////

	private Nodo procesaExpresion3(Vector<Token> expresion) throws Exception {

		TToken operacion = null;
		Nodo raizaux = new Nodo();
		int indice = 0;
		int indice2 = 0;
		int referencia = 0;
		Vector<Token> expresionSinParent = new Vector<Token>();
		if (expresion.size() == 0) {
			throw new Exception("La expresión de la asignación es incorrecta");
		}
		int lineaActual = expresion.get(indice).getLinea();

		if (expresion.size() == 1) {
			// si el tamaño es solamente 1, es que tenemos o un numero o un identificador

			if (procesaTipo(expresion, indice)) {
				if (!(TS.containsKey(expresion.get(indice).getLexema()))
						&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
					throw new Exception("Se intenta asignar un identificador que no esta en TS");

				} else {
					raizaux.info = new ByteCode(tByteCode.apila, expresion.get(indice).getLexema(),dameTipoParaBytecode(expresion,indice));
				}

			} else if ((expresion.get(indice).getTipoToken() == TToken.ident)
					&& TS.containsKey(expresion.get(indice).getLexema())) {
				String aux = String.valueOf(TS.get(
						expresion.get(indice).getLexema()).getDireccion());
				raizaux.info = new ByteCode(tByteCode.apila_dir, aux);
			} else
				throw new Exception("Error procesando el elemento");

			indice++;
		}

		else if ((indice < expresion.size())
				&& (buscaOperacionCuatroDos(indice, expresion) != -1)) {

			referencia = buscaOperacionCuatroDos(indice, expresion);
			operacion = expresion.get(referencia).getTipoToken();
			if (operacion != null) {
				raizaux.info = new ByteCode(procesaOperacion(operacion));
			}
			Vector<Token> expresionDer = new Vector<Token>();
			indice2 = referencia + 1;
			while (indice2 != expresion.size()) {
				expresionDer.add(expresion.get(indice2));
				indice2++;
			}

			try {
				raizaux.der = procesaExpresion3(expresionDer);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}

		} else if ((indice < expresion.size())
				&& (buscaOperacionMenosUnario(indice, expresion) != -1)) {

			referencia = buscaOperacionMenosUnario(indice, expresion);
			operacion = expresion.get(referencia).getTipoToken();
			if (operacion != null) {
				raizaux.info = new ByteCode(procesaOperacion(operacion));
			}
			Vector<Token> expresionDer = new Vector<Token>();
			indice2 = referencia + 1;
			while (indice2 != expresion.size()) {
				expresionDer.add(expresion.get(indice2));
				indice2++;
			}

			try {
				raizaux.der = procesaExpresion3(expresionDer);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}

		}
		else if ((indice < expresion.size())
				&& (buscaOperacionCuatroUno(indice, expresion) != -1)) {

			referencia = buscaOperacionCuatroUno(indice, expresion);
			operacion = expresion.get(referencia).getTipoToken();
			if (operacion != null) {
				raizaux.info = new ByteCode(procesaOperacion(operacion));
			}
			Vector<Token> expresionDer = new Vector<Token>();
			indice2 = referencia + 1;
			while (indice2 != expresion.size()) {
				expresionDer.add(expresion.get(indice2));
				indice2++;
			}

			try {
				raizaux.der = procesaExpresion3(expresionDer);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}
		}
		
		
		// Quitamos los () para enviarlo a procesaExpresionRecursivo
		else {
			if ((indice < expresion.size())
					&& ((expresion.get(indice).getTipoToken() == TToken.PA) && 
							//(expresion.get(expresion.size() - 1).getTipoToken() == TToken.PC))) {
							(procesaExpParentesis(expresion,indice)-1==expresion.size()-1))){
				indice++;
				while (indice != (expresion.size() - 1)) {
					expresionSinParent.add(expresion.get(indice));
					indice++;
				}

			} else {
				while (indice != (expresion.size())) {
					expresionSinParent.add(expresion.get(indice));
					indice++;
				}

			}
			// Este While no hace nada, solo compruebo que la expresion que pasamos es correcta
			while (indice2 != (expresionSinParent.size())) {
				operacion = expresionSinParent.get(indice2).getTipoToken();
				indice2++;
			}

			// Método que hara lo mismo que ProcesaExpresion pero devolviendo un
			// Nodo para construir el arbol
			raizaux = procesaExpresionRecursivo(expresionSinParent);
		}
		return raizaux;
	}

	// //////////////////////////////////////////////////////
	// ////////Procesa Expresiones Recursivo/////////////////
	// //////////////////////////////////////////////////////

	private Nodo procesaExpresionRecursivo(Vector<Token> expre) {

		TToken operacion = null;
		Nodo raiz = new Nodo();
		int i = 0;
		int indice = 0;
		int indice2 = 0;
		int referencia;
		int lineaActual = expre.get(indice).getLinea();
		// Nos hacemos un array auxiliar para meter la expresion General
		Vector<Token> expresion = new Vector<Token>();
		while (i < expre.size()) {
			expresion.add(expre.get(i));
			i++;
		}

		// Si encontramos un ( ) nos situamos después de él.
		if (expresion.get(indice).getTipoToken() == TToken.PA) {
			indice = procesaExpParentesis(expresion, indice);
			if (indice == -1) {
				error(expre.get(i).getLinea(), "error en los parentesis");
				return null;
			}
		}

		// Si encontramos un nat,real,... o ident nos situamos después de él.
		if ((indice < expresion.size()) && (procesaTipo(expresion, indice))) {
			if (!(TS.containsKey(expresion.get(indice).getLexema()))
					&& (expresion.get(indice).getTipoToken() == TToken.ident)) {
				error(expresion.get(indice).getLinea(),
						"Se intenta asignar un identificador que no está en TS");
				return null;
			} else {
				indice++;

			}
		}

		// Si encontramos un op0 meter raiz arbol binario
		if ((indice < expresion.size())
				&& (buscaOperacionCero(indice, expresion) != -1)) {
			// Seleccionamos el Op0 y almacenamos su indice en Refenrecia para
			// luego dividir en dos subvectores
			referencia = buscaOperacionCero(indice, expresion);

			operacion = expresion.get(referencia).getTipoToken();

			if (operacion != null) {
				raiz.info = new ByteCode(procesaOperacion(operacion));
			}

			// Nos hacemos dos subarrays de las expresiones de los lados del operador
			Vector<Token> expresionIzq = new Vector<Token>();
			Vector<Token> expresionDer = new Vector<Token>();

			while (indice2 != referencia) {
				expresionIzq.add(expresion.get(indice2));
				indice2++;
			}
			indice2++;
			while (indice2 != expresion.size()) {
				expresionDer.add(expresion.get(indice2));
				indice2++;
			}

			try {
				raiz.izq = procesaExpresion0(expresionIzq);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}

			try {
				raiz.der = procesaExpresion0(expresionDer);
			}

			catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}

		}
		// si no e encontrado ningun Op0
		else {
			try {
				raiz = procesaExpresion0(expresion);
			} catch (Exception e) {
				if (e != null) {
					error(lineaActual, e.getMessage());
					// e.printStackTrace();
					return null;
				}
			}
		}

		return raiz;
	}

	// /////////////Procesa Paréntesis///////////////////////
	// //////////////////////////////////////////////////////

	private int procesaExpParentesis(Vector<Token> v, int i) {

		int parentesisAbiertos = 0; // llegamos con un parentesis abierto.
		int inicio = i;

		while (inicio < v.size()) {
			if (v.get(i).getTipoToken() == TToken.PA)
				parentesisAbiertos++;
			if (v.get(i).getTipoToken() == TToken.PC)
				parentesisAbiertos--;
			if (parentesisAbiertos == 0)
				break;
			else {
				inicio++;
				i++;
			}
		}

		if (parentesisAbiertos != 0)
			i = -1;
		else {
			i++;
		}

		return i;
	}

	// /////////////Procesa Operaciones//////////////////////
	// /////////////Op0,Op1,Op2,Op3//////////////////////////

	private boolean buscaOperacion(Vector<Token> expresion, int indice) {
		while (expresion.get(indice).getTipoToken() != TToken.PC) {
			if (procesaOperacionCero(expresion.get(indice).getTipoToken())
					|| procesaOperacionUno(expresion.get(indice).getTipoToken())
					|| procesaOperacionDos(expresion.get(indice).getTipoToken())
					|| procesaOperacionTres(expresion.get(indice)
							.getTipoToken())) {
				return true;
			}
			indice++;
		}
		return false;
	}

	private boolean procesaOperacionCero(TToken oper0) {

		if ((oper0 == TToken.great || oper0 == TToken.distinto
				|| oper0 == TToken.igualIgual || oper0 == TToken.less
				|| oper0 == TToken.greatEq || oper0 == TToken.lessEq)) {
			return true;
		} else
			return false;

	}

	private int buscaOperacionCero(int indice, Vector<Token> expresion) {

		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}

			if ((expresion.get(indice).getTipoToken() == TToken.great
					|| expresion.get(indice).getTipoToken() == TToken.distinto
					|| expresion.get(indice).getTipoToken() == TToken.igualIgual
					|| expresion.get(indice).getTipoToken() == TToken.less
					|| expresion.get(indice).getTipoToken() == TToken.greatEq || expresion
					.get(indice).getTipoToken() == TToken.lessEq)) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	private boolean procesaOperacionUno(TToken oper1) {

		if ((oper1 == TToken.sum || oper1 == TToken.rest || oper1 == TToken.oLogica)) {
			return true;
		} else
			return false;

	}

	private int buscaOperacionUno(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if ((expresion.get(indice).getTipoToken() == TToken.sum
					|| expresion.get(indice).getTipoToken() == TToken.rest || expresion
					.get(indice).getTipoToken() == TToken.oLogica)) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	private boolean procesaOperacionDos(TToken oper2) {

		if ((oper2 == TToken.mult || oper2 == TToken.div || oper2 == TToken.mod || oper2 == TToken.yLogica)) {
			return true;
		} else
			return false;

	}

	private int buscaOperacionDos(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if ((expresion.get(indice).getTipoToken() == TToken.mult
					|| expresion.get(indice).getTipoToken() == TToken.div
					|| expresion.get(indice).getTipoToken() == TToken.mod || expresion
					.get(indice).getTipoToken() == TToken.yLogica)) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	private boolean procesaOperacionTres(TToken oper3) {

		if ((oper3 == TToken.despIzq || oper3 == TToken.despDer)) {
			return true;
		} else
			return false;

	}

	private int buscaOperacionTres(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if ((expresion.get(indice).getTipoToken() == TToken.despIzq || expresion
					.get(indice).getTipoToken() == TToken.despDer)) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	private int buscaOperacionCuatroDos(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if (expresion.get(indice).getTipoToken() == TToken.negLogica) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	private int buscaOperacionCuatroUno(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if (expresion.get(indice).getTipoToken() == TToken.castChar ||
				expresion.get(indice).getTipoToken() == TToken.castNat ||
				expresion.get(indice).getTipoToken() == TToken.castInt ||
				expresion.get(indice).getTipoToken() == TToken.castFloat) {
				return indice;
			}
			indice++;
		}
		return -1;
	}
	
	
	private int buscaOperacionMenosUnario(int indice, Vector<Token> expresion) {
		while (indice < expresion.size()) {
			if (procesaExpParentesis(expresion, indice) != -1) {

				indice = procesaExpParentesis(expresion, indice) - 1;
			}
			if (expresion.get(indice).getTipoToken() == TToken.negArit) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	/*Método que devuelve true si es una palabra reservada que no se puede utilizar en el 
	 * cuerpo del programa*/
	private boolean esPalReservada(TToken t){
		
		if (t == TToken.var || t == TToken.constante || t == TToken.varsConsts || t == TToken.instrucciones 
				|| t == TToken.program || t == TToken.tipoVarBooleano || t == TToken.tipoVarCaracter 
				|| t == TToken.tipoVarNatural || t == TToken.tipoVarEntero || t == TToken.tipoVarReal){
			
			return true;
		}
		else
			return false;
	}
	
	// ///////////////// ERRORES Y MAIN//////////////////////
	// //////////////////////////////////////////////////////

	public void error(int i, String comentario) {
		// if (comentario == null)
		descripError = "Error en linea " + i + " de tipo: " + comentario + "\n";
		errorCompilacion = true;

	}

	private void procesaRestriccionesContextuales(String tipo, int linea,Vector<Token> v) {
		int i=0;
		boolean encontrado=false;
		if(tipo.equals("tipoVarNatural")){
			while(i<v.size()){
				if(procesaOperacionCero(v.get(i).getTipoToken())){
					descripErrorContextual.add("Error en la linea "+linea+" Operador de tipo0 Asignado a natural");
					i++;
				}
				i++;
			}
			i=0;
			while(i<v.size()){
				if(v.get(i).getTipoToken()==TToken.real||v.get(i).getTipoToken()==TToken.entero||v.get(i).getTipoToken()==TToken.booleano||v.get(i).getTipoToken()==TToken.caracter){
					descripErrorContextual.add("Error en la linea "+linea+" Operando de tipo boolenao o caracter Asignado a natural");
					i++;
					}
				i++;
				}
		}
		else if(tipo.equals("tipoVarReal")){
			while(i<v.size()){
				if(procesaOperacionCero(v.get(i).getTipoToken())){
					descripErrorContextual.add("Error en la linea "+linea+" Operador de tipo0 Asignado a real");
					i++;
					}
				i++;
				}
			i=0;
			while(i<v.size()){
				if(v.get(i).getTipoToken()==TToken.booleano||v.get(i).getTipoToken()==TToken.caracter){
					descripErrorContextual.add("Error en la linea "+linea+" Operando de tipo boolenao o caracter Asignado a real");
					i++;
					}
				i++;
				}
			}
		else if(tipo.equals("tipoVarEntero")){
			while(i<v.size()){
				if(procesaOperacionCero(v.get(i).getTipoToken())){
					descripErrorContextual.add("Error en la linea "+linea+" Operador de tipo0 Asignado a entero");
					i++;
					}
				i++;
			}
			i=0;
			while(i<v.size()){
				if(v.get(i).getTipoToken()==TToken.booleano||v.get(i).getTipoToken()==TToken.caracter){
					descripErrorContextual.add("Error en la linea "+linea+" Operando de tipo boolenao o caracter Asignado a entero");
					i++;
					}
				i++;
				}
			}
		else if(tipo.equals("tipoVarCaracter")){
			while(i<v.size()){
				if(v.get(i).getTipoToken()==TToken.booleano||v.get(i).getTipoToken()==TToken.natural||
						v.get(i).getTipoToken()==TToken.entero||v.get(i).getTipoToken()==TToken.real){
					descripErrorContextual.add("Error en la linea "+linea+" Operando de tipo boolenao,entero,natural o real Asignado a caracter");
					i++;
				}
				i++;
				}
			}
		else if(tipo.equals("tipoVarBooleano")){
			while(i<v.size()){
				if(procesaOperacionCero(v.get(i).getTipoToken())){
					encontrado=true;
					i++;
					}
				i++;
				}
			if(!encontrado){descripErrorContextual.add("Error en la linea "+linea+" Expresión sin operando de tipo0 asignada a booleano");}
			}
	}
	

	private void procesaRestriccionesContextualesExpresion(Vector<Token> expresion) {
	
		//arbol.
		
	}
	
	


	public void printParser() {

		salida+="***********************************************************************\n";
		salida+="*                           LENGUAJE PILA                             *\n";
		salida+="***********************************************************************\n";
		salida += "\n";
		salida += "\n";
		salida +="Resultado\n";
		salida +="---------\n";
		salida += "\n";
		if (!errorCompilacion)
			salida += "La compilacion ha sido satisfactoria." + "\n"
							+ "Fueron leidas " + scanner.getContPrograma()+ " lineas.\n";
		else {
			if (descripError == null)
				salida += "Ocurrierron fallos durante el analisis\n";
			else
				salida +="Ocurrierron fallos durante el analisis:"
						+ "\n" + descripError+ "\n";
		}
		salida += "\n";
		if (!errorCompilacion) {
			salida+="-------------------------------\n";
			salida+="Detalle del analisis sintactico\n";
			salida+="-------------------------------\n";
			salida += "\n";

			printTablaTS();
			printTablaMemoria();

			salida += "\n";
			salida +="*Detalle del lenguaje pila\n";
			salida += "\n";

			for (int i = 0; i < byteOut.size(); i++) {

				if (byteOut.get(i).getDireccion().equals("")) {
					salida +=i + ": "	+ byteOut.get(i).getByteCode().toString()+"\n";
				} else
					salida +=i + ": "
							+ byteOut.get(i).getByteCode().toString() + " "
							+ byteOut.get(i).getDireccion()+"\n";
			}

		}

		if ((descripErrorContextual.size() > 0)) {

			salida += "\n";
			salida +="*Lista de Errores Contextuales\n";
			salida += "--------------------------\n";
			salida += "\n";

			for (int i = 0; i < descripErrorContextual.size(); i++) {
				salida += descripErrorContextual.get(i)+ "\n";
			}

		}

		salida += "\n";
		salida += "\n";

	}

	public void printTablaTS() {
		salida +="*Detalle de la tabla de simbolos\n";
		salida +="--------------------------------\n";
		salida += "\n";

		Iterator it = TS.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			salida +="El identificador '" + e.getKey() + "' \n";
			TablaInfo info = (TablaInfo) e.getValue();
			salida +=info.print()+"\n";

		}

		salida += "\n";
	}

	public void printTablaMemoria() {
		salida +="*Detalle de la tabla de memoria\n";
		salida +="-------------------------------\n";
		salida += "\n";

		Iterator it = dirMemoria.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			salida +="Posicion " + e.getKey() + " : " + e.getValue()+"\n";
		}

		salida += "\n";
	}

	public boolean procesaTipoVariable(Vector<Token> v, int i) {

		if (v.get(i).getTipoToken() == TToken.tipoVarBooleano
				|| v.get(i).getTipoToken() == TToken.tipoVarCaracter
				|| v.get(i).getTipoToken() == TToken.tipoVarNatural
				|| v.get(i).getTipoToken() == TToken.tipoVarEntero
				|| v.get(i).getTipoToken() == TToken.tipoVarReal)
			return true;
		else
			return false;
	}

	private String dameTipoParaBytecode(Vector<Token> expresion, int indice) {
   	 if ((expresion.get(indice).getTipoToken() == TToken.natural)){return "nat";}
   	 else  if ((expresion.get(indice).getTipoToken() == TToken.entero)){return "int";}
   	 else  if ((expresion.get(indice).getTipoToken() == TToken.real)){return "real";}
   	 else  if ((expresion.get(indice).getTipoToken() == TToken.caracter)){return "char";}
   	 else  if ((expresion.get(indice).getTipoToken() == TToken.booleanoCierto)){return "bool";}
   	 else  if ((expresion.get(indice).getTipoToken() == TToken.booleanoFalso)){return "bool";}
   	 else return null;
	}
   

	
	public boolean numNegativo(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.negArit)
			return true;
		else
			return false;
	}

	public boolean procesaFinInstruccion(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.puntoyComa)
			return true;
		else
			return false;
	}

	public boolean procesaIdentificador(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.ident)
			return true;
		else
			return false;
	}

	public void actualizaPunteroMemoriaDatos() {
		if (posMemoLibre < 100) { // mismo numero que la creacion de las tablas hash
			posMemoLibre = posMemoLibre + 1;
		} else {
			salida +="YA NO HAY ESPACIO LIBRE EN LA MEMORIA\n";
			errorCompilacion = true;
		}
	}

	public boolean procesaAsignacion(Vector<Token> v, int i) {
		if (v.get(i).getTipoToken() == TToken.asigConst)
			return true;
		else
			return false;
	}

	public boolean correspondenciaDeTipos(Vector<Token> v, String t, int i) {

		if (t == "tipoVarBooleano") {
			if (v.get(i).getTipoToken().toString() == "booleanoFalso"
					|| v.get(i).getTipoToken().toString() == "booleanoCierto")
				return true;
		}

		else if (t == "tipoVarCaracter") {
			if (v.get(i).getTipoToken().toString() == "caracter")
				return true;
		}

		else if (t == "tipoVarNatural") {
			// por si acaso metemos un numero negativo al natural!!
			if (v.get(i - 1).getTipoToken().toString() != "negArit"
					&& v.get(i).getTipoToken().toString() == "natural")
				return true;
		}

		else if (t == "tipoVarEntero") {
			if (v.get(i).getTipoToken().toString() == "entero"
					|| v.get(i).getTipoToken().toString() == "natural")
				return true;
		}

		else if (t == "tipoVarReal") {
			if (v.get(i).getTipoToken().toString() == "entero"
					|| v.get(i).getTipoToken().toString() == "natural"
					|| v.get(i).getTipoToken().toString() == "real")
				return true;
		}

		return false;
	}

	public boolean procesaTipo(Vector<Token> expresion, int indice) {
		if ((expresion.get(indice).getTipoToken() == TToken.natural
				|| expresion.get(indice).getTipoToken() == TToken.entero
				|| expresion.get(indice).getTipoToken() == TToken.real
				|| expresion.get(indice).getTipoToken() == TToken.caracter
				|| expresion.get(indice).getTipoToken() == TToken.booleanoCierto
				|| expresion.get(indice).getTipoToken() == TToken.booleanoFalso || expresion
				.get(indice).getTipoToken() == TToken.ident))
			return true;
		else
			return false;
	}

	public tByteCode procesaOperacion(TToken operacion) {

		switch (operacion) {
		case igualIgual:
			return tByteCode.igual;
		case great:
			return tByteCode.mayor;
		case less:
			return tByteCode.menor;
		case greatEq:
			return tByteCode.mayorigual;
		case lessEq:
			return tByteCode.menorigual;
		case distinto:
			return tByteCode.distinto;
		case sum:
			return tByteCode.suma;
		case rest:
			return tByteCode.resta;
		case div:
			return tByteCode.divide;
		case mult:
			return tByteCode.multiplica;
		case mod:
			return tByteCode.modulo;
		case oLogica:
			return tByteCode.or;
		case yLogica:
			return tByteCode.and;
		case despIzq:
			return tByteCode.desplazamientoizquierda;
		case despDer:
			return tByteCode.desplazamientoderecha;
		case negLogica:
			return tByteCode.negacionlogica;
		case negArit:
			return tByteCode.restaunitaria;
		case castChar:
			return tByteCode.cchar;
		case castInt:
			return tByteCode.cint;
		case castFloat:
			return tByteCode.cfloat;
		case castNat:
			return tByteCode.cnat;
		}
		return null;
	}
	
	public Vector<ByteCode> getByteOut() {
		return this.byteOut;
	}


	public String getSalida() {
		return salida;
	}
	
	// MAAAAAAIIIIIINNNNNN_____________________________

	public static void main(String[] args) {

		String nombreFichero = "src/aLexico/ejemplos/ejemplo2.txt";
		AnalizadorSintactico sintetiza = new AnalizadorSintactico(nombreFichero);
		sintetiza.compilar();
		sintetiza.printParser();
		System.out.println(salida);

	}


}
