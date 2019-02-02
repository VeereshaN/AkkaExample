package com.idemia.beverage;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class CoffeeActor extends AbstractActor {
    private static int coffee =1;
    public static Props props(){
        return Props.create(CoffeeActor.class);
    }
    public Receive createReceive() {
        return receiveBuilder().match(String.class,s->{
            if(coffee<=0){
                getSender().tell("Coffee is empty!!! Try other drinks", getSelf());
            }else {
                coffee = coffee - 1;
                System.out.println("Your coffee is served.. Have nice day!!");
            }
            getContext().stop(getSelf());
        }).build();
    }
}
