package Entidades.Power_Ups;
import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.SuperMario;
import Fabricas.Sprite;

public class SuperChampinion extends PowerUp{

    public SuperChampinion(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 100;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return colisionan;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        this.setPosicionEnY(1000);
    }

    public void setEstadoMario(Jugador j){
        j.setEstadoJugador(new SuperMario(j));
    }
}
