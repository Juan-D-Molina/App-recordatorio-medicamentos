package servicios;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.util.Map;

public class ReportePacientesMasAlarmas extends ReportePDF {

    public ReportePacientesMasAlarmas(String rutaArchivo) {
        super("REPORTE DE PACIENTES CON MÁS RECORDATORIOS", rutaArchivo);
    }

    @Override
    protected void agregarContenido(Document document) {
        document.add(new Paragraph("Top de Pacientes según el número total de recordatorios (alarmas) programados."));
        document.add(new Paragraph("\n"));
        
        Map<String, Integer> recordatoriosPorPaciente = estadisticas.getConteoRecordatoriosPorPaciente();

        if (recordatoriosPorPaciente.isEmpty()) {
            document.add(new Paragraph("No se encontraron registros de recordatorios."));
            return;
        }

        String[] headers = {"Paciente", "N° Recordatorios"};
        Table tabla = crearTabla(headers, recordatoriosPorPaciente);
        document.add(tabla);
    }
}