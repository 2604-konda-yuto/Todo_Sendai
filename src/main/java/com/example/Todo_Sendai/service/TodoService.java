package com.example.Todo_Sendai.service;

import com.example.Todo_Sendai.controller.form.TodoForm;
import com.example.Todo_Sendai.repository.TodoRepository;
import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
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

        for (Todo result : results) {
            TodoForm form = new TodoForm();
            form.setId(result.getId());
            form.setContent(result.getContent());

            if (result.getLimitDate() != null) {
                form.setLimitDate(result.getLimitDate().toString());
            }

            reports.add(form);
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
        Todo updateTodo = setTodoEntity(todo);
        // 引数を個別に分解して渡す
        todoRepository.updateTodoContent(
                updateTodo.getId(),
                updateTodo.getContent(),
                updateTodo.getLimitDate()
        );
    }

    private Todo setTodoEntity(TodoForm reqTodo) {
        Todo todo = new Todo();
        todo.setId(reqTodo.getId());
        todo.setContent(reqTodo.getContent());
        todo.setStatus(reqTodo.getStatus());
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

        if (saveTodo.getStatus() == null) {
            saveTodo.setStatus(1);
        }

        todoRepository.save(saveTodo);
    }
}