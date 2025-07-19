package modelo;

public class IDGenerator {

    // Contador interno estático (compartido por todas las llamadas)
    private static int contador = 1;

    /**
     * Genera un ID único para reservas con el prefijo "RES-"
     * Ejemplo: RES-1, RES-2, ...
     */

    public static String generateReservaId() {
        String id = "RES-" + contador;
        contador++; // Incrementa para el siguiente ID
        return id;
    }
}

