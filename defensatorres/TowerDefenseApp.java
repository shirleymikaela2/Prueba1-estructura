package defensatorres;

import java.util.Scanner;

/** Punto de entrada y menu principal solicitado en la prueba. */
public class TowerDefenseApp {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final JuegoTowerDefense JUEGO = new JuegoTowerDefense();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: registrarTorre(); break;
                case 2: JUEGO.mostrarTorres(); break;
                case 3: eliminarTorre(); break;
                case 4: registrarOleada(); break;
                case 5: JUEGO.mostrarOleadas(); break;
                case 6: JUEGO.iniciarSiguienteOleada(); break;
                case 7: JUEGO.avanzarTurno(); break;
                case 8: mostrarEnemigos(); break;
                case 9: JUEGO.mostrarEstado(); break;
                case 10: JUEGO.reiniciarCicloOleadas(); break;
                case 11: cargarCasoPrueba(); break;
                case 12: System.out.println("Programa finalizado."); break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 12);
        ENTRADA.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== TOWER DEFENSE - ESTRUCTURAS DE DATOS ===");
        System.out.println("1. Registrar torre defensiva");
        System.out.println("2. Mostrar torres registradas");
        System.out.println("3. Eliminar torre");
        System.out.println("4. Registrar oleada");
        System.out.println("5. Mostrar oleadas");
        System.out.println("6. Iniciar siguiente oleada");
        System.out.println("7. Avanzar turno");
        System.out.println("8. Mostrar enemigos activos");
        System.out.println("9. Mostrar estado general del juego");
        System.out.println("10. Reiniciar ciclo de oleadas");
        System.out.println("11. Cargar caso de prueba sugerido");
        System.out.println("12. Salir");
    }

    private static void registrarTorre() {
        int id = leerPositivo("ID de torre: ");
        String nombre = leerTexto("Nombre: ");
        String tipo = leerTexto("Tipo: ");
        int posicion = leerRango("Posicion (0 a 20): ", 0, JuegoTowerDefense.FIN_RUTA);
        int danio = leerPositivo("Danio: ");
        int rango = leerPositivo("Rango: ");
        int costo = leerPositivo("Costo: ");
        System.out.println(JUEGO.registrarTorre(new Torre(id, nombre, tipo, posicion, danio, rango, costo))
                ? "Torre registrada." : "No se pudo registrar: ID repetido o capacidad maxima alcanzada.");
    }

    private static void eliminarTorre() {
        int id = leerPositivo("ID de la torre a eliminar: ");
        Torre torre = JUEGO.buscarTorre(id);
        if (torre == null) System.out.println("No existe una torre con ese ID.");
        else System.out.println(JUEGO.eliminarTorre(id) ? "Torre eliminada: " + torre : "No se pudo eliminar.");
    }

    private static void registrarOleada() {
        int id = leerPositivo("ID de oleada: ");
        int cantidad = leerPositivo("Cantidad de enemigos: ");
        String tipo = leerTexto("Tipo de enemigo: ");
        int vida = leerPositivo("Vida base: ");
        int velocidad = leerPositivo("Velocidad base: ");
        JUEGO.registrarOleada(new Oleada(id, cantidad, tipo, vida, velocidad));
        System.out.println("Oleada registrada en la lista circular.");
    }

    private static void mostrarEnemigos() {
        System.out.println("Recorrido hacia adelante:");
        JUEGO.mostrarEnemigosAdelante();
        System.out.println("Recorrido hacia atras:");
        JUEGO.mostrarEnemigosAtras();
    }

    private static void cargarCasoPrueba() {
        boolean t1 = JUEGO.registrarTorre(new Torre(1, "Arquero", "Arquero", 3, 20, 2, 50));
        boolean t2 = JUEGO.registrarTorre(new Torre(2, "Canon", "Canon", 8, 35, 3, 100));
        if (t1) JUEGO.registrarOleada(new Oleada(1, 3, "Basico", 50, 1));
        if (t2) JUEGO.registrarOleada(new Oleada(2, 2, "Rapido", 40, 2));
        System.out.println("Caso de prueba cargado. Si ya existia, los elementos repetidos se ignoraron.");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(ENTRADA.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Ingrese un numero entero valido."); }
        }
    }

    private static int leerPositivo(String mensaje) {
        int valor;
        do { valor = leerEntero(mensaje); if (valor <= 0) System.out.println("Debe ser mayor que cero."); } while (valor <= 0);
        return valor;
    }

    private static int leerRango(String mensaje, int minimo, int maximo) {
        int valor;
        do { valor = leerEntero(mensaje); if (valor < minimo || valor > maximo) System.out.println("Valor permitido: " + minimo + " a " + maximo + "."); }
        while (valor < minimo || valor > maximo);
        return valor;
    }

    private static String leerTexto(String mensaje) {
        String texto;
        do { System.out.print(mensaje); texto = ENTRADA.nextLine().trim(); if (texto.isEmpty()) System.out.println("Este dato es obligatorio."); }
        while (texto.isEmpty());
        return texto;
    }
}
