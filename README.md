# BiciFast

![banner](App/src/main/resources/imagenes/bicifast_logo.jpeg)

[![license](https://img.shields.io/github/license/marcelo9987/BiciFast.svg)](LICENSE)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

**BiciFast** es una aplicación de gestión de alquiler de bicicletas que permite a los usuarios alquilar bicicletas de forma rápida, intuitiva y eficiente. Diseñada con un enfoque centrado en la usabilidad, BiciFast ofrece funcionalidades pensadas para facilitar tanto la experiencia de alquiler como la gestión interna del sistema.

BiciFast es una aplicación de gestión de alquiler de bicicletas que permite a los usuarios alquilar bicicletas de manera rápida y eficiente. La aplicación está diseñada para ser fácil de usar y ofrece una variedad de características para mejorar la experiencia del usuario.

## Índice

<!-- TOC -->

* [BiciFast](#bicifast)
    * [Índice](#índice)
    * [Autor@s](#autors)
    * [Fecha](#fecha)
    * [Fuentes](#fuentes)
    * [Instalación y puesta en marcha](#instalación-y-puesta-en-marcha)
        * [📦 Dependencias](#-dependencias)
    * [API](#api)

<!-- TOC -->

## Autor@s

* [Marcelo F.M.](https://github.com/marcelo9987)
* [Alba C.U.](https://github.com/Albacrucexx)
* [Fernando L.S.](https://github.com/Fernanplays46)
* [Héctor J.P.](https://github.com/hectorjimenez11)

## Fecha

Mayo 2025

## Fuentes

* [Stack Overflow](https://stackoverflow.com/)
* [Baeldung](https://www.baeldung.com/)
* [Maven](https://maven.apache.org/)
* [Medium](https://medium.com/)
* [MySQL](https://www.mysql.com/)

## Instalación y puesta en marcha

### Requisitos previos

Para obtener el código fuente de la aplicación, puedes clonar el repositorio utilizando el siguiente comando:

```bash
git clone www.github.com/marcelo9987/BiciFast
```

O, si prefieres, puedes descargar el archivo ZIP del repositorio y descomprimirlo en tu máquina local.

Una vez que hayas clonado o descargado el repositorio, asegúrate de tener instalado Java y Maven en tu máquina. Puedes verificar si tienes Java instalado ejecutando el siguiente comando en tu terminal:

```bash
java -version
```

SI USAS UN IDE COMO IntelliJ IDEA, ES POSIBLE QUE NO NECESITES DESCARGAR MANUALMENTE EL JDK, YA QUE EL PROPIO IDE TE LO DESCARGARÁ AUTOMÁTICAMENTE.

Es preciso que tengas el JDK 21 o superior instalado, puesto que si tienes una versión inferior, la aplicación no funcionará correctamente.
Si no tienes Java instalado, puedes descargarlo desde el sitio web oficial de [Oracle](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html).

Una vez hecho todo esto, asegurate de tener Maven instalado en tu máquina. Puedes verificar si tienes Maven instalado ejecutando el siguiente comando en tu terminal:

```bash
mvn -version
```

SI USAS UN IDE COMO IntelliJ IDEA, ES POSIBLE QUE NO NECESITES DESCARGAR MANUALMENTE Maven, YA QUE EL PROPIO IDE TE LO DESCARGARÁ AUTOMÁTICAMENTE.

Si no tienes Maven instalado, puedes descargarlo desde el sitio web oficial de [Maven](https://maven.apache.org/download.cgi).

### 🗄️ Configuración de Base de Datos

Requisitos previos

MySQL Server 8.0 o superior
Acceso administrativo para crear bases de datos

#### Opción 1: Creación desde cero

1. **Ejecutar el script de creación:**
   ```bash
   mysql -u root -p < SCRIPTS/CREAR_BBDD.sql
   mysql -u root -p < SCRIPTS/MOCK_DATA.sql
   ```
   Nota: Si falla la creación de la base de datos, asegúrate de que el usuario tenga permisos suficientes para crear bases de datos y tablas.

#### Opción 2: Importar dump completo (Recomendado)

```bash
mysql -u root -p < mysql_workbench/db_dump.sql
```

*Este dump incluye la estructura y datos de ejemplo para pruebas.*

#### Configuración de conexión

Edita el archivo de configuración con tus credenciales:

```properties
# src/main/resources/database.properties
db.url=jdbc:mysql://localhost:3306/bicifast_db
db.username=bicifast_user
db.password=tu_password
```

### Compilación y ejecución

Una vez que tengas Java y Maven instalados, puedes compilar y ejecutar la aplicación utilizando los siguientes comandos:

```bash
cd BiciFast\App
mvn -f pom.xml clean compile package
```

Esto generará un archivo JAR en el directorio `target` de la aplicación.
Para ejecutar la aplicación, utiliza el siguiente comando en la carpeta `App/target/`:

```bash
java -jar target/BiciFast-VERSIONXX-jar-with-dependencies.jar 
```

o, en segundo plano sin salida por consola:

```bash
javaw -jar target/BiciFast-VERSIONXX-jar-with-dependencies.jar
```

Otra opción es seleccionar los pasos anteriores en el IDE IntelliJ IDEA, y ejecutar la aplicación directamente desde allí.
Se recomienda hacerlo así, ya que es como se ha hecho durante el desarrollo de la aplicación.

Nota: Asegúrate de reemplazar `VERSIONXX` con la versión actual del proyecto.
Nota2: Hay habilitado por defecto un usuario con el usuario: `a@a.a` y la contraseña: `a`, para que puedas probar la aplicación sin necesidad de crear un usuario nuevo.

### 📦 Dependencias

- [**ch.qos.logback:logback-core** (1.5.13)](https://logback.qos.ch/)
- [**ch.qos.logback:logback-classic** (1.5.13)](https://logback.qos.ch/)
- [**com.mysql:mysql-connector-j** (9.3.0)](https://dev.mysql.com/downloads/connector/j/)
- [**com.password4j:password4j** (1.8.3)](https://password4j.com/) — *excluye:* `org.slf4j:slf4j-api`
- [**io.github.vincenzopalazzo:material-ui-swing** (1.1.4)](https://github.com/vincenzopalazzo/material-ui-swing)
- [**org.junit.jupiter:junit-jupiter** (5.13.0-RC1)](https://junit.org/junit5/) — *sólo para tests*
- [**org.netbeans.external:AbsoluteLayout** (RELEASE260)](https://netbeans.apache.org/) — *utilizado para diseño visual*

## API

Para más información sobre cómo funciona el código,
puedes consultar los javadocs generados en el proyecto.

