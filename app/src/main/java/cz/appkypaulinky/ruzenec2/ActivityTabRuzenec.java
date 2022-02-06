package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.PowerManager;
import android.os.Vibrator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.TEXT_SIZE;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.VISIBILITY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.BOLESTNY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.DALSI_MODLITBY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.DRUH_RUZENCE;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.HNED_HRAT;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.JAK_SE_POUZIVA;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.NA_UVOD;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.POLE_ZVUKU;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.RADOSTNY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.SLAVNY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.SLOVO_PAPEZE;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.SVETLA;
import static cz.appkypaulinky.ruzenec2.R.id.container;

public class ActivityTabRuzenec extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    public static int druhRuzence = 0;
    private boolean paused = false;
    private boolean play = false;
    private int[] audioResourceId;
    private int index = 0;
    public static MediaPlayer mediaPlayer;
    private FloatingActionButton fab;
    public PowerManager pm;
    TextView arrow;
    Context ctx;
    public SharedPreferences prefs;
    public static int textSize;
    public static boolean jeVidetVse;

//    int[] koralky = {0,0,0,0,0};


    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_ruzenec);

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textSize = prefs.getInt(TEXT_SIZE, 20);
        jeVidetVse = prefs.getBoolean(VISIBILITY, true);

        ctx = this;

        //zabrání přejít mobilu do režimu spánku
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = getIntent();
        druhRuzence = intent.getIntExtra(DRUH_RUZENCE, 0);
        boolean hnedHraj = intent.getBooleanExtra(HNED_HRAT, false);
        audioResourceId = intent.getIntArrayExtra(POLE_ZVUKU);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.i("ActivityTabRuzenec", "Nelze aplikovat setDisplayHomeAsUpEnabled");
        }
        getSupportActionBar().setTitle("Růženec");

        switch (druhRuzence) {
            case RADOSTNY:
                getSupportActionBar().setSubtitle("radostný");
                break;

            case BOLESTNY:
                getSupportActionBar().setSubtitle("bolestný");
                break;

            case SLAVNY:
                getSupportActionBar().setSubtitle("slavný");
                break;

            case SVETLA:
                getSupportActionBar().setSubtitle("světla");
                break;
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // ************  FAB
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (!play) {
                    play = true;
                    paused = false;
                    playHelper();
                } else {
                    play = false;
                    paused = true;
                    prestanHrat();
                }
            }
        });


        if (hnedHraj) {
            play = true;
            paused = false;
            playHelper();
        }

        Toast.makeText(this, R.string.toast_info_dalsi_desatek, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mViewPager.setCurrentItem(mViewPager.getCurrentItem());
    }

    private void prestanHrat() {
        mediaPlayer.release();
        mediaPlayer = null;
        changeFabIconToPlay(fab);
    }

    private void playHelper() {
        mediaPlayer = MediaPlayer.create(this,audioResourceId[index]);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start();
        changeFabIconToPause(fab);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (paused) {
            prestanHrat();
            playHelper();
        }
        else if((index+1)<audioResourceId.length) {
            index++;
            prestanHrat();
            playHelper();
        }
        else {
            index=0;
            play = false;
            changeFabIconToPlay(fab);
        }
    }

    public static void attachFragment ( int fragmentHolderLayoutId, Fragment fragment, Context context, String tag ) {

        FragmentManager manager = ( (AppCompatActivity) context ).getSupportFragmentManager ();
        manager.findFragmentByTag ( tag );
        FragmentTransaction ft = manager.beginTransaction ();

        if (manager.findFragmentByTag ( tag ) == null) { // No fragment in backStack with same tag..
            ft.add ( fragmentHolderLayoutId, fragment, tag );
            ft.addToBackStack ( tag );
            ft.commit ();
        }
        else {
            ft.show ( manager.findFragmentByTag ( tag ) ).commit ();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textSize = prefs.getInt(TEXT_SIZE, 20);
        jeVidetVse = prefs.getBoolean(VISIBILITY, true);

        //kvůli projevům změny velikosti textu
        int page = mViewPager.getCurrentItem();
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(page);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem settingsItem = menu.findItem(R.id.action_zobrazit_skryt_texty);
        // set your desired icon here based on a flag if you like
        if (jeVidetVse) {
            settingsItem.setIcon(getResources().getDrawable(R.mipmap.ic_visibility_off_white_24dp));
        } else {
            settingsItem.setIcon(getResources().getDrawable(R.mipmap.ic_visibility_white_24dp));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_tab_ruzenec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {

            case R.id.action_zobrazit_skryt_texty:
                SharedPreferences.Editor editor = prefs.edit();
                if (jeVidetVse) {
                    item.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_visibility_white_24dp));
                    editor.putBoolean("je vidět vše", false);
                    jeVidetVse = false;
                    editor.apply();
                    //kvůli projevům změny textu
                    int page = mViewPager.getCurrentItem();
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                    mViewPager.setCurrentItem(page);

                } else {
                    item.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_visibility_off_white_24dp));
                    editor.putBoolean("je vidět vše", true);
                    jeVidetVse = true;
                    editor.apply();
                    //kvůli projevům změny textu
                    int page = mViewPager.getCurrentItem();
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                    mViewPager.setCurrentItem(page);
                }
                return true;

            case R.id.action_text_size_change:
                intent = new Intent(ActivityTabRuzenec.this, ActivitySeekBar.class);
                ActivityTabRuzenec.this.startActivity(intent);
                return true;

            case R.id.action_slovo_papeze:
                intent = new Intent(ActivityTabRuzenec.this, ActivityEmpty.class);
                intent.putExtra("typ textu", SLOVO_PAPEZE);
                ActivityTabRuzenec.this.startActivity(intent);
                return true;

            case R.id.action_dalsi_modlitby:
                intent = new Intent(ActivityTabRuzenec.this, ActivityDalsiModlitby.class);
                intent.putExtra("typ textu", DALSI_MODLITBY);
                ActivityTabRuzenec.this.startActivity(intent);
                return true;

            case R.id.action_about_app:
                intent = new Intent(ActivityTabRuzenec.this, ActivityEmpty.class);
                intent.putExtra("typ textu", NA_UVOD);
                ActivityTabRuzenec.this.startActivity(intent);
                return true;

            case R.id.action_jak_se_pouziva:
                intent = new Intent(ActivityTabRuzenec.this, ActivityEmpty.class);
                intent.putExtra("typ textu", JAK_SE_POUZIVA);
                ActivityTabRuzenec.this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
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
//            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_activity_tab_ruzenec, container, false);

            int tajemstvi = getArguments().getInt(ARG_SECTION_NUMBER);

            Resources res = getResources();
            LinearLayout ll = rootView.findViewById(R.id.LL);

            TextView TVuvod = rootView.findViewById(R.id.uvod);
            TextView TVtajemstvi = rootView.findViewById(R.id.tajemstvi);
            TextView TVnazev = rootView.findViewById(R.id.nazev);
            TextView TVhlavni = rootView.findViewById(R.id.hlavni_text);
            TextView TVodkaz = rootView.findViewById(R.id.odkaz);
            TextView TVotcenasZdravas = rootView.findViewById(R.id.otcenas_zdravas);
            TextView TVsvataSlava = rootView.findViewById(R.id.svata_slava);
            TextView TVfatima = rootView.findViewById(R.id.fatimsky_dodatek);
            TextView TVdodatek = rootView.findViewById(R.id.dodatek);
            TextView TVzaver = rootView.findViewById(R.id.zaver);
            TextView TVmezera = rootView.findViewById(R.id.mezera);
            TextView TVarrow = rootView.findViewById(R.id.arrow);
            MyCustomView myCustomView = rootView.findViewById(R.id.custom_view);

//            final View customView = rootView.findViewById(R.id.custom_view);

            TVuvod.setTextSize(textSize);
            TVotcenasZdravas.setTextSize(textSize);
            TVsvataSlava.setTextSize(textSize);
            TVtajemstvi.setTextSize(textSize);
            TVnazev.setTextSize(textSize);
            TVhlavni.setTextSize(textSize);
            TVodkaz.setTextSize(textSize);
            TVfatima.setTextSize(textSize);
            TVdodatek.setTextSize(textSize);
            TVzaver.setTextSize(textSize);
            TVmezera.setTextSize(textSize);
            TVarrow.setTextSize(textSize*3);
            myCustomView.setTajemstvi(tajemstvi-1);

            if (tajemstvi < 5) {
                TVarrow.setVisibility(View.VISIBLE);
            } else {
                TVarrow.setVisibility(View.GONE);
            }

            TVtajemstvi.setText(getString(R.string.tajemstvi, getArguments().getInt(ARG_SECTION_NUMBER)));

            String[] nazev;
            String[] hlavni;
            String[] odkaz;
            String[] dodatek;

            switch(druhRuzence) {
                case RADOSTNY:
                    ll.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.radostBackground));

                    nazev = res.getStringArray(R.array.nazev_radost);
                    TVnazev.setText(nazev[tajemstvi-1]);
                    TVnazev.setTextColor(ContextCompat.getColor(getContext(), R.color.radostText));

                    hlavni = res.getStringArray(R.array.hlavni_text_radost);
                    TVhlavni.setText(hlavni[tajemstvi-1]);

                    odkaz = res.getStringArray(R.array.odkaz_radost);
                    TVodkaz.setText(odkaz[tajemstvi-1]);

                    dodatek = res.getStringArray(R.array.dodatek_radost);
                    TVdodatek.setText(dodatek[tajemstvi-1]);

                    break;

                case BOLESTNY:
                    ll.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bolestBackground));

                    nazev = res.getStringArray(R.array.nazev_bolest);
                    TVnazev.setText(nazev[tajemstvi-1]);
                    TVnazev.setTextColor(ContextCompat.getColor(getContext(), R.color.bolestText));

                    hlavni = res.getStringArray(R.array.hlavni_text_bolest);
                    TVhlavni.setText(hlavni[tajemstvi-1]);

                    odkaz = res.getStringArray(R.array.odkaz_bolest);
                    TVodkaz.setText(odkaz[tajemstvi-1]);

                    dodatek = res.getStringArray(R.array.dodatek_bolest);
                    TVdodatek.setText(dodatek[tajemstvi-1]);

                    break;

                case SLAVNY:
                    ll.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.slavaBackground));

                    nazev = res.getStringArray(R.array.nazev_slava);
                    TVnazev.setText(nazev[tajemstvi-1]);
                    TVnazev.setTextColor(ContextCompat.getColor(getContext(), R.color.slavaText));

                    hlavni = res.getStringArray(R.array.hlavni_text_slava);
                    TVhlavni.setText(hlavni[tajemstvi-1]);

                    odkaz = res.getStringArray(R.array.odkaz_slava);
                    TVodkaz.setText(odkaz[tajemstvi-1]);

                    dodatek = res.getStringArray(R.array.dodatek_slava);
                    TVdodatek.setText(dodatek[tajemstvi-1]);

                    break;

                default:
                    ll.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.svetloBackground));

                    nazev = res.getStringArray(R.array.nazev_svetlo);
                    TVnazev.setText(nazev[tajemstvi-1]);
                    TVnazev.setTextColor(ContextCompat.getColor(getContext(), R.color.svetloText));

                    hlavni = res.getStringArray(R.array.hlavni_text_svetlo);
                    TVhlavni.setText(hlavni[tajemstvi-1]);

                    odkaz = res.getStringArray(R.array.odkaz_svetlo);
                    TVodkaz.setText(odkaz[tajemstvi-1]);

                    dodatek = res.getStringArray(R.array.dodatek_svetlo);
                    TVdodatek.setText(dodatek[tajemstvi-1]);
            }

            //potřebuji sloučit zdrávas s tajemstvím:
            String zdravasStred = getString(R.string.otcenas_zdravas);
            zdravasStred = zdravasStred + " " + nazev[tajemstvi-1] + ".";

            if (Build.VERSION.SDK_INT >= 24) {
                TVuvod.setText(Html.fromHtml(getResources().getString(R.string.uvod), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                TVotcenasZdravas.setText(Html.fromHtml(zdravasStred, Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                TVsvataSlava.setText(Html.fromHtml(getResources().getString(R.string.svata_slava), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                TVfatima.setText(Html.fromHtml(getResources().getString(R.string.fatimsky_dodatek), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                TVzaver.setText(Html.fromHtml(getResources().getString(R.string.zaver), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                TVarrow.setText(Html.fromHtml(getResources().getString(R.string.arrow), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            } else {
                TVuvod.setText(Html.fromHtml(getResources().getString(R.string.uvod))); // or for older api
                TVotcenasZdravas.setText(Html.fromHtml(zdravasStred)); // or for older api
                TVsvataSlava.setText(Html.fromHtml(getResources().getString(R.string.svata_slava))); // or for older api
                TVfatima.setText(Html.fromHtml(getResources().getString(R.string.fatimsky_dodatek))); // or for older api
                TVzaver.setText(Html.fromHtml(getResources().getString(R.string.zaver))); // or for older api
                TVarrow.setText(Html.fromHtml(getResources().getString(R.string.arrow))); // or for older api
            }

            if (jeVidetVse) {
                TVotcenasZdravas.setVisibility(View.VISIBLE);
                TVsvataSlava.setVisibility(View.VISIBLE);
                TVfatima.setVisibility(View.VISIBLE);
            } else {
                TVotcenasZdravas.setVisibility(View.GONE);
                TVsvataSlava.setVisibility(View.GONE);
                TVfatima.setVisibility(View.GONE);
            }

            if (tajemstvi == 1 && jeVidetVse) {
                TVuvod.setVisibility(View.VISIBLE);
                TVzaver.setVisibility(View.GONE);
            } else if (tajemstvi == 5 && jeVidetVse) {
                TVuvod.setVisibility(View.GONE);
                TVzaver.setVisibility(View.VISIBLE);
            } else {
                TVuvod.setVisibility(View.GONE);
                TVzaver.setVisibility(View.GONE);
            }

            return rootView;
        }
    }

    public void nextPage(View view) {
        int page = mViewPager.getCurrentItem();
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(page+1);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    protected class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
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
            // Show 3 total pages.
            return 5;
        }
    }

    private void changeFabIconToPause(FloatingActionButton fab) {
        fab.setImageResource(R.mipmap.ic_pause_white_24dp);
    }

    private void changeFabIconToPlay(FloatingActionButton fab) {
        fab.setImageResource(R.mipmap.ic_play_arrow_white_24dp);
    }

    @Override
    protected void onStop() {
        super.onStop();

        boolean isScreenOn = true;
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

        if (Build.VERSION.SDK_INT >= 20) try {
            isScreenOn = pm.isInteractive();
        } catch (NullPointerException e) {
            Log.i("ActivityTabRuzenec", "Nenačetl se powermanager...");
        }
        else try {
            isScreenOn = pm.isScreenOn();
        } catch (NullPointerException e) {
            Log.i("ActivityTabRuzenec", "Nenačetl se powermanager...");
        }


        if (isScreenOn && (mediaPlayer != null)) {
            play = false;
            paused = true;
            prestanHrat();
        }
    }
    

    public void Viber(Context cn, String value) {
        if (value.equals("on")) {
            try {
                Vibrator v = (Vibrator) cn.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(300);

            } catch (NullPointerException e) {
                Log.i("ActivityTabRuzenec", "Chyba při pokusu o vibrace");
            }
        }
    }
}
