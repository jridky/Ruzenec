package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.TEXT_SIZE;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.DALSI_MODLITBY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.JAK_SE_MODLI_RUZENEC;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.JAK_SE_POUZIVA;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.NA_UVOD;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.SLOVO_PAPEZE;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.TYP_TEXTU;

public class ActivityEmpty extends AppCompatActivity {

    TextView mainTextView;
    ImageView ruzenec;
    int typTextu;
    SharedPreferences prefs;
    int textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Intent intent = getIntent();
        typTextu = intent.getIntExtra(TYP_TEXTU, NA_UVOD);

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textSize = prefs.getInt(TEXT_SIZE, 20);

        mainTextView = findViewById(R.id.tv_empty);
        mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        ruzenec = findViewById(R.id.im_empty);


        switch (typTextu) {
            case NA_UVOD:
                setTitle(R.string.action_o_aplikaci);
                if (Build.VERSION.SDK_INT >= 24) {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.na_uvod_content), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                } else {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.na_uvod_content))); // or for older api
                }
                break;

            case JAK_SE_POUZIVA:
                setTitle(R.string.action_jak_se_pouziva);
                if (Build.VERSION.SDK_INT >= 24) {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.jak_se_pouziva_content), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                } else {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.jak_se_pouziva_content))); // or for older api
                }
                break;

            case SLOVO_PAPEZE:
                setTitle(R.string.action_slovo_papeze);
                if (Build.VERSION.SDK_INT >= 24) {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.slovo_papeze), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                } else {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.slovo_papeze))); // or for older api
                }
                break;

                //v tomto případě je třeba i zjistit, jak je obrazovka široká a dle toho upravit šířku imageview, aby byl obrázek přes celou obrazovku!
            case JAK_SE_MODLI_RUZENEC:
                setTitle(R.string.action_jak_se_modli_ruzenec);

                mainTextView.setVisibility(View.GONE);
                ruzenec.setVisibility(View.VISIBLE);

                PhotoViewAttacher pAttacher;
                pAttacher = new PhotoViewAttacher(ruzenec);
                pAttacher.update();

                //zároveň potřebuji změnit pozadí na černé
                ScrollView sv = findViewById(R.id.sv_empty);
                sv.setBackgroundColor(Color.BLACK);

                break;

            case DALSI_MODLITBY:
                setTitle(R.string.action_dalsi_modlitby);
                if (Build.VERSION.SDK_INT >= 24) {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                } else {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby))); // or for older api
                }
                break;

            default:
                setTitle(R.string.action_o_aplikaci);
                if (Build.VERSION.SDK_INT >= 24) {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.na_uvod_content), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
                } else {
                    mainTextView.setText(Html.fromHtml(getResources().getString(R.string.na_uvod_content))); // or for older api
                }
                break;
        }

        mainTextView.setMovementMethod((LinkMovementMethod.getInstance()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (typTextu == JAK_SE_MODLI_RUZENEC) {
            return true;
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_text_size, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_text_size_change:
                Intent myIntent2 = new Intent(ActivityEmpty.this, ActivitySeekBar.class);
                ActivityEmpty.this.startActivity(myIntent2);
                return true;

            //pokud se klepne na tlačítko UP, tak se vrátí do předchozí aktivity, jako při klepnutí na zpět
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

}
