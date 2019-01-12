package com.iphayao.linetest.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoWebController {
    @Autowired
    TodoService todoService;

    @GetMapping("/todos-edit")
    public String editTodo(@RequestParam(value = "user", required = false) String userId, Model model) {
        model.addAttribute("todos", todoService.retrieveTodoByUserId(userId));
        return "todosEdit";
    }
}
