package mx.com.audioweb.lcv;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Unirse_Reunion extends Activity {

    ImageButton unirse;
    TextView pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ur);

        pin = (TextView) findViewById(R.id.pinText);
        unirse = (ImageButton) findViewById(R.id.unirseButton);
        unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npin = pin.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                //callIntent.setData(Uri.parse("tel:018000832002" + PhoneNumberUtils.PAUSE + npin + "#"));
                callIntent.setData(Uri.parse("tel:5528814600"+ PhoneNumberUtils.PAUSE +npin +"#"));
                startActivity(callIntent);
                Log.i("LLAMANDO ", callIntent.toString());
            }
        });

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Unirse a Reunion");
        ImageButton imageButton = (ImageButton) mCustomView
                .findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

}
