# ✈️ One Way Ticket Travel

**One Way Ticket Travel** is a web application built with Spring Boot that allows users to search for travel offers using dynamic filters such as continent, country, destination city, departure date, and number of travelers.

## 📦 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL 
- Maven

## 🧭 Features

- 🔍 Search offers by:
  - Continent
  - Country
  - Destination city
- 📄 Dynamic filtering using `Specification`
- 🗺️ Entity-to-DTO mapping
- 🧪 Sample data via `schema.sql` and `data.sql`
- 🎨 Clean and responsive UI with search form and results view

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
    │   ├── service/
    │   ├── repository/
    │   ├── entities/
    │   ├── dto/
    │   ├── mapper/
    │   ├── specification/
    │   └── model/
    └── resources/
        ├── templates/
        ├── schema.sql
        └── data.sql
        
📁 Folder Descriptions
●  controller/ – Spring MVC controllers
● service/ – Business logic
● repository/ – JPA repositories
● entities/ – JPA entities
● dto/ – Data Transfer Objects
● mapper/ – Entity ↔ DTO mapping
● specification/ – Dynamic filtering logic
● model/ – Search filter object
● templates/ – Thymeleaf views (index.html, results.html)
● schema.sql – Database schema
● data.sql – Sample data

🛠️ Sample Data:
The data.sql file includes sample continents, countries, cities, and travel offers — for example, “Italy Sun & Pizza” with destination city “Rome”.

📌 Project Status:
✅ Core functionality implemented

🔜 Upcoming features:
● Pagination of results
● Sorting by price and date
● City autocomplete
● REST API support

👨‍💻 Author
Created by Paweł Popala

