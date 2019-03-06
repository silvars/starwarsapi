# STAR WARS API

Antes de rodar a aplicação devemos criar o database e o usuario/senha no banco de dados.
Para criar siga as instruções abaixo:

## Criando a base de dados no MySql
create database starwarsapi; -- Create the new database

create user 'starwars'@'%' identified by 'Starwars1@'; -- Creates the user

grant all on starwarsapi.* to 'starwars'@'%'; -- Gives all the privileges to the new user on the newly created database
