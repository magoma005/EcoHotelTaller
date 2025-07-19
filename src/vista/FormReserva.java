package vista;

import modelo.Huesped;
import modelo.Habitacion;
import modelo.Reserva;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.FechaInvalidaException;
import exceptions.HabitacionNoDisponibleException;
import exceptions.DatoInvalidoException;

//Formulario de gestión de reservas, permite registrar y listar reservas en una tabla.
public class FormReserva extends JInternalFrame {

    // Componentes de la interfaz
    private JComboBox<Huesped> cbxHuesped;
    private JComboBox<Habitacion> cbxHabitacion;
    private JTextField txtFechaEntrada, txtFechaSalida;
    private JButton btnGuardar, btnLimpiar;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;

    // Listas temporales
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Huesped> listaHuespedes = new ArrayList<>();
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();

    //Constructor que configura el formulario.
    public FormReserva() {
        setTitle("Gestión de Reservas");
        setClosable(true);
        setMaximizable(true);
        setSize(700, 500);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Huésped:"));
        cbxHuesped = new JComboBox<>();
        panelForm.add(cbxHuesped);
        panelForm.add(new JLabel("Habitación:"));
        cbxHabitacion = new JComboBox<>();
        panelForm.add(cbxHabitacion);
        panelForm.add(new JLabel("Fecha Entrada (AAAA-MM-DD):"));
        txtFechaEntrada = new JTextField();
        panelForm.add(txtFechaEntrada);
        panelForm.add(new JLabel("Fecha Salida (AAAA-MM-DD):"));
        txtFechaSalida = new JTextField();
        panelForm.add(txtFechaSalida);
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarReserva());
        panelForm.add(btnGuardar);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarCampos());
        panelForm.add(btnLimpiar);
        add(panelForm, BorderLayout.NORTH);

        // Tabla de reservas
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Huésped", "Habitación", "Entrada", "Salida"}, 0);
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaReservas);
        add(scroll, BorderLayout.CENTER);

        // Cargar datos de ejemplo en ComboBox
        cargarDatosEjemplo();
    }

    //Método para cargar datos de prueba en los JComboBox
    private void cargarDatosEjemplo() {
        // Huéspedes de prueba
        Huesped h1 = new Huesped("Ana Perez", "1001", "ana@correo.com", "3216549870");
        Huesped h2 = new Huesped("Luis Gomez", "1002", "luis@correo.com", "3104561234");
        listaHuespedes.add(h1);
        listaHuespedes.add(h2);
        for (Huesped h : listaHuespedes) {
            cbxHuesped.addItem(h);
        }
        // Habitaciones de prueba
        Habitacion hab1 = new Habitacion(101, "Estándar", 2, "Libre");
        Habitacion hab2 = new Habitacion(202, "Suite ecológica", 4, "Libre");
        listaHabitaciones.add(hab1);
        listaHabitaciones.add(hab2);
        for (Habitacion hab : listaHabitaciones) {
            cbxHabitacion.addItem(hab);
        }
    }

    //Método para guardar una nueva reserva.
    private void guardarReserva() {
        try {
            Huesped huesped = (Huesped) cbxHuesped.getSelectedItem();
            Habitacion habitacion = (Habitacion) cbxHabitacion.getSelectedItem();
            String fechaEntradaStr = txtFechaEntrada.getText();
            String fechaSalidaStr = txtFechaSalida.getText();

            if (huesped == null || habitacion == null ||
                    fechaEntradaStr.isEmpty() || fechaSalidaStr.isEmpty()) {
                throw new exceptions.DatoInvalidoException("Todos los campos son obligatorios.");
            }

            LocalDate fechaEntrada = LocalDate.parse(fechaEntradaStr);
            LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr);

            if (fechaEntrada.isBefore(LocalDate.now())) {
                throw new FechaInvalidaException("La fecha de entrada no puede ser anterior a hoy.");
            }
            if (!fechaSalida.isAfter(fechaEntrada)) {
                throw new FechaInvalidaException("La fecha de salida debe ser posterior a la fecha de entrada.");
            }

            // Validar disponibilidad de la habitación
            if (habitacion.getEstado().equalsIgnoreCase("Ocupada")) {
                throw new HabitacionNoDisponibleException("La habitación seleccionada no está disponible.");
            }

            // Cambiar estado de la habitación a ocupada (en sistema real se actualizaría en BD)
            habitacion.setEstado("Ocupada");

            Reserva r = new Reserva(fechaEntrada, fechaSalida, huesped, habitacion);
            listaReservas.add(r);

            modeloTabla.addRow(new Object[]{
                    r.getIdReserva(),
                    huesped.getNombre(),
                    habitacion.getNumero(),
                    fechaEntrada,
                    fechaSalida
            });

            JOptionPane.showMessageDialog(this, "Reserva guardada exitosamente.");
            limpiarCampos();

        } catch (DatoInvalidoException | FechaInvalidaException | HabitacionNoDisponibleException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error general: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método para limpiar los campos de entrada.
    private void limpiarCampos() {
        txtFechaEntrada.setText("");
        txtFechaSalida.setText("");
        cbxHuesped.setSelectedIndex(0);
        cbxHabitacion.setSelectedIndex(0);
    }
}