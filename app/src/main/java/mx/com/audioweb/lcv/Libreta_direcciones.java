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

import java.util.ArrayList;

import mx.com.audioweb.lcv.adapter.ContactListAdapter;
import mx.com.audioweb.lcv.adapter.Contact_Item;
import mx.com.audioweb.lcv.data.Contacto;
import mx.com.audioweb.lcv.data.SharedPreferencesExecutor;

public class Libreta_direcciones extends Activity {

    private ListView listDirecciones;
    private ArrayList<Contact_Item> contact_item;
    private ContactListAdapter adapter;
    private ArrayList<Contacto> contactos;
    private SharedPreferencesExecutor<Contacto> shaEx;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libreta_direcciones);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Libreta de direcciones");
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

        listDirecciones = (ListView) findViewById(R.id.libretaView);
        contact_item = new ArrayList<Contact_Item>();
        refresh();

    }

    public void refresh() {
        this.context = this;
        shaEx = new SharedPreferencesExecutor<Contacto>(context);
        contactos = shaEx.retriveAll(Contacto.class);
        contact_item = new ArrayList<Contact_Item>();
        Log.e("Contactos",String.valueOf(contactos));
        for (Contacto contacto : contactos) {
            contact_item.add(new Contact_Item(contacto.getPb_fname() + " " + contacto.getPb_lname(), contacto.getPb_ph_num(), contacto.getPb_email()));
            Log.e("LD", contacto.getPb_fname());
        }
        if(contact_item.size()>0){
        adapter = new ContactListAdapter(getApplicationContext(), contact_item);
        listDirecciones.setAdapter(adapter);}
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Libreta_direcciones.this);
            alertDialog.setTitle("Libreta de direcciones");
            alertDialog.setMessage("No tienes ningun contacto registrado");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            alertDialog.show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Libreta_direcciones.this, DashBoard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        shaEx.removeAll(Contacto.class);
    }
}
