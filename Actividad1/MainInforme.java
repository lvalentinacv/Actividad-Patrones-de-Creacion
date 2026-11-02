import java.util.Arrays;
import java.util.List;
// Interfaz Común
interface Reporte{
    void procesar();
}
// Clases Concretas de Informes
class EntradaFacturaReporte implements Reporte{
    private String nombre;
    public EntradaFacturaReporte(String nombre){
        this.nombre=nombre;}
    public void procesar() {System.out.println("[Procesando] Categoría: ENTRADA | Tipo: FACTURA | Archivo: " + nombre);}
}

class EntradaCompraReporte implements Reporte{
    private String nombre;
    public EntradaCompraReporte(String nombre){
        this.nombre=nombre;}
    public void procesar() {System.out.println("[Procesando] Categoría: ENTRADA | Tipo: COMPRA | Archivo: " + nombre);}
}

class SalidaFacturaReporte implements Reporte{
    private String nombre;
    public SalidaFacturaReporte(String nombre){
        this.nombre=nombre;}
     public void procesar() {System.out.println("[Procesando] Categoría: SALIDA | Tipo: FACTURA | Archivo: " + nombre);}
}

class SalidaCompraReporte implements Reporte{
    private String nombre;
    public SalidaCompraReporte(String nombre){
        this.nombre=nombre;}
     public void procesar() {System.out.println("[Procesando] Categoría: SALIDA | Tipo: COMPRA | Archivo: " + nombre);}
}

// Interfaz de la factoria abstracta
interface CategoriaReporteFactory{
    Reporte crearFactura(String nombre);
    Reporte crearCompra(String nombre);
}

// Factorias Concretas
class EntradaReporteFactory implements CategoriaReporteFactory{
    
    public Reporte crearFactura(String nombre){return new EntradaFacturaReporte(nombre);}
    
    public Reporte crearCompra(String nombre){return new EntradaCompraReporte(nombre);}
}

class SalidaReporteFactory implements CategoriaReporteFactory{
    
    public Reporte crearFactura(String nombre){return new SalidaFacturaReporte(nombre);}
    
    public Reporte crearCompra(String nombre){return new SalidaCompraReporte(nombre);}
}

//Clase principal 
public class MainInforme {
    public static void main(String[] args) {
        List<String> archivos = Arrays.asList(
            "ENT_FAC_001.txt",
            "ENT_COM_001.txt",
            "SAL_FAC_001.txt",
            "SAL_COM_001.txt"
        );

        for (String archivo : archivos) {
            String[] partes = archivo.split("_");
            String cat = partes[0];
            String tipo = partes[1];

            CategoriaReporteFactory factory = null;

            if (cat.equals("ENT")) {
                factory = new EntradaReporteFactory();
            } else if (cat.equals("SAL")) {
                factory = new SalidaReporteFactory();
            }

            if (factory != null) {
                Reporte reporte = null;
                if (tipo.equals("FAC")) {
                    reporte = factory.crearFactura(archivo);
                } else if (tipo.equals("COM")) {
                    reporte = factory.crearCompra(archivo);
                }

                if (reporte != null) {
                    reporte.procesar();
                }
            }
        }
    }
}