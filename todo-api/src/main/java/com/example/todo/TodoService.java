package com.example.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** Business logic + storage. Uses a Map in memory (data resets on restart). */
@Service
public class TodoService {

    private final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public Todo findById(Long id) {
        Todo todo = store.get(id);
        if (todo == null) throw new TodoNotFoundException(id);
        return todo;
    }

    public Todo create(TodoRequest req) {
        long id = idGenerator.incrementAndGet();
        Todo todo = new Todo(id, req.title(), Boolean.TRUE.equals(req.completed()));
        store.put(id, todo);
        return todo;
    }

    public Todo update(Long id, TodoRequest req) {
        Todo todo = findById(id);
        todo.setTitle(req.title());
        todo.setCompleted(Boolean.TRUE.equals(req.completed()));
        return todo;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) throw new TodoNotFoundException(id);
    }
}
