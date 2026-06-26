package com.example.Todo_Sendai.repository;


import com.example.Todo_Sendai.repository.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> { @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Todo t SET t.status = :status WHERE t.id = :id")
    void updateTodo(@Param("id") Integer id, @Param("status") Integer status);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Todo t SET t.content = :content, t.limitDate = :limitDate WHERE t.id = :id")
    void updateTodoContent(
            @Param("id") Integer id,
            @Param("content") String content,
            @Param("limitDate") java.time.LocalDateTime limitDate
    );
    public List<Todo> findByLimitDateBetweenAndStatusAndContentContaining
            (LocalDateTime start, LocalDateTime end,
             Integer status, String content);
    public List<Todo> findByLimitDateBetweenAndContentContaining(
            LocalDateTime start,
            LocalDateTime end,
            String content
    );

    List<Todo> findAllByOrderByLimitDateAsc();
}
