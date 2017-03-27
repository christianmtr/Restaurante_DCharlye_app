package com.sistematica.restaurantedcharlye.webservice_consumer;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by imac on 24/03/17.
 */

public class AgregarDeliveryPost extends AsyncTask<Void, Void, String> {

    String numero;
    String direccion;
    String notas;

    ProgressDialog pd;

    InputStream in = null;

    public AgregarDeliveryPost(String numero, String direccion, String notas, ProgressDialog progressDialog) {
        super();
        this.numero = numero;
        this.direccion = direccion;
        this.notas = notas;
        this.pd = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setMessage("Enviando información, por favor espere...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
    }

    @Override
    protected String doInBackground(Void... params) {
        String u = "http://192.168.1.54:8000/servicio_web/agregardelivery/";
        HttpURLConnection urlConnection = null;
        Log.d("Peticion", "ruta=" + u);
//        StringBuilder sb = null;

        try {
            URL url = new URL(u);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            urlConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            urlConnection.connect();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nt", numero);
            jsonObject.put("dir", direccion);
            jsonObject.put("notas", notas);
//            jsonObject.put("csrftoken", "yWTzenFgXxY1KtaefQLzhIF1iXgTFj82e4tsChPxchtVrw7qBF1XKFUoToIzz0Eb");

            Log.d("JSon", jsonObject.toString());

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            return urlConnection.getResponseMessage();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            urlConnection.disconnect();
        }

    }
}
