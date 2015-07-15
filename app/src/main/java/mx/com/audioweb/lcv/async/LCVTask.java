package mx.com.audioweb.lcv.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import mx.com.audioweb.lcv.Lcv_Activity;
import mx.com.audioweb.lcv.data.ClienteHttp;
import mx.com.audioweb.lcv.data.Participante;
import mx.com.audioweb.lcv.data.SharedPreferencesExecutor;

/**
 * Created by Juan Acosta on 8/20/2014.
 */
public class LCVTask extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private ArrayList<Participante> participantes;
    private String message;

    public LCVTask(Context context, ArrayList<Participante> participantes) {
        this.context = context;
        this.participantes = participantes;

    }

    @Override
    protected Boolean doInBackground(String... params) {
        String acnum = params[0];

        try {
            ClienteHttp clienteHttp = new ClienteHttp();
            this.participantes = clienteHttp.participante(acnum);
            if (this.participantes == null) {
                this.message = "No hay nadie conectado";
            } else {
                SharedPreferencesExecutor<Participante> shaEx = new SharedPreferencesExecutor<Participante>(this.context);
                Log.e("LCVTask","Entra For");
                for (Participante participante : participantes) {
                    shaEx.saveData(Participante.class.getName() + "_" + participante.getMi_member_id(), participante);
                }
                for(Participante participante : participantes){
                    Log.d("LCVTask", participante.getMi_mem_name());
                    Log.d("LCVTask", participante.getMi_access_number());
                    Log.d("LCVTask", participante.getMi_member_id());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("LCV", "Error cargando contactos");
        } catch (Exception e) {
            Log.e("LCV", "Error inesperado");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("LCVTask", "Entro onPostExecute");
        this.context.startActivity(new Intent(this.context, Lcv_Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
