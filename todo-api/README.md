# Todo REST API (Spring Boot)

## Run
Requires Java 17+ and Maven.

    mvn spring-boot:run

API base URL: http://localhost:8080/api/todos

## Try it (curl)

    # create
    curl -i -X POST localhost:8080/api/todos -H "Content-Type: application/json" -d '{"title":"Learn REST"}'

    # list
    curl localhost:8080/api/todos

    # get one
    curl localhost:8080/api/todos/1

    # update
    curl -X PUT localhost:8080/api/todos/1 -H "Content-Type: application/json" -d '{"title":"Learn REST","completed":true}'

    # delete
    curl -i -X DELETE localhost:8080/api/todos/1

    # see validation error (blank title -> 400)
    curl -i -X POST localhost:8080/api/todos -H "Content-Type: application/json" -d '{"title":""}'

    # see 404
    curl -i localhost:8080/api/todos/999

## Exercises
1. Add a `GET /api/todos?completed=true` filter using `@RequestParam`.
2. Add a `PATCH /api/todos/{id}/complete` endpoint.
3. Replace the in-memory Map with Spring Data JPA + H2 database.
4. Add pagination (`page`, `size` params).
5. Write a test with `@WebMvcTest` and `MockMvc`.
