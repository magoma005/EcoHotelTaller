package exceptions;

public class ReservaDuplicadaException extends Exception {
    public ReservaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}