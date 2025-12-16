# Projeto E-diaristas

projeto criado utilizando tecnologias como Java e Spring-boot

## dependencias do Projeto

- Spring boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## dependencias de desenvolvimento

- Spring Boot Devtools
- lombok

## Requisitos do Projeto

- Java 17
- Maven 3.8

## Como testar esse projeto na minha maquina?

clone este repositorio e entre na pasta do projeto

```sh
git clone https://github.com/rickicr-collab/Projeto-E-diaristas.git
cd e-diaristas-spring
```

atualize as informações sobre banco de dados no arquivo [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:mysql://<host>:<porta>/<nomedobanco>
spring.datasource.username=<usuario do banco>
spring.datasource.password=<senha do banco > 
```

execute o projeto no Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/servicos](http://localhost:8080/admin/servicos).