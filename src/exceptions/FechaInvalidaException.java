package exceptions;

 //Excepción que se lanza cuando las fechas de la reserva son inconsistentes.

public class FechaInvalidaException extends Exception {
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

