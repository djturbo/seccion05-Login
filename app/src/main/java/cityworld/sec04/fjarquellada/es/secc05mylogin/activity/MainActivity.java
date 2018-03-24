package cityworld.sec04.fjarquellada.es.secc05mylogin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cityworld.sec04.fjarquellada.es.secc05mylogin.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return true;
    }

    private void logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void deleteLoginPrefs(){
        this.prefs.edit().clear().apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean res = false;

        switch(item.getItemId()){
            case R.id.item_forget_logout:
                deleteLoginPrefs();
                logout();
                break;
            case R.id.item_logout:
                logout();
                break;
            default:
                res = super.onOptionsItemSelected(item);
                break;
        }
        return res;
    }
}
