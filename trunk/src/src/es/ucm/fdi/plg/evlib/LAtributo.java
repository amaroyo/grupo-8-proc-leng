package src.es.ucm.fdi.plg.evlib;

public class LAtributo extends Atributo {
   private Object valor;
   public LAtributo(String contexto, Object valor) {
     super(contexto);  
     this.valor = valor;  
     debug(valor);
   }
   @Override
   public Object valor() {return valor;}
   @Override
   public boolean calculado() {return true;} 
}
