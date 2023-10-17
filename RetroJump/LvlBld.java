import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

class LvlBld{//Level Builder
	LinkedList<Objet> lvl;
    LookAhead la;

    public LvlBld(String s) throws IOException{
		lvl= new LinkedList<Objet>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(s)));
		Lexer lexer= new Lexer(reader);
		la=new LookAhead(lexer);
    }
    
    //////////////////////////////////////Fonctions///////////////////////////////////////////
    

	public void adHB(double xd, double yd, int nbCarreaux){
		for(int x = (int)xd; x<=32*nbCarreaux+xd; x+= 32){
			lvl.add(new Block(x, yd,(byte)1));
		}
	}
 
	public void ground(double xd, double yd, int nbCarreaux){
		for(int x = (int)xd; x<=32*nbCarreaux+xd; x+= 32){
			lvl.add(new Ground(x, yd));
		}
	}

	public void fground(double xd, double yd, int nbCarreaux){
		for(int x = (int)xd; x<=32*nbCarreaux+xd; x+= 32){
			lvl.add(new FrznGr(x, yd));
		}
	}

	public void adVB(double xd, double yd, int nbCarreaux){
		for(int y = (int)yd; y<=32*nbCarreaux+yd; y+= 32){
			lvl.add(new Block(xd, y,(byte)1));
		}
	}
	public void adVB2(double xd, double yd, int nbCarreaux){
		for(int y = (int)yd; y<=32*nbCarreaux+yd; y+= 32){
			if(y == 32*nbCarreaux+yd -32 || y == 32*nbCarreaux+yd-64 || y == 32*nbCarreaux+yd-96){
				lvl.add(new Block(xd, y, (byte)0));
			}else{
				lvl.add(new Block(xd, y, (byte)2));
			}
		}
	}

	public void adVG(double xd, double yd, int nbCarreaux){
		lvl.add(new Ground(xd, yd));
		adVB(xd,yd+32,nbCarreaux-1);
	}

	public void reInitP(){
		for(int i = 0; i< lvl.size() ; i++){
			if(lvl.get(i) instanceof Piege){
				Piege ptmp = (Piege) (lvl.get(i));
				ptmp.reinitList();
			}
		}
	}
	public void reInitPos(){
		Objet k;
		for(int i = 0; i< lvl.size() ; i++){
			k = lvl.get(i);
			if(k instanceof IntAc){
				IntAc t = (IntAc) k;
				t.rotation = 0;
			}
		}
	}
	public void addPic(int x, int y, int limitex){
		Random ran = new Random();
		for(int i = x; i<=limitex; i+=12){
			Diagonal d5 = new Diagonal(i, y, i, y+150	);
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

				// t3.addPiege(new Pique(i, y, 7, true, 2L));
			}else if (r==4){
				t3.addPiege(new Pique(i, y, 0, true, 0L));
			}else{

			}
			lvl.add(t3);
		}

	}

    /////////////////////////////////////////Parser///////////////////////////////////////////
    public boolean parse(){
    	try{
	    	boolean b=false;
	    	double[] tab,tab0;
	    	double[][] tab1;
	    	double d0,d1;
	    	long l0,l1;
	    	int i0,i1;
	    	boolean b0,b1;
			Diagonal d;
			LonToken lt;
			BoolToken bt;
			Ennemy e;
	    	if(!la.check(Sym.PER))
	    		return false;
	    	la.eat(Sym.PER);
	    	
	    	
	    	tab0=db(2);

	    	if(tab0==null)
	    		return false;

	    	lvl.add(new Pers(tab0[0], tab0[1]));

	    	

	    	while(!la.isNull()){

		    	tab=null;
		    	tab0=null;
		    	tab1=null;
		    	l0=0;
		    	l1=0;
		    	i0=0;
		    	i1=0;
		    	b0=false;
		    	b1=false;
		    	d=null;
		    	lt=null;
		    	bt=null;
		    	e=null;

	    		switch(la.see()){
	    			case ARM: 
	    			la.eat(Sym.ARM);
	    			tab = db(7);
					i0=getInt();
					if(!(lvl.get(i0)instanceof Ennemy))
						return false;
					e=(Ennemy)lvl.get(i0);
					e.addWeapon(new Arme(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6]));
	    			break;

	    			case BC :
	    				la.eat(Sym.BC);
				    	
				    	tab1 = db(2,1);
				    	if(tab1==null)
				    		return false;

				    	if(tab1[1]!=null)
					    	lvl.add(new Block(tab1[0][0], tab1[0][1], (byte)1, tab1[1][0]));
				    	else
							lvl.add(new Block(tab1[0][0], tab1[0][1], (byte)1));
	    				break;
	    			
	    			case BNC :
	    				la.eat(Sym.BNC);
				    	
				    	tab1 = db(2,1);
				    	if(tab1==null)
				    		return false;

				    	if(tab1[1]!=null)
					    	lvl.add(new Bouncer(tab1[0][0], tab1[0][1], tab1[1][0]));
				    	else
							lvl.add(new Bouncer(tab1[0][0], tab1[0][1]));
	    				break;
	    			
	    			case DIA :
	    				d=diag();
	    				System.out.println(d.getX()+" "+d.getY()+" "+d.getX2()+" "+d.getY2());
	    				if(d==null)
	    					return false;
	    				lvl.add(d);
	    				break;
	    			
	    			case ENN :
	    				la.eat(Sym.ENN);
	    				tab0=db(4);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();

			   			if(!la.check(Sym.VIR))
   				    		return false;
   	    				la.eat(Sym.VIR);

						if(!la.check(Sym.LON)){
							if(!la.check(Sym.NUM) && !!la.check(Sym.MOS))
									return false;
							l0=getLong();
						}else{
						   	la.eat(Sym.LON);		   	
			   		    }

			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

	    				d=diag();

					   	if(!la.check(Sym.VIR))
   				    		return false;
   	    				la.eat(Sym.VIR);

						if(!la.check(Sym.BOO))
							return false;
						bt=(BoolToken)la.curr();
						b0=bt.getB();
					   	la.eat(Sym.BOO);
						lvl.add(new Ennemy(tab0[0],tab0[1],tab0[2],tab0[3],i0,l0, d, b0));
	    				break;

	    			case ERROR : return b;
	    			
	    			case FGD :la.eat(Sym.FGD);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
					
						lvl.add(new FrznGr(tab0[0],tab0[1]));
	    				break;
	    			
	    			case FGR :la.eat(Sym.FGR);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();

			   	    	fground(tab0[0],tab0[1],i0);

	    				break;
	    			
	    			case FIB :la.eat(Sym.FIB);
	    				tab0=db(4);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	lvl.add(new FireBall(tab0[0],tab0[1],tab0[2],tab0[3]));
	    				break;
	    			
	    			case FLE :la.eat(Sym.FLE);
	    				tab0=db(6);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	lvl.add(new Fleche(tab0[0],tab0[1],tab0[2],tab0[3],tab0[4],tab0[5]));

	    				break;
	    			
	    			case GD :la.eat(Sym.GD);
	    				tab1=db(2,1);
				    	if(tab1[0]==null)
				    		return false;
			   		    if(tab1[1]==null)
			   		    	lvl.add(new Ground(tab1[0][0],tab1[0][1]));
			   		    else
			   	    	lvl.add(new Ground(tab1[0][0],tab1[0][1],tab1[1][0]));

	    				break;
	    			
	    			case GR :la.eat(Sym.GR);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();

			   	    	ground(tab0[0],tab0[1],i0);
	    				break;
	    			
	    			case HB :la.eat(Sym.HB);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();

			   	    	adHB(tab0[0],tab0[1],i0);

	    				break;
	    			
	    			case IBC :la.eat(Sym.IBC);
	    				break;
	    			
	    			case LAS :la.eat(Sym.LAS);
	    				break;
	    			
	    			case MPC :la.eat(Sym.MPC);
	    				break;
	    			
	    			case PER : return b;
	    				    			
	    			case PIC :la.eat(Sym.PIC);
	    				break;
	    			
	    			case PIG :
	    				la.eat(Sym.PIG);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;

			   			if(!la.check(Sym.VIR))
   				    		return false;
   	    				la.eat(Sym.VIR);

	    				d=diag();
	    				lvl.add(new Piege(tab0[0],tab0[1],d));
	    				break;
	    			
	    			case PRT :
	    				la.eat(Sym.PRT);
		    			tab0=db(4);
				    	if(tab0==null)
				    		return false;

			 	    	if(!la.check(Sym.VIR)){
			 	    		b=true;
			 	    		lvl.add(new Portal(tab0[0], tab0[1], tab0[2], tab0[3]));
			 	    	}else{
			 	    		la.eat(Sym.VIR);

			 	    		if(la.check(Sym.BOO)){
								bt=(BoolToken)la.curr();
								b0=bt.getB();
						   		la.eat(Sym.BOO);
						   		if(!la.check(Sym.VIR))
					   	    		lvl.add(new Portal(tab0[0], tab0[1], tab0[2], tab0[3],b0));
					   	    	else{
					   	    		la.eat(Sym.VIR);

					   	    		tab=db(2);
							    	if(tab==null)
							    		return false;

			 	    				lvl.add(new Portal(tab0[0], tab0[1], tab0[2], tab0[3], b0, tab[0], tab[1]));
			 	    			}

			 	    		}else{
			 	    			tab=db(2);
			 	    			lvl.add(new Portal(tab0[0], tab0[1], tab0[2], tab0[3], tab[0], tab[1]));
			 	    		}

	    				}
	    				b=true;
	    				break;
	    			
	    			case VBD :la.eat(Sym.VBD);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();
			   	    	adVB2(tab0[0],tab0[1],i0);

	    				break;
	    			
	    			case VBO :la.eat(Sym.VBO);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);
						
						i0=getInt();
			   	    	adVB(tab0[0],tab0[1],i0);

	    				break;
	    			
	    			case VG :la.eat(Sym.VG);
	    				tab0=db(2);
				    	if(tab0==null)
				    		return false;
			   		    	
			   	    	if(!la.check(Sym.VIR))
			   	    		return false;
			   	    	la.eat(Sym.VIR);

						
						i0=getInt();
			   	    	adVG(tab0[0],tab0[1],i0);

	    				break;
	    			
	    			case WIF :la.eat(Sym.WIF);
	    				d0=getDb();
	    				lvl.add(new WorldInFire(d0));
	    				break;
	    			
	    			default : 
	    			if(!la.isNull()){
	    				System.out.println(la.see());
	    				la.eat(la.see());}
	    			else return b;
	    			break;
	    		}
	    	}
	    	return b;
	    } catch(Exception e){ return false;}
    }

    private double getDb(){
    	boolean bo=false,ti=false;
    	double res=0, d0=0, d1=0;
    	ArrayList<Double> d= new ArrayList<>();
    	ArrayList<Integer> b =new ArrayList<>();
		try{
			if(la.check(Sym.MOS)){
	    			bo=true;
					la.eat(Sym.MOS);
				}
			NumToken nt=(NumToken)la.curr();
	    	if(nt.p)
	    		d0 = nt.getD();
	    	else
	    		d0 = (double)nt.getI();
	    	la.eat(Sym.NUM);

	    	if(!(la.check(Sym.MOS)||la.check(Sym.PLU)||la.check(Sym.TIM)))
	    		if(bo)
	    			return 0-d0;
	    		else
	    			return d0;

	    		d.add(d0);
	    	while( la.check(Sym.MOS)||la.check(Sym.PLU)||la.check(Sym.TIM) ){
	    		if(la.check(Sym.MOS)){
	    			b.add(0);
					la.eat(Sym.MOS);
				} else
	    		if(la.check(Sym.PLU)){
	    			b.add(1);
					la.eat(Sym.PLU);

		    		} else{
		    		b.add(2);
					la.eat(Sym.TIM);
				}
				
				if(!la.check(Sym.NUM))
					return 0;
				nt=(NumToken)la.curr();
		    	if(nt.p)
		    		d0 = nt.getD();
		    	else
		    		d0 = (double)nt.getI();

	    		d.add(d0);
	    		la.eat(Sym.NUM);

	    	}

	    	res=d.get(0);
	    	if(bo)
	    		res=0-res;

	    	for(int k=b.size();k>0;k--){
	    		if(b.get(k-1)==2){
	    			if(ti)
	    				d1=d1*d.get(k);
	    			else
	    				d1=d.get(k);
	    			ti=true;
	    		}else
	    			if(b.get(k-1)==2){
	    			    if(ti) res+=d1*d.get(k);
	    				else res+=d.get(k);
	    				ti=false;
	    			}else{
	    			    if(ti) res-=d1*d.get(k);
	    				else res-=d.get(k);
	    				ti=false;
	    			}
	    	}
	    	if(ti) res=res*d1;
	    	return res;	
		}catch(Exception e){return 0;}
    }

    private int getInt(){
    	boolean bo=false,ti=false;
    	int res=0, i0=0, i1=0;
    	ArrayList<Integer> i= new ArrayList<>();
    	ArrayList<Integer> b =new ArrayList<>();
		try{
		if(la.check(Sym.MOS)){
    			bo=true;
				la.eat(Sym.MOS);
			}

		NumToken nt=(NumToken)la.curr();
    	if(!nt.p)
    		i0 = nt.getI();
    	else
    		i0 = (int)nt.getD();
    	la.eat(Sym.NUM);

    	if(!(la.check(Sym.MOS)||la.check(Sym.PLU)||la.check(Sym.TIM)))
    		if(bo)
    			return 0-i0;
    		else
    		return i0;

    	while( la.check(Sym.MOS)||la.check(Sym.PLU)||la.check(Sym.TIM) ){
    		i.add(i0);
    		if(la.check(Sym.MOS)){
    			b.add(0);
				la.eat(Sym.MOS);
			} else
    		if(la.check(Sym.PLU)){
    			b.add(1);
				la.eat(Sym.PLU);

	    		} else{
	    		b.add(2);
				la.eat(Sym.TIM);
			}
			
			if(!la.check(Sym.NUM))
				return 0;
			nt=(NumToken)la.curr();
	    	if(!nt.p)
	    		i0 = nt.getI();
	    	else
	    		i0 = (int)nt.getD();

    		i.add(i0);
	    	la.eat(Sym.NUM);


    	}

    	res=i.get(0);
    	if(bo)
    		res=0-res;

    	for(int k=b.size();k>0;k--){
    		if(b.get(k-1)==2){
    			if(ti)
    				i1=i1*i.get(k);
    			else
    				i1=i.get(k);
    			ti=true;
    		}else
    			if(b.get(k-1)==2){
    			    if(ti) res+=i1*i.get(k);
    				else res+=i.get(k);
    				ti=false;
    			}else{
    			    if(ti) res-=i1*i.get(k);
    				else res-=i.get(k);
    				ti=false;
    			}
    	}
    	return res;

		}catch(Exception e){return 0;}
    }

    private long getLong(){
    	boolean bo=false,ti=false;
    	double res=0, d0=0, d1=0;
    	long fin=0L;
    	ArrayList<Double> d= new ArrayList<>();
    	ArrayList<Integer> b =new ArrayList<>();
    	LonToken lt=null;
		try{
		if(la.check(Sym.MOS)){
    			bo=true;
				la.eat(Sym.MOS);
			}
		NumToken nt=(NumToken)la.curr();
    	if(nt.p)
    		d0 = nt.getD();
    	else
    		d0 = (double)nt.getI();
    	la.eat(Sym.NUM);

    	while( la.check(Sym.MOS)||la.check(Sym.PLU)||la.check(Sym.TIM) ){
    		d.add(d0);
    		if(la.check(Sym.MOS)){
    			b.add(0);
				la.eat(Sym.MOS);
			} else
    		if(la.check(Sym.PLU)){
    			b.add(1);
				la.eat(Sym.PLU);

	    		} else{
	    		b.add(2);
				la.eat(Sym.TIM);
			}
			
			if(!la.check(Sym.NUM))
				if(la.check(Sym.LON)){
					lt=(LonToken)la.curr();
					fin=lt.getL();
					break;
				}
				else
					return 0;
				
			nt=(NumToken)la.curr();
	    	if(nt.p)
	    		d0 = nt.getD();
	    	else
	    		d0 = (double)nt.getI();

    		d.add(d0);
	    	la.eat(Sym.NUM);


    	}

    	res=d.get(0);
    	if(bo)
    		res=0-res;

    	for(int k=b.size();k>0;k--){
    		if(b.get(k-1)==2){
    			if(ti)
    				d1=d1*d.get(k);
    			else
    				d1=d.get(k);
    			ti=true;
    		}else
    			if(b.get(k-1)==2){
    			    if(ti) res+=d1*d.get(k);
    				else res+=d.get(k);
    				ti=false;
    			}else{
    			    if(ti) res-=d1*d.get(k);
    				else res-=d.get(k);
    				ti=false;
    			}
    	}
    	if(lt==null)
    		return 0;
    	return (long)res*fin;
    	}catch(Exception e){return 0L;}
    }

    private Diagonal diag(){
    	try{
		la.eat(Sym.DIA);
		}catch(Exception e){return null;}
		    	
	    double [] d=db(4);
	    	
	    if(d==null)
	    	return null;

		return new Diagonal(d[0],d[1],d[2],d[3]);
    }

    private double[]db(int i){
    	try{
	    	double [] tab=new double[i];
	    	for(int j=0;j<i-1;j++){
	        	if(!la.check(Sym.NUM) && !la.check(Sym.MOS))
	    	   		return null;
	    			
	   			tab[j]=getDb();
	   		    	
	   	    	if(!la.check(Sym.VIR))
	   	    		return null;
	   	    	la.eat(Sym.VIR);
	   	    }

	        	if(!la.check(Sym.NUM) && !la.check(Sym.MOS))
	    		return null;
	    	tab[i-1]=getDb();

			for( double d : tab)
				System.out.println(d);
			return tab;
	}catch(Exception e){return null;}
    }

    private double[][] db(int i, int n){
    	try{
    	double [][] tab=new double[2][];
    	tab[0]=new double[i];
    	for(int j=0;j<i-1;j++){
        	
	        	if(!la.check(Sym.NUM) && !!la.check(Sym.MOS))
    	   		return null;
    			
   			tab[0][j]=getDb();

   		    	
   	    	if(!la.check(Sym.VIR))
   	    		return null;
   	    	la.eat(Sym.VIR);
   	    }

        
	        	if(!la.check(Sym.NUM) && !!la.check(Sym.MOS))
    		return null;
    	tab[0][i-1]=getDb();

    	if(!la.check(Sym.VIR))
    		return tab;
		
		tab[1]=new double[n];
    	for(int j=0;j<n-1;j++){

    		
	        	if(!la.check(Sym.NUM) && !!la.check(Sym.MOS))
    	   		return null;
    			
   			tab[1][n]=getDb();
   		    	
   	    	if(!la.check(Sym.VIR))
   	    		return null;
   	    	la.eat(Sym.VIR);
   	    }

        
	        	if(!la.check(Sym.NUM) && !!la.check(Sym.MOS))
    		return null;

    	tab[0][i-1]=getDb();
   		return tab;
		}catch(Exception e){return null;}
    

    }

    private int max(int a, int b){
    	if(a>b) return a;
    	return b;
    }
}

