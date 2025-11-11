package modelo;

import java.util.HashMap;
import java.util.Map;

public class Estadisticas {
    private static Estadisticas instance;
    private Map<Paciente, Integer> alarmasPorPaciente;
    private Map<Doctor, Integer> consultasPorDoctor;

    private Paciente paciente1;
    private Paciente paciente2;

    private Estadisticas() {
        alarmasPorPaciente = new HashMap<>();
        consultasPorDoctor = new HashMap<>();
    }

    public static Estadisticas getInstance() {
        if (instance == null) instance = new Estadisticas();
        return instance;
    }

    public void setPacientes(Paciente p1, Paciente p2) {
        this.paciente1 = p1;
        this.paciente2 = p2;
    }

    public Paciente getPaciente1() { return paciente1; }
    public Paciente getPaciente2() { return paciente2; }

    public void registrarAlarma(Paciente p) {
        alarmasPorPaciente.put(p, alarmasPorPaciente.getOrDefault(p, 0) + 1);
    }

    public void registrarConsulta(Doctor d) {
        consultasPorDoctor.put(d, consultasPorDoctor.getOrDefault(d, 0) + 1);
    }

    public String mostrarEstadisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alarmas por paciente:\n");
        alarmasPorPaciente.forEach((p, c) -> sb.append(p.getNombre()).append(": ").append(c).append("\n"));
        sb.append("\nConsultas por doctor:\n");
        consultasPorDoctor.forEach((d, c) -> sb.append(d.getNombre()).append(": ").append(c).append("\n"));
        return sb.toString();
    }
}
