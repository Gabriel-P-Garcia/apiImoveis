<div align="center">
<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger" />
</div>

# API de Gerenciamento de Imóveis e Proprietários

Esta API RESTful foi desenvolvida como projeto final para a disciplina de Desenvolvimento de Software, utilizando o ecossistema Spring Boot 3.5+, persistência com Spring Data JPA e banco de dados PostgreSQL.

## 🚀 Objetivo do Projeto

O sistema gerencia o relacionamento 1 para N entre Proprietários e seus Imóveis. Um proprietário pode possuir múltiplos imóveis, enquanto cada imóvel pertence a um único proprietário.

## 🛠️ Tecnologias Utilizadas

•
Java 25

•
Spring Boot 3.5.15

•
Spring Data JPA (Hibernate)

•
Spring Security + JWT (Autenticação Stateless)

•
PostgreSQL (Banco de Dados)

•
Springdoc OpenAPI (Swagger UI)

•
Lombok (Produtividade)

## 🏗️ Arquitetura do Sistema

O projeto segue o padrão de arquitetura em camadas:

1. Model/Entity: Mapeamento objeto-relacional com JPA.

2. DTO (Data Transfer Object): Isolamento entre a camada de persistência e a camada de controle.

3. Repository: Interfaces que herdam de JpaRepository com consultas customizadas.

4. Service: Camada de regras de negócio com controle transacional (@Transactional).

5. Controller: Exposição dos endpoints REST e tratamento de status HTTP.

6. Security: Filtro de autenticação JWT e controle de acesso.

## 🔐 Segurança e Autenticação

A API utiliza JSON Web Token (JWT) para proteger os endpoints.

•
Rota Pública: /api/auth/login

•
Rotas Protegidas: Todas as demais rotas sob /api/** exigem o cabeçalho Authorization: Bearer <token>.

## 🔑 Credenciais de Teste

Para testar a API no Swagger ou via Postman, utilize:

•
Usuário: admin

•
Senha: 123456

## 📖 Como Executar a Aplicação

1. Banco de Dados

Certifique-se de ter um banco PostgreSQL rodando. As configurações de conexão estão no arquivo src/main/resources/application.yaml.
Execute o script SQL localizado na raiz do projeto:

•
ScriptatividadeImoveis.sql: Cria as tabelas e realiza a carga inicial de dados.

2. Execução

Execute o comando Maven para rodar a aplicação:

Bash


`./mvnw spring-boot:run`



A aplicação estará disponível em: http://localhost:7777

3. Documentação (Swagger )

Acesse a documentação interativa para testar os endpoints:
http://localhost:7777/swagger-ui.html

Passos para testar no Swagger:

1. Vá no endpoint POST /api/auth/login.

2. Informe as credenciais de teste.

3. Copie o token da resposta.

4. Clique no botão "Authorize" no topo da página e cole o token.

5. Agora você pode executar as operações de CRUD.

## 📋 Endpoints Principais

Proprietários

•
GET /api/proprietarios: Lista todos.

•
GET /api/proprietarios/filtro?nome=...: Busca por nome (Consulta customizada ).

•
POST /api/proprietarios: Cadastra novo.

•
PUT /api/proprietarios/{id}: Atualiza.

•
DELETE /api/proprietarios/{id}: Remove.

Imóveis

•
GET /api/imoveis: Lista todos.

•
GET /api/imoveis/proprietario/{id}: Lista imóveis de um proprietário específico (Consulta customizada).

•
POST /api/imoveis: Cadastra novo.

•
DELETE /api/imoveis/{id}: Remove.


___

Desenvolvedor: Gabriel Pereira Garcia

Data: 25 de Junho de 2026

Disciplina: Desenvolvimento de Software

