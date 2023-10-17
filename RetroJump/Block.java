import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends Objet{

	double ind;
	byte type;

	public Block(double a, double b, byte t){
		super(a, b, 32, 32);
		ind=0.3;
		this.type = t;
	}
	public Block(double a, double b, byte t, double d){
		super(a, b, 32, 32);
		this.type = t;
		ind=d;
	}

	public void tick(LinkedList<Objet> obj){}
	//colli retourne true si le perso est pose false sinon
	public boolean colli(LinkedList<Objet> o, IntAc mv){

		Objet tmp;
		// Pers p = (Pers)o.get(0);
		
		if(!(mv instanceof FireBall)){
			if(this.type == 1){
				if(mv.getBounds().intersects(getBounds())  ){
					// System.out.println("DANS COLLI");
					// System.out.println("BLOCK1 collision");
					if(mv instanceof Pers || mv instanceof Ennemy){
							mv.setY(y-mv.hgt);
							mv.setVelY(0);
					// mv.setVelY(Phys.boing(mv.getVelY(),0.1));
					
							mv.setVelX(Phys.frott(mv.getVelX(),ind));
					}else{
						mv.remove();
					}
					return true;		
//			rh = true;

				}
				if(mv.getBoundsLeft().intersects(getBounds()) && !mv.getBounds().intersects(getBounds()) ){
					
					// mv.setX(mv.getX()+Math.abs(mv.getVelX())+1);
					if(mv instanceof Pers){
						// mv.setVelX(-Phys.frott(mv.getVelX(),0.6));
						mv.x = (x+wdt)+1;
						// mv.setX(mv.getX()-mv.getVelX());
					// mv.setX(x+this.wdt);
						mv.setVelX(0);
					}else{
						mv.remove();
					}
					
				}
				if(mv.getBoundsRight().intersects(getBounds()) && !mv.getBounds().intersects(getBounds()) ){
					// mv.setX(mv.getX()-Math.abs(mv.getVelX()));
					if(mv instanceof Pers){
						 // mv.setVelX(-Phys.frott(mv.getVelX(),0.6));
						 mv.x = (x-mv.wdt)-1;
					// mv.setX(mv.getX()-mv.getVelX());
					// mv.setX(x-this.wdt);
						 mv.setVelX(0);
					}else{
						mv.remove();
					}
				
				}
					
				if(mv.getBoundsTop().intersects(getBounds())){
					
					mv.setY(y+32);
					mv.setVelY(-Phys.boing(-mv.getVelY(),0.2)+2);
				
				}
				
				
				
			
				return false;
			}else if(this.type == 2){
				if(mv.getBounds().intersects(getBounds())){
					mv.y =this.y - mv.hgt;

					return true;

				}



				if(mv.getBoundsRight().intersects(getBounds()) && !mv.getBoundsLeft().intersects(getBounds()) ){
					mv.x= this.x-mv.wdt;

				}
			}else{
				if(mv.getBounds().intersects(getBounds())){
					mv.y= this.y-mv.hgt;
					return true;

				}
				if(mv.getBoundsRight().intersects(getBounds())){
					mv.x = this.x;

				}
			}
			return false;
		}else{ //C'est une boule de feu
			FireBall fire = (FireBall) mv;
			if(fire.getBoundsEllipse().intersects(x, y, wdt, hgt)){
		
				fire.remove();
			}
			return false;
		}
	}
	public void render(Graphics g){
		g.drawImage(Texture.getImg(0),(int)x,(int)y,null);
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)this.wdt, (int)this.hgt);
	}

}
