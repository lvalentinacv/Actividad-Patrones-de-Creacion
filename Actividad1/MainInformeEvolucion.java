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
// Se agrega el nuevo tipo a la categoria entrada
class EntradaPedidoReporte implements Reporte{
    private String nombre;
    public EntradaPedidoReporte(String nombre){
        this.nombre=nombre;}
    public void procesar() {System.out.println("[Procesando] Categoría: ENTRADA | Tipo: PEDIDO | Archivo: " + nombre);}
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
// Se agrega la nueva categoria mixta

class MixtaFacturaReporte implements Reporte{
    private String nombre;
    public MixtaFacturaReporte(String nombre){
        this.nombre=nombre;}
     public void procesar() {System.out.println("[Procesando] Categoría: MIXTA | Tipo: FACTURA | Archivo: " + nombre);}
}

class MixtaCompraReporte implements Reporte{
    private String nombre;
    public MixtaCompraReporte(String nombre){
        this.nombre=nombre;}
     public void procesar() {System.out.println("[Procesando] Categoría: MIXTA | Tipo: COMPRA | Archivo: " + nombre);}
}

// Interfaz de la factoria abstracta
interface CategoriaReporteFactory{
    Reporte crearFactura(String nombre);
    Reporte crearCompra(String nombre);
    default Reporte crearPedido(String nombre){
        throw new UnsupportedOperationException("El tipo de pedido no es soportado");
    }
}


// Factorias Concretas
class EntradaReporteFactory implements CategoriaReporteFactory{
    
    public Reporte crearFactura(String nombre){return new EntradaFacturaReporte(nombre);}
    
    public Reporte crearCompra(String nombre){return new EntradaCompraReporte(nombre);}

    public Reporte crearPedido(String nombre){return new EntradaPedidoReporte(nombre);}

}

class SalidaReporteFactory implements CategoriaReporteFactory{
    
    public Reporte crearFactura(String nombre){return new SalidaFacturaReporte(nombre);}
    
    public Reporte crearCompra(String nombre){return new SalidaCompraReporte(nombre);}
}

class MixtaReporteFactory implements CategoriaReporteFactory{
    public Reporte crearFactura(String nombre){return new MixtaFacturaReporte(nombre);}
    public Reporte crearCompra(String nombre){return new MixtaCompraReporte(nombre);}

}

//Clase principal 
public class MainInformeEvolucion {
    public static void main(String[] args) {
        List<String> archivos = Arrays.asList(
            "ENT_FAC_001.txt",
            "ENT_COM_001.txt",
            "ENT_PED_001.txt", 
            "SAL_FAC_001.txt",
            "SAL_COM_001.txt",
            "MIX_FAC_001.txt", 
            "MIX_COM_001.txt"
        );

        for (String archivo : archivos) {
            String[] partes = archivo.split("_");
            String cat = partes[0];
            String tipo = partes[1];

            CategoriaReporteFactory factory = null;

            switch (cat) {
                case "ENT": factory = new EntradaReporteFactory(); break;
                case "SAL": factory = new SalidaReporteFactory(); break;
                case "MIX": factory = new MixtaReporteFactory(); break;
            }

            if (factory != null) {
                Reporte reporte = null;
                switch (tipo) {
                    case "FAC": reporte = factory.crearFactura(archivo); break;
                    case "COM": reporte = factory.crearCompra(archivo); break;
                    case "PED": reporte = factory.crearPedido(archivo); break;
                }

                if (reporte != null) {
                    reporte.procesar();
                }
            }
        }
    }
}