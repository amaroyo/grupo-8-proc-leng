package MV;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;

import parser.ByteCode;
import parser.tByteCode;

public class Interprete {
	
public Interprete()
{
	
}


private Vector <Object> datosParaInterprete;
private JTextArea textArea;

public void generar(String ruta,int modo,Vector <Object> datosParaInterprete1, JTextArea textArea) throws Exception 
{
	boolean error=false;
	instr=new Vector<byte[]>();
	
	datosParaInterprete=datosParaInterprete1;
	this.textArea = textArea;
	
	/** Para probar la memoria(nos lo tiene que pasar)**/
	/*
	GeneradorFichero it=new GeneradorFichero();
	HashMap<Integer, String> dirMemoria= new HashMap<Integer, String>();
	dirMemoria.put(0,"int:0");
	dirMemoria.put(1,"int:0");
	/*
	dirMemoria.put(1,"bool:true");
	dirMemoria.put(2,"nat:1900");
	dirMemoria.put(3,"real:186.35");
	dirMemoria.put(4,"null");
	dirMemoria.put(5,"char:h");
	*/
	int longitud=datosParaInterprete.size()/5;
	Vector <Object> memoria=new Vector <Object>(longitud);
	for (int l=0;l<longitud;l++)
		memoria.add(new Object());
	
	//int longitud=datosParaInterprete.size()/5;
	for(int j=0;j<longitud;j++)
	{
		memoria.remove(((Integer)datosParaInterprete.elementAt((j*5)+3)).intValue());
		memoria.add(((Integer)datosParaInterprete.elementAt((j*5)+3)).intValue(),datosParaInterprete.elementAt((j*5)+4));
	}
	
	//Vector <Object> memoria=it.generarMemoria(dirMemoria);
	
	
	
	/**fin prueba*/
	mw=new InstruccionMW(memoria, textArea);
	File archivo = null;
	FileReader fr= null;
	BufferedReader br = null;
	archivo = new File (ruta);
	
		fr = new FileReader (archivo);	
		br=new BufferedReader(fr);
		String linea="";
		StringTokenizer stringTokenizer;
		while (((linea=br.readLine())!= null) && (!error))
		{
			stringTokenizer = new StringTokenizer(linea, " ");
			while ((stringTokenizer.hasMoreElements()) &&(!error)) 
			{
				byte[] array=convertirArrayByte(stringTokenizer.nextToken());
				if (array==null)
					error=true;
				instr.addElement(array);	
			}
		} 
		if (error)
			System.out.println("Hubo errores");
		else
			System.out.println("Instrucciones: ");
		
		//System.out.println("array bits entero: " +bytesToString(intToBytes(-30100)));
		//System.out.println("array bits float: " +bytesToString(floatToByteArrayBE((float)-118.625)));
		//System.out.println("array bits double: " +bytesToString(doubleToByteArrayBE((double)1.7974745471064497E308)));
		//System.out.println("array bits: " +bytesToString(new byte[]{(byte) 0x8A,0x6C}));
		//System.out.println("Numero :"+convertirBinToDec(new byte[]{(byte) 0x8A,(byte) 0x6C}));
		//System.out.println("Numero float:"+byteArrayToFloatBE(new byte[]{(byte) 0xC2,(byte) 0xED,0x40,0x00}));
		//System.out.println("Numero double:"+byteArrayToDoubleBE(new byte[]{(byte) 0x7f,(byte) 0xef,(byte)0xFF,(byte)0x00,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF}));
		imprimirInstr(instr,datosParaInterprete,modo);
		
		

		try {
			if (fr != null)
				fr.close();
 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	
}

public String imprimirMemoria()
{
	Vector <Object> mem=mw.getMem();
	boolean enc=false;
	int k=3;
	int longitud=datosParaInterprete.size();
	int i=0;
	while (i<mem.size())
	{
		k=3;
		while ((!enc)&&(k < longitud))
		{
			if ((((Integer)datosParaInterprete.elementAt(k)).intValue())==i)
			{
				//datosParaInterprete.remove(k+1);
				//datosParaInterprete.setElementAt(mem.elementAt(i), k+1);
				datosParaInterprete.setElementAt(mem.elementAt(i),k+1);
				enc=true;
			}
			k=k+5;
		}
		enc=false;
		i=i+1;
	}
	int len=datosParaInterprete.size()/5;
	String res="";
	for(int j=0;j<len;j++)
	{
		res=res+"Nombre variable: "+datosParaInterprete.elementAt(j*5)+"|";//id
		res=res+"tipo: "+datosParaInterprete.elementAt(j*5+1)+"|";//tipo
		res=res+"cte: "+datosParaInterprete.elementAt(j*5+2)+"|";//cte
		res=res+"Dir: "+datosParaInterprete.elementAt(j*5+3)+"|";//dir
		
		if ((datosParaInterprete.elementAt(j*5+1).equals("tipoVarBooleano")))
		{
		    if (((Boolean)datosParaInterprete.elementAt(j*5+4)).booleanValue()==false)
		    	res=res+"Valor: false"+"\n";//valor
		    else
		    	res=res+"Valor: true"+"\n";//valor
		}
		else
			res=res+"Valor: "+datosParaInterprete.elementAt(j*5+4)+"\n";//valor
	}
		
	return res;
	
}

private void imprimirInstr(Vector<byte[]> v,Vector <Object> datosMem, int modoTraza) throws Exception 
{
	int i=0;
	while (i<v.size())
	{
		if (modoTraza == 1)
		{
		   JFrame frame = new JFrame("Paso a paso");
		   JOptionPane.showMessageDialog(frame, "Pulse para seguir");
		   
		   textArea.append("*************PASO A PASO************* \n");
		}
		
		
		byte [] array=(byte[])(v.elementAt(i));
		switch (array[0]) {
			case Operaciones.MENOR:
			{
				if (modoTraza == 1) textArea.append("MENOR \n");
				System.out.println("MENOR");
				try {
					mw.menor();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.MAYOR:
			{
				if (modoTraza == 1) textArea.append("MAYOR \n");
				System.out.println("MAYOR");
				try {
					mw.mayor();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.MENOR_IGUAL:
			{
				if (modoTraza == 1) textArea.append("MENOR_IGUAL \n");
				System.out.println("MENOR_IGUAL");
				try {
					mw.menorIgual();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.MAYOR_IGUAL:
			{
				if (modoTraza == 1) textArea.append("MAYOR_IGUAL \n");
				System.out.println("MAYOR_IGUAL");
				try {
					mw.mayorIgual();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.DISTINTO:
			{
				if (modoTraza == 1) textArea.append("DISTINTO \n");
				System.out.println("DISTINTO");
				try {
					mw.distinto();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.SUMA:
			{
				if (modoTraza == 1) textArea.append("SUMA \n");
				System.out.println("SUMA");
				try {
					mw.suma();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.RESTA:
			{
				if (modoTraza == 1) textArea.append("RESTA \n");
				System.out.println("RESTA");
				try {
					mw.resta();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.PRODUCTO:
			{
				if (modoTraza == 1) textArea.append("PRODUCTO \n");
				System.out.println("PRODUCTO");
				try {
					mw.multiplica();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.DIVISION:
			{
				if (modoTraza == 1) textArea.append("DIVISION \n");
				System.out.println("DIVISION");
				mw.divide();
				i++;
				break;
			}
			case Operaciones.MODULO:
			{
				if (modoTraza == 1) textArea.append("MODULO \n");
				System.out.println("MODULO");
				try {
					mw.modulo();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.AND:
			{
				if (modoTraza == 1) textArea.append("AND \n");
				System.out.println("AND");
				try {
					mw.and();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.OR:
			{
				if (modoTraza == 1) textArea.append("OR \n");
				System.out.println("OR");
				try {
					mw.or();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.NOT:
			{
				if (modoTraza == 1) textArea.append("NOT \n");
				System.out.println("NOT");
				try {
					mw.not();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.NEG:
			{
				if (modoTraza == 1) textArea.append("NEG \n");
				System.out.println("NEG");
				try {
					mw.menos();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.DESPLAZAMIENTO_IZQUIERDA:
			{
				if (modoTraza == 1) textArea.append("DESPLAZAMIENTO_IZQUIERDA \n");
				System.out.println("DESPLAZAMIENTO_IZQUIERDA");
				try {
					mw.despIzq();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.DESPLAZAMIENTO_DERECHA:
			{
				if (modoTraza == 1) textArea.append("DESPLAZAMIENTO_DERECHA \n");
				System.out.println("DESPLAZAMIENTO_DERECHA");
				try {
					mw.despDer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.CONVERSION_NAT:
			{
				if (modoTraza == 1) textArea.append("CONVERSION_NAT \n");
				System.out.println("CONVERSION_NAT");
				try {
					mw.convertToNat();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.CONVERSION_INT:
			{
				if (modoTraza == 1) textArea.append("CONVERSION_INT \n");
				System.out.println("CONVERSION_INT");
				try {
					mw.convertoToInt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.CONVERSION_CHAR:
			{
				if (modoTraza == 1) textArea.append("CONVERSION_CHAR \n");
				System.out.println("CONVERSION_CHAR");
				try {
					mw.convertToString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.CONVERSION_FLOAT:
			{
				if (modoTraza == 1) textArea.append("CONVERSION_FLOAT \n");
				System.out.println("CONVERSION_FLOAT");
				try {
					mw.convertToReal();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.VALOR_ABSOLUTO:
			{
				if (modoTraza == 1) textArea.append("VALOR_ABSOLUTO \n");
				System.out.println("VALOR_ABSOLUTO");
				i++;
				break;
			}
			case Operaciones.LEER:
			{
				if (modoTraza == 1) textArea.append("LEER \n");
				System.out.println("LEER");
		
				    // a jframe here isn't strictly necessary, but it makes the example a little more real
				    JFrame frame = new JFrame("InputDialog Example #1");

				    // prompt the user to enter their name
				    String valor = JOptionPane.showInputDialog(frame, "Input");
				    
					mw.read(valor);
			
				i++;
				break;
			}
			case Operaciones.ESCRIBIR:
			{
				if (modoTraza == 1) textArea.append("ESCRIBIR \n");
				System.out.println("ESCRIBIR");
				try {
					mw.write();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				break;
			}
			case Operaciones.APILA:
			{
				if (modoTraza == 1) textArea.append("APILA \n");

				System.out.print("APILA ");
				if (convertirBinToDec(instr.elementAt(i+1))==0)//Si es natural 
				{
					System.out.println(convertirBinToDec(instr.elementAt(i+2)));
					mw.apila((int)convertirBinToDec(instr.elementAt(i+2)));
				}
				else
					if (convertirBinToDec(instr.elementAt(i+1))==1)//Si es  entero
					{
						System.out.println(convertirBinToDec(instr.elementAt(i+2)));
						mw.apila((long)convertirBinToDec(instr.elementAt(i+2)));
					}
					else
					if (convertirBinToDec(instr.elementAt(i+1))==2)//Si es punto flotante doble
					{
						System.out.println(byteArrayToDoubleBE(instr.elementAt(i+2)));//Si es punto flotante doble
						mw.apila((double)byteArrayToDoubleBE(instr.elementAt(i+2)));
					}
					else
					{		
							if (convertirBinToDec(instr.elementAt(i+1))==3)//Si es boolean
							{
								if (convertirBinToDec(instr.elementAt(i+2))==0)//si es false
								{
									System.out.println("false");//es false
									//mw.apila("false");
									//mw.apila(new Integer(0));
									mw.apila(new Boolean(false));
								}
								else
								{
									System.out.println("true");// es true
									//mw.apila("true");
									//mw.apila(new Integer(1));
									mw.apila(new Boolean(true));
								}
								
			
							}
							else//es char
							{
								System.out.println(new String(instr.elementAt(i+2)));//Si es char
								mw.apila(new String(instr.elementAt(i+2)));
							}
					}
				i=i+3;
				break;
			}
			case Operaciones.APILA_DIR:
			{
				if (modoTraza == 1) textArea.append("APILA_DIR \n");

				System.out.print("APILA_DIR ");
				System.out.println(convertirBinToDec(instr.elementAt(i+1)));
				try {
					mw.apila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA:
			{
				if (modoTraza == 1) textArea.append("DESAPILA \n");

				System.out.print("DESAPILA ");
				System.out.println(convertirBinToDec(instr.elementAt(i+1)));
				//try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
		
				
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA_DIR_BOOLEAN:
			{
				if (modoTraza == 1) textArea.append("DESAPILA_DIR_BOOLEAN \n");

				System.out.println("DESAPILA_DIR_BOOLEAN");
				try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA_DIR_INTEGER:
			{
				if (modoTraza == 1) textArea.append("DESAPILA_DIR_INTEGER \n");

				System.out.print("DESAPILA_DIR_INTEGER ");
				System.out.println(convertirBinToDec(instr.elementAt(i+1)));
				try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA_DIR_NATURAL:
			{
				if (modoTraza == 1) textArea.append("DESAPILA_DIR_NATURAL \n");

				System.out.print("DESAPILA_DIR_NATURAL ");
				System.out.println(convertirBinToDec(instr.elementAt(i+1)));
				try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA_DIR_FLOAT:
			{
				if (modoTraza == 1) textArea.append("DESAPILA_DIR_FLOAT \n");

				System.out.print("DESAPILA_DIR_FLOAT ");
				System.out.println(convertirBinToDec(instr.elementAt(i+1)));//La dir siempre es un entero positivo
				try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.DESAPILA_DIR_CHAR:
			{
				if (modoTraza == 1) textArea.append("DESAPILA_DIR_CHAR \n");

				System.out.println("DESAPILA_DIR_CHAR");
				try {
					mw.desapila_dir((int)convertirBinToDec(instr.elementAt(i+1)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=i+2;
				break;
			}
			case Operaciones.STOP:
			{
				if (modoTraza == 1) textArea.append("STOP \n");

				System.out.println("STOP");
				System.out.print("Resultado memoria: "+mw.resultadoMem());
			
				//mw.resultadoMem();
				i++;
				break;
			}
			case Operaciones.SWAP1:
			{
				if (modoTraza == 1) textArea.append("SWAP1 \n");

				System.out.println("SWAP1");
				mw.swap1();
				i++;
				break;
			}
			case Operaciones.SWAP2:
			{
				if (modoTraza == 1) textArea.append("SWAP2 \n");
				
				System.out.println("SWAP2");
				mw.swap2();
				i++;
				break;
			}
	
			default:
			{
				if (modoTraza == 1) textArea.append("UNKNOWN \n");
				
				System.out.println("UNKNOWN");
				i++;
				break;
			}
		} 
		
		if (modoTraza == 1)
		{
			textArea.append(mw.resultadoMem() + "\n");
			textArea.append(mw.muestraPila());

		}
				
	}
}
	
private byte[] convertirArrayByte(String cad)//Cad tendra un multiplo de 8 bits
{
	String s1;
	int longitud=cad.length()/8;
	if ((cad.length() % 8)!=0)//si no es multiplo de 8 
		return null;
	byte [] array=new byte[longitud];
	for (int i=0;i<longitud;i++)
	{
	     s1=cad.substring(i*8,(i*8)+8);
	     array[i]= (byte)Short.parseShort(s1, 2);
	}
	return array;
}
private Vector<byte[]> instr;//Cada array puede tener 1 o mas elementos(si son parametros de mas de 1 byte. Las instrucciones solo tienen 1 byte)
private InstruccionMW mw;


String bytesToString( byte[] bytes )
{
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
}


private byte[] intToBytes(int my_int){
	

	    BigInteger bigInt = BigInteger.valueOf(my_int);      
	    return bigInt.toByteArray();
}
private int bytesToInt(byte[] int_bytes) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(int_bytes);
    ObjectInputStream ois = new ObjectInputStream(bis);
    int my_int = ois.readInt();
    ois.close();
    return my_int;
}

private long convertirBinToDec(byte[] array)//array de bytes, representa un solo numero binario en complemento 2
{
	int longitud=array.length;//numero de bytes
	if (longitud==0)
		return 0;
	byte positivo=(byte) (array[0]&0x80);
	long value = 0;
	if (positivo==0)//si es positivo
	{
		
		for (int i = 0; i < array.length; i++)
		{
			value = (value << 8) + (array[i] & 0xff);
		}		
	}
	else//es negativo, hay que pasarlo a positivo
	{
		byte[]aux=new byte[longitud +1];
		aux[0]=(byte) 0x00;
		for(int i=1;i<aux.length;i++)
			aux[i]=(byte) ~array[i-1];
		for (int i = 0; i < aux.length; i++)
		{
			value = (value << 8) + (aux[i] & 0xff);
		}
		return -(value+1);
	}
	
	return value;
	
}

public static float byteArrayToFloatBE( byte[] data ) {
    if (data == null || data.length != 4)
        return 0x0;
    // ---------- simple:
    return Float.intBitsToFloat(byteArrayToIntBE(data));
}

public static int byteArrayToIntBE( byte[] data ) {
    if (data == null || data.length != 4)
        return 0x0;
    // ----------
    return (int) ( // NOTE: type cast not necessary for int
    (0xff & data[0]) << 24 | (0xff & data[1]) << 16 | (0xff & data[2]) << 8 | (0xff & data[3]) << 0);
}

public static double byteArrayToDoubleBE( byte[] data ) {
    if (data == null || data.length != 8)
        return 0x0;
    // ---------- simple:
    return Double.longBitsToDouble(byteArrayToLongBE(data));
}

public static long byteArrayToLongBE( byte[] data ) {
    if (data == null)
        return 0x0;
    long accum = 0;
    int shiftBy = 8 * data.length - 8;
    for( byte b : data ) {
        accum |= (long) (0xff & b) << shiftBy;
        shiftBy -= 8;
    }
    return accum;
}
/**
 * Convert a float to a byte array (big endian).
 * 
 * @param data the float to convert.
 * @return the byte array.
 */
public static byte[] floatToByteArrayBE( float data ) {
    return intToByteArrayBE(Float.floatToRawIntBits(data));
}

/**
 * Convert a double to a byte array (big endian).
 * 
 * @param data the double to convert.
 * @return the byte array.
 */
public static byte[] doubleToByteArrayBE( double data ) {
    return longToByteArrayBE(Double.doubleToRawLongBits(data));
}
/**
 * Convert an integer to a byte array (big endian).
 * 
 * @param data the int to convert.
 * @return the byte array.
 */
private static byte[] intToByteArrayBE( int data ) {
    return new byte[]{(byte) ((data >> 24) & 0xff), (byte) ((data >> 16) & 0xff), (byte) ((data >> 8) & 0xff),
            (byte) ((data >> 0) & 0xff),};
}

/**
 * Convert an long to a byte array (big endian).
 * 
 * @param data the long to convert.
 * @return the byte array.
 */
private static byte[] longToByteArrayBE( long data ) {
    return new byte[]{(byte) ((data >> 56) & 0xff), (byte) ((data >> 48) & 0xff), (byte) ((data >> 40) & 0xff),
            (byte) ((data >> 32) & 0xff), (byte) ((data >> 24) & 0xff), (byte) ((data >> 16) & 0xff),
            (byte) ((data >> 8) & 0xff), (byte) ((data >> 0) & 0xff),};
}

public String muestraPila()
{
	return mw.muestraPila();
}

public String muestraMemoria()
{
	return mw.resultadoMem();
}


public static void main(String[] args) {
	Interprete inter=new Interprete();
	Vector <Object> datosParaInterprete1=new Vector <Object>();
	datosParaInterprete1.addElement("ident1");
	datosParaInterprete1.addElement("int");
	datosParaInterprete1.addElement(new Boolean(false));
	datosParaInterprete1.addElement(new Integer(0));
	datosParaInterprete1.addElement(new Integer(0));
	datosParaInterprete1.addElement("ident2");
	datosParaInterprete1.addElement("int");
	datosParaInterprete1.addElement(new Boolean(false));
	datosParaInterprete1.addElement(new Integer(1));
	datosParaInterprete1.addElement(new Integer(0));
	//inter.generar("ficheroBinario3.txt",0,datosParaInterprete1);
	System.out.println(inter.imprimirMemoria());
}

}
