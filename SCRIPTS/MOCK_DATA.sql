USE BiciFast;

-- Estacións
INSERT
    INTO
        estacion (ubicacion, aforo)
    VALUES
        ('Plaza Mayor', 10)
      , ('Estación de tren', 15)
      , ('Universidad', 20);

-- Usuarios
INSERT
    INTO
        usuario (nombre, primer_apellido, segundo_apellido, dni, email, direccion, fecha_nacimiento, telefono, contrasenha, metodo_pago, tipo_usuario)
    VALUES
        ('Juan', 'Alfonso', 'Ramirez', '41111111Y', 'a@a.a', 'a', '2025-05-15', '666666660', '$2b$12$YVPWtgnr2qrV0Epb.owGue58eGWapAFc8Mi3edI9xMVZmCMx/uuoG', 'TARJETA', 'NORMAL')
      , ('Laura', 'Gómez', 'Pérez', '12345678A', 'laura@example.com', 'Calle A, 1', '1990-05-10', '600123456', /*clave123*/'$2b$12$o1iAa7fmN2aZDARqU6QNIuXSFiDJ6U82G2o5IQ20w96rbwB2kphaS', 'TARJETA', 'NORMAL')
      , ('Carlos', 'Ruiz', NULL, '87654321B', 'carlos@example.com', 'Av. B, 2', '1985-08-15', '600654321', /*clave456*/'$2b$12$h8OJB57bGowswCW1jJbOS.rY7G7cSQyzV4pa9mujKBpkqwDRmqXvm', 'EFECTIVO', 'NORMAL')
      , ('Ana', 'López', 'Martínez', '11223344C', 'ana@example.com', 'Calle C, 3', '2000-01-20', '600111222', /*clave789*/'$2b$12$qi/loQmOcY15yugcTpcaYugb4K.zxc7WQN2PZ7lkdHGnr7bIlv2Da', 'TARJETA', 'NORMAL');

-- Bicicletas
INSERT
    INTO
        bicicleta (estado, estacion)
    VALUES
        ('CORRECTO', 1)
       , ('CORRECTO', 1)
       , ('CORRECTO', 1)
       , ('CORRECTO', 1)
       , ('CORRECTO', 1)
       , ('CORRECTO', 1)
       , ('REVISION', 2)
       , ('CORRECTO', 3)
       , ('CORRECTO', 1);

-- Viaxes
INSERT
    INTO
        viaje (usuario, bicicleta, hora_inicio, hora_fin, origen, destino)
    VALUES
        (1, 1, '2025-05-10 08:00:00', '2025-05-10 08:30:00', 1, 2),
      (1, 4, '2025-05-11 09:15:00', NULL, 1, NULL),
      (2, 2, '2025-05-09 17:00:00', '2025-05-09 17:45:00', 2, 3);

