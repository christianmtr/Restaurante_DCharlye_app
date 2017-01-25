package com.sistematica.restaurantedcharlye.peticiones_servicioweb;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by christianmtr on 02/01/17.
 */

public class PedirMesa extends AsyncTask<String, Void, String> {
    private String nmesa;
    InputStream in = null;
    ProgressDialog pd;

    private EditText et_resultado;

    public PedirMesa(String nm, ProgressDialog p, EditText et) {
        super();
        nmesa = nm;
        pd = p;
        et_resultado = et;
        Log.d("Constructor", "Ya esta aqui");
    }

    @Override
    protected String doInBackground(String... params) {
        String u = "http://restaurantedcharlye.pythonanywhere.com//servicio_web/ver_pedido/" + nmesa + "/";
        HttpURLConnection urlConnection = null;
        Log.d("Peticion", "doInBackground: u=" + u);
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
            Log.d("Respuesta", "doInBackground: in=" + sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("Primer catch", "MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Segundo catch", "IOExection");
        } finally {
            urlConnection.disconnect();
            Log.d("Finally", "Ya esta aqui");
        }

        return sb.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setCancelable(false);
        pd.show();
        Log.d("onPreExecute", "Ya esta aqui");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();

        Log.d("onPostExecute", "parametro= " + s);
        Log.d("onPostExecute", "termino");
    }

    /*@Override
    protected String doInBackground(Object[] params) {
        String u = "http://192.168.1.36:8000/servicio_web/ver_pedido/" + nmesa + "/";
        HttpURLConnection urlConnection = null;
        Log.d("Peticion", "doInBackground: u=" + u);
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
            Log.d("Respuesta", "doInBackground: in=" + sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("Primer catch", "MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Segundo catch", "IOExection");
        } finally {
            urlConnection.disconnect();
            Log.d("Finally", "Ya esta aqui");
        }

        return sb.toString();
    }*/

/*    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        pd.dismiss();

        Log.d("onPostExecute", "termino");
    }*/
}
