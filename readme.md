# Aplicação de cadastro de cliente e endereços 
Aplicação criada em JavaFX para efetuar o cadastro de endereços e clientes dentro de um banco de dados

## Tecnologia
* Java 20
* Java FX
* PostgreSQL
* SpringBoot

## Banco de dados
SQL Do Banco de dados, nome da base de dados dever ser boing

````

CREATE TABLE public.clientes (
id serial not null primary key,
nome varchar(45) NOT NULL,
documento  varchar (45) not NULL,
rg numeric (10, 2),
email varchar (100),
telefone numeric (10, 2)

);

INSERT INTO clientes (nome, documento, rg, email, telefone) VALUES ('adrian', 'RG' , 2142, 'adrianbonatto@hotmai.com', 9747812);

insert into endereco (id_cliente, cep, bairro, numero, cidade, estado) values (1, 88817300, 'Vila zuleima', 63, 'Criciuma', 'SC');

create table public.endereco (
id_cliente int not null primary key,
cep numeric(10, 2),
bairro varchar(100),
numero int,
cidade varchar(45),
estado varchar(2)
)

select * from clientes c

drop table clientes;
````