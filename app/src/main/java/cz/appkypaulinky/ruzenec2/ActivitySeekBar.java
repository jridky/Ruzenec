package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.TEXT_SIZE;

public class ActivitySeekBar extends AppCompatActivity {

    TextView testText;
    protected int textSize;
    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textSize = prefs.getInt(TEXT_SIZE, 20);

        setTitle(getResources().getString(R.string.change_text_size_title));

        //inicializace + nastavení hodnoty z Shared prefs
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(textSize);

        //inicializace + nastavení hodnoty z Shared prefs
        testText = (TextView) findViewById(R.id.testText);
        testText.setTextSize(textSize);

        //listener pro seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            SharedPreferences.Editor editor = prefs.edit();

            @Override
            //
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                testText.setTextSize(progresValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textSize = seekBar.getProgress();
                editor.putInt(TEXT_SIZE, textSize);
                editor.commit();
            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //pokud se klepne na tlačítko UP, tak se vrátí do předchozí aktivity, jako při klepnutí na zpět
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }


}
