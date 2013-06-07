package src.plg.proto;

import java.io.FileInputStream;
import java.util.ArrayList;

import src.plg.proto.Parser;
import src.plg.proto.Scanner;

import src.es.ucm.fdi.plg.evlib.TAtributos;

import java_cup.parser;
import java_cup.runtime.*;


public class Main {
  public static void main(String[] args) throws Exception {   
     // Parser p = new Parser(new Scanner(new FileInputStream("input.txt")),new DefaultSymbolFactory());
      //Symbol s = p.parse();
      //System.out.println(((TAtributos)s.value).a("cod").valor());
  //}
	  try {
	      Parser p = new Parser(new Scanner(new FileInputStream("input.txt")),new DefaultSymbolFactory());
	      Symbol symbol = p.parse();
	      TAtributos prog = (TAtributos) symbol.value;
	      ArrayList<String> cod = (ArrayList<String>) prog.a("testError").valor();
	      System.out.println(cod);
//	      TablaSimbolos ts = (TablaSimbolos)prog.a("testTs").valor();
//	      System.out.println(ts.devuelveTabla());
		  } catch (Error e) {
			  System.err.print(e.getMessage());
		  }
	  }
}
