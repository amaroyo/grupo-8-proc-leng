package plg.proto;

import java_cup.runtime.*;

%%

%class Scanner

%unicode
%cup 
%line 
%column


//operadores
mas				=	"+"
menos			=	"-"
division		=	"/"
multiplicacion	=	"*"
modulo			=	"%"
mayor			=	">"
menor			=	"<"
mayorigual		=	">="
menorigual		=	"<="
igualigual		=	"=="
distinto		=	"!="

or      		= 	"or"
and     		= 	"and"
not				=	"not"
despIzq			=	"<<"
despDer			=	">>"


//simbolos
cero			=	"0"

pabierto		=	"("
pcerrado			=	")"
dospuntos		=	":"
llabierta			=	"{"
llcerrada		=	"}"
puntoycoma		=	";"
igual			=	"="
arroba			=	"@"
cabierto		=	"["
ccerrado		=	"]"
coma			= 	","
guionbajo		=	"_"


//palabras reservadas
program 		= 	"program"
consts 			= 	"consts"
const 			= 	"const"
tipos 			= 	"tipos"
tipo 			= 	"tipo" 
vars 			= 	"vars"
var 			= 	"var"
subprograms 	= 	"subprograms"
subprogram 		= 	"subprogram"
instructions 	= 	"instructions"

in 				= 	"in"
out 			= 	"out"
swap1 			= 	"swap1"
swap2 			= 	"swap2" 
call			= 	"call"
if      		= 	"if"
then    		= 	"then"
else    		= 	"else"
endif   		= 	"endif"
while 			= 	"while"
do 				= 	"do"
endwhile 		= 	"endwhile"

float			= 	"float"
integer 		= 	"integer"
natural 		= 	"natural"
caracter 		= 	"caracter"
boolean 		= 	"boolean"
true 			= 	"true"
false 			= 	"false"

int 			= 	"int"
nat 			= 	"nat"
char			= 	"char"


//conjuntos
letraMinus 		=	[a-z]
letraMayus		=	[A-Z]
letra			=	{letraMinus} | {letraMayus}
digitoPos		=	[1-9]
digito			=	[0-9]
parteDecimal 	= 	"." ("0" | {digito}* {digitoPos})

caracter 		= 	\' [^\'] \'
numeroEnt		=	{cero} | {digitoPos}{digito}*
exponencial 	= 	("e" | "E" ) ("-")? ({numeroEnt})
numeroReal 			= 	{numeroEnt} ( {parteDecimal} | {exponencial} | {parteDecimal} {exponencial} )
//real 			= 	{natural} ( {parteDecimal} )+
identificador	=	{letraMinus} ( {letra} | {digito} )*

String  = \"[^\"]*\"

%%

//special symbols
[\t\r\n ] {}




 
					

//operadores

{mas}			{return new Symbol(sym.SUMA, yyline, yycolumn);}
{menos}			{return new Symbol(sym.RESTA, yyline, yycolumn);}
{division}		{return new Symbol(sym.DIV, yyline, yycolumn);}
{multiplicacion} {return new Symbol(sym.MULT, yyline, yycolumn);}
{modulo}		{return new Symbol(sym.MOD, yyline, yycolumn);}
{mayor}			{return new Symbol(sym.MAYOR, yyline, yycolumn);}
{menor}			{return new Symbol(sym.MENOR, yyline, yycolumn);}
{mayorigual}	{return new Symbol(sym.MAYORIGUAL, yyline, yycolumn);}
{menorigual}	{return new Symbol(sym.MENORIGUAL, yyline, yycolumn);}
{igualigual}	{return new Symbol(sym.IGUALIGUAL, yyline, yycolumn);}
{distinto}		{return new Symbol(sym.DISTINTO, yyline, yycolumn);}
{not}			{return new Symbol(sym.NOT, yyline, yycolumn);}
{and}     		{return new Symbol(sym.and, yyline, yycolumn);}
{or}      		{return new Symbol(sym.or, yyline, yycolumn);}
{despIzq}		{return new Symbol(sym.DESPIZQ, yyline, yycolumn);}
{despDer}		{return new Symbol(sym.DESPDER, yyline, yycolumn);}


//simbolos

{pabierto}        	{return new Symbol(sym.PABIERTO, yyline, yycolumn);}
{pcerrado}       {return new Symbol(sym.PCERRADO, yyline, yycolumn);}
{llabierta}		{return new Symbol(sym.LLABIERTA, yyline, yycolumn);}
{llcerrada}	{return new Symbol(sym.LLCERRADA, yyline, yycolumn);}
{cabierto}	{return new Symbol(sym.CABIERTO, yyline, yycolumn);}
{ccerrado} {return new Symbol(sym.CCERRADO, yyline, yycolumn);}
{dospuntos}		{return new Symbol(sym.DP, yyline, yycolumn);}
{puntoycoma}		{return new Symbol(sym.PYC, yyline, yycolumn);}
{igual}			{return new Symbol(sym.IGUAL, yyline, yycolumn);}
//{dosPuntosIgual} {return new Symbol(sym.DPIGUAL, yyline, yycolumn);}
{coma}			{return new Symbol(sym.COMA, yyline, yycolumn);}
{arroba}		{return new Symbol(sym.ARROBA, yyline, yycolumn);}
{guionbajo}		{return new Symbol(sym.GUIONBAJO, yyline, yycolumn);}

//palabras reservadas
{program}		{return new Symbol(sym.program, yyline, yycolumn);}
{consts}		{return new Symbol(sym.consts, yyline, yycolumn);}
{const}			{return new Symbol(sym.const, yyline, yycolumn);}
{tipos}			{return new Symbol(sym.tipos, yyline, yycolumn);}
{tipo}			{return new Symbol(sym.tipo, yyline, yycolumn);}
{vars}			{return new Symbol(sym.vars, yyline, yycolumn);}
{var}			{return new Symbol(sym.var, yyline, yycolumn);}
{subprograms}	{return new Symbol(sym.subprograms, yyline, yycolumn);}
{subprogram}	{return new Symbol(sym.subprogram, yyline, yycolumn);}
{instructions}	{return new Symbol(sym.instructions, yyline, yycolumn);}

{in}			{return new Symbol(sym.in, yyline, yycolumn);}
{out}			{return new Symbol(sym.out, yyline, yycolumn);}
{swap1}			{return new Symbol(sym.SWAP1, yyline, yycolumn);}
{swap2}			{return new Symbol(sym.SWAP2, yyline, yycolumn);}
{call}			{return new Symbol(sym.Call, yyline, yycolumn);}
{if}      		{return new Symbol(sym.if, yyline, yycolumn);}
{then}    		{return new Symbol(sym.then, yyline, yycolumn);}
{else}    		{return new Symbol(sym.else, yyline, yycolumn);}
{endif}    		{return new Symbol(sym.endif, yyline, yycolumn);}
{while}			{return new Symbol(sym.while, yyline, yycolumn);}
{do}			{return new Symbol(sym.do, yyline, yycolumn);}
{endwhile}		{return new Symbol(sym.endwhile, yyline, yycolumn);}

{float}			{return new Symbol(sym.float, yyline, yycolumn);}
{integer}		{return new Symbol(sym.integer, yyline, yycolumn);}
{natural}		{return new Symbol(sym.natural, yyline, yycolumn);}
{caracter}		{return new Symbol(sym.caracter, yyline, yycolumn);}
{boolean}		{return new Symbol(sym.boolean, yyline, yycolumn);}
{true}			{return new Symbol(sym.TRUE, yyline, yycolumn);}
{false}			{return new Symbol(sym.FALSE, yyline, yycolumn);}

{int}			{return new Symbol(sym.INT, yyline, yycolumn);}
{nat}			{return new Symbol(sym.NAT, yyline, yycolumn);}
{char}			{return new Symbol(sym.CHAR, yyline, yycolumn);}



//conjuntos
{numeroEnt}  	{return new Symbol(sym.CARACTER, yyline, yycolumn, yytext());}
//{cNatural}  	{return new Symbol(sym.CNATURAL, yyline, yycolumn, new Integer(yytext()));}
{numeroReal}  	{return new Symbol(sym.REAL, yyline, yycolumn, new Float(yytext()));}
{identificador}         {return new Symbol(sym.IDENT, yyline, yycolumn, yytext());}



//errores
[^]   	    	{throw new Error("Caracter no reconocido: " + yytext() + " linea: " + yyline
								+ " columna: " + yycolumn);}
