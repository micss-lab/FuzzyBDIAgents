

/* Initial beliefs and rules */

/* Initial goals */

buildStatus(0).
buildFree(true).




/* Plans */



+!arrangePosition: true <- resetPosition; .print("Build initializes itself"); !awareSortAgent. // reset first




+!build : true <- -+buildFree(false); !awareSortAgent; ?buildStatus(M); .print("M= ",M); K = M+1; -+buildStatus(K); .print("K=", K);!state.

+!state : buildStatus(L) & L ==1 <- mediumPress; .print("Press 1  ",L); .send(dropAgent,achieve,loop);  -+buildFree(true); !awareSortAgent. //state_one 

+!state : buildStatus(L) & L ==2 <- secondPress; .print("Press 2  " ,L);!eject; -+buildFree(true); !awareSortAgent.




+!eject : true <- .print("Eject"); -+buildStatus(0); ejectProduct; .send(dropAgent,achieve,loop). //state_eject


 +!awareSortAgent: true <- ?buildFree(Bf); .send(sortAgent,tell,buildFree(Bf)).