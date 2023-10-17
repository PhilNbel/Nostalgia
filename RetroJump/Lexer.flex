%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%debug
	
Num = [0-9]+("."[0-9]+)?
Long = [0-9]+"L"
Text = .+
Bool = "true"|"false"
Space = [ \n\f\r\t]

%{
  private Token token(Sym type) {
      return new Token(type);
  }

%}
%state PAR
%state TEXT
%%
<YYINITIAL>{
{Space}	{}
"Block"   {return token(Sym.BC);}
"Bouncer"   {return token(Sym.BNC);}
"Diagonal"   {return token(Sym.DIA);}
"Ennemy"   {return token(Sym.ENN);}
"FrznGrd"   {return token(Sym.FGD);}
"fground"   {return token(Sym.FGR);}
"FireBall"   {return token(Sym.FIB);}
"Arme"		{return token(Sym.ARM);}
"Fleche"   {return token(Sym.FLE);}
"Ground"   {return token(Sym.GD);}
"ground"   {return token(Sym.GR);}
"adHB"   {return token(Sym.HB);}
"IBlock"   {return token(Sym.IBC);}
"Laser"   {return token(Sym.LAS);}
"MurPiquant"   {return token(Sym.MPC);}
"Pers"   {return token(Sym.PER);}
"addPic"   {return token(Sym.PIC);}
"Piege"   {return token(Sym.PIG);}
"Portal"   {return token(Sym.PRT);}
"adVB2"   {return token(Sym.VBD);}
"adVB"   {return token(Sym.VBO);}
"adVG"   {return token(Sym.VG);}
"Wif"   {return token(Sym.WIF);}
"("		  {yybegin(PAR);}
}

<PAR>{
{Space}	{}
{Bool}	{return new BoolToken(Sym.BOO,yytext());}
{Num}	{return new NumToken(Sym.NUM,yytext());}
{Long}	{return new LonToken(Sym.LON,yytext());}
"Rand"	{return token(Sym.RAN);}

"Diagonal"   {return token(Sym.DIA);}
"-"		{return token(Sym.MOS);}
"+"		{return token(Sym.PLU);}
"*"		{return token(Sym.TIM);}
","		{return token(Sym.VIR);}
"\""	{yybegin(TEXT);}
")"		{yybegin(YYINITIAL);}
}

<TEXT>{
{Text} 	{return new StrToken(Sym.STR,yytext());}
"\""	{yybegin(PAR);}
}