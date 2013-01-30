package parser;



public class TablaInfo {
	
	private String tipo;
	private boolean constante;
	private int direccion;
				
	
	


	TablaInfo(String t, boolean cte, int dir){
		this.tipo = t;
		this.constante = cte;
		this.direccion = dir;
	}
	
	
	public String print(){
		String c;
		if(this.constante) c = " es ";
		else c = " no es ";
				
		String x = "de tipo: "+ tipo +  c + "constante y tiene esta direccion de memoria: " + direccion;
		return x;
	}
}
