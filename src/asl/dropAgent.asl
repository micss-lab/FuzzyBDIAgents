// Agent dropAgent in project deneme

/* Initial beliefs and rules */


//productReady(false).
//dropButtonStatus(false).
motor(drop,false).
motor(init,false).
conveyorStart(false).
done(false).
dropButtonStatus(false).


/* Initial goals */

/* Plans */

//+!start : true <- .print(dropMotor).


+done(M)[source(initAgent)] : true        // 1
   <- .print(" 1) DONE Message from ",initAgent,": ",M);
      -done(M);!checkButtonStatus.  //!senseDrop; iptal et.
      
@chk1 [atomic]	      
+!checkButtonStatus : dropButtonStatus(false)[source(percept),source(self)]  <-.print("state 1"); buttonPressed; .drop_all_intentions; !checkButtonStatus. 
@chk2 [atomic]	  
+!checkButtonStatus : dropButtonStatus(true)[source(percept)] <- .print("state 1 is true");!checkProductStatus.
@chk3 [atomic]	  
-!checkButtonStatus <- .print("Failure plan");!checkButtonStatus.

+!loop: true <- productState; !checkProductStatus.
      
 
@cpk1 [atomic]	        
+!checkProductStatus : productReady(false) <- .print("2 -Product State"); productState; ?productReady(Y); .print("***BP- Product State =",Y,"***");!checkProductStatus.      
@cpk2 [atomic]	  
+!checkProductStatus : dropButtonStatus(true) & productReady(true)<- .print("3"); .print(" 3-) Drop process Runs - Conveyor Starts \n"); conveyorStart; -+conveyorStart(true); shredStart; -+shredStart(true); .send([shredAgent,sortAgent],tell,dropped(true)); !motorDrop.
@cpk3 [atomic]	  
-!checkProductStatus <- .wait(10); !checkProductStatus.

+dropButtonStatus(true): true <- .print("DropStatus TRUE").

//+!senseDrop:  true <- senseDrop; ?productReady(D); .print(" 3-) Product State ",D). // button and productReady can also be checked here. 
      

+!motorDrop : true <- .print(" 5-) Drop Motor: Drops \n"); dropProduct; !dropped.

//+!motorDrop : motor(drop,true) <- .print(" 6-) Drop Motor Goes Back \n"); !dropped.      //.abolish(motor(_,_)); // .abolish(motor(_,_)); +asd(asd);
      
+!dropped: true <- .print("LAST-) Drop Cycle Ends "); !endCycle.  //.send([shredAgent,sortAgent],tell,dropped(true));  -+dropButtonStatus(false); +productReady(0); 
+!endCycle : true <- .print(" Cycle Ended \n").




