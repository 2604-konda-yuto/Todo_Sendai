package com.example.Todo_Sendai.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
public class Todo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;
    @Column
    private Integer status;
    @Column
    private LocalDateTime limitDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date updateDate;
}
