package parser;

import aLexico.TToken;

public class ArbolBin {

    class Nodo
      {
    	ByteCode info;
        Nodo izq, der;
      }
      Nodo raiz;

      public ArbolBin(ByteCode x) {
          raiz.info=x;
          raiz.izq=null;
          raiz.der=null;
      }
      
      public void insertarIzq (ByteCode info)
      {
          Nodo nuevo;
          nuevo = new Nodo ();
          nuevo.info = info;
          raiz.izq=nuevo;
   
      }
      public void insertarDer (ByteCode info)
      {
          Nodo nuevo;
          nuevo = new Nodo ();
          nuevo.info = info;
          raiz.der=nuevo;
   
      }
      
      
      
   /*   
      public void insertar (TToken info)
      {
          Nodo nuevo;
          nuevo = new Nodo ();
          nuevo.info = info;
          nuevo.izq = null;
          nuevo.der = null;
          if (raiz == null)
              raiz = nuevo;
          else
          {
              Nodo anterior = null, reco;
              reco = raiz;
              while (reco != null)
              {
                  anterior = reco;
                  if (info < reco.info)
                      reco = reco.izq;
                  else
                      reco = reco.der;
              }
              if (info < anterior.info)
                  anterior.izq = nuevo;
              else
                  anterior.der = nuevo;
          }
      }

*/
      private void imprimirPre (Nodo reco)
      {
          if (reco != null)
          {
              System.out.print(reco.info + " ");
              imprimirPre (reco.izq);
              imprimirPre (reco.der);
          }
      }

      public void imprimirPre ()
      {
          imprimirPre (raiz);
          System.out.println();
      }

      private void imprimirEntre (Nodo reco)
      {
          if (reco != null)
          {    
              imprimirEntre (reco.izq);
              System.out.print(reco.info + " ");
              imprimirEntre (reco.der);
          }
      }

      public void imprimirEntre ()
      {
          imprimirEntre (raiz);
          System.out.println();
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