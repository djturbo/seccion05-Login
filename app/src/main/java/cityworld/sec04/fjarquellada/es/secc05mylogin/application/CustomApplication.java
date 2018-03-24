package cityworld.sec04.fjarquellada.es.secc05mylogin.application;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by francisco on 24/3/18.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(3000);
    }
}
