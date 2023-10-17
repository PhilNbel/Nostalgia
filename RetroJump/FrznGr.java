import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;
class FrznGr extends Ground{
	double ind;
	public FrznGr(double a, double b){ 
		super(a, b);
		ind=0;
	}

	public void render(Graphics g){
		g.drawImage(Texture.getImg(1),(int)x,(int)y,null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}