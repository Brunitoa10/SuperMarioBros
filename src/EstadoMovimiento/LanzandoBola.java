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
    public void saltar(Jugador j) {
        j.setEstadoMovimiento((new MarioSaltando(j)));
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }

    @Override
    public void actualizar() {
        if(mario.getDireccion()==1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
        if(mario.getDireccion()==-1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
    }

    @Override
    public boolean estaEnElSuelo() {
        return false;
    }

    @Override
    public void LanzarBola() {
        if(mario.getDireccion()==1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallRigth.gif");
        if(mario.getDireccion()==-1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego/TiraFireBallLeft.gif");
    }
}
