package com.sistematica.restaurantedcharlye;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Consumo_Activity extends AppCompatActivity {

    EditText et_dialogo_nmesa;
    TextView tv_consumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);

        et_dialogo_nmesa = (EditText) findViewById(R.id.et_dialogo_nmesa);
        tv_consumo = (TextView) findViewById(R.id.tv_consumo);

        createSimpleDialog().show();
    }


    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.cuadro_de_dialogo, null);

        builder.setView(v);

//        Button btn_dialogo_cancelar = (Button) findViewById(R.id.btn_dialogo_cancelar);
//        Button btn_dialogo_aceptar = (Button) findViewById(R.id.btn_dialogo_aceptar);

//        btn_dialogo_cancelar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        btn_dialogo_aceptar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        builder.setTitle("Igrese N° de mesa")
//                .setMessage("El Mensaje para el usuario")
        builder.setPositiveButton("Consultar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_consumo.setText(et_dialogo_nmesa.getText());
                    }
                })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

        return builder.create();
    }

}
