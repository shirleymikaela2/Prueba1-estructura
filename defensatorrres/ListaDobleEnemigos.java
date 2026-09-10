package defensatorres;
/** Lista doble sin centinelas: primero.anterior y ultimo.siguiente son null. */
public final class ListaDobleEnemigos {
    private NodoEnemigo primero;
    private NodoEnemigo ultimo;
    private int cantidad;

    public void insertarAlFinal(Enemigo enemigo) {
        if (enemigo == null || !enemigo.estaVivo()) {
            throw new IllegalArgumentException("Debe insertar un enemigo vivo.");
        }
        if (buscarPorId(enemigo.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un enemigo con ese ID.");
        }
        NodoEnemigo nuevo = new NodoEnemigo(enemigo, this);
        nuevo.anterior = ultimo;
        if (ultimo == null) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        cantidad++;
    }

    public Enemigo buscarPorId(int id) {
        NodoEnemigo nodo = buscarNodo(id);
        return nodo == null ? null : nodo.enemigo;
    }

    private NodoEnemigo buscarNodo(int id) {
        NodoEnemigo actual = primero;
        while (actual != null) {
            if (actual.enemigo.getId() == id) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarPorId(int id) {
        return eliminarNodo(buscarNodo(id));
    }

    /** Desenlaza en O(1); rechaza nodos ajenos o que ya fueron eliminados. */
    public boolean eliminarNodo(NodoEnemigo nodo) {
        if (nodo == null || nodo.propietario != this) {
            return false;
        }
        if (nodo.anterior == null) {
            primero = nodo.siguiente;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
        }
        if (nodo.siguiente == null) {
            ultimo = nodo.anterior;
        } else {
            nodo.siguiente.anterior = nodo.anterior;
        }
        nodo.anterior = null;
        nodo.siguiente = null;
        nodo.propietario = null;
        cantidad--;
        return true;
    }

    public NodoEnemigo getPrimero() { return primero; }
    public NodoEnemigo getUltimo() { return ultimo; }
    public int contar() { return cantidad; }
    public boolean estaVacia() { return cantidad == 0; }

    public String mostrarAdelante() { return mostrar(false); }
    public String mostrarAtras() { return mostrar(true); }

    private String mostrar(boolean haciaAtras) {
        StringBuilder texto = new StringBuilder();
        NodoEnemigo actual = haciaAtras ? ultimo : primero;
        while (actual != null) {
            texto.append(actual.enemigo).append('\n');
            actual = haciaAtras ? actual.anterior : actual.siguiente;
        }
        return estaVacia() ? "No hay enemigos activos.\n" : texto.toString();
    }
}
