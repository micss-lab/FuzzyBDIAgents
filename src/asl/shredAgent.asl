// Agent shredAgent in project productionLine

/* Initial beliefs and rules */


isit(T) :- speedEmg(T,D1) & not(speedEmg(_,D2) & D2>D1).

counter(0).
speed(300).

dropped(false).
conveyorRuns(false).
shredMotor(false).
shredConveyorStarted(false).
emergencyLocked(false).


/* Initial goals */


!status.





/* Plans */




+!status : dropped(false) <-  !status. //.print("Shred waits"); 
+!status : dropped(true)  <-  .print("Shredding is started.");-+shredMotor(true);!checkEmergency.//;!checkEmergency. //;!status


+!checkEmergency: true <- .wait(1);  ?counter(CounterValue);  checkEmergency(CounterValue); .wait(10);  !emergency. //
 
														
			 											
			
// For (stop) we directly stop the motor.								
				 											
+!emergency : isit(low) <- ?speedEmg(low,D1); .print("low triggered"); ?speed(Speed); changeConveyorSpeed(Speed,1-D1);  !checkEmergency.      //.print("No emergency conveyor is started again.");                       //startConveyor.


+!emergency : isit(high) <-?speedEmg(high,D2); .print("high triggered"); ?speed(Speed); changeConveyorSpeed(Speed,D2); !checkEmergency.

+!emergency :  dropped(false) | emergencyLocked(true) <- !checkEmergency. 

// For (free) we do not trigger anything as the system in the safe zone.	









+!emergencyLocked:   true   <- -+emergencyLocked(true).
+!emergencyUnlocked: true   <- -+emergencyLocked(false).



+!shredConveyorStart: shredConveyorStarted(false) <- conveyorStart;shredStart; -+shredConveyorStarted(true).
+!shredConveyorStart: shredConveyorStarted(true) <- .print("shred motor and conveyor motor already re-started.").






+dropped(M)[source(dropAgent)] : true
   <- .print("Message from ",dropAgent,": ",M);
      -msg(M);-+dropped(true);-+conveyorRuns(true);!status.
      
+shredEnd(C)[source(sortAgent)] : true
   <- .print("Message from ",sortAgent,": ",C);
      -msg(C);!shredend.
      
      

      
      
+!shredend: true <- .print("Shredder and Coveyor Motors Stop"); conveyorStop; shredStop;  .print("!!!!WAITING!!!!"); .wait(10);-+shredMotor(false);-+conveyorRuns(false); +shredReceived(true).  // after this plan, this agent should wait for done message again.  
+!shredend: false <- .print("Shredder and Coveyor Motors are Restarted"); conveyorStart; shredStart;  .wait(10);-+shredMotor(true);-+conveyorRuns(true); +shredReceived(true).

