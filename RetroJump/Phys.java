
import java.lang.Math;
class Phys{

	public static double grav = 0.6;
	public static double maxSpeedY =10;
	public static double maxSpeedX = 6;
	
	public static double frott(double vit, double coef){
		double d=vit-(vit*coef);
		if(vit>0){
			return Math.min(d,maxSpeedX);
		}
		return Math.max(d,-maxSpeedX);

	}

	public static double[] react(double p, double fall, double speed){
		if (p>-0.03 && p<0.03) {
			double[] mod={0,0};
			return mod;
		}
		double[] mod = {0,0};
		//x=y y=-x

		double ang=0;
		ang=Math.atan(p);
		mod[1]= Math.sin(ang)*fall;
		mod[0]= -Math.cos(ang)*fall+speed;
		return mod;
		
	}

	public static double boing(double vit, double ind){
		double d=Math.min(0-(vit*ind),maxSpeedY);
		if(d<0.25 && d > -0.25) d=0;
		return d;
	}

	public static double transf(double vit, int ang){
		return Math.cos(Math.toRadians(ang));
	}
	
}