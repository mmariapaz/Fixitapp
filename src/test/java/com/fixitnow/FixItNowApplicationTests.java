package com.fixitnow;

import com.fixitnow.model.entity.*;
import com.fixitnow.model.enums.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FixItNowApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("El contexto de Spring Boot carga correctamente")
    void contextLoads() {
        assertNotNull(entityManager);
    }

    @Test
    @DisplayName("Las entidades JPA se persisten y relacionan correctamente")
    void testPersistAndRetrieveEntities() {
        // 1. Obtener Roles precargados por data.sql
        Rol rolCliente = entityManager.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre", Rol.class)
                .setParameter("nombre", RolNombre.ROLE_CLIENTE)
                .getSingleResult();
        Rol rolProfesional = entityManager.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre", Rol.class)
                .setParameter("nombre", RolNombre.ROLE_PROFESIONAL)
                .getSingleResult();

        // 2. Usuarios
        Usuario cliente = new Usuario("Juan", "Perez", "juan@fixitnow.com", "hash123", "+5491100001111", "35123456");
        cliente.agregarRol(rolCliente);
        entityManager.persist(cliente);

        Usuario profesionalUser = new Usuario("Carlos", "Gomez", "carlos@fixitnow.com", "hash456", "+5491122223333", "38987654");
        profesionalUser.agregarRol(rolProfesional);
        entityManager.persist(profesionalUser);

        // 3. Perfil Profesional y Especialidades (usando categoría de data.sql)
        CategoriaOficio catPlomeria = entityManager.createQuery("SELECT c FROM CategoriaOficio c WHERE c.nombre = :nombre", CategoriaOficio.class)
                .setParameter("nombre", "Plomería")
                .getSingleResult();

        PerfilProfesional perfil = new PerfilProfesional(profesionalUser, -34.6037, -58.3816, 15.0); // Obelisco CABA
        perfil.setMatriculaLicencia("MAT-9988");
        perfil.agregarEspecialidad(catPlomeria);
        entityManager.persist(perfil);

        // 4. Dirección del Cliente
        Direccion dir = new Direccion(cliente, "Av. Corrientes", "1234", "CABA", -34.6040, -58.3820);
        entityManager.persist(dir);

        // 5. Solicitud de Servicio
        SolicitudServicio solicitud = new SolicitudServicio(
                cliente, catPlomeria, dir, "Fuga de agua en cocina", "Caño roto debajo de la mesada", NivelUrgencia.ALTA
        );
        entityManager.persist(solicitud);

        // 6. Oferta de Presupuesto
        PresupuestoOferta oferta = new PresupuestoOferta(
                solicitud, perfil, new BigDecimal("15000.00"), "Incluye repuestos y mano de obra", 3
        );
        entityManager.persist(oferta);

        // 7. Flush y validaciones
        entityManager.flush();

        assertNotNull(cliente.getId());
        assertNotNull(profesionalUser.getId());
        assertNotNull(solicitud.getId());
        assertNotNull(oferta.getId());
        assertEquals(EstadoSolicitud.PUBLICADA, solicitud.getEstado());
        assertEquals(EstadoOferta.PENDIENTE, oferta.getEstadoOferta());

        // Verificar cálculo de Haversine dentro de radio
        assertTrue(perfil.estaEnRadio(dir.getLatitud(), dir.getLongitud()));
    }
}
