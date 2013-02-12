package parser;

public class ByteCode {

	private tByteCode tipoByteC;
	private String direccion;

	ByteCode(tByteCode tipo) {
		this.tipoByteC = tipo;
		this.direccion = "";
	}

	ByteCode(tByteCode tipo, String dir) {
		this.tipoByteC = tipo;
		this.direccion = dir;
	}

	public String getDireccion() {
		return direccion;
	}

	public tByteCode getByteCode() {
		return tipoByteC;
	}

	@Override
	public String toString() {
		return "ByteCode [tipoByteC=" + tipoByteC + ", direccion=" + direccion
				+ "]";
	}

}
