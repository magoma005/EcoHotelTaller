package modelo;

public class IDGenerator {
    private static int idActual = 1;

  //Genera un nuevo ID incremental para reservas.

    public static int generarID() {
        return idActual++;
    }
}

