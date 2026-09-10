package defensatorres;

/** Enemigo que avanza por la ruta durante una partida. */
public class Enemigo {
    private final int id;
    private final String tipo;
    private int vida;
    private final int velocidad;
    private int posicion;
    private final int recompensa;

    public Enemigo(int id, String tipo, int vida, int velocidad, int posicion, int recompensa) {
        this.id = id;
        this.tipo = tipo;
        this.vida = vida;
        this.velocidad = velocidad;
        this.posicion = posicion;
        this.recompensa = recompensa;
    }

    public int getId() { return id; }
    public int getVida() { return vida; }
    public int getVelocidad() { return velocidad; }
    public int getPosicion() { return posicion; }
    public int getRecompensa() { return recompensa; }

    public void avanzar() { posicion += velocidad; }
    public void recibirDanio(int danio) { vida -= danio; }
    public boolean estaDestruido() { return vida <= 0; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + tipo + " | Vida: " + vida + " | Velocidad: " + velocidad
                + " | Posicion: " + posicion + " | Recompensa: " + recompensa;
    }
}
