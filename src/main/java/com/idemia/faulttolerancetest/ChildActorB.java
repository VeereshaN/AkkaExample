package com.idemia.faulttolerancetest;

import akka.actor.AbstractActor;
import akka.actor.Kill;
import akka.actor.PoisonPill;
import akka.actor.Props;

import java.awt.*;

public class ChildActorB extends AbstractActor {


    @Override
    public void preStart(){
        System.out.println("child  actor B started");
    }

    @Override
    public void postStop(){
        System.out.println("child actor B Stopped");
    }


    @Override
    public void postRestart(Throwable o){
        System.out.println("restarted the child actor B");
    }


    public static Props props(){
        return Props.create(ChildActorB.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Exception.class,exception->{
            System.out.println("Inside the exception match");
            // getContext().stop(getSelf());
            throw new NullPointerException();
        }).match(String.class,s->{
            System.out.println("Inside the String match");
        }).build();
    }
}
