package com.fixitnow.model.entity;

import com.fixitnow.model.enums.RolNombre;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private RolNombre nombre;

    public Rol() {
    }

    public Rol(RolNombre nombre) {
        this.nombre = nombre;
    }

    public Rol(Long id, RolNombre nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolNombre getNombre() {
        return nombre;
    }

    public void setNombre(RolNombre nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(id, rol.id) || nombre == rol.nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
