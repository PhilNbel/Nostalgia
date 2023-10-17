import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Fleche extends IntAc{
	
	public Fleche(double x, double y, double velX, double velY, double w, double h){
		super(x, y, w, h);
		this.velX = velX;
		this.velY = velY;
		

	}
	public void tick(LinkedList<Objet> obj){
		this.x += this.velX;
		this.y += this.velY;

	}

	// public void run_collision(LinkedList<Objet> obj){

	// }
	
	public boolean collision(LinkedList<Objet> obj){
		


			Pers p = (Pers)obj.get(0);
			if(p.getBoundsLeft().intersects(getBounds())){
		
				return true;
			}
			if(p.getBounds().intersects(getBounds())){
			
				return true;
			}
			if(p.getBoundsRight().intersects(getBounds())){
				
				return true;
			}
			if(p.getBoundsTop().intersects(getBounds())){
				return true;
			}
			Objet a;
			for(int i = 1; i<obj.size() ; i++){
				a = obj.get(i);
				if(this.remove){
					break;
				}
				if(a instanceof IntAc){
					IntAc tmp = (IntAc) a;

					if(tmp.getBoundsLeft().intersects(getBounds())){
						this.remove();
					}
					if(tmp.getBoundsRight().intersects(getBounds())){
						this.remove();
					}	
					if(tmp.getBounds().intersects(getBounds())){
						this.remove();
					}
					if(tmp.getBoundsTop().intersects(getBounds())){
						this.remove();
					}
				}else{
					if(a instanceof Block || a instanceof Ground){
						if(a.getBounds().intersects(getBounds())){	
							this.remove();
						}
					}
					// if(a.getBounds().intersects(getBounds())){	
					// 	this.remove();
					// }
				}
			}
			return false;
		
		
	}

	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.yellow);
		g2.fill(new Rectangle((int)this.x, (int)this.y , (int)this.wdt, (int)this.hgt));
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)this.wdt, (int)this.hgt);
	}
}