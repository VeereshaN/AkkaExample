package com.idemia.routing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.FromConfig;
import akka.routing.RoundRobinPool;

import java.util.Arrays;
import java.util.List;

public class Parent extends AbstractActor {


    // #paths
    List<String> paths = Arrays.asList("/user/workers/w1", "/user/workers/w2", "/user/workers/w3");
    // #paths

    // #round-robin-pool-1
    ActorRef router1 = getContext().actorOf(FromConfig.getInstance().props(Props.create(Worker.class)), "router1");
    // #round-robin-pool-1

    // #round-robin-pool-2
    ActorRef router2 = getContext().actorOf(new RoundRobinPool(5).props(Props.create(Worker.class)), "router2");
    // #round-robin-pool-2


    // #round-robin-group-1
    ActorRef router3 = getContext().actorOf(FromConfig.getInstance().props(), "router3");
    // #round-robin-group-1



    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,k->{
            for(int i=0;i<4;i++) {
                router3.tell(new Work("Hi"), getSelf());
            }
        }).build();
    }
}
