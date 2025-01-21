# Book Catalog Application

## Description
This is a book catalog application that uses PostgreSQL as the database and JDBC to interact with the database. The application implements a complete CRUD cycle for managing books, along with a search feature that allows users to search books by author or title. The application follows the **MVC (Model-View-Controller)** architectural pattern and provides a graphical user interface built with **JavaFX**. **Maven** is used for dependency management and building the project. Confidential information, such as the database password, is stored in a **.env** file, which is not uploaded to the repository.

## Technologies Used

- **Java 11+** — The primary programming language.
- **PostgreSQL** — The database used to store book information.
- **JDBC** — The interface used to interact with the database.
- **JavaFX** — The library used to create the graphical user interface.
- **Maven** — The build and dependency management system.
- **.env** — Used for storing confidential information (password and other credentials).
- **MVC (Model-View-Controller)** — An architectural pattern that separates logic and interface.

## Features

- **CRUD Operations**:
  - Add books to the database.
  - Update information about books.
  - Delete books.
  - View all books in the database.
  
- **Book Search**:
  - Search books by title.
  - Search books by author.

- **User Interface**:
  - A user-friendly graphical interface created using JavaFX.
  - A simple and intuitive UI for managing book data.

- **Architecture**:
  - The **MVC** (Model-View-Controller) pattern is implemented to separate business logic from the interface.
  - The **BookDAO** class is used for interacting with the database.

## Installation and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/book-catalog.git
2. Make sure you have Java 11+ and Maven installed
3. Create a .env file in the root of the project and define the database connection parameters:
   ```bash
   DB_URL="your_db_url_here"
   DB_USER="your_username_here"
   DB_PASSWORD="your_password_here"
4. Run the application using Maven:
   ```bash
   mvn javafx:run
5. To make the application work, you will need a configured PostgreSQL database with the following table for books:
   ```bash
   CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year INT,
    pages INT,
    status VARCHAR(50)
   };
