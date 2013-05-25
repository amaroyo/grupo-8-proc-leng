package src.es.ucm.fdi.plg.evlib;

import java.util.LinkedList;
import java.util.List;


public class SAtributo extends Atributo {
   private Atributo[] dependeDe;
   private SemFun semfun;
   private Object valor;   
   private boolean calculado;
   public SAtributo() {
     this("");  
   }
   public SAtributo(String doc) {
     super(doc);  
     this.valor = null;
     this.calculado = false;
   }
   public Object valor() {
      if (! calculado) {
         valor = semfun.eval(dependeDe);
         debug(valor);
         calculado = true;
      }
      return valor;
   }
   public void fijaValor(Object valor) {
     this.valor = valor;
     calculado = true;
   }   
   public boolean calculado() {return calculado;}

   public void ponDependencias(Atributo ... as) {
      dependeDe = as;
   }   
   public void ponSemFun(SemFun semfun) {
     this.semfun = semfun; 
   }
}

 