package com.idemia.sendmessages;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ThridActor extends AbstractActor {

    public static Props props(){
        return Props.create(ThridActor.class);
    }

    @Override
    public Receive createReceive(){
    return receiveBuilder().match(String.class,s->{
    System.out.println("working inside"+ self()+ " "+ self().path()+ " sender : " + getSender()+ "Path : "+ getSender().path());
    try {
        System.out.println("String is handled!!");
        getSender().tell(4, getSelf());
    }
    catch (Exception e){
        getSender().tell(new akka.actor.Status.Failure(e),getSelf());
        throw e;
    }
    }).match(Integer.class,i->{
        try {
            System.out.println("working inside"+ self()+ " "+ self().path()+ " sender : " + getSender()+ "Path : "+ getSender().path());
            System.out.println("Integer is handled here and value of integer is " + i);
            getSender().tell(i,getSelf());
        }catch (Exception e){
            System.out.println("Exception occured !!!!");
            getSender().tell(new akka.actor.Status.Failure(e),getSelf());
            throw e;
        }
      //
    }).build();
    }

}
