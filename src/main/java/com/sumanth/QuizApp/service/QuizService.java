package com.sumanth.QuizApp.service;


import com.sumanth.QuizApp.dao.QuestionDao;
import com.sumanth.QuizApp.dao.QuizDao;
import com.sumanth.QuizApp.mode.Question;
import com.sumanth.QuizApp.mode.QuestionWrappper;
import com.sumanth.QuizApp.mode.Quiz;
import com.sumanth.QuizApp.mode.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrappper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz =  quizDao.findById(id);

        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrappper> questionsForUser = new ArrayList<>();

        for(Question q : questionsFromDB)
        {
            QuestionWrappper qw = new QuestionWrappper(q.getId(), q.getQuestionTitle(), q.getCategory(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int rightAns = 0;
        int i = 0;
        for(Response response : responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                rightAns++;
            i++;
        }

        return new ResponseEntity<>(rightAns, HttpStatus.OK);
    }
}
