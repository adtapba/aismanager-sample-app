# AIS Manager - Application

### Introducción

Pequeña aplicación [Java EE](https://en.wikipedia.org/wiki/Java_Platform,_Enterprise_Edition) que ofrece una funcionalidad básica para la gestión de señales provenientes de un [AIS](https://en.wikipedia.org/wiki/Automatic_identification_system) imaginario. 

Junto con el proyecto [aismanager-sample-bundles](https://github.com/adtapba/aismanager-sample-bundles), este sistema sólo tiene el objetivo de servir como un ejemplo básico de integración para un caso de uso cercano a la realidad en el entorno de negocio marítimo-portuario, mostrando las directrices de integración marcadas por la [APBA](https://www.apba.es/). No tiene las características exigibles a un sistema comercial de gestión de datos AIS.

### Funcionalidad

* Configuración de líneas de control de paso de embarcaciones.
* Recepción de eventos provenientes de estaciones AIS.
* Consulta de la última posición registrada para un buque.

### Construcción
Ejecutar el siguiente comando [Maven](http://maven.apache.org/) en la raiz de la carpeta:
```
mvn clean install
```
### Ejecución
Se puede iniciar la aplicación mediante el [plugin Wildfly de Maven](https://docs.jboss.org/wildfly/plugins/maven/latest/):
```
mvn wildfly:run
```
La aplicación dispone de unas [suites de prueba de los recursos REST que publica](https://github.com/adtapba/aismanager-sample-app/tree/master/src/test/resources) que se pueden importar en [Postman](https://www.postman.com/).

Para parar la ejecución del servidor [Wildfly](https://wildfly.org/) pulsar Ctrl-C.
