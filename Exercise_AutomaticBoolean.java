import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Exercise_AutomaticBoolean {


    public class MyThread extends Thread{

        @Override
        public void run() {
            super.run();
            System.out.println("my Thread start");
        }
    }
    private static class BarWorker implements Runnable {

        private static boolean exists = false;

        private String name;

        public BarWorker(String name) {
            this.name = name;
        }

        public void run() {
            if (!exists) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    // do nothing
                }
                exists = true;
                System.out.println(name + " enter");
                try {
                    System.out.println(name + " working");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    // do nothing
                }
                System.out.println(name + " leave");
                exists = false;
            } else {
                System.out.println(name + " give up");
            }
        }

    }


    private static class BarWorkAutomatic implements Runnable{

        private String  name ;

        public BarWorkAutomatic(String name) {
            this.name = name;
        }

        private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        @Override
        public void run() {
            try {
                if (atomicBoolean.compareAndSet(false, true)) {
                    System.out.println(name + "Enter");
                    try {
                        System.out.println(name+"working");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e1) {
                        // do nothing
                    }
                    System.out.println(name+"leave");
                    atomicBoolean.set(false);
                }
            } catch (Exception e) {
                System.out.println(name+"give up ");
                e.printStackTrace();
            }


        }
    }


    private static class BarWorkerTest implements Runnable {

        private static AtomicBoolean exists = new AtomicBoolean(false);

        private String name;

        public BarWorkerTest(String name) {
            this.name = name;
        }

        public void run() {
            if (exists.compareAndSet(false, true)) {
                System.out.println(name + " enter");
                try {
                    System.out.println(name + " working");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    // do nothing
                }
                System.out.println(name + " leave");
                exists.set(false);
            }else{
                System.out.println(name + " give up");
            }
        }

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        System.out.println("----程序开始运行----");

        //1: to test the muti-thread, I use the run method
/*        new  Thread(new BarWorker("Test1")).start();
        new Thread(new BarWorker("Test2")).start();*/

        //my code ,no exception so can not follow the other thread
 /*       new Thread(new BarWorkAutomatic("AutoTest1")).start();
        new Thread(new BarWorkAutomatic("AutoTest2")).start();*/

        // the other code, we can see the give up operation
        new Thread(new BarWorkerTest("AutoTest1")).start();
        new Thread(new BarWorkerTest("AutoTest2")).start();
    }

    }
