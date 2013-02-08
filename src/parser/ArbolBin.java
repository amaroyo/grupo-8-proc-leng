package parser;

import java.util.Vector;

import aLexico.TToken;

public class ArbolBin {

      Nodo raiz;

      public ArbolBin() {
          raiz=null;
      }
      
      public void insertarNodo(Nodo x) {
          raiz=x;
      }
      
      public void insertarIzq (Nodo info)
      {
          raiz.izq=info;
   
      }
      public void insertarDer (Nodo info)
      {
          raiz.der=info;
      }
      
    
  public void posorden(Nodo x, Vector<ByteCode> byteOut)
      {
    	  if (raiz!=null)
    	  {
    	  posorden(raiz.izq,byteOut);
    	  posorden(raiz.der,byteOut);
    	  byteOut.add(raiz.info);
    	  }
    }

 
  private void imprimirPost (Nodo reco)
      {
          if (reco != null)
          {
              imprimirPost (reco.izq);
              imprimirPost (reco.der);
              System.out.print(reco.info + " ");
          }
      }


      public void imprimirPost ()
      {
          imprimirPost (raiz);
          System.out.println();
      }
}