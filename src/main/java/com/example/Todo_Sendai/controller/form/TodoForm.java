package com.example.Todo_Sendai.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Data
public class TodoForm {
    private Integer id;
    private String content;
    private Integer status = 0;
    private String limitDate;
    private LocalDateTime updateDate;
}