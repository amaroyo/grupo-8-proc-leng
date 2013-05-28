package MV;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
			if (arrayS[0].equals("nat"))
				memoria.addElement(new Integer(Integer.parseInt(arrayS[1])));
			else
				if (arrayS[0].equals("int"))
					memoria.addElement(new Long(Integer.parseInt(arrayS[1])));
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

	
	// path: donde se genera el fichero binario de salida.
    // byteOut: 
	public boolean generaFichero(String path,Vector<ByteCode> byteOut)
	{
		boolean res=true;
		int longitud=byteOut.size();
		
		ObjectOutputStream fich=null;	
		//FileWriter fich=null;
		try {
			fich=new ObjectOutputStream(new FileOutputStream(path));
			//fich = new FileWriter(path);
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.APILA}));//instruccion
						//fich.writeObject(" ");
						fich.writeObject(new byte[]{Operaciones.APILA});//instruccion
						if ((byteOut.elementAt(i)).getTipoVar().equals("nat"))
						{
							//fich.writeObject(bytesToString(new byte[]{0x00}));//tipo nat
							//fich.writeObject(" ");
							//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
							//fich.writeObject("\n");//salto linea
							fich.writeObject(new byte[]{0x00});//tipo nat
							fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
							
							
						}
						else
							if ((byteOut.elementAt(i)).getTipoVar().equals("int"))
							{
								//fich.writeObject(bytesToString(new byte[]{0x01}));//tipo entero
								//fich.writeObject(" ");
								//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
								//fich.writeObject("\n");//salto linea
								fich.writeObject(new byte[]{0x01});//tipo entero
								fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
							}
							else
							if ((byteOut.elementAt(i)).getTipoVar().equals("real"))
							{
								
								//fich.writeObject(bytesToString(new byte[]{0x02}));// tipo double
								//fich.writeObject(" ");
								//fich.writeObject(bytesToString(doubleToByteArrayBE(Double.parseDouble((byteOut.elementAt(i)).getDireccion()))));//valor real
								//fich.writeObject("\n");//salto linea
								fich.writeObject(new byte[]{0x02});// tipo double
								fich.writeObject(doubleToByteArrayBE(Double.parseDouble((byteOut.elementAt(i)).getDireccion())));//valor real
								
							}
							else 
								if ((byteOut.elementAt(i)).getTipoVar().equals("bool"))
								{
									//fich.writeObject(bytesToString(new byte[]{0x03}));//boolean
									//fich.writeObject(" ");
									fich.writeObject(new byte[]{0x03});//boolean
									
									if ((byteOut.elementAt(i)).getDireccion().equals("false "))
										//fich.writeObject(bytesToString(new byte[]{0x00}));// valor false
										fich.writeObject(new byte[]{0x00});// valor false
									else
										//fich.writeObject(bytesToString(new byte[]{0x01}));// valor true
										fich.writeObject(new byte[]{0x01});// valor true
									//fich.writeObject("\n");//salto linea
								}
								else 
									if ((byteOut.elementAt(i)).getTipoVar().equals("char"))
									{
										//fich.writeObject(bytesToString(new byte[]{0x04}));//tipo char
										//fich.writeObject(" ");
										//fich.writeObject(bytesToString((byteOut.elementAt(i)).getDireccion().getBytes()));
										//fich.writeObject("\n");//salto linea
										fich.writeObject(new byte[]{0x04});//tipo char
										fich.writeObject((byteOut.elementAt(i)).getDireccion().getBytes());
										
										
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.APILA_DIR}));
						//fich.writeObject(" ");
						//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.APILA_DIR});
						fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.DESAPILA}));
						//fich.writeObject(" ");
						//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.DESAPILA});
						fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.LEER}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.LEER});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.ESCRIBIR}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.ESCRIBIR});
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
						//fich.writeObject(bytesToString(new byte[]{(byte)0x30}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{(byte)0x30});
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
						//fich.writeObject(bytesToString(new byte[]{(byte)0x31}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{(byte)0x31});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.DISTINTO}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.DISTINTO});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MAYOR_IGUAL}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MAYOR_IGUAL});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MENOR}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MENOR});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MAYOR}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MAYOR});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MAYOR_IGUAL}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MAYOR_IGUAL});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MENOR_IGUAL}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MENOR_IGUAL});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.RESTA}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.RESTA});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.SUMA}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.SUMA});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.OR}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.OR});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.DIVISION}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.DIVISION});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.PRODUCTO}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.PRODUCTO});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.AND}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.AND});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.MODULO}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.MODULO});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.DESPLAZAMIENTO_DERECHA}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.DESPLAZAMIENTO_DERECHA});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.DESPLAZAMIENTO_IZQUIERDA}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.DESPLAZAMIENTO_IZQUIERDA});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.NOT}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.NOT});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.NEG}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.NEG});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.STOP}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.STOP});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.CONVERSION_INT}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.CONVERSION_INT});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.CONVERSION_FLOAT}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.CONVERSION_FLOAT});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.CONVERSION_NAT}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.CONVERSION_NAT});
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
						//fich.writeObject(bytesToString(new byte[]{Operaciones.CONVERSION_CHAR}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.CONVERSION_CHAR});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case ir_f:
				{
					try 
					{
						//fich.writeObject(bytesToString(new byte[]{Operaciones.IR_F}));
						//fich.writeObject("\n");//salto linea
						//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.IR_F});
						fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case ir_v:
				{
					try 
					{
						//fich.writeObject(bytesToString(new byte[]{Operaciones.IR_V}));
						//fich.writeObject("\n");//salto linea
						//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.IR_V});
						fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case ir_a:
				{
					try 
					{
						//fich.writeObject(bytesToString(new byte[]{Operaciones.IR_A}));
						//fich.writeObject("\n");//salto linea
						//fich.writeObject(bytesToString(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion()))));//valor nat o entero
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.IR_A});
						fich.writeObject(intToBytes(Integer.parseInt((byteOut.elementAt(i)).getDireccion())));//valor nat o entero
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res=false;
					}//instruccion				
					break;
					
				}
				case copia:
				{
					try 
					{
						//fich.writeObject(bytesToString(new byte[]{Operaciones.COPIA}));
						//fich.writeObject("\n");//salto linea
						fich.writeObject(new byte[]{Operaciones.COPIA});
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
				fich.writeObject(null);
				fich.close();
			} catch (IOException e) {
				res=false;
				e.printStackTrace();
			}
		return res;
	}

}
