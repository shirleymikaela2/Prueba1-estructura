package defensatorres;

/** Nodo utilizado por la lista doblemente enlazada de enemigos. */
public class NodoEnemigo {

    Enemigo dato;
    NodoEnemigo anterior;
    NodoEnemigo siguiente;

    public NodoEnemigo(Enemigo dato) {
        this.dato = dato;
        anterior = null;
        siguiente = null;
    }

   public Enemigo getDato() {
    return dato;
}

public NodoEnemigo getSiguiente() {
    return siguiente;
}
}