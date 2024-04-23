package cz.appkypaulinky.ruzenec2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.BOLESTNY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.RADOSTNY;
import static cz.appkypaulinky.ruzenec2.ActivityVyberRuzenec.SLAVNY;

/**
 * Created by ales on 15.11.17.
 */

public class Generator {
    private LinkedList<Integer> ticho = new LinkedList<>();
    private LinkedList<Integer> playlist = new LinkedList<>();
    private LinkedList<Integer> krizList = new LinkedList<>();
    private LinkedList<Integer> verimListUvod = new LinkedList<>();
    private LinkedList<Integer> verimListZaver = new LinkedList<>();
    private LinkedList<Integer> otcenasListUvod = new LinkedList<>();
    private LinkedList<Integer> otcenasListZaver = new LinkedList<>();
    private LinkedList<Integer> zdravasList = new LinkedList<>();
    private LinkedList<Integer> svataList = new LinkedList<>();
    private LinkedList<Integer> tajUvodVerimeList = new LinkedList<>();
    private LinkedList<Integer> tajUvodDoufameList = new LinkedList<>();
    private LinkedList<Integer> tajUvodMilujemeList = new LinkedList<>();
    private LinkedList<Integer> slavaListUvod = new LinkedList<>();
    private LinkedList<Integer> slavaListZaver = new LinkedList<>();
    private LinkedList<Integer> fatimskyDodatek = new LinkedList<>();
    private LinkedList<Integer> tajemstviRadostList = new LinkedList<>();
    private LinkedList<Integer> tajemstviBolestList = new LinkedList<>();
    private LinkedList<Integer> tajemstviSlavaList = new LinkedList<>();
    private LinkedList<Integer> tajemstviSvetloList = new LinkedList<>();
    private LinkedList<Integer> kralovnoList = new LinkedList<>();
    private LinkedList<Integer> hlavniTextRadostList = new LinkedList<>();
    private LinkedList<Integer> hlavniTextBolestList = new LinkedList<>();
    private LinkedList<Integer> hlavniTextSlavaList = new LinkedList<>();
    private LinkedList<Integer> hlavniTextSvetloList = new LinkedList<>();
    private LinkedList<Integer> dodatekRadostList = new LinkedList<>();
    private LinkedList<Integer> dodatekBolestList = new LinkedList<>();
    private LinkedList<Integer> dodatekSlavaList = new LinkedList<>();
    private LinkedList<Integer> dodatekSvetloList = new LinkedList<>();
    private LinkedList<Integer> orodujListUvod = new LinkedList<>();
    private LinkedList<Integer> orodujListZaver = new LinkedList<>();
    private LinkedList<Integer> zaverList = new LinkedList<>();
    private LinkedList<Integer> amenList = new LinkedList<>();

    protected Generator() {
        naplnSeznamy();
        zamichejSeznamy();
    }

    private void naplnSeznamy() {
        ticho.add(R.raw.silence);

        krizList.add(R.raw.znameni_krize1);
        krizList.add(R.raw.znameni_krize2);

        verimListUvod.add(R.raw.credo_uvod1);
        verimListUvod.add(R.raw.credo_uvod2);
        verimListZaver.add(R.raw.credo_zaver1);
        verimListZaver.add(R.raw.credo_zaver2);

        otcenasListUvod.add(R.raw.otce_nas_uvod1);
        otcenasListUvod.add(R.raw.otce_nas_uvod2);
        otcenasListZaver.add(R.raw.otce_nas_zaver1);
        otcenasListZaver.add(R.raw.otce_nas_zaver2);

        zdravasList.add(R.raw.zdravas1);
        zdravasList.add(R.raw.zdravas2);

        svataList.add(R.raw.svata1);
        svataList.add(R.raw.svata2);

        tajUvodVerimeList.add(R.raw.verime1);
        tajUvodVerimeList.add(R.raw.verime2);
        tajUvodDoufameList.add(R.raw.doufame1);
        tajUvodDoufameList.add(R.raw.doufame2);
        tajUvodMilujemeList.add(R.raw.milujeme1);
        tajUvodMilujemeList.add(R.raw.milujeme2);

        slavaListUvod.add(R.raw.slava_uvod1);
        slavaListUvod.add(R.raw.slava_uvod2);
        slavaListZaver.add(R.raw.slava_zaver1);
        slavaListZaver.add(R.raw.slava_zaver2);

        fatimskyDodatek.add(R.raw.fatim1);
        fatimskyDodatek.add(R.raw.fatim2);

        hlavniTextRadostList.add(R.raw.hlavni_radost1);
        hlavniTextRadostList.add(R.raw.hlavni_radost2);
        hlavniTextRadostList.add(R.raw.hlavni_radost3);
        hlavniTextRadostList.add(R.raw.hlavni_radost4);
        hlavniTextRadostList.add(R.raw.hlavni_radost5);

        hlavniTextBolestList.add(R.raw.hlavni_bolest1);
        hlavniTextBolestList.add(R.raw.hlavni_bolest2);
        hlavniTextBolestList.add(R.raw.hlavni_bolest3);
        hlavniTextBolestList.add(R.raw.hlavni_bolest4);
        hlavniTextBolestList.add(R.raw.hlavni_bolest5);

        hlavniTextSlavaList.add(R.raw.hlavni_slavna1);
        hlavniTextSlavaList.add(R.raw.hlavni_slavna2);
        hlavniTextSlavaList.add(R.raw.hlavni_slavna3);
        hlavniTextSlavaList.add(R.raw.hlavni_slavna4);
        hlavniTextSlavaList.add(R.raw.hlavni_slavna5);

        hlavniTextSvetloList.add(R.raw.hlavni_svetlo1);
        hlavniTextSvetloList.add(R.raw.hlavni_svetlo2);
        hlavniTextSvetloList.add(R.raw.hlavni_svetlo3);
        hlavniTextSvetloList.add(R.raw.hlavni_svetlo4);
        hlavniTextSvetloList.add(R.raw.hlavni_svetlo5);

        tajemstviRadostList.add(R.raw.radost1);
        tajemstviRadostList.add(R.raw.radost2);
        tajemstviRadostList.add(R.raw.radost3);
        tajemstviRadostList.add(R.raw.radost4);
        tajemstviRadostList.add(R.raw.radost5);

        tajemstviBolestList.add(R.raw.bolest1);
        tajemstviBolestList.add(R.raw.bolest2);
        tajemstviBolestList.add(R.raw.bolest3);
        tajemstviBolestList.add(R.raw.bolest4);
        tajemstviBolestList.add(R.raw.bolest5);

        tajemstviSlavaList.add(R.raw.slavna1);
        tajemstviSlavaList.add(R.raw.slavna2);
        tajemstviSlavaList.add(R.raw.slavna3);
        tajemstviSlavaList.add(R.raw.slavna4);
        tajemstviSlavaList.add(R.raw.slavna5);

        tajemstviSvetloList.add(R.raw.svetlo1);
        tajemstviSvetloList.add(R.raw.svetlo2);
        tajemstviSvetloList.add(R.raw.svetlo3);
        tajemstviSvetloList.add(R.raw.svetlo4);
        tajemstviSvetloList.add(R.raw.svetlo5);

        dodatekRadostList.add(R.raw.dodatek_radost1);
        dodatekRadostList.add(R.raw.dodatek_radost2);
        dodatekRadostList.add(R.raw.dodatek_radost3);
        dodatekRadostList.add(R.raw.dodatek_radost4);
        dodatekRadostList.add(R.raw.dodatek_radost5);

        dodatekBolestList.add(R.raw.dodatek_bolest1);
        dodatekBolestList.add(R.raw.dodatek_bolest2);
        dodatekBolestList.add(R.raw.dodatek_bolest3);
        dodatekBolestList.add(R.raw.dodatek_bolest4);
        dodatekBolestList.add(R.raw.dodatek_bolest5);

        dodatekSlavaList.add(R.raw.dodatek_slavna1);
        dodatekSlavaList.add(R.raw.dodatek_slavna2);
        dodatekSlavaList.add(R.raw.dodatek_slavna3);
        dodatekSlavaList.add(R.raw.dodatek_slavna4);
        dodatekSlavaList.add(R.raw.dodatek_slavna5);

        dodatekSvetloList.add(R.raw.dodatek_svetlo1);
        dodatekSvetloList.add(R.raw.dodatek_svetlo2);
        dodatekSvetloList.add(R.raw.dodatek_svetlo3);
        dodatekSvetloList.add(R.raw.dodatek_svetlo4);
        dodatekSvetloList.add(R.raw.dodatek_svetlo5);

        kralovnoList.add(R.raw.kralovna);

        orodujListUvod.add(R.raw.oroduj_uvod);

        orodujListZaver.add(R.raw.oroduj_zaver1);
        orodujListZaver.add(R.raw.oroduj_zaver2);
        orodujListZaver.add(R.raw.oroduj_zaver3);

        zaverList.add(R.raw.zaver);

        amenList.add(R.raw.amen1);
        amenList.add(R.raw.amen2);
    }

    private void zamichejSeznamy() {
        Collections.shuffle(krizList);
        Collections.shuffle(verimListUvod);
        Collections.shuffle(verimListZaver);
        Collections.shuffle(otcenasListUvod);
        Collections.shuffle(otcenasListZaver);
        Collections.shuffle(zdravasList);
        Collections.shuffle(svataList);
        Collections.shuffle(tajUvodVerimeList);
        Collections.shuffle(tajUvodDoufameList);
        Collections.shuffle(tajUvodMilujemeList);
        Collections.shuffle(slavaListUvod);
        Collections.shuffle(slavaListZaver);
        Collections.shuffle(fatimskyDodatek);
        Collections.shuffle(kralovnoList);
        Collections.shuffle(orodujListZaver);
        Collections.shuffle(amenList);
    }

    private void pridejTicho() {
        playlist.add(ticho.get(0));
        Collections.shuffle(ticho);
    }

    private void pridejKriz() {
        playlist.add(krizList.get(0));
        Collections.shuffle(krizList);
    }

    private void pridejVerim() {
        playlist.add(verimListUvod.get(0));
        Collections.shuffle(verimListUvod);
        playlist.add(verimListZaver.get(0));
        Collections.shuffle(verimListZaver);
    }

    private void pridejOtcenas() {
        playlist.add(otcenasListUvod.get(0));
        Collections.shuffle(otcenasListUvod);
        playlist.add(otcenasListZaver.get(0));
        Collections.shuffle(otcenasListZaver);
    }

    private void pridejZdravas() {
        playlist.add(zdravasList.get(0));
        Collections.shuffle(zdravasList);
    }

    private void pridejSvata() {
        playlist.add(svataList.get(0));
        Collections.shuffle(svataList);
    }

    private void pridejTajUvodVerime() {
        playlist.add(tajUvodVerimeList.get(0));
        pridejSvata();
    }

    private void pridejTajUvodDoufame() {
        playlist.add(tajUvodDoufameList.get(0));
        pridejSvata();
    }

    private void pridejTajUvodMilujeme() {
        playlist.add(tajUvodMilujemeList.get(0));
        pridejSvata();
    }

    private void pridejSlava() {
        playlist.add(slavaListUvod.get(0));
        Collections.shuffle(slavaListUvod);
        playlist.add(slavaListZaver.get(0));
        Collections.shuffle(slavaListZaver);
    }

    private void pridejFatimskyDodatek() {
        playlist.add(fatimskyDodatek.get(0));
        Collections.shuffle(fatimskyDodatek);
    }

    private void pridejHlavniText(int druhRuzence, int kteryDesatek) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(hlavniTextRadostList.get(kteryDesatek-1));
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(hlavniTextBolestList.get(kteryDesatek-1));
        } else if (druhRuzence == SLAVNY) {
            playlist.add(hlavniTextSlavaList.get(kteryDesatek-1));
        } else { // SVETLA
            playlist.add(hlavniTextSvetloList.get(kteryDesatek-1));
        }
    }

    private void pridejTajemstvi(int druhRuzence, int desatek) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(tajemstviRadostList.get(desatek-1));
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(tajemstviBolestList.get(desatek-1));
        } else if (druhRuzence == SLAVNY) {
            playlist.add(tajemstviSlavaList.get(desatek-1));
        } else { // SVETLA
            playlist.add(tajemstviSvetloList.get(desatek-1));
        }
    }
        private void pridejDodatek(int druhRuzence, int kteryDesatek) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(dodatekRadostList.get(kteryDesatek-1));
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(dodatekBolestList.get(kteryDesatek-1));
        } else if (druhRuzence == SLAVNY) {
            playlist.add(dodatekSlavaList.get(kteryDesatek-1));
        } else { // SVETLA
            playlist.add(dodatekSvetloList.get(kteryDesatek-1));
        }
    }

    private void pridejKralovno() {
        playlist.add(kralovnoList.get(0));
    }

    private void pridejOroduj() {
        playlist.add(orodujListUvod.get(0));
        playlist.add(orodujListZaver.get(0));
    }

    private void pridejZaverecnouModlitbu() {
        playlist.add(zaverList.get(0));
        playlist.add(amenList.get(0));
    }

    //*************************** tady probíhá hlavní příprava růžence + převod na klasické pole
    protected int[] pripravRuzenec(int druhRuzence) {
        vyprazdniPlaylist();

        //***************** úvod
        pridejTicho();
        pridejKriz();
        pridejVerim();
        pridejOtcenas();
        pridejTajUvodVerime();
        pridejTajUvodDoufame();
        pridejTajUvodMilujeme();
        pridejSlava();

        //***************** tady vytvořím desátky
        for (int desatek = 1; desatek <= 5; desatek++) {
            pridejHlavniText(druhRuzence, desatek);
            pridejOtcenas();
            for (int i = 0; i<10; i++) {
                pridejZdravas();
                pridejTajemstvi(druhRuzence, desatek);
                pridejSvata();
            }
            pridejSlava();
            pridejFatimskyDodatek();
            pridejDodatek(druhRuzence, desatek);
        }

        pridejKralovno();
        pridejOroduj();
        pridejZaverecnouModlitbu();

        // převedu na klasické pole int
        int[] pole = new int[playlist.size()];
        for (int i=0; i<playlist.size(); i++) {
            pole[i] = playlist.get(i);
        }
        return pole;
    }

    private void vyprazdniPlaylist() {
        playlist.clear();
    }


    public int[] pripravDesatek(int druhRuzence, int kteryDesatek) {
        vyprazdniPlaylist();

        pridejKriz();

        pridejHlavniText(druhRuzence, kteryDesatek);
        pridejOtcenas();
        for (int i = 0; i<10; i++) {
            pridejZdravas();
            pridejTajemstvi(druhRuzence, kteryDesatek);
            pridejSvata();
        }
        pridejSlava();
        pridejFatimskyDodatek();
        pridejDodatek(druhRuzence, kteryDesatek);

        // převedu na klasické pole int
        int[] pole = new int[playlist.size()];
        for (int i=0; i<playlist.size(); i++) {
            pole[i] = playlist.get(i);
        }

        return pole;
    }

}
