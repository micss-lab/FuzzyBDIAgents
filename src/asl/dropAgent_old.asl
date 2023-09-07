// Agent dropAgent in project deneme

/* Initial beliefs and rules */


//productReady(false).
//dropButtonStatus(false).
motor(drop,false).
motor(init,false).
conveyorStart(false).
done(false).

/* Initial goals */

!basla.

+!basla: true<- .print("dropRuns"); buttonPressed.

/* Plans */

//+!start : true <- .print(dropMotor).


+done(M)[source(initAgent)] : true        // 1
   <- .print(" 1) DONE Message from ",initAgent,": ",M);
      -done(M);!checkButtonStatus.  //!senseDrop; iptal et.
      
+!checkButtonStatus  : dropButtonStatus(false) <-.print("state 1"); buttonPressed; !checkButtonStatus.
+!checkButtonStatus  : dropButtonStatus(true) <- .print("state 1 is true");!checkProductStatus.
+!checkProductStatus : true  <- !checkProductStatus.      


+!loop: true <- productState;!checkProductStatus.
      
+!checkProductStatus : productReady(false) <- .print("2 -Product State"); productState; ?productReady(Y); .print("***BP- Product State =",Y,"***");!checkProductStatus.      

+!checkProductStatus : dropButtonStatus(true) & productReady(true)<- .print("3"); .print(" 3-) Drop process Runs - Conveyor Starts \n"); conveyorStart; -+conveyorStart(true); !motorDrop.

//+!checkProductStatus : dropButtonStatus(true) & productReady(true)<- .print("3"); .print(" 3-) Drop process Runs - Conveyor Starts \n"); conveyorStart; -+conveyorStart(true);  .send([shredAgent,sortAgent],tell,dropped(true)); !motorDrop.


// 

+dropButtonStatus(true): true <- .print("Print").

//+!senseDrop:  true <- senseDrop; ?productReady(D); .print(" 3-) Product State ",D). // button and productReady can also be checked here. 
      

+!motorDrop : true <- .print(" 5-) Drop Motor: Drops \n"); dropProduct; !dropped.

//+!motorDrop : motor(drop,true) <- .print(" 6-) Drop Motor Goes Back \n"); !dropped.      //.abolish(motor(_,_)); // .abolish(motor(_,_)); +asd(asd);
      
+!dropped: true <- .print("LAST-) Drop Cycle Ends "); !endCycle. //sortAgent'i ekle!! //.send([shredAgent,sortAgent],tell,dropped(true));  -+dropButtonStatus(false); +productReady(0); 
+!endCycle : true <- .print(" Cycle Ended \n").


//?productReady(P);

// done mesaji gelirse dropSensor'den degerleri sense et.

