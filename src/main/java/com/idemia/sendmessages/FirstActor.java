package com.idemia.sendmessages;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.dispatch.sysmsg.Terminate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class FirstActor extends AbstractActor {

    private  List<ActorRef> listOfActors = new ArrayList<>();
    private final ActorRef actorRef;
    public FirstActor(ActorRef name){
        actorRef = name;
    }
    public static Props props(ActorRef name){
        return Props.create(FirstActor.class,()->new FirstActor(name));
    }
    public Receive createReceive() {
        return receiveBuilder().match(String.class,p->{
            System.out.println(p+ " Working in "+ self()+ ", Sender : " + sender() +" "+ getSender() + " pathOfSender : "+ getSender().path());
            CompletableFuture<Object> s = ask(context().actorOf(ThridActor.props(),"cal"),10,Duration.ofMillis(1000)).toCompletableFuture();
            CompletableFuture<Object> n = ask(context().actorOf(ThridActor.props(),"sub"),9,Duration.ofMillis(1000)).toCompletableFuture();
            System.out.println("hi>>>"+context().watch(actorRef));
            CompletableFuture<Integer>  result =CompletableFuture.allOf(s,n).thenApply(j ->{

                           int d = (Integer)s.join();
                           int e =(Integer)n.join();
                           return d+e;

            });
                   System.out.println("result is " +result.join());

          //  actorRef.tell(s.toString(),getSelf());
          // actorRef.forward("forward to 3nd actor",getContext());
        }).matchEquals("watch",s->{
            ActorRef actorA = getContext().actorOf(SecondActor.props(),"actorA");
           ActorRef actorB = getContext().actorOf(SecondActor.props(),"actorB");
           ActorRef actorC = getContext().actorOf(SecondActor.props(),"actorC");
            listOfActors.add(actorA);
            listOfActors.add(actorB);
            listOfActors.add(actorC);
            getContext().watch(actorA);
            getContext().watch(actorB);
            getContext().watch(actorC);
            actorA.tell("stop",getSelf());
            actorB.tell("stop",getSelf());
            actorC.tell(23,getSelf());

//            Iterable<ActorRef> childActors = getContext().getChildren();
//           Iterator itr = childActors.iterator();
//           while(itr.hasNext()){
//               System.out.println("Alive child actors are : " +itr.next());
//           }
          //  System.out.println("Alive childrens are : ");
         //   s123.forEach(sb-> System.out.println(sb));
        }).match(Terminated.class,s->{
            listOfActors.remove(s.getActor());
            System.out.println("Dead actor : " + s.getActor());
         //   System.out.println("aliveActors : "+listOfActors.get(0));
        }).matchEquals("aliveActors",s->{
            listOfActors.forEach(v->System.out.println("Alive actor name : "+ v));
        }).build();
    }


}
