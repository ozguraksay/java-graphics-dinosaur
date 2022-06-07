import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import sheffield.EasyGraphics;
import sheffield.EasyReader;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Dino extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected int SCALE;
	protected boolean on;
    final int AXIS_Y = 35;
    final int AXIS_X = 123;
    final int DOUBLE_WIDTH= 2;
    EasyGraphics window = new EasyGraphics();
	EasyReader file = new EasyReader("dino.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dino frame = new Dino();
					frame.setVisible(true);
					frame.setTitle("Draw Dinosaurs");
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dino() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel colour_label = new JLabel("Select Background Colour");
		colour_label.setFont(new Font("Arial", Font.PLAIN, 14));
		colour_label.setBounds(65, 110, 204, 23);
		contentPane.add(colour_label);
		
		JComboBox<Object> colour_options = new JComboBox<Object>();
		colour_options.setBackground(new Color(255, 255, 255));
		colour_options.setFont(new Font("Arial", Font.PLAIN, 14));
		colour_options.setModel(new DefaultComboBoxModel<Object>(new String[] {"Blue", "Yellow", "Black", "Red", "Green"}));
		colour_options.setBounds(100, 140, 90, 21);
		contentPane.add(colour_options);	

		JLabel size_label = new JLabel("Select Size");
		size_label.setFont(new Font("Arial", Font.PLAIN, 14));
		size_label.setBounds(107, 22, 97, 23);
		contentPane.add(size_label);
		
		JComboBox<Object> size = new JComboBox<Object>();
		size.setFont(new Font("Arial", Font.PLAIN, 14));
		size.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6"}));
		size.setBounds(120, 55, 45, 21);
		contentPane.add(size);

		JButton applyButton = new JButton();
		applyButton = new JButton("Run");
		applyButton.setForeground(new Color(255, 255, 255));
		applyButton.setBackground(new Color(0, 0, 128));
		applyButton.setFont(new Font("Arial", Font.BOLD, 12));
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				int SCALE =size.getSelectedIndex()+1;
				System.out.println("Size --> " + SCALE);

				String colour = (String) colour_options.getSelectedItem();
				System.out.println("Background Colour --> " + colour);
				
				window = new EasyGraphics(AXIS_X*SCALE*DOUBLE_WIDTH,AXIS_Y*SCALE);
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
		        		    	 
		        		    	 if(upperCaseColour.equals("YELLOW")){
		        		    		 window.setColor(0,0,255);}
			        			 else{
		        		    		 window.setColor(255,255,0);}
			        		         
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
		});
		window.dispose();
		applyButton.setBounds(105, 180, 77, 21);
		contentPane.add(applyButton);
	}
}