# Overview

This application contain the following endpoints.
• GET /api/interest-rates (get a list of current interest rates)
• POST /api/mortgage-check (post the parameters to calculate for a mortgage check)

assumptions: 
. Saved interestRate or MortgageRates in database. Assuming we will receive this from customer. 
. Mortgage check request is Async for handling load.
. Not added any deployment setup.
. For Database used h2 database that can be changed to Postgres , oracle or any database as per our use case.
. To make production ready , need to add check style , jacoco for code coverage and check in pom xml.
. Assuming mortgage period with will be unique in mortgage_rate table.
. Mapper for request and response object is not included.
# Local setup

To build and run the application locally, you will need the following:

* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* H2 in memory database from intellij
* 
### Build

During development, you might find it useful to skip Checkstyle or coverage checks. This can be done using the following
Maven commands:
mvn clean compile
mvn install

### Database

The application requires following tables:
- **TESTDB**
Added a configuration in application property.
- run data.sql file before start of the application.
Refer to `src/test/resources/data.sql` for the initial database setup.

### Running the Application

To run the application, you can either use the below commands or the `Run App against Default` configuration in your
IDE:

First, navigate to the `src` directory:

run MortgageServiceApplication using default application.properties.

### API Documentation 
http://localhost:8086/v3/api-docs
