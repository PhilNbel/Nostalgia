import java.util.LinkedList;
import java.awt.*;
import java.lang.Math;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
public class Diagonal extends Ligne{
	double p;
	double diffH;
	public Diagonal(double x1, double y1, double x2, double y2){
		super(x1, y1, x2, y2);
		this.p = (y1-y2)/(x2-x1);
		//to_string();
		//System.out.println("p==== " + p );
		if(x1 <= x2 ){
			this.diffH = x2-x1;
		}else{
			this.diffH = x1 - x2;
		}
		//System.out.println(p);
	}

	public void to_string(){
		System.out.println("x="+x+"y="+y+"x2="+x2+"y2="+y2);
		System.out.println("a = "+(y-y2)+", b = "+(x2-x)+", a/b = "+p);
	}

	public double getP(){ return p; }

	public double getX2(){
		return x2;
	}
	public double getY2(){
		return y2;
	}
	public void setX2(double x2){
		this.x2 = x2;
	}
	public void setY2(double y2){
		this.y2 = y2;
	}

	public boolean colli(LinkedList<Objet> obj, IntAc mv){
		// Pers per = (Pers)obj.get(0);
		double p= getP();
		line = new Line2D.Double(this.x, this.y, this.x2, this.y2);

		if(mv.getBounds().intersectsLine(line) && mv.getBoundsRight().intersectsLine(line) && !mv.getBoundsLeft().intersectsLine(line) ){
			if(! (mv instanceof Pers)){
				mv.velY = Math.abs(p);
			}


			mv.climb();
			mv.y = mv.y - (mv.velY);
			mv.x = mv.x - mv.velX;
			if(mv.velX != 0){
			mv.x += Math.abs(p);
			}
			mv.y -= Math.abs(p);
			double[] mod = Phys.react(p,mv.velY,mv.velX);
			if(p>0){
				mv.velX = mod[0];
				mv.velY = mod[1];
			}else{
				mv.velX = - mod[0];
				mv.velY = - mod[1];
			}

			mv.rotation = Math.atan2(y - y2, x - x2);
			mv.angle = p;
			return true;
		}else if(mv.getBounds().intersectsLine(line) && mv.getBoundsLeft().intersectsLine(line) && !mv.getBoundsRight().intersectsLine(line) ){
			mv.climb();
			if(! (mv instanceof  Pers)){
				mv.velY = Math.abs(p);
			}
			// System.out.println("bas et gauche");

			mv.y = mv.y - (mv.velY)-1;
			mv.x = mv.x - mv.velX;
			if(mv.velX != 0){
				mv.x -= Math.abs(p);
			}
			mv.y -= Math.abs(p);
			double[] mod = Phys.react(p,mv.velY,mv.velX);
			if(p>0){
				mv.velX = mod[0];
				mv.velY = mod[1];
			}else{
				mv.velX = - mod[0];
				mv.velY = - mod[1];
			}
			mv.rotation = Math.atan2(y - y2, x - x2);
			mv.angle = p;
			return true;
		}else if( mv.getBounds().intersectsLine(line)&& mv.getBoundsLeft().intersectsLine(line) && mv.getBoundsRight().intersectsLine(line) && p<0.2 && p>-0.2){

				mv.climb();
				mv.y = this.y - mv.hgt;
				// System.out.println("tout droit");
				mv.angle = 0;
				return true;
		}else if(mv.getBoundsTop().intersectsLine(line))
				mv.setVelX(0);
		else{
			// System.out.println("autre cas");
			if(mv.getBoundsRight().intersectsLine(line)){
			//	System.out.println("droite");
				mv.x -= mv.velX;
				mv.y -= mv.velY;
			}
			// mv.dontclimb();
		}
		return false;

	}
	public void tick(LinkedList<Objet> obj){}

	public void render(Graphics g){

		g.setColor(Color.white);
		// System.out.println(this.x +" " + this.y + " " + this.x2+ " " + this.y2);
		Graphics2D g2 = (Graphics2D) g;
		line = new Line2D.Double(this.x, this.y, this.x2, this.y2);
		// g2.setStroke(new BasicStroke(5)); <-- Permet de gerer lepaisseur de notre ligne
		g2.draw(line);


	}

	// public Rectangle getBounds(){
	// 	Rectangle2D r = line.getBounds();

	// 	return (Rectangle)r;
	// }

	public Line2D getBoundsLine(){
		return line;
	}

}
