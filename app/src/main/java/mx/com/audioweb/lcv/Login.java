package mx.com.audioweb.lcv;


import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.ImageButton;

import mx.com.audioweb.lcv.async.LoginTask;
import mx.com.audioweb.lcv.data.SessionManager;

public class Login extends Activity {

    EditText User, Pass;
    ImageButton LogIn;
    SessionManager session;
    int backButton = 0;
    String Conexion_URL = "do Login URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        User = (EditText) findViewById(R.id.Usuario);
        Pass = (EditText) findViewById(R.id.Password);

        LogIn = (ImageButton) findViewById(R.id.IB);

        session = new SessionManager(getApplicationContext());

        LogIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String usr = User.getText().toString();
                String pass = Pass.getText().toString();

                if (usr.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Alguno de los campos vacios", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    login(usr, pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void login(String usr, String pass) throws JSONException {
        new LoginTask(getApplicationContext(), usr, pass, LogIn).execute();
    }

}


