# Projeto CRUD de Produtos com Hibernate JPA

Este é um projeto Java que implementa as operações CRUD (Create, Read, Update, Delete) para a entidade de produtos. 
O projeto utiliza Hibernate JPA para interagir com um banco de dados relacional. 
A aplicação permite cadastrar produtos com informações como nome, marca, quantidade em estoque e valor unitário.

### Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Java Development Kit (JDK)
- Apache Maven
- Banco de dados MySQL (ou outro banco de dados suportado pelo Hibernate)

### Configuração do Banco de Dados

1. Crie um banco de dados chamado "estoque" no seu servidor MySQL.
2. Caso queira copiar os dados da tabela para não conter erros - aqui está:
   ```
   create table produtos
   (
      id int not null auto_increment,
      nome varchar(100) not null,
      marca varchar(100) default null,
      quantidade int,
      valor_unitario double,
      primary key(id)
   );

## Configuração do Projeto

1. No arquivo `CRUD/src/main/resources/META-INF/persistence.xml` altere os dados `url, user e password` para que você possa executar corretamente o código.
2. Em `Project Files`, altere o arquivo `pom.xml` implementando os seguintes códigos logo abaixo de `<packaging>jar</packaging>`:
   ```
   <dependencies>
        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.3.1.Final</version>
        </dependency>
        
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.31</version>
        </dependency>
    </dependencies>

## Executando o Projeto

Execute o aplicativo utilizando o Maven:

```bash
mvn clean install 
mvn exec:java
