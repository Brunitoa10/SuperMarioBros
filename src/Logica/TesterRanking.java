package Logica;


public class TesterRanking {
    public static void main (String[]args){
        Ranking rank = new Ranking();

        rank.agregarAlRanking("juancitoa",206);
        mostrarRank(rank);


    }

    private static void mostrarRank(Ranking rank) {
        for (JugadorRanking jug : rank.mostrarRanking()) {
            System.out.println(jug.getNombre());
            System.out.println(jug.getPuntaje());

        }
    }
}
