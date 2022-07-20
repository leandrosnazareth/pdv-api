<h1  align="center">Bem vindo ao PDV Web!</h1>
<p  align="center">

<img  src="https://i.ibb.co/S69XqSw/logopdv.png" />

</p>
<p display="inline-block">

<img  src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />

<a  href="https://github.com/CaduGimenes/vendas/blob/master/LICENSE">

<img  alt="License: MIT"  src="https://img.shields.io/badge/License-MIT-yellow.svg"  target="_blank" />

</a>

<img  src="https://img.shields.io/github/issues/leandrosnazareth/pdv-api.svg" />

<img  src="https://img.shields.io/github/forks/leandrosnazareth/pdv-api.svg" />

<img  src="https://img.shields.io/github/stars/leandrosnazareth/pdv-api.svg" />
 
</p>

**PDV Web** é um sistema, **Open Source**, de vendas de frente de caixa. O PDV é a ferramenta para registar vendas de forma rápida e confiável. Este projeto foi pensando em atender as necessidades de  pequenos empreendedores do ramo de mercearias, minimercados e afins.

## Objetivo do projeto
Disponibilizar um projeto de código fonte **Open Source** de um sistema de vendas em PDF desenvolvido  utilizando as tecnologias listadas abaixo:

- Spring Boot;
- Spring Data JPA (com Hibernate);
- Spring Starter Test;
- Spring Boot Starter Web;
- Angular Material;
- Flyway;
- Lombok;
- Maven;
- JUnit;
- Swagger (Exposição da API REST do sistema de forma padronizada);
- Mockito;
- Testcontainers;
- Docker;
- TDD (Test-Driven Development) testes unitários e de integração;
- CoreUI Bootstrap Admin Dashboard Template.

## O sistema

### Dashboard
![](https://i.ibb.co/SVVBrJ2/dashboard.png)

### PDV

![](https://i.ibb.co/QrW4mMw/pdv.png)

### Produto

![](https://i.ibb.co/HXVssNT/cad-Produto.png)

![](https://i.ibb.co/LnPxG4r/list-Produto.png)

## Swagger Documentation

![](https://i.ibb.co/N3vy5Dk/Swagger.png)

## Pré-requisitos

* npm >= 8.3.1

* java = 11.0.14

* mysql >= 8.0.29

* Angular = 12.1.3

* Node >= 16.13.1

## Instalação APP

Faça download do NodeJs caso não tenha instalando em sua máquina

```sh

https://nodejs.org/en/download/

```

1 - Git clone: https://github.com/leandrosnazareth/pdv-app

2 - Abrir projeto na IDE Visual Studio Code

3 - No terminal, na pasta do projeto executar os comando abaixo

4 - npm install @angular/cli@ 12.1.3

5 - ng serve --open

### Uso

O projeto está localizado em
  

```sh

localhost:4200

```


## Instalação API

1 - Git clone: https://github.com/leandrosnazareth/pdv-api

2 - Faça o download e instalação padrão do Docker

https://docs.docker.com/desktop/windows/install/

3- execute o comando abaixo para instalar o mysql no docker

docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

4- Execute o mysql no docker

4- Faça download do JDK 11 caso não tenha instalado em sua máquina

https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html

Execute o projeto...

## Uso

O projeto está localizado em
  

```sh

localhost:8080

```


## Autor

👤 **Leandro Nazareth**

* Github: [@leandrosnazareth](https://github.com/leandrosnazareth)

## 🤝 Contribuições

Contribuições, são bem vindas!<br />
Sinta-se à vontade para perguntar API [issues page](https://github.com/leandrosnazareth/pdv-api/issues).

## Mostre seu apoio

Dê uma ⭐️ se este projeto te ajudou!

## 📝 Licença

Copyright © 2022 [Leandro Nazareth](https://github.com/leandrosnazareth).<br />

Este projeto está sob lincença [MIT](https://github.com/leandrosnazareth/pdv-app/blob/master/LICENSE).

Template usado no projeto [CoreUi](https://coreui.io/angular/)
