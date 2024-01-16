package com.sumanth.QuizApp.controller;

import com.sumanth.QuizApp.mode.Question;
import com.sumanth.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
        //return new ArrayList<>();
    }

    @GetMapping("category/{cat}")
    public List<Question> getQuestionsByCategory(@PathVariable("cat") String category)
    {
        return questionService.getQuestionsByCategory(category);
    }


    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@RequestBody Question question)
    {
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

}
