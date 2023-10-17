import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;//Un ennemy peut etre invisible, bouger et attaquer
public class Ennemy extends IntrEnn{ //1s et 1 000 ms
	boolean attaque, see=false;
	Diagonal detectLine;
	double longeur; // strictiment positif
	double limite, portee; //Strictement positif et > 0
	long interAttaque; //L'intervalle entre les attaque en seconde
	int direction; //0 l'ennemi va de gauche a droite, puis inversement ; 1 l'ennemy va de droite a gauche puis inversement
	Arme weapon;
	long tmpTime = 0l;
	double exx2;
	static int i = 1;
	static boolean b = false;
	public Ennemy(double posX, double posY, double velX, double longeur, int direction, long interAttaque, Diagonal detectLine, boolean visible){
		super(posX, posY, velX, 0.0, visible, 0L);
		this.wdt = 27;
		this.hgt = 39;
		this.longeur = longeur;
		this.detectLine = detectLine;
		this.direction = direction;
		this.limite = (direction == 0) ? posX+longeur : posX-longeur;
		this.interAttaque = interAttaque; //Intervall
		this.weapon = null;
		this.portee=portee;
	}

	public void addWeapon(Arme a){
		this.weapon = a;
	}


	//DEUX TYPES D'ARME, PORTEE, DISTANCE
	public void tick(LinkedList<Objet> obj){

		this.weapon.tick(obj);
		if(attaque){

			


			//si attaque l'ennemi s'arrete sa porte augmente et il tire
			if(tmpTime == 0L){
				i = 1;
				tmpTime = System.currentTimeMillis();
				if(!see){
				if(this.direction == 0){
					exx2 = this.detectLine.x2;
					this.detectLine.x2 += this.weapon.porte;
					see=true;
				}else{
					exx2 = this.detectLine.x2;

					this.detectLine.x2 -=  this.weapon.porte;
					see=true;
				}
				}
			}else{
				if( b == true){
					i++;
				}
				boolean a = this.weapon.tick(obj, direction, tmpTime, this.interAttaque*i, (byte)1);
				if(a == true){
					b = true;
				}else{
					b = false;
				}
			}




		}else{
			tmpTime = 0L;
			this.x += this.velX;

			if(this.weapon != null){
				this.weapon.x += this.velX;
			}
			this.y += this.velY;

			this.detectLine.x += this.velX;
			this.detectLine.x2 += this.velX;
		}
		if( ((this.x >= this.limite )|| (this.x <= tmpX)) && (this.direction == 0) ){
			this.velX = -this.velX;
			this.direction = 1;
			this.weapon.x = this.x-this.weapon.wdt;
			this.detectLine.x2 = this.detectLine.x - this.detectLine.diffH;
		}else{
			if((this.x >= this.limite || this.x <= this.tmpX) && direction == 1){
				this.velX = -this.velX;
				this.weapon.x = this.x + this.wdt;
				this.direction = 0;
				this.detectLine.x2 = this.detectLine.x + this.detectLine.diffH;
			}
		}
		collision(obj);



	}

	public boolean tick(LinkedList<Objet> o, long l){
		return true;
	}
	// public void run_collision(LinkedList<Objet> o){

	// }

	public void colli(LinkedList<Objet> o){
		if(dead) return;
		Diagonal diag2 = (Diagonal)this.detectLine;
		Pers p = (Pers)o.get(0);
		Line2D.Double line = new Line2D.Double(diag2.x, diag2.y, diag2.x2, diag2.y2);
		if(p.getBoundsRight().intersectsLine(line) || p.getBoundsLeft().intersectsLine(line) || p.getBounds().intersectsLine(line) || p.getBoundsTop().intersectsLine(line)  ){
						// System.out.println("collision detecteur");
			this.setActif(true);

		}else{
			this.setActif(false);
		}
	}


	public boolean collision(LinkedList<Objet> o){
		if(dead) return true;
		Line2D line = new Line2D.Double(detectLine.x, detectLine.y, detectLine.x2, detectLine.y2);
		Pers p = (Pers) o.get(0);
		if(this.weapon.collision(o) == true ){
			p.setX(5);
			p.setY(5);
		}

		return true;
	}

	public void setActif(boolean a){
		attaque = a;
	}
	public void render(Graphics g){
		if(detectLine == null){
			System.out.println("Pas de render");
		}
		// piege.render(g);
		Graphics2D g2 = (Graphics2D)g;
		Line2D line = new Line2D.Double(detectLine.x, detectLine.y, detectLine.x2, detectLine.y2);
		this.weapon.render(g);
		
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File("img/enne.png"));
		}catch(Exception e){}
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


		g2.drawImage(img, at, null);
		// weapon.render(g);
		// g2.draw(detectLine.getBoundsLine());
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)this.wdt, (int)this.hgt);
	}

	public Line2D getBoundsLine(){
		return detectLine.line;
	}
	//possede vX, vY ...
}
