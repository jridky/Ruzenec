package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

//tato aktivita pouze zobrazí úvodní obrázek
public class ActivityIntroScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "cz.paulinky.ruzenec2";
    public static final String TEXT_SIZE = "velikost textu";
    public static final String VISIBILITY = "je vidět vše";

    public SharedPreferences prefs;
    protected int textSize;
    protected boolean jeVidetVse;

    static Timer timer = new Timer();
    static boolean isEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      schovám statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//      schovám ActionBar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_intro_screen);

        //otevřu preference a načtu viditelnost z minula
        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        jeVidetVse = prefs.getBoolean(VISIBILITY, true);
        MyTimer();
    }

    public void skip(View v) {
        Intent intent = new Intent(this, ActivityVyberRuzenec.class);
        startActivity(intent);
        isEnded = true;
        finish();
    }

    // musel jsem přidat proměnnou isEnded, protože thread MyTimer běžel dál i v případě dotyku a zbytečně ukončoval aktuální aktivitu :)
    void skip() {
        if (!isEnded) {
            Intent intent = new Intent(this, ActivityVyberRuzenec.class);
            startActivity(intent);
            finish();
        }
    }

    // timer hlídá čas, po který je zobrazen úvodní obrázek (po třech sekundách ho zavře)
    public void MyTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                skip();
            }
        }, 3*1000);

    }
}
