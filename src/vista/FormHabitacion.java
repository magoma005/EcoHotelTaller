package vista;

import modelo.Habitacion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

//Formulario de gestión de habitaciones, permite registrar y listar habitaciones en una tabla.

public class FormHabitacion extends JInternalFrame {

    // Componentes de la interfaz
    private JSpinner spnNumero, spnCapacidad;
    private JComboBox<String> cbxTipo, cbxEstado;
    private JButton btnGuardar, btnLimpiar;
    private JTable tablaHabitaciones;
    private DefaultTableModel modeloTabla;

    // Lista temporal de habitaciones
    public static ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();

    //Constructor que configura el formulario.
    public FormHabitacion() {
        setTitle("Gestión de Habitaciones");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Número:"));
        spnNumero = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        panelForm.add(spnNumero);
        panelForm.add(new JLabel("Tipo:"));
        cbxTipo = new JComboBox<>(new String[]{"Estándar", "Suite ecológica", "Familiar"});
        panelForm.add(cbxTipo);
        panelForm.add(new JLabel("Capacidad:"));
        spnCapacidad = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        panelForm.add(spnCapacidad);
        panelForm.add(new JLabel("Estado:"));
        cbxEstado = new JComboBox<>(new String[]{"Libre", "Ocupada", "Mantenimiento"});
        panelForm.add(cbxEstado);
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarHabitacion());
        panelForm.add(btnGuardar);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarCampos());
        panelForm.add(btnLimpiar);
        add(panelForm, BorderLayout.NORTH);

        // Tabla de habitaciones
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Número", "Tipo", "Capacidad", "Estado"}, 0);
        tablaHabitaciones = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaHabitaciones);
        add(scroll, BorderLayout.CENTER);
    }

    //Método para guardar una nueva habitación en la lista y tabla.

    private void guardarHabitacion() {
        try {
            int numero = (Integer) spnNumero.getValue();
            String tipo = (String) cbxTipo.getSelectedItem();
            int capacidad = (Integer) spnCapacidad.getValue();
            String estado = (String) cbxEstado.getSelectedItem();

            Habitacion h = new Habitacion(numero, tipo, capacidad, estado);
            listaHabitaciones.add(h);

            modeloTabla.addRow(new Object[]{
                    h.getIdHabitacion(),
                    h.getNumero(),
                    h.getTipo(),
                    h.getCapacidad(),
                    h.getEstado()
            });

            JOptionPane.showMessageDialog(this, "Habitación guardada exitosamente.");
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Excepción", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método para limpiar los campos de entrada.

    private void limpiarCampos() {
        spnNumero.setValue(1);
        cbxTipo.setSelectedIndex(0);
        spnCapacidad.setValue(1);
        cbxEstado.setSelectedIndex(0);
    }
}