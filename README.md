# Club-Management-App
Club Management Application

It's an application to manage eployers of virtual music club.<br>
I made this application to learn some web development features.
It implements Spring Security and roles feature, mail senders, scheduled tasks, managing employers, their absences.
<br><br>
There aren't implemented some features like add new employee or interface to change settings and parameters connected with work and employers structure (CRUD operations to manage accounts, change contract types etc.) because it will be repeatable code. I made this application to train web programming skills and lot of things can be done better, or can be more expanded, but maybe in future I will come back to this project and refactor it.

### Used technologies
Java 11<br>
Spring Boot<br>
Spring Security<br>
Spring Data<br>
MySQL<br>
Thymeleaf<br>
Twitter Bootstrap<br>
Maven<br>

### Packaging
JAR

### What do you need?

You should create executable jar using maven. Just go to project directory and type this command in terminal:<br>
```mvn clean package```<br>

You should have configured mail server to provide e-mail sending feature implemented in application. You can modify the configuration in ```application.properties```.<br>
You also need working MySQL database to run application. Name of schema in application is configured to ```cm``` and needs to working on default MySQL port ```3306```.
