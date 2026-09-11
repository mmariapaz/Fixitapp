// Solicitud.java
import java.time.LocalDateTime;

public class Solicitud {
    private Long id;
    private String categoria; // Ej: "Electricidad"
    private Double latitud;
    private Double longitud;
    private LocalDateTime fechaCreacion;

    public Solicitud() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Solicitud(Long id, String categoria, Double latitud, Double longitud) {
        this.id = id;
        this.categoria = categoria;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
