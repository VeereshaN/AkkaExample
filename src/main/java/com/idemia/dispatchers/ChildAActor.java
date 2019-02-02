package com.idemia.dispatchers;

import akka.actor.AbstractActor;

public class ChildAActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,k->{
          //  System.out.println(k);
        }).build();
    }
}
