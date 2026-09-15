package com.fixitnow.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "resenas_calificaciones")
public class ResenaCalificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id", nullable = false, unique = true)
    private SolicitudServicio solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private PerfilProfesional profesional;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer puntaje;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_resena", nullable = false, updatable = false)
    private LocalDateTime fechaResena;

    public ResenaCalificacion() {
        this.fechaResena = LocalDateTime.now();
    }

    public ResenaCalificacion(SolicitudServicio solicitud, Usuario cliente, PerfilProfesional profesional, Integer puntaje, String comentario) {
        this.solicitud = solicitud;
        this.cliente = cliente;
        this.profesional = profesional;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.fechaResena = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaResena == null) {
            this.fechaResena = LocalDateTime.now();
        }
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public PerfilProfesional getProfesional() {
        return profesional;
    }

    public void setProfesional(PerfilProfesional profesional) {
        this.profesional = profesional;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDateTime fechaResena) {
        this.fechaResena = fechaResena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResenaCalificacion that = (ResenaCalificacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
