package MV;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Vector;

import parser.ByteCode;

public class GeneradorFichero {

String bytesToString( byte[] bytes )
{
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
}
byte[] intToBytes(int my_int){
	

    BigInteger bigInt = BigInteger.valueOf(my_int);      
    return bigInt.toByteArray();
}
public static byte[] doubleToByteArrayBE( double data ) {
    return longToByteArrayBE(Double.doubleToRawLongBits(data));
}

public static byte[] longToByteArrayBE( long data ) {
    return new byte[]{(byte) ((data >> 56) & 0xff), (byte) ((data >> 48) & 0xff), (byte) ((data >> 40) & 0xff),
            (byte) ((data >> 32) & 0xff), (byte) ((data >> 24) & 0xff), (byte) ((data >> 16) & 0xff),
            (byte) ((data >> 8) & 0xff), (byte) ((data >> 0) & 0xff),};
}

public Vector<Object> generarMemoria(HashMap<Integer, String> dirMemoria )
{
	Vector<Object> memoria=new Vector<Object>();
	int longitud=dirMemoria.size();
	for (int i=0;i<longitud;i++)
	{
		String valor=dirMemoria.get(i);
		if (valor!=null)
		{
			String[] arrayS=valor.split(":");
			if (arrayS[0].equals("int")||arrayS[0].equals("nat"))
				memoria.addElement(new Integer(Integer.parseInt(arrayS[1])));
			else
				if (arrayS[0].equals("real"))
					memoria.addElement(new Double(Double.parseDouble(arrayS[1])));
				else
					if (arrayS[0].equals("bool"))
						memoria.addElement(new Boolean(Boolean.parseBoolean(arrayS[1])));
					else
						if (arrayS[0].equals("char"))
							memoria.addElement(arrayS[1]);//char
						else
							memoria.addElement(new Integer(0));//inicializar con 0
		}
		else
		{
			memoria.addElement(new Integer(0));
		}
		
	}
	return memoria;
}

	
	// 
	public boolean generaFichero(String path,Vector<ByteCode> byteOut)
	{
		boolean res=true;
		int longitud=byteOut.size();
		FileWriter fich=null;
		try {
			fich = new FileWriter(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			res=false;
		}
		for (int i=0;i< longitud;i++)
		{
			switch ((byteOut.elementAt(i)).getTipoByteC()) 
			{
				case apila:
				{   
					try {
						fich.write(bytesToString(new byte[]{Operaciones.APILA}));//instruccion
						fich.write(" ");
						if ((byteOut.elementAt(i)).getTipoVar().equals("nat")||(byteOut.elementAt(i)).getTipoVar().equals("int"))
						{
							fich.write(bytesToString(new byte[]{0x00}));//tipo nat o entero
							fich.write(" ");
							fich.write(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
							fich.write("\n");//salto linea
						}
						else
							if ((byteOut.elementAt(i)).getTipoVar().equals("real"))
							{
								fich.write(bytesToString(new byte[]{0x02}));// tipo double
								fich.write(" ");
								fich.write(bytesToString(doubleToByteArrayBE(Double.parseDouble((byteOut.elementAt(i)).getDireccion()))));//valor real
								fich.write("\n");//salto linea
							}
							else 
								if ((byteOut.elementAt(i)).getTipoVar().equals("bool"))
								{
									fich.write(bytesToString(new byte[]{0x03}));//boolean
									fich.write(" ");
									if ((byteOut.elementAt(i)).getDireccion().equals("false "))
										fich.write(bytesToString(new byte[]{0x00}));// valor false
									else
										fich.write(bytesToString(new byte[]{0x01}));// valor true
									fich.write("\n");//salto linea
								}
								else 
									if ((byteOut.elementAt(i)).getTipoVar().equals("char"))
									{
										fich.write(bytesToString(new byte[]{0x04}));//tipo char
										fich.write(" ");
										fich.write(bytesToString((byteOut.elementAt(i)).getDireccion().getBytes()));
										fich.write("\n");//salto linea
										
									}			
					} catch (IOException e) {
						e.printStackTrace();
						res=false;
					}
					
					break;
				}
				case apila_dir:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.APILA_DIR}));
						fich.write(" ");
						fich.write(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion
					
					break;
				}
				case desapila_dir:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.DESAPILA}));
						fich.write(" ");
						fich.write(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion
					
					break;
				}
				case read:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.LEER}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion
					
					break;
					
				}
				case write:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.ESCRIBIR}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;				
				}
				case swap1://Falta incluir
				{
					try 
					{
						fich.write(bytesToString(new byte[]{(byte)0x30}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;				
				}
				case swap2://Falta incluir
				{
					try 
					{
						fich.write(bytesToString(new byte[]{(byte)0x31}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;				
				}
				case distinto:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.DISTINTO}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case igual:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MAYOR_IGUAL}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case menor:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MENOR}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case mayor:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MAYOR}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case mayorigual:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MAYOR_IGUAL}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case menorigual:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MENOR_IGUAL}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case resta:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.RESTA}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case suma:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.SUMA}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case or:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.OR}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case divide:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.DIVISION}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case multiplica:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.PRODUCTO}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case and:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.AND}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case modulo:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.MODULO}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case desplazamientoderecha:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.DESPLAZAMIENTO_DERECHA}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case desplazamientoizquierda:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.DESPLAZAMIENTO_IZQUIERDA}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case negacionlogica:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.NOT}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case restaunitaria:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.NEG}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
				}
				case stop:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.STOP}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case cint:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.CONVERSION_INT}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case cfloat:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.CONVERSION_FLOAT}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case cnat:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.CONVERSION_NAT}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case cchar:
				{
					try 
					{
						fich.write(bytesToString(new byte[]{Operaciones.CONVERSION_CHAR}));
						fich.write("\n");//salto linea
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
			default:
				break;
			}
			
		}
		if (null != fich)
			try {
				fich.close();
			} catch (IOException e) {
				res=false;
				e.printStackTrace();
			}
		return res;
	}

}
