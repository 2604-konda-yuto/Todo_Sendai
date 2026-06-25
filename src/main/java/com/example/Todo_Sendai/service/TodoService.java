package com.example.Todo_Sendai.service;

import com.example.Todo_Sendai.Controller.form.TodoForm;
import com.example.Todo_Sendai.repository.TodoRepository;
import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TodoService{
    @Autowired
    TodoRepository todoRepository;
    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);}
}
