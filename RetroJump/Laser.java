
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Laser extends IntrEnn{

	public Laser(double x, double y, double vX, double vY, boolean visib, long temps){
		super(x, y, vX, vY, visib, temps);
		this.hgt = 8;
		this.wdt = 40;

	}

	public  void tick(LinkedList<Objet> obj){
		x += this.velX;
		if(tombe){
			y += this.velY;
		}
	}
	// public void run_collision(LinkedList<Objet> obj){
	// 		Objet tmp;
	// 		for(int i = 0 ; i< obj.size(); i++){
	// 			tmp = obj.get(i);
	// 			if(tmp.getIdObj() == IdObj.Diagonal){ //On utilise intersectline
	// 				Diagonal d = (Diagonal) tmp;
	// 				Line2D.Double l =  new Line2D.Double(d.x, d.y, d.x2, d.y2);
	// 				double p = d.getP();


	// 			}else{ //on utilise intersects

	// 			}
	// 		}
	// }

	public  boolean tick(LinkedList<Objet> obj, long debtime){

		long t = System.currentTimeMillis();
		if((t - (debtime+(this.decompte*500))) >= 0L) {
			this.visible = true;
			this.x += this.velX;
			this.y += this.velY;
		}
		return this.visible;
	}
	public boolean colli(LinkedList<Objet> obj, IntAc mv){
		if(mv instanceof Pers){
			Pers p = (Pers)mv;
			if(p.getBoundsLeft().intersects(getBounds())){
				p.setX(5);
				p.setY(5);
				p.setVelX(0);
				p.setVelY(0);

				return true;
			}
			if(p.getBounds().intersects(getBounds())){
				p.setX(5);
				p.setY(5);
				p.setVelX(0);
				p.setVelY(0);

				return true;
			}
			if(p.getBoundsRight().intersects(getBounds())){
				p.setX(5);
				p.setY(5);
				p.setVelX(0);
				p.setVelY(0);

				return true;
			}
			if(p.getBoundsTop().intersects(getBounds())){
				p.setX(5);
				p.setY(5);
				p.setVelX(0);
				p.setVelY(0);

				return true;
			}
		}else{
			if(mv instanceof Laser || mv instanceof Fleche ||  mv instanceof Hache ){
				if(mv.getBoundsLeft().intersects(getBounds())){
					mv.remove();
				}
				if(mv.getBounds().intersects(getBounds())){
					mv.remove();
				}
				if(mv.getBoundsRight().intersects(getBounds())){
					mv.remove();
				}
				if(mv.getBoundsTop().intersects(getBounds())){
					mv.remove();				}
			}

		}
		return false;
	}

	public boolean collision(LinkedList<Objet> obj){
		Pers p = (Pers)obj.get(0);
		if(p.getBoundsLeft().intersects(getBounds())){

			return true;
		}
		if(p.getBounds().intersects(getBounds())){

			return true;
		}
		if(p.getBoundsRight().intersects(getBounds())){

			return true;
		}
		if(p.getBoundsTop().intersects(getBounds())){
			return true;
		}
		Objet a;
		for(int i = 1; i<obj.size() ; i++){
			a = obj.get(i);
			if(this.remove){
				break;
			}
			if(a instanceof IntAc){
				IntAc tmp = (IntAc) a;
				if(tmp.getBoundsLeft().intersects(getBounds())){
					this.remove();
				}
				if(tmp.getBoundsRight().intersects(getBounds())){
					this.remove();
				}
				if(tmp.getBounds().intersects(getBounds())){
					this.remove();
				}
				if(tmp.getBoundsTop().intersects(getBounds())){
					this.remove();
				}
			}else{
				if(a instanceof Block || a instanceof Ground){
					if(a.getBounds().intersects(getBounds())){
						this.remove();
					}
				}
					// if(a.getBounds().intersects(getBounds())){
					// 	this.remove();
					// }
			}
		}
		return false;

	}

	public  void render(Graphics g){
		if(this.visible){
			BufferedImage img = null;
			/*try{
			img = ImageIO.read(new File("img/laser.png"));
			} catch(Exception e){}
	*/		img=Texture.getImg(147);
	//		g.drawImage(Texture.getImg(147),(int)x,(int)y,null);
			Graphics2D g2 = (Graphics2D)g;
			AffineTransform at = AffineTransform.getTranslateInstance(this.x, this.y);
			if(this.rotation != 0 && saute == false){
				if(this.rotation < 0){
					//System.out.println("On descend");
					at = AffineTransform.getTranslateInstance(this.x, this.y-(this.rotation+3.14159));
				}else{
					//System.out.println("On descend");
					at = AffineTransform.getTranslateInstance(this.x, this.y+(40-10));
				}
			// System.out.println("this.rotation ==="+this.rotation);
				at.rotate(this.rotation+Math.toRadians(180));
			}else{
				this.rotation = 0;
			}

			g2.drawImage(img, at, null);
			
			// g.setColor(Color.yellow);
			// g2.fill(new Rectangle((int)this.x, (int)this.y , (int)this.wdt, (int)this.hgt));
		}
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y , (int)this.wdt, (int)this.hgt);
	}
}
