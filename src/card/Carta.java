package card;

public class Carta {


    private Seme seme;
    private int num;
    private int qualità;


    public Carta(Seme seme, int num) {
        this.seme = seme;
        this.num = num;
        this.qualità = 0;
        impostaQualitàCarta();
    }


    //METODI GET E SET
    public int getQualità() {
        return qualità;
    }

    public void setQualità(int qualità) {
        this.qualità = qualità;
    }

    public Seme getSeme() {
        return seme;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        if (qualità >50 ){
            return "\033[33m"+ num + " di " + seme+"\033[30m";
        }else{
            return "\033[30m" + num + " di " + seme + "\033[30m";
        }
    }

    //INIZIALIZZA LA QUALITA' DELLE CARTE
    private void impostaQualitàCarta(){
        if(num==1){
            qualità = qualità +20;
        }else if(num == 3){
            qualità = qualità +num +10;
        }else{
            qualità = qualità +num;
        }
    }

}