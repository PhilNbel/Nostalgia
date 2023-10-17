class StrToken extends Token{
	private String s;
	StrToken(Sym sy, String st){
		super(sy);
		s=st;
	}

	public String getS(){ return s; }
}