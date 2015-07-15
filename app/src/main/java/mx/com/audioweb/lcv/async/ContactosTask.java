package mx.com.audioweb.lcv.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import mx.com.audioweb.lcv.Libreta_direcciones;
import mx.com.audioweb.lcv.data.ClienteHttp;
import mx.com.audioweb.lcv.data.Contacto;
import mx.com.audioweb.lcv.data.SharedPreferencesExecutor;

/**
 * Created by Juan Acosta on 8/20/2014.
 */
public class ContactosTask extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private ArrayList<Contacto> contactos;
    private String message;

    public ContactosTask(Context context, ArrayList<Contacto> contactos) {
        this.context = context;
        this.contactos = contactos;

    }

    @Override
    protected Boolean doInBackground(String... params) {
        String usr_id = params[0];

        try {
            ClienteHttp clienteHttp = new ClienteHttp();
            this.contactos = clienteHttp.contacto(usr_id);
            if (this.contactos == null) {
                this.message = "No existen contactos en tu libreta de direcciones";
            } else {
                SharedPreferencesExecutor<Contacto> shaEx = new SharedPreferencesExecutor<Contacto>(this.context);
                for (Contacto contacto : contactos) {
                    shaEx.saveData(Contacto.class.getName() + "_" + contacto.getPb_id(), contacto);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Contactos", "Error cargando contactos");
        } catch (Exception e) {
            Log.e("Contactos", "Error inesperado");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("ContactosTask", "Entro onPostExecute");
        this.context.startActivity(new Intent(this.context, Libreta_direcciones.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
