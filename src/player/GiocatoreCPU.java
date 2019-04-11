package player;

import card.MazzoCarte;
import card.Carta;

public class GiocatoreCPU extends Giocatore{


    private Carta cartaAvversario;
    private Carta briscolaPartita;



    public GiocatoreCPU(String nome, MazzoCarte mazzo) {
        super(nome, mazzo);
    }

    //METODI GET E SET
    private Carta getCartaAvversario() {
        return cartaAvversario;
    }

    public void setCartaAvversario(Carta a){
        cartaAvversario = a;
    }

    public void setBriscolaPartita(Carta a){
        briscolaPartita = a;

    }

    private Carta getBriscolaPartita() {
        return briscolaPartita;
    }

    private Carta getCartaMano(int i){
        return mano.get(i);
    }

    //METODO SELEZIONA CARTA IMPLEMENTATO DAL GIOCATORE CPU
    public Carta selezionaCarta() {

        Carta selezionata = null;
        int ok1 = 0,ok2 = 0,ok3 = 0;
        Carta carta1,carta2,carta3;

        if (mano.size() == 3) {// SE LA MANO è COMPOSTA DA 3 CARTE
            carta1 = getCartaMano(0);
            carta2 = getCartaMano(1);
            carta3 = getCartaMano(2);
            if (vinceMano) {//SE LA CPU è PRIMA A GIOCARE
                selezionata = cartaMinore(carta1, carta2, carta3);
            } else {//SE LA CPU è SECONDA A GIOCARE
                if (getCartaAvversario().getSeme() == getBriscolaPartita().getSeme()) {
                    selezionata = cartaMinore(carta1, carta2, carta3);
                } else if (getCartaAvversario().getSeme() != getBriscolaPartita().getSeme()) {
                    if (getCartaAvversario().getSeme() == carta1.getSeme() && carta1.getQualità() > getCartaAvversario().getQualità()) {
                        ok1 = 1;
                    }
                    if (getCartaAvversario().getSeme() == carta2.getSeme() && carta2.getQualità() > getCartaAvversario().getQualità()) {
                        ok2 = 1;
                    }
                    if (getCartaAvversario().getSeme() == carta3.getSeme() && carta3.getQualità() > getCartaAvversario().getQualità()) {
                        ok3 = 1;
                    }
                    if (ok1 == 1 && ok2 == 0 && ok3 == 0) {
                        selezionata = mano.remove(0);
                    } else if (ok1 == 0 && ok2 == 1 && ok3 == 0) {
                        selezionata = mano.remove(1);
                    } else if (ok1 == 0 && ok2 == 0 && ok3 == 1) {
                        selezionata = mano.remove(2);
                    } else if (ok1 == 1 && ok2 == 1 && ok3 == 0) {
                        if (carta1.getQualità() > carta2.getQualità()) {
                            selezionata = mano.remove(0);
                        } else {
                            selezionata = mano.remove(1);
                        }
                    } else if (ok1 == 1 && ok2 == 0) { // && ok3 == 1
                        if (carta1.getQualità() > carta3.getQualità()) {
                            selezionata = mano.remove(0);
                        } else {
                            selezionata = mano.remove(2);
                        }
                    } else if (ok1 == 0 && ok2 == 1 ) { //&& ok3 == 1
                        if (carta2.getQualità() > carta3.getQualità()) {
                            selezionata = mano.remove(1);
                        } else {
                            selezionata = mano.remove(2);
                        }
                    } else if (ok1 == 1 ) {//&& ok2 == 1 && ok3 == 1
                            selezionata = cartaMaggiore(carta1, carta2, carta3);
                    } else { //(ok1 == 0 && ok2 == 0 && ok3 == 0)
                        if (getCartaAvversario().getNum() == 1 || getCartaAvversario().getNum() == 3) {
                            if (carta1.getSeme() == getBriscolaPartita().getSeme()) {
                                selezionata = mano.remove(0);
                            } else if (carta2.getSeme() == getBriscolaPartita().getSeme()) {
                                selezionata = mano.remove(1);
                            } else if (carta3.getSeme()== getBriscolaPartita().getSeme()) {
                                selezionata = mano.remove(2);
                            } else {
                                selezionata = cartaMinore(carta1, carta2, carta3);
                            }
                        } else {
                            selezionata = cartaMinore(carta1, carta2, carta3);
                        }
                    }
                }
            }

        } else if (mano.size() == 2) {// SE LA CPU HA 2 CARTE IN MANO
            carta1 = getCartaMano(0);
            carta2 = getCartaMano(1);
            if (vinceMano) {
                selezionata = cartaMinore(carta1, carta2);
            } else{
                if (getCartaAvversario().getSeme() == getBriscolaPartita().getSeme()) {
                    selezionata = cartaMinore(carta1, carta2);
                } else{
                    if (getCartaAvversario().getSeme() == carta1.getSeme() && carta1.getQualità() > getCartaAvversario().getQualità()) {
                        selezionata = mano.remove(0);
                    } else if (getCartaAvversario().getSeme() == carta2.getSeme() && carta2.getQualità() > getCartaAvversario().getQualità()) {
                        selezionata = mano.remove(1);
                    } else if(getCartaAvversario().getNum()==1 || getCartaAvversario().getNum()==3){
                        if (carta1.getSeme()== briscolaPartita.getSeme()) {
                            selezionata = mano.remove(0);
                        } else if(carta2.getSeme()== briscolaPartita.getSeme()) {
                            selezionata = mano.remove(1);
                        } else {
                            selezionata = cartaMinore(carta1, carta2);
                        }
                    }else {
                        selezionata = cartaMinore(carta1, carta2);
                    }
                }
            }
        } else if (mano.size() == 1) {// SE LA CPU HA 1 SOLA CARTA IN MANO
            selezionata = mano.remove(0);
        }
        return selezionata;
    }

    //RITORNA LA CARTA DI MINOR QUALITA' CHE SI HA IN MANO
    private Carta cartaMinore(Carta ... args) {
        int i=0;
        trattieniCarichi();
        if (args.length==3) {
            if (args[0].getQualità() <= args[1].getQualità() && args[0].getQualità() <= args[2].getQualità()) {
                i=0;
            } else if (args[1].getQualità() <= args[0].getQualità() && args[1].getQualità() <= args[2].getQualità()) {
                i=1;
            } else if (args[2].getQualità() <= args[0].getQualità() && args[2].getQualità() <= args[1].getQualità()) {
                i=2;
            }
        } else {
            if (args[0].getQualità() <= args[1].getQualità()) {
                i=0;
            } else if (args[1].getQualità() <= args[0].getQualità()) {
               i=1;
            }
        }
        resetTrattieniCarichi();
        return mano.remove(i);
    }

    //AUMENTA LA QUALITA' DEI CARICHI IN MODO CHE QUANDO VIENE UTILIZZATO IL METODO CARTA MINORE LA CPU PREFERISCE PERDERE IN SITUAZIONI CRITICHE UNA BRISCOLA AL POSTO DI UN CARICO
    private void trattieniCarichi(){
        for (Carta c: mano) {
            if (c.getNum()==1){
                c.setQualità(c.getQualità()+42);
            }
            if (c.getNum()==3){
                c.setQualità(c.getQualità()+48);
            }
        }
    }

    //RESETTA LA QUALITA' DEI CARICHI
    private void resetTrattieniCarichi(){
        for (Carta c: mano) {
            if (c.getNum()==1){
                c.setQualità(c.getQualità()-42);
            }
            if (c.getNum()==3){
                c.setQualità(c.getQualità()-48);
            }
        }
    }

    //RITORNA LA CARTA DI MAGGIOR QUALITA' CHE SI HA IN MANO
    private Carta cartaMaggiore(Carta... args) {
        int i=0;
            if (args[0].getQualità() > args[1].getQualità() && args[0].getQualità() > args[2].getQualità()) {
                i=0;
            } else if (args[1].getQualità() > args[0].getQualità() && args[1].getQualità() > args[2].getQualità()) {
                i=1;
            } else if (args[2].getQualità() > args[0].getQualità() && args[2].getQualità() > args[1].getQualità()) {
                i=2;
            }
        return mano.remove(i);
    }


}