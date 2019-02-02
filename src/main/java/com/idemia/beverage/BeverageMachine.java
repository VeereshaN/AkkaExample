package com.idemia.beverage;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.Scanner;

public class BeverageMachine {

    public static void main(String args[]){
       //create actor system
        ActorSystem system = ActorSystem.create("BeverageMachine");
        String drinkType;
       ActorRef dispenser = system.actorOf(Dispenser.props(),"dispenser");
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your drink name or press 1 to exit");
        drinkType = input.next();
        while(!( drinkType.equals("1"))){
            dispenser.tell(drinkType,ActorRef.noSender());
            drinkType = input.next();
        }
        System.exit(1);





    }
}
