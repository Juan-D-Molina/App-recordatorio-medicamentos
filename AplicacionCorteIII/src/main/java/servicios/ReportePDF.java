package servicios;

import modelo.Estadisticas;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import com.itextpdf.layout.element.Image; // 🚨 Importación para imagen
import com.itextpdf.io.image.ImageDataFactory; // 🚨 Importación para crear la data de la imagen
import com.itextpdf.io.image.ImageData; // 🚨 Importación para la data de la imagen
import com.itextpdf.kernel.geom.PageSize; // 🚨 Importación para obtener dimensiones de la página

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL; // Necesario para cargar recursos
import java.util.Map;
import java.util.Comparator; 

/**
 * Clase base abstracta para la generación de reportes PDF usando iText.
 */
public abstract class ReportePDF {
    
    protected Estadisticas estadisticas = Estadisticas.getInstance();
    protected String tituloReporte;
    protected String rutaArchivo;

    // 🚨 Nueva constante para la ruta del icono
    private static final String RUTA_ICONO_REPORTE = "iconos/logo_mini.png"; // Asegúrate de que esta ruta sea correcta
    private static final int ICONO_ANCHO = 40;
    private static final int ICONO_ALTO = 40;

    public ReportePDF(String tituloReporte, String rutaArchivo) {
        this.tituloReporte = tituloReporte;
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Intenta cargar una imagen como recurso del proyecto y la escala.
     */
    private Image cargarIcono() {
        try {
            URL imgURL = getClass().getClassLoader().getResource(RUTA_ICONO_REPORTE);
            
            if (imgURL != null) {
                ImageData data = ImageDataFactory.create(imgURL);
                Image img = new Image(data);
                img.setWidth(ICONO_ANCHO);
                img.setHeight(ICONO_ALTO);
                return img;
            } else {
                System.err.println("❌ Recurso de imagen para el PDF no encontrado: " + RUTA_ICONO_REPORTE);
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el icono para el PDF: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Agrega el icono en la parte superior izquierda del documento.
     */
    private void agregarIcono(Document document) {
        Image img = cargarIcono();
        if (img != null) {
            // Posicionamiento
            // Obtenemos el tamaño de la página para calcular la posición
            PageSize pageSize = document.getPdfDocument().getDefaultPageSize();
            float margin = document.getLeftMargin();
            
            // Posición X: Margen izquierdo + un pequeño offset (ej. 5)
            float x = margin + 5; 
            
            // Posición Y: Altura de la página - Margen superior - Altura del icono - un pequeño offset (ej. 5)
            float y = pageSize.getHeight() - document.getTopMargin() - ICONO_ALTO - 5; 
            
            img.setFixedPosition(x, y);
            document.add(img);
        }
    }


    /**
     * Implementación del método de generación de PDF.
     */
    public void generar() {
        try {
            // Asegura que la ruta termine en .pdf
            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                rutaArchivo += ".pdf";
            }
            
            // Creación de la carpeta si no existe
            File archivo = new File(rutaArchivo);
            File directorio = archivo.getParentFile();

            // Si el directorio no existe, intenta crearlo
            if (directorio != null && !directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("📂 Directorio '" + directorio.getName() + "' creado exitosamente.");
                } else {
                    System.err.println("❌ Error: No se pudo crear el directorio para guardar el reporte.");
                    return; // Detener si no se puede crear la carpeta
                }
            }

            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            // Creamos el documento con márgenes amplios para dejar espacio al icono
            Document document = new Document(pdf); 
            document.setMargins(60, 36, 36, 36); // Ajuste de margen superior (60)
            
            // 🚨 Llamada al nuevo método para agregar el icono
            agregarIcono(document); 

            // --- Encabezado General ---
            document.add(new Paragraph(tituloReporte)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18)
                    .setBold());
            
            document.add(new Paragraph("Fecha de Generación: " + java.time.LocalDate.now().toString()).setFontSize(10).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            // --- Contenido específico (abstracto) ---
            agregarContenido(document);

            document.close();
            System.out.println("✅ Reporte '" + tituloReporte + "' generado en: " + rutaArchivo);

        } catch (FileNotFoundException e) {
            System.err.println("Error al generar el PDF: Archivo no encontrado. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al generar el PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected abstract void agregarContenido(Document document);

    protected Table crearTabla(String[] headers, Map<String, Integer> data) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{3f, 1f})); 
        table.setWidth(UnitValue.createPercentValue(80)); 
        table.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);

        // Encabezados
        table.addHeaderCell(new Paragraph(headers[0]).setBold());
        table.addHeaderCell(new Paragraph(headers[1]).setBold());

        // Datos: ordenados por valor descendente
        data.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
            .forEach(entry -> {
                table.addCell(entry.getKey());
                table.addCell(new Paragraph(String.valueOf(entry.getValue()))); 
            });

        return table;
    }
}