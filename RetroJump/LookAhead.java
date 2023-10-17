import java.io.*;

class LookAhead  { 
    /* Simulating a reader class for a stream of Token */
    
    private Token current;
    private Lexer lexer;

    public LookAhead(Lexer l) throws IOException {
		lexer=l;
		current=lexer.yylex();
    }

    public boolean check(Sym s) throws Exception {
	/* check whether the first character is of type s*/
        if(isNull()) return false;
        return (current.symbol() == s); 
    }

    public void eat(Sym s) throws Exception {
	/* consumes a token of type s from the stream,
	   exception when the contents does not start on s.   */
		if (!check(s)) {
		    throw new Exception("\nCan't eat "+s+" current being "+current);
		}
        current=lexer.yylex();

    }

    public Sym see(){
        if(isNull()) return Sym.ERROR;
        return current.symbol();
    }

    public Token curr(){
        return current;
    }

    public boolean isNull(){
        return (current==null);
    }
}
