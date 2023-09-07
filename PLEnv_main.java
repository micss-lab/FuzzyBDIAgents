

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


//Jason Imports Start
import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.invoke.StringConcatFactory;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Logger;
//Jason Imports End
import java.util.stream.IntStream;

/*
 * 
 *         //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                motorLeft.stop();
                motorRight.stop();
            }
        }));
 */

public class PLEnv extends Environment {
	
	
	
	static EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S2); // DropButton
	static final EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S1); //drop sensor
	static EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);
	static EV3ColorSensor color = new EV3ColorSensor(SensorPort.S4); 
	
	
	static  boolean call_mode =false;										//drop sensor
    
    
        //sort Agent Color Sensor
    static  boolean call_mode2 =false;
    
    
	
	static EV3MediumRegulatedMotor motor1 = new EV3MediumRegulatedMotor(MotorPort.B); // Conveyor 1  Motor 
	static EV3LargeRegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.D); // Conveyor 2  Motor 
	
	
	
	
	static EV3MediumRegulatedMotor dropmotor = new EV3MediumRegulatedMotor(MotorPort.A); // DropMotor
	//static EV3LargeRegulatedMotor shredermotor = new EV3LargeRegulatedMotor(MotorPort.C); // ShredMotor

	
	
	static EV3MediumRegulatedMotor pushmotor = new EV3MediumRegulatedMotor(MotorPort.C);
	
	
	
	
	
	
	
	
	public static final Term motorB = Literal.parseLiteral("actuate(motorB)");
	public static final Term initComponents = Literal.parseLiteral("initialize_comps");
	public static final Term senseDrop = Literal.parseLiteral("senseDrop");
	
	public static final Term conveyorStart = Literal.parseLiteral("conveyorStart");
	public static final Term conveyorStop = Literal.parseLiteral("conveyorStop");
	public static final Term conveyorReverse = Literal.parseLiteral("conveyorReverse");
	
	
	
	
	
	
	public static final Term sampleColor = Literal.parseLiteral("sampleColor");
	public static final Term buttonPressed = Literal.parseLiteral("buttonPressed");
	
	public static final Term productState = Literal.parseLiteral("productState");
	public static final Term dropProduct = Literal.parseLiteral("dropProduct");
	
	public static final Term shredStart = Literal.parseLiteral("shredStart");
	public static final Term shredStop = Literal.parseLiteral("shredStop");
	
	public static final Term checkEmergency = Literal.parseLiteral("checkEmergency"); //motor1.stop();
	
	
	
	//push Agent
	
	public static final Term pushMotor = Literal.parseLiteral("pushMotor");
	
	//sort Agent
	
	public static final Term goRedPosition = Literal.parseLiteral("goRedPosition");
	
	
	public static final Term goBluePosition = Literal.parseLiteral("goBluePosition");
	public static final Term goGreenPosition = Literal.parseLiteral("goGreenPosition");
	public static final Term goYellowPosition = Literal.parseLiteral("goYellowPosition");
	
	
	
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
	
	 @Override
	    public void init(String[] args) {
		// final EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
    // motorBact  = new EV3LargeRegulatedMotor(MotorPort.B);
		 System.out.println("Sensor modes and Motors are initializing"); 
		 rand = new Random();
		 
		 clearAllPercepts();
		 updatePercepts();
		 isPressed();
		 Color_Sensor();
		 Drop_Sensor(); // always initialize
		 check_Emergency(); // check Emergency
		 
		 
		 tryRGB();
		 
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
		        sensor.getRGBMode();
		        SampleProvider sp = sensor.getColorIDMode();	
		        
				
		        call_mode=true;
		        }
		        
				

		        int sampleSize = sensor.sampleSize();
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
		            
		            removePercept("dropAgent",Literal.parseLiteral("productReady(true)"));
	        		removePercept("dropAgent",Literal.parseLiteral("productReady(false)"));
		        	//removePerceptsByUnif("dropAgent",Literal.parseLiteral("productReady(_)"));
	        		//System.out.println(drop_result);
	        		addPercept("dropAgent",Literal.parseLiteral("productReady("+drop_result+")"));
	            
	        		idcolor=0;
		    }
		   
		
	 
	 
	 private void Color_Sensor() {	 		
		 

		        if (!call_mode2){
		            color.getRGBMode();
		            call_mode2=true;
		        }
		    int readcolorlist[]= new int[15];
		    int idcolor = 0;
		    int sum =0;

		    
		   
		    for (int i=0;i<15;i++) {
		    	idcolor = color.getColorID();
		    	System.out.println(idcolor);
		        readcolorlist[i]=idcolor;
		        sum +=readcolorlist[i];	
		       // LocalTime now = LocalTime.now();
		       // DateTimeFormatter cr_time = DateTimeFormatter.ofPattern("HH:mm:ss");
		       // index = index + 1;	
		    } 
		       // if (index==10){
		
		    
		        
		      sum = (int)sum/15;
		      System.out.println("sum"+sum);
		        	
		        	
		           
		        	removePerceptsByUnif("sortAgent",Literal.parseLiteral("colorValue(_)"));
		 		    addPercept("sortAgent",Literal.parseLiteral("colorValue("+sum+")"));
		        	
		          /*  if (x==3|| x==4|| x==5 ||x==6) {
		                System.out.printf(String.valueOf(index),idcolor);
		                System.out.println("Retval : "+x);
		              //  dev1.retVal = x
		            }
		                else{
		               // dev1.retVal = 0.0
		            }*/
		           // }
		 		    
		 		// count++;
					// System.out.println(count);
		        }
		    
		     

	 public static void readcolor(){
		    color.getColorID();
		    System.out.println("Color readed"+color.getColorID());
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
		
		logger.info(ag+" doing: "+ action);
        try {
            if (action.equals(motorB)) {
            	actuate_MotorB();  
            	
            } 
           
            else if(action.equals(initComponents)) {
            	
            	initComponents_func();
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
            
            else if(action.equals(conveyorReverse)) {
            	
            	conveyorMotor1_reverse();	
            }
            
            
            else if(action.equals(sampleColor)) {
            	
            	Color_Sensor();
            }
            
            else if(action.equals(buttonPressed)) {
            	
            	isPressed();
            }
            
		    else if(action.equals(productState)) {
		            	
			  Drop_Sensor();
            }
            
		    else if(action.equals(dropProduct)) {
		    	
		      Drop_Product();
		    } 
		    
		    else if(action.equals(shredStart)) {
		    	
			  Shred_Start();
			      
		    }
            
		    else if(action.equals(shredStop)) {
		    	
			
		      Shred_Stop();				      
		    }            
            
		    else if(action.equals(checkEmergency)) {
		    	
			  check_Emergency();
				      
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
            
            
            //push agent
            
		    else if(action.equals(pushMotor)) {
		    	
		    	push();
					      
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
	
	private void conveyorStop_func() {
		
		motor1.stop();
		Delay.msDelay(1);
	}
	
	private void conveyorMotor1_reverse() {
		
		motor1.setSpeed(900);		
		motor1.rotate(-360);
		Delay.msDelay(1);
		
		conveyorStart_func();
	}
	
	

	private void Shred_Stop() {
		
		
	//	 shredermotor.stop();
		 Delay.msDelay(1);
	}

	private void check_Emergency() {
		
		
		  Delay.msDelay(1);
		  SampleProvider sp = ultrasonicSensor.getDistanceMode();
          int distanceValue = 0;
        
          float[] sample = new float[sp.sampleSize()];

          sp.fetchSample(sample, 0);
          distanceValue = (int) sample[0];

          System.out.println("Distance: " + distanceValue);
            
          removePerceptsByUnif("shredAgent",Literal.parseLiteral("ultraSonicEmergency(_)"));
          addPercept("shredAgent",Literal.parseLiteral("ultraSonicEmergency("+distanceValue+")"));
           
         //if (distanceValue<100) {
           
	}

	/* private void senseDrop_func() {

		 
		
		
	}*/

	private void Shred_Start() {
		
		//shredermotor.setSpeed(400);
        Delay.msDelay(5);
        //shredermotor.forward();
		
	}

	private void Drop_Product() {
		
		dropmotor.setSpeed(800);
		dropmotor.rotateTo(180);
		Delay.msDelay(10);
		dropmotor.rotateTo(0);
		
		
	//	 dropmotor.setSpeed(500);
	  //      Delay.msDelay(1);
	  //      dropmotor.rotate(360);
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
        Delay.msDelay(950);
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
        Delay.msDelay(1250);
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
	
	
	
	
	
	
	

	private void initComponents_func() {
		System.out.println("Drop Motor is resetting...");
		
		/* reset drop motor*/
		//dropmotor.setSpeed(100);
        //Delay.msDelay(1);
        //dropmotor.rotateTo(0); 
		
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
    
    
}
