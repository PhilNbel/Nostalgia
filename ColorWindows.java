import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ColorWindows implements Runnable{
	

	//Define the elements for each instance of ColorWindows
	JFrame windowFrame;
	String message;
	JTextArea messageBox;
	JButton sendButton;

	//To have use sliders instead of the TextArea
	JSlider redAmount;
	JSlider greenAmount;
	JSlider blueAmount;
	JSlider alphaValue;

	/*int charNum;*/

	static int baseHeight = 1500;
	static int baseWidth = 500;

	//Function to change windowFrame's background color

	static int getInt(String toRetrieve){
		try{
			int value = Integer.valueOf(toRetrieve);
			return value;
		} catch (NumberFormatException exc){
			/*exc.printStackTrace();*/
			return -1;
		}
	}

	boolean tryRGBA(String input){
		//We check if it is 4 ints separated by 3 commas
		int red,blue,green, alpha;
		String value;
		String tmp;
		
		//From the start to the first comma
		int nextComma = input.indexOf(",");
		if(nextComma==-1 || nextComma == input.length()-1)
			return false;
		value = input.substring(0,nextComma);

		red = getInt(value);
		if(red == -1 || red > 255) return false;
		
		//We take out the red component
		//If there is a space between the comma and the Integer, we skip it
		if(input.charAt(nextComma+1) == ' ')
			tmp = input.substring(nextComma+2);
		else
			tmp = input.substring(nextComma+1);
		input = tmp;

		nextComma = input.indexOf(",");
		if(nextComma==-1 || nextComma == input.length()-1)
			return false;
		value = input.substring(0,nextComma);
		green = getInt(value);
		if(green == -1 || green > 255) return false;

		//We take out the green component
		//If there is a space between the comma and the Integer, we skip it

		if(input.charAt(nextComma+1) == ' ')
			tmp = input.substring(nextComma+2);
		else
			tmp = input.substring(nextComma+1);
		input = tmp;

		nextComma = input.indexOf(",");
		if(nextComma==-1 || nextComma == input.length()-1)
			return false;
		value = input.substring(0,nextComma);

		blue = getInt(value);
		if(blue == -1 || blue > 255) return false;
		//We take out the blue component, and the alpha one is what is left
		//If there is a space between the comma and the Integer, we skip it

		if(input.charAt(nextComma+1) == ' ')
			tmp = input.substring(nextComma+2);
		else
			tmp = input.substring(nextComma+1);
		input = tmp;
		value = input;

		//If there is a line break at the end, we disregard it
		if(input.charAt(input.length()-1) == '\n')
			value = input.substring(0, input.length()-1);

		alpha = getInt(value);
		if(alpha == -1 || alpha > 255) return false;
		
		this.windowFrame.getContentPane().setBackground(new Color(red, green, blue, alpha));

		return true;
	}

	boolean tryRGB(String input){
		//We check if it is 3 ints separated by 2 commas
		int red,blue,green;
		String value;
		String tmp;
		
		//From the start to the first comma
		int nextComma = input.indexOf(",");
		if(nextComma==-1 || nextComma == input.length()-1)
			return false;
		value = input.substring(0,nextComma);

		red = getInt(value);

		if(red == -1 || red > 255) return false;
		
		//We take out the red component
		//If there is a space between the comma and the Integer, we skip it
		if(input.charAt(nextComma+1) == ' ')
			tmp = input.substring(nextComma+2);
		else
			tmp = input.substring(nextComma+1);
		input = tmp;

		nextComma = input.indexOf(",");
		if(nextComma==-1 || nextComma == input.length()-1)
			return false;
		value = input.substring(0,nextComma);
		green = getInt(value);

		if(green == -1 || green > 255) return false;

		//We take out the green component, and the blue one is what is left
		//If there is a space between the comma and the Integer, we skip it

		if(input.charAt(nextComma+1) == ' ')
			tmp = input.substring(nextComma+2);
		else
			tmp = input.substring(nextComma+1);
		input = tmp;
		value = input;
		//If there is a line break at the end, we disregard it
		if(input.charAt(input.length()-1) == '\n')
			value = input.substring(0, input.length()-1);
		blue = getInt(value);

		if(blue == -1 || blue > 255) return false;
		

		this.windowFrame.getContentPane().setBackground(new Color(red, green, blue));

		return true;
	}

	void process(String colors){

		//if the String is in an "(int,int,int,int)" format, handle it as a RGBA input
		if(tryRGBA(colors)) return;

		//if the String is in an "(int,int,int)" format, handle it as a RGBinput
		if(tryRGB(colors)) return;

		//Check the Color names, and set the background this color if there is a match
		switch(colors.toLowerCase()){
			case "red":
				this.windowFrame.getContentPane().setBackground(Color.red);
				return;
			case "blue":
				this.windowFrame.getContentPane().setBackground(Color.blue);
				return;
			case "green":
				this.windowFrame.getContentPane().setBackground(Color.green);
				return;
			case "black":
				this.windowFrame.getContentPane().setBackground(Color.black);
				return;
			case "magenta":
				this.windowFrame.getContentPane().setBackground(Color.magenta);
				return;
			case "cyan":
				this.windowFrame.getContentPane().setBackground(Color.cyan);
				return;
			case "orange":
				this.windowFrame.getContentPane().setBackground(Color.orange);
				return;
			case "pink":
				this.windowFrame.getContentPane().setBackground(Color.pink);
				return;
			case "yellow":
				this.windowFrame.getContentPane().setBackground(Color.yellow);
				return;
			case "grey":
				this.windowFrame.getContentPane().setBackground(Color.gray);
				return;
			case "gray":
				this.windowFrame.getContentPane().setBackground(Color.gray);
				return;
			case "white":
				this.windowFrame.getContentPane().setBackground(Color.white);
				return;
			//Else it is not a color input
			default:
        		System.out.println("Invalid Format");
				return;
			}
	}


	// JFrame init(int initTag){}
	// Create an instance of the window
	// ColorWindows(int type){
	//	Define the Text area for the input to update the background's color
	// 	this.windowFrame = init(type);
	// }

	void setVisible(boolean b){
		this.windowFrame.setVisible(b);
	}

	public static void main(String[]args){

		ColorWindows currColorWindows;
		for(int i = 0; i<5; i++){
			currColorWindows = new ColorWindows(i);
			currColorWindows.setVisible(true);
		}
	}
	
}