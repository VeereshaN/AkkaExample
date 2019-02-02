package com.idemia.actorlifecycle;

import akka.actor.AbstractActor;

public class ActorLifeCycle extends AbstractActor {

    @Override
    public void preStart(){
    System.out.println("Actor is starting......");
    }

    @Override
    public void postStop(){
   System.out.println("Actor is stoping....");
    }



    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,s->{
            System.out.println("Sender : "+ getSender());
            System.out.println("Self : "+ getSelf());
        }).build();
    }
}
