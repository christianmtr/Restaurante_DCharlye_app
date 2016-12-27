package com.sistematica.restaurantedcharlye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sistematica.restaurantedcharlye.adaptores_lista.carta;

import java.util.ArrayList;

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

    ListView lv_carta;
    ArrayList<carta> lpollos = new ArrayList<carta>();
    ArrayList<carta> lchifa = new ArrayList<carta>();
    ArrayList<carta> lparrilla = new ArrayList<carta>();
    ArrayList<carta> lbebidas = new ArrayList<carta>();


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

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            // mi codigo xD
//            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
//                case 1:
//                    textView.setText(textView.getText() + "Pollos,\n");
//                case 2:
//                    textView.setText(textView.getText() + "Chifas,\n");
//                case 3:
//                    textView.setText(textView.getText() + "Parrillas,\n");
//                case 4:
//                    textView.setText(textView.getText() + "Otros xD,\n");
//            }

            if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 1) {
                textView.setText(textView.getText() + "Pollos,\n");
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 2) {
                textView.setText(textView.getText() + "Chifas,\n");
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 3) {
                textView.setText(textView.getText() + "Parrillas,\n");
            } else if ((getArguments().getInt(ARG_SECTION_NUMBER)) == 4) {
                textView.setText(textView.getText() + "Otros xD,\n");
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
//            switch (position) {
//                case 0:
//                    Toast.makeText(CartaActivity.this, "Hola mundo! Pollo", Toast.LENGTH_SHORT).show();
//
//                    return PlaceholderFragment.newInstance(position + 1);
//                case 1:
//                    lchifa.add(new carta(getDrawable(R.mipmap.ic_launcher),"Chifa1"));
//                    lchifa.add(new carta(getDrawable(R.mipmap.ic_launcher),"Chifa2"));
//                    lchifa.add(new carta(getDrawable(R.mipmap.ic_launcher),"Chifa3"));
//                    lchifa.add(new carta(getDrawable(R.mipmap.ic_launcher),"Chifa4"));
//
//                    lv_carta = (ListView) findViewById(R.id.lv_lista_carta);
//                    lista_carta adaptador_chifa = new lista_carta(this,lchifa);
//                    lv_carta.setAdapter(adaptador_chifa);
//
//                    Toast.makeText(CartaActivity.this, "Hola mundo! Chifas!", Toast.LENGTH_SHORT).show();
//
//                    return PlaceholderFragment.newInstance(position + 1);
//                case 2:
//                    lparrilla.add(new carta(getDrawable(R.mipmap.ic_launcher),"Parrilla2"));
//                    lparrilla.add(new carta(getDrawable(R.mipmap.ic_launcher),"Parrilla3"));
//                    lparrilla.add(new carta(getDrawable(R.mipmap.ic_launcher),"Parrilla4"));
//                    lparrilla.add(new carta(getDrawable(R.mipmap.ic_launcher),"Parrilla5"));
//
//                    lv_carta = (ListView) findViewById(R.id.lv_lista_carta);
//                    lista_carta adaptador_parrila = new lista_carta(this,lparrilla);
//                    lv_carta.setAdapter(adaptador_parrila);
//
//                    Toast.makeText(CartaActivity.this, "Hola mundo! Parrishas!", Toast.LENGTH_SHORT).show();
//
//                    return PlaceholderFragment.newInstance(position + 1);
//                case 3:
//                    lbebidas.add(new carta(getDrawable(R.mipmap.ic_launcher),"Gaseosa1"));
//                    lbebidas.add(new carta(getDrawable(R.mipmap.ic_launcher),"Gaseosa2"));
//                    lbebidas.add(new carta(getDrawable(R.mipmap.ic_launcher),"Gaseosa3"));
//                    lbebidas.add(new carta(getDrawable(R.mipmap.ic_launcher),"Jugo4"));
//                    lv_carta = (ListView) findViewById(R.id.lv_lista_carta);
//                    lista_carta adaptador_bebidas = new lista_carta(this,lbebidas);
//                    lv_carta.setAdapter(adaptador_bebidas);
//
//                    Toast.makeText(CartaActivity.this, "Hola mundo! Otros xD", Toast.LENGTH_SHORT).show();
//
//                    return PlaceholderFragment.newInstance(position + 1);
//            }
//
//            return null;

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
}
