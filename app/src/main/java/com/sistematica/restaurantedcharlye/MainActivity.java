package com.sistematica.restaurantedcharlye;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int INTERVALO = 2000; //2 segundos para salir
    private long tiempoPrimerClick;

    ImageView iv_logo_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv_logo_main = (ImageView) findViewById(R.id.iv_logo_main);
        int img_logo = R.drawable.dcharlye;

        Glide.with(this).load(img_logo).into(iv_logo_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
                this.finish();
            }else {
                Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
            }
            tiempoPrimerClick = System.currentTimeMillis();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_carta) {
            Intent i = new Intent(this,CartaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_consumo) {
            Intent j = new Intent(this, ConsumosActivity.class);
            startActivity(j);
        } else if (id == R.id.nav_share) {
            Intent k = new Intent(this, CompartirActivity.class);
            startActivity(k);
        } else if (id == R.id.nav_agregar_delivery) {
            Intent l = new Intent(this, AgregarDeliveryActivity.class);
            startActivity(l);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
