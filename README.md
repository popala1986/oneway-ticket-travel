✈️ One Way Ticket Travel
One Way Ticket Travel is a web application built with Spring Boot that allows users to search for travel offers using dynamic filters such as continent, country, destination city, and price range. Users can reserve offers, view confirmation details, and enjoy a clean, responsive interface. The application also exposes a REST API documented via Swagger UI for seamless integration and testing.

## 📦 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL 
- Maven
- Spring AOP (Aspect-Oriented Programming)
- Swagger UI (OpenAPI 3)
- Spring Security


## 🧭 Features

- 🔍 Search offers by:
  - Continent
  - Country
  - Destination city
  - Price range (minPrice, maxPrice)
- 📄 Dynamic filtering using `Specification`
- 🗺️ Entity-to-DTO mapping
- 🧪 Sample data via `schema.sql` and `data.sql`
- 🎨 Clean and responsive UI with search form and results view
- 📊 Transparent logging and performance monitoring via AOP aspects
- ✅ Offer reservation system with status tracking (AVAILABLE, RESERVED, CANCELLED)
- 📩 Reservation confirmation view with offer details
- 🔁 Return-to-search navigation after booking
- ⚠️ Styled alert for empty search results
- 🔐 Foreign key-safe database reset logic
- 🧹 Auto-reset of sample data on startup
- 🌐 Public REST API with Swagger documentation
- ❗ Custom exception handling with OfferNotFoundException and global @ControllerAdvice


🔍 Application Monitoring with Aspects
To improve observability and debugging, the application includes three dedicated aspects:
  ● OfferControllerAspect Logs form input, measures execution time of the controller, and captures exceptions
  ● OfferServiceAspect Logs search filters, tracks method duration, and logs errors in business logic
  ● OfferMapperAspect Logs entity input, measures DTO mapping time, and handles mapping exceptions
  ● These aspects provide transparent logging, performance metrics, and error tracking across the core layers of the application — without modifying business logic.


  🌐 REST API Endpoints
  
Publicly accessible via Spring Security and documented in Swagger UI:

OfferRestController

    ● GET /api/offers → returns all available offers
  
    ● POST /api/offers/search → filters offers based on SearchFilter criteria

    ● GET /admin/offers?offerName=... → returns a single offer matching the provided name
    Throws OfferNotFoundException if no match is found
    Handled globally via GlobalException using ProblemDetail and HTTP 404
    
ReservationRestController

    ● POST /api/reservations/reserve/{offerId} → reserves an offer for the authenticated user

Swagger UI: http://localhost:8080/swagger-ui/index.html


⚠️ Error Handling

The application includes centralized exception handling for cleaner API responses:

  ● OfferNotFoundException — thrown when an offer is not found by name
  
  ● Handled globally via GlobalException class annotated with @ControllerAdvice
  
  ● Returns standardized ProblemDetail with HTTP 404 status and descriptive message


## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/popala1986/oneway-ticket-travel.git

2. Run the application:
   From your IDE (e.g. IntelliJ) — run OnewayTicketTravelApplication

3. Open your browser and go to:
     http://localhost:8080/

🗂️ Project Structure:

src/
└── main/
    ├── java/pl/onewaytickettravel/app/
    │   ├── controller/
    │   ├── restController/
    │   ├── service/
    │   ├── repository/
    │   ├── entities/
    │   ├── dto/
    │   ├── mapper/
    │   ├── specification/
    │   ├── model/
    │   └── aspect/
    └── resources/
        ├── templates/
        ├── schema.sql
        └── data.sql
        
📁 Folder Descriptions

●  controller/ – Spring MVC controllers

● restController/ – REST API endpoints

● service/ – Business logic

● repository/ – JPA repositories

● entities/ – JPA entities

● dto/ – Data Transfer Objects

● mapper/ – Entity ↔ DTO mapping

● specification/ – Dynamic filtering logic

● model/ – Search filter object

● aspect/ – AOP aspects for logging and performance

● templates/ – Thymeleaf views (index.html, results.html, reservation-confirmation.html)

● schema.sql – Database schema

● data.sql – Sample data

🛠️ Sample Data:
The data.sql file includes sample continents, countries, cities, and travel offers — for example, “Italy Sun & Pizza” with destination city “Rome”.

📌 Project Status:
✅ Core functionality implemented
✅ AOP aspects for controller, service, and mapper added
✅ Reservation system with confirmation view
✅ Styled empty results alert
✅ Safe database reset logic
✅ REST API with Swagger documentation
✅ Price range filtering in search form and backend

🔜 Upcoming features:
● Pagination of results
● Sorting by price and date
● City autocomplete
● REST API support
● Search offers by hotel type, departure date, and number of travelers 
● User authentication and personalized reservations
● Admin panel for managing offers and reservations


👨‍💻 Author
Created by Paweł Popala

