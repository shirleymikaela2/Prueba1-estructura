package defensatorres;

/** Lista simplemente enlazada circular para administrar las oleadas. */
public class ListaCircularOleadas {

    private NodoOleada ultimo;
    private NodoOleada actual;
    private int cantidad;

    public ListaCircularOleadas() {
        ultimo = null;
        actual = null;
        cantidad = 0;
    }

    public void registrar(Oleada oleada) {
        NodoOleada nuevo = new NodoOleada(oleada);

        if (ultimo == null) {
            ultimo = nuevo;
            nuevo.siguiente = nuevo;
            actual = nuevo;
        } else {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }

        cantidad++;
    }

    public Oleada obtenerActual() {
        if (actual == null) {
            return null;
        }

        return actual.dato;
    }

    public void avanzarSiguiente() {
        if (actual != null) {
            actual = actual.siguiente;
        }
    }

    public void reiniciarCiclo() {
        if (ultimo != null) {
            actual = ultimo.siguiente;
        }
    }

    public int contar() {
        return cantidad;
    }

    public void mostrar() {
        if (ultimo == null) {
            System.out.println("No hay oleadas registradas.");
            return;
        }

        NodoOleada primero = ultimo.siguiente;
        NodoOleada nodo = primero;

        do {
            System.out.println(nodo.dato);
            nodo = nodo.siguiente;
        } while (nodo != primero);
    }

    public String resumen() {
        if (ultimo == null) {
            return "No hay oleadas registradas.";
        }

        StringBuilder resultado = new StringBuilder();

        NodoOleada primero = ultimo.siguiente;
        NodoOleada nodo = primero;

        do {
            resultado.append(nodo.dato);

            if (nodo == actual) {
                resultado.append(" <- oleada actual");
            }

            resultado.append("\n");
            nodo = nodo.siguiente;
        } while (nodo != primero);

        return resultado.toString();
    }
}