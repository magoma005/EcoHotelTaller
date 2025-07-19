package vista;

import modelo.IDGenerator;

 //Clase de prueba para verificar generación de IDs únicos.

public class Main {
    public static void main(String[] args) {
        // Llamadas al método estático para generar IDs
        String id1 = IDGenerator.generateReservaId();
        String id2 = IDGenerator.generateReservaId();
        String id3 = IDGenerator.generateReservaId();

        // Mostrar IDs generados
        System.out.println("ID 1: " + id1); // RES-1
        System.out.println("ID 2: " + id2); // RES-2
        System.out.println("ID 3: " + id3); // RES-3
    }
}
