package cityworld.sec04.fjarquellada.es.secc05mylogin.util;

import android.content.SharedPreferences;

/**
 * Created by francisco on 24/3/18.
 */

public class UtilPrefs {

    public static String getUserMailPref(SharedPreferences preferences){
        return preferences.getString("key_mail", "");
    }
}
