// Servicio.java
import java.util.ArrayList;
import java.util.List;

public class Servicio {
    private Long id;
    private String nombre;        // Ej: "Electricidad", "Plomería"
    private String descripcion;   // Ej: "Reparaciones eléctricas del hogar"
    private String iconoUrl;       // URL para el ícono en la app móvil
    
    // Un servicio contiene muchos trabajadores que lo ofrecen
    private List<Trabajador> trabajadores;

    public Servicio() {
        this.trabajadores = new ArrayList<>();
    }

    public Servicio(Long id, String nombre, String descripcion, String iconoUrl) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.iconoUrl = iconoUrl;
        this.trabajadores = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIconoUrl() { return iconoUrl; }
    public void setIconoUrl(String iconoUrl) { this.iconoUrl = iconoUrl; }

    public List<Trabajador> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(List<Trabajador> trabajadores) { this.trabajadores = trabajadores; }

    // Método helper para vincular un trabajador a este servicio
    public void agregarTrabajador(Trabajador trabajador) {
        this.trabajadores.add(trabajador);
    }
}
