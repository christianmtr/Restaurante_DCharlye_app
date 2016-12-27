package com.sistematica.restaurantedcharlye.adaptores_lista;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sistematica.restaurantedcharlye.R;

import java.util.ArrayList;

/**
 * Created by christianmtr on 10/12/16.
 */

public class lista_carta extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<carta> items;

    public lista_carta(Activity activity, ArrayList<carta> items) {
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

    public void addAll(ArrayList<carta> lista) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista_carta, null);
        }

        carta dir = items.get(i);

        TextView titulo = (TextView) v.findViewById(R.id.tv_lista);
        titulo.setText(dir.getPlatillo());

        ImageView img = (ImageView) v.findViewById(R.id.iv_lista);
        img.setImageDrawable(dir.getImagen());

        return v;
    }
}
