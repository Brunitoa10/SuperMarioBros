    package EstadoMovimiento;

    import Entidades.Jugador;

    public class MarioEnAire implements EstadoMovimiento{

        protected Jugador mario;
        protected int VELOCIDAD_SALTO;
        protected static int alturaMax;
        protected int piso;
        private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
        private int velocidadY;

        public MarioEnAire(Jugador mario) {
            this.mario = mario;
            alturaMax = mario.getPosicionEnY()+50;
            piso = mario.getPiso();
            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png");
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png");
            }
        }

        @Override
        public void saltar(Jugador mario) {
            System.out.println("Mario en el aire");
        }

        @Override
        public void desplazarEnX(int direccion) {
            mario.setDireccion(direccion);
            mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
        }

        @Override
        public void actualizar() {
            // Movimiento horizontal
            mario.getEstadoJugador().actualizarSprite();
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen());
            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png");
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png");
            }
            // Aplicar gravedad (para que empiece a bajar eventualmente)
            velocidadY += GRAVEDAD;

            // Movimiento vertical
            mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

            // Verificar si Mario toca el suelo
            if (estaEnElSuelo()) {
            // Cambiar al estado de caminar si está en el suelo
                mario.setEstadoMovimiento(new MarioParado(mario));
            }
        }

        public boolean estaEnElSuelo() {
            return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
        }

    }
