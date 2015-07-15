package mx.com.audioweb.lcv;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import mx.com.audioweb.lcv.data.SessionManager;

public class Llamar_Ac extends Activity {

    TextView numOrg, numPart;
    SessionManager session;
    ImageButton IC, SP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar_);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Llamar Ac");
        ImageButton imageButton = (ImageButton) mCustomView
                .findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Llamar_Ac.this, DashBoard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getuserDetails();
        final String organizador = user.get(SessionManager.KEY_MODERATOR);
        String participante = user.get(SessionManager.KEY_ACCES);

        numOrg = (TextView) findViewById(R.id.num_organizador);
        numPart = (TextView) findViewById(R.id.num_participante);
        IC = (ImageButton) findViewById(R.id.imageIniciarConf);
        SP = (ImageButton) findViewById(R.id.imageButton2);


        numOrg.setText(organizador);
        numPart.setText(participante);

        IC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:5528814600" + PhoneNumberUtils.PAUSE + organizador + "#,#"));
                startActivity(callIntent);
                Log.i("LLAMANDO ", callIntent.toString());
            }
        });

        SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:018000832007"));
                startActivity(callIntent);
                Log.i("LLAMANDO ", callIntent.toString());
            }
        });
    }

}
