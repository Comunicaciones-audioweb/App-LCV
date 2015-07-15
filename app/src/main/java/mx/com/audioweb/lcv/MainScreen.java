package mx.com.audioweb.lcv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainScreen extends Activity implements View.OnClickListener {

    ImageButton UR, VR, AC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        UR = (ImageButton) findViewById(R.id.imageBUr);
        VR = (ImageButton) findViewById(R.id.imageBVr);
        AC = (ImageButton) findViewById(R.id.imageBAc);

        VR.setOnClickListener(this);
        UR.setOnClickListener(this);
        AC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBUr:
                UR();
                break;
            case R.id.imageBVr:
                VR();
                break;
            case R.id.imageBAc:
                AC();
                break;
        }
    }

    public void UR() {
        Intent intent = new Intent(MainScreen.this, Unirse_Reunion.class);
        startActivity(intent);
    }

    public void VR() {
        Intent intent = new Intent(MainScreen.this, Login.class);
        startActivity(intent);
    }

    public void AC() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:018000832007"));
        startActivity(callIntent);
    }
}
