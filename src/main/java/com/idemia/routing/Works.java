package com.idemia.routing;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Works extends AbstractActor {

    @Override
    public void preStart() {
        getContext().actorOf(Props.create(Worker.class), "w1");
        getContext().actorOf(Props.create(Worker.class), "w2");
        getContext().actorOf(Props.create(Worker.class), "w3");
    }
    // ...
    // #create-worker-actors

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
