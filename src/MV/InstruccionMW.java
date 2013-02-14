package MV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InstruccionMW {

	
	private Stack<Object> pila = new Stack<Object>();
	private int PC = 0;
	private int H = 0;
	private int ST = -1;
	private Vector<Object> Mem = new Vector<Object>();
	private int tamMem = Integer.MAX_VALUE;
	private boolean traza = false;
	private boolean multInvertido = false;
	private boolean sumaInvertido = false;
	
	private boolean haciendoSumaInvertido = false;
	private boolean haciendoMultInvertido = false;

	
	public InstruccionMW (Vector<Object> mem) 
	{
		Mem = mem;
	}

	/**
	 * Accesor para el atributo de la clase, Mem. Que indica el estado de la
	 * memoria del Programa.
	 * 
	 * @return Vector donde cada celda es una posicion de Memoria.
	 */
	public Vector<Object> getMem() {
		System.out.println("getMem");
		return Mem;
	}

	/**
	 * Mutador para el atributo de la clase, Mem. Que indica el estado de la
	 * memoria del Programa.
	 * 
	 * @param mem
	 *            Se recibe un vector a modo de memoria de Progrma.
	 */
	public void setMem(Vector<Object> mem) {
		System.out.println("setMem");
		Mem = mem;
	}

	/**
	 * Accesor para el atributo de la clase, PC. Que indica el contador de
	 * instrucciones del Programa.
	 * 
	 * @return Entero que indica el numero de instruccion.
	 */
	public int getPC() {
		System.out.println("getPC");
		return PC;
	}

	/**
	 * Mutador para el atributo de la clase, PC. Que indica el contador de
	 * instrucciones del Programa.
	 * 
	 * @param pc
	 *            Entero para actualizar la nueva poscion del programa.
	 */
	public void setPC(int pc) {
		System.out.println("setPC");
		PC = pc;
	}

	/**
	 * Accesor para el atributo de la clase, pila. Pila del programa.
	 * 
	 * @return Devuelve la pila del programa.
	 */
	public Stack<Object> getPila() {
		System.out.println("getPila");
		return pila;
	}

	/**
	 * Mutador para el atributo de la clase, pila. Se actualizara la pila del
	 * programa.
	 * 
	 * @param pila
	 *            una nueva pila con valores y operaciones.
	 */
	public void setPila(Stack<Object> pila) {
		System.out.println("setPila");
		this.pila = pila;
	}

	/**
	 * Accesor para el atributo la clase, ST. Se devuelve el valor que apunta a
	 * la cima de la pila.
	 * 
	 * @return Entero que apunta a la cima de la pila de la maquina.
	 */
	public int getST() {
		System.out.println("getST");
		return ST;
	}

	/**
	 * Mutador para el atributo de la clase ST. Se cambia el puntero a la cima
	 * de la pila, con lo que cambiara la cima de la pila.
	 * 
	 * @param st
	 *            Recibe por parametro el entero con el que cambiar la cima de
	 *            la pila.
	 */
	public void setST(int st) {
		System.out.println("setST");
		ST = st;
	}

	/**
	 * Aumenta el tamaÌ±o del vector memoria segun las necesidades del
	 * programa que va a ejecutar.
	 * 
	 * @param tam
	 *            Recibe un entero con el tamao que ha de aumentar.
	 */
	private void aumentoMem(int tam) {
		// System.out.println("aumentoMem");
		for (int i = Mem.size(); i < tam + 1; i++) {
			Mem.add(i, null);
		}
	}

	private boolean esReal(String linea) {
		if (linea.contains("."))
			return true;
		return false;
	}

	/**
	 * Metodo que devuelve un String con el contenido de la Memoria. Se usa para
	 * ver el contenido final de la memoria despues de ejecutar la maquina P.
	 * 
	 * @return String con el contenido del vector Memoria.
	 */
	public String resultadoMem() {
		// System.out.println("resultadoMem");
		String s = "\n" + "Memoria :" + "\n";
		for (int i = 0; i < Mem.size(); i++) {
			if (Mem.elementAt(i) != null) {
				s = s.concat("posicion " + i + ":  "
						+ ( Mem.elementAt(i)));
				s = s.concat(" \n");
			} else {
				s = s.concat("posicion " + i + ": " + " null");
				s = s.concat(" \n");
			}
		}
		return s;
	}

	public void swap1()
	{
		sumaInvertido = !sumaInvertido;
		PC = PC + 1;
	}
	
	public void swap2()
	{
		multInvertido = !multInvertido;
		PC = PC + 1;
	}
	
	public void suma() throws Exception {
		if ((sumaInvertido) && (!haciendoSumaInvertido))
		{
			haciendoSumaInvertido = true;
			this.resta();
			return;
		}
		
		if (traza)
			System.out.println("suma");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Suma. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (esReal(ob.toString()) || esReal(ob2.toString())){
			Double s1 = new Double(ob.toString());
			Double s2 = new Double(ob2.toString());
			Double s = new Double(s2.doubleValue() + s1.doubleValue());
			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			Integer s = new Integer(s2.intValue() + s1.intValue());
			pila.push(s);
		}
		
		haciendoSumaInvertido = false;
		ST = ST - 1;
		PC = PC + 1;
	}

	public void resta() throws Exception {
		if ((sumaInvertido) && (!haciendoSumaInvertido))
		{
			haciendoSumaInvertido = true;
			this.suma();
			return;
		}
		
		if (traza)
			System.out.println("resta");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Resta. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (esReal(ob.toString()) || esReal(ob2.toString())){
			Double s1 = new Double(ob.toString());
			Double s2 = new Double(ob2.toString());
			Double s = new Double(s2.doubleValue() - s1.doubleValue());
			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			Integer s = new Integer(s2.intValue() - s1.intValue());
			pila.push(s);
		}
		
		haciendoSumaInvertido = false;
		ST = ST - 1;
		PC = PC + 1;
	}

	public void multiplica() throws Exception {
		if ((multInvertido) && (!haciendoMultInvertido))
		{
			haciendoMultInvertido = true;
			this.divide();
			return;
		}
		
		if (traza)
			System.out.println("multiplica");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Multiplica. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (esReal(ob.toString()) || esReal(ob2.toString())){
			Double s1 = new Double(ob.toString());
			Double s2 = new Double(ob2.toString());
			Double s = new Double(s2.doubleValue() * s1.doubleValue());
			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			Integer s = new Integer(s2.intValue() * s1.intValue());
			pila.push(s);
		}
		
		haciendoMultInvertido = false;
		ST = ST - 1;
		PC = PC + 1;
	}

	public void divide() throws Exception {
		if ((multInvertido) && (!haciendoMultInvertido))
		{
			haciendoMultInvertido = true;
			this.multiplica();
			return;
		}
		
		if (traza)
			System.out.println("divide");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Divide. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (esReal(ob.toString()) || esReal(ob2.toString())){
			Double s1 = new Double(ob.toString());
			Double s2 = new Double(ob2.toString());
			//
			
			if (s1.doubleValue() == 0){
				JFrame frame = new JFrame("Error operacion");

			    // prompt the user to enter their name
			    JOptionPane.showMessageDialog(frame, "ERROR: Estas tratando de dividir por 0.");
				throw new Exception("ERROR: Estas tratando de dividir por 0.");
			}
			Double s = new Double(s2.doubleValue() / s1.doubleValue());

			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			
			
			if (s1.intValue() == 0){
				JFrame frame = new JFrame("Error operacion");

			    // prompt the user to enter their name
			    JOptionPane.showMessageDialog(frame, "ERROR: Estas tratando de dividir por 0.");
				throw new Exception("ERROR: Estas tratando de dividir por 0.");
			}
			Integer s = new Integer(s2.intValue() / s1.intValue());
			pila.push(s);
		}
		
		haciendoMultInvertido = false;
		ST = ST - 1;
		PC = PC + 1;
	}

	public void modulo() throws Exception {
		if (traza)
			System.out.println("modulo");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Modulo. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (esReal(ob.toString()) || esReal(ob2.toString())){
			Double s1 = new Double(ob.toString());
			Double s2 = new Double(ob2.toString());
			if (s1.doubleValue() == 0)
				throw new Exception("ERROR: Estas tratando de hacer el modulo  por 0.");
			Double s = new Double(s2.doubleValue() % s1.doubleValue());
			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			if (s1.intValue() == 0)
				throw new Exception("ERROR: Estas tratando de hacer el modulo por 0.");
			Integer s = new Integer(s2.intValue() % s1.intValue());
			pila.push(s);
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void apila(int n) {
		if (traza)
			System.out.println("apila");
		ST = ST + 1;
		pila.push(new Integer(n));
		PC = PC + 1;
	}
	public void apila(boolean n) {
		if (traza)
			System.out.println("apila");
		ST = ST + 1;
		pila.push(new Boolean(n));
		PC = PC + 1;
	}
	
	public void apila(double n) {
		if (traza)
			System.out.println("apila");
		ST = ST + 1;
		pila.push(new Double(n));
		PC = PC + 1;
	}
	
	public void apila(String n) {
		if (traza)
			System.out.println("apila");
		ST = ST + 1;
		pila.push(n);
		PC = PC + 1;
	}
	
	public void apila_dir(int d) throws Exception {
		if (traza)
			System.out.println("apila-dir");
		ST = ST + 1;
		// System.out.println("Con valor de dir " + d);
		if (d < tamMem) {
			if (d >= 0) {
				if ((Mem.size() >= d) && (!Mem.isEmpty())) {
					if (Mem.elementAt(d) != null)
						pila.push(Mem.elementAt(d));
					else
						throw new Exception("ERROR: Variable sin inicializar.");
					PC = PC + 1;
				} else {
					throw new Exception("ERROR: Variable sin inicializar.");
				}
			}
		} else {
			throw new Exception("ERROR: Variable sin declarar.");
		}
	}

	public void desapila_dir(int d) throws Exception {
		// Primero comprobamos que la memoria sea suficiente.
		// Sino lo es aumentamos el tama?o del vector.
		if (traza)
			System.out.println("desapila_dir");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Desapila_dir. La pila no contiene los datos necesarios.");
		}
		if (d < tamMem) {
			if (d >= 0) {
				if (d >= Mem.size()) {
					aumentoMem(d);
					
					//----------------------------------
					Object o=Mem.elementAt(d);
					Object cima=pila.pop();
					if (( cima instanceof String)&&( o instanceof String))
						if (((String)cima).length()>1)
						{
							JFrame frame = new JFrame("Error de longitud caracter");

						    // prompt the user to enter their name
						    JOptionPane.showMessageDialog(frame, "Caracter de mas de 1 longitud");
							throw new Exception("Error de longitud caracter");
						}
						else
							Mem.set(d, cima);
					else
						if ( o instanceof Integer)
						{
							
							if ( cima instanceof Integer)
								Mem.set(d, cima);
						}
						else
							if (( o instanceof Double))
							{
								if ( cima instanceof Integer)
									Mem.set(d,new Double(((Integer) cima).intValue()));
								if ( cima instanceof Double)
									Mem.set(d, cima);
							}
							else
								if (( cima instanceof Boolean)&&( o instanceof Boolean))
									Mem.set(d, cima);
								else{
									JFrame frame = new JFrame("Error de Tipos");

								    // prompt the user to enter their name
								    JOptionPane.showMessageDialog(frame, "Tipo de datos incompatibles");
									throw new Exception("Tipo de datos incompatibles");
								}
					//--------------------------------------------------------------
 					//Mem.set(d, pila.pop());
				} else {
					//----------------------------------
					Object o=Mem.elementAt(d);
					Object cima=pila.pop();
					if (( cima instanceof String)&&( o instanceof String))
						Mem.set(d, cima);
					else
						if (( o instanceof Integer))
						{
							
							if ( cima instanceof Integer)
								Mem.set(d, cima);
						}
						else
							if (( o instanceof Double))
							{
								if ( cima instanceof Integer)
									Mem.set(d,new Double(((Integer) cima).intValue()));
								if ( cima instanceof Double)
									Mem.set(d, cima);
							}
							else
								if (( cima instanceof Boolean)&&( o instanceof Boolean))
									Mem.set(d, cima);
								else
								{
									JFrame frame = new JFrame("Error de Tipos");

								    // prompt the user to enter their name
								    JOptionPane.showMessageDialog(frame, "Tipo de datos incompatibles");
									throw new Exception("Tipo de datos incompatibles");
								}
					//-----------------------------------------------
					//Mem.set(d, pila.pop());
				}
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void eof() {
		System.out.println("eof");
		H = 1;
	}

	public void error() {
		System.out.println("error");
		H = -1;
	}

	public void and() throws Exception {
		if (traza)
			System.out.println("and");
		if (ST < 1) {
			throw new Exception(
					"ERROR: And. La pila no contiene los datos necesarios.");
		}
		boolean a1 = ((Boolean) pila.pop()).booleanValue();
		boolean a2 = ((Boolean) pila.pop()).booleanValue();
		if (a2 == false) {
			pila.push(new Boolean (false));
		} else {
			pila.push(new Boolean (a1));
		}
		/*
		int a1 = ((Integer) pila.pop()).intValue();
		int a2 = ((Integer) pila.pop()).intValue();
		if (a2 == 0) {
			pila.push(0);
		} else {
			pila.push(a1);
		}
		
		*/
		ST = ST - 1;
		PC = PC + 1;
	}

	public void or() throws Exception {
		if (traza)
			System.out.println("or");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Or. La pila no contiene los datos necesarios.");
		}
		//
		boolean o1 = ((Boolean) pila.pop()).booleanValue();
		boolean o2 = ((Boolean) pila.pop()).booleanValue();
		if (o2 == false) {
			pila.push(new Boolean (o1));
		} else {
			pila.push(new Boolean (true));
		}
		//
		/*
		int o1 = ((Integer) pila.pop()).intValue();
		int o2 = ((Integer) pila.pop()).intValue();
		if (o2 == 0) {
			pila.push(o1);
		} else {
			pila.push(1);
		}
		*/
		
		ST = ST - 1;
		PC = PC + 1;
	}

	public void not() throws Exception {
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		boolean n = ((Boolean) pila.pop()).booleanValue();
		
		if (n == false) {
			pila.push(new Boolean (true));
			
		} else {
			pila.push(new Boolean (false));
		}
		/*
		int n = ((Integer) pila.pop()).intValue();
		if (n == 0) {
			pila.push(new Integer(1));
		} else {
			pila.push(new Integer(0));
		}
		
		*/
		PC = PC + 1;
	}

	public void menos() throws Exception {
		if (traza)
			System.out.println("menos");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Neg. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		if (esReal(ob.toString())){
			Double n = (Double) ob;
			if (n.doubleValue() != 0)
				pila.push(new Double(-(n.doubleValue())));
			else
				pila.push(n);
		}
		else{
			Integer n = (Integer) ob;
			if (n.intValue() != 0)
				pila.push(new Integer(-(n.intValue())));
			else
				pila.push(n);
		}
		PC = PC + 1;
	}

	public void menor() throws Exception {
		if (traza)
			System.out.println("menor");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Menor. La pila no contiene los datos necesarios.");
		}
		
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) < 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 < c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 < c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void menorIgual() throws Exception {
		if (traza)
			System.out.println("menorIgual");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Menor o igual. Memoria sin inicializar.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) <= 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 <= c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 <= c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void mayor() throws Exception {
		if (traza)
			System.out.println("mayor");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Mayor. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) > 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 > c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else
		{

			if ((ob instanceof Boolean)&&(ob2 instanceof Boolean))  
			{
				boolean c1=((Boolean)ob).booleanValue();
				boolean c2=((Boolean)ob2).booleanValue();
				if (c1)
				{ 
						pila.push(new Boolean(false));
				}
				else
					if (c2)
						pila.push(new Boolean(true));
					else
						pila.push(new Boolean(false));
			}
			else
			{
				int c1 = ((Integer) ob).intValue();
				int c2 = ((Integer) ob2).intValue();
				if (c2 > c1) {
					pila.push(new Boolean(true));
					//pila.push(new Integer(1));
				} else {
					pila.push(new Boolean(false));
					//pila.push(new Integer(0));
				}
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void mayorIgual() throws Exception {
		if (traza)
			System.out.println("mayorIgual");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Mayor o igual. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) >= 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 >= c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 >= c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void igual() throws Exception {
		if (traza)
			System.out.println("igual");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Igual. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) == 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 == c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 == c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	public void distinto() throws Exception {
		if (traza)
			System.out.println("distinto");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Distinto. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		if (ob instanceof String)
		{
			String c1 = (String) ob;
			String c2 = (String) ob2;
			if (c2.compareTo(c1) != 0) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 != c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 != c1) {
				pila.push(new Boolean(true));
				//pila.push(new Integer(1));
			} else {
				pila.push(new Boolean(false));
				//pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	//Esto debe hacer push con el objeto correcto. Si mete un nat o int, meterlo en un Integer, si char, meterlo en un string, si es real meterlo en un double. Si es booleano, meterlo en un Boolean.
	public void read(String line) throws Exception {
		if (traza)
			System.out.println("read");
		/*InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		String line = in.readLine();*/
		
		//
		try
		{
			//es un int
			int valor = (new Integer(line)).intValue();
			pila.push(new Integer(valor));
			
		}
		catch (Exception exInt )
		{
			try
			{
				//
				//es un double
				double valor = (new Double(line)).doubleValue();
				pila.push(new Double(valor));	
			}
			catch (Exception exDouble)
			{
				//es un boolean
				try{
					if (line.toLowerCase().equals("true"))
						pila.push (new Boolean(true));
					if (line.toLowerCase().equals("false"))
						pila.push (new Boolean(false));
				}
				catch (Exception exBoolean){
					//es un string
					pila.push (line);	
				}
				
			}
		}
		//
		
		/*
		try
		{
			//es un double
			double valor = (new Double(line)).doubleValue();
			pila.push(new Double(valor));
		}
		catch (Exception exDouble)
		{
			try
			{
				//es un int
				int valor = (new Integer(line)).intValue();
				pila.push(new Integer(valor));
			}
			catch (Exception exInt)
			{
				//es un string
				pila.push (line);
			}
		}
		*/
		
		ST++;
		PC++;
	}

	public void write() throws Exception {
		if (traza)
			System.out.println("write");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Write. La pila no contiene los datos necesarios.");
		}
		Object ob = pila.pop();
		System.out.println(ob.toString());
		
		JDialog jd = new JDialog();
		jd.setBounds(200, 400, 100, 80);
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setTitle("OUT");
		jd.setModal(false);
		
		JLabel lblOut = new JLabel(ob.toString());
		lblOut.setBounds(10, 5, 30, 20);
		jd.add(lblOut);
		
		jd.setVisible(true);
		
		ST--;
		PC++;
	}
	
	public void convertoToInt() throws Exception
	{
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		
		Object ob = pila.pop();
		if (ob instanceof String)
		{
			Integer n = new Integer((int)(ob.toString().charAt(0)));
			pila.push(n);
		}
		else if (ob instanceof Double)
		{
			Integer n = new Integer(((Double)ob).intValue());
			pila.push(n);
		}
		else
		{
			Integer n = (Integer)ob;
			pila.push(n);
		}

		PC = PC + 1;
	}
	
	public void convertToReal() throws Exception
	{
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		
		Object ob = pila.pop();
		
		if (ob instanceof String)
		{
			Double n = new Double((double)(ob.toString().charAt(0)));
			pila.push(n);
		}
		else if (ob instanceof Double)
		{
			Double n = (Double)ob;
			pila.push(n);
		}
		else
		{
			Double n = new Double(((Integer)ob).doubleValue());
			pila.push(n);
		}

		PC = PC + 1;
	}
	
	public void convertToString() throws Exception
	{
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		
		Object ob = pila.pop();
		
		if (ob instanceof String)
		{
			String n = (String)ob;
			pila.push(n);
		}
		else if (ob instanceof Integer)
		{
			char [] x = new char[1];
			x[0] = ((char)((Integer)ob).intValue());
			String n = new String(x);
			pila.push(n);
		}
		else
		{
			//no se permite convertir a char algo que no sea char o integer
			pila.push(ob);
		}

		PC = PC + 1;
	}
	
	public void convertToNat() throws Exception
	{
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		
		Object ob = pila.pop();
		
		if (ob instanceof String)
		{
			Integer n = new Integer((int)(ob.toString().charAt(0)));
			pila.push(n);
		}
		else if (ob instanceof Integer)
		{
			Integer n = (Integer)ob;
			pila.push(n);
		}
		else
		{
			//No admite nada que no sea un nat o un char
			pila.push(ob);
		}

		PC = PC + 1;
	}
	
	public void despIzq() throws Exception
	{
		if (traza)
			System.out.println("igual");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Igual. La pila no contiene los datos necesarios.");
		}
		
		//solo opera sobre naturales, si nos encontramos otra cosa falla
		
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		int c1 = ((Integer) ob).intValue();
		int c2 = ((Integer) ob2).intValue();
		c2 <<= c1;
			
		pila.push(new Integer(c2));

		ST = ST - 1;
		PC = PC + 1;
	}
	
	public void despDer() throws Exception
	{
		if (traza)
			System.out.println("igual");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Igual. La pila no contiene los datos necesarios.");
		}
		
		//solo opera sobre naturales, si nos encontramos otra cosa falla
		
		Object ob = pila.pop();
		Object ob2 = pila.pop();
		
		int c1 = ((Integer) ob).intValue();
		int c2 = ((Integer) ob2).intValue();
		c2 >>= c1;
			
		pila.push(new Integer(c2));

		ST = ST - 1;
		PC = PC + 1;
	}

	public String muestraPila() {
		// System.out.println("muestraPila");
		Stack<Object> aux = new Stack<Object>();
		String pilas = "El contenido de la pila es: \n";
		while (!pila.isEmpty()) {
			Object n = pila.pop();
			pilas = pilas.concat(n.toString());
			pilas = pilas.concat("\n");
			aux.push(n);
		}
		while (!aux.isEmpty()) {
			Object n = aux.pop();
			pila.push(n);
		}
		return pilas;
	}
}