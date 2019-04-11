package card;

import java.util.ArrayList;
import java.util.Collections;

public class MazzoCarte {


    private ArrayList<Carta> mazzo;


    public MazzoCarte() {
        mazzo = new ArrayList<Carta>();
        riempiMazzo();
        mescolaMazzo();
    }


    //METODI GET E SET
    public ArrayList<Carta> getMazzo() {
        return mazzo;
    }

    //ESTRAE LA CARTA CHE DETERMINERA' IL SEME DI BRISCOLA DELLA PARTITA E LA PONE IN ULTIMA POSIZIONE DEL MAZZO
    public Carta estraiBriscola() {
        Carta briscola;
        briscola = mazzo.get(0);
        mazzo.remove(0);
        mazzo.add(briscola);
        return briscola;
    }

    //RIMUOVE E RITORNA LA PRIMA CARTA DEL MAZZO
    public Carta rimuoviCarta() {
            return this.mazzo.remove(0);
    }

    //CONTROLLA SE IL MAZZO NON è VUOTO
    public boolean checkMazzoNonVuoto(){
        return !mazzo.isEmpty();
    }

    //RIEMPIE IL MAZZO CON LE 40 CARTE (10 PER OGNI SEME)
    private void riempiMazzo() {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i <= 10; i++)
                this.mazzo.add(new Carta(Seme.values()[j], i));
        }
    }

    //MESCOLA RANDOM IL MAZZO DI CARTE
    private void mescolaMazzo(){
        Collections.shuffle(this.mazzo);
    }
}