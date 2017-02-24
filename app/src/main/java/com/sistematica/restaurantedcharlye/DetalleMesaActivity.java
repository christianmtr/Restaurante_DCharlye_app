package com.sistematica.restaurantedcharlye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import com.sistematica.restaurantedcharlye.table_adapters.DetalleMesaAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DetalleMesaActivity extends AppCompatActivity {

    TextView tv_detalle_mesa_numero;
    TableLayout tl_detalle_mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_detalle_mesa_numero = (TextView) findViewById(R.id.tv_detalle_mesa_numero);
        tl_detalle_mesa = (TableLayout) findViewById(R.id.tl_detalle_mesa);

        String nmesa = getIntent().getExtras().getString("nmesa");
        String resultado_conumo = getIntent().getExtras().getString("resultado_conumo");
        Log.d("DetalleMesaActivity", resultado_conumo);

        tv_detalle_mesa_numero.setText(tv_detalle_mesa_numero.getText() + " " + nmesa);
        tl_detalle_mesa.setStretchAllColumns(true);

        DetalleMesaAdapter dmsa = new DetalleMesaAdapter(this, tl_detalle_mesa);
        dmsa.agregarCabecera(R.array.cabecera_detalle_mesa);

        try {
            JSONArray json_consumo = new JSONArray(resultado_conumo);
/*
            String anterior = new String();

            int cantidad = 0;
            int ultima_posicion_diferente = 0;

            String[] row = new String[3];
*/
            for (int i = 0; i < json_consumo.length(); i++) {
                /*row[0] = json_consumo.getJSONObject(i).getString("a");
                if (!(row[0].equalsIgnoreCase(anterior))) {
                    cantidad = 1;
                    row[0] = json_consumo.getJSONObject(ultima_posicion_diferente).getString("a");
                    row[1] = json_consumo.getJSONObject(ultima_posicion_diferente).getString("b");
                    row[2] = String.valueOf(cantidad);
                    ultima_posicion_diferente = i;
                }
                anterior = row[0];*/
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(json_consumo.getJSONObject(i).getString("a"));
                elementos.add("0");
                elementos.add(json_consumo.getJSONObject(i).getString("b"));
                dmsa.agregarFilaTabla(elementos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        startActivity(getSupportParentActivityIntent());
    }
}
