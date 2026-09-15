# **PROYECTO INTEGRADOR: ARREGLAR AHORA** 

**Plataforma Web de Intermediación y Matching Georreferenciado para Servicios de Oficios del Hogar** 

#### **Ficha Técnica del Proyecto** 















**Nombre del Sistema:** FixIt Now 

**Tipo de Sistema:** Plataforma Web Transaccional / Marketplace de Servicios On-Demand **Versión del Documento:** v2.0.0 (Memoria Técnica Integral de Ingeniería de Software) 

**Carrera:** Tecnicatura Superior / Licenciatura en Desarrollo de Software 

**Materia:** Proyecto Integrador / Modelado y Arquitectura de Software 

###### **Integrantes del Equipo:** 

- Bernabéu, Laura 



- Duch, Salvador 



- Torres, María Paz 



###### **Stack Tecnológico Principal:** 

- **Backend:** Java 17/21, Spring Boot 3.x, Spring Data JPA, Spring Security 6 (JWT) **Base de Datos:** Relacional (PostgreSQL / MySQL) 





- **Frontend:** Aplicación de una sola página (SPA - React / HTML5 Semántico + TypeScript/JS) 



- **Documentación de API:** OpenAPI 3.0 / Swagger UI 



- **Herramientas:** Git, GitHub, Maven, Docker 



### **TABLA DE CONTENIDOS** 

##### 1. Introducción <u>y Justificación</u> 

- 1.1. Contexto y Planteamiento del Problema 

- 1.2. Propuesta de Valor y Solución 

- 1.3. Objetivos del Sistema (General y Específicos) 

   - 1.4. Alcance y Límites del Sistema 

2. Metodología <u>de Desarrollo y Gestión del Proyecto</u> 

   - 2.1. Marco Metodológico Híbrido (Scrum + Proceso Unificado) 

   - 2.2.Gestión de Configuración y Control de Versiones 

3. Especifcacióni <u>de requisitos del sistema</u> 

      - 3.1. Identificación y Matriz de Actores 

      - 3.2.Requisitos Funcionales (RF) 

      - 3.3.Requisitos No Funcionales (RNF) 

      - 3.4.Reglas de Negocio (RN) 

   - N. <u>Arquitectura y Patrones de Diseño de Software</u> 

- 4.1. Arquitectura en Capas en Spring Boot 

      - 4.2. Patrones de Diseño Implementados 

      - 4.3.Modelo de Seguridad y Autenticación Stateless (JWT) 

      - 4.4. Algoritmo de Matching Georreferenciado (Haversine) 

- U. <u>Diseño y Modelado de la Base de Datos</u> 5.1. Diagrama Entidad-Relación (DER) 

      - 5.2.Diccionario de Datos Normalizado (3FN) 

      - 5.3.Script DDL de Estructura Relacional (SQL) 

6. Modelado <u>UML del Sistema</u> 

      - 6.1. Diagrama de Casos de Uso General 

      - 6.2.Diagramas de Secuencia Clave (Matching y Presupuestación) 

      - 6.3.Diagrama de Estados del Ciclo de Vida de una Solicitud 

      - 6.4.Diagrama de Clases del Dominio Backend 

7. Especifcacióni <u>de la API RESTful (Endpoints)</u> 

8. Estrategia <u>de Pruebas y Aseguramiento de la Calidad</u> 

9. Conclusiones <u>y Líneas de Trabajo Futuro</u> 

10. <u>Glosario de Términos</u> 

## **1. INTRODUCCIÓN Y JUSTIFICACIÓN** 

#### **1.1. Contexto y Planteamiento del Problema** 

La contratación de servicios de mantenimiento, reparaciones y oficios para el hogar (tales como plomería, electricidad, gas, cerrajería, albañilería y pintura) ha operado históricamente bajo un esquema de alta informalidad, asimetría de información y fricción operativa: 





**Falta de inmediata y geolocalización:** Los usuarios recurren al "boca en boca" oa directorios web estáticos que no informan la disponibilidad real ni la proximidad geográfica del prestador. 

**Incertidumbre en tarifas:** Carencia de presupuestos transparentes previos a la visita, derivando 

en sobreprecios o desacuerdos económicos. 





**Inseguridad y falta de reputación:** Dificultad para validar la identidad, idoneidad y antecedentes de desempeño de los trabajadores que ingresan a los domicilios particulares. 

**Gestión ineficiente para el profesional:** Los trabajadores de oficios pierden tiempo en traslados extensos y carecen de un canal digital formal para captar clientes en su zona de cobertura directa. 

#### **1.2. Propuesta de Valor y Solución** 

**FixIt Now** es concebida como una plataforma web transaccional bajo el modelo de _marketplace on-demand_ (inspirado en la dinámica operativa de plataformas como _PedidosYa_ o _Uber_ , pero especializada en servicios técnicos y de oficios). 

La plataforma interconecta en tiempo real la demanda domiciliaria de un **Cliente** con la oferta calificada de **Profesionales de Oficios** cercanos. Mediante algoritmos de geolocalización, cotización estandarizada, seguimiento de estados en vivo y un sistema bidireccional de valoraciones, FixIt Now profesionaliza el sector, garantiza transparencia tarifaria y optimiza los tiempos de respuesta. 

#### **1.3. Objetivos del Sistema** 



###### **Objetivo General:** 

Diseñar e implementar una plataforma web integral basada en una arquitectura de microservicios/monolito modular en **Java con Spring Boot** y base de datos relacional, que automatice el proceso de solicitud, geolocalización, presupuestación, ejecución y calificación de servicios de oficios para el hogar. 



###### **Objetivos Específicos:** 

1. Desarrollar un motor de matching geográfico que filtre y notifique a profesionales dentro del radio de acción de una solicitud. 

2. Implementar un módulo de gestión de ciclo de vida de pedidos mediante una máquina de estados finita ( _Solicitado_ , _Ofertado_ , _Asignado_ , _En Camino_ , _En Proceso_ , _Finalizado_ , _Calificado_ ). 

3. Proveer un esquema de seguridad robusto con autenticación sin estado mediante **JSON Web Tokens (JWT)** y control de acceso basado en roles ( **RBAC** ). 

- N. Diseñar un modelo de datos en 3ra Forma Normal (3FN) con integridad referencial estricta en PostgreSQL/MySQL. 

- U. Ofrecer una interfaz de usuario responsiva y accesible para clientes, prestadores y administradores. 

#### **1.4.Alcance y Límites del Sistema** 



###### **Dentro del alcance:** 

Registro y autenticación diferenciada (Clientes, Profesionales, Administrador). Configuración de perfil profesional con especialidades, matrícula y radio de cobertura (km). 

















- Publicación de solicitudes de servicio con ubicación georreferenciada, fotos y urgencia. 

- Envío y recepción de presupuestos/ofertas en tiempo real. 

- Aceptación de cotizaciones y asignación formal del trabajo. 

- Seguimiento del estado del servicio paso a paso. 

- Sistema de reseñas y puntuación (1 a 5 estrellas) con recálculo de promedio ponderado. 

- Panel administrativo para moderación de usuarios, alta de categorías y auditoría. 



###### **Límites (versión actual / MVP):** 

El procesamiento de pagos monetarios se asume contra entrega o mediante 



pasarela simulada en esta etapa; la integración con webhooks de 

MercadoPago/Stripe queda definida como trabajo futuro. 

## **2. METODOLOGÍA DE DESARROLLO Y GESTIÓN DEL PROYECTO** 

#### **2.1. Marco Metodológico Híbrido (Scrum + Unified Process)** 

Para combinar la rigurosidad en el modelado técnico con la flexibilidad de entrega continua, se adoptó un marco híbrido: 

1. **Fase de Inicio (Inception):** Definición de la visión, requerimientos clave y matriz de riesgos. 

2. **Fase de Elaboración (Elaboration):** Diseño de la arquitectura base en Java Spring Boot, modelo DER de la base de datos y diagramas UML esenciales. 

3. **Fase de Construcción (Construction):** Implementación iterativa en Sprints de dos semanas bajo Scrum (Autenticación $\rightarrow$ Gestión de Oficios $\rightarrow$ Motor de Matching 

$\rightarrow$ Presupuestación $\rightarrow$ Feedback). 

- N. **Fase de Transición (Transition):** Pruebas integrales de endpoints, optimización de consultas SQL y documentación final de la API. 

#### **2.2. Gestión de Configuración y Control de Versiones** 



**Repositorio Central:** GitHub bajo el flujo de trabajo **GitFlow** ( `main` , `develop` , `feature/*` , 

`bugfix/*` ). 



**Versionado Semántico (SemVer):** Formato `MAJOR.MINOR.PATCH` (v1.0.0 inicial $ 

\rightarrow$ v2.0.0 versión completa con arquitectura en capas y base de datos). 

## **3. ESPECIFICACIÓN DE REQUERIMIENTOS DEL SISTEMA** 

#### **3.1. Identificación y Matriz de Actores** 

|**Actor**|**Tipo**|**Descripción y Responsabilidad**|
|---|---|---|
|**Cliente**<br>**(Solicitante)**|Humano<br>/ Externo|Usuario que publica necesidades de reparación, evalúa<br>cotizaciones de prestadores cercanos, acepta ofertas y califica<br>el trabajo concluido.|
|**Actor**|**Tipo**|**Descripción y Responsabilidad**|
|**Profesional**<br>**(Prestador)**|Humano<br>/ Externo|Especialista en uno o varios oficios que define su radio de<br>trabajo, recibe alertas de solicitudes cercanas, envía<br>presupuestosyactualiza el estado de la labor.|
|**Administrado**<br>**r**|Humano<br>/ Interno|Responsable de la plataforma que gestiona las categorías de<br>oficios, valida credenciales profesionales y audita disputas o<br>reportes.|
|**Motor de**<br>**Matching**|Sistema /<br>Automático|Servicio backend que calcula distancias geodésicas y filtra a los<br>profesionales elegibles ante cada nueva solicitud.|



#### **3.2. Requerimientos Funcionales (RF)** 

###### **Módulo 1: Seguridad y Gestión de Usuarios** 



**RF-01: Registro de Usuarios Diferenciado.** El sistema debe permitir el registro de 

usuarios distinguiendo entre rol `CLIENTE` y rol `PROFESIONAL` . 







**RF-02: Autenticación y Emisión de Tokens.** El sistema debe autenticar usuarios mediante correo y contraseña encriptada (BCrypt), emitiendo un token JWT firmado para sesiones subsecuentes. 

**RF-03: Gestión de Perfil Profesional.** El profesional debe poder configurar sus oficios/especialidades, matrícula profesional, descripción de experiencia y radio de cobertura en kilómetros ($r \in [1, 50]\text{ km}$). 

**RF-04: Gestión de Direcciones del Cliente.** El cliente debe poder almacenar una o más direcciones de servicio, registrando calle, número, localidad y coordenadas geográficas (latitud y longitud). 

###### **Módulo 2: Solicitudes de Servicio y Motor de Matching** 





**RF-05: Publicación de Solicitud.** El cliente debe poder crear una solicitud especificando categoría del oficio, título, descripción detallada, nivel de urgencia ( _BAJA_ , _MEDIA_ , _ALTA/EMERGENCIA_ ), fecha tentativa y dirección de ejecución. 

- **RF-06: Matching y Notificación Geográfica.** Al crearse una solicitud, el sistema debe calcular qué 

profesionales del rubro seleccionado tienen la dirección del cliente dentro de su radio de cobertura y poner a su disposición dicha solicitud. 



**RF-07: Consulta de Solicitudes Disponibles.** El profesional debe poder consultar la lista de solicitudes activas y disponibles dentro de su zona de cobertura geográfica. 

###### **Módulo 3: Cotización, Ofertas y Asignación** 



**RF-08: Emisión de Presupuesto/Oferta.** El profesional debe poder postular una oferta a una solicitud abierta, indicando monto estimado ($), tiempo estimado de resolución 

(horas/días) y una propuesta técnica. 





**RF-09: Comparación y Aceptación de Oferta.** El cliente debe visualizar las ofertas recibidas para su solicitud (con el perfil, precio y reputación de cada profesional) y seleccionar una única oferta ganadora. 

**RF-10: Asignación y Cierre de Ofertas Competidoras.** Al aceptar una oferta, la solicitud debe cambiar a estado `ASIGNADA` y las demás ofertas postuladas deben pasar automáticamente a estado `RECHAZADA` . 

###### **Módulo 4: Ejecución y Ciclo de Vida del Servicio** 





**RF-11: Transición de Estados del Servicio.** El profesional asignado debe registrar el avance del servicio transitando por los estados: `EN_CAMINO` $\rightarrow$ `EN_PROCESO` $\rightarrow$ `FINALIZADA` . 

**RF-12: Cancelación de Solicitud.** Tanto el cliente como el profesional deben poder cancelar el servicio bajo causales justificadas antes de la etapa `EN_PROCESO` . 

###### **Módulo 5: Reputación y Reseñas** 





**RF-13: Calificación del Servicio.** Una vez finalizado el servicio, el cliente debe poder emitir una reseña obligatoria con puntaje del 1 al 5 y comentario descriptivo. 

**RF-14: Recálculo de Promedio de Reputación.** El sistema debe actualizar automáticamente la calificación promedio del profesional tras cada nueva reseña recibida. 

###### **Módulo 6: Administración y Parametrización** 



**RF-15: Gestión de Categorías de Oficios.** El administrador debe poder crear, editar, dar 

de baja lógica y listar las categorías de oficios y sus tarifas de referencia. 



**RF-16: Bloqueo y Moderación.** El administrador debe poder suspender cuentas de clientes o profesionales ante reportes fundados de mal comportamiento. 

#### **3.3. Requerimientos No Funcionales (RNF)** 





**RNF-01 (Rendimiento):** El tiempo de respuesta de las consultas de matching geográfico no debe superar los 500 ms para un universo de 10.000 profesionales registrados. 

**RNF-02 (Seguridad):** Todas las contraseñas deben almacenarse bajo hash irreversible utilizando 

el algoritmo **BCrypt** con factor de costo 10 o superior. Ningún endpoint transaccional debe 

ser accesible sin un token JWT válido. 





**RNF-03 (Escalabilidad y Concurrencia):** La arquitectura backend en Spring Boot debe ser _Stateless_ (sin estado en sesión de servidor), permitiendo escalamiento horizontal detrás de un balanceador de carga. 

**RNF-04 (Integridad de Datos):** La persistencia debe garantizar propiedades **ACID** mediante el uso de transacciones declarativas ( `@Transactional` ) en operaciones críticas (como la asignación 

de presupuestos y cierre de ofertas). 



**RNF-05 (Usabilidad y Responsive Design):** La interfaz web debe adaptarse 

automáticamente a dispositivos móviles (smartphones, tablets) y computadoras de 

escritorio cumpliendo estándares de diseño responsivo. 



**RNF-06 (Disponibilidad):** La plataforma debe contemplar una disponibilidad objetivo del 

99.5% en entornos productivos. 

#### **3.4. Reglas de Negocio (RN)** 









**RN-01 (Unicidad de Postulación):** Un profesional solo puede enviar un único presupuesto activo por cada solicitud de servicio. 

**RN-02 (Exclusividad de Asignación):** Una solicitud de servicio solo puede tener asignado un único profesional a la vez. 

**RN-03 (Inmutabilidad de Reseñas):** Una vez emitida una calificación por un servicio finalizado, esta no puede ser modificada por el cliente para evitar manipulaciones de reputación. 

**RN-04 (Radio de Cobertura Válido):** Un profesional solo puede visualizar y postularse a solicitudes cuya distancia euclidiana/geodésica respecto a su base operativa sea menor o igual a su `radio_cobertura_km` . 

## **4. ARQUITECTURA Y PATRONES DE DISEÑO DE SOFTWARE** 

#### **4.1.Arquitectura en Capas (Layered Architecture) en Spring Boot** 

El sistema adopta una arquitectura desacoplada y modular estándar de la industria, estructurada en cinco capas bien diferenciadas: 

```
graph TD
```

```
subgraph CLIENTE["Capa de Presentación (Frontend / SPA)"]
UI[Navegador Web / Interfaz de Usuario]
```

```
end
```

```
subgraph API_GATEWAY["Capa de Exposición / Controladores"]
REST[REST Controllers / Swagger OpenAPI]
```

```
AUTH_FILTER[JWT Authentication Filter]
```

```
end
```

```
subgraph SERVICE_LAYER["Capa de Lógica de Negocio"]
```

```
SVC_MATCH[MatchingService]
SVC_SOLICITUD[SolicitudService]
SVC_OFERTA[PresupuestoService]
SVC_USER[UsuarioService]
```

```
end
```

```
subgraph DATA_LAYER["Capa de Acceso a Datos (DAO / Repositorios)"]
REPO[Spring Data JPA Repositories]
end
subgraph DATABASE["Capa de Persistencia"]
DB[(Base de Datos Relacional SQL)]
end
UI -->|HTTP Requests + Bearer JWT| AUTH_FILTER
AUTH_FILTER --> REST
REST --> SERVICE_LAYER
SERVICE_LAYER --> DATA_LAYER
DATA_LAYER -->|Hibernate / JDBC| DB
```

1.<sup>**Capa de Controladores (**</sup> @RestController **):** Expone los endpoints RESTful, valida el 

esquema de los DTOs de entrada mediante anotaciones `@Valid` y retorna respuestas con códigos de estado HTTP estandarizados ( `200 OK` , `201 Created` , `400 Bad` 

`Request` , `401 Unauthorized` , `404 Not Found` ). 

2. **Capa de Seguridad (** Spring Security + JWT **):** Filtro interceptor `JwtAuthenticationFilter` que valida la firma criptográfica y expiración del token antes de permitir el acceso al controlador. 

3.<sup>**Capa de Servicios (**</sup> @Service **):** Contiene la lógica de negocio pura, la orquestación del matching, las validaciones de reglas de negocio y la gestión transaccional con `@Transactional` . 

- N. **Capa de Transferencia de Datos (** DTOs & Mappers **):** Aísla las entidades de base de datos de la exposición externa, evitando problemas de recursión JSON y asegurando el encapsulamiento. 

- U. **Capa de Acceso a Datos (** @Repository **):** Interfaces que extienden `JpaRepository<T,` 

`ID>` , aprovechando consultas derivadas por nombre y consultas `@Query` optimizadas en JPQL/SQL nativo. 

**4.2. Patrones de Diseño Implementados** 

**Repository Pattern:** Abstrae las operaciones de persistencia y consultas SQL complejas de la lógica de negocio. 











**Data Transfer Object (DTO) Pattern:** Desacopla las entidades del modelo relacional de los contratos expuestos a la interfaz de usuario. 

- **Strategy Pattern:** Utilizado en el cálculo y ordenamiento del motor de matching (estrategia por 

menor distancia, estrategia por mayor reputación o estrategia por menor precio). **Builder Pattern:** Utilizado para la construcción fluida de objetos de respuesta DTO y solicitudes compuestas. 

**Dependency Injection (IoC):** Provisto de forma nativa por el contenedor de Spring 

Framework para lograr bajo acoplamiento entre componentes. 

#### **4.3. Modelo de Seguridad y Autenticación Stateless (JWT)** 

El sistema opera bajo un esquema **Stateless** : el servidor no almacena sesiones en 

memoria ( `SessionCreationPolicy.STATELESS` ). 

1. El usuario envía sus credenciales al endpoint `/api/v1/auth/login` . 

2. Spring Security verifica el hash BCrypt en la base de datos. 

3. Se genera un token JWT firmado con algoritmo HMAC-SHA256 que encapsula los claims: `sub` 

(email), `role` (roles asignados) y `exp` (tiempo de expiración de 24 horas). 

N. El cliente almacena el token y lo adjunta en la cabecera `Authorization: Bearer <token>` 

en todas las peticiones posteriores. 

#### **4.4. Algoritmo de Matching Georreferenciado** 

Para determinar si un profesional califica para una solicitud sin requerir motores espaciales pesados en el MVP, se implementa en Java la **Fórmula del Semiverseno (Haversine Formula)** , calculando la distancia del gran círculo entre dos puntos geográficos $(\text{lat}_1, \text{lon}_1)$ y $(\text{lat}_2, 

\text{lon}_2)$: 

$$d = 2R \cdot \arcsin\left(\sqrt{\sin^2\left(\frac{\Delta \text{lat}}{2}\right) + \cos(\text{lat}_1)\cos(\text{lat}_2)\sin^2\left(\frac{\Delta \text{lon}}{2}\right)}\right)$$ 

Donde $R = 6371\text{ km}$ (radio medio de la Tierra) y $\Delta \text{lat}, \Delta \text{lon}$ representan la diferencia de coordenadas en radianes. 

Un profesional es considerado candidato elegible si y solo si: 

$$\text{EspecialidadProfesional} = \text{CategoriaSolicitud} \quad \land \quad 

d(\text{UbicacionCliente}, \text{UbicacionProfesional}) \le \text{RadioCoberturaProfesional}$$ 

## **5. DISEÑO Y MODELADO DE LA BASE DE DATOS** 

#### **5.1. Diagrama Entidad-Relación (DER)** 

```
erDiagram
```

```
USUARIOS ||--o{ USUARIO_ROLES : "posee"
ROLES ||--o{ USUARIO_ROLES : "asignado a"
USUARIOS ||--o| PERFILES_PROFESIONALES : "extiende datos si es presta
USUARIOS ||--o{ DIRECCIONES : "registra domicilios"
CATEGORIAS_OFICIOS ||--o{ PROFESIONAL_CATEGORIAS : "clasifica"
PERFILES_PROFESIONALES ||--o{ PROFESIONAL_CATEGORIAS : "atiende"
```

```
USUARIOS ||--o{ SOLICITUDES_SERVICIO : "crea como cliente"
CATEGORIAS_OFICIOS ||--o{ SOLICITUDES_SERVICIO : "clasifica servicio"
DIRECCIONES ||--o{ SOLICITUDES_SERVICIO : "ubica servicio en"
```

```
SOLICITUDES_SERVICIO ||--o{ PRESUPUESTOS_OFERTAS : "recibe cotizacion
PERFILES_PROFESIONALES ||--o{ PRESUPUESTOS_OFERTAS : "postula oferta"
```

```
SOLICITUDES_SERVICIO ||--o| RESENAS_CALIFICACIONES : "genera al concl
USUARIOS ||--o{ RESENAS_CALIFICACIONES : "emite (cliente)"
PERFILES_PROFESIONALES ||--o{ RESENAS_CALIFICACIONES : "recibe (prest
```

```
USUARIOS {
```

```
BIGINT id PK
VARCHAR nombre
VARCHAR apellido
VARCHAR email UK
VARCHAR password_hash
VARCHAR telefono
VARCHAR dni UK
BOOLEAN activo
TIMESTAMP fecha_creacion
}
ROLES {
BIGINT id PK
VARCHAR nombre UK
}
PERFILES_PROFESIONALES
```

```
{ BIGINT id PK
BIGINT usuario_id FK
VARCHAR matricula_licencia
TEXT descripcion_experiencia
```

```
DOUBLE latitud_base
DOUBLE longitud_base
DOUBLE radio_cobertura_km
DOUBLE calificacion_promedio
INT total_resenas
BOOLEAN disponible
}
```

```
CATEGORIAS_OFICIOS
{ BIGINT id PK
VARCHAR nombre UK
TEXT descripcion
VARCHAR icono_url
BOOLEAN activa
}
```

```
DIRECCIONES {
```

```
BIGINT id PK
BIGINT usuario_id FK
VARCHAR calle
VARCHAR numero
VARCHAR piso_depto
VARCHAR ciudad
VARCHAR codigo_postal
DOUBLE latitud
DOUBLE longitud
BOOLEAN es_principal
}
```

```
SOLICITUDES_SERVICIO
{ BIGINT id PK
BIGINT cliente_id FK
BIGINT categoria_id FK
BIGINT direccion_id FK
BIGINT profesional_asignado_id FK
VARCHAR titulo
TEXT descripcion
VARCHAR nivel_urgencia
VARCHAR estado
TIMESTAMP fecha_solicitada
TIMESTAMP fecha_creacion
}
```

```
PRESUPUESTOS_OFERTAS {
```

```
BIGINT id PK
BIGINT solicitud_id FK
BIGINT profesional_id FK
DECIMAL monto_estimado
TEXT descripcion_propuesta
INT tiempo_estimado_horas
VARCHAR estado_oferta
TIMESTAMP fecha_oferta
}
RESENAS_CALIFICACIONES
{ BIGINT id PK
BIGINT solicitud_id FK
BIGINT cliente_id FK
BIGINT profesional_id FK
INT puntaje
TEXT comentario
TIMESTAMP fecha_resena
}
```

#### **5.2. Diccionario de Datos Normalizado (3FN)** 

##### **1. Tabla** 



Almacena las credenciales y datos personales universales de todo usuario registrado. 

|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|---|---|---|---|---|
|`id`|`BIGINT`|NO|PK|Identificador único autoincremental.|
|`nombre`|`VARCHAR(1`<br>`00)`|NO|-|Nombre de pila del usuario.|
|`apellido`|`VARCHAR(1`<br>`00)`|NO|-|Apellido del usuario.|
|`email`|`VARCHAR(1`<br>`50)`|NO|UK|Correo electrónico institucional/personal<br>(Username de login).|
|`password_ha`<br>`sh`|`VARCHAR(2`<br>`55)`|NO|-|Hash criptográfico de contraseña (BCrypt).|
|`telefono`|`VARCHAR(3`<br>`0)`|NO|-|Número de teléfono de contacto y WhatsApp.|
|`dni`|`VARCHAR(2`<br>`0)`|NO|UK|Documento Nacional de Identidad o<br>identificación tributaria.|



|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|---|---|---|---|---|
|`activo`|`BOOLEAN`|NO|-|Flag de habilitación lógica (`TRUE`por defecto).|
|`fecha_creac`<br>`ion`|`TIMESTAMP`|NO|-|Fecha y hora de alta del registro.|



##### **2. Tabla** 



Extensión 1-a-1 de la tabla `usuarios` para aquellos usuarios que prestan servicios técnicos. 

|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|---|---|---|---|---|
|`id`|`BIGINT`|NO|PK|Identificador único del perfil<br>profesional.|
|`usuario_id`|`BIGINT`|NO|FK,<br>UK|Referencia al usuario<br>titular (`usuarios.id`).|
|`matricula_licen`<br>`cia`|`VARCHAR(10`<br>`0)`|<br>SI|-|Número de matrícula profesional o<br>registro habilitante.|
|`descripcion_expe`<br>`riencia`|`TEXT`|SI|-|Resumen curricular de trayectoria y<br>especialidades.|
|`latitud_base`|`DOUBLE`<br>`PRECISION`|NO|-|Latitud geográfica de la base<br>operativa del profesional.|
|`longitud_base`|`DOUBLE`<br>`PRECISION`|NO|-|Longitud geográfica de la base<br>operativa delprofesional.|
|`radio_cobertura`<br>`_km`|`DOUBLE`<br>`PRECISION`|NO|-|Radio máximo de cobertura en<br>kilómetros para recibir ofertas.|
|`calificacion_pro`<br>`medio`|`DOUBLE`<br>`PRECISION`|NO|-|Puntuación promedio acumulada (ej.<br>4.85).|
|`total_resenas`|`INT`|NO|-|Cantidad total de reseñas recibidas.|
|`disponible`|`BOOLEAN`|NO|-|Estado de disponibilidad para tomar<br>nuevos trabajos.|



##### **3.Tabla** 



Registra cada necesidad de servicio publicada por un cliente. 

|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|---|---|---|---|---|
|`id`|`BIGINT`|NO|PK|Identificador único de la solicitud.|
|`cliente_id`|`BIGINT`|NO|FK|Usuario cliente que emite el|



|||||pedido( `usuarios.id`).|
|---|---|---|---|---|
|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|`categoria_id`|`BIGINT`|NO|FK|Rubro u oficio solicitado<br>( `categorias_oficios.id`).|
|`direccion_id`|`BIGINT`|NO|FK|Domicilio donde debe ejecutarse el trabajo.|
|`profesional_a`<br>`signado_id`|<br>`BIGINT`|SI|FK|Profesional cuya oferta fue aceptada<br>( `perfiles_profesionales.id`).|
|`titulo`|`VARCHA`<br>`R(150)`|NO|-|Título resumido de la falla o requerimiento.|
|`descripcion`|`TEXT`|NO|-|Detalle exhaustivo del trabajo a realizar.|
|`nivel_urgenc`<br>`ia`|`VARCHA`<br>`R(20)`|NO|-|Enum:`BAJA`,`MEDIA`,`ALTA`,`EMERGENCIA`.|
|`estado`|`VARCHA`<br>`R(30)`|NO|-|Enum:`PUBLICADA`,`ASIGNADA`,`EN_CAMINO`,<br>`EN_PROCESO`,`FINALIZADA`,`CANCELADA`.|
|`fecha_solici`<br>`tada`|`TIMEST`<br>`AMP`|SI|-|Fecha y hora pactada para la visita técnica.|
|`fecha_creaci`<br>`on`|`TIMEST`<br>`AMP`|NO|-|Marca temporal de publicación de la solicitud.|



##### **4. Tabla** 



Registra las cotizaciones enviadas por los profesionales a una solicitud abierta. 

|**Campo**|**TipoSQL**|**Nulo**|**Clave**|**Descripción**|
|---|---|---|---|---|
|`id`|`BIGINT`|NO|PK|Identificador único de<br>la oferta/presupuesto.|
|`solicitud_id`|`BIGINT`|NO|FK|Solicitud asociada<br>( `solicitudes_servicio.id`).|
|`profesional_id`|`BIGINT`|NO|FK|Prestador que emite la cotización.|
|`monto_estimado`|`DECIMAL(12`<br>`,2)`|NO|-|Valor monetario presupuestado para<br>la labor.|
|`descripcion_propu`<br>`esta`|`TEXT`|NO|-|Detalle de mano de obra y materiales<br>incluidos.|
|`tiempo_estimado_h`<br>`oras`|`INT`|NO|-|Duración estimada de ejecución<br>en horas.|
|`estado_oferta`|`VARCHAR(20`<br>`)`|NO|-|Enum:`PENDIENTE`,`ACEPTADA`,<br>`RECHAZADA`.|



`fecha_oferta TIMESTAMP` NO - Marca temporal del envío del presupuesto. 

#### **5.3. Script DDL de Estructura Relacional (SQL Estándar)** 

```
-- SCRIPT DE DEFINICIÓN DE ESTRUCTURA DDL (PostgreSQL / MySQL compatible)
```

```
-- PROYECTO: FixIt Now
```

```
CREATE TABLE roles (
```

```
id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(50) NOT NULL UNIQUE
);
```

```
INSERT INTO roles (nombre) VALUES ('ROLE_CLIENTE'), ('ROLE_PROFESIONAL'),
```

```
CREATE TABLE usuarios (
```

```
id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
apellido VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL UNIQUE,
password_hash VARCHAR(255) NOT NULL,
telefono VARCHAR(30) NOT NULL,
dni VARCHAR(20) NOT NULL UNIQUE,
activo BOOLEAN NOT NULL DEFAULT TRUE,
fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
```

```
);
```

```
CREATE TABLE usuario_roles
```

```
( usuario_id BIGINT NOT NULL,
rol_id BIGINT NOT NULL,
PRIMARY KEY (usuario_id, rol_id),
CONSTRAINT fk_usuario_roles_user FOREIGN KEY (usuario_id) REFERENCES
CONSTRAINT fk_usuario_roles_rol FOREIGN KEY (rol_id) REFERENCES roles
```

```
);
```

```
CREATE TABLE categorias_oficios
```

```
( id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL UNIQUE,
descripcion TEXT,
icono_url VARCHAR(255),
```

```
activa BOOLEAN NOT NULL DEFAULT TRUE
```

```
);
```

```
CREATE TABLE perfiles_profesionales
```

```
( id BIGSERIAL PRIMARY KEY,
```

```
usuario_id BIGINT NOT NULL UNIQUE,
matricula_licencia VARCHAR(100),
descripcion_experiencia TEXT,
latitud_base DOUBLE PRECISION NOT NULL,
longitud_base DOUBLE PRECISION NOT NULL,
radio_cobertura_km DOUBLE PRECISION NOT NULL DEFAULT 10.0,
calificacion_promedio DOUBLE PRECISION NOT NULL DEFAULT 0.0,
total_resenas INT NOT NULL DEFAULT 0,
disponible BOOLEAN NOT NULL DEFAULT TRUE,
CONSTRAINT fk_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usua
```

```
);
```

```
CREATE TABLE profesional_categorias
```

```
( profesional_id BIGINT NOT NULL,
categoria_id BIGINT NOT NULL,
PRIMARY KEY (profesional_id, categoria_id),
CONSTRAINT fk_prof_cat_prof FOREIGN KEY (profesional_id) REFERENCES p
CONSTRAINT fk_prof_cat_cat FOREIGN KEY (categoria_id) REFERENCES cate
```

```
);
```

```
CREATE TABLE direcciones
( id BIGSERIAL PRIMARY
KEY,
usuario_id BIGINT NOT NULL,
calle VARCHAR(150) NOT NULL,
numero VARCHAR(20) NOT NULL,
piso_depto VARCHAR(30),
ciudad VARCHAR(100) NOT NULL,
codigo_postal VARCHAR(20),
latitud DOUBLE PRECISION NOT NULL,
longitud DOUBLE PRECISION NOT NULL,
es_principal BOOLEAN NOT NULL DEFAULT FALSE,
CONSTRAINT fk_direccion_usuario FOREIGN KEY (usuario_id) REFERENCES u
```

```
);
```

```
CREATE TABLE solicitudes_servicio
( id BIGSERIAL PRIMARY KEY,
cliente_id BIGINT NOT NULL,
categoria_id BIGINT NOT NULL,
direccion_id BIGINT NOT NULL,
profesional_asignado_id BIGINT,
titulo VARCHAR(150) NOT NULL,
descripcion TEXT NOT NULL,
```

```
nivel_urgencia VARCHAR(20) NOT NULL DEFAULT 'MEDIA',
estado VARCHAR(30) NOT NULL DEFAULT 'PUBLICADA',
```

```
fecha_solicitada TIMESTAMP,
```

```
fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_solicitud_cliente FOREIGN KEY (cliente_id) REFERENCES u
CONSTRAINT fk_solicitud_categoria FOREIGN KEY (categoria_id) REFERENC
CONSTRAINT fk_solicitud_direccion FOREIGN KEY (direccion_id) REFERENC
CONSTRAINT fk_solicitud_profesional FOREIGN KEY (profesional_asignado
```

```
);
```

```
CREATE TABLE presupuestos_ofertas
( id BIGSERIAL PRIMARY KEY,
solicitud_id BIGINT NOT NULL,
profesional_id BIGINT NOT NULL,
monto_estimado DECIMAL(12,2) NOT NULL,
descripcion_propuesta TEXT NOT NULL,
tiempo_estimado_horas INT NOT NULL,
estado_oferta VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
fecha_oferta TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_oferta_solicitud FOREIGN KEY (solicitud_id) REFERENCES
CONSTRAINT fk_oferta_profesional FOREIGN KEY (profesional_id) REFEREN
CONSTRAINT uk_solicitud_profesional UNIQUE (solicitud_id, profesional
```

```
);
```

```
CREATE TABLE resenas_calificaciones
```

```
( id BIGSERIAL PRIMARY KEY,
solicitud_id BIGINT NOT NULL UNIQUE,
cliente_id BIGINT NOT NULL,
profesional_id BIGINT NOT NULL,
```

```
puntaje INT NOT NULL CHECK (puntaje >= 1 AND puntaje <= 5),
comentario TEXT,
```

```
fecha_resena TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_resena_solicitud FOREIGN KEY (solicitud_id) REFERENCES
CONSTRAINT fk_resena_cliente FOREIGN KEY (cliente_id) REFERENCES usua
CONSTRAINT fk_resena_profesional FOREIGN KEY (profesional_id) REFEREN
```

```
);
```

```
-- Índices de optimización para consultas geográficas y de estado
```

```
CREATE INDEX idx_solicitudes_estado ON solicitudes_servicio (estado);
CREATE INDEX idx_solicitudes_categoria ON solicitudes_servicio (categoria
CREATE INDEX idx_profesionales_disponible ON perfiles_profesionales (disp
```

## **6. MODELADO UML DEL SISTEMA** 

#### **6.1. Diagrama de Casos de Uso General** 

```
graph LR
subgraph ACTORES
C((Cliente))
P((Profesional))
A((Administrador))
```

```
end
```

```
subgraph SISTEMA_FIXIT_NOW["Plataforma FixIt Now"]
UC1([CU-01: Registrar e Iniciar Sesión])
UC2([CU-02: Publicar Solicitud de Oficio])
UC3([CU-03: Visualizar Ofertas y Asignar])
UC4([CU-04: Calificar Servicio Finalizado])
UC5([CU-05: Configurar Perfil y Radio Cobertura])
UC6([CU-06: Ver Solicitudes Cercanas])
UC7([CU-07: Enviar Presupuesto / Cotización])
UC8([CU-08: Actualizar Estado del Servicio])
UC9([CU-09: Gestionar Categorías de Oficios])
UC10([CU-10: Auditar Usuarios y Reportes])
```

```
end
```

```
C --> UC1
C --> UC2
C --> UC3
C --> UC4
P --> UC1
P --> UC5
P --> UC6
P --> UC7
P --> UC8
A --> UC1
A --> UC9
A --> UC10
```

#### **6.2. Diagramas de Secuencia Clave** 

**Flujo Transaccional: Publicación, Matching, Presupuesto y Asignación** 

```
sequenceDiagram
```

```
autonumber
actor Cliente as Cliente (App Web)
participant API as SolicitudController (REST)
participant SvcMatch as MatchingService
participant Repo as SolicitudRepository / DB
actor Profesional as Profesional (App Web)
participant SvcOferta as PresupuestoService
```

```
Cliente->>API: POST /api/v1/solicitudes (categoriaId, direccionId, ti
API->>Repo: save(solicitud, estado=PUBLICADA)
API->>SvcMatch: buscarProfesionalesElegibles(solicitud)
SvcMatch-->>API: Lista de profesionales en radio de cobertura
API-->>Cliente: 201 Created (SolicitudPublicadaDTO)
```

```
Profesional->>API: GET /api/v1/solicitudes/cercanas
API-->>Profesional: 200 OK (Lista de Solicitudes en su zona)
```

```
Profesional->>API: POST /api/v1/presupuestos (solicitudId, monto, pro
API->>SvcOferta: registrarOferta(profesionalId, solicitudId, DTO)
SvcOferta->>Repo: save(presupuesto, estado=PENDIENTE)
API-->>Profesional: 201 Created (OfertaRegistradaDTO)
```

```
Cliente->>API: GET /api/v1/solicitudes/{id}/presupuestos
API-->>Cliente: 200 OK (Lista de Cotizaciones recibidas)
```

```
Cliente->>API: PUT /api/v1/solicitudes/{id}/asignar-oferta/{ofertaId}
API->>SvcOferta: asignarOfertaGanadora(solicitudId, ofertaId)
Note over SvcOferta,Repo: Transacción Atómica (@Transactional)
SvcOferta->>Repo: update oferta set estado=ACEPTADA
SvcOferta->>Repo: update otras ofertas set estado=RECHAZADA
SvcOferta->>Repo: update solicitud set estado=ASIGNADA, profesional_i
API-->>Cliente: 200 OK (ServicioAsignadoDTO)
```

#### **6.3. Diagrama de Estados del Ciclo de Vida de una Solicitud** 

```
stateDiagram-v2
```

```
[*] --> PUBLICADA: Cliente publica necesidad
PUBLICADA --> EN_EVALUACION: Profesionales envían presupuestos
EN_EVALUACION --> ASIGNADA: Cliente acepta una oferta
EN_EVALUACION --> CANCELADA: Cliente cancela sin elegir
```

```
PUBLICADA --> CANCELADA: Cancelada por cliente
```

```
ASIGNADA --> EN_CAMINO: Profesional notifica salida a domicilio
EN_CAMINO --> EN_PROCESO: Profesional arriba e inicia el trabajo
EN_PROCESO --> FINALIZADA: Profesional concluye labor
```

```
FINALIZADA --> CALIFICADA: Cliente califica y emite reseña
CALIFICADA --> [*]
```

```
ASIGNADA --> CANCELADA: Cancelación justificada
```

#### **6.4. Diagrama de Clases del Dominio Backend (Java Entities)** 

```
classDiagram
class Usuario {
-Long id
-String nombre
-String apellido
-String email
-String passwordHash
-String telefono
-String dni
-Boolean activo
-LocalDateTime fechaCreacion
-Set~Rol~ roles
+isHabilitado() Boolean
}
class Rol {
-Long id
-String nombre
}
class PerfilProfesional {
-Long id
-String matriculaLicencia
-String descripcionExperiencia
-Double latitudBase
-Double longitudBase
-Double radioCoberturaKm
-Double calificacionPromedio
-Integer totalResenas
```

```
-Boolean disponible
```

```
-Set~CategoriaOficio~ especialidades
+actualizarCalificacion(int nuevoPuntaje) void
```

```
+estaEnRadio(Double lat, Double lon) Boolean
```

```
}
```

```
class CategoriaOficio {
```

```
-Long id
```

```
-String nombre
```

```
-String descripcion
```

```
-String iconoUrl
```

```
-Boolean activa
```

```
}
```

```
class SolicitudServicio {
```

- `-Long id` 

```
-String titulo
```

- `-String descripcion` 

- `-NivelUrgencia nivelUrgencia` 

```
-EstadoSolicitud estado
```

- `-LocalDateTime fechaSolicitada` 

- `-LocalDateTime fechaCreacion` 

- `-Usuario cliente` 

```
-CategoriaOficio categoria
```

```
-Direccion direccion
```

```
-PerfilProfesional profesionalAsignado
+asignarProfesional(PerfilProfesional prof) void
```

- `+avanzarEstado(EstadoSolicitud nuevoEstado) void` 

```
}
```

```
class PresupuestoOferta {
```

- `-Long id` 

- `-BigDecimal montoEstimado` 

- `-String descripcionPropuesta` 

- `-Integer tiempoEstimadoHoras` 

- `-EstadoOferta estadoOferta` 

- `-LocalDateTime fechaOferta` 

- `-SolicitudServicio solicitud` 

- `-PerfilProfesional profesional` 

- `+aceptar() void` 

- `+rechazar() void` 

```
}
```

```
class ResenaCalificacion {
```

```
-Long id
-Integer puntaje
-String comentario
-LocalDateTime fechaResena
-SolicitudServicio solicitud
-Usuario cliente
-PerfilProfesional profesional
}
```

```
Usuario "1" *-- "many" Rol : tiene
Usuario "1" o-- "0..1" PerfilProfesional : extiende
PerfilProfesional "many" o-- "many" CategoriaOficio : atiende
SolicitudServicio "many" --> "1" Usuario : solicitada por
SolicitudServicio "many" --> "1" CategoriaOficio : clasificada en
SolicitudServicio "1" *-- "many" PresupuestoOferta : recibe
PresupuestoOferta "many" --> "1" PerfilProfesional : emitida por
ResenaCalificacion "1" --> "1" SolicitudServicio : evalúa
```

## **7.ESPECIFICACIÓN DE LA API RESTFUL (ENDPOINTS)** 

La API sigue los lineamientos de diseño RESTful, con payloads en formato JSON, versionamiento en la URI ( `/api/v1/` ) y autenticación mediante encabezado HTTP `Authorization: Bearer <JWT>` . 

|**Métod**<br>**o**|**Endpoint URI**|**Rol**<br>**Requerido**|**Descripción y Payload**|
|---|---|---|---|
|`POST`|`/api/v1/auth/registro`|Público|Registro de nuevo cliente o<br>profesional. Retorna`201 Created`.|
|`POST`|`/api/v1/auth/login`|Público|Autenticación con email/password.<br>Retorna token JWT y datos de sesión.|
|`GET`|`/api/v1/categorias`|Público|Lista catálogo de oficios habilitados<br>(Plomería, Electricidad, etc.).|
|`POST`|`/api/v1/profesionales/p`<br>`erfil`|`ROLE_PRO`<br>`FESIONAL`|<br>Configura radio de cobertura (km),<br>coordenadas base y especialidades.|
|`POST`|`/api/v1/solicitudes`|`ROLE_CLI`<br>`ENTE`|<br>Crea una nueva solicitud de servicio<br>con ubicacióngeorreferenciada.|
|`GET`|`/api/v1/solicitudes/cer`<br>`canas`|`ROLE_PRO`<br>`FESIONAL`|<br>Retorna solicitudes abiertas que<br>intersectan el radio del prestador.|
|`POST`|`/api/v1/presupuestos`|`ROLE_PRO`|Envía una cotización/oferta formal|



|||`FESIONAL`|para una solicitud abierta.|
|---|---|---|---|
|**Métod**<br>**o**|**Endpoint URI**|**Rol**<br>**Requerido**|**Descripción y Payload**|
|`GET`|`/api/v1/solicitudes/{id`<br>`}/presupuestos`|`ROLE_CLI`<br>`ENTE`|<br>Obtiene la lista de presupuestos<br>recibidospara comparar.|
|`PUT`|`/api/v1/solicitudes/{id`<br>`}/asignar/{ofertaId}`|`ROLE_CLI`<br>`ENTE`|<br>Acepta la cotización elegida y<br>transiciona a estado`ASIGNADA`.|
|`PATC`<br>`H`|`/api/v1/solicitudes/{id`<br>`}/estado`|`ROLE_PRO`<br>`FESIONAL`|<br>Transiciona estado a`EN_CAMINO`,<br>`EN_PROCESO`o`FINALIZADA`.|
|`POST`|`/api/v1/calificaciones`|`ROLE_CLI`<br>`ENTE`|<br>Registra puntaje (1-5) y reseña de un<br>servicio finalizado.|
|`GET`|`/api/v1/profesionales/`<br>`{ id}/resenas`|Público|Consulta el historial de calificaciones<br>ycomentarios de unprestador.|
|`POST`|`/api/v1/admin/categoria`<br>`s`|`ROLE_AD`<br>`MIN`|Alta de nuevas categorías de oficios<br>en la plataforma.|



## **8. ESTRATEGIA DE PRUEBAS Y ASEGURAMIENTO DE LA CALIDAD** 

Para garantizar la fiabilidad del sistema y cumplir con las pautas de evaluación académica, se estructuró una pirámide de pruebas automatizadas: 

```
graph BT
```

```
E2E["Pruebas End-to-End y de Integración REST (MockMvc + Postman Runn
UNIT["Pruebas Unitarias de Lógica y Algoritmos (JUnit 5 + Mockito)"]
DB_TEST["Pruebas de Repositorio e Integridad Transaccional (@DataJpaT
```

```
DB_TEST --> UNIT
UNIT --> E2E
```

1. **Pruebas Unitarias ( ):** 







Validación del cálculo geodésico de la fórmula de Haversine con coordenadas reales. Verificación de la máquina de estados de solicitudes (prohibición de saltos de estado inválidos, ej. de `PUBLICADA` directamente a `FINALIZADA` ). 

Verificación de recálculo aritmético del promedio de calificaciones. 

2. **Pruebas de Integración de Capa de Datos ( ):** 



Verificación de restricciones de clave foránea, unicidad de presupuestos por profesional ( `uk_solicitud_profesional` ) y cascadas. 



Validación de la atomicidad transaccional en la asignación de ofertas ( `@Transactional` ). 

3. **Pruebas de Seguridad y Endpoints ( ):** 



Verificación de bloqueo de endpoints protegidos ante peticiones 

anónimas ( `401 Unauthorized` ). 



Verificación de control de acceso por roles ( `403 Forbidden` si un cliente intenta invocar endpoints administrativos). 

## **9. CONCLUSIONES Y LÍNEAS DE TRABAJO FUTURO** 

#### **9.1. Conclusiones Técnicas** 

El desarrollo de **FixIt Now** como plataforma integral de matching de oficios demuestra la aplicación rigurosa de los principios de la ingeniería de software moderna: 





La separación en capas mediante **Spring Boot** garantiza alta cohesión y bajo acoplamiento, facilitando el mantenimiento y la extensibilidad del código. 

El diseño de la base de datos normalizada en **3FN** elimina redundancias, previene anomalías de 

inserción/borrado y asegura la integridad transaccional del flujo de cotizaciones. 



El algoritmo de filtrado georreferenciado resuelve de manera eficiente la 

problemática de cercanía espacial, transformando lo que solía ser un catálogo 

pasivo en un ecosistema transaccional dinámico y de valor agregado. 

#### **9.2. Líneas de Trabajo Futuro** 

1. **Integración con Pasarela de Pagos (Escrow):** Incorporar retención de pagos vía API de MercadoPago, liberando los fondos al profesional únicamente cuando el cliente confirma la finalización conforme del trabajo. 

2. **Canal de Chat en Tiempo Real:** Implementar mensajería bidireccional cliente-prestador mediante **WebSockets / STOMP** para coordinar detalles técnicos de la visita. 

3. **Aplicación Móvil Híbrida/Nativa:** Desarrollar interfaces nativas para Android/iOS con geolocalización en segundo plano para seguimiento en tiempo real del profesional "en camino". 

**10. GLOSARIO DE TÉRMINOS** 





**ACID (Atomicity, Consistency, Isolation, Durability):** Conjunto de propiedades que garantizan que las transacciones en una base de datos relacional se procesen de forma confiable. 

**BCrypt:** Función de derivación de claves criptográficas y hashing basada en el cifrado Blowfish, 

resistente a ataques de fuerza bruta. 





**DTO (Data Transfer Object):** Objeto que transporta datos entre procesos para reducir el número de llamadas a métodos remotos y ocultar la estructura interna de las entidades. 

**Haversine:** Ecuación astronómica que calcula la distancia de círculo máximo entre dos puntos en 

la superficie de una esfera a partir de sus longitudes y latitudes. 



- **JWT (JSON Web Token):** Estándar abierto (RFC 7519) que define una forma compacta y 

- autónoma de transmitir información segura entre partes en formato JSON. 





- **Matching:** Algoritmo de emparejamiento automatizado que asocia solicitudes de usuarios con proveedores idóneos según criterios de distancia, rubro y disponibilidad. 

- **RBAC (Role-Based Access Control):** Mecanismo de restricción de acceso al sistema 

- basado en los roles asignados a cada usuario autorizado. 





- **Spring Boot:** Framework de desarrollo en lenguaje Java enfocado en simplificar la configuración y despliegue de aplicaciones empresariales y microservicios REST. 

- **3FN (Tercera Forma Normal):** Estado de una base de datos relacional donde todos los atributos 

no clave dependen de manera directa y no transitiva de la clave primaria. 

