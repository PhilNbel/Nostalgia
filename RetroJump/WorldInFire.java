
import java.util.LinkedList;
import java.awt.*;
import java.util.Random;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;
public class WorldInFire extends Objet{
	double nbbytick;
	long tmpT;
	LinkedList<FireBall> balls = new LinkedList<FireBall>();
	long decompte;
	public WorldInFire(double nbbytick){
		super(0,0);
		this.nbbytick = nbbytick;
		this.tmpT = 0L;
		this.decompte = 5L;
	}

	public void tick(LinkedList<Objet> obj){

		if(this.tmpT == 0l){
			tmpT = System.currentTimeMillis();
		}else{
			Pers p =(Pers)obj.get(0);
			long t = System.currentTimeMillis();
			//0 ca vient de la gauche
			//1 ca vient du haut
			//2 droite
			Random random = new Random();
			double midleX = p.getX();
			double midleY = p.getY();
			double back = p.backX;
			double top = p.topY;
			if((t - (tmpT+(this.decompte*500))) >= 0L) {
				for(int i = 0; i< this.nbbytick; i++){
					int from = random.nextInt(3);
					double poseX = 0;
					double poseY = 0;
					double vX = 0;
					double vY = 0;
					double angle = 0;
					// System.out.println("from = "+from);
					if(from == 0){
						// double vfx = (Math.random()*((1-0.75)+1))+0.75;
						poseX = back-random.nextInt(50);
						poseY = midleY-(random.nextDouble()*(midleY-top));
						vX =  random.nextDouble()*10;
						if(vX< 4){
							vX += 4;
						}
						angle =  Math.random()*2;
						vY = angle;


						balls.add(new FireBall(poseX, poseY, vX, vY));
					}else if(from == 2){

						poseX = back + Jeu.WIDTH+ random.nextInt(50);

						poseY = midleY-(random.nextDouble()*(midleY-top+100));
						vX =  random.nextDouble()*10;
						if(vX< 4){
							vX += 4;
						}
						angle =  random.nextDouble()*2;
						vY = angle;


						balls.add(new FireBall(poseX, poseY, -vX, vY));

					}else{
						if(from == 1){
							poseX = back+ random.nextInt(Jeu.WIDTH);
							poseY = top;
							angle =  Math.random()*2;
							if(poseX > midleX){
								vX = -angle;
							}else if(poseX == midleX){
								vX = 0;
							}else{
								vX = angle;
							}
							vY = (Math.random() * 10);
							if(vY < 4){
								vY += 4;
							}
							balls.add(new FireBall(poseX, poseY, vX, vY));
						}

					}




				}
				this.tmpT = 0L;
			}
		}
		tickBalls(obj);
	}
	public void colliHache(Hache h){
		for(int i =0 ; i<balls.size() ; i++){
			FireBall f = balls.get(i);
			if(f.getBoundsEllipse().intersects(h.x, h.y, h.wdt, h.hgt)){
				f.remove();
			}
		}
		verifBall();
	}
	public void tickBalls(LinkedList<Objet> obj){
		for(int i = 0; i<balls.size(); i++){
			FireBall f = balls.get(i);
			f.tick(obj);
		}
	}

	public void collision(LinkedList<Objet> obj){
		Pers p = (Pers) obj.get(0);
		Rectangle lft = p.getBoundsLeft();
		Rectangle rgt = p.getBoundsRight();
		Rectangle bnd = p.getBounds();
		Rectangle top = p.getBoundsTop();
		for(int i = 0; i<balls.size(); i++){
			FireBall f = balls.get(i);
			if(f.getBoundsEllipse().intersects(lft.getX(), lft.getY(), lft.getWidth(), lft.getHeight())){
				p.setX(5);
				p.setY(5);
				break;
			}
			if(f.getBoundsEllipse().intersects(rgt.getX(), rgt.getY(), rgt.getWidth(), rgt.getHeight())){
				p.setX(5);
				p.setY(5);
				break;
			}
			if(f.getBoundsEllipse().intersects(bnd.getX(), bnd.getY(), bnd.getWidth(), bnd.getHeight())){
				p.setX(5);
				p.setY(5);
				break;
			}
			if(f.getBoundsEllipse().intersects(top.getX(), top.getY(), top.getWidth(), top.getHeight())){
				p.setX(5);
				p.setY(5);
				break;
			}
			for(int j = 1; j<obj.size(); j++){
				obj.get(j).colli(obj, (IntAc)f);
			}

		}
		verifBall();
	}

	public void verifBall(){
		for(int i = 0; i< balls.size(); i++){
			if(balls.get(i).remove){
				balls.remove(i);
			}
		}
	}

	public void render(Graphics g){
		for(int i =0 ; i<balls.size() ; i++){
			FireBall f = balls.get(i);
			f.render(g);
		}
	}
	//Rectangle exetends Rectangle2D :)
	public Rectangle getBounds(){
		return (Rectangle) null;
	}



}
