package modelo;

import java.util.*;

// Singleton para gestionar las estadísticas globales del sistema
public class Estadisticas {
    private static Estadisticas instance;
    
    // Constante que fija el costo del servicio. ¡La lógica de negocio reside aquí!
    private static final double COSTO_RECORDATORIO = 5000.0; 

    // Pacientes inicializados (necesarios para el UI)
    private Paciente paciente1;
    private Paciente paciente2;

    private Map<Paciente, Integer> recordatoriosPorPaciente;
    private Map<Paciente, Double> gastoPorPaciente;
    private Map<Paciente, List<String>> doctoresConsultadosPorPaciente;

    // Totales de gasto por rubro
    private double totalGastadoDoctores;
    private double totalGastadoPediatras;
    private double totalGastadoRecordatorios;

    private Estadisticas() {
        recordatoriosPorPaciente = new HashMap<>();
        gastoPorPaciente = new HashMap<>();
        doctoresConsultadosPorPaciente = new HashMap<>();
    }

    // Método para obtener la única instancia del Singleton
    public static Estadisticas getInstance() {
        if (instance == null) {
            instance = new Estadisticas();
        }
        return instance;
    }

    public static double getCostoRecordatorio() {
        return COSTO_RECORDATORIO;
    }

    // Inicializa los mapas de estadísticas para los pacientes
    public void setPacientes(Paciente p1, Paciente p2) {
        this.paciente1 = p1;
        this.paciente2 = p2;
        recordatoriosPorPaciente.putIfAbsent(p1, 0);
        recordatoriosPorPaciente.putIfAbsent(p2, 0);
        gastoPorPaciente.putIfAbsent(p1, 0.0);
        gastoPorPaciente.putIfAbsent(p2, 0.0);
        doctoresConsultadosPorPaciente.putIfAbsent(p1, new ArrayList<>());
        doctoresConsultadosPorPaciente.putIfAbsent(p2, new ArrayList<>());
    }

    public Paciente getPaciente1() { return paciente1; }
    public Paciente getPaciente2() { return paciente2; }

    // Registra un gasto general para un paciente
    public void registrarGasto(Paciente paciente, double monto) {
        gastoPorPaciente.put(paciente, gastoPorPaciente.getOrDefault(paciente, 0.0) + monto);
    }

    // Incrementa el contador de alarmas/recordatorios para un paciente
    public void registrarAlarma(Paciente paciente) {
        recordatoriosPorPaciente.put(paciente, recordatoriosPorPaciente.getOrDefault(paciente, 0) + 1);
    }

    // Registra una consulta médica, incluyendo el gasto asociado
    public void registrarConsulta(Paciente paciente, Doctor doctor) {
        double costo = (doctor instanceof Pediatra) ? 35000 : 30000;
        
        if (doctor instanceof Pediatra) {
            totalGastadoPediatras += costo;
        } else {
            totalGastadoDoctores += costo;
        }

        registrarGasto(paciente, costo);
        doctoresConsultadosPorPaciente.get(paciente).add(doctor.getNombre());
    }

    // REGISTRO CORREGIDO: La GUI llama a este método sin conocer el costo.
    public void registrarRecordatorio(Paciente paciente) {
        totalGastadoRecordatorios += COSTO_RECORDATORIO;
        registrarGasto(paciente, COSTO_RECORDATORIO); // Registra el costo fijo
        registrarAlarma(paciente);                   // Registra el uso de la alarma
    }

    public String mostrarEstadisticas() {
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