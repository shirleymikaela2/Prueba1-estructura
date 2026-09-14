package defensatorres;

import javax.swing.SwingUtilities;

/** Punto de entrada de la interfaz grafica. */
public class TowerDefenseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaTowerDefense().setVisible(true));
    }
}
