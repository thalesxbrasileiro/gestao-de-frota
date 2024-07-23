# Projeto API REST de Gestão de Veículos e Clientes

## Descrição do Projeto

Este projeto consiste no desenvolvimento de uma API REST para gerenciar informações de veículos e clientes. A aplicação permite a criação, leitura, atualização e exclusão (CRUD) de registros de veículos e clientes em um banco de dados MySQL.

## Requisitos

- **Linguagem de Programação:** Java 17
- **Framework Web:** Spring Boot
- **ORM:** Hibernate
- **Banco de Dados:** MySQL
- **Operações CRUD:** Implementadas para veículos e clientes
- **Validações de Entrada:** Implementadas para os campos de veículos e clientes

## Pontos Extras (Opcionais)

- Controle de transações para garantir a consistência dos dados.
- Mecanismo de logging para registro de eventos importantes da aplicação.
- Documentação da API utilizando Swagger ou outra ferramenta similar.
- Sistema de cache para melhoria de desempenho da API.

## Instruções para Execução do Projeto

### Pré-requisitos

- JDK 17
- Maven 3.6+
- MySQL 5.7+
- Git
- Docker

### Configuração do Banco de Dados

1. Crie um banco de dados no MySQL:
    ```sql
    CREATE DATABASE gestao_veiculos_clientes;
    ```

2. Execute os scripts DDL fornecidos em `/resources/banco/mysql/01-base-de-dados.sql` para criar as tabelas necessárias.

### Configuração do Projeto

1. Clone o repositório:
    ```sh
    git clone https://github.com/seu-usuario/gestao-veiculos-clientes.git
    cd gestao-veiculos-clientes
    ```

2. Configure as propriedades do banco de dados em `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/gestao_de_frota?useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=mysql
    spring.jpa.hibernate.ddl-auto=update
    ```

### Configuração com Docker

#### Criar e rodar o contêiner MySQL com um volume gerenciado pelo Docker:
```sh
docker run -d -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=mysql -v volume_mysql:/var/lib/mysql mysql
```

#### Criar e rodar o contêiner Redis:
```sh
docker run --name my-redis -p 6379:6379 -d redis
```

## Endpoints

A documentação completa dos endpoints está disponível via Swagger após iniciar a aplicação em `http://localhost:8080/swagger-ui.html`.

