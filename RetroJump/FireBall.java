import java.awt.geom.Ellipse2D.Double;

import java.awt.geom.Ellipse2D;



import java.awt.Graphics;

import java.util.LinkedList;

import java.awt.Color;

import java.awt.Point;

import java.awt.*;

import java.awt.geom.Rectangle2D;



public class FireBall extends IntAc{

	Ellipse2D.Double ball;

	public FireBall(double x1, double y1, double vX, double vY){

		super(x1, y1, 16, 16);

		this.ball = new Ellipse2D.Double(this.x, this.y, this.wdt, this.hgt);

		this.velX = vX;

		this.velY = vY;

	}





	public void tick(LinkedList<Objet> obj){
		// System.out.println("tick boule de feu vX"+velX+" vY"+velY);
		
		this.x+=velX;

		this.y+=velY;

	}



	// public void run_collision(LinkedList<Objet> obj){



	// }

	public boolean colli(LinkedList<Objet> obj, IntAc mv){
		if(mv instanceof Pers){
			Pers p = (Pers)obj.get(0);

			if(getBoundsEllipse().intersects(p.x, p.y, p.wdt, p.hgt)){
				p.setX(5.0);
				p.setY(5.0);
			}
		}else{
			if(!(mv instanceof FireBall)){
				Rectangle lft = mv.getBoundsLeft();
				Rectangle rgt = mv.getBoundsRight();
				Rectangle bnd = mv.getBounds();
				Rectangle top = mv.getBoundsTop();
			if(this.getBoundsEllipse().intersects(lft.getX(), lft.getY(), lft.getWidth(), lft.getHeight())){
				this.remove();
			}
			if(this.getBoundsEllipse().intersects(rgt.getX(), rgt.getY(), rgt.getWidth(), rgt.getHeight())){
				this.remove();
			}
			if(this.getBoundsEllipse().intersects(bnd.getX(), bnd.getY(), bnd.getWidth(), bnd.getHeight())){
				this.remove();
			}
			if(this.getBoundsEllipse().intersects(top.getX(), top.getY(), top.getWidth(), top.getHeight())){
				this.remove();
			}

			}
		}
		return false;
	}



	public void render(Graphics g){

		
		Graphics2D g2 = (Graphics2D)g;
		

		g2.drawImage(Texture.getImg(98),(int)x,(int)y,null);





	}


	public Rectangle getBounds(){

		Rectangle2D r = ball.getBounds2D();
		


		return new Rectangle((int)r.getX(),(int)r.getY(),(int)r.getHeight(),(int)r.getWidth());
		

	}



	public Ellipse2D.Double getBoundsEllipse(){
		this.ball = new Ellipse2D.Double(this.x, this.y, this.wdt, this.hgt);
		return ball;

	}

}
