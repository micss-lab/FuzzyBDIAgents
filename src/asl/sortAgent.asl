// Agent sortAgent in project productionLine

/* Initial beliefs and rules */


isit(Is) :- reverseBack(Is,S1) & not(reverseBack(_,S2) & S2>S1).



isitRed(Is) :- colorRed(Is,S1) & not(colorRed(_,S2) & S2>S1).

isitGreen(Is) :- colorGreen(Is,S1) & not(colorGreen(_,S2) & S2>S1).

isitBlue(Is) :- colorBlue(Is,S1) & not(colorBlue(_,S2) & S2>S1).



dropped(false).
shredEndSent(false).
count_time(0).
reverse_triggered(false).

colorDecided(false).

//colorValue(0).

/* Initial goals */

//!status.

/* Plans */



+!status : dropped(false) <-  !status.
+!status : dropped(true) <-   .print("MESSAGE SORT - dropped"); !samplecolor.		

+!contsamplecolor: true<- -+colorDecided(false); !samplecolor.


																																//; 
												
+!samplecolor: colorDecided(CDS)  & CDS==false  <-  ?rgb(RR,GG,BB); .print("Collecting",RR,GG,BB); sampleColor; reverseMovements; .wait(20);  !decidecolorF.//  ?rgb(RR,GG,BB); .print(RR,GG,BB);  .print("---------Sampled Color Value =-------------", "R ", RR, "G ", GG, "B ",BB);  //  ?count_time(M); .print(M); New_time=M+1; -+count_time(New_time);;    // 



-!samplecolor <- !samplecolor. // .drop_all_intentions; 



// Replace this "!colorBucket(2) !toPushF(3,Color);" !toBuild for PushForce Experiment
+!decidecolorF: isitRed(high) & isitGreen(low) & isitBlue(medium)    <-   .print(" $$$$$ RED 1 -HIGH- $$$$$"); resetCounter; .wait(10);sampleColor;?colorRed(high,Color);  ?colorGreen(low,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20); !samplecolor.  

+!decidecolorF: isitRed(high) & isitGreen(medium) & isitBlue(medium)    <-   .print(" $$$$$ RED 2 -HIGH- $$$$$");resetCounter; .wait(10);sampleColor;?colorRed(high,Color);  ?colorGreen(medium,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20);  !samplecolor. 

+!decidecolorF: isitRed(high) & isitGreen(high) & isitBlue(low)    <-   .print("$$$$$ RED 3 -HIGH- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(high,Color); ?colorGreen(high,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20);   !samplecolor. 

+!decidecolorF: isitRed(medium) & isitGreen(high) & isitBlue(low)    <-   .print(" $$$$$ RED 7 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor;  ?colorRed(medium,Color);  ?colorGreen(high,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20); !samplecolor. 

+!decidecolorF: isitRed(medium) & isitGreen(medium) & isitBlue(low)    <-   .print(" $$$$$ RED 4 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color);   ?colorGreen(medium,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20);  !samplecolor. 

////////
+!decidecolorF: isitRed(medium) & isitGreen(veryhigh) & isitBlue(low)    <-   .print(" $$$$$ RED 9 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color);   ?colorGreen(medium,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20);  !samplecolor.
+!decidecolorF: isitRed(high) & isitGreen(veryhigh) & isitBlue(low)    <-   .print(" $$$$$ RED 10 -HIGH- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color);   ?colorGreen(medium,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20);  !samplecolor.

//mor

+!decidecolorF: isitRed(medium) & isitGreen(medium) & isitBlue(medium)    <-   .print(" $$$$$ PURPLE 4 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(medium,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor. // Karistirma noktasi

+!decidecolorF: isitRed(medium) & isitGreen(high) & isitBlue(medium)    <-   .print("$$$$$ PURPLE 5 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor. 

+!decidecolorF: isitRed(high) & isitGreen(high) & isitBlue(medium)    <-   .print("$$$$$ PURPLE 2 -HIGH- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(high,Color); ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor. //?? Karistirma noktasi Kirmizi

+!decidecolorF: isitRed(medium) & isitGreen(medium) & isitBlue(high)    <-   .print("$$$$$ PURPLE 7 -MEDIUM- $$$$$ ");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(medium,Color2); ?colorBlue(high,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);  !samplecolor. 

+!decidecolorF: isitRed(high) & isitGreen(medium) & isitBlue(low)    <-   .print("$$$$$ PURPLE 3 -HIGH- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(high,Color); ?colorGreen(medium,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor.

+!decidecolorF: isitRed(medium) & isitGreen(veryhigh) & isitBlue(medium)    <-   .print("$$$$$ PURPLE 13 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(medium) & isitGreen(veryhigh) & isitBlue(high)    <-   .print("$$$$$ PURPLE 14 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(high) & isitGreen(veryhigh) & isitBlue(high)    <-   .print("$$$$$ PURPLE 15 -MEDIUM- $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color); -+colordecided(true); .wait(20);   !samplecolor.




// FOR Light Green 


+!decidecolorF: isitRed(medium) & isitGreen(ultramedium) & isitBlue(medium)    <-   .print("####################### LIGHT GREEN 1 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color);  ?colorGreen(ultramedium,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toBuild; -+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(medium) & isitGreen(ultralow) & isitBlue(medium)    <-   .print("####################### LIGHT GREEN 2 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(ultralow,Color2); ?colorBlue(medium,Color3);.print(Color," ",Color2," ",Color3);  !toBuild; -+colordecided(true); .print(Color," ",Color2," ",Color3); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(medium) & isitGreen(ultramedium) & isitBlue(medium)    <-   .print("####################### LIGHT GREEN 3 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(ultralow,Color2); ?colorBlue(medium,Color3);.print(Color," ",Color2," ",Color3);  !toBuild; -+colordecided(true); .print(Color," ",Color2," ",Color3); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(medium) & isitGreen(ultrahigh) & isitBlue(medium)    <-   .print("####################### LIGHT GREEN 4 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(medium,Color); ?colorGreen(ultralow,Color2); ?colorBlue(medium,Color3);.print(Color," ",Color2," ",Color3);  !toBuild; -+colordecided(true); .print(Color," ",Color2," ",Color3); .wait(20);   !samplecolor.

// Middle Green

+!decidecolorF: isitRed(low) & isitGreen(ultralow) & isitBlue(medium)    <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color); ?colorGreen(ultralow,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(low) & isitGreen(ultramedium) & isitBlue(low)    <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 2 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color); ?colorGreen(ultramedium,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(low) & isitGreen(ultralow) & isitBlue(low)    <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 3 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color); ?colorGreen(ultralow,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(low) & isitGreen(ultramedium) & isitBlue(medium)    <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 4 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color); ?colorGreen(ultramedium,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toBuild;-+colordecided(true); .wait(20);   !samplecolor.

// Dark Green


+!decidecolorF: isitRed(low) & isitGreen(high) & isitBlue(low)    <-   .print("KKKKKKKKKK DARK GREEN 2 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color); ?colorGreen(high,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color2);-+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(low) & isitGreen(high) & isitBlue(medium)    <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");resetCounter;.wait(10);sampleColor; ?colorRed(low,Color);  ?colorGreen(high,Color2); ?colorBlue(medium,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color2);-+colordecided(true); .wait(20);   !samplecolor.
+!decidecolorF: isitRed(low) & isitGreen(veryhigh) & isitBlue(low)    <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");resetCounter;.wait(10);sampleColor;?colorRed(low,Color);  ?colorGreen(veryhigh,Color2); ?colorBlue(low,Color3); .print(Color," ",Color2," ",Color3); !toPushF(3,Color2);-+colordecided(true);  .wait(20);  !samplecolor.

-!decidecolorF: true <-   .drop_all_intentions;   !samplecolor.// .print(" !!!!!!!!!!!!!!!!!! No new Color!!!!!!!!!!!!!!!!!!! ");





+!decideApplyReverse(Threshold) : Threshold> 5 <- .print("Reverse");  !decideRev.
+!decideApplyReverse(Threshold) : Threshold <= 5 <- .print("Not Reverse"); !samplecolor. 


+!decideRev: isit(low)   & reverseBack(low,D1) & D1>0   <-   ?reverseBack(low,D1); .send(shredAgent,achieve,emergencyLocked); .send(shredAgent,achieve,shredend); .print("rev low");  reverseMovementsAction(275,1-D1);  .send(shredAgent,achieve,emergencyUnlocked);  !samplecolor.

+!decideRev: isit(medium)  & reverseBack(medium,D1) & D1>0 <-    ?reverseBack(medium,D1); .send(shredAgent,achieve,emergencyLocked); .send(shredAgent,achieve,shredend); .print("rev medium");   reverseMovementsAction(325,D1); .send(shredAgent,achieve,emergencyUnlocked);   !samplecolor.

+!decideRev: isit(high) & reverseBack(high,D1) & D1>0   <-   ?reverseBack(high,D1); .send(shredAgent,achieve,emergencyLocked);  .send(shredAgent,achieve,shredend); .print("rev  fast");     reverseMovementsAction(350,D1);  .send(shredAgent,achieve,emergencyUnlocked); !samplecolor.

-!decideRev: true <- .print("decideRev Failure",DD); !samplecolor.



+!colorbucket(F): F==5 <- .print("Sending to the Build Agent");!toBuild;+toBuild(true).

+!colorbucket(F): F\==5 & F\==0 <- .print("Sending to the Bucket and PushAgent",F);!toPush(F);+toPush(true).

+!colorbucket(F): F < 2 <- !samplecolor.


+dropped(M)[source(dropAgent)] : true
   <- .print("Message from ",dropAgent,": ",M);
      -msg(M);-+dropped(true);!status.
      
      
      
+buildFree(M)[source(buildAgent)] : true
   <- .print("Message from ",buildAgent,": ",M);
      -msg(M);.abolish(buildFree(_)); -+buildFree(M).
      

      
      
+!shredend: true <- .send(shredAgent,tell,shredEnd(true)).
+!shredstart: true <- .send(shredAgent,tell,shredEnd(false)).

				
					
+!toPush(Ph):  Ph==2 <-  goBluePosition;    .print(" Blue   - A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop);  .send(pushAgent,achieve,pushForce(PV)); !samplecolor. //!samplecolor.      //  !shredstart; .send([pushAgent],achieve,push). // go to samplecolor.
+!toPushF(Ph,PV):  Ph==3 <-  goGreenPosition;   .print(" Green  - A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop);  .send(pushAgent,achieve,pushForce(PV)); !samplecolor .  //!samplecolor. // .send(pushAgent,achieve,push); .send(dropAgent,achieve,loop);
+!toPush(Ph):  Ph==4 <-  goYellowPosition;   .print(" Yellow - A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop); .send(pushAgent,achieve,push); !samplecolor . //.send(pushAgent,achieve,push); !shredstart. //!samplecolor.
+!toPush(Ph):  Ph\==2 |Ph\==3 |Ph\==4 <-     .print("A message should be sent to Push Agent."); -+colorDecided(false); !samplecolor.

+!toBuild: buildFree(true) <-  goRedPosition;  .send(buildAgent,achieve,build); .print("A message should be sent to Build Agent."); -+colorDecided(false); !samplecolor.    //.send([buildAgent],achieve,build). // go to samplecolor.
+!toBuild: buildFree(false) <-  goYellowPosition;  .send(pushAgent,achieve,push); .print("Build is not free RED is discarded"); -+colorDecided(false); !samplecolor. 
						         

