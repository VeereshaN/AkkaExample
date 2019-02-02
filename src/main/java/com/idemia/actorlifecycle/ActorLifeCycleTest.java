package com.idemia.actorlifecycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorLifeCycleTest {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("lifecycle");
        ActorSystem system1 = ActorSystem.create("lifecycle1");
      ActorRef ref1 =  system1.actorOf(Props.create(ActorLifeCycle.class),"actorlifecycle1");
      ActorRef ref = system.actorOf(Props.create(ActorLifeCycle.class),"actorlifecycle");
      ref.tell(new String(),ActorRef.noSender());
      ref1.tell(new String(),ActorRef.noSender());


    }
}
