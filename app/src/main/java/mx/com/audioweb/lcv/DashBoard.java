package mx.com.audioweb.lcv;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import mx.com.audioweb.lcv.async.ContactosTask;
import mx.com.audioweb.lcv.async.LCVTask;
import mx.com.audioweb.lcv.async.ReportesTask;
import mx.com.audioweb.lcv.data.Contacto;
import mx.com.audioweb.lcv.data.Participante;
import mx.com.audioweb.lcv.data.SessionManager;
import mx.com.audioweb.lcv.data.SharedPreferencesExecutor;


public class DashBoard extends Activity implements View.OnClickListener {
    private ArrayList<Contacto> contactos;
    private ArrayList<Participante> participantes;
    ImageButton Iac, Lcv, Ld, Rc;
    SessionManager session;
    int backButton = 0;
    private static ArrayList<Activity> activities = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("SOPORTE MONITOREO");
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

        Iac = (ImageButton) findViewById(R.id.image_iac);
        Lcv = (ImageButton) findViewById(R.id.image_lcv);
        Ld = (ImageButton) findViewById(R.id.image_ld);
        Rc = (ImageButton) findViewById(R.id.image_rc);

        Iac.setOnClickListener(this);
        Lcv.setOnClickListener(this);
        Ld.setOnClickListener(this);
        Rc.setOnClickListener(this);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_iac:
                abrirIAC();
                break;
            case R.id.image_lcv:
                abrirLCV();
                break;
            case R.id.image_ld:
                abrirLD();
                break;
            case R.id.image_rc:
                abrirRC();
                break;
        }
    }

    public void abrirIAC() {
        session.getuserDetails();
        Intent intent = new Intent(DashBoard.this, Llamar_Ac.class);
        startActivity(intent);
    }

    public void abrirLCV() {
        HashMap<String, String> user = session.getuserDetails();
        String codigo = user.get(SessionManager.KEY_MODERATOR);
        SharedPreferencesExecutor<Participante> shaEx = new SharedPreferencesExecutor<Participante>(getApplicationContext());
        shaEx.removeAll(Participante.class);
        new LCVTask(getApplicationContext(), this.participantes).execute(codigo);
    }



    public void abrirLD() {
        HashMap<String, String> user = session.getuserDetails();
        String usr_id = user.get(SessionManager.KEY_ID);
        new ContactosTask(getApplicationContext(), this.contactos).execute(usr_id);

    }

    public void abrirRC() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat d = new SimpleDateFormat("dd");
        SimpleDateFormat m = new SimpleDateFormat("MM");
        SimpleDateFormat a = new SimpleDateFormat("yyy");
        String Mes = m.format(c.getTime());
        String Dia = d.format(c.getTime());
        String Anio = a.format(c.getTime());
        String f1 = Anio + "-" + Mes + "-01";
        String f2 = Anio + "-" + Mes + "-" + Dia;
        HashMap<String, String> user = session.getuserDetails();
        String codigo = user.get(SessionManager.KEY_MODERATOR);
        new ReportesTask(getApplicationContext(), f1, f2, codigo).execute();
    }

    public void onBackPressed() {
        Intent intent = new Intent(DashBoard.this, MainScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        session.EraseData();
        startActivity(intent);
    }
}
