import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;

import java.awt.geom.Line2D ;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double; 
import java.awt.image.BufferedImage;
public class Hache extends IntAc{
	double portee, xsave;
	boolean stuck = false;
	long debutLancement;
	int rotate = 1;
	public Hache(double x, double y, double velX, double velY, double p){
		super(x, y, 10, 18);
		this.velX = velX;
		this.velY = velY;

		this.xsave = x;
		this.portee = p;

	}
	public void tick(LinkedList<Objet> obj){
		this.x += this.velX;
		this.y += this.velY;

	}
	public boolean ticki(LinkedList<Objet> obj){
		this.x += this.velX;
		this.y += this.velY;
		if(velX < 0){
			if(this.x <= this.portee){
				return true; // On enleve la hache
			}else{
				return stuck;
			}
		}else{
			if(this.x >= this.portee){
				return true; //On enleve la hache;
			}else{
				return stuck;
			}
		}	
	}
	//Rappel : direction = 0 si ca va de gauche a droite et inversement si 1;
	/*public void tick(LinkedList<Objet> obj, long debut, int direction){
		if(debutLancement == 0L){
			this.xsave = this.x;
			this.debutLancement = debut;
		}else{
			this.x += this.velX;
			this.y += this.velY;
			if(direction == 0){
				if(this.x >= this.xsave + portee){
					this.velX = -this.velX;
				}
			}else{

			}

		}
	}*/
	// public void run_collision(LinkedList<Objet> obj){
		
	// }
	public boolean colli(LinkedList<Objet> obj, IntAc mv){
		System.out.println("coliH");
		if(mv.getBounds().intersects(getBounds())){
			System.out.println("DIE!");
			mv.die();
			return true;
		}
		return false;
		
	}

	public boolean collision(LinkedList<Objet> obj){		
		
		Objet t ;
		for(int i = 1; i<obj.size(); i++){
			t = obj.get(i);
			if(t instanceof IntAc){
				IntAc inta = (IntAc)t;
				if(! (inta instanceof FireBall)){
					if(inta.getBounds().intersects(getBounds())){
						inta.remove();
					}
					if(inta.getBoundsLeft().intersects(getBounds())){
						inta.remove();
					}
					if(inta.getBoundsRight().intersects(getBounds())){
						inta.remove();
					}
					if(inta.getBoundsTop().intersects(getBounds())){
						inta.remove();
					}
				}else{ //On fume la fireBall
					FireBall tmp = (FireBall) inta;

					if(tmp.getBoundsEllipse().intersects(this.x, this.y, this.wdt, this.hgt)){
						tmp.remove();
					}
				}
			}else{
				if( t instanceof Block ){
					if(t.getBounds().intersects(getBounds())){
						this.remove = true;
					}
				}else{
					if(t instanceof Diagonal){
						Diagonal diag = (Diagonal)t;
						Line2D.Double line = new Line2D.Double(diag.x, diag.y, diag.x2, diag.y2);
						if(getBounds().intersectsLine(line)){
							this.remove = true;
						}
					}
					if(t instanceof WorldInFire){
						WorldInFire wif = (WorldInFire) t;
						wif.colliHache(this);

					}

					if(t instanceof Piege){
						Piege p1 = (Piege)t;
						p1.colliHache(this);
					}
				}

			}
		}
		return this.remove;
	}

	public void render(Graphics g){
		BufferedImage h1;
		if(velX > 0) h1= Texture.getImg(298);
		else h1 = Texture.getImg(297);
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(rotate), 5, 9);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(h1, at, null);
		if(velX > 0){
			this.rotate += 5;
		}else{
			this.rotate -= 5;
		}
	}
	public Rectangle getBounds(){ 
		return new Rectangle((int)x, (int)y, 10, 18);
	}
}