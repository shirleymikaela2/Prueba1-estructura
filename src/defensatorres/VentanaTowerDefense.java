package defensatorres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Interfaz Swing para operar el juego sin menu de consola. */
public class VentanaTowerDefense extends JFrame {
    private final JuegoTowerDefense juego = new JuegoTowerDefense();
    private final PanelRuta panelRuta = new PanelRuta();
    private final JTextArea salida = new JTextArea();
    private final JLabel estado = new JLabel();

    private final JTextField torreId = new JTextField();
    private final JTextField torreNombre = new JTextField();
    private final JTextField torreTipo = new JTextField();
    private final JTextField torrePosicion = new JTextField();
    private final JTextField torreDanio = new JTextField();
    private final JTextField torreRango = new JTextField();
    private final JTextField torreCosto = new JTextField();
    private final JTextField oleadaId = new JTextField();
    private final JTextField oleadaCantidad = new JTextField();
    private final JTextField oleadaTipo = new JTextField();
    private final JTextField oleadaVida = new JTextField();
    private final JTextField oleadaVelocidad = new JTextField();

    public VentanaTowerDefense() {
        setTitle("Tower Defense - Estructura de Datos");
        setSize(1150, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirInterfaz();
        actualizarVista();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel titulo = new JLabel("TOWER DEFENSE", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 4, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel formularios = new JPanel(new GridLayout(1, 2, 10, 10));
        formularios.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        formularios.add(crearPanelTorres());
        formularios.add(crearPanelOleadas());

        salida.setEditable(false);
        salida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        salida.setText("Bienvenida. Registre torres y oleadas para comenzar.\n");
        JScrollPane scroll = new JScrollPane(salida);
        scroll.setBorder(BorderFactory.createTitledBorder("Registro de acciones"));
        scroll.setPreferredSize(new java.awt.Dimension(370, 260));

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        centro.add(formularios, BorderLayout.NORTH);
        centro.add(panelRuta, BorderLayout.CENTER);
        centro.add(scroll, BorderLayout.EAST);
        add(centro, BorderLayout.CENTER);

        JPanel controles = new JPanel(new GridLayout(2, 4, 8, 8));
        controles.setBorder(BorderFactory.createTitledBorder("Controles del juego"));
        agregarBoton(controles, "Mostrar torres", e -> registrarMensaje("TORRES", juego.resumenTorres()));
        agregarBoton(controles, "Eliminar torre", e -> eliminarTorre());
        agregarBoton(controles, "Mostrar oleadas", e -> registrarMensaje("OLEADAS", juego.resumenOleadas()));
        agregarBoton(controles, "Iniciar oleada", e -> iniciarOleada());
        agregarBoton(controles, "Avanzar turno", e -> { registrarMensaje("TURNO", juego.avanzarTurno()); actualizarVista(); });
        agregarBoton(controles, "Mostrar enemigos", e -> mostrarEnemigos());
        agregarBoton(controles, "Cargar ejemplo", e -> { registrarMensaje("EJEMPLO", juego.cargarCasoPrueba()); actualizarVista(); });
        agregarBoton(controles, "Reiniciar ciclo", e -> { registrarMensaje("REINICIO", juego.reiniciarCicloOleadas()); actualizarVista(); });

        estado.setHorizontalAlignment(SwingConstants.CENTER);
        estado.setBorder(BorderFactory.createEmptyBorder(8, 8, 10, 8));
        JPanel sur = new JPanel(new BorderLayout());
        sur.setBorder(BorderFactory.createEmptyBorder(0, 12, 8, 12));
        sur.add(controles, BorderLayout.CENTER);
        sur.add(estado, BorderLayout.SOUTH);
        add(sur, BorderLayout.SOUTH);
    }

    private JPanel crearPanelTorres() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 6, 6));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar torre"));
        agregarCampo(panel, "ID", torreId);
        agregarCampo(panel, "Nombre", torreNombre);
        agregarCampo(panel, "Tipo", torreTipo);
        agregarCampo(panel, "Posicion (0-20)", torrePosicion);
        agregarCampo(panel, "Danio", torreDanio);
        agregarCampo(panel, "Rango", torreRango);
        agregarCampo(panel, "Costo", torreCosto);
        JButton registrar = new JButton("Registrar torre");
        registrar.addActionListener(e -> registrarTorre());
        panel.add(new JLabel());
        panel.add(registrar);
        return panel;
    }

    private JPanel crearPanelOleadas() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 6, 6));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar oleada"));
        agregarCampo(panel, "ID", oleadaId);
        agregarCampo(panel, "Cantidad", oleadaCantidad);
        agregarCampo(panel, "Tipo enemigo", oleadaTipo);
        agregarCampo(panel, "Vida base", oleadaVida);
        agregarCampo(panel, "Velocidad", oleadaVelocidad);
        JButton registrar = new JButton("Registrar oleada");
        registrar.addActionListener(e -> registrarOleada());
        panel.add(new JLabel());
        panel.add(registrar);
        return panel;
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        panel.add(new JLabel(etiqueta + ":"));
        panel.add(campo);
    }

    private void agregarBoton(JPanel panel, String texto, java.awt.event.ActionListener evento) {
        JButton boton = new JButton(texto);
        boton.addActionListener(evento);
        panel.add(boton);
    }

    private void registrarTorre() {
        try {
            Torre torre = new Torre(entero(torreId), texto(torreNombre, "Nombre"), texto(torreTipo, "Tipo"),
                    enteroRango(torrePosicion, 0, JuegoTowerDefense.FIN_RUTA), enteroPositivo(torreDanio),
                    enteroPositivo(torreRango), enteroPositivo(torreCosto));
            if (juego.registrarTorre(torre)) {
                registrarMensaje("TORRE", "Torre registrada correctamente.");
                limpiar(torreId, torreNombre, torreTipo, torrePosicion, torreDanio, torreRango, torreCosto);
            } else registrarMensaje("TORRE", "No se pudo registrar: ID repetido o capacidad maxima alcanzada.");
            actualizarVista();
        } catch (IllegalArgumentException error) { advertir(error.getMessage()); }
    }

    private void registrarOleada() {
        try {
            juego.registrarOleada(new Oleada(entero(oleadaId), enteroPositivo(oleadaCantidad),
                    texto(oleadaTipo, "Tipo de enemigo"), enteroPositivo(oleadaVida), enteroPositivo(oleadaVelocidad)));
            registrarMensaje("OLEADA", "Oleada registrada en la lista circular.");
            limpiar(oleadaId, oleadaCantidad, oleadaTipo, oleadaVida, oleadaVelocidad);
            actualizarVista();
        } catch (IllegalArgumentException error) { advertir(error.getMessage()); }
    }

    private void eliminarTorre() {
        String valor = JOptionPane.showInputDialog(this, "ID de la torre a eliminar:");
        if (valor == null) return;
        try {
            int id = Integer.parseInt(valor.trim());
            registrarMensaje("TORRE", juego.eliminarTorre(id) ? "Torre eliminada." : "No existe una torre con ese ID.");
            actualizarVista();
        } catch (NumberFormatException error) { advertir("Ingrese un ID entero valido."); }
    }

    private void iniciarOleada() {
        registrarMensaje("OLEADA", juego.iniciarSiguienteOleada()
                ? "Oleada iniciada. Ahora puede avanzar turnos."
                : "No hay oleadas disponibles o ya se iniciaron todas.");
        actualizarVista();
    }

    private void mostrarEnemigos() {
        registrarMensaje("ENEMIGOS HACIA ADELANTE", juego.resumenEnemigosAdelante());
        registrarMensaje("ENEMIGOS HACIA ATRAS", juego.resumenEnemigosAtras());
    }

    private int entero(JTextField campo) {
        try { return Integer.parseInt(campo.getText().trim()); }
        catch (NumberFormatException error) { throw new IllegalArgumentException("Ingrese numeros enteros validos."); }
    }

    private int enteroPositivo(JTextField campo) {
        int valor = entero(campo);
        if (valor <= 0) throw new IllegalArgumentException("Este valor debe ser mayor que cero.");
        return valor;
    }

    private int enteroRango(JTextField campo, int minimo, int maximo) {
        int valor = entero(campo);
        if (valor < minimo || valor > maximo)
            throw new IllegalArgumentException("La posicion debe estar entre " + minimo + " y " + maximo + ".");
        return valor;
    }

    private String texto(JTextField campo, String nombre) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) throw new IllegalArgumentException(nombre + " es obligatorio.");
        return valor;
    }

    private void limpiar(JTextField... campos) { for (JTextField campo : campos) campo.setText(""); }
    private void registrarMensaje(String titulo, String mensaje) { salida.append("\n[" + titulo + "]\n" + mensaje + "\n"); }
    private void actualizarEstado() { estado.setText(juego.resumenEstado()); }

    private void actualizarVista() {
        actualizarEstado();
        panelRuta.actualizarDatos(juego.obtenerTorres(), juego.obtenerEnemigos(), juego.obtenerVidasJugador());
    }
    private void advertir(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Dato invalido", JOptionPane.WARNING_MESSAGE); }
}
