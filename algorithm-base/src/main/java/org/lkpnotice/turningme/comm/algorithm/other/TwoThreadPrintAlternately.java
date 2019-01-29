package org.lkpnotice.turningme.comm.algorithm.other;

import org.lkpnotice.turningme.comm.algorithm.Execution;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liujinpeng on 2019/1/29.
 *
 *
 * Problem:given two threads ThreadA and ThreadB ,then Thread A print 1 ,ThreadB print2,ThreadA print 3 ,as so.
 *
 *
 */
public class TwoThreadPrintAlternately {
    Integer mutexA = new Integer(0);
    Integer mutexB = new Integer(1);


    /**
     * wait notify mode controll two threads to run alternatively
     *
     * which cause the threads context switch rapidly
     */
    void printWithWaitNotify(){

        Callable taskA = new Callable() {

            Boolean flag = true;

            public Object call() throws Exception {

                for (int i = 1 ;i<=100 ; i+=2){
                    waitIt();
                    System.out.println(String.format("%s-%s","ThreadA",i));

                    notifyIt();
                }

                return null;
            }



            void waitIt() throws InterruptedException {

                synchronized (mutexA){
                    while (!flag){
                        mutexA.wait();
                        flag = true;
                    }
                }

            }


            void notifyIt(){
                flag = false;
               // System.out.println("mutexB notify enter");
                synchronized (mutexB){
                    mutexB.notifyAll();
                }
            }

        };


         Callable taskB = new Callable() {
             Boolean flag = false;

             public Object call() throws Exception {

                 for (int i = 2 ;i<=100 ; i+=2){
                     waitIt();
                     System.out.println(String.format("%s-%s","ThreadA",i));

                     notifyIt();
                 }

                 return null;
             }



             void waitIt() throws InterruptedException {

                 synchronized (mutexB){
                     while (!flag){
                        // System.out.println("mutexB wait enter");
                         mutexB.wait();
                        // System.out.println("mutexB wait leave");
                         flag = true;
                     }
                 }

             }


             void notifyIt(){
                 flag = false;
                 synchronized (mutexA){
                     mutexA.notifyAll();
                 }

             }
         };


         ExecutorService executorService = Executors.newCachedThreadPool();
         executorService.submit(taskB);
         executorService.submit(taskA);


         executorService.shutdown();
    }








    static class PrintService{

        boolean flag = false;
        ReentrantLock lock = new ReentrantLock();
        Condition condA = lock.newCondition();
        Condition condB = lock.newCondition();

        int printAStart = 1;
        int PrintBStart = 2;

        boolean isPrintAEnd(){
            return printAStart > 100;
        }


        boolean isPrintBEnd(){
            return PrintBStart > 100;
        }

        void printA() throws InterruptedException {
            lock.lock();
            try {
                if (flag){
                    condA.await();
                }
                System.out.println(String.format("%s-%s","ThreadA",printAStart));
                printAStart += 2;

                flag = true;
                condB.signal();
            }finally {
                lock.unlock();
            }

        }



        void printB() throws InterruptedException {

            lock.lock();
            try {
                if (!flag){
                    condB.await();
                }

                System.out.println(String.format("%s-%s","ThreadB",PrintBStart));
                PrintBStart += 2;


                flag = false;
                condA.signal();
            }finally {
                lock.unlock();
            }

        }
    }




    static void printCondition(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final PrintService printService = new PrintService();

        Callable t1 = new Callable() {
            public Object call() throws Exception {
                while(!printService.isPrintAEnd()){
                    printService.printA();
                }

                return null;
            }
        };


        Callable t2 = new Callable() {
            public Object call() throws Exception {
                while(!printService.isPrintBEnd()){
                    printService.printB();
                }

                return null;
            }
        };


        executorService.submit(t1);
        executorService.submit(t2);
        executorService.shutdown();

    }




    public static void main(String[] args){
        //new TwoThreadPrintAlternately().printWithWaitNotify();


        new TwoThreadPrintAlternately(). printCondition();
    }


}
