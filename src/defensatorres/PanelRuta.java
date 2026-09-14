package defensatorres;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class PanelRuta extends JPanel {

    private ListaSecuencialTorres torres;
    private ListaDobleEnemigos enemigos;
    private int vidasJugador;

    public PanelRuta() {
        setBackground(new Color(117, 190, 77));
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

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        dibujarTerreno(g2);
        dibujarCamino(g2);
        dibujarDecoracion(g2);
        dibujarEntradaYBase(g2);
        dibujarTorres(g2);
        dibujarEnemigos(g2);
        dibujarEstado(g2);

        g2.dispose();
    }

    private void dibujarTerreno(Graphics2D g2) {
        g2.setColor(new Color(120, 194, 77));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(106, 177, 68));

        for (int x = 20; x < getWidth(); x += 45) {
            for (int y = 25; y < getHeight(); y += 38) {
                g2.fillOval(x, y, 3, 3);
            }
        }
    }

    private void dibujarCamino(Graphics2D g2) {
        Point inicio = obtenerPosicion(0);
        Point giro1 = obtenerPosicion(5);
        Point giro2 = obtenerPosicion(9);
        Point giro3 = obtenerPosicion(14);
        Point fin = obtenerPosicion(20);

        Path2D camino = new Path2D.Double();
        camino.moveTo(inicio.x, inicio.y);
        camino.lineTo(giro1.x, giro1.y);
        camino.lineTo(giro2.x, giro2.y);
        camino.lineTo(giro3.x, giro3.y);
        camino.lineTo(fin.x, fin.y);

        g2.setStroke(new BasicStroke(
                58,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));
        g2.setColor(new Color(63, 128, 57));
        g2.draw(camino);

        g2.setStroke(new BasicStroke(
                46,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));
        g2.setColor(new Color(218, 174, 113));
        g2.draw(camino);

        g2.setStroke(new BasicStroke(1));
        g2.setColor(new Color(137, 99, 60));

        for (int i = 0; i <= 20; i++) {
            Point punto = obtenerPosicion(i);
            g2.drawOval(punto.x - 3, punto.y - 3, 6, 6);
        }
    }

    private void dibujarDecoracion(Graphics2D g2) {
        int ancho = getWidth();
        int alto = getHeight();

        dibujarArbol(g2, 35, 55);
        dibujarArbol(g2, 95, 70);
        dibujarArbol(g2, ancho - 85, 55);
        dibujarArbol(g2, ancho - 145, 95);
        dibujarArbol(g2, 55, alto - 85);
        dibujarArbol(g2, 140, alto - 65);
        dibujarArbol(g2, ancho - 80, alto - 90);
        dibujarArbol(g2, ancho - 165, alto - 55);

        g2.setColor(new Color(95, 95, 95));
        g2.fillOval(ancho / 2 - 45, alto / 2 - 35, 18, 12);
        g2.fillOval(ancho / 2 + 100, alto / 2 + 55, 13, 9);

        g2.setColor(new Color(245, 220, 70));
        g2.fillOval(ancho / 2 - 10, 50, 6, 6);
        g2.fillOval(ancho / 2 + 45, alto - 65, 6, 6);
    }

    private void dibujarArbol(Graphics2D g2, int x, int y) {
        g2.setColor(new Color(101, 65, 35));
        g2.fillRect(x - 4, y + 14, 8, 17);

        g2.setColor(new Color(30, 113, 55));
        g2.fillOval(x - 18, y - 12, 36, 36);

        g2.setColor(new Color(45, 145, 67));
        g2.fillOval(x - 13, y - 17, 26, 27);
    }

    private void dibujarEntradaYBase(Graphics2D g2) {
        Point entrada = obtenerPosicion(0);
        Point base = obtenerPosicion(20);

        g2.setColor(new Color(49, 116, 71));
        g2.fillRoundRect(entrada.x - 34, entrada.y - 25, 28, 50, 8, 8);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("INI", entrada.x - 30, entrada.y + 4);

        g2.setColor(new Color(155, 58, 54));
        g2.fillRoundRect(base.x - 15, base.y - 30, 34, 60, 8, 8);

        g2.setColor(Color.WHITE);
        g2.drawString("BASE", base.x - 12, base.y + 4);
    }

    private void dibujarTorres(Graphics2D g2) {
        if (torres == null) {
            return;
        }

        for (int i = 0; i < torres.getCantidad(); i++) {
            Torre torre = torres.obtener(i);

            if (torre != null) {
                Point punto = obtenerPosicion(torre.getPosicion());

                g2.setColor(new Color(42, 74, 145));
                g2.fillRoundRect(punto.x - 13, punto.y - 44, 26, 30, 6, 6);

                g2.setColor(new Color(208, 217, 233));
                g2.fillRect(punto.x - 4, punto.y - 57, 8, 17);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 11));
                g2.drawString("T" + torre.getId(), punto.x - 8, punto.y - 21);
            }
        }
    }

    private void dibujarEnemigos(Graphics2D g2) {
        if (enemigos == null || enemigos.getPrimero() == null) {
            return;
        }

        NodoEnemigo actual = enemigos.getPrimero();

        while (actual != null) {
            Enemigo enemigo = actual.getDato();
            Point punto = obtenerPosicion(enemigo.getPosicion());

            g2.setColor(new Color(180, 57, 49));
            g2.fillOval(punto.x - 12, punto.y - 12, 24, 24);

            g2.setColor(new Color(75, 25, 25));
            g2.fillOval(punto.x - 5, punto.y - 4, 4, 4);
            g2.fillOval(punto.x + 2, punto.y - 4, 4, 4);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            g2.drawString("E" + enemigo.getId(), punto.x - 8, punto.y + 28);

            actual = actual.getSiguiente();
        }
    }

    private void dibujarEstado(Graphics2D g2) {
        g2.setColor(new Color(34, 77, 40));
        g2.fillRoundRect(15, 15, 130, 34, 12, 12);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("VIDAS: " + vidasJugador, 27, 37);

        g2.setColor(new Color(255, 255, 255, 220));
        g2.fillRoundRect(getWidth() - 120, 15, 100, 28, 10, 10);

        g2.setColor(new Color(50, 80, 45));
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("RUTA 0 - 20", getWidth() - 110, 34);
    }

    private Point obtenerPosicion(int posicion) {
        int margenIzquierdo = 70;
        int margenDerecho = getWidth() - 85;

        int ySuperior = getHeight() / 4;
        int yMedio = getHeight() / 2;
        int yInferior = getHeight() - 95;

        int xPrimerGiro = getWidth() / 3;
        int xSegundoGiro = (getWidth() * 2) / 3;

        Point[] puntos = {
            new Point(margenIzquierdo, ySuperior),
            new Point(xPrimerGiro, ySuperior),
            new Point(xPrimerGiro, yMedio),
            new Point(xSegundoGiro, yMedio),
            new Point(xSegundoGiro, yInferior)
        };

        int[] marcas = {0, 5, 9, 14, 20};

        for (int i = 0; i < marcas.length - 1; i++) {
            if (posicion >= marcas[i] && posicion <= marcas[i + 1]) {
                double avance = (double) (posicion - marcas[i])
                        / (marcas[i + 1] - marcas[i]);

                int x = (int) (puntos[i].x
                        + (puntos[i + 1].x - puntos[i].x) * avance);

                int y = (int) (puntos[i].y
                        + (puntos[i + 1].y - puntos[i].y) * avance);

                return new Point(x, y);
            }
        }

        return new Point(margenDerecho, yInferior);
    }
}
