package parser;

public class ByteCode {

	private tByteCode tipoByteC;
	private String direccion;
	private String tipoVar;

	ByteCode(tByteCode tipo) {
		this.tipoByteC = tipo;
		this.direccion = "";
	}

	ByteCode(tByteCode tipo, String dir) {
		this.tipoByteC = tipo;
		this.direccion = dir;
	}
	
	ByteCode(tByteCode tipo, String dir,String tipovar) {
		this.tipoByteC = tipo;
		this.direccion = dir;
		this.tipoVar = tipovar;
	}

	public tByteCode getTipoByteC() {
		return tipoByteC;
	}

	public void setTipoByteC(tByteCode tipoByteC) {
		this.tipoByteC = tipoByteC;
	}

	public String getTipoVar() {
		return tipoVar;
	}

	public void setTipoVar(String tipoVar) {
		this.tipoVar = tipoVar;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
				+ ", tipoVar=" + tipoVar + "]";
	}

}
