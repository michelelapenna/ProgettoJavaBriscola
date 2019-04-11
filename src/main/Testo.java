package main;

public class Testo {

    public static void schermataIniziale(){
        System.out.println("\033[32m@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "@                                                   @\n" +
                "@    ####  ####  #  ####  ####  ####  #      ##     @\n" +
                "@    #  #  #  #  #  #     #     #  #  #     #  #    @\n" +
                "@    ###   # #   #  ####  #     #  #  #     ####    @\n" +
                "@    #  #  #  #  #     #  #     #  #  #     #  #    @\n" +
                "@    ###   #  #  #  ####  ####  ####  ####  #  #    @\n"+
                "@                                                   @\n"+
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                      {EDIT BY MICHELE LAPENNA\033[19m}\n\n"+
                "BENVENUTI NEL GIOCO DELLA BRISCOLA !\nPer giocare inserisci un nickname.\n");
    }

    public static void infocomandi(){
        System.out.println("\033[19mLa partita è appena iniziata!\nUtilizza i pulsanti 1, 2 e 3 della tastiera per scegliere e giocare la rispettiva carta.");
        System.out.println("P.S. Le carte di briscola della partita saranno evidenziate in \033[33mGIALLO\033[19m.\n");
    }

    public static void divisore(){
        System.out.println("\033[19m@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +
                "\n@---------------------------------------------------------------@" +
                "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public static void finegioco1(){
        System.out.println("\n\033[32m" +
                " #   #  #  #  #  #  #  ####  ####        \n" +
                " #   #  #  ## #  ## #  #     #  #      \n" +
                " # # #  #  # ##  # ##  ####  # #       \n" +
                " ## ##  #  #  #  #  #  #     #  #          \n" +
                " #   #  #  #  #  #  #  ####  #  #   \n"+"\033[19m\n Grazie per aver giocato! :)");
    }

    public static void finegioco2(){
        System.out.println("\n\033[32m" +
                "####   ##   #   #  ####     ####  #   #  ####  ####   \n" +
                "#     #  #  ## ##  #        #  #  #   #  #     #  #   \n" +
                "# ##  ####  # # #  ####     #  #  #   #  ####  # #    \n" +
                "#  #  #  #  #   #  #        #  #   # #   #     #  #   \n" +
                "####  #  #  #   #  ####     ####    #    ####  #  #   \n"+"\033[19m\n Grazie per aver giocato! :)");
    }

    public static void finegioco3(){
        System.out.println("\n\033[32m" +
                "####  ####  ####  ###      ####   ##   #   #  ####  \n" +
                "#     #  #  #  #  #  #     #     #  #  ## ##  #     \n" +
                "# ##  #  #  #  #  #  #     # ##  ####  # # #  ####  \n" +
                "#  #  #  #  #  #  #  #     #  #  #  #  #   #  #     \n" +
                "####  ####  ####  ###      ####  #  #  #   #  ####  \n"+"\033[19m\n Grazie per aver giocato! :)");
    }

}
