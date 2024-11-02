package IA;

import Entidades.Enemigos.Enemigo;
import Logica.Temporizador;


public class IAPiranhaPlant implements ComportamientoIA{
    protected boolean subiendo;
    protected boolean bajando;
    protected int yEscondido;
    protected int yMaximo;
    protected Temporizador temporizadorSubirBajar;

    public IAPiranhaPlant(int yEscondido, int yMaximo) {
        subiendo = false;
        bajando = false;
        this.yEscondido = yEscondido;
        this.yMaximo = yMaximo;
        temporizadorSubirBajar = new Temporizador();
        temporizadorSubirBajar.iniciar();
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        if (temporizadorSubirBajar.hanPasadoNSegundos(7000)) {
            if (estaEscondido(enemigo)) {
                subiendo = true;
            } else if (estaEnYMaximo(enemigo)) {
                bajando = true;
            }
        }
        if (subiendo) {
            subirPlanta(enemigo);
            if (estaEnYMaximo(enemigo)) {
                subiendo = false;
                temporizadorSubirBajar.resetear();
                temporizadorSubirBajar.iniciar();
            }
        }
        if (bajando) {
            bajarPlanta(enemigo);
            if (estaEscondido(enemigo)) {
                bajando = false;
                temporizadorSubirBajar.resetear();
                temporizadorSubirBajar.iniciar();
            }
        }
    }


    private void subirPlanta(Enemigo enemigo) {
        enemigo.setPosicionEnY(enemigo.getPosicionEnY() - enemigo.getVelocidad());
    }

    private void bajarPlanta(Enemigo enemigo) {
        enemigo.setPosicionEnY(enemigo.getPosicionEnY() + enemigo.getVelocidad());
    }

    public boolean estaEscondido(Enemigo enemigo) {
        return yEscondido == enemigo.getPosicionEnY();
    }

    public boolean estaEnYMaximo(Enemigo enemigo) {
        return yMaximo == enemigo.getPosicionEnY();
    }

    @Override
    public boolean comienzoAtaque() {
        return false;
    }
}
