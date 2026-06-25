package com.example.Todo_Sendai.service;

import com.example.Todo_Sendai.controller.form.TodoForm;
import com.example.Todo_Sendai.repository.TodoRepository;
import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    /*
     * レコード全件取得処理
     */
    public  List<TodoForm> findAllTodo() {
        List<Todo> results = todoRepository.findByOrderByLimitDateAsc();
        List<TodoForm> todo = setTodoForm(results);
        return todo;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<TodoForm> setTodoForm(List<Todo> results) {
        List<TodoForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TodoForm todo = new TodoForm();
            Todo result = results.get(i);
            todo.setId(result.getId());
            todo.setContent(result.getContent());

            reports.add(todo);
        }
        return reports;
    }

    /*
     * レコード追加
     */
    public void saveTodo(TodoForm reqTodo) {
        Todo saveTodo = setTodoEntity(reqTodo);
        todoRepository.save(saveTodo);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Todo setTodoEntity(TodoForm reqTodo) {
        Todo todo = new Todo();
        todo.setContent(reqTodo.getContent());
        todo.setLimitDate(reqTodo.getLimitDate());

        return todo;
    }

}
