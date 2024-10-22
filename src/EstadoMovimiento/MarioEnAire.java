    package EstadoMovimiento;

    import Entidades.Jugador;

    public class MarioEnAire implements EstadoMovimiento{

        protected Jugador mario;
        protected static int alturaMax;
        private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
        private int velocidadY;

        public MarioEnAire(Jugador mario) {
            this.mario = mario;
            alturaMax = mario.getPosicionEnY()+50;
            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
            }
        }

        @Override
        public void saltar(Jugador mario) {
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

            // Aplicar gravedad (para que empiece a bajar eventualmente)
            velocidadY += GRAVEDAD;

            // Movimiento vertical
            mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
            }
        }

        public boolean estaEnElSuelo() {
            return false;
        }



    }
