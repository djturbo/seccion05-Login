package cityworld.sec04.fjarquellada.es.secc05mylogin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cityworld.sec04.fjarquellada.es.secc05mylogin.R;
import cityworld.sec04.fjarquellada.es.secc05mylogin.util.UtilPrefs;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_splash);

        this.prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        if("".equals(UtilPrefs.getUserMailPref(this.prefs))){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
        finish();
    }
}
