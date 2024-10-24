    package EstadoMovimiento;

    import Entidades.Jugador;

    public class MarioEnAire implements EstadoMovimiento{

        protected Jugador mario;
        private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
        private static final int TOPE_GRAVEDAD = 15;
        private int velocidadY;

        public MarioEnAire(Jugador mario, int velocidadY) {
            this.mario = mario;
            this.velocidadY = velocidadY;
            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
            }
        }

        @Override
        public void saltar() {

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
            if (velocidadY <= TOPE_GRAVEDAD) {
                velocidadY += GRAVEDAD;
            }

            // Movimiento vertical
            mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

            if(mario.getDireccion()==1) {
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
            }else{
                mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
            }
        }

        public void LanzarBola() {
            mario.setEstadoMovimiento(new LanzandoBola(mario));
        }
        public void EnAire() {
        }

        @Override
        public void AFK() {
        }

    }
