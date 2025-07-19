package exceptions;


 //Excepción que se lanza cuando un dato es inválido o nulo.

public class DatoInvalidoException extends Exception {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

