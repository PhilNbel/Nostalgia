import java.awt.Graphics;
import java.util.LinkedList;
abstract class IntrEnn extends IntAc{
	boolean visible, isdone, wasvisible; // Est ce que le piege est invisible
	long decompte;
	double tmpX, tmpY, tmpVX, tmpVY;


	public IntrEnn(double a, double b, double c, double d, boolean visible, long decompte){
		super(a, b);
		this.tmpX = a;
		this.tmpY = b;
		this.visible = visible;
		this.wasvisible=visible;
		this.decompte = decompte;
		this.velX = c;
		this.velY = d;
		this.tmpVX = c;
		this.tmpVY = d;
	}

	public boolean getVis(){
		return visible;
	}



	abstract boolean tick(LinkedList<Objet> o, long l);
	

	abstract boolean collision(LinkedList<Objet> o);


}