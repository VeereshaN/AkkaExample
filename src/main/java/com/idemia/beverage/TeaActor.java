package com.idemia.beverage;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class TeaActor extends AbstractActor {
    private static int tea =1;

    public static Props props(){
        return Props.create(TeaActor.class);
    }
    public Receive createReceive() {
        return receiveBuilder().match(String.class,s->{
            if(tea<=0){
                getSender().tell("Tea is empty!! try other drinks",getSelf());
            }else {
                tea = tea - 1;
                System.out.println("Your Tea is served.. Have nice day!!");
            }
            getContext().stop(getSelf());
        }).build();
    }
}
