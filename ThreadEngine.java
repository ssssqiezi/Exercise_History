import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jie on 2015/11/16.
 */
public enum  ThreadEngine {

    INSTANCE ;

    private volatile ExecutorService mThreadService=null;

    private ThreadEngine() {
        try {
            if(mThreadService==null){
                mThreadService=Executors.newCachedThreadPool();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExecutorService getInstance(){
        return  mThreadService;
    }
}
