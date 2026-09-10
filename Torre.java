package defensatorres;

/** Representa una estructura defensiva colocada en la ruta. */
public class Torre {
    private final int id;
    private final String nombre;
    private final String tipo;
    private final int posicion;
    private final int danio;
    private final int rango;
    private final int costo;

    public Torre(int id, String nombre, String tipo, int posicion, int danio, int rango, int costo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.danio = danio;
        this.rango = rango;
        this.costo = costo;
    }

    public int getId() { return id; }
    public int getPosicion() { return posicion; }
    public int getDanio() { return danio; }
    public int getRango() { return rango; }

    public boolean estaEnRango(Enemigo enemigo) {
        return Math.abs(posicion - enemigo.getPosicion()) <= rango;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " (" + tipo + ") | Pos: " + posicion
                + " | Danio: " + danio + " | Rango: " + rango + " | Costo: " + costo;
    }
}
