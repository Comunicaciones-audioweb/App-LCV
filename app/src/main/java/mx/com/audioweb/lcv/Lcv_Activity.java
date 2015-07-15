package mx.com.audioweb.lcv;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.com.audioweb.lcv.adapter.ParticipanteListAdapter;
import mx.com.audioweb.lcv.adapter.Participante_Item;
import mx.com.audioweb.lcv.data.Participante;
import mx.com.audioweb.lcv.data.SharedPreferencesExecutor;

public class Lcv_Activity extends Activity {

    private ListView listParticipante;
    private ArrayList<Participante_Item> participante_item;
    private ParticipanteListAdapter adapter;
    private ArrayList<Participante> participantes;

    private SharedPreferencesExecutor<Participante> shaEx;
    Context context;
    private AlertDialogManager alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo_vivo);

        alert = new AlertDialogManager();
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Monitoreo en Vivo");
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
        Log.e("LCV", "Entro");
        listParticipante = (ListView) findViewById(R.id.monitoreoView);
        participante_item = new ArrayList<Participante_Item>();
        refreshLcv();
    }

    public void refreshLcv() {
        this.context = this;
        Log.e("LCV", "ENTRO REFRESH");
        Boolean data = true;
        shaEx = new SharedPreferencesExecutor<Participante>(context);
        participantes = shaEx.retriveAll(Participante.class);
        participante_item = new ArrayList<Participante_Item>();
        Log.e("LCV", "FOR");
        for (Participante participante : participantes) {
            if (participante.getMi_join_time() == null) {
                data = false;
                break;
            }
            participante_item.add(new Participante_Item(participante.getMi_id(), participante.getMi_join_time(), participante.getMi_mem_name()));
        }
        Log.e("LCV", "SALIO FOR");
        if (participante_item.size() > 0) {
            adapter = new ParticipanteListAdapter(getApplicationContext(), participante_item);
            listParticipante.setAdapter(adapter);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Lcv_Activity.this);
            alertDialog.setTitle("Live Conference View");
            alertDialog.setMessage("No hay ningun participante en la conferencia");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            alertDialog.show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Lcv_Activity.this, DashBoard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
