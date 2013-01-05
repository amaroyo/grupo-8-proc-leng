package definicionLex;

import java.io.BufferedReader;


import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class Alexico {
	
	

	private BufferedReader br;
	private String[] identificadores;
	private int contErrores;
	
	public void leerArchivo() {
		try {
		
	   br = new BufferedReader(new FileReader("datos.txt"));
	   String line = null;
 	   int i=0; 	
 	
		while((line=br.readLine())!=null){
			
			//Comprobamos la Cabecera
			String[] tokens = line.split(" ");
			compruebaProgram(tokens,br);
						
		}
		br.close();	
		br = new BufferedReader(new FileReader("datos.txt"));
	    System.out.println("Programa de entrada: ");
	 	while((line=br.readLine())!=null){
				i++;
				
				System.out.println("Línea"+i+":"+"  "+line);
							
			}
		
		br.close();	
		
		if(contErrores>0){
			System.out.println("Número de errores="+contErrores);
		}
		else{
			mostrarTS();
		}
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}

///////////////////////         MAIN              ////////////////////////////////////
	
	public static void main(String args[]){
		Alexico a=new Alexico();
		a.leerArchivo();
	}
	
////////////////////// COMPROBACIONES DE LA GRAMÁTICA INCONTEXTUAL  ///////////////
	
	public void compruebaProgram(String[] a, BufferedReader br){
		try {
		if(!a[0].equals("program:")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera del programa", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		if(!identificadorValido(a[1])){
			JOptionPane.showMessageDialog(null, "Error en el identificador del programa", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		if(!escorcheteAbierto(a[2])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura del programa", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
	    
		//Comprobamos la Sección de Declaraciones
		String line=br.readLine();
		String[] tokens = line.split(" ");
	    compruebaSecDeclaraciones(tokens,br);
		
	    		
		//Comprobamos la Sección de Instrucciones
		line=br.readLine();
		tokens = line.split(" ");
	    compruebaSecInstrucciones(tokens,br);
		
		//Comprobamos corchete de fin de programa y que no hay nada escrito antes de el
		line=br.readLine();
		tokens = line.split(" ");
	    compruebaFinalProgram(tokens);
		
	    if((line=br.readLine())!=null){
			JOptionPane.showMessageDialog(null, "Error: Hay algo escrito al fin al del programa", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
	    }
		
		
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}


	private void compruebaSecDeclaraciones(String[] tokens, BufferedReader br) {
		
		try {
			
		if(!tokens[0].equals("var-const")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion Decl", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		if(!escorcheteAbierto(tokens[1])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura de la sección de Decl", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		
		//Comprobamos las Declaraciones
		
		
		String line=br.readLine();
		String[] token = null;
		
		while(identificadorValido(line)){
		token = line.split(" ");
	    compruebaDeclaraciones(token);
	    line=br.readLine();
		}
		token = line.split(" ");
		compruebaFinalSecDec(token);
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	private void compruebaDeclaraciones(String[] token) {
		
		if(!token[0].equals("varconst")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void compruebaFinalSecDec(String[] tokens) {
	if(!escorcheteCerrado(tokens[0])){
		JOptionPane.showMessageDialog(null, "Error en el corchete de clausura de la Secc. Decl.", "alert", JOptionPane.ERROR_MESSAGE);
		contErrores++;
	}
    }
	
	private void compruebaSecInstrucciones(String[] tokens, BufferedReader br) {
		
		try {
			
		if(!tokens[0].equals("instructions")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion de Instr", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		if(!escorcheteAbierto(tokens[1])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura de la sección de Instr.", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
		
		//Comprobamos las Instrucciones
		
		
				String line=br.readLine();
				String[] token = null;
				
				while(identificadorValido(line)){
				token = line.split(" ");
			    compruebaInstrucciones(token);
			    line=br.readLine();
				}
				token = line.split(" ");
				compruebaFinalSecInst(token);
				
					}
				catch (Exception e) {
					e.printStackTrace();
					}
		}
    

	private void compruebaInstrucciones(String[] token) {
		
		if(!token[0].equals("instructions")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void compruebaFinalSecInst(String[] tokens) {
		if(!escorcheteCerrado(tokens[0])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de clausura de la Secc. Inst.", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
	}



	private void compruebaFinalProgram(String[] tokens) {
		if(!escorcheteCerrado(tokens[0])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de clausura del programa", "alert", JOptionPane.ERROR_MESSAGE);
			contErrores++;
		}
	}

	////////////////////Creación TABLA DE SIMBOLOS////////////////////
	

private void mostrarTS() {
		
	
		
	}

	
	///////////////////EXPRESIONES REGULARES////////////////////////////
	
	private boolean escorcheteAbierto(String string) {
		Pattern  p = Pattern.compile("[{]");
		Matcher m = p.matcher(string);
		if( m.find()){
			return true;
			}else{
			return false;
			}
	}
	private boolean escorcheteCerrado(String string) {
		Pattern  p = Pattern.compile("[}]");
		Matcher m = p.matcher(string);
		if( m.find()){
			return true;
			}else{
			return false;
			}
	}
	private boolean identificadorValido(String string) {
		Pattern  p = Pattern.compile("[a-z]");
		Matcher m = p.matcher(string);
		// Comprobación de que el primer caracter es letra y minúscula
		if( m.find() && !Character.isUpperCase(string.charAt(0))){
			return true;
			}else{
			return false;
			}
	}
	
	
}
