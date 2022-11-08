# Getting Started

### 1. Prerequisites
Java 17, Maven 3.6 (and above), MongoDB

### 2. How to build application
To build application please run maven command: **mvn clean install**

### 3. How to run application
To run application please execute java command:  

**java "-Dspring.data.mongodb.uri=URI_TO_MONGODB" -jar target/openfda-api-radoslaw-kozdrun-0.0.1-SNAPSHOT.jar**

where *URI_TO_MONGODB* is a url to connect to mongo database

### 4. How to run tests
To build application please run maven command: **mvn test**

### 5. API
Swagger for application API is available here: **http://localhost:8080/swagger-ui.html**

