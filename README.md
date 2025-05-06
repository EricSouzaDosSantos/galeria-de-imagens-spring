# Galeria de Imagens - Spring + AWS S3

Este projeto é uma API REST desenvolvida com Java e Spring Boot, que permite o upload, listagem, atualização e remoção de imagens armazenadas na AWS S3. O projeto tem como objetivo o aprimoramento de conhecimentos em serviços de nuvem AWS, em especial o S3, além da prática com arquitetura limpa (Clean Architecture).

## Objetivo do Projeto

- Aprimorar conhecimentos em serviços da aws.
- Implementar uma arquitetura limpa separando responsabilidades em camadas bem definidas.
- Criar uma API segura com autenticação JWT.
- Manipular imagens de usuários com integração ao AWS S3.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Web**
- **AWS SDK para Java (S3)**
- **Lombok**
- **Swagger (OpenAPI)**
- **Maven**
- **Banco de Dados PostgreSQL**
- **Arquitetura Limpa (Clean Architecture)**

## Autenticação

Autenticação via JWT com endpoints públicos para login e registro.

### Endpoint: `/auth`

| Método | Rota       | Descrição                          |
|--------|------------|------------------------------------|
| POST   | `/login`   | Realiza login e retorna o JWT      |
| POST   | `/register`| Cria um novo usuário               |

### Exemplo de Requisição de Login

```json
{
  "email": "user@email.com",
  "password": "senha123"
}
