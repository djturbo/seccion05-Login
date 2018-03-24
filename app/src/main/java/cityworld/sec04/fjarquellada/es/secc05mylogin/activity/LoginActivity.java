package cityworld.sec04.fjarquellada.es.secc05mylogin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cityworld.sec04.fjarquellada.es.secc05mylogin.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRememberme;
    private Button btnLogin;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.pref = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        this.bindUI();
        this.editTextEmail.setText(this.pref.getString("key_mail", "").toString());
        this.editTextPassword.setText(this.pref.getString("key_password", "").toString());
        this.switchRememberme.setChecked(this.pref.getBoolean("key_rememberme", false));

        this.btnLogin.setOnClickListener(this);
    }

    private void bindUI(){
        this.editTextEmail    = findViewById(R.id.editTextMail);
        this.editTextPassword = findViewById(R.id.editTextPassword);
        this.switchRememberme = findViewById(R.id.switchRememberme);
        this.btnLogin         = findViewById(R.id.btnLogin);
    }

    private boolean login(){
        boolean res = true;
        res = isEmailValid(res);
        if(!res){
            Toast.makeText(this, "Email no válido", Toast.LENGTH_LONG).show();
            return res;
        }
        res = isPasswordValid(res);
        if(!res){
            Toast.makeText(this, "Password no válida", Toast.LENGTH_LONG).show();
            return res;
        }
        return res;
    }

    private boolean isPasswordValid(boolean res) {
        if(TextUtils.isEmpty(this.editTextPassword.getText()) ||
                this.editTextPassword.getText().length() < 4){
            res = false;
        }
        return res;
    }

    private boolean isEmailValid(boolean res) {
        if(TextUtils.isEmpty(this.editTextEmail.getText()) ||
                !Patterns.EMAIL_ADDRESS.matcher(this.editTextEmail.getText()).matches()){
            res = false;
        }
        return res;
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void savePreferences(String mail, String password){
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("key_mail", mail);
            edit.putString("key_password", password);
            edit.putBoolean("key_rememberme", true);
            // Guarda Asincronamente
            edit.apply();
            // Guarda sincronamente
            // edit.commit();
    }
    private void removePreferences(){
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("key_mail", "");
            edit.putString("key_password", "");
            edit.putBoolean("key_rememberme", false);
            // Guarda Asincronamente
            edit.apply();
            // Guarda sincronamente
            // edit.commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.btnLogin.getId()){
            if(login()){
                Toast.makeText(this, "Autenticación correcta.", Toast.LENGTH_LONG).show();
                if(this.switchRememberme.isChecked()){
                    savePreferences(this.editTextEmail.getText().toString(), this.editTextPassword.getText().toString());
                }else {
                    removePreferences();
                }
                goToMain();
            }
        }
    }
}
