package com.example.Todo_Sendai.repository;


import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> { @Transactional
    @Modifying
    @Query("UPDATE Todo t SET t.status = :status WHERE t.id = :id") // 実際の変数名に合わせて調整してください
    void updateTodo(@Param("id") Integer id, @Param("status") Integer status);
    @Modifying
    @Query("UPDATE Todo t SET t.status = :status WHERE t.id = :id") //
    void updateTodoContent(Todo report);
}
