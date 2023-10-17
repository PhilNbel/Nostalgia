import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ColorBroadcaster{

	static int baseHeight = 1500;
	static int baseWidth = 500;

	static int screenPosX = 100;
	static int screenPosY = 150;

	int tcpAddress;
	int tcpEmissionAddress;
	int broadcastingFrequence;
	int portInUse;
	boolean isBroadcasting;
	Socket tcpsocket;
	DatagramSocket udpsocket;

	JFrame windowFrame;
	JTextArea rgbInput;
	JSlider colorAmount;
	JButton sendButton;

	//Some getters
	boolean broadcasts(){ return isBroadcasting; }
	int getTCPAddress(){ return tcpAddress; }
	int getFrquence(){ return broadcastingFrequence; }
	int getPort(){ return portInUse; }

	Socket getSocket(){
		if(isBroadcasting)
			return null; 
		return tcpsocket;
	}
	DatagramSocket getDatagramSocket(){
		if(isBroadcasting)
			return udpsocket; 
		return null;
	}


	void init(){
		//Create a new JFrame
		rgbInput = new JTextArea("",1,512);
		rgbInput.setBounds(9*baseHeight/20, 7*baseWidth/20, baseHeight/10, baseWidth/20);

		windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setTitle("Back to the windows");
		windowFrame.setLocation(screenPosX, screenPosY);
		//We change screenPos X and Y to avoid superposition
		screenPosX+=50;
		screenPosY+=75;

		//Define it's size and color
		windowFrame.setSize(baseHeight,baseWidth);
		windowFrame.setLayout(null);
		windowFrame.getContentPane().setBackground(Color.green);

		//Add the elements
		/*windowFrame.getContentPane().add(emptyLabel, BorderLayout.CENTER);*/
		windowFrame.add(rgbInput);
		windowFrame.add(sendButton);
	}

	void broadcast(boolean UDP){
		if(UDP){

		}
	}

	ColorBroadcaster(boolean doBroadcast, int address){
		//if doBroadcast is true, the color is broadcasted
		//on a broadcasting ferquence to any number of RenderWindows
		if(doBroadcast){
			tcpAddress = -1;
			tcpEmissionAddress = -1;
			broadcastingFrequence = address;

			for(int i=0;i<9999; i++){
	    	    try {
	        	    DatagramSocket ds = new DatagramSocket(i);
	        	    udpsocket = ds;
	        	    portInUse = i;
	        	    break;
		        } catch(Exception e) {
	            	continue;
	        	}
	        }
		} else {
			//if not, we bind a TCP address to the ColorBroadcaster
			//and we ask him to bind to avoid noise
			tcpEmissionAddress = address;
			broadcastingFrequence = tcpAddress;
			for(int i=0;i<9999; i++){
	    	    try {
	        	    Socket s = new Socket(i);
	        	    tcpsocket = s;
	        	    portInUse = i;
	        	    break;
		        } catch(Exception e) {
	            	continue;
	        	}
	        }
		}
		init();
	}


}