package com.idemia.faulttolerancetest;

import akka.actor.*;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import static akka.pattern.Patterns.ask;

public class MainForTestingSupervision {

    public static void main(String args[]){
        Props superprops = Props.create(ParentActor.class);
        ActorSystem system = ActorSystem.create("supervisorsystem");
        ActorRef parentactor = system.actorOf(superprops, "parentactor");
       parentactor.tell(new String("Idemia"),ActorRef.noSender());

      //  parentactor.tell(PoisonPill.getInstance(),ActorRef.noSender());

    }
}
