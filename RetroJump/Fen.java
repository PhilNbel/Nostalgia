import javax.swing.*;
 
class Fen{
	
	JFrame fen;
	public Fen(String nom, int lo, int la, Jeu j){

    fen = new JFrame();

    fen.add(j);
    fen.setTitle(nom);
    fen.setSize(lo, la);
    fen.setLocationRelativeTo(null);
    fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fen.setVisible(true);


    j.start();
  }
  // public Fen(String nom, int lo, int la){

    // fen = new JFrame();

    // fen.add(j);
    // fen.setTitle(nom);
    // fen.setSize(lo, la);
    // fen.setLocationRelativeTo(null);
    // fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // fen.setVisible(true);


    // j.start();
  // }

  public Fen(int lo, int la, Jeu j){ this("",lo,la,j); }
  
  
  public int getWidthFen(){
		return fen.getWidth();
  }
  public int getHeightFen(){
		return fen.getHeight();
  }
 
}