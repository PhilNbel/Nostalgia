class BoolToken extends Token{
	private boolean b;
	BoolToken(Sym sy, String s){
		super(sy);
		b=false;
		if(s.equals("true"))
			b=true;
	}

	public boolean getB(){
			return b;
	}
}