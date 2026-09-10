package defensatorres;
/** Enlaces modificados exclusivamente por ListaDobleEnemigos. */
public final class NodoEnemigo {
    final Enemigo enemigo;
    NodoEnemigo anterior;
    NodoEnemigo siguiente;
    ListaDobleEnemigos propietario;

    NodoEnemigo(Enemigo enemigo, ListaDobleEnemigos propietario) {
        this.enemigo = enemigo;
        this.propietario = propietario;
    }

    public Enemigo getEnemigo() { return enemigo; }
    public NodoEnemigo getAnterior() { return anterior; }
    public NodoEnemigo getSiguiente() { return siguiente; }
}
