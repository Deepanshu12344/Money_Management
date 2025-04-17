-- Insert: Insert data into the users table
Insert=INSERT INTO users (name, email) VALUES ('John Doe', 'john.doe@example.com');

-- CreateTable: Create the users table
CreateTable=CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50)
);

-- UseDatabase: Select the database to use
UseDatabase=USE testdb;
