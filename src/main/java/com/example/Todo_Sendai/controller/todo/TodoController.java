package com.example.Todo_Sendai.controller.todo;

import com.example.Todo_Sendai.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTodo(@PathVariable Integer id){
        todoService.deleteTodo(id);
        return new ModelAndView("redirect:/");
    }
}
