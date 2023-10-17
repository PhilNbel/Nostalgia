import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bouncer extends Block{

	double indice;

	public Bouncer(double a, double b){
		super(a, b, (byte)5);
		indice=1.3;
	}
	public Bouncer(double a, double b, double d){
		super(a, b, (byte)5);
		indice=d;
	}

	public void tick(LinkedList<Objet> obj){}

	public boolean colli(LinkedList<Objet> o, IntAc mv){

		Objet tmp;
		// Pers p = (Pers)o.get(0);
		if(mv.getBoundsLeft().intersects(getBounds())){
			mv.setX(mv.getX()+Math.abs(mv.getVelX())+1);
			mv.setVelX(0);
		}
		if(mv.getBoundsRight().intersects(getBounds())){
			mv.setX(mv.getX()-Math.abs(mv.getVelX())-1);
			mv.setVelX(-mv.getVelX());
		}

		if(mv.getBoundsTop().intersects(getBounds())){
			mv.setY(y+32);
			mv.setVelY(-Phys.boing(mv.getVelY(),indice)+2);

		}

		if(mv.getBounds().intersects(getBounds())){
			// System.out.println("DANS COLLI");
			mv.setY(y-mv.hgt+1);
			mv.setVelY(Phys.boing(mv.getVelY()+2,indice));
			mv.stand();
			mv.setVelX(Phys.frott(mv.getVelX(),ind));
//			rh = true;

		}
		return false;
	}
	public void render(Graphics g){
		g.drawImage(Texture.getImg(9),(int)x,(int)y,null);
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)this.wdt, (int)this.hgt);
	}

}
