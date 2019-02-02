package com.idemia.routing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.FromConfig;

public class Worker extends AbstractActor {




    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Work.class,s->{
            System.out.println("inside worker " + s.payload+" Self : " + getSelf());
           // getContext().stop(getSelf());
        }).build();
    }
}
