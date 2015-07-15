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
import java.util.Calendar;
import java.util.HashMap;

import mx.com.audioweb.lcv.async.ReportesTask;
import mx.com.audioweb.lcv.data.SessionManager;

public class Reportes_Ac extends Activity {

    SimpleDateFormat d, m, a, mm;
    SessionManager session;
    TextView code, mes, minutos, conferencias, llamadas, participantes;
    String Month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = new SessionManager(getApplicationContext());
        Calendar c = Calendar.getInstance();

        d = new SimpleDateFormat("dd");
        m = new SimpleDateFormat("MM");
        a = new SimpleDateFormat("yyy");

        String Mes = m.format(c.getTime());
        String Dia = d.format(c.getTime());
        String Anio = a.format(c.getTime());
        String f1 = Anio + "-" + Mes + "-01";
        String f2 = Anio + "-" + Mes + "-" + Dia;
        HashMap<String, String> user = session.getuserDetails();
        String codigo = user.get(SessionManager.KEY_MODERATOR);


        new ReportesTask(getApplicationContext(), f1, f2, codigo);
        String minuto = user.get(SessionManager.KEY_MINUTOS);
        String conf = user.get(SessionManager.KEY_CONFERENCIA);
        String part = user.get(SessionManager.KEY_PARTICIPA);
        String llamada = user.get(SessionManager.KEY_LLAMADA);

        int foo = Integer.parseInt(Mes);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Reportes de Ac");
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


        switch (foo) {
            case 1:
                Month = "Enero";
                break;
            case 2:
                Month = "Febrero";
                break;
            case 3:
                Month = "Marzo";
                break;
            case 4:
                Month = "Abril";
                break;
            case 5:
                Month = "Mayo";
                break;
            case 6:
                Month = "Junio";
                break;
            case 7:
                Month = "Julio";
                break;
            case 8:
                Month = "Agosto";
                break;
            case 9:
                Month = "Septiembre";
                break;
            case 10:
                Month = "Octubre";
                break;
            case 11:
                Month = "Noviembre";
                break;
            case 12:
                Month = "Diciembre";
                break;
        }

        code = (TextView) findViewById(R.id.TextCliente);
        mes = (TextView) findViewById(R.id.TextMes);
        minutos = (TextView) findViewById(R.id.TextMinutos);
        conferencias = (TextView) findViewById(R.id.TextConferencias);
        llamadas = (TextView) findViewById(R.id.TextLlamadas);
        participantes = (TextView) findViewById(R.id.TextParticipantes);
        code.setText("#" + codigo);

        if (minuto != "null") {
            minutos.setText(minuto);
        } else {
            minutos.setText("0");
        }
        conferencias.setText(conf);
        llamadas.setText(llamada);
        participantes.setText(part);
        mes.setText("Reportes del mes de " + Month);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
