package mx.com.audioweb.lcv.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Acosta on 8/11/2014.
 */
public class ClienteHttp {
    public static final String URL = "http://www.audiowebcentral.com.mx/webServices/";
    private static final String DATEF = "yyyy-MM-dd HH:mm:ss";
    private Gson gson = new GsonBuilder().setDateFormat(DATEF).create();

    public static JSONArray Login(String URL, String acUser, String acPass) throws JSONException {
        BufferedReader bufferedReader = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL + "doLogin.php");
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("acUser", acUser));
        postParameters.add(new BasicNameValuePair("acPass", acPass));

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                    postParameters);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);

            bufferedReader = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            bufferedReader.close();

            JSONArray jsonArray = new JSONArray(stringBuffer.toString());

            return jsonArray;

        } catch (ClientProtocolException e) {

            e.printStackTrace();
            Log.d("ClientProtocolException", e.toString());

        } catch (IOException e) {

            e.printStackTrace();

            Log.d("Exception", e.toString());

        } finally {
            if (bufferedReader != null) {
                try {

                    bufferedReader.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        }
        return null;
    }

    public static JSONArray reporteAc(String Url, String f1, String f2, String codigo) throws JSONException {
        BufferedReader bufferedReader = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost reqAcumulado = new HttpPost(Url + "getAcumulado.php");
        List<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
        parametrosPost.add(new BasicNameValuePair("f1", f1));
        parametrosPost.add(new BasicNameValuePair("f2", f2));
        parametrosPost.add(new BasicNameValuePair("codigo", codigo));

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametrosPost);
            reqAcumulado.setEntity(entity);
            HttpResponse response = httpClient.execute(reqAcumulado);

            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();

            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            return jsonArray;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.d("ClientProtocol Exception", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Exception", e.toString());
        } finally {
            if (bufferedReader != null) {
                try {

                    bufferedReader.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        }
        return null;
    }

    public ArrayList<Contacto> contacto(String usr_id) throws JSONException {
        BufferedReader bufferedReader = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL + "getNotebook.php");
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("usr_id", usr_id));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            if (jsonArray != null) {
                ArrayList<Contacto> contactos = new ArrayList<Contacto>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    contactos.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Contacto.class));
                    Log.d("Contactos", contactos.toString());
                }
                return contactos;
            } else {
                return null;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ArrayList<Participante> participante(String acnum) throws JSONException {
        BufferedReader bufferedReader = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL + "getConferences.php");
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("acnum", acnum));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            if (jsonArray != null) {
                ArrayList<Participante> participantes = new ArrayList<Participante>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    participantes.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Participante.class));
                }
                return participantes;
            } else {
                return null;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}