/**
 * Created by lenovo on 2015/12/7.
 */
public class ThreadLocalTest {

    private int sysncInt =0 ;

    public int getSyncInt(){
        return sysncInt;
    }

    public void setSyncInt(int syncInt){
        this.sysncInt =  syncInt;
    }

    static class Threadtest extends Thread{

        private ThreadLocalTest sn;

        public Threadtest(ThreadLocalTest sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i <= 3; i++) {
                // ④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getSyncInt() + "]");
                sn.setSyncInt(i);
            }
        }

    }


    public static void startTestThread(){

        //错误
/*        new Threadtest(new ThreadLocalTest()).start();
        new Threadtest(new ThreadLocalTest()).start();
        new Threadtest(new ThreadLocalTest()).start();*/

        //正确的调用方式
        Threadtest test1 = new Threadtest(new ThreadLocalTest());
        Threadtest test2 = new Threadtest(new ThreadLocalTest());
        Threadtest test3 = new Threadtest(new ThreadLocalTest());

        test1.start();
        test2.start();
        test3.start();



    }

}



