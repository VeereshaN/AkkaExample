package com.idemia.faulttolerancetest;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;

import java.time.Duration;

public class ParentActor extends AbstractActor {

    public static SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.ofMinutes(1),
            DeciderBuilder.match(ArithmeticException.class,e->SupervisorStrategy.resume())
                    .match(NullPointerException.class,e->SupervisorStrategy.restart())
                    .match(IllegalArgumentException.class,e->SupervisorStrategy.stop())
                    .match(ActorKilledException.class,e->SupervisorStrategy.restart())
                    .matchAny(o->SupervisorStrategy.escalate())
                    .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }


    ActorRef childActor = getContext().actorOf(ChildActor.props(),"childactor");
   // ActorRef childActorB = getContext().actorOf(ChildActorB.props(),"childactorb");
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,s->{
             childActor.tell(new Exception(),ActorRef.noSender());
           // System.out.println("inside the string match");
           // childActor.tell(PoisonPill.getInstance(),ActorRef.noSender());
        }).build();
    }
}
