import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
class End extends Objet{
	public End(){
		super(-1,-1);
	}
	public void tick(LinkedList<Objet> obj){}

	public void render(Graphics g){}
	public Rectangle getBounds(){return null;}
}