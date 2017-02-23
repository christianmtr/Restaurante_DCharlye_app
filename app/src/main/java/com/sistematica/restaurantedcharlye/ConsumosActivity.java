package com.sistematica.restaurantedcharlye;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sistematica.restaurantedcharlye.webservice_consumer.MesaGet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class ConsumosActivity extends AppCompatActivity {

    EditText et_dialogo_nmesa;
    EditText et_resultado;
    TextView tv_consumo_subtotal;
    ProgressDialog pd;

    ImageView iv_voucher_arriba;
    ImageView iv_voucher_abajo;

    String nmesa;
    String resultado_consumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv_consumo_subtotal = (TextView) findViewById(R.id.tv_consumo_subtotal);
        iv_voucher_arriba = (ImageView) findViewById(R.id.iv_voucher_arriba);
        iv_voucher_abajo = (ImageView) findViewById(R.id.iv_voucher_abajo);
        int id_img_voucher_arriba = R.drawable.voucher_arriba;
        int id_img_voucher_abajo = R.drawable.voucher_abajo;

        Glide.with(this).load(id_img_voucher_arriba).into(iv_voucher_arriba);
        Glide.with(this).load(id_img_voucher_abajo).into(iv_voucher_abajo);

        pd = new ProgressDialog(this);

        if (isOnlineNet()) {
            PedirNumeroMesaDialog().show();
        } else {
            Toast.makeText(this, "No se puede acceder al servicio. Compruebe su conexion a Internet.", Toast.LENGTH_SHORT).show();
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

            Intent i = new Intent(this, DetalleMesaActivity.class);
            i.putExtra("nmesa", nmesa);
            i.putExtra("resultado_conumo", resultado_consumo);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public AlertDialog PedirNumeroMesaDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadro_de_dialogo, null);

        et_dialogo_nmesa = (EditText) v.findViewById(R.id.et_dialogo_nmesa);

        builder.setView(v);

        AlertDialog.Builder builder1 = builder.setTitle("Igrese N° de mesa")
                .setPositiveButton("Consultar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nmesa = et_dialogo_nmesa.getText().toString();
                                String ruta_consumo = "ver_detalle_mesa/" + nmesa + "/";
                                String ruta_total = "ver_pedido/" + nmesa + "/";

                                String row[] = new String[3];

                                if (nmesa.isEmpty()) {
                                    Toast.makeText(ConsumosActivity.this, "Debe ingresar el número de mesa.", Toast.LENGTH_LONG).show();
                                    PedirNumeroMesaDialog().show();
                                } else {
                                    pd.setMessage("Consultando informacion, por favor espere...");
                                    MesaGet consumo = new MesaGet(ruta_consumo, pd);
                                    MesaGet total = new MesaGet(ruta_total, pd);
                                    try {
                                        resultado_consumo = consumo.execute().get();

                                        String resultado_total = total.execute().get();
                                        Log.d("ElResultado", "r= " + resultado_total);
                                        JSONArray json_total = new JSONArray(resultado_total);
                                        String subtotal = json_total.getJSONObject(0).getString("a");
                                        Log.d("json_decodeado", "SubTotal= " + subtotal);

                                        tv_consumo_subtotal.setText(subtotal);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
