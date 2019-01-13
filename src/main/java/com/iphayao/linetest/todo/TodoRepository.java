package com.iphayao.linetest.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId(String userId);
    List<Todo> findByUserIdAndImportance(String userid, boolean importance);
    Todo findByIdAndUserId(int id, String userId);
}
