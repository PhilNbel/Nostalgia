import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.image.BufferStrategy;
import javax.swing.JApplet;
public class Jeu extends Canvas implements Runnable{

	private static boolean run =false;
	boolean lvlD=false;
	private Thread th;
	public int lvl;
	public boolean cont;
	private Controleur kl;
	public LinkedList<Objet>objs = new LinkedList<Objet>();
	private Objet obj;
	Mod modele;
	Cadrage cadre;
	public static int WIDTH, HEIGHT;
	Jeu(){
		init();
	}
	public Jeu(Single s){

		this.lvl = s.n;
		System.out.println("lvl ==="+this.lvl);
		cont = true;
		init();

	}
	public void setLv(int n){
		lvl = n;
	}

	public void run(){

		requestFocus();
		long time = System.nanoTime();
		double tick = 60.0;
		double ns = 1000000000 / tick;
		double d = 0;
		long timr = System.currentTimeMillis();
		int upd = 0;
		int frm = 0;
		WIDTH = getWidth();
		HEIGHT = getHeight();
		level();
		while(run){
			long now = System.nanoTime();
			d += (now - time) / ns;
			time = now;
			while(d >= 1){
				tick();
				collision();
				upd++;
				d--;
			}
			render();
			frm++;

			if(System.currentTimeMillis() - timr > 1000){
				timr += 1000;
				//System.out.println("FPS: " + frm + " TICKS: " + upd);
				frm = 0;
				upd = 0;
			}
		}
	}

//================================ON AJOUTE LE PERSO EN PREMIER DANS CREATELVL D'OU get(0)
	public void collision(){
		modele.collision2();
	}
	public void tick(){
		modele.tick();

		Pers tmp = (Pers) modele.objs.get(0);
		double posX = tmp.getX();
		double posY = tmp.getY();

		double vX = tmp.getVelX();
		double vY = tmp.getVelY();
		
		if(posX-vX ==5 && posY-vY == 5){ //OK pour le recadraage en cas de loose
			restart();

		}else{
			cadre.recadreBis(tmp);
		}
	}
	private void level(){
		modele.level(lvl);
	}


	public void text(float x, float y,Graphics g, String s, double dist){
		Color col=g.getColor();
		g.setColor(Color.WHITE);
	    Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

		double db = Math.abs(150-modele.objs.get(0).getX())/dist;
		float trans=(float)db;
		if(trans>1f)trans=1f;
		if(trans<0)trans=0;
	    Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f-trans);
	    g2.setComposite(c);
	    Font font = new Font("Verdana", Font.BOLD, 16);
	    FontRenderContext fontRenderContext = g2.getFontRenderContext();
	    GlyphVector glyphVector = font.createGlyphVector(fontRenderContext, s);
	    GlyphMetrics metrics = glyphVector.getGlyphMetrics(10);
	    //Glyphs are either STANDARD, LIGATURE, COMBINING, or COMPONENT.
	    boolean isStandard = metrics.isStandard();
	    float glyphAdvance = metrics.getAdvance();
	    g2.drawGlyphVector(glyphVector, x, y);

	    c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	    g2.setComposite(c);

	    g.setColor(col);
	}

	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;

		
		g.drawImage(Texture.getImg(299),(int)(cadre.getX())/10,(int)(cadre.getY())/10,null); //fonction de java awt qui permet de donner respectivement la longeur et la hauteur du canvas
		g2.translate(cadre.getX(), cadre.getY());

		modele.render(g);
		g2.translate(-cadre.getX(), -cadre.getY());

		g.dispose();
		bs.show();

	}


	public synchronized void start(){
		if(run)
			return;
		run=true;
		th=new Thread(this);
		th.start();
	}


	public void init(){
		Texture t=new Texture(null);
		t.load();
		// System.out.println("getwidth ==="+getWidth());
		// System.out.println("getheight ==="+getHeight());
		modele = new Mod();
		kl=new Controleur(modele);
		this.addKeyListener(kl);
		cadre = new Cadrage(0.0, 0.0, 0.0, 0.0);
		// modele.addObj(new Block(100, 100, IdObj.Block));
		Fen f = new Fen("Rag", 800, 600, this);

	}

	public void restart(){
		System.out.println("restart");
		cadre.setX(0);
		cadre.setY(0);
		modele.level(modele.lv);
		modele.objs.get(0).x+=1;
		modele.objs.get(0).y+=1;
		// modele.back(false);
	}
	public void cMap(){

	}



}
