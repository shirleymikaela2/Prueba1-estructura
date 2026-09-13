package defensatorres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/** Panel que representa la ruta, las torres, los enemigos y la base. */
public class PanelRuta extends JPanel {
    private ListaSecuencialTorres torres;
    private ListaDobleEnemigos enemigos;
    private int vidasJugador;

    public PanelRuta() {
        setBackground(new Color(231, 245, 255));
        setPreferredSize(new Dimension(620, 300));
    }

    public void actualizarDatos(ListaSecuencialTorres torres, ListaDobleEnemigos enemigos, int vidasJugador) {
        this.torres = torres;
        this.enemigos = enemigos;
        this.vidasJugador = vidasJugador;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int inicioX = 55;
        int finX = getWidth() - 70;
        int rutaY = getHeight() / 2;
        int largoRuta = finX - inicioX;

        g.setColor(new Color(48, 126, 62));
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString("Vidas de la base: " + vidasJugador, 18, 25);

        g.setColor(new Color(115, 115, 115));
        g.fillRoundRect(inicioX, rutaY - 20, largoRuta, 40, 18, 18);

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 11));
        for (int i = 0; i <= JuegoTowerDefense.FIN_RUTA; i++) {
            int x = inicioX + i * largoRuta / JuegoTowerDefense.FIN_RUTA;
            g.drawLine(x, rutaY - 27, x, rutaY - 18);
            g.drawString(String.valueOf(i), x - 4, rutaY + 38);
        }

        g.setColor(new Color(57, 150, 73));
        g.fillRoundRect(inicioX - 42, rutaY - 40, 31, 80, 7, 7);
        g.setColor(Color.WHITE);
        g.drawString("INI", inicioX - 39, rutaY + 4);

        g.setColor(new Color(171, 54, 54));
        g.fillRoundRect(finX + 12, rutaY - 42, 46, 84, 7, 7);
        g.setColor(Color.WHITE);
        g.drawString("BASE", finX + 15, rutaY + 4);

        dibujarTorres(g, inicioX, rutaY, largoRuta);
        dibujarEnemigos(g, inicioX, rutaY, largoRuta);
    }

    private void dibujarTorres(Graphics g, int inicioX, int rutaY, int largoRuta) {
        if (torres == null) return;

        for (int i = 0; i < torres.contarActivas(); i++) {
            Torre torre = torres.obtenerEn(i);
            int x = inicioX + torre.getPosicion() * largoRuta / JuegoTowerDefense.FIN_RUTA;

            g.setColor(new Color(49, 103, 204));
            g.fillRect(x - 9, rutaY - 66, 18, 34);
            g.setColor(Color.BLACK);
            g.drawString("T" + torre.getId(), x - 8, rutaY - 73);
        }
    }

    private void dibujarEnemigos(Graphics g, int inicioX, int rutaY, int largoRuta) {
        if (enemigos == null) return;

        NodoEnemigo actual = enemigos.obtenerPrimero();
        while (actual != null) {
            Enemigo enemigo = actual.obtenerDato();
            int posicion = Math.min(enemigo.getPosicion(), JuegoTowerDefense.FIN_RUTA);
            int x = inicioX + posicion * largoRuta / JuegoTowerDefense.FIN_RUTA;

            g.setColor(new Color(211, 65, 65));
            g.fillOval(x - 10, rutaY - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString("E" + enemigo.getId(), x - 9, rutaY + 58);
            actual = actual.obtenerSiguiente();
        }
    }
}
