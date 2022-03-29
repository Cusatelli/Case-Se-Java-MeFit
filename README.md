<div id="top"></div>

<div align="center">
  <img src="https://img.icons8.com/color/96/000000/bench-press-with-dumbbells.png" alt="Logo" width="96" height="96">
  <h3 align="center">MeFit Application</h3>
  <p align="center">
    Noroff Case: Group 1
    <br />
    <a href="#">View Demo</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
# 📙 Table of Contents
[📚 About the Project](-about-the-project)  
[⌛&nbsp; Install](-install)  
[💻 Usage](-usage)  
[⚙ Maintainers](-maintainers)  
[🤝 Contributing](-contributing)  
[㊗ Conventions](-conventions)  
[📝 License](-license)  
[📬 Contact](-contact)  

<!-- ABOUT THE PROJECT -->
# 📚 About the project
<img align="left" width="46" src="https://img.icons8.com/color/480/000000/rules-book.png">
<h3>&nbsp; <a href="https://github.com/Cusatelli/Case-Se-Java-MeFit/wiki/Requirement-Specification">Requirement Specification</a></h3>

For a detailed list of all our requirements & analysis please refer to the [Requirement Specification](https://github.com/Cusatelli/Case-Se-Java-MeFit/wiki/Requirement-Specification) documentation.


<img align="left" width="46" src="https://img.icons8.com/color/480/000000/tree-structure.png">
<h3>&nbsp; Dependencies</h3>

```
Java            SE 17

React           17.0.2
  Calendar      3.7.0
  Dom           17.0.2
  Hook-Form     7.28.0
  Router-Dom    6.2.2
  Scripts       5.0.0
  
Web-Vitals      2.1.4
    
Spring-Boot     2.6.4
  WEB           2.6.4
  TOMCAT        2.6.4
  JDBC          2.6.2
  JPA           2.6.2
  
Spring-Security 5.6.2
  OAuth2        5.6.2
  
Hibernate
  Core          5.6.6
  Annotations   5.1.2

Swagger         4.5.0
  OpenAPI       1.6.6
  
KeyCloak        16.1.1
  KeyCloak-JS   17.0.0

PostgreSQL      42.3.3
  Image         14-alpine
  
Docker          20.10.12

Lombok          1.18.22

Apache
  DBCP2         2.9.0
  CSV           1.9.0
```

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>

<!-- INSTALL -->
# ⌛&nbsp; Install
## Back-End
To get started with this application you will need to set up a few services.
1. Download [Docker Desktop](https://www.docker.com/products/docker-desktop).
2. Get the [Official Postgres Docker Image | 14-alpine](https://hub.docker.com/_/postgres).
    * See the [setup](#setup) section for a detailed installation guide.
3. Heroku Account.
4. IDE (We will be using **IntelliJ IDEA Ultimate Edition**).
5. Java JDK 17
6. Spring Boot 2.6.4
7. Gradle  
   Maven works too, but this setup will be using Gradle for demonstration purposes.

When you have completed above steps 1-7 you can go ahead and clone this repository.

```bash
# Https
git clone https://github.com/Cusatelli/Case-Se-Java-MeFit.git

# SSH
git clone git@github.com:Cusatelli/Case-Se-Java-MeFit.git
```

When the clone is finished you can open up the project and navigate to
`src > main > java > Main.java` & click `ᐈ Run 'Main'`.

You'll notice that it does not run and throws an error.
This is expected as we have not yet finished setting up our whole project yet.

The next steps will be to initialize our database & keycloak image via Docker containers.
1. To set up the database run:
   ```
   docker run -d --name postgres -e POSTGRES_PASSWORD=4242 -e POSTGRES_DB=myDB -p 5432:5432 postgres:14-alpine
   ```
   This will install and run our postgres database for us in our Docker Desktop app.
<br/>
<br/>
2. Similarly to run KeyCloak as a docker container, run:
   ```
   docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.1 start-dev
   ```
   This will install and run an instance of keycloak locally on our machine for us to connect to when developing.

## Front-End
```bash
Installation guide goes here...
```

<!-- USAGE -->
# 💻 Usage
```bash
How to use the repository goes here...
```

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>

<!-- MAINTAINERS -->
# ⚙ Maintainers
<a href="https://github.com/Cusatelli"><img src="https://avatars.githubusercontent.com/u/39692033?v=4" alt="Logo" width="50"></a>
<a href="https://github.com/meckan"><img src="https://avatars.githubusercontent.com/u/15233202?v=4" alt="Logo" width="50"></a>
<a href="https://github.com/OmarAbdiAli"><img src="https://avatars.githubusercontent.com/u/97539265?v=4" alt="Logo" width="50"></a>
<a href="https://github.com/Pizzarulle"><img src="https://avatars.githubusercontent.com/u/48913785?v=4" alt="Logo" width="50"></a>

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">⬆️</a>
</p>

<!-- CONTRIBUTING -->
# 🤝 Contributing
No active contributors.

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>

<!-- CONVENTIONS -->
# ㊗ Conventions
Format: `<type>(<scope>): <subject>`

`<scope>` is optional

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

Read more: [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) v1.0.0

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>

<!-- LICENSE -->
# 📝 License
No active license.

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>

<!-- CONTACT -->
# 📬 Contact
<a href="mailto:github.cusatelli@gmail.com"><img src="https://avatars.githubusercontent.com/u/39692033?v=4" alt="Logo" width="50"></a>

<p align="right"><!-- BACK TO TOP -->
  <a href="#top" align="right">🔝</a>
</p>
