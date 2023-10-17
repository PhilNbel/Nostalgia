import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
abstract class Objet{
	double x, y, wdt, hgt;
	boolean remove = false;

	public Objet(double a, double b){ //TYPIQUEMENT UNE DIAGONALE ON A PAS BESOIN DE CONNAITRE SA WIDTH OU HEIGTH
		x=a ;
		y=b;

	}
	public Objet(double a, double b, double c, double d){
		x=a ;
		y=b;
		wdt = c;
		hgt = d;
	}

	public abstract void tick(LinkedList<Objet> obj);

	public abstract void render(Graphics g);
	//Rectangle exetends Rectangle2D :)
	public abstract Rectangle getBounds();

	public void colli(LinkedList<Objet> o){}

	public boolean colli(LinkedList<Objet> o,IntAc mv){return false;}

	public void remove(){
		remove = true;
	}

	public  double getX(){
		return x;
	}
	public  double getY(){
		return y;
	}
	public  void setX(double x){
		this.x = x;
	}
	public  void setY(double y){
		this.y = y;
	}
	public  double getWdt(){
		return x;
	}
	public  double getHgt(){
		return y;
	}
	public  void setWdt(double w){
		this.wdt = w;
	}
	public  void setHgt(double h){
		this.hgt = h;
	}
	//Nous servira a reconnaitre les objets
	
}
