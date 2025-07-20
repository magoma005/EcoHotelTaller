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
import vista.FormHabitacion;




//Formulario de gestión de reservas, permite registrar y listar reservas en una tabla.
public class FormReserva extends JInternalFrame {

    // Componentes de la interfaz
    private JComboBox<Huesped> cbxHuesped;
    private JComboBox<Habitacion> cbxHabitacion;
    private JTextField txtFechaEntrada, txtFechaSalida;
    private JButton btnGuardar, btnLimpiar;
    private JButton btnActualizarHuespedes, btnActualizarHabitaciones;
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
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 5, 5)); // aumenté filas a 7 para el nuevo botón
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

        // Botón para actualizar huéspedes
        btnActualizarHuespedes = new JButton("Actualizar huéspedes");
        btnActualizarHuespedes.addActionListener(e -> actualizarHuespedes());
        panelForm.add(btnActualizarHuespedes);

        // Botón para actualizar habitaciones
        btnActualizarHabitaciones = new JButton("Actualizar habitaciones");
        btnActualizarHabitaciones.addActionListener(e -> actualizarHabitaciones());
        panelForm.add(btnActualizarHabitaciones);

        add(panelForm, BorderLayout.NORTH);

        // Tabla de reserva
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Huésped", "Habitación", "Entrada", "Salida"}, 0);
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaReservas);
        add(scroll, BorderLayout.CENTER);
        cargarDatosEjemplo();
    }

    //Método para cargar datos de prueba en los JComboBox
    private void cargarDatosEjemplo() {
        // Cargar huéspedes creados en FormHuesped
        listaHuespedes = FormHuesped.listaHuespedes;
        for (Huesped h : listaHuespedes) {
            cbxHuesped.addItem(h);
        }

        // Cargar habitaciones creadas en FormHabitacion
        listaHabitaciones = FormHabitacion.listaHabitaciones;
        for (Habitacion h : listaHabitaciones) {
            cbxHabitacion.addItem(h);
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
                throw new DatoInvalidoException("Todos los campos son obligatorios.");
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

            // Cambiar estado de la habitación a ocupada
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

    //Método para actualizar huéspedes
    private void actualizarHuespedes() {
        cbxHuesped.removeAllItems();
        listaHuespedes = FormHuesped.listaHuespedes;
        for (Huesped h : listaHuespedes) {
            cbxHuesped.addItem(h);
        }
        JOptionPane.showMessageDialog(this, "Huespedes actualizados.");
    }

    //Método para actualizar habitaciones
    private void actualizarHabitaciones() {
        cbxHabitacion.removeAllItems();
        listaHabitaciones = FormHabitacion.listaHabitaciones;
        for (Habitacion hab : listaHabitaciones) {
            cbxHabitacion.addItem(hab);
        }
        JOptionPane.showMessageDialog(this, "Habitaciones actualizadas.");
    }


    //Método para limpiar los campos de entrada.
    private void limpiarCampos() {
        txtFechaEntrada.setText("");
        txtFechaSalida.setText("");
        cbxHuesped.setSelectedIndex(0);
        cbxHabitacion.setSelectedIndex(0);
    }
}
