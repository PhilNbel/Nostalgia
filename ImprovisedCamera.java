class ImprovisedCamera{
	//This will be my first attempt at creating a camera to lay the grounds for a Graphics engine.
	//As such, it is bound to be wonky and have quirks

	//Image feed will start as a 2D byte array for easier programmation
	byte[][] feed;

	//We store respectively the radial distance, the azimuthal angle and the polar(inclination) angle
	//This lets us know in which direction the direction the camera is pointing and the focal distance
	//We keep the value private to keep the encapsulation
	
	private double rho, theta, phi;
	private ProtoCoordinates position;

	//As screen values are not a crucial element, we keep the default access rules
	double cameraWidth, cameraHeight;
	
	//Might be modified throughout development and/or through methods
	double defaultRhoValue = 5.0;
	double defaultResolution = 10;
	//TODO: transform feed into a correct video feed, probably through a byte stream converted to an image
		//Consult https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
		//https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html
		//https://www.tutorialspoint.com/How-to-convert-Byte-Array-to-Image-in-java
		//https://stackoverflow.com/questions/11247595/java-converting-byte-to-image

	void init(double camWidth, double camHeight, double focalDistance){
		//We give the object the values of the argument
		this.cameraWidth = screenSize.getWidth();
		this.cameraHeight = screenSize.getHeight();
		this.rho = focalDistance;
		//With these values, we use trigonometry to deduce the angles of view of the camera
		this.theta = 2*Math.atan( camHeight / (2*focalDistance) );
		this.phi = 2*Math.atan( camWidth / (2*focalDistance) );
		int feedWidth = (int)cameraWidth / this.defaultResolution;
		int feedHeight = (int)cameraHeight / this.defaultResolution;
		byte feed = new byte[feedHeight][feedWidth];
		this.updateView();
	}

	void move(ProtoCoordinates vector){ this.position = this.position.sum(vector); }
	void setPosition(ProtoCoordinates newCoordinates){ this.position = newCoordinates; }

	//TODO: Check if additional constructor are needed. Maybe to define focal distance at creation
	ImprovisedCamera(QuasiWorld environment){
		//This improvised camera will take the user's screen width and height and create an image with this format
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.init( screenSize.getWidth(), screenSize.getHeight(), this.defaultRhoValue );
		/*AWT solution to multi-monitor display
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		this.cameraWidth = gd.getDisplayMode().getWidth();
		this.cameraHeight = gd.getDisplayMode().getHeight();*/

		this.position = new ProtoCoordinates(0,0,0);
	}

	ImprovisedCamera(QuasiWorld environment, ProtoCoordinates coord){
		this();
		this.move(coord);
	}

	ImprovisedCamera(QuasiWorld environment, double camWidth, double camHeight){
		//If called with special values, the camera will return a video feed with the given format
		this.init( screenSize.getWidth(), screenSize.getHeight(), this.defaultRhoValue );
	}

	ImprovisedCamera(QuasiWorld environment, double camWidth, double camHeight,ProtoCoordinates coord){
		this(camWidth, camHeight);
		this.move(coord);
	}

	byte checkPoint(double x, double y){
		//We send a ray from the camera in a direction that depend from the angle the camera is pointing in
		//and the place in the screen the ray corresponds to
		return sendRay(position, theta + x, phi + y);
	}

	void updateView(){
		//We check the image that the camera can see and update the feed with it
		for(int y = 0; y < this.feed.length; y++){
			for(int x = 0; x < this.feed[y].length; x++){
				this.feed[y][x] = this.checkPoint( (double)( x - feed[y].length/2 ), (double) ( y - feed.length/2 ) );
			}
		}
	}
}