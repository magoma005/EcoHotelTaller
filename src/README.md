# 🏡 EcoHotel Rural – Sistema de Gestión de Reservas

## 📌 Descripción

Este proyecto es una aplicación de escritorio en **Java Swing** que permite gestionar huéspedes, habitaciones y reservas en un EcoHotel Rural. Es un ejercicio académico diseñado para aplicar conceptos de **programación orientada a objetos**, manejo de excepciones, listas dinámicas y validaciones en el contexto de un caso de estudio real.

---

## ✨ Funcionalidades implementadas

✅ Registrar huéspedes con nombre completo, documento, correo electrónico y teléfono.  
✅ Registrar habitaciones con número, tipo, capacidad y estado.  
✅ Registrar reservas con fecha de entrada, salida, huésped y habitación asociada.  
✅ Validar disponibilidad de habitaciones antes de permitir una reserva.  
✅ Mostrar listado de reservas activas, huéspedes y habitaciones.  
✅ Permitir cancelar una reserva, actualizando el estado de la habitación.  
✅ Lanzar excepciones en casos de:
- Fechas inconsistentes.
- Campos vacíos o inválidos.
- Reservas duplicadas o superpuestas.

---

## 🗂️ Estructura actual del proyecto
src/
├── modelo/
│ ├── Huesped.java
│ ├── Habitacion.java
│ ├── Reserva.java
│
├── vista/
│ ├── FormHuesped.java
│ ├── FormHabitacion.java
│ ├── FormReserva.java
│
├── exceptions/
│ ├── DatoInvalidoException.java
│ ├── FechaInvalidaException.java
│ ├── HabitacionNoDisponibleException.java

yaml
Copiar
Editar

---

## 💻 Tecnologías utilizadas

- Java 17 (compatible con 8+)
- Swing (JInternalFrame, JTable, JComboBox, JOptionPane)
- IDE: NetBeans

---

## ⚠️ Limitaciones actuales

🚫 No implementa persistencia (los datos se pierden al cerrar).  
🚫 No tiene capa DAO ni controlador (toda la lógica está en las vistas).

---

## 🎯 Próximos pasos

1. Implementar DAOs para persistencia en archivos `.dat` o base de datos.
2. Crear controladores para separar lógica de la interfaz.
3. Generar reportes de reservas (PDF o Excel).
4. Mejorar la interfaz gráfica con diseño profesional.
5. Preparar un diagrama de clases UML actualizado con DAOs y Controladores.

---

## 📝 Autor

👤 **MIGUEL ANGEL GUARIN**  
Estudiante de INGENIERIA DE SOFTWARE 
📧 magoo123005@gmail.com

---

## 🚀 Estado actual del proyecto

✅ **Versión funcional básica completada.**  
🔜 Migración a arquitectura **MVC + DAO** en la siguiente iteración.

---


