package servicios;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.util.Map;

public class ReportePacientesMasConsulta extends ReportePDF {

    public ReportePacientesMasConsulta(String rutaArchivo) {
        super("REPORTE DE PACIENTES CON MAYOR GASTO", rutaArchivo);
    }

    @Override
    protected void agregarContenido(Document document) {
        document.add(new Paragraph("Top de Pacientes según el gasto total acumulado (Consultas + Recordatorios)."));
        document.add(new Paragraph("\n"));
        
        Map<String, Integer> gastoPorPaciente = estadisticas.getConteoGastoPorPaciente();

        if (gastoPorPaciente.isEmpty()) {
            document.add(new Paragraph("No se encontraron registros de gastos en pacientes."));
            return;
        }

        String[] headers = {"Paciente", "Gasto Total (COP)"};
        Table tabla = crearTabla(headers, gastoPorPaciente);
        document.add(tabla);
    }
}