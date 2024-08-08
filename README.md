# Course Management Web Application

This project is a user-friendly Spring Boot web application designed to empower course advisors to manage courses and enable students to register or drop courses for a semester within a university. 

## Technologies Used

- **Spring Boot**: Simplifies the development process with built-in features and configurations.
- **Spring MVC**: Implements the Model-View-Controller design pattern to separate concerns.
- **JSP (JavaServer Pages) and JSTL (JavaServer Pages Standard Tag Library)**: Used for creating the front-end and dynamic content rendering.
- **Hibernate**: Facilitates ORM (Object-Relational Mapping) for interaction with the MySQL database.
- **MySQL**: Relational database used to store and manage the application's data.

## Key Features and Implementation Details

### User-Friendly Interface

- Developed an intuitive web interface for course advisors to manage courses (e.g., add, update, delete) and for students to register or drop courses.
- Uses JSP for rendering views and JSTL for handling logic and presenting data.

### Spring MVC Framework

- **Model**: Represents data and business logic with Java classes corresponding to database entities.
- **View**: JSP pages presenting data to the user.
- **Controller**: Handles user requests, processes them, and returns the appropriate view or response using Spring annotations for mapping HTTP requests to Java methods.

### REST API Integration

- Implemented RESTful API endpoints to handle CRUD (Create, Read, Update, Delete) operations for course and student data.
- Facilitates communication between the front-end and back-end, allowing data retrieval and manipulation.

### Database Interaction

- **Hibernate**: Used for mapping Java objects to database tables with annotations defining entity relationships and mapping.
- **Criteria API and HQL (Hibernate Query Language)**: Used for building dynamic queries and performing database operations. Criteria API allows type-safe queries, while HQL offers a flexible querying approach.

### Entity Relationships

- **OneToMany**: A course can have many students enrolled in it.
- **ManyToOne**: A student can be enrolled in many courses, but each enrollment is associated with a specific course.

### Data Validation

- Integrated **Spring Validators** to validate user input before processing, ensuring data integrity and form field validation.

## Summary

This project leverages various technologies to build a robust web application that simplifies course management and student enrollment processes. It incorporates effective use of Spring Boot, MVC architecture, JSP for the front-end, Hibernate for ORM, and REST APIs for seamless interaction between the front-end and back-end. The focus on validation and efficient data handling ensures a reliable and user-friendly application.
