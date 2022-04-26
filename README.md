# Votação Spring Solutis

Aplicação de votação feita como projeto final do curso de Java Spring Boot da [Solutis](https://solutis.com.br)

##Sumário

* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Pré-requisitos](#pré-requisitos)
* [Instalação](#instalação)
* [Recursos](#recursos)
* [Tarefas bônus](#tarefas-bônus)

## Tecnologias utilizadas

- Spring Boot
- Lombok
- H2 Database

## Pré-requisitos

Estas são as instalações necessárias para executar o projeto:

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/products/docker-desktop/)

### Após a instalação é necessário configurar as variáveis de ambiente:
- JAVA_HOME - Apontando para local de instalação do Java
- MAVEN_HOME - Apontando para o local de instalação do Maven
- PATH - Adicionar "JAVA_HOME\bin", "MAVEN_HOME\bin" e "...\Docker\resources\bin"

### Após instalações e configurações verificar se o ambiente está pronto:

Execute o seguinte comando no terminal
```
java -version
```

Resultado esperado
```
java version "17.0.2" 2022-01-18 LTS
Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)
```

Execute o seguinte comando no terminal
```
mvn -version
```

Resultado esperado
```
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: ...\apache-maven-3.8.5
Java version: 17.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17.0.2
```

Execute o seguinte comando no terminal
```
docker-compose --version
```

Resultado esperado
```
Docker Compose version v2.3.3
```

## Instalação

### Clonar o projeto localmente
```
git clone https://github.com/philipe02/VotingSolutis.git
```
### Navegar para pasta do projeto
```
cd VotingSolutis
```
### Instalar dependências do projeto utilizando maven
```
mvn clean install
```
### Utilizar o docker para ligar os serviços auxiliares
```
docker-compose up -d
```
### Rodar a aplicação utilizando maven
```
mvn spring-boot:run
```

## Recursos

Com a aplicação rodando corretamente ficarão disponíveis os recursos abaixo:

### Swagger

Documentação da api disponível pelo link: http://localhost:8080/swagger-ui/index.html

### Postman

Coleção do postman: https://app.getpostman.com/join-team?invite_code=1f2d5bb702b9fea6b217371f184950ff&target_code=5aef32813a1c1433cdcc086e08e67838

### Sonar

Necessário fazer o cadastro do sistema no sonar

1. Acessar o link do sonar http://localhost:9000
2. Realizar o login com usuário e senha: admin
3. Selecionar criar projeto manualmente
4. Dar um nome ao projeto e confirmar
5. Selecionar análise de projeto localmente
6. Gerar um token
7. Rodar o comando a seguir substituindo os parâmetros:
```
mvn clean verify sonar:sonar -D sonar.projectKey={Nome do Projeto} -D sonar.login={Token gerado} -D sonar.host.url=http://localhost:9000 
```

##Tarefas bônus

* Tarefa Bônus 1 — Integração com sistemas externos ✅
* Tarefa Bônus 2 — Contabilização automática ✅
* Tarefa Bônus 3 — Mensageria e filas ✅
* Tarefa Bônus 4 — Hospede sua API na nuvem ❌
* Tarefa Bônus 5 — Análise de qualidade do código ✅
* Tarefa Bônus 6 — Versionamento da API ✅

---

Feito por Philipe Santos 👺
