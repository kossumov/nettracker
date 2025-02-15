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

### Postman Collection
Click the button below to import the API collection into Postman:
[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/1716113-2148a0f0-9a43-4676-ae97-d574efe992b5?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D1716113-2148a0f0-9a43-4676-ae97-d574efe992b5%26entityType%3Dcollection%26workspaceId%3Dead81463-c9d5-422d-863c-183947ca9f7c)