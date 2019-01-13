package com.iphayao.linetest.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/todos")
public class TodoWebController {
    @Autowired
    TodoService todoService;

    @GetMapping("/todos-edit")
    public String editTodo(@RequestParam(value = "user", required = false) String userId, Model model) {
        model.addAttribute("todos", todoService.retrieveTodoByUserId(userId));
        return "todosEdit";
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        model.addAttribute("todos", todoService.retrieveTodoByUserId(principal.getName()));
        return "todosEdit";
    }
}
