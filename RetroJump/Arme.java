import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;

import java.awt.geom.Line2D ;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double; 

public class Arme extends Objet{
	LinkedList<IntAc>munitions = new LinkedList<IntAc>();
	double h; //heigth
	double w; //width
	double porte;
	double velXAttaque; //velX chiffre strictement positif
	boolean cac;
	double velYAttaque;

	public Arme(double posX, double posY, double h, double w, double porte,double velYAttaque, double velXAttaque){
		super(posX, posY);
		this.h = h;
		this.w = w;
		this.porte = porte;
		this.velYAttaque = velYAttaque;
		this.velXAttaque = velXAttaque;
		this.cac = false;
	}

	public void tick(LinkedList<Objet> obj){
		for(int i = 0; i<munitions.size(); i++){
			IntAc e = munitions.get(i);
			if(e instanceof Hache){
				Hache h = (Hache)e;
				boolean a = h.ticki(obj);
				if(a){
					munitions.remove(i);
				}
			}else{
				e.tick(obj);
			}
		}
	}

	public double getPortee(){
		return porte;
	}
	
	public boolean tick(LinkedList<Objet> obj, int direction, long debtime, long interval, byte typeMunition){ 
		

		long t = System.currentTimeMillis();
		double limx = 0;
		//if(this.getIdObj() == IdObj.Arc){
			if((t - (debtime+(interval*500))) >= 0L){
				double va = 0;
				if(direction == 0){
					va = this.velXAttaque;
					limx = this.x + porte;

				}else{
					va -= this.velXAttaque;
					limx = this.x - porte;
				}
				if(typeMunition == 1){
					munitions.add(new Fleche(this.x, this.y, va, velYAttaque, 38, 4));	
					//System.out.println("FLECHE AJOUTE");
				}else{
					munitions.add(new Hache(this.x, this.y-10, va, -velYAttaque, limx));	
					//System.out.println("Hache AJOUTE va = "+va+ "porte_y = "+velYAttaque);

				}
				tick(obj);
				return true;
			}else{
				tick(obj);

				return false;
			}

		//}
	
	}
	public boolean collision(LinkedList<Objet> o){
		for(int m = 0; m<munitions.size() ; m++){
			IntAc mun = munitions.get(m);
			if(mun instanceof Fleche){
				Fleche f = (Fleche) mun;
				if(f.collision(o) == true){
					return true;
				}	
			}
			if(mun instanceof Hache){
				Hache h = (Hache) mun;
				if(h.collision(o) == true){
					h.remove();
				}	
			}
			
		}
		verifMun();
		return false;
	}

	public void verifMun(){
		for(int i = 0; i<munitions.size(); i++){
			if(munitions.get(i).remove){
				munitions.remove(i);
			}
		}
	}
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.yellow);
		g2.fill(new Rectangle((int)this.x, (int)this.y , (int)this.w, (int)this.h));
		for(int j = 0; j<munitions.size() ; j++){
			IntAc m = munitions.get(j);
			m.render(g);
		}
	}
	//Rectangle exetends Rectangle2D :) 
	public  Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)w, (int)h);
	}
}
