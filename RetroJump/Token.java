class Token {
    protected Sym symbol;
    public Token(Sym s) {
	symbol=s;
    }
    public Sym symbol() {
	return symbol;
    }

}
