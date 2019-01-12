package com.iphayao.linetest.todo;

import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Optional<Todo> createTodo(String userid, String todoMessage) {
        String[] data = todoMessage.split("\\ : ");

        TodoType type;

        switch (data[1]) {
            case "date/month/year":
                type = TodoType.ByDate;
                break;
            case "today":
                type = TodoType.Today;
                break;
            case "tomorrow":
                type = TodoType.Tomorrow;
                break;
            default:
                type = TodoType.Other;
                break;
        }

        Todo todo = Todo.builder()
                .userId(userid)
                .type(type)
                .action(data[2].substring(9).trim())
                .date(data[3])
                .time(data[4])
                .build();

        return Optional.of(todoRepository.save(todo));
    }

    public void editTodo() {
        return;
    }
}
