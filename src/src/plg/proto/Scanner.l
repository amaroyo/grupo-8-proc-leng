package plg.proto;

import java_cup.runtime.*;

%%

%class Scanner

%unicode
%cup 
%line 
%column

and     = "and"
or      = "or"
if      = "if"
then    = "then"
else    = "else"
fi      = "fi"
true    = "true"
false   = "false"
write   = "write"
String  = \"[^\"]*\"

%%

[\t\r\n ] {}
#[^\n]*   {}
{String}  {return new Symbol(sym.STRING, yytext());}
{and}     {return new Symbol(sym.AND);}
{or}      {return new Symbol(sym.OR);}
{if}      {return new Symbol(sym.IF);}
{then}    {return new Symbol(sym.THEN);}
{else}    {return new Symbol(sym.ELSE);}
{fi}      {return new Symbol(sym.FI);}
{write}   {return new Symbol(sym.WRITE);}
{true}    {return new Symbol(sym.TRUE);}
{false}   {return new Symbol(sym.FALSE);}
\(        {return new Symbol(sym.PAP);}
\)        {return new Symbol(sym.PCIERRE);}
[^]       {throw new Error("Caracter no reconocido:"+yytext());}


