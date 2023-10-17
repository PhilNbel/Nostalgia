import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;

import java.awt.geom.Line2D ;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
public class Ligne extends Objet{
	double x2 , y2;
	Line2D line;
	public Ligne(double x1, double y1, double x2, double y2){ 
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
		// p=10*(y2-y1)/(x2-x1);
	}

	public Ligne(double x1, double y1, int l){
		super(x1, y1);
		this.x2 = x1+l;
		this.y2 = y1;
	}



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
	
	public void tick(LinkedList<Objet> obj){}

	public void render(Graphics g){

		g.setColor(Color.white);
		// System.out.println(this.x +" " + this.y + " " + this.x2+ " " + this.y2);
		Graphics2D g2 = (Graphics2D) g;
		line = new Line2D.Double(this.x, this.y, this.x2, this.y2); 
		// g2.setStroke(new BasicStroke(5)); <-- Permet de gere lepaisseur de notre ligne
		g2.draw(line);
		
		
	}
	
	public Rectangle getBounds(){
		Rectangle2D r = line.getBounds();
	
		return (Rectangle)r;
	}
	
	public Line2D getBoundsLine(){
		return line;
	}

}