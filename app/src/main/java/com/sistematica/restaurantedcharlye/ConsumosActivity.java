package com.sistematica.restaurantedcharlye;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sistematica.restaurantedcharlye.peticiones_servicioweb.PedirMesa;

public class ConsumosActivity extends AppCompatActivity {

    EditText et_dialogo_nmesa;
    EditText et_resultado;
    TextView tv_consumo;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_consumo = (TextView) findViewById(R.id.tv_consumo);

        pd = new ProgressDialog(this);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Si hay conexion.", Toast.LENGTH_SHORT).show();
            PedirNumeroMesaDialog().show();
        } else {
            Toast.makeText(this, "No hay conexion a Internet.", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consumos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_consumos) {
            Toast.makeText(this, "Aqui veras el detalle de tu pedido", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public AlertDialog PedirNumeroMesaDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadro_de_dialogo, null);

        et_dialogo_nmesa = (EditText) v.findViewById(R.id.et_dialogo_nmesa);
        et_resultado = (EditText) v.findViewById(R.id.et_resultado);

        builder.setView(v);

        builder.setTitle("Igrese N° de mesa")
                .setPositiveButton("Consultar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nmesa = et_dialogo_nmesa.getText().toString();
                                Log.d("Mesa", "nmesa=" + nmesa);

                                if (nmesa.isEmpty()) {
                                    Toast.makeText(ConsumosActivity.this, "Debe ingresar el número de mesa.", Toast.LENGTH_LONG).show();
                                    PedirNumeroMesaDialog().show();
                                } else {
                                    tv_consumo.setText(nmesa);
//                                    pd.setMessage("Consultando informacion, por favor espere...");
                                    PedirMesa respuesta = new PedirMesa(nmesa, pd);
                                    respuesta.execute();
                                }
                            }
                        }
                )
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();
                            }
                        }

                );

        return builder.create();
    }
}