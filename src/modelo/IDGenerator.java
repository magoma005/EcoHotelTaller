package modelo;

//Clase que se usa para generar identificadores únicos para distintas entidades del sistema EcoHotel.

public class IDGenerator {

    // Contadores estáticos privados por entidad
    private static int contadorHuesped = 1;
    private static int contadorHabitacion = 1;
    private static int contadorServicio = 1;
    private static int contadorReserva = 1;

    /**
     * Esto genera un ID único para huéspedes.
     * Ejemplo: HUE-1, HUE-2, ...
     */
    public static String generateHuespedId() {
        String id = "HUE-" + contadorHuesped;
        contadorHuesped++; // Incrementa solo el contador de huéspedes
        return id;
    }

    /**
     * Esto genera un ID único para habitaciones.
     * Ejemplo: HAB-1, HAB-2, ...
     */
    public static String generateHabitacionId() {
        String id = "HAB-" + contadorHabitacion;
        contadorHabitacion++; // Incrementa solo el contador de habitaciones
        return id;
    }

    /**
     * Este genera un ID único para servicios adicionales.
     * Ejemplo: SER-1, SER-2, ...
     */
    public static String generateServicioId() {
        String id = "SER-" + contadorServicio;
        contadorServicio++; // Incrementa solo el contador de servicios
        return id;
    }

    /**
     * Genera un ID único para reservas.
     * Ejemplo: RES-1, RES-2, ...
     */
    public static String generateReservaId() {
        String id = "RES-" + contadorReserva;
        contadorReserva++; // Incrementa solo el contador de reservas
        return id;
    }
}
