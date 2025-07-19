package vista;

import modelo.Huesped;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import exceptions.DatoInvalidoException;

//Este es el formulario de gestión de huéspedes, permite registrar y listar huéspedes en una tabla.

public class FormHuesped extends JInternalFrame {

    // Componentes de la interfaz
    private JTextField txtNombre, txtDocumento, txtCorreo, txtTelefono;
    private JButton btnGuardar, btnLimpiar;
    private JTable tablaHuespedes;
    private DefaultTableModel modeloTabla;

    // Lista temporal de huéspedes
    private ArrayList<Huesped> listaHuespedes = new ArrayList<>();

    //Constructor que configura el formulario.
    public FormHuesped() {
        setTitle("Gestión de Huéspedes");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Documento:"));
        txtDocumento = new JTextField();
        panelForm.add(txtDocumento);
        panelForm.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);
        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarHuesped());
        panelForm.add(btnGuardar);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarCampos());
        panelForm.add(btnLimpiar);
        add(panelForm, BorderLayout.NORTH);

        // Tabla de huéspedes
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Documento", "Correo", "Teléfono"}, 0);
        tablaHuespedes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaHuespedes);
        add(scroll, BorderLayout.CENTER);
    }

    //Método para guardar un nuevo huésped en la lista y tabla.

    private void guardarHuesped() {
        try {
            String nombre = txtNombre.getText();
            String documento = txtDocumento.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            // Validaciones
            if (nombre.isEmpty() || documento.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                throw new DatoInvalidoException("Todos los campos son obligatorios.");
            }
            if (!correo.contains("@")) {
                throw new DatoInvalidoException("El correo debe contener '@'.");
            }
            Huesped h = new Huesped(nombre, documento, correo, telefono);
            listaHuespedes.add(h);
            modeloTabla.addRow(new Object[]{
                    h.getIdHuesped(),
                    h.getNombre(),
                    h.getDocumento(),
                    h.getCorreo(),
                    h.getTelefono()
            });
            JOptionPane.showMessageDialog(this, "Huésped guardado exitosamente.");
            limpiarCampos();
        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Dato inválido", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error general: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método para limpiar los campos de texto.

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
}