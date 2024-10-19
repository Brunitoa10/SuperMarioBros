package Generador;

import Entidades.Vacio;
import Fabricas.*;
import Logica.Nivel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeneradorNivel {
    protected FabricaEntidad fabricaEntidades;


    public GeneradorNivel(FabricaEntidad fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
    }

    public void cambiarFabricaEntidades(CreadorEntidad fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
    }

    public Nivel generarNivel(int numero) {
        Nivel nivel = new Nivel(numero);
        String ruta_a_nivel = "src/Recursos/Niveles/nivel" + numero + ".txt";
        generarVaciosNivel(nivel);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta_a_nivel))) {
            System.out.println(ruta_a_nivel);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" ");

                int idEntidad = Integer.parseInt(datos[0]);
                System.out.println("El identidad es" + idEntidad );
                int posicionX = Integer.parseInt(datos[1]);
                int posicionY = Integer.parseInt(datos[2]);
                int idPowerUp = 0;
                if (idEntidad == 3) {
                    idPowerUp = Integer.parseInt(datos[3]);
                }
                if (idEntidad >= 1 && idEntidad <= 4) {
                    this.casoPlataformas(idEntidad, posicionX, posicionY, idPowerUp, nivel);
                }
                else if (idEntidad >= 5 && idEntidad <= 8) {
                    this.casoPowerUps(idEntidad, posicionX, posicionY, nivel);
                }
                else if (idEntidad >= 10 && idEntidad <= 16) {
                    this.casoEnemigos(idEntidad, posicionX, posicionY, nivel);
                }
                else {
                    System.out.println("El identidad es" + idEntidad );
                    this.restoCasos(idEntidad, posicionX, posicionY, nivel);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + ruta_a_nivel);
            e.printStackTrace();
        }
        return nivel;
    }

    private void generarVaciosNivel(Nivel nivel) {
        nivel.agregarVacio(new Vacio(1624, 457,new Sprite("/",70,32)));

    }

    private void restoCasos(int idEntidad, int x, int y, Nivel nivel) {
        System.out.println(idEntidad);
        switch (idEntidad) {
            case 0:
                System.out.println(idEntidad+" "+x+" "+y+" ");
                nivel.agregarJugador(fabricaEntidades.crearJugador(x,y));
                break;
            case 9:
                nivel.agregarMoneda(fabricaEntidades.crearMoneda(x,y));
                break;
            case 17:
                nivel.agregarBandera(fabricaEntidades.crearBandera(x,y));
                break;
            case 18:
                nivel.agregarPrincesa(fabricaEntidades.crearPrincesa(x,y));
                break;
            case 19:
                nivel.agregarVacio(fabricaEntidades.crearVacio(x,y));
                break;
        }
    }

    private void casoEnemigos(int idEntidad, int x, int y, Nivel nivel) {
        switch (idEntidad) {
            case 10:
                nivel.agregarEnemigo(fabricaEntidades.crearBowser(x,y));
                break;
            case 11:
                nivel.agregarEnemigo(fabricaEntidades.crearBuzzyBeetle(x,y));
                break;
            case 12:
                nivel.agregarEnemigo(fabricaEntidades.crearGoomba(x,y));
                break;
            case 13:
                nivel.agregarEnemigo(fabricaEntidades.crearKoopaTroopa(x,y));
                break;
            case 14:
                nivel.agregarEnemigo(fabricaEntidades.crearLakitu(x,y));
                break;
            case 15:
                nivel.agregarEnemigo(fabricaEntidades.crearPiranhaPlant(x,y));
                break;
            case 16:
                nivel.agregarEnemigo(fabricaEntidades.crearSpiny(x,y));
                break;
        }
    }

    private void casoPowerUps(int idEntidad, int x, int y, Nivel nivel) {
        switch (idEntidad) {
            case 5:
                nivel.agregarPowerUp(fabricaEntidades.crearFlorDeFuego(x,y));
                break;
            case 6:
                nivel.agregarPowerUp(fabricaEntidades.crearEstrella(x,y));
                break;
            case 7:
                nivel.agregarPowerUp(fabricaEntidades.crearChampinionVerde(x,y));
                break;
            case 8:
                nivel.agregarPowerUp(fabricaEntidades.crearSuperChampinion(x,y));
                break;
        }
    }

    private void casoPlataformas(int idEntidad, int x, int y, int idPowerUp, Nivel nivel) {
        switch (idEntidad) {
            case 1:
                nivel.agregarPlataforma(fabricaEntidades.crearBloqueSolido(x,y));
                break;
            case 2:
                nivel.agregarPlataforma(fabricaEntidades.crearLadrilloSolido(x,y));
                break;
            case 3:
                nivel.agregarPlataforma(fabricaEntidades.crearBloquePregunta(x,y,idPowerUp));
                break;
            case 4:
                nivel.agregarPlataforma(fabricaEntidades.crearTuberia(x,y));
        }
    }
}