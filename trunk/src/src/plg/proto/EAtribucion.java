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

class anadirTS implements SemFun{
	
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


class dirMasTamano implements SemFun{
	
	
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

class tamanoDe implements SemFun{
	
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


class errorIf0_While0 implements SemFun{

	// dependencias(If0.a("err"),Exp.a("err"),Insts0.a("err"),Exp.a("tipo"));
	
    @Override
    public Object eval(Atributo... args) {

    	ArrayList<String> errores = new ArrayList<String>();

		ArrayList<String> errorExp=(ArrayList<String>)args[0].valor();
		ArrayList<String> errorInst=(ArrayList<String>)args[1].valor();
		Tipo tipo=(Tipo) args[2].valor();
	
		int i=0;
		
		if(!errorExp.isEmpty()){
			errores.set(i,errorExp.toString());
			i++;
		}
		
		if(!errorInst.isEmpty()){
			errores.set(i,errorInst.toString());
			i++;
		}
		
		if(!tipo.equals("<t:Bool , tam:1>")){
			errores.set(i, "No es tipo Bool");
			i++;
		}
        return errores;
    
    }
}

class errorIf1 implements SemFun{

	
	//dependencias(If1.a("err"),Exp.a("err"),Exp.a("tipo"),Insts0.a("err"),Insts1.a("err"));
    @Override
    public Object eval(Atributo... args) {

    	ArrayList<String> errores = new ArrayList<String>();

		ArrayList<String> errorExp=(ArrayList<String>)args[0].valor();
		ArrayList<String> errorInst0=(ArrayList<String>)args[2].valor();
		ArrayList<String> errorInst1=(ArrayList<String>)args[2].valor();
		Tipo tipo=(Tipo) args[1].valor();
	
		int i=0;
		
		if(!errorExp.isEmpty()){
			errores.set(i,errorExp.toString());
			i++;
		}
		
		if(!errorInst0.isEmpty()){
			errores.set(i,errorInst0.toString());
			i++;
		}
		if(!errorInst1.isEmpty()){
			errores.set(i,errorInst0.toString());
			i++;
		}
		if(!tipo.equals("<t:Bool , tam:1>")){
			errores.set(i, "No es tipo Bool");
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
	private static SemFun anadirTS = new anadirTS();
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
    
    private static SemFun  dirMasTamano =new  dirMasTamano();
    private static SemFun tamanoDe =new tamanoDe();
    
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
    private static SemFun errorIf0_While0 = new errorIf0_While0();
    private static SemFun errorIf1 = new errorIf1();
    

    
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
    
    public TAtributos Programa(String id,TAtributos consts,TAtributos tipos,TAtributos vars,TAtributos subprogramas,TAtributos insts){
        regla("Programa → program: ident { Consts Tipos Vars Subprogramas instructions { Insts }}");
                
        TAtributos Programa = atributosPara("Programa", "TS","err","cod","etq","etqh","TPR");
        

       // dependencias(consts.a("TSH"),Programa.a("TS"));//Consts.TSH = Programa.TS
        //dependencias(tipos.a("TSH"),consts.a("TS"));//Tipos.TSH = Consts.TS
        //dependencias(vars.a("TSH"),tipos.a("TS"));//Vars.TSH = Tipos.TS
        //dependencias(subprogramas.a("TSH"),vars.a("TS"));//Subprogramas.TSH = Vars.TS
        //dependencias(insts.a("TSH"),subprogramas.a("TS"));//Insts.TSH = Subprogramas.TS         

      //  dependencias(consts.a("TPRH"),Programa.a("TPR"));//Consts.TPRH = Programa.TPR
      //  dependencias(tipos.a("TPRH"),consts.a("TPRH"));//Tipos.TPRH = Consts.TPRH
      //  dependencias(vars.a("TPRH"),tipos.a("TPRH"));//Vars.TPRH = Tipos.TPRH
      //  dependencias(subprogramas.a("TPRH"),vars.a("TPRH"));//Subprogramas.TPRH = Vars.TPRH
       dependencias(Programa.a("err"),consts.a("err"),tipos.a("err"),vars.a("err"),subprogramas.a("err"),insts.a("err"));//Programa.err = Consts.err OR  Tipos.err OR Vars.err OR Subprogramas.err OR Insts.err
        dependencias(Programa.a("cod"),insts.a("cod"));//Prog.cod=Insts.cod || stop
        
      //  dependencias(subprogramas.a("etqh"),Programa.a("etqh"));
       // dependencias(insts.a("etqh"),subprogramas.a("etq"));
        //  dependencias(Programa.a("etq"),insts.a("etq"));

        calculo(Programa.a("TPR"),creaTS);
        calculo(Programa.a("cod"),concatenarPrograma);
        calculo(Programa.a("TPR"),creaTPR);
       // calculo(consts.a("TSH"),asignacion);
       // calculo(tipos.a("TSH"),asignacion);
       // calculo(vars.a("TSH"),asignacion);
       // calculo(subprogramas.a("TSH"),asignacion);
       // calculo(insts.a("TSH"),asignacion);
        
       
       // calculo(consts.a("TPRH"),asignacion);
       // calculo(tipos.a("TPRH"),asignacion);
      //  calculo(vars.a("TPRH"),asignacion);
      //  calculo(subprogramas.a("TPRH"),asignacion);
        calculo(Programa.a("err"),asignacionOR5);
        
     //   calculo(Programa.a("etqh"),asignacero);
     //   calculo(subprogramas.a("etqh"),asignacion);
     //   calculo(insts.a("etqh"),asignacion);
     //   calculo(Programa.a("etq"),asignacion);

        
        return Programa;
        
    }
  
    public TAtributos Consts0(TAtributos decs){
        regla("Consts → consts { Decs }");
                
        TAtributos Consts0 = atributosPara("Consts", "TS","TSH","TPRH","err");
        
        dependencias(decs.a("TPRH"),Consts0.a("TPRH"));
        dependencias(Consts0.a("TS"),decs.a("TS"));
        dependencias(decs.a("TSH"),Consts0.a("TSH"));
        dependencias(Consts0.a("err"),decs.a("err"));
      
        calculo(decs.a("TPRH"),asignacion);
        calculo(Consts0.a("TS"),asignacion);
        calculo(decs.a("TSH"),asignacion);
        calculo(Consts0.a("err"),asignacion);
        
        return Consts0;     
    }
    
    public TAtributos Consts1(){
        regla("Consts →  λ ");
                
        TAtributos Consts1 = atributosPara("Consts", "err");

        calculo(Consts1.a("err"),errorFalso);
        
        return Consts1;
        
    }
    
    public TAtributos Tipos0(TAtributos decs){
        regla("Tipos → tipos { decs } ");
                
        TAtributos Tipos0 = atributosPara("Tipos", "TS","TSH","TPRH","err");
        
        dependencias(decs.a("TPRH"),Tipos0.a("TPRH"));
        dependencias(Tipos0.a("TS"),decs.a("TS"));
        dependencias(decs.a("TSH"),Tipos0.a("TSH"));
        dependencias(Tipos0.a("err"),decs.a("err"));
      
        calculo(decs.a("TPRH"),asignacion);
        calculo(Tipos0.a("TS"),asignacion);
        calculo(decs.a("TSH"),asignacion);
        calculo(Tipos0.a("err"),asignacion);
        
        return Tipos0;
        
    }
    
    public TAtributos Tipos1(){
        regla("Tipos →  λ ");
                
        TAtributos Tipos1 = atributosPara("Tipos","err");
        
        calculo(Tipos1.a("err"),errorFalso);
        
        return Tipos1;
        
    }
    
    
    public TAtributos Vars0(TAtributos decs){
        regla("Vars →  vars { decs }");
                
        TAtributos Vars0 = atributosPara("Vars", "TS","TSH","TPRH","err");
        

        dependencias(decs.a("TPRH"),Vars0.a("TPRH"));
        dependencias(Vars0.a("TS"),decs.a("TS"));
        dependencias(decs.a("TSH"),Vars0.a("TSH"));
        dependencias(Vars0.a("err"),decs.a("err"));
      
        calculo(decs.a("TPRH"),asignacion);
        calculo(Vars0.a("TS"),asignacion);
        calculo(decs.a("TSH"),asignacion);
        calculo(Vars0.a("err"),asignacion);
        
        return Vars0;
        
    }
    
    public TAtributos Vars1(){
        regla("Vars →   λ");
                
        TAtributos Vars1 = atributosPara("Vars","err");

        calculo(Vars1.a("err"),errorFalso);
        
        return Vars1;
        
    }
    
    public TAtributos Subprogramas0(TAtributos decs){
        regla("Subprogramas →  subprograms { decs }");
                
        TAtributos Subprogramas0 = atributosPara("Subprogramas", "TS","TSH","TPRH","err","etq","etqh");
        

        dependencias(decs.a("TPRH"),Subprogramas0.a("TPRH"));
        dependencias(Subprogramas0.a("TS"),decs.a("TS"));
        dependencias(decs.a("TSH"),Subprogramas0.a("TSH"));
        dependencias(Subprogramas0.a("err"),decs.a("err"));
        
        dependencias(decs.a("etqh"),Subprogramas0.a("etq"));
        dependencias(Subprogramas0.a("etq"),decs.a("etq"));
        
        
        calculo(decs.a("TPRH"),asignacion);
        calculo(Subprogramas0.a("TS"),asignacion);
        calculo(decs.a("TSH"),asignacion);
        calculo(Subprogramas0.a("err"),asignacion);
        calculo(decs.a("etqh"),asignacion);
        calculo(Subprogramas0.a("etq"),asignacion);
        
        return Subprogramas0;
        
    }
    
    public TAtributos Subprogramas1(){
        regla("Subprogramas →   λ");
                
        TAtributos Subprogramas1 = atributosPara("Subprogramas","err");

        calculo(Subprogramas1.a("err"),errorFalso);
        
        return Subprogramas1;
        
    }
    
    
    public TAtributos Decs0(TAtributos decs,TAtributos dec){
        regla(" Decs → Decs ; Dec ");
                
        TAtributos Decs0 = atributosPara("Decs","TS","TSH","TPRH","dir","err","etq","etqh");
        
        dependencias(decs.a("TPRH"),Decs0.a("TPRH"));
        dependencias(dec.a("TPRH"),decs.a("TPRH"));
        dependencias(Decs0.a("TS"),decs.a("TSH"),dec.a("id"),dec.a("clase"),dec.a("nivel"),dec.a("dir"),dec.a("tipo"),dec.a("valor"));
        dependencias(decs.a("TSH"),Decs0.a("TSH"));
        dependencias(dec.a("TSH"),decs.a("TSH"));
        dependencias(Decs0.a("err"),decs.a("err"),dec.a("err"),dec.a("id"),decs.a("TSH"),decs.a("TPRH"));
        dependencias(Decs0.a("dir"),decs.a("TSH"),decs.a("dir"),dec.a("id"),dec.a("clase"));
        dependencias(decs.a("etqh"),Decs0.a("etqh"));
        dependencias(dec.a("etqh"),decs.a("etq"));
        dependencias(Decs0.a("etq"),dec.a("etq"));
        
        calculo(decs.a("TPRH"),asignacion);
        calculo(dec.a("TPRH"),asignacion);
        calculo(Decs0.a("TS"),anadirTS);
        calculo(decs.a("TSH"),asignacion);
        calculo(Decs0.a("err"),erroresDecs);
        calculo(dec.a("TSH"),asignacion);
        calculo(Decs0.a("dir"),dirMasTamano);
        calculo(decs.a("etqh"),asignacion);
        calculo(dec.a("etqh"),asignacion);
        calculo(Decs0.a("etq"),asignacion);
        
        return Decs0;
        
    }
    
    public TAtributos Decs1(TAtributos dec){
        regla("Decs → Dec");
                
        TAtributos Decs1 = atributosPara("Decs", "TS","TSH","TPRH","dir","err","etq","etqh");
        LAtributo Zero = new LAtributo("cero", 0);
      
        dependencias(dec.a("TPRH"),Decs1.a("TPRH"));
        dependencias(dec.a("TSH"),Decs1.a("TSH"));
        dependencias(Decs1.a("TS"),dec.a("TSH"),dec.a("id"),dec.a("clase"),dec.a("nivel"),Zero,dec.a("tipo"),dec.a("valor"));
        dependencias(Decs1.a("err"),dec.a("err"),dec.a("id"),Decs1.a("TSH"),Decs1.a("TPRH"));
        dependencias(Decs1.a("dir"),dec.a("TSH"),dec.a("id"),dec.a("clase"));
        dependencias(dec.a("etqh"),Decs1.a("etqh"));
        dependencias(Decs1.a("etq"),dec.a("etq"));
        
        calculo(Decs1.a("err"),erroresDec);
        calculo(dec.a("TPRH"),asignacion);
        calculo(dec.a("TSH"),asignacion);
        calculo(Decs1.a("dir"),tamanoDe);
        calculo(Decs1.a("TS"),anadirTS);
        calculo(dec.a("etqh"),asignacion);
        calculo(Decs1.a("etq"),asignacion);
        
        return Decs1;
        
    }
    
    public TAtributos Dec0(TAtributos tipobasico,String id,TAtributos valores){
        regla("Dec → const tipobasico id = Valores");
                
        TAtributos Dec0 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err","valor");
        
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Dec0.a("id"),identif);
        dependencias(Dec0.a("tipo"),tipobasico.a("tipo"));
        dependencias(Dec0.a("valor"),valores.a("valor"));
        dependencias(Dec0.a("err"),tipobasico.a("err"),Dec0.a("tipo"),valores.a("tipo"));
        
        
        calculo(Dec0.a("err"),errorDec);
        calculo(Dec0.a("id"),asignacion);
        calculo(Dec0.a("clase"),asignaClaseConst);
        calculo(Dec0.a("nivel"),asignacero);
        calculo(Dec0.a("tipo"),asignacion);
        calculo(Dec0.a("valor"),asignacion);
        
        return Dec0;
        
    }
    
    public TAtributos Dec1(TAtributos tipo,String id){
        regla("Dec → tipo tipoBasico ident");
                
        TAtributos Dec1 = atributosPara("Dec","TSH","TPRH","id","clase","niv","tipo","err","valor");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Dec1.a("id"),identif);
        dependencias(Dec1.a("tipo"),tipo.a("tipo"));
        dependencias(tipo.a("TSH"),Dec1.a("TSH"));
        dependencias(tipo.a("TPRH"),Dec1.a("TPRH"));
        dependencias(Dec1.a("err"),tipo.a("err"));
        
        calculo(Dec1.a("id"),asignacion);
        calculo(Dec1.a("clase"),asignaClaseTipo);
        calculo(Dec1.a("nivel"),asignacero);
        calculo(Dec1.a("tipo"),asignacion);
        calculo(tipo.a("TSH"),asignacion);
        calculo(tipo.a("TPRH"),asignacion);
        calculo(Dec1.a("err"),asignacion);
        calculo(Dec1.a("valor"),asignavacio);
        
        return Dec1;
        
    }
    
    
    public TAtributos Dec2(TAtributos tipo,String id){
        regla("Dec → var Tipo ident");
                
        TAtributos Dec2 = atributosPara("Dec", "TSH","TPRH","id","clase","niv","tipo","err","valor");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Dec2.a("id"),identif);
        dependencias(Dec2.a("tipo"),tipo.a("tipo"));
        dependencias(tipo.a("TSH"),Dec2.a("TSH"));
        dependencias(tipo.a("TPRH"),Dec2.a("TPRH"));
        dependencias(Dec2.a("err"),tipo.a("err"));
        
        calculo(Dec2.a("id"),asignacion);
        calculo(Dec2.a("clase"),asignaClaseVars);
        calculo(Dec2.a("nivel"),asignacero);
        calculo(Dec2.a("tipo"),asignacion);
        calculo(Dec2.a("valor"),asignavacio);
        calculo(tipo.a("TSH"),asignacion);
        calculo(tipo.a("TPRH"),asignacion);
        calculo(Dec2.a("err"),asignavacio);
        
        return Dec2;
    }
    
    public TAtributos Dec3(String id, TAtributos pfs, TAtributos cs){
        regla("Dec → subprogram: ident (pfs ) {CS}");
        
        TAtributos Dec3 = atributosPara("Dec","TPRH","id","clase","niv","tipo","dir","err","etq","etqh");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(pfs.a("TPRH"),Dec3.a("TPRH"));
        dependencias(cs.a("TPRH"),pfs.a("TPRH"));
        dependencias(pfs.a("TSLH"),Dec3.a("TSH"));
        dependencias(cs.a("TSLH"),pfs.a("TSL"));
        dependencias(Dec3.a("id"),identif);
        dependencias(Dec3.a("tipo"),pfs.a("tipo"));
        dependencias(Dec3.a("err"),pfs.a("err"),cs.a("err"));
        dependencias(cs.a("etqh"),Dec3.a("etqh"));
        dependencias(Dec3.a("etq"),cs.a("etq"));
        
        calculo(pfs.a("TPRH"),asignacion);
        calculo(cs.a("TPRH"),asignacion);
        calculo(pfs.a("TSLH"),creaTSL);
        calculo(cs.a("TSLH"),asignacion);
        calculo(Dec3.a("id"),asignacion);
        calculo(Dec3.a("clase"),asignaClaseSubPro);
        calculo(Dec3.a("niv"),asignacero);
        calculo(Dec3.a("tipo"),asignacion);
        calculo(Dec3.a("valor"),asignavacio);
        calculo(Dec3.a("dir"),asignacero);
        calculo(Dec3.a("err"),asignacionOR2);
        
        calculo(cs.a("etqh"),asignacion);
        calculo(Dec3.a("etq"),asignacion);
        
        return Dec3;
    }
    
    
   
    
    public TAtributos PFs0(TAtributos pfs1, TAtributos pf){
        regla("pfs → pfs, PF");
                
        TAtributos pfs0 = atributosPara("pfs","tipo","TSLH","TPRH","err");
        
        dependencias(pfs0.a("tipo"),pf.a("tipo"));//pfs.tipo= pf.tipo
        dependencias(pfs1.a("TSLH"),pfs0.a("TSLH"));//pfs1.TSLH= pfs0.TSLH
        dependencias(pf.a("TSLH"),pfs1.a("TSLH"));//pf.TSLH= pfs1.TSLH
        dependencias(pfs1.a("TPRH"),pfs0.a("TPRH"));//pfs1.TPRH= pfs0.TPRH
        dependencias(pf.a("TPRH"),pfs1.a("TPRH"));//pf.TPRH= pfs1.TPRH
        dependencias(pfs0.a("err"),pfs1.a("err"),pf.a("err"));//pfs0.err= pfs1.err OR pf.err
        
        calculo(pfs0.a("tipo"),asignacion);
        calculo(pfs1.a("TSLH"),asignacion);
        calculo(pf.a("TSLH"),asignacion);
        calculo(pfs1.a("TPRH"),asignacion);
        calculo(pf.a("TPRH"),asignacion);
        calculo(pfs0.a("err"),asignacionOR2);
        
        return pfs0;
    }
    
    public TAtributos PFs1(TAtributos pf){
        regla("PFs → PF");
                
        TAtributos PFs1 = atributosPara("PFs","tipo","TSLH","TPRH","err","TSH");
        
        dependencias(PFs1.a("tipo"),pf.a("tipo"));//PFs.tipo= pf.tipo
        dependencias(PFs1.a("TSL"),pf.a("TSLH"),pf.a("id"),pf.a("clase"),pf.a("niv"),pf.a("dir"),pf.a("tipo"),pf.a("valor"));
        dependencias(PFs1.a("err"),PFs1.a("TSH"),pf.a("id"),PFs1.a("TPRH"),pf.a("err"));
        
        calculo(PFs1.a("err"),estaId_OR_esReserv);
        calculo(PFs1.a("tipo"),asignacion);
        calculo(PFs1.a("TSL"),anadirTS);
        
        return PFs1;
    }
    
    public TAtributos PF0(TAtributos tipo, String id){
        regla("PF → tipo id");
                
        TAtributos PF0 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(PF0.a("id"),identif);//PF.id = ident.lex
        dependencias(PF0.a("tipo"),tipo.a("tipo"));//PF.tipo = tipo.tipo
        dependencias(PF0.a("dir"),PF0.a("id"),PF0.a("clase"));//PF.dir = tamañoDe(PF.id , PF.clase)
        dependencias(PF0.a("err"),tipo.a("err"));//PF.err= tipo.err
        
        calculo(PF0.a("id"),asignacion);
        calculo(PF0.a("clase"),asignaClasePF);
        calculo(PF0.a("niv"),asignauno);
        calculo(PF0.a("tipo"),asignacion);
        calculo(PF0.a("dir"),tamanoDe);
        calculo(PF0.a("err"),asignacion);
        calculo(PF0.a("valor"),asignavacio);
        
        return PF0;
    }
    
    
    public TAtributos PF1(TAtributos tipo, String id){
        regla("PF → tipo * ident");
                
        TAtributos PF1 = atributosPara("PF","id","clase","niv","tipo","dir","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(PF1.a("id"),identif);//PF.id = Designador.lex
        dependencias(PF1.a("tipo"),tipo.a("tipo"));//PF.tipo = tipo.tipo
        dependencias(PF1.a("dir"),PF1.a("id"),PF1.a("clase"));//PF.dir = tamañoDe(PF.id , PF.clase)
        dependencias(PF1.a("err"),tipo.a("err"));//PF.err= tipo.err
        
        calculo(PF1.a("id"),asignacion);
        calculo(PF1.a("clase"),asignaClasePF);
        calculo(PF1.a("niv"),asignauno);
        calculo(PF1.a("tipo"),asignacion);
        calculo(PF1.a("dir"),tamanoDe);
        calculo(PF1.a("err"),asignacion);
        calculo(PF1.a("valor"),asignavacio);
        
        return PF1;
    }
    

    public TAtributos CS(TAtributos decssubs, TAtributos Insts){
        regla("CS  → vars { DecsSubs } instructions { Insts }");
                
        TAtributos CS = atributosPara("CS","TPRH","TSLH","niv","tipo","dir","err","etq","etqh");
        
        dependencias(decssubs.a("TPRH"),CS.a("TPRH"));//DecsSubs.TPRH = CS.TPRH
        dependencias(decssubs.a("TSLH"),CS.a("TSLH"));//DecsSubs.TSLH = CS.TSLH
        dependencias(Insts.a("TSH"),decssubs.a("TSL"));//Insts.TSH = DecsSubs.TSL
        dependencias(CS.a("err"),decssubs.a("err"),Insts.a("err"));//CS.err= DecsSubs.err OR Insts.err
        dependencias(Insts.a("etqh"),CS.a("etqh"));
        dependencias(CS.a("etq"),Insts.a("etq"));
        
        calculo(decssubs.a("TPRH"),asignacion);
        calculo(decssubs.a("TSLH"),asignacion);
        calculo(Insts.a("tipo"),asignacion);
        calculo(CS.a("err"),asignacionOR2);

        calculo(Insts.a("etqh"),asignacion);
        calculo(CS.a("etq"),asignacion);
        
        return CS;
    }
    
    
    
    
    
    public TAtributos DecsSubs0(TAtributos decssubs,TAtributos decsub){
        regla("decssubs → decssubs; decsub");
                
        TAtributos decssubs0 = atributosPara("decssubs", "TSL","TSLH","TPRH","dir","err");
        
     

        dependencias(decssubs.a("TPRH"),decssubs0.a("TPRH"));
        dependencias(decsub.a("TPRH"),decssubs.a("TPRH"));
        dependencias(decssubs.a("TSLH"),decssubs0.a("TSLH"));
        dependencias(decsub.a("TSLH"),decssubs.a("TSLH"));
        dependencias(decssubs0.a("TSL"),decsub.a("TSLH"),decsub.a("id"),decsub.a("clase"),decsub.a("nivel"),decsub.a("dir"),decsub.a("tipo"),decsub.a("valor"));
        
        dependencias(decssubs0.a("err"),decssubs.a("err"),decsub.a("err"),decsub.a("id"),decssubs.a("TSLH"),decssubs.a("TPRH"));
        dependencias(decssubs0.a("dir"),decssubs.a("dir"),decsub.a("id"),decsub.a("clase"));

        
        calculo(decssubs.a("TPRH"),asignacion);
        calculo(decsub.a("TPRH"),asignacion);
        calculo(decssubs.a("TSLH"),asignacion);
        calculo(decsub.a("TSLH"),asignacion);
        calculo(decssubs0.a("TS"),anadirTS);
        calculo(decssubs0.a("dir"),dirMasTamano);
        calculo(decssubs0.a("err"),erroresDecs);
        
        return decssubs0;
        
    }
    
    public TAtributos DecsSubs1(TAtributos decsub){
        regla("DecsSubs → decsub");
                
        TAtributos DecsSubs1 = atributosPara("DecsSubs","TSL","TSLH","TPRH","dir","err");
        
        LAtributo Zero = new LAtributo("cero", 0);
        
        dependencias(decsub.a("TPRH"),DecsSubs1.a("TPRH"));
        dependencias(decsub.a("TSLH"),DecsSubs1.a("TSLH"));
        dependencias(DecsSubs1.a("err"),decsub.a("err"),decsub.a("id"),DecsSubs1.a("TSLH"),DecsSubs1.a("TPRH"));
        dependencias(DecsSubs1.a("TSL"),decsub.a("TSLH"),decsub.a("id"),decsub.a("clase"),decsub.a("nivel"),Zero,decsub.a("tipo"));
        dependencias(DecsSubs1.a("dir"),DecsSubs1.a("TSLH"),decsub.a("id"),decsub.a("clase"));

        calculo(decsub.a("TPRH"),asignacion);
        calculo(decsub.a("TSLH"),asignacion);
        calculo(DecsSubs1.a("err"),erroresDec);
        calculo(DecsSubs1.a("TS"),anadirTS);
        calculo(DecsSubs1.a("dir"),tamanoDe);
        
        return DecsSubs1;
        
    }
    
    public TAtributos DecsSubs2(){
        regla("DecsSubs → λ");
                
        TAtributos DecsSubs2 = atributosPara("DecsSubs","err");
        
        calculo(DecsSubs2.a("err"),errorFalso);
        
        return DecsSubs2;
        
    }
    
    
    public TAtributos DecSub0(TAtributos tipo,String id){
        regla("DecSub → var tipo ident");
                
        TAtributos DecSub0 = atributosPara("DecSub","TSLH","TPRH","id","clase","nivel","tipo","err");
        
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(DecSub0.a("id"),identif);
        dependencias(DecSub0.a("tipo"),tipo.a("tipo"));
        dependencias(tipo.a("TSH"),DecSub0.a("TSH"));
        dependencias(tipo.a("TPRH"),DecSub0.a("TPRH"));
        dependencias(DecSub0.a("err"),tipo.a("err"));
        
      
        calculo(DecSub0.a("id"),asignacero);
        calculo(DecSub0.a("clase"),asignaClaseSubVars);
        calculo(DecSub0.a("nivel"),asignauno);
        calculo(DecSub0.a("tipo"),asignacion);
        calculo(DecSub0.a("valor"),asignavacio);
        calculo(tipo.a("TSH"),asignacion);
        calculo(tipo.a("TPRH"),asignacion);
        calculo(DecSub0.a("err"),asignacion);
        
        return DecSub0;
    }

   

    public TAtributos designador1(String id){
        regla("designador → ident");
                
        TAtributos designador1 = atributosPara("designador","lex");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(designador1.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador1.a("lex"),asignacion);
        
        return designador1;
    }
    
    
    public TAtributos designador2(String id,TAtributos exp){
        regla("designador → ident[Exp]");
                
        TAtributos designador2 = atributosPara("designador");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(designador2.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador2.a("lex"),asignacion);
        return designador2;
    }
    
    public TAtributos designador3(String id){
        regla("designador → ident_numero");
                
        TAtributos designador3 = atributosPara("designador");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(designador3.a("lex"),identif);//designador.lex=ident.lex
        
        calculo(designador3.a("lex"),asignacion);
        
        return designador3;
    }
    

    
    public TAtributos Tipo0(TAtributos tipobasico){
        regla("Tipo → tipobasico");
                
        TAtributos Tipo0 = atributosPara("Tipo","tipo","err");
        
        dependencias(Tipo0.a("tipo"),tipobasico.a("tipo"));
        dependencias(Tipo0.a("err"),tipobasico.a("err"));

        calculo(Tipo0.a("tipo"),asignacion);
        calculo(Tipo0.a("err"),asignacion);
        
        return Tipo0;
    }
    
    public TAtributos Tipo1(String id){
        regla("Tipo → ident");
                
        TAtributos Tipo1 = atributosPara("Tipo","tipo","err");
        Atributo identif = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Tipo1.a("tipo"),Tipo1.a("TSH"),identif);
        dependencias(Tipo1.a("err"),Tipo1.a("TSH"),Tipo1.a("TPRH"),identif);

        calculo(Tipo1.a("tipo"),tipoDe);
        calculo(Tipo1.a("err"),estaId_OR_esReserv);
        
        return Tipo1;
    }
    
    public TAtributos Tipo2(TAtributos tipo,TAtributos comp){
        regla("tipo → tipo [Componente]");
                
        TAtributos tipo2 = atributosPara("tipo","tipo","err");
        
        dependencias(tipo2.a("tipo"),tipo.a("tipo"),comp.a("lex"));
        dependencias(tipo2.a("err"),tipo.a("err"),comp.a("err"));

        calculo(tipo2.a("tipo"),tipoArray);
        calculo(tipo2.a("err"),asignacionOR2);
        
        return tipo2;
    }
    
    /*Componente → numero 
       Componente0.lex = numero.lex
        Componente0.err=false
     */
    
    public TAtributos Componente0(Integer numEnt){
        regla("Componente → numero ");
                
        TAtributos Componente0 = atributosPara("Componente","lex","err");//Componente.lex = numero.lex
        Atributo identComp0 = atributoLexicoPara("Identificador", "lex", numEnt);//Componente.err=false
        
        dependencias(Componente0.a("lex"),identComp0);
        calculo(Componente0.a("lex"),asignacion);
        calculo(Componente0.a("err"),errorFalso);
        
        return Componente0;
    }
    
    /*Componente → ident
       Componente1.lex = ident.lex
       Componente1.err = esComponente(ident.lex)
*/
    public TAtributos Componente1(String id){
        regla("Componente → ident ");
                
        TAtributos Componente1 = atributosPara("Componente","lex");
        Atributo identComp1 = atributoLexicoPara("Identificador", "lex", id);
        
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
    public TAtributos Tipo3(TAtributos tipos){
        regla("Tipo → (TiposTupla) ");
                
        TAtributos Tipo3 = atributosPara("Tipo","tipo","err");
        
        dependencias(Tipo3.a("tipo"),tipos.a("tipo"));//Tipo3.tipo = TiposTupla.tipo
        dependencias(Tipo3.a("err"),tipos.a("err"));//Tipos3.err = TiposTupla.err
        
        calculo(Tipo3.a("tipo"),asignacion);
        calculo(Tipo3.a("err"),asignacion); 
        
        return Tipo3;
    }
    
    /*TiposTupla0 → TiposTupla1, Tipo
        TiposTupla 0.tipo = TiposTupla1.Tipo || tipoTupla(Tipo.tipo)
        TiposTupla 0.err = TiposTupla1.err OR Tipo.err
    */
    public TAtributos TiposTupla0(TAtributos tipostupla1, TAtributos tipo){
        regla("TiposTupla → TiposTupla , Tipo ");
                
        TAtributos TiposTupla0 = atributosPara("Tipos","tipo","err");
        
        dependencias(TiposTupla0.a("tipo"),tipostupla1.a("tipo"),tipo.a("tipo"));//TiposTupla 0.tipo = tipostupla1.Tipo || tipoTupla(Tipo.tipo)
        dependencias(TiposTupla0.a("err"),tipostupla1.a("err"),tipo.a("err"));//TiposTupla 0.err = tipostupla1.err OR Tipo.err
     
        calculo(tipo.a("tipo"),tipoTuplade2);
        calculo(TiposTupla0.a("err"),asignacionOR2); 
        return TiposTupla0;
    }
    
    /*TiposTupla → Tipo
        TiposTupla1.tipo = tipoTupla(Tipo.tipo)
        TiposTupla1.err = Tipo.err
     */
    
    public TAtributos TiposTupla1(TAtributos tipo){
        regla("TiposTupla → Tipo ");
                
        TAtributos TiposTupla1 = atributosPara("Tipo","tipo","err");
        
        dependencias(TiposTupla1.a("tipo"),tipo.a("tipo"));//TiposTupla1.tipo = tipoTupla(Tipo.tipo)
        dependencias(TiposTupla1.a("err"),tipo.a("err"));//TiposTupla1.err = Tipo.err

        calculo(tipo.a("tipo"),tipoTupla);
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
    
    
    public TAtributos Valores(Integer numnat){
        regla("Valores → numeroNat ");
                
        TAtributos Valores = atributosPara("Valores","valor","tipo");
        Atributo numRe = atributoLexicoPara("Identificador", "lex", numnat);
        
        dependencias(Valores.a("valor"),numRe);
        calculo(Valores.a("valor"),asignacion);
        calculo(Valores.a("tipo"),asignaTipoNat);
        
        return Valores;
    }
    
    public TAtributos Valores0(Float numreal){
        regla("Valores → numeroReal ");
                
        TAtributos Valores0 = atributosPara("Valores","valor","tipo");
        Atributo numRe = atributoLexicoPara("Identificador", "lex", numreal);
        
        dependencias(Valores0.a("valor"),numRe);
        calculo(Valores0.a("valor"),asignacion);
        calculo(Valores0.a("tipo"),asignaTipoFloat);
        
        return Valores0;
    }
    
    public TAtributos Valores1(Integer numEnt){
        regla("Valores → numeroEnt");
                
        TAtributos Valores1 = atributosPara("Valores","valor","tipo");
        Atributo numEnter = atributoLexicoPara("Identificador", "lex", numEnt);
   
        dependencias(Valores1.a("valor"),numEnter);
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
        
    public TAtributos Valores4(String id){
        regla("Valores → ’(letra)’ ");
        
        TAtributos Valores4 = atributosPara("Valores","valor");
        Atributo letras = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Valores4.a("valor"),letras);
        calculo(Valores4.a("valor"),asignacion);
        calculo(Valores4.a("tipo"),asignaTipoChar);
   
        return Valores4;
    }
    
    public TAtributos Valores5(String id){
        regla("Valores → ’(digito)’ ");
        
        TAtributos Valores5 = atributosPara("Valores","valor");
        Atributo digitos = atributoLexicoPara("Identificador", "lex", id);
        
        dependencias(Valores5.a("valor"),digitos);
        calculo(Valores5.a("valor"),asignacion);
        calculo(Valores5.a("tipo"),asignaTipoChar);
   
        return Valores5;
    }
    
    
    
    //////////////////// INSTRUCCIONES
    
    
    public TAtributos Insts0(TAtributos insts,TAtributos inst){
        regla("Insts → Insts ; Inst ");
                
        TAtributos insts0 = atributosPara("Insts", "err","cod","TSH","etq","etqh");
        
        //dependencias(insts.a("TSH"),insts0.a("TSH"));//insts1.TSH = insts0.TSH
        //dependencias(inst.a("TSH"),insts.a("TSH"));//inst.TSH = insts1.TSH
        dependencias(insts.a("etqh"),insts0.a("etqh"));//insts1.etqh= insts0.etqh
        dependencias(inst.a("etqh"),insts.a("etq"));//inst.etqh=insts1.etq
        dependencias(insts0.a("etq"),inst.a("etq"));// insts0.etq=inst.etq
        dependencias(insts0.a("err"),insts.a("err"),inst.a("err"));//inst0.err = inst1.err  OR  inst.err
        dependencias(insts0.a("cod"),insts.a("cod"),inst.a("cod"));//insts0.cod=insts1.Cod || inst.cod
        
        
       // calculo(insts.a("TSH"),asignacion);
       // calculo(inst.a("TSH"),asignacion);
        calculo(insts.a("etqh"),asignacion);
        calculo(inst.a("etqh"),asignacion);
        calculo(insts0.a("etq"),asignacion);
        calculo(insts0.a("err"),asignacionOR2);
        calculo(insts0.a("cod"),concatenar2);
        
        return insts0;
        
    }
    
    public TAtributos Insts1(TAtributos inst){
        regla("Insts → Inst ");
                
        TAtributos Insts1 = atributosPara("Insts", "err","cod","TSH","etq","etqh");
        
       // dependencias(inst.a("TSH"),Insts1.a("TSH"));//Inst.TSH = Insts.TSH
        dependencias(inst.a("etqh"),Insts1.a("etqh"));//Inst.etqh=Insts.etqh
        dependencias(Insts1.a("etq"),inst.a("etq"));//Insts.etq=Inst.etq
        dependencias(Insts1.a("err"),inst.a("err"));//Insts.err = Inst.err
        dependencias(Insts1.a("cod"),inst.a("cod"));//Insts.cod= Inst.cod
        
        
        //calculo(inst.a("TSH"),asignacion);
        calculo(inst.a("etqh"),asignacion);
        calculo(Insts1.a("etq"),asignacion);
        calculo(Insts1.a("err"),asignacion);
        calculo(Insts1.a("cod"),asignacion);
        
        return Insts1;
        
    }
    
    public TAtributos Inst0(TAtributos insasig){
        regla("Inst → insasig ");
                
        TAtributos Inst0 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
        
        dependencias(insasig.a("TSH"),Inst0.a("TSH"));//insasig.TSH = Inst.TSH 
        dependencias(insasig.a("etqh"),Inst0.a("etqh"));//insasig.etqh=Inst.etqh 
        dependencias(Inst0.a("etq"),insasig.a("etq"));//Inst.etq= insasig.etq
        dependencias(Inst0.a("err"),insasig.a("err"));//Inst.err = insasig.err
        dependencias(Inst0.a("cod"),insasig.a("cod"));//Inst.cod=insasig.cod 
        
        
        calculo(insasig.a("TSH"),asignacion);
        calculo(insasig.a("etqh"),asignacion);
        calculo(Inst0.a("etq"),asignacion);
        calculo(Inst0.a("err"),asignacion);
        calculo(Inst0.a("cod"),asignacion);
        
        return Inst0;
        
    }
    
    public TAtributos Inst1(TAtributos insr){
        regla("Inst → insr ");
                
        TAtributos Inst1 = atributosPara("Inst","err","cod","TSH","etq","etqh");
        
        dependencias(insr.a("TSH"),Inst1.a("TSH"));//insr.TSH = Inst.TSH 
        dependencias(insr.a("etqh"),Inst1.a("etqh"));//insr.etqh=Inst.etqh
        dependencias(Inst1.a("etq"),insr.a("etq"));//Inst.etq= insr.etq
        dependencias(Inst1.a("err"),insr.a("err"));//Inst.err = insr.err
        dependencias(Inst1.a("cod"),insr.a("cod"));//Inst.cod=insr.cod
        
        
        calculo(insr.a("TSH"),asignacion);
        calculo(insr.a("etqh"),asignacion);
        calculo(Inst1.a("etq"),asignacion);
        calculo(Inst1.a("err"),asignacion);
        calculo(Inst1.a("cod"),asignacion);
        
        return Inst1;
        
    }
    
    
    public TAtributos Inst2(TAtributos insw){
        regla("Inst → insw ");
                
        TAtributos Inst2 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
        
        dependencias(insw.a("TSH"),Inst2.a("TSH"));//insw.TSH=Inst.TSH 
        dependencias(insw.a("etqh"),Inst2.a("etqh"));//insw.etqh=Inst.etqh
        dependencias(Inst2.a("etq"),insw.a("etq"));//Inst.etq= insw.etq
        dependencias(Inst2.a("err"),insw.a("err"));//Inst.err = insw.err
        dependencias(Inst2.a("cod"),insw.a("cod"));//Inst.cod=insw.cod
        
        
        calculo(insw.a("TSH"),asignacion);
        calculo(insw.a("etqh"),asignacion);
        calculo(Inst2.a("etq"),asignacion);
        calculo(Inst2.a("err"),asignacion);
        calculo(Inst2.a("cod"),asignacion);
        
        return Inst2;
        
    }
    
    public TAtributos Inst3(){
        regla("Inst → SWAP1() ");
                
        TAtributos Inst3 = atributosPara("Inst","etq","etqh","err","cod");
        
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
                
        TAtributos Inst4 = atributosPara("Inst","etq","etqh","err","cod");
        
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
    public TAtributos Inst5(TAtributos ExpIf){
    	
    	regla("Inst → ExpIf ");
         
         TAtributos Inst5 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
         
         dependencias(ExpIf.a("TSH"),Inst5.a("TSH"));//ExpIf.TSH = Inst.TSH  
         dependencias(ExpIf.a("etqh"),Inst5.a("etqh"));//ExpIf.etqh=Inst.etqh
         dependencias(Inst5.a("etq"),ExpIf.a("etq"));//Inst.etq= ExpIf.etq
         dependencias(Inst5.a("err"),ExpIf.a("err"));//Inst.err = ExpIf.err
         dependencias(Inst5.a("cod"),ExpIf.a("cod"));//Inst.cod=ExpIf.cod
         
         
         calculo(ExpIf.a("TSH"),asignacion);
         calculo(ExpIf.a("etqh"),asignacion);
         calculo(Inst5.a("etq"),asignacion);
         calculo(Inst5.a("err"),asignacion);
         calculo(Inst5.a("cod"),asignacion);
         
         return Inst5;
        
    }

    public TAtributos Inst6(TAtributos ExpWhile){
        	
        	regla("Inst → ExpWhile ");
             
             TAtributos Inst6 = atributosPara("Inst","err","cod","TSH","etq","etqh");
             
             dependencias(ExpWhile.a("TSH"),Inst6.a("TSH"));//ExpWhile.TSH = Inst.TSH  
             dependencias(ExpWhile.a("etqh"),Inst6.a("etqh"));//ExpWhile.etqh=Inst.etqh
             dependencias(Inst6.a("etq"),ExpWhile.a("etq"));//Inst.etq= ExpWhile.etq
             dependencias(Inst6.a("err"),ExpWhile.a("err"));//Inst.err = ExpWhile.err
             dependencias(Inst6.a("cod"),ExpWhile.a("cod"));//Inst.cod=ExpWhile.cod
             
             
             calculo(ExpWhile.a("TSH"),asignacion);
             calculo(ExpWhile.a("etqh"),asignacion);
             calculo(Inst6.a("etq"),asignacion);
             calculo(Inst6.a("err"),asignacion);
             calculo(Inst6.a("cod"),asignacion);
             
             return Inst6;
            
        }
   
    public TAtributos Inst7(TAtributos llamada){
    
    	regla("Inst → llamada ");
         
         TAtributos Inst7 = atributosPara("Inst", "err","cod","TSH","etq","etqh");
         
         dependencias(llamada.a("TSH"),Inst7.a("TSH"));//llamada.TSH = Inst.TSH  
         dependencias(Inst7.a("err"),llamada.a("err"));//Inst.err = llamada.err
         dependencias(Inst7.a("cod"),llamada.a("cod"));//Inst.cod=llamada.cod
         dependencias(llamada.a("etqh"),Inst7.a("etqh"));//llamada.etqh=Inst.etqh
         dependencias(Inst7.a("etq"),llamada.a("etq"));// Inst.etq= llamada.etq
        

         calculo(llamada.a("TSH"),asignacion);
         calculo(Inst7.a("err"),asignacion);
         calculo(Inst7.a("cod"),asignacion);
         calculo(llamada.a("etqh"),asignacion);
         calculo(Inst7.a("etq"),asignacion);
         
         return Inst7;
        
    }
    
    public TAtributos InsAsig(TAtributos desig,TAtributos exp){
        regla("InsAsig → desig = exp ");
                
        TAtributos InsAsig = atributosPara("InsAsig","err","cod","TSH","etq","etqh");
        
        dependencias(exp.a("TSH"),InsAsig.a("TSH"));//exp.TSH = InsAsig.TSH
        dependencias(InsAsig.a("err"),InsAsig.a("TSH"),desig.a("lex"),exp.a("tipo"),exp.a("err"));//
        dependencias(InsAsig.a("etq"),exp.a("etq"));//exp.etqh=InsAsig.etqh
        dependencias(exp.a("etqh"),InsAsig.a("etqh"));//exp.etqh=InsAsig.etqh
        dependencias(InsAsig.a("cod"),exp.a("cod"),InsAsig.a("TSH"),desig.a("lex"));
  
  
        calculo(InsAsig.a("cod"),concatenarAsig);
        calculo(exp.a("TSH"),asignacion);
        calculo(InsAsig.a("err"),errorInsAsig);
        calculo(InsAsig.a("etq"),sumauno);
        calculo(exp.a("etqh"),asignacion);
  
        
        
        return InsAsig;
        
    }
    
    public TAtributos InsR(TAtributos desig){
        regla("InsR → in (desig) ");
                
        TAtributos InsR = atributosPara("InsR","err","cod","TSH","etq","etqh");
        
 
        dependencias(InsR.a("err"),InsR.a("TSH"),desig.a("lex"));
        dependencias(InsR.a("cod"),InsR.a("TSH"),desig.a("lex"));
        dependencias(InsR.a("etq"),desig.a("etq"));
    
        calculo(InsR.a("etq"),sumaDos);
        calculo(InsR.a("cod"),concatenarIn);
        calculo(InsR.a("err"),errorInsR);
        
        return InsR;
        
    }
    
   public TAtributos InsW(TAtributos exp) {
       
       regla("InsW → out (exp)");
       
       TAtributos InsW = atributosPara("InsW","TSH","err", "cod", "etq","etqh");
     
       dependencias(InsW.a("cod"), exp.a("valor"));
       dependencias(InsW.a("etq"),InsW.a("etqh"));
       dependencias(exp.a("TSH"),InsW.a("TSH"));
       dependencias(InsW.a("err"),exp.a("err"),exp.a("tipo"));
       
       calculo(InsW.a("etq"),sumaDos);
       calculo(InsW.a("cod"),concatenarOut);
       calculo(InsW.a("err"),errorInsW);
       calculo(exp.a("TSH"),asignacion);
       
       return InsW;
   }
    

   public TAtributos If0(TAtributos exp,TAtributos Insts0){
       regla("IF → if exp then Insts endif");
               
       TAtributos If0 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(exp.a("TSH"),If0.a("TSH"));
       dependencias(Insts0.a("TSH"),exp.a("TSH"));
       dependencias(If0.a("err"),exp.a("err"),Insts0.a("err"),exp.a("tipo"));
       
       dependencias(If0.a("cod"),exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"));
       dependencias(exp.a("etqh"),If0.a("etqh"));
       dependencias(Insts0.a("etqh"),exp.a("etq"));

       dependencias(If0.a("etq"),Insts0.a("etq"));
       dependencias(exp.a("irvh"),exp.a("etq"));
       dependencias(exp.a("irfh"),exp.a("etq"));
       
       
       calculo(exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(If0.a("err"),errorIf0_While0);

       calculo(Insts0.a("etqh"),sumauno);

       calculo(If0.a("cod"),concatenarIf1);
       calculo(If0.a("etq"),asignacion);
       calculo(exp.a("etqh"),asignacion);
       calculo(exp.a("irvh"),asignacion);
       calculo(exp.a("irfh"),asignacion);
       
       
       return If0;
       
   }
    
   public TAtributos If1(TAtributos exp,TAtributos Insts0, TAtributos Insts1){
       regla("IF → if  exp then Insts else Insts endif ");
       
       TAtributos If1 = atributosPara("If","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(exp.a("TSH"),If1.a("TSH"));
       dependencias(Insts0.a("TSH"),exp.a("TSH"));
       dependencias(Insts1.a("TSH"),Insts0.a("TSH"));
       dependencias(If1.a("err"),exp.a("err"),exp.a("tipo"),Insts0.a("err"),Insts1.a("err"));
       
       dependencias(If1.a("cod"),exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"),Insts1.a("etq"),Insts1.a("cod"));
       dependencias(exp.a("etqh"),If1.a("etqh"));
       dependencias(Insts0.a("etqh"),exp.a("etq"));
       dependencias(Insts1.a("etqh"),Insts0.a("etq"));
       dependencias(If1.a("etq"),Insts1.a("etq"));
       dependencias(exp.a("irvh"),exp.a("etq"));
       dependencias(exp.a("irfh"),exp.a("etq"));
       
       
       calculo(exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(Insts1.a("TSH"),asignacion);
       calculo(If1.a("err"),errorIf1);
       calculo(Insts0.a("etqh"),sumauno);
       calculo(Insts1.a("etqh"),sumauno);
       calculo(If1.a("cod"),concatenarIf2);
       calculo(If1.a("etq"),asignacion);
       calculo(exp.a("etqh"),asignacion);
       calculo(exp.a("irvh"),asignacion);
       calculo(exp.a("irfh"),asignacion);
       
       
       return If1;
       
   }
   
   
   public TAtributos While0(TAtributos exp, TAtributos Insts0){
	   regla("IF → if exp then Insts endif");
       
       TAtributos While0 = atributosPara("While","TSH","err", "cod","etq","etqh","irvh","irfh");
       
       
       dependencias(exp.a("TSH"),While0.a("TSH"));
       dependencias(Insts0.a("TSH"),exp.a("TSH"));
       dependencias(While0.a("err"),Insts0.a("err"));
       
       dependencias(While0.a("cod"),exp.a("cod"),Insts0.a("etq"),Insts0.a("cod"),While0.a("etqh"));
       dependencias(exp.a("etqh"),While0.a("etqh"));
       dependencias(Insts0.a("etqh"),exp.a("etq"));

       dependencias(While0.a("etq"),Insts0.a("etq"));
       dependencias(exp.a("irvh"),exp.a("etq"));
       dependencias(exp.a("irfh"),exp.a("etq"));
       
       
       calculo(exp.a("TSH"),asignacion);
       calculo(Insts0.a("TSH"),asignacion);
       calculo(While0.a("err"),asignacionOR2);

       calculo(Insts0.a("etqh"),sumauno);

       calculo(While0.a("cod"),concatenarWhile1);
       calculo(While0.a("etq"),asignacion);
       calculo(exp.a("etqh"),asignacion);
       calculo(exp.a("irvh"),asignacion);
       calculo(exp.a("irfh"),asignacion);
       
       
       return While0;
       
   }
    
   
   public TAtributos Llamada0(String ident,TAtributos Parametros){
       regla("LLAMADA → Call ident (Parametros)");
               
       TAtributos Llamada0 = atributosPara("Llamada","TSH","err", "cod","etq","etqh");

       
       dependencias(Parametros.a("TSH"),Llamada0.a("TSH"));
       dependencias(Llamada0.a("err"),Parametros.a("err"));
       dependencias(Llamada0.a("etq"),Parametros.a("etqh"));
       //dependencias(Llamada0.a("cod"),Parametros.a("etqh"));

       //calculo(Llamada0.a("cod"),asignacion);
       calculo(Parametros.a("TSH"),asignacion);
       calculo(Llamada0.a("err"),asignacion);
       calculo(Llamada0.a("etq"),sumauno);
       
       
       return Llamada0;
       
   }
   
   public TAtributos Parametros0(TAtributos Parametros1,TAtributos Parametro){
       regla("Parametros → Parametros, Parametro");
               
       TAtributos Parametros0 = atributosPara("Parametros","TSH","err","etq","etqh");
       
       dependencias(Parametros1.a("TSH"),Parametros0.a("TSH"));
       dependencias(Parametro.a("TSH"),Parametros1.a("TSH"));     
       dependencias(Parametros0.a("err"),Parametros1.a("err"),Parametro.a("err"));
       dependencias(Parametros1.a("etqh"),Parametros0.a("etqh"));
       dependencias(Parametro.a("etqh"),Parametros1.a("etq"));
       dependencias(Parametros0.a("etq"),Parametro.a("etq"));
       
       

       calculo(Parametros1.a("TSH"),asignacion);
       calculo(Parametro.a("TSH"),asignacion);
       calculo(Parametros0.a("err"),asignacionOR2);
       
       calculo(Parametros1.a("etqh"),asignacion);
       calculo(Parametro.a("etqh"),asignacion);
       calculo(Parametros0.a("etq"),asignacion);

       
       
       
       return Parametros0;
       
   }
   
   public TAtributos Parametros1(TAtributos Parametro){
       regla("Parametros →  Parametro");
               
       TAtributos Parametros1 = atributosPara("Parametros","TSH","err","etq","etqh");
       
       dependencias(Parametro.a("TSH"),Parametros1.a("TSH"));     
       dependencias(Parametros1.a("err"),Parametro.a("err"));
       dependencias(Parametro.a("etqh"),Parametros1.a("etqh"));     
       dependencias(Parametros1.a("etq"),Parametro.a("etq")); 

       calculo(Parametro.a("TSH"),asignacion);
       calculo(Parametros1.a("err"),asignacion);
       
       calculo(Parametro.a("etqh"),asignacion);
       calculo(Parametros1.a("etq"),asignacion);
       
       return Parametros1;
       
   }
    
   public TAtributos Parametro0(String ident,TAtributos exp){
       regla("Parametro → ident = exp ");
               
       TAtributos Parametro0 = atributosPara("Parametro","TSH","err","etq","etqh");
       Atributo identComp1 = atributoLexicoPara("Identificador", "lex", ident);
       
       
       dependencias(exp.a("TSH"),Parametro0.a("TSH"));     
       dependencias(Parametro0.a("err"),exp.a("err"),Parametro0.a("TSH"),identComp1,exp.a("tipo"));
       dependencias(exp.a("etqh"),Parametro0.a("etqh"));     
       dependencias(Parametro0.a("etq"),exp.a("etq")); 
       
       
       calculo(exp.a("TSH"),asignacion);
       calculo(Parametro0.a("err"),errorParam);
       calculo(exp.a("etqh"),asignacion);
       calculo(Parametro0.a("etq"),asignacion);
       
       
       return Parametro0;
       
   }
   

	public TAtributos Exp0(TAtributos exp01,TAtributos Op0,TAtributos exp02){
	    
		regla("exp → exp0 Op0 exp0");
	     
	     TAtributos exp0 = atributosPara("exp0", "TSH","tipo","err","cod","etq");
	     
	     dependencias(exp01.a("TSH"),exp0.a("TSH"));//exp0.TSH = exp.TSH
	     dependencias(exp0.a("err"),exp01.a("err"),exp02.a("err"));//exp.err = exp01.err  OR exp02.err
	     dependencias(exp0.a("tipo"),exp01.a("tipo"),exp02.a("tipo"));//exp.tipo = exp01.tipo  OR exp02.tipo 
	     dependencias(exp01.a("etqh"),exp0.a("etqh"));//exp01.etqh=exp0.etqh
	     dependencias(exp02.a("etqh"),exp01.a("etq"));//exp02.etqh=exp01.etq
	     dependencias(exp0.a("etq"),exp01.a("etq"));//exp0.etq=exp01.etq+1
	     dependencias(exp0.a("cod"),exp01.a("cod"),exp02.a("cod"),Op0.a("cod"));//expBool.cod= exp0.cod||exp1.cod||Op0.cod
	     
	     
	     calculo(exp01.a("TSH"),asignacion);
	     calculo(exp0.a("err"),asignacionOR2);
	     calculo(exp0.a("tipo"),asignacionOR2);
	     calculo(exp01.a("etqh"),asignacion);
	     calculo(exp02.a("etqh"),asignacion);
	     calculo(exp0.a("etq"),sumauno);
	     calculo(exp0.a("cod"),concatenarExp);
	     
	     return exp0;
	    
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