package src.plg.proto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class TPR {
	
	private static Vector<String> TPR;

	
	public TPR() {
		this.TPR = new Vector<String>();
		iniciaVecPalReservadas();
	}

	public static boolean buscaId(String id) {
		int i=0;
		while(i<TPR.size()){
			 if(TPR.get(i).equals(id)){
				 return true;
			 }
			i++; 
		}
   return false;
	}

	

    public void iniciaVecPalReservadas() {
		TPR.clear();
		
		TPR.add("var");
		TPR.add("vars");
		TPR.add("const");
		TPR.add("consts");
		TPR.add("tipos");
		TPR.add("tipo");
		TPR.add("subprogram");
		TPR.add("subprograms");
		TPR.add("instructions");
		TPR.add("program");
		TPR.add("swap1");
		TPR.add("swap2");
		TPR.add("e");
		TPR.add("E");
		
		//tipos
		TPR.add("boolean");
		TPR.add("character");
		TPR.add("natural");
		TPR.add("integer");
		TPR.add("float");
		
		//Valores booleanos
		TPR.add("true");
		TPR.add("false");
		
		//Operadores booleanos
		TPR.add("or");
		TPR.add("and");
		TPR.add("not");
		
		//Operadores de entrada / salida
		TPR.add("in");
		TPR.add("out");
		
		//Operadores de casting
		TPR.add("char");
		TPR.add("nat");
		TPR.add("int");
		
		//Instrucciones
		TPR.add("if");
		TPR.add("then");
		TPR.add("else");
		TPR.add("endif");
		
		TPR.add("while");
		TPR.add("do");
		TPR.add("endwhile");
		TPR.add("call");

	}
	
}
