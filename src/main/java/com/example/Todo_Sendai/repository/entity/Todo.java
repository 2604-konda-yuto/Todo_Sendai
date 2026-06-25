package com.example.Todo_Sendai.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Data
public class Todo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    @PreUpdate
    public void preUpdate(){
        this.updateDate = LocalDateTime.now();
    }
    @Column(name = "limitDate")
    private LocalDateTime limitDate;
    @Column
    private Integer status;
}
