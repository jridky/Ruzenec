package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
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

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        EdgeToEdge.enable(this);

//      schovám ActionBar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_intro_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_root), (v, windowInsets) -> {
            Insets systemBarsInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets cutoutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(), v);
            if (insetsController != null) {
                insetsController.setAppearanceLightStatusBars(false);
            }
            v.setPadding(Math.max(systemBarsInsets.left, cutoutInsets.left),
                         Math.max(systemBarsInsets.top, cutoutInsets.top),
                         Math.max(systemBarsInsets.right, cutoutInsets.right),
                         (systemBarsInsets.bottom < 100 ? 0 : systemBarsInsets.bottom));
            return windowInsets;
        });

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
