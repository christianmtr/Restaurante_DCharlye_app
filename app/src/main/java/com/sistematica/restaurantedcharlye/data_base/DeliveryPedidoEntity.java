package com.sistematica.restaurantedcharlye.data_base;

/**
 * Created by imac on 25/03/17.
 */

public class DeliveryPedidoEntity {
    public int id;
    public String numero;
    public String direccion;
    public String notas;
    public int id_remoto;
    public boolean activo; // uso local, no tiene q ver con el estado del pedido en el sistema web

    public DeliveryPedidoEntity() {
    }

    public DeliveryPedidoEntity(int id, String numero, String direccion, String notas, int id_remoto, boolean activo) {
        this.id = id;
        this.numero = numero;
        this.direccion = direccion;
        this.notas = notas;
        this.id_remoto = id_remoto;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getId_remoto() {
        return id_remoto;
    }

    public void setId_remoto(int id_remoto) {
        this.id_remoto = id_remoto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
