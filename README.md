# Sistema de Gestão de Projetos Internos

Este projeto é uma API REST desenvolvida para a gestão de projetos internos, permitindo a gestão de tarefas e projetos com controle de permissões baseado em Keycloak. O sistema conta com três perfis de usuários: **Usuário Comum**, **Gerente de Projeto** e **Administrador**, cada um com permissões distintas.

## Tecnologias Utilizadas

- **Backend**: Java 21, Spring Boot, Spring Security, JPA/Hibernate, PostgreSQL, Keycloak
- **Autenticação**: Keycloak
- **Banco de Dados**: PostgreSQL
- **Infraestrutura**: Docker

## Pendências e Melhorias

- Integração completa do Keycloak com o frontend (ainda não implementado)
- Implementação completa das permissões nos endpoints
- Melhor cobertura de testes unitários e de integração
- Deploy completo com Kubernetes (atualmente apenas Docker)

## Como Rodar o Projeto

### **Requisitos**
- Java 21
- Docker e Docker Compose
- PostgreSQL
- Keycloak configurado com os perfis de usuários

### **Passos**
1. Clone o repositório:
   ```sh
   git clone https://github.com/britodaniel97/gestao-projetos.git
   cd gestao-projetos
   ```
2. Suba o ambiente com Docker:
   ```sh
   docker-compose up -d
   ```
3. Acesse o Keycloak e configure os perfis de usuários.
4. Inicie a aplicação:
   ```sh
   mvn spring-boot:run
   ```
5. A API estará disponível em `http://localhost:8080`

## Decisões e Justificativas

- **Spring Boot** foi escolhido pela facilidade de desenvolvimento e integração com Keycloak.
- **Keycloak** foi utilizado para autenticação e controle de acessos.
- **Docker** foi configurado para facilitar a execução do projeto sem dependências externas.
- **A entrega parcial** se deu por conta do tempo limitado para finalizar todas as funcionalidades e ajustes.

