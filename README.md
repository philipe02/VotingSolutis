# Votação Spring Solutis
Aplicação de votação feita como projeto final do curso de Java Spring Boot da [Solutis](https://solutis.com.br)

## Tecnologias utilizadas

- Spring Boot
- Lombok
- H2 Database

## Pré-requisitos

* Java JDK 17
* Maven
* Docker

## Instalação


### Clonar o projeto localmente
`git clone https://github.com/philipe02/VotingSolutis.git`
### Navegar para pasta do projeto
`cd VotingSolutis`
### Instalar dependências do projeto utilizando maven
`mvn clean install`
### Utilizar o docker para ligar os serviços auxiliares
`docker-compose up -d`
### Rodar a aplicação utilizando maven
`mvn spring-boot:run`

## Recursos

Com a aplicação rodando corretamente ficarão disponíveis os recursos abaixo:

### Swagger
Documentação da api disponível pelo link: http://localhost:8080/swagger-ui/index.html
### Sonar
Necessário fazer o cadastro do sistema
1. Acessar o link do sonar http://localhost:9000
2. Realizar o login com usuário e senha: admin
3. Selecionar criar projeto manualmente
4. Dar um nome ao projeto e confirmar
5. Selecionar análise de projeto localmente
6. Gerar um token
7. Rodar o comando a seguir substituindo os parâmetros: <br>
```
mvn clean verify sonar:sonar -D sonar.projectKey={Nome do Projeto} -D sonar.login={Token gerado} -D sonar.host.url=http://localhost:9000 
```

---

Feito por Philipe Santos 👺
