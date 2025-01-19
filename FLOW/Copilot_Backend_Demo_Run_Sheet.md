
# Copilot Backend Demo Run-Sheet

## Structure for the Day

### 1. Intro & Recap (10 minutes)

#### Introduce the Team and the Session Goals (5 minutes)
- Briefly introduce the presenters and the objective: demonstrating GitHub Copilot’s capabilities in backend development.
- Mention the focus areas: Writing RESTful APIs, generating tests, database interactions, and integrating APIs.

#### GitHub Copilot Overview (5 minutes)
- Explain what GitHub Copilot is and how it integrates with development workflows.
- Emphasize its role as a productivity tool, not a replacement for developers.

---

### 2. Writing RESTful APIs (30 minutes)

#### Set the Context (5 minutes)
- Introduce the task: creating a basic REST API for fetching users.
- Explain how REST APIs are essential in backend development.

#### Live Demo (20 minutes)
- **Generate a Controller and Endpoint**: Add a meaningful comment like `Create a GET endpoint for fetching a list of users`.
- **Develop the `User` Entity and Repository**: Use Copilot to scaffold these components.
- **Test the Endpoint**: Use Postman to verify the API works.

#### Audience Engagement (5 minutes)
- Take questions or feedback about Copilot’s role in REST API development.

---

### 3. Generating Unit and Integration Tests (25 minutes)

#### Explain the Importance of Testing (5 minutes)
- Discuss the need for unit and integration tests in backend development.
- Introduce the function to be tested (e.g., `getUserById`).

#### Live Demo (15 minutes)
- **Generate Test Cases**: Add a comment like `Write a unit test for getUserById`.
- **Explain Copilot’s Suggestions**: Review the generated test cases for coverage and accuracy.
- **Run the Tests**: Use Maven or your IDE’s testing tools to execute the tests and review results.

#### Audience Engagement (5 minutes)
- Discuss the reliability and limitations of Copilot in test generation.

---

### 4. Creating Database Interactions (25 minutes)

#### Set the Context (5 minutes)
- Explain the task: creating an ORM-based model or a raw SQL query for a `User` table.
- Discuss database interaction as a core part of backend systems.

#### Live Demo (15 minutes)
- **Generate a Database Connection**: Add a comment like `Create a JPA repository for User`.
- **Develop CRUD Operations**: Use Copilot to scaffold methods like `findAll`, `save`, and `delete`.
- **Test the Database Integration**: Verify operations using Postman.

#### Audience Engagement (5 minutes)
- Ask for feedback on how Copilot simplifies database tasks.

---

### 5. Integrating Third-Party APIs (15 minutes)

#### Set the Context (3 minutes)
- Explain the use case: consuming an external API like JSONPlaceholder or GitHub’s API.
- Highlight the importance of external API integration in backend development.

#### Live Demo (10 minutes)
- **Generate API Service**: Add a comment like `Fetch data from JSONPlaceholder API`.
- **Develop an API Controller**: Use Copilot to scaffold the endpoint for `GET /api/external/users`.
- **Test the Integration**: Use Postman to confirm data retrieval from the external API.

#### Audience Engagement (2 minutes)
- Answer questions about integrating third-party APIs.

---

### 6. Wrap-Up (20 minutes)

#### Generating Documentation (10 minutes)
- **Live Demo**: Use Springdoc OpenAPI to generate API documentation.
- **Customize Swagger UI**: Add descriptions or examples to endpoints.
- **Review**: Open Swagger UI to show the generated documentation.

#### Discussion on Uncovered Topics (5 minutes)
- Briefly discuss additional backend capabilities Copilot can assist with:
  - Error handling and logging.
  - Middleware implementation.
  - Debugging and refactoring.

#### Overview of Additional Capabilities (5 minutes)
- Highlight broader use cases like Infrastructure as Code (IaC) or performance optimizations.

#### Final Recap and Q&A (5 minutes)
- Recap key takeaways from the session.
- Open the floor for questions or feedback.

---

## Tips for Execution
- **Engage the Audience**: Encourage questions after each demo section.
- **Use Copilot’s Suggestions as a Teaching Moment**: Discuss unexpected or incomplete suggestions openly.
- **Keep It Practical**: Focus on use cases that are universally relevant for backend developers.
