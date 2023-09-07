// Agent pushAgent in project productionLine

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */




+!push : true <- .wait(100); pushMotor;.print("Actuating push motor : Brick is PUSHED");.send(dropAgent,achieve,loop).

+!pushForce(X) : true <- .wait(100); pushMotorVal(X);.print("Actuating push motor : Brick is PUSHED 2"); .send(dropAgent,achieve,loop).



+done(M)[source(initAgent)] : true        // 1
   <- .print(" 1) DONE Message from ",initAgent,": ",M);
      -done(M).  

