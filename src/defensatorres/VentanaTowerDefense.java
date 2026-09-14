package defensatorres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaTowerDefense extends JFrame {

    private final JuegoTowerDefense juego = new JuegoTowerDefense();
    private final PanelRuta panelRuta = new PanelRuta();
    private final JTextArea salida = new JTextArea();
    private final JLabel estado = new JLabel();

    public VentanaTowerDefense() {
        setTitle("Tower Defense");
        setSize(1120, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        construirInterfaz();
        actualizarVista();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(228, 238, 220));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setBackground(new Color(35, 82, 45));
        cabecera.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("TOWER DEFENSE");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel subtitulo = new JLabel("Defiende la base y derrota a los enemigos");
        subtitulo.setForeground(new Color(220, 240, 220));
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 13));

        cabecera.add(titulo, BorderLayout.CENTER);
        cabecera.add(subtitulo, BorderLayout.SOUTH);
        add(cabecera, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        centro.add(panelRuta, BorderLayout.CENTER);
        centro.add(crearPanelAcciones(), BorderLayout.EAST);

        add(centro, BorderLayout.CENTER);

        salida.setEditable(false);
        salida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        salida.setBackground(new Color(30, 42, 32));
        salida.setForeground(new Color(230, 245, 230));
        salida.setText("Bienvenida a Tower Defense.\nPulsa Cargar ejemplo para comenzar.\n");

        JScrollPane scroll = new JScrollPane(salida);
        scroll.setPreferredSize(new Dimension(800, 145));
        scroll.setBorder(BorderFactory.createTitledBorder("Registro de acciones"));

        estado.setHorizontalAlignment(SwingConstants.CENTER);
        estado.setFont(new Font("Arial", Font.BOLD, 13));
        estado.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        estado.setOpaque(true);
        estado.setBackground(new Color(238, 218, 151));

        JPanel pie = new JPanel(new BorderLayout(0, 5));
        pie.setBorder(BorderFactory.createEmptyBorder(0, 12, 10, 12));
        pie.setOpaque(false);
        pie.add(scroll, BorderLayout.CENTER);
        pie.add(estado, BorderLayout.SOUTH);

        add(pie, BorderLayout.SOUTH);
    }

    private JPanel crearPanelAcciones() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(220, 360));
        panel.setBackground(new Color(242, 247, 235));
        panel.setBorder(BorderFactory.createTitledBorder("Acciones del juego"));

        JLabel ayuda = new JLabel(
                "<html><center>Registra elementos con<br>ventanas pequeñas y juega<br>desde estos controles.</center></html>",
                SwingConstants.CENTER
        );
        ayuda.setAlignmentX(CENTER_ALIGNMENT);
        ayuda.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(ayuda);
        panel.add(Box.createVerticalStrut(10));

        panel.add(crearBoton("Cargar ejemplo", new Color(53, 117, 67), e -> {
            registrarMensaje("EJEMPLO", juego.cargarCasoPrueba());
            actualizarVista();
        }));

        panel.add(crearBoton("Registrar torre", new Color(55, 112, 184), e -> registrarTorreConDialogo()));
        panel.add(crearBoton("Registrar oleada", new Color(122, 87, 174), e -> registrarOleadaConDialogo()));
        panel.add(crearBoton("Iniciar oleada", new Color(191, 118, 43), e -> iniciarOleada()));
        panel.add(crearBoton("Avanzar turno", new Color(191, 75, 52), e -> {
            registrarMensaje("TURNO", juego.avanzarTurno());
            actualizarVista();
        }));

        panel.add(Box.createVerticalStrut(6));

        panel.add(crearBoton("Mostrar torres", new Color(86, 104, 110),
                e -> registrarMensaje("TORRES", juego.resumenTorres())));

        panel.add(crearBoton("Mostrar enemigos", new Color(86, 104, 110),
                e -> mostrarEnemigos()));

        panel.add(crearBoton("Eliminar torre", new Color(86, 104, 110),
                e -> eliminarTorre()));

        panel.add(crearBoton("Reiniciar ciclo", new Color(86, 104, 110), e -> {
            registrarMensaje("REINICIO", juego.reiniciarCicloOleadas());
            actualizarVista();
        }));

        return panel;
    }

    private JButton crearBoton(String texto, Color color, ActionListener evento) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(190, 34));
        boton.setPreferredSize(new Dimension(190, 34));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.addActionListener(evento);

        JPanel contenedor = new JPanel();
        contenedor.setOpaque(false);
        contenedor.add(boton);

        return boton;
    }

    private void registrarTorreConDialogo() {
        String id = solicitar("ID de la torre:");
        if (id == null) return;

        String nombre = solicitar("Nombre de la torre:");
        if (nombre == null) return;

        String tipo = solicitar("Tipo de torre:");
        if (tipo == null) return;

        String posicion = solicitar("Posición de la torre (0 a 20):");
        if (posicion == null) return;

        String danio = solicitar("Daño de la torre:");
        if (danio == null) return;

        String rango = solicitar("Rango de ataque:");
        if (rango == null) return;

        String costo = solicitar("Costo de la torre:");
        if (costo == null) return;

        try {
            Torre torre = new Torre(
                    entero(id, "ID"),
                    textoObligatorio(nombre, "Nombre"),
                    textoObligatorio(tipo, "Tipo"),
                    enteroRango(posicion, 0, JuegoTowerDefense.FIN_RUTA),
                    enteroPositivo(danio, "Daño"),
                    enteroPositivo(rango, "Rango"),
                    enteroPositivo(costo, "Costo")
            );

            if (juego.registrarTorre(torre)) {
                registrarMensaje("TORRE", "Torre registrada correctamente.");
            } else {
                registrarMensaje("TORRE", "No se pudo registrar: ID repetido o capacidad máxima alcanzada.");
            }

            actualizarVista();
        } catch (IllegalArgumentException error) {
            advertir(error.getMessage());
        }
    }

    private void registrarOleadaConDialogo() {
        String id = solicitar("ID de la oleada:");
        if (id == null) return;

        String cantidad = solicitar("Cantidad de enemigos:");
        if (cantidad == null) return;

        String tipo = solicitar("Tipo de enemigo:");
        if (tipo == null) return;

        String vida = solicitar("Vida base de los enemigos:");
        if (vida == null) return;

        String velocidad = solicitar("Velocidad de los enemigos:");
        if (velocidad == null) return;

        try {
            Oleada oleada = new Oleada(
                    entero(id, "ID"),
                    enteroPositivo(cantidad, "Cantidad"),
                    textoObligatorio(tipo, "Tipo de enemigo"),
                    enteroPositivo(vida, "Vida base"),
                    enteroPositivo(velocidad, "Velocidad")
            );

            juego.registrarOleada(oleada);
            registrarMensaje("OLEADA", "Oleada registrada correctamente.");
            actualizarVista();
        } catch (IllegalArgumentException error) {
            advertir(error.getMessage());
        }
    }

    private void iniciarOleada() {
        if (juego.iniciarSiguienteOleada()) {
            registrarMensaje("OLEADA", "Oleada iniciada. Ahora puede avanzar turnos.");
        } else {
            registrarMensaje("OLEADA", "No hay oleadas disponibles o ya se iniciaron todas.");
        }

        actualizarVista();
    }

    private void eliminarTorre() {
        String valor = solicitar("ID de la torre a eliminar:");
        if (valor == null) return;

        try {
            int id = entero(valor, "ID");
            String mensaje = juego.eliminarTorre(id)
                    ? "Torre eliminada."
                    : "No existe una torre con ese ID.";

            registrarMensaje("TORRE", mensaje);
            actualizarVista();
        } catch (IllegalArgumentException error) {
            advertir(error.getMessage());
        }
    }

    private void mostrarEnemigos() {
        registrarMensaje("ENEMIGOS HACIA ADELANTE", juego.resumenEnemigosAdelante());
        registrarMensaje("ENEMIGOS HACIA ATRÁS", juego.resumenEnemigosAtras());
    }

    private String solicitar(String mensaje) {
        String valor = JOptionPane.showInputDialog(this, mensaje);
        return valor == null ? null : valor.trim();
    }

    private int entero(String valor, String nombre) {
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(nombre + " debe ser un número entero válido.");
        }
    }

    private int enteroPositivo(String valor, String nombre) {
        int numero = entero(valor, nombre);

        if (numero <= 0) {
            throw new IllegalArgumentException(nombre + " debe ser mayor que cero.");
        }

        return numero;
    }

    private int enteroRango(String valor, int minimo, int maximo) {
        int numero = entero(valor, "Posición");

        if (numero < minimo || numero > maximo) {
            throw new IllegalArgumentException(
                    "La posición debe estar entre " + minimo + " y " + maximo + "."
            );
        }

        return numero;
    }

    private String textoObligatorio(String valor, String nombre) {
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " es obligatorio.");
        }

        return valor.trim();
    }

    private void registrarMensaje(String titulo, String mensaje) {
        salida.append("\n[" + titulo + "]\n" + mensaje + "\n");
        salida.setCaretPosition(salida.getDocument().getLength());
    }

    private void actualizarVista() {
        estado.setText(juego.resumenEstado());

        panelRuta.actualizarDatos(
                juego.obtenerTorres(),
                juego.obtenerEnemigos(),
                juego.obtenerVidasJugador()
        );
    }

    private void advertir(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Dato inválido",
                JOptionPane.WARNING_MESSAGE
        );
    }
}