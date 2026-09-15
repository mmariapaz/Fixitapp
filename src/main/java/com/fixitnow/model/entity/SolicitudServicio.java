package com.fixitnow.model.entity;

import com.fixitnow.model.enums.EstadoSolicitud;
import com.fixitnow.model.enums.NivelUrgencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "solicitudes_servicio", indexes = {
    @Index(name = "idx_solicitudes_estado", columnList = "estado"),
    @Index(name = "idx_solicitudes_categoria", columnList = "categoria_id")
})
public class SolicitudServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaOficio categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id", nullable = false)
    private Direccion direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_asignado_id")
    private PerfilProfesional profesionalAsignado;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_urgencia", nullable = false, length = 20)
    private NivelUrgencia nivelUrgencia = NivelUrgencia.MEDIA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado = EstadoSolicitud.PUBLICADA;

    @Column(name = "fecha_solicitada")
    private LocalDateTime fechaSolicitada;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresupuestoOferta> ofertas = new ArrayList<>();

    @OneToOne(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ResenaCalificacion resena;

    public SolicitudServicio() {
        this.estado = EstadoSolicitud.PUBLICADA;
        this.nivelUrgencia = NivelUrgencia.MEDIA;
        this.fechaCreacion = LocalDateTime.now();
    }

    public SolicitudServicio(Usuario cliente, CategoriaOficio categoria, Direccion direccion, String titulo, String descripcion, NivelUrgencia nivelUrgencia) {
        this.cliente = cliente;
        this.categoria = categoria;
        this.direccion = direccion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nivelUrgencia = (nivelUrgencia != null) ? nivelUrgencia : NivelUrgencia.MEDIA;
        this.estado = EstadoSolicitud.PUBLICADA;
        this.fechaCreacion = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
        if (this.estado == null) {
            this.estado = EstadoSolicitud.PUBLICADA;
        }
        if (this.nivelUrgencia == null) {
            this.nivelUrgencia = NivelUrgencia.MEDIA;
        }
    }

    /**
     * Asigna un profesional ganador a la solicitud y transiciona el estado a ASIGNADA.
     */
    public void asignarProfesional(PerfilProfesional profesional) {
        this.profesionalAsignado = profesional;
        this.estado = EstadoSolicitud.ASIGNADA;
    }

    /**
     * Valida y ejecuta la transición de la máquina de estados.
     */
    public void avanzarEstado(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado == null) return;

        switch (this.estado) {
            case PUBLICADA -> {
                if (nuevoEstado == EstadoSolicitud.EN_EVALUACION || 
                    nuevoEstado == EstadoSolicitud.ASIGNADA || 
                    nuevoEstado == EstadoSolicitud.CANCELADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde PUBLICADA hacia " + nuevoEstado);
                }
            }
            case EN_EVALUACION -> {
                if (nuevoEstado == EstadoSolicitud.ASIGNADA || nuevoEstado == EstadoSolicitud.CANCELADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde EN_EVALUACION hacia " + nuevoEstado);
                }
            }
            case ASIGNADA -> {
                if (nuevoEstado == EstadoSolicitud.EN_CAMINO || nuevoEstado == EstadoSolicitud.CANCELADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde ASIGNADA hacia " + nuevoEstado);
                }
            }
            case EN_CAMINO -> {
                if (nuevoEstado == EstadoSolicitud.EN_PROCESO || nuevoEstado == EstadoSolicitud.CANCELADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde EN_CAMINO hacia " + nuevoEstado);
                }
            }
            case EN_PROCESO -> {
                if (nuevoEstado == EstadoSolicitud.FINALIZADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde EN_PROCESO hacia " + nuevoEstado);
                }
            }
            case FINALIZADA -> {
                if (nuevoEstado == EstadoSolicitud.CALIFICADA) {
                    this.estado = nuevoEstado;
                } else {
                    throw new IllegalStateException("Transición inválida desde FINALIZADA hacia " + nuevoEstado);
                }
            }
            case CALIFICADA, CANCELADA -> 
                throw new IllegalStateException("El servicio ya se encuentra en estado terminal: " + this.estado);
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public CategoriaOficio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaOficio categoria) {
        this.categoria = categoria;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public PerfilProfesional getProfesionalAsignado() {
        return profesionalAsignado;
    }

    public void setProfesionalAsignado(PerfilProfesional profesionalAsignado) {
        this.profesionalAsignado = profesionalAsignado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public NivelUrgencia getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitada() {
        return fechaSolicitada;
    }

    public void setFechaSolicitada(LocalDateTime fechaSolicitada) {
        this.fechaSolicitada = fechaSolicitada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<PresupuestoOferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<PresupuestoOferta> ofertas) {
        this.ofertas = ofertas;
    }

    public ResenaCalificacion getResena() {
        return resena;
    }

    public void setResena(ResenaCalificacion resena) {
        this.resena = resena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudServicio that = (SolicitudServicio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
