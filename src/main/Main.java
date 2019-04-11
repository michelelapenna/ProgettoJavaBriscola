package main;

import game.Partita;

public class Main {
    public static void main(String[] args){
        Testo.schermataIniziale();
        Partita P = new Partita();
        Testo.infocomandi();
        System.out.println("BRISCOLA estratta: "+P.getBriscola()+"\n");
        while (!P.getGiocatore1().getMano().isEmpty() && !P.getGiocatoreCPU().getMano().isEmpty()) {
            P.turno();
            Testo.divisore();
        }
        P.getGiocatore1().contaPunti();
        P.getGiocatoreCPU().contaPunti();
        P.vincitore();
    }
}
