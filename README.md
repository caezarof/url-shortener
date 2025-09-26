# URL Shortener
A URL shortening service developed in Java with Spring Boot, providing a RESTful API to create and manage short links.

- ## ✨ Features
- **URL Shortening**: Convert long URLs into unique short codes
- **Redirection**: Automatic access to original URLs through short codes
- **Access Statistics**: Click count for each shortened URL
- **URL Validation**: Format and security validation of URLs
- **Unique Code Generation**: Algorithm that guarantees unique short codes
- **Database Persistence**: Secure storage in PostgreSQL
- **API Documentation**: Interactive Swagger/OpenAPI documentation

- ## 🛠 Technologies Used
- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Docker & Docker Compose**
- **SpringDoc OpenAPI** (Swagger)
- **JUnit 5 & Mockito**
- **JaCoCo** (Test coverage)
- **Lombok**
- **Validation API**

- ## 📋 Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose (optional)
- PostgreSQL (if running without Docker)

## 🚀 Installation and Execution

***First: Update application.properties with your local or Docker image Postgre DB:  
spring.datasource.url=${YOUR_DB_URL}  
spring.datasource.username=${YOUR_DB_USERNAME}  
spring.datasource.password=${YOUR_DB_PASSWORD}  

### Option 1: Using Docker (Recommended)
  
1. Clone the repository:  
terminal:  
git clone https://github.com/caezarof/url-shortener.git  
cd url-shortener

2. Configure environment variables in the .env file(For docker compose):  
.env:  
POSTGRES_DB=url_shortener  
POSTGRES_USER=postgres  
POSTGRES_PASSWORD=password  

3. Run with Docker Compose:  
terminal:    
docker-compose up -d    
mvn spring-boot:run    
The application will be available at: http://localhost:8080  

Option 2: Local Execution  
1. Configure PostgreSQL locally  
2. Update configurations in application.properties  
3. Run:  
terminal:  
mvn spring-boot:run

📚 API Documentation  
Swagger UI  
Once the application is running, access the interactive API documentation at:  
http://localhost:8080/swagger-ui.html

🔌 API Endpoints  
Shorten URL  
http:  
POST /shorten  
Content-Type: application/json  

{  
  "originalUrl": "https://www.example.com/very-long-page-url"  
}  

Response:  
json  
{  
  "id": 1,  
  "originalUrl": "https://www.example.com/",  
  "shortCode": "AbC123",  
  "createdAt": "14:30:25 25/09/2025"  
}

Redirect  
http:  
GET /{shortCode}  
Response: 302 Redirect to the original URL  

API Response Codes  
  201 Created: URL successfully shortened  
  302 Found: Successful redirect  
  400 Bad Request: Invalid URL format or parameters  
  404 Not Found: Short code not found  
  500 Internal Server Error: Server error during code generation
  
✅ Testing  
The project has comprehensive test coverage:  
terminal:  
# Run tests  
mvn test

# Generate coverage report  
mvn jacoco:report

Test Coverage Includes:  
  Unit tests for utilities  
  Service tests with Mockito  
  Integration tests  
  Error and edge case handling  

🚀 Potential Enhancements  
Redis caching for frequent redirects  
Detailed statistics API  
Automatic URL expiration  
Web interface for administration  
Rate limiting to prevent abuse  
Metrics with Micrometer and Prometheus  
Bulk URL shortening  
Custom short code support  
QR code generation  
User authentication and URL management  
