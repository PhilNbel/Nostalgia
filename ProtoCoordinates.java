class ProtoCoordinates{
	
	//We give the point private coordinates to preserve encapsulation
	//Encapsulation lets us control data flow 
	private double x,y,z;

	ProtoCoordinates(double newx, double newy, double newz){
		this.x = newx;	
		this.y = newy;
		this.z = newz;	
	}

	//We do not set a constructor without argument as there is no reason to have multiple points at 0,0,0
	//As such, it is considered that a user desiring to have some will have to purposefuly initiate them.

	ProtoCoordinates sum(ProtoCoordinates toAdd){
		//we return the sum of the values
		return new ProtoCoordinates( (this.x + toAdd.x), (this.y + toAdd.y), (this.z + toAdd.z) );
	}
	
	//We create getters and setters to allow encapsulation to function
	void setX(double newx){ this.x = newx; }
	void setY(double newy){ this.y = newy; }
	void setZ(double newz){ this.z = newz; }

	void setCoordinates(double newx, double newy, double newz){
		this.x = newx;
		this.y = newy;
		this.z = newz;
	}

	boolean setCoordinates(double[] newCoordinates){
		//We check if the array is the right size. If not, we abort
		if(newCoordinates.length != 3)
			return false;

		this.x = newCoordinates[0];
		this.y = newCoordinates[1];
		this.z = newCoordinates[2];

		return true;
	}

	double getX(){ return this.x; }
	
	double getY(){ return this.y; }
	
	double getZ(){ return this.z; }

	double[] getCoordinates(){
		//To get all coordinates and reduces stack load
		double[] coords = { this.x, this.y, this.z };
		return coords;
	}

}