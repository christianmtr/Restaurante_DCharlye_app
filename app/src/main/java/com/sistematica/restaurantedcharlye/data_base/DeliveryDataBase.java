package com.sistematica.restaurantedcharlye.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by imac on 25/03/17.
 */

public class DeliveryDataBase extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "restaurant_dcharlye";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DELIVERYPEDIDO = "Delivery_Pedido";
    private static final String KEY_ID_DELIVERY = "id";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_DIRECCION = "direccion";
    private static final String KEY_NOTAS = "notas";
    private static final String KEY_ID_REMOTO_DELIVERY = "id_remoto";

    private static final String TABLE_ITEMS = "Items";
    private static final String KEY_ID_ITEMS = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_PRECIO = "precio";
    private static final String KEY_ID_REMOTO_ITEMS = "id_remoto";
    private static final String KEY_ID_DELIVERY_LOCAL = "id_delivery_local";

    public static String TAG = "tag";

    private static final String CREATE_TABLE_DELIVERY =
            "CREATE TABLE " + TABLE_DELIVERYPEDIDO + "(" +
                    KEY_ID_DELIVERY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NUMERO + " TEXT,"
                    + KEY_DIRECCION + " TEXT,"
                    + KEY_NOTAS + " TEXT,"
                    + KEY_ID_REMOTO_DELIVERY + " INTEGER)";

    private static final String CREATE_TABLE_ITEMS =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    KEY_ID_ITEMS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NOMBRE + " TEXT,"
                    + KEY_PRECIO + " TEXT,"
                    + KEY_ID_REMOTO_ITEMS + " INTEGER,"
                    + KEY_ID_DELIVERY_LOCAL + " INTEGER)";

    private SQLiteDatabase db = null;

    public DeliveryDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DELIVERY);
        db.execSQL(CREATE_TABLE_ITEMS);
    }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
            throw new RuntimeException("Error al abrir la base de datos.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS; " + CREATE_TABLE_DELIVERY);
        db.execSQL("DROP TABLE IF EXISTS; " + CREATE_TABLE_ITEMS);
        onCreate(db);
    }

    public long InsertDelivery(DeliveryPedidoEntity delivery_pedido) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUMERO, delivery_pedido.getNumero());
        values.put(KEY_DIRECCION, delivery_pedido.getDireccion());
        values.put(KEY_NOTAS, delivery_pedido.getNotas());
        long insert = db.insert(TABLE_DELIVERYPEDIDO, null, values);
        Log.d("InsertDelivery", (String.valueOf(insert)));
        return insert;
    }

    public long InsertItems(ItemsEntity items) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, items.getNombre());
        values.put(KEY_PRECIO, items.getPrecio());
        values.put(KEY_ID_REMOTO_ITEMS, items.getId_remoto());
        values.put(KEY_ID_DELIVERY_LOCAL, items.getId_delivery_local());
        long insert = db.insert(TABLE_ITEMS, null, values);
        return insert;
    }

}
