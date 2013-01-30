package parser;



public class ByteCode {

	private tByteCode tipoByteC;
	private int direccion;
	
	

	ByteCode(tByteCode tipo){
		this.tipoByteC = tipo;
		this.direccion = -1;
	}
	
	ByteCode(tByteCode tipo, int dir){
		this.tipoByteC = tipo;
		this.direccion = dir;
	}
	
	public int getDireccion(){
		return direccion;
	}
	
	public tByteCode getByteCode(){
		return tipoByteC;
	}
	
	
}

