package com.idemia.routing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.routing.*;

import java.util.ArrayList;
import java.util.List;

public class Master extends AbstractActor {

    Router router;
    {
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 10; i++) {
            ActorRef r = getContext().actorOf(Props.create(Worker.class),"router"+i);
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        System.out.println("routers created ");
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }
    public Receive createReceive() {
        return receiveBuilder()
                .match(Work.class, message -> {
                    System.out.println("message "+ message);
                    for(int i=0;i<11;i++) {
                        router.route(message, getSelf());
                    }
                }).match(Terminated.class, message -> {
                    System.out.println(message.actor());
                    router = router.removeRoutee(message.actor());
                    ActorRef r = getContext().actorOf(Props.create(Worker.class));
                    getContext().watch(r);
                    router = router.addRoutee(new ActorRefRoutee(r));
                }).build();
    }
}
