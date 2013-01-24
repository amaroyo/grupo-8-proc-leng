package aLexico;

public enum TToken {
	
	tipoVarBooleano, tipoVarCaracter, tipoVarNatural, tipoVarEntero,tipoVarReal,
	
	booleano, caracter, natural, entero, real,
	
	equals, igualIgual, great, less, greatEq, lessEq, distinto,
	
	punto, puntoyComa, dosPuntos, asigConst,
	
	sum, rest, negArit, mult, div, mod,
	
	comentario, program, varsConsts, constante, instrucciones,
	
	tokenError,
	
	PA, PC,
	
	ident,
	
	booleanoCierto, booleanoFalso,	negLogica, oLogica, yLogica,
	
	castChar, castNat, castInt, castFloat,
	
	despIzq, despDer,
	
	swap1, swap2,
	
	entradaTeclado, salidaPantalla,
	
	finDeFichero,

	LA, LC,
	
	var, decTipo

}
