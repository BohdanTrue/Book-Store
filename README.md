#Online BookStore API
___A comprehensive Library Management API tailored for seamless integration into digital platforms, perfect for powering online book repositories and literary platforms📚.___

###👨‍💻Tech stack
- [Spring Boot](https://spring.io/projects/spring-boot/): provides a set of pre-built templates and conventions for creating stand-alone, production-grade Spring-based applications.
- [Hibernate](https://hibernate.org/): simplifies the interaction between Java applications and databases by mapping Java objects to database tables and vice versa.
- [Spring Security](https://spring.io/projects/spring-security/): provides features like authentication, authorization, and protection against common security threats.
- [Spring Web](https://spring.io/projects/spring-ws/): includes tools for handling HTTP requests, managing sessions, and processing web-related tasks.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa/): provides a higher-level abstraction for working with databases and includes support for JPA (Java Persistence API).
- [Lombok](https://projectlombok.org/): helps reduce boilerplate code by automatically generating common code constructs (like getters, setters, constructors, etc.) during compile time.
- [Mapstruct](https://mapstruct.org/): generates mapping code based on annotations, reducing the need for manual, error-prone mapping code.
- [Liquibase](https://www.liquibase.com/): helps manage database schema changes over time, making it easier to track and deploy database updates.
- [Swagger](https://swagger.io/): provides a framework for generating interactive API documentation, allowing developers to understand, test, and use APIs more easily.
- [Docker](https://www.docker.com/): provides a consistent and reproducible way to deploy applications across different environments.
- [Postman](https://www.postman.com/): allows developers to create and send HTTP requests to test APIs, monitor responses, and automate testing workflows.

###🚦Endpoints of controllers
- _AuthenticationController_: handles user registration and authorization.
- _BookController_: handles book-related tasks, including the creation, modification, deletion, and retrieval of book information.
- _CartController_: oversees the status of the shopping cart, enabling users to add, modify, fetch, and remove items from the cart.
- _CategoryController_: handles category management, permitting administrators to perform actions such as creation, modification, retrieval, and deletion.
- _OrderController_: manages order-related tasks, including the creation, modification, deletion, and retrieval of order history.#Online BookStore API
  ___A comprehensive Library Management API tailored for seamless integration into digital platforms, perfect for powering online book repositories and literary platforms📚.___


###📦Postman Collection
For your convenience, i've created a Postman collection that includes sample requests for various API endpoints. You can download it [here](https://www.postman.com/navigation-administrator-66332846/workspace/workspace/collection/31486926-0ced58cf-7634-4bf9-ad03-a78840858135?action=share&creator=31486926) and import it into your Postman workspace to get started quickly.

###🧐How to run and test my application?
First, download a [repository](https://github.com/BohdanTrue/book-store-spring).
You can use git console command:

```bash
git clone https://github.com/BohdanTrue/book-store-spring.git
```

Build a project using **Maven**:
```bash
mvn clean install
```
Then, rise a **Docker** container of your app:
```bash
docker build -t {your-image-name} .
docker-compose build
docker-compose up
```
Also, you can run this project without docker, but before that, you need to configure the connection to your local database in the application properties. Run this command after that:
```bash
 mvn spring-boot:run
```

That's all you need to know to start! 🎉

###🥊Challenges and Solutions

######Challenges
During the implementation of Spring Security within our Online Book Store API, several challenges were encountered, requiring thoughtful solutions:

- Complex Configuration: Spring Security can be intricate to configure, particularly when dealing with custom authentication and authorization requirements.

- Token-Based Authentication: The integration of JWT (JSON Web Tokens) for token-based authentication posed challenges related to token creation, validation, and secure storage.

- User Management: The efficient management of user roles, permissions, and access control within the API needed a structured approach.

######Solutions Implemented
To overcome these challenges and establish a robust and secure authentication and authorization system, the following solutions were put into effect:

- Detailed Configuration Documentation: The Spring Security configuration was documented comprehensively, providing thorough explanations for each aspect. This documentation offers clarity for both contributors and users on the setup of authentication and authorization.

- JWT Integration and Best Practices: JWT was adopted as the authentication mechanism, adhering to industry best practices. Established libraries were utilized, and security guidelines for token creation, validation, and storage were followed.

- Custom UserDetailsService: A custom UserDetailsService was implemented to manage user roles, permissions, and access control. This customization allowed for user management tailored to specific requirements while maintaining security.

By solving these challenges and implementing solutions, a secure and reliable authentication and authorization system using Spring Security was established. This ensures the protection of user data and sensitive operations within our Online Book Store API, creating a secure and trustworthy environment for users.

###🎯Happy coding!
