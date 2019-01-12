package com.iphayao.linetest.todo;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Optional<Todo> createTodo(String userId, String todoMessage) {
        Todo todo = extractTodoMessage(userId, todoMessage);

        return Optional.of(todoRepository.save(todo));
    }

    public Todo extractTodoMessage(String userId, String todoMessage) {
        String[] messages = todoMessage.split("\\ : ");

        TodoType type;
        String date = messages[3];
        String time = "00:00";
        if(messages.length == 5) {
            time = messages[4];
        }
        LocalDateTime dateTime = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/[yyyy][yy] HH:mm");

        switch (messages[1]) {
            case "date/month/year":
                type = TodoType.ByDate;
                dateTime = LocalDateTime.parse(String.format("%s %s", date, time), formatter);
                break;
            case "today":
                type = TodoType.Today;
                dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(time));
                break;
            case "tomorrow":
                type = TodoType.Tomorrow;
                dateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.parse(time));
                break;
            default:
                type = TodoType.Other;
                break;
        }

        return Todo.builder()
                .userId(userId)
                .type(type)
                .action(messages[2].substring(9).trim())
                .dateTime(dateTime)
                .done(false)
                .build();
    }

    public List<Todo> retrieveTodoByUserId(String userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        todos.sort(new Comparator<Todo>() {
            @Override
            public int compare(Todo a, Todo b) {
                return a.getDateTime().compareTo(b.getDateTime());
            }
        });
        return todos;
    }

    public Todo markTodoDone(int todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if(todo.isPresent()) {
            todo.get().setDone(true);
            return todoRepository.save(todo.get());
        }

        return null;
    }

    public Todo editTodo(String userId, int todoId, Todo editTodo) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if(todo.isPresent()) {
            editTodo.setUserId(userId);
            editTodo.setId(todoId);
            return todoRepository.save(editTodo);
        }

        return null;
    }
}
