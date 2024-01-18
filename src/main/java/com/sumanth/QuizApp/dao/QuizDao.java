package com.sumanth.QuizApp.dao;

import com.sumanth.QuizApp.mode.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
