package src.plg.proto;

import java.io.FileInputStream;

import src.es.ucm.fdi.plg.evlib.TAtributos;

import java_cup.parser;
import java_cup.runtime.*;


public class Main {
  public static void main(String[] args) throws Exception {   
      Parser p = new Parser(new Scanner(new FileInputStream("input.txt")),new DefaultSymbolFactory());
      Symbol s = p.parse();
      System.out.println(((TAtributos)s.value).a("cod").valor());
  }
}
