package src.plg.proto;

import src.es.ucm.fdi.plg.evlib.Atribucion;
import src.es.ucm.fdi.plg.evlib.Atributo;
import src.es.ucm.fdi.plg.evlib.LAtributo;
import src.es.ucm.fdi.plg.evlib.SemFun;
import src.es.ucm.fdi.plg.evlib.TAtributos;
import java.util.HashMap;
import java.util.Map;




// DefiniciÃ³n de las funciones semÃ¡nticas



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
class AsignacionOR2 implements SemFun{

    @Override
    public Object eval(Atributo... args) {
        return args[0].valor() ;//|| args[1].valor() ;
    }

}
class AsignaCero implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
    	
        return 0;
    }
}

class AsignaUno implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
    	
        return 1;
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

class concatenar2 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= args[0].valor() +""+ args[1].valor();
        return s;
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
    
    // Se crean los objetos que representan las diferentes funciones semÃ¡nticas
	private static SemFun creaTS = new creaTS();
    private static SemFun asignacion = new Asignacion();
    private static SemFun asignacionOR2 = new AsignacionOR2();
    private static SemFun asignacero = new AsignaCero();
    private static SemFun asignauno = new AsignaUno();
    private static SemFun asignacierto = new asignaCierto();
    private static SemFun asignafalso = new asignaFalso();
    
    private static SemFun concatenar2 = new concatenar2();
    private static SemFun concatenarIR1 = new concatenarIR1();
    private static SemFun concatenarIR2 = new concatenarIR2();
    private static SemFun concatenarER1 = new concatenarER1();
    private static SemFun concatenarTR1 = new concatenarTR1();
  //  private static SemFun addVal = new AddVal();
  //  private static SemFun initT = new InitT();
    
    private static SemFun sumauno = new SumaUno();
    private static SemFun sumaDos = new SumaDos();
    private static SemFun sumaTres = new SumaTres();
    
   
    
    //PRACTICA PLG 2Âº CUATRI!!!!!!
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
  
    public TAtributos Consts0(TAtributos Decs){
        regla("Consts → consts { Decs }");
                
        TAtributos Consts0 = atributosPara("Consts", "TS","TSH","TPRH","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Consts0.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Consts0.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Consts0.a("TSH"));//Vars.TSH = Tipos.TS
        dependencias(Consts0.a("err"),Decs.a("err"));//Subprogramas.TSH = Vars.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Consts0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Consts0.a("err"),asignacion);
        
        return Consts0;
        
    }
    
    public TAtributos Consts1(TAtributos Decs){
        regla("Consts →  λ ");
                
        TAtributos Consts1 = atributosPara("Consts", "TS","TSH","TPRH","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Consts1.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Consts1.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Consts1.a("TSH"));//Vars.TSH = Tipos.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Consts1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Consts1.a("err"),asignafalso);
        
        return Consts1;
        
    }
    
    public TAtributos Tipos0(TAtributos Decs){
        regla("Tipos → tipos { Decs } ");
                
        TAtributos Tipos0 = atributosPara("Tipos", "TS","TSH","TPRH","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Tipos0.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Tipos0.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Tipos0.a("TSH"));//Vars.TSH = Tipos.TS
        dependencias(Tipos0.a("err"),Decs.a("err"));//Subprogramas.TSH = Vars.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Tipos0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Tipos0.a("err"),asignacion);
        
        return Tipos0;
        
    }
    
    public TAtributos Tipos1(TAtributos Decs){
        regla("Tipos →  λ ");
                
        TAtributos Tipos1 = atributosPara("Tipos", "TS","TSH","TPRH","err");
        
        //FALTA  Programa.TS = CreaTS()
        dependencias(Decs.a("TPRH"),Tipos1.a("TPRH"));//Consts.TSH = Programa.TS
        dependencias(Tipos1.a("TS"),Decs.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Decs.a("TSH"),Tipos1.a("TSH"));//Vars.TSH = Tipos.TS
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Tipos1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Tipos1.a("err"),asignafalso);
        
        return Tipos1;
        
    }
    
    
    public TAtributos Vars0(TAtributos Decs){
        regla("Vars →  vars { Decs }");
                
        TAtributos Vars0 = atributosPara("Vars", "TS","TSH","TPRH","err");
        

        dependencias(Decs.a("TPRH"),Vars0.a("TPRH"));
        dependencias(Vars0.a("TS"),Decs.a("TS"));
        dependencias(Decs.a("TSH"),Vars0.a("TSH"));
        dependencias(Vars0.a("err"),Decs.a("err"));
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Vars0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Vars0.a("err"),asignacion);
        
        return Vars0;
        
    }
    
    public TAtributos Vars1(TAtributos Decs){
        regla("Vars →   λ");
                
        TAtributos Vars1 = atributosPara("Vars", "TS","TSH","TPRH","err");
        
        
        dependencias(Decs.a("TPRH"),Vars1.a("TPRH"));
        dependencias(Vars1.a("TS"),Decs.a("TS"));
        dependencias(Decs.a("TSH"),Vars1.a("TSH"));

        calculo(Decs.a("TPRH"),asignacion);
        calculo(Vars1.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Vars1.a("err"),asignafalso);
        
        return Vars1;
        
    }
    
    public TAtributos Decs0(TAtributos Decs,TAtributos Dec){
        regla(" Decs → Decs ; Dec ");
                
        TAtributos Decs0 = atributosPara("Decs","TS","TSH","TPRH","dir","err");
        
     
        // FALTA DECS.TS = AÃ‘ADIr....
        // FALTA ERR
        dependencias(Decs.a("TPRH"),Decs0.a("TPRH"));
        dependencias(Dec.a("TPRH"),Decs.a("TPRH"));
        dependencias(Decs.a("TSH"),Decs0.a("TSH"));
        dependencias(Dec.a("TSH"),Decs.a("TSH"));
        dependencias(Decs0.a("err"),Decs.a("err"));
        dependencias(Decs0.a("dir"),Decs.a("dir"));
        
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs0.a("dir"),sumauno);
        
        return Decs0;
        
    }
    
    public TAtributos Decs1(TAtributos Dec){
        regla("Decs → Dec");
                
        TAtributos Decs1 = atributosPara("Decs", "TS","TSH","TPRH","dir","err");
        
        // FALTA DECS.TS = AÃ‘ADIr....
        // FALTA ERR
        dependencias(Dec.a("TPRH"),Decs1.a("TPRH"));
        dependencias(Dec.a("TSH"),Decs1.a("TSH"));
        dependencias(Decs1.a("err"),Dec.a("err"));
       
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs1.a("dir"),asignacero);
        
        return Decs1;
        
    }
    
    public TAtributos Dec0(TAtributos Tipo,TAtributos ident){
        regla("Dec → const Tipo ident = Valores");
                
        TAtributos Dec0 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err");
        
        //FALTA ERROR
        dependencias(Dec0.a("id"),ident.a("lex"));
        dependencias(Dec0.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),Dec0.a("TSH"));
        dependencias(Tipo.a("TPRH"),Dec0.a("TPRH"));
        dependencias(Dec0.a("err"),Tipo.a("err"));
        
        calculo(Dec0.a("id"),asignacion);
      //  calculo(Dec0.a("clase"),asignacero);
        calculo(Dec0.a("nivel"),asignacero);
        calculo(Dec0.a("tipo"),asignacion);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
      
    //  calculo(Dec0.a("err"),asignacion....OR...);
        
        return Dec0;
        
    }
    
    public TAtributos Dec1(TAtributos Tipo,TAtributos ident){
        regla("Dec → tipo Tipo ident");
                
        TAtributos Dec1 = atributosPara("Dec","TSH","TPRH","id","clase","niv","tipo","err");
        
        dependencias(Dec1.a("id"),ident.a("lex"));
        dependencias(Dec1.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),Dec1.a("TSH"));
        dependencias(Tipo.a("TPRH"),Dec1.a("TPRH"));
        dependencias(Dec1.a("err"),Tipo.a("err"));
        
        calculo(Dec1.a("id"),asignacion);
      //  calculo(Dec1.a("clase"),asignacero);
        calculo(Dec1.a("nivel"),asignacero);
        calculo(Dec1.a("tipo"),asignacion);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(Dec1.a("err"),asignacion);
        
        return Dec1;
        
    }
    
    
    public TAtributos Dec2(TAtributos Tipo,TAtributos Designador){
        regla("Dec → var Tipo Designador");
                
        TAtributos Dec2 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err");
        
        dependencias(Dec2.a("id"),Designador.a("lex"));
        dependencias(Dec2.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),Dec2.a("TSH"));
        dependencias(Tipo.a("TPRH"),Dec2.a("TPRH"));
        dependencias(Dec2.a("err"),Tipo.a("err"));
        
        calculo(Dec2.a("id"),asignacion);
      //  calculo(Dec2.a("clase"),asignacero);
        calculo(Dec2.a("nivel"),asignacero);
        calculo(Dec2.a("tipo"),asignacion);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(Dec2.a("err"),asignacion);
        
        return Dec2;
    }
    
    
    public TAtributos DecsSubs0(TAtributos DecsSubs,TAtributos DecSub){
        regla("DecsSubs → DecsSubs; DecSub");
                
        TAtributos DecsSubs0 = atributosPara("DecsSubs", "TSL","TSLH","TPRH","dir","err");
        
     
        // FALTA DecsSubs.TSL = AÃ‘ADIr....
        // FALTA ERR
        dependencias(DecsSubs.a("TPRH"),DecsSubs0.a("TPRH"));
        dependencias(DecSub.a("TPRH"),DecsSubs.a("TPRH"));
        dependencias(DecsSubs.a("TSLH"),DecsSubs0.a("TSLH"));
        dependencias(DecSub.a("TSLH"),DecsSubs.a("TSLH"));
        dependencias(DecsSubs0.a("err"),DecsSubs.a("err"));
        dependencias(DecsSubs0.a("dir"),DecsSubs.a("dir"));
        
        calculo(DecsSubs.a("TPRH"),asignacion);
        calculo(DecSub.a("TPRH"),asignacion);
        calculo(DecsSubs.a("TSLH"),asignacion);
        calculo(DecSub.a("TSLH"),asignacion);
        calculo(DecsSubs0.a("dir"),sumauno);
        
        return DecsSubs0;
        
    }
    
    public TAtributos DecsSubs1(TAtributos DecSub){
        regla("DecsSubs → DecSub");
                
        TAtributos DecsSubs1 = atributosPara("DecsSubs","TSL","TSLH","TPRH","dir","err");
        
        
        dependencias(DecSub.a("TPRH"),DecsSubs1.a("TPRH"));
        dependencias(DecSub.a("TSLH"),DecsSubs1.a("TSLH"));
        dependencias(DecsSubs1.a("err"),DecSub.a("err"));
        
        calculo(DecSub.a("TPRH"),asignacion);
        calculo(DecSub.a("TSLH"),asignacion);
        // FALTA DecsSubs.TSL = AÃ‘ADIr....
        // FALTA ERR
       

        calculo(DecsSubs1.a("dir"),asignacero);
        
        return DecsSubs1;
        
    }
    
    public TAtributos DecsSubs2(){
        regla("DecsSubs → λ");
                
        TAtributos DecsSubs2 = atributosPara("DecsSubs","err");
        
        calculo(DecsSubs2.a("err"),asignafalso);
        
        return DecsSubs2;
        
    }
    
    
    public TAtributos DecSub0(TAtributos Tipo,TAtributos Designador){
        regla("DecSub → var Tipo Designador");
                
        TAtributos DecSub0 = atributosPara("DecSub","TSLH","TPRH","id","clase","nivel","tipo","err");
        
        dependencias(DecSub0.a("id"),Designador.a("lex"));
        dependencias(DecSub0.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),DecSub0.a("TSH"));
        dependencias(Tipo.a("TPRH"),DecSub0.a("TPRH"));
        dependencias(DecSub0.a("err"),Tipo.a("err"));
        
      
      //  calculo(DecSub0.a("clase"),asignacero);
        calculo(DecSub0.a("nivel"),asignauno);
        calculo(DecSub0.a("tipo"),asignacion);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(DecSub0.a("err"),asignacion);
        
        return DecSub0;
    }

   

    public TAtributos designador1(TAtributos ident){
        regla("designador → ident");
                
        TAtributos designador1 = atributosPara("designador","lex");
        
        dependencias(designador1.a("lex"),ident.a("lex"));//designador.lex=ident.lex
        
        calculo(designador1.a("lex"),asignacion);
        
        return designador1;
    }
    
    
    public TAtributos designador2(TAtributos designador,TAtributos Exp){
        regla("designador → designador[Exp]");
                
        TAtributos designador2 = atributosPara("designador");
        
        //dependencias(designador1.a("lex"),ident.a("lex"));
        
        //calculo(designador1.a("lex"),asignacion);
      //  calculo(DecSub1.a("clase"),asignacero);
        //calculo(DecSub1.a("nivel"),asignauno);
        //calculo(DecSub1.a("tipo"),asignacion);
        //calculo(DecSub1.a("err"),asignacion);
        
        return designador2;
    }
    
    public TAtributos designador3(TAtributos designador_numero){
        regla("designador → designador_numero");
                
        TAtributos designador3 = atributosPara("designador");
        
        //dependencias(designador1.a("lex"),ident.a("lex"));
        
        //calculo(designador1.a("lex"),asignacion);
      //  calculo(DecSub1.a("clase"),asignacero);
        //calculo(DecSub1.a("nivel"),asignauno);
        //calculo(DecSub1.a("tipo"),asignacion);
        //calculo(DecSub1.a("err"),asignacion);
        
        return designador3;
    }
    
    public TAtributos Subprogramas0(TAtributos Subprogramas1,TAtributos Subprograma){
        regla("Subprogramas → Subprogramas Subprograma");
                
        TAtributos Subprogramas0 = atributosPara("Subprogramas","TPRH","TSH","err");
        
        dependencias(Subprogramas1.a("TPRH"),Subprogramas0.a("TPRH"));//Subprogramas1.TPRH = Subprogramas0.TPRH
        dependencias(Subprograma.a("TPRH"),Subprogramas1.a("TPRH"));//Subprograma.TPRH = Subprogramas1.TPRH
        dependencias(Subprogramas1.a("TSH"),Subprogramas0.a("TSH"));//Subprogramas1.TSH = Subprogramas0.TSH
        dependencias(Subprograma.a("TSH"),Subprogramas1.a("TSH"));//Subprograma.TSH = Subprogramas1.TSH
        dependencias(Subprogramas0.a("err"),Subprogramas1.a("err"),Subprograma.a("err"));//Subprogramas0.err= Subprogramas1.err OR Subprograma.err 
        
        calculo(Subprogramas1.a("TPRH"),asignacion);
        calculo(Subprograma.a("TPRH"),asignacero);
        calculo(Subprogramas1.a("TSH"),asignauno);
        calculo(Subprograma.a("TSH"),asignacion);
        calculo(Subprogramas0.a("err"),asignacion);
        
        return Subprogramas0;
    } 
    
    
    public TAtributos Subprogramas1(TAtributos Subprograma){
        regla("Subprogramas → Subprograma");
                
        TAtributos Subprogramas1 = atributosPara("Subprogramas","TPRH","TSH","err");
        
        dependencias(Subprograma.a("TPRH"),Subprogramas1.a("TPRH"));//Subprograma.TPRH = Subprogramas.TPRH
        dependencias(Subprograma.a("TSH"),Subprogramas1.a("TSH"));//Subprograma.TPRH = Subprogramas1.TPRH
       // dependencias(Subprogramas1.a("TSH"),Subprogramas0.a("TSH"));//Subprograma.TSL(local) = CreaTSL(Subprograma.TSH)
        //AÑADIRRRRRRdependencias(Subprograma.a("TSH"),Subprogramas1.a("TSH"));//Subprograma.TS= Añadir (Subprograma.TSH, Subprograma.id,
        //Subprograma.clase, Subprograma.niv ,Subprograma.dir, Subprograma.tipo)
        dependencias(Subprogramas1.a("err"),Subprogramas1.a("TSH"),Subprograma.a("id"),Subprograma.a("clase"),Subprograma.a("dir"),Subprograma.a("niv"),Subprograma.a("tipo"),Subprogramas1.a("TPRH"),Subprograma.a("err"));//Subprogramas0.err= Subprogramas1.err OR Subprograma.err 
        
        calculo(Subprograma.a("TPRH"),asignacion);
        calculo(Subprograma.a("TSH"),asignacero);
       // calculo(Subprograma.a("TSL"),creaTSL);
       // calculo(Subprograma.a("TS"),Añadir);
        //calculo(Subprogramas1.a("err"),EstaId?);
        //calculo(Subprogramas1.a("err"),EsPalReservada?);
        
        return Subprogramas1;
    }

    public TAtributos Subprogramas2(){
        regla("Subprogramas → λ");
                
        TAtributos Subprogramas2 = atributosPara("Subprogramas","TS","TSH","err");
        
        dependencias(Subprogramas2.a("TS"),Subprogramas2.a("TSH"));//Subprogramas.TS = Subprogramas.TSH

        calculo(Subprogramas2.a("TS"),asignacion);
        calculo(Subprogramas2.a("err"),asignafalso);
       // calculo(Subprograma.a("TSL"),creaTSL);
       // calculo(Subprograma.a("TS"),Añadir);
        //calculo(Subprogramas1.a("err"),EstaId?);
        //calculo(Subprogramas1.a("err"),EsPalReservada?);
        
        return Subprogramas2;
    }
    
    public TAtributos Subprograma0(TAtributos ident, TAtributos PFs, TAtributos CS){
        regla("Subprograma → subprogram: ident (PFs ) {CS }");
                
        TAtributos Subprograma0 = atributosPara("Subprograma","TPRH","id","clase","niv","tipo","dir","err");
        
        dependencias(PFs.a("TPRH"),Subprograma0.a("TPRH"));//PFs.TPRH = Subprograma.TPRH
        dependencias(CS.a("TPRH"),PFs.a("TPRH"));//CS.TPRH = PFs.TPRH
        dependencias(PFs.a("TSLH"),Subprograma0.a("TSL"));//PFs.TSLH = Subprograma.TSL(local)
        dependencias(CS.a("TSLH"),PFs.a("TSL"));//CS.TSLH = PFs.TSL
        dependencias(Subprograma0.a("id"),ident.a("lex"));//Subprograma.id = ident.Lex
       // dependencias(Subprograma0.a("clase"),PFs.a("TSL"));//Subprograma.clase = proc
       // dependencias(Subprograma0.a("TSLH"),PFs.a("TSL"));//Subprograma.niv = 0
        dependencias(Subprograma0.a("tipo"),PFs.a("tipo"));//Subprograma.tipo = PFs.tipo
       // dependencias(Subprograma0.a("TSLH"),PFs.a("TSL"));//Subprograma.dir = ¿??????????????????
        dependencias(Subprograma0.a("err"),PFs.a("err"),CS.a("err"));//Subprograma.err= PFs.err OR CS.err
        
        calculo(PFs.a("TPRH"),asignacion);
        calculo(CS.a("TPRH"),asignacion);
        calculo(PFs.a("TSLH"),asignacion);
        calculo(CS.a("TSLH"),asignacion);
        calculo(Subprograma0.a("id"),asignacion);
        //calculo(Subprograma0.a("clase"),asignacionProc);
        calculo(Subprograma0.a("niv"),asignacero);
        calculo(Subprograma0.a("tipo"),asignacion);
       // calculo(Subprograma0.a("dir"),asignacion);
        calculo(Subprograma0.a("err"),asignacionOR2);
        
        return Subprograma0;
    }  
    
    public TAtributos PFs0(TAtributos PFs1, TAtributos PF){
        regla("PFs → PFs, PF");
                
        TAtributos PFs0 = atributosPara("PFs","tipo","TSLH","TPRH","err");
        
        dependencias(PFs0.a("tipo"),PF.a("tipo"));//PFs.tipo= PF.tipo
        dependencias(PFs1.a("TSLH"),PFs0.a("TSLH"));//PFs1.TSLH= PFs0.TSLH
        dependencias(PF.a("TSLH"),PFs1.a("TSLH"));//PF.TSLH= PFs1.TSLH
        dependencias(PFs1.a("TPRH"),PFs0.a("TPRH"));//PFs1.TPRH= PFs0.TPRH
        dependencias(PF.a("TPRH"),PFs1.a("TPRH"));//PF.TPRH= PFs1.TPRH
        dependencias(PFs0.a("err"),PFs1.a("err"),PF.a("err"));//PFs0.err= PFs1.err OR PF.err
        
        calculo(PFs0.a("tipo"),asignacion);
        calculo(PFs1.a("TSLH"),asignacion);
        calculo(PF.a("TSLH"),asignacion);
        calculo(PFs1.a("TPRH"),asignacion);
        calculo(PF.a("TPRH"),asignacion);
        calculo(PFs0.a("err"),asignacionOR2);
        
        return PFs0;
    }
    
    public TAtributos PFs1(TAtributos PF){
        regla("PFs → PFs, PF");
                
        TAtributos PFs1 = atributosPara("PFs","tipo","TSLH","TPRH","err","TSH");
        
        dependencias(PFs1.a("tipo"),PF.a("tipo"));//PFs.tipo= PF.tipo
        //PFs.TSL= Añadir (PF.TSLH, PF.id,PF.clase, PF.niv, PF.Dir , PF.tipo)
        dependencias(PFs1.a("TSL"),PF.a("TSLH"),PF.a("id"),PF.a("clase"),PF.a("niv"),PF.a("dir"),PF.a("tipo"));
        //PFs.err= EstaId?( PFs.TSH, PF.id) OR EsPalReservada?( PFs.TPRH, PF.id) OR PF.err
        dependencias(PFs1.a("err"),PFs1.a("TSH"),PF.a("id"),PFs1.a("TPRH"),PF.a("err"));
        
        calculo(PFs1.a("tipo"),asignacion);
        //calculo(PFs1.a("TSL"),añadir);
        //calculo(PFs.a("err"),estaId?);
        
        return PFs1;
    }
    
    public TAtributos PF0(TAtributos Tipo, TAtributos ident){
        regla("PF → Tipo ident");
                
        TAtributos PF0 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        
        dependencias(PF0.a("id"),ident.a("lex"));//PF.id = ident.lex
        //dependencias(PF0.a("clase"),ident.a("lex"));//PF.clase = pf
        //dependencias(PF0.a("niv"),ident.a("lex"));//PF.niv = 1
        dependencias(PF0.a("tipo"),Tipo.a("tipo"));//PF.tipo = Tipo.tipo
        dependencias(PF0.a("dir"),PF0.a("tipo"),PF0.a("clase"));//PF.dir = tamañoDe(PF.tipo , PF.clase)
        dependencias(PF0.a("err"),Tipo.a("err"));//PF.err= Tipo.err
        
        calculo(PF0.a("id"),asignacion);
        //calculo(PF0.a("clase"),asignacionPF);¿¿¿¿¿¿¿¿¿?????????
        calculo(PF0.a("niv"),asignauno);
        calculo(PF0.a("tipo"),asignacion);
        //calculo(PF0.a("dir"),asignacion);¿¿¿¿¿¿¿¿¿¿¿¿¿?????????
        calculo(PF0.a("err"),asignacion);
        
        return PF0;
    }
    
    public TAtributos PF1(TAtributos Tipo, TAtributos Designador){
        regla("PF → Tipo * designador");
                
        TAtributos PF1 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        
        dependencias(PF1.a("id"),Designador.a("lex"));//PF.id = Designador.lex
        //dependencias(PF0.a("clase"),ident.a("lex"));//PF.clase = pf
        //dependencias(PF0.a("niv"),ident.a("lex"));//PF.niv = 1
        dependencias(PF1.a("tipo"),Tipo.a("tipo"));//PF.tipo = Tipo.tipo
        dependencias(PF1.a("dir"),PF1.a("tipo"),PF1.a("clase"));//PF.dir = tamañoDe(PF.tipo , PF.clase)
        dependencias(PF1.a("err"),Tipo.a("err"));//PF.err= Tipo.err
        
        calculo(PF1.a("id"),asignacion);
        //calculo(PF1.a("clase"),asignacionPF);¿¿¿¿¿¿¿¿¿?????????
        calculo(PF1.a("niv"),asignauno);
        calculo(PF1.a("tipo"),asignacion);
        //calculo(PF1.a("dir"),asignacion);¿¿¿¿¿¿¿¿¿¿¿¿¿?????????
        calculo(PF1.a("err"),asignacion);
        
        return PF1;
    }
    
    /*CS  → vars { DecsSubs } instructions { Insts }
        DecsSubs.TPRH = CS.TPRH
        DecsSubs.TSLH = CS.TSLH
        Insts.TSH = DecsSubs.TSL 
        CS.err= DecsSubs.err OR Insts.err
*/
    public TAtributos CS(TAtributos DecSubs, TAtributos Insts){
        regla("CS  → vars { DecsSubs } instructions { Insts }");
                
        TAtributos CS = atributosPara("CS","TPRH","TSLH","niv","tipo","dir","err");
        
        dependencias(DecSubs.a("TPRH"),CS.a("TPRH"));//DecsSubs.TPRH = CS.TPRH
        dependencias(DecSubs.a("TSLH"),CS.a("TSLH"));//DecsSubs.TSLH = CS.TSLH
        dependencias(Insts.a("TSH"),DecSubs.a("TSL"));//Insts.TSH = DecsSubs.TSL
        dependencias(CS.a("err"),DecSubs.a("err"),Insts.a("err"));//CS.err= DecsSubs.err OR Insts.err
        
        calculo(DecSubs.a("TPRH"),asignacion);
        calculo(DecSubs.a("TSLH"),asignacion);
        calculo(Insts.a("tipo"),asignacion);
        calculo(CS.a("err"),asignacionOR2);
        
        return CS;
    }
    
    public TAtributos Tipo0(TAtributos TipoBasico){
        regla("Tipo → TipoBasico");
                
        TAtributos Tipo0 = atributosPara("Tipo","tipo","err");
        
        dependencias(Tipo0.a("tipo"),TipoBasico.a("tipo"));
        dependencias(Tipo0.a("err"),TipoBasico.a("err"));

        calculo(Tipo0.a("tipo"),asignacion);
        calculo(Tipo0.a("err"),asignacion);
        
        return Tipo0;
    }
    
    public TAtributos Tipo1(TAtributos ident){
        regla("Tipo → ident");
                
        TAtributos Tipo1 = atributosPara("Tipo","tipo","err");
        
        //TIPO ARRAY!!!!dependencias(Tipo1.a("tipo"),TipoBasico.a("tipo"));
      
        // ERROR ESTA ID???  dependencias(Tipo1.a("err"),TipoBasico.a("err"));

        calculo(Tipo1.a("err"),asignacion);
        
        return Tipo1;
    }
    
    public TAtributos Tipo2(TAtributos Tipo,TAtributos Dimensiones){
        regla("Tipo → Tipo Dimensiones");
                
        TAtributos Tipo2 = atributosPara("Tipo","tipo","err");
        
      //TIPO ARRAY!!!!dependencias(Tipo2.a("tipo"),TipoBasico.a("tipo"));
        dependencias(Tipo2.a("err"),Tipo.a("err"),Dimensiones.a("err"));

       // calculo(Tipo2.a("tipo"),asignacion);
        calculo(Tipo2.a("err"),asignacionOR2);
        
        return Tipo2;
    }
    
    
    public TAtributos Dimensiones0(TAtributos Componente){
        regla("Dimensiones → Dimensiones[Componente] | [Componente]");
                
        TAtributos Dimensiones0 = atributosPara("Dimensiones","lex");
        
        dependencias(Dimensiones0.a("lex"),Componente.a("lex"));

        calculo(Dimensiones0.a("lex"),asignacion);
        
        return Dimensiones0;
    }
    
    
    public TAtributos Componente0(TAtributos numero){
        regla("Componente → numero ");
                
        TAtributos Componente0 = atributosPara("Componente","lex");

        dependencias(Componente0.a("lex"),numero.a("lex"));
        calculo(Componente0.a("lex"),asignacion);
        
        return Componente0;
    }
    
    public TAtributos Componente1(TAtributos ident){
        regla("Componente → numero ");
                
        TAtributos Componente1 = atributosPara("Componente","lex");

        dependencias(Componente1.a("lex"),ident.a("lex"));
        calculo(Componente1.a("lex"),asignacion);

        
        return Componente1;
    }
    
    public TAtributos Tipo3(TAtributos TiposTupla){
        regla("Tipo → (TiposTupla) ");
                
        TAtributos Tipo3 = atributosPara("Tipo","tipo");
        
      //TIPO TUPLA!!!!dependencias(Tipo2.a("tipo"),TipoBasico.a("tipo"));
       // FALTA ERR "OR" 
        
        return Tipo3;
    }
    
    public TAtributos TiposTupla0(TAtributos Tipos){
        regla("TiposTupla → TiposTupla , Tipo ");
                
        TAtributos TiposTupla0 = atributosPara("Tipos","tipo");
        
      //TIPO TUPLA!!!!dependencias(Tipo2.a("tipo"),TipoBasico.a("tipo"));
       // FALTA ERR "OR" 
        
        return TiposTupla0;
    }
    
    public TAtributos TiposTupla1(TAtributos Tipos){
        regla("TiposTupla → Tipo ");
                
        TAtributos TiposTupla1 = atributosPara("Tipo","tipo");
        
      //TIPO TUPLA!!!!dependencias(Tipo2.a("tipo"),TipoBasico.a("tipo"));
       // FALTA ERR "OR" 

        return TiposTupla1;
    }
    
    public TAtributos TiposBasico0(){
        regla("TiposBasico → boolean ");
                
        TAtributos TiposBasico0 = atributosPara("TipoBasico","tipo","err");
         
        //calculo(TiposBasico0.a("lex"),FUNCIONBOOL);
        calculo(TiposBasico0.a("err"),asignafalso);
        return TiposBasico0;
    }
    
    
    public TAtributos TiposBasico1(){
        regla("TiposBasico → integer ");
                
        TAtributos TiposBasico1 = atributosPara("TipoBasico","tipo","err");
         
        //calculo(TiposBasico0.a("lex"),FUNCION INT);
        calculo(TiposBasico1.a("err"),asignafalso);
        return TiposBasico1;
    }
    
    public TAtributos TiposBasico2(){
        regla("TiposBasico → natural ");
                
        TAtributos TiposBasico2 = atributosPara("TipoBasico","tipo","err");
         
        //calculo(TiposBasico0.a("lex"),FUNCION NATURAL);

        return TiposBasico2;
    }
    
    public TAtributos TiposBasico3(){
        regla("TiposBasico → float ");
                
        TAtributos TiposBasico3 = atributosPara("TipoBasico","tipo","err");
         
        //calculo(TiposBasico0.a("lex"),FUNCION FLOAT;
        calculo(TiposBasico3.a("err"),asignafalso);
        return TiposBasico3;
    }
    
    public TAtributos TiposBasico4(){
        regla("TiposBasico → carácter ");
                
        TAtributos TiposBasico4 = atributosPara("TipoBasico","tipo","err");
         
        //calculo(TiposBasico0.a("lex"),FUNCION CHAR);
        calculo(TiposBasico4.a("err"),asignafalso);
        return TiposBasico4;
    }
    
    
    public TAtributos Valores0(TAtributos numeroReal){
        regla("Valores → numeroReal ");
                
        TAtributos Valores0 = atributosPara("Valores","valor");
   
        dependencias(Valores0.a("valor"),numeroReal.a("lex"));
        calculo(Valores0.a("valor"),asignacion);
   
        return Valores0;
    }
    
    public TAtributos Valores1(TAtributos numeroEnt){
        regla("Valores → numeroEnt");
                
        TAtributos Valores1 = atributosPara("Valores","valor");
   
        dependencias(Valores1.a("valor"),numeroEnt.a("lex"));
        calculo(Valores1.a("valor"),asignacion);
   
        return Valores1;
    }
    
    public TAtributos Valores2(TAtributos numeroEnt){
        regla("Valores → false ");
                
        TAtributos Valores2 = atributosPara("Valores","valor");
   
        calculo(Valores2.a("valor"),asignafalso);
   
        return Valores2;
    }
    
    public TAtributos Valores3(TAtributos numeroEnt){
        regla("Valores → true ");
                
        TAtributos Valores3 = atributosPara("Valores","valor");

        calculo(Valores3.a("valor"),asignacierto);
   
        return Valores3;
    }
        
    public TAtributos Valores4(TAtributos letra){
        regla("Valores → ’(letra)’ ");
        
        TAtributos Valores4 = atributosPara("Valores","valor");
   
        dependencias(Valores4.a("valor"),letra.a("lex"));
        calculo(Valores4.a("valor"),asignacion);
   
        return Valores4;
    }
    
    public TAtributos Valores5(TAtributos digito){
        regla("Valores → ’(digito)’ ");
        
        TAtributos Valores5 = atributosPara("Valores","valor");
   
        dependencias(Valores5.a("valor"),digito.a("lex"));
        calculo(Valores5.a("valor"),asignacion);
   
        return Valores5;
    }
    
    
    public TAtributos Insts0(TAtributos Insts,TAtributos Inst){
        regla("Insts → Insts ; Inst ");
                
        TAtributos Insts0 = atributosPara("Insts", "err","cod","TSH","etq","etqh");
        
        dependencias(Insts.a("TSH"),Insts0.a("TSH"));
        dependencias(Inst.a("TSH"),Insts.a("TSH"));
        dependencias(Insts.a("etqh"),Insts0.a("etqh"));
        dependencias(Inst.a("etqh"),Insts.a("etq"));
        dependencias(Insts0.a("etq"),Inst.a("etq"));
        dependencias(Insts0.a("err"),Insts.a("err"),Inst.a("err"));
        dependencias(Insts0.a("cod"),Insts.a("cod"),Inst.a("cod"));
        
        
        calculo(Insts.a("TSH"),asignacion);
        calculo(Inst.a("TSH"),asignacion);
        calculo(Insts.a("etqh"),asignacion);
        calculo(Inst.a("etqh"),asignacion);
        calculo(Insts0.a("etq"),asignacion);
        calculo(Insts0.a("err"),asignacionOR2);
        calculo(Insts0.a("cod"),concatenar2);
        
        return Insts0;
        
    }
    
    public TAtributos Insts1(TAtributos Inst){
        regla("Insts → Inst ");
                
        TAtributos Insts1 = atributosPara("Insts", "err","cod","TSH","etq","etqh");
        
        dependencias(Inst.a("TSH"),Insts1.a("TSH"));
        dependencias(Inst.a("etqh"),Insts1.a("etqh"));
        dependencias(Insts1.a("etq"),Inst.a("etq"));
        dependencias(Insts1.a("err"),Inst.a("err"));
        dependencias(Insts1.a("cod"),Inst.a("cod"));
        
        
        calculo(Inst.a("TSH"),asignacion);
        calculo(Inst.a("etqh"),asignacion);
        calculo(Insts1.a("etq"),asignacion);
        calculo(Insts1.a("err"),asignacion);
        calculo(Insts1.a("cod"),asignacion);
        
        return Insts1;
        
    }
    
    public TAtributos Inst0(TAtributos InsAsig){
        regla("Inst → InsAsig ");
                
        TAtributos Inst2 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
        
        dependencias(InsAsig.a("TSH"),Inst2.a("TSH"));
        dependencias(InsAsig.a("etqh"),Inst2.a("etqh"));
        dependencias(Inst2.a("etq"),InsAsig.a("etq"));
        dependencias(Inst2.a("err"),InsAsig.a("err"));
        dependencias(Inst2.a("cod"),InsAsig.a("cod"));
        
        
        calculo(InsAsig.a("TSH"),asignacion);
        calculo(InsAsig.a("etqh"),asignacion);
        calculo(Inst2.a("etq"),asignacion);
        calculo(Inst2.a("err"),asignacion);
        calculo(Inst2.a("cod"),asignacion);
        
        return Inst2;
        
    }
    
    public TAtributos Inst1(TAtributos InsR){
        regla("Inst → InsR ");
                
        TAtributos Inst1 = atributosPara("Inst","err","cod","TSH","etq","etqh");
        
        dependencias(InsR.a("TSH"),Inst1.a("TSH"));
        dependencias(InsR.a("etqh"),Inst1.a("etqh"));
        dependencias(Inst1.a("etq"),InsR.a("etq"));
        dependencias(Inst1.a("err"),InsR.a("err"));
        dependencias(Inst1.a("cod"),InsR.a("cod"));
        
        
        calculo(InsR.a("TSH"),asignacion);
        calculo(InsR.a("etqh"),asignacion);
        calculo(Inst1.a("etq"),asignacion);
        calculo(Inst1.a("err"),asignacion);
        calculo(Inst1.a("cod"),asignacion);
        
        return Inst1;
        
    }
    
    
    public TAtributos Inst2(TAtributos InsW){
        regla("Inst → InsW ");
                
        TAtributos Inst2 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
        
        dependencias(InsW.a("TSH"),Inst2.a("TSH"));
        dependencias(InsW.a("etqh"),Inst2.a("etqh"));
        dependencias(Inst2.a("etq"),InsW.a("etq"));
        dependencias(Inst2.a("err"),InsW.a("err"));
        dependencias(Inst2.a("cod"),InsW.a("cod"));
        
        
        calculo(InsW.a("TSH"),asignacion);
        calculo(InsW.a("etqh"),asignacion);
        calculo(Inst2.a("etq"),asignacion);
        calculo(Inst2.a("err"),asignacion);
        calculo(Inst2.a("cod"),asignacion);
        
        return Inst2;
        
    }
    
    public TAtributos Inst3(TAtributos SWAP1){
        regla("Inst → SWAP1() ");
                
        TAtributos Inst3 = atributosPara("Inst","etqh","err","cod");
        
        dependencias(Inst3.a("err"),SWAP1.a("err"));
        dependencias(Inst3.a("cod"),SWAP1.a("cod"));
        

        calculo(SWAP1.a("etq"),asignacero);
        calculo(Inst3.a("err"),asignacion);
        calculo(Inst3.a("cod"),asignacion);
        
        return Inst3;
        
    }
    
    public TAtributos Inst4(TAtributos SWAP2){
        regla("Inst → SWAP2() ");
                
        TAtributos Inst4 = atributosPara("Inst","etqh","err","cod");
        
        dependencias(Inst4.a("err"),SWAP2.a("err"));
        dependencias(Inst4.a("cod"),SWAP2.a("cod"));
        

        calculo(SWAP2.a("etq"),asignacero);
        calculo(Inst4.a("err"),asignacion);
        calculo(Inst4.a("cod"),asignacion);
        
        return Inst4;
        
    }
    public TAtributos Inst5(TAtributos IF){
    	
    	regla("Inst → IF ");
         
         TAtributos Inst5 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
         
         dependencias(IF.a("TSH"),Inst5.a("TSH"));
         dependencias(IF.a("etqh"),Inst5.a("etqh"));
         dependencias(Inst5.a("etq"),IF.a("etq"));
         dependencias(Inst5.a("err"),IF.a("err"));
         dependencias(Inst5.a("cod"),IF.a("cod"));
         
         
         calculo(IF.a("TSH"),asignacion);
         calculo(IF.a("etqh"),asignacion);
         calculo(Inst5.a("etq"),asignacion);
         calculo(Inst5.a("err"),asignacion);
         calculo(Inst5.a("cod"),asignacion);
         
         return Inst5;
        
    }

    public TAtributos Inst6(TAtributos WHILE){
        	
        	regla("Inst → IF ");
             
             TAtributos Inst6 = atributosPara("Inst","err","cod","TSH","etq","etqh");
             
             dependencias(WHILE.a("TSH"),Inst6.a("TSH"));
             dependencias(WHILE.a("etqh"),Inst6.a("etqh"));
             dependencias(Inst6.a("etq"),WHILE.a("etq"));
             dependencias(Inst6.a("err"),WHILE.a("err"));
             dependencias(Inst6.a("cod"),WHILE.a("cod"));
             
             
             calculo(WHILE.a("TSH"),asignacion);
             calculo(WHILE.a("etqh"),asignacion);
             calculo(Inst6.a("etq"),asignacion);
             calculo(Inst6.a("err"),asignacion);
             calculo(Inst6.a("cod"),asignacion);
             
             return Inst6;
            
        }
   
    public TAtributos Inst7(TAtributos LLAMADA){
    
    	regla("Inst → LLAMADA ");
         
         TAtributos Inst7 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
         
         dependencias(LLAMADA.a("TSH"),Inst7.a("TSH"));
         dependencias(Inst7.a("err"),LLAMADA.a("err"));
         dependencias(Inst7.a("cod"),LLAMADA.a("cod"));
         
         
         calculo(LLAMADA.a("TSH"),asignacion);
         calculo(Inst7.a("err"),asignacion);
         calculo(Inst7.a("cod"),asignacion);
         calculo(LLAMADA.a("etq"),asignacero);
         
         return Inst7;
        
    }
    
    public TAtributos InsAsig(TAtributos Designador,TAtributos Exp){
        regla("InsAsig → Designador = Exp ");
                
        TAtributos InsAsig = atributosPara("InsAsig","err","cod","TSH","etq","etqh");
        
        dependencias(Exp.a("etqh"),InsAsig.a("etqh"));
        dependencias(InsAsig.a("err"),InsAsig.a("TSH"),Designador.a("lex"),Designador.a("tipo"),Exp.a("tipo"),Exp.a("err"));
        dependencias(InsAsig.a("etq"),Exp.a("etq"));
        dependencias(Exp.a("etqh"),InsAsig.a("etqh"));
        dependencias(InsAsig.a("cod"),InsAsig.a("TSH"),Designador.a("lex"));
  
        /// FALTA EL COD!!!
        calculo(Exp.a("etqh"),asignacion);
        //FALTA ERRORR
        calculo(InsAsig.a("etq"),sumauno);
        calculo(Exp.a("etqh"),asignacion);
  
        
        
        return InsAsig;
        
    }
    
    public TAtributos InsR(TAtributos in,TAtributos Designador){
        regla("InsR → in (Designador) ");
                
        TAtributos InsR = atributosPara("InsR","err","cod","TSH","etq","etqh");
        
 
        dependencias(InsR.a("err"),InsR.a("TSH"),Designador.a("lex"));
        dependencias(InsR.a("etq"),in.a("etq"));
        dependencias(in.a("etqh"),InsR.a("etqh"));
        dependencias(InsR.a("cod"),InsR.a("TSH"),Designador.a("lex"));
  
        /// FALTA EL COD!!!
        //FALTA ERRORR
        calculo(InsR.a("etq"),sumauno);
        calculo(in.a("etqh"),asignacion);
  
        
        
        return InsR;
        
    }
    
   public TAtributos InsW(TAtributos Exp) {
       
       regla("InsW → out (Exp)");
       
       TAtributos InsW = atributosPara("InsW","TSH","err", "cod", "etq","etqh");
     
       /// VALORE????
       dependencias(InsW.a("cod"), Exp.a("valor"));
       dependencias(InsW.a("etq"),InsW.a("etqh"));
       dependencias(Exp.a("TSH"),InsW.a("TSH"));
       dependencias(InsW.a("err"),Exp.a("err"));
       
       calculo(InsW.a("etq"),sumaDos);
       calculo(InsW.a("cod"),concatenarIR2);
       calculo(InsW.a("err"),asignacion);
       calculo(Exp.a("TSH"),asignacion);
       
       return InsW;
   }
    

   public TAtributos If0(TAtributos ExpBool,TAtributos Insts0){
       regla("IF → if ExpBool then Insts endif");
               
       TAtributos If0 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(ExpBool.a("TSH"),If0.a("TSH"));
       dependencias(Insts0.a("TSH"),ExpBool.a("TSH"));
       dependencias(If0.a("err"),Insts0.a("err"));
       
       dependencias(If0.a("cod"),ExpBool.a("cod"),Insts0.a("etq"),Insts0.a("cod"));
       dependencias(ExpBool.a("etqh"),If0.a("etqh"));
       dependencias(Insts0.a("etqh"),ExpBool.a("etq"));

       dependencias(If0.a("etq"),Insts0.a("etq"));
       dependencias(ExpBool.a("irvh"),ExpBool.a("etq"));
       dependencias(ExpBool.a("irfh"),ExpBool.a("etq"));
       
       
       calculo(ExpBool.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(If0.a("err"),asignacionOR2);

       calculo(Insts0.a("etqh"),sumauno);

       //CONCATENAR DIFERENTE
       calculo(If0.a("cod"),concatenarIR1);
       calculo(If0.a("etq"),asignacion);
       calculo(ExpBool.a("etqh"),asignacion);
       calculo(ExpBool.a("irvh"),asignacion);
       calculo(ExpBool.a("irfh"),asignacion);
       
       
       return If0;
       
   }
    
   public TAtributos If1(TAtributos ExpBool,TAtributos Insts0, TAtributos Insts1){
       regla("IF → if  ExpBool then Insts else Insts endif ");
       
       TAtributos If1 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(ExpBool.a("TSH"),If1.a("TSH"));
       dependencias(Insts0.a("TSH"),ExpBool.a("TSH"));
       dependencias(Insts1.a("TSH"),Insts0.a("TSH"));
       dependencias(If1.a("err"),Insts0.a("err"),Insts1.a("err"));
       
       dependencias(If1.a("cod"),ExpBool.a("cod"),Insts0.a("etq"),Insts0.a("cod"),Insts1.a("etq"),Insts1.a("cod"));
       dependencias(ExpBool.a("etqh"),If1.a("etqh"));
       dependencias(Insts0.a("etqh"),ExpBool.a("etq"));
       dependencias(Insts1.a("etqh"),Insts0.a("etq"));
       dependencias(If1.a("etq"),Insts1.a("etq"));
       dependencias(ExpBool.a("irvh"),ExpBool.a("etq"));
       dependencias(ExpBool.a("irfh"),ExpBool.a("etq"));
       
       
       calculo(ExpBool.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(Insts1.a("TSH"),asignacion);
       //// FALTA ERRRORRR
       calculo(Insts0.a("etqh"),sumauno);
       calculo(Insts1.a("etqh"),sumauno);
       calculo(If1.a("cod"),concatenarIR1);
       calculo(If1.a("etq"),asignacion);
       calculo(ExpBool.a("etqh"),asignacion);
       calculo(ExpBool.a("irvh"),asignacion);
       calculo(ExpBool.a("irfh"),asignacion);
       
       
       return If1;
       
   }
   
   
   public TAtributos While(TAtributos ExpBool, TAtributos Insts0){
	   regla("IF → if ExpBool then Insts endif");
       
       TAtributos While0 = atributosPara("While","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(ExpBool.a("TSH"),While0.a("TSH"));
       dependencias(Insts0.a("TSH"),ExpBool.a("TSH"));
       dependencias(While0.a("err"),Insts0.a("err"));
       
       dependencias(While0.a("cod"),ExpBool.a("cod"),Insts0.a("etq"),Insts0.a("cod"),While0.a("etqh"));
       dependencias(ExpBool.a("etqh"),While0.a("etqh"));
       dependencias(Insts0.a("etqh"),ExpBool.a("etq"));

       dependencias(While0.a("etq"),Insts0.a("etq"));
       dependencias(ExpBool.a("irvh"),ExpBool.a("etq"));
       dependencias(ExpBool.a("irfh"),ExpBool.a("etq"));
       
       
       calculo(ExpBool.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(While0.a("err"),asignacionOR2);

       calculo(Insts0.a("etqh"),sumauno);

       //CONCATENAR DIFERENTE
       calculo(While0.a("cod"),concatenarIR1);
       calculo(While0.a("etq"),asignacion);
       calculo(ExpBool.a("etqh"),asignacion);
       calculo(ExpBool.a("irvh"),asignacion);
       calculo(ExpBool.a("irfh"),asignacion);
       
       
       return While0;
       
   }
    
   
   public TAtributos Llamada0(TAtributos ident,TAtributos Parametros){
       regla("LLAMADA → Call ident (Parametros)");
               
       TAtributos Llamada0 = atributosPara("Llamada","TSH","err", "cod","etq","etqh");
       
       
       dependencias(Parametros.a("TSH"),Llamada0.a("TSH"));
       dependencias(Llamada0.a("err"),Parametros.a("err"));
       dependencias(Llamada0.a("cod"),ident.a("cod"),Parametros.a("cod"),ident.a("etqh"));
       dependencias(Llamada0.a("etq"),ident.a("etq"));
       dependencias(ident.a("etqh"),Llamada0.a("etqh"));

       

       calculo(Parametros.a("TSH"),asignacion);
       calculo(Llamada0.a("err"),asignacion);
       //CONCATENAR DIFERENTE
       calculo(Llamada0.a("etqh"),asignacion);
       calculo(Llamada0.a("etq"),sumauno);

       
       
       return Llamada0;
       
   }
   
   public TAtributos Parametros0(TAtributos Parametros1,TAtributos Parametro){
       regla("Parametros → Parametros, Parametro");
               
       TAtributos Parametros0 = atributosPara("Parametros","TSH","err");
       
       dependencias(Parametros1.a("TSH"),Parametros0.a("TSH"));
       dependencias(Parametro.a("TSH"),Parametros1.a("TSH"));     
       dependencias(Parametros0.a("err"),Parametros1.a("err"),Parametro.a("err"));


       calculo(Parametros1.a("TSH"),asignacion);
       calculo(Parametro.a("TSH"),asignacion);
       calculo(Parametros0.a("err"),asignacionOR2);
       
       return Parametros0;
       
   }
   
   public TAtributos Parametros1(TAtributos Parametro){
       regla("Parametros → Parametros, Parametro");
               
       TAtributos Parametros1 = atributosPara("Parametros","TSH","err");
       
       dependencias(Parametro.a("TSH"),Parametros1.a("TSH"));     
       dependencias(Parametros1.a("err"),Parametro.a("err"));

       calculo(Parametro.a("TSH"),asignacion);
       calculo(Parametros1.a("err"),asignacion);
       
       return Parametros1;
       
   }
    
   public TAtributos Parametro0(TAtributos Exp){
       regla("Parametro → ident = Exp ");
               
       TAtributos Parametro0 = atributosPara("Parametros","TSH","err");
       
       dependencias(Exp.a("TSH"),Parametro0.a("TSH"));     
       dependencias(Parametro0.a("err"),Exp.a("err"));

       calculo(Exp.a("TSH"),asignacion);
       calculo(Parametro0.a("err"),asignacion);
       
       return Parametro0;
       
   }
   
   public TAtributos Parametro1(TAtributos Exp){
       regla("Parametro →  Designador = Exp");
               
       TAtributos Parametro1 = atributosPara("Parametros","TSH","err");
       
       dependencias(Exp.a("TSH"),Parametro1.a("TSH"));     
       dependencias(Parametro1.a("err"),Exp.a("err"));

       calculo(Exp.a("TSH"),asignacion);
       calculo(Parametro1.a("err"),asignacion);
       
       return Parametro1;
       
   }
    
    
    
    
    
   
    //========================================================================
    /////////////////EJERCICIO CUP DE EJEMPLO!
    
    
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