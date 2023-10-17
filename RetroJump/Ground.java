import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;
class Ground extends Block{
	
	public Ground(double a, double b){ 
		super(a, b, (byte)1);
	}

	public Ground(double a, double b, double d){ 
		super(a, b, (byte)1, d);
	}

	public void render(Graphics g){
		g.drawImage(Texture.getImg(2),(int)x,(int)y,null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}