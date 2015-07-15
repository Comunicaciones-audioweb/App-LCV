package mx.com.audioweb.lcv.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.com.audioweb.lcv.data.ClienteHttp;
import mx.com.audioweb.lcv.Reportes_Ac;
import mx.com.audioweb.lcv.data.SessionManager;

/**
 * Created by Juan Acosta on 8/14/2014.
 */
public class ReportesTask extends AsyncTask<String, Void, Boolean> {

    private static final String KEY_MIN = "minutos";
    private static final String KEY_CALL = "llamadas";
    private static final String KEY_CONF = "conferencias";
    private static final String KEY_PART = "participantes";

    private Context context;
    private String f1, f2, codigo, message;

    public ReportesTask(Context ctx, String f1, String f2, String codigo) {
        this.context = ctx;
        this.f1 = f1;
        this.f2 = f2;
        this.codigo = codigo;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        try {
            jsonArray = ClienteHttp.reporteAc(ClienteHttp.URL, this.f1, this.f2, this.codigo);
            String value, m, c, l, p;
            SessionManager session = new SessionManager(this.context);
            jsonObject = jsonArray.getJSONObject(0);
            value = jsonObject.getString("minutos");

            if (value != null) {
                m = jsonObject.getString(KEY_MIN);
                c = jsonObject.getString(KEY_CONF);
                l = jsonObject.getString(KEY_CALL);
                p = jsonObject.getString(KEY_PART);
                session.crearReporte(m, c, l, p);
                return false;
            } else {
                Log.d("method", "error");
                return true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            this.message = "Ocurrio un Error en el Login: JSON Parser";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.message = "Error Inesperado";
            return true;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("ReportesTask", "Entra omPOst Excecute...");
        if (!result) {

            this.context.startActivity(new Intent(this.context, Reportes_Ac.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        }
        //Toast.makeText(this.context, this.message, Toast.LENGTH_LONG).show();
    }
}
