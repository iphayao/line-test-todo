package com.iphayao.linetest.todo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TodoDto {
    private List<Todo> todos;

    public TodoDto() {
        this.todos = new ArrayList<>();
    }

    public void add(Todo todo) {
        todos.add(todo);
    }

    public void add(List<Todo> todos) {
        this.todos.addAll(todos);
    }
}
