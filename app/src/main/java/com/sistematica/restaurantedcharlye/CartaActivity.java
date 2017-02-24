package com.sistematica.restaurantedcharlye;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.sistematica.restaurantedcharlye.list_adapters.CartaEntity;
import com.sistematica.restaurantedcharlye.list_adapters.CartaList;
import com.sistematica.restaurantedcharlye.webservice_consumer.ListaCartaGet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CartaActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    static ListView lv_carta;
    static ArrayList<CartaEntity> lista1 = new ArrayList<CartaEntity>(); // lista de pollos
    static ArrayList<CartaEntity> lista2 = new ArrayList<CartaEntity>(); // lista de chifa
    static ArrayList<CartaEntity> lista3 = new ArrayList<CartaEntity>(); // lista de parrillas
    static String tipos[];

    static JSONArray rr;

    String tmp0;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Drawable d = getDrawable(R.mipmap.ic_launcher);

        if (!isOnlineNet()) {
            Toast.makeText(this, "No se puede acceder al servicio. Compruebe su conexion a Internet.", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        pd = new ProgressDialog(this);
        pd.setMessage("Consultando informacion, por favor espere...");

        ListaCartaGet plc = new ListaCartaGet(pd);
        tmp0 = new String();

        try {
            String r = plc.execute().get();
            Log.d("ElResultado", "r= " + r);

            if (!validar_resultado(r)) {
                cerrar_actividad();
                Toast.makeText(this, "Lo sentimos, no hemos encontrado datos. Vuelve a intentarlo más tarde.", Toast.LENGTH_LONG).show();
            }

            rr = new JSONArray(r);
            Log.d("JSON:", rr.toString());

            for (int i = 0; i < rr.length(); i++) {
                String tmp1 = rr.getJSONObject(i).getString("a");
                String tmp2 = rr.getJSONObject(i).getString("b");
                String tmp3 = rr.getJSONObject(i).getString("c");

                if (tmp3.equalsIgnoreCase("Pollo a la brasa")) {
                    lista1.add(new CartaEntity(d, tmp1, tmp2));
                } else if (tmp3.equalsIgnoreCase("Chifa")) {
                    lista2.add(new CartaEntity(d, tmp1, tmp2));
                } else if (tmp3.equalsIgnoreCase("Parrilla")) {
                    lista3.add(new CartaEntity(d, tmp1, tmp2));
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        limpia_listas();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_carta, container, false);

            /*********************************** mi codigo ****************************************/
            if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 1) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                CartaList adaptador_pollos = new CartaList(getActivity(), lista1);
                lv_carta.setAdapter(adaptador_pollos);
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 2) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                CartaList adaptador_chifa = new CartaList(getActivity(), lista2);
                lv_carta.setAdapter(adaptador_chifa);
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 3) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                CartaList adaptador_parrila = new CartaList(getActivity(), lista3);
                lv_carta.setAdapter(adaptador_parrila);
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pollos a la brasa";
                case 1:
                    return "Chifa";
                case 2:
                    return "Parrillas";
            }
            return null;
        }
    }

    public void limpia_listas() {
        lista1.clear();
        lista2.clear();
        lista3.clear();
    }

    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public Boolean validar_resultado(String r) {
        // verifica si fueron devueltos datos, si la mesa o delivery tienen pedidos asignados.
        return !r.equalsIgnoreCase("[]");
    }

    protected void cerrar_actividad() {
        startActivity(getSupportParentActivityIntent());
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cerrar_actividad();
    }

}
