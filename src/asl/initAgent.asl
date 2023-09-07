// Agent initAgent in project productionLine

/* Initial beliefs and rules */
// System belives that sensors and actuators are not configured.

/* ~inputButton.
~dropSensor.
~dropMotor.
~conveyorBelt1.
~conveyorBelt2.
~ultraSonicSensor.
~shredderMotor.
~colorSensor.
*/
//~initComponents.
/* Initial goals */

!init.

/* Plans */

+!init
   : true <- .print("Init Agent runs"); initialize_comps; !done. //single-shot plan.
   
      //: true <- initialize_comps; .print("Init Agent runs");!done. //single-shot plan.

 //+!done: true <- .send(dropAgent,achieve,startDropMotor).
 //+!done: true <- .send([dropAgent,pushAgent],tell,done(true)).
 
  // TEST+!done: true <- .wait(2000); goRedPosition; .print("Message Sent").
  
   +!done: true <- .wait(12000); .send([dropAgent],tell,done(true));.print("Message Sent to Drop Agent"). // ORJINAL
