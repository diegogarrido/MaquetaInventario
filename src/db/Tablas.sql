CREATE TABLE IF NOT EXISTS Producto
(
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(50),
  PRIMARY KEY (id),
  UNIQUE (nombre)
);

CREATE TABLE IF NOT EXISTS Region
(
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Comuna
(
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  id_region INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_region) REFERENCES Region(id)
);

CREATE TABLE IF NOT EXISTS Sucursal
(
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  direccion VARCHAR(50) NOT NULL,
  id_comuna INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_comuna) REFERENCES Comuna(id)
);

CREATE TABLE IF NOT EXISTS SucursalTieneProducto
(
  id INT NOT NULL AUTO_INCREMENT,
  id_sucursal INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id),
  FOREIGN KEY (id_producto) REFERENCES Producto(id)
);