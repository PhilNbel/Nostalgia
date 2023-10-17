
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle; //Ici vx ==  vy
public class MurPiquant extends Laser{ //Qui suit la trajectoire de la diagonale qu'il utilise comme detecteur
	double limite = 0;
	double debut = 0;
	int sens = 0; // si sens == 0 > gauche a droite if sens == 1 droite a gauche
	public MurPiquant(double x, double y, Diagonal l, double vel, int sens, boolean visib, long temps){
		super(x, y, vel, l.p*vel, visib, temps);
		this.hgt= 32*4;
		this.wdt = 40;

		if(sens == 1){
			this.velX = -this.velX;
			this.limite = (l.x >= l.x2) ? l.x2 : l.x;

		}else{
			this.limite = (l.x >= l.x2) ? l.x : l.x2;
			

		}
		if(this.limite == l.x2){
				this.debut = l.x;
		}else{
			this.debut = l.x2;
		}
		this.sens = sens;
		this.isdone = false;
		
	}

	public  void tick(LinkedList<Objet> obj){
		super.tick(obj);
	}

	// public void run_collision(LinkedList<Objet> obj){

	// }
	
	public  boolean tick(LinkedList<Objet> obj, long debtime){

		long t = System.currentTimeMillis();
		if((t - (debtime+(this.decompte*500))) >= 0L){
			this.visible = true;
			if(sens == 1){
				if(!isdone){
					if(this.x <= this.limite){
						this.velX = -this.velX;
						this.velY = -this.velY;
						isdone = true;
					}
				}else{
					if(this.x >= this.debut){
						this.velX = 0;
						this.velY = 0;
					}
				}
			}else{
				if(!isdone){
					if(this.x >= this.limite){
						this.velX = -this.velX;
						this.velY = -this.velY;
						isdone = true;
					}
				}else{
					if(this.x <= this.debut ){
						this.velX = 0;
						this.velY = 0;
					}
				}
			}
			this.x += this.velX;
			this.y += this.velY;
		}
		return this.visible;
	}

	public boolean collision(LinkedList<Objet> obj){
		return super.collision(obj);
		
	}

	public  void render(Graphics g){
		g.setColor(Color.yellow);
		Graphics2D g2 = (Graphics2D)g;
		g2.fill(new Rectangle((int)this.x, (int)this.y , (int)this.wdt, (int)this.hgt));
	}

	public Rectangle getBounds(){
		return super.getBounds();
	}
}