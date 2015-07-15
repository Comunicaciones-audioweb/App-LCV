package mx.com.audioweb.lcv.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Juan Acosta on 8/18/2014.
 */
public class SharedPreferencesExecutor<T> {
    private Context context;

    public SharedPreferencesExecutor(Context context) {
        this.context = context;
    }

    public void saveData(String Key, T sharedPreferencesEntry) {
        SharedPreferences appSharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sharedPreferencesEntry);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
        Log.d("JSONVAL", json);
    }

    public T retrive(String Key, Class<T> clazz) {
        SharedPreferences appSharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPref.getString(Key, "");
        return (T) gson.fromJson(json, clazz);
    }

    public ArrayList<T> retriveAll(Class<T> clazz) {
        ArrayList<T> arrayList = new ArrayList<T>();
        SharedPreferences appSharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        Map<String, ?> jsonS = appSharedPref.getAll();
        for (Map.Entry<String, ?> json : jsonS.entrySet()) {
            if (json.getKey().split("_")[0].equals(clazz.getName())) {
                arrayList.add((T) gson.fromJson(json.getValue().toString(), clazz));
            }
        }
        return arrayList;
    }

    public void removeAll(Class<T> clazz) {
        SharedPreferences appSharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPref.edit();
        Map<String, ?> mapEntryList = appSharedPref.getAll();
        for (Map.Entry<String, ?> entry : mapEntryList.entrySet()) {
            if (entry.getKey().split("_")[0].equals(clazz.getName())) {
                prefsEditor.remove(entry.getKey());
                prefsEditor.commit();
            }
        }
    }
}
