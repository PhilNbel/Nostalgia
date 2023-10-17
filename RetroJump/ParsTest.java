import java.io.*;

class ParsTest{
	public static void main(String[]args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
			Lexer lexer= new Lexer(reader);
			Token t ;
			do{
				t=lexer.yylex();
				if(t!=null){
					System.out.println(t);
					}
			}while(t!=null);
		}catch(IOException e ){
			System.err.println("IOException:"+e.getMessage());
		}
	}
}
