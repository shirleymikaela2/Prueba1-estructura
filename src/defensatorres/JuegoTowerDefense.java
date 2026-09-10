package defensatorres;

public class JuegoTowerDefense {
    public static final int FIN_RUTA = 20;
    private final ListaSecuencialTorres torres = new ListaSecuencialTorres(20);
    private final ListaDobleEnemigos enemigos = new ListaDobleEnemigos();
    private final ListaCircularOleadas oleadas = new ListaCircularOleadas();
    private int vidasJugador = 3;
    private int siguienteIdEnemigo = 1;
    private int oleadasIniciadas;
    private int turno;

    public boolean registrarTorre(Torre torre) { return torres.insertar(torre); }
    public boolean eliminarTorre(int id) { return torres.eliminarPorId(id); }
    public Torre buscarTorre(int id) { return torres.buscarPorId(id); }
    public void mostrarTorres() { torres.mostrar(); }
    public void registrarOleada(Oleada oleada) { oleadas.registrar(oleada); }
    public void mostrarOleadas() { oleadas.mostrar(); }
    public void mostrarEnemigosAdelante() { enemigos.mostrarAdelante(); }
    public void mostrarEnemigosAtras() { enemigos.mostrarAtras(); }

    public boolean iniciarSiguienteOleada() {
        if (oleadas.contar() == 0) return false;
        if (oleadasIniciadas >= oleadas.contar()) {
            System.out.println("Ya se iniciaron todas las oleadas definidas. Puede reiniciar el ciclo para jugar otra partida.");
            return false;
        }
        Oleada oleada = oleadas.obtenerActual();
        for (int i = 0; i < oleada.getCantidadEnemigos(); i++) {
            enemigos.insertarFinal(new Enemigo(siguienteIdEnemigo++, oleada.getTipoEnemigo(), oleada.getVidaBase(),
                    oleada.getVelocidadBase(), 0, oleada.getVidaBase() / 2));
        }
        oleadasIniciadas++;
        oleadas.avanzarSiguiente();
        System.out.println("Oleada " + oleada.getIdOleada() + " iniciada: " + oleada.getCantidadEnemigos() + " enemigos agregados.");
        return true;
    }

    public void avanzarTurno() {
        if (vidasJugador <= 0) { System.out.println("La partida ya termino: no quedan vidas."); return; }
        if (enemigos.estaVacia()) { System.out.println("No hay enemigos activos. Inicie una oleada primero."); return; }
        turno++;
        int escapados = enemigos.moverYEliminarEscapados(FIN_RUTA);
        vidasJugador = Math.max(0, vidasJugador - escapados);
        int ataques = enemigos.atacarConTorres(torres);
        int destruidos = enemigos.eliminarDestruidos();
        System.out.println("Turno " + turno + ": ataques=" + ataques + ", destruidos=" + destruidos
                + ", escapados=" + escapados + ", vidas restantes=" + vidasJugador + ".");
        if (vidasJugador == 0) System.out.println("Fin de partida: la base fue destruida.");
        else if (oleadasIniciadas == oleadas.contar() && enemigos.estaVacia())
            System.out.println("Fin de partida: todas las oleadas definidas fueron completadas.");
    }

    public void reiniciarCicloOleadas() {
        if (!enemigos.estaVacia()) { System.out.println("No se puede reiniciar: todavia hay enemigos activos."); return; }
        oleadas.reiniciarCiclo();
        oleadasIniciadas = 0;
        vidasJugador = 3;
        turno = 0;
        System.out.println("Ciclo de oleadas reiniciado. Vidas restauradas a 3.");
    }

    public void mostrarEstado() {
        System.out.println("Ruta: 0 a " + FIN_RUTA + " | Turno: " + turno + " | Vidas: " + vidasJugador);
        System.out.println("Torres activas: " + torres.contarActivas() + " | Enemigos activos: " + enemigos.contar()
                + " | Oleadas iniciadas: " + oleadasIniciadas + "/" + oleadas.contar());
    }
}
