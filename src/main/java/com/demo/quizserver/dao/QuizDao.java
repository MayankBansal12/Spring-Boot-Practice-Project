package com.demo.quizserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.quizserver.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
}