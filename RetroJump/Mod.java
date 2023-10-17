import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.awt.geom.* ;
import java.awt.geom.Line2D.Double ;

class Mod{
	public LinkedList<Objet>objs = new LinkedList<Objet>();
	//Une case vaut a minima la largeur du perso
	boolean restart=false;
	int lv;
	double limX=2500,limY=1200;
	public Objet obj;
	public Mod(){
	}

	public boolean again(){return restart;}
	public void back(boolean b){restart=b;}

	public void tick(){
		if(objs.get(objs.size()-1) instanceof End)
			nextLvl();

		for(int i=0; i<objs.size();i++){
			obj=objs.get(i);
			obj.tick(objs); //objs pour verifier les collision par exmple
		}
		Pers p = (Pers) objs.get(0);
		if(p.warp(limX,limY)){
			p.die();
			restart=true;
		}
	}
	//On verifie les collision pour les objets dit "mouvants" c'est a dire les instance de IntAc
	// public void collision(){
	// 	IntAc mv;
	// 	for(int i=0; i<objs.size();i++){
	// 		obj=objs.get(i);
	// 		if(obj instanceof IntAc){
	// 			mv = (IntAc)obj;
	// 			mv.run_collision(objs);
	// 		}
	// 		// obj.collision(objs); //objs pour verifier les collision par exmple
	// 	}
	// }
	public void collision2(){

		for(int i = 0; i<objs.size(); i++){
			obj=objs.get(i);
			if(obj  instanceof IntAc){
				IntAc mv = (IntAc) obj;
				if(mv instanceof Pers){
					Pers po = (Pers) mv;
					po.collision(objs);
				}
				boolean t = false;
				mv.fall();
				for(int j = 0; j<objs.size(); j++){
					if(i != j){//On ne regarde pas les  collision entre un objet et lui meme
						Objet tmp;
						boolean rb=false;
						boolean rg=false;
						boolean rd=false;
						boolean rh=false;
						Line2D line;




						tmp = objs.get(j);

						if(tmp instanceof Bouncer){
							Bouncer bc = (Bouncer) tmp;
							boolean bc1 = bc.colli(objs, mv);
							if(bc1){
								mv.stand();
							}
						}else if(tmp instanceof Block){
							Block b1 = (Block) tmp;
							boolean bo1 = b1.colli(objs, mv);
							if(bo1){
								mv.stand();
							}
							
						}else if(tmp instanceof Laser){			

						
							Laser las = (Laser)tmp;
							las.colli(objs, mv);
						}else if(tmp instanceof Diagonal){

							Diagonal l = (Diagonal) tmp;
							boolean l1 = l.colli(objs, mv);
							if(l1){
								mv.stand();
							}
						}else if(tmp instanceof FireBall){
								// if(mv.getIdObj() == IdObj.Pers){
							FireBall f = (FireBall) tmp;
							f.colli(objs, mv);
								// }

						}else if(tmp instanceof Piege){
							if(mv instanceof Pers){
								Piege pie = (Piege) tmp;
								pie.colli(objs);
							}
						}else if(tmp instanceof WorldInFire){
							if(mv instanceof Pers){

								WorldInFire wif = (WorldInFire) tmp;
								wif.collision(objs);
							}
						}else if(tmp instanceof Portal){
							Portal por = (Portal) tmp;
							por.colli(objs, mv);
						}else if(tmp instanceof Ennemy){
							if(mv instanceof Pers){
								Ennemy enn = (Ennemy)tmp;
								enn.colli(objs);
							}
						}
						



					}

				}
			}


		}
		remv();

	}

	public void remv(){
		Objet o;
		for(int i = 0; i< objs.size(); i++){
			o = objs.get(i);
			if(o.remove){
				System.out.println("On supprime ca mamen");
				objs.remove(i);
			}
		}
	}
	public void nextLvl(){
		System.out.println("nextLvl");
		objs=new LinkedList<Objet>();
		lv++;
		level(lv);
	}

	public void level(int lvl){
		Levels niv= new Levels();
		lv=lvl;
		objs.clear();
		objs=niv.level(lvl);
	}
	public void render(Graphics g){
		for(int i=0; i<objs.size();i++){
			obj=objs.get(i);
			obj.render(g); //ICI G en argument car render sert a dessiner
		}

	}


}
