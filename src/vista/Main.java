package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase principal que muestra el JFrame con su menú de navegación.

public class Main extends JFrame {

    // Componentes globales
    private JDesktopPane desktopPane;

     //Constructor que le da inicio a la Ventana Principal
    public Main() {
        setTitle("EcoHotel - Refugio Natural");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // pantalla completa
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crea un desktopPane para JInternalFrames
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(31, 31, 31)); // fondo gris
        setContentPane(desktopPane);

        // Configuracion del menu
        JMenuBar menuBar = new JMenuBar();

        // Menu archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);

        // Menu gestion
        JMenu menuGestion = new JMenu("Gestión");

        JMenuItem itemHuesped = new JMenuItem("Huéspedes");
        itemHuesped.addActionListener(e -> abrirFormHuesped());

        JMenuItem itemHabitacion = new JMenuItem("Habitaciones");
        itemHabitacion.addActionListener(e -> abrirFormHabitacion());

        JMenuItem itemReserva = new JMenuItem("Reservas");
        itemReserva.addActionListener(e -> abrirFormReserva());

        menuGestion.add(itemHuesped);
        menuGestion.add(itemHabitacion);
        menuGestion.add(itemReserva);

        // Agrega menús al menuBar
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);

        setJMenuBar(menuBar);
    }

     //Método para abrir el formulario de huéspedes.

    private void abrirFormHuesped() {
        FormHuesped fh = new FormHuesped();
        desktopPane.add(fh);
        fh.setVisible(true);
    }

    //Método para abrir el formulario de habitaciones.

    private void abrirFormHabitacion() {
        FormHabitacion fh = new FormHabitacion();
        desktopPane.add(fh);
        fh.setVisible(true);
    }

    //Método para abrir el formulario de reservas.

    private void abrirFormReserva() {
        FormReserva fr = new FormReserva();
        desktopPane.add(fr);
        fr.setVisible(true);
    }

    //Método main para ejecutar la aplicación.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }
}
