/*This program is written to display dinasours by reading characters from a file
  Written by: Ozgur Tekin Aksay
*/
import sheffield.*;
public class Dino{
	public static void main(String[] args){
		EasyReader file = new EasyReader("dino.txt");
		EasyReader input = new EasyReader();
     	EasyWriter screen = new EasyWriter();
        final int AXIS_Y = 35;
        final int AXIS_X = 123;
        final int DOUBLE_WIDTH= 2;
        
        //You can change the size of dinasours by using variable SCALE
        final int SCALE = input.readInt("Enter a size for your dinasours (1-6) ----> ");
        EasyGraphics window = new EasyGraphics(AXIS_X*SCALE*DOUBLE_WIDTH,AXIS_Y*SCALE);
        window.setVisible(false);
        //User decides the colour of the background
   	    String colour = input.readString("Background Color: Blue/Yellow/Black/Red/Green?: ");  	
        String upperCaseColour = colour.toUpperCase();
        switch (upperCaseColour){
       		case "BLUE":
       		   	window.setColor(0,0,255);
       		   	break;
       		case "YELLOW":
       		   	window.setColor(255,255,0);
       		   	break;
       		case "RED":   	
       		window.setColor(255,0,0);
       			break;
       		case "GREEN":
       		   	window.setColor(0,255,0);
       		   	break;	
       		default:
       		  	  window.setColor(0,0,0);
       		  	break;
        	}
        
		window.setVisible(true);
		window.setLocationRelativeTo(null);
 	
        window.fillRectangle(0,0,AXIS_X*SCALE*DOUBLE_WIDTH,AXIS_Y*SCALE);
        int i=0;     
        int numberOfStars=0;
        // This loop randomly allocates 50 stars at the sky
        while(numberOfStars<=50){
        	int randomWidth = (int)(Math.random()*AXIS_X*SCALE*DOUBLE_WIDTH);
        	int randomHeight = (int)(Math.random()*AXIS_Y*SCALE);
       		window.setColor(255,255,255);
        	window.plot(randomWidth,randomHeight);
        	numberOfStars++;  
        }
        char[][] letters = new char [AXIS_Y][AXIS_X];	 
     	for(int h=AXIS_Y; h>=0; h--){
        	for(int w=0; w<AXIS_X;w++){
        		 if(i<35&&w<123){
        		 	 //Reads characters from dino.txt into array
        		 	 letters[i][w]=file.readChar();  
        	         //Decodes, checks if it is divisible by 2,then colours that pixel
        	         if((int)letters[i][w]%2==0){
        	         	 window.setColor(0,255,255);
        		 	     window.fillRectangle(w*SCALE,h*SCALE,SCALE,SCALE);  //Scales the dinasour 1 up by size of constant SCALE
        		 	     window.fillRectangle((w*SCALE+AXIS_X*SCALE),h*SCALE,SCALE,SCALE); //Scales the dinasour 3 up by size of constant SCALE
        		 	     window.fillRectangle((w*SCALE+AXIS_X/2*SCALE),h*SCALE,SCALE,SCALE); //Scales the dinasour at the centre up by size of constant SCALE
        		     }  
        		     //Colours bottom part which is horizon
        		     if((int)letters[i][w]%2!=0 && h<7){	 

        		     	 window.setColor(255,255,0);
        		         window.fillRectangle(w*SCALE,0,SCALE,SCALE);
        		   	     window.fillRectangle((w*SCALE+AXIS_X*SCALE),0,SCALE,SCALE);
        		   	     window.fillRectangle((w*SCALE+AXIS_X*SCALE),h*SCALE,SCALE,SCALE);	
        	    	     window.fillRectangle(w*SCALE,h*SCALE,SCALE,SCALE);
        		     }  
        		 } 
        	}
        	     i++;   
        }
	}
}