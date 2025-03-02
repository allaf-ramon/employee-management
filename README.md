# Employee Management System

Este projeto é um sistema de gestão de funcionários desenvolvido com Spring Boot, que permite realizar operações CRUD (Create, Read, Update, Delete) para dados de funcionários.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- MySQL (produção)
- H2 Database (desenvolvimento)
- Docker e Docker Compose
- Maven

## Estrutura do projeto

O projeto segue uma arquitetura em camadas:

- **Controllers**: Gerenciam as requisições HTTP
- **Services**: Contêm a lógica de negócio
- **Repositories**: Responsáveis pelo acesso aos dados
- **Entities**: Modelos de dados

## Entidades principais

- **Funcionario**: Representa um funcionário com seus dados pessoais e profissionais
- **FuncionarioLog**: Mantém um registro das alterações realizadas nos dados dos funcionários

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de funcionários
- Auditoria automática de todas as operações

## Endpoints da API

- `GET /funcionario/{id}`: Retorna os dados de um funcionário específico
- `POST /funcionario`: Cadastra um novo funcionário
- `PUT /funcionario/{id}`: Atualiza os dados de um funcionário
- `DELETE /funcionario/{id}`: Remove um funcionário do sistema

## Executando localmente

### Requisitos

- Java 21
- Maven
- Docker e Docker Compose (opcional)

### Com Docker

```bash
# Clone o repositório
git clone https://github.com/allaf-ramon/employee-management

# Entre na pasta do projeto
cd employee-management

# Execute com Docker Compose
docker-compose up
```

### Sem Docker

```bash
# Clone o repositório
git clone https://github.com/allaf-ramon/employee-management

# Entre na pasta do projeto
cd employee-management

# Execute com Maven
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configurações

O projeto possui diferentes perfis de configuração:

- **default**: Configuração padrão para ambiente de produção (MySQL)
- **dev**: Configuração para ambiente de desenvolvimento (H2 Database)

## Modelo de dados

O sistema armazena as seguintes informações dos funcionários:

- Nome
- Endereço
- Ramal
- Email profissional
- Departamento
- Salário
- Data de admissão

Para cada operação realizada, o sistema mantém um histórico detalhado com os dados antigos e novos.
