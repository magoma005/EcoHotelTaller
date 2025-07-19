package vista;

import modelo.IDGenerator;

// Esta es una clase de prueba para verificar la generación de IDs únicos para cada entidad del sistema EcoHotel.

public class Main {
    public static void main(String[] args) {

        // Generar y mostrar IDs de Huesped
        System.out.println(IDGenerator.generateHuespedId()); // HUE-1
        System.out.println(IDGenerator.generateHuespedId()); // HUE-2

        // Generar y mostrar IDs de Habitacion
        System.out.println(IDGenerator.generateHabitacionId()); // HAB-1
        System.out.println(IDGenerator.generateHabitacionId()); // HAB-2

        // Generar y mostrar IDs de Servicio adicional
        System.out.println(IDGenerator.generateServicioId()); // SER-1
        System.out.println(IDGenerator.generateServicioId()); // SER-2

        // Generar y mostrar IDs de Reserva
        System.out.println(IDGenerator.generateReservaId()); // RES-1
        System.out.println(IDGenerator.generateReservaId()); // RES-2

        //Llamadas múltiples para verificar incremento
        System.out.println(IDGenerator.generateHuespedId()); // HUE-3
        System.out.println(IDGenerator.generateHabitacionId()); // HAB-3
        System.out.println(IDGenerator.generateServicioId()); // SER-3
        System.out.println(IDGenerator.generateReservaId()); // RES-3
    }
}

