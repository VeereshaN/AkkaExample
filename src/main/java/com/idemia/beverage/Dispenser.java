package com.idemia.beverage;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import static akka.pattern.Patterns.ask;


public class Dispenser extends AbstractActor {

    public static Props props(){
        return Props.create(Dispenser.class);
    }

    public Receive createReceive() {
        return receiveBuilder().matchEquals("tea",s->{
            ActorRef teaActor = getContext().actorOf(TeaActor.props(),"teaActor");
            teaActor.tell("getTea",getSelf());
        }).matchEquals("coffee",v->{
            ActorRef coffeeActor = getContext().actorOf(CoffeeActor.props(),"coffeeActor");
            coffeeActor.tell("getCoffee",getSelf());
        }).match(String.class,s->{
            System.out.println(s);
        }).build();
    }
}
