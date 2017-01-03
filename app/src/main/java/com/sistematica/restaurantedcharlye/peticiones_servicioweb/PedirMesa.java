package com.sistematica.restaurantedcharlye.peticiones_servicioweb;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by christianmtr on 02/01/17.
 */

public class PedirMesa extends AsyncTask {
    private String nmesa;
    InputStream in = null;
    ProgressDialog pd;

    public PedirMesa(String nm, ProgressDialog p) {
        super();
        nmesa = nm;
        pd = p;
    }

    @Override
    protected void onPreExecute() {
//        super.onPreExecute();
        ProgressDialog.show(pd.getContext(), "Consultando", "Por favor espere...", true, false);
    }

    @Override
    protected InputStream doInBackground(Object[] params) {
        String u = "http://192.168.1.40:8000/servicio_web/ver_pedido/" + nmesa + "/";
        HttpURLConnection urlConnection = null;
        Log.d("Peticion", "doInBackground: u=" + u);
        try {
            URL url = new URL(u);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("Respuesta", "doInBackground: in=" + in);
//            readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return in;
    }

    @Override
    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
        pd.dismiss();
    }
}
