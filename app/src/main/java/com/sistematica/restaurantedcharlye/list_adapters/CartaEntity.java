package com.sistematica.restaurantedcharlye.list_adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by christianmtr on 10/12/16.
 */

public class CartaEntity {
    private Drawable imagen;
    private String platillo;
    private String precio;

    public CartaEntity() { }

    public CartaEntity(String platillo) {
        this.platillo = platillo;
    }

    public CartaEntity(Drawable imagen, String platillo, String precio) {
        this.imagen = imagen;
        this.platillo = platillo;
        this.precio = precio;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}

