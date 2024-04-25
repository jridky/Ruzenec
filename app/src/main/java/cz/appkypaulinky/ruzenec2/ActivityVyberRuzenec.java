package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Calendar;

import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;

public class ActivityVyberRuzenec extends AppCompatActivity {

    public static final String HNED_HRAT = "Mám to hned přehrát?";
    public static final String POLE_ZVUKU = "Pole zvuků";
    public static final String DRUH_RUZENCE = "Druh růžence";
    public static final String TYP_TEXTU = "typ textu";

    public static final int RADOSTNY = 0;
    public static final int BOLESTNY = 1;
    public static final int SLAVNY = 2;
    public static final int SVETLA = 3;

    public static final int NA_UVOD = 1;
    public static final int SLOVO_PAPEZE = 2;
    public static final int DALSI_MODLITBY = 5;
    public static final int JAK_SE_MODLI_RUZENEC = 6;
    public static final int JAK_SE_POUZIVA = 7;
    public static final int PAULINKY = 8;

    public SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vyber_ruzenec);

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        zvyrazniAktualniDen();



        // FAB
        FloatingActionButton fab;
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int den = jakyJeDnesDen();

                switch (den) {
                    case Calendar.MONDAY:
                    case Calendar.SATURDAY:
                        vybrano(RADOSTNY, true);
                        break;
                    case Calendar.TUESDAY:
                    case Calendar.FRIDAY:
                        vybrano(BOLESTNY, true);
                        break;
                    case Calendar.WEDNESDAY:
                    case Calendar.SUNDAY:
                        vybrano(SLAVNY, true);
                        break;
                    case Calendar.THURSDAY:
                        vybrano(SVETLA, true);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //vynuluji korálky v případě, že jdu vybrat jiný růženec
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("korálky2", "0 0 0 0 0");
        editor.apply();
    }

    // tady upravuji velikost písma na vstupních tlačítkách, aby nebylo ani malé ani velké
    @Override
    protected void onPostResume() {
        super.onPostResume();
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxText = size.x/15;

        TextView tvRadost = findViewById(R.id.tvRadost);
        TextView tvBolest = findViewById(R.id.tvBolest);
        TextView tvSlava = findViewById(R.id.tvSlava);
        TextView tvSvetlo = findViewById(R.id.tvSvetlo);

        float px = maxText;

        float sp = px / getResources().getDisplayMetrics().scaledDensity;
        tvRadost.setTextSize(sp);
        tvBolest.setTextSize(sp);
        tvSlava.setTextSize(sp);
        tvSvetlo.setTextSize(sp);
    }

    protected void vybrano(int typRuzence, boolean hnedHrat) {
        Generator generator = new Generator();
        vibrate(20);
        Intent intent = new Intent(this, ActivityTabRuzenec.class);
        int[] audioResource = generator.pripravRuzenec(typRuzence);
        intent.putExtra(POLE_ZVUKU, audioResource);
        intent.putExtra(DRUH_RUZENCE, typRuzence);
        intent.putExtra(HNED_HRAT, hnedHrat);
        startActivity(intent);
    }

    //metoda vrátí aktuální den v číslech
    public int jakyJeDnesDen() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void zvyrazniAktualniDen() {

        TextView tvRadost = findViewById(R.id.tvRadost);
        TextView tvBolest = findViewById(R.id.tvBolest);
        TextView tvSlava = findViewById(R.id.tvSlava);
        TextView tvSvetlo = findViewById(R.id.tvSvetlo);

        TextView tvRadostDen = findViewById(R.id.tvRadostDen);
        TextView tvBolestDen = findViewById(R.id.tvBolestDen);
        TextView tvSlavaDen = findViewById(R.id.tvSlavaDen);
        TextView tvSvetloDen = findViewById(R.id.tvSvetloDen);

        //tady způsobím, že se karta zakolíbá
        Animation anim_bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        CardView cvRadost = findViewById(R.id.cardViewRadost);
        CardView cvBolest = findViewById(R.id.cardViewBolest);
        CardView cvSlava = findViewById(R.id.cardViewSlava);
        CardView cvSvetlo = findViewById(R.id.cardViewSvetlo);

        //zvýrazním a zanimuji tlačítko odpovídající aktuálnímu dni
        switch (jakyJeDnesDen()) {
            case Calendar.MONDAY:
            case Calendar.SATURDAY:
                tvRadost.setTextColor(Color.WHITE);
                tvRadostDen.setTextColor(Color.WHITE);
                cvRadost.startAnimation(anim_bounce);
                break;
            case Calendar.TUESDAY:
            case Calendar.FRIDAY:
                tvBolest.setTextColor(Color.WHITE);
                tvBolestDen.setTextColor(Color.WHITE);
                cvBolest.startAnimation(anim_bounce);
                break;
            case Calendar.WEDNESDAY:
            case Calendar.SUNDAY:
                tvSlava.setTextColor(Color.WHITE);
                tvSlavaDen.setTextColor(Color.WHITE);
                cvSlava.startAnimation(anim_bounce);
                break;
            case Calendar.THURSDAY:
                tvSvetlo.setTextColor(Color.WHITE);
                tvSvetloDen.setTextColor(Color.WHITE);
                cvSvetlo.startAnimation(anim_bounce);
                break;
        }

    }

    public void radost(View v) {
        vybrano(RADOSTNY, false);
    }

    public void bolest(View v) {
        vybrano(BOLESTNY, false);
    }

    public void slava(View v) {
        vybrano(SLAVNY, false);
    }

    public void svetlo(View v) {
        vybrano(SVETLA, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vyber_ruzenec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        int itemId = item.getItemId();
        if (itemId == R.id.action_slovo_papeze) {
            intent = new Intent(this, ActivityEmpty.class);
            intent.putExtra(TYP_TEXTU, SLOVO_PAPEZE);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_jak_se_modli_ruzenec) {
            intent = new Intent(this, ActivityEmpty.class);
            intent.putExtra(TYP_TEXTU, JAK_SE_MODLI_RUZENEC);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_dalsi_modlitby) {
            intent = new Intent(this, ActivityDalsiModlitby.class);
            intent.putExtra(TYP_TEXTU, DALSI_MODLITBY);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_na_uvod) {
            intent = new Intent(this, ActivityEmpty.class);
            intent.putExtra(TYP_TEXTU, NA_UVOD);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_jak_se_pouziva) {
            intent = new Intent(this, ActivityEmpty.class);
            intent.putExtra(TYP_TEXTU, JAK_SE_POUZIVA);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_about_paulines) {
            intent = new Intent(this, ActivityEmpty.class);
            intent.putExtra(TYP_TEXTU, PAULINKY);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void vibrate(int x) {

                if (Build.VERSION.SDK_INT >= 26) {
                    try {
                        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(x, VibrationEffect.DEFAULT_AMPLITUDE));
                    } catch (NullPointerException e){
                        Log.e("Vibrator", "Někde se stala chyba");
                    }

                } else {
                    try {
                        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(x);
                    } catch (NullPointerException e){
                        Log.e("Vibrator", "Někde se stala chyba");
                    }
                }
    }
}
