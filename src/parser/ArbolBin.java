package parser;

import aLexico.TToken;

public class ArbolBin {

      Nodo raiz;

      public ArbolBin() {
          raiz=null;
      }
      
      public void insertarNodo(Nodo x) {
          raiz=x;
          raiz.izq=null;
          raiz.der=null;
      }
      
      public void insertarIzq (Nodo info)
      {
          raiz.izq=info;
   
      }
      public void insertarDer (Nodo info)
      {
          raiz.der=info;
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