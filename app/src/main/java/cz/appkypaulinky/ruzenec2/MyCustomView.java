package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.Context.VIBRATOR_SERVICE;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;

public class MyCustomView extends View  {

//    int pocet;
    int tajemstvi;
    final int ZDRAVAS = 0;
    final int CELY_DESATEK = 1;
    public static int[] koralky = {0,0,0,0,0};
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    public SharedPreferences prefs;
    String koralkyAktualne;


    public MyCustomView(Context context) {
        super(context);
        init(null);
//        this.setOnClickListerner(this);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    public void setTajemstvi(int cislo) {
        tajemstvi = cislo;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        prefs = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        int mezera = getWidth()/11;
        int vyska = getHeight()/2;
        int radius;

        //tady načtu korálky se sharedPrefs:
        String koralkyZeZalohy = prefs.getString("korálky2", "0 0 0 0 0");
        String[] koralkyString = koralkyZeZalohy.split(" ");

        try {
            koralky[0] = Integer.valueOf(koralkyString[0]);
            koralky[1] = Integer.valueOf(koralkyString[1]);
            koralky[2] = Integer.valueOf(koralkyString[2]);
            koralky[3] = Integer.valueOf(koralkyString[3]);
            koralky[4] = Integer.valueOf(koralkyString[4]);
        } catch (Exception e) {
            Log.e("MyCustomView", "chyba při načítání proměnných");
        }


        if ((getWidth()/25) < (getHeight())) {
            radius = getWidth()/25;
        } else {
            radius = getHeight();
        }

        paint.setColor(Color.rgb(139,69,19));
        paint2.setColor(Color.WHITE);

        //tady kreslím prázdné korálky
        for(int i=1; i<11; i++){
            canvas.drawCircle(mezera*i, vyska, radius, paint);
            canvas.drawCircle(mezera*i, vyska, radius*9/10, paint2);
        }

        //tady korálky naplním
        for (int i=1; i<=koralky[tajemstvi]; i++) {
            canvas.drawCircle(mezera * i, vyska, radius, paint);
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                if (koralky[tajemstvi]<10) {
                    koralky[tajemstvi]++;
                    koralkyAktualne = "" + koralky[0] + " " + koralky[1] + " " + koralky[2] + " " + koralky[3] + " " + koralky[4];
                    editor.putString("korálky2", koralkyAktualne);
                    editor.apply();
                } else {
                    koralky[tajemstvi] = 0;
                    koralkyAktualne = "" + koralky[0] + " " + koralky[1] + " " + koralky[2] + " " + koralky[3] + " " + koralky[4];
                    editor.putString("korálky2", koralkyAktualne);
                    editor.apply();
                }

                if (koralky[tajemstvi] == 10) {
                    vibrate(CELY_DESATEK);
                } else {
                    vibrate(ZDRAVAS);
                }

                // Redraw the view.
                invalidate();
            }
        });
    }


    private void vibrate(int hodnota) {
        int x = 25;
        long[] pattern = {0, 25, 200, 25};

        if (Build.VERSION.SDK_INT >= 26) {
            try {
                if (hodnota == ZDRAVAS) {
                    ((Vibrator) getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(x, VibrationEffect.DEFAULT_AMPLITUDE));
                }
                if (hodnota == CELY_DESATEK) {
                    ((Vibrator) getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createWaveform(pattern, -1));
                }

            } catch (NullPointerException e){
                Log.e("MyCustomView", "Chyba při pokusu o vibrace");
            }

        } else {
            try {
                if (hodnota == ZDRAVAS) {
                    ((Vibrator) getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(x);
                }
                if (hodnota == CELY_DESATEK) {
                    ((Vibrator) getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(pattern, -1);
                }


            } catch (NullPointerException e){
                Log.e("MyCustomView", "Chyba při pokusu o vibrace");
            }
        }
    }
}