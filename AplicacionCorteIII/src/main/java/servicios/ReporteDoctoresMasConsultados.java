package servicios;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.util.Map;

public class ReporteDoctoresMasConsultados extends ReportePDF {

    public ReporteDoctoresMasConsultados(String rutaArchivo) {
        super("REPORTE DE DOCTORES MÁS CONSULTADOS", rutaArchivo);
    }

    @Override
    protected void agregarContenido(Document document) {
        document.add(new Paragraph("Top de Doctores según el número total de consultas en el sistema."));
        document.add(new Paragraph("\n"));
        
        Map<String, Integer> conteoDoctores = estadisticas.getConteoDoctoresConsultados();

        if (conteoDoctores.isEmpty()) {
            document.add(new Paragraph("No se encontraron registros de consultas de doctores."));
            return;
        }

        String[] headers = {"Doctor", "N° Consultas"};
        Table tabla = crearTabla(headers, conteoDoctores);
        document.add(tabla);
    }
}