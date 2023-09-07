

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.actuators.lego.motors.EV3MediumRegulatedMotor;
import ev3dev.sensors.Battery;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import ev3dev.sensors.ev3.EV3TouchSensor;
import ev3dev.sensors.ev3.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import ev3dev.sensors.ev3.EV3ColorSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Jason Imports Start
import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.StringConcatFactory;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Logger;
//Jason Imports End
import java.util.stream.IntStream;

import org.apache.tools.ant.util.StreamUtils;

  
 




public class PLEnv extends Environment {
	
	
	 float red =0;
     float green =0;
     float blue =0;

	
	  public static int counter_g = -3;
	  
	  
	  public static int counter_temp;
	  
	  public int dist =0;
	  
	  public  double d1_g=0.0;
	  public  double d2_g=0.0;
	  
	  
	  
	  public  double revd1_g=0.0;
	  public  double revd2_g=0.0;
	  public  double revd3_g=0.0;
	  
	  
	  public  int stopConv=0;

	    public static boolean reset_counter = false;
	
    public TimerTask task = new TimerTask() {


        int counter = -3;

        public void run() {
           // System.out.println("Task performed on: " + "n" + Thread's name: " + Thread.currentThread().getName());

            if (reset_counter){
                counter = -5;
                reset_counter = false;
            }
            
            if (counter > 40){
                counter = -5;
                
            }

            counter++;
            counter_g = counter;
            
           System.out.println("TIME "+counter);
            add_counterToSortAgent(counter);
            
           
            
           // System.out.println(counter);

        }

	    };
	
	
	
	
	
	
		SampleProvider sp =null;
		
		SampleProvider sp_drop =null;
	
	
	static EV3TouchSensor Layer2_touchSensor = null;
	
	public static EV3TouchSensor touchSensor = null; // DropButton
	public static EV3ColorSensor sensor =  null; //drop sensor
	public static EV3UltrasonicSensor ultrasonicSensor =  null;
	public static EV3ColorSensor color =  null; 
	
	
	 
	 //public static EV3ColorSensor colorSensor2 = null;
	
	
	static  boolean call_mode =false;										//drop sensor
    
    
        //sort Agent Color Sensor
    static  boolean call_mode2 =false;
    
    
	
	static EV3MediumRegulatedMotor motor1 = null; // Conveyor 1  Motor 
	static EV3LargeRegulatedMotor motor2 = null; // Conveyor 2  Motor 
	
	
	
	
	static EV3MediumRegulatedMotor dropmotor = null; // DropMotor
	static EV3LargeRegulatedMotor shredermotor = null; // ShredMotor

	
	
	public static final Term startTimer = Literal.parseLiteral("startTimer");
	
	
	
	// LAYER 2
	static EV3LargeRegulatedMotor ejectmotor = null;
	static EV3LargeRegulatedMotor pressmotor = null;
	static EV3MediumRegulatedMotor pushmotor = null;
	
	
	
	
	
	
	
	
	public static final Term motorB = Literal.parseLiteral("actuate(motorB)");
	
	public static final Term initComponents = Literal.parseLiteral("initialize_comps");
	public static final Term init2Components = Literal.parseLiteral("initialize2_comps");
	
	public static final Term senseDrop = Literal.parseLiteral("senseDrop");
	
	public static final Term conveyorStart = Literal.parseLiteral("conveyorStart");
	public static final Term conveyorStop = Literal.parseLiteral("conveyorStop");
	public static final Term conveyorReverse = Literal.parseLiteral("conveyorReverse");
	
	public static final Term reverseMovementsAction = Literal.parseLiteral("reverseMovementsAction");
	
	
	public static final Term reverseMovements = Literal.parseLiteral("reverseMovements");
	
	public static final Term resetCounter = Literal.parseLiteral("resetCounter");
	
	
	
	public static final Term sampleColor = Literal.parseLiteral("sampleColor");
	public static final Term buttonPressed = Literal.parseLiteral("buttonPressed");
	
	public static final Term productState = Literal.parseLiteral("productState");
	public static final Term dropProduct = Literal.parseLiteral("dropProduct");   
	public static final Term resetProduct = Literal.parseLiteral("resetProduct"); 
	
	
	public static final Term shredStart = Literal.parseLiteral("shredStart");
	public static final Term shredStop = Literal.parseLiteral("shredStop");
	
	public static final Term checkEmergency = Literal.parseLiteral("checkEmergency"); //motor1.stop();
	
	
	
	
	//sort Agent
	
	public static final Term goRedPosition = Literal.parseLiteral("goRedPosition");
	
	
	public static final Term goBluePosition = Literal.parseLiteral("goBluePosition");
	public static final Term goGreenPosition = Literal.parseLiteral("goGreenPosition");
	public static final Term goYellowPosition = Literal.parseLiteral("goYellowPosition");
	
	
	
	
	
	//////////// LAYER 2 ///////////////////////
	
	//push Agent
	
	
	
	
	
		public static final Term pushMotor = Literal.parseLiteral("pushMotor");
		
	// Build Agent resetposition
	
		public static final Term resetPosition = Literal.parseLiteral("resetPosition"); 
		public static final Term mediumPress = Literal.parseLiteral("mediumPress");
	
		public static final Term secondPress = Literal.parseLiteral("secondPress");
		
		public static final Term ejectProduct = Literal.parseLiteral("ejectProduct");
		
	// GoRedPosition()
	
	
	
	
	
	
//	public static final Literal senseDrop = Literal.parseLiteral("senseDrop");
	static int count=0;
	static Logger logger = Logger.getLogger(PLEnv.class.getName());
	static Random rand = null;
	static int n= 0;
	
	//EV3LargeRegulatedMotor motorBact;
	
	 public PLEnv() {
	//        this.motorBact = null;
		 		 
	    }
	 
	
	 void add_counterToSortAgent(int counter) {
		
		 
		    System.out.println("Counter:" + counter);
		    
		    // Clear the previous percepts
		 	removePerceptsByUnif("sortAgent",Literal.parseLiteral("count_time(_)"));
		 	
		 	//Add new percept
		    addPercept("sortAgent",Literal.parseLiteral("count_time("+counter+")"));
		 
		 
		
	}

	@Override
	    public void init(String[] args) {
		// final EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
    // motorBact  = new EV3LargeRegulatedMotor(MotorPort.B);
		/* System.out.println("Sensor modes and Motors are initializing"); 
		 rand = new Random();
		 
		 clearAllPercepts();
		 updatePercepts();
		 isPressed();
		 fuzzyColourSensor();
		 Drop_Sensor(); // always initialize
		 check_Emergency(); // check Emergency
		 
		 
		 tryRGB();
		 */
		
		   //To Stop the motor in case of pkill java for example
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            System.out.println("Emergency Stop");
	            motor1.stop();
	            motor2.stop();
	        }
	    }));
		 
	 }
	 
	 
	
	 
	   
	   public void isPressed() {
		   
		  // boolean touch=false;
		   
		   boolean touch= touchSensor.isPressed();
		 /*  boolean array[];
			array = new boolean[2];
			array[0] = false;
			array[1] = true;
			
			//array[2] = false;
			//array[3] = false;
			
			//System.out.println("Java boolean Array Example");
			for(int i=0; i < array.length;i++)
			{
				 touch = array[i];
				// System.out.println(i);
			}
		   */
		 //  Random rd = new Random();
		   
	  //      boolean touch = touchSensor.isPressed();
	        String press_result=String.valueOf(touch);
	        System.out.println("BUTTON STATUS:"+press_result);
	        		
	        
	        		 
	        		removePercept("dropAgent",Literal.parseLiteral("dropButtonStatus(true)"));
	        		removePercept("dropAgent",Literal.parseLiteral("dropButtonStatus(false)"));
	            	addPercept("dropAgent",Literal.parseLiteral("dropButtonStatus("+press_result+")"));
	            
	           
	            
	         //   System.out.println("Button Pressed =" + press_result);
	            

	    } //dropButtonStatus(false).
	 
	 
	   private void Drop_Sensor() {
		 
		        if (!call_mode){
		       // sensor.getRGBMode();
		        sp_drop = sensor.getColorIDMode();	
		        
				
		        call_mode=true;
		        }
		        
				

		        int sampleSize = sp_drop.sampleSize();
		        float[] sample = new float[sampleSize];
		        
		        int idcolor =0;
		        
		        for(int i=0;i<15;i++) {
		        	
		        	
		        	sensor.fetchSample(sample, 0);
		        	idcolor+= (int)sample[0];
		        	
		        	//sensor.fetchSample(sample, 0);
		        	Delay.msDelay(1);
		        	//System.out.println("Readed Color ID =  "+sample[0]+" "+i);
		        }
		        
		        idcolor=idcolor/15;
		        
		      //  System.out.println("Readed Color DIVIDED ID =  "+idcolor);
		        
		        String drop_result="false";
		       // while (true) {

		            if (idcolor == 2 || idcolor == 3 || idcolor == 4 || idcolor == 5 || idcolor == 6 || idcolor == 7) {
		            //    System.out.println("Product ready for drop");
		           //     System.out.println("idcolor " + idcolor);
		                
		                drop_result = "true";		                
		              //  addPercept("dropAgent",Literal.parseLiteral("productReady("+drop_result+")"));
		                 

		            } else {
		             //   System.out.println("Wrong color or no product");
		             //   System.out.println("idcolor " + idcolor);
		                
		                drop_result = "false";		                
		                //addPercept("dropAgent",Literal.parseLiteral("productReady("+drop_result+")"));
		               
		            }
		            
		            removePercept("dropAgent",Literal.parseLiteral("productReady(_)"));
	        //		removePercept("dropAgent",Literal.parseLiteral("productReady(false)"));
		        	//removePerceptsByUnif("dropAgent",Literal.parseLiteral("productReady(_)"));
	        		//System.out.println(drop_result);
	        		addPercept("dropAgent",Literal.parseLiteral("productReady("+drop_result+")"));
	            
	        		idcolor=0;
		    }
		   
		
	   
	   private void fuzzyColourSensor() {	 	
			 
	//	   System.out.println("colorReadinggg");
		   
		   long start1 = System.nanoTime();
			 
			 if (!call_mode2){ // This is for one-shot configuration.
				    // access to the color sensor via the BrickPI API
			        sp =  color.getRGBMode();	// Switch to RGB mode and sample data
			        
					
			        call_mode2=true;
			        }
			        
					
			 		
			        int sampleSize = sp.sampleSize();
			        float[] sample = new float[sampleSize];
			
			 
	           Delay.msDelay(10);
	           
	            sp.fetchSample(sample, 0);
	            red=sample[0];
	            green=sample[1];
	            blue =sample[2];
	        		   
		    	 
	            
		
		    	 
		    	 double values[] = new double[13]; // KURALLAR ARTINCA BUNUDA ARTTIR.
		    	 
		         values=fuzzyColorSensor(red,green,blue);
		         
		
		      // Clear the previous percepts
		         
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorRed(low,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorRed(medium,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorRed(high,_)"));
		         
		         
		         
		         
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(low,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(medium,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(high,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(veryhigh,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(ultralow,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(ultramedium,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorGreen(ultrahigh,_)"));
		         
		         
		         
		    
		         
		         
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorBlue(low,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorBlue(medium,_)"));
		         removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorBlue(high,_)"));
		        
		         
		         
		         	Literal colorRed1 = Literal.parseLiteral("colorRed(low," + values[0] + ")");
		    		Literal colorRed2 = Literal.parseLiteral("colorRed(medium," + values[1] + ")");  
		    		Literal colorRed3 = Literal.parseLiteral("colorRed(high," + values[2] + ")");  
		    		
		    		Literal colorGreen1 = Literal.parseLiteral("colorGreen(low," + values[3] + ")");
		    		Literal colorGreen2 = Literal.parseLiteral("colorGreen(medium," + values[4] + ")");  
		    		Literal colorGreen3 = Literal.parseLiteral("colorGreen(high," + values[5] + ")");
		    		
		    		
		    		
		    		
		    		
		    		Literal colorGreen4 = Literal.parseLiteral("colorGreen(veryhigh," + values[6] + ")");
		    		Literal colorGreen5 = Literal.parseLiteral("colorGreen(ultralow," + values[7] + ")");
		    		Literal colorGreen6 = Literal.parseLiteral("colorGreen(ultramedium," + values[8] + ")");
		    		Literal colorGreen7 = Literal.parseLiteral("colorGreen(ultrahigh," + values[9] + ")");
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		Literal colorBlue1 = Literal.parseLiteral("colorBlue(low," + values[10] + ")");
		    		Literal colorBlue2 = Literal.parseLiteral("colorBlue(medium," + values[11] + ")");  
		    		Literal colorBlue3 = Literal.parseLiteral("colorBlue(high," + values[12] + ")");
		    		
		    		
		    		
		    		Literal rgb = Literal.parseLiteral("rgb("+red+","+green+","+blue+")");
		    		
		    		
		    		
		    		
		    		// Add the new membership degrees to the beliefs
		            addPercept("sortAgent",colorRed1);
		            addPercept("sortAgent",colorRed2);
		            addPercept("sortAgent",colorRed3);
		            

		            addPercept("sortAgent",colorGreen1);
		            addPercept("sortAgent",colorGreen2);
		            addPercept("sortAgent",colorGreen3);
		            addPercept("sortAgent",colorGreen4);
		            
		            addPercept("sortAgent",colorGreen5);
		            addPercept("sortAgent",colorGreen6);
		            addPercept("sortAgent",colorGreen7);
		            
		            addPercept("sortAgent",colorBlue1);
		            addPercept("sortAgent",colorBlue2);
		            addPercept("sortAgent",colorBlue3);
		         
		         
		            addPercept("sortAgent",Literal.parseLiteral("observedt"));  
		         
		            
		            // this is for debugging purposes.
		            removePerceptsByUnif("sortAgent",Literal.parseLiteral("rgb(_,_,_)"));
		            addPercept("sortAgent",rgb); 
		            
		            
		     /*       long end1 = System.nanoTime();      
			 	      System.out.println("******Elapsed Time in nano seconds: ****"+ (end1-start1));
			 	      
			 	     System.out.println("Writing Sum "+red+" "+green+" "+blue);
			 	      
			 	     if (red>25.0 || green>25.0 || blue>25.0) { 
			 	    	 System.out.println("Writing Sum "+red+" "+green+" "+blue);
			 	    	 writeToFile(String.valueOf(end1-start1));
			      }*/
		
		         
		 //        System.out.println("RGB"+"R"+red+"G"+green+"B"+blue);
		         
		 //   	 System.out.println("R1:"+values[0]+" R2:"+values[1]+" R3:"+values[2]+" "
		 //   	 		+ "G1:"+values[3]+" G2:"+values[4]+" G3:"+values[5]+" G4:"+values[6]+ " G5:"+values[7]+ " G6:"+values[8]+ " G7:"+values[9]+
		 //   			 " B1:"+values[10]+" B2:"+values[11]+" B3:"+values[12]);
		    	 		    	 		    
	} 
	   
	   
	   private static final String newLine = System.getProperty("line.separator");
		 
		 private static void writeToFile(String msg)  {
		        
			    String fileName = "runOutputFuzzy.txt";
			    PrintWriter printWriter = null;
			    File file = new File(fileName);
			    try {
			        if (!file.exists()) file.createNewFile();
			        printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			        printWriter.write(newLine + msg);
			    } catch (IOException ioex) {
			        ioex.printStackTrace();
			    } finally {
			        if (printWriter != null) {
			            printWriter.flush();
			            printWriter.close();
			        }
			    }
			}
	   
	   
	   public static double[] fuzzyColorSensor(float r, float g, float b) {



		    double r1 = 0.0;
		    double r2 = 0.0;
		    double r3 = 0.0;
		    double r4 = 0.0;
		    double r5 = 0.0;


		    double g1 = 0.0;
		    double g2 = 0.0;
		    double g3 = 0.0;
		    double g4 = 0.0;
		    double g5 = 0.0;
		    double g6 = 0.0;
		    double g7 = 0.0;


		    double b1 = 0.0;
		    double b2 = 0.0;
		    double b3 = 0.0;
		    double b4 = 0.0;
		    double b5 = 0.0;

		    int red =  0;
		    int green = 0;
		    int blue = 0;



		    red = (int) r;
		    green =(int) g;
		    blue =(int) b;




		    String s_red = "";
		    String s_green = "";
		    String s_blue = "";


		    String result = "";
		    
		 // Get the new membership degrees
		    
///////////////////////////////////////////////////////////////////////////////////////

		    if (red < 60)    // low red
		    {

		        r1 = (60.0-red)/60.0;
		        r2 = red/60.0;
		        r3 = 0;
		        //    System.out.println("low red" + " " + r1 + " " + r2 + " " + r3);

		        s_red = "low red";

		    } else if (red >= 60 && red < 115) {   // middle red

		        r1 = 0;
		        r2 = (115 - red) / 55.0;
		        r3 = (red-60.0)/55.0;
		        //   System.out.println("middle red" + " " + r1 + " " + r2 + " " + r3);

		        s_red = "middle red";

		    } else {


		        r1=0;
		        r2=0;
		        r3=1;

		        s_red = "full red";
		    }



///////////////////////////////////////////////////////////////////////////////////////
		    if (blue < 10.5)    // low blue
		    {

		        b1 = (10.5-blue)/10.5;
		        b2 = (blue/10.5);
		        b3 = 0;
		        //     System.out.println("low blue" + " " + b1 + " " + b2 + " " + b3);

		        s_red = "low blue";

		    } else if (blue >= 10.5 && blue < 19.5) {   // middle red

		        b1 = 0;
		        b2 = (19.5 - blue) / 9.0;
		        b3 = (blue-10.5)/9.0;
		        //      System.out.println("middle blue" + " " + b1 + " " + b2 + " " + b3);

		        s_blue = "middle blue";

		    } else {  // full red


		        b1=0;
		        b2=0;
		        b3=1;

		        s_blue = "full blue";
		    }

///////////////////////////////////////////////////////////////////////////////////////
		    if (green < 20)    // low green
		    {

		        g1 = (20.0-green)/20.0;
		        g2 = green/20.0;
		        g3 = 0;
		        //     System.out.println("low green" + " " + r1 + " " + r2 + " " + r3);

		        s_green = "low green";

		    } else if (green >= 20 && green < 30) {   // middle green

		        g1 = 0;
		        g2 = (30.0 - green) / 10.0;
		        g3 = (green-20.0)/10.0;
		        //    System.out.println("middle green" + " " + r1 + " " + r2 + " " + r3);

		        s_green = "middle green";

		/*    } else {


		        g1=0;
		        g2=0;
		        g3=1;

		        s_green = "full green";

		    }
		*/
		        
		    } else if ( green >=30 && green<50) {


	            g1=0;
	            g2=0;
	            g3=(50.0-green)/20.0;
	            g4=(green-30.0)/20.0;
	            g5=0;


	            s_green = "high green";

	        }   
		    
	     else if ( green >=50 && green<80) {


            g1=0;
            g2=0;
            g3=0;
            
            g4=(80.0-green)/30.0;
            g5=(green-50.0)/30.0;


            s_green = "very high green";

        }
		    
	     else if ( green >=80 && green<110) {


	            g1=0;
	            g2=0;
	            g3=0;
	            g4=0;
	            g5=(110.0-green)/30.0;
	            g6=(green-80.0)/30.0;
	            


	            s_green = "ultra green";

	      }
		    
	     else if ( green >=110 && green<145) {


	            g1=0;
	            g2=0;
	            g3=0;
	            g4=0;
	            g5=0;
	            g6=(145-green)/35.0;
	            g7=(green-110.0)/35.0;


	            s_green = "ultra high green";

	        }
		    
///////////////////////////////////////////////////////////////////////////////////////		    
		    double ret_val [] = {r1,r2,r3,g1,g2,g3,g4,g5,g6,g7,b1,b2,b3};
		    
		    // return new membership degrees
			return ret_val;

		}
		   
	   

	/* 
	 private void Color_Sensor_prev() {	 		
		 

		        if (!call_mode2){
		            color.getRGBMode();
		            call_mode2=true;
		        }
		    int readcolorlist[]= new int[15];
		    int idcolor = 0;
		    int sum =0;

		    
		   
		    for (int i=0;i<15;i++) {
		    	idcolor = color.getColorID();
		 //   	System.out.println(idcolor);
		        readcolorlist[i]=idcolor;
		        sum +=readcolorlist[i];	
		       // LocalTime now = LocalTime.now();
		       // DateTimeFormatter cr_time = DateTimeFormatter.ofPattern("HH:mm:ss");
		       // index = index + 1;	
		    } 
		       // if (index==10){
		
		    
		        
		      sum = (int)sum/15;
		     // System.out.println("sum"+sum);
		        	
		        	
		           
		        	removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorValue(_)"));
		 		    addPercept("sortAgent",Literal.parseLiteral("colorValue("+sum+")"));
		        	
		          /*  if (x==3|| x==4|| x==5 ||x==6) {
		                System.out.printf(String.valueOf(index),idcolor);
		                System.out.println("Retval : "+x);
		              //  dev1.retVal = x
		            }
		                else{
		               // dev1.retVal = 0.0
		            }
		           // }
		 		    
		 		// count++;
					// System.out.println(count);
		        }
		   */ 
		     

	 public static void readcolor(){
		    color.getColorID();
		    // System.out.println("Color readed"+color.getColorID());
		}
 
   
	private void updatePercepts() {
		 int n = rand.nextInt(50);
		// System.out.println("rand"+n);
		 String s =String.valueOf(n);
		 
		 addPercept("dropAgent",Literal.parseLiteral("dropValue("+s+")"));
		// count++;
		// System.out.println(count);
		}

	@Override
    public boolean executeAction(String ag, Structure action) {
		
	
		
		
		
		
	//	logger.info(ag+" doing: "+ action);
        try {
            if (action.equals(motorB)) {
            	actuate_MotorB();  
            	
            } 
           
            else if(action.equals(initComponents)) {
            	
            	initComponents_func();
            }
            
            else if(action.equals(init2Components)) {
            	
            	init2Components_func();
            }
            
            
            
            else if(action.getFunctor().equals("changeConveyorSpeed"))
        	{
    			
    			// System.out.println("BOMM");
    			double t1 = (double)((NumberTerm)action.getTerm(0)).solve();
        		double d1 = (double)((NumberTerm)action.getTerm(1)).solve(); 
        		
        		
        		//System.out.println("BOMM" + t1+" " +d1);
        		
        		//d1 = 1.0-d1;
        		
        		changeShredAndConveyorSpeed(t1*d1,t1,d1);
        		
        		
        		
        		
        		
        	}
            
            else if(action.getFunctor().equals("pushMotorVal"))
        	{
    			
    			// System.out.println("BOMM");
    			double pushForce = (double)((NumberTerm)action.getTerm(0)).solve();
        		
        		
        		
        		//System.out.println("BOMM" + t1+" " +d1);
        		
        		//d1 = 1.0-d1;
        		
        		pushForceVal((int) 1000*pushForce);
        		
        		
        		
        		
        		
        	}
            
            
            
            else if(action.getFunctor().equals("reverseMovementsAction"))
        	{
    			
    			// System.out.println("BOMM");
    			double t1 = (double)((NumberTerm)action.getTerm(0)).solve();
        		double d1 = (double)((NumberTerm)action.getTerm(1)).solve(); 
        		
        		
        		System.out.println("reverse" + t1+" " +d1);
        		
        		//d1 = 1.0-d1;
        		
        		reverseMovementsAction(t1*d1);
        		
        		
        		
        		
        		
        	}
            
            
            
            
            else if(action.equals(senseDrop)) {
            	
            	updatePercepts();
            }
            
            else if(action.equals(conveyorStart)) {
            	
            	conveyorStart_func();
            }
            
            else if(action.equals(conveyorStop)) {
            	
            	conveyorStop_func();
            }
            
            else if(action.equals(reverseMovements)) {
            	
            	reverseMovements();	
            }
            
            else if(action.equals(resetCounter)) {
            	
            	resetCounter();
            }
            
            
            else if(action.equals(sampleColor)) {
            	
            	fuzzyColourSensor();
            }
            
            else if(action.equals(buttonPressed)) {
            	
            	isPressed();
            }
            
            
            else if(action.equals(startTimer)) {
            	
            	start_timer();
            }
            
		    else if(action.equals(productState)) {
		            	
			  Drop_Sensor();
            }
            
		    else if(action.equals(dropProduct)) {
		    	
		      Drop_Product();
		    } 
            
		    else if(action.equals(resetProduct)) {
		    	
		    	Reset_Product();
			
		    }
            
            
    
		    
		    else if(action.equals(shredStart)) {
		    	
			  Shred_Start();
			      
		    }
            
		    else if(action.equals(shredStop)) {
		    	
			
		      Shred_Stop();				      
		    }            
            
		 /*   else if(action.equals(checkEmergency)) {
		    	
		    	
			  
				      
			}*/
            
		    else if(action.getFunctor().equals("checkEmergency"))
        	{
    			
    			// System.out.println("BOMM");
    			int counter = (int)((NumberTerm)action.getTerm(0)).solve();
    			
    			check_Emergency_fuzzy_v2(counter);
    			
        	}
            
		    else if(action.equals(goRedPosition)) {
		    	
		    	GoRedPosition();
					      
				}
            
		    else if(action.equals(goGreenPosition)) {
		    	
		    	GoGreenPosition();
					      
				}
            
		    else if(action.equals(goBluePosition)) {
		    	
		    	GoBluePosition();
					      
				}
            
		    else if(action.equals(goYellowPosition)) {
		    	
		    	GoYellowPosition();
					      
				}
            
            
            
            //////////// LAYER 2 ///////////////////////
            //push agent  
            
		    else if(action.equals(pushMotor)) {
		    	
		    	push();
					      
				}
            
		    else if(action.equals(resetPosition)) {
		    	
		    	resetposition();
					      
				}
            
		    else if(action.equals(mediumPress)) {
		    	
		    	mediumpress();
					      
				}
            
		    else if(action.equals(secondPress)) {
		    	
		    	secondPress();
					      
				}
            
		    else if(action.equals(ejectProduct)) {
		    	
		    	Eject();
					      
				}
            
            
            
            
           
			    
            
         
           
            
            	
            
       /*     else if(action.equals(senseDrop)) {
            	
            	senseDrop_func();
            }
    */        
            
            else {
                return false;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Thread.sleep(1);
        } catch (Exception e) {}
        informAgsEnvironmentChanged();
        return true;
		
	}	
	
	


	private void resetCounter() {
		
		
		reset_counter = true;
		
	}


	private void secondPress() {
		
		
		
        pressDown1();
        mediumpress1();
        pressUp1();
		
		
	}

	private void mediumpress1() {
		
		
		pressmotor.setSpeed(150);		
		Delay.msDelay(1000);
	    pressmotor.backward();
	    Delay.msDelay(5000);
	    pressmotor.stop();
		
	}

	private void conveyorStop_func() {
		
		motor1.stop();
		Delay.msDelay(1);
	}
	
/*	private void conveyorMotor1_reverse() {
		
		motor1.setSpeed(900);		
		motor1.rotate(-360);
		Delay.msDelay(1);
		
		conveyorStart_func();
	}
*/	
	

	private void Shred_Stop() {
		
		
		 shredermotor.stop();
		 Delay.msDelay(1);
	}
	
	
	private void check_Emergency_fuzzy_v2(int counter ){
		
		// the saved hand movements					
		 int[] input_data = {200,200,200,146,146,68,68,32,32,32,16,10,10,0,0,0,0}; 
		 					
		 int nextTurnn=1;
		
		 String state = "";
		 
		 double d1 = 0.0, d2 = 0.0, d3 = 0.0;
		 
		 int distanceValue= input_data[counter];
		
		// Get the new membership degrees
		 
		   if (distanceValue < 5) {
               System.out.println("STOP");
               state = "STOP";

               d1 = 1;
               d2 = 0;
               //  d3=0;

         //      speed = a;
               System.out.println(" " + d1 + " " + d2 + " " + d3);
               
               
               
         
		   } else if (distanceValue >= 5 && distanceValue < 100) {

               System.out.println("LOW");
               state = "LOW";


          //     d1 = (200-distanceValue) / 196.0;
         //      d2 = (distanceValue-4) / 196.0;
               //   d3 = (ultra-25)/25;
               
               d1=0.5;
               d2=0.5;

               
               System.out.println(d1 + " " + d2 + " " + d3);


           } else if (distanceValue >= 100 && distanceValue <= 190) {
               state = "HIGH";
               System.out.println("HIGH");

           //    d1 = (200-distanceValue) / 196.0;
            //   d2 = (distanceValue-4) / 196.0;

               //   d3=1;

               	d1=0;
               	d2=1.0;

              
               System.out.println(d1 + " " + d2 + " " + d3);


           } else if (distanceValue > 191) {
               state = "FREE";
               d1 = 0;
               d2 = 1;
             
               System.out.println(d1 + " " + d2 + " " + d3);
           }
		   
		   
		   	  counter=counter+1;
		   	  
		   	  
		   	  if (counter >= input_data.length) {
		   		  
		   		  counter=0;
		
		   		// nextTurnn=1;
		   		  
		   	  }
		   	 /* else {
		   	  	nextTurnn=0;
		   	  }
		   	  */
		   	  System.out.println("Counter Value:"+counter+"Input Val:"+input_data[counter]);
		   	  
		   	  
		   	  
		   	  
		   	  
		     // Clear the previous percepts
		   
		   	  removePerceptsByUnif("shredAgent",Literal.parseLiteral("speedEmg(low,_)"));
	          removePerceptsByUnif("shredAgent",Literal.parseLiteral("speedEmg(high,_)"));
	          removePerceptsByUnif("shredAgent",Literal.parseLiteral("counter(_)"));
	       //   removePerceptsByUnif("shredAgent",Literal.parseLiteral("nextTurn(_)"));
	          
	          
	          d1_g = d1;
	          d2_g = d2;
	          
	          Literal speedEmgd1 = Literal.parseLiteral("speedEmg(low," + d1 + ")");
	 		  Literal speedEmgd2 = Literal.parseLiteral("speedEmg(high," + d2 + ")");
	 		  Literal counterLiteral = Literal.parseLiteral("counter(" + counter + ")");
	 		  Literal nextTurn = Literal.parseLiteral("nextTurn(" + nextTurnn + ")");
	 		
	 		  
	 		  
	 		// Add the new membership degrees to the beliefs	
	 		  addPercept("shredAgent",speedEmgd1);
	 		  addPercept("shredAgent",speedEmgd2);
	 		  addPercept("shredAgent",counterLiteral);
	 		  addPercept("shredAgent",nextTurn);
	 	

		
	}
	
	


	private void check_Emergency_fuzzy() {
		
		 double d1=0.0,d2=0.0;
		
		  Delay.msDelay(1);
		  SampleProvider sp = ultrasonicSensor.getDistanceMode();
          int distanceValue = 0;
        
          float[] sample = new float[sp.sampleSize()];

          sp.fetchSample(sample, 0);
          distanceValue = (int) sample[0];

       //   System.out.println("Distance: " + distanceValue);
          
          if (distanceValue == 2147483647){ // A workaround incase of sensor buffer overflow, this happens because ultra sonic sensor waves can generate infitinive.
        	  distanceValue =0;
          }
          
          dist = distanceValue;
            
         // removePerceptsByUnif("shredAgent",Literal.parseLiteral("ultraSonicEmergency(_)"));
        //  addPercept("shredAgent",Literal.parseLiteral("ultraSonicEmergency("+distanceValue+")"));
           
         //if (distanceValue<100) {
          
          
          // Get the new membership degrees
          
          if(distanceValue<4)
          {
              System.out.println("LOW");

              d1=1;
              d2=0;
              
            		 
            //  d3=0;

          //    speed = 200;
         //    System.out.println(d1+" "+d2+");
 
          }

      /*    else if(distanceValue>=4 && distanceValue<60 ) {

              System.out.println("MEDIUM");

            
              d1 = (distanceValue-4)/100;
              d2 = (60 - distanceValue) / 100;
            
              
           //   d3 = (ultra-25)/25;

              //speed = 400;
         //     System.out.println(d1+" "+d2+" "+d3);
          }
          
          */

          else if(distanceValue>=4 && distanceValue<=110 )
          {

              System.out.println("FAST");


              
              d1=(110.0-distanceValue)/106.0;
              d2=(distanceValue-4.0)/106.0;
              
             
           
            
              	//   d3=1;



             // speed = 450;
           //   System.out.println(d1+" "+d2+" "+d3);


          }
          
          else if(distanceValue>111 )
          {
              d1=0;
              d2=1;
              
            //  speed = 500;
          }
          
          if (distanceValue < 37)
        	  stopConv = 0;
          else if (distanceValue > 37 && distanceValue < 50 )
        	  stopConv = 100;
          else 
        	  stopConv = 200;

   /* 
          
          */
          // Clear the previous percepts
          removePerceptsByUnif("shredAgent",Literal.parseLiteral("speedEmg(low,_)"));
          removePerceptsByUnif("shredAgent",Literal.parseLiteral("speedEmg(high,_)"));
          
          
          
          d1_g = d1;
          d2_g = d2;
          
         	Literal speedEmgd1 = Literal.parseLiteral("speedEmg(low," + d1 + ")");
 			Literal speedEmgd2 = Literal.parseLiteral("speedEmg(high," + d2 + ")");
 		
 		// Add the new membership degrees to the beliefs
 			addPercept("shredAgent",speedEmgd1);
 			addPercept("shredAgent",speedEmgd2);
 	


             // double result_max = find_max(d1,d2,d3);
         //     double result_max = Math.max(d1,d2);
       //       System.out.println("result-max"+result_max+"speed"+distanceValue);

            int constant = 0;
            
              if (d1==1.0){
                  constant = 1;
              }
              else{
                  constant = 0;
              }



         //     System.out.println ( (int)Math.round(speed*(result_max*(1-constant))) );
        //      int result = ((int)Math.round(speed*(result_max*(1-constant))));
              
              // removePerceptsByUnif("shredAgent",Literal.parseLiteral("ultraSonicEmergency(_)"));
              //  addPercept("shredAgent",Literal.parseLiteral("ultraSonicEmergency("+distanceValue+")"));

        //      System.out.println("result"+result);
        //      motor.setSpeed(result);
        //      Delay.msDelay(10);
     //         motor.forward();
          }
           
	
	
	
	
    public static double find_max(double d1,double d2,double d3){

        double num1 = d1;
        double num2 = d2;
        double num3 = d3;
        double max;

    if (num1 >= num2 && num1 >= num3)
        max = num1;

    else if (num2 >= num1 && num2 >= num3)
        max = num2;
    else
        max = num3;

        return  max;

    }

	/* private void senseDrop_func() {

		 
		
		
	}*/

	private void Shred_Start() {
		
		shredermotor.setSpeed(400);
        Delay.msDelay(5);
        shredermotor.forward();
		
	}

	private void Drop_Product() {
		
		dropmotor.setSpeed(1000);
		Delay.msDelay(1);
		dropmotor.rotateTo(-75);
		
	
		
		
	//	 dropmotor.setSpeed(500);
	  //      Delay.msDelay(1);
	  //      dropmotor.rotate(360);
	}
	
	private void Reset_Product() {
		Delay.msDelay(1);
		dropmotor.rotateTo(0);
	
	}
	
	
private void	changeShredAndConveyorSpeed(double result, double t1, double d1){
	
	  motor1.setSpeed((int) result);
      Delay.msDelay(10);
      motor1.forward();
      
      System.out.println("result is:"+(int) result);
      
      /*
      System.out.println("WRITING TO FILE" + motor1.getSpeed());
      
      try {
		whenAppendToFileUsingFileWriter_thenCorrect();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
	  shredermotor.setSpeed((int) result);
      Delay.msDelay(10);
      shredermotor.forward();
		*/
		
	}
	


public void whenAppendToFileUsingFileWriter_thenCorrect() throws IOException {
	
			
	
		    FileWriter fw = new FileWriter("/home/robot/productionLineFuzzy/src/java/emergencyFuzzy.txt", true);
		   // BufferedWriter bw = new BufferedWriter(fw);
		 //   bw.write(motor1.getSpeed());
		 //   bw.newLine();
		  //  bw.close();
		    
		    fw.write(Integer.toString(motor1.getSpeed())+","+dist+","+d1_g+","+d2_g+","+stopConv);
		    fw.write(System.getProperty( "line.separator" ));

		   
		    fw.close();
		    
		    
		    
}


public  void saveBrick(double my_result) throws IOException {
	
	
	
 //   FileWriter fw = new FileWriter("/home/robot/productionLineFuzzy/src/java/saveBrick.txt", true);
   // BufferedWriter bw = new BufferedWriter(fw);
 //   bw.write(motor1.getSpeed());
 //   bw.newLine();
  //  bw.close();
    
    
 //   fw.write(counter_g+","+Integer.toString((int) my_result)+","+revd1_g+","+revd2_g+","+revd3_g);
 //   fw.write(System.getProperty( "line.separator" ));
    
//    fw.write(Integer.toString((int) my_result));
//    fw.write(System.getProperty( "line.separator" ));

   
//    fw.close();
    
    
    
}
		    
		   



	private void conveyorStart_func() {
		/*	
		  
		   

		        System.out.println("Creating Motor");
		        System.out.println("Defining motor speed");

		  //      motor1.setSpeed(250);

		        System.out.println("Go Forward with the motors");
		  //      motor1.forward();*/
		
		//System.out.println("Conveyor 1 is getting Started.");
		   motor1.setSpeed(400);
	       Delay.msDelay(10);
	       motor1.brake();
	       Delay.msDelay(10);
	       motor1.forward();
		
	}
	
	
	
	
	/* CONVEYOR 2 STARTS*/ 
	

  
    public  void Stopconveyor2() {
        motor2.stop();
    }
    
    
	public  void GoGreenPosition() {
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(1050);
        motor2.stop();
        motor2.getPosition();
    }
    
    
    
    public  void GoBluePosition() {
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(725);
        motor2.stop();
        motor2.getPosition();
    }   
    
    
    public  void GoYellowPosition(){
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(1500);
        motor2.stop();
        motor2.getPosition();
    }
    public  void GoRedPosition() {
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(3000);
        motor2.stop();
        motor2.getPosition();
    }
  /*  public static void runconveyor() {
        motor2.setSpeed(speed);
        Delay.msDelay(1);
        motor2.rotateTo(-500);
    }*/
	
	
    /* CONVEYOR 2 ENDS*/ 
	
	
	
    private void init2Components_func() {


    	System.out.println("Init Layer 2 All");
    	
    	
    	
    
    	
    	pushmotor = new EV3MediumRegulatedMotor(MotorPort.A);
		
    	ejectmotor = new EV3LargeRegulatedMotor(MotorPort.C);
    	
    	pressmotor  = new EV3LargeRegulatedMotor(MotorPort.B);
		
    	
   	init2_sensor();
    
       // colorSensor2= new EV3ColorSensor(SensorPort.S4);
    	
    	
	}
    
    
    
	
	
	

	private void init2_sensor() {
	
		System.out.println("Sensor here.");
		
		Layer2_touchSensor = new EV3TouchSensor(SensorPort.S2);
		
		System.out.println(Layer2_touchSensor.isPressed());
		
	}

	private void initComponents_func() {
		System.out.println("Init All");
		
		/* reset drop motor*/
		//dropmotor.setSpeed(100);
        //Delay.msDelay(1);
        //dropmotor.rotateTo(0); 
		
		color = new EV3ColorSensor(SensorPort.S4); 
		
		touchSensor = new EV3TouchSensor(SensorPort.S2);
		sensor = new EV3ColorSensor(SensorPort.S1);
		ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);
		

		motor1 = new EV3MediumRegulatedMotor(MotorPort.B);

		motor2 = new EV3LargeRegulatedMotor(MotorPort.D);

		dropmotor = new EV3MediumRegulatedMotor(MotorPort.A);
		
		shredermotor = new EV3LargeRegulatedMotor(MotorPort.C);
		
		
		 System.out.println("Sensor modes and Motors are initializing"); 
		 rand = new Random();
		 
		 clearAllPercepts();
		 updatePercepts();
		 isPressed();// -> uncomment this
		 fuzzyColourSensor();// -> uncomment this
	     Drop_Sensor(); // always initialize -> uncomment this
	//	 check_Emergency_fuzzy(); experimented with _v2 .
		 reverseMovements();
	//	 tryRGB();
		 
	        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	            public void run() {
	                System.out.println("Emergency Stop");
	                motor1.stop();
	                shredermotor.stop();
	            }
	        }));
		 
		 
		// reverseMovementsAction(100.0);
	}
	
	
	void start_timer () {
		
		 // init timer.
		System.out.println("Timer is started");
		 Timer timer = new Timer("Timer");
	     long delay = 1000L;
	     timer.schedule(task, 0 ,delay);
		
	}
	
	

	void actuate_MotorB() {
		/* motorBact.setSpeed(500);
		 Delay.msDelay(1);
		 motorBact.forward();
		 Delay.msDelay(2000); 
         motorBact.stop();
        */		 
		 System.out.println("Motor Runs");
		 System.out.println("Motor Stops");
     }
	
	private void tryRGB() {
		
		//RGB    
				System.out.println("Switching to RGB Mode");
				color.setFloodlight(true);
				SampleProvider sp = color.getRGBMode();

				int sampleSize = sp.sampleSize();
				float[] sample = new float[sampleSize];

		        // Takes some samples and prints them
		        for (int i = 0; i < 20; i++) {
		        	sp.fetchSample(sample, 0);
				//	System.out.println("N=" + i + " Sample={}" + (sample[0]));
				//	System.out.println("N=" + i + " Sample={}" + (sample[1]));
				//	System.out.println("N=" + i + " Sample={}" + (sample[2]));
		        	//Color cl = new Color((int)(sample[0] * 255), (int)(sample[1] * 255), (int)(sample[2] * 255));
		        //	System.out.println(cl.getRed() +" " +cl.getGreen() + " "+ cl.getBlue() + " " + cl.getColor());
		         //   Delay.msDelay(100);
		        }
		        
		        sp = color.getAmbientMode();
	            sampleSize = sp.sampleSize();
	            sample = new float[sampleSize];
	            sp.fetchSample(sample, 0);
	            System.out.println("Ambient" + (sample[0]));
		
	}
	
	


	
	//////////////////////////////////////////////////// PART 2 //////////////////////////////////////////////////////
	
	
	
	
	
	public static void push(){
       pushmotor.setSpeed(800);
        pushmotor.forward();
        Delay.msDelay(500);
        pushmotor.stop();
        Delay.msDelay(800);
        pushmotor.backward();
        Delay.msDelay(500);
        pushmotor.stop();
        
     

    }
	
	public static void pushForceVal(double d){
	       pushmotor.setSpeed((int) d);
	        pushmotor.forward();
	        Delay.msDelay(500);
	        pushmotor.stop();
	        Delay.msDelay(800);
	        pushmotor.backward();
	        Delay.msDelay(500);
	        pushmotor.stop();
	        
	        System.out.println(d);
	        
	     

	    }
    public static void pushForward(){
     runDegs(-100,60);

     /*
     orState = dev2.psm.BBM2.isBusy()
            return motorState # returns true when motor busy
      */
    }
    public static void pushBack(){
        runDegs(110,60);
        /*
         motorState = dev2.psm.BBM1.isBusy()
            return motorState # returns true when motor busy, false when ended
         */
    }

    public static void  pushBackLight(){
        runDegs(80,30);
      /*  motorState = dev2.psm.BBM1.isBusy()
        return motorState # returns true when motor busy, false when ended
        */
    }




    public static void runDegs(int angle, int speed){
       pushmotor.setSpeed(speed);
        pushmotor.rotate(angle);
        
     
  }
    
    
    
    
    
    
    
    
   


//////////// PRESS MOTOR STARTS /////////////////////////

public static void pressDown() {

    //if (dev2.ispressed()) {
    pressmotor.setSpeed(200);
    Delay.msDelay(10);
    pressmotor.backward();
    Delay.msDelay(1300);
    pressmotor.stop();
       }

public static void pressDown1() {
    pressmotor.setSpeed(200);
    Delay.msDelay(10);
    pressmotor.backward();
    Delay.msDelay(1300);
    pressmotor.stop();
}

public static void pressUp() {
    pressmotor.setSpeed(200);
    Delay.msDelay(1);
    while (true) {
        pressmotor.forward();
        if (press()) {
            Delay.msDelay(100);
            pressmotor.stop();
            break;
        }
    }

}
public static void pressUp1() {
    pressmotor.setSpeed(150);
    Delay.msDelay(1);
    while (true) {
        pressmotor.forward();
        
        
        
       
        if (press()) {
            Delay.msDelay(100);
            pressmotor.stop();
            break;
        }
   }
}
public static void mediumpress() {
    /***medium press to take the brick  */
	
	
pressmotor.setSpeed(150);
	
	Delay.msDelay(1000);
    pressmotor.backward();
    Delay.msDelay(5000);
    pressmotor.stop();
	
	/*
    //pressmotor.setSpeed(60);
    Delay.msDelay(1);
    pressmotor.forward();
    Delay.msDelay(2000);
    pressmotor.stop();
    Delay.msDelay(300);
    pressmotor.backward();
    Delay.msDelay(2000);
    pressmotor.stop();
    */
    pressUp();

}

public static void resetposition() {  /***get the initial position */
   
	 System.out.println("Before Reset");
	pressmotor.setSpeed(60);
    Delay.msDelay(1);
    
   
    
    while (true) {
    	
    	System.out.println("In Reset");
    	
      if (!press()){
        pressmotor.forward();
    }
    else if(press()) {
        pressmotor.stop();
       // Delay.msDelay(1000);
       // pressmotor.backward();
       // Delay.msDelay(5000);
       // pressmotor.stop();

        break;
    }
    }



}




public static boolean press(){

    boolean touch= Layer2_touchSensor.isPressed();
    System.out.println(touch);
    return touch;


}




/////////////// PRESS MOTOR ENDS ////////////////



////////////////////// EJECT MOTOR STARTS //////////////////////////////////////



public static void Eject() { /// EJECT SEQUENCE
    //  EjectMotor.ejectForward();
     // Delay.msDelay(2000);
      ejectForward();
      Delay.msDelay(500);
      ejectBack();
      Delay.msDelay(1000);
      ejectForward1();
      Delay.msDelay(1000);
      ejectBack();
      Delay.msDelay(1000);
      ejectForward2();
      Delay.msDelay(800);
      ejectBack();
  }





public static void ejectForward()
{
    ejectmotor.setSpeed(500);
    Delay.msDelay(1);
    ejectmotor.forward();
    Delay.msDelay(500);
    ejectmotor.stop();
}


public static void ejectForward1(){
ejectmotor.setSpeed(500);
Delay.msDelay(1);
ejectmotor.forward();
Delay.msDelay(600);
ejectmotor.stop();
} 

public static void ejectForward2(){
ejectmotor.setSpeed(500);
Delay.msDelay(1);
ejectmotor.forward();
Delay.msDelay(800);
ejectmotor.stop();
}
public static void ejectBack() {
ejectmotor.setSpeed(500);
Delay.msDelay(1);
ejectmotor.rotateTo(0);
}

//////////////////////EJECT MOTOR ENDS //////////////////////////////////////




/////////////////////////// FUZZY SENSOR HERE ////////////////////////////////////////


public static void fuzzyColorSensor() {

	



        double r1 = 0.0;
        double r2 = 0.0;
        double r3 = 0.0;
        double r4 = 0.0;
        double r5 = 0.0;


        double g1 = 0.0;
        double g2 = 0.0;
        double g3 = 0.0;
        double g4 = 0.0;
        double g5 = 0.0;


        double b1 = 0.0;
        double b2 = 0.0;
        double b3 = 0.0;
        double b4 = 0.0;
        double b5 = 0.0;

        int red =  0;
        int green = 0;
        int blue = 0;



    //    red = (int) r;
   //     green =(int) g;
   //     blue =(int) b;




        String s_red = "";
        String s_green = "";
        String s_blue = "";


        String result = "";


        if (red < 60)    // low red
        {

            r1 = (60.0-red)/60.0;
            r2 = red/60.0;
            r3 = 0;
            //    System.out.println("low red" + " " + r1 + " " + r2 + " " + r3);

            s_red = "low red";

        } else if (red >= 60 && red < 115) {   // middle red

            r1 = 0;
            r2 = (115 - red) / 55.0;
            r3 = (red-60.0)/55.0;
            //   System.out.println("middle red" + " " + r1 + " " + r2 + " " + r3);

            s_red = "middle red";

        } else {


            r1=0;
            r2=0;
            r3=1;

            s_red = "full red";
        }

        



        if (blue < 10.5)    // low blue
        {

            b1 = (10.5-blue)/10.5;
            b2 = (blue/10.5);
            b3 = 0;
            //     System.out.println("low blue" + " " + b1 + " " + b2 + " " + b3);

            s_red = "low blue";

        } else if (blue >= 10.5 && blue < 19.5) {   // middle red

            b1 = 0;
            b2 = (19.5 - blue) / 9.0;
            b3 = (blue-10.5)/9.0;
            //      System.out.println("middle blue" + " " + b1 + " " + b2 + " " + b3);

            s_blue = "middle blue";

        } else {  // full red


            b1=0;
            b2=0;
            b3=1;

            s_blue = "full blue";
        }


        
        
        
        
        if (green < 20)    // low red
        {

            g1 = (20.0-green)/20.0;
            g2 = green/20.0;
            g3 = 0;
            //     System.out.println("low green" + " " + r1 + " " + r2 + " " + r3);

            s_green = "low green";

        } else if (green >= 20 && green < 30) {   // middle green

            g1 = 0;
            g2 = (30.0 - green) / 10.0;
            g3 = (green-20.0)/10.0;
            //    System.out.println("middle green" + " " + r1 + " " + r2 + " " + r3);

            s_green = "middle green";

        } else {


            g1=0;
            g2=0;
            g3=1;

            s_green = "full green";

        }
}



void reverseMovements () {
	 int count_l =counter_g;
	 counter_temp = count_l;
	
	
	 double d1=0.0,d2=0.0,d3=0.0;
	 
	 
	 // Get the new membership degrees
	  if (count_l>0 && count_l < 20.0) {
        //  System.out.println("LOW " + counter_g);

          d1 = (20.0-count_l) / 20.0;
          d2 = count_l/20.0;
          d3 = 0;

       //   speed = 250;
          System.out.println(d1 + " " + d2 + " " + d3);




      //    double result_max = find_max(d1,d2,d3);

      //    System.out.println("result-max"+result_max+"speed"+speed);

      //    System.out.println ( (int)Math.round(speed*(result_max)) );
      //    int result = ((int)Math.round(speed*(result_max)));

      //    System.out.println("result"+result);

        //  action(result);


      } else if (count_l >= 20.0 && count_l < 40.0) {

        //  System.out.println("MEDIUM " + ultra);


          d1 = 0;
          d2 = (40.0 - count_l) / 20.0;
          d3 = (count_l-20.0)/20.0;

      //    speed = 350;
     //     System.out.println(d1 + " " + d2 + " " + d3);


      //    double result_max = find_max(d1,d2,d3);

      //    System.out.println("result-max"+result_max+"speed"+speed);

         // System.out.println ( (int)Math.round(speed*(result_max)) );
        //  int result = ((int)Math.round(speed*(result_max)));

       //   System.out.println("result"+result);

        //  action(result);


      } 
	  
	  
	  	 // Clear the previous percepts
	  
	        removePerceptsByUnif("sortAgent",Literal.parseLiteral("reverseBack(low,_)"));
	        removePerceptsByUnif("sortAgent",Literal.parseLiteral("reverseBack(medium,_)"));
            removePerceptsByUnif("sortAgent",Literal.parseLiteral("reverseBack(high,_)"));

         
        	Literal reverseBack1 = Literal.parseLiteral("reverseBack(low," + d1 + ")");
			Literal reverseBack2 = Literal.parseLiteral("reverseBack(medium," + d2 + ")");
			Literal reverseBack3 = Literal.parseLiteral("reverseBack(high," + d3 + ")");
			
			
			revd1_g=d1;
			revd2_g=d2;
			revd3_g=d3;
			 
			
		
			// Add the new membership degrees to the beliefs
			addPercept("sortAgent",reverseBack1);
			addPercept("sortAgent",reverseBack2);
			addPercept("sortAgent",reverseBack3);

}




public  void reverseMovementsAction (double my_result) throws IOException{

	//motor1.stop();
	System.out.println("REVERSE ACTIONS");
	 Delay.msDelay(10);
    motor1.rotate(((int)my_result*-1));
    Delay.msDelay(10);
    
	 
	 motor1.rotate(((int)my_result));
	    Delay.msDelay(10);

    
    saveBrick(my_result);
    
    
    
 //   motor1.rotate((int)my_result);
 //   Delay.msDelay(10);
    
 //   saveBrick();

  //  motor1.forward();


}





    
}
