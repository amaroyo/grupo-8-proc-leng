package src.es.ucm.fdi.plg.evlib;

public abstract class Atributo {
   private static boolean DEBUG=true;   
   public static void fijaDebug(boolean debug) {DEBUG=debug;}   
      
   private String contexto; 
   
   protected Atributo(String contexto) {
       this.contexto = contexto;  
   }
   public void debug(Object valor) {
      if (DEBUG) System.out.println(contexto+"="+valor);  
   }
   public abstract Object valor();
   public abstract boolean calculado();
}


