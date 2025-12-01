# Eventify

A RESTful API for event and venue management built with Spring Boot. This application allows you to create, manage, and organize events along with their associated venues.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Database](#database)
- [Configuration](#configuration)
- [Development](#development)

## 🎯 Overview

Eventify is a comprehensive event management system that provides a complete REST API for managing events and venues. The application follows a clean architecture pattern with clear separation of concerns, making it maintainable and scalable.

## ✨ Features

### Event Management

- Create new events with detailed information
- Retrieve events by ID or list all events
- Filter events by venue
- Update existing event information
- Delete events
- Associate events with venues

### Venue Management

- Create and manage event venues
- Store complete venue information (name, address, city, state, country, zip code)
- Retrieve venues by ID or list all venues
- Update venue details
- Delete venues

### Additional Features

- Input validation with detailed error messages
- Global exception handling
- Interactive API documentation with Swagger/OpenAPI
- H2 in-memory database for development
- RESTful API design following best practices

## 🛠 Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.5.7** - Application framework
  - Spring Data JPA - Data persistence
  - Spring Web - REST API
  - Spring Validation - Input validation
  - Spring DevTools - Development utilities
- **H2 Database** - In-memory database for development
- **Lombok** - Reduces boilerplate code
- **SpringDoc OpenAPI** - API documentation (Swagger UI)
- **Maven** - Dependency management and build tool

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/codeup/eventify/
│   │   ├── config/
│   │   │   └── SwaggerConfig.java          # Swagger/OpenAPI configuration
│   │   ├── domain/
│   │   │   ├── Event.java                  # Event domain model
│   │   │   └── Venue.java                  # Venue domain model
│   │   ├── entity/
│   │   │   ├── EventEntity.java            # Event JPA entity
│   │   │   └── VenueEntity.java            # Venue JPA entity
│   │   ├── mapper/
│   │   │   ├── EventMapper.java            # Event DTO/Entity mapper
│   │   │   └── VenueMapper.java            # Venue DTO/Entity mapper
│   │   ├── repository/
│   │   │   ├── EventRepository.java        # Event data access
│   │   │   └── VenueRepository.java        # Venue data access
│   │   ├── service/
│   │   │   ├── EventService.java           # Event business logic
│   │   │   └── VenueService.java           # Venue business logic
│   │   ├── web/
│   │   │   ├── advice/
│   │   │   │   ├── ErrorResponse.java      # Error response model
│   │   │   │   └── GlobalExceptionHandler.java  # Global exception handling
│   │   │   ├── controller/
│   │   │   │   ├── EventController.java    # Event REST endpoints
│   │   │   │   └── VenueController.java    # Venue REST endpoints
│   │   │   └── dto/
│   │   │       ├── request/
│   │   │       │   ├── EventRequestDTO.java
│   │   │       │   └── VenueRequestDTO.java
│   │   │       └── response/
│   │   │           ├── EventResponseDTO.java
│   │   │           └── VenueResponseDTO.java
│   │   └── EventifyApplication.java        # Main application class
│   └── resources/
│       └── application.properties          # Application configuration
└── test/
    └── java/com/codeup/eventify/
        └── EventifyApplicationTests.java   # Application tests
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:

```bash
git clone <repository-url>
cd Eventify
```

2. Build the project:

```bash
./mvnw clean install
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

### Event Endpoints

| Method | Endpoint                      | Description            |
| ------ | ----------------------------- | ---------------------- |
| POST   | `/api/events`                 | Create a new event     |
| GET    | `/api/events`                 | Get all events         |
| GET    | `/api/events/{id}`            | Get event by ID        |
| GET    | `/api/events/venue/{venueId}` | Get events by venue ID |
| PUT    | `/api/events/{id}`            | Update an event        |
| DELETE | `/api/events/{id}`            | Delete an event        |

### Venue Endpoints

| Method | Endpoint           | Description        |
| ------ | ------------------ | ------------------ |
| POST   | `/api/venues`      | Create a new venue |
| GET    | `/api/venues`      | Get all venues     |
| GET    | `/api/venues/{id}` | Get venue by ID    |
| PUT    | `/api/venues/{id}` | Update a venue     |
| DELETE | `/api/venues/{id}` | Delete a venue     |

### Example Request: Create Event

```json
POST /api/events
{
  "title": "Rock Concert 2025",
  "description": "Amazing rock concert with live bands",
  "venueId": 1,
  "date": "2025-12-15",
  "hour": "2025-12-15T20:00:00",
  "price": 550.00,
  "hostedBy": "Live Nation"
}
```

### Example Request: Create Venue

```json
POST /api/venues
{
  "name": "Madison Square Garden",
  "country": "USA",
  "address": "4 Pennsylvania Plaza",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001"
}
```

## 💾 Database

The application uses an H2 in-memory database for development purposes.

### H2 Console Access

- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:eventifydb`
- **Username**: `sa`
- **Password**: (leave empty)

### Database Schema

**Events Table:**

- id (Long) - Primary Key
- title (String) - Event title
- description (String) - Event description
- date (String) - Event date
- hour (LocalTime) - Event time
- price (Double) - Ticket price
- hostedBy (String) - Event organizer
- venue_id (Long) - Foreign Key to Venues

**Venues Table:**

- id (Long) - Primary Key
- name (String) - Venue name
- country (String) - Country
- address (String) - Street address
- city (String) - City
- state (String) - State/Province
- zipCode (String) - Postal code

## ⚙️ Configuration

Key configuration properties in `application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:eventifydb
spring.jpa.hibernate.ddl-auto=update

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logging
logging.level.com.codeup.eventify=DEBUG
```

## 🔧 Development

### Building the Project

```bash
# Clean and build
./mvnw clean install

# Run tests
./mvnw test

# Run without tests
./mvnw clean install -DskipTests
```

### Running in Development Mode

The application includes Spring DevTools for automatic restart on code changes:

```bash
./mvnw spring-boot:run
```

### Code Style

The project uses:

- **Lombok** annotations to reduce boilerplate code
- **Jakarta Validation** for input validation
- **RESTful** design principles
- **DTO pattern** for request/response handling
- **Mapper pattern** for entity/domain conversion

## 📝 Architecture

The application follows a layered architecture:

1. **Controller Layer** - Handles HTTP requests and responses
2. **Service Layer** - Contains business logic
3. **Repository Layer** - Data access layer using Spring Data JPA
4. **Entity Layer** - JPA entities for database mapping
5. **Domain Layer** - Business domain models
6. **DTO Layer** - Data Transfer Objects for API communication
7. **Mapper Layer** - Converts between entities, domains, and DTOs

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the terms specified in the project configuration.

## 👥 Author

Adrian Gutierrez Regino

## 🐛 Known Issues

- Date and time handling uses String format; consider using LocalDate and LocalTime for better type safety
- In-memory database resets on application restart

## 🔮 Future Enhancements

- Add authentication and authorization
- Implement pagination for list endpoints
- Add search and filtering capabilities
- Support for recurring events
- Email notifications for event updates
- File upload for event images
- Integration with external calendar systems
- Migration to persistent database (PostgreSQL/MySQL)
