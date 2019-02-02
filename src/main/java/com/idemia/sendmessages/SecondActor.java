package com.idemia.sendmessages;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;

import javax.naming.Context;

public class SecondActor extends AbstractActor {



    @Override
    public void preStart() {
        //ActorRef ref1 = context().actorOf(FirstActor.props("prestart"),"prestart");
      //  System.out.println("Inside Second actor ");
    }

    @Override
    public void postStop() {
       // System.out.println("Stopped second actor");
    }

    public static Props props() {
        return Props.create(SecondActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Integer.class, s -> {
            System.out.println(s + " Working in " + getSelf() + "Sender path " + getSender().path() + " sender " + getSender());

        }).matchEquals("stop",s->{
            System.out.println(getSelf() +" "+ getSender());
           getContext().stop(getSelf());

        }).build();
    }
}
