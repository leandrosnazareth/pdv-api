# PDV Web

<p align="center">
  <img src="https://i.ibb.co/S69XqSw/logopdv.png" alt="PDV Web Logo" width="200"/>
</p>

<p align="center">
  <img alt="Versão" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000"/>
  <img alt="Licença" src="https://img.shields.io/badge/License-MIT-yellow.svg"/>
  <a href="https://github.com/leandrosnazareth/pdv-api/issues">
    <img alt="Issues da API" src="https://img.shields.io/github/issues/leandrosnazareth/pdv-api.svg"/>
  </a>
  <a href="https://github.com/leandrosnazareth/pdv-app/issues">
    <img alt="Issues do App" src="https://img.shields.io/github/issues/leandrosnazareth/pdv-app.svg"/>
  </a>
  <a href="https://github.com/leandrosnazareth/pdv-api">
    <img alt="Stars da API" src="https://img.shields.io/github/stars/leandrosnazareth/pdv-api.svg"/>
  </a>
</p>

<p align="center">
  <strong>PDV Web</strong> é um sistema de Ponto de Venda (PDV) <strong>Open Source</strong>, projetado para ser uma ferramenta de frente de caixa rápida e confiável. O projeto foi criado para atender às necessidades de micro e pequenos empreendedores, como mercearias, minimercados e estabelecimentos similares.
</p>

## ✨ Funcionalidades

* **🖥️ Dashboard Intuitivo:** Visualize as principais métricas de vendas rapidamente.
* **🛒 Frente de Caixa (PDV):** Interface otimizada para agilizar o registro de vendas.
* **📦 Gestão de Produtos:** Cadastre e gerencie seus produtos de forma simples.
* **📄 Documentação de API:** API REST documentada com Swagger para fácil integração.

<details>
  <summary><b>📸 Screenshots do Sistema</b></summary>
  <br/>
  <p align="center">
    <b>Dashboard</b><br>
    <img src="https://i.ibb.co/SVVBrJ2/dashboard.png" alt="Dashboard Screenshot">
    <br/><br/>
    <b>PDV (Frente de Caixa)</b><br>
    <img src="https://i.ibb.co/QrW4mMw/pdv.png" alt="PDV Screenshot">
    <br/><br/>
    <b>Cadastro de Produto</b><br>
    <img src="https://i.ibb.co/HXVssNT/cad-Produto.png" alt="Product Registration Screenshot">
    <br/><br/>
    <b>Listagem de Produtos</b><br>
    <img src="https://i.ibb.co/LnPxG4r/list-Produto.png" alt="Product List Screenshot">
    <br/><br/>
    <b>Documentação Swagger</b><br>
    <img src="https://i.ibb.co/N3vy5Dk/Swagger.png" alt="Swagger Documentation Screenshot">
  </p>
</details>

## 💻 Tecnologias Utilizadas

O projeto é dividido em duas partes principais: um backend robusto e um frontend reativo.

| Backend (API)                                 | Frontend (APP)                      |
| --------------------------------------------- | ----------------------------------- |
| **Java 11** & **Spring Boot** | **Angular 12** & **TypeScript** |
| **Spring Data JPA** (com Hibernate)           | **Angular Material** |
| **Maven** para gerenciamento de dependências  | **Node.js** & **NPM** |
| **MySQL 8** como banco de dados               | **CoreUI** para o template          |
| **Flyway** para migração de banco de dados    |                                     |
| **Docker** & **Testcontainers** |                                     |
| **JUnit 5**, **Mockito** & **TDD** |                                     |
| **Swagger** para documentação da API REST     |                                     |
| **Lombok** |                                     |

## 🚀 Como Começar

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local.

### Pré-requisitos

Certifique-se de que você tem as seguintes ferramentas instaladas:

* **Git:** Para clonar os repositórios.
* **Docker:** Para executar o banco de dados MySQL.
* **Java (JDK) 11:** Para executar o backend.
* **Node.js >= 16.13.1** (com **NPM >= 8.3.1**): Para executar o frontend.
* **Angular CLI 12.1.3:** `npm install -g @angular/cli@12.1.3`

### Instalação e Execução

A instalação é dividida em duas etapas: **Backend** e **Frontend**. Execute-as em terminais separados.

#### 1. Backend (API)

```bash
# Clone o repositório da API
git clone [https://github.com/leandrosnazareth/pdv-api.git](https://github.com/leandrosnazareth/pdv-api.git)

# Navegue até o diretório
cd pdv-api

# Inicie um container Docker com o MySQL
# (Aguarde alguns segundos para o banco de dados iniciar completamente)
docker run --name mysql-pdv -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.29

# Execute a aplicação Spring Boot com Maven
# (O Flyway cuidará da criação das tabelas automaticamente)
./mvnw spring-boot:run
```

🎉 O backend estará em execução em `http://localhost:8080`.

#### 2. Frontend (APP)

```bash
# Em um novo terminal, clone o repositório do App
git clone [https://github.com/leandrosnazareth/pdv-app.git](https://github.com/leandrosnazareth/pdv-app.git)

# Navegue até o diretório
cd pdv-app

# Instale as dependências do projeto
npm install

# Inicie o servidor de desenvolvimento do Angular
ng serve --open
```

🎉 O frontend abrirá automaticamente em seu navegador em `http://localhost:4200`.

## 🤝 Como Contribuir

Contribuições são o que tornam a comunidade de código aberto um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito bem-vinda**.

1.  Faça um **Fork** do projeto.
2.  Crie uma **Branch** para sua feature (`git checkout -b feature/AmazingFeature`).
3.  Faça o **Commit** de suas mudanças (`git commit -m 'Add some AmazingFeature'`).
4.  Faça o **Push** para a Branch (`git push origin feature/AmazingFeature`).
5.  Abra um **Pull Request**.

Sinta-se à vontade para abrir uma issue na [página de issues da API](https://github.com/leandrosnazareth/pdv-api/issues) ou do [App](https://github.com/leandrosnazareth/pdv-app/issues).

## ⭐️ Mostre seu Apoio

Se este projeto te ajudou de alguma forma, dê uma ⭐️ nos repositórios!

## 📝 Licença

Copyright © 2022 [Leandro Nazareth](https://github.com/leandrosnazareth).<br />
Este projeto está sob a licença [MIT](https://github.com/CaduGimenes/vendas/blob/master/LICENSE).