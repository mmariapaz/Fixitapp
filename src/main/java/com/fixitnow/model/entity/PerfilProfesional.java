package com.fixitnow.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perfiles_profesionales")
public class PerfilProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "matricula_licencia", length = 100)
    private String matriculaLicencia;

    @Column(name = "descripcion_experiencia", columnDefinition = "TEXT")
    private String descripcionExperiencia;

    @Column(name = "latitud_base", nullable = false)
    private Double latitudBase;

    @Column(name = "longitud_base", nullable = false)
    private Double longitudBase;

    @Column(name = "radio_cobertura_km", nullable = false)
    private Double radioCoberturaKm = 10.0;

    @Column(name = "calificacion_promedio", nullable = false)
    private Double calificacionPromedio = 0.0;

    @Column(name = "total_resenas", nullable = false)
    private Integer totalResenas = 0;

    @Column(nullable = false)
    private Boolean disponible = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "profesional_categorias",
        joinColumns = @JoinColumn(name = "profesional_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoriaOficio> especialidades = new HashSet<>();

    public PerfilProfesional() {
        this.radioCoberturaKm = 10.0;
        this.calificacionPromedio = 0.0;
        this.totalResenas = 0;
        this.disponible = true;
    }

    public PerfilProfesional(Usuario usuario, Double latitudBase, Double longitudBase, Double radioCoberturaKm) {
        this.usuario = usuario;
        this.latitudBase = latitudBase;
        this.longitudBase = longitudBase;
        this.radioCoberturaKm = (radioCoberturaKm != null && radioCoberturaKm > 0) ? radioCoberturaKm : 10.0;
        this.calificacionPromedio = 0.0;
        this.totalResenas = 0;
        this.disponible = true;
    }

    /**
     * Recalcula el promedio acumulado de calificaciones con cada nueva reseña.
     */
    public void actualizarCalificacion(int nuevoPuntaje) {
        if (this.totalResenas == null) this.totalResenas = 0;
        if (this.calificacionPromedio == null) this.calificacionPromedio = 0.0;

        double totalPuntosPrevios = this.calificacionPromedio * this.totalResenas;
        this.totalResenas++;
        double nuevoPromedio = (totalPuntosPrevios + nuevoPuntaje) / this.totalResenas;
        this.calificacionPromedio = Math.round(nuevoPromedio * 100.0) / 100.0;
    }

    /**
     * Verifica si una coordenada dada se encuentra dentro del radio de cobertura del profesional
     * utilizando la fórmula de Haversine.
     */
    public boolean estaEnRadio(Double latDestino, Double lonDestino) {
        if (this.latitudBase == null || this.longitudBase == null || latDestino == null || lonDestino == null) {
            return false;
        }

        final double RADIO_TIERRA_KM = 6371.0;
        double dLat = Math.toRadians(latDestino - this.latitudBase);
        double dLon = Math.toRadians(lonDestino - this.longitudBase);

        double lat1Rad = Math.toRadians(this.latitudBase);
        double lat2Rad = Math.toRadians(latDestino);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distancia = RADIO_TIERRA_KM * c;
        return distancia <= this.radioCoberturaKm;
    }

    public void agregarEspecialidad(CategoriaOficio categoria) {
        this.especialidades.add(categoria);
    }

    public void removerEspecialidad(CategoriaOficio categoria) {
        this.especialidades.remove(categoria);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMatriculaLicencia() {
        return matriculaLicencia;
    }

    public void setMatriculaLicencia(String matriculaLicencia) {
        this.matriculaLicencia = matriculaLicencia;
    }

    public String getDescripcionExperiencia() {
        return descripcionExperiencia;
    }

    public void setDescripcionExperiencia(String descripcionExperiencia) {
        this.descripcionExperiencia = descripcionExperiencia;
    }

    public Double getLatitudBase() {
        return latitudBase;
    }

    public void setLatitudBase(Double latitudBase) {
        this.latitudBase = latitudBase;
    }

    public Double getLongitudBase() {
        return longitudBase;
    }

    public void setLongitudBase(Double longitudBase) {
        this.longitudBase = longitudBase;
    }

    public Double getRadioCoberturaKm() {
        return radioCoberturaKm;
    }

    public void setRadioCoberturaKm(Double radioCoberturaKm) {
        this.radioCoberturaKm = radioCoberturaKm;
    }

    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public Integer getTotalResenas() {
        return totalResenas;
    }

    public void setTotalResenas(Integer totalResenas) {
        this.totalResenas = totalResenas;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Set<CategoriaOficio> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<CategoriaOficio> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilProfesional that = (PerfilProfesional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
