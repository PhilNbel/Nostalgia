import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;

import java.awt.geom.Line2D ;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ; //Un piege est constitué d'au minimum une ligne qui le déclanche et d'objets piégé qui réagissent en fonction
public class Piege extends Objet{ //1s et 1 000 ms
	boolean actif;
	LinkedList<IntrEnn> pieges =new LinkedList<IntrEnn>();
	Diagonal detectLine;
	long tmpT = 0L;												//En secondes

	public Piege(double posX, double posY ,Diagonal detectLine){
		super(posX, posY);
		
		this.detectLine = detectLine;
	}


	public void addPiege(IntrEnn pi){
		pieges.add(pi);
	}

	public void tick(LinkedList<Objet> obj){
		if(actif){
			if(this.tmpT == 0l){
				tmpT = System.currentTimeMillis();
			}
			for(IntrEnn i : pieges){
				boolean ra = i.tick(obj, tmpT);
				if(ra){
					boolean f = i.collision(obj);
					if(f){
						Pers p = (Pers) obj.get(0);
						p.setX(5);
						p.setY(5);
						p.setVelX(0);
						p.setVelX(0);
					}
				}
			}

			verifMun();
		}
	}
	public void verifMun(){
		for(int i = 0; i< pieges.size(); i++){
			if(pieges.get(i).remove){
				pieges.remove(i);
			}
		}
	}

	public void reinitList(){
		tmpT = 0L;
		actif = false;
		for(IntrEnn e : pieges){
			e.x = e.tmpX;
			e.y = e.tmpY;
			e.visible = e.wasvisible;
			e.isdone = false;
			e.velX = e.tmpVX;
			e.velY = e.tmpVY;
		}
	}

	public void colli(LinkedList<Objet> o){
		Pers p=(Pers)o.get(0);
		Diagonal diag = (Diagonal)detectLine;
		Line2D.Double line = new Line2D.Double(diag.x, diag.y, diag.x2, diag.y2);
		if(p.getBoundsRight().intersectsLine(line) || p.getBoundsLeft().intersectsLine(line) || p.getBounds().intersectsLine(line) || p.getBoundsTop().intersectsLine(line)  ){
			// System.out.println("collision detecteur");
			setActif(true);
		}	
	}

	private boolean collision(LinkedList<Objet> o){
		// Line2D line = new Line2D.Double(detectLine.x, detectLine.y, detectLine.x2, detectLine.y2);
		// Pers p = (Pers) o.get(0);
		// if()
		return true;
	}

	public void setActif(boolean a){
		actif = a;
	}
	public void render(Graphics g){
		if(detectLine == null){
			System.out.println("Pas de render");
		}
		// piege.render(g);
		Graphics2D g2 = (Graphics2D)g;
		Line2D line = new Line2D.Double(detectLine.x, detectLine.y, detectLine.x2, detectLine.y2);
		// g2.draw(line);
		// g2.draw(detectLine.getBoundsLine());
		for(IntrEnn i : pieges){
			if(actif){
				i.render(g);
			}else{
				if(i.getVis()){
					i.render(g);
				}
			}
		}
	}	

	public void colliHache(Hache h){
		for(int i =0; i<pieges.size(); i++){
			IntrEnn p = pieges.get(i);

			if(p.getBounds().intersects(h.getBounds())){
				p.remove();
				
			}
			if(p.getBoundsLeft().intersects(h.getBounds())){
				p.remove();
				
			}
			if(p.getBoundsTop().intersects(h.getBounds())){
				p.remove();
				
			}
			if(p.getBoundsRight().intersects(h.getBounds())){
				p.remove();

			}
		}
		verifMun();
	}

	public Rectangle getBounds(){
		return (Rectangle) null;
	}

	public Line2D getBoundsLine(){
		return detectLine.line;
	}
	//possede vX, vY ... 	
}