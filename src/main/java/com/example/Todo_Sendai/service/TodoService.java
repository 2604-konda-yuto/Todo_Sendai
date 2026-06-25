package com.example.Todo_Sendai.service;

import com.example.Todo_Sendai.Controller.form.TodoForm;
import com.example.Todo_Sendai.repository.TodoRepository;
import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository taskRepository;

    /*
     * レコード全件取得処理
     */
    public List<TodoForm> findAllReport() {
        List<Todo> results = reportRepository.findAll();
        List<TodoForm> reports = setReportForm(results);
        return reports;
    }
    /*
     * DBから取得したデータをFormに設定
     */
    private List<TodoForm> setReportForm(List<Todo> results) {
        List<TodoForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TodoForm report = new TodoForm();
            Todo result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            reports.add(report);
        }
        return reports;
    }

}
