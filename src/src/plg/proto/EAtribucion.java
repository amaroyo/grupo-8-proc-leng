package src.plg.proto;

import java.util.ArrayList;

import src.es.ucm.fdi.plg.evlib.Atribucion;
import src.es.ucm.fdi.plg.evlib.Atributo;
import src.es.ucm.fdi.plg.evlib.SemFun;
import src.es.ucm.fdi.plg.evlib.TAtributos;
// DefiniciÃ³n de las funciones semÃ¡nticas
import src.plg.proto.TS;

class creaTPR implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
        
    	
    	TPR TPal=new TPR();
    	
    		
    	return args[0].valor();
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
        String s="";
        //s= args[0].valor()+" ir_f("+n+") "+args[2].valor()+" ir_a("+args[3].valor()+") "+args[4].valor();
        s = args[0].valor()+""+ args[1].valor() + "or";
    	return s;
    }

}

class AsignacionOR5 implements SemFun{

    @Override
    public Object eval(Atributo... args) {
        String s="";
        //s= args[0].valor()+" ir_f("+n+") "+args[2].valor()+" ir_a("+args[3].valor()+") "+args[4].valor();
        s = args[0].valor()+""+ args[1].valor() + "or"+ args[2].valor() + "or"+args[3].valor() + "or" + args[4].valor();
    	return s;
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

class concatenarExp10 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class concatenarPrograma implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=args[0].valor()+" stop ";
        return s;
    }
}

//----------OpsFunctions---------------------------
class asignaOp00 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="mayor";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp01 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="menor";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp02 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="menor igual";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp03 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="mayor igual";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp04 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="igual";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp05 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="distinto";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp10 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="suma";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp11 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="resta";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp20 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="multiplicacion";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}


class asignaOp21 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="division";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}


class asignaOp22 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="modulo";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp30 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="desplazamiento izquierda";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp31 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="desplazamiento derecha";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp410 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="conversionChar";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp411 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="conversionInt";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp412 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="conversionNat";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp413 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="conversionFloat";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

class asignaOp420 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="negacion logica";
       // s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

public class EAtribucion extends Atribucion {    
    
	
	
	public TS tSimbolos;
	
    // Se crean los objetos que representan las diferentes funciones semÃ¡nticas
	private static SemFun creaTPR = new creaTPR();
    private static SemFun asignacion = new Asignacion();
    private static SemFun asignacionOR2 = new AsignacionOR2();
    private static SemFun asignacionOR5 = new AsignacionOR5();
    private static SemFun asignacero = new AsignaCero();
    private static SemFun asignauno = new AsignaUno();
    private static SemFun asignacierto = new asignaCierto();
    private static SemFun asignafalso = new asignaFalso();
    
    private static SemFun concatenar2 = new concatenar2();
    private static SemFun concatenarIR1 = new concatenarIR1();
    private static SemFun concatenarIR2 = new concatenarIR2();
    private static SemFun concatenarExp10 = new concatenarExp10();
    private static SemFun concatenarPrograma = new concatenarPrograma();
  //  private static SemFun addVal = new AddVal();
  //  private static SemFun initT = new InitT();
    
    private static SemFun sumauno = new SumaUno();
    private static SemFun sumaDos = new SumaDos();
    private static SemFun sumaTres = new SumaTres();
   //funciones Ops
    private static SemFun asignaOp00 = new asignaOp00();
    private static SemFun asignaOp01 = new asignaOp01();
    private static SemFun asignaOp02 = new asignaOp02();
    private static SemFun asignaOp03 = new asignaOp03();
    private static SemFun asignaOp04 = new asignaOp04();
    private static SemFun asignaOp05 = new asignaOp05();
    //-------------------------------------------------
    private static SemFun asignaOp10 = new asignaOp10();
    private static SemFun asignaOp11 = new asignaOp11();
    //--------------------------------------------------
    private static SemFun asignaOp20 = new asignaOp20();
    private static SemFun asignaOp21 = new asignaOp21();
    private static SemFun asignaOp22 = new asignaOp22();
    //--------------------------------------------------
    private static SemFun asignaOp30 = new asignaOp30();
    private static SemFun asignaOp31 = new asignaOp31();
    //--------------------------------------------------
    private static SemFun asignaOp410 = new asignaOp410();
    private static SemFun asignaOp411 = new asignaOp411();
    private static SemFun asignaOp412 = new asignaOp412();
    private static SemFun asignaOp413 = new asignaOp413();
    //---------------------------------------------------
    private static SemFun asignaOp420 = new asignaOp420();
    
    
    //PRACTICA PLG 2Âº CUATRI!!!!!!
    public TAtributos Programa(TAtributos Consts,TAtributos Tipos,TAtributos Vars,TAtributos Subprogramas,TAtributos Insts){
        regla("Programa → program: ident { Consts Tipos Vars Subprogramas instructions { Insts }}");
                
        TAtributos Programa = atributosPara("Programa", "TS","err","cod","etq","etqh","TPR");
        
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
        
        dependencias(Subprogramas.a("etqh"),Programa.a("etqh"));
        dependencias(Insts.a("etqh"),Subprogramas.a("etq"));
        dependencias(Programa.a("etq"),Insts.a("etq"));

        TS tSimbolos=new TS();
        calculo(Programa.a("TPR"),creaTPR);
        calculo(Programa.a("cod"),concatenarIR1);
        calculo(Consts.a("TSH"),asignacion);
        calculo(Tipos.a("TSH"),asignacion);
        calculo(Vars.a("TSH"),asignacion);
        calculo(Subprogramas.a("TSH"),asignacion);
        calculo(Insts.a("TSH"),asignacion);
        
        calculo(Programa.a("cod"),concatenarPrograma);
        calculo(Consts.a("TPRH"),asignacion);
        calculo(Tipos.a("TPRH"),asignacion);
        calculo(Vars.a("TPRH"),asignacion);
        calculo(Subprogramas.a("TPRH"),asignacion);
        calculo(Programa.a("err"),asignacionOR5);
        
        calculo(Programa.a("etqh"),asignacero);
        calculo(Subprogramas.a("etqh"),asignacion);
        calculo(Insts.a("etqh"),asignacion);
        calculo(Programa.a("etq"),asignacion);

        
        return Programa;
        
    }
  
    public TAtributos Consts0(TAtributos Decs){
        regla("Consts → consts { Decs }");
                
        TAtributos Consts0 = atributosPara("Consts", "TS","TSH","TPRH","err");
        
        dependencias(Decs.a("TPRH"),Consts0.a("TPRH"));
        dependencias(Consts0.a("TS"),Decs.a("TS"));
        dependencias(Decs.a("TSH"),Consts0.a("TSH"));
        dependencias(Consts0.a("err"),Decs.a("err"));
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Consts0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Consts0.a("err"),asignacion);
        
        return Consts0;     
    }
    
    public TAtributos Consts1(TAtributos Decs){
        regla("Consts →  λ ");
                
        TAtributos Consts1 = atributosPara("Consts", "err");

        calculo(Consts1.a("err"),asignafalso);
        
        return Consts1;
        
    }
    
    public TAtributos Tipos0(TAtributos Decs){
        regla("Tipos → tipos { Decs } ");
                
        TAtributos Tipos0 = atributosPara("Tipos", "TS","TSH","TPRH","err");
        
        dependencias(Decs.a("TPRH"),Tipos0.a("TPRH"));
        dependencias(Tipos0.a("TS"),Decs.a("TS"));
        dependencias(Decs.a("TSH"),Tipos0.a("TSH"));
        dependencias(Tipos0.a("err"),Decs.a("err"));
      
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Tipos0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Tipos0.a("err"),asignacion);
        
        return Tipos0;
        
    }
    
    public TAtributos Tipos1(TAtributos Decs){
        regla("Tipos →  λ ");
                
        TAtributos Tipos1 = atributosPara("Tipos","err");
        
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
                
        TAtributos Vars1 = atributosPara("Vars","err");

        calculo(Vars1.a("err"),asignafalso);
        
        return Vars1;
        
    }
    
    public TAtributos Subprogramas0(TAtributos Decs){
        regla("Subprogramas →  subprograms { Decs }");
                
        TAtributos Subprogramas0 = atributosPara("Subprogramas", "TS","TSH","TPRH","err","etq","etqh");
        

        dependencias(Decs.a("TPRH"),Subprogramas0.a("TPRH"));
        dependencias(Subprogramas0.a("TS"),Decs.a("TS"));
        dependencias(Decs.a("TSH"),Subprogramas0.a("TSH"));
        dependencias(Subprogramas0.a("err"),Decs.a("err"));
        
        dependencias(Decs.a("etqh"),Subprogramas0.a("etq"));
        dependencias(Subprogramas0.a("etq"),Decs.a("etq"));
        
        
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Subprogramas0.a("TS"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Subprogramas0.a("err"),asignacion);
        calculo(Decs.a("etqh"),asignacion);
        calculo(Subprogramas0.a("etq"),asignacion);
        
        return Subprogramas0;
        
    }
    
    public TAtributos Subprogramas1(TAtributos Decs){
        regla("Subprogramas →   λ");
                
        TAtributos Subprogramas1 = atributosPara("Subprogramas","err");

        calculo(Subprogramas1.a("err"),asignafalso);
        
        return Subprogramas1;
        
    }
    
    
    public TAtributos Decs0(TAtributos Decs,TAtributos Dec){
        regla(" Decs → Decs ; Dec ");
                
        TAtributos Decs0 = atributosPara("Decs","TS","TSH","TPRH","dir","err","etq","etqh");
        
     
        // FALTA DECS.TS = AÃ‘ADIr....
        // FALTA ERR
        dependencias(Decs.a("TPRH"),Decs0.a("TPRH"));
        dependencias(Dec.a("TPRH"),Decs.a("TPRH"));
        dependencias(Decs0.a("TS"),Decs.a("TS"),Dec.a("id"),Dec.a("clase"),Decs.a("nivel"),Decs.a("dir"),Dec.a("tipo"));
        dependencias(Decs.a("TSH"),Decs0.a("TSH"));
        dependencias(Dec.a("TSH"),Decs.a("TSH"));
        dependencias(Decs0.a("err"),Decs.a("err"),Dec.a("err"),Dec.a("id"),Decs.a("TSH"),Decs.a("TPRH"));
        dependencias(Decs0.a("dir"),Decs.a("dir"));
        dependencias(Decs.a("etqh"),Decs0.a("etqh"));
        dependencias(Dec.a("etqh"),Decs.a("etq"));
        dependencias(Decs0.a("etq"),Dec.a("etq"));
        
        tSimbolos.añadirTS(Dec.a("id"),tSimbolos.creaParametros(Dec.a("clase"),Decs.a("nivel"),Decs.a("dir"),Dec.a("tipo")));
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs0.a("dir"),sumauno);
        calculo(Decs.a("etqh"),asignacion);
        calculo(Dec.a("etqh"),asignacion);
        calculo(Decs0.a("etq"),asignacion);
        
        return Decs0;
        
    }
    
    public TAtributos Decs1(TAtributos Dec){
        regla("Decs → Dec");
                
        TAtributos Decs1 = atributosPara("Decs", "TS","TSH","TPRH","dir","err","etq","etqh");
        
        // FALTA DECS.TS = AÃ‘ADIr....
        // FALTA ERR
        dependencias(Dec.a("TPRH"),Decs1.a("TPRH"));
        dependencias(Dec.a("TSH"),Decs1.a("TSH"));
        dependencias(Decs1.a("err"),Dec.a("err"));
        dependencias(Dec.a("etqh"),Decs1.a("etqh"));
        dependencias(Decs1.a("etq"),Dec.a("etq"));
        
       
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs1.a("dir"),asignacero);
        
        calculo(Dec.a("etqh"),asignacion);
        calculo(Decs1.a("etq"),asignacion);
        
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
    
    public TAtributos Dec3(TAtributos ident, TAtributos PFs, TAtributos CS){
        regla("Dec → subprogram: ident (PFs ) {CS}");
        
        TAtributos Dec3 = atributosPara("Dec","TPRH","id","clase","niv","tipo","dir","err","etq","etqh");
        
        dependencias(PFs.a("TPRH"),Dec3.a("TPRH"));//PFs.TPRH = Subprograma.TPRH
        dependencias(CS.a("TPRH"),PFs.a("TPRH"));//CS.TPRH = PFs.TPRH
        dependencias(PFs.a("TSLH"),Dec3.a("TSL"));//PFs.TSLH = Subprograma.TSL(local)
        dependencias(CS.a("TSLH"),PFs.a("TSL"));//CS.TSLH = PFs.TSL
        dependencias(Dec3.a("id"),ident.a("lex"));//Subprograma.id = ident.Lex
       // dependencias(Dec3.a("clase"),PFs.a("TSL"));//Subprograma.clase = proc
       // dependencias(Dec3.a("TSLH"),PFs.a("TSL"));//Subprograma.niv = 0
        dependencias(Dec3.a("tipo"),PFs.a("tipo"));//Subprograma.tipo = PFs.tipo
       // dependencias(Dec3.a("TSLH"),PFs.a("TSL"));//Subprograma.dir = ¿??????????????????
        dependencias(Dec3.a("err"),PFs.a("err"),CS.a("err"));//Subprograma.err= PFs.err OR CS.err
        
        dependencias(CS.a("etqh"),Dec3.a("etqh"));
        dependencias(Dec3.a("etq"),CS.a("etq"));
        
        calculo(PFs.a("TPRH"),asignacion);
        calculo(CS.a("TPRH"),asignacion);
        calculo(PFs.a("TSLH"),asignacion);
        calculo(CS.a("TSLH"),asignacion);
        calculo(Dec3.a("id"),asignacion);
        //calculo(Dec3.a("clase"),asignacionProc);
        calculo(Dec3.a("niv"),asignacero);
        calculo(Dec3.a("tipo"),asignacion);
       // calculo(Dec3.a("dir"),asignacion);
        calculo(Dec3.a("err"),asignacionOR2);
        
        calculo(CS.a("etqh"),asignacion);
        calculo(Dec3.a("etq"),asignacion);
        
        return Dec3;
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
                
        TAtributos CS = atributosPara("CS","TPRH","TSLH","niv","tipo","dir","err","etq","etqh");
        
        dependencias(DecSubs.a("TPRH"),CS.a("TPRH"));//DecsSubs.TPRH = CS.TPRH
        dependencias(DecSubs.a("TSLH"),CS.a("TSLH"));//DecsSubs.TSLH = CS.TSLH
        dependencias(Insts.a("TSH"),DecSubs.a("TSL"));//Insts.TSH = DecsSubs.TSL
        dependencias(CS.a("err"),DecSubs.a("err"),Insts.a("err"));//CS.err= DecsSubs.err OR Insts.err
        dependencias(Insts.a("etqh"),CS.a("etqh"));
        dependencias(CS.a("etq"),Insts.a("etq"));
        
        calculo(DecSubs.a("TPRH"),asignacion);
        calculo(DecSubs.a("TSLH"),asignacion);
        calculo(Insts.a("tipo"),asignacion);
        calculo(CS.a("err"),asignacionOR2);
        
        calculo(Insts.a("etqh"),asignacion);
        calculo(CS.a("etq"),asignacion);
        
        return CS;
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
   
   
   public TAtributos While0(TAtributos ExpBool, TAtributos Insts0){
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
               
       TAtributos Parametro0 = atributosPara("Parametro","TSH","err");
       
       dependencias(Exp.a("TSH"),Parametro0.a("TSH"));     
       dependencias(Parametro0.a("err"),Exp.a("err"));

       calculo(Exp.a("TSH"),asignacion);
       calculo(Parametro0.a("err"),asignacion);
       
       return Parametro0;
       
   }
   
   public TAtributos Parametro1(TAtributos Exp){
       regla("Parametro →  Designador = Exp");
               
       TAtributos Parametro1 = atributosPara("Parametro","TSH","err");
       
       dependencias(Exp.a("TSH"),Parametro1.a("TSH"));     
       dependencias(Parametro1.a("err"),Exp.a("err"));

       calculo(Exp.a("TSH"),asignacion);
       calculo(Parametro1.a("err"),asignacion);
       
       return Parametro1;
       
   }
    
   public TAtributos ExpBool(TAtributos Exp0,TAtributos Op0,TAtributos Exp1){
	    
		regla("ExpBool → Exp Op0 Exp ");
	     
	     TAtributos ExpBool = atributosPara("ExpBool", "TSH","tipo","err","cod","etq");
	     
	     dependencias(Exp0.a("TSH"),ExpBool.a("TSH"));//Exp.TSH = ExpBool.TSH
	     dependencias(ExpBool.a("err"),Exp0.a("err"),Exp1.a("err"));//ExpBool.err = Exp0.err  OR Exp1.err
	     dependencias(ExpBool.a("tipo"),Exp0.a("tipo"),Exp1.a("tipo"));//ExpBool.tipo = Exp0.tipo  OR Exp1.tipo 
	     dependencias(Exp0.a("etqh"),ExpBool.a("etqh"));//Exp0.etqh=ExpBool.etqh
	     dependencias(Exp1.a("etqh"),Exp0.a("etq"));//Exp1.etqh=Exp0.etq
	     dependencias(ExpBool.a("etq"),Exp1.a("etq"));//ExpBool.etq=Exp1.etq+1
	     dependencias(ExpBool.a("cod"),Exp0.a("cod"),Exp1.a("cod"),Op0.a("cod"));//ExpBool.cod= Exp0.cod||Exp1.cod||Op0.cod
	     
	     
	     calculo(Exp0.a("TSH"),asignacion);
	     calculo(ExpBool.a("err"),asignacionOR2);
	     calculo(ExpBool.a("tipo"),asignacionOR2);
	     calculo(Exp0.a("etqh"),asignacion);
	     calculo(Exp1.a("etqh"),asignacion);
	     calculo(Exp1.a("etq"),sumauno);
	     calculo(ExpBool.a("etq"),asignacionOR2);
	    // calculo(ExpBool.a("cod"),concatenarExpBool);
	     
	     return ExpBool;
	    
	}
	public TAtributos Exp0(TAtributos Exp01,TAtributos Op0,TAtributos Exp02){
	    
		regla("Exp → Exp0 Op0 Exp0");
	     
	     TAtributos Exp0 = atributosPara("Exp0", "TSH","tipo","err","cod","etq");
	     
	     dependencias(Exp01.a("TSH"),Exp0.a("TSH"));//Exp0.TSH = Exp.TSH
	     dependencias(Exp0.a("err"),Exp01.a("err"),Exp02.a("err"));//Exp.err = Exp01.err  OR Exp02.err
	     dependencias(Exp0.a("tipo"),Exp01.a("tipo"),Exp02.a("tipo"));//Exp.tipo = Exp01.tipo  OR Exp02.tipo 
	     dependencias(Exp01.a("etqh"),Exp0.a("etqh"));//Exp01.etqh=Exp0.etqh
	     dependencias(Exp02.a("etqh"),Exp01.a("etq"));//Exp02.etqh=Exp01.etq
	     dependencias(Exp0.a("etq"),Exp01.a("etq"));//Exp0.etq=Exp01.etq+1
	     dependencias(Exp0.a("cod"),Exp01.a("cod"),Exp02.a("cod"),Op0.a("cod"));//ExpBool.cod= Exp0.cod||Exp1.cod||Op0.cod
	     
	     
	     calculo(Exp01.a("TSH"),asignacion);
	     calculo(Exp0.a("err"),asignacionOR2);
	     calculo(Exp0.a("tipo"),asignacionOR2);
	     calculo(Exp01.a("etqh"),asignacion);
	     calculo(Exp02.a("etqh"),asignacion);
	     calculo(Exp01.a("etq"),sumauno);
	     calculo(Exp0.a("etq"),asignacion);
	    // calculo(Exp0.a("cod"),concatenarExp0);
	     
	     return Exp0;
	    
	}


	public TAtributos Exp1(TAtributos Exp0){
	regla("Exp → Exp0");

	TAtributos Exp1 = atributosPara("Exp1","TSH","tipo","err","cod","etq","etqh");

	dependencias(Exp0.a("TSH"),Exp1.a("TSH"));//Exp0.TSH = Exp.TSH
	dependencias(Exp1.a("err"),Exp0.a("err"));//Exp.err = Exp0.err
	dependencias(Exp1.a("tipo"),Exp0.a("tipo"));//Exp.tipo = Exp0.tipo 
	dependencias(Exp0.a("etqh"),Exp1.a("etqh"));//Exp0.etqh= Exp.etqh
	dependencias(Exp1.a("etq"),Exp0.a("etq"));//Exp.etq=Exp0.etq
	dependencias(Exp1.a("cod"),Exp0.a("cod"));//Exp.cod= Exp0.cod

	calculo(Exp0.a("TSH"),asignacion);
	calculo(Exp1.a("err"),asignacion);
	calculo(Exp1.a("tipo"),asignacion);
	calculo(Exp0.a("etqh"),asignacion);
	calculo(Exp1.a("etq"),asignacion);
	calculo(Exp1.a("cod"),asignacion);

	return Exp1;
	}



	/*Exp00 → Exp01 Op1 Exp1 
	Exp01.TSH = Exp00.TSH
	Exp1.TSH = Exp01.TSH
	Exp00.err = Exp01.err  OR Exp1.err
	Exp00.tipo = Exp01.tipo OR Exp1.tipo 
	Exp01.etqh=Exp00.etqh 
	Exp1.etqh=Exp01.etqh
	Exp00.etq=Exp1.etq+1
	Exp00.cod= Exp01.cod||Exp1.cod||Op1.cod 
	*/ 
	public TAtributos Exp00(TAtributos Exp01,TAtributos Op1,TAtributos Exp1){

		regla("Exp0 → Exp0 Op1 Exp1");
	     
	     TAtributos Exp00 = atributosPara("Exp00", "TSH","tipo","err","cod","etq");
	     
	     dependencias(Exp01.a("TSH"),Exp00.a("TSH"));//Exp01.TSH = Exp00.TSH
	     dependencias(Exp1.a("TSH"),Exp01.a("TSH"));//Exp1.TSH = Exp01.TSH
	     dependencias(Exp00.a("err"),Exp01.a("err"),Exp1.a("err"));//Exp00.err = Exp01.err  OR Exp1.err
	     dependencias(Exp00.a("tipo"),Exp01.a("tipo"),Exp1.a("tipo"));//Exp00.tipo = Exp01.tipo OR Exp1.tipo
	     dependencias(Exp01.a("etqh"),Exp00.a("etqh"));//Exp01.etqh=Exp00.etqh
	     dependencias(Exp1.a("etqh"),Exp01.a("etqh"));//Exp1.etqh=Exp01.etqh
	     dependencias(Exp00.a("etq"),Exp1.a("etq"));//Exp00.etq=Exp1.etq+1
	     dependencias(Exp00.a("cod"),Exp01.a("cod"),Exp1.a("cod"),Op1.a("cod"));//Exp00.cod= Exp01.cod||Exp1.cod||Op1.cod 
	     
	     calculo(Exp01.a("TSH"),asignacion);
	     calculo(Exp1.a("TSH"),asignacion);
	     calculo(Exp00.a("err"),asignacionOR2);
	     calculo(Exp00.a("tipo"),asignacionOR2);
	     calculo(Exp01.a("etqh"),asignacion);
	     calculo(Exp1.a("etqh"),asignacion);
	     calculo(Exp1.a("etq"),sumauno);
	     calculo(Exp00.a("etq"),asignacion);
	     //calculo(Exp00.a("cod"),concatenarExp00);
	     
	     return Exp00;   
	}
	/*Exp01 → Exp0 OR Exp1 
	Exp0.TSH = Exp00.TSH
	Exp1.TSH = Exp01.TSH
	Exp01.err = Exp01.err  OR Exp1.err
	Exp01.tipo = Exp0.tipo OR Exp1.tipo
	Exp01.cod = Exp0.cod || copia || ir_v(Exp00.irvh) || desapila || Exp1.cod
	Exp0.etqh = Exp01.etqh
	Exp1.etqh = Exp01.etq + 3
	Exp01.etq = Exp1.etq
	Exp01.irvh = Exp1.etq
	Exp01.irfh = Exp1.etq
	Exp0.irvh = Exp01.irvh
	Exp0.irfh = Exp01.etq + 2
	Exp1.irvh = Exp01.irvh
	Exp1.irfh = Exp01.irfh
	*/
	public TAtributos Exp01(TAtributos Exp0,TAtributos Exp1){
	    
		regla("Exp0 → Exp0 OR Exp1");
	     
	     TAtributos Exp01 = atributosPara("Exp01", "TSH","tipo","err","cod","etq","irvh","irfh");
	     
	     dependencias(Exp0.a("TSH"),Exp01.a("TSH"));// Exp0.TSH = Exp01.TSH
	     dependencias(Exp1.a("TSH"),Exp01.a("TSH"));//Exp1.TSH = Exp01.TSH
	     dependencias(Exp01.a("err"),Exp0.a("err"),Exp1.a("err"));//Exp01.err = Exp0.err  OR Exp1.err
	     dependencias(Exp01.a("tipo"),Exp0.a("tipo"),Exp1.a("tipo"));//Exp01.tipo = Exp0.tipo OR Exp1.tipo
	     dependencias(Exp01.a("cod"),Exp0.a("cod"),Exp0.a("irvh"),Exp1.a("cod"));//Exp01.cod = Exp0.cod || copia || ir_v(Exp0.irvh) || desapila || Exp1.cod
	     dependencias(Exp0.a("etqh"),Exp01.a("etqh"));//Exp0.etqh = Exp01.etqh
	     dependencias(Exp1.a("etqh"),Exp01.a("etqh"));//Exp1.etqh = Exp01.etq + 3
	     dependencias(Exp01.a("etq"),Exp1.a("etq"));//Exp01.etq = Exp1.etq 
	     
	     dependencias(Exp01.a("irvh"),Exp1.a("etq"));//Exp01.irvh = Exp1.etq
	     dependencias(Exp01.a("irfh"),Exp1.a("etq"));//Exp01.irfh = Exp1.etq
	     dependencias(Exp0.a("irvh"),Exp01.a("irvh"));//Exp0.irvh = Exp01.irvh
	     dependencias(Exp0.a("irfh"),Exp01.a("etq"));//Exp0.irfh = Exp01.etq + 2
	     dependencias(Exp1.a("irvh"),Exp01.a("irvh"));//Exp1.irvh = Exp01.irvh
	     dependencias(Exp1.a("irfh"),Exp01.a("irfh"));//Exp1.irfh = Exp01.irfh
	     
	     calculo(Exp0.a("TSH"),asignacion);
	     calculo(Exp1.a("TSH"),asignacion);
	     calculo(Exp01.a("err"),asignacionOR2);
	     calculo(Exp01.a("tipo"),asignacionOR2);
	     //calculo(Exp01.a("cod"),concatenarExp01);
	     calculo(Exp0.a("etqh"),asignacion);
	     calculo(Exp01.a("etq"),sumaTres);
	     calculo(Exp1.a("etqh"),asignacion);
	     calculo(Exp01.a("etq"),asignacion);
	     
	     calculo(Exp01.a("irvh"),asignacion);
	     calculo(Exp01.a("irfh"),asignacion);
	     calculo(Exp0.a("irvh"),asignacion);
	     calculo(Exp01.a("etq"),sumaDos);
	     calculo(Exp1.a("irvh"),asignacion);
	     calculo(Exp1.a("irfh"),asignacion);
	     return Exp01;   
	}

	/*Exp02 → Exp1
	       Exp1.TSH = Exp02.TSH
	       Exp02.err =  Exp1.err
	       Exp02.tipo =  Exp1.tipo
	       Exp1.etqh= Exp02.etqh
	       Exp02.etq= Exp1.etq  
	       Exp02.cod= Exp1.cod
	*/
	    
	public TAtributos Exp02(TAtributos Exp1){
	    
		regla("Exp0 → Exp1");
	     
	     TAtributos Exp02 = atributosPara("Exp02","TSH","tipo","err","cod","etq","etqh");
	     
	     dependencias(Exp1.a("TSH"),Exp02.a("TSH"));//Exp1.TSH = Exp02.TSH
	     dependencias(Exp02.a("err"),Exp1.a("err"));//Exp02.err =  Exp1.err
	     dependencias(Exp02.a("tipo"),Exp1.a("tipo"));//Exp02.tipo =  Exp1.tipo 
	     dependencias(Exp1.a("etqh"),Exp02.a("etqh"));//Exp1.etqh= Exp02.etqh
	     dependencias(Exp02.a("etq"),Exp1.a("etq"));//Exp02.etq= Exp1.etq
	     dependencias(Exp02.a("cod"),Exp1.a("cod"));//Exp02.cod= Exp1.cod
	     
	     calculo(Exp1.a("TSH"),asignacion);
	     calculo(Exp02.a("err"),asignacion);
	     calculo(Exp02.a("tipo"),asignacion);
	     calculo(Exp1.a("etqh"),asignacion);
	     calculo(Exp02.a("etq"),asignacion);
	     calculo(Exp02.a("cod"),asignacion);
	     
	     return Exp1;
	    
	}
   
	/*Exp1 → Exp1 Op2 Exp2 
    Exp11.TSH = Exp10.TSH
    Exp2.TSH = Exp11.TSH
    Exp10.err = Exp11.err  OR Exp2.err
    Exp10.tipo = Exp11.tipo  OR Exp2.tipo 
    Exp11.etqh=Exp10.etqh
   Exp2.etqh=Exp11.etq
   Exp1.etq=Exp2.etq+1 
   Exp1.cod= Exp1.cod||Exp2.cod||Op2.cod 
*/
public TAtributos Exp10(TAtributos Exp11,TAtributos Op2,TAtributos Exp2){

regla("Exp1 → Exp1 Op2 Exp2");
 
 TAtributos Exp10 = atributosPara("Exp10", "TSH","tipo","err","cod","etq","etqh");
 
 dependencias(Exp11.a("TSH"),Exp10.a("TSH"));//Exp11.TSH = Exp10.TSH
 dependencias(Exp2.a("TSH"),Exp11.a("TSH"));//Exp2.TSH = Exp11.TSH
 dependencias(Exp10.a("err"),Exp11.a("err"),Exp2.a("err"));//Exp10.err = Exp11.err  OR Exp2.err
 dependencias(Exp10.a("tipo"),Exp11.a("tipo"),Exp2.a("tipo"));//Exp10.tipo = Exp11.tipo  OR Exp2.tipo
 dependencias(Exp11.a("etqh"),Exp10.a("etqh"));//Exp11.etqh=Exp10.etqh
 dependencias(Exp2.a("etqh"),Exp11.a("etqh"));//Exp2.etqh=Exp11.etq
 dependencias(Exp10.a("etq"),Exp2.a("etq"));//Exp10.etq=Exp2.etq+1  
 dependencias(Exp10.a("cod"),Exp11.a("cod"),Exp2.a("cod"),Op2.a("cod"));//Exp1.cod= Exp1.cod||Exp2.cod||Op2.cod 
 
 calculo(Exp11.a("TSH"),asignacion);
 calculo(Exp2.a("TSH"),asignacion);
 calculo(Exp10.a("err"),asignacionOR2);
 calculo(Exp10.a("tipo"),asignacionOR2);
 calculo(Exp11.a("etqh"),asignacion);
 calculo(Exp2.a("etqh"),asignacion);
 calculo(Exp2.a("etq"),sumauno);
 calculo(Exp10.a("etq"),asignacion);
 calculo(Exp10.a("cod"),concatenarExp10);
 
 return Exp10;   
}

/*Exp1 → Exp1 AND Exp2 
        Exp12.TSH = Exp11.TSH
        Exp2.TSH = Exp12.TSH
        Exp11.err = Exp12.err  OR Exp2.err
        Exp11.tipo = Exp12.tipo  OR Exp2.tipo
        Exp11.cod = Exp12.cod || copia || ir_f(Exp11.irfh) || desapila || Exp2.cod
        Exp11.etqh = Exp11.etqh
        Exp2.etqh = Exp12.etq + 3
        Exp10.etq = Exp2.etq
        Exp10.irvh = Exp2.etq
        Exp10.irfh = Exp2.etq
        Exp12.irvh = Exp12.etq + 2
        Exp12.irfh = Exp11.irfh
        Exp2.irvh = Exp11.irvh
        Exp2.irfh = Exp11.irfh
*/
   
public TAtributos Exp11(TAtributos Exp12,TAtributos Exp2){
    
	regla("Exp1 → Exp1 AND Exp2");
     
     TAtributos Exp11 = atributosPara("Exp01", "TSH","tipo","err","cod","etq","irvh","irfh");
     
     dependencias(Exp12.a("TSH"),Exp11.a("TSH"));// Exp12.TSH = Exp11.TSH
     dependencias(Exp2.a("TSH"),Exp12.a("TSH"));//Exp2.TSH = Exp12.TSH
     dependencias(Exp11.a("err"),Exp12.a("err"),Exp2.a("err"));//Exp11.err = Exp12.err  OR Exp2.err
     dependencias(Exp11.a("tipo"),Exp12.a("tipo"),Exp2.a("tipo"));//Exp11.tipo = Exp12.tipo  OR Exp2.tipo
     dependencias(Exp11.a("cod"),Exp12.a("cod"),Exp11.a("irfh"),Exp2.a("cod"));//Exp11.cod = Exp12.cod || copia || ir_f(Exp11.irfh) || desapila || Exp2.cod
     dependencias(Exp12.a("etqh"),Exp11.a("etqh"));//Exp12.etqh = Exp11.etqh
     dependencias(Exp2.a("etqh"),Exp12.a("etqh"));//Exp2.etqh = Exp12.etq + 3
     dependencias(Exp11.a("etq"),Exp2.a("etq"));//Exp11.etq = Exp2.etq
     
     dependencias(Exp11.a("irvh"),Exp2.a("etq"));//Exp11.irvh = Exp2.etq
     dependencias(Exp11.a("irfh"),Exp2.a("etq"));//Exp11.irfh = Exp2.etq
     dependencias(Exp12.a("irvh"),Exp12.a("irvh"));//Exp12.irvh = Exp12.etq + 2
     dependencias(Exp12.a("irfh"),Exp11.a("etq"));//Exp12.irfh = Exp11.irfh
     dependencias(Exp2.a("irvh"),Exp11.a("irvh"));//Exp2.irvh = Exp11.irvh
     dependencias(Exp2.a("irfh"),Exp11.a("irfh"));//Exp2.irfh = Exp11.irfh
     
     calculo(Exp12.a("TSH"),asignacion);
     calculo(Exp2.a("TSH"),asignacion);
     calculo(Exp11.a("err"),asignacionOR2);
     calculo(Exp11.a("tipo"),asignacionOR2);
     //calculo(Exp11.a("cod"),concatenarExp11);
     calculo(Exp12.a("etqh"),asignacion);
     calculo(Exp12.a("etq"),sumaTres);
     calculo(Exp2.a("etqh"),asignacion);
     calculo(Exp11.a("etq"),asignacion);
     
     calculo(Exp11.a("irvh"),asignacion);
     calculo(Exp11.a("irfh"),asignacion);
     calculo(Exp12.a("etq"),sumaDos);
     calculo(Exp12.a("irvh"),asignacion);
     calculo(Exp12.a("irfh"),asignacion);
     calculo(Exp2.a("irvh"),asignacion);
     calculo(Exp2.a("irfh"),asignacion);
     return Exp11;   
} 
   
 /*Exp1 → Exp2
       Exp2.TSH = Exp12.TSH
       Exp12.err =  Exp2.err
       Exp12.tipo =  Exp2.tipo
       Exp2.etqh= Exp12.etqh
       Exp12.etq= Exp2.etq  
       Exp12.cod= Exp2.cod 
*/

public TAtributos Exp12(TAtributos Exp2){
    
	regla("Exp1 → Exp2");
     
     TAtributos Exp12 = atributosPara("Exp12","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp2.a("TSH"),Exp12.a("TSH"));//Exp2.TSH = Exp12.TSH
     dependencias(Exp12.a("err"),Exp2.a("err"));//Exp12.err =  Exp2.err
     dependencias(Exp12.a("tipo"),Exp2.a("tipo"));//Exp12.tipo =  Exp2.tipo 
     dependencias(Exp2.a("etqh"),Exp12.a("etqh"));//Exp2.etqh= Exp12.etqh
     dependencias(Exp12.a("etq"),Exp2.a("etq"));//Exp12.etq= Exp2.etq
     dependencias(Exp12.a("cod"),Exp2.a("cod"));//Exp12.cod= Exp2.cod
     
     calculo(Exp2.a("TSH"),asignacion);
     calculo(Exp12.a("err"),asignacion);
     calculo(Exp12.a("tipo"),asignacion);
     calculo(Exp2.a("etqh"),asignacion);
     calculo(Exp12.a("etq"),asignacion);
     calculo(Exp12.a("cod"),asignacion);
     
     return Exp12;
    
}
   
 /*Exp2 → Exp3 Op3 Exp2 
       Exp3.TSH = Exp20.TSH
       Exp21.TSH = Exp3.TSH 
        Exp20.err = Exp3.err OR Exp21.err
        Exp20.tipo = Exp3.tipo OR Exp21.tipo 
       Exp3.etqh=Exp20.etqh 
       Exp21.etqh=Exp3.etq
       Exp20.etq=Exp21.etq+1
       Exp20.cod= Exp3.cod||Exp2.cod||Op3.cod 
*/  
public TAtributos Exp20(TAtributos Exp3,TAtributos Op3,TAtributos Exp21){

	regla("Exp2 → Exp3 Op3 Exp2");
 
	TAtributos Exp20 = atributosPara("Exp20", "TSH","tipo","err","cod","etq","etqh");
 
	dependencias(Exp3.a("TSH"),Exp20.a("TSH"));//Exp3.TSH = Exp20.TSH
	dependencias(Exp21.a("TSH"),Exp3.a("TSH"));//Exp21.TSH = Exp3.TSH
 	dependencias(Exp20.a("err"),Exp3.a("err"),Exp21.a("err"));//Exp20.err = Exp3.err OR Exp21.err
 	dependencias(Exp20.a("tipo"),Exp3.a("tipo"),Exp21.a("tipo"));//Exp20.tipo = Exp3.tipo OR Exp21.tipo
 	dependencias(Exp3.a("etqh"),Exp20.a("etqh"));//Exp3.etqh=Exp20.etqh
 	dependencias(Exp21.a("etqh"),Exp3.a("etqh"));//Exp21.etqh=Exp3.etq
 	dependencias(Exp20.a("etq"),Exp21.a("etq"));//Exp20.etq=Exp21.etq+1  
 	dependencias(Exp20.a("cod"),Exp3.a("cod"),Exp21.a("cod"),Op3.a("cod"));//Exp20.cod= Exp3.cod||Exp21.cod||Op3.cod 
 
 	calculo(Exp3.a("TSH"),asignacion);
 	calculo(Exp21.a("TSH"),asignacion);
 	calculo(Exp20.a("err"),asignacionOR2);
 	calculo(Exp20.a("tipo"),asignacionOR2);
 	calculo(Exp3.a("etqh"),asignacion);
 	calculo(Exp21.a("etqh"),asignacion);
 	calculo(Exp21.a("etq"),sumauno);
 	calculo(Exp20.a("etq"),asignacion);
 	//calculo(Exp20.a("cod"),concatenarExp20);
 
 return Exp20;   
}  
 
/*Exp2 → Exp3
       Exp3.TSH = Exp21.TSH
       Exp21.err = Exp3.err
       Exp21.tipo = Exp3.tipo
       Exp3.etqh= Exp21.etqh
       Exp21.etq= Exp3.etq  
       Exp21.cod= Exp3.cod
*/
  
public TAtributos Exp21(TAtributos Exp3){
    
	regla("Exp2 → Exp3");
     
     TAtributos Exp21 = atributosPara("Exp21","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp3.a("TSH"),Exp21.a("TSH"));//Exp3.TSH = Exp21.TSH
     dependencias(Exp21.a("err"),Exp3.a("err"));//Exp21.err = Exp3.err
     dependencias(Exp21.a("tipo"),Exp3.a("tipo"));//Exp21.tipo = Exp3.tipo 
     dependencias(Exp3.a("etqh"),Exp21.a("etqh"));//Exp3.etqh= Exp21.etqh
     dependencias(Exp21.a("etq"),Exp3.a("etq"));//Exp21.etq= Exp3.etq
     dependencias(Exp21.a("cod"),Exp3.a("cod"));//Exp21.cod= Exp3.cod
     
     calculo(Exp3.a("TSH"),asignacion);
     calculo(Exp21.a("err"),asignacion);
     calculo(Exp21.a("tipo"),asignacion);
     calculo(Exp3.a("etqh"),asignacion);
     calculo(Exp21.a("etq"),asignacion);
     calculo(Exp21.a("cod"),asignacion);
     
     return Exp21;
    
}
    
/*Exp3 → Op41 Designador
Exp30.err = NOT EstaId? (Exp30.TSH, desginador.Lex) 
        Exp30.tipo = desginador.tipo
        Exp30.etq= Exp3.etqh + 2
        Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
*/  
    
public TAtributos Exp30(TAtributos Op41,TAtributos Desig){
    
	regla("Exp3 → Op41 Designador");
     
     TAtributos Exp30 = atributosPara("Exp30","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp30.a("err"),Exp30.a("TSH"),Desig.a("lex"));//Exp30.err = NOT EstaId? (Exp30.TSH, desginador.Lex)
     dependencias(Exp30.a("tipo"),Desig.a("tipo"));//Exp30.tipo = desginador.tipo
     dependencias(Exp30.a("etq"),Exp30.a("etqh"));//Exp30.etq= Exp3.etqh + 2 
     dependencias(Exp30.a("cod"),Exp30.a("TSH"),Desig.a("lex"),Op41.a("cod"));//Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
     
     calculo(Exp30.a("err"),asignacion);
     calculo(Exp30.a("tipo"),asignacion);
     calculo(Exp30.a("etqh"),sumaDos);
     calculo(Exp30.a("etq"),asignacion);
     //calculo(Exp30.a("cod"),concatenarExp30);
     
     return Exp30;
    
} 

/*Exp3 → Op42 Exp3 
        Exp3.TSH = Exp31.TSH
        Exp31.err = Exp3.err
        Exp31.tipo = Exp3.tipo
       Exp3.etqh= Exp31.etqh 
       Exp31.etq= Exp3.etq + 1
       Exp31.cod= apila(1)||Exp3.cod || Op42.cod
*/

public TAtributos Exp31(TAtributos Op42,TAtributos Exp3){
    
	regla("Exp3 → Op42 Exp3");
     
     TAtributos Exp31 = atributosPara("Exp31","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp3.a("TSH"),Exp31.a("TSH"));//Exp3.TSH = Exp31.TSH
     dependencias(Exp31.a("err"),Exp3.a("err"));//Exp31.err = Exp3.err
     dependencias(Exp31.a("tipo"),Exp3.a("tipo"));//Exp31.tipo = Exp3.tipo 
     dependencias(Exp3.a("etqh"),Exp31.a("etqh"));//Exp3.etqh= Exp31.etqh
     dependencias(Exp31.a("etq"),Exp3.a("etq"));//Exp31.etq= Exp3.etq + 1
     dependencias(Exp31.a("cod"),Exp3.a("cod"),Op42.a("cod"));//Exp31.cod= apila(1)||Exp3.cod || Op42.cod 
     
     calculo(Exp3.a("TSH"),asignacion);
     calculo(Exp31.a("err"),asignacion);
     calculo(Exp31.a("tipo"),asignacion);
     calculo(Exp3.a("etqh"),asignacion);
     calculo(Exp3.a("etq"),sumauno);
     calculo(Exp31.a("etq"),asignacion);
     //calculo(Exp31.a("cod"),concatenarExp31);
     
     return Exp31;
    
} 

/*Exp3 → - (Exp3) 
        Exp3.TSH = Exp32.TS
        Exp32.err = Exp3.err
        Exp32.tipo = Exp3.tipo
        Exp32.cod= apila(0)||Exp3.cod||resta
        Exp32.etq = Exp3.etqh+1;
*/

public TAtributos Exp32(TAtributos Exp3){
    
	regla("Exp3 → - (Exp3)");
     
     TAtributos Exp32 = atributosPara("Exp32","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp3.a("TSH"),Exp32.a("TS"));//Exp3.TSH = Exp32.TS
     dependencias(Exp32.a("err"),Exp3.a("err"));//Exp32.err = Exp3.err
     dependencias(Exp32.a("tipo"),Exp3.a("tipo"));//Exp32.tipo = Exp3.tipo 
     dependencias(Exp32.a("cod"),Exp3.a("cod"));//Exp32.cod= apila(0)||Exp3.cod||resta
     dependencias(Exp32.a("etq"),Exp3.a("etq"));//Exp32.etq = Exp3.etqh+1; 
     
     calculo(Exp3.a("TSH"),asignacion);
     calculo(Exp32.a("err"),asignacion);
     calculo(Exp32.a("tipo"),asignacion);
     //calculo(Exp2.a("cod"),concatenarExp32);
     calculo(Exp3.a("etqh"),sumauno);
     calculo(Exp32.a("etq"),asignacion);
     
     return Exp32;
    
}

/*Exp3 → Designador
       Exp33.err = NOT EstaId? (Exp33.TSH, desginador.Lex)
       Exp33.tipo = Designador.tipo
       Exp33.cod=apila
       Exp33.etq = Exp33.etqh+1;
*/

public TAtributos Exp33(TAtributos Desig){
    
	regla("Exp3 → Designador");
     
     TAtributos Exp33 = atributosPara("Exp33","TSH","tipo","err","cod","etq","etqh");
     
     dependencias(Exp33.a("err"),Exp33.a("TSH"),Desig.a("lex"));//Exp33.err = NOT EstaId? (Exp33.TSH, desginador.Lex)
     dependencias(Exp33.a("tipo"),Desig.a("tipo"));//Exp33.tipo = Designador.tipo
    // dependencias(Exp33.a("cod"),Exp3.a("tipo"));//Exp33.cod=apila 
     dependencias(Exp33.a("etq"),Exp33.a("etqh"));//Exp33.etq = Exp33.etqh+1; 
     
     //calculo(Exp3.a("err"),asignacion);¿¿¿¿????
     calculo(Exp33.a("tipo"),asignacion);
     calculo(Exp33.a("cod"),asignacion);
     calculo(Exp33.a("etqh"),sumauno);
     calculo(Exp33.a("etq"),asignacion);
     
     return Exp33;
    
}

/*Exp3 → Valores
        Exp3.tipo = Valores.tipo
        Exp3.cod=apila
        Exp3.etq = Exp3.etqh+1;
*/

public TAtributos Exp34(TAtributos Valores){
    
	regla("Exp3 → Valores");
     
     TAtributos Exp34 = atributosPara("Exp34","tipo","cod","etq","etqh");
     
     dependencias(Exp34.a("tipo"),Valores.a("tipo"));//Exp34.tipo = Valores.tipo
     //dependencias(Exp34.a("cod"));//Exp34.cod=apila
     dependencias(Exp34.a("etq"),Exp34.a("etqh"));// Exp34.etq = Exp3.etqh+1; 
     
     calculo(Exp34.a("tipo"),asignacion);
     calculo(Exp34.a("cod"),asignacion);
     calculo(Exp34.a("etqh"),sumauno);
     calculo(Exp34.a("etq"),asignacion);
     
     return Exp34;
    
}

/*Exp3 → (Exp)
       Exp.TSH= Exp35.TSH
       Exp35.err = Exp.err
       Exp35.tipo = Exp.tipo
       Exp.etqh= Exp35.etqh
       Exp35.etq= Exp.etq  
       Exp35.cod=Exp.cod
       Exp.irvh= Exp35.irvh
       Exp.irfh= Exp35.irfh
*/

public TAtributos Exp35(TAtributos Exp){
    
	regla("Exp3 → (Exp)");
     
     TAtributos Exp35 = atributosPara("Exp35","tipo","cod","etq","etqh","TS","TSH","irvh","irfh");
     
     dependencias(Exp.a("TSH"),Exp35.a("TSH"));//Exp.TSH= Exp35.TSH
     dependencias(Exp35.a("err"),Exp.a("err"));//Exp35.err = Exp.err
     dependencias(Exp35.a("tipo"),Exp.a("tipo"));//Exp35.tipo = Exp.tipo 
     dependencias(Exp.a("etqh"),Exp35.a("etqh"));//Exp.etqh= Exp35.etqh
     dependencias(Exp35.a("etq"),Exp.a("etq"));//Exp35.etq= Exp.etq 
     dependencias(Exp35.a("cod"),Exp.a("cod"));//Exp35.cod=Exp.cod 
     dependencias(Exp.a("irvh"),Exp35.a("tipo"));//Exp.irvh= Exp35.irvh
     dependencias(Exp.a("irfh"),Exp35.a("irfh"));//Exp.irfh= Exp35.irfh
     
     calculo(Exp.a("TSH"),asignacion);
     calculo(Exp35.a("err"),asignacion);
     calculo(Exp35.a("tipo"),asignacion);
     calculo(Exp.a("etqh"),asignacion);
     calculo(Exp35.a("etq"),asignacion);
     calculo(Exp35.a("cod"),asignacion);
     calculo(Exp.a("irvh"),asignacion);
     calculo(Exp.a("irfh"),asignacion);
     
     return Exp35;
    
}

/*Op0 → >
      Op0.cod= mayor
*/

public TAtributos Op00(){
    
	regla("Op0 → >");
     
     TAtributos Op00 = atributosPara("Op00","cod");
     
     //dependencias(Exp.a("TSH"),Exp35.a("TSH"));//Exp.TSH= Exp35.TSH
     
     calculo(Op00.a("cod"),asignaOp00);
     
     return Op00;
     
 }

/*Op0 → <
      Op0.cod= menor
*/

public TAtributos Op01(){
    
	regla("Op0 → <");
     
     TAtributos Op01 = atributosPara("Op01","cod");
     
     //dependencias(Exp.a("TSH"),Exp35.a("TSH"));//Exp.TSH= Exp35.TSH
     
     calculo(Op01.a("cod"),asignaOp01);
     
     return Op01;
     
 }

/*Op0 → <=
      Op0.cod= menor igual
*/

public TAtributos Op02(){
    
	regla("Op0 → <=");
     
     TAtributos Op02 = atributosPara("Op02","cod");
     
     calculo(Op02.a("cod"),asignaOp02);
     
     return Op02;
     
 }

/*Op0 → >=
      Op0.cod= mayor igual
*/

public TAtributos Op03(){
    
	regla("Op0 → >=");
     
     TAtributos Op03 = atributosPara("Op03","cod");
     
     calculo(Op03.a("cod"),asignaOp03);
     
     return Op03;
     
 }

/*Op0 → ==
      Op0.cod= igual
*/

public TAtributos Op04(){
    
	regla("Op0 → ==");
     
     TAtributos Op04 = atributosPara("Op04","cod");
     
     calculo(Op04.a("cod"),asignaOp04);
     
     return Op04;
     
 }

/*Op0 → !=
      Op0.cod= distinto
*/

public TAtributos Op05(){
    
	regla("Op0 → !=");
     
     TAtributos Op05 = atributosPara("Op05","cod");
     
     calculo(Op05.a("cod"),asignaOp05);
     
     return Op05;
     
 }

/*Op1 → +
      Op1.cod= suma
*/

public TAtributos Op10(){
    
	regla("Op1 → +");
     
     TAtributos Op10 = atributosPara("Op10","cod");
     
     calculo(Op10.a("cod"),asignaOp10);
     
     return Op10;
     
 }

/*Op1 → -
      Op1.cod= resta
*/

public TAtributos Op11(){
    
	regla("Op1 → -");
     
     TAtributos Op11 = atributosPara("Op11","cod");
     
     calculo(Op11.a("cod"),asignaOp11);
     
     return Op11;
     
 }

/*Op2 → *
      Op2.cod= multiplicación
*/

public TAtributos Op20(){
    
	regla("Op2 → *");
     
     TAtributos Op20 = atributosPara("Op20","cod");
     
     calculo(Op20.a("cod"),asignaOp20);
     
     return Op20;
     
 }

/*Op2 → /
      Op2.cod= división
*/

public TAtributos Op21(){
    
	regla("Op2 → /");
     
     TAtributos Op21 = atributosPara("Op21","cod");
     
     calculo(Op21.a("cod"),asignaOp21);
     
     return Op21;
     
 }

/*Op2 → %
      Op2.cod= modulo
*/

public TAtributos Op22(){
    
	regla("Op2 → %");
     
     TAtributos Op22 = atributosPara("Op22","cod");
     
     calculo(Op22.a("cod"),asignaOp22);
     
     return Op22;
     
 }

/*Op3 → <<
      Op3.cod= desplazamiento izquierda
*/

public TAtributos Op30(){
    
	regla("Op3 → <<");
     
     TAtributos Op30 = atributosPara("Op30","cod");
     
     calculo(Op30.a("cod"),asignaOp30);
     
     return Op30;
     
 }

/*Op3 → >>
      Op3.cod= desplazamiento derecha
*/

public TAtributos Op31(){
    
	regla("Op3 → >>");
     
     TAtributos Op31 = atributosPara("Op31","cod");
     
     calculo(Op31.a("cod"),asignaOp31);
     
     return Op31;
     
 }

/*Op41 → (char)
      Op41.cod= conversiónChar
*/

public TAtributos Op410(){
    
	regla("Op41 → (char)");
     
     TAtributos Op410 = atributosPara("Op410","cod");
     
     calculo(Op410.a("cod"),asignaOp410);
     
     return Op410;
     
 }

/*Op41 → (int)
      Op41.cod= conversiónInt
*/

public TAtributos Op411(){
    
	regla("Op41 → (int)");
     
     TAtributos Op411 = atributosPara("Op411","cod");
     
     calculo(Op411.a("cod"),asignaOp411);
     
     return Op411;
     
 }

/*Op41→ (nat)
      Op41.cod= conversiónNat
*/

public TAtributos Op412(){
    
	regla("Op41 → (nat)");
     
     TAtributos Op412 = atributosPara("Op412","cod");
     
     calculo(Op412.a("cod"),asignaOp412);
     
     return Op412;
     
 }

/*Op41 → (float)
      Op41.cod= conversiónFloat
*/

public TAtributos Op413(){
    
	regla("Op41 → (float)");
     
     TAtributos Op413 = atributosPara("Op413","cod");
     
     calculo(Op413.a("cod"),asignaOp413);
     
     return Op413;
     
 }

/*Op42 → not
      Op41.cod= negación logica
*/

public TAtributos Op420(){
    
	regla("Op42 → not");
     
     TAtributos Op420 = atributosPara("Op420","cod");
     
     calculo(Op420.a("cod"),asignaOp420);
     
     return Op420;
     
 }

}