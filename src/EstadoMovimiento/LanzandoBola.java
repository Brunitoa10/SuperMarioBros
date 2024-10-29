package EstadoMovimiento;

import Entidades.Jugador;
import Logica.Temporizador;

public class LanzandoBola implements EstadoMovimiento {
    protected Jugador mario;
    protected Temporizador temporizador;

    public LanzandoBola(Jugador mario) {
        this.mario = mario;
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    public void saltar() {
        mario.setEstadoMovimiento((new MarioSaltando(mario)));
    }

    @Override
    public void desplazarEnX(int direccion) {
        if (mario.estaEnPlataforma()) {
            mario.setEstadoMovimiento(new MarioCaminando(mario));
        } else {
            mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
            mario.getEstadoMovimiento().desplazarEnX(direccion);
        }
    }

    @Override
    public void actualizar() {
        if (temporizador.hanPasadoNSegundos(500))
            if (mario.estaEnPlataforma())
                AFK();
            else
                EnAire();
        System.out.println(mario.getDireccion());
        if (mario.getDireccion() == 1) {
            if (mario.estaEnPlataforma()) {
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
            } else {
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/SaltoYFireBall/TiraBolaAireRigth.gif");
            }

        } else {
            if (mario.getDireccion() == -1) {
                if (mario.estaEnPlataforma()) {
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
                } else {
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/SaltoYFireBall/TiraBolaAireLeft.gif");
                }
            }
        }
    }

    @Override
    public void LanzarBola() {

    }

    @Override
    public void EnAire() {
        mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
    }

    @Override
    public void AFK() {
        mario.setEstadoMovimiento(new MarioParado(mario));
    }
}
