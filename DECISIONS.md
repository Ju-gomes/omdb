🎬 OMDb API

Este documento resume as principais decisões técnicas tomadas no desenvolvimento do projeto.

🧭 Arquitetura Geral

Separação em dois sistemas independentes:

Backend (Spring Boot)
Frontend (React)
🎯 Motivo
Melhor organização e escalabilidade
Deploy independente das camadas
Baixo acoplamento entre UI e API
Facilidade de manutenção

⚙️ Backend
☕ Spring Boot

🎯 Motivo
Familiaridade com a linguagem.
Construção da API REST.
Framework consolidado no ecossistema Java
Configuração rápida e produtiva
Suporte nativo a REST, validação e dependências

Backend atua como gateway intermediário para a OMDb API, conforme proposto.

Configurações:
Proteção da API Key
Centralização da lógica de integração
Possibilidade de cache e rate limit

⚡ Cache (Caffeine)
Uso de cache em memória com Caffeine.
🎯 Motivo
Redução de chamadas externas
Melhoria de performance
Simplicidade de implementação

🚦 Rate Limit (Bucket4j)
Controle de requisições por cliente.
🎯 Motivo
Evitar abuso da API
Proteção do backend e da API externa
Estabilidade do sistema

📄 Swagger (OpenAPI)
Documentação automática da API.
🎯 Motivo
Facilidade de testes
Documentação sempre atualizada
Integração simples com frontend

🎨 Frontend
⚛️ React
Uso de React para interface do usuário.

🎯 Motivo
Componentização
Ecossistema maduro
Boa performance para SPA

🌐 Axios
Cliente HTTP baseado em Axios.
🎯 Motivo
Sintaxe simples
Melhor tratamento de erros
Interceptors úteis

🧭 React Router
Gerenciamento de rotas no frontend.
🎯 Motivo
Navegação SPA
URLs amigáveis
Melhor experiência do usuário

🎨 CSS Modules / Styled Components
🎯 Motivo
Evita conflitos globais
Melhor organização de estilos
Escalabilidade

🔐 Configuração
✔️ Variáveis de ambiente
🎯 Motivo
Proteção de dados sensíveis (API Key)
Flexibilidade entre ambientes
Facilita deploy

🧪 Testes
✔️ Backend e Frontend
🎯 Motivo
Garantia de qualidade do código
Prevenção de regressões
Validação de comportamento da aplicação

📌 API Design

🔍 Endpoint de busca flexível
Uso de filtros em um único endpoint /api/omdb/filtro.
🎯 Motivo
Maior flexibilidade
Evita excesso de endpoints
Melhor integração com frontend

🎯 Busca por ID
Endpoint dedicado /api/omdb/{id}.
🎯 Motivo
Simplicidade
Acesso direto a detalhes
Padrão REST
