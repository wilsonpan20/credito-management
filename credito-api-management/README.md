# Credito API Management

API RESTful desenvolvida para o desafio técnico de consulta de créditos constituídos. A aplicação permite consultar créditos por número da NFS-e ou pelo número do crédito, notificando cada consulta através de um tópico Kafka.

## 🚀 Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3.3.1**
*   **Spring Data JPA / Hibernate**
*   **PostgreSQL** (Banco de Dados)
*   **Flyway** (Migração de Banco de Dados)
*   **Apache Kafka** (Mensageria)
*   **Docker & Docker Compose** (Containerização)
*   **MapStruct** (Mapeamento de Objetos)
*   **Lombok** (Redução de boilerplate)
*   **SpringDoc OpenAPI (Swagger)** (Documentação da API)
*   **JUnit 5 & Mockito** (Testes Automatizados)

## 📋 Pré-requisitos

Para executar este projeto, você precisará ter instalado em sua máquina:

*   [Docker](https://www.docker.com/get-started) e [Docker Compose](https://docs.docker.com/compose/install/)
*   [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
*   [Maven](https://maven.apache.org/download.cgi)

## 🛠️ Como Executar o Projeto

Siga os passos abaixo para colocar a aplicação em execução:

### 1. Clonar o Repositório

```bash
git clone git@github.com:wilsonpan20/credito-management.git
cd credito-api-management
```

### 2. Subir a Infraestrutura (Docker)

Utilize o Docker Compose para iniciar o banco de dados PostgreSQL, o Zookeeper, o Kafka e a Kafka UI.

```bash
docker-compose up -d
```

Aguarde alguns instantes até que todos os containers estejam rodando e saudáveis.

### 3. Compilar e Executar a Aplicação

Você pode rodar a aplicação diretamente via Maven ou pela sua IDE de preferência.

**Via Terminal (Maven):**

```bash
mvn spring-boot:run
```

A aplicação iniciará na porta **8080**.

## 📚 Documentação da API (Swagger)

Com a aplicação rodando, você pode acessar a documentação interativa da API através do Swagger UI:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Lá você poderá visualizar todos os endpoints disponíveis e testá-los diretamente pelo navegador.

## 🧪 Como Testar

### Endpoints Principais

1.  **Consultar Créditos por NFS-e:**
    *   **GET** `/api/creditos/{numeroNfse}`
    *   Exemplo: `http://localhost:8080/api/creditos/7891011`

2.  **Consultar Crédito por Número:**
    *   **GET** `/api/creditos/credito/{numeroCredito}`
    *   Exemplo: `http://localhost:8080/api/creditos/credito/123456`

### Rodar Testes Automatizados

Para executar os testes unitários e de integração:

```bash
mvn test
```

## 🔍 Monitoramento (Kafka UI)

Para visualizar os tópicos e as mensagens enviadas para o Kafka, acesse a interface do Kafka UI:

👉 **[http://localhost:8088](http://localhost:8088)**

*   Vá em **Topics** -> **consulta-credito-events**.
*   Lá você verá uma nova mensagem sempre que uma consulta for realizada na API.

## 🗄️ Banco de Dados

O banco de dados é inicializado automaticamente com alguns dados de teste através do Flyway.

*   **Host:** `localhost`
*   **Porta:** `5432`
*   **Database:** `credito_db`
*   **Usuário:** `root` (definido no .env ou docker-compose)
*   **Senha:** `desafiotec` (definido no .env ou docker-compose)

---
Desenvolvido por Wilson.
