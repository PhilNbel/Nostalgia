
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.geom.Line2D ;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
abstract class IntAc extends Objet{
	double velX = 0, velY = 0;
	boolean tombe = true, saute , dbsaut, fnor, climb, dead = false;
	double rotation;
	double angle;

	public IntAc(double a, double b){
		super(a,b);
		this.rotation = 0;
	}
	public IntAc(double a, double b, double c, double d){
		super(a, b, c, d);
		this.rotation = 0;
	}

	public abstract void tick(LinkedList<Objet> obj);

	public abstract void render(Graphics g);

	// public abstract void run_collision(LinkedList<Objet> obj);

	public void setDir(boolean f){ fnor=f; }

	public void climb(){
		climb = true;
	}
	public void dontclimb(){
		climb = false;
	}
	public  double getVelX(){
		return velX;
	}
	public  double getVelY(){
		return velY;
	}
	public  void setVelX(double x){
		this.velX = x;
	}
	public  void setVelY(double y){
		this.velY = y;
	}
	public void fall(){
		tombe = true;
	}

	public void stand(){
		saute = false;
		dbsaut = false;
		tombe = false;
	}
	public void die(){dead=true;}
	public Rectangle getBounds(){
		return new Rectangle((int) ((int)x+(this.wdt/2)-((this.wdt/2)/2)), (int)((int)y+(this.hgt/2)+6), (int)(this.wdt/2), (int)((this.hgt/2)-2));
	}

	public Rectangle getBoundsTop(){
		return new Rectangle((int) ((int)x+(this.wdt/2)-((this.wdt/2)/2)), (int)y-2, (int)(this.wdt/2), (int)(this.hgt/2));
	}

	public Rectangle getBoundsRight(){
		return new Rectangle((int) ((int)x+this.wdt-5), (int)(y+1), (int)5, (int)(this.hgt-3));
	}

	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x, (int)(y+1), (int)5, (int)(this.hgt-3));
	}

}
