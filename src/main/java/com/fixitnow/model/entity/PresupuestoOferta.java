package com.fixitnow.model.entity;

import com.fixitnow.model.enums.EstadoOferta;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "presupuestos_ofertas", uniqueConstraints = {
    @UniqueConstraint(name = "uk_solicitud_profesional", columnNames = {"solicitud_id", "profesional_id"})
})
public class PresupuestoOferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudServicio solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private PerfilProfesional profesional;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "monto_estimado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoEstimado;

    @NotBlank
    @Column(name = "descripcion_propuesta", nullable = false, columnDefinition = "TEXT")
    private String descripcionPropuesta;

    @NotNull
    @Min(1)
    @Column(name = "tiempo_estimado_horas", nullable = false)
    private Integer tiempoEstimadoHoras;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_oferta", nullable = false, length = 20)
    private EstadoOferta estadoOferta = EstadoOferta.PENDIENTE;

    @Column(name = "fecha_oferta", nullable = false, updatable = false)
    private LocalDateTime fechaOferta;

    public PresupuestoOferta() {
        this.estadoOferta = EstadoOferta.PENDIENTE;
        this.fechaOferta = LocalDateTime.now();
    }

    public PresupuestoOferta(SolicitudServicio solicitud, PerfilProfesional profesional, BigDecimal montoEstimado, String descripcionPropuesta, Integer tiempoEstimadoHoras) {
        this.solicitud = solicitud;
        this.profesional = profesional;
        this.montoEstimado = montoEstimado;
        this.descripcionPropuesta = descripcionPropuesta;
        this.tiempoEstimadoHoras = tiempoEstimadoHoras;
        this.estadoOferta = EstadoOferta.PENDIENTE;
        this.fechaOferta = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaOferta == null) {
            this.fechaOferta = LocalDateTime.now();
        }
        if (this.estadoOferta == null) {
            this.estadoOferta = EstadoOferta.PENDIENTE;
        }
    }

    public void aceptar() {
        this.estadoOferta = EstadoOferta.ACEPTADA;
    }

    public void rechazar() {
        this.estadoOferta = EstadoOferta.RECHAZADA;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitudServicio getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudServicio solicitud) {
        this.solicitud = solicitud;
    }

    public PerfilProfesional getProfesional() {
        return profesional;
    }

    public void setProfesional(PerfilProfesional profesional) {
        this.profesional = profesional;
    }

    public BigDecimal getMontoEstimado() {
        return montoEstimado;
    }

    public void setMontoEstimado(BigDecimal montoEstimado) {
        this.montoEstimado = montoEstimado;
    }

    public String getDescripcionPropuesta() {
        return descripcionPropuesta;
    }

    public void setDescripcionPropuesta(String descripcionPropuesta) {
        this.descripcionPropuesta = descripcionPropuesta;
    }

    public Integer getTiempoEstimadoHoras() {
        return tiempoEstimadoHoras;
    }

    public void setTiempoEstimadoHoras(Integer tiempoEstimadoHoras) {
        this.tiempoEstimadoHoras = tiempoEstimadoHoras;
    }

    public EstadoOferta getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(EstadoOferta estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public LocalDateTime getFechaOferta() {
        return fechaOferta;
    }

    public void setFechaOferta(LocalDateTime fechaOferta) {
        this.fechaOferta = fechaOferta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresupuestoOferta that = (PresupuestoOferta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
