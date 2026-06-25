package com.example.Todo_Sendai.service;

import com.example.Todo_Sendai.controller.form.TodoForm;
import com.example.Todo_Sendai.repository.TodoRepository;
import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TodoService{
    @Autowired
    TodoRepository todoRepository;

    public List<TodoForm> findAllTodo() {
        List<Todo> results = todoRepository.findAll();
        List<TodoForm> todo = setTodoForm(results);
        return todo;
    }

    private List<TodoForm> setTodoForm(List<Todo> results) {
        List<TodoForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TodoForm todo = new TodoForm();
            Todo result = results.get(i);
            todo.setId(result.getId());
            todo.setStatus(result.getStatus());
            todo.setContent(result.getContent());
            todo.setLimitDate(todo.getLimitDate());
            reports.add(todo);
        }
        return reports;
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);}

    public TodoForm findById(Integer id) {
        List<Todo> results = new ArrayList<>();
        results.add((Todo) todoRepository.findById(id).orElse(null));
        List<TodoForm> reports = setTodoForm(results);
        return reports.get(0);
    }

    public void updateStatus(Integer id, Integer statusId) {

    }

    public TodoForm editTodo(Integer id) {
        List<Todo> results = new ArrayList<>();
        results.add((Todo) todoRepository.findById(id).orElse(null));
        List<TodoForm> reports = setTodoForm(results);
        return reports.get(0);
    }

    public void updateTodo(TodoForm todo) {
        Todo report = setTodoEntity(todo);
        todoRepository.updateTodoContent(report);
    }

    private Todo setTodoEntity(TodoForm reqTodo) {
        Todo todo = new Todo();
        todo.setId(reqTodo.getId());
        todo.setContent(reqTodo.getContent());
        todo.setStatus(todo.getStatus());
        if (StringUtils.hasText(reqTodo.getLimitDate())) {
            todo.setLimitDate(LocalDate.parse(reqTodo.getLimitDate()).atStartOfDay());
        } else {
            todo.setLimitDate(null); // 入力がない場合はnull
        }
        return todo;
    }

    public void saveTodo(TodoForm reqTodo) {
        Todo saveTodo = setTodoEntity(reqTodo);

        if (saveTodo.getLimitDate() != null) {
            java.time.LocalDate dateOnly = saveTodo.getLimitDate().toLocalDate();
            saveTodo.setLimitDate(dateOnly.atTime(23, 59, 59));
        }
        if(saveTodo.getStatus() == null){
            saveTodo.setStatus(1);
        }

        todoRepository.save(saveTodo);
    }
}