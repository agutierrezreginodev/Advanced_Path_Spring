# Eventify

A RESTful API for event and venue management built with Spring Boot following **Hexagonal Architecture (Ports & Adapters)**. This application allows you to create, manage, and organize events along with their associated venues with clean separation of concerns and technological independence.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Recent Updates & Refactoring](#recent-updates--refactoring)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Database](#database)
- [Configuration](#configuration)
- [Development](#development)

## 🎯 Overview

Eventify is a comprehensive event management system that provides a complete REST API for managing events and venues. The application follows **Hexagonal Architecture (Ports & Adapters)** with clear separation between domain logic and infrastructure, making it maintainable, testable, and technologically independent.

## ✨ Features

### Event Management

- Create new events with detailed information
- Retrieve events by ID or list all events
- Filter events by venue
- Update existing event information
- Delete events
- Associate events with venues
- **NEW**: Proper venue resolution with validation

### Venue Management

- Create and manage event venues
- Store complete venue information (name, address, city, state, country, zip code)
- Retrieve venues by ID or list all venues
- Update venue details
- Delete venues
- **NEW**: Full CRUD operations implemented

### Additional Features

- Input validation with detailed error messages
- Global exception handling
- Interactive API documentation with Swagger/OpenAPI
- Supabase PostgreSQL database for persistent storage
- RESTful API design following best practices
- **NEW**: Comprehensive test suite with 24 tests
- **NEW**: Proper error handling for missing venues
- **NEW**: Enhanced logging and debugging capabilities

## 🔄 Recent Updates & Refactoring

### 🆕 Supabase PostgreSQL Integration (December 2025)

#### ✅ Database Migration Completed

- **Migrated from H2 in-memory database to Supabase PostgreSQL**
- **Implemented session pooler configuration** for optimal connection management
- **Added Row Level Security (RLS)** for secure data access
- **Created comprehensive database schema** with proper foreign key constraints
- **Added automatic timestamp triggers** for created_at and updated_at fields
- **Configured SSL connections** for secure database communication
- **Updated all documentation** to reflect the new database setup

#### 🔧 Technical Improvements

- **Updated dependencies**: Added PostgreSQL driver 42.6.0 and Spring Data JPA 3.4.2
- **Enhanced configuration**: Optimized for production-ready cloud database
- **Persistent storage**: Data now persists across application restarts
- **Scalability**: Ready for production deployment with cloud database

### Major Bug Fixes (December 2025)

#### 🐛 NullPointerException Resolution

- **Issue**: Events API was failing with `NullPointerException` when creating events due to null venue resolution
- **Root Cause**: `EventRestAdapter.toEvent()` method was setting venue to null, causing `CreateEventUseCaseImpl` to fail when accessing `event.getVenue().getId()`
- **Solution**:
  - Modified `EventRestAdapter` to properly resolve venues using `VenueRepositoryPort`
  - Added proper null checking and validation in use cases
  - Enhanced error messages for better debugging

#### 🧪 Test Suite Enhancement

- **Added**: Comprehensive test coverage with 24 passing tests
- **Fixed**: All test failures and compilation errors
- **Improved**: Mock configurations and test data setup
- **Coverage**: Both Event and Venue REST adapters fully tested

#### 🔧 Code Quality Improvements

- **Resolved**: All lint warnings and unused imports
- **Documented**: Deprecated `@MockBean` usage with migration notes
- **Cleaned**: Removed unused fields and variables
- **Enhanced**: Error handling and validation logic

### Technical Debt Addressed

#### Port Configuration Fixes

- **Added**: Missing `update` method in `VenueRepositoryPort` interface
- **Implemented**: `update` method in `VenueJpaAdapter` concrete class
- **Fixed**: Inconsistent use case imports and dependencies

#### Test Infrastructure

- **Updated**: Test classes to properly mock all dependencies
- **Added**: Venue repository mocking in event tests
- **Fixed**: Mock bean declarations and test setup

### Architecture Improvements

#### Better Separation of Concerns

- **Enhanced**: Venue resolution logic moved to adapter layer
- **Improved**: Error handling with proper exception messages
- **Strengthened**: Domain layer independence from infrastructure

#### Testing Strategy

- **Implemented**: Comprehensive unit and integration tests
- **Added**: MockMvc for REST layer testing
- **Enhanced**: Test data management and cleanup

## 🛠 Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.5.7** - Application framework
  - Spring Data JPA - Data persistence
  - Spring Web - REST API
  - Spring Validation - Input validation
  - Spring DevTools - Development utilities
  - Spring Boot Test - Testing framework
- **Supabase PostgreSQL** - Cloud PostgreSQL database with persistent storage
- **Lombok** - Reduces boilerplate code
- **SpringDoc OpenAPI** - API documentation (Swagger UI)
- **MapStruct** - Bean mapping between layers
- **Maven** - Dependency management and build tool
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **MockMvc** - Spring MVC testing framework

## 🏗️ Project Structure

The application follows **Hexagonal Architecture** with clear separation of concerns:

```
src/
├── main/
│   ├── java/com/codeup/eventify/
│   │   ├── domain/                    # Domain Layer (Pure Business Logic)
│   │   │   ├── model/                  # Domain Entities (POJOs)
│   │   │   │   ├── Event.java         # Event domain model
│   │   │   │   └── Venue.java         # Venue domain model
│   │   │   └── ports/                  # Ports (Interfaces)
│   │   │       ├── in/                 # Input Ports (Use Cases)
│   │   │       │   ├── events/         # Event use cases
│   │   │       │   │   ├── CreateEventUseCase.java
│   │   │       │   │   ├── RetrieveEventUseCase.java
│   │   │       │   │   ├── UpdateEventUseCase.java
│   │   │       │   │   └── DeleteEventUseCase.java
│   │   │       │   └── venues/         # Venue use cases
│   │   │       │       ├── CreateVenueUseCase.java
│   │   │       │       ├── RetrieveVenueUseCase.java
│   │   │       │       ├── UpdateVenueUseCase.java
│   │   │       │       └── DeleteVenueUseCase.java
│   │   │       └── out/                # Output Ports (Repositories)
│   │   │           ├── EventRepositoryPort.java
│   │   │           └── VenueRepositoryPort.java
│   │   ├── application/                 # Application Layer
│   │   │   └── usecase/                # Use Case Implementations
│   │   │       ├── events/             # Event use case implementations
│   │   │       │   ├── CreateEventUseCaseImpl.java
│   │   │       │   ├── RetrieveEventUseCaseImpl.java
│   │   │       │   ├── UpdateEventUseCaseImpl.java
│   │   │       │   └── DeleteEventUseCaseImpl.java
│   │   │       └── venues/             # Venue use case implementations
│   │   │           ├── CreateVenueUseCaseImpl.java
│   │   │           ├── RetrieveVenueUseCaseImpl.java
│   │   │           ├── UpdateVenueUseCaseImpl.java
│   │   │           └── DeleteVenueUseCaseImpl.java
│   │   ├── infrastructure/            # Infrastructure Layer
│   │   │   ├── adapters/               # Adapters
│   │   │   │   ├── in/                 # Input Adapters (Web)
│   │   │   │   │   └── web/
│   │   │   │   │       ├── EventRestAdapter.java    # Event REST controller
│   │   │   │   │       ├── VenueRestAdapter.java    # Venue REST controller
│   │   │   │   │       └── dto/       # Data Transfer Objects
│   │   │   │   │           ├── request/
│   │   │   │   │           │   ├── EventRequestDTO.java
│   │   │   │   │           │   └── VenueRequestDTO.java
│   │   │   │   │           └── response/
│   │   │   │   │               ├── EventResponseDTO.java
│   │   │   │   │               └── VenueResponseDTO.java
│   │   │   │   └── out/                # Output Adapters (Persistence)
│   │   │   │       └── jpa/
│   │   │   │           ├── entity/    # JPA Entities
│   │   │   │           │   ├── EventEntity.java
│   │   │   │           │   └── VenueEntity.java
│   │   │   │           ├── mapper/    # MapStruct Mappers
│   │   │   │           │   ├── EventMapper.java
│   │   │   │           │   └── VenueMapper.java
│   │   │   │           ├── repository/ # Spring Data Repositories
│   │   │   │           │   ├── SpringEventRepository.java
│   │   │   │           │   └── SpringVenueRepository.java
│   │   │   │           ├── EventJpaAdapter.java   # Event Repository Implementation
│   │   │   │           └── VenueJpaAdapter.java   # Venue Repository Implementation
│   │   │   └── config/                  # Configuration Beans
│   │   │       ├── ApplicationConfig.java
│   │   │       └── SwaggerConfig.java   # OpenAPI Configuration
│   │   └── EventifyApplication.java    # Main application class
│   └── resources/
│       └── application.properties      # Application configuration
└── test/
    └── java/com/codeup/eventify/
        ├── EventifyApplicationTests.java   # Application integration tests
        └── infrastructure/
            └── adapters/
                └── in/
                    └── web/
                        ├── EventRestAdapterTest.java    # Event REST controller tests
                        └── VenueRestAdapterTest.java    # Venue REST controller tests
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

### Troubleshooting

If you encounter a port conflict:

```bash
# Kill process using port 8080 (Windows)
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Or run on a different port
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **H2 Console**: http://localhost:8080/h2-console

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
  "hour": "20:00:00",
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

## 🧪 Testing

The application includes a comprehensive test suite with **24 passing tests** covering:

### Test Coverage

- **REST Controllers**: Full CRUD operations for both Events and Venues
- **Input Validation**: Proper validation error handling
- **Mock Dependencies**: All external dependencies properly mocked
- **Error Scenarios**: Missing resources and invalid data handling

### Running Tests

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=EventRestAdapterTest
./mvnw test -Dtest=VenueRestAdapterTest

# Run with coverage report
./mvnw test jacoco:report
```

### Test Architecture

- **@WebMvcTest**: For REST layer testing
- **MockMvc**: For HTTP request/response testing
- **Mockito**: For dependency mocking
- **JSON Assertions**: For response validation

### Test Results

```
Tests run: 24, Failures: 0, Errors: 0, Skipped: 0
```

## 💾 Database

The application uses **Supabase PostgreSQL** for persistent data storage, providing a scalable and reliable cloud database solution.

### Supabase Configuration

The application connects to Supabase using the session pooler configuration for optimal performance:

- **Database**: PostgreSQL (hosted on Supabase)
- **Connection**: Session pooler for connection management
- **SSL**: Enabled for secure connections
- **Schema**: Auto-created via Hibernate DDL

### Database Schema

**Events Table:**

- id (Long) - Primary Key (auto-generated)
- title (String) - Event title
- description (String) - Event description
- date (String) - Event date
- hour (LocalTime) - Event time
- price (Double) - Ticket price
- hostedBy (String) - Event organizer
- venue_id (Long) - Foreign Key to Venues

**Venues Table:**

- id (Long) - Primary Key (auto-generated)
- name (String) - Venue name
- country (String) - Country
- address (String) - Street address
- city (String) - City
- state (String) - State/Province
- zipCode (String) - Postal code

### Database Setup

1. **Create a Supabase Project**:

   - Go to [supabase.com](https://supabase.com)
   - Create a new project
   - Note your project URL and database credentials

2. **Run the Database Schema**:

   ```sql
   -- Execute the SQL script in database/create_postgresql_database.sql
   -- This creates the tables, indexes, and sample data
   ```

3. **Configure Application**:
   - Update `src/main/resources/application.properties` with your Supabase credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://[YOUR-PROJECT-URL]:5432/postgres?user=[USERNAME]&password=[PASSWORD]
   spring.datasource.username=[USERNAME]
   spring.datasource.password=[PASSWORD]
   ```

### Features

- **Persistent Storage**: Data persists across application restarts
- **Row Level Security**: Enabled for secure data access
- **Automatic Timestamps**: Created and updated timestamps managed by triggers
- **Foreign Key Constraints**: Ensuring data integrity between events and venues
- **Indexes**: Optimized for common query patterns

## ⚙️ Configuration

Key configuration properties in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration (Supabase)
spring.datasource.url=jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?user=postgres.jrrtownrpkbismkbduhg&password=Qwe.123*
spring.datasource.username=postgres.jrrtownrpkbismkbduhg
spring.datasource.password=Qwe.123*
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration for PostgreSQL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Logging Configuration
logging.level.com.codeup.eventify=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
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

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Running in Development Mode

The application includes Spring DevTools for automatic restart on code changes:

```bash
./mvnw spring-boot:run
```

### Code Style and Patterns

The project uses:

- **Hexagonal Architecture** (Ports & Adapters) for clean separation
- **Domain-Driven Design** principles with pure domain models
- **MapStruct** for automatic bean mapping between layers
- **Lombok** annotations to reduce boilerplate code
- **Jakarta Validation** for input validation
- **RESTful** design principles
- **DTO pattern** for request/response handling
- **Repository Pattern** with port abstraction
- **Builder Pattern** for object construction
- **Test-Driven Development** with comprehensive test coverage

### Development Best Practices

1. **Always write tests** for new features
2. **Follow hexagonal architecture** principles
3. **Use proper exception handling** with meaningful error messages
4. **Keep domain layer clean** from framework dependencies
5. **Mock external dependencies** in tests
6. **Validate inputs** at the controller level
7. **Use proper HTTP status codes** and error responses

## 🏛️ Architecture

The application follows **Hexagonal Architecture (Ports & Adapters)**:

### Core Principles

- **Domain Independence**: Business logic has no framework dependencies
- **Port Abstraction**: Interfaces define contracts for input/output
- **Adapter Implementation**: Concrete implementations handle technology concerns
- **Dependency Inversion**: High-level modules don't depend on low-level modules

### Architecture Layers

1. **Domain Layer** (`dominio/`)

   - Pure business entities (Event, Venue)
   - Input ports (Use Cases interfaces)
   - Output ports (Repository interfaces)
   - **No framework dependencies**

2. **Application Layer** (`application/usecase/`)

   - Use case implementations
   - Business logic orchestration
   - Validation and logging
   - **NEW**: Enhanced error handling and validation

3. **Infrastructure Layer** (`infrastructure/`)
   - **Input Adapters** (`in/web/`): REST controllers, DTOs
   - **Output Adapters** (`out/jpa/`): JPA entities, repositories, mappers
   - Configuration beans
   - **NEW**: Improved venue resolution logic

### Recent Architecture Improvements

#### Better Error Handling

- Centralized exception handling in use cases
- Proper validation with meaningful error messages
- Enhanced logging for debugging

#### Enhanced Testing Strategy

- Comprehensive test coverage for all layers
- Proper mocking of dependencies
- Integration tests for REST endpoints

#### Improved Data Flow

- Better venue resolution in event creation
- Proper null checking and validation
- Cleaner separation between adapter and use case layers

### Benefits of This Architecture

- **Testability**: Domain logic can be tested without frameworks
- **Maintainability**: Clear separation of concerns
- **Flexibility**: Easy to swap implementations (e.g., change database)
- **Scalability**: Each layer can evolve independently
- **Clean Code**: Business logic isolated from technical concerns

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Write tests for your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

### Contribution Guidelines

- Follow the existing code style and architecture patterns
- Write comprehensive tests for new features
- Update documentation for API changes
- Ensure all tests pass before submitting PR

## 📄 License

This project is licensed under the terms specified in the project configuration.

## 👥 Author

Adrian Gutierrez Regino

## 🐛 Recently Resolved Issues

### ✅ Fixed Issues (December 2025)

- **NullPointerException in Event Creation**: Resolved venue resolution issue
- **Missing Repository Methods**: Added `update` method to venue repository
- **Test Failures**: Fixed all 24 tests to pass
- **Compilation Errors**: Resolved missing dependencies and imports
- **Lint Warnings**: Cleaned up unused imports and variables
- **Port Conflicts**: Added troubleshooting documentation

## 🔮 Future Enhancements

### Short Term

- Add authentication and authorization
- Implement pagination for list endpoints
- Add search and filtering capabilities
- Support for recurring events

### Long Term

- Email notifications for event updates
- File upload for event images
- Integration with external calendar systems
- ~~Migration to persistent database (PostgreSQL/MySQL)~~ ✅ **COMPLETED** - Supabase PostgreSQL integration
- Add integration tests for adapters
- Implement CQRS pattern for complex queries
- Add event sourcing capabilities
- Implement caching with Redis
- Add monitoring and metrics (Micrometer)
- GraphQL API support
- Async event processing with RabbitMQ/Kafka

### Technical Debt

- **@MockBean Migration**: Plan migration to @MockitoBean for Spring Boot 3.4.0+ compatibility
- **Date/Time Handling**: Consider using LocalDate and LocalTime for better type safety
- ~~Persistent Database\*\*: ~~Evaluate migration from in-memory H2 to PostgreSQL~~ ✅ **COMPLETED** - Supabase PostgreSQL deployed
- **Bidirectional Relationships**: Reconsider entity relationship design for complex scenarios
