package com.demo.quizserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.quizserver.model.Question;
import com.demo.quizserver.service.QuestionService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("question")
// Handles Requests For route /question
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // /question?category -> For getting questions that belong to specific category
    @GetMapping("")
    public List<Question> getCategory(@RequestParam String category) {
        return questionService.getByCategory(category);
    }

    // /question/allQuestions -> For getting all the questions from db
    @GetMapping("allQuestions")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    // /question/createQuestion -> For creating a new question
    @PostMapping("createQuestion")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
