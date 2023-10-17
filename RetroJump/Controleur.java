import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
public class Controleur extends KeyAdapter{
	public Mod model;
    boolean l,r;  
	public Controleur(Mod play){
		this.model = play;
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		for(int i = 0; i<model.objs.size(); i++){
			if(model.objs.get(i) instanceof IntAc){
				IntAc tmp = (IntAc) model.objs.get(i);
				if(tmp instanceof Pers){
					Pers p= (Pers) tmp;
					if(k == KeyEvent.VK_R){
						p.setX(5.0);
						p.setY(5.0);
						p.setVelX(0.0);
						p.setVelY(0.0);
						
					}

					// if(k == KeyEvent.VK_F){
					// 	p.attaque(model.objs);
					// }

					if(k == KeyEvent.VK_P){
						System.out.println("X: "+p.getX()+", Y: "+p.getY());
					}

					if(k == KeyEvent.VK_LEFT){
						p.setDir(false);
						l=true;
						p.setMove(1);
					}else{
						if(k == KeyEvent.VK_RIGHT){
							p.setDir(true);
							r=true;
							p.setMove(2);
						}
						if((k == KeyEvent.VK_UP && p.saute == false)){
						
							p.saute = true;
							p.setVelY(-8.0);
						
						}else{
							if((k == KeyEvent.VK_UP && p.dbsaut == false)){
								p.dbsaut = true;
								p.setVelY(-8.0);
							}
							
						}
					}
					
				}

			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int k = e.getKeyCode();
		for(int i = 0; i<model.objs.size(); i++){
			if(model.objs.get(i) instanceof IntAc){
				IntAc cur = (IntAc) model.objs.get(i);

				if(cur instanceof Pers){

					Pers tmp=(Pers)cur;
					if(k == KeyEvent.VK_F){
						tmp.attaque(model.objs);
					}
					
					if(k == KeyEvent.VK_LEFT){
						l=false;
						if(!r)
							tmp.setMove(0);
						else
							tmp.setMove(2);
					}else{
						if(k == KeyEvent.VK_RIGHT){
							r=false;
							if(!l)
								tmp.setMove(0);
							else
								tmp.setMove(1);
						}
						
					}
					
				}
			}
		}
	}
	
	public void keyTyped(KeyEvent e){
		int k = e.getKeyCode();
		for(int i = 0; i<model.objs.size(); i++){
			if(model.objs.get(i) instanceof IntAc){
				IntAc tmp = (IntAc) model.objs.get(i);
				if(tmp instanceof Pers){
					if(k == KeyEvent.VK_UP){
						System.out.println("saute");
						tmp.saute = true;
						tmp.setVelY(-10.0);
						
					}
				}
			}
		}
	}
	
	  


	  


	

}