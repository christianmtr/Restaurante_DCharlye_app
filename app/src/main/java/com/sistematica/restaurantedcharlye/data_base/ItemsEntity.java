package com.sistematica.restaurantedcharlye.data_base;

/**
 * Created by imac on 25/03/17.
 */

public class ItemsEntity {
    private int id;
    private String nombre;
    private String precio;
    private int id_remoto;
    private int id_delivery_local;

    public ItemsEntity() {
    }

    public ItemsEntity(int id, String nombre, String precio, int id_remoto, int id_delivery_local) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.id_remoto = id_remoto;
        this.id_delivery_local = id_delivery_local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getId_remoto() {
        return id_remoto;
    }

    public void setId_remoto(int id_remoto) {
        this.id_remoto = id_remoto;
    }

    public int getId_delivery_local() {
        return id_delivery_local;
    }

    public void setId_delivery_local(int id_delivery_local) {
        this.id_delivery_local = id_delivery_local;
    }
}
