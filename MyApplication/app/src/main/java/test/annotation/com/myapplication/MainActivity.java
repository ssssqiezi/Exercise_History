package test.annotation.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ContentView(value=R.layout.activity_main)
public class MainActivity extends Activity {


    private static final String METHOD_SET_CONTENTVIEW = "setContentView";


    @ViewInject(R.id.test_txt)
    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        InjectContentView(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void InjectContentView(Activity activity){
        Class<? > clss = activity.getClass();
        //Class<? extends Activity> clss = activity.getClass();
        ContentView contentView = clss.getAnnotation(ContentView.class);
        //activity.setContentView(contentView.value());
        try {
            if(contentView!=null){
                //Method method = clss.getDeclaredMethod(METHOD_SET_CONTENTVIEW, int.class);
                int contentViewLayoutId = contentView.value();
                Method method = clss.getMethod(METHOD_SET_CONTENTVIEW, int.class);
                //wrong way
                //Method method = clss.getDeclaredMethod(METHOD_SET_CONTENTVIEW, int.class);
                method.setAccessible(true);
                //wrong direction
                try {
                    //spend some time debugging this exception
                    //log:
                    //java.lang.IllegalArgumentException: Expected receiver of type android.app.Activity, but got java.lang.Class<test.annotation.com.myapplication.MainActivity>
                    //method.invoke(clss, contentViewLayoutId);
                    method.invoke(activity, contentViewLayoutId);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }



}
