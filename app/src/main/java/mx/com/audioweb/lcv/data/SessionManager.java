package mx.com.audioweb.lcv.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import mx.com.audioweb.lcv.Login;

/**
 * Created by Juan Acosta on 8/14/2014.
 */
public class SessionManager {

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AudiowebPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "usr_id";
    public static final String KEY_ACCES = "usr_acces_code";
    public static final String KEY_MODERATOR = "usr_moderator_pin";

    public static final String KEY_MINUTOS = "minutos";
    public static final String KEY_CONFERENCIA = "conferencias";
    public static final String KEY_LLAMADA = "llamdas";
    public static final String KEY_PARTICIPA = "participantes";

    public SessionManager(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    // Metodo para poder Guardar Inicio de Sesion
    public void createLogin(String id, String acces, String moderator) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_ACCES, acces);
        editor.putString(KEY_MODERATOR, moderator);
        editor.commit();
    }

    public void crearReporte(String minutos, String conferencias, String llamadas, String participantes) {
        editor.putString(KEY_MINUTOS, minutos);
        editor.putString(KEY_CONFERENCIA, conferencias);
        editor.putString(KEY_LLAMADA, llamadas);
        editor.putString(KEY_PARTICIPA, participantes);
        editor.commit();
    }

    //Verificar Inicio de Sesion
    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    //Metodo para obtener los datos guardados en la Applicacion
    public HashMap<String, String> getuserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, preferences.getString(KEY_ID, null));
        user.put(KEY_ACCES, preferences.getString(KEY_ACCES, null));
        user.put(KEY_MODERATOR, preferences.getString(KEY_MODERATOR, null));
        user.put(KEY_MINUTOS, preferences.getString(KEY_MINUTOS, null));
        user.put(KEY_CONFERENCIA, preferences.getString(KEY_CONFERENCIA, null));
        user.put(KEY_LLAMADA, preferences.getString(KEY_LLAMADA, null));
        user.put(KEY_PARTICIPA, preferences.getString(KEY_PARTICIPA, null));
        return user;
    }

    //Metodo para Borrar datos
    public void EraseData() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
