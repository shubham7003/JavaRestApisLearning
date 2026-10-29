package com.example.todo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/** The HTTP layer: maps URLs + HTTP verbs to Java methods. */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {   // constructor injection
        this.service = service;
    }

    // GET /api/todos  -> 200 OK
    @GetMapping
    public List<Todo> getAll() {
        return service.findAll();
    }

    // GET /api/todos/1  -> 200 OK (or 404)
    @GetMapping("/{id}")
    public Todo getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    // POST /api/todos  -> 201 Created + Location header
    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoRequest request) {
        Todo created = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // PUT /api/todos/1  -> 200 OK (or 404)
    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return service.update(id, request);
    }

    // DELETE /api/todos/1  -> 204 No Content (or 404)
    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
