package com.idemia.actordeath;

import akka.actor.AbstractActor;
import akka.actor.PoisonPill;

public class MasterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(PoisonPill.class,p->{
            System.out.println("inside the posi");
        }).build();
    }
}
