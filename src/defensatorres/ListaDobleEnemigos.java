package defensatorres;

/** Lista doble manual que administra los enemigos activos. */
public class ListaDobleEnemigos {

    private NodoEnemigo primero;
    private NodoEnemigo ultimo;
    private int cantidad;

    public ListaDobleEnemigos() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    public void insertarFinal(Enemigo enemigo) {
        NodoEnemigo nuevo = new NodoEnemigo(enemigo);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }

        cantidad++;
    }

    public Enemigo buscarPorId(int id) {
        NodoEnemigo actual = primero;

        while (actual != null) {
            if (actual.dato.getId() == id) {
                return actual.dato;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    private void eliminarNodo(NodoEnemigo nodo) {
        if (nodo.anterior != null) {
            nodo.anterior.siguiente = nodo.siguiente;
        } else {
            primero = nodo.siguiente;
        }

        if (nodo.siguiente != null) {
            nodo.siguiente.anterior = nodo.anterior;
        } else {
            ultimo = nodo.anterior;
        }

        cantidad--;
    }

    public int moverYEliminarEscapados(int finRuta) {
        int escapados = 0;
        NodoEnemigo actual = primero;

        while (actual != null) {
            NodoEnemigo siguiente = actual.siguiente;

            actual.dato.avanzar();

            if (actual.dato.getPosicion() >= finRuta) {
                eliminarNodo(actual);
                escapados++;
            }

            actual = siguiente;
        }

        return escapados;
    }

    public int atacarConTorres(ListaSecuencialTorres torres) {
        int ataques = 0;
        NodoEnemigo enemigo = primero;

        while (enemigo != null) {
            for (int i = 0; i < torres.contarActivas(); i++) {
                Torre torre = torres.obtenerEn(i);

                if (torre.estaEnRango(enemigo.dato)) {
                    enemigo.dato.recibirDanio(torre.getDanio());
                    ataques++;
                }
            }

            enemigo = enemigo.siguiente;
        }

        return ataques;
    }

    public int eliminarDestruidos() {
        int eliminados = 0;
        NodoEnemigo actual = primero;

        while (actual != null) {
            NodoEnemigo siguiente = actual.siguiente;

            if (actual.dato.estaDestruido()) {
                eliminarNodo(actual);
                eliminados++;
            }

            actual = siguiente;
        }

        return eliminados;
    }

    public int contar() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public NodoEnemigo obtenerPrimero() {
        return primero;
    }

    public void mostrarAdelante() {
        if (primero == null) {
            System.out.println("No hay enemigos activos.");
            return;
        }

        NodoEnemigo actual = primero;

        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public void mostrarAtras() {
        if (ultimo == null) {
            System.out.println("No hay enemigos activos.");
            return;
        }

        NodoEnemigo actual = ultimo;

        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.anterior;
        }
    }

    public String resumenAdelante() {
        if (primero == null) {
            return "No hay enemigos activos.";
        }

        StringBuilder resultado = new StringBuilder();
        NodoEnemigo actual = primero;

        while (actual != null) {
            resultado.append(actual.dato).append("\n");
            actual = actual.siguiente;
        }

        return resultado.toString();
    }

    public String resumenAtras() {
        if (ultimo == null) {
            return "No hay enemigos activos.";
        }

        StringBuilder resultado = new StringBuilder();
        NodoEnemigo actual = ultimo;

        while (actual != null) {
            resultado.append(actual.dato).append("\n");
            actual = actual.anterior;
        }

        return resultado.toString();
    }
}