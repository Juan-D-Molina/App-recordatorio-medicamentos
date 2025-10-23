package app;

import java.util.Scanner;
import modelo.*;
import servicios.*;

public class AppMedicamentos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorMedicamentos gestor = new GestorMedicamentos();
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones();

        Paciente paciente = new Paciente("Carlos Pérez", "12345678");

        // Registramos al paciente como suscriptor de notificaciones
        gestorNotificaciones.registrar(paciente);

        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE MEDICAMENTOS ---");
            System.out.println("1. Agregar medicamento");
            System.out.println("2. Mostrar medicamentos");
            System.out.println("3. Mostrar recordatorios del paciente");
            System.out.println("4. Guardar datos (no disponible)");
            System.out.println("5. Cargar datos (no disponible)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: debe ingresar un número.");
                continue; // vuelve al menú
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del medicamento: ");
                    String nombre = sc.nextLine().trim();

                    System.out.print("Dosis: ");
                    String dosis = sc.nextLine().trim();

                    System.out.print("Hora del recordatorio (por ejemplo, 08:00): ");
                    String hora = sc.nextLine().trim();

                    Medicamento m = new MedicamentoRecetado(nombre, dosis);
                    Recordatorio r = new Recordatorio(hora, m);

                    // Registrar recordatorio como notificable también
                    gestorNotificaciones.registrar(r);

                    paciente.agregarRecordatorio(r);
                    gestor.agregarMedicamento(m);

                    // Enviar notificación global
                    gestorNotificaciones.enviarNotificacion(
                        "Se agregó el medicamento " + m.getNombre() +
                        " (" + m.getDosis() + ") para las " + hora
                    );

                    System.out.println("✅ Medicamento agregado correctamente.");
                }

                case 2 -> {
                    System.out.println("\n--- LISTA DE MEDICAMENTOS ---");
                    gestor.mostrarMedicamentos();
                }

                case 3 -> {
                    System.out.println("\n--- RECORDATORIOS DEL PACIENTE ---");
                    paciente.mostrarRecordatorios();
                }

                case 4 -> System.out.println(" Función de guardado no implementada.");
                case 5 -> System.out.println(" Función de carga no implementada.");
                case 6 -> System.out.println(" Saliendo del sistema...");
                default -> System.out.println(" Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6);

        sc.close();
    }
}
