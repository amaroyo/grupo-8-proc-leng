package definicionLex;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


enum estado {e0, e1, e2, e3, e4, e5, e6};

public class Alexico {
	
	
	
	private estado estadoAct;
	
	private BufferedReader br;
	private String[] identificadores;
	
	
	public void leerArchivo() {
		try {
		
		br = new BufferedReader(new FileReader("datos.txt"));
		String line = null;
		
		while((line=br.readLine())!=null){
			
			//Comprobamos la Cabecera
			String[] tokens = line.split(" ");
			compruebaProgram(tokens);
			System.out.println(line);
			
			//Comprobamos la Sección de Declaraciones
			line=br.readLine();
			tokens = line.split(" ");
		    compruebaDeclaraciones(tokens);
			System.out.println(line);
			
			//Comprobamos la Sección de Instrucciones
			line=br.readLine();
			tokens = line.split(" ");
		    compruebaInstrucciones(tokens);
			System.out.println(line);
			
			//Comprobamos corchete de fin de programa
			line=br.readLine();
			tokens = line.split(" ");
		    compruebaFinalProgram(tokens);
			System.out.println(line);
			
		}
		
		
		br.close();		
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
	
	public void compruebaProgram(String[] a){
		
		if(!a[0].equals("program:")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera del programa", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!identificadorValido(a[1])){
			JOptionPane.showMessageDialog(null, "Error en el identificador del programa", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!escorcheteAbierto(a[2])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura del programa", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void compruebaDeclaraciones(String[] a) {
		if(!a[0].equals("var-const")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!escorcheteAbierto(a[1])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura de la sección de Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!escorcheteCerrado(a[2])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de clausura de la sección de Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void compruebaInstrucciones(String[] a) {
		
		if(!a[0].equals("instructions")){
			JOptionPane.showMessageDialog(null, "Error en la cabecera de la seccion Decl", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!escorcheteAbierto(a[1])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de apertura de la sección de Instr.", "alert", JOptionPane.ERROR_MESSAGE);
		}
		if(!escorcheteCerrado(a[2])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de clausura de la sección de Instr.", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}



	private void compruebaFinalProgram(String[] a) {
		if(!escorcheteCerrado(a[0])){
			JOptionPane.showMessageDialog(null, "Error en el corchete de clausura del programa", "alert", JOptionPane.ERROR_MESSAGE);
		}
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
