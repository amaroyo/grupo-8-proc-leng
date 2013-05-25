package src.es.ucm.fdi.plg.evlib;

public class Atribucion {
    private String regla;
    public void regla(String regla) {
        this.regla = regla;
    }
    public TAtributos atributosPara(String simbolo, String ... as) {
       TAtributos tas = new TAtributos();
       for (String an: as) {
          tas.ponAtributo(an,new SAtributo(regla+"||"+simbolo+"."+an)); 
       }
       return tas;
    }
    public LAtributo atributoLexicoPara(String simbolo, String an, Object val) {
        return new LAtributo(regla+"||"+simbolo+"."+an,val);        
    }
    public void dependencias(SAtributo a, Atributo ... usados) {
        a.ponDependencias(usados);
    }
    public void calculo(SAtributo a, SemFun semfun) {
       a.ponSemFun(semfun); 
    }
    public static void activaDebug() {
       Atributo.fijaDebug(true); 
    }
}

   