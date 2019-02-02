package com.idemia.faulttolerancetest;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.awt.*;

public class ChildActor extends AbstractActor {
    private int state=0;

    @Override
    public void preStart(){
        System.out.println("child actor started");
    }

    @Override
    public void postStop(){
        System.out.println("child actor Stopped");
    }


    @Override
    public void postRestart(Throwable o){
        System.out.println("restarted the child");
    }


    public static Props props(){
        return Props.create(ChildActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(Exception.class,exception->{
                 System.out.println("Inside the exception match");
               // getContext().stop(getSelf());
                 throw new NullPointerException();
                })
                .match(Integer.class,i->{state =i;})
                .matchEquals("get",s->{getSender().tell(state,getSelf());})
                .build();
    }
}
