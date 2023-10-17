class BlueCube{
	ProtoCoordinates centerPoint;
	double rho, theta, sideLength;
	/*Points are at ( sqrt(3)*sidelength )/2 from the center point
	They are at 45(mod 90) degrees increments of the theta and phi axes*/
	ProtoCoordinates[] cornerPoints;
	/*To check if it is intersecting, we check if the line's equation has a match in*/
	boolean intersects(){/
	//Equation diophantienne?

		//Definir le cube par 3 vecteurs. Tout point du cube est une somme de a*v1+b*v2+c*v3
		//avec 0<=a,b,c<=1 et v1,v2,v3 les 3 vecteurs qui definissent le cube


		//comment savoir si une ligne coupe cet espace?
		//Definir par la negation? (si la ligne passe a k*v1 avec k>1 ou k<0, par exemple)
		//Definir les 6 surfaces et verifier si la ligne intersecte la surface d'une des 6 faces?
		//Si oui, comparer la position du point d'intersection avec la somme des 2 vecteurs qui definissent la surface
	}



	//Une surface peut etre definie en 3 dimensions comme une fonction qui assigne une valeur z a la profondeur
	//en fonction du couple (x,y) (comme un equivalent d'une fonction affine en 3D)

	//Comment obtenir cette fonction avec 1 ligne et 1 vecteur?
	//Avoir une classe propre pour les surfaces?

	double abs(double d){ return d>0? d : -d;}

	double max(double a, double b){ return a>b? a : b; }

	double max(double a, double b, double c){
		//Returns the highest value in a set of 3 doubles
		if(a>b)
			return a>c? a : c;
		return b>c? b:c;
	}
}