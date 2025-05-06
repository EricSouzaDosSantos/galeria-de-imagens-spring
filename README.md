# Galeria de Imagens - Spring + AWS S3

Este projeto é uma API REST desenvolvida com Java e Spring Boot, que permite o upload, listagem, atualização e remoção de imagens armazenadas na AWS S3. O projeto tem como objetivo o desenvolvimento dos meus conhecimentos em serviços de nuvem AWS, em especial o S3, além da prática com arquitetura limpa (Clean Architecture).

## Objetivo do Projeto

- Aprimorar meus conhecimentos em serviços da aws.
- Implementar uma arquitetura limpa separando responsabilidades em camadas bem definidas.
- Criar uma API com autenticação JWT.
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
```

## Funcionalidades da Galeria de Imagens
Todos os endpoints abaixo exigem um token JWT no header:
```
Authorization: Bearer <token_jwt>
```
### Endpoint: /api/v1/images
| Método | Rota | Descrição                           |
|--------|------|-------------------------------------|
| POST   | 	/ 	 | Faz upload de uma imagem            |
| GET	   | /	   | Retorna todas as imagens do usuário |
| PUT	| /{oldImageUrl} | 	Atualiza uma imagem existente      |      
| DELETE |	/{imageURL} | 	Remove uma imagem do S3 e da API   |

### Organização em Camadas
- interfaces.controller: Camada de entrada (Controllers REST)

- application.dto: Transferência de dados (DTOs)

- domain.model: Modelos de entidade

- domain.service: Regras de negócio

- usecase.*: Casos de uso isolados por responsabilidade

- infrastructure.security: Configuração de segurança e autenticação

- infrastructure.s3: Integração com AWS S3

### Requisitos para Rodar o Projeto
- Java 17 instalado

- Credenciais da AWS configuradas no ~/.aws/credentials ou variáveis de ambiente





