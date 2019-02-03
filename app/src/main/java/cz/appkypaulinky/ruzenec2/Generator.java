package cz.appkypaulinky.ruzenec2;

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
    private LinkedList<Integer> verimList = new LinkedList<>();
    private LinkedList<Integer> otcenasList = new LinkedList<>();
    private LinkedList<Integer> zdravasList = new LinkedList<>();
    private LinkedList<Integer> svataList = new LinkedList<>();
    private LinkedList<Integer> tajUvodVerimeList = new LinkedList<>();
    private LinkedList<Integer> tajUvodDoufameList = new LinkedList<>();
    private LinkedList<Integer> tajUvodMilujemeList = new LinkedList<>();
    private LinkedList<Integer> slavaList = new LinkedList<>();
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

    protected Generator() {
        naplnSeznamy();
        zamichejSeznamy();
    }

    private void naplnSeznamy() {
        ticho.add(R.raw.silence);

        krizList.add(R.raw.kriz_uni);

        verimList.add(R.raw.v01);
        verimList.add(R.raw.v02);
        verimList.add(R.raw.v03);
        verimList.add(R.raw.v04);
        verimList.add(R.raw.v05);

        otcenasList.add(R.raw.on_uni);

        zdravasList.add(R.raw.z_uni);

        svataList.add(R.raw.sv01);
        svataList.add(R.raw.sv02);
        svataList.add(R.raw.sv03);
        svataList.add(R.raw.sv04);
        svataList.add(R.raw.sv05);
        svataList.add(R.raw.sv06);
        svataList.add(R.raw.sv07);
        svataList.add(R.raw.sv08);
        svataList.add(R.raw.sv09);
        svataList.add(R.raw.sv10);
        svataList.add(R.raw.sv_uni);

        tajUvodVerimeList.add(R.raw.taj_uvod_ver_01);
        tajUvodDoufameList.add(R.raw.taj_uvod_dou_01);
        tajUvodMilujemeList.add(R.raw.taj_uvod_nad_01);

        slavaList.add(R.raw.sl_uni);

        fatimskyDodatek.add(R.raw.sl_uni);

        hlavniTextRadostList.add(R.raw.hlavni_text_uni);
        hlavniTextBolestList.add(R.raw.hlavni_text_uni);
        hlavniTextSlavaList.add(R.raw.hlavni_text_uni);
        hlavniTextSvetloList.add(R.raw.hlavni_text_uni);

        tajemstviRadostList.add(R.raw.tajemstvi_radost);
        tajemstviBolestList.add(R.raw.tajemstvi_bolest);
        tajemstviSlavaList.add(R.raw.tajemstvi_slava);
        tajemstviSvetloList.add(R.raw.tajemstvi_svetla);

        dodatekRadostList.add(R.raw.dodatek_uni);
        dodatekBolestList.add(R.raw.dodatek_uni);
        dodatekSlavaList.add(R.raw.dodatek_uni);
        dodatekSvetloList.add(R.raw.dodatek_uni);

        kralovnoList.add(R.raw.kral01);
        kralovnoList.add(R.raw.kral02);
        kralovnoList.add(R.raw.kral03);
        kralovnoList.add(R.raw.kral04);
        kralovnoList.add(R.raw.kral05);
    }

    private void zamichejSeznamy() {
        Collections.shuffle(krizList);
        Collections.shuffle(verimList);
        Collections.shuffle(otcenasList);
        Collections.shuffle(zdravasList);
        Collections.shuffle(svataList);
        Collections.shuffle(tajUvodVerimeList);
        Collections.shuffle(tajUvodDoufameList);
        Collections.shuffle(tajUvodMilujemeList);
        Collections.shuffle(slavaList);
        Collections.shuffle(fatimskyDodatek);
        Collections.shuffle(kralovnoList);
        Collections.shuffle(tajemstviRadostList);
        Collections.shuffle(tajemstviBolestList);
        Collections.shuffle(tajemstviSlavaList);
        Collections.shuffle(tajemstviSvetloList);
    }

    private void pridejTicho() {
        playlist.add(ticho.get(0));
        if (ticho.size()>1) {
            ticho.remove(0);
        }
    }

    private void pridejKriz() {
        playlist.add(krizList.get(0));
        if (krizList.size()>1) {
            krizList.remove(0);
        }
    }

    private void pridejVerim() {
        playlist.add(verimList.get(0));
        if (verimList.size()>1) {
            verimList.remove(0);
        }
    }

    private void pridejOtcenas() {
        playlist.add(otcenasList.get(0));
        if (otcenasList.size()>1) {
            otcenasList.remove(0);
        }
    }

    private void pridejZdravas() {
        playlist.add(zdravasList.get(0));
        if (zdravasList.size()>1) {
            zdravasList.remove(0);
        }
    }

    private void pridejSvata() {
        playlist.add(svataList.get(0));
        if (svataList.size()>1) {
            svataList.remove(0);
        }
    }

    private void pridejTajUvodVerime() {
        playlist.add(tajUvodVerimeList.get(0));
        if (tajUvodVerimeList.size()>1) {
            tajUvodVerimeList.remove(0);
        }
    }

    private void pridejTajUvodDoufame() {
        playlist.add(tajUvodDoufameList.get(0));
        if (tajUvodDoufameList.size()>1) {
            tajUvodDoufameList.remove(0);
        }
    }

    private void pridejTajUvodMilujeme() {
        playlist.add(tajUvodMilujemeList.get(0));
        if (tajUvodMilujemeList.size()>1) {
            tajUvodMilujemeList.remove(0);
        }
    }

    private void pridejSlava() {
        playlist.add(slavaList.get(0));
        if (slavaList.size()>1) {
            slavaList.remove(0);
        }
    }

    private void pridejFatimskyDodatek() {
        playlist.add(fatimskyDodatek.get(0));
        if (fatimskyDodatek.size()>1) {
            fatimskyDodatek.remove(0);
        }
    }

    private void pridejHlavniText(int druhRuzence) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(hlavniTextRadostList.get(0));

            if (hlavniTextRadostList.size() > 1) {
                hlavniTextRadostList.remove(0);
            }
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(hlavniTextBolestList.get(0));

            if (hlavniTextBolestList.size() > 1) {
                hlavniTextBolestList.remove(0);
            }
        } else if (druhRuzence == SLAVNY) {
            playlist.add(hlavniTextSlavaList.get(0));

            if (hlavniTextSlavaList.size() > 1) {
                hlavniTextSlavaList.remove(0);
            }
        } else { // SVETLA
            playlist.add(hlavniTextSvetloList.get(0));

            if (hlavniTextSvetloList.size() > 1) {
                hlavniTextSvetloList.remove(0);
            }
        }
    }

    private void pridejHlavniText(int druhRuzence, int kteryDesatek) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(hlavniTextRadostList.get(kteryDesatek));
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(hlavniTextBolestList.get(kteryDesatek));
        } else if (druhRuzence == SLAVNY) {
            playlist.add(hlavniTextSlavaList.get(kteryDesatek));
        } else { // SVETLA
            playlist.add(hlavniTextSvetloList.get(kteryDesatek));
        }
    }

    private void pridejTajemstvi(int druhRuzence) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(tajemstviRadostList.get(0));

            if (tajemstviRadostList.size()>1) {
                tajemstviRadostList.remove(0);
            }
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(tajemstviBolestList.get(0));

            if (tajemstviBolestList.size()>1) {
                tajemstviBolestList.remove(0);
            }
        } else if (druhRuzence == SLAVNY) {
            playlist.add(tajemstviSlavaList.get(0));

            if (tajemstviSlavaList.size()>1) {
                tajemstviSlavaList.remove(0);
            }
        } else { // SVETLA
            playlist.add(tajemstviSvetloList.get(0));

            if (tajemstviSvetloList.size()>1) {
                tajemstviSvetloList.remove(0);
            }
        }
    }

    private void pridejDodatek(int druhRuzence) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(dodatekRadostList.get(0));

            if (dodatekRadostList.size() > 1) {
                dodatekRadostList.remove(0);
            }
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(dodatekBolestList.get(0));

            if (dodatekBolestList.size() > 1) {
                dodatekBolestList.remove(0);
            }
        } else if (druhRuzence == SLAVNY) {
            playlist.add(dodatekSlavaList.get(0));

            if (dodatekSlavaList.size() > 1) {
                dodatekSlavaList.remove(0);
            }
        } else { // SVETLA
            playlist.add(dodatekSvetloList.get(0));

            if (dodatekSvetloList.size() > 1) {
                dodatekSvetloList.remove(0);
            }
        }
    }

    private void pridejDodatek(int druhRuzence, int kteryDesatek) {
        if (druhRuzence == RADOSTNY) {
            playlist.add(dodatekRadostList.get(kteryDesatek));
        } else if (druhRuzence == BOLESTNY) {
            playlist.add(dodatekBolestList.get(kteryDesatek));
        } else if (druhRuzence == SLAVNY) {
            playlist.add(dodatekSlavaList.get(kteryDesatek));
        } else { // SVETLA
            playlist.add(dodatekSvetloList.get(kteryDesatek));
        }
    }

    private void pridejKralovno() {
        playlist.add(kralovnoList.get(0));
        if (kralovnoList.size()>1) {
            kralovnoList.remove(0);
        }
    }

    //*************************** tady probíhá hlavní příprava růžence + převod na klasické pole
    protected int[] pripravRuzenec(int druhRuzence) {
        vyprazdniPlaylist();

        //***************** úvod
        pridejTicho();
        pridejKriz();
        pridejVerim();
        pridejOtcenas();
        pridejZdravas();
        pridejTajUvodVerime();
        pridejSvata();
        pridejZdravas();
        pridejTajUvodDoufame();
        pridejSvata();
        pridejZdravas();
        pridejTajUvodMilujeme();
        pridejSvata();
        pridejSlava();

        //***************** tady vytvořím desátky
        for (int desatek = 1; desatek <= 5; desatek++) {
            pridejHlavniText(druhRuzence);
            pridejOtcenas();
            for (int i = 0; i<10; i++) {
                pridejZdravas();
                pridejTajemstvi(druhRuzence);
                pridejSvata();
            }
            pridejSlava();
            pridejFatimskyDodatek();
            pridejDodatek(druhRuzence);
        }

        pridejKralovno();

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
            pridejTajemstvi(druhRuzence);
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
