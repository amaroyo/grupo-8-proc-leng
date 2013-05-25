package src.plg.proto;

import src.es.ucm.fdi.plg.evlib.Atribucion;
import src.es.ucm.fdi.plg.evlib.Atributo;
import src.es.ucm.fdi.plg.evlib.LAtributo;
import src.es.ucm.fdi.plg.evlib.SemFun;
import src.es.ucm.fdi.plg.evlib.TAtributos;
import java.util.HashMap;
import java.util.Map;




// Definición de las funciones semánticas



class creaTS implements SemFun{

    @Override
    public Object eval(Atributo... args) {
        return args[0].valor();
    }

}



class Ejemplo implements SemFun {    
   public Object eval(Atributo ... args) {
         return (Integer)args[0].valor()+(Integer)args[1].valor();
      }     
   }
class Asignacion implements SemFun{

    @Override
    public Object eval(Atributo... args) {
        return args[0].valor();
    }

}
class AsignaCero implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
    	
        return 0;
    }
}

class SumaUno implements SemFun{
    	

        @Override
        public Object eval(Atributo... args) {
        	
            return (Integer) args[0].valor()+1;
        }
}
        
class SumaDos implements SemFun{
        	

        @Override
        public Object eval(Atributo... args) {
            	
            return (Integer) args[0].valor()+2;
        }
}
class SumaTres implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return (Integer) args[0].valor()+3;
    }
}
class asignaFalso implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return " apila_falso() ";
    }
}

class asignaCierto implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return " apila_cierto() ";
    }
}

class concatenarIR1 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        int n=(Integer) args[1].valor()+1;
        s= args[0].valor()+" ir_f("+n+") "+args[2].valor()+" ir_a("+args[3].valor()+") "+args[4].valor();
        return s;
    }
}

class concatenarIR2 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= " apila_str("+args[0].valor()+") "+" escribe ";
        return s;
    }
}

class concatenarER1 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= args[0].valor()+" copia "+" ir_v("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}


class concatenarTR1 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}
public class EAtribucion extends Atribucion {    
    
    // Se crean los objetos que representan las diferentes funciones semánticas
	private static SemFun creaTS = new creaTS();
    private static SemFun asignacion = new Asignacion();
    private static SemFun asignacero = new AsignaCero();
    private static SemFun asignacierto = new asignaCierto();
    private static SemFun asignafalso = new asignaFalso();
    
    private static SemFun concatenarIR1 = new concatenarIR1();
    private static SemFun concatenarIR2 = new concatenarIR2();
    private static SemFun concatenarER1 = new concatenarER1();
    private static SemFun concatenarTR1 = new concatenarTR1();
  //  private static SemFun addVal = new AddVal();
  //  private static SemFun initT = new InitT();
    
    private static SemFun sumauno = new SumaUno();
    private static SemFun sumaDos = new SumaDos();
    private static SemFun sumaTres = new SumaTres();
    
   
    
    //PRACTICA PLG 2º CUATRI!!!!!!
    public TAtributos Programa(TAtributos Consts,TAtributos Tipos,TAtributos Vars,TAtributos Subprogramas,TAtributos Insts){
        regla("Programa → program: ident { Consts Tipos Vars Subprogramas instructions { Insts }}");
                
        TAtributos Programa = atributosPara("Programa", "TS","err","cod");//etq,etqh??
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Consts.a("TSH"),Programa.a("TS"));//Consts.TSH = Programa.TS
        dependencias(Tipos.a("TSH"),Consts.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Vars.a("TSH"),Tipos.a("TS"));//Vars.TSH = Tipos.TS
        dependencias(Subprogramas.a("TSH"),Vars.a("TS"));//Subprogramas.TSH = Vars.TS
        dependencias(Insts.a("TSH"),Subprogramas.a("TS"));//Insts.TSH = Subprogramas.TS         
       // dependencias(e.a("irfh"),e.a("etq"));//Programa.TPR= CreaTPR()
        dependencias(Consts.a("TPRH"),Programa.a("TPR"));//Consts.TPRH = Programa.TPR
        dependencias(Tipos.a("TPRH"),Consts.a("TPRH"));//Tipos.TPRH = Consts.TPRH
        dependencias(Vars.a("TPRH"),Tipos.a("TPRH"));//Vars.TPRH = Tipos.TPRH
        dependencias(Subprogramas.a("TPRH"),Vars.a("TPRH"));//Subprogramas.TPRH = Vars.TPRH
        dependencias(Programa.a("err"),Consts.a("err"),Tipos.a("err"),Vars.a("err"),Subprogramas.a("err"),Insts.a("err"));//Programa.err = Consts.err OR  Tipos.err OR Vars.err OR Subprogramas.err OR Insts.err
        dependencias(Programa.a("cod"),Insts.a("cod"));//Prog.cod=Insts.cod || stop
        
        calculo(Programa.a("TS"),creaTS);
        calculo(Programa.a("cod"),concatenarIR1);
        calculo(Consts.a("TSH"),asignacion);
        calculo(Tipos.a("TSH"),asignacion);
        calculo(Vars.a("TSH"),asignacion);
        calculo(Subprogramas.a("TSH"),asignacion);
        calculo(Insts.a("TSH"),asignacion);
        
        calculo(Programa.a("cod"),concatenarIR1);
        calculo(Consts.a("TPRH"),asignacion);
        calculo(Tipos.a("TPRH"),asignacion);
        calculo(Vars.a("TPRH"),asignacion);
        calculo(Subprogramas.a("TPRH"),asignacion);
        calculo(Programa.a("err"),asignacion);
        return Programa;
        
    }
  
    public TAtributos Consts1(TAtributos Decs){
        regla("Consts → consts { Decs }");
                
        TAtributos Consts1 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Consts1.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Consts1.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Consts1.a("TSH"));//Vars.TSH = Tipos.TS
        dependencias(Consts1.a("err"),Decs.a("err"));//Subprogramas.TSH = Vars.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Consts1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Consts1.a("err"),asignacion);
        
        return Consts1;
        
    }
    
    public TAtributos Consts2(TAtributos Decs){
        regla("Consts → λ ");
                
        TAtributos Consts2 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Consts2.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Consts2.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Consts2.a("TSH"));//Vars.TSH = Tipos.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Consts2.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Consts2.a("err"),asignafalso);
        
        return Consts2;
        
    }
    
    public TAtributos Tipos1(TAtributos Decs){
        regla("Tipos → tipos { Decs } ");
                
        TAtributos Tipos1 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Tipos1.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Tipos1.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Tipos1.a("TSH"));//Vars.TSH = Tipos.TS
        dependencias(Tipos1.a("err"),Decs.a("err"));//Subprogramas.TSH = Vars.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Tipos1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Tipos1.a("err"),asignacion);
        
        return Tipos1;
        
    }
    
    public TAtributos Tipos2(TAtributos Decs){
        regla("Tipos → λ ");
                
        TAtributos Tipos2 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Tipos2.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Tipos2.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Tipos2.a("TSH"));//Vars.TSH = Tipos.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Tipos2.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Tipos2.a("err"),asignafalso);
        
        return Tipos2;
        
    }
    
    
    public TAtributos Vars1(TAtributos Decs){
        regla("Vars→  vars { Decs }");
                
        TAtributos Vars1 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Vars1.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Vars1.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Vars1.a("TSH"));//Vars.TSH = Tipos.TS
        dependencias(Vars1.a("err"),Decs.a("err"));//Subprogramas.TSH = Vars.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Vars1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Vars1.a("err"),asignacion);
        
        return Vars1;
        
    }
    
    public TAtributos Vars2(TAtributos Decs){
        regla("Vars→  λ");
                
        TAtributos Vars2 = atributosPara("Consts", "TS","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Vars2.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Vars2.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Vars2.a("TSH"));//Vars.TSH = Tipos.TS

        calculo(Decs.a("TPRH"),asignacion);
        calculo(Vars2.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Vars2.a("err"),asignafalso);
        
        return Vars2;
        
    }
    
    
    
    
    
    
    
    
    
    //========================================================================
    
    
    
    public TAtributos sR1(TAtributos i){
        regla("S -> I");
        
        TAtributos s = atributosPara("S", "cod");
        dependencias(s.a("cod"), i.a("cod"));

        calculo(i.a("etqh"), asignacero);
        calculo(s.a("cod"), asignacion);
       
        return s;
    }
    
    public TAtributos iR1(TAtributos e,TAtributos i1, TAtributos i2){
        regla("I -> if E then I else I fi");
                
        TAtributos i0 = atributosPara("I", "cod","etq","etqh","irvh","irfh");
        dependencias(i0.a("cod"),e.a("cod"),i1.a("etq"),i1.a("cod"),i2.a("etq"),i2.a("cod"));
        dependencias(e.a("etqh"),i0.a("etqh"));
        dependencias(i1.a("etqh"),e.a("etq"));
        dependencias(i2.a("etqh"),i1.a("etq"));
        dependencias(i0.a("etq"),i2.a("etq"));
        dependencias(e.a("irvh"),e.a("etq"));
        dependencias(e.a("irfh"),e.a("etq"));
        
        calculo(i1.a("etqh"),sumauno);
        calculo(i2.a("etqh"),sumauno);
        calculo(i0.a("cod"),concatenarIR1);
        calculo(i0.a("etq"),asignacion);
        calculo(e.a("etqh"),asignacion);
        calculo(e.a("irvh"),asignacion);
        calculo(e.a("irfh"),asignacion);
        
        
        return i0;
        
    }
    
   public TAtributos iR2(String str) {
       
       regla("I -> write string");
       
       Atributo string = atributoLexicoPara("String", "lex", str);
       TAtributos i = atributosPara("I", "cod", "etq","etqh");
     
       dependencias(i.a("cod"), string);
       dependencias(i.a("etq"),i.a("etqh"));
       
       calculo(i.a("etq"),sumaDos);
       calculo(i.a("cod"),concatenarIR2);
       
       return i;
   }
   
   
   public TAtributos eR1(TAtributos e1, TAtributos t) {
       
       regla("E -> E or T");
       
     
       TAtributos e0 = atributosPara("E", "cod","etq","etqh","irvh","irfh");
       
       dependencias(e0.a("cod"),e1.a("cod"),e0.a("irvh"),t.a("cod"));
       dependencias(e1.a("etqh"),e0.a("etqh"));
       dependencias(t.a("etqh"),e1.a("etq"));
       dependencias(e0.a("etq"),t.a("etq"));
       dependencias(e1.a("irvh"),e0.a("irvh"));
       dependencias(e1.a("irfh"),e1.a("etq"));
       dependencias(t.a("irvh"),e0.a("irvh"));
       dependencias(t.a("irfh"),e0.a("irfh"));
       
       
       calculo(e0.a("cod"),concatenarER1);
       calculo(e1.a("etqh"),asignacion);
       calculo(t.a("etqh"),sumaTres);
       calculo(e0.a("etq"),asignacion);
       calculo(e1.a("irvh"),asignacion);
       calculo(e1.a("irfh"),sumaDos);
       calculo(t.a("irvh"),asignacion);
       calculo(t.a("irfh"),asignacion);

       return e0;
   }
   
public TAtributos eR2(TAtributos t) {
       
        
       regla("E -> T"); 
       
       TAtributos e = atributosPara("E", "cod", "etq","etqh","irvh","irfh");

       dependencias(e.a("cod"),t.a("cod"));
       dependencias(t.a("etqh"),e.a("etqh"));
       dependencias(e.a("etq"),t.a("etq"));
       dependencias(t.a("irvh"),e.a("irvh"));
       dependencias(t.a("irfh"),e.a("irfh"));
       
       calculo(e.a("cod"),asignacion);
       calculo(e.a("etq"),asignacion);
       calculo(t.a("etqh"),asignacion);
       calculo(t.a("irvh"),asignacion);
       calculo(t.a("irfh"),asignacion);
       
       return e;
   }

public TAtributos tR1(TAtributos t1, TAtributos f) {
   
   regla("T -> T and F");
   
   TAtributos t0 = atributosPara("E", "cod","etq","etqh","irvh","irfh");
    
   dependencias(t0.a("cod"),t1.a("cod"),t0.a("irfh"),f.a("cod"));
   dependencias(t1.a("etqh"),t0.a("etqh"));
   dependencias(f.a("etqh"),t1.a("etq"));
   dependencias(t0.a("etq"),f.a("etq"));
   dependencias(t1.a("irvh"),t1.a("etq"));
   dependencias(t1.a("irfh"),t0.a("irfh"));
   dependencias(f.a("irvh"),t0.a("irvh"));
   dependencias(f.a("irfh"),t0.a("irfh"));
   
   
   calculo(t0.a("cod"),concatenarTR1);
   calculo(t1.a("etqh"),asignacion);
   calculo(f.a("etqh"),sumaTres);
   calculo(t0.a("etq"),asignacion);
   calculo(t1.a("irvh"),asignacion);
   calculo(t1.a("irfh"),sumaDos);
   calculo(f.a("irvh"),asignacion);
   calculo(f.a("irfh"),asignacion);
   
   
   return t0;
}

public TAtributos tR2(TAtributos f) {
   
   regla("T -> F");
   
   TAtributos t = atributosPara("T", "cod", "etq","etqh","irvh","irfh");
   
   dependencias(t.a("cod"),f.a("cod"));
   dependencias(f.a("etqh"),t.a("etqh"));
   dependencias(t.a("etq"),f.a("etq"));
   dependencias(f.a("irvh"),t.a("irvh"));
   dependencias(f.a("irfh"),t.a("irfh"));
   
   calculo(t.a("cod"),asignacion);
   calculo(t.a("etq"),asignacion);
   calculo(f.a("etqh"),asignacion);
   calculo(f.a("irvh"),asignacion);
   calculo(f.a("irfh"),asignacion);

   return t;
}

public TAtributos fR1(String CIERTO) {
   
   regla("F -> CIERTO");
   
   TAtributos f = atributosPara("F", "cod", "etq","etqh","irvh","irfh");
   
   dependencias(f.a("etq"),f.a("etqh"));
   
   calculo(f.a("cod"),asignacierto);
   calculo(f.a("etq"),sumauno);
   
   return f;
}

public TAtributos fR2(String FALSO) {
   
   regla("F -> FALSO");
   
   TAtributos f = atributosPara("F", "cod", "etq","etqh","irvh","irfh");
   
   dependencias(f.a("etq"),f.a("etqh"));
   
   calculo(f.a("cod"),asignafalso);
   calculo(f.a("etq"),sumauno);
   
   return f;
}

public TAtributos fR3(TAtributos e) {
   

   regla("F -> ( E )");
   
   TAtributos f = atributosPara("F", "cod", "etq","etqh","irvh","irfh");
 
   dependencias(f.a("cod"),e.a("cod"));
   dependencias(e.a("etqh"),f.a("etqh"));
   dependencias(f.a("etq"),e.a("etq"));
   dependencias(e.a("irvh"),f.a("irvh"));
   dependencias(e.a("irfh"),f.a("irfh"));
   
   calculo(f.a("cod"),asignacion);
   calculo(e.a("etqh"),asignacion);
   calculo(f.a("etq"),asignacion);
   calculo(e.a("irvh"),asignacion);
   calculo(e.a("irfh"),asignacion);
   
   return f;
}
      
}