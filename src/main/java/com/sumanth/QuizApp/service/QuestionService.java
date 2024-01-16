package com.sumanth.QuizApp.service;

import com.sumanth.QuizApp.dao.QuestionDao;
import com.sumanth.QuizApp.mode.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Question Added";
    }

    public String updateQuestion(Question question) {
        questionDao.save(question);
        return "Updated Successfully";
    }

    public String deleteQuestion(int id) {
        questionDao.deleteById(id);
        return "Deleted Successfully";
    }
}
