package aLexico;

public class Token {
	
	private String lexema;
	private TToken tipoToken;
	private int linea;
	
	public Token() {
		setLexema(null);
		setTipoToken(TToken.tokenError);
	}
	
	public Token(TToken _tipoToken) {
		setLexema(null);
		setTipoToken(_tipoToken);
	}
	
	public Token(TToken _tipoToken, String _lexema) {
		setLexema(new String(_lexema));
		setTipoToken(_tipoToken);
	}
	
	public Token(TToken _tipoToken, int _linea) {
		setLexema(null);
		setTipoToken(_tipoToken);
		setLinea(_linea);
	}
	
	public Token(TToken _tipoToken, String _lexema, int _linea) {
		setLexema(new String(_lexema));
		setTipoToken(_tipoToken);
		setLinea(_linea);
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getLexema() {
		return lexema;
	}

	public void setTipoToken(TToken tipoToken) {
		this.tipoToken = tipoToken;
	}

	public TToken getTipoToken() {
		return tipoToken;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

}
