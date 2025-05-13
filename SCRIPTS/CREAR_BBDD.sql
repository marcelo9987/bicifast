-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BiciFast
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BiciFast
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BiciFast` DEFAULT CHARACTER SET utf8 ;
USE `BiciFast` ;

-- -----------------------------------------------------
-- Table `BiciFast`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiciFast`.`usuario` ;

CREATE TABLE IF NOT EXISTS `BiciFast`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `primer_apellido` VARCHAR(255) NOT NULL,
  `segundo_apellido` VARCHAR(255) NULL,
  `dni` CHAR(9) NOT NULL,
  `email` VARCHAR(255) NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `telefono` CHAR(9) NOT NULL,
  `contrasenha` BINARY(60) NOT NULL COMMENT 'BINARY extiende por 0x00 para completar, mientras que char trunca esp. vacios, por lo que es mas optima',
  `metodo_pago` ENUM('TARJETA', 'EFECTIVO') NOT NULL,
  `fecha_inicio_suscripcion` DATETIME NOT NULL DEFAULT NOW(),
  `fecha_fin_suscripcion` DATETIME GENERATED ALWAYS AS (DATE_ADD(fecha_inicio_suscripcion, INTERVAL 30 DAY)) VIRTUAL,
  `tipo_usuario` ENUM('ADMIN', 'MANT', 'NORMAL') NOT NULL DEFAULT 'NORMAL',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BiciFast`.`estacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiciFast`.`estacion` ;

CREATE TABLE IF NOT EXISTS `BiciFast`.`estacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ubicacion` TINYTEXT NOT NULL,
  `aforo` TINYINT(255) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BiciFast`.`bicicleta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiciFast`.`bicicleta` ;

CREATE TABLE IF NOT EXISTS `BiciFast`.`bicicleta` (
  `id` INT NOT NULL,
  `estado` ENUM('CORRECTO', 'REVISION') NOT NULL DEFAULT 'CORRECTO',
  `estacion` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_bicicleta_estacion1_idx` (`estacion` ASC) VISIBLE,
  CONSTRAINT `fk_bicicleta_estacion1`
    FOREIGN KEY (`estacion`)
    REFERENCES `BiciFast`.`estacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BiciFast`.`viaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiciFast`.`viaje` ;

CREATE TABLE IF NOT EXISTS `BiciFast`.`viaje` (
  `usuario` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `bicicleta` INT NOT NULL,
  `hora_inicio` DATETIME NOT NULL,
  `hora_fin` DATETIME NULL,
  `origen` INT NOT NULL,
  `destino` INT NULL,
  PRIMARY KEY (`usuario`, `id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `inicio_UNIQUE` (`hora_inicio` ASC) VISIBLE,
  UNIQUE INDEX `fin_UNIQUE` (`hora_fin` ASC) VISIBLE,
  INDEX `usuario_id_idx` (`usuario` ASC) VISIBLE,
  UNIQUE INDEX `bicicleta_UNIQUE` (`bicicleta` ASC) VISIBLE,
  INDEX `fk_viaje_estacion1_idx` (`origen` ASC) VISIBLE,
  INDEX `fk_viaje_estacion2_idx` (`destino` ASC) VISIBLE,
  CONSTRAINT `fk_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `BiciFast`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bicicleta`
    FOREIGN KEY (`bicicleta`)
    REFERENCES `BiciFast`.`bicicleta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viaje_estacion1`
    FOREIGN KEY (`origen`)
    REFERENCES `BiciFast`.`estacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viaje_estacion2`
    FOREIGN KEY (`destino`)
    REFERENCES `BiciFast`.`estacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
