package MV;

public class Operaciones {
	public static final byte MENOR=0x00;
	public static final byte MAYOR=0x01;
	public static final byte MENOR_IGUAL=0x02; 
	public static final byte MAYOR_IGUAL=0x03;
	public static final byte DISTINTO=0x04;
	public static final byte SUMA=0x05;
	public static final byte RESTA=0x06;
	public static final byte PRODUCTO=0x07;
	public static final byte DIVISION=0x08;
	public static final byte MODULO=0x09; 
	public static final byte AND=0x1A; 
	public static final byte OR=0x1B; 
	public static final byte NOT=0x1C;
	public static final byte NEG=0x1D; 
	public static final byte DESPLAZAMIENTO_IZQUIERDA=0x1E; 
	public static final byte DESPLAZAMIENTO_DERECHA=0x1F;
	public static final byte CONVERSION_NAT=0x20; 
	public static final byte CONVERSION_INT=0x21;
	public static final byte CONVERSION_CHAR=0x22;
	public static final byte CONVERSION_FLOAT=0x23;
	public static final byte VALOR_ABSOLUTO=0x24;
	public static final byte LEER=0x25;
	public static final byte ESCRIBIR=0x26;
	public static final byte APILA=0x27;
	public static final byte APILA_DIR=0x28;
	public static final byte DESAPILA=0x29;
	public static final byte DESAPILA_DIR_BOOLEAN=0x2A;
	public static final byte DESAPILA_DIR_INTEGER=0x2B;
	public static final byte DESAPILA_DIR_NATURAL=0x2C;
	public static final byte DESAPILA_DIR_FLOAT=0x2D;
	public static final byte DESAPILA_DIR_CHAR=0x2E;
	public static final byte STOP=0x2F;
}
