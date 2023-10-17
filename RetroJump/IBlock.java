import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
public class IBlock extends IntrEnn{
	
	public IBlock(double x, double y, double vX, double vY){
		super(x, y, vX, vY, false, 0L);
		

	}

	public  void tick(LinkedList<Objet> obj){}
	
	public  boolean tick(LinkedList<Objet> obj, long debtime){
		long t = System.currentTimeMillis();
		if((t - (debtime+(this.decompte*500))) >= 0L) {
			this.visible = true;
			this.x += this.velX;
			this.y += this.velY;
			return true;
		}else{
			return false;
		}
	}
	// public void run_collision(LinkedList<Objet> obj){

	// }

	public boolean collision(LinkedList<Objet> obj){
		Pers p = (Pers)obj.get(0);
		if(p.getBoundsLeft().intersects(getBounds())){
			double xt = this.x;
			xt += 32;
			p.setX(xt);
		}
		if(p.getBounds().intersects(getBounds())){
			p.y =this.y - p.wdt;
			p.velY = 0.0;
			p.saute = false;
			p.dbsaut = false;
			p.tombe = false;
						
		}
		if(p.getBoundsRight().intersects(getBounds())){
			double xt2 = this.x;
			xt2 -= 32;
			p.setX(xt2);	
		
		}
		if(p.getBoundsTop().intersects(getBounds())){
			p.y= this.y+34;
			p.velY = 0.0;
			p.tombe = true;
			p.rh = true;
		}
		return false;
		
	}

	public  void render(Graphics g){
		if(this.visible){
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(Color.yellow);
			g2.fill(new Rectangle((int)this.x, (int)this.y , 32, 32));
		}
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y , 32, 32);
	}
} 	