# Web Framework Development for REST Services and Static File Management

This framework allows developers to define REST services using lambda functions, manage query values from requests, and specify the location of static files. The goal is to provide a robust and scalable framework for building web applications with backend services and static file handling.

As a demonstration of the server's functionality, a simple web application is included to handle books. It allows users to add, delete, and list books using REST services.

## Getting Started

These instructions will guide you to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project, you need the following software installed on your local machine:

- **Java 21+**: The project is built using Java. 
- **Maven**: Used for dependency management.
- **IDE (optional)**: An Integrated Development Environment like IntelliJ IDEA can be used for development.

### Installing

Follow these steps to get the development environment running:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nat15005/AREP-Taller2-Microframeworks-WEB.git
   ```
2. **Navigate to the project folder:**
   ```bash
   cd AREP-Taller2-Microframeworks-WEB
   ```
3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

### Running the Server

Once the project is built, you can start the server with the following command:

```bash
java -cp target/classes edu.escuelaing.arep.server.HttpServer
```

The server will start and listen on port `35000`.

### Accessing the Application

Open your web browser and go to:

```
http://localhost:35000/
```

You should see the main page of the application.

![image](https://github.com/user-attachments/assets/ee733dbf-a387-4ed7-b243-9d8bdeaf2666)

![image](https://github.com/user-attachments/assets/f2382c33-b777-4073-8d8f-9a355a512263)

### Example of GET REST Services
To test the functionality of the new REST framework, try the following endpoints in your browser:

- GET request with query parameters:
  
```  
http://localhost:35000/App/hello?name=Natalia
```

This will return: Hello Natalia

![image](https://github.com/user-attachments/assets/1c2664e9-ae19-40e6-baf2-c772b0077842)


- GET request with predefined response:
  
```  
http://localhost:35000/App/pi
```
This will return the value of Pi: 3.141592653589793

![image](https://github.com/user-attachments/assets/84b2226c-674c-4105-968f-376323dffbe9)

### Static File Location Specification

The framework includes a staticfiles() method that allows developers to define where the static files (like images, CSS, and HTML) are located. By default, this method looks for static files in the /static folder. 

Once the staticfiles() method is configured, you can easily access static resources like CSS, PNG, and other files by simply making a request to the corresponding URL. For example, a request to:

```  
http://localhost:35000/index.css

```
![image](https://github.com/user-attachments/assets/b2592241-c83d-42f6-9443-05fb27035a8c)

This will return the index.css file, and:

```  
http://localhost:35000/pato.png

```
This will serve the pato.png image from the static folder.

![image](https://github.com/user-attachments/assets/d32c91ef-8457-48fc-8226-ef4a9f60f52a)

#### Changing the Static File Location

You can change where the framework looks for static files by configuring the staticfiles() method to point to a different folder. For example, if you have a test folder called prueba, you can update the configuration as follows:

![image](https://github.com/user-attachments/assets/45e7297e-4c58-4ad4-abf2-b48dcd5f9912)

Once this change is made, the server will search for static files in the prueba directory. This means that any requests for static resources will now be handled by files located inside prueba. For example:

```  
http://localhost:35000/index.html

```
This will serve the index.html file from the prueba folder.
![image](https://github.com/user-attachments/assets/ef2cf31a-8dd1-4126-9a53-0f6430a8db91)


### Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```
![image](https://github.com/user-attachments/assets/cc10c5a0-3515-4140-9c7c-01e8b8f2c805)

### BookTest
- testBookCreation: Verifies that a Book object is created correctly with the specified title and author.
- testToString: Checks that the toString method of the Book class returns the expected JSON representation of the book.

### FileHandlerTest

- testServeExistingFile: Tests that an existing file is served correctly, returning a 200 OK response along with the file content.
- testServeNonExistingFile: Verifies that a request for a non-existent file returns a 404 Not Found response.
- testGetContentType: Confirms that the correct MIME type is returned for various file extensions.

### HttpServerTest

- testHelloEndpoint: Tests the /App/hello endpoint to ensure it responds with a 200 OK status and the correct greeting message.
- testPiEndpoint: Verifies that the /App/pi endpoint returns a 200 OK status and the value of π.
- testStaticFile: Checks that a static file (e.g., index.html) can be accessed and returns a 200 OK status.
- testNonExistentRoute: Tests that a request to a non-existent route returns a 404 Not Found status.

### RequestHandlerTest
- testGetBooks: Simulates a GET request to /getBooks and verifies that the response contains the expected JSON format.
- testPostAddBook: Tests a POST request to /addBook to ensure that a book is added correctly and the response confirms the addition.
- testDeleteBook: Verifies that a DELETE request to /deleteBook successfully removes a book and returns the appropriate response.
- testGetNonExistentRoute: Checks that a GET request to a non-existent route returns a 404 Not Found response.
- testDeleteNonExistentBook: Tests that attempting to delete a non-existent book returns a "Book not found" message.

### RequestTest

- testGetValues: Verifies that the getValues method correctly retrieves the value of a query parameter (in this case, "name") from the request.
- testGetValuesNotFound: Tests that the getValues method returns an empty string when a non-existent query parameter (in this case, "age") is requested.

### ResponseTest

- testDefaultContentType: Verifies that the default content type of a Response object is "text/plain".
- testSetContentType: Tests that setting a new content type updates the response correctly.
- testSetContentTypeToNull: Checks that setting the content type to null behaves as expected.
- testSetContentTypeToEmptyString: Verifies that setting the content type to an empty string updates the response accordingly.

### WebFrameworkTest

- testStaticFilesConfiguration: Tests the configuration of the static files directory to ensure it can be set and retrieved correctly.
- testGetRouteRegistration: Verifies that a GET route can be registered successfully.
- testHandleGetRequest: Simulates a GET request to a registered route and checks that the response is correct.
- testHandleStaticFileRequest: Tests that a request for a static file returns the expected response.
- testHandleNonExistentRoute: Verifies that a request to a non-existent route returns a 404 Not Found response.

### MockSocket
This class is not directly tested but is used in other tests to simulate a network socket for handling HTTP requests without actual network communication.

### Project Structure

```
AREP-Taller-1
├───src
│   ├───main
│   │   ├───java
│   │   │   └───edu
│   │   │       └───escuelaing
│   │   │           └───arep
│   │   │                   Book.java
│   │   │                   FileHandler.java
│   │   │                   HttpServer.java
│   │   │                   Request.java
│   │   │                   RequestHandler.java
│   │   │                   Response.java
│   │   │                   WebFramework.java
│   │   │
│   │   └───resources
│   │       ├───prueba
│   │       │       index.html
│   │       │
│   │       └───static
│   │               fondo.jpg
│   │               index.css
│   │               index.html
│   │               index.js
│   │               pato.png
```

#### 📚 Book:
Represents a book with a title and an author. Provides methods to access book details and return a JSON representation.

#### 📂 FileHandler:
Handles file reading and serving in the HTTP server. Serves static files from a configured directory and determines MIME types.

#### 🌐 HttpServer:
Implements an HTTP server listening on port 35000. Manages REST requests and serves static files.

#### 📩 Request:
Represents an HTTP request, storing query parameters and providing methods to retrieve their values.

#### 🔄 RequestHandler:
Manages client HTTP requests, including handling books (GET, POST, DELETE) and serving static files.

#### 📤 Response:
Represents an HTTP response, allowing content type configuration.

### Technologies Used

- **Java** - Main programming language
- **Maven** - Dependency management and build tool
- **JUnit** - For unit testing
- **HTML, CSS, JavaScript** - Frontend components

### Author

Developed by **Natalia Rojas** https://github.com/Nat15005.

### Acknowledgments

- Java and Networking Documentation - For offering essential references on socket programming.

- Open Source Community - For tools and resources that helped in the development of this project.


