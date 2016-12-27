package com.sistematica.restaurantedcharlye.adaptores_lista;

import android.graphics.drawable.Drawable;

/**
 * Created by christianmtr on 10/12/16.
 */

public class carta {
    private Drawable imagen;
    private String platillo;

    public carta(String platillo) {
        this.platillo = platillo;
    }

    public carta(Drawable imagen, String platillo) {
        this.imagen = imagen;
        this.platillo = platillo;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public String getPlatillo() {
        return platillo;
    }

    public void setPlatillo(String platillo) {
        this.platillo = platillo;
    }
}

