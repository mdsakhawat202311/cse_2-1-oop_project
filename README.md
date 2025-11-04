# BeanBuddies - Online Course Platform (Web Application)

## Overview
BeanBuddies is a Spring Boot web application for browsing and enrolling in online courses. This application has been converted from a JavaFX desktop application to a modern web application.

## Technologies Used
- **Spring Boot 3.2.0** - Main framework
- **Spring Web MVC** - Web layer
- **Thymeleaf** - Server-side template engine
- **Bootstrap 5.3.0** - Frontend CSS framework
- **Maven** - Build tool
- **Java 17** - Programming language

## Features
- Browse courses with beautiful card-based UI
- Filter courses by:
  - **Category** (Web Development, App Development, Cyber Security, Data Science, Cloud Computing, Competitive Programming)
  - **Level** (Beginner, Intermediate, Advanced)
  - **Price Range** (Free, Under $75, $75-$90, Over $90)
  - **Search** by title, instructor, or description
- View detailed course information
- User authentication (Login/Signup)
- Responsive design for desktop and mobile

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Steps to Run
1. Navigate to the project directory:
   ```bash
   cd "/Users/md.sakhawathosen/Desktop/OOP Project/beanbuddies copy"
   ```

2. Clean and build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open your web browser and navigate to:
   ```
   http://localhost:8080
   ```

## Application Structure

```
src/main/java/org/example/beanbuddies/
├── BeanBuddiesApplication.java    # Main Spring Boot application
├── Course.java                     # Course model
├── CourseService.java              # Business logic for courses
└── CourseController.java           # Web controller handling HTTP requests

src/main/resources/
├── templates/                      # Thymeleaf HTML templates
│   ├── index.html                  # Main course listing page
│   ├── course-detail.html          # Course detail page
│   ├── login.html                  # Login page
│   └── signup.html                 # Signup page
└── application.properties          # Application configuration
```

## Available Endpoints

- `GET /` - Home page with course listing and filters
- `GET /course/{id}` - Course detail page
- `GET /login` - Login page
- `POST /login` - Process login
- `GET /signup` - Signup page
- `POST /signup` - Process signup

## Course Categories

The application includes courses in the following categories:
1. **Web Development** - Full-stack web development, Spring Boot, React, SQL
2. **App Development** - Java, JavaFX, iOS development
3. **Cyber Security** - Ethical hacking, penetration testing
4. **Data Science** - Python for data science, machine learning
5. **Cloud Computing** - AWS, Docker, Kubernetes
6. **Competitive Programming** - Algorithms and data structures

## Default Port
The application runs on port **8080** by default. You can change this in `application.properties`:
```properties
server.port=8080
```

## Development Mode
The application includes Spring Boot DevTools for automatic restart during development. Any changes to Java files or templates will automatically trigger a restart.

## Future Enhancements
- Database integration (MySQL/PostgreSQL)
- Spring Security for proper authentication
- User dashboard and enrolled courses
- Payment integration
- Course reviews and ratings
- Video streaming capabilities

## Notes
- This is a converted web application from a JavaFX desktop application
- The application uses in-memory data storage (no database yet)
- User authentication is simplified for demonstration purposes

