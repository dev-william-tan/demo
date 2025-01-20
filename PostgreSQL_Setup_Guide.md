
# Setting Up PostgreSQL Locally and Configuring the Project

## 1. Install PostgreSQL Locally

### Install PostgreSQL
- **On macOS**:
  Use Homebrew:
  ```bash
  brew install postgresql
  ```
  Start PostgreSQL:
  ```bash
  brew services start postgresql
  ```

- **On Windows**:
  - Download the installer from [PostgreSQL.org](https://www.postgresql.org/download/).
  - Follow the installation wizard.
  - Ensure you install `pgAdmin` for managing the database.

- **On Linux**:
  Use your package manager (e.g., `apt` for Ubuntu):
  ```bash
  sudo apt update
  sudo apt install postgresql postgresql-contrib
  ```
  Start PostgreSQL:
  ```bash
  sudo service postgresql start
  ```

---

## 2. Set Up the Database

### Create a Database and User
1. Open the PostgreSQL command-line tool:
   ```bash
   psql -U postgres
   ```
   (Enter the default `postgres` password, or set one if prompted.)

2. Create a database named `copilot_demo`:
   ```sql
   CREATE DATABASE copilot_demo;
   ```

3. Create a user for the application:
   ```sql
   CREATE USER demo_user WITH PASSWORD 'demopassword';
   ```

4. Grant privileges to the user:
   ```sql
   GRANT ALL PRIVILEGES ON DATABASE copilot_demo TO demo_user;
   ```

---

## 3. Configure PostgreSQL for Your Project

### Update `application.properties`
Edit `src/main/resources/application.properties` to configure the PostgreSQL connection:
```properties
# Database connection
spring.datasource.url=jdbc:postgresql://localhost:5432/copilot_demo
spring.datasource.username=demo_user
spring.datasource.password=demopassword

# JPA settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## 4. Add PostgreSQL Dependency

Add the PostgreSQL driver to your `pom.xml`:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 5. Initialize the Database Schema

### Add `schema.sql` and `data.sql`
Place SQL scripts in the `src/main/resources/` folder to initialize the database:

**`schema.sql`:**
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);
```

**`data.sql`:**
```sql
INSERT INTO users (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Doe', 'jane.doe@example.com');
```

### Spring Boot Auto-Execution
Spring Boot will automatically run these scripts on application startup if `spring.jpa.hibernate.ddl-auto` is set to `update` or `none`.

---

## 6. Test the Database Connection

1. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

2. Verify the connection:
   - Check the logs to confirm the application connects to `copilot_demo` and initializes the `users` table.
   - Use `psql` or a GUI tool like `pgAdmin` to verify the schema and data.

---

## 7. Document Database Setup for the Team

Add instructions to `README.md` for collaborators:
```markdown
### Database Setup (PostgreSQL)

1. Install PostgreSQL:
   - **macOS**: `brew install postgresql`
   - **Windows**: Download the installer from [PostgreSQL.org](https://www.postgresql.org/download/).
   - **Linux**: `sudo apt install postgresql postgresql-contrib`

2. Create the database and user:
   ```bash
   psql -U postgres
   ```
   Inside the `psql` console:
   ```sql
   CREATE DATABASE copilot_demo;
   CREATE USER demo_user WITH PASSWORD 'demopassword';
   GRANT ALL PRIVILEGES ON DATABASE copilot_demo TO demo_user;
   ```

3. Configure the application:
   - Update the `application.properties` file with the following:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/copilot_demo
     spring.datasource.username=demo_user
     spring.datasource.password=demopassword
     ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Verify the database:
   - Use `psql` or a GUI like `pgAdmin` to check the `users` table and initial data.
```

---

## Optional: Use Docker for PostgreSQL

### `docker-compose.yml`:
```yaml
version: "3.9"
services:
  db:
    image: postgres:13
    container_name: copilot_demo_db
    environment:
      POSTGRES_DB: copilot_demo
      POSTGRES_USER: demo_user
      POSTGRES_PASSWORD: demopassword
    ports:
      - "5432:5432"
    volumes:
      - ./db_data:/var/lib/postgresql/data
      - ./src/main/resources/schema.sql:/docker-entrypoint-initdb.d/schema.sql
      - ./src/main/resources/data.sql:/docker-entrypoint-initdb.d/data.sql
```

### Start the container:
```bash
docker-compose up
```

This ensures a consistent and easily replicable setup for all team members.
