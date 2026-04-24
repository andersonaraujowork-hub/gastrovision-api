# GastroVision API

Sistema de gestão compartilhada para redes de restaurantes — Pós-Tech FIAP ADJT Fase 1.

## Tecnologias

- Java 21
- Spring Boot 4.0
- MySQL 8.0
- Docker + Docker Compose
- Maven
- Springdoc OpenAPI (Swagger)

## Estrutura do projeto

```
src/main/java/br/com/gastrovision/api/
├── controllers/       # Endpoints REST
├── dtos/              # Objetos de entrada (request)
├── entity/            # Entidades JPA
├── repositories/      # Interface e implementação de acesso ao banco
└── services/          # Regras de negócio
```

## Como rodar

### Pré-requisitos

- Docker e Docker Compose instalados

### 1. Clonar o repositório

```bash
git clone https://github.com/andersonaraujowork-hub/gastrovision-api.git
cd gastrovision-api
```

### 2. Configurar variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```env
# MySQL
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=db_gastrovision
MYSQL_USER=gastrovision
MYSQL_PASSWORD=gstdb2026

# Aplicação
SERVER_PORT=8080
DDL_AUTO=update
SHOW_SQL=false
```

### 3. Subir a aplicação

```bash
docker compose up --build
```

A API estará disponível em `http://localhost:8080`.

---

## Endpoints

### Usuários

#### Criar usuário
```
POST /users
```

Body:
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "login": "joao",
  "password": "123456",
  "userType": "CUSTOMER",
  "address": {
    "street": "Rua das Flores",
    "number": "100",
    "complement": "Apto 1",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "01001000"
  }
}
```

Resposta: `201 Created`

---

#### Listar usuários
```
GET /users?page=1&size=10
```

Resposta: `200 OK`
```json
[
  {
    "id": "33e8dcbf-3a3a-404b-98ae-e42610195574",
    "name": "João Silva",
    "email": "joao@email.com",
    "login": "joao",
    "userType": "CUSTOMER",
    "address": {
      "street": "Rua das Flores",
      "number": "100",
      "complement": "Apto 1",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "01001000"
    },
    "createdAt": "2026-04-24T02:35:19.674187",
    "updatedAt": "2026-04-24T02:35:19.674212"
  }
]
```

---

#### Buscar usuário por ID
```
GET /users/{id}
```

Resposta: `200 OK` ou `404 Not Found`

---

### Tipos de usuário

| Valor | Descrição |
|---|---|
| `OWNER` | Dono de restaurante |
| `CUSTOMER` | Cliente |

---

## Documentação Swagger

Com a aplicação rodando, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Coleção Postman

O arquivo `gastrovision-api.postman_collection.json` está na raiz do repositório e cobre os principais cenários de teste.

---

## Variáveis de ambiente

| Variável | Descrição | Exemplo |
|---|---|---|
| `MYSQL_ROOT_PASSWORD` | Senha do root do MySQL | `root` |
| `MYSQL_DATABASE` | Nome do banco de dados | `db_gastrovision` |
| `MYSQL_USER` | Usuário do banco | `gastrovision` |
| `MYSQL_PASSWORD` | Senha do usuário | `gstdb2026` |
| `SERVER_PORT` | Porta da aplicação | `8080` |
| `DDL_AUTO` | Estratégia do Hibernate para o schema | `update` |
| `SHOW_SQL` | Exibir SQL no console | `false` |

---

## Status

Em desenvolvimento — Fase 1 (módulo de usuários)
