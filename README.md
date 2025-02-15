# Library Microservice
Application for library management
## Requirements
- Java 17
- Gradle 8.12
- Docker & Docker Compose

## Running the application locally
Launch all microservices using docker compose
```shell
docker-compose up --build
```
## API Endpoints

### **Books**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/book` | Add a new book |
| `GET`  | `/book` | Get all books |
| `GET`  | `/book/{id}` | Get book by ID |
| `PUT`  | `/book/{id}` | Update book |
| `DELETE` | `/book/{id}` | Delete book |

### **Authors**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/author` | Add a new author |
| `GET`  | `/author` | Get all authors |
| `GET`  | `/author/{id}` | Get author by ID |
| `PUT`  | `/author/{id}` | Update author |
| `DELETE` | `/author/{id}` | Delete author |

### **Kafka Topics**
| Event         | Topic |
|---------------|----------|
| Book Events   | `book.events` |
| Author Events | `author.events` |