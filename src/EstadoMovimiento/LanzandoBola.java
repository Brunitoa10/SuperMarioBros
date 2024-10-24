package EstadoMovimiento;

import Entidades.Jugador;

public class LanzandoBola implements EstadoMovimiento{
    protected Jugador mario;

    public LanzandoBola(Jugador mario) {
        this.mario = mario;
        if(mario.getDireccion()==1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
        if(mario.getDireccion()==-1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
    }
    public void saltar() {
        mario.setEstadoMovimiento((new MarioSaltando(mario)));
    }

    @Override
    public void desplazarEnX(int direccion) {
        if(mario.estaEnPlataforma()) {
            mario.setEstadoMovimiento(new MarioCaminando(mario));
        }else{
            mario.setEstadoMovimiento(new MarioEnAire(mario,0));
            mario.getEstadoMovimiento().desplazarEnX(direccion);
        }
    }

    @Override
    public void actualizar() {
        if(mario.getDireccion()==1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
        if(mario.getDireccion()==-1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
    }

    @Override
    public void LanzarBola() {
        if(mario.getDireccion()==1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
        if(mario.getDireccion()==-1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
    }

    @Override
    public void EnAire() {
        mario.setEstadoMovimiento(new MarioEnAire(mario,0));
    }

    @Override
    public void AFK() {
        mario.setEstadoMovimiento(new MarioParado(mario));
    }
}
