package defensatorres;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PanelRuta extends JPanel {

    private ListaSecuencialTorres torres;
    private ListaDobleEnemigos enemigos;
    private int vidasJugador;

    public PanelRuta() {
        setBackground(new Color(230, 245, 255));
        vidasJugador = 3;
    }

    public void actualizarDatos(
            ListaSecuencialTorres torres,
            ListaDobleEnemigos enemigos,
            int vidasJugador) {

        this.torres = torres;
        this.enemigos = enemigos;
        this.vidasJugador = vidasJugador;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int inicioX = 60;
        int finX = getWidth() - 80;
        int rutaY = getHeight() / 2;
        int largoRuta = finX - inicioX;

        g.setColor(new Color(120, 120, 120));
        g.fillRoundRect(inicioX, rutaY - 22, largoRuta, 44, 20, 20);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 13));

        for (int i = 0; i <= 20; i++) {
            int x = inicioX + (i * largoRuta / 20);
            g.drawLine(x, rutaY - 28, x, rutaY - 18);
            g.drawString(String.valueOf(i), x - 4, rutaY + 43);
        }

        g.setColor(new Color(70, 140, 70));
        g.fillRect(inicioX - 38, rutaY - 42, 28, 84);
        g.setColor(Color.WHITE);
        g.drawString("INI", inicioX - 35, rutaY + 4);

        g.setColor(new Color(170, 60, 60));
        g.fillRect(finX + 15, rutaY - 42, 35, 84);
        g.setColor(Color.WHITE);
        g.drawString("BASE", finX + 15, rutaY + 4);

        g.setColor(Color.BLACK);
        g.drawString("Vidas: " + vidasJugador, 20, 30);

        dibujarTorres(g, inicioX, rutaY, largoRuta);
        dibujarEnemigos(g, inicioX, rutaY, largoRuta);
    }

    private void dibujarTorres(Graphics g, int inicioX, int rutaY, int largoRuta) {
        if (torres == null) {
            return;
        }

        for (int i = 0; i < torres.getCantidad(); i++) {
            Torre torre = torres.obtener(i);

            if (torre != null) {
                int x = inicioX + (torre.getPosicion() * largoRuta / 20);

                g.setColor(new Color(55, 100, 210));
                g.fillRect(x - 10, rutaY - 70, 20, 35);

                g.setColor(Color.BLACK);
                g.drawString("T" + torre.getId(), x - 10, rutaY - 78);
            }
        }
    }

    private void dibujarEnemigos(Graphics g, int inicioX, int rutaY, int largoRuta) {
        if (enemigos == null || enemigos.getPrimero() == null) {
            return;
        }

        NodoEnemigo actual = enemigos.getPrimero();

        while (actual != null) {
            Enemigo enemigo = actual.getDato();
            int x = inicioX + (enemigo.getPosicion() * largoRuta / 20);

            g.setColor(new Color(210, 70, 70));
            g.fillOval(x - 11, rutaY - 11, 22, 22);

            g.setColor(Color.BLACK);
            g.drawString("E" + enemigo.getId(), x - 10, rutaY + 70);

            actual = actual.getSiguiente();
        }
    }
}