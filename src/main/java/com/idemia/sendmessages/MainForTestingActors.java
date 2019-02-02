package com.idemia.sendmessages;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

public class MainForTestingActors {
    public static void main(String[] args){
        //create ActorSystem
        ActorSystem system = ActorSystem.create("exampleActor");

        //Create Actors
        ActorRef secondActor = system.actorOf(SecondActor.props(),"thirdFromMain");
        ActorRef firstActor = system.actorOf(FirstActor.props(secondActor),"first");
        firstActor.tell(new String("ask"),ActorRef.noSender());
     //   firstActor.tell("aliveActors",ActorRef.noSender());
      //  ActorSelection c = system.actorSelection("akka://exampleActor/user/first/actorC");

       // system.terminate();
     //   FirstActor.getAliveActors();
    }
}
