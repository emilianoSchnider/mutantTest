# API Rest - Detector de mutantes

### Tecnologías usadas
- Java 8
- Apache Maven 3.6.0
- Eclipse 2019-09 (4.13)
- Google Firebase (Cloud Firestore)
- Heroku

### Cobertura de test
[![Coverage Status](https://coveralls.io/repos/github/emilianoSchnider/mutantTest/badge.svg?branch=master)](https://coveralls.io/github/emilianoSchnider/mutantTest?branch=master)

### Ejecutar API localmente
- Clonar repositorio desde git:
```bash
git clone https://github.com/emilianoSchnider/mutantTest.git
```

- Importar proyecto Maven desde IDE (En Eclipse ir a File/Import/Maven Existing Projects)

- Ejecutar la api
Desde la carpeta del proyecto, ejecutar por comando
```bash
 mvn spring-boot:run
```

### URL del API
- Local: http://localhost:8080
- Cloud: https://mutantapi.herokuapp.com

### Utilización
#### Servicio de validación de mutantes
##### Endpoint: [URL_API]/mutant
##### Ejemplo de uso desde Postman:
* Crear nuevo Request.
* Seleccionar método POST
* Setear URL https://mutantapi.herokuapp.com/mutant
* En Body, seleccionar raw y formato Json
* Ingresar matriz a validar, ej:
{
    "dna": [
        "ATGCGA",
        "AAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCTTA",
        "TCACTG"
    ]
        
}


#### Servicio de estadísticas de verificaciones
##### Endpoint: [URL_API]/stats
##### Ejemplo de uso desde Postman:
* Crear nuevo Request.
* Seleccionar método GET
* Setear URL https://mutantapi.herokuapp.com/stats

##### Ejemplo desde línea de comando:
curl -X GET -H "Content-Type: application/json" -d '' https://mutantapi.herokuapp.com/stats




