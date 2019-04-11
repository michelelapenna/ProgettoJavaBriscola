package game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import card.Carta;
import card.MazzoCarte;
import main.Testo;
import player.Giocatore1;
import player.GiocatoreCPU;

public class Partita {


    private Giocatore1 P1;
    private GiocatoreCPU P2;
    private MazzoCarte mazzo;
    private Carta briscola;



    public Partita() {
        Scanner in = new Scanner(System.in);
        String nomeG;
        mazzo = new MazzoCarte();
        this.briscola = mazzo.estraiBriscola();
        incrementaQualitàBriscole();
        System.out.print("Il tuo nome per questa partita: ");
        do {
            nomeG = in.nextLine();
            if(nomeG.length()<3)
                System.out.print("Errore, il tuo nome contiene meno di 3 caratteri.\nInserisci un nome valido:");
        }while (nomeG.length()<3);
        P1 = new Giocatore1(nomeG, mazzo);
        P2 = new GiocatoreCPU("GiocatoreCPU", mazzo);
        P2.setBriscolaPartita(getBriscola());//LA CPU MEMORIZZA LA BRISCOLA DELLA PARTITA
        lancioMoneta();
    }


    //METODI GET E SET
    public Carta getBriscola() {
        return this.briscola;
    }

    public Giocatore1 getGiocatore1(){
        return this.P1;
    }

    public GiocatoreCPU getGiocatoreCPU(){
        return this.P2;
    }

    //INCREMENTA LA QUALITA' DI TUTTE LE CARTE DELLA PARTITA AVENTI IL SEME UGUALE ALLA BRISCOLA ESTRATTA
    private void incrementaQualitàBriscole(){
        for (Carta m:mazzo.getMazzo()) {
            if (m.getSeme()==briscola.getSeme()){
                m.setQualità(m.getQualità()+50);
            }else{
                m.setQualità(m.getQualità());
            }
        }
    }

    //DECIDE QUALE GIOCATORE DEVE INCOMINCIARE LA PARTITA
    private void lancioMoneta() {
        if ((int) (Math.random()*2)== 0) {// se esce 0 incomincia Giocatore1
            System.out.println("\nLa sorte ha deciso che incomincerà \033[34m"+P1.getNome()+"\033[19m");
            incomiciaTurno(true);
        } else{// se esce 1 incomincia GiocatoreCPU
            System.out.println("\nLa sorte ha deciso che incomincerà \033[31m"+P2.getNome()+"\033[19m");
            incomiciaTurno(false);
        }
    }

    //SETTA CHI INCOMICIA IL TURNO
    private void incomiciaTurno(boolean vince) {
        P1.setVinceMano(vince);
        P2.setVinceMano(!vince);
    }

    //GESTISCE IL TURNO DI GIOCO
    public void turno()  {
        Carta primaCarta,secondaCarta,cartaVince;
        if(P1.getVinceMano()){//SE IL GIOCATORE 1 è PRIMO A GIOCARE
            System.out.println("\033[34mE' il turno di " + P1.getNome()+"\033[30m");
            System.out.println("\033[34mMano di "+P1.getNome()+" : "+"\033[30m"+P1.getMano());
            primaCarta = P1.selezionaCarta();
            System.out.println("\033[34mHai giocato "+"\033[30m"+ primaCarta+"\n");
            P2.setCartaAvversario(primaCarta);//LA CPU MEMORIZZA LA CARTA
            System.out.println("\033[31m"+P2.getNome()+" sta pensando..."+"\033[30m");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            secondaCarta = P2.selezionaCarta();
            System.out.println("\033[31m"+P2.getNome()+" ha giocato "+"\033[30m"+ secondaCarta+"\n");
        } else {// SE LA CPU è PRIMA A GIOCARE
            System.out.println("\033[31mE' il turno di " + P2.getNome()+"\033[30m");
            System.out.println("\033[31m"+P2.getNome()+" sta pensando...");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            primaCarta = P2.selezionaCarta();
            System.out.println(P2.getNome()+" ha giocato "+"\033[30m"+ primaCarta+"\n");
            System.out.println("\033[34mMano di "+P1.getNome()+" : "+"\033[30m"+P1.getMano());
            secondaCarta = P1.selezionaCarta();
            System.out.println("\033[34mHai giocato\033[30m "+ secondaCarta+"\n");


        }
        cartaVince = vince(primaCarta,secondaCarta);

        //SE VINCE LA CARTA DEL PRIMO GIOCATORE
        if((cartaVince==primaCarta && P1.getVinceMano()) || (cartaVince==secondaCarta && P2.getVinceMano())){
            System.out.println("\033[34mHa preso " + P1.getNome()+"\033[19m");
            incomiciaTurno(true);//INCOMINCERA' PER PRIMO IL TURNO SUCCESSIVO
            P1.aggiungiPresa(primaCarta);
            P1.aggiungiPresa(secondaCarta);
            if (mazzo.checkMazzoNonVuoto()) {//PESCA PRIMA IL PRIMO GIOCATORE
                P1.pescaCarta(mazzo.rimuoviCarta());
                P2.pescaCarta(mazzo.rimuoviCarta());
            }
            //SE VINCE LA CARTA DEL GIOCATORECPU
        }else if((cartaVince==secondaCarta && P1.getVinceMano()) || (cartaVince==primaCarta && P2.getVinceMano())) {
            System.out.println("\033[31mHa preso " + P2.getNome()+"\033[19m");
            incomiciaTurno(false);//INCOMINCIERA' PER PRIMO IL TURNO SUCCESSIVO
            P2.aggiungiPresa(primaCarta);
            P2.aggiungiPresa(secondaCarta);
            if (mazzo.checkMazzoNonVuoto()) {//PESCA PRIMA GIOCATORECPU
                P2.pescaCarta(mazzo.rimuoviCarta());
                P1.pescaCarta(mazzo.rimuoviCarta());
            }
        }
    }

    //STABILISCE LA CARTA VINCENTE TRA LE 2 GIOCATE
    private Carta vince(Carta A, Carta B) {
        if (A.getSeme()==B.getSeme()){// SE LE CARTE HANNO SEME UGUALE VINCE CHI HA LA QUALITA' MAGGIORE
            if (A.getQualità()>B.getQualità()){
                return A;
            }else {
                return  B;
            }
        }else if (A.getSeme()!=B.getSeme() && B.getQualità()<50){//SE LE CARTE HANNO SEME DIFFERENTE E LA SECONDA CARTA NON è BRISCOLA VINCE LA PRIMA
            return A;
        }else{
            return B;
        }
    }

    //STAMPA I RISPETTIVI PUNTEGGI E STABILISCE IL VINCITORE DELLA PARTITA
    public void vincitore() {
        System.out.println("\n\033[34mPrese di "+P1.getNome()+": \033[30m"+P1.getPrese());
        System.out.println("\033[34m"+P1.getNome()+" ha totalizzato "+"\033[34m"+P1.getPunteggio()+" punti"+"\033[30m\n");
        System.out.println("\033[31mPrese di "+P2.getNome()+": \033[30m"+P2.getPrese());
        System.out.println("\033[31m"+P2.getNome()+" ha totalizzato "+"\033[31m"+P2.getPunteggio()+" punti"+"\033[30m");
        if (P1.getPunteggio() > P2.getPunteggio()) {
            System.out.println("\n\033[34m"+P1.getNome()+" ha vinto la partita!\033[19m");
            Testo.finegioco1();
        }
        else if (P2.getPunteggio() == P1.getPunteggio()) {
            System.out.println("\nI giocatori hanno totalizzato lo stesso punteggio, quindi è pareggio!\n");
            Testo.finegioco3();
        } else {
            System.out.println("\n\033[31m"+P2.getNome()+" ha vinto la partita!\033[19m");
            Testo.finegioco2();
        }
    }


}