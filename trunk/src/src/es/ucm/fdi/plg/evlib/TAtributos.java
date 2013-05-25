package src.es.ucm.fdi.plg.evlib;

import java.util.Map;
import java.util.HashMap;

public class TAtributos {
   private Map<String,SAtributo> atributos;   
   public TAtributos() {
     atributos = new HashMap<String, SAtributo>();
   }
   public void ponAtributo(String nombre, SAtributo a) {
     atributos.put(nombre,a);  
   }
   public SAtributo a(String a) {
     return atributos.get(a);  
   }
}
