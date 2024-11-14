## COMP303 Assignment 3: Blood Bank Information System

This is a Spring Boot CRUD application that allows users to create, read, update, and delete the following entities:
1. Seekers -> `Seeker.java`
2. Blood Groups -> `BloodGroup.java`
3. Blood Stocks -> `BloodStocks.java`

where CRUD stands for Create, Read, Update, and Delete but its similar in a sense to how Spring-Boot does their annotations 
with PUT, POST, GET, and DELETE mapping annotations.

More specifically, this application is a RESTful API and follows the `Service-Controller-Repository` pattern,
but without the use of a Repository as it was not required as part of the assignment.

## Getting Started

### Requirements
- Java 17
- Spring Boot 3+
- Maven
- Jakarta Validation API
- Spring Devtools
- Lombok (Optional but Preferred)

## Routing and Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/v1/seekers` | Get all seekers |
| GET    | `/api/v1/seekers/{id}` | Get a seeker by id |
| POST   | `/api/v1/seekers` | Create a seeker |
| PUT    | `/api/v1/seekers/{id}` | Update a seeker |
| DELETE | `/api/v1/seekers/{id}` | Delete a seeker |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/v1/blood-stocks` | Get all blood stocks |
| GET    | `/api/v1/blood-stocks/{id}` | Get a blood stock by id |
| POST   | `/api/v1/blood-stocks` | Create a blood stock |
| PUT    | `/api/v1/blood-stocks/{id}` | Update a blood stock |
| DELETE | `/api/v1/blood-stocks/{id}` | Delete a blood stock |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/v1/blood-banks` | Get all blood banks |
| GET    | `/api/v1/blood-banks/{id}` | Get a blood bank by id |
| POST   | `/api/v1/blood-banks` | Create a blood bank |
| PUT    | `/api/v1/blood-banks/{id}` | Update a blood bank |
| DELETE | `/api/v1/blood-banks/{id}` | Delete a blood bank |

There are also some other endpoints that are not listed here like Global Exception Handler. For the sake of brevity,
they are not listed and are not that important to mention.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Jakarta Validation API](https://beanvalidation.org/)
- [Spring Devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html)
- [Lombok](https://projectlombok.org/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.