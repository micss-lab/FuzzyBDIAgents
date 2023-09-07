// Agent init2Agent in project productionLine

/* Initial beliefs and rules */

/* Initial goals */

!init.

/* Plans */

+!init
   : true <- .print("Init 2 Agent runs"); initialize2_comps; .send(buildAgent,achieve,arrangePosition). //single-shot plan.
   
      //: true <- initialize_comps; .print("Init Agent runs");!done. //single-shot plan.

 //+!done: true <- .send(dropAgent,achieve,startDropMotor).
 //+!done: true <- .send([dropAgent,pushAgent],tell,done(true)).
 
  // TEST+!done: true <- .wait(2000); goRedPosition; .print("Message Sent").
  
   //+!done: true <- .wait(2000); .send([dropAgent],tell,done(true));.print("Message Sent to Drop Agent"). // ORJINAL