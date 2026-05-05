# 🎬 OMDb Gateway - Fullstack Dev

Aplicação fullstack para pesquisa e visualização de filmes e séries, utilizando a API pública do OMDb.

---

## 📌 Visão Geral

Este projeto é dividido em duas partes principais:

* **Backend (Java)**: API REST consumindo OMDb API
* **Frontend (React)**: Interface web que consome o backend, oferecendo uma experiência fluida de busca e navegação.

---

## 🧱 Arquitetura

## ⚙️ Backend

### 🚀 Tecnologias utilizadas

* Java (Spring Boot)
* Caffeine (cache)
* OpenAPI (Swagger)
* Logback (logs)
* bucket4j (rate-limit)

### 📡 Endpoints

#### 🔍 Buscar títulos

```
GET /api/omdb/filtro
```

**Query params:**

* `title` (string, obrigatório)
* `year` (opcional)
* `type` (movie, series, episode)
* `page` (default: 1)

#### 🎯 Buscar por ID IMDb

```
GET /api/omdb/{id}
```

### ▶️ Executando o Backend

```bash
# Clone o projeto
git clone <repo-url>
cd backend

# Execute
./mvnw spring-boot:run
```

Backend disponível em:

```
http://localhost:8080
```

Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 🎨 Frontend

### 🚀 Tecnologias

* React (JavaScript)
* Axios (HTTP client)
* React Router
* CSS Modules / Styled Components

---

### 🖥️ Funcionalidades

* 🔍 Busca com filtros (título, ano, tipo)
* 📄 Listagem paginada com:

  * Poster
  * Título
  * Ano
    
* 🎬 Tela de detalhes:
* Descrição completa
  * Plot 
  * Gênero, elenco, דירetor, avaliações

---

### ▶️ Executando o Frontend

```bash
cd frontend

# Instalar dependências
npm install

# Rodar aplicação
npm run dev
```

Frontend disponível em:

```
http://localhost:5173
```

---

## 🔐 Variáveis de Ambiente

### Backend

```
OMDB_API_KEY=your_api_key
CACHE_TTL=3600
RATE_LIMIT=100
```

### Frontend

```
REACT_APP_API_URL=http://localhost:8080/api
```

---

## 🧪 Testes

### Backend

API: 
 http://localhost:8080/api/omdb/filme/{id}, 
 http://localhost:8080/api/omdb/filtro?title={text}&page=1'
 
```bash
./mvnw test
```

### Frontend

```bash
npm test
```
