# Moodle Clone Backend

Backend de uma plataforma de gestão escolar inspirada no Moodle. A aplicação
foi desenvolvida em Java 21 com Spring Boot e organiza o código segundo os
princípios de Clean Architecture apresentados por Robert C. Martin (Uncle
Bob).

## Objetivo

O sistema disponibiliza operações para:

- cadastrar, consultar, atualizar, ativar e desativar usuários;
- atribuir e remover papéis de usuários;
- cadastrar e consultar cursos;
- atualizar informações de cursos, instrutores, período e categoria;
- publicar e arquivar cursos;
- criar, consultar, concluir e cancelar matrículas;
- consultar matrículas por aluno ou por curso.

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Maven
- Jakarta Bean Validation
- SpringDoc OpenAPI
- JUnit 5 e Spring Boot Test

## Arquitetura

O projeto utiliza uma arquitetura hexagonal baseada nos princípios da Clean
Architecture. A regra de dependência aponta para o centro: o domínio não
depende de Spring, JPA, HTTP ou banco de dados. Detalhes externos são
conectados ao núcleo por meio de portas e adaptadores.

```text
┌─────────────────────────────────────────────────────────────┐
│                    Adapter / Web (in)                       │
│ Controllers, DTOs e tratamento de exceções HTTP             │
└──────────────────────────────┬──────────────────────────────┘
                               │ usa
┌──────────────────────────────▼──────────────────────────────┐
│                         Application                          │
│ Casos de uso, comandos, portas e read models                │
└──────────────────────────────┬──────────────────────────────┘
                               │ aplica regras
┌──────────────────────────────▼──────────────────────────────┐
│                           Domain                             │
│ Entidades, objetos de valor, estados e invariantes           │
└─────────────────────────────────────────────────────────────┘
                               ▲
                               │ implementa portas
┌──────────────────────────────┴──────────────────────────────┐
│                 Adapter / Persistence (out)                  │
│ JPA entities, repositories, mappers e adapters               │
└─────────────────────────────────────────────────────────────┘
```

### Camada de domínio

Localizada em `domain`, contém o núcleo das regras de negócio:

- `User`: identidade, papéis, dados pessoais e ciclo de ativação;
- `Course`: instrutores, capacidade, carga horária e ciclo de publicação;
- `Enrollment`: matrícula ativa, concluída ou cancelada;
- `Email`, `Phone` e `Address`: objetos de valor com validações próprias;
- enums e exceções específicas do domínio;
- `BaseEntity`: identidade e controle de datas da entidade.

As entidades protegem suas próprias invariantes. Por exemplo, um curso não
pode ser criado sem instrutor, uma matrícula não pode ser concluída duas vezes
e um usuário deve manter pelo menos um papel.

### Camada de aplicação

Localizada em `application`, coordena os casos de uso sem conhecer detalhes
de transporte ou persistência:

- `usecase`: ações como `CreateUserUseCase`, `PublishCourseUseCase` e
  `CreateEnrollmentUseCase`;
- `port.in`: comandos de entrada usados para transportar intenções para os
  casos de uso;
- `port.out`: contratos de persistência e consulta;
- `readmodel`: modelos de leitura específicos para respostas;
- `exception`: erros de aplicação, como conflito e recurso não encontrado.

Cada caso de uso recebe suas dependências por construtor e depende de
interfaces. Isso permite testar as regras de aplicação com repositórios
substitutos, sem iniciar banco de dados ou servidor web.

### Adaptadores de entrada

Localizados em `adapter/in/web`, recebem requisições HTTP e fazem a conversão
entre DTOs e comandos da aplicação. Os controllers não implementam regras de
negócio; eles delegam a execução aos casos de uso.

Endpoints principais:

| Recurso | Base |
| --- | --- |
| Usuários | `/api/users` |
| Cursos | `/api/courses` |
| Matrículas | `/api/enrollments` |

As operações de criação retornam `201 Created`, consultas retornam `200 OK` e
operações de alteração de estado retornam `204 No Content`. O
`GlobalExceptionHandler` converte erros de domínio e aplicação em respostas
HTTP padronizadas.

### Adaptadores de saída

Localizados em `adapter/out/persistence`, implementam as portas definidas pela
aplicação:

- `repository`: interfaces Spring Data JPA;
- `entity`: representação persistente das entidades;
- `mapper`: conversão entre entidades JPA e objetos de domínio;
- `adapter`: implementação das portas de repositório e consulta.

Essa separação evita que entidades JPA vazem para o domínio ou para os
controllers.

### Configuração e inversão de dependência

`UseCaseConfig` registra os casos de uso como beans e injeta as portas
implementadas pelos adaptadores. A composição das dependências fica na borda
da aplicação, mantendo o centro independente de frameworks.

## Estrutura de diretórios

```text
src/
├── main/
│   ├── java/moodle_clone/backend/
│   │   ├── adapter/
│   │   │   ├── in/web/
│   │   │   └── out/persistence/
│   │   ├── application/
│   │   │   ├── exception/
│   │   │   ├── port/in/
│   │   │   ├── port/out/
│   │   │   ├── readmodel/
│   │   │   └── usecase/
│   │   ├── config/
│   │   └── domain/
│   └── resources/
└── test/
    └── java/moodle_clone/backend/
```

## Pré-requisitos

- JDK 21;
- PostgreSQL em execução;
- Maven 3.9 ou o Maven Wrapper incluído no projeto;
- banco de dados criado para a aplicação.

## Configuração do banco

A aplicação utiliza as seguintes variáveis de ambiente:

```text
DB_NAME=moodle
DB_USERNAME=postgres
DB_PASSWORD=senha
```

O endereço padrão configurado é:

```text
jdbc:postgresql://localhost:5432/${DB_NAME}
```

As demais configurações estão em
[`application.properties`](src/main/resources/application.properties).

## Executando a aplicação

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Com Maven instalado:

```bash
mvn spring-boot:run
```

Por padrão, a API fica disponível em `http://localhost:8080`.

## Documentação da API

Com a aplicação em execução, a especificação OpenAPI pode ser acessada em:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Testes

Para executar todos os testes:

```powershell
.\mvnw.cmd test
```

Para executar somente os testes de domínio:

```powershell
.\mvnw.cmd -Dtest="moodle_clone.backend.domain.**" test
```

Os testes unitários de domínio validam entidades, objetos de valor, transições
de estado e invariantes sem dependência de banco de dados ou contexto Spring.

## Princípios de código

O projeto segue práticas alinhadas à Clean Architecture e ao Clean Code:

- regras de negócio concentradas no domínio;
- dependência de abstrações por meio de portas;
- injeção de dependência por construtor;
- casos de uso pequenos e com responsabilidade única;
- DTOs separados dos modelos de domínio;
- validações próximas aos dados e comportamentos que protegem;
- nomes expressivos para classes, comandos e operações;
- testes unitários independentes de infraestrutura;
- conversões explícitas entre camadas;
- tratamento centralizado de exceções na borda HTTP.

## Licença

Este projeto é de uso educacional e serve como estudo de arquitetura limpa,
modelagem de domínio e desenvolvimento de APIs REST com Java e Spring Boot.
