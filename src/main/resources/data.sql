-- Inserción de Roles iniciales
MERGE INTO roles (id, nombre) KEY(nombre) VALUES (1, 'ROLE_CLIENTE');
MERGE INTO roles (id, nombre) KEY(nombre) VALUES (2, 'ROLE_PROFESIONAL');
MERGE INTO roles (id, nombre) KEY(nombre) VALUES (3, 'ROLE_ADMIN');

-- Inserción de Categorías de Oficios iniciales
MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (1, 'Plomería', 'Instalación y reparación de cañerías, griferías, sanitarios y bombas de agua.', 'https://img.icons8.com/color/96/plumbing.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (2, 'Electricidad', 'Instalaciones eléctricas, tableros, iluminación, cortocircuitos y cableado.', 'https://img.icons8.com/color/96/electrical.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (3, 'Gasista', 'Instalación y mantenimiento de estufas, termotanques, calefones y detección de fugas.', 'https://img.icons8.com/color/96/gas-industry.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (4, 'Cerrajería', 'Apertura de puertas, cambio de cerraduras, copias de llaves y cerrojos de seguridad.', 'https://img.icons8.com/color/96/key.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (5, 'Pintura', 'Pintura de interiores, exteriores, impermeabilización y tratamiento de humedad.', 'https://img.icons8.com/color/96/paint-roller.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (6, 'Albañilería', 'Refacciones generales, revoques, colocación de cerámicos y pisos.', 'https://img.icons8.com/color/96/brick-wall.png', true);

MERGE INTO categorias_oficios (id, nombre, descripcion, icono_url, activa) KEY(nombre) 
VALUES (7, 'Climatización / Aire Acondicionado', 'Instalación, carga de gas, limpieza y reparación de equipos split y calefacción.', 'https://img.icons8.com/color/96/air-conditioner.png', true);
