package com.sistematica.restaurantedcharlye.webservice_consumer;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by christianmtr on 14/01/17.
 */

public class ListaCartaGet extends AsyncTask<Void, Void, String> {

    ProgressDialog pd;
    InputStream in = null;

    public ListaCartaGet(ProgressDialog pd) {
        this.pd = pd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
        String u = "http://restaurantedcharlye.pythonanywhere.com/servicio_web/lista/";
        HttpURLConnection urlConnection = null;
        Log.d("Peticion", "ruta=" + u);
        StringBuilder sb = null;
        try {
            URL url = new URL(u);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                Log.e("Lee InputStream", "error al convertir InputStream", e);
            } finally {
                in.close();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("Primer catch", "MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Segundo catch", "IOExection");
        } finally {
            urlConnection.disconnect();
        }

        return sb.toString();
    }
}
