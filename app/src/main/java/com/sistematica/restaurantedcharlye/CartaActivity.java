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

import com.sistematica.restaurantedcharlye.adaptores_lista.carta;
import com.sistematica.restaurantedcharlye.adaptores_lista.lista_carta;
import com.sistematica.restaurantedcharlye.peticiones_servicioweb.PedirListaCarta;

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
    static ArrayList<carta> lpollos = new ArrayList<carta>();
    static ArrayList<carta> lchifa = new ArrayList<carta>();
    static ArrayList<carta> lparrilla = new ArrayList<carta>();
    static ArrayList<carta> lbebidas = new ArrayList<carta>();

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

        pd = new ProgressDialog(this);
        pd.setMessage("Consultando informacion, por favor espere...");

        PedirListaCarta plc = new PedirListaCarta(pd);

        try {
            String r = plc.execute().get();
            Log.d("ElResultado", "r= " + r);

//            JSONObject rr = new JSONObject(r.substring(1,r.length()-1));
            JSONArray rr = new JSONArray(r);

            for (int i = 0; i < rr.getJSONObject(0).getJSONArray("platillos").length(); i++) {
                String temp1 = rr.getJSONObject(0).getJSONArray("platillos").getJSONObject(i).getJSONObject("fields").getString("NombrePlatillo");
                String temp2 = rr.getJSONObject(0).getJSONArray("platillos").getJSONObject(i).getJSONObject("fields").getString("TipoPlatillo");
                if (temp2.equals("1")) {
                    lpollos.add(new carta(d, temp1));
                } else if (temp2.equals("2")) {
                    lchifa.add(new carta(d, temp1));
                } else if (temp2.equals("3")) {
                    lparrilla.add(new carta(d, temp1));
                }
            }

            for (int j = 0; j < rr.getJSONObject(1).getJSONArray("productos").length(); j++) {
                String temp1 = rr.getJSONObject(1).getJSONArray("productos").getJSONObject(j).getJSONObject("fields").getString("NombreProducto");
                lbebidas.add(new carta(d, temp1));
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
                lista_carta adaptador_pollos = new lista_carta(getActivity(), lpollos);
                lv_carta.setAdapter(adaptador_pollos);
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 2) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                lista_carta adaptador_chifa = new lista_carta(getActivity(), lchifa);
                lv_carta.setAdapter(adaptador_chifa);
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 3) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                lista_carta adaptador_parrila = new lista_carta(getActivity(), lparrilla);
                lv_carta.setAdapter(adaptador_parrila);
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 4) {
                lv_carta = (ListView) rootView.findViewById(R.id.lv_lista_carta);
                lista_carta adaptador_bebidas = new lista_carta(getActivity(), lbebidas);
                lv_carta.setAdapter(adaptador_bebidas);
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
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pollo a la brasa";
                case 1:
                    return "Chifa";
                case 2:
                    return "Parrillas";
                case 3:
                    return "Bebidas";
            }
            return null;
        }
    }

    public void limpia_listas() {
        lpollos.clear();
        lchifa.clear();
        lparrilla.clear();
        lbebidas.clear();
    }
}
