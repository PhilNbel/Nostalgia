import java.awt.*;
import java.awt.event.*;
                     //  FRAME :le conteneur
import javax.swing.*; //  PANNEL :ce qui remplit le conteneur
import java.util.*;

public class ChooseLvl extends JFrame implements ActionListener{
	private String banderole;
   protected Single niveau;
   private JPanel panel;
   public Integer tp;
   private ArrayList<JButton> arr = new ArrayList<JButton>();
   private ArrayList<JButton> arrRobot = new ArrayList<JButton>();

    
   
   public ChooseLvl(Single niveau){
      this.niveau=niveau;
      
      setSize(600, 800);
      setTitle("Choix du niveau");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);

      Container contentPane=getContentPane();
      contentPane.setLayout(null);	
      contentPane.setPreferredSize(new Dimension(80, 800));
      contentPane.setLayout(new GridLayout(4,4));
      String s = "";
      for(int i = 0; i<10; i++){
         s = "Lvl "+(i+1);
         JButton b = new JButton(s);
         b.addActionListener(this);
         contentPane.add(b);
         this.arr.add(b);
      }
      
      setVisible(true);
   }
   
   public int lvl(){ // n'est utilise qu'une fois
   
      return niveau.n;
   }

	public void actionPerformed(ActionEvent e){
    	int choix = 0;
      
    	for(int i = 0;i< this.arr.size();i++){
         
        	if(e.getSource() == this.arr.get(i)){
            
               choix = i+1;
     
            }
      }
      this.niveau.setN(choix);
 
      javax.swing.SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            
                  Jeu j=new Jeu(niveau);
                                             
         }
      });
      dispose();
   }
   public static void main(String[] args){	
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
	         Single s=new Single(0);
			   ChooseLvl tp=new ChooseLvl(s);

			}
		});	

	}

}