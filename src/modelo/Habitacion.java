package modelo;

public class Habitacion {
    private int numero;
    private String tipo;
    private int capacidad;
    private String estado; // libre, ocupada, mantenimiento

    public Habitacion(int numero, String tipo, int capacidad, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        setCapacidad(capacidad);
        this.estado = estado;
    }

    // Getters y Setters con validación
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad > 0) {
            this.capacidad = capacidad;
        } else {
            throw new IllegalArgumentException("Capacidad inválida: debe ser mayor a 0.");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitación " + numero + " (" + tipo + ")";
    }
}

