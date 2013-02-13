package MV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class InstruccionMW {

	/*
	 * Atributos de la clase:
	 * 
	 * pila: La pila de los operandos de la maquina. PC: Contador de programa.
	 * Al final de la ejecucion nos dice cuantas lineas tiene dicho programa. H:
	 * Indica si la maquina esta en ejecucion, parada por error, o acabo su
	 * ejecuci?n. ST: Puntero a la cima de la pila. Prog:Memoria de programas.
	 * Aqui habia puesto el nombre del fichero pero quizas deberia ser el codigo
	 * del programa. Mem: Memoria de datos estatica. fichero: Fichero donde se
	 * encuetra el codigo que va a ejecutar la MaquinaP. pasos: String con todos
	 * los pasos que ejecuta la MaquinaP.
	 */

	/*
	 * private Stack pila; private int PC; private int H; private int ST;
	 * private Vector Prog; private Vector Mem; private FileReader fichero;
	 * private String pasos; private int tamMem;
	 */

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

	/**
	 * El constructor de la clase MaquinaP que solo tiene el buffer de lectura
	 * del fichero como parametro de entrada.
	 * 
	 * @param file
	 *            Recibe como parametro el fichero a ejecutar para poder
	 *            inicializar todo.
	 * 
	 */
	
	public InstruccionMW (Vector<Object> mem) 
	{
		Mem = mem;
	}
	/*
	public InstruccionMW () 
	{
	}
	*/

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
	 * Aumenta el tama̱o del vector memoria segun las necesidades del
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
	
	/**
	 * Metodo que realiza una operacion de suma. Se desapilan los dos primeros
	 * elementos de la pila y se suman. Despues se apila en la cima el
	 * resultado, disminuye en 1 el puntero a la cima ya que habra un elemento
	 * menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R1) suma: Pila[ST-1] <-- Pila[ST-1] + Pila[ST] ST <-- ST -1 PC <-- PC +
	 * 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */

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

	/**
	 * Metodo que realiza una operacion de resta. Se desapilan los dos primeros
	 * elementos de la pila y se restan. Despues se apila en la cima el
	 * resultado, disminuye en 1 el puntero a la cima ya que habra un elemento
	 * menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R2) resta: Pila[ST-1] <-- Pila[ST-1] - Pila[ST] ST <-- ST -1 PC <-- PC +
	 * 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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

	/**
	 * Metodo que realiza una operacion de multiplicacion. Se desapilan los dos
	 * primeros elementos de la pila y se multiplican. Despues se apila en la
	 * cima el resultado, disminuye en 1 el puntero a la cima ya que habra un
	 * elemento menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R3) multiplica: Pila[ST-1] <-- Pila[ST-1] * Pila[ST] ST <-- ST -1 PC <--
	 * PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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

	/**
	 * Metodo que realiza una operacion de division. Se desapilan los dos
	 * primeros elementos de la pila y se dividen. Despues se apila en la cima
	 * el resultado, disminuye en 1 el puntero a la cima ya que habra un
	 * elemento menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R4) divide: Pila[ST-1] <-- Pila[ST-1] DIV Pila[ST] ST <-- ST -1 PC <--
	 * PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
			if (s1.doubleValue() == 0)
				throw new Exception("ERROR: Estas tratando de dividir por 0.");
			Double s = new Double(s2.doubleValue() / s1.doubleValue());
			pila.push(s);
		}
		else{
			Integer s1 = (Integer) ob;
			Integer s2 = (Integer) ob2;
			if (s1.intValue() == 0)
				throw new Exception("ERROR: Estas tratando de dividir por 0.");
			Integer s = new Integer(s2.intValue() / s1.intValue());
			pila.push(s);
		}
		
		haciendoMultInvertido = false;
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * La instruccion modulo se realiza entre la cima de la pila y el siguiente
	 * elemento. Se almacena el resultado en la cima de la pila, se disminuye en
	 * 1 el puntero a la cima ya que habra un elemento menos. Tambien se aumenta
	 * en uno el contador del programa.
	 * 
	 * (R27) mod: Pila[ST-1] <-- Pila [ST-1 ] % Pila [ST] ST <-- ST - 1 PC <--
	 * PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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

	/**
	 * Metodo que apila un entero en la pila. Se aumenta en uno el tamao de la
	 * pila y se apila el entero que recibe como parametro. Tambien se aumenta
	 * en uno el contador del programa.
	 * 
	 * ST <-- ST + 1 Pila[ST] <-- n PC <-- PC + 1
	 * 
	 * @param n
	 */
	public void apila(int n) {
		if (traza)
			System.out.println("apila");
		ST = ST + 1;
		pila.push(new Integer(n));
		PC = PC + 1;
	}
	
	/**
	 * Metodo que apila un real en la pila. Se aumenta en uno el tamao de la
	 * pila y se apila el real que recibe como parametro. Tambien se aumenta
	 * en uno el contador del programa.
	 * 
	 * ST <-- ST + 1 Pila[ST] <-- n PC <-- PC + 1
	 * 
	 * @param n
	 */
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
	
	/**
	 * Metodo que apila en la cima de la pila el valor que contiene la direccion
	 * de memoria que recibe como parametro. Se comprueba antes si la direccion
	 * de memoria pertenece a memoria estatica o memoria dinamica. Tambien se
	 * aumenta en uno el contador del programa.
	 * 
	 * (R6) apila-dir(d): ST <-- ST + 1 Pila[ST] <-- Mem[d] PC <-- PC + 1
	 * 
	 * @param d
	 *            direcion de la que se va a obtener el valor que apilar en la
	 *            cima.
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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

	/**
	 * Metodo que desapila la cima de la pila y lo guarda en la direccion de
	 * memoria que recibe como parametro. Se disminuye en uno el tamao de la
	 * pila y se comprueba si es memoria dinamica o estatica. Tambien se aumenta
	 * en uno el contador del programa.
	 * 
	 * (R7) desapila-dir(d): Mem[d] <-- Pila[ST] ST <-- ST -1 PC <-- PC + 1
	 * 
	 * @param d
	 *            Direccion de la memoria donde se almacenara el valor que hay
	 *            en la cima de la pila.
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
					Mem.set(d, pila.pop());
				} else {
					Mem.set(d, pila.pop());
				}
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que para la ejecucion de la mquina P cuando se recibe un final de
	 * fichero.
	 * 
	 * (R8) EOF: H <-- 1
	 */
	public void eof() {
		System.out.println("eof");
		H = 1;
	}

	/**
	 * Metodo que indica a la pila que pare la ejecucion con un error.
	 * 
	 * (R9) En cualquier otro caso, la m?quina entra en estado de error y se
	 * detiene la ejecuci?n. H <-- -1
	 */
	public void error() {
		System.out.println("error");
		H = -1;
	}

	/**
	 * Metodo que realiza una operacion and. Se desapilan los dos primeros
	 * elementos de la pila y se realiza una and. Despues se apila en la cima el
	 * resultado, disminuye en 1 el puntero a la cima ya que habra un elemento
	 * menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R13) And:
	 * 
	 * si pila[ST-1] == 0 pila[ST-1] <-- 0 si no pila[ST-1] <-- pila[ST] fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
	public void and() throws Exception {
		if (traza)
			System.out.println("and");
		if (ST < 1) {
			throw new Exception(
					"ERROR: And. La pila no contiene los datos necesarios.");
		}
		int a1 = ((Integer) pila.pop()).intValue();
		int a2 = ((Integer) pila.pop()).intValue();
		if (a2 == 0) {
			pila.push(0);
		} else {
			pila.push(a1);
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion or. Se desapilan los dos primeros
	 * elementos de la pila y se realiza la operacion. Despues se apila en la
	 * cima el resultado, disminuye en 1 el puntero a la cima ya que habra un
	 * elemento menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R14) Or:
	 * 
	 * si pila[ST-1] == 0 pila[ST-1] <-- pila[ST] si no pila[ST-1] <-- 1 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
	public void or() throws Exception {
		if (traza)
			System.out.println("or");
		if (ST < 1) {
			throw new Exception(
					"ERROR: Or. La pila no contiene los datos necesarios.");
		}
		int o1 = ((Integer) pila.pop()).intValue();
		int o2 = ((Integer) pila.pop()).intValue();
		if (o2 == 0) {
			pila.push(o1);
		} else {
			pila.push(1);
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de negacion. Se desapila la cima de la
	 * pila y se realiza una negacion. Despues se apila en la cima el resultado.
	 * Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R15) Not: si pila[ST] = 0 pila[ST] <-- 1 si no pila[ST] <-- 0 fsi
	 * 
	 * PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
	public void not() throws Exception {
		if (traza)
			System.out.println("not");
		if (ST < 0) {
			throw new Exception(
					"ERROR: Not. La pila no contiene los datos necesarios.");
		}
		int n = ((Integer) pila.pop()).intValue();
		if (n == 0) {
			pila.push(new Integer(1));
		} else {
			pila.push(new Integer(0));
		}
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion negacion de un entero. Se desapilan los
	 * dos primeros elementos de la pila y se realiza una and. Despues se apila
	 * en la cima el resultado, disminuye en 1 el puntero a la cima ya que habra
	 * un elemento menos. Tambien se aumenta en uno el contador del programa.
	 * 
	 * (R16) Neg: Pila[ST] <-- - Pila[ST] PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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

	/**
	 * Metodo que realiza una operacion de menor con dos operandos. Se desapilan
	 * los dos primeros elementos de la pila y se realiza la operacion. Despues
	 * se apila en la cima el resultado, disminuye en 1 el puntero a la cima ya
	 * que habra un elemento menos. Tambien se aumenta en uno el contador del
	 * programa.
	 * 
	 * (R17) Menor: si pila[ST-1] < pila[ST] pila[ST-1] <-- 1 si no pila[ST-1]
	 * <-- 0 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 < c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 < c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de menor o igual con dos operandos. Se
	 * desapilan los dos primeros elementos de la pila y se realiza la
	 * operacion. Despues se apila en la cima el resultado, disminuye en 1 el
	 * puntero a la cima ya que habra un elemento menos. Tambien se aumenta en
	 * uno el contador del programa.
	 * 
	 * (R18) MenorIgual: si pila[ST-1] <= pila[ST] pila[ST-1] <-- 1 si no
	 * pila[ST-1] <-- 0 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 <= c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 <= c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de myor con dos operandos. Se desapilan
	 * los dos primeros elementos de la pila y se realiza la operacion. Despues
	 * se apila en la cima el resultado, disminuye en 1 el puntero a la cima ya
	 * que habra un elemento menos. Tambien se aumenta en uno el contador del
	 * programa.
	 * 
	 * (R19) Mayor: si pila[ST-1] <= pila[ST] pila[ST-1] <-- 0 si no pila[ST-1]
	 * <-- 1 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 > c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 > c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de mayor o igual con dos operandos. Se
	 * desapilan los dos primeros elementos de la pila y se realiza la
	 * operacion. Despues se apila en la cima el resultado, disminuye en 1 el
	 * puntero a la cima ya que habra un elemento menos. Tambien se aumenta en
	 * uno el contador del programa.
	 * 
	 * (R20) MayorIgual: si pila[ST-1] < pila[ST] pila[ST-1] <-- 0 si no
	 * pila[ST-1] <-- 1 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 >= c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 >= c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de igual con dos operandos. Se desapilan
	 * los dos primeros elementos de la pila y se realiza la operacion. Despues
	 * se apila en la cima el resultado, disminuye en 1 el puntero a la cima ya
	 * que habra un elemento menos. Tambien se aumenta en uno el contador del
	 * programa.
	 * 
	 * (R21) Igual: si pila[ST-1] = pila[ST] pila[ST-1] <-- 1 si no pila[ST-1]
	 * <-- 0 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 == c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 == c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que realiza una operacion de distinto con dos operandos. Se
	 * desapilan los dos primeros elementos de la pila y se realiza la
	 * operacion. Despues se apila en la cima el resultado, disminuye en 1 el
	 * puntero a la cima ya que habra un elemento menos. Tambien se aumenta en
	 * uno el contador del programa.
	 * 
	 * (R22) Distinto: si pila[ST-1] = pila[ST] pila[ST-1] <-- 0 si no
	 * pila[ST-1] <-- 1 fsi
	 * 
	 * ST <-- ST - 1 PC <-- PC + 1
	 * 
	 * @throws Exception
	 *             Propaga una excepcion que haya sucedido en otro lugar.
	 */
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
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else if (esReal(ob.toString()) || esReal(ob2.toString())){
			double c1 = (new Double(ob.toString())).doubleValue();
			double c2 = (new Double(ob2.toString())).doubleValue();
			if (c2 != c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		else{
			int c1 = ((Integer) ob).intValue();
			int c2 = ((Integer) ob2).intValue();
			if (c2 != c1) {
				pila.push(new Integer(1));
			} else {
				pila.push(new Integer(0));
			}
		}
		ST = ST - 1;
		PC = PC + 1;
	}

	/**
	 * Metodo que lee un dato de entrada
	 * 
	 * (R23) Read: ST <-- ST+1 pila[ST] <-- READ PC <-- PC+1
	 * 
	 */
	public void read(String line) throws Exception {
		if (traza)
			System.out.println("read");
		/*InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		String line = in.readLine();*/
		
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
		
		ST++;
		PC++;
	}

	/**
	 * Metodo que muestra un dato por pantalla
	 * 
	 * (R23) Write: WRITE pila[ST] ST <-- ST-1 PC <-- PC+1
	 * 
	 */
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

	/**
	 * Metodo que obtiene el contenido de la pila en un String para ver su
	 * contenido.
	 * 
	 * @return String con el contenido de la Pila
	 */
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