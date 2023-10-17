	import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;
import java.awt.geom.Line2D ;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
public class Pers extends IntAc{
	int ti,n,move;
	
	boolean rg,rd,rh,rb;
	Arme weap;
	boolean attaque;
	double backX;
	double topY;
	long tmpAttaque = System.currentTimeMillis();
	public Pers(double lo, double la){
		super(5.0,5.0, la, lo);
		setDir(true);
		ti=0;
		this.weap = new Arme(this.x, this.y, 0, 0, 200, 0.0, 10);
		this.backX = 0;
		this.topY = 0;

	}
	public void attaque(LinkedList<Objet> obj){//touche pas a ca
		if(tmpAttaque == 0L){
			tmpAttaque = System.currentTimeMillis();
		}else{
			long t = System.currentTimeMillis();
		//if(this.getIdObj() == IdObj.Arc){
			if((t - (tmpAttaque+(1L*200))) >= 0L){
				System.out.println("Pers attaque !!!");
				if(fnor){//Le perso regarde a droite
					this.weap.tick(obj, 0, 0, 0, (byte)2);
				}else{
					this.weap.tick(obj, 1, 0, 0, (byte)2);
				}
				tmpAttaque = 0L;	
			}
		}
		
		// System.out.println("Pers attaque !!!");
	}
	
	public void setMove(int inte){ move=inte; }

	public boolean warp(double a, double b){ return (x>a||y>b); }

	


	public void tick(LinkedList<Objet> obj){
		if(velX==0) if(fnor)ti=0; else ti=35;
		else if( velX>0 && ti <3) ti=5;
		else if( velX<0 && ti <3) ti=45;
		if( velX>0 && ti>=30) ti=5;
		if( velX<0 && ti>=65) ti=40;
		ti++;
		if(this.angle != 0){
			if(this.angle > 0){ //Une montee
				if(fnor){
					this.weap.velYAttaque = this.weap.velXAttaque * this.angle;
				}else{
					this.weap.velYAttaque = -this.weap.velXAttaque * this.angle;
				}
			}else{ //Une descente
				if(fnor){
					this.weap.velYAttaque = this.weap.velXAttaque * this.angle;

				}else{

					this.weap.velYAttaque = -this.weap.velXAttaque * this.angle;
				}
			}
		
			
		}
		if(saute){
			this.weap.velYAttaque = 0;
		}

		if(tombe || saute){
			if(this.climb == false){ //...
				this.rotation = 0;
			}
			rb = false;
			velY+=Phys.grav;
			if(velY > Phys.maxSpeedY){
				velY = Phys.maxSpeedY;
			}
		}
		

		if(move==1)
			velX-=3.0;
		else if(move==2)
			velX+=3.0;

		if(move==1)
			fnor=false;
		if(move==2)
			fnor=true;

		if(velX>Phys.maxSpeedX)
			velX=Phys.maxSpeedX;
		if(velX<-Phys.maxSpeedX)
			velX=-Phys.maxSpeedX;

		if(x<0) x = 0;
		if(tombe)
			// System.out.println("tombe");
			velX=Phys.frott(velX,0.1);
		

		if(velX>-0.05&&velX<0.05) velX=0;
		if(velY>-0.05&&velY<0.05) velY=0;
		if(fnor){
			this.weap.x = this.x;
			this.weap.y = this.y+(this.hgt/2);
		}else{
			this.weap.x = this.x-(this.wdt);
			this.weap.y = this.y+(this.hgt/2);

		}
		x += velX;
		y += velY;
		this.weap.tick(obj);
	}
	public void collision(LinkedList<Objet> objs){ //Notre Perso n'a que des haches
		boolean ret = this.weap.collision(objs);
		
	}

	public void render(Graphics g){
		this.weap.render(g);
		AffineTransform at = AffineTransform.getTranslateInstance(this.x, this.y);
		if(this.rotation != 0 && saute == false){ 
			if( this.rotation >=3 && this.rotation <=3.2){
				at = AffineTransform.getTranslateInstance(this.x, this.y+Math.abs(this.rotation));
			}
			else if(this.rotation <= 0){
				// System.out.println("rotation ="+this.rotation);
				// System.out.println("On descend");
				at = AffineTransform.getTranslateInstance(this.x+this.wdt, this.y+Math.abs(this.rotation));
			} else{
				at = AffineTransform.getTranslateInstance(this.x-this.wdt, this.y+(hgt/Math.abs(this.rotation)));
			}
			// System.out.println("this.rotation ==="+this.rotation);
			at.rotate(this.rotation+Math.toRadians(180));
		}else{
			this.rotation = 0;
		}
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(Texture.getImg(49+(ti/5)), at, null);
		
		g.setColor(Color.red);
		// g2.draw(getBounds());
		// g2.draw(getBoundsLeft());
		// g2.draw(getBoundsRight());
		// g2.draw(getBoundsTop());
	}
	
	
	
}
