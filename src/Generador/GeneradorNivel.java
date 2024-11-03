package Generador;


import Constantes.ConstantesBloques;
import Entidades.Enemigos.EstadoSpiny.SpinyEnNube;
import Entidades.Enemigos.Lakitu;
import Entidades.Enemigos.Spiny;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.SueloNivel;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.ProyectilKoopa;
import Entidades.Vacio;
import Fabricas.FabricaEntidad;
import Fabricas.Sprite;
import Logica.Nivel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GeneradorNivel {
    protected FabricaEntidad fabricaEntidades;


    public GeneradorNivel(FabricaEntidad fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
    }


    public Nivel generarNivel(int numero) {
        Nivel nivel = new Nivel(numero);
        String ruta_a_nivel = "src/Recursos/Niveles/nivel" + numero + ".txt";
        if (nivel.nivel() == 1) {
            generarVaciosYSuelosNivelUno(nivel);
        } else {
            if (nivel.nivel() == 2) {
                generarVaciosYSuelosNivelDos(nivel);
            } else {
                generarVaciosYSuelosNivelTres(nivel);
            }

        }

        try (BufferedReader br = new BufferedReader(new FileReader(ruta_a_nivel))) {
            System.out.println(ruta_a_nivel);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" ");

                int idEntidad = Integer.parseInt(datos[0]);
                System.out.println("El identidad es" + idEntidad);
                int posicionX = Integer.parseInt(datos[1]);
                int posicionY = Integer.parseInt(datos[2]);
                int idExtra = 5;
                if (idEntidad == 3 || idEntidad == 4) {
                    idExtra = Integer.parseInt(datos[3]);
                }
                if (idEntidad >= 1 && idEntidad <= 4) {
                    this.casoPlataformas(idEntidad, posicionX, posicionY, idExtra, nivel);
                } else if (idEntidad >= 5 && idEntidad <= 8) {
                    this.casoPowerUps(idEntidad, posicionX, posicionY, nivel);
                } else if (idEntidad >= 10 && idEntidad <= 16) {
                    this.casoEnemigos(idEntidad, posicionX, posicionY, nivel);
                } else {
                    System.out.println("El identidad es" + idEntidad);
                    this.restoCasos(idEntidad, posicionX, posicionY, nivel);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + ruta_a_nivel);
            e.printStackTrace();
        }
        return nivel;
    }

    private void restoCasos(int idEntidad, int x, int y, Nivel nivel) {
        System.out.println(idEntidad);
        switch (idEntidad) {
            case 0:
                System.out.println(idEntidad + " " + x + " " + y + " ");
                nivel.agregarJugador(fabricaEntidades.crearJugador(x, y));
                break;
            case 9:
                nivel.agregarMoneda(fabricaEntidades.crearMoneda(x, y, nivel.getMonedas()));
                break;
            case 19:
                nivel.agregarVacio(fabricaEntidades.crearVacio(x, y, nivel.getVacios()));
                break;
        }
    }

    private void casoEnemigos(int idEntidad, int x, int y, Nivel nivel) {
        switch (idEntidad) {
            case 11:
                nivel.agregarEnemigo(fabricaEntidades.crearBuzzyBeetle(x, y, nivel.getEnemigos()));
                break;
            case 12:
                nivel.agregarEnemigo(fabricaEntidades.crearGoomba(x, y, nivel.getEnemigos()));
                break;
            case 13:
                ProyectilKoopa p = fabricaEntidades.crearProyectilKoopa(x, y + 32, nivel.getProyectiles());
                nivel.agregarProyectil(p);
                nivel.agregarEnemigo(fabricaEntidades.crearKoopaTroopa(x, y, p, nivel.getEnemigos()));
                break;
            case 14:
                List<Spiny> lista = new ArrayList<Spiny>();//En este caso se precarga los proyectiles enemigos spinnys para evitar problemas en el desarrollo del juego
                Lakitu lakitu = fabricaEntidades.crearLakitu(x, y, nivel.getEnemigos(), lista);
                for (int cont = 0; cont < 3; cont++) {
                    Spiny spiny = fabricaEntidades.crearSpiny(x, y + 74, nivel.getEnemigos());
                    nivel.agregarEnemigo(spiny);
                    spiny.setEstadoSpiny(new SpinyEnNube(spiny, lakitu));
                    lista.add(spiny);
                }
                nivel.agregarEnemigo(lakitu);
                break;
            case 15:
                nivel.agregarEnemigo(fabricaEntidades.crearPiranhaPlant(x, y, nivel.getEnemigos()));
                break;
            case 16:
                nivel.agregarEnemigo(fabricaEntidades.crearSpiny(x, y, nivel.getEnemigos()));
                break;
        }
    }

    private PowerUp casoPowerUps(int idEntidad, int x, int y, Nivel nivel) {
        PowerUp Creado = fabricaEntidades.crearSuperChampinion(x, y, nivel.getPowerUps());

        switch (idEntidad) {
            case 5:
                Creado = fabricaEntidades.crearFlorDeFuego(x, y, nivel.getPowerUps());
                nivel.agregarPowerUp(Creado);
                break;
            case 6:
                Creado = fabricaEntidades.crearEstrella(x, y, nivel.getPowerUps());
                nivel.agregarPowerUp(Creado);
                break;
            case 7:
                Creado = fabricaEntidades.crearChampinionVerde(x, y, nivel.getPowerUps());
                nivel.agregarPowerUp(Creado);
                break;
            case 8:
                Creado = fabricaEntidades.crearSuperChampinion(x, y, nivel.getPowerUps());
                nivel.agregarPowerUp(Creado);
                break;
        }

        return Creado;
    }

    private void generarVaciosYSuelosNivelUno(Nivel nivel) {
        nivel.agregarVacio(new Vacio(1624, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 70, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(2222, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 104, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(4575, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 70, 32,""), nivel.getVacios()));
        nivel.agregarPlataforma(new SueloNivel(-800, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 2423, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(1695, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 526, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(2327, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 2247, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(4646, 456, new Sprite(ConstantesBloques.SPRITE_VACIA, 2423, 69,""), nivel.getPlataformas()));
    }

    private void generarVaciosYSuelosNivelDos(Nivel nivel) {
        nivel.agregarVacio(new Vacio(2332, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 149, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(4332, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 99, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(4552, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 99, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(5232, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 349, 32,""), nivel.getVacios()));
        nivel.agregarPlataforma(new SueloNivel(-800, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 3131, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(2482, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 1849, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(4432, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 99, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(4632, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 599, 69,""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(5582, 453, new Sprite(ConstantesBloques.SPRITE_VACIA, 1483, 69,""), nivel.getPlataformas()));
    }

    private void generarVaciosYSuelosNivelTres(Nivel nivel) {
        nivel.agregarPlataforma(new SueloNivel(-800, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 786, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(773, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 98, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(872, 431, new Sprite(ConstantesBloques.SPRITE_VACIA, 2260, 98, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(3133, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 196, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(3723, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 835, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(4657, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 97, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(4854, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 638, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(6132, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 144, 246, ""), nivel.getPlataformas()));
        nivel.agregarPlataforma(new SueloNivel(6277, 431, new Sprite(ConstantesBloques.SPRITE_VACIA, 788, 98, ""), nivel.getPlataformas()));
        nivel.agregarVacio(new Vacio(6277, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 32, 32, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(5493, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 638, 246, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(4755, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 98, 246, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(4559, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 97, 246, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(3330, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 392, 246, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(3101, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 32, 32, ""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(871, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 32, 32,""), nivel.getVacios()));
        nivel.agregarVacio(new Vacio(-13, 283, new Sprite(ConstantesBloques.SPRITE_VACIA, 785, 246, ""), nivel.getVacios()));
    }

    private void casoPlataformas(int idEntidad, int x, int y, int idExtra, Nivel nivel) {
        switch (idEntidad) {
            case 1:
                nivel.agregarPlataforma(fabricaEntidades.crearBloqueSolido(x, y, nivel.getPlataformas()));
                break;
            case 2:
                nivel.agregarPlataforma(fabricaEntidades.crearLadrilloSolido(x, y, nivel.getPlataformas()));
                break;
            case 3:
                Moneda moneda;
                if(idExtra == 9) {
                    moneda = fabricaEntidades.crearMoneda(x, y -32, nivel.getMonedas());
                    nivel.agregarMoneda(moneda);
                    nivel.agregarPlataforma(fabricaEntidades.crearBloquePregunta(x, y, moneda, nivel.getPlataformas()));
                } else {
                    nivel.agregarPlataforma(fabricaEntidades.crearBloquePregunta(x, y, casoPowerUps(idExtra, x, y - 32, nivel), nivel.getPlataformas()));
                }
                break;
            case 4:
                nivel.agregarPlataforma(fabricaEntidades.crearTuberia(x, y, nivel.getPlataformas()));
                if (idExtra == 1) {
                    nivel.agregarEnemigo(fabricaEntidades.crearPiranhaPlant(x+16, y, nivel.getEnemigos()));
                }
                break;
        }
    }
}