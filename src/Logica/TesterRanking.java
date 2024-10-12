package Logica;

public class TesterRanking {
    public static void main (String[]args){
        Ranking rank = new Ranking();
        rank.mostrarRanking();
        rank.actualizarRanking("Juan2",1000);
        rank.mostrarRanking();

    }

}
