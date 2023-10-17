class Line3D{
	ProtoCoordinates basePoint, vector;
	//On definit une ligne comme un point d'origine et un vecteur
	boolean intersects(Line3D b){
		//To note : This function accepts twice the same Line3D and will return true

		double[] thisCoords = this.basePoint.getCoordinates();
		double[] thisVect = this.vector.getCoordinates();
		
		double[] bCoords = b.basePoint.getCoordinates();
		double[] bVect = b.vector.getCoordinates();
		

		//X+a*t = X'+a'*t <=> X+a*t-X'-a'*t = 0 <=> (a-a')t = X'- X <=> t = (X'- X)/(a-a')
		//We are looking for the t that matches this condition, so that we can check if it works for other coordinates 
		double toCheck = (bCoords[0] - thisCoords[0])/(thisVect[0]-bVect[0]);
		if( (thisCoords[1] + toCheck * thisVect[1]) != (bCoords[1] + toCheck * bVect[1]) )
			//At the point where the X matches, if the Y does not match, the lines do not intersect
			return false;
		//Else the answer is : Do the Z coordinates match too
		return ( (thisCoords[2] + toCheck * thisVect[2]) == (bCoords[2] + toCheck * bVect[2]));
	}

	//A plane is defined by the normal vector that is perpendicular to it and the point of origin
	
}