import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Display{
	//Define the elements for each instance of Display
	JFrame windowFrame;
	JPanel coloredPane;
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
		
		this.coloredPane.setBackground(new Color(red, green, blue, alpha));

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
		

		this.coloredPane.setBackground(new Color(red, green, blue));

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
				this.coloredPane.setBackground(Color.red);
				return;
			case "blue":
				this.coloredPane.setBackground(Color.blue);
				return;
			case "green":
				this.coloredPane.setBackground(Color.green);
				return;
			case "black":
				this.coloredPane.setBackground(Color.black);
				return;
			case "magenta":
				this.coloredPane.setBackground(Color.magenta);
				return;
			case "cyan":
				this.coloredPane.setBackground(Color.cyan);
				return;
			case "orange":
				this.coloredPane.setBackground(Color.orange);
				return;
			case "pink":
				this.coloredPane.setBackground(Color.pink);
				return;
			case "yellow":
				this.coloredPane.setBackground(Color.yellow);
				return;
			case "grey":
				this.coloredPane.setBackground(Color.gray);
				return;
			case "gray":
				this.coloredPane.setBackground(Color.gray);
				return;
			case "white":
				this.coloredPane.setBackground(Color.white);
				return;
			//Else it is not a color input
			default:
        		System.out.println("Invalid Format");
				return;
			}
	}


	JFrame init(){
		//Define the Text area for the input to update the background's color
		this.messageBox = new JTextArea("",1,512);
		this.messageBox.setBounds(9*baseHeight/20, 7*baseWidth/20, baseHeight/10, baseWidth/20);

		//Create a button that calls the process() function that changes the color
		this.sendButton = new JButton("Update");
		this.sendButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        		process(messageBox.getText());
        		}
        	});

		//Define it's size and position
		this.sendButton.setBounds(9*baseHeight/20, 9*baseWidth/20, baseHeight/10, baseWidth/20);
		this.sendButton.setFocusable(false);

		//Create a new JFrame
		this.windowFrame = new JFrame();
		this.windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.windowFrame.setTitle("Back to the windows");
		this.windowFrame.setLocationRelativeTo(null);

		//We create an intermediary panel to improve display quality
		JPanel jp0 = new JPanel();
		jp0.setLayout(new GridLayout(3,3));

		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(1,3));

		//We create an empty component to have empty spaces
		JComponent emptySpace = new JPanel();
		//and a colored pane to display color
		this.coloredPane = new JPanel();
		this.coloredPane.setBackground(Color.GREEN);
		//Add the elements
		/*this.windowFrame.getContentPane().add(emptyLabel, BorderLayout.CENTER);*/
		
		jp1.add(messageBox);
		jp1.add(sendButton);

		/*this.windowFrame.setIconImage();
		this.windowFrame.pack();*/

		//We define the window's size and add the JPanel to have everything
		this.windowFrame.setSize(baseHeight,baseWidth);
//		this.windowFrame.setLayout()
		this.windowFrame.add(jp0, BorderLayout.CENTER);

		//Then we add 6 colored panes, 1 empty space, the text field and button, and a last empty space to be sure
		jp0.add(coloredPane,1,1);
		jp0.add(coloredPane,1,2);
		jp0.add(coloredPane,1,3);
		jp0.add(coloredPane,2,1);
		jp0.add(coloredPane,2,2);
		jp0.add(coloredPane,2,3);
		jp0.add(emptySpace,3,1);
		jp0.add(jp1,3,2);
		jp0.add(emptySpace,3,3);

		return this.windowFrame;
	}

	//Create an instance of the window
	Display(){
		this.windowFrame = init();
	}

	void setVisible(boolean b){
		this.windowFrame.setVisible(b);
	}

	public static void main(String[]args){

		Display mainDisplay = new Display();
		mainDisplay.setVisible(true);
	}
}