package mx.com.audioweb.lcv.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.com.audioweb.lcv.data.ClienteHttp;
import mx.com.audioweb.lcv.DashBoard;
import mx.com.audioweb.lcv.data.SessionManager;

/**
 * Created by Juan Acosta on 8/11/2014.
 */
public class LoginTask extends AsyncTask<String, Void, Boolean> {

    private static final String KEY_ID = "usr_id";
    private static final String KEY_ACCES = "usr_access_code";
    private static final String KEY_MODERATOR = "usr_moderator_pin";

    private Context context;
    private String acUser;
    private String acPass;
    private String message;
    private ImageButton loginButton;

    public LoginTask(Context ctx, String acUser, String acPass, ImageButton loginButton) {
        this.context = ctx;
        this.acUser = acUser;
        this.acPass = acPass;
        this.loginButton = loginButton;
        this.loginButton.setEnabled(false);

    }


    @Override
    protected Boolean doInBackground(String... params) {
        Log.d("LoginTask", "Entra a doInBack..");
        JSONArray jsonArray;
        JSONObject jsonObject;
        try {
            jsonArray = ClienteHttp.Login(ClienteHttp.URL, this.acUser, this.acPass);
            String value, id, access, moderator;
            SessionManager session = new SessionManager(this.context);
            jsonObject = jsonArray.getJSONObject(0);
            value = jsonObject.getString(KEY_ID);

            Log.e("Try  ", value);
            if (value != null) {
                id = jsonObject.getString(KEY_ID);
                access = jsonObject.getString(KEY_ACCES);
                moderator = jsonObject.getString(KEY_MODERATOR);
                Log.d("JSON ", id);
                session.createLogin(id, access, moderator);
                return false;
            } else {
                this.message = "Error Login";
                Log.d("LoginTask", "ErrorLogin");
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.message = "Usuario o Contraseña invalidos";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.message = "Error Inesperado";
            return true;
        }
    }


    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("LoginTask", "Entra a onPostExecute..");
        this.loginButton.setEnabled(true);
        if (!result) {
            this.context.startActivity(new Intent(this.context, DashBoard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            //Toast.makeText(this.context, "Loggeo Exitosamente!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this.context, this.message, Toast.LENGTH_LONG).show();
        }
    }

}
