package com.idemia.routing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RouterTest {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("routing");
//   ActorRef master= system.actorOf(Props.create(Master.class),"master");
//    master.tell(new Work("hello"),ActorRef.noSender());

       ActorRef workers = system.actorOf(Props.create(Workers.class),"workers");
       ActorRef parent = system.actorOf(Props.create(Parent.class),"parent");
     parent.tell(new String("Hi"),ActorRef.noSender());
    }
}
