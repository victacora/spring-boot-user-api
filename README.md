### Tecnologías necesarias
`Java version: 17.0.12`  `Apache Maven 3.9.5`
### Diagrama de solucion

### Instalación del proyecto
1. Clonar el repositorio en tu equipo:
```sh
> cd <folder path>
> git clone https://github.com/victacora/sprint-boot-user-api
```
2. Importar el proyecto usando **IntelliJ IDEA**
   1. **Import Project**, seleccionar la carpeta.
   2. Marcar **Create Project from external model**, elegir **Maven**.  

### Ejecución
1. Desplegar el proyecto localmente: `>mvn clean install -DskipTests`
2. Arrancar el proyecto: `> mvn spring-boot:run`

### Swagger
1. http://localhost:8080/swagger-ui/index.html

### Servicio registro
```
curl -X 'POST' \
'http://localhost:8080/api/v1/users/register' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"name": "German Puyo",
"email": "germanpuyo@test.cl",
"password": "1234aB$O",
"phoneList": [
{
"number": "3217666877",
"cityCode": "2",
"countryCode": "57"
}
]
}
'
```

### Testing
1. Ejecutar tests unitarios: `> mvn test`

