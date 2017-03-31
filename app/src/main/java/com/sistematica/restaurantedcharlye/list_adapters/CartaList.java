package com.sistematica.restaurantedcharlye.list_adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sistematica.restaurantedcharlye.CartaActivity;
import com.sistematica.restaurantedcharlye.R;

import java.util.ArrayList;

/**
 * Created by christianmtr on 10/12/16.
 */

public class CartaList extends BaseAdapter {
    protected Activity activity;
    protected static ArrayList<CartaEntity> items;

    public CartaList(Activity activity, ArrayList<CartaEntity> items) {
        this.activity = activity;
        this.items = items;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<CartaEntity> lista) {
        for (int i = 0; i < lista.size(); i++) {
            items.add(lista.get(i));
        }
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista_carta, null);
        }

        CartaEntity dir = items.get(i);

        TextView titulo = (TextView) v.findViewById(R.id.tv_lista);
        titulo.setText(dir.getPlatillo());

        TextView precio = (TextView) v.findViewById(R.id.tv_precio);
        precio.setText("S/. " + dir.getPrecio());

        ImageView img = (ImageView) v.findViewById(R.id.iv_lista);
        img.setImageDrawable(dir.getImagen());

        return v;
    }

}
