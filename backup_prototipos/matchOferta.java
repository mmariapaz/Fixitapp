// MatchOferta.java
import java.time.LocalDateTime;

public class MatchOferta {
    private Long id;
    private Solicitud solicitud;
    private Trabajador trabajador;
    private EstadoMatch estado;
    private LocalDateTime fechaCreacion;

    public MatchOferta() {
        this.estado = EstadoMatch.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    public MatchOferta(Long id, Solicitud solicitud, Trabajador trabajador) {
        this.id = id;
        this.solicitud = solicitud;
        this.trabajador = trabajador;
        this.estado = EstadoMatch.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; }

    public Trabajador getTrabajador() { return trabajador; }
    public void setTrabajador(Trabajador trabajador) { this.trabajador = trabajador; }

    public EstadoMatch getEstado() { return estado; }
    public void setEstado(EstadoMatch estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
