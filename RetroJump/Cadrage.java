public class Cadrage{
	double x, y, vx, vy;
	boolean reX =false, reY=false;
	public Cadrage(double x, double y, double velx, double vely){
		this.x=x;
		this.y=y;
		this.vx=velx;
		this.vy=vely;
	}
	
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	
	public void recadre(double pose){
		if(pose > ((Jeu.WIDTH*7)/10)-x){
			x-=Phys.maxSpeedX;
		}
	}
	public void recadre(double poseX, double poseY, double vX, double vY){
		/*if(poseX-vX == Phys.maxSpeedX && poseY-vY == Phys.maxSpeedY){
			x = 0;
			y = 0;
		}*/
		if(poseX == 5 && poseY == 5){
			x = 0;
			y = 0;
		}
		if(poseX > (Math.abs(x)+(Jeu.WIDTH/2)) ){
			x -= Phys.maxSpeedX;
		}
		if(vY == 0){
			vY = Phys.maxSpeedY;
		}
		if(poseX > ((Jeu.WIDTH*6)/10)-x){
			x-=vX;
		}else{
			if(this.x !=  0){
				if(poseX < ((Jeu.WIDTH*2)/10)-x){
					x -= vX;
				}
			}
		}
		if(poseY > ((Jeu.HEIGHT*7)/10)-y ){
			y-=vY;
		}else{
			if(poseY < ((Jeu.HEIGHT*3)/10)-y){
				y+= Math.abs(vY);
			}
		}
		if(x > 0){
			x = 0;
		}

	}

	public void recadreBis(Pers tmp){
		double poseX = tmp.getX();
		double poseY = tmp.getY();

		// double vX = tmp.getVelX();
		// double vY = tmp.getVelY();
		if(reX ){
			if(poseX < ((Jeu.WIDTH*6)/10)-x && poseX > ((Jeu.WIDTH*4)/10)-x)
				reX=false;
			else if(poseX >((Jeu.WIDTH*6)/10)-x)
				x-=Phys.maxSpeedX;
			else if(poseX < ((Jeu.WIDTH*4)/10)-x)
				x+=Phys.maxSpeedX;
			
		}else
			if(poseX > ((Jeu.WIDTH*8)/10)-x)
				reX=true;
			else
				if(this.x !=  0)
					if(poseX < ((Jeu.WIDTH*2)/10)-x)
						reX=true;
		
		if(reY){
			if(poseY < ((Jeu.HEIGHT*6)/10)-y && poseY > ((Jeu.HEIGHT*4)/10)-y)
				reY=false;
			
				if(poseY > ((Jeu.HEIGHT*6)/10)-y)
					y-=Phys.maxSpeedY;
				
				
				else if(poseY < ((Jeu.HEIGHT*4)/10)-y)
					y+=Phys.maxSpeedY;
				
		
			}if(poseY > ((Jeu.HEIGHT*8)/10)-y)
				reY=true;
			else
				if(this.y !=  0)
					if(poseY < ((Jeu.HEIGHT*2)/10)-y)
						reY=true;
		
	
		
		if(x > 0){
			x = 0;
		}
		tmp.backX = Math.abs(x);
		tmp.topY = y;

	}
		
}