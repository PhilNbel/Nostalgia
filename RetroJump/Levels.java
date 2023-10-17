import java.util.LinkedList;
import java.util.Random;

class Levels{
	LinkedList<Objet> l;
	Levels(){
		l = new LinkedList<Objet>();
	}
	
	public LinkedList<Objet> level(int lvl){
		switch(lvl){
			case 0:
				lvl0();
				break;
			case 1:
				lvl1();
				break;
			case 2:
				lvl2();
				break;
			case 3:
				lvl3();
				break;
			case 4:
				lvl4bis();
				break;
			case 5:
				lvl5();
				break;
			case 6:
				lvl6();
				break;
			case 7:
				lvl7();
				break;
			case 8:
				lvl8();
				break;
			case 9:
				lvl9();
				break;
			case 10:
				lvl10();
				break;

			default :
				System.exit(0);
				break;
		}
		return l;
	}
	public void createLvl(){
		l.add(new Pers(40, 20));
		for(int i = 0; i< Jeu.WIDTH+32; i+=32){
			l.add(new Ground(i, Jeu.HEIGHT-32));

		}
		for(int y = 32*9; y<= Jeu.HEIGHT-32 ; y+=32){
			l.add(new Block(0, y, (byte)1));
		}

		for(int y = 256; y<= Jeu.HEIGHT-32 ; y+=32){

			for(int a = Jeu.WIDTH-(32*((y/32)+1)); a<=Jeu.WIDTH-32 ;a+=32){

				l.add(new Block(a, y, (byte)1));
			}
		}
		for(int x = 32; x<= 32*4; x+=32){
			int y=Jeu.HEIGHT-(32*6);
			l.add(new Block(x, y, (byte)1));
		}

		l.add(new Diagonal( 200 , 0, 600, 150));
		l.add(new Diagonal( 0 , 50, 150 , 150));
		l.add(new Diagonal(Jeu.WIDTH+450, 250, Jeu.WIDTH+650, 250));
		l.add(new Diagonal(Jeu.WIDTH, 250, Jeu.WIDTH+250, 250));
		l.add(new Diagonal(200, 200, 500, 100));
		int x = Jeu.WIDTH;
		int y = 300;
		for(int i = 0; i < 10; i++){
			l.add(new Diagonal(x, y, x+250, y));
			x+=300;
			y-=50;

		}
		y = Jeu.HEIGHT-32;
		Random ran = new Random();
		for(int nbCarreaux = 1; nbCarreaux < 100 ; nbCarreaux++){
			l.add(new Block(x, y, (byte)1));
			l.add(new Block(x+200, y, (byte)1));
			int xc = ran.nextInt(136)+x;
			l.add(new Diagonal(x+32, y, xc , y)); //OK
			// System.out.println("x =="+x+"xc =="+xc);
			y+=32;
		}

		for(int j = 0; j<100; j++){
			int x1 = ran.nextInt(50000) + 300;
			int y1 = ran.nextInt(Jeu.HEIGHT/2);
			int a1 = ran.nextInt(5) + 1;
			int a2 = ran.nextInt(3) + 1;
			l.add(new FireBall(x1, y1, a1, 0));
			l.add(new FireBall(1500, y1, 8, 0));
		}

		for(int j = 0; j<100; j++){
			int x1 = ran.nextInt(50000) + 300;
			int y1 = ran.nextInt(Jeu.HEIGHT);
			int a1 = ran.nextInt(5) + 1;
			int a2 = ran.nextInt(3) + 1;
			l.add(new FireBall(x1, y1, a1, 0));
			l.add(new FireBall(1500, y1, 8, 0));
		}
	}

	public void adHB(int xd, int yd, int nbCarreaux){
		for(int x = xd; x<=32*nbCarreaux+xd; x+= 32){
			l.add(new Block(x, yd,(byte)1));
		}
	}

	public void ground(int xd, int yd, int nbCarreaux){
		for(int x = xd; x<=32*nbCarreaux+xd; x+= 32){
			l.add(new Ground(x, yd));
		}
	}

	public void fground(int xd, int yd, int nbCarreaux){
		for(int x = xd; x<=32*nbCarreaux+xd; x+= 32){
			l.add(new FrznGr(x, yd));
		}
	}

	public void adVB(int xd, int yd, int nbCarreaux){
		for(int y = yd; y<=32*nbCarreaux+yd; y+= 32){
			l.add(new Block(xd, y, (byte)1));
		}
	}
	public void adVB2(int xd, int yd, int nbCarreaux){
		for(int y = yd; y<=32*nbCarreaux+yd; y+= 32){
			if(y == 32*nbCarreaux+yd -32 || y == 32*nbCarreaux+yd-64 || y == 32*nbCarreaux+yd-96){
				l.add(new Block(xd, y, (byte)3));
			}else{
				l.add(new Block(xd, y, (byte)2));
			}
		}
	}

	public void adVG(int xd, int yd, int nbCarreaux){
		l.add(new Ground(xd, yd));
		adVB(xd,yd+32,nbCarreaux-1);
	}

	public void reInitP(){
		for(int i = 0; i< l.size() ; i++){
			if(l.get(i) instanceof Piege){
				Piege ptmp = (Piege) (l.get(i));
				ptmp.reinitList();
			}
		}
	}
	public void reInitPos(){
		Objet k;
		for(int i = 0; i< l.size() ; i++){
			k = l.get(i);
			if(k instanceof IntAc){
				IntAc t = (IntAc) k;
				t.rotation = 0;
			}
		}
	}
	public void addPic(int x, int y, int limitex){
		Random ran = new Random();
		for(int i = x; i<=limitex; i+=12){
			Diagonal d5 = new Diagonal(i, y, i, y+150);
			Piege t3 = new Piege(i, y, d5);
			int r = ran.nextInt(10);
			if(r == 0){
				t3.addPiege(new Pique(i, y, 0, true, 0L));
			}else if(r == 1){
				t3.addPiege(new Pique(i, y, 10, true, 4L));
			}else if(r==2){
				t3.addPiege(new Pique(i, y, 15, false, 1L));
			}else if(r==3){
				t3.addPiege(new Pique(i, y, 10, true, 1L));
				t3.addPiege(new IBlock(i, y+120, -2, 0));

				// t3.addPiege(new Pique(i, y, 7, true, 2L, IdObj.Pique));
			}else if (r==4){
				t3.addPiege(new Pique(i, y, 0, true, 0L));
			}else{

			}
			l.add(t3);
		}

	}

	public void lvl0() {
		l.add(new Pers(40.0, 20.0));

		ground(0,300,10);
		adHB(320,300,25);
		ground(320,268,25);
		adVB(600,200,3);

		l.add(new Portal(1130,230,40,20,true));
	}

	public void lvl1() {
		l.add(new Pers(40.0, 20.0));

		ground(0,300,10);
		adHB(320,300,25);
		ground(320,268,25);
		adVB(600,200,3);
		l.add(new Bouncer(868,236));
		adVB(900,138,5);


		l.add(new Portal(1130,230,40,20,true));
	}

	public void lvl2() {
		l.add(new Pers(40, 20));

		ground(0, 32*10, 10);
		adHB(32*10, 32*10, 10);
		ground(32*10, 32*9, 10);
		ground(32*20, 32*14, 10);
		ground(32*30, 32*12, 10);
		ground(32*30, 32*6, 8);
		ground(32*40, 32*9, 3);
		ground(32*43, 32*6, 4); //Ici on pourrait mettre un Ennemy
		Ennemy e =new Ennemy(32*43, 32*6-42, 1, 32*4+20, 0, 4L, new Diagonal(32*43+10, 32*6-21, 32*53+10, 32*6-21), true);
		e.addWeapon(new Arme(32*43, 32*6-33, 20, 5, 250, 0, 5));
		l.add(e);
		adHB(32*30, 32*14, 10);
		adHB(32*30, 32*13, 10);

		l.add(new Portal(960,150,40,20,true));
	}

	public void lvl3(){
		l.add(new Pers(40.0, 20.0));

		adHB(32*5, 0, 25);
		adHB(32*5, -32, 25);
		adHB(0, -64, 30);
		ground(0, 32*5, 25);
		ground(32*5, 32*10, 25);
		ground(0, 32*15, 25);
		ground(32*5, 32*20, 25);
		ground(0, 32*25, 30);
		adVB2(32*31, -32*3, 28);
		adVB2(32*32, -32*3, 28);

		Diagonal detect = new Diagonal(32*5, 50, 32*5, 150);
		Piege blocInvisble = new Piege(32*2, 50, detect);
		blocInvisble.addPiege(new Laser(-100.0, 70.0, 7.0, 0.0, false, 1L)); // Si transparent regarder la velocite pour la direction du laser
		blocInvisble.addPiege(new Laser(-100.0, 90.0, 7.0, 0.0, false, 3L));//non invisible
		blocInvisble.addPiege(new Laser(800.0, 80.0, -7.0, 0.0, false,  1L)); //invisible
		blocInvisble.addPiege(new Laser(800.0, 130, -7.0, 0.0, false, 3L));
		blocInvisble.addPiege(new Laser(800.0, 125, -25.0, 0.0, false, 6L));
		blocInvisble.addPiege(new Laser(-100.0, 100, 25.0, 0.0, false, 6L));
		blocInvisble.addPiege(new Laser(800.0, 140, -25.0, 0.0, false, 8L));
		blocInvisble.addPiege(new Laser(-100.0, 95, 25.0, 0.0, false, 8L));
		l.add(blocInvisble);
		Diagonal d = new Diagonal(32*4, -32, 32*5, -32);
		Piege bI = new Piege(32*4, 0, d);
		bI.addPiege(new Laser(32*4, 0, 0, 7, false, 2L));
		l.add(bI);
		l.add(new Diagonal(100, -120, 150, -150));
		Diagonal d3 = new Diagonal(32*7, 150, 32*17, 50);
		Piege test = new Piege(32*7, 150, d3);
		test.addPiege(new Laser(32*7, 150, 7, 7*d3.p, false, 1L));
		l.add(test);
		Diagonal d4 = new Diagonal(0.0, 32*9, 32*26, 32*9);
		Piege t2 = new Piege(0.0, 32*9, d4);
		t2.addPiege(new MurPiquant(0, 32*6, d4, 25,0, false, 0L));
		l.add(t2);
		/*addPique(32*5, 32*10+10, 32*24);*/
		Diagonal d6 = new Diagonal(32*8+10, (32*19)+5, 32*15, (32*19)+5);
		Ennemy e =new Ennemy(32*8, 32*19 - 13, 2, 500.0, 0, 2L, d6, true);
		Arme e1 = new Arme(32*8, 32*19, 19, 5, 250, 0, 5);
		e.addWeapon(e1);
		l.add(e);
		// arr_to_string();
		l.add(new Portal(0,-100,40,20,true));
	}

	public void lvl4() {
		l.add(new Pers(40.0, 20.0));

		l.add(new Diagonal(0, 500, 700, 0));
		l.add(new Diagonal(700, 0, 750, 0));
		l.add(new Diagonal(750, 0, 1450, 500));
		l.add(new Laser(0, 350, 10, 0, true, 0L));
		l.add(new Laser(1800, 450, -10, 0, true, 0L));
		adVB(32*5, 500-35*5, 5);
		l.add(new Portal(1650,500,40,20,true));
	}
	public void lvl4bis() {
		l.add(new Pers(40.0, 20.0));
		adHB(0, 500, 20);
		adVB(32*8, 500-32*5, 5);
		l.add(new Laser(3000, 350, -10, 0, true, 0L));
		l.add(new Laser(3000, 300, -10, 0, true, 0L));
		l.add(new Laser(-2800, 300, 10, 0, true, 5L));
		l.add(new Portal(21*32,500,40,20,true));
	}

	public void lvl5() {
		l.add(new Pers(40.0, 20.0));

		ground(0,32*10,10);
		l.add(new Portal(32*10,32*9,32*54,32*7)); //P1

		ground(32*50, 32*10, 10); //1
		l.add(new Portal(32*50,32*9,32*59,32*14)); //P2
		l.add(new Portal(32*60,32*9,32*55,32*19)); //P3

		ground(32*50, 32*15, 10); //2
		l.add(new Portal(32*50,32*14,32*59,32*24)); //P4
		l.add(new Portal(32*60,32*14,32*0,32*9)); //P5

		ground(32*50, 32*20, 10); //3
		l.add(new Portal(32*50,32*19,32*50,32*0)); //PM
		l.add(new Portal(32*60,32*19,32*55,32*4)); //P0

		ground(32*50, 32*25, 10); //4
		Ennemy e =new Ennemy(32*51, 32*25-42, 0, 5, 0, 4L, new Diagonal(32*51+10, 32*25-21, 32*56+10, 32*25-21), true);
		e.addWeapon(new Arme(32*52, 32*25-33, 20, 5, 5, 0, 5));
		l.add(e);
		l.add(new Portal(32*50,32*24,32*55,32*19)); //P3

		ground(32*49, 32*5, 12); //0
		adVB(32*49, 32*6, 19);
		adVB(32*61, 32*6, 19);

		ground(32*45, 32*1, 20);
		l.add(new Portal(32*65,32*0,40,20,true)); //PF
	}

	public void lvl6() { //Labyrinthe arbre
		l.add(new Pers(40.0, 20.0));
		l.add(new WorldInFire(5));
		ground(0,32*12,9); //1
		ground(32*12,10*32,7); //2
		ground(32*12,14*32,7); //3
		ground(32*22,8*32,7); //4
		ground(32*22,12*32,7); //5
		ground(32*22,16*32,7); //6
		ground(32*32,6*32,7); //7
		ground(32*32,10*32,7); //8
		ground(32*32,14*32,7); //9
		ground(32*32,18*32,7); //10
		fground(32*42,4*32,7); //11
		fground(32*42,8*32,7); //12
		fground(32*42,12*32,7); //13
 		fground(32*42,16*32,7); //14
		fground(32*42,20*32,7); //15
		fground(32*52,4*32,7); //11
		fground(32*52,8*32,7); //12
		fground(32*52,12*32,7); //13
 		fground(32*52,16*32,7); //14
		fground(32*52,20*32,7); //15
		fground(32*62,4*32,7); //11
		fground(32*62,8*32,7); //12
		fground(32*62,12*32,7); //13
 		fground(32*62,16*32,7); //14
		fground(32*62,20*32,7); //15
		l.add(new Portal(32*69,3*32,5,5)); //11
		l.add(new Portal(32*69,7*32,5,5)); //12
		l.add(new Portal(32*69,11*32,5,5)); //13
		l.add(new Portal(32*69,15*32,5,5,true)); //14
		l.add(new Portal(32*69,19*32,5,5)); //15
	}

	public void lvl7() { //Bouncer Obstacles
		l.add(new Pers(40.0, 20.0));

		ground(32*0,32*-3,51);
		adHB(32*0,32*3,51);
		Diagonal d = new Diagonal(0, 20, 80, 20);
		Piege t = new Piege(0, -3*32, d);
		for (int i = 16; i<=32*50; i += 16) {
			t.addPiege(new Pique(i, (32*-2)-16, 0, true, 0L));
		}
		l.add(t);
		ground(0,32*2,5);
		l.add(new Bouncer(32*6, 32*2));
		l.add(new Bouncer(32*7, 32*2));
		l.add(new Bouncer(32*8, 32*2));
		l.add(new Bouncer(32*9, 32*2));
		l.add(new Bouncer(32*10, 32*2));
		ground(32*11,32*2,10);
		l.add(new Bouncer(32*21, 32*2));
		l.add(new Bouncer(32*22, 32*2));
		l.add(new Bouncer(32*23, 32*2));
		l.add(new Bouncer(32*24, 32*2));
		l.add(new Bouncer(32*25, 32*2));
		ground(32*26,32*2,10);
		l.add(new Bouncer(32*36, 32*2));
		l.add(new Bouncer(32*37, 32*2));
		l.add(new Bouncer(32*38, 32*2));
		l.add(new Bouncer(32*39, 32*2));
		l.add(new Bouncer(32*40, 32*2));
		ground(32*41,32*2,10);

		l.add(new Portal(32*50,32*1,40,20,true));
	}

	public void lvl8() { //Murs PiquantsGogo
		l.add(new Pers(40.0, 20.0));
		l.add(new Portal(32*0,32*5,32*50,32*0));
		fground(32*40,32*10,20);
		adHB(32*40,32*11,20);
		l.add(new Bouncer(32*44, 32*9));
		l.add(new Bouncer(32*45, 32*9));
		l.add(new Bouncer(32*46, 32*9));
		l.add(new Bouncer(32*47, 32*9));
		l.add(new Bouncer(32*48, 32*9));
		Diagonal d2 = new Diagonal(32*51+16, 32*10-16, 32*81+16, 32*10-16);
		Piege t2 = new Piege(32*51,32*6, d2);
		t2.addPiege(new MurPiquant(32*81, 32*6, d2, 25,0, false, 0L));
		t2.addPiege(new MurPiquant(32*81, 32*6, d2, 25,0, false, 5L));
		l.add(t2);
		l.add(new Portal(32*60,32*9,40,20,true));
	}

	public void lvl9() { //
		l.add(new Pers(40.0, 20.0));
		fground(0,32*2,50);
		l.add(new Portal(32*50,32*1,40,20,true));
	}

	public void lvl10() { // Niveau final avec boules de feu latrales
		l.add(new Pers(40.0, 20.0));
		l.add(new WorldInFire(5));
		adHB(32*0,32*10,25);
	}

	public void lvl11() { //Ecran de fin de jeu
		//-Bah du coup y'a pas besoin d'un niveau pour \E7a, si?

	  l.add(new Pers(40.0,20.0));
		fground(0,32*2,50);

	}

	public void lvlSec() { //Un niveau bonus secret
		l.add(new Pers(40.0,20.0));
		ground(0,300,10);
 	 	adHB(0,332,10);
		l.add(new Portal(50,100,40,20,true));
	}

	
}
