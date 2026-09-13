package defensatorres;

/** Coordina las estructuras de datos y las reglas de la partida. */
public class JuegoTowerDefense {

    public static final int FIN_RUTA = 20;

    private final ListaSecuencialTorres torres;
    private final ListaDobleEnemigos enemigos;
    private final ListaCircularOleadas oleadas;

    private int vidasJugador;
    private int siguienteIdEnemigo;
    private int oleadasIniciadas;
    private int turno;

    public JuegoTowerDefense() {
        torres = new ListaSecuencialTorres(20);
        enemigos = new ListaDobleEnemigos();
        oleadas = new ListaCircularOleadas();

        vidasJugador = 3;
        siguienteIdEnemigo = 1;
        oleadasIniciadas = 0;
        turno = 0;
    }

    public boolean registrarTorre(Torre torre) {
        return torres.insertar(torre);
    }

    public boolean eliminarTorre(int id) {
        return torres.eliminarPorId(id);
    }

    public Torre buscarTorre(int id) {
        return torres.buscarPorId(id);
    }

    public void registrarOleada(Oleada oleada) {
        oleadas.registrar(oleada);
    }

    public boolean iniciarSiguienteOleada() {
        if (oleadas.contar() == 0) {
            return false;
        }

        if (oleadasIniciadas >= oleadas.contar()) {
            return false;
        }

        Oleada oleada = oleadas.obtenerActual();

        for (int i = 0; i < oleada.getCantidadEnemigos(); i++) {
            Enemigo enemigo = new Enemigo(
                    siguienteIdEnemigo,
                    oleada.getTipoEnemigo(),
                    oleada.getVidaBase(),
                    oleada.getVelocidadBase(),
                    0,
                    oleada.getVidaBase() / 2
            );

            enemigos.insertarFinal(enemigo);
            siguienteIdEnemigo++;
        }

        oleadasIniciadas++;
        oleadas.avanzarSiguiente();

        return true;
    }

    public String avanzarTurno() {
        if (vidasJugador <= 0) {
            return "La partida ya terminó: no quedan vidas.";
        }

        if (enemigos.estaVacia()) {
            return "No hay enemigos activos. Inicie una oleada primero.";
        }

        turno++;

        int escapados = enemigos.moverYEliminarEscapados(FIN_RUTA);
        vidasJugador = Math.max(0, vidasJugador - escapados);

        int ataques = enemigos.atacarConTorres(torres);
        int destruidos = enemigos.eliminarDestruidos();

        String mensaje = "Turno " + turno
                + ": ataques=" + ataques
                + ", destruidos=" + destruidos
                + ", escapados=" + escapados
                + ", vidas restantes=" + vidasJugador + ".";

        if (vidasJugador == 0) {
            mensaje += " Fin de partida: la base fue destruida.";
        } else if (oleadasIniciadas == oleadas.contar() && enemigos.estaVacia()) {
            mensaje += " Fin de partida: todas las oleadas fueron completadas.";
        }

        return mensaje;
    }

    public String reiniciarCicloOleadas() {
        if (!enemigos.estaVacia()) {
            return "No se puede reiniciar: todavía hay enemigos activos.";
        }

        oleadas.reiniciarCiclo();
        oleadasIniciadas = 0;
        vidasJugador = 3;
        turno = 0;
        siguienteIdEnemigo = 1;

        return "Ciclo de oleadas reiniciado. Vidas restauradas a 3.";
    }

    public String resumenTorres() {
        return torres.resumen();
    }

    public String resumenOleadas() {
        return oleadas.resumen();
    }

    public String resumenEnemigosAdelante() {
        return enemigos.resumenAdelante();
    }

    public String resumenEnemigosAtras() {
        return enemigos.resumenAtras();
    }

    public String resumenEstado() {
        return "Ruta: 0 a " + FIN_RUTA
                + " | Turno: " + turno
                + " | Vidas: " + vidasJugador
                + " | Torres: " + torres.contarActivas()
                + " | Enemigos: " + enemigos.contar()
                + " | Oleadas iniciadas: " + oleadasIniciadas
                + "/" + oleadas.contar();
    }

    public ListaSecuencialTorres obtenerTorres() {
        return torres;
    }

    public ListaDobleEnemigos obtenerEnemigos() {
        return enemigos;
    }

    public int obtenerVidasJugador() {
        return vidasJugador;
    }

    public String cargarCasoPrueba() {
        boolean torre1 = registrarTorre(
                new Torre(1, "Arquero", "Arquero", 3, 20, 2, 50)
        );

        boolean torre2 = registrarTorre(
                new Torre(2, "Canon", "Canon", 8, 35, 3, 100)
        );

        if (torre1) {
            registrarOleada(new Oleada(1, 3, "Basico", 50, 1));
        }

        if (torre2) {
            registrarOleada(new Oleada(2, 2, "Rapido", 40, 2));
        }

        return "Caso de prueba cargado. Los elementos repetidos se ignoraron.";
    }
}