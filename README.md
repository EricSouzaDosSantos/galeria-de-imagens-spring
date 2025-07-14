# Galeria de Imagens - Spring + AWS S3 + Clean Architecture

Este projeto é uma API REST desenvolvida com Java e Spring Boot, que permite o upload, listagem, atualização e remoção de imagens e albums armazenados no AWS S3. O projeto tem como objetivo o desenvolvimento dos meus conhecimentos em serviços de nuvem AWS, em especial o S3, além da prática com arquitetura limpa (Clean Architecture).

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

### Endpoint: /api/v1/albums
| Método | Rota | Descrição                          |
|--------|------|------------------------------------|
| POST   | 	/ 	 | Cria um novo álbum                 |
| GET	   | /	   | Retorna todos os álbuns do usuário |
| GET   | /{albumId} | Retorna todas as imagens de um álbum específico |
| PUT	| /{albumId} | Atualiza um álbum existente        |
| DELETE |	/{albumId} | Remove um álbum      |

### Organização em Camadas
- interfaces.controller: Camada de entrada (Controllers REST)

- interfaces.dto: Transferência de dados (DTOs)

- interfaces.mapper: Mapeamento entre entidades de domínio/negócio e entidades de persistência

- infrastructure.security: Configuração e implementação de segurança e autenticação

- infrastructure.s3: Integração com AWS S3

- infrastructure.persistence: Implementação de repositórios e entidades de persistência

- infrastructure.configuration: Configurações gerais do Spring Boot 

- application.usecase.*: Casos de uso isolados por responsabilidade

- application.service: Serviços de aplicação que orquestram os casos de uso

- application.exception: Exceções específicas da aplicação

- core.model: Modelos de entidade

- core.gateways: Interfaces de repositórios e serviços

### Requisitos para Rodar o Projeto
- Java 17+ instalado

- Credenciais da AWS configuradas no ~/.aws/credentials ou variáveis de ambiente
- Banco de dados PostgreSQL configurado e rodando
- Arquivo `.env` configurado com as credenciais do banco de dados e AWS S3

#### Exemplo de arquivo `.env`
```properties
DB_URL=jdbc:postgresql://localhost:5432/galeria_imagens
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha

JWT_SECRET=sua_chave_secreta
JWT_ISSUER=seu_emissor
JWT_EXPIRATION=seu_tempo_de_expiracao_em_milisegundos

AWS_S3_BUCKET_NAME=seu_bucket_s3
AWS_S3_REGION=sua_regiao_s3
```





