package com.sistematica.restaurantedcharlye;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sistematica.restaurantedcharlye.list_adapters.CartaEntity;
import com.sistematica.restaurantedcharlye.list_adapters.CartaList;
import com.sistematica.restaurantedcharlye.webservice_consumer.ListaCartaGet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaPedidoActivity extends AppCompatActivity {

    ListView lv_listas;
    static ArrayList<CartaEntity> lista1 = new ArrayList<CartaEntity>();
    static ArrayList<CartaEntity> lista2 = new ArrayList<CartaEntity>();
    static ArrayList<CartaEntity> lista3 = new ArrayList<CartaEntity>();
    static ArrayList<CartaEntity> lista4 = new ArrayList<CartaEntity>();

    static JSONArray rr;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Drawable d = getDrawable(R.mipmap.ic_launcher);


        lv_listas = (ListView) findViewById(R.id.lv_listas);

        pd = new ProgressDialog(this);
        pd.setMessage("Consultando informacion, por favor espere...");

        ListaCartaGet plc = new ListaCartaGet(pd);

        try {
            String r = plc.execute().get();
            Log.d("ElResultado", "r= " + r);

            if (!validar_resultado(r)) {
                // hacer algo cuando no haya internet, eliminar el registro en la bd
                Toast.makeText(this, "Lo sentimos, no hemos encontrado datos. Vuelve a intentarlo más tarde.", Toast.LENGTH_LONG).show();
            }

            rr = new JSONArray(r);
            Log.d("JSON:", rr.toString());

            for (int i = 0; i < rr.length(); i++) {
                String tmp1 = rr.getJSONObject(i).getString("a");
                String tmp2 = rr.getJSONObject(i).getString("b");
                String tmp3 = rr.getJSONObject(i).getString("c");

                if (tmp3.equalsIgnoreCase("Pollo a la brasa")) {
                    lista1.add(new CartaEntity(d, tmp1, tmp2));
                } else if (tmp3.equalsIgnoreCase("Chifa")) {
                    lista2.add(new CartaEntity(d, tmp1, tmp2));
                } else if (tmp3.equalsIgnoreCase("Parrilla")) {
                    lista3.add(new CartaEntity(d, tmp1, tmp2));
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lv_listas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                CartaEntity item = CartaList.getItem(pos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ver_pollos) {
            CartaList adaptador = new CartaList(this, lista1);
            lv_listas.setAdapter(adaptador);
        } else if (id == R.id.ver_chifa) {
            CartaList adaptador = new CartaList(this, lista2);
            lv_listas.setAdapter(adaptador);
        } else if (id == R.id.ver_parrillas) {
            CartaList adaptador = new CartaList(this, lista3);
            lv_listas.setAdapter(adaptador);
        } else if (id == R.id.ver_otros) {
            CartaList adaptador = new CartaList(this, lista4);
            lv_listas.setAdapter(adaptador);
        }

        return super.onOptionsItemSelected(item);
    }

    public Boolean validar_resultado(String r) {
        // verifica si fueron devueltos datos, si la mesa o delivery tienen pedidos asignados.
        return !r.equalsIgnoreCase("[]");
    }
}
