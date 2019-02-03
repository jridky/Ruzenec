package cz.appkypaulinky.ruzenec2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.SHARED_PREFS;
import static cz.appkypaulinky.ruzenec2.ActivityIntroScreen.TEXT_SIZE;


public class ActivityDalsiModlitby extends AppCompatActivity {

    boolean litanie = false;
    boolean podOchranu = false;
    boolean zdravaBud = false;
    boolean pamatuj = false;
    boolean zdravasKralovno = false;
    boolean fatimaZauzivana = false;
    boolean fatimaSpravna = false;
    boolean andeliNovejsi = false;
    boolean andeliStarsi = false;
    int textSize;
    public SharedPreferences prefs;


    TextView tvLitanie;
    TextView tvLitanieText;
    TextView tvPodOchranu;
    TextView tvPodOchranuText;
    TextView tvZdravaBud;
    TextView tvZdravaBudText;
    TextView tvPamatuj;
    TextView tvPamatujText;
    TextView tvZdravasKralovno;
    TextView tvZdravasKralovnoText;
    TextView tvFatimaSpravna;
    TextView tvFatimaSpravnaText;
    TextView tvFatimaZauzivana;
    TextView tvFatimaZauzivanaText;
    TextView tvAndeliNove;
    TextView tvAndeliNoveText;
    TextView tvAndeliStare;
    TextView tvAndeliStareText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalsi_modlitby);
        setTitle(R.string.action_dalsi_modlitby);

        prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        textSize = prefs.getInt(TEXT_SIZE, 20);

        tvLitanie = findViewById(R.id.tv_litanie);
        tvLitanieText = findViewById(R.id.tv_litanie_text);
        tvPodOchranu = findViewById(R.id.tv_pod_ochranu);
        tvPodOchranuText = findViewById(R.id.tv_pod_ochranu_text);
        tvZdravaBud = findViewById(R.id.tv_zdrava_bud);
        tvZdravaBudText = findViewById(R.id.tv_zdrava_bud_text);
        tvPamatuj = findViewById(R.id.tv_pamatuj);
        tvPamatujText = findViewById(R.id.tv_pamatuj_text);
        tvZdravasKralovno = findViewById(R.id.tv_zdravas_kralovno);
        tvZdravasKralovnoText = findViewById(R.id.tv_zdravas_kralovno_text);
        tvFatimaSpravna = findViewById(R.id.tv_fatimsky_spravna);
        tvFatimaSpravnaText = findViewById(R.id.tv_fatimsky_spravna_text);
        tvFatimaZauzivana = findViewById(R.id.tv_fatimsky_zauzivana);
        tvFatimaZauzivanaText = findViewById(R.id.tv_fatimsky_zauzivana_text);
        tvAndeliStare = findViewById(R.id.tv_archandeli_starsi);
        tvAndeliStareText = findViewById(R.id.tv_archandeli_starsi_text);
        tvAndeliNove = findViewById(R.id.tv_archandeli_novejsi);
        tvAndeliNoveText = findViewById(R.id.tv_archandeli_novejsi_text);


        tvLitanie.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvLitanieText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPodOchranu.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPodOchranuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravaBud.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravaBudText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPamatuj.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPamatujText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravasKralovno.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravasKralovnoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliNove.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliStare.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaSpravna.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaZauzivana.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        if (Build.VERSION.SDK_INT >= 24) {
            tvLitanie.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_litanie_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvLitanieText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_litanie), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvPodOchranu.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pod_ochranu_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvPodOchranuText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pod_ochranu), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvZdravaBud.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdrava_bud_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvZdravaBudText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdrava_bud), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvPamatuj.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pamatuj_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvPamatujText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pamatuj), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvZdravasKralovno.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdravas_kralovno_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvZdravasKralovnoText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdravas_kralovno), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvFatimaZauzivana.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_zauzivana_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvFatimaZauzivanaText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_zauzivana), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvFatimaSpravna.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_spravna_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvFatimaSpravnaText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_spravna), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvAndeliStare.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_starsi_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvAndeliStareText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_starsi), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more

            tvAndeliNove.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_novejsi_nadpis), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
            tvAndeliNoveText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_novejsi), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more


        } else {
            tvLitanie.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_litanie_nadpis))); // or for older api
            tvLitanieText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_litanie))); // or for older api

            tvPodOchranu.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pod_ochranu_nadpis))); // or for older api
            tvPodOchranuText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pod_ochranu))); // or for older api

            tvZdravaBud.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdrava_bud_nadpis))); // or for older api
            tvZdravaBudText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdrava_bud))); // or for older api

            tvPamatuj.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pamatuj_nadpis))); // or for older api
            tvPamatujText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_pamatuj))); // or for older api

            tvZdravasKralovno.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdravas_kralovno_nadpis))); // or for older api
            tvZdravasKralovnoText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_zdravas_kralovno))); // or for older api

            tvAndeliNove.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_novejsi_nadpis))); // or for older api
            tvAndeliNoveText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_novejsi))); // or for older api

            tvAndeliStare.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_starsi_nadpis))); // or for older api
            tvAndeliStareText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_svaty_michaeli_starsi))); // or for older api

            tvFatimaSpravna.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_spravna_nadpis))); // or for older api
            tvFatimaSpravnaText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_spravna))); // or for older api

            tvFatimaZauzivana.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_zauzivana_nadpis))); // or for older api
            tvFatimaZauzivanaText.setText(Html.fromHtml(getResources().getString(R.string.dalsi_modlitby_fatymsky_dodatek_zauzivana))); // or for older api
        }
    }

    public void litanieSwitch(View view) {
        if (!litanie) {
            litanie = true;
            tvLitanieText.setVisibility(View.VISIBLE);
        } else {
            litanie = false;
            tvLitanieText.setVisibility(View.GONE);
        }
    }

    public void podOchranuSwitch(View view) {
        if (!podOchranu) {
            podOchranu = true;
            tvPodOchranuText.setVisibility(View.VISIBLE);
        } else {
            podOchranu = false;
            tvPodOchranuText.setVisibility(View.GONE);
        }
    }

    public void zdravaBudSwitch(View view) {
        if (!zdravaBud) {
            zdravaBud = true;
            tvZdravaBudText.setVisibility(View.VISIBLE);
        } else {
            zdravaBud = false;
            tvZdravaBudText.setVisibility(View.GONE);
        }
    }

    public void zdravasKralovnoSwitch(View view) {
        if (!zdravasKralovno) {
            zdravasKralovno = true;
            tvZdravasKralovnoText.setVisibility(View.VISIBLE);
        } else {
            zdravasKralovno = false;
            tvZdravasKralovnoText.setVisibility(View.GONE);
        }
    }

    public void pamatujSwitch(View view) {
        if (!pamatuj) {
            pamatuj = true;
            tvPamatujText.setVisibility(View.VISIBLE);
        } else {
            pamatuj = false;
            tvPamatujText.setVisibility(View.GONE);
        }
    }

    public void fatimaZauzivanaSwitch(View view) {
        if (!fatimaZauzivana) {
            fatimaZauzivana = true;
            tvFatimaZauzivanaText.setVisibility(View.VISIBLE);
        } else {
            fatimaZauzivana = false;
            tvFatimaZauzivanaText.setVisibility(View.GONE);
        }
    }

    public void fatimaSpravnaSwitch(View view) {
        if (!fatimaSpravna) {
            fatimaSpravna = true;
            tvFatimaSpravnaText.setVisibility(View.VISIBLE);
        } else {
            fatimaSpravna = false;
            tvFatimaSpravnaText.setVisibility(View.GONE);
        }
    }

    public void archandeliNovejsiSwitch(View view) {
        if (!andeliNovejsi) {
            andeliNovejsi = true;
            tvAndeliNoveText.setVisibility(View.VISIBLE);
        } else {
            andeliNovejsi = false;
            tvAndeliNoveText.setVisibility(View.GONE);
        }
    }

    public void archandeliStarsiSwitch(View view) {
        if (!andeliStarsi) {
            andeliStarsi = true;
            tvAndeliStareText.setVisibility(View.VISIBLE);
        } else {
            andeliStarsi = false;
            tvAndeliStareText.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_text_size, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_text_size_change:
                Intent myIntent2 = new Intent(ActivityDalsiModlitby.this, ActivitySeekBar.class);
                ActivityDalsiModlitby.this.startActivity(myIntent2);
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
        tvLitanie.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvLitanieText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPodOchranu.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPodOchranuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravaBud.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravaBudText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPamatuj.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvPamatujText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravasKralovno.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvZdravasKralovnoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliStare.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliStareText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliNove.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvAndeliNoveText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaSpravna.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaSpravnaText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaZauzivana.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvFatimaZauzivanaText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

    }


}
