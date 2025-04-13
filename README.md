# Documentação do Sistema CRUD de Clientes
## Descrição Geral
Este é um sistema de gerenciamento de clientes desenvolvido com Spring Boot que implementa operações CRUD (Create, Read, Update, Delete). O sistema utiliza Jakarta EE com Spring Data JPA e Spring MVC, sendo construído com Java SDK 17.
## Arquitetura do Sistema
O sistema segue uma arquitetura em camadas típica de aplicações Spring:
1. **Camada de Apresentação (Controllers)**
    - Responsável por receber requisições HTTP e retornar respostas adequadas
    - Implementa endpoints REST para operações CRUD

2. **Camada de Serviço**
    - Contém a lógica de negócios do sistema
    - Faz a ponte entre os controllers e os repositórios

3. **Camada de Persistência (Repositories)**
    - Interface com o banco de dados
    - Utiliza Spring Data JPA para operações com o banco de dados

4. **Camada de Entidades/DTOs**
    - Representa os objetos de domínio da aplicação
    - Utiliza DTOs para transferência de dados

## Principais Componentes
### Classe Principal
``` java
@SpringBootApplication
public class CrudClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudClientApplication.class, args);
    }
}
```
### Entidades
A entidade `Client` representa um cliente no sistema com os seguintes atributos:
- `id`: Identificador único (Long)
- `name`: Nome do cliente (String)
- `cpf`: CPF do cliente (String)
- `income`: Renda do cliente (Double)
- `birthDate`: Data de nascimento (LocalDate)
- `children`: Número de filhos (Integer)

### DTOs
A classe `ClientDTO` é utilizada para transferência de dados entre as camadas do sistema, contendo os mesmos campos da entidade Client, além de validações como:
- `@NotBlank` para o nome, garantindo que seja preenchido
- `@PastOrPresent` para a data de nascimento, garantindo que não seja uma data futura

### Tratamento de Exceções
O sistema possui tratamento de exceções com classes específicas:
- `ResourceNotFoundException`: Tratamento para recursos não encontrados
- `ControllerExceptionHandler`: Manipulador global de exceções
- `CustomError` e `ValidationError`: Representações padronizadas de erros

## Configuração
O sistema utiliza perfis de configuração através dos arquivos:
- `application.properties`: Configurações gerais
- `application-test.properties`: Configurações específicas para ambiente de teste
- `import.sql`: Script para carga inicial de dados

## Como Executar
Para executar o sistema, basta rodar a classe `CrudClientApplication` que contém o método `main()`. O Spring Boot irá inicializar o servidor e disponibilizar as APIs REST automaticamente.
## Endpoints Disponíveis
O sistema disponibiliza endpoints REST para gerenciamento de clientes, permitindo:
- Cadastro de novos clientes
- Consulta de clientes (individual ou listagem)
- Atualização de dados de clientes
- Remoção de clientes

## Validações
O sistema possui validações para garantir a integridade dos dados:
- Validação de campos obrigatórios
- Validação de data de nascimento (não pode ser futura)

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Spring MVC
- Jakarta EE
- Banco de dados H2 (para testes)
