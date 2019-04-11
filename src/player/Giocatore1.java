package player;

import card.Carta;
import card.MazzoCarte;

import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore1 extends Giocatore {


    public Giocatore1(String nome, MazzoCarte mazzo) {
        super(nome, mazzo);
    }

    //METODO SELEZIONA CARTA IMPLEMENTATO DAL GIOCATORE UMANO
    public Carta selezionaCarta() {
        Carta selezionata = null;
        int c;
        boolean esci = true;
        System.out.print("\033[34mGioca una carta: \033[30m");
        do {
            try {
                c = new Scanner(System.in).nextInt();
                try {
                    selezionata = getMano().remove(c - 1);
                    esci = false;
                    } catch (Exception cartaNULLA) {
                        System.out.println("Errore, non hai selezionato alcuna carta! Scegli una carta che hai in mano...");
                    }
                }catch(Exception lettera){
                    System.out.println("Errore non ha digitato un numero! Seleziona una carta...");
                }
        }while (esci);
        return selezionata;
    }


}