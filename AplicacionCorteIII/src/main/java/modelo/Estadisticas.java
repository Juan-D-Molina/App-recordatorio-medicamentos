package modelo;

import java.util.*;

// Singleton para gestionar las estadísticas globales del sistema
public class Estadisticas {
    private static Estadisticas instance;
    
    private static final double COSTO_RECORDATORIO = 5000.0; 

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

    // --- MÉTODOS DE REGISTRO ---

    public static double getCostoRecordatorio() {
        return COSTO_RECORDATORIO;
    }

    public void registrarPaciente(Paciente p) {
        recordatoriosPorPaciente.putIfAbsent(p, 0);
        gastoPorPaciente.putIfAbsent(p, 0.0);
        doctoresConsultadosPorPaciente.putIfAbsent(p, new ArrayList<>());
    }

    public void registrarGasto(Paciente paciente, double monto) {
        gastoPorPaciente.put(paciente, gastoPorPaciente.getOrDefault(paciente, 0.0) + monto);
    }

    public void registrarAlarma(Paciente paciente) {
        recordatoriosPorPaciente.put(paciente, recordatoriosPorPaciente.getOrDefault(paciente, 0) + 1);
    }

    public void registrarConsulta(Paciente paciente, Doctor doctor) {
        // Usa el valor real de la consulta del doctor
        double costo = doctor.getValorConsulta();
        
        if (doctor instanceof Pediatra) {
            totalGastadoPediatras += costo;
        } else {
            totalGastadoDoctores += costo;
        }

        registrarGasto(paciente, costo);
        doctoresConsultadosPorPaciente.putIfAbsent(paciente, new ArrayList<>());
        doctoresConsultadosPorPaciente.get(paciente).add(doctor.getNombre());
    }

    public void registrarRecordatorio(Paciente paciente) {
        totalGastadoRecordatorios += COSTO_RECORDATORIO;
        registrarGasto(paciente, COSTO_RECORDATORIO); 
        registrarAlarma(paciente);
    }
    
    // --- MÉTODOS DE CONSULTA (GETTERS) ---
    
    public int getRecordatoriosProgramados(Paciente paciente) {
        return recordatoriosPorPaciente.getOrDefault(paciente, 0);
    }
    
    public int getConsultasRealizadas(Paciente paciente) {
        return doctoresConsultadosPorPaciente.getOrDefault(paciente, new ArrayList<>()).size();
    }
    
    public double getCostoTotal(Paciente paciente) {
        return gastoPorPaciente.getOrDefault(paciente, 0.0);
    }
    
    /**
     * Devuelve el doctor más consultado por un paciente.
     */
    public Doctor getDoctorMasConsultado(Paciente paciente) {
        List<String> doctores = doctoresConsultadosPorPaciente.get(paciente);
        if (doctores == null || doctores.isEmpty()) {
            return null;
        }
        
        Map<String, Integer> conteo = new HashMap<>();
        for (String nombre : doctores) {
            conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
        }
        
        String nombreMasConsultado = conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        
        if (nombreMasConsultado != null) {
            // Devuelve un Doctor simple con el nombre del más consultado
            // Nota: Este Doctor no tendrá valorConsulta real, solo nombre/id.
            return new Doctor(nombreMasConsultado, "ID_Ficticio"); 
        }
        return null;
    }
    
    // --- MÉTODOS DE REPORTE PDF (NUEVOS) ---

    /**
     * Devuelve el conteo de cuántas veces fue consultado cada doctor en el sistema.
     * @return Map<NombreDoctor, Conteo>
     */
    public Map<String, Integer> getConteoDoctoresConsultados() {
        Map<String, Integer> conteoGlobal = new HashMap<>();
        
        doctoresConsultadosPorPaciente.values().stream()
            .flatMap(List::stream) // Aplanar la lista de listas de nombres
            .forEach(nombreDoctor -> conteoGlobal.put(
                nombreDoctor, 
                conteoGlobal.getOrDefault(nombreDoctor, 0) + 1));
        
        return conteoGlobal;
    }

    /**
     * Devuelve el gasto total (redondeado a entero) por cada paciente.
     * @return Map<NombrePaciente, GastoTotalEntero>
     */
    public Map<String, Integer> getConteoGastoPorPaciente() {
        Map<String, Integer> gastoConteo = new HashMap<>();
        
        gastoPorPaciente.forEach((paciente, monto) -> 
            gastoConteo.put(paciente.getNombre(), (int) Math.round(monto)));
            
        return gastoConteo;
    }

    /**
     * Devuelve la cantidad de recordatorios programados por cada paciente.
     * @return Map<NombrePaciente, N° Recordatorios>
     */
    public Map<String, Integer> getConteoRecordatoriosPorPaciente() {
        Map<String, Integer> recordatorioConteo = new HashMap<>();
        
        recordatoriosPorPaciente.forEach((paciente, cantidad) -> 
            recordatorioConteo.put(paciente.getNombre(), cantidad));
            
        return recordatorioConteo;
    }
    
    // ... (El método mostrarEstadisticas() se mantiene sin cambios)
}