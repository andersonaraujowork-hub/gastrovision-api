# GastroVision API

Plataforma de gestão compartilhada para redes de restaurantes — Pós-Tech FIAP ADJT Fase 1.

## Tecnologias

- Java 21
- Spring Boot 3.4.5
- MySQL 8.0
- Docker + Docker Compose
- Maven

## Como rodar

### Pré-requisitos

- Docker e Docker Compose instalados

### 1. Clonar o repositório

```bash
git clone https://github.com/andersonaraujowork-hub/gastrovision-api.git
cd gastrovision-api
```

### 2. Configurar variáveis de ambiente

```bash
cp .env.example .env
```

### 3. Subir a aplicação

```bash
docker compose up --build
```

A API estará disponível em `http://localhost:8080/api/v1`.

## Status

🚧 Em desenvolvimento — Fase 1 (módulo de usuários)
