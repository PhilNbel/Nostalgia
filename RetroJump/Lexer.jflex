%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%debug

Deci = [0-9]+
%{
  private Token token(Sym type) {
      return new Token(type);
  }

%}

%%
"Pers"   {return token(Sym.GR);}
"ground"   {return token(Sym.GR);}
"fground"   {return token(Sym.FGR);}
"adHB"   {return token(Sym.HB);}
"adVB"   {return token(Sym.VBO);}
"adVB2"   {return token(Sym.VBD);}
"adVG"   {return token(Sym.VG);}
"addPic"   {return token(Sym.PIC);}
"MurPiquant"   {return token(Sym.MPC);}
"Piege"   {return token(Sym.PIG);}
"Diagonal"   {return token(Sym.DIA);}
"Wif"   {return token(Sym.WIF);}
"Portal"   {return token(Sym.PRT);}
"Feche"   {return token(Sym.FLE);}
"Laser"   {return token(Sym.LAS);}
