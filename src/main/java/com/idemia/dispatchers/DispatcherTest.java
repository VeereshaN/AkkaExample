package com.idemia.dispatchers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.idemia.faulttolerancetest.ChildActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class DispatcherTest  {

    public static void main(String[] args){

        Config config = ConfigFactory.parseFile(new File("application.conf"));
        Config akkaConfig = config.withFallback(ConfigFactory.load());
        ActorSystem system = ActorSystem.create("dispacther",akkaConfig);
       ActorRef actor =  system.actorOf(Props.create(ChildAActor.class),"childa");
      System.out.println(system.getDispatcher());
        long startTime = System.currentTimeMillis();
       for(int i=0;i<1000000;i++){
          actor.tell("jjj",ActorRef.noSender());
       }
        long stopTime = System.currentTimeMillis();
       System.out.println("Time Taken " + (stopTime-startTime));

   //   System.out.println(system.dispatcher().toString());


//        ActorRef myActor =
//                system.actorOf(Props.create(ChildActor.class).withDispatcher("my-dispatcher"),
//                        "myactor3");
//        System.out.println( system.dispatchers().lookup("my-dispatcher"));





     //   Config conf = ConfigFactory.load("application.conf");
      //  System.out.println(conf.getString("akka.actor.debug.lifecycle"));
    }
}
