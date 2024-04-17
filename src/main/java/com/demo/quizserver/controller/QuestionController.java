package com.demo.quizserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.quizserver.model.Question;
import com.demo.quizserver.service.QuestionService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("question")
// Handles Requests For route /question
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // /question/allQuestions -> For getting all the questions from db
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getQuestions() {
        return questionService.getAllQuestions();
    }

    // /question?category -> For getting questions that belong to specific category
    @GetMapping("/")
    public ResponseEntity<List<Question>> getCategory(@RequestParam String category) {
        return questionService.getByCategory(category);
    }

    // /question/:id -> For reading a specific ques using id
    @GetMapping("{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable String id) {
        return questionService.getQuestion(Integer.parseInt(id));
    }

    // /question/add -> For creating a new question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // /question/:id -> Put for updating the value for that id
    @PutMapping("{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable String id, @RequestBody Question question) {
        return questionService.updateQuestion(Integer.parseInt(id), question);
    }

    // /question/:id -> For deleting the question with that id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        return questionService.deleteQuestion(Integer.parseInt(id));
    }
}
