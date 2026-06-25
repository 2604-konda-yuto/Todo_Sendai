package com.example.Todo_Sendai.controller;

import com.example.Todo_Sendai.controller.form.TodoForm;
import com.example.Todo_Sendai.service.TodoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ModelAndView top() throws ParseException {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        List<TodoForm> contentData = todoService.findAllTodo();
        LocalDate today = LocalDate.now();
        mav.addObject("today", today);
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("contents", contentData);
        return mav;
    }
    @GetMapping("/new")
    public ModelAndView newtodo(HttpSession session) {
        String todoerror = (String) session.getAttribute("todoerror");
        session.removeAttribute("todoerror");
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        TodoForm todoForm = new TodoForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        mav.addObject("todoerror", todoerror);
        // 準備した空のFormを保管
        mav.addObject("formModel", todoForm);
        return mav;
    }
    @PostMapping("/add")
    public ModelAndView addTodo(@ModelAttribute("formModel") TodoForm todoForm, HttpSession session) {
        if (!StringUtils.hasText(todoForm.getContent())) {
            session.setAttribute("todoerror", "投稿内容を記入してください");
            return new ModelAndView("/new");
        } else if (todoForm.getContent().length() > 255) {
            session.setAttribute("todoerror", "投稿内容は255文字以内で入力してください");
            return new ModelAndView("/new");
        } else if (todoForm.getLimitDate().isBlank()) {
            session.setAttribute("todoerror", "期限を入力してください");
            return new ModelAndView("/new");
        }
        // 投稿をテーブルに格納
        todoService.saveTodo(todoForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/status/update/{id}")
    public ModelAndView updateStatus(
            @PathVariable("id") Integer id,
            @RequestParam("status") Integer statusId
    ) {
        TodoForm todo = todoService.findById(id);
        todoService.updateStatus(id, statusId);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id, HttpSession session) {
        String todoerror = (String) session.getAttribute("todoerror");
        session.removeAttribute("todoerror");
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        TodoForm todo = todoService.editTodo(id);
        // 編集する投稿をセット
        mav.addObject("formModel", todo);
        // 画面遷移先を指定
        mav.setViewName("/edit");
        return mav;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateContent(
            @PathVariable Integer id,
            @ModelAttribute("formModel") TodoForm todo,
            HttpSession session
    ) {

        if (todo.getLimitDate() == null || todo.getLimitDate().isBlank()) {
            session.setAttribute("todoerror", "期限を入力してください");
            return new ModelAndView("redirect:/edit/" + id);
        }

        if (!StringUtils.hasText(todo.getContent())) {
            session.setAttribute("todoerror", "投稿内容を記入してください");
            return new ModelAndView("redirect:/edit/" + id);
        }

        if (todo.getContent().length() > 255) {
            session.setAttribute("todoerror", "投稿内容は255文字以内で入力してください");
            return new ModelAndView("redirect:/edit/" + id);
        }
        todo.setId(id);
        todoService.updateTodo(todo);
        return new ModelAndView("redirect:/");
    }
}