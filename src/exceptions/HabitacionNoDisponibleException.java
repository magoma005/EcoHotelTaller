package exceptions;

 //Excepción que se lanza cuando una habitación no está disponible para la reserva.

public class HabitacionNoDisponibleException extends Exception {
    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}