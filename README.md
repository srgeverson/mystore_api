# MyStore API
API para a administracao dos sistemas MyStore APP e MyStore Web.

#### 🧭 Baixando e rodando o projeto
```bash

# Clone este repositório
$ git clone https://github.com/tcc-mystore/mystore_api.git

# Acesse a pasta do projeto no terminal/cmd
$ cd mystore_api/arquivos_do_projeto

# Execute a API
$ java -jar mystore_api-0.0.1.jar
# O servidor inciará na porta:8080 - acesse http://localhost:8080 

# O servidor inciará na porta:3000 - acesse http://localhost:3000 

```

## 🚀 Como o projeto foi iniciado

Este projeto é divido em apenas uma parte:
1. Backend (mystore_api/arquivos_do_projeto) 

💡Esta aplicação precisa que o Backend esteja sendo executado para funcionar.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com) e [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html). 
Além disto é bom ter um editor para trabalhar com o código como [Spring Tools Suite](https://spring.io/tools).

#### 🎲 Rodando o servidor (Backend)

```bash
# Acesse a pasta do projeto no terminal/cmd
$ cd mystore_api/arquivos_do_projeto

# Execute a aplicação em modo de desenvolvimento
$ java -jar sac-api-0.0.1.jar

# O servidor inciará na porta:8080 - acesse http://localhost:8080 

```
#### 🛠️ Construindo a aplicação

```bash
# Configurando o projeto inicial [https://start.spring.io/](https://start.spring.io/).
$ spring-boot-starter

#Instala a dependência do java web [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/2.2.2.RELEASE].
$ spring-boot-starter-web

# Instala a dependência de restart da aplicação, recomendado apenas para ambiente de desenvolvimento [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools/2.2.2.RELEASE].
$ spring-boot-devtools

# Instala a dependência de teste de integração [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test/2.2.2.RELEASE].
$ spring-boot-starter-test

# Instala a dependência de validação dos objetos para os testes de integração [https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.0].
$ rest-assured

# Instala a dependência de persistência de dados [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/2.2.2.RELEASE].
$ spring-boot-starter-data-jpa

# Instala o driver do banco de dados [https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.21].
$ ojdbc8

# Instala a dependência migração de banco de dados [https://mvnrepository.com/artifact/org.flywaydb/flyway-core/6.0.8].
$ flyway-core

# Instala a dependência de automatização de encapsulamento das classes [https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.10].
$ lombok

# Instala a dependência de metodos extras para a manipulação das classes [https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.9].
$ commons-lang3

# Instala a dependência de filtro das propriedades dos objetos JSON [https://mvnrepository.com/artifact/com.github.bohnman/squiggly-filter-jackson/1.3.18]
$ squiggly-filter-jackson

# Instala a dependência de geração dos relatórios [https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports/6.16.0].
$ jasperreports

# Instala a dependência de funções dos relatórios(Ex: costumização de campos) [https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports-functions/6.16.0].
$ jasperreports-functions

# Instala a dependência que gera a documentação da API [https://mvnrepository.com/artifact/io.springfox/springfox-swagger2/2.9.2].
$ springfox-swagger2

# Instala a dependência de visualização da documentação da API[https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui/2.9.2].
$ springfox-swagger-ui

# Instala a dependência de envio de email [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail/2.2.2.RELEASE].
$ spring-boot-starter-mail

# Instala a dependência de aplicativos da web MVC usando visualizações FreeMarker (Usado no envio de email) [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-freemarker/2.3.9.RELEASE].
$ spring-boot-starter-freemarker

# Instala a dependência de automatização de geração de especificações legíveis para APIs JSON [https://mvnrepository.com/artifact/io.springfox/springfox-bean-validators/2.9.2].
$ springfox-bean-validators

# Instala a dependência aplicando nível 3 de uma REST API baseado em hipermídia tornando a API RESTful [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-hateoas/2.2.2.RELEASE].
$ spring-boot-starter-hateoas

```

## 🛠️ Construído com

* [Spring Tools 4](https://spring.io/tools) - IDE Spring Tools
* [Workbench](https://www.mysql.com/products/workbench/) - MySQL Workbench
* [Postman](https://www.postman.com/) - Postman API


## 👨‍💻 Equipe de Desenvolvimento

* **Geverson Souza** - [Geverson Souza](https://www.linkedin.com/in/srgeverson/)

## ✒️ Autores

* **Geverson Souza** - [Geverson Souza](https://www.linkedin.com/in/srgeverson/)

## 📌 Versão ainda em produção

Nós usamos [Bitbucket](https://bitbucket.org/) para controle de versão.