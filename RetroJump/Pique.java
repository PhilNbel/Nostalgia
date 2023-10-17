import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Color;

import java.util.LinkedList;

import java.awt.Rectangle;

public class Pique extends Laser{



	public Pique(double x, double y, double vY, boolean visib, long temps){

		super(x, y, 0, vY, visib, temps);

		this.hgt = 40;

		this.wdt = 8;



	}



	public  void tick(LinkedList<Objet> obj){



	}



	public  boolean tick(LinkedList<Objet> obj, long debtime){

		return super.tick(obj, debtime);

	}



	public boolean collision(LinkedList<Objet> obj){

		return super.collision(obj);



	}



	// public void run_collision(LinkedList<Objet> obj){



	// }

	public  void render(Graphics g){

		if(this.visible){

			Graphics2D g2 = (Graphics2D)g;

			g.setColor(Color.yellow);

			g2.fill(new Rectangle((int)this.x, (int)this.y , (int)this.wdt, (int)this.hgt));

		}

	}



	public Rectangle getBounds(){

		return new Rectangle((int)x, (int)y , (int)this.wdt, (int)this.hgt);

	}



}
