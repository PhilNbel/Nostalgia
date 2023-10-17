import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;

class Portal extends Block{

	boolean fin;
	double destx, desty, velx, vely;

	public Portal(double a, double b, double x, double y){
		super(a, b, (byte)1);
	    destx = x;
	    desty = y;
	    fin=false;
			velx = 0;
			vely = 0;
	}

	public Portal(double a, double b, double x, double y, boolean bol){
		super(a, b, (byte)1);
	    destx = x;
	    desty = y;
	    fin=bol;
			velx = 0;
			vely = 0;
	}

	public Portal(double a, double b, double x, double y, double velx, double vely){
		super(a, b, (byte)1);
	    destx = x;
	    desty = y;
	    fin=false;
			this.velx = velx;
			this.vely = vely;
	}

	public Portal(double a, double b, double x, double y, boolean bol, double velx, double vely){
		super(a, b, (byte)1);
	    destx = x;
	    desty = y;
	    fin=bol;
			this.velx = velx;
			this.vely = vely;
	}

	public boolean colli(LinkedList<Objet> o, IntAc mv){
		/// Pers p = (Pers)o.get(0);
		if(! (mv instanceof FireBall)){
			if(mv.getBoundsLeft().intersects(getBounds()) || mv.getBoundsRight().intersects(getBounds()) || mv.getBounds().intersects(getBounds()) || mv.getBoundsTop().intersects(getBounds())){
				if(fin){
					
					o.add(new End());
				}
				mv.setX(destx);
     			mv.setY(desty);
			}
		}else{
			FireBall fire = (FireBall) mv;
			if(fire.getBoundsEllipse().intersects(x, y, wdt, hgt)){
				fire.remove();
			}
			return false;

		}
		return false;
	}



	public void tick(LinkedList<Objet> obj){
		x += velx;
		y += vely;
	}

	public void finish(boolean b){fin=b;}

	public void render(Graphics g){
	    g.setColor(Color.green);
	  	Graphics2D g2 = (Graphics2D) g;
	    g2.fill(new Rectangle((int)this.x, (int)this.y , 32, 32));
	    // g2.draw(line);
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

  	public boolean collision(LinkedList<Objet> obj){
		Pers p = (Pers)obj.get(0);
		if(p.getBoundsLeft().intersects(getBounds()) || p.getBoundsRight().intersects(getBounds()) || p.getBounds().intersects(getBounds()) || p.getBoundsTop().intersects(getBounds())){
			p.setX(destx);
     	p.setY(desty);
     	System.out.println("contact");
		}
		return false;
	 }

}
