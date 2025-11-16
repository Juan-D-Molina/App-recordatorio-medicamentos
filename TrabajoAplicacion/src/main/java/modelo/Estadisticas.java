package modelo;

import java.util.*;

// Singleton para gestionar las estadísticas globales del sistema
public class Estadisticas {
    private static Estadisticas instance;
    
    private static final double COSTO_RECORDATORIO = 5000.0; 

    // Los pacientes fijos se eliminan
    // private Paciente paciente1;
    // private Paciente paciente2;

    private Map<Paciente, Integer> recordatoriosPorPaciente;
    private Map<Paciente, Double> gastoPorPaciente;
    private Map<Paciente, List<String>> doctoresConsultadosPorPaciente;

    private double totalGastadoDoctores;
    private double totalGastadoPediatras;
    private double totalGastadoRecordatorios;

    private Estadisticas() {
        recordatoriosPorPaciente = new HashMap<>();
        gastoPorPaciente = new HashMap<>();
        doctoresConsultadosPorPaciente = new HashMap<>();
    }

    public static Estadisticas getInstance() {
        if (instance == null) {
            instance = new Estadisticas();
        }
        return instance;
    }

    public static double getCostoRecordatorio() {
        return COSTO_RECORDATORIO;
    }

    // Se elimina setPacientes(p1, p2)
    
    // Nuevo método para registrar un paciente en las estadísticas
    public void registrarPaciente(Paciente p) {
        recordatoriosPorPaciente.putIfAbsent(p, 0);
        gastoPorPaciente.putIfAbsent(p, 0.0);
        doctoresConsultadosPorPaciente.putIfAbsent(p, new ArrayList<>());
    }

    // Se eliminan getPaciente1() y getPaciente2()

    public void registrarGasto(Paciente paciente, double monto) {
        gastoPorPaciente.put(paciente, gastoPorPaciente.getOrDefault(paciente, 0.0) + monto);
    }

    public void registrarAlarma(Paciente paciente) {
        recordatoriosPorPaciente.put(paciente, recordatoriosPorPaciente.getOrDefault(paciente, 0) + 1);
    }

    public void registrarConsulta(Paciente paciente, Doctor doctor) {
        double costo = (doctor instanceof Pediatra) ? 35000 : 30000;
        
        if (doctor instanceof Pediatra) {
            totalGastadoPediatras += costo;
        } else {
            totalGastadoDoctores += costo;
        }

        registrarGasto(paciente, costo);
        // Asegura que el paciente esté en el mapa antes de añadir doctores
        doctoresConsultadosPorPaciente.putIfAbsent(paciente, new ArrayList<>());
        doctoresConsultadosPorPaciente.get(paciente).add(doctor.getNombre());
    }

    public void registrarRecordatorio(Paciente paciente) {
        totalGastadoRecordatorios += COSTO_RECORDATORIO;
        registrarGasto(paciente, COSTO_RECORDATORIO); 
        registrarAlarma(paciente);
    }

    public String mostrarEstadisticas() {
        // (El resto del método mostrarEstadisticas sigue igual)
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADÍSTICAS GENERALES\n\n");

        sb.append("Gasto Global por Rubro:\n");
        sb.append(String.format(" - Total gastado en Doctores: $%,.0f\n", totalGastadoDoctores));
        sb.append(String.format(" - Total gastado en Pediatras: $%,.0f\n", totalGastadoPediatras));
        sb.append(String.format(" - Total gastado en Recordatorios: $%,.0f\n", totalGastadoRecordatorios));

        sb.append("\nResumen por Paciente:\n");
        gastoPorPaciente.forEach((paciente, monto) -> {
            sb.append(String.format(" - %s: $%,.0f (Gasto Total)\n", paciente.getNombre(), monto));
        });

        sb.append("\nRecordatorios (Alarmas) Activados por Paciente:\n");
        recordatoriosPorPaciente.forEach((paciente, cantidad) -> {
            sb.append(" - ").append(paciente.getNombre()).append(": ").append(cantidad).append(" recordatorio(s)\n");
        });

        sb.append("\nDoctores Consultados por Paciente:\n");
        doctoresConsultadosPorPaciente.forEach((paciente, doctores) -> {
            String listaDoctores = doctores.isEmpty() ? "Ninguno" : String.join(", ", doctores);
            sb.append(" - ").append(paciente.getNombre()).append(": ").append(listaDoctores).append("\n");
        });

        return sb.toString();
    }
}