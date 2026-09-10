package defensatorres;

/** Configuracion base de una oleada de enemigos. */
public class Oleada {
    private final int idOleada;
    private final int cantidadEnemigos;
    private final String tipoEnemigo;
    private final int vidaBase;
    private final int velocidadBase;

    public Oleada(int idOleada, int cantidadEnemigos, String tipoEnemigo, int vidaBase, int velocidadBase) {
        this.idOleada = idOleada;
        this.cantidadEnemigos = cantidadEnemigos;
        this.tipoEnemigo = tipoEnemigo;
        this.vidaBase = vidaBase;
        this.velocidadBase = velocidadBase;
    }

    public int getIdOleada() { return idOleada; }
    public int getCantidadEnemigos() { return cantidadEnemigos; }
    public String getTipoEnemigo() { return tipoEnemigo; }
    public int getVidaBase() { return vidaBase; }
    public int getVelocidadBase() { return velocidadBase; }

    @Override
    public String toString() {
        return "Oleada " + idOleada + " | " + tipoEnemigo + " | Cantidad: " + cantidadEnemigos
                + " | Vida base: " + vidaBase + " | Velocidad base: " + velocidadBase;
    }
}
