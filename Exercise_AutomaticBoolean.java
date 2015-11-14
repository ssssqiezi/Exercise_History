import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        System.out.println("----程序开始运行----");

        new  Thread(new BarWorker("Test1")).start();
        new Thread(new BarWorker("Test2")).start();


    }

    }
