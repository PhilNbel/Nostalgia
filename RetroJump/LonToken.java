class LonToken extends Token{
	private long l;//pour savoir s'il y a un point (donc si c'est un double)
	LonToken(Sym sy, String s){
		super(sy);
		String bis="";
		for(int i=0;i<s.length()-1;i++)
			bis+=s.charAt(i);
		l=Long.parseLong(bis);
	}

	public long getL(){
			return l;
	}
}