package defensatorres;

/** Lista secuencial manual: arreglo fijo mas contador de elementos. */
public class ListaSecuencialTorres {
    private final Torre[] torres;
    private int cantidad;

    public ListaSecuencialTorres(int capacidad) {
        torres = new Torre[capacidad];
        cantidad = 0;
    }

    public boolean insertar(Torre torre) {
        if (torre == null || cantidad == torres.length || buscarPorId(torre.getId()) != null) return false;
        torres[cantidad++] = torre;
        return true;
    }

    public Torre buscarPorId(int id) {
        for (int i = 0; i < cantidad; i++) {
            if (torres[i].getId() == id) return torres[i];
        }
        return null;
    }

    public boolean eliminarPorId(int id) {
        for (int i = 0; i < cantidad; i++) {
            if (torres[i].getId() == id) {
                for (int j = i; j < cantidad - 1; j++) torres[j] = torres[j + 1];
                torres[--cantidad] = null;
                return true;
            }
        }
        return false;
    }

    public int contarActivas() { return cantidad; }

    public void mostrar() {
        if (cantidad == 0) { System.out.println("No hay torres registradas."); return; }
        for (int i = 0; i < cantidad; i++) System.out.println(torres[i]);
    }

    public Torre obtenerEn(int indice) {
        return indice >= 0 && indice < cantidad ? torres[indice] : null;
    }
}
