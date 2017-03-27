package com.sistematica.restaurantedcharlye;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sistematica.restaurantedcharlye.data_base.DeliveryDataBase;
import com.sistematica.restaurantedcharlye.data_base.DeliveryPedidoEntity;

public class AgregarDeliveryActivity extends AppCompatActivity {

    EditText et_ntelefono;
    EditText et_direccion;
    EditText et_notas;

    Button btn_continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_delivery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_ntelefono = (EditText) findViewById(R.id.et_ntelefono);
        et_direccion = (EditText) findViewById(R.id.et_direccion);
        et_notas = (EditText) findViewById(R.id.et_notas);

        btn_continuar = (Button) findViewById(R.id.btnContinuar);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_ntelefono.getText().toString().equalsIgnoreCase("") || et_direccion.getText().toString().equalsIgnoreCase("") || et_notas.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(AgregarDeliveryActivity.this, "Debe llenar todos los datos solicitados.", Toast.LENGTH_SHORT).show();
                } else {

                    DeliveryDataBase ddb = new DeliveryDataBase(AgregarDeliveryActivity.this);
                    SQLiteDatabase db = ddb.getWritableDatabase();

                    DeliveryPedidoEntity dp = new DeliveryPedidoEntity();
                    dp.setNumero(et_ntelefono.getText().toString());
                    dp.setDireccion(et_direccion.getText().toString());
                    dp.setNotas(et_notas.getText().toString());
                    dp.setActivo(true);

                    try {
                        ddb.InsertDelivery(dp);
                    } catch (Exception e) {
                        Log.d("Error InsertDelivery", e.toString());
                        finish();
                        Toast.makeText(AgregarDeliveryActivity.this, "Hemos tenido un error! Intentalo más tarde o llama por teléfono para hacer tu pedido.", Toast.LENGTH_SHORT).show();
                    } finally {
                        Intent i = new Intent(AgregarDeliveryActivity.this, ListaPedidoActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

}
