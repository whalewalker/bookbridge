# Library Management System

This project is a Library Management System API built using Spring Boot. It provides RESTful endpoints for managing books, patrons, and borrowing records.

## Technologies

- Java 21
- Spring Boot 3.2.5
- PostgresSQL
- Spring WebFlux
- Spring Security
- Spring Cache
- OpenAPI (Swagger)

## Getting Started

### Prerequisites

- Java 21 or higher
- PostgresSQL
- Maven

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/library-management-system.git
```

2. Navigate to the project directory:

```bash
cd library-management-system
```

3. Configure the PostgreSQL database connection in the `application.properties` file.

4. Build the project:

```bash
mvn clean install
```

5. Run the application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html` when the application is running. You can explore and test the available endpoints using the interactive Swagger UI.

## API Endpoints

### Book Management

- `GET /api/books`: Retrieve a list of all books.
- `GET /api/books/{id}`: Retrieve details of a specific book by ID.
- `POST /api/books`: Add a new book to the library.
- `PUT /api/books/{id}`: Update an existing book's information.
- `DELETE /api/books/{id}`: Remove a book from the library.

### Patron Management

- `GET /api/patrons`: Retrieve a list of all patrons.
- `GET /api/patrons/{id}`: Retrieve details of a specific patron by ID.
- `POST /api/patrons`: Add a new patron to the system.
- `PUT /api/patrons/{id}`: Update an existing patron's information.
- `DELETE /api/patrons/{id}`: Remove a patron from the system.

### Borrowing Management

- `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book.
- `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book by a patron.

## Security

This application implements JSON Web Token (JWT) based authentication to protect the API endpoints. When a user authenticates with valid credentials, the application generates a JWT token that is used for subsequent requests. The token is typically included in the `Authorization` header of HTTP requests.

To authenticate and obtain a JWT token, send a POST request to the `/authenticate` endpoint with the user's credentials (e.g., username and password). The response will contain the JWT token, which should be included in the `Authorization` header of subsequent requests.

Example:

```
POST /api/auth/register
Content-Type: application/json

{
  "email": "your-email",
  "password": "your-password"
}
```

Response:

```json
{
  "token": "your-jwt-token"
}
```

For subsequent requests, include the JWT token in the `Authorization` header:

```
GET /api/books
Authorization: Bearer your-jwt-token
```

## Caching

The application utilizes Spring's caching mechanisms to cache frequently accessed data, such as book details or patron information, to improve system performance.

## Logging

The application implements logging using Aspect-Oriented Programming (AOP) to log method calls, exceptions, and performance metrics of certain operations like book additions, updates, and patron transactions.

## Transaction Management

Declarative transaction management is implemented using Spring's `@Transactional` annotation to ensure data integrity during critical operations.

## Testing

Unit tests have been written to validate the functionality of API endpoints. The project uses JUnit, Mockito, and SpringBootTest for testing.

To run the tests, execute the following command:

```bash
mvn test
```