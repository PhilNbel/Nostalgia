class NumToken extends Token{
	private int i;
	private double d;
	public boolean p;//pour savoir s'il y a un point (donc si c'est un double)
	NumToken(Sym sy, String s){
		super(sy);
		for(int n = 0; n < s.length(); n++ )
			if(s.charAt(n)=='.')
				p=true;
		if(p)
			d=Double.parseDouble(s);
		else
			i=Integer.parseInt(s);
	}

	public double getD(){
			return d;
	}
	public int getI(){
			return i;
	}
}