package com.example.Todo_Sendai.repository;


import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByOrderByLimitDateAsc();
}
