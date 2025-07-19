package modelo;

public class Huesped {
    private String nombre;
    private String documento;
    private String correo;
    private String telefono;

    public Huesped(String nombre, String documento, String correo, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        setCorreo(correo);
        this.telefono = telefono;
    }

    // Getters y Setters con validación
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo.contains("@")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo inválido: debe contener '@'.");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }
}
