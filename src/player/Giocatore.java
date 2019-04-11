package player;

import card.Carta;
import card.MazzoCarte;

import java.util.ArrayList;

abstract class Giocatore {

    private String nome;
    private int punteggio;
    private ArrayList<Carta> prese;
    protected ArrayList<Carta> mano;
    protected boolean vinceMano;

    public Giocatore(String nome, MazzoCarte mazzo) {
        punteggio = 0;
        prese = new ArrayList<Carta>();
        mano = new ArrayList<Carta>();
        this.nome = nome;
        for(int i=0;i<3;i++) {
            pescaCarta(mazzo.rimuoviCarta());
        }
    }

    public ArrayList<Carta> getPrese() {
        return prese;
    }

    //METODI GET E SET
    public ArrayList<Carta> getMano() {
        return this.mano;
    }

    public int getPunteggio() {
        return this.punteggio;
    }

    public String getNome() {
        return this.nome;
    }

    public void setVinceMano(boolean vince) {
        vinceMano = vince;
    }

    public boolean getVinceMano(){
        return vinceMano;
    }

    //METODO CHE AGGIUNGE UNA CARTA DAL MAZZO ALLA MANO
    public void pescaCarta(Carta C) {
        this.mano.add(C);
    }

    //METODO CHE AGGIUNGE LE PRESE AL PROPRIO MAZZO DELLE PRESE
    public void aggiungiPresa(Carta P) {
        this.prese.add(P);
    }

    //METODO CHE CALCOLA A FINE PARTITA I PUNTI DELLE CARTE PRESENTI NEL MAZZO DELLE PRESE
    public void contaPunti() {
        for (Carta o: prese) {
            if (o.getNum() == 1)
                punteggio += 11;
            if (o.getNum() == 3)
                punteggio += 10;
            if (o.getNum() == 8)
                punteggio += 2;
            if (o.getNum() == 9)
                punteggio += 3;
            if (o.getNum() == 10)
                punteggio += 4;
        }
    }

    //METODO CHE SELEZIONA LA CARTA DA GIOCARE
    public abstract Carta selezionaCarta();

 }
