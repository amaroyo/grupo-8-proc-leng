package src.plg.proto;

import java.util.ArrayList;
import java.util.Iterator;



import src.es.ucm.fdi.plg.evlib.Atribucion;
import src.es.ucm.fdi.plg.evlib.Atributo;
import src.es.ucm.fdi.plg.evlib.LAtributo;
import src.es.ucm.fdi.plg.evlib.SemFun;
import src.es.ucm.fdi.plg.evlib.TAtributos;
// DefiniciÃ³n de las funciones semÃ¡nticas
import src.plg.proto.TS;
import src.plg.proto.Tipo;
import tiposNuevos.TiposArray;
import tiposNuevos.TiposTupla;



/////////////////////////  TS

class creaTS implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	return new TS();
    }

}

class añadirTS implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
		TS tablasimbolos = (TS)args[0].valor();
		String id = (String)args[1].valor();
		String clase = (String)args[2].valor();
		String nivel = (String)args[3].valor();
		String dir = (String)args[4].valor();
		Tipo tipo = (Tipo)args[5].valor();
		int valor = (Integer)args[6].valor();
		
		tablasimbolos.añadir(id, clase, nivel, dir, tipo,valor);
    	return tablasimbolos;
    }

}



class creaTPR implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	return new TPR();
    }

}

class creaTSL implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	TS tablasimbolos = (TS)args[0].valor();
    	return tablasimbolos.copia();
    }

}


class dirMasTamaño implements SemFun{
	
	
	// dependencias(Decs0.a("dir"),Decs.a("TSH"),Decs.a("dir"),Dec.a("id"),Dec.a("clase"));
    @Override
    public Object eval(Atributo... args) {
    	
    	TS ts = (TS)args[0].valor();
    	String dirDecs1 = (String)args[1].valor();
		String id = (String)args[2].valor();
		String clase = (String)args[3].valor();

	    int dirDecsUno=Integer.parseInt(dirDecs1);
		int tamano=ts.tamanoDe(id,clase);
		
	    return String.valueOf(dirDecsUno+tamano);
		
    }

}









///////////////////////// Asignaciones  

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

class asignaVacio implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return " ";
    }
}


class asignaCierto implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return " apila_cierto() ";
    }
}


class asignaCLaseConst implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "Const";
    }
}

class asignaCLaseTipo implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "Tipo";
    }
}

class asignaCLaseVars implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "Var";
    }
}

class asignaCLaseSubVars implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "Var";
    }
}

class asignaCLaseSubPro implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "Proc";
    }
}


class asignaCLasePF implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "PVar";
    }
}

class asignaTipoNat implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "<t:Nat , tam:1>";
    }
}

class asignaTipoInt implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "<t:Int , tam:1>";
    }
}

class asignaTipoFloat implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "<t:Float , tam:1>";
    }
}

class asignaTipoBool implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "<t:Bool , tam:1>";
    }
}


class asignaTipoChar implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        	
        return "<t:Char , tam:1>";
    }
}




///////////////////////// TIPOS

class tamañoDe implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
       	
    	TS ts = (TS)args[0].valor();
		String id = (String)args[1].valor();
		String clase = (String)args[2].valor();
		
		int tamano=ts.tamanoDe(id,clase);
		
	    return String.valueOf(tamano);
    }
}

class tipoDe implements SemFun{
	
	//dependencias(Tipo1.a("tipo"),Tipo1.a("TSH"),ident.a("lex"));
    @Override
    public Object eval(Atributo... args) {
    	
		TS ts = (TS) args[0].valor();
		String id = (String) args[1].valor();
		
		Tipo tipo = ts.dameTipo(id);
		return tipo;
    }
}


class tipoArray implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
		Tipo tipo = (Tipo) args[0].valor();
		int nelems = (Integer) args[1].valor();
		int tamtipoDeArray= tipo.gettamano();
		TiposArray aux=new TiposArray("",tamtipoDeArray,nelems, tipo);
		
    	
        return aux;
    }
}


class tipoTupla implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	ArrayList<Tipo> tipo = (ArrayList<Tipo>) args[0].valor();
        TiposTupla aux=new TiposTupla("tupla",tipo);
    	
        return aux;
    }
}

class tipoTuplade2 implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	ArrayList<Tipo> tipos = (ArrayList<Tipo>) args[0].valor();
    	ArrayList<Tipo> tipo = (ArrayList<Tipo>) args[1].valor();
    	
    	ArrayList<Tipo> tiposDelaTupla=new ArrayList<Tipo>();
    	Iterator<Tipo> it=tipos.iterator();
    	Iterator<Tipo> it2=tipo.iterator();
    	
    	while (it.hasNext()) {
			Tipo aux=it.next();
			tiposDelaTupla.add(aux);
		}
    	
    	while (it2.hasNext()) {
			Tipo aux2=it2.next();
			tiposDelaTupla.add(aux2);
		}
    	
 
        TiposTupla aux=new TiposTupla("tupla",tiposDelaTupla);
    	
        return aux;
    }
    
}
///////////////////////////// ERRORESSSSSSSSSSSS


class erroresDecs implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
    	    	
    	ArrayList<String> errores = new ArrayList<String>();
    	
    	ArrayList<String> erroresdeDecs=(ArrayList<String>)args[0].valor();
    	ArrayList<String> erroresdeDec=(ArrayList<String>)args[1].valor();
    	TS ts = (TS) args[3].valor();
    	TPR tpr = (TPR) args[4].valor();
		String id = (String) args[2].valor();
		
		int i=0;
		if(ts.buscaId(id)){
			errores.set(i, "Identificador Repetido en TS");
			i++;
		}
		if(tpr.buscaId(id)){
			errores.set(i, "Identificador Repetido en TPR");
			i++;
		}
		if(!erroresdeDecs.isEmpty()){
			errores.set(i,erroresdeDecs.toString());
			i++;
		}
		if(!erroresdeDec.isEmpty()){
			errores.set(i,erroresdeDec.toString());
			i++;
		}
		
        return errores;
    }
}

class erroresDec implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
    	
       //dependencias(Decs1.a("err"),Dec.a("err"),Dec.a("id"),Decs1.a("TSH"),Decs1.a("TPRH"));
    	
    	ArrayList<String> errores = new ArrayList<String>();
    	
    	ArrayList<String> erroresdeDec=(ArrayList<String>)args[0].valor();
    	TS ts = (TS) args[2].valor();
    	TPR tpr = (TPR) args[3].valor();
		String id = (String) args[1].valor();
		
		int i=0;
		if(ts.buscaId(id)){
			errores.set(i, "Identificador Repetido en TS");
			i++;
		}
		if(tpr.buscaId(id)){
			errores.set(i, "Identificador Repetido en TPR");
			i++;
		}
		if(!erroresdeDec.isEmpty()){
			errores.set(i,erroresdeDec.toString());
			i++;
		}
		
        return errores;
    }
}

class errorDec implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
    	
       //dependencias(Dec0.a("err"),TipoBasico.a("err"),Dec0.a("tipo"),Valores.a("tipo"));
    	
    	ArrayList<String> errores = new ArrayList<String>();
    	
    	ArrayList<String> erroresdeTipoBasico=(ArrayList<String>)args[0].valor();
    	Tipo tipo = (Tipo) args[1].valor();
    	Tipo tipo2 = (Tipo) args[2].valor();
		
		int i=0;
		if(tipo.equals(tipo2)){
			errores.set(i, "No es mismo tipo el asignado a Const");
			i++;
		}
		if(!erroresdeTipoBasico.isEmpty()){
			errores.set(i,erroresdeTipoBasico.toString());
			i++;
		}
		
        return errores;
    }
}


class errorFalso implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {

    return "";
    }
}

class errorInsAsig implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {

    	//dependencias(InsAsig.a("err"),InsAsig.a("TSH"),Designador.a("lex"),Exp.a("tipo"),Exp.a("err"));//
        
    	
    	ArrayList<String> errores = new ArrayList<String>();
    	TS ts = (TS) args[0].valor();
		String id = (String) args[1].valor();
		ArrayList<String> errorExp=(ArrayList<String>)args[3].valor();
		
		Tipo tipoDesig=ts.dameTipo(id);
		Tipo tipoExp=(Tipo) args[2].valor();
		int i=0;
		
		if(ts.buscaId(id)){
			errores.set(i, "Identificador no está en la TS");
			i++;
		}
		
		if(!tipoDesig.compatibles(tipoExp)){
			errores.set(i,"Tipos no compatibles!!");
			i++;
		}
		
		if(!errorExp.isEmpty()){
			errores.set(i,errorExp.toString());
			i++;
		}
		
        return errores;
 
    
    }
}



class errorInsR implements SemFun{
	
	//dependencias(InsR.a("err"),InsR.a("TSH"),Designador.a("lex"));
	
    @Override
    public Object eval(Atributo... args) {

    	 // dependencias(InsW.a("err"),Exp.a("err"),Exp.a("tipo"));
        ArrayList<String> errores = new ArrayList<String>();
        TS ts = (TS) args[0].valor();
        String id = (String) args[1].valor();
        Tipo tipo=ts.dameTipo(id);
		
		int i=0;
		if(!tipo.equals("<t:Bool , tam:1>") || !tipo.equals("<t:Char , tam:1>") || !tipo.equals("<t:Int , tam:1>")
				|| !tipo.equals("<t:Float , tam:1>") || !tipo.equals("<t:Nat , tam:1>")){
			errores.set(i, "No es tipo BASICO");
			i++;
		}		
        return errores;
    
    }
}




class errorInsW implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {

    	 // dependencias(InsW.a("err"),Exp.a("err"),Exp.a("tipo"));
        ArrayList<String> errores = new ArrayList<String>();
    	
    	ArrayList<String> erroresdeTipoBasico=(ArrayList<String>)args[0].valor();
    	Tipo tipo = (Tipo) args[1].valor();
		
		int i=0;
		if(!tipo.equals("<t:Bool , tam:1>") || !tipo.equals("<t:Char , tam:1>") || !tipo.equals("<t:Int , tam:1>")
				|| !tipo.equals("<t:Float , tam:1>") || !tipo.equals("<t:Nat , tam:1>")){
			errores.set(i, "No es tipo BASICO");
			i++;
		}
		if(!erroresdeTipoBasico.isEmpty()){
			errores.set(i,erroresdeTipoBasico.toString());
			i++;
		}
		
        return errores;
    
    }
}




class errorParam implements SemFun{

	// dependencias(Parametro0.a("err"),Exp.a("err"),Parametro0.a("TSH"),identComp1,Exp.a("tipo"));
	
    @Override
    public Object eval(Atributo... args) {

    	ArrayList<String> errores = new ArrayList<String>();
    	TS ts = (TS) args[1].valor();
		String id = (String) args[2].valor();
		ArrayList<String> errorExp=(ArrayList<String>)args[0].valor();
		
		Tipo tipoDesig=ts.dameTipo(id);
		Tipo tipoExp=(Tipo) args[3].valor();
		int i=0;
		
		if(ts.buscaId(id)){
			errores.set(i, "Identificador no está en la TS");
			i++;
		}
		
		if(!tipoDesig.compatibles(tipoExp)){
			errores.set(i,"Tipos no compatibles!!");
			i++;
		}
		
		if(!errorExp.isEmpty()){
			errores.set(i,errorExp.toString());
			i++;
		}
		
        return errores;
    
    }
}


class estaId_OR_esReserv implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
    	ArrayList<String> errores = new ArrayList<String>();
    	TS ts = (TS) args[0].valor();
    	TPR tpr = (TPR) args[1].valor();
		String id = (String) args[2].valor();
		
		int i=0;
		if(ts.buscaId(id)){
			errores.set(i, "Identificador Repetido en TS");
			i++;
		}
		if(tpr.buscaId(id)){
			errores.set(i, "Identificador está en TPR");
		}
        return errores;
    }
}

class estaId implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	
    	ArrayList<String> errores = new ArrayList<String>();
    	TS ts = (TS) args[0].valor();
		String id = (String) args[1].valor();

		int i=0;
		if(!ts.buscaId(id)){
			errores.set(i, "Identificador no está en TS");
			i++;
		}
        return errores;
    }
}



class esComponente implements SemFun{
	
    @Override
    public Object eval(Atributo... args) {
    	ArrayList<String> errores = new ArrayList<String>();
		String id = (String) args[0].valor();
		int aux=Integer.parseInt(id);
		
	    if(aux>=0){return "";}
	    else{return errores.set(0, "Componente Inválida");}
	    
	    
    }
}







///////////////////////////CONCATENAR


class concatenaTipoTupla implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=args[0].valor()+" stop ";
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


class concatenar2 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= args[0].valor() +""+ args[1].valor();
        return s;
    }
}


class concatenarIf1 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        int n=(Integer) args[1].valor()+1;
        s= args[0].valor()+" ir_f("+n+") "+args[2].valor();
        return s;
    }
}

class concatenarIf2 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        int n=(Integer) args[1].valor()+1;
        s= args[0].valor()+" ir_f("+n+") "+args[2].valor()+" ir_a("+args[3].valor()+") "+args[4].valor();
        return s;
    }
}

class concatenarWhile1 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        int n=(Integer) args[1].valor()+1;
        s= args[0].valor()+" ir_f("+n+") "+args[2].valor()+" ir_a("+args[3].valor()+") ";
        return s;
    }
}
class concatenarAsig implements SemFun{
	
//dependencias(InsAsig.a("cod"),Exp.a("cod"),InsAsig.a("TSH"),Designador.a("lex"));
    @Override
    public Object eval(Atributo... args) {
    	
    	TS ts = (TS) args[1].valor();
		String id = (String) args[2].valor();
	    String s;    
        s=args[1].valor()+"desapila_dir("+ts.dameDir(id)+") ";
        return s;
    }
}
class concatenarIn implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        
    	TS ts = (TS) args[1].valor();
		String id = (String) args[2].valor();
	    String s;    
        s= "lee" +"desapila_dir("+ts.dameDir(id)+") ";
        return s;
    }
}

class concatenarOut implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= " apila_str("+args[0].valor()+") "+" escribe ";
        return s;
    }
}

class concatenarExp implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= args[0].valor() +""+ args[1].valor()+""+ args[2].valor();
        return s;
    }
}



class concatenarOr implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s= args[0].valor()+" copia "+" ir_v("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}


class concatenarAnd implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=args[0].valor()+" copia "+" ir_f("+args[1].valor()+") "+" desapila "+args[2].valor();
        return s;
    }
}

// dependencias(Exp30.a("cod"),Exp30.a("TSH"),Desig.a("lex"),Op41.a("cod"))


class concatenarExp30 implements SemFun{
	
//dependencias(Exp30.a("cod"),Exp30.a("TSH"),Desig.a("lex"),Op41.a("cod"));//Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
    @Override
    public Object eval(Atributo... args) {
        
    	
    	TS ts = (TS) args[0].valor();
		String id = (String) args[1].valor();
	    String s;    
        s="apila_dir("+ts.dameDir(id)+") "+args[2].valor();
        return s;
    }
}

class concatenarExp31 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=" apila(1) "+args[1].valor()+args[2].valor();
        return s;
    }
}

class concatenarExp32 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s=" apila(0) "+args[1].valor()+"resta";
        return s;
    }
}

class concatenarExp33 implements SemFun{
	
// dependencias(Exp33.a("cod"),Exp33.a("TSH"),Desig.a("lex"));//Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
    @Override
    public Object eval(Atributo... args) {
    	TS ts = (TS) args[0].valor();
		String id = (String) args[1].valor();
	    String s;    
        s="apila_dir("+ts.dameDir(id)+") ";
        return s;
    }
}

class concatenarExp34 implements SemFun{
	

    @Override
    public Object eval(Atributo... args) {
        String s="";
        s="apila("+args[0].valor()+") ";
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
    
	
	
    // Se crean los objetos que representan las diferentes funciones semÃ¡nticas
	private static SemFun creaTS = new creaTS();
	private static SemFun añadirTS = new añadirTS();
	private static SemFun creaTPR = new creaTPR();
	private static SemFun creaTSL = new creaTSL();
	
	
    private static SemFun asignacion = new Asignacion();
    private static SemFun asignacionOR2 = new AsignacionOR2();
    private static SemFun asignacionOR5 = new AsignacionOR5();
    private static SemFun asignacero = new AsignaCero();
    private static SemFun asignauno = new AsignaUno();
    private static SemFun asignavacio = new asignaVacio();
    private static SemFun asignacierto = new asignaCierto();
    private static SemFun asignafalso = new asignaFalso();
    private static SemFun asignaClaseConst =new asignaCLaseConst();
    private static SemFun asignaClaseTipo =new asignaCLaseTipo();
    private static SemFun asignaClaseVars =new asignaCLaseVars();
    private static SemFun asignaClaseSubVars =new asignaCLaseSubVars();
    private static SemFun asignaClaseSubPro =new asignaCLaseSubPro();
    private static SemFun asignaClasePF =new asignaCLasePF();
    
    
    private static SemFun asignaTipoInt =new asignaTipoInt();
    private static SemFun asignaTipoNat =new asignaTipoNat();
    private static SemFun asignaTipoFloat =new asignaTipoFloat();
    private static SemFun asignaTipoBool =new asignaTipoBool();
    private static SemFun asignaTipoChar =new asignaTipoChar();
    
    private static SemFun  dirMasTamaño =new  dirMasTamaño();
    private static SemFun tamañoDe =new tamañoDe();
    
    /////////////////Funciones de Tipos(Ident, array, tupla) y Errores
    private static SemFun tipoDe = new tipoDe();
    private static SemFun tipoArray = new tipoArray();
    private static SemFun tipoTupla = new tipoTupla();
    private static SemFun tipoTuplade2 = new tipoTuplade2();
    private static SemFun estaId_OR_esReserv = new estaId_OR_esReserv();
    private static SemFun estaId = new estaId();
    private static SemFun erroresDecs = new erroresDecs();
    private static SemFun erroresDec = new erroresDec();
    private static SemFun errorDec = new errorDec();
    private static SemFun errorFalso = new errorFalso();
    private static SemFun esComponente = new esComponente();
    
    private static SemFun errorInsAsig = new errorInsAsig();
    private static SemFun errorInsR = new errorInsR();
    private static SemFun errorInsW = new errorInsW();
    private static SemFun errorParam = new errorParam();
    

    
  /////////////////Funciones de Concatenación
    private static SemFun concatenarExp = new concatenarExp();
    private static SemFun concatenar2 = new concatenar2();
    private static SemFun concatenarIf1 = new concatenarIf1();
    private static SemFun concatenarIf2 = new concatenarIf2();
    private static SemFun concatenarWhile1 = new concatenarWhile1();
    private static SemFun concatenarAsig = new concatenarAsig();
    private static SemFun concatenarIn = new concatenarIn();
    private static SemFun concatenarOut = new concatenarOut();
    private static SemFun concatenaTipoTupla = new concatenaTipoTupla();
    
    
    private static SemFun concatenarPrograma = new concatenarPrograma();
    private static SemFun concatenarOr = new concatenarOr();
    private static SemFun concatenarAnd = new concatenarAnd();
    
    private static SemFun concatenarExp30 = new concatenarExp30();
    private static SemFun concatenarExp31 = new concatenarExp31();
    private static SemFun concatenarExp32 = new concatenarExp32();
    private static SemFun concatenarExp33 = new concatenarExp33();
    private static SemFun concatenarExp34 = new concatenarExp34();
    
    
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
    
    
    //PRACTICA PLG SEGUNDO CUATRI!!!!!!
    
    public TAtributos Programa(TAtributos Consts,TAtributos Tipos,TAtributos Vars,TAtributos Subprogramas,TAtributos Insts){
        regla("Programa → program: ident { Consts Tipos Vars Subprogramas instructions { Insts }}");
                
        TAtributos Programa = atributosPara("Programa", "TS","err","cod","etq","etqh","TPR");
        

        dependencias(Consts.a("TSH"),Programa.a("TS"));//Consts.TSH = Programa.TS
        dependencias(Tipos.a("TSH"),Consts.a("TS"));//Tipos.TSH = Consts.TS
        dependencias(Vars.a("TSH"),Tipos.a("TS"));//Vars.TSH = Tipos.TS
        dependencias(Subprogramas.a("TSH"),Vars.a("TS"));//Subprogramas.TSH = Vars.TS
        dependencias(Insts.a("TSH"),Subprogramas.a("TS"));//Insts.TSH = Subprogramas.TS         

        dependencias(Consts.a("TPRH"),Programa.a("TPR"));//Consts.TPRH = Programa.TPR
        dependencias(Tipos.a("TPRH"),Consts.a("TPRH"));//Tipos.TPRH = Consts.TPRH
        dependencias(Vars.a("TPRH"),Tipos.a("TPRH"));//Vars.TPRH = Tipos.TPRH
        dependencias(Subprogramas.a("TPRH"),Vars.a("TPRH"));//Subprogramas.TPRH = Vars.TPRH
        dependencias(Programa.a("err"),Consts.a("err"),Tipos.a("err"),Vars.a("err"),Subprogramas.a("err"),Insts.a("err"));//Programa.err = Consts.err OR  Tipos.err OR Vars.err OR Subprogramas.err OR Insts.err
        dependencias(Programa.a("cod"),Insts.a("cod"));//Prog.cod=Insts.cod || stop
        
        dependencias(Subprogramas.a("etqh"),Programa.a("etqh"));
        dependencias(Insts.a("etqh"),Subprogramas.a("etq"));
        dependencias(Programa.a("etq"),Insts.a("etq"));

        calculo(Programa.a("TPR"),creaTS);
        calculo(Programa.a("cod"),concatenarPrograma);
        calculo(Programa.a("TPR"),creaTPR);
        calculo(Consts.a("TSH"),asignacion);
        calculo(Tipos.a("TSH"),asignacion);
        calculo(Vars.a("TSH"),asignacion);
        calculo(Subprogramas.a("TSH"),asignacion);
        calculo(Insts.a("TSH"),asignacion);
        
       
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
    
    public TAtributos Consts1(){
        regla("Consts →  λ ");
                
        TAtributos Consts1 = atributosPara("Consts", "err");

        calculo(Consts1.a("err"),errorFalso);
        
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
    
    public TAtributos Tipos1(){
        regla("Tipos →  λ ");
                
        TAtributos Tipos1 = atributosPara("Tipos","err");
        
        calculo(Tipos1.a("err"),errorFalso);
        
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
    
    public TAtributos Vars1(){
        regla("Vars →   λ");
                
        TAtributos Vars1 = atributosPara("Vars","err");

        calculo(Vars1.a("err"),errorFalso);
        
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
    
    public TAtributos Subprogramas1(){
        regla("Subprogramas →   λ");
                
        TAtributos Subprogramas1 = atributosPara("Subprogramas","err");

        calculo(Subprogramas1.a("err"),errorFalso);
        
        return Subprogramas1;
        
    }
    
    
    public TAtributos Decs0(TAtributos Decs,TAtributos Dec){
        regla(" Decs → Decs ; Dec ");
                
        TAtributos Decs0 = atributosPara("Decs","TS","TSH","TPRH","dir","err","etq","etqh");
        
        dependencias(Decs.a("TPRH"),Decs0.a("TPRH"));
        dependencias(Dec.a("TPRH"),Decs.a("TPRH"));
        dependencias(Decs0.a("TS"),Decs.a("TSH"),Dec.a("id"),Dec.a("clase"),Dec.a("nivel"),Dec.a("dir"),Dec.a("tipo"),Dec.a("valor"));
        dependencias(Decs.a("TSH"),Decs0.a("TSH"));
        dependencias(Dec.a("TSH"),Decs.a("TSH"));
        dependencias(Decs0.a("err"),Decs.a("err"),Dec.a("err"),Dec.a("id"),Decs.a("TSH"),Decs.a("TPRH"));
        dependencias(Decs0.a("dir"),Decs.a("TSH"),Decs.a("dir"),Dec.a("id"),Dec.a("clase"));
        dependencias(Decs.a("etqh"),Decs0.a("etqh"));
        dependencias(Dec.a("etqh"),Decs.a("etq"));
        dependencias(Decs0.a("etq"),Dec.a("etq"));
        
        calculo(Decs.a("TPRH"),asignacion);
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Decs0.a("TS"),añadirTS);
        calculo(Decs.a("TSH"),asignacion);
        calculo(Decs0.a("err"),erroresDecs);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs0.a("dir"),dirMasTamaño);
        calculo(Decs.a("etqh"),asignacion);
        calculo(Dec.a("etqh"),asignacion);
        calculo(Decs0.a("etq"),asignacion);
        
        return Decs0;
        
    }
    
    public TAtributos Decs1(TAtributos Dec){
        regla("Decs → Dec");
                
        TAtributos Decs1 = atributosPara("Decs", "TS","TSH","TPRH","dir","err","etq","etqh");
        LAtributo Zero = new LAtributo("cero", 0);
      
        dependencias(Dec.a("TPRH"),Decs1.a("TPRH"));
        dependencias(Dec.a("TSH"),Decs1.a("TSH"));
        dependencias(Decs1.a("TS"),Dec.a("TSH"),Dec.a("id"),Dec.a("clase"),Dec.a("nivel"),Zero,Dec.a("tipo"),Dec.a("valor"));
        dependencias(Decs1.a("err"),Dec.a("err"),Dec.a("id"),Decs1.a("TSH"),Decs1.a("TPRH"));
        dependencias(Decs1.a("dir"),Dec.a("TSH"),Dec.a("id"),Dec.a("clase"));
        dependencias(Dec.a("etqh"),Decs1.a("etqh"));
        dependencias(Decs1.a("etq"),Dec.a("etq"));
        
        calculo(Decs1.a("err"),erroresDec);
        calculo(Dec.a("TPRH"),asignacion);
        calculo(Dec.a("TSH"),asignacion);
        calculo(Decs1.a("dir"),tamañoDe);
        calculo(Decs1.a("TS"),añadirTS);
        calculo(Dec.a("etqh"),asignacion);
        calculo(Decs1.a("etq"),asignacion);
        
        return Decs1;
        
    }
    
    public TAtributos Dec0(TAtributos TipoBasico,String ident,TAtributos Valores){
        regla("Dec → const TipoBasico ident = Valores");
                
        TAtributos Dec0 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err","valor");
        
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(Dec0.a("id"),identif);
        dependencias(Dec0.a("tipo"),TipoBasico.a("tipo"));
        dependencias(Dec0.a("valor"),Valores.a("valor"));
        dependencias(Dec0.a("err"),TipoBasico.a("err"),Dec0.a("tipo"),Valores.a("tipo"));
        
        
        calculo(Dec0.a("err"),errorDec);
        calculo(Dec0.a("id"),asignacion);
        calculo(Dec0.a("clase"),asignaClaseConst);
        calculo(Dec0.a("nivel"),asignacero);
        calculo(Dec0.a("tipo"),asignacion);
        calculo(Dec0.a("valor"),asignacion);
        
        return Dec0;
        
    }
    
    public TAtributos Dec1(TAtributos Tipo,String ident){
        regla("Dec → tipo TipoBasico ident");
                
        TAtributos Dec1 = atributosPara("Dec","TSH","TPRH","id","clase","niv","tipo","err","valor");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(Dec1.a("id"),identif);
        dependencias(Dec1.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),Dec1.a("TSH"));
        dependencias(Tipo.a("TPRH"),Dec1.a("TPRH"));
        dependencias(Dec1.a("err"),Tipo.a("err"));
        
        calculo(Dec1.a("id"),asignacion);
        calculo(Dec1.a("clase"),asignaClaseTipo);
        calculo(Dec1.a("nivel"),asignacero);
        calculo(Dec1.a("tipo"),asignacion);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(Dec1.a("err"),asignacion);
        calculo(Dec1.a("valor"),asignavacio);
        
        return Dec1;
        
    }
    
    
    public TAtributos Dec2(TAtributos Tipo,String ident){
        regla("Dec → var Tipo ident");
                
        TAtributos Dec2 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err","valor");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(Dec2.a("id"),identif);
        dependencias(Dec2.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),Dec2.a("TSH"));
        dependencias(Tipo.a("TPRH"),Dec2.a("TPRH"));
        dependencias(Dec2.a("err"),Tipo.a("err"));
        
        calculo(Dec2.a("id"),asignacion);
        calculo(Dec2.a("clase"),asignaClaseVars);
        calculo(Dec2.a("nivel"),asignacero);
        calculo(Dec2.a("tipo"),asignacion);
        calculo(Dec2.a("valor"),asignavacio);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(Dec2.a("err"),asignavacio);
        
        return Dec2;
    }
    
    public TAtributos Dec3(String ident, TAtributos PFs, TAtributos CS){
        regla("Dec → subprogram: ident (PFs ) {CS}");
        
        TAtributos Dec3 = atributosPara("Dec","TPRH","id","clase","niv","tipo","dir","err","etq","etqh");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(PFs.a("TPRH"),Dec3.a("TPRH"));
        dependencias(CS.a("TPRH"),PFs.a("TPRH"));
        dependencias(PFs.a("TSLH"),Dec3.a("TSH"));
        dependencias(CS.a("TSLH"),PFs.a("TSL"));
        dependencias(Dec3.a("id"),identif);
        dependencias(Dec3.a("tipo"),PFs.a("tipo"));
        dependencias(Dec3.a("err"),PFs.a("err"),CS.a("err"));
        dependencias(CS.a("etqh"),Dec3.a("etqh"));
        dependencias(Dec3.a("etq"),CS.a("etq"));
        
        calculo(PFs.a("TPRH"),asignacion);
        calculo(CS.a("TPRH"),asignacion);
        calculo(PFs.a("TSLH"),creaTSL);
        calculo(CS.a("TSLH"),asignacion);
        calculo(Dec3.a("id"),asignacion);
        calculo(Dec3.a("clase"),asignaClaseSubPro);
        calculo(Dec3.a("niv"),asignacero);
        calculo(Dec3.a("tipo"),asignacion);
        calculo(Dec3.a("valor"),asignavacio);
        calculo(Dec3.a("dir"),asignacero);
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
        regla("PFs → PF");
                
        TAtributos PFs1 = atributosPara("PFs","tipo","TSLH","TPRH","err","TSH");
        
        dependencias(PFs1.a("tipo"),PF.a("tipo"));//PFs.tipo= PF.tipo
        dependencias(PFs1.a("TSL"),PF.a("TSLH"),PF.a("id"),PF.a("clase"),PF.a("niv"),PF.a("dir"),PF.a("tipo"),PF.a("valor"));
        dependencias(PFs1.a("err"),PFs1.a("TSH"),PF.a("id"),PFs1.a("TPRH"),PF.a("err"));
        
        calculo(PFs1.a("err"),estaId_OR_esReserv);
        calculo(PFs1.a("tipo"),asignacion);
        calculo(PFs1.a("TSL"),añadirTS);
        
        return PFs1;
    }
    
    public TAtributos PF0(TAtributos Tipo, String ident){
        regla("PF → Tipo ident");
                
        TAtributos PF0 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(PF0.a("id"),identif);//PF.id = ident.lex
        dependencias(PF0.a("tipo"),Tipo.a("tipo"));//PF.tipo = Tipo.tipo
        dependencias(PF0.a("dir"),PF0.a("id"),PF0.a("clase"));//PF.dir = tamañoDe(PF.id , PF.clase)
        dependencias(PF0.a("err"),Tipo.a("err"));//PF.err= Tipo.err
        
        calculo(PF0.a("id"),asignacion);
        calculo(PF0.a("clase"),asignaClasePF);
        calculo(PF0.a("niv"),asignauno);
        calculo(PF0.a("tipo"),asignacion);
        calculo(PF0.a("dir"),tamañoDe);
        calculo(PF0.a("err"),asignacion);
        calculo(PF0.a("valor"),asignavacio);
        
        return PF0;
    }
    
    
    public TAtributos PF1(TAtributos Tipo, String ident){
        regla("PF → Tipo * ident");
                
        TAtributos PF1 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(PF1.a("id"),identif);//PF.id = Designador.lex
        dependencias(PF1.a("tipo"),Tipo.a("tipo"));//PF.tipo = Tipo.tipo
        dependencias(PF1.a("dir"),PF1.a("id"),PF1.a("clase"));//PF.dir = tamañoDe(PF.id , PF.clase)
        dependencias(PF1.a("err"),Tipo.a("err"));//PF.err= Tipo.err
        
        calculo(PF1.a("id"),asignacion);
        calculo(PF1.a("clase"),asignaClasePF);
        calculo(PF1.a("niv"),asignauno);
        calculo(PF1.a("tipo"),asignacion);
        calculo(PF1.a("dir"),tamañoDe);
        calculo(PF1.a("err"),asignacion);
        calculo(PF1.a("valor"),asignavacio);
        
        return PF1;
    }
    

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
        
     

        dependencias(DecsSubs.a("TPRH"),DecsSubs0.a("TPRH"));
        dependencias(DecSub.a("TPRH"),DecsSubs.a("TPRH"));
        dependencias(DecsSubs.a("TSLH"),DecsSubs0.a("TSLH"));
        dependencias(DecSub.a("TSLH"),DecsSubs.a("TSLH"));
        dependencias(DecsSubs0.a("TSL"),DecSub.a("TSLH"),DecSub.a("id"),DecSub.a("clase"),DecSub.a("nivel"),DecSub.a("dir"),DecSub.a("tipo"),DecSub.a("valor"));
        
        dependencias(DecsSubs0.a("err"),DecsSubs.a("err"),DecSub.a("err"),DecSub.a("id"),DecsSubs.a("TSLH"),DecsSubs.a("TPRH"));
        dependencias(DecsSubs0.a("dir"),DecsSubs.a("dir"),DecSub.a("id"),DecSub.a("clase"));

        
        calculo(DecsSubs.a("TPRH"),asignacion);
        calculo(DecSub.a("TPRH"),asignacion);
        calculo(DecsSubs.a("TSLH"),asignacion);
        calculo(DecSub.a("TSLH"),asignacion);
        calculo(DecsSubs0.a("TS"),añadirTS);
        calculo(DecsSubs0.a("dir"),dirMasTamaño);
        calculo(DecsSubs0.a("err"),erroresDecs);
        
        return DecsSubs0;
        
    }
    
    public TAtributos DecsSubs1(TAtributos DecSub){
        regla("DecsSubs → DecSub");
                
        TAtributos DecsSubs1 = atributosPara("DecsSubs","TSL","TSLH","TPRH","dir","err");
        
        LAtributo Zero = new LAtributo("cero", 0);
        
        dependencias(DecSub.a("TPRH"),DecsSubs1.a("TPRH"));
        dependencias(DecSub.a("TSLH"),DecsSubs1.a("TSLH"));
        dependencias(DecsSubs1.a("err"),DecSub.a("err"),DecSub.a("id"),DecsSubs1.a("TSLH"),DecsSubs1.a("TPRH"));
        dependencias(DecsSubs1.a("TSL"),DecSub.a("TSLH"),DecSub.a("id"),DecSub.a("clase"),DecSub.a("nivel"),Zero,DecSub.a("tipo"));
        dependencias(DecsSubs1.a("dir"),DecsSubs1.a("TSLH"),DecSub.a("id"),DecSub.a("clase"));

        calculo(DecSub.a("TPRH"),asignacion);
        calculo(DecSub.a("TSLH"),asignacion);
        calculo(DecsSubs1.a("err"),erroresDec);
        calculo(DecsSubs1.a("TS"),añadirTS);
        calculo(DecsSubs1.a("dir"),tamañoDe);
        
        return DecsSubs1;
        
    }
    
    public TAtributos DecsSubs2(){
        regla("DecsSubs → λ");
                
        TAtributos DecsSubs2 = atributosPara("DecsSubs","err");
        
        calculo(DecsSubs2.a("err"),errorFalso);
        
        return DecsSubs2;
        
    }
    
    
    public TAtributos DecSub0(TAtributos Tipo,String ident){
        regla("DecSub → var Tipo ident");
                
        TAtributos DecSub0 = atributosPara("DecSub","TSLH","TPRH","id","clase","nivel","tipo","err");
        
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(DecSub0.a("id"),identif);
        dependencias(DecSub0.a("tipo"),Tipo.a("tipo"));
        dependencias(Tipo.a("TSH"),DecSub0.a("TSH"));
        dependencias(Tipo.a("TPRH"),DecSub0.a("TPRH"));
        dependencias(DecSub0.a("err"),Tipo.a("err"));
        
      
        calculo(DecSub0.a("id"),asignacero);
        calculo(DecSub0.a("clase"),asignaClaseSubVars);
        calculo(DecSub0.a("nivel"),asignauno);
        calculo(DecSub0.a("tipo"),asignacion);
        calculo(DecSub0.a("valor"),asignavacio);
        calculo(Tipo.a("TSH"),asignacion);
        calculo(Tipo.a("TPRH"),asignacion);
        calculo(DecSub0.a("err"),asignacion);
        
        return DecSub0;
    }

   

    public TAtributos designador1(String ident){
        regla("designador → ident");
                
        TAtributos designador1 = atributosPara("designador","lex");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(designador1.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador1.a("lex"),asignacion);
        
        return designador1;
    }
    
    
    public TAtributos designador2(String ident,TAtributos Exp){
        regla("designador → ident[Exp]");
                
        TAtributos designador2 = atributosPara("designador");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(designador2.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador2.a("lex"),asignacion);
        return designador2;
    }
    
    public TAtributos designador3(String ident_numero){
        regla("designador → ident_numero");
                
        TAtributos designador3 = atributosPara("designador");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident_numero);
        
        dependencias(designador3.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador3.a("lex"),asignacion);
        
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
    
    public TAtributos Tipo1(String ident){
        regla("Tipo → ident");
                
        TAtributos Tipo1 = atributosPara("Tipo","tipo","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(Tipo1.a("tipo"),Tipo1.a("TSH"),identif);
        dependencias(Tipo1.a("err"),Tipo1.a("TSH"),Tipo1.a("TPRH"),identif);

        calculo(Tipo1.a("tipo"),tipoDe);
        calculo(Tipo1.a("err"),estaId_OR_esReserv);
        
        return Tipo1;
    }
    
    public TAtributos Tipo2(TAtributos Tipo,TAtributos Componente){
        regla("Tipo → Tipo [Componente]");
                
        TAtributos Tipo2 = atributosPara("Tipo","tipo","err");
        
        dependencias(Tipo2.a("tipo"),Tipo.a("tipo"),Componente.a("lex"));
        dependencias(Tipo2.a("err"),Tipo.a("err"),Componente.a("err"));

        calculo(Tipo2.a("tipo"),tipoArray);
        calculo(Tipo2.a("err"),asignacionOR2);
        
        return Tipo2;
    }
    
    /*Componente → numero 
       Componente0.lex = numero.lex
        Componente0.err=false
     */
    
    public TAtributos Componente0(Integer numero){
        regla("Componente → numero ");
                
        TAtributos Componente0 = atributosPara("Componente","lex","err");//Componente.lex = numero.lex
        Atributo identComp0 = atributoLexicoPara("Identificador", "lex", numero);//Componente.err=false
        
        dependencias(Componente0.a("lex"),identComp0);
        calculo(Componente0.a("lex"),asignacion);
        calculo(Componente0.a("err"),errorFalso);
        
        return Componente0;
    }
    
    /*Componente → ident
       Componente1.lex = ident.lex
       Componente1.err = esComponente(ident.lex)
*/
    public TAtributos Componente1(String ident){
        regla("Componente → ident ");
                
        TAtributos Componente1 = atributosPara("Componente","lex");
        Atributo identComp1 = atributoLexicoPara("Identificador", "lex", ident);
        
        dependencias(Componente1.a("lex"),identComp1);
        dependencias(Componente1.a("err"),identComp1);
        
        calculo(Componente1.a("lex"),asignacion);
        calculo(Componente1.a("err"),esComponente);
        
        return Componente1;
    }
    
    /*Tipo3 → (TiposTupla) 
        Tipo3.tipo = TiposTupla.tipo
        Tipos3.err = TiposTupla.err
     */
    public TAtributos Tipo3(TAtributos TiposTupla){
        regla("Tipo → (TiposTupla) ");
                
        TAtributos Tipo3 = atributosPara("Tipo","tipo","err");
        
        dependencias(Tipo3.a("tipo"),TiposTupla.a("tipo"));//Tipo3.tipo = TiposTupla.tipo
        dependencias(Tipo3.a("err"),TiposTupla.a("err"));//Tipos3.err = TiposTupla.err
        
        calculo(Tipo3.a("tipo"),asignacion);
        calculo(Tipo3.a("err"),asignacion); 
        
        return Tipo3;
    }
    
    /*TiposTupla0 → TiposTupla1, Tipo
        TiposTupla 0.tipo = TiposTupla1.Tipo || tipoTupla(Tipo.tipo)
        TiposTupla 0.err = TiposTupla1.err OR Tipo.err
    */
    public TAtributos TiposTupla0(TAtributos TiposTupla1, TAtributos Tipo){
        regla("TiposTupla → TiposTupla , Tipo ");
                
        TAtributos TiposTupla0 = atributosPara("Tipos","tipo","err");
        
        dependencias(TiposTupla0.a("tipo"),TiposTupla1.a("tipo"),Tipo.a("tipo"));//TiposTupla 0.tipo = TiposTupla1.Tipo || tipoTupla(Tipo.tipo)
        dependencias(TiposTupla0.a("err"),TiposTupla1.a("err"),Tipo.a("err"));//TiposTupla 0.err = TiposTupla1.err OR Tipo.err
     
        calculo(Tipo.a("tipo"),tipoTuplade2);
        calculo(TiposTupla0.a("err"),asignacionOR2); 
        return TiposTupla0;
    }
    
    /*TiposTupla → Tipo
        TiposTupla1.tipo = tipoTupla(Tipo.tipo)
        TiposTupla1.err = Tipo.err
     */
    
    public TAtributos TiposTupla1(TAtributos Tipo){
        regla("TiposTupla → Tipo ");
                
        TAtributos TiposTupla1 = atributosPara("Tipo","tipo","err");
        
        dependencias(TiposTupla1.a("tipo"),Tipo.a("tipo"));//TiposTupla1.tipo = tipoTupla(Tipo.tipo)
        dependencias(TiposTupla1.a("err"),Tipo.a("err"));//TiposTupla1.err = Tipo.err

        calculo(Tipo.a("tipo"),tipoTupla);
        calculo(TiposTupla1.a("tipo"),asignacion);
        calculo(TiposTupla1.a("err"),asignacion);
        
        return TiposTupla1;
    }
    
    public TAtributos TipoBasico0(){
        regla("TiposBasico → boolean ");
                
        TAtributos TiposBasico0 = atributosPara("TipoBasico","tipo","err");
         
        calculo(TiposBasico0.a("tipo"),asignaTipoBool);
        calculo(TiposBasico0.a("err"),errorFalso);
        return TiposBasico0;
    }
    
    
    public TAtributos TipoBasico1(){
        regla("TiposBasico → integer ");
                
        TAtributos TiposBasico1 = atributosPara("TipoBasico","tipo","err");
         
        calculo(TiposBasico1.a("tipo"),asignaTipoInt);
        calculo(TiposBasico1.a("err"),errorFalso);
        return TiposBasico1;
    }
    
    public TAtributos TipoBasico2(){
        regla("TiposBasico → natural ");
                
        TAtributos TiposBasico2 = atributosPara("TipoBasico","tipo","err");
         
        calculo(TiposBasico2.a("tipo"),asignaTipoNat);
        calculo(TiposBasico2.a("err"),errorFalso);

        return TiposBasico2;
    }
    
    public TAtributos TipoBasico3(){
        regla("TiposBasico → float ");
                
        TAtributos TiposBasico3 = atributosPara("TipoBasico","tipo","err");
         
        calculo(TiposBasico3.a("tipo"),asignaTipoFloat);
        calculo(TiposBasico3.a("err"),errorFalso);
        return TiposBasico3;
    }
    
    public TAtributos TipoBasico4(){
        regla("TiposBasico → carácter ");
                
        TAtributos TiposBasico4 = atributosPara("TipoBasico","tipo","err");
         
        calculo(TiposBasico4.a("tipo"),asignaTipoChar);
        calculo(TiposBasico4.a("err"),errorFalso);
        return TiposBasico4;
    }
    
    
    public TAtributos Valores(Integer numero){
        regla("Valores → numeroNat ");
                
        TAtributos Valores = atributosPara("Valores","valor","tipo");
        Atributo numRe = atributoLexicoPara("Identificador", "lex", numero);
        
        dependencias(Valores.a("valor"),numRe);
        calculo(Valores.a("valor"),asignacion);
        calculo(Valores.a("tipo"),asignaTipoNat);
        
        return Valores;
    }
    
    public TAtributos Valores0(Float numeroReal){
        regla("Valores → numeroReal ");
                
        TAtributos Valores0 = atributosPara("Valores","valor","tipo");
        Atributo numRe = atributoLexicoPara("Identificador", "lex", numeroReal);
        
        dependencias(Valores0.a("valor"),numRe);
        calculo(Valores0.a("valor"),asignacion);
        calculo(Valores0.a("tipo"),asignaTipoFloat);
        
        return Valores0;
    }
    
    public TAtributos Valores1(Integer numeroEnt){
        regla("Valores → numeroEnt");
                
        TAtributos Valores1 = atributosPara("Valores","valor","tipo");
        Atributo numEnt = atributoLexicoPara("Identificador", "lex", numeroEnt);
   
        dependencias(Valores1.a("valor"),numEnt);
        calculo(Valores1.a("valor"),asignacion);
        calculo(Valores1.a("tipo"),asignaTipoInt);
   
        return Valores1;
    }
    
    public TAtributos Valores2(){
        regla("Valores → false ");
                
        TAtributos Valores2 = atributosPara("Valores","valor");
   
        calculo(Valores2.a("valor"),asignafalso);
        calculo(Valores2.a("tipo"),asignaTipoBool);
   
        return Valores2;
    }
    
    public TAtributos Valores3(){
        regla("Valores → true ");
                
        TAtributos Valores3 = atributosPara("Valores","valor");

        calculo(Valores3.a("valor"),asignacierto);
        calculo(Valores3.a("tipo"),asignaTipoBool);
   
        return Valores3;
    }
        
    public TAtributos Valores4(String letra){
        regla("Valores → ’(letra)’ ");
        
        TAtributos Valores4 = atributosPara("Valores","valor");
        Atributo letras = atributoLexicoPara("Identificador", "lex", letra);
        
        dependencias(Valores4.a("valor"),letras);
        calculo(Valores4.a("valor"),asignacion);
        calculo(Valores4.a("tipo"),asignaTipoChar);
   
        return Valores4;
    }
    
    public TAtributos Valores5(String digito){
        regla("Valores → ’(digito)’ ");
        
        TAtributos Valores5 = atributosPara("Valores","valor");
        Atributo digitos = atributoLexicoPara("Identificador", "lex", digito);
        
        dependencias(Valores5.a("valor"),digitos);
        calculo(Valores5.a("valor"),asignacion);
        calculo(Valores5.a("tipo"),asignaTipoChar);
   
        return Valores5;
    }
    
    
    
    //////////////////// INSTRUCCIONES
    
    
    public TAtributos Insts0(TAtributos Insts,TAtributos Inst){
        regla("Insts → Insts ; Inst ");
                
        TAtributos Insts0 = atributosPara("Insts", "err","cod","TSH","etq","etqh");
        
        dependencias(Insts.a("TSH"),Insts0.a("TSH"));//Insts1.TSH = Insts0.TSH
        dependencias(Inst.a("TSH"),Insts.a("TSH"));//Inst.TSH = Insts1.TSH
        dependencias(Insts.a("etqh"),Insts0.a("etqh"));//Insts1.etqh= Insts0.etqh
        dependencias(Inst.a("etqh"),Insts.a("etq"));//Inst.etqh=Insts1.etq
        dependencias(Insts0.a("etq"),Inst.a("etq"));// Insts0.etq=Inst.etq
        dependencias(Insts0.a("err"),Insts.a("err"),Inst.a("err"));//Inst0.err = Inst1.err  OR  Inst.err
        dependencias(Insts0.a("cod"),Insts.a("cod"),Inst.a("cod"));//Insts0.cod=Insts1.Cod || Inst.cod
        
        
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
        
        dependencias(Inst.a("TSH"),Insts1.a("TSH"));//Inst.TSH = Insts.TSH
        dependencias(Inst.a("etqh"),Insts1.a("etqh"));//Inst.etqh=Insts.etqh
        dependencias(Insts1.a("etq"),Inst.a("etq"));//Insts.etq=Inst.etq
        dependencias(Insts1.a("err"),Inst.a("err"));//Insts.err = Inst.err
        dependencias(Insts1.a("cod"),Inst.a("cod"));//Insts.cod= Inst.cod
        
        
        calculo(Inst.a("TSH"),asignacion);
        calculo(Inst.a("etqh"),asignacion);
        calculo(Insts1.a("etq"),asignacion);
        calculo(Insts1.a("err"),asignacion);
        calculo(Insts1.a("cod"),asignacion);
        
        return Insts1;
        
    }
    
    public TAtributos Inst0(TAtributos InsAsig){
        regla("Inst → InsAsig ");
                
        TAtributos Inst0 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
        
        dependencias(InsAsig.a("TSH"),Inst0.a("TSH"));//InsAsig.TSH = Inst.TSH 
        dependencias(InsAsig.a("etqh"),Inst0.a("etqh"));//InsAsig.etqh=Inst.etqh 
        dependencias(Inst0.a("etq"),InsAsig.a("etq"));//Inst.etq= InsAsig.etq
        dependencias(Inst0.a("err"),InsAsig.a("err"));//Inst.err = InsAsig.err
        dependencias(Inst0.a("cod"),InsAsig.a("cod"));//Inst.cod=InsAsig.cod 
        
        
        calculo(InsAsig.a("TSH"),asignacion);
        calculo(InsAsig.a("etqh"),asignacion);
        calculo(Inst0.a("etq"),asignacion);
        calculo(Inst0.a("err"),asignacion);
        calculo(Inst0.a("cod"),asignacion);
        
        return Inst0;
        
    }
    
    public TAtributos Inst1(TAtributos InsR){
        regla("Inst → InsR ");
                
        TAtributos Inst1 = atributosPara("Inst","err","cod","TSH","etq","etqh");
        
        dependencias(InsR.a("TSH"),Inst1.a("TSH"));//InsR.TSH = Inst.TSH 
        dependencias(InsR.a("etqh"),Inst1.a("etqh"));//InsR.etqh=Inst.etqh
        dependencias(Inst1.a("etq"),InsR.a("etq"));//Inst.etq= InsR.etq
        dependencias(Inst1.a("err"),InsR.a("err"));//Inst.err = InsR.err
        dependencias(Inst1.a("cod"),InsR.a("cod"));//Inst.cod=InsR.cod
        
        
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
        
        dependencias(InsW.a("TSH"),Inst2.a("TSH"));//InsW.TSH=Inst.TSH 
        dependencias(InsW.a("etqh"),Inst2.a("etqh"));//InsW.etqh=Inst.etqh
        dependencias(Inst2.a("etq"),InsW.a("etq"));//Inst.etq= InsW.etq
        dependencias(Inst2.a("err"),InsW.a("err"));//Inst.err = InsW.err
        dependencias(Inst2.a("cod"),InsW.a("cod"));//Inst.cod=InsW.cod
        
        
        calculo(InsW.a("TSH"),asignacion);
        calculo(InsW.a("etqh"),asignacion);
        calculo(Inst2.a("etq"),asignacion);
        calculo(Inst2.a("err"),asignacion);
        calculo(Inst2.a("cod"),asignacion);
        
        return Inst2;
        
    }
    
    public TAtributos Inst3(){
        regla("Inst → SWAP1() ");
                
        TAtributos Inst3 = atributosPara("Inst","etqh","err","cod");
        
        ArrayList<String> codFunciones = new ArrayList<String>();
        codFunciones.add("swap1");
    	LAtributo swap1Cod = new LAtributo("cod", codFunciones);
    	
        dependencias(Inst3.a("etq"),Inst3.a("etqh"));// SWAP1().etq= Inst.etqh + 1 
        dependencias(Inst3.a("cod"),swap1Cod);//Inst.cod=SWAP1.cod
        
        calculo(Inst3.a("err"),errorFalso);
        calculo(Inst3.a("cod"),asignacion);
        calculo(Inst3.a("etq"),sumauno);
        
        return Inst3;
        
    }
    
    public TAtributos Inst4(){
        regla("Inst → SWAP2() ");
                
        TAtributos Inst4 = atributosPara("Inst","etqh","err","cod");
        
        ArrayList<String> codFunciones = new ArrayList<String>();
        codFunciones.add("swap2");
    	LAtributo swap2Cod = new LAtributo("cod", codFunciones);
    	
        dependencias(Inst4.a("etq"),Inst4.a("etqh"));// SWAP1().etq= Inst.etqh + 1 
        dependencias(Inst4.a("cod"),swap2Cod);//Inst.cod=SWAP1.cod
        
        calculo(Inst4.a("err"),errorFalso);
        calculo(Inst4.a("cod"),asignacion);
        calculo(Inst4.a("etq"),sumauno);
        
        return Inst4;
        
    }
    public TAtributos Inst5(TAtributos IF){
    	
    	regla("Inst → IF ");
         
         TAtributos Inst5 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
         
         dependencias(IF.a("TSH"),Inst5.a("TSH"));//IF.TSH = Inst.TSH  
         dependencias(IF.a("etqh"),Inst5.a("etqh"));//IF.etqh=Inst.etqh
         dependencias(Inst5.a("etq"),IF.a("etq"));//Inst.etq= IF.etq
         dependencias(Inst5.a("err"),IF.a("err"));//Inst.err = IF.err
         dependencias(Inst5.a("cod"),IF.a("cod"));//Inst.cod=IF.cod
         
         
         calculo(IF.a("TSH"),asignacion);
         calculo(IF.a("etqh"),asignacion);
         calculo(Inst5.a("etq"),asignacion);
         calculo(Inst5.a("err"),asignacion);
         calculo(Inst5.a("cod"),asignacion);
         
         return Inst5;
        
    }

    public TAtributos Inst6(TAtributos WHILE){
        	
        	regla("Inst → WHILE ");
             
             TAtributos Inst6 = atributosPara("Inst","err","cod","TSH","etq","etqh");
             
             dependencias(WHILE.a("TSH"),Inst6.a("TSH"));//WHILE.TSH = Inst.TSH  
             dependencias(WHILE.a("etqh"),Inst6.a("etqh"));//WHILE.etqh=Inst.etqh
             dependencias(Inst6.a("etq"),WHILE.a("etq"));//Inst.etq= WHILE.etq
             dependencias(Inst6.a("err"),WHILE.a("err"));//Inst.err = WHILE.err
             dependencias(Inst6.a("cod"),WHILE.a("cod"));//Inst.cod=WHILE.cod
             
             
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
         
         dependencias(LLAMADA.a("TSH"),Inst7.a("TSH"));//LLAMADA.TSH = Inst.TSH  
         dependencias(Inst7.a("err"),LLAMADA.a("err"));//Inst.err = LLAMADA.err
         dependencias(Inst7.a("cod"),LLAMADA.a("cod"));//Inst.cod=LLAMADA.cod
         dependencias(LLAMADA.a("etqh"),Inst7.a("etqh"));//LLAMADA.etqh=Inst.etqh
         dependencias(Inst7.a("etq"),LLAMADA.a("etq"));// Inst.etq= LLAMADA.etq
        

         calculo(LLAMADA.a("TSH"),asignacion);
         calculo(Inst7.a("err"),asignacion);
         calculo(Inst7.a("cod"),asignacion);
         calculo(LLAMADA.a("etqh"),asignacion);
         calculo(Inst7.a("etq"),asignacion);
         
         return Inst7;
        
    }
    
    public TAtributos InsAsig(TAtributos Designador,TAtributos Exp){
        regla("InsAsig → Designador = Exp ");
                
        TAtributos InsAsig = atributosPara("InsAsig","err","cod","TSH","etq","etqh");
        
        dependencias(Exp.a("TSH"),InsAsig.a("TSH"));//Exp.TSH = InsAsig.TSH
        dependencias(InsAsig.a("err"),InsAsig.a("TSH"),Designador.a("lex"),Exp.a("tipo"),Exp.a("err"));//
        dependencias(InsAsig.a("etq"),Exp.a("etq"));//Exp.etqh=InsAsig.etqh
        dependencias(Exp.a("etqh"),InsAsig.a("etqh"));//Exp.etqh=InsAsig.etqh
        dependencias(InsAsig.a("cod"),Exp.a("cod"),InsAsig.a("TSH"),Designador.a("lex"));
  
  
        calculo(InsAsig.a("cod"),concatenarAsig);
        calculo(Exp.a("TSH"),asignacion);
        calculo(InsAsig.a("err"),errorInsAsig);
        calculo(InsAsig.a("etq"),sumauno);
        calculo(Exp.a("etqh"),asignacion);
  
        
        
        return InsAsig;
        
    }
    
    public TAtributos InsR(TAtributos Designador){
        regla("InsR → in (Designador) ");
                
        TAtributos InsR = atributosPara("InsR","err","cod","TSH","etq","etqh");
        
 
        dependencias(InsR.a("err"),InsR.a("TSH"),Designador.a("lex"));
        dependencias(InsR.a("cod"),InsR.a("TSH"),Designador.a("lex"));
        dependencias(InsR.a("etq"),Designador.a("etq"));
    
        calculo(InsR.a("etq"),sumaDos);
        calculo(InsR.a("cod"),concatenarIn);
        calculo(InsR.a("err"),errorInsR);
        
        return InsR;
        
    }
    
   public TAtributos InsW(TAtributos Exp) {
       
       regla("InsW → out (Exp)");
       
       TAtributos InsW = atributosPara("InsW","TSH","err", "cod", "etq","etqh");
     
       dependencias(InsW.a("cod"), Exp.a("valor"));
       dependencias(InsW.a("etq"),InsW.a("etqh"));
       dependencias(Exp.a("TSH"),InsW.a("TSH"));
       dependencias(InsW.a("err"),Exp.a("err"),Exp.a("tipo"));
       
       calculo(InsW.a("etq"),sumaDos);
       calculo(InsW.a("cod"),concatenarOut);
       calculo(InsW.a("err"),errorInsW);
       calculo(Exp.a("TSH"),asignacion);
       
       return InsW;
   }
    

   public TAtributos If0(TAtributos Exp,TAtributos Insts0){
       regla("IF → if Exp then Insts endif");
               
       TAtributos If0 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(Exp.a("TSH"),If0.a("TSH"));
       dependencias(Insts0.a("TSH"),Exp.a("TSH"));
       dependencias(If0.a("err"),Insts0.a("err"));
       
       dependencias(If0.a("cod"),Exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"));
       dependencias(Exp.a("etqh"),If0.a("etqh"));
       dependencias(Insts0.a("etqh"),Exp.a("etq"));

       dependencias(If0.a("etq"),Insts0.a("etq"));
       dependencias(Exp.a("irvh"),Exp.a("etq"));
       dependencias(Exp.a("irfh"),Exp.a("etq"));
       
       
       calculo(Exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(If0.a("err"),asignacionOR2);

       calculo(Insts0.a("etqh"),sumauno);

       calculo(If0.a("cod"),concatenarIf1);
       calculo(If0.a("etq"),asignacion);
       calculo(Exp.a("etqh"),asignacion);
       calculo(Exp.a("irvh"),asignacion);
       calculo(Exp.a("irfh"),asignacion);
       
       
       return If0;
       
   }
    
   public TAtributos If1(TAtributos Exp,TAtributos Insts0, TAtributos Insts1){
       regla("IF → if  Exp then Insts else Insts endif ");
       
       TAtributos If1 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(Exp.a("TSH"),If1.a("TSH"));
       dependencias(Insts0.a("TSH"),Exp.a("TSH"));
       dependencias(Insts1.a("TSH"),Insts0.a("TSH"));
       dependencias(If1.a("err"),Insts0.a("err"),Insts1.a("err"));
       
       dependencias(If1.a("cod"),Exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"),Insts1.a("etq"),Insts1.a("cod"));
       dependencias(Exp.a("etqh"),If1.a("etqh"));
       dependencias(Insts0.a("etqh"),Exp.a("etq"));
       dependencias(Insts1.a("etqh"),Insts0.a("etq"));
       dependencias(If1.a("etq"),Insts1.a("etq"));
       dependencias(Exp.a("irvh"),Exp.a("etq"));
       dependencias(Exp.a("irfh"),Exp.a("etq"));
       
       
       calculo(Exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(Insts1.a("TSH"),asignacion);
       //// FALTA ERRRORRR
       calculo(Insts0.a("etqh"),sumauno);
       calculo(Insts1.a("etqh"),sumauno);
       calculo(If1.a("cod"),concatenarIf2);
       calculo(If1.a("etq"),asignacion);
       calculo(Exp.a("etqh"),asignacion);
       calculo(Exp.a("irvh"),asignacion);
       calculo(Exp.a("irfh"),asignacion);
       
       
       return If1;
       
   }
   
   
   public TAtributos While0(TAtributos Exp, TAtributos Insts0){
	   regla("IF → if Exp then Insts endif");
       
       TAtributos While0 = atributosPara("While","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(Exp.a("TSH"),While0.a("TSH"));
       dependencias(Insts0.a("TSH"),Exp.a("TSH"));
       dependencias(While0.a("err"),Insts0.a("err"));
       
       dependencias(While0.a("cod"),Exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"),While0.a("etqh"));
       dependencias(Exp.a("etqh"),While0.a("etqh"));
       dependencias(Insts0.a("etqh"),Exp.a("etq"));

       dependencias(While0.a("etq"),Insts0.a("etq"));
       dependencias(Exp.a("irvh"),Exp.a("etq"));
       dependencias(Exp.a("irfh"),Exp.a("etq"));
       
       
       calculo(Exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(While0.a("err"),asignacionOR2);

       calculo(Insts0.a("etqh"),sumauno);

       //CONCATENAR DIFERENTE
       calculo(While0.a("cod"),concatenarWhile1);
       calculo(While0.a("etq"),asignacion);
       calculo(Exp.a("etqh"),asignacion);
       calculo(Exp.a("irvh"),asignacion);
       calculo(Exp.a("irfh"),asignacion);
       
       
       return While0;
       
   }
    
   
   public TAtributos Llamada0(String ident,TAtributos Parametros){
       regla("LLAMADA → Call ident (Parametros)");
               
       TAtributos Llamada0 = atributosPara("Llamada","TSH","err", "cod","etq","etqh");
       Atributo identLlamada0 = atributoLexicoPara("Identificador", "lex", ident);
       Atributo identLlamada1 = atributoLexicoPara("Identificador", "cod", ident);
       Atributo identLlamada2 = atributoLexicoPara("Identificador", "etq", ident);
       Atributo identLlamada3 = atributoLexicoPara("Identificador", "etqh", ident);
       
       dependencias(Parametros.a("TSH"),Llamada0.a("TSH"));
       dependencias(Llamada0.a("err"),Parametros.a("err"));
       dependencias(Llamada0.a("cod"),identLlamada1,Parametros.a("cod"),identLlamada3);
       dependencias(Llamada0.a("etq"),identLlamada2);
       dependencias(identLlamada3,Llamada0.a("etqh"));

       

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
       regla("Parametros →  Parametro");
               
       TAtributos Parametros1 = atributosPara("Parametros","TSH","err");
       
       dependencias(Parametro.a("TSH"),Parametros1.a("TSH"));     
       dependencias(Parametros1.a("err"),Parametro.a("err"));

       calculo(Parametro.a("TSH"),asignacion);
       calculo(Parametros1.a("err"),asignacion);
       
       return Parametros1;
       
   }
    
   public TAtributos Parametro0(String ident,TAtributos Exp){
       regla("Parametro → ident = Exp ");
               
       TAtributos Parametro0 = atributosPara("Parametro","TSH","err");
       Atributo identComp1 = atributoLexicoPara("Identificador", "lex", ident);
       
       
       dependencias(Exp.a("TSH"),Parametro0.a("TSH"));     
       dependencias(Parametro0.a("err"),Exp.a("err"),Parametro0.a("TSH"),identComp1,Exp.a("tipo"));

       calculo(Exp.a("TSH"),asignacion);
       calculo(Parametro0.a("err"),errorParam);
       
       return Parametro0;
       
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
	     calculo(Exp0.a("etq"),sumauno);
	     calculo(Exp0.a("cod"),concatenarExp);
	     
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
	     calculo(Exp00.a("etq"),sumauno);
	     calculo(Exp00.a("cod"),concatenarExp);
	     
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
	     calculo(Exp01.a("cod"),concatenarOr);
	     calculo(Exp0.a("etqh"),asignacion);
	     calculo(Exp01.a("etq"),sumaTres);
	     calculo(Exp1.a("etqh"),asignacion);
	     calculo(Exp01.a("etq"),asignacion);
	     
	     calculo(Exp01.a("irvh"),asignacion);
	     calculo(Exp01.a("irfh"),asignacion);
	     calculo(Exp0.a("irvh"),asignacion);
	     calculo(Exp0.a("irfh"),sumaDos);
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
 calculo(Exp10.a("etq"),sumauno);
 calculo(Exp10.a("cod"),concatenarExp);
 
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
     calculo(Exp11.a("cod"),concatenarAnd);
     calculo(Exp12.a("etqh"),asignacion);

     calculo(Exp2.a("etqh"),sumaTres);
     calculo(Exp11.a("etq"),asignacion);
     
     calculo(Exp11.a("irvh"),asignacion);
     calculo(Exp11.a("irfh"),asignacion);

     calculo(Exp12.a("irvh"),sumaDos);
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
 	calculo(Exp20.a("etq"),sumauno);
 	calculo(Exp20.a("cod"),concatenarExp);
 
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
     dependencias(Exp30.a("tipo"),Exp30.a("TSH"),Desig.a("lex"));//Exp30.tipo = desginador.tipo
     dependencias(Exp30.a("etq"),Exp30.a("etqh"));//Exp30.etq= Exp3.etqh + 2 
     dependencias(Exp30.a("cod"),Exp30.a("TSH"),Desig.a("lex"),Op41.a("cod"));//Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
     
     calculo(Exp30.a("err"),estaId);
     calculo(Exp30.a("tipo"),tipoDe);
     calculo(Exp30.a("etq"),sumaDos);
     calculo(Exp30.a("cod"),concatenarExp30);
     
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
     calculo(Exp31.a("etq"),sumauno);
     calculo(Exp31.a("cod"),concatenarExp31);
     
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
     calculo(Exp3.a("cod"),concatenarExp32);
     calculo(Exp32.a("etq"),sumauno);
     
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
     dependencias(Exp33.a("tipo"),Exp33.a("TSH"),Desig.a("lex"));//Exp33.tipo = Designador.tipo
     dependencias(Exp33.a("cod"),Exp33.a("TSH"),Desig.a("lex"));//Exp30.cod= apila_dir(Exp30.TSH [Designador.lex].dir)||Op41.cod
     dependencias(Exp33.a("etq"),Exp33.a("etqh"));//Exp33.etq = Exp33.etqh+1; 
     
     calculo(Exp33.a("err"),estaId);
     calculo(Exp33.a("tipo"),tipoDe);
     calculo(Exp33.a("cod"),concatenarExp33);
     calculo(Exp33.a("etq"),sumauno);

     
     return Exp33;
    
}

/*Exp3 → Valores
        Exp3.tipo = Valores.tipo
        Exp3.cod=apila
        Exp3.etq = Exp3.etqh+1;
*/

public TAtributos Exp34(TAtributos Valores){
    
	regla("Exp3 → Valores");
     
     TAtributos Exp34 = atributosPara("Exp34","tipo","err","cod","etq","etqh");
     
     dependencias(Exp34.a("tipo"),Valores.a("tipo"));//Exp34.tipo = Valores.tipo
     dependencias(Exp34.a("cod"),Valores.a("valor"));//Exp34.cod=apila
     dependencias(Exp34.a("etq"),Exp34.a("etqh"));// Exp34.etq = Exp3.etqh+1; 
     
     calculo(Exp34.a("tipo"),asignacion);
     calculo(Exp34.a("cod"),concatenarExp34);
     calculo(Exp34.a("err"),errorFalso);
     calculo(Exp34.a("etq"),sumauno);
     
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