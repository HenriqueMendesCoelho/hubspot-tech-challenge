# 🛠️ HubSpot Tech Challenge

Este projeto foi desenvolvido como parte do processo seletivo técnico da Meetime.

Trata-se de uma aplicação **Java + Spring Boot** que implementa uma integração com a **API do HubSpot**, utilizando *
*OAuth 2.0 (Authorization Code Flow)** para autenticação segura.

---

## 📌 Funcionalidades

- 🔐 **Autenticação via OAuth 2.0**
    - Geração da URL de autorização
    - Processamento do callback e troca pelo access token

- 👤 **Criação de contatos**
    - Endpoint para criar um novo contato no CRM HubSpot
    - Respeita o rate limit da API

- 🔔 **Webhook**
    - Endpoint para escutar eventos de criação de contatos (`contact.creation`) enviados pelo HubSpot

- 📂 **Persistência de tokens**
    - Tokens de acesso e refresh são armazenados em banco H2 em memória, com estrutura JPA

---

## 🚀 Como executar

1. Clone o projeto:
   ```bash
   git clone https://github.com/HenriqueMendesCoelho/hubspot-tech-challenge.git
   cd hubspot-tech-challenge
   ```

2. Configure seu `application.properties` com as credenciais do HubSpot:
   ```properties
   hubspot.clientid=SEU_CLIENT_ID
   hubspot.clientsecret=SEU_CLIENT_SECRET
   ```

3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse o H2 console (opcional):
    - URL: http://localhost:8080/api/h2-console
    - JDBC URL: `jdbc:h2:mem:hubspot`
    - User: `sa`
    - Senha: (deixe em branco)

---

## 🔀 Fluxo OAuth

1. Requisição à URL de autorização (`GET /oauth/authorize-url`)
2. Usuário é redirecionado ao HubSpot para login e consentimento
3. HubSpot chama seu endpoint de callback (`/oauth/callback`)
4. Sua aplicação troca o `code` pelo `access_token` e armazena com o `refresh_token`

---

## 📬 Webhook

- Endpoint: `POST /webhook/hubspot`
- Eventos processados: `contact.creation`
- Inclui validação básica de origem (pode ser expandida com verificação de assinatura HMAC)

---

## 📦 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web / Data JPA
- H2 Database
- Maven

---

## 💡 Decisões Técnicas

- Utilização de **Spring Boot** por familiaridade, robustez e suporte nativo ao OAuth
- **H2 Database** foi adotado para persistência temporária dos tokens, permitindo reuso e refresh
- A arquitetura foi dividida em camadas (controller, usecase, repository) visando clareza e manutenção futura

---

## 🔧 Melhorias Futuras

- Persistência dos tokens em banco real (ex: PostgreSQL)
- Melhor controle de sessões por usuário (multiusuário)
- Validação de webhook com assinatura HMAC enviada pelo HubSpot
- Testes automatizados para os endpoints

---

🚀 **Obrigado pela oportunidade!**
